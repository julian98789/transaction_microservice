package com.transaction_microservice.transaction.domain.usecase;

import com.transaction_microservice.transaction.domain.api.ISupplyModelServicePort;
import com.transaction_microservice.transaction.domain.exception.CartEmptyException;
import com.transaction_microservice.transaction.domain.model.cart.CartModel;
import com.transaction_microservice.transaction.domain.model.sale.SaleDetailsModel;
import com.transaction_microservice.transaction.domain.model.sale.SaleReportModel;
import com.transaction_microservice.transaction.domain.model.sale.SalesModel;
import com.transaction_microservice.transaction.domain.security.IAuthenticationSecurityPort;
import com.transaction_microservice.transaction.domain.spi.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaleModelUseCaseTest {

    private ICartConnectionPersistencePort cartConnectionPersistencePort;
    private IAuthenticationSecurityPort authenticationPersistencePort;
    private ISaleModelPersistencePort saleModelPersistencePort;
    private IStockConnectionPersistencePort stockConnectionPersistencePort;
    private ISaleReportConnectionPersistencePort saleReportConnectionPersistencePort;
    private ISupplyModelServicePort supplyModelServicePort;
    private ISaleDetailModelPersistencePort saleDetailModelPersistencePort;
    private SaleModelUseCase saleModelUseCase;

    @BeforeEach
    void setUp() {
        cartConnectionPersistencePort = mock(ICartConnectionPersistencePort.class);
        authenticationPersistencePort = mock(IAuthenticationSecurityPort.class);
        saleModelPersistencePort = mock(ISaleModelPersistencePort.class);
        stockConnectionPersistencePort = mock(IStockConnectionPersistencePort.class);
        saleReportConnectionPersistencePort = mock(ISaleReportConnectionPersistencePort.class);
        supplyModelServicePort = mock(ISupplyModelServicePort.class);
        saleDetailModelPersistencePort = mock(ISaleDetailModelPersistencePort.class);

        saleModelUseCase = new SaleModelUseCase(
                cartConnectionPersistencePort,
                authenticationPersistencePort,
                saleModelPersistencePort,
                stockConnectionPersistencePort,
                saleReportConnectionPersistencePort,
                supplyModelServicePort,
                saleDetailModelPersistencePort
        );
    }

    @Test
    @DisplayName("Compra artículos del carrito y verifica el reporte de venta")
    void testBuyItemsFromTheCart() {
        Long userId = 1L;
        List<CartModel> cartModels = new ArrayList<>();

        CartModel cart = new CartModel();
        cart.setArticleId(100L);
        cart.setQuantity(2);
        cartModels.add(cart);

        when(authenticationPersistencePort.getAuthenticatedUserId()).thenReturn(userId);
        when(cartConnectionPersistencePort.getCartByUser(userId)).thenReturn(cartModels);
        when(stockConnectionPersistencePort.isStockSufficient(100L, 2)).thenReturn(true);
        when(stockConnectionPersistencePort.getArticlePriceById(100L)).thenReturn(50.0);
        when(saleModelPersistencePort.saveSale(any(SalesModel.class))).thenAnswer(invocation -> {
            SalesModel salesModel = invocation.getArgument(0);
            salesModel.setId(1L);
            return salesModel;
        });
        when(saleDetailModelPersistencePort.saveSaleDetailsModel(any(SaleDetailsModel.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(saleReportConnectionPersistencePort.createSaleReport(any(SalesModel.class))).thenReturn(new SaleReportModel());

        SaleReportModel saleReport = saleModelUseCase.buyItemsFromTheCart();

        assertNotNull(saleReport);
        verify(cartConnectionPersistencePort).deleteCartByUser(userId);
    }

    @Test
    @DisplayName("Lanza CartEmptyException cuando el carrito está vacío")
    void testBuyItemsFromTheCart_EmptyCart() {
        Long userId = 1L;
        when(authenticationPersistencePort.getAuthenticatedUserId()).thenReturn(userId);
        when(cartConnectionPersistencePort.getCartByUser(userId)).thenReturn(new ArrayList<>());

        assertThrows(CartEmptyException.class, () -> saleModelUseCase.buyItemsFromTheCart());
    }

}
