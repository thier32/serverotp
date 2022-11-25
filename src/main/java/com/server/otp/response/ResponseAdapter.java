package com.server.otp.response;


import com.server.otp.error.OtpException;
import com.server.otp.response.ResponseListener;

public class ResponseAdapter implements ResponseListener {


    @Override
    public <M, C, T> T execute(M model, C codeFonction) throws OtpException {
        return null;
    }

    @Override
    public <C, T> T execute(C codeFonction) throws OtpException {
        return null;
    }
}
