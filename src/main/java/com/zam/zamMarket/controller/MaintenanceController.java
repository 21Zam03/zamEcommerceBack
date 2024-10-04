package com.zam.zamMarket.controller;

import com.zam.zamMarket.entity.CategoryEntity;
import com.zam.zamMarket.payload.request.CategoryCreateRequest;
import com.zam.zamMarket.payload.request.CategoryUpdateRequest;
import com.zam.zamMarket.payload.request.ClientUpdateRequest;
import com.zam.zamMarket.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(MaintenanceController.API_PATH)
public class MaintenanceController {

    public static final String API_PATH = "/api/maintenance";
    public static final String PRODUCT_PATH = "/products";
    public static final String CLIENT_PATH = "/clients";
    public static final String CATEGORY_PATH = "/categories";
    public static final String BRAND_PATH = "/brands";

    private final MaintenanceService maintenanceService;

    @Autowired
    public MaintenanceController(MaintenanceService service) {
        this.maintenanceService = service;
    }

    @PostMapping(value = MaintenanceController.PRODUCT_PATH, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createProduct(
            @RequestPart("photo") MultipartFile file,
            @RequestPart("category") String category,
            @RequestPart("name") String name,
            @RequestPart("price") String price,
            @RequestPart("brand") String brand,
            @RequestPart("discount") String discount) throws Exception {
        return new ResponseEntity<>(
                this.maintenanceService.createProduct(file, Integer.parseInt(category), name,
                        Double.parseDouble(price), brand, Integer.parseInt(discount)), HttpStatus.CREATED);
    }

    @PutMapping(value = MaintenanceController.PRODUCT_PATH, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProduct(
            @RequestPart("productId") Integer productId,
            @RequestPart("fileImage") MultipartFile file,
            @RequestPart("imageId") Integer imageId,
            @RequestPart("category") Integer category,
            @RequestPart("name") String name,
            @RequestPart("price") Double price,
            @RequestPart("brand") String brand,
            @RequestPart("discount") Integer discount,
            @RequestPart("active") Boolean active) throws Exception {
        return new ResponseEntity<>(
                this.maintenanceService.updateProduct(productId, file, imageId, category, name, price, brand, discount, active), HttpStatus.OK);
    }

    @PutMapping(MaintenanceController.CLIENT_PATH)
    public ResponseEntity<?> updateClient(@RequestBody ClientUpdateRequest clientUpdateRequest) {;
        return new ResponseEntity<>(this.maintenanceService.updateClient(clientUpdateRequest), HttpStatus.OK);
    }

    @GetMapping(MaintenanceController.CLIENT_PATH)
    public ResponseEntity<?> searchClients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String searchParameter,
            @RequestParam(required = false) String type_doc,
            @RequestParam(required = false) String country_parameter,
            @RequestParam(required = false) String genre_parameter,
            @RequestParam(required = false) String date_parameter,
            @RequestParam(required = false) String active_parameter,
            @RequestParam(defaultValue = "client_id") String sortField,
            @RequestParam(defaultValue = "DESC") String sortDirection) {
        return new ResponseEntity<>(
                this.maintenanceService.searchClients(
                        searchParameter, type_doc, country_parameter, genre_parameter,
                        date_parameter, active_parameter, sortField, sortDirection,
                        page, size), HttpStatus.OK);
    }

    @PostMapping(value = MaintenanceController.CATEGORY_PATH)
    public ResponseEntity<?> createCategory(@RequestBody CategoryCreateRequest category) {
        return new ResponseEntity<>(
                this.maintenanceService.createCategory(category), HttpStatus.CREATED);
    }

    @PutMapping(value = MaintenanceController.CATEGORY_PATH)
    public ResponseEntity<?> updateCategory(@RequestBody CategoryUpdateRequest category) {
        return new ResponseEntity<>(
                this.maintenanceService.updateCategory(category), HttpStatus.OK);
    }


}
