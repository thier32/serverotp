package com.server.otp.service;

import com.server.otp.repository.OtpRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OtpServiceTest {

    OtpService otpService = Mockito.mock(OtpService.class);

    @DisplayName("existsByReferenceFails Vérification de l'otp")
    @Test
    public void existsByReferenceFails()
    {
        String reference = "0225222";
        OtpRepository otpRepository = Mockito.mock(OtpRepository.class);
        Mockito.when(otpRepository.existsByReference(reference)).thenReturn(false);
    }

    @DisplayName("existsByCodeFails Vérification du duplication")
    @Test
    public void existsByCodeFails()
    {
        String code = "0rsdf5222";
        OtpRepository otpRepository = Mockito.mock(OtpRepository.class);
        Mockito.when(otpRepository.existsByCode(code)).thenReturn(false);
    }

    @DisplayName("lgcGenerateOtpCodeFails Vérification du duplication")
    @Test
    public void existsByOtpidFails()
    {
        Long optid = 286225858L;
        OtpRepository otpRepository = Mockito.mock(OtpRepository.class);
        Mockito.when(otpRepository.existsByOtpid(optid)).thenReturn(false);
    }


    @DisplayName("lgcGenerateOtpCodeFails Vérification du duplication")
    @Test
    public void existsByOtpidOrCodeOrReferenceFails()
    {
        String code = "0225222";
        Long optid = 286225858L;
        String reference = "28655dfd";
        OtpRepository otpRepository = Mockito.mock(OtpRepository.class);
        Mockito.when(otpRepository.existsByOtpidOrCodeOrReference(optid,code,reference)).thenReturn(false);
    }

    @DisplayName("lgcGenerateOtpCodeFails Vérification du duplication")
    @Test
    public void existsByCodeAndOtpidFails()
    {
        String code = "0225222";
        Long optid = 286225858L;
        OtpRepository otpRepository = Mockito.mock(OtpRepository.class);
        Mockito.when(otpRepository.existsByCodeAndOtpid(code,optid)).thenReturn(false);
    }

}
