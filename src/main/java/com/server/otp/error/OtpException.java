package com.server.otp.error;

import com.server.otp.util.FunctionCode;
import lombok.Data;

@Data
public class OtpException extends Exception{
    protected String errorMessage;
    protected String functiocode;
    protected FunctionCode functionCode;

    public OtpException()
    {

    }

    public OtpException(FunctionCode functionCode,Exception e){
        this.functionCode = functionCode;
        this.functionCode.setMessage(e.getMessage());
    }

    public OtpException(String message, String fonccode)
    {
        this.errorMessage = message;
        this.functiocode = fonccode;
    }

    public OtpException(FunctionCode functionCode){
        this.functionCode = functionCode;
        this.errorMessage = functionCode.getErrorMessage();
    }
}
