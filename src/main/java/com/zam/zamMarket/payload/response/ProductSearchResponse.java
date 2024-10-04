package com.zam.zamMarket.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSearchResponse {

    private Integer productId;
    private String categoryName;
    private String name;
    private Double price;
    private String brand;
    private Integer discount;
    private Boolean isActive;

}
