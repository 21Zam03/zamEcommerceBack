package com.zam.zamMarket.service.impl;

import com.zam.zamMarket.entity.ClientEntity;
import com.zam.zamMarket.entity.ProductEntity;
import com.zam.zamMarket.entity.ShopCartDetailEntity;
import com.zam.zamMarket.entity.ShopCartEntity;
import com.zam.zamMarket.exceptions.NotFoundException;
import com.zam.zamMarket.payload.response.MessageResponse;
import com.zam.zamMarket.repository.ClientRepository;
import com.zam.zamMarket.repository.ProductRepository;
import com.zam.zamMarket.repository.ShopCartDetailRepository;
import com.zam.zamMarket.repository.ShopCartRepository;
import com.zam.zamMarket.service.ShopCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class ShopCartServiceImpl implements ShopCartService {

    private final ShopCartRepository shopCartRepository;
    private final ClientRepository clientRepository;
    private final ShopCartDetailRepository shopCartDetailRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ShopCartServiceImpl(ShopCartRepository shopCartRepository, ClientRepository clientRepository,
                               ShopCartDetailRepository shopCartDetailRepository, ProductRepository productRepository) {
        this.shopCartRepository = shopCartRepository;
        this.clientRepository = clientRepository;
        this.shopCartDetailRepository = shopCartDetailRepository;
        this.productRepository = productRepository;
    }

    @Override
    public MessageResponse addProductToCart(Integer productId, Integer quantity, Integer clientId) {
        ShopCartEntity shopCart = shopCartRepository.findByClient_ClientId(clientId).orElseThrow(()-> {
            log.info("Shop cart not found");
            return new NotFoundException("Shop cart not found");
        });

        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(()-> {
            log.info("Product not found");
            return new NotFoundException("Product not found");
        });

        ShopCartDetailEntity shopCartDetailEntity = ShopCartDetailEntity.builder()
                .shopCart(shopCart)
                .product(productEntity)
                .quantity(quantity)
                .build();

        shopCartDetailRepository.save(shopCartDetailEntity);
        log.info("Added product to cart");
        return new MessageResponse("Successfully added product to shop cart");
    }

    @Override
    public MessageResponse createCart(Integer clientId, Integer discount) {
        ClientEntity clientEntity = clientRepository.findById(clientId).orElseThrow(() -> {
            log.info("Client not found");
            return new NotFoundException("Client not found");
        });

        ShopCartEntity shopCartEntity = ShopCartEntity.builder()
                .client(clientEntity)
                .creationDate(LocalDate.now())
                .discount(discount)
                .build();
        this.shopCartRepository.save(shopCartEntity);
        return new MessageResponse("Shop cart was created");
    }

}
