package com.muhamad.adli.backend.services;

import com.muhamad.adli.backend.dtos.ProdukRequest;
import com.muhamad.adli.backend.dtos.ProdukResponse;

import java.util.List;

public interface ProdukService {

    // method untuk tambah produk
    ProdukResponse tambahProduk(ProdukRequest data);

    // method untuk update produk
    ProdukResponse updateProduk(Long id, ProdukRequest data);

    // method untuk Ambil Semua Data produk
    List<ProdukResponse> ambilSemuaData();

    // method untuk delete produk
    void deleteProduk(Long id);
}