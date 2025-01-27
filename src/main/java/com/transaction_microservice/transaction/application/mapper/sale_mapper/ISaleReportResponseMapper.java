package com.transaction_microservice.transaction.application.mapper.sale_mapper;

import com.transaction_microservice.transaction.application.dto.sale_dto.SaleReportResponse;
import com.transaction_microservice.transaction.domain.model.sale.SaleReportModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISaleReportResponseMapper {

    SaleReportModel saleReportResponseToSaleReportModel(SaleReportResponse saleReportResponse);

    SaleReportResponse saleReportModelToSaleReportResponse(SaleReportModel saleReportModel);
}
