package com.transaction_microservice.transaction.domain.usecase;

import com.transaction_microservice.transaction.domain.api.ISaleModelServicePort;
import com.transaction_microservice.transaction.domain.api.ISupplyModelServicePort;
import com.transaction_microservice.transaction.domain.exception.InsufficientStockException;
import com.transaction_microservice.transaction.domain.exception.PurchaseException;
import com.transaction_microservice.transaction.domain.model.CartModel;
import com.transaction_microservice.transaction.domain.model.SaleDetailsModel;
import com.transaction_microservice.transaction.domain.model.SalesModel;
import com.transaction_microservice.transaction.domain.security.IAuthenticationSecurityPort;
import com.transaction_microservice.transaction.domain.spi.ICartConnectionPersistencePort;
import com.transaction_microservice.transaction.domain.spi.ISaleModelPersistencePort;
import com.transaction_microservice.transaction.domain.spi.ISaleReportConnectionPersistencePort;
import com.transaction_microservice.transaction.domain.spi.IStockConnectionPersistencePort;
import com.transaction_microservice.transaction.domain.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaleModelUseCase implements ISaleModelServicePort {

    private final ICartConnectionPersistencePort cartConnectionPersistencePort;
    private final IAuthenticationSecurityPort authenticationPersistencePort;
    private final ISaleModelPersistencePort saleModelPersistencePort;
    private final IStockConnectionPersistencePort stockConnectionPersistencePort;
    private final ISaleReportConnectionPersistencePort saleReportConnectionPersistencePort;
    private final ISupplyModelServicePort supplyModelServicePort;

    private static final Logger logger = LoggerFactory.getLogger(SaleModelUseCase.class);

    public SaleModelUseCase(ICartConnectionPersistencePort cartConnectionPersistencePort, IAuthenticationSecurityPort authenticationPersistencePort, ISaleModelPersistencePort saleModelPersistencePort, IStockConnectionPersistencePort stockConnectionPersistencePort, ISaleReportConnectionPersistencePort saleReportConnectionPersistencePort, ISupplyModelServicePort supplyModelServicePort) {
        this.cartConnectionPersistencePort = cartConnectionPersistencePort;
        this.authenticationPersistencePort = authenticationPersistencePort;
        this.saleModelPersistencePort = saleModelPersistencePort;
        this.stockConnectionPersistencePort = stockConnectionPersistencePort;
        this.saleReportConnectionPersistencePort = saleReportConnectionPersistencePort;
        this.supplyModelServicePort = supplyModelServicePort;
    }

    @Override
    public void buyCart() {
        Long userId = authenticationPersistencePort.getAuthenticatedUserId();
        List<CartModel> articlesInCart = cartConnectionPersistencePort.getCartByUser(userId);
        try {
            articlesInCart.forEach(cart -> validateStockAvailability(cart.getArticleId(), cart.getQuantity()));
            SalesModel salesModel = createSales(articlesInCart, userId);
            articlesInCart.forEach(cart -> reduceStockQuantity(cart.getArticleId(), cart.getQuantity()));
            saleReportConnectionPersistencePort.createSaleReport(salesModel);
            cartConnectionPersistencePort.deleteCartByUser(userId);

        }catch (Exception e){
            throw new PurchaseException(Util.PURCHASE_ERROR, e);
        }

    }

    private SalesModel createSales(List<CartModel> articlesInCart, Long userId) {

        SalesModel sale = new SalesModel();
        sale.setUserId(userId);
        sale.setTotal(0.0);
        sale.setCreationDate(LocalDate.now());

        List<SaleDetailsModel> saleDetailsModels = new ArrayList<>();
        for (CartModel cart : articlesInCart) {
            SaleDetailsModel saleDetailsModel = new SaleDetailsModel();
            saleDetailsModel.setArticleId(cart.getArticleId());
            saleDetailsModel.setQuantity(cart.getQuantity());
            saleDetailsModel.setPrice(stockConnectionPersistencePort.getArticlePriceById(cart.getArticleId()));
            saleDetailsModel.setSubtotal(saleDetailsModel.getPrice() * saleDetailsModel.getQuantity());

            saleDetailsModels.add(saleDetailsModel);
        }

        SalesModel saleFinal = saleModelPersistencePort.saveSale(sale);


        saleDetailsModels.forEach(detail -> detail.setSale(saleFinal));


        saleDetailsModels.forEach(detail -> saleModelPersistencePort.saveSaleDetailsModel(detail));


        Double total = saleDetailsModels.stream().mapToDouble(SaleDetailsModel::getSubtotal).sum();
        saleFinal.setTotal(total);


        saleModelPersistencePort.saveSale(saleFinal);

        saleFinal.setSaleDetails(saleDetailsModels);



        return saleFinal;
    }





    private void validateStockAvailability(Long articleId, int totalQuantity) {
        if (!stockConnectionPersistencePort.isStockSufficient(articleId, totalQuantity)) {
            String nextSupplyDate = supplyModelServicePort.getNextSupplyDate(articleId).toString();

            throw new InsufficientStockException(Util.INSUFFICIENT_STOCK, nextSupplyDate);
        }
    }

    private void reduceStockQuantity(Long articleId, int totalQuantity) {
        stockConnectionPersistencePort.reduceArticleQuantity(articleId, totalQuantity);
    }
}
