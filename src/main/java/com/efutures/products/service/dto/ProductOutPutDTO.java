package com.efutures.products.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOutPutDTO {

    private Integer id;
    private String name;
    private BigDecimal price;
    private String description;
    private Character status;
    private ProductCategoryOutPutDTO productCategory;
    private List<ProductCommentOutPutDTO> productCommentList;
}
