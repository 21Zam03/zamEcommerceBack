package com.zam.zamMarket.repository;

import com.zam.zamMarket.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    boolean existsByName(String name);
    boolean existsByNameAndProductIdNot(String name, Integer productId);

}
