package com.server.otp.response;

import com.server.otp.error.OtpException;

public interface ResponseListener {
    <M,C,T> T execute(M model, C codeFonction) throws OtpException;
}
