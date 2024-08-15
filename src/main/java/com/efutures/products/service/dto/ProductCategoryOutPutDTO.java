package com.efutures.products.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryOutPutDTO {

    private Integer id;
    private String name;
    private String description;
}
