package com.muhamad.adli.backend.usecases;

import com.muhamad.adli.backend.dtos.ProdukRequest;
import com.muhamad.adli.backend.dtos.ProdukResponse;
import com.muhamad.adli.backend.exceptions.ProdukNotFoundException;
import com.muhamad.adli.backend.exceptions.ProdukValidationException;
import com.muhamad.adli.backend.models.MasterProduk;
import com.muhamad.adli.backend.repositories.ProdukRepository;
import com.muhamad.adli.backend.services.ProdukService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor

public class ProductUsecase implements ProdukService {

    private ProdukRepository produkRepository;

    // tambah data produk
    @Transactional
    public ProdukResponse tambahProduk(ProdukRequest data) {
        if(data == null){
            throw new ProdukValidationException("data produk tidak boleh null");
        }
        if (data.getNamaProduk() == null || data.getNamaProduk().isEmpty()){
            throw new ProdukValidationException("nama produk harus lebih dari 0");
        }
        if (data.getHarga() == null || data.getHarga().compareTo(BigDecimal.ZERO) <= 0){
            throw new ProdukValidationException("Harga produk harus lebih dari 0 ");
        }
        if (data.getKategori() == null || data.getKategori().isEmpty()){
            throw new ProdukValidationException("kategor produk harus lebih dari 0");
        }
        if (data.getStok() == null || data.getStok() < 0){
            throw new ProdukValidationException("stok produk harus 0 atau lebih");
        }

        MasterProduk prosesproduk = MasterProduk.builder()
                .namaProduk(data.getNamaProduk())
                .harga(data.getHarga())
                .kategori(data.getKategori())
                .stok(data.getStok())
                .build();

        MasterProduk save = produkRepository.save(prosesproduk);

        return ProdukResponse.builder()
                .id(save.getId())
                .namaProduk(save.getNamaProduk())
                .harga(save.getHarga())
                .kategori(save.getKategori())
                .stok(save.getStok())
                .build();
    }

    // update data produk
    @Transactional
    public ProdukResponse updateProduk(Long id, ProdukRequest data){
        Optional<MasterProduk> optionalProduk = produkRepository.findById(id);

    if (optionalProduk.isEmpty()){
        throw new ProdukNotFoundException("prduk dengan id" + id + "tidak di temukan");
    }
    MasterProduk produk = optionalProduk.get();

    if (data == null ){
        throw new ProdukNotFoundException("data Produk tidak boleh 0");
    }
    if (data.getNamaProduk() == null || data.getNamaProduk().isEmpty()){
        throw new ProdukNotFoundException("nama produk tidak boleh 0");
    }
    if  (data.getHarga() == null || data.getHarga().compareTo(BigDecimal.ZERO) <= 0){
        throw new ProdukNotFoundException("harga produk tidak boleh 0");
    }
    if (data.getKategori() == null || data.getKategori().isEmpty()){
        throw new ProdukNotFoundException("kategori produk tidak boleh 0");
    }
    if (data.getStok() == null || data.getStok() < 0){
        throw new ProdukNotFoundException("stok produk harus 0 atau lebih");
    }
    produk.setNamaProduk(data.getNamaProduk());
    produk.setHarga(data.getHarga());
    produk.setKategori(data.getKategori());
    produk.setStok(data.getStok());
    MasterProduk updateProduk = produkRepository.save(produk);
    return ProdukResponse.builder()
            .id(updateProduk.getId())
            .namaProduk(updateProduk.getNamaProduk())
            .harga(updateProduk.getHarga())
            .kategori(updateProduk.getKategori())
            .stok(updateProduk.getStok())
            .build();
    }

    // ambil semua data produk
    public List<ProdukResponse> ambilSemuaData() {
        List<MasterProduk> daftarProduk = produkRepository.findAll();
        List<ProdukResponse> daftarResponsProduk = new ArrayList<>();

        for (MasterProduk produk : daftarProduk){
            ProdukResponse responsProduk = ProdukResponse.builder()
                    .id(produk.getId())
                    .namaProduk(produk.getNamaProduk())
                    .harga(produk.getHarga())
                    .kategori(produk.getKategori())
                    .stok(produk.getStok())
                    .build();
            daftarResponsProduk.add(responsProduk);
        }
        return daftarResponsProduk;
    }

    // delete Produk
    @Transactional
    public void deleteProduk(Long id){
        // cek id produk yang ingin di hapus
        if (!produkRepository.existsById(id)){
            throw new ProdukNotFoundException("Produk dengan id " + id + "tidak ditemukan");
        }
        produkRepository.deleteById(id);
    }
}



