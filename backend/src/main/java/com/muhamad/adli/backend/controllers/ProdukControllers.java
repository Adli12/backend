package com.muhamad.adli.backend.controllers;

import com.muhamad.adli.backend.dtos.ProdukRequest;
import com.muhamad.adli.backend.dtos.ProdukResponse;
import com.muhamad.adli.backend.services.ProdukService;
import com.muhamad.adli.backend.utils.ResponseStatus;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/produk")
@AllArgsConstructor
@Slf4j
public class ProdukControllers {

    private ProdukService produkService;

    // INSERT DATA
    @PostMapping(value = "/post")
    public ResponseEntity<ResponseStatus<ProdukResponse>> tambahProduk(@Valid @RequestBody ProdukRequest request, Errors errors){
        ResponseStatus<ProdukResponse> responseData = new  ResponseStatus<>();
        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setStatusCode(HttpStatus.OK);
        responseData.setData(produkService.tambahProduk(request));
        responseData.getMessages().add("berhasil menambahkan data produk");
        log.info("SUCCESFULLY INSERT - PRODUCT WITH  DATA: {}" , request);
        return ResponseEntity.ok(responseData);
    }

    //UPDATE DATA
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ResponseStatus <ProdukResponse>> updateProduk(@Valid @PathVariable(value = "id") Long id,
                                                                        @RequestBody ProdukRequest request, Errors errors){
        ResponseStatus<ProdukResponse> responseData = new ResponseStatus<>();
        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setStatusCode(HttpStatus.OK);
        responseData.setData(produkService.updateProduk(id, request));
        responseData.getMessages().add("update product dengan ID : " + id + " | berhasil di update");
        log.info("SUCCESFULLY  UPDATE - PRODUCT WITH  DATA: {}", request);
        return ResponseEntity.ok(responseData);
    }

    // GET ALL DATA
    @GetMapping(value = "/index")
    public ResponseEntity<ResponseStatus<List<ProdukResponse>>> ambilSemuaData(){
        List<ProdukResponse> produkResponses = produkService.ambilSemuaData();
        ResponseStatus<List<ProdukResponse>> responseData = new ResponseStatus<>();
        if (produkResponses.isEmpty()){
            responseData.setStatus(true);
            responseData.setStatusCode(HttpStatus.OK);
            responseData.getMessages().add("data tidak ditemukan.");
            responseData.setData(Collections.emptyList());
        }else {
            responseData.setStatus(true);
            responseData.setStatusCode(HttpStatus.OK);
            responseData.getMessages().add("data ditemukan, menampilkan data dari database");
            responseData.setData(produkResponses);
        }
        log.info("SUCCESSFULLY GET ALL DATA - ADMINISTRATOR");
        return ResponseEntity.ok(responseData);
    }

    // DELETE
    @DeleteMapping(value = "/delete/{id}")
    public void deleteData( @PathVariable(value = "id") Long id){
        produkService.deleteProduk(id);
        String message = "produk dengan ID : " + id + "| berhasil  di hapus";
        log.info(message);
    }
}

