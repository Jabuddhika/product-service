package com.efutures.products.service.service;

import com.efutures.products.service.entity.ProductCategory;


public interface ProductCategoryService {

   ProductCategory findByName(String name);
}
