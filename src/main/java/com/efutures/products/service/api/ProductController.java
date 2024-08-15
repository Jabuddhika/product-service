package com.efutures.products.service.api;

import com.efutures.products.service.dto.ProductDetailsInputDTO;
import com.efutures.products.service.dto.util.ValidationGroups;
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
    public ResponseEntity<?> createProduct(@Validated({ValidationGroups.Save.class})
                                               @RequestBody ProductDetailsInputDTO inputDTO) {
        productService.createProduct(inputDTO);
        return ResponseEntity.ok("Product successfully created");
    }
    @PatchMapping("/update/{product_id}")
    public ResponseEntity<?> updateProduct( @RequestBody @Valid ProductDetailsInputDTO dto,
                                            @PathVariable("product_id") Integer productId){
        dto.setProductId(productId);
        productService.updateProduct(dto);
        return ResponseEntity.ok("product successfully updated");
    }
    @DeleteMapping("/remove/{product_id}")
    public ResponseEntity<?> removeProduct(@PathVariable("product_id") Integer productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok("product successfully deleted");
    }
    @GetMapping("/by-category")
    public ResponseEntity<?> findAllProductByCategoryName(@RequestParam(value = "category",required = true)
                                                             String categoryName){
        return ResponseEntity.ok(productService.findProductByCategoryName(categoryName));
    }
    @GetMapping("/by-price")
    public ResponseEntity<?> findPremiumProductByPrice() {
        return ResponseEntity.ok(productService.findPremiumProductByPrice());
    }








}
