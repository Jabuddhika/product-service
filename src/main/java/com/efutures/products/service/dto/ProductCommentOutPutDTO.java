package com.efutures.products.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCommentOutPutDTO {

    private Integer id;
    private String comment;
}
