package com.zam.zamMarket.controller;

import com.zam.zamMarket.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ShopCartController.API_PATH)
public class ShopCartController {

    private final ShopCartService shopCartService;

    public static final String API_PATH = "/api/shopcarts";

    @Autowired
    public ShopCartController(ShopCartService shopCartService) {
        this.shopCartService = shopCartService;
    }

    @PostMapping(value = ShopCartController.API_PATH)
    public ResponseEntity<?> createCart(@RequestParam Integer clientId, @RequestParam Integer discount) {
        return new ResponseEntity<>(
                this.shopCartService.createCart(clientId, discount), HttpStatus.CREATED);
    }

}
