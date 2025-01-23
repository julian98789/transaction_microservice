package com.transaction_microservice.transaction.infrastructure.http.feign;

import com.transaction_microservice.transaction.application.dto.cart_dto.CartResponse;
import com.transaction_microservice.transaction.domain.util.Util;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = Util.CART_SERVICE_NAME, url = Util.CART_SERVICE_URL)
public interface ICartFeignClient {

    @GetMapping("/api/cart/get-cart-by-user")
    List<CartResponse> getCartByUser();

    @DeleteMapping("/api/cart/delete-cart")
    void deleteCartByUser();

}