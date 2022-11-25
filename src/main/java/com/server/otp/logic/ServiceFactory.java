package com.server.otp.logic;

import com.server.otp.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactory {
    @Autowired
    OtpService otpService;

    public OtpService getOtpService() {
        return otpService;
    }

}
