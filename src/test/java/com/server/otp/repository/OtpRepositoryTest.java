package com.server.otp.repository;

import com.server.otp.entity.Otp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OtpRepositoryTest {

    OtpRepository otpRepository = Mockito.mock(OtpRepository.class);

    @DisplayName("existsByReference Vérification de l'otp")
    @Test
    public void existsByReferenceFails()
    {
        String reference = "0225222";
        when(otpRepository.existsByCode(reference)).thenReturn(false);
        assertFalse(otpRepository.existsByReference(reference));
    }

    @DisplayName("existsByCodeFails Vérification du duplication")
    @Test
    public void existsByCodeFails()
    {
        String code = "0225222";
        assertFalse(otpRepository.existsByCode(code));
    }

    @DisplayName("lgcGenerateOtpCodeFails Vérification du duplication")
    @Test
    public void existsByOtpidFails()
    {
        Long optid = 286225858L;
        assertFalse(otpRepository.existsByOtpid(optid));
    }


    @DisplayName("lgcGenerateOtpCodeFails Vérification du duplication")
    @Test
    public void existsByOtpidOrCodeOrReferenceFails()
    {
        String code = "0225222";
        Long optid = 286225858L;
        String reference = "28655dfd";
        assertFalse(otpRepository.existsByOtpidOrCodeOrReference(optid,code,reference));
    }

    @DisplayName("lgcGenerateOtpCodeFails Vérification du duplication")
    @Test
    public void existsByCodeAndOtpidFails()
    {
        String code = "0225222";
        Long optid = 286225858L;
        assertFalse(() -> otpRepository.existsByCodeAndOtpid(code,optid));
    }

}
