package com.transaction_microservice.transaction.infrastructure.persistence.jpa.adapter;

import com.transaction_microservice.transaction.application.dto.cartdto.CartResponse;
import com.transaction_microservice.transaction.application.mapper.cartmapper.ICartResponseMapper;
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
import static org.mockito.Mockito.*;

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
    @DisplayName("Should retrieve cart by user ID")
    void shouldRetrieveCartByUserId() {
        Long userId = 1L;
        CartResponse cartResponse = new CartResponse();
        List<CartResponse> cartResponseList = Collections.singletonList(cartResponse);
        CartModel cartModel = new CartModel();
        List<CartModel> cartModelList = Collections.singletonList(cartModel);

        when(cartFeignClient.getCartByUser()).thenReturn(cartResponseList);
        when(cartResponseMapper.cartResponseToCartModel(cartResponseList)).thenReturn(cartModelList);

        List<CartModel> result = cartConnectionAdapter.getCartByUser(userId);

        assertEquals(cartModelList, result);

        verify(cartFeignClient, times(1)).getCartByUser();
        verify(cartResponseMapper, times(1)).cartResponseToCartModel(cartResponseList);
    }

    @Test
    @DisplayName("Should delete cart by user ID")
    void shouldDeleteCartByUserId() {
        Long userId = 1L;

        doNothing().when(cartFeignClient).deleteCartByUser();

        cartConnectionAdapter.deleteCartByUser(userId);

        verify(cartFeignClient, times(1)).deleteCartByUser();
    }
}