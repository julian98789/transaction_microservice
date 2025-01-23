package com.transaction_microservice.transaction.infrastructure.persistence.jpa.adapter;

import com.transaction_microservice.transaction.application.dto.cart_dto.CartResponse;
import com.transaction_microservice.transaction.application.mapper.cart_mapper.ICartResponseMapper;
import com.transaction_microservice.transaction.domain.model.CartModel;
import com.transaction_microservice.transaction.domain.spi.ICartConnectionPersistencePort;
import com.transaction_microservice.transaction.infrastructure.http.feign.ICartFeignClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CartConnectionAdapter implements ICartConnectionPersistencePort {

    private final ICartFeignClient cartFeignClient;
    private final ICartResponseMapper cartResponseMapper;

    @Override
    public List<CartModel> getCartByUser(Long userId) {
       List<CartResponse> cartResponse = cartFeignClient.getCartByUser();
       return cartResponseMapper.cartResponseToCartModel(cartResponse);
    }

    @Override
    @Transactional
    public void deleteCartByUser(Long userId) {
        cartFeignClient.deleteCartByUser();
    }
}
