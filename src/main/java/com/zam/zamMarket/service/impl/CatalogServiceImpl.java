package com.zam.zamMarket.service.impl;

import com.zam.zamMarket.entity.ProductEntity;
import com.zam.zamMarket.payload.response.ProductResponse;
import com.zam.zamMarket.repository.ProductRepository;
import com.zam.zamMarket.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final ProductRepository productRepository;

    @Autowired
    public CatalogServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductResponse> getProducts(int page, int size) {
        Sort.Direction direction = Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "productId"));
        Page<ProductEntity> products = productRepository.findAll(pageable);
        List<ProductResponse> productResponses = new ArrayList<>();
        for ( int i = 0; i < products.getContent().size(); i++ ) {
            ProductResponse productResponse = ProductResponse.builder()
                    .productId(products.getContent().get(i).getProductId())
                    .urlImage(products.getContent().get(i).getImage().getUrl())
                    .categoryName(products.getContent().get(i).getCategory().getName())
                    .name(products.getContent().get(i).getName())
                    .price(products.getContent().get(i).getPrice())
                    .brand(products.getContent().get(i).getBrand())
                    .discount(products.getContent().get(i).getDiscount())
                    .isActive(products.getContent().get(i).getIsActive())
                    .build();
            productResponses.add(productResponse);
        }
        return new PageImpl<>(productResponses, pageable, products.getTotalElements());
    }

}
