package com.zam.zamMarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shop_cart_detail")
public class ShopCartDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_cart_detail_id")
    private Integer shopCartDetailId;

    @ManyToOne
    @JoinColumn(name = "shop_cart_id")
    private ShopCartEntity shopCart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;

}
