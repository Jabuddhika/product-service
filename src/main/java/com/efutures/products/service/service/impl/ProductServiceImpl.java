package com.efutures.products.service.service.impl;

import com.efutures.products.service.dto.ProductDetailsInputDTO;
import com.efutures.products.service.entity.Product;
import com.efutures.products.service.entity.ProductCategory;
import com.efutures.products.service.exception.CustomException;
import com.efutures.products.service.repository.ProductCategoryRepository;
import com.efutures.products.service.repository.ProductRepository;
import com.efutures.products.service.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductCategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    private final ModelMapper mapper;
    public ProductServiceImpl(ProductCategoryRepository categoryRepository, ProductRepository productRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
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
        product.setStatus('S');
        productRepository.save(product);
    }
    @Override
    public void updateProduct(ProductDetailsInputDTO dto) {
        Product product = productRepository.findById(dto.getProductId()).
                orElseThrow(() -> new CustomException("product cant be found", HttpStatus.NOT_FOUND));
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        productRepository.save(product);
    }
    @Override
    public void deleteProduct(Integer productId) {
        Product product = productRepository.findById(productId).
                orElseThrow(() -> new CustomException("product id cant be found", HttpStatus.NOT_FOUND));
        product.setStatus('D');
        productRepository.save(product);
    }
    private ProductCategory createNewCategory(String name, String description) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(name);
        productCategory.setDescription(description);
        return productCategory;
    }
}
