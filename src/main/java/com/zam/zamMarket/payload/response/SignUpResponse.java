package com.zam.zamMarket.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpResponse {

    private String email;
    private String message;
    private String token;
    private Integer status;

}
