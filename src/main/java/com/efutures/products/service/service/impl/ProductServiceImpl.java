package com.efutures.products.service.service.impl;

import com.efutures.products.service.dto.ProductDetailsInputDTO;
import com.efutures.products.service.entity.Product;
import com.efutures.products.service.entity.ProductCategory;
import com.efutures.products.service.repository.ProductCategoryRepository;
import com.efutures.products.service.repository.ProductRepository;
import com.efutures.products.service.service.ProductService;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductCategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    public ProductServiceImpl(ProductCategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }
    @Override
    public void createProduct(ProductDetailsInputDTO details) {
        ProductCategory productCategory = categoryRepository.findByName(details.getCategory())
                .orElseGet(() -> createNewCategory(details.getCategory(), details.getDescription()));

        Product product = new Product();
        product.setProductCategory(productCategory);
        product.setName(details.getName());
        product.setPrice(details.getPrice());
        product.setDescription(details.getDescription());

        productRepository.save(product);
    }
    private ProductCategory createNewCategory(String name, String description) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(name);
        productCategory.setDescription(description);
        return productCategory;
    }
}
