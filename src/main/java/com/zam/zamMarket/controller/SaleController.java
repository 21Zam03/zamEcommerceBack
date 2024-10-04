package com.zam.zamMarket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales")
@Slf4j
public class SaleController {
/*
    public final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<SaleEntity> createSale(@RequestBody SaleDto saleDto) {
        Integer clientId = saleDto.getIdCliente();
        List<ProductDto> productsList = saleDto.getListaProductos();
        Date date = saleDto.getFecha();
        return new ResponseEntity<>(saleService.createSale(clientId, productsList, date), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleEntity> getSale(@PathVariable(value = "id") String saleId) {
        return new ResponseEntity<>(saleService.getSale(Integer.valueOf(saleId)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SaleEntity>> listSales() {
        return new ResponseEntity<>(saleService.listSales(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<SaleEntity> updateSale(@RequestBody SaleEntity saleEntity) {
        return new ResponseEntity<>(saleService.updateSale(saleEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteSale(@PathVariable(value = "id") String saleId) {
        saleService.deleteSale(Integer.valueOf(saleId));
    }
*/
}
