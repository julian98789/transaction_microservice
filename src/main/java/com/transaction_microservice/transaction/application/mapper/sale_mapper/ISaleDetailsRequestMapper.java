package com.transaction_microservice.transaction.application.mapper.sale_mapper;

import com.transaction_microservice.transaction.application.dto.sale_dto.SaleDetailsRequest;
import com.transaction_microservice.transaction.domain.model.SaleDetailsModel;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ISaleDetailsRequestMapper {

    SaleDetailsRequest saleDetailsModelToSaleDetailsRequest(SaleDetailsModel saleDetailsModel);

}
