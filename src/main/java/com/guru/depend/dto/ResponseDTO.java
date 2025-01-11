package com.guru.depend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {

    private String status;
    private int statusCode;
    private Object data;

    public ResponseDTO(String status, int statusCode, Object data) {
        this.status=status;
        this.statusCode = statusCode;
        this.data=data;
    }
}
