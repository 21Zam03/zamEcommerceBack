package com.zam.zamMarket.service;

import com.zam.zamMarket.payload.response.MessageResponse;

public interface ShopCartService {

    public MessageResponse addProductToCart(Integer productId, Integer quantity, Integer clientId);
    //public MessageResponse removeProductFromCart(Integer productId);
    //public MessageResponse updateProductQuantity(Integer productId, Integer quantity);
    public MessageResponse createCart(Integer clientId, Integer discount);
    //public void getCart();
    //public MessageResponse removeCart();

}
