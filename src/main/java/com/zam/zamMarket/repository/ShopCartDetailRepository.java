package com.zam.zamMarket.repository;

import com.zam.zamMarket.entity.ShopCartDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopCartDetailRepository extends JpaRepository<ShopCartDetailEntity, Integer> {
}
