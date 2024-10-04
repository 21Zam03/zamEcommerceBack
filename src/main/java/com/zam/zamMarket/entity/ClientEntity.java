package com.zam.zamMarket.entity;

import com.zam.zamMarket.Enums.GenreEnum;
import com.zam.zamMarket.Enums.IdTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Integer clientId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "type_identity_document", nullable = false)
    @Enumerated(EnumType.STRING)
    private IdTypeEnum typeIdentityDocument;

    @Column(name = "identity_document_number", unique = true, length = 20, nullable = false)
    private String identityDocumentNumber;

    @Column(name = "phone_country_code", length = 10, nullable = false)
    private String phoneCountryCode;

    @Column(name = "phone_number", unique = true, length = 20, nullable = false)
    private String phoneNumber;

    @Column(name = "country", length = 100, nullable = false)
    private String country;

    @Column(name = "genre", nullable = false)
    @Enumerated(EnumType.STRING)
    private GenreEnum genre;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "active", nullable = false)
    private Boolean isActive;

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    public String getAge() {
        LocalDate today_date = LocalDate.now();
        Period period = Period.between(this.birthdate, today_date);
        return String.valueOf(period.getYears());
    }

    public String getFullPhoneNumber() {
        return phoneCountryCode + phoneNumber;
    }

}
