package com.server.otp.controller;

import com.server.otp.response.ResponseManager;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
    @Autowired
    ResponseManager responseManager;
}
