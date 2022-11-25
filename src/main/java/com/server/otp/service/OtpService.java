package com.server.otp.service;

import com.server.otp.entity.Otp;
import com.server.otp.error.OtpException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OtpService {
    Otp serviceNewOtp(Otp otp) throws OtpException;
    <T> List<Otp> serviceListOtp(T critere) throws OtpException;
    <T> List<Otp> serviceListOtp() throws OtpException;
    boolean serviceVerifierOtp(Long optiId, String optCode) throws  OtpException;
    <T> boolean serviceExistOtp(Otp otp) throws OtpException;
    boolean serviceVerifierOtpExpiry(Long optiId, String optCode) throws OtpException;
}
