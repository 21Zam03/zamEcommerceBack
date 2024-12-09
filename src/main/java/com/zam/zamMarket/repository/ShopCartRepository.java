package com.zam.zamMarket.repository;

import com.zam.zamMarket.entity.ShopCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopCartRepository extends JpaRepository<ShopCartEntity, Integer> {

    public Optional<ShopCartEntity> findByClient_ClientId(Integer clientId);

}
