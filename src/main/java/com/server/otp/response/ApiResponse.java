package com.server.otp.response;

import lombok.Data;

@Data

public class ApiResponse {
    private Object result;
    private String message;
    private int status;
    public ApiResponse(){}
    public ApiResponse(Object val, String mess, int statu){
        this.result = val;
        this.message = mess;
        this.status = statu;
    }
}
