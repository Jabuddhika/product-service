package com.efutures.products.service.service.impl;

import com.efutures.products.service.entity.ProductCategory;
import com.efutures.products.service.exception.CustomException;
import com.efutures.products.service.repository.ProductCategoryRepository;
import com.efutures.products.service.service.ProductCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository repository;

    public ProductCategoryServiceImpl(ProductCategoryRepository repository) {
        this.repository = repository;
    }
    @Override
    public ProductCategory findByName(String name) {
      return repository.findByName(name).
              orElseThrow(()-> new CustomException("product category is not found", HttpStatus.NOT_FOUND));
    }
}
