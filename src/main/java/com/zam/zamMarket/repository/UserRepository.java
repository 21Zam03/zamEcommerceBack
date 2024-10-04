package com.zam.zamMarket.repository;

import com.zam.zamMarket.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    public Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);
    boolean existsByEmailAndUserIdNot(String identityDocumentNumber, Integer clientId);
}
