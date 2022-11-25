package com.server.otp.response;



import com.server.otp.error.OtpException;

import java.util.HashMap;

public class ResponseAdapter implements ResponseListener {
    public ResponseAdapter(){

    }

    @Override
    public <M, C, T> T execute(M model, C codeFonction) throws OtpException {
        return null;
    }
}
