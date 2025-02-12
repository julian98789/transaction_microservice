package com.transaction_microservice.transaction.application.mapper.cartmapper;

import com.transaction_microservice.transaction.application.dto.cartdto.CartResponse;
import com.transaction_microservice.transaction.domain.model.cart.CartModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICartResponseMapper {
    List<CartModel> cartResponseToCartModel(List<CartResponse> cartResponse);
}
