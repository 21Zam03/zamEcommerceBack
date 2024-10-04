package com.zam.zamMarket.repository;

import com.zam.zamMarket.entity.SaleDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetailEntity, String> {

    @Query("SELECT d FROM SaleDetailEntity d WHERE d.sale.saleId = :saleId")
    public List<SaleDetailEntity> findBySaleQuery(String saleId);

}
