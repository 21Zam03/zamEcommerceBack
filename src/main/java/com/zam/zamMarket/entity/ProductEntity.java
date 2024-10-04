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
@Table(name = "product", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name"})
})
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @OneToOne
    @JoinColumn(name = "image_id")
    private ImageEntity image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    //@Size(min = 1, max = 100, message = "Name must have between 1 and 100 characters")
    //@NotNull(message = "Name is obligatory")
    @Column(name = "name")
    private String name;

    //@NotNull(message = "Price is obligatory")
    //@Min(value = 0, message = "Price must be positive")
    @Column(name = "price")
    private Double price;

    //@Size(min = 1, max = 100, message = "Name must have between 1 and 100 characters")
    //@NotNull(message = "Brand is obligatory")
    @Column(name = "brand")
    private String brand;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "active")
    private Boolean isActive;

}
