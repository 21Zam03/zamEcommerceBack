package com.zam.zamMarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDto {

    private Integer idCliente;
    private List<ProductDto> listaProductos;
    private Date fecha;

}
