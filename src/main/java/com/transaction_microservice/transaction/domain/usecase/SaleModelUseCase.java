package com.transaction_microservice.transaction.domain.usecase;

import com.transaction_microservice.transaction.domain.api.ISaleModelServicePort;
import com.transaction_microservice.transaction.domain.api.ISupplyModelServicePort;
import com.transaction_microservice.transaction.domain.exception.CartEmptyException;
import com.transaction_microservice.transaction.domain.exception.InsufficientStockException;
import com.transaction_microservice.transaction.domain.exception.PurchaseException;
import com.transaction_microservice.transaction.domain.model.cart.CartModel;
import com.transaction_microservice.transaction.domain.model.sale.SaleDetailsModel;
import com.transaction_microservice.transaction.domain.model.sale.SaleReportModel;
import com.transaction_microservice.transaction.domain.model.sale.SalesModel;
import com.transaction_microservice.transaction.domain.security.IAuthenticationSecurityPort;
import com.transaction_microservice.transaction.domain.spi.*;
import com.transaction_microservice.transaction.domain.util.Util;

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
    private final ISaleDetailModelPersistencePort saleDetailModelPersistencePort;

    public SaleModelUseCase(ICartConnectionPersistencePort cartConnectionPersistencePort,
                            IAuthenticationSecurityPort authenticationPersistencePort,
                            ISaleModelPersistencePort saleModelPersistencePort,
                            IStockConnectionPersistencePort stockConnectionPersistencePort,
                            ISaleReportConnectionPersistencePort saleReportConnectionPersistencePort,
                            ISupplyModelServicePort supplyModelServicePort,
                            ISaleDetailModelPersistencePort saleDetailModelPersistencePort) {
        this.cartConnectionPersistencePort = cartConnectionPersistencePort;
        this.authenticationPersistencePort = authenticationPersistencePort;
        this.saleModelPersistencePort = saleModelPersistencePort;
        this.stockConnectionPersistencePort = stockConnectionPersistencePort;
        this.saleReportConnectionPersistencePort = saleReportConnectionPersistencePort;
        this.supplyModelServicePort = supplyModelServicePort;
        this.saleDetailModelPersistencePort = saleDetailModelPersistencePort;
    }

    @Override
    public SaleReportModel buyItemsFromTheCart() {
        Long userId = authenticationPersistencePort.getAuthenticatedUserId();
        List<CartModel> articlesInCart = cartConnectionPersistencePort.getCartByUser(userId);
        validateCartNotEmpty(articlesInCart);

        try {
            validateStockForAllArticles(articlesInCart);
            SalesModel salesModel = processSales(articlesInCart, userId);
            generateSaleReport(salesModel);
            clearUserCart(userId);

            return saleReportConnectionPersistencePort.createSaleReport(salesModel);
        } catch (Exception e) {
            throw new PurchaseException(Util.PURCHASE_ERROR, e);
        }
    }

    private void validateCartNotEmpty(List<CartModel> articlesInCart) {
        if (isCartEmpty(articlesInCart)) {
            throw new CartEmptyException(Util.CART_EMPTY);
        }
    }

    private boolean isCartEmpty(List<CartModel> articlesInCart) {
        return articlesInCart == null || articlesInCart.isEmpty();
    }

    private void validateStockForAllArticles(List<CartModel> articlesInCart) {
        articlesInCart.forEach(cart -> validateStockAvailability(cart.getArticleId(), cart.getQuantity()));
    }

    private void validateStockAvailability(Long articleId, int totalQuantity) {
        if (!stockConnectionPersistencePort.isStockSufficient(articleId, totalQuantity)) {
            String nextSupplyDate = supplyModelServicePort.getNextSupplyDate(articleId).toString();
            throw new InsufficientStockException(Util.INSUFFICIENT_STOCK, nextSupplyDate);
        }
    }

    private SalesModel processSales(List<CartModel> articlesInCart, Long userId) {
        SalesModel sale = initializeSale(userId);
        List<SaleDetailsModel> saleDetails = generateSaleDetails(articlesInCart, sale);
        return saveSaleWithDetails(sale, saleDetails);
    }

    private SalesModel initializeSale(Long userId) {
        SalesModel sale = new SalesModel();
        sale.setUserId(userId);
        sale.setCreationDate(LocalDate.now());
        return sale;
    }

    private List<SaleDetailsModel> generateSaleDetails(List<CartModel> articlesInCart, SalesModel sale) {
        List<SaleDetailsModel> saleDetailsModels = new ArrayList<>();
        double total = 0.0;

        for (CartModel cart : articlesInCart) {
            SaleDetailsModel saleDetailsModel = createSaleDetail(cart);
            saleDetailsModels.add(saleDetailsModel);
            total += saleDetailsModel.getSubtotal();
        }

        sale.setTotal(total);
        return saleDetailsModels;
    }

    private SaleDetailsModel createSaleDetail(CartModel cart) {
        SaleDetailsModel saleDetailsModel = new SaleDetailsModel();
        saleDetailsModel.setArticleId(cart.getArticleId());
        saleDetailsModel.setQuantity(cart.getQuantity());
        saleDetailsModel.setPrice(stockConnectionPersistencePort.getArticlePriceById(cart.getArticleId()));
        saleDetailsModel.setSubtotal(saleDetailsModel.getPrice() * saleDetailsModel.getQuantity());
        return saleDetailsModel;
    }

    private SalesModel saveSaleWithDetails(SalesModel sale, List<SaleDetailsModel> saleDetails) {
        SalesModel savedSale = saleModelPersistencePort.saveSale(sale);
        saleDetails.forEach(detail -> detail.setSale(savedSale));
        List<SaleDetailsModel> savedDetails = saleDetails.stream()
                .map(saleDetailModelPersistencePort::saveSaleDetailsModel)
                .toList();
        savedSale.setSaleDetails(savedDetails);
        return savedSale;
    }

    private void generateSaleReport(SalesModel salesModel) {
        saleReportConnectionPersistencePort.createSaleReport(salesModel);
    }

    private void clearUserCart(Long userId) {
        cartConnectionPersistencePort.deleteCartByUser(userId);
    }
}
