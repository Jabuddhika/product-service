package com.efutures.products.service.repository;

import com.efutures.products.service.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    Optional<ProductCategory> findByName(String name);
}
