package com.efutures.products.service.service.impl;

import com.efutures.products.service.dto.ProductDetailsInputDTO;
import com.efutures.products.service.entity.Product;
import com.efutures.products.service.entity.ProductCategory;
import com.efutures.products.service.repository.ProductCategoryRepository;
import com.efutures.products.service.repository.ProductRepository;
import com.efutures.products.service.service.ProductService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.math.BigDecimal;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final Faker faker = new Faker();

    @Test
    void testProductServiceOperations() {

        ProductCategory category = new ProductCategory();
        category.setName(faker.commerce().department());
        category.setDescription(faker.commerce().material());
        category = categoryRepository.save(category);

        ProductDetailsInputDTO createDetails = ProductDetailsInputDTO.builder()
                .name(faker.commerce().productName())
                .description(faker.commerce().material())
                .price(new BigDecimal(faker.commerce().price(0.1, 1000.0)))
                .category(category.getName())
                .build();

        assertDoesNotThrow(() -> productService.createProduct(createDetails));

        Product createdProduct = productRepository.findAllByCategoryName(category.getName()).get(0);
        assertNotNull(createdProduct);
        assertEquals(createDetails.getName(), createdProduct.getName());

        ProductDetailsInputDTO updateDetails = ProductDetailsInputDTO.builder()
                .productId(createdProduct.getId())
                .name(faker.commerce().productName())
                .description(faker.commerce().material())
                .price(new BigDecimal(faker.commerce().price(0.1, 1000.0)))
                .category(category.getName())
                .build();

        assertDoesNotThrow(() -> productService.updateProduct(updateDetails));

        Product updatedProduct = productRepository.findById(createdProduct.getId()).orElseThrow();
        assertEquals(updateDetails.getName(), updatedProduct.getName());
        assertEquals(updateDetails.getDescription(), updatedProduct.getDescription());

        assertDoesNotThrow(() -> productService.deleteProduct(createdProduct.getId()));

        Product deletedProduct = productRepository.findById(createdProduct.getId()).orElseThrow();
        assertEquals('D', deletedProduct.getStatus());
    }

}