package com.server.otp.controller;

import com.server.otp.dto.OtpDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;


public class OtpControllerTest extends AbstractTest
{
    String addUri = "/generate";
    String updateUri = "/update";
    String deleteUri = "/delete";
    String listUri = "/list";

    @Test
    public void test3_addTemplateTest() throws Exception {
        /*String uri = getBaseUrl()+addUri;
        OtpDto otpDto = new OtpDto();
        otpDto.setTelephone("237685996668");

        addTest(uri,otpDto);*/
    }
}
