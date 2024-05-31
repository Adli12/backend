package com.muhamad.adli.backend.repositories;

import com.muhamad.adli.backend.models.MasterProduk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdukRepository extends JpaRepository<MasterProduk, Long> {
}