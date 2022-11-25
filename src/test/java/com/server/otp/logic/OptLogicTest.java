package com.server.otp.logic;

import com.server.otp.error.OtpException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OptLogicTest {

    @Autowired
    OtpLogic otpLogic = Mockito.mock(OtpLogic.class);


    @DisplayName("lgcGenerateOtpCodeFails Vérification du duplication")
    @Test
    public void lgcGenerateOtpCodeFails()
    {
        assertDoesNotThrow(() -> otpLogic.lgcGenerateOtpCode());
    }

    @DisplayName("lgcGenerateOtpidSizeOk Vérification de la taille de l'optId")
    @Test
    public void lgcGenerateOtpidSizeOk()
    {
        assertDoesNotThrow(() ->
                {
                    Long generateOptId= otpLogic.lgcGenerateOtpId();
                    assertTrue(String.valueOf(generateOptId).length() == 8);
                }
        );
    }

    @DisplayName("lgcGenerateOtpidStringType Vérification du type de l'otpid")
    @Test
    public void lgcGenerateOtpidStringType()
    {
        assertDoesNotThrow(() ->
                {
                    Long generateOptId= otpLogic.lgcGenerateOtpId();
                    assertTrue(generateOptId instanceof Long);
                }
        );
    }


    @DisplayName("lgcGenerateOtpCodeSizeOk Vérification de la taille du code")
    @Test
    public void lgcGenerateOtpCodeSizeOk()
    {
        assertDoesNotThrow(() ->
                {
                    String generateCode= otpLogic.lgcGenerateOtpCode();
                    assertTrue(generateCode.length() == 16);
                }
        );
    }

    @DisplayName("lgcGenerateOtpCode Vérification du type de l'otp code")
    @Test
    public void lgcGenerateOtpCodeStringType()
    {
        assertDoesNotThrow(() ->
                {
                    String generateCode= otpLogic.lgcGenerateOtpCode();
                    assertTrue(generateCode instanceof String);
                }
        );
    }

    @DisplayName("Vérfication du format du teléphone format invalide 1")
    @Test
    public void logicCheckPhoneFormatTestFails() {
        String phone = "2376952582228";
        OtpException otpException = assertThrows(OtpException.class, ()
                -> otpLogic.lgcCheckPhoneFormat(phone));
        assertEquals("check.phone.format.fail", otpException.getErrorMessage()
        );
    }

    @DisplayName("Vérfication du format du teléphone format invalide 2")
    @Test
    public void logicCheckPhoneFormatTestFails1() {
        String phone = "2376";
        OtpException otpException = assertThrows(OtpException.class, ()
                -> otpLogic.lgcCheckPhoneFormat(phone));
        assertEquals("check.phone.format.fail", otpException.getErrorMessage()
        );
    }

    @DisplayName("Vérfication du format du teléphone format valide")
    @Test
    public void logicCheckPhoneFormatTestSuccess() {
        String phone = "237689652222";
        assertDoesNotThrow(()  -> otpLogic.lgcCheckPhoneFormat(phone));
    }
}
