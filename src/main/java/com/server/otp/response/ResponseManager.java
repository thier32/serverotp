package com.server.otp.response;

import com.server.otp.error.OtpException;
import com.server.otp.util.FunctionCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ResponseManager {


    public <R, T, P,F> T rsmBuildResult(R request, ResponseListener listener, P entity, F functionCode) {
        FunctionCode codeFunction = FunctionCode.rsmBuildResult.updateParent((FunctionCode)functionCode);

        T response = null;
        Object result= null;
        boolean isSuccess = false;
        try{
            result =  (T) listener.execute(entity,codeFunction);
            isSuccess = true;
        }catch (OtpException otpException){
            result = otpException;
        }
        response = (T) buildResponse(result,isSuccess,functionCode);
        return response;
    }

    private <T, L, P> ResponseEntity buildResponse(T result, P... params) {
        boolean isSucess = false;
        FunctionCode codeFunction = null;
        new HashMap();
        Object[] var7 = params;
        int var8 = params.length;
        String message = "";
        FunctionCode functionCode = null;
        for (Object param:params) {
            if(param instanceof Boolean){
                isSucess = Boolean.valueOf((Boolean) param);
            }
            if(param instanceof FunctionCode)
            {
                functionCode = ((FunctionCode)param);
            }
        }
        int status = isSucess ? 200 : 406;

        ApiResponse custumApiResponse = new ApiResponse();

        if(result instanceof OtpException)
        {
            custumApiResponse.setResult(((OtpException)result).getFunctionCode().getCallTree());
            custumApiResponse.setMessage(((OtpException)result).getErrorMessage());
        }
        else
        {
            if(isSucess)
            {
                custumApiResponse.setMessage(functionCode.getSuccessMessage());
            }else{
                custumApiResponse.setMessage(functionCode.getErrorMessage());
            }
            custumApiResponse.setResult(result);
        }
        custumApiResponse.setStatus(status);

        ResponseEntity responseEntity = new ResponseEntity(custumApiResponse, HttpStatus.valueOf(status));
        return responseEntity;
    }
}