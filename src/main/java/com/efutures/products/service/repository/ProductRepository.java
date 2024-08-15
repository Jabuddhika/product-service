package com.efutures.products.service.repository;

import com.efutures.products.service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("SELECT p FROM Product p " +
            "LEFT JOIN FETCH p.productCategory pc " +
            "LEFT JOIN FETCH p.productCommentList pcom " +
            "WHERE pc.name = :categoryName AND p.status <> 'D'")
    List<Product> findAllByCategoryName(@Param("categoryName") String categoryName);
    @Query("SELECT p FROM Product p " +
            "LEFT JOIN FETCH p.productCategory pc " +
            "LEFT JOIN FETCH p.productCommentList pcom " +
            "WHERE p.price >= 500 AND p.status <> 'D'")
    List<Product> findPremiumProductsByPrice();

}
