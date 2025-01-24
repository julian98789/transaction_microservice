package com.transaction_microservice.transaction.infrastructure.persistence.jpa.adapter;

import com.transaction_microservice.transaction.application.dto.sale_dto.SaleRequest;
import com.transaction_microservice.transaction.application.dto.sale_dto.SaleDetailsRequest;
import com.transaction_microservice.transaction.application.mapper.sale_mapper.ISaleRequestMapper;
import com.transaction_microservice.transaction.domain.model.SalesModel;
import com.transaction_microservice.transaction.domain.spi.ISaleReportConnectionPersistencePort;
import com.transaction_microservice.transaction.domain.usecase.SaleModelUseCase;
import com.transaction_microservice.transaction.infrastructure.http.feign.IReportFeignClient;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ISaleReportConnectionAdapter implements ISaleReportConnectionPersistencePort {

    private final IReportFeignClient iReportFeignClient;
    private final ISaleRequestMapper saleRequestMapper;

    private static final Logger logger = LoggerFactory.getLogger(ISaleReportConnectionAdapter.class);

    @Override
    public void createSaleReport(SalesModel salesModel) {
        // Mapear SalesModel a SaleRequest
        SaleRequest saleRequest = saleRequestMapper.saleModelToSaleRequest(salesModel);

        // Log para mostrar los detalles de la venta
        if (salesModel.getSaleDetails() != null && !salesModel.getSaleDetails().isEmpty()) {
            salesModel.getSaleDetails().forEach(detail ->
                    logger.info("Sale Detail - ArticleId: {}, Quantity: {}, Price: {}, Subtotal: {}",
                            detail.getArticleId(),
                            detail.getQuantity(),
                            detail.getPrice(),
                            detail.getSubtotal()
                    )
            );
        } else {
            logger.info("No se encontro la lista .");
        }

        // Llamar al cliente Feign
        iReportFeignClient.createSaleReport(saleRequest);
    }
}

