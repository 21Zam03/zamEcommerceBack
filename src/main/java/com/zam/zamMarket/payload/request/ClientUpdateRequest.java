package com.zam.zamMarket.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zam.zamMarket.Enums.GenreEnum;
import com.zam.zamMarket.Enums.IdTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientUpdateRequest {

    private Integer clientId;
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private IdTypeEnum typeIdentityDocument;
    private String identityDocumentNumber;
    private String phoneNumber;
    private String phoneCountryCode;
    private String country;
    private GenreEnum genre;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthdate;
    private boolean active;

}
