package com.efutures.products.service.service;

import com.efutures.products.service.dto.ProductDetailsInputDTO;
import com.efutures.products.service.dto.ProductOutPutDTO;
import java.util.List;

public interface ProductService {

    void createProduct(ProductDetailsInputDTO detailsInputDTO);

    void updateProduct(ProductDetailsInputDTO dto);

    void deleteProduct(Integer productId);

    List<ProductOutPutDTO> findProductByCategoryName(String categoryName);

    List<ProductOutPutDTO> findPremiumProductByPrice();
}
