package com.transaction_microservice.transaction.domain.spi;

import com.transaction_microservice.transaction.domain.model.CartModel;

import java.util.List;

public interface ICartConnectionPersistencePort {

    List<CartModel> getCartByUser(Long userId);

    void deleteCartByUser(Long userId);
}
