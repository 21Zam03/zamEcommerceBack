package com.zam.zamMarket.controller;

import com.zam.zamMarket.payload.response.ProductResponse;
import com.zam.zamMarket.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(CatalogController.API_PATH)
public class CatalogController {

    public static final String API_PATH = "/api/catalog";

    private final CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping()
    public ResponseEntity<Page<ProductResponse>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(this.catalogService.getProducts(page, size), HttpStatus.OK);
    }

}


