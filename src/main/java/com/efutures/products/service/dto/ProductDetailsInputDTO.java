package com.efutures.products.service.dto;

import com.efutures.products.service.dto.util.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsInputDTO implements Serializable {

    private Integer productId;
    @NotBlank(message = "product name cant be empty or null")
    private String name;
    @NotBlank(message = "product description cant be empty or null")
    private String description;
    @DecimalMin(value = "0.1", inclusive = false, message = "Product price must be greater than 0.1")
    private BigDecimal price;
    @NotEmpty(message = "product category cant be empty or null",groups = ValidationGroups.Save.class)
    private String category;


}
