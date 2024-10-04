package com.zam.zamMarket.payload.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class ExceptionResponse {

    private LocalDateTime localDateTime;
    private int status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> errors;

}
