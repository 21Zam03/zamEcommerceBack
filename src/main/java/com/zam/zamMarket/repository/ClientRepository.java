package com.zam.zamMarket.repository;

import com.zam.zamMarket.entity.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {

    @Query("SELECT c FROM ClientEntity c WHERE c.user.userId = :userId")
    public ClientEntity findByUserQuery(String userId);

    boolean existsByIdentityDocumentNumber(String identityDocumentNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByIdentityDocumentNumberAndClientIdNot(String identityDocumentNumber, Integer clientId);

    boolean existsByPhoneNumberAndClientIdNot(String phoneNumber, Integer clientId);

    @Query(value = "SELECT " +
            "c.client_id, " +
            "CONCAT(c.first_name, ' ', c.last_name) AS full_name, " +
            "u.email, " +
            "c.type_identity_document, " +
            "c.identity_document_number, " +
            "CONCAT(c.phone_country_code, ' ', c.phone_number) AS full_phone_number, " +
            "c.country, " +
            "c.genre, " +
            "c.birthdate, " +
            "c.active " +
            "FROM " +
            "   client c " +
            "INNER JOIN " +
            "   `user` u " +
            "ON c.user_id = u.user_id " +
            "WHERE (:search_parameter IS NULL " +
            "   OR (CAST(:search_parameter AS UNSIGNED) IS NOT NULL AND client_id = CAST(:search_parameter AS UNSIGNED)) " +
            "   OR (CAST(:search_parameter AS UNSIGNED) IS NOT NULL AND c.identity_document_number = CAST(:search_parameter AS UNSIGNED)) " +
            "   OR (CAST(:search_parameter AS UNSIGNED) IS NOT NULL AND c.phone_number = CAST(:search_parameter AS UNSIGNED)) " +
            "   OR (CAST(:search_parameter AS UNSIGNED) = 0 AND :search_parameter IS NOT NULL AND :search_parameter NOT REGEXP '^[0-9]+$' " +
            "       AND (" +
            "           CONCAT(c.first_name, ' ', c.last_name) LIKE CONCAT('%', :search_parameter, '%') " +
            "           OR u.email LIKE CONCAT('%', :search_parameter, '%') " +
            "           OR CONCAT(c.phone_country_code, ' ', c.phone_number) like CONCAT('%', :search_parameter, '%') " +
            "       ))) " +
            "AND (:type_doc IS NULL OR c.type_identity_document = :type_doc) " +
            "AND (:country_parameter IS NULL OR c.country = :country_parameter) " +
            "AND (:genre_parameter IS NULL or c.genre = :genre_parameter) " +
            "AND (:date_parameter IS NULL or c.birthdate = :date_parameter) " +
            "AND (:active_parameter IS NULL or c.active = :active_parameter)",
            countQuery = "SELECT COUNT(*) " +
                    "FROM client c " +
                    "INNER JOIN `user` u ON c.user_id = u.user_id " +
                    "WHERE (:search_parameter IS NULL " +
                    "   OR (CAST(:search_parameter AS UNSIGNED) IS NOT NULL AND client_id = CAST(:search_parameter AS UNSIGNED)) " +
                    "   OR (CAST(:search_parameter AS UNSIGNED) IS NOT NULL AND c.identity_document_number = CAST(:search_parameter AS UNSIGNED)) " +
                    "   OR (CAST(:search_parameter AS UNSIGNED) IS NOT NULL AND c.phone_number = CAST(:search_parameter AS UNSIGNED)) " +
                    "   OR (CAST(:search_parameter AS UNSIGNED) = 0 AND :search_parameter IS NOT NULL AND :search_parameter NOT REGEXP '^[0-9]+$' " +
                    "       AND (" +
                    "           CONCAT(c.first_name, ' ', c.last_name) LIKE CONCAT('%', :search_parameter, '%') " +
                    "           OR u.email LIKE CONCAT('%', :search_parameter, '%') " +
                    "           OR CONCAT(c.phone_country_code, ' ', c.phone_number) like CONCAT('%', :search_parameter, '%') " +
                    "       ))) " +
                    "AND (:type_doc IS NULL OR c.type_identity_document = :type_doc) " +
                    "AND (:country_parameter IS NULL OR c.country = :country_parameter) " +
                    "AND (:genre_parameter IS NULL or c.genre = :genre_parameter) " +
                    "AND (:date_parameter IS NULL or c.birthdate = :date_parameter) " +
                    "AND (:active_parameter IS NULL or c.active = :active_parameter)",
            nativeQuery = true)
    public Page<Object[]> searchClients(String search_parameter, String type_doc, String country_parameter,
                                        String genre_parameter, String date_parameter, String active_parameter,
                                        Pageable pageable);
}
