package com.zam.zamMarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "claim")
public class ClaimEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_id")
    private Integer claimId;

    @Column(name = "message")
    private String message;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity byClient;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity attendedBy;

    @Column(name = "state")
    private String state;

    @Column(name = "active")
    private Boolean isActive;

}
