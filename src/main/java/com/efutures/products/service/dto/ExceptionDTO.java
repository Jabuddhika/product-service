package com.efutures.products.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionDTO {

    private int errorCode;
    private String errorMessage;
    private Map<String, Object> data;
}
