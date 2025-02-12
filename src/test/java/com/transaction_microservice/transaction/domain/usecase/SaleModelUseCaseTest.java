package com.transaction_microservice.transaction.domain.usecase;

import com.transaction_microservice.transaction.domain.model.cart.CartModel;
import com.transaction_microservice.transaction.domain.model.sale.SaleDetailsModel;
import com.transaction_microservice.transaction.domain.model.sale.SaleReportModel;
import com.transaction_microservice.transaction.domain.model.sale.SalesModel;
import com.transaction_microservice.transaction.domain.security.IAuthenticationSecurityPort;
import com.transaction_microservice.transaction.domain.spi.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaleModelUseCaseTest {

    @Mock
    private ICartConnectionPersistencePort cartConnectionPersistencePort;

    @Mock
    private IAuthenticationSecurityPort authenticationPersistencePort;

    @Mock
    private ISaleModelPersistencePort saleModelPersistencePort;

    @Mock
    private IStockConnectionPersistencePort stockConnectionPersistencePort;

    @Mock
    private ISaleReportConnectionPersistencePort saleReportConnectionPersistencePort;

    @Mock
    private ISaleDetailModelPersistencePort saleDetailModelPersistencePort;

    @InjectMocks
    private SaleModelUseCase saleModelUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Buy items from the cart - should process the sale and generate a report")
    void shouldProcessSaleAndGenerateReport() {
        Long userId = 1L;
        Long articleId = 1L;
        List<CartModel> articlesInCart = new ArrayList<>();
        CartModel cartModel = new CartModel();
        cartModel.setArticleId(1L);
        cartModel.setQuantity(2);
        articlesInCart.add(cartModel);

        SalesModel salesModel = new SalesModel();

        SaleDetailsModel saleDetailsModel = new SaleDetailsModel();

        salesModel.setSaleDetails(List.of(saleDetailsModel));

        SaleReportModel saleReportModel = new SaleReportModel();

        when(authenticationPersistencePort.getAuthenticatedUserId()).thenReturn(userId);
        when(cartConnectionPersistencePort.getCartByUser(userId)).thenReturn(articlesInCart);
        when(stockConnectionPersistencePort.isStockSufficient(articleId, 2)).thenReturn(true);
        when(stockConnectionPersistencePort.getArticlePriceById(articleId)).thenReturn(100.0);
        when(saleModelPersistencePort.saveSale(any(SalesModel.class))).thenReturn(salesModel);
        when(saleDetailModelPersistencePort.saveSaleDetailsModel(any(SaleDetailsModel.class))).thenReturn(saleDetailsModel);
        when(saleReportConnectionPersistencePort.createSaleReport(any(SalesModel.class))).thenReturn(saleReportModel);

        SaleReportModel result = saleModelUseCase.buyItemsFromTheCart();

        assertEquals(saleReportModel, result);

        verify(cartConnectionPersistencePort, times(1)).deleteCartByUser(userId);
        verify(stockConnectionPersistencePort, times(1)).isStockSufficient(articleId, 2);
        verify(stockConnectionPersistencePort, times(1)).getArticlePriceById(articleId);
        verify(saleModelPersistencePort, times(1)).saveSale(any(SalesModel.class));
        verify(saleDetailModelPersistencePort, times(1)).saveSaleDetailsModel(any(SaleDetailsModel.class));
        verify(saleReportConnectionPersistencePort, times(1)).createSaleReport(any(SalesModel.class));
    }
}
