package com.muhamad.adli.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class ProdukResponse {
    private Long id;
    private String namaProduk;
    private BigDecimal harga;
    private String kategori;
    private Integer stok;
}