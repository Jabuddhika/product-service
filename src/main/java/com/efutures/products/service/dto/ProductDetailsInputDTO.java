package com.efutures.products.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsInputDTO implements Serializable {

    @NotNull(message = "product name cant be empty")
    @NotBlank(message = "product name cant be empty")
    private String name;
    @NotNull(message = "product description cant be empty")
    private String description;
    @DecimalMin(value = "0.1", inclusive = false, message = "Product price must be greater than 0.1")
    private BigDecimal price;
    @NotNull(message = "product category cant be empty")
    private String category;


}
