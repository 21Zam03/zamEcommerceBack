package com.zam.zamMarket.service;

import com.zam.zamMarket.payload.response.ProductResponse;
import org.springframework.data.domain.Page;

public interface CatalogService {

    public Page<ProductResponse> getProducts(int page, int size);

}
