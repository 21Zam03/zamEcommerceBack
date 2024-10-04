package com.zam.zamMarket.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class ClientSearchResponse {

    private Integer clientId;
    private String fullName;
    private String email;
    private String typeIdentityDocument;
    private String identityDocumentNumber;
    private String fullPhoneNumber;
    private String country;
    private String genre;
    private LocalDate birthdate;
    private boolean active;

}
