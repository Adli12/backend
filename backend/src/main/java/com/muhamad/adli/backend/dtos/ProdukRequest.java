package com.muhamad.adli.backend.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class ProdukRequest {

    @NotEmpty(message = "nama produk tidak boleh kosong")
    private String namaProduk;

    @NotNull(message = "Harga produk tidak boleh null")
    @Min(value = 1, message = "Harga produk harus lebih dari 0")
    private BigDecimal harga;

    @NotEmpty(message = "Kategori produk tidak boleh kosong")
    private String kategori;

    @NotNull(message = "Stok produk tidak boleh null")
    @Min(value = 0, message = "Stok produk harus 0 atau lebih")
    private Integer stok;

}