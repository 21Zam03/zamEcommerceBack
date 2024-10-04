package com.zam.zamMarket.service.impl;

import com.zam.zamMarket.entity.*;
import com.zam.zamMarket.exceptions.DuplicateException;
import com.zam.zamMarket.exceptions.NotFoundException;
import com.zam.zamMarket.payload.dtos.BlobDto;
import com.zam.zamMarket.payload.request.CategoryCreateRequest;
import com.zam.zamMarket.payload.request.CategoryUpdateRequest;
import com.zam.zamMarket.payload.request.ClientUpdateRequest;
import com.zam.zamMarket.payload.response.*;
import com.zam.zamMarket.repository.*;
import com.zam.zamMarket.service.FireBaseStorageService;
import com.zam.zamMarket.service.MaintenanceService;
import com.zam.zamMarket.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MaintenanceServiceImpl implements MaintenanceService {

    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final FireBaseStorageService fireBaseStorageService;

    @Autowired
    public MaintenanceServiceImpl(ProductRepository productRepository, ClientRepository clientRepository,
                                  UserRepository userRepository, CategoryRepository categoryRepository,
                                  ImageRepository imageRepository, FireBaseStorageService fireBaseStorageService) {
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.imageRepository = imageRepository;
        this.fireBaseStorageService = fireBaseStorageService;
    }


    @Override
    @Transactional
    public MessageResponse createProduct(MultipartFile file, Integer categoryId, String name, Double price,
                                         String brand, Integer discount) throws Exception {
        boolean isDuplicate = productRepository.existsByName(name);
        if (isDuplicate) {
            throw new DuplicateException("Name "+ name + " is already registered in the system");
        }

        BlobDto blob = fireBaseStorageService.uploadFile(file);
        String fileExtension = FileUtil.getFileExtension(file);
        String fileName = FileUtil.getFileName(file);

        ImageEntity imageEntity = ImageEntity.builder()
                .fileName(fileName)
                .url(blob.getUrl())
                .fileSize(file.getSize())
                .extension(fileExtension)
                .isActive(true)
                .build();

        ImageEntity imageCreated = imageRepository.save(imageEntity);

        CategoryEntity categoryFound = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        ProductEntity productToCreate = ProductEntity.builder()
                .name(name)
                .price(price)
                .category(categoryFound)
                .brand(brand)
                .discount(discount)
                .image(imageCreated)
                .isActive(true)
                .build();
        ProductEntity productCreated = productRepository.save(productToCreate);
        log.info("Product created with id: {}", productCreated.getProductId());
        return new MessageResponse("Product created successfully");
    }

    @Override
    @Transactional
    public MessageResponse updateProduct(Integer productId, MultipartFile file, Integer imageId, Integer categoryId,
                                         String name, Double price, String brand, Integer discount,
                                         Boolean active) throws Exception {
        ProductEntity productToUpdate = productRepository.findById(productId)
                .orElseThrow(()-> {
                    log.warn("Product to update was not found");
                    return new NotFoundException("Product to update was not found");
                });
        boolean isDuplicate = productRepository.existsByNameAndProductIdNot(name, productId);
        if (isDuplicate) {
            throw new DuplicateException("Name "+ name + " is already registered in the system");
        }

        ImageEntity imageToUpdate = imageRepository.findById(imageId)
                .orElseThrow(()-> new NotFoundException("Image to update was not found"));

        fireBaseStorageService.deleteFile(imageToUpdate.getFileName());

        BlobDto blob = fireBaseStorageService.uploadFile(file);
        String fileExtension = FileUtil.getFileExtension(file);
        String fileName = FileUtil.getFileName(file);

        imageToUpdate.setFileName(fileName);
        imageToUpdate.setUrl(blob.getMediaLink());
        imageToUpdate.setFileSize(blob.getSize());
        imageToUpdate.setExtension(fileExtension);
        ImageEntity imageUpdated = imageRepository.save(imageToUpdate);

        CategoryEntity categoryFound = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        productToUpdate.setImage(imageUpdated);
        productToUpdate.setCategory(categoryFound);
        productToUpdate.setName(name);
        productToUpdate.setPrice(price);
        productToUpdate.setBrand(brand);
        productToUpdate.setDiscount(discount);
        productToUpdate.setIsActive(active);

        ProductEntity productUpdated = productRepository.save(productToUpdate);
        log.info("Product was updated with id: {}", productUpdated.getProductId());
        return new MessageResponse("Product updated successfully");
    }

    @Override
    public Page<ProductSearchResponse> searchProducts(
            String search_parameter, String name, String category,
            String brand, String discount, String active_parameter,
            String sortField, String sortDirection, int page, int size) {

        return null;
    }

    @Override
    @Transactional
    public MessageResponse updateClient(ClientUpdateRequest client) {
        ClientEntity clientToUpdate = clientRepository.findById(client.getClientId())
                .orElseThrow(()-> {
                    log.warn("Client to update was not found");
                    return new NotFoundException("Client to update was not found");
                });
        boolean isDuplicate = clientRepository.existsByIdentityDocumentNumberAndClientIdNot(client.getIdentityDocumentNumber(), client.getClientId());
        if (isDuplicate) {
            throw new DuplicateException("Identity document number "+ client.getIdentityDocumentNumber() + " is already registered in the system");
        }
        UserEntity userToUpdate = userRepository.findById(client.getUserId())
                .orElseThrow(() -> {
                    log.warn("User to update was not found");
                    return new NotFoundException("User to update was not found");
                });
        userToUpdate.setEmail(client.getEmail());
        UserEntity userUpdated = userRepository.save(userToUpdate);

        clientToUpdate.setUser(userUpdated);
        clientToUpdate.setFirstName(client.getFirstName());
        clientToUpdate.setLastName(client.getLastName());
        clientToUpdate.setTypeIdentityDocument(client.getTypeIdentityDocument());
        clientToUpdate.setIdentityDocumentNumber(client.getIdentityDocumentNumber());
        clientToUpdate.setPhoneNumber(client.getPhoneNumber());
        clientToUpdate.setPhoneCountryCode(client.getPhoneCountryCode());
        clientToUpdate.setCountry(client.getCountry());
        clientToUpdate.setGenre(client.getGenre());
        clientToUpdate.setBirthdate(client.getBirthdate());
        clientToUpdate.setIsActive(client.isActive());

        ClientEntity clientUpdated = clientRepository.save(clientToUpdate);
        log.info("Client was updated with id: {}", clientUpdated.getClientId());
        return new MessageResponse("Client was updated successfully");
    }

    @Override
    public Page<ClientSearchResponse> searchClients(
            String search_parameter, String type_doc, String country_parameter,
            String genre_parameter, String date_parameter, String active_parameter,
            String sortField, String sortDirection, int page, int size) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        Page<Object[]> results = clientRepository.searchClients(search_parameter, type_doc, country_parameter,
                genre_parameter, date_parameter, active_parameter, pageable);
        if (results.isEmpty()) {
            throw new NotFoundException("There are not results");
        }
        List<ClientSearchResponse> list = results.getContent().stream().map(result -> {
            Integer clientId = ((Number) result[0]).intValue();
            String fullName = ((String) result[1]);
            String email = ((String) result[2]);
            String typeIdentityDocument = ((String) result[3]);
            String identityDocumentNumber = ((String) result[4]);
            String fullPhoneNumber = ((String) result[5]);
            String country = ((String) result[6]);
            String genre = ((String) result[7]);
            LocalDate birthdate = ((Date) result[8]).toLocalDate();
            boolean active = ((boolean) result[9]);
            return new ClientSearchResponse(clientId, fullName, email, typeIdentityDocument, identityDocumentNumber,
                    fullPhoneNumber, country, genre, birthdate, active);
        }).collect(Collectors.toList());
        return new PageImpl<>(list, pageable, results.getTotalElements());
    }

    @Override
    public SaleEntity updateSale(SaleEntity sale) {
        return null;
    }

    @Override
    public Page<SaleSearchResponse> searchSales() {
        return null;
    }

    @Override
    public MessageResponse createCategory(CategoryCreateRequest category) {
        boolean isDuplicate = categoryRepository.existsByName(category.getName());
        if (isDuplicate) {
            throw new DuplicateException("Name "+ category.getName() + " is already registered in the system");
        }

        CategoryEntity categoryToCreate = CategoryEntity.builder()
                .name(category.getName())
                .description(category.getDescription())
                .isActive(true)
                .build();

        CategoryEntity categoryCreated = categoryRepository.save(categoryToCreate);
        log.info("Category was created with id: {}", categoryCreated.getCategoryId());
        return new MessageResponse("Category was created successfully");
    }

    @Override
    public MessageResponse updateCategory(CategoryUpdateRequest category) {
        CategoryEntity categoryToUpdate = categoryRepository.findById(category.getCategoryId())
                .orElseThrow(()-> {
                    log.warn("Category to update was not found");
                    return new NotFoundException("Category to update was not found");
                });
        boolean isDuplicate = categoryRepository.existsByNameAndCategoryIdNot(category.getName(), category.getCategoryId());
        if (isDuplicate) {
            throw new DuplicateException("Name "+ category.getName() + " is already registered in the system");
        }

        categoryToUpdate.setName(category.getName());
        categoryToUpdate.setDescription(category.getDescription());
        categoryToUpdate.setIsActive(category.getIsActive());

        CategoryEntity categoryUpdated = categoryRepository.save(categoryToUpdate);
        log.info("Category was updated with id: {}", categoryUpdated.getCategoryId());
        return new MessageResponse("Category was updated successfully");
    }

    @Override
    public RoleEntity createRole(RoleEntity role) {
        return null;
    }

    @Override
    public RoleEntity updateRole(RoleEntity role) {
        return null;
    }

    @Override
    public Page<RoleSearchResponse> searchRoles() {
        return null;
    }


}
