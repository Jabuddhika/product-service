package com.efutures.products.service.api;

import com.efutures.products.service.dto.ProductDetailsInputDTO;
import com.efutures.products.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDetailsInputDTO inputDTO) {
        productService.createProduct(inputDTO);
        return ResponseEntity.ok("Product successfully created");
    }



}
