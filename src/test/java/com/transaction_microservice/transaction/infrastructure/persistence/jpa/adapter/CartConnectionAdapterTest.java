package com.transaction_microservice.transaction.infrastructure.persistence.jpa.adapter;

import com.transaction_microservice.transaction.application.dto.cart_dto.CartResponse;
import com.transaction_microservice.transaction.application.mapper.cart_mapper.ICartResponseMapper;
import com.transaction_microservice.transaction.domain.model.cart.CartModel;
import com.transaction_microservice.transaction.infrastructure.http.feign.ICartFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CartConnectionAdapterTest {

    @Mock
    private ICartFeignClient cartFeignClient;

    @Mock
    private ICartResponseMapper cartResponseMapper;

    @InjectMocks
    private CartConnectionAdapter cartConnectionAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Obtiene el carrito por usuario")
    void testGetCartByUser() {
        Long userId = 1L;
        CartResponse cartResponse = new CartResponse();
        List<CartResponse> cartResponseList = Collections.singletonList(cartResponse);
        CartModel cartModel = new CartModel();
        List<CartModel> cartModelList = Collections.singletonList(cartModel);

        when(cartFeignClient.getCartByUser()).thenReturn(cartResponseList);
        when(cartResponseMapper.cartResponseToCartModel(cartResponseList)).thenReturn(cartModelList);

        List<CartModel> result = cartConnectionAdapter.getCartByUser(userId);

        assertEquals(cartModelList, result);
    }

    @Test
    @DisplayName("Elimina el carrito por usuario")
    void testDeleteCartByUser() {
        Long userId = 1L;

        cartConnectionAdapter.deleteCartByUser(userId);

        verify(cartFeignClient).deleteCartByUser();
    }
}