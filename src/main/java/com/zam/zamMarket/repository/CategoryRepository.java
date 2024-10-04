package com.zam.zamMarket.repository;

import com.zam.zamMarket.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    boolean existsByName(String name);
    boolean existsByNameAndCategoryIdNot(String name, Integer categoryId);

}
