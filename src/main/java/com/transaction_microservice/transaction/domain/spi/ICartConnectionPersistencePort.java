package com.transaction_microservice.transaction.domain.spi;

import com.transaction_microservice.transaction.domain.model.cart.CartModel;

import java.util.List;

public interface ICartConnectionPersistencePort {

    List<CartModel> getCartByUser(Long userId);

    void deleteCartByUser(Long userId);
}
