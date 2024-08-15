package com.efutures.products.service.service;

import com.efutures.products.service.dto.ProductDetailsInputDTO;

public interface ProductService {

    void createProduct(ProductDetailsInputDTO detailsInputDTO);

    void updateProduct(ProductDetailsInputDTO dto);

    void deleteProduct(Integer productId);
}
