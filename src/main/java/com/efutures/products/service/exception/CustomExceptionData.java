package com.efutures.products.service.exception;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import java.util.Map;

@Data
@Builder
public class CustomExceptionData {

    @Singular("data")
    private Map<String, Object> dataMap;
}
