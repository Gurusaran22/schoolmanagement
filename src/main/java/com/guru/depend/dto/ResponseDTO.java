package com.guru.depend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {

    private String status;
    private int statusCode;
    private String message;
    private Object data;

    public ResponseDTO(String status, int statusCode,String message, Object data) {
        this.status=status;
        this.statusCode = statusCode;
        this.message=message;
        this.data=data;
    }
}
