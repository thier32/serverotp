package com.server.otp.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public class ErrorHandler {

    @ExceptionHandler(OtpException.class)
    ResponseEntity response(HttpServletRequest request, OtpException otpException)
    {
        return new ResponseEntity<>(otpException.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(NullPointerException.class)
    ResponseEntity responseNullPointer(HttpServletRequest request, NullPointerException nullPointerException)
    {
        return new ResponseEntity<>(nullPointerException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ClassCastException.class)
    ResponseEntity responseClassCast(HttpServletRequest request, ClassCastException classCastException)
    {
        return new ResponseEntity<>(classCastException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    ResponseEntity responseEntity(HttpServletRequest request, Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
