package com.server.otp.listener;

import com.server.otp.error.OtpException;
import com.server.otp.logic.OtpLogic;
import com.server.otp.response.ResponseAdapter;
import com.server.otp.response.ResponseListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OtpResponseListener {
    @Autowired
    OtpLogic otpLogic;



    public ResponseListener generateListener = new ResponseAdapter(){

        public <M, C, T> T execute(M model, C codeFonction) throws OtpException
        {
            return (T) otpLogic.lgcNewOtp(model,codeFonction);
        }
    };

    public ResponseListener verifyListener = new ResponseAdapter(){
        @Override
        public <M, C, T> T execute(M model, C codeFonction) throws OtpException
        {
            Boolean result = Boolean.valueOf(otpLogic.lgcVerifierOtp(model,codeFonction));
            return (T) result;
        }
    };


    public ResponseListener listListener = new ResponseAdapter(){

        @Override
        public <M, C, T> T execute(M model, C codeFonction) throws OtpException {
            return (T) otpLogic.lgcListOtp(codeFonction);
        }
    };


}
