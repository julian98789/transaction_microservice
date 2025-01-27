package com.transaction_microservice.transaction.application.mapper.cart_mapper;

import com.transaction_microservice.transaction.application.dto.cart_dto.CartResponse;
import com.transaction_microservice.transaction.domain.model.cart.CartModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICartResponseMapper {
    List<CartModel> cartResponseToCartModel(List<CartResponse> cartResponse);
}
