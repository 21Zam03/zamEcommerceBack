package com.zam.zamMarket.service;

import com.zam.zamMarket.entity.RoleEntity;
import com.zam.zamMarket.entity.SaleEntity;
import com.zam.zamMarket.payload.request.CategoryCreateRequest;
import com.zam.zamMarket.payload.request.CategoryUpdateRequest;
import com.zam.zamMarket.payload.request.ClientUpdateRequest;
import com.zam.zamMarket.payload.response.*;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface MaintenanceService {

    public MessageResponse createProduct(MultipartFile file, Integer categoryId, String name, Double price,
                                         String brand, Integer discount) throws Exception;
    public MessageResponse updateProduct(Integer productId, MultipartFile file, Integer imageId, Integer categoryId,
                                         String name, Double price, String brand, Integer discount,
                                         Boolean active) throws Exception;
    public Page<ProductSearchResponse> searchProducts(
            String search_parameter, String name, String category,
            String brand, String discount, String active_parameter,
            String sortField, String sortDirection, int page, int size);

    public MessageResponse updateClient(ClientUpdateRequest client);
    public Page<ClientSearchResponse> searchClients(
            String search_parameter, String type_doc, String country_parameter,
            String genre_parameter, String date_parameter, String active_parameter,
            String sortField, String sortDirection, int page, int size);

    public SaleEntity updateSale(SaleEntity sale);
    public Page<SaleSearchResponse> searchSales();

    public MessageResponse createCategory(CategoryCreateRequest category);
    public MessageResponse updateCategory(CategoryUpdateRequest category);

    public RoleEntity createRole(RoleEntity role);
    public RoleEntity updateRole(RoleEntity role);
    public Page<RoleSearchResponse> searchRoles();

}
