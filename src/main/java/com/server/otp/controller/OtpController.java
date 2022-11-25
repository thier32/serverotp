package com.server.otp.controller;

import com.server.otp.dto.CritereDto;
import com.server.otp.dto.OtpDto;
import com.server.otp.listener.OtpResponseListener;
import com.server.otp.util.FunctionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/opt")
public class OtpController extends BaseController
{
    @Autowired
    OtpResponseListener otpResponseListener;

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

    @GetMapping("/list")
    public ResponseEntity crtListOtp(HttpServletRequest request)
    {
        ResponseEntity responseEntity = responseManager.
                rsmBuildResult(request,
                        otpResponseListener.listListener,
                        null,
                        FunctionCode.crtListOtp
                );
        return responseEntity;
    }

    @PostMapping("/validate")
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
