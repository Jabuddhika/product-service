package com.efutures.products.service.api;

import com.efutures.products.service.dto.ProductDetailsInputDTO;
import com.efutures.products.service.dto.ProductOutPutDTO;
import com.efutures.products.service.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductController.class)
@MockBean(JpaMetamodelMappingContext.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProduct() throws Exception {

        ProductDetailsInputDTO inputDTO = ProductDetailsInputDTO.builder()
                .name("Test Product")
                .description("Test Description")
                .price(new BigDecimal("29.99"))
                .category("Test Category")
                .build();

        doNothing().when(productService).createProduct(any(ProductDetailsInputDTO.class));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(inputDTO)))
                .andExpect(status().isOk());

    }
    @Test
    void updateProduct() throws Exception {
        ProductDetailsInputDTO dto = ProductDetailsInputDTO.builder()
                .name("Updated Product")
                .description("Updated Description")
                .price(new BigDecimal("39.99"))
                .build();

        Integer productId = 1;
        doNothing().when(productService).createProduct(any(ProductDetailsInputDTO.class));

        mockMvc.perform(patch("/api/v1/products/update/{product_id}", productId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("product successfully updated"));

    }
    @Test
    void removeProduct() throws Exception {
        Integer productId = 1;
        doNothing().when(productService).deleteProduct(productId);

        mockMvc.perform(delete("/api/v1/products/remove/{product_id}", productId))
                .andExpect(status().isOk())
                .andExpect(content().string("product successfully deleted"));

    }
    @Test
    void findAllProductByCategoryName() throws Exception {

        String categoryName = "Test Category";
        List<ProductOutPutDTO> products = List.of(new ProductOutPutDTO());
        when(productService.findProductByCategoryName(categoryName)).thenReturn(products);

        mockMvc.perform(get("/api/v1/products/by-category")
                        .param("category", categoryName))
                .andExpect(status().isOk());
    }

    @Test
    void findPremiumProductByPrice() throws Exception {
        List<ProductOutPutDTO> products = List.of(new ProductOutPutDTO());
        when(productService.findPremiumProductByPrice()).thenReturn(products);

        mockMvc.perform(get("/api/v1/products/by-price"))
                .andExpect(status().isOk());

    }

}


