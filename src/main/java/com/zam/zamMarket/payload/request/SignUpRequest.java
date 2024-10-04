package com.zam.zamMarket.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zam.zamMarket.Enums.GenreEnum;
import com.zam.zamMarket.Enums.IdTypeEnum;
import com.zam.zamMarket.validation.annotations.EnumValue;
import com.zam.zamMarket.validation.annotations.NumericValue;
import com.zam.zamMarket.validation.annotations.TextValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequest {

    @NotNull(message = "Email must not be null")
    @Size(min = 5, max = 50, message = "Email must have between 5 and 50 characters")
    @Email(message = "Email must be an email address with correct format")
    private String email;

    @NotNull(message = "Password must not be null")
    @Size(min = 10, max = 100, message = "Password must have between 10 and 100 characters")
    private String password;

    @NotNull(message = "Role List must not be null")
    private Set<Integer> roleList;

    @NotNull(message = "Firstname must not be null")
    @Size(min = 5, max = 100, message = "First name must have between 5 and 100 characters")
    @TextValue(message = "Firstname must not contain numeric values")
    private String firstName;

    @NotNull(message = "Lastname must not be null")
    @Size(min = 5, max = 100, message = "Last name must have between 5 and 100 characters")
    @TextValue(message = "Lastname must not contain numeric values")
    private String lastName;

    @NotNull(message = "Type identity document must not be null")
    @EnumValue(enumClass = IdTypeEnum.class, message = "Invalid type identity Document")
    private String typeIdentityDocument;

    @NotNull(message = "Identity document number must not be null")
    @Size(min = 8, max = 20, message = "Identity document number must have between 8 and 20 characters")
    @NumericValue(message = "Identity document number must be a value numeric")
    private String identityDocumentNumber;

    @NotNull(message = "Phone country must not be null")
    @Size(min = 3, max = 10, message = "Phone country code must have between 3 and 10 characters")
    private String phoneCountryCode;

    @NumericValue(message = "Phone number must be a value numeric")
    @NotNull(message = "Phone number must not be null")
    @Size(min = 9, max = 20, message = "Phone number must have between 9 and 20 characters")
    private String phoneNumber;

    @NotNull(message = "Country must not be null")
    @Size(min = 4, max = 100, message = "Country must have between 4 and 100 characters")
    @TextValue(message = "Country must not contain numeric values")
    private String country;

    @NotNull(message = "Genre must not be null")
    @EnumValue(enumClass = GenreEnum.class, message = "Invalid genre")
    private String genre;

    @NotNull(message = "Birthdate must not be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    @NotNull(message = "Active must not be null")
    private boolean active;

}
