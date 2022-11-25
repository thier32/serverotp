package com.server.otp.controller;

import com.server.otp.dto.CritereDto;
import com.server.otp.dto.OtpDto;
import com.server.otp.listener.OtpResponseListener;
import com.server.otp.util.FunctionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/opt")
public class OtpController extends BaseController
{
    @Autowired
    OtpResponseListener otpResponseListener;

    @RequestMapping("/")
    public String home() {
        return "Hello Docker World";
    }

    @PostMapping("/generate")
    public ResponseEntity crtGenererOtp(HttpServletRequest request , @RequestBody OtpDto optDto)
    {
        ResponseEntity responseEntity = responseManager.
                rsmBuildResult(request,
                        otpResponseListener.generateListener,
                        optDto,
                        FunctionCode.crtGenererOtp
                );
        return responseEntity;
    }

    @PostMapping("/list")
    public ResponseEntity crtListOtp(HttpServletRequest request , @RequestBody CritereDto critereDto)
    {
        ResponseEntity responseEntity = responseManager.
                rsmBuildResult(request,
                        otpResponseListener.listListener,
                        critereDto,
                        FunctionCode.crtGenererOtp
                );
        return responseEntity;
    }

    @PostMapping("/verify")
    public ResponseEntity crtVerifyOtp(HttpServletRequest request , @RequestBody CritereDto critereDto)
    {
        ResponseEntity responseEntity = responseManager.
                rsmBuildResult(request,
                        otpResponseListener.verifyListener,
                        critereDto,
                        FunctionCode.crtVerifyOtp
                );
        return responseEntity;
    }
}
