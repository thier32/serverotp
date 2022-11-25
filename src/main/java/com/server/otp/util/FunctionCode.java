package com.server.otp.util;

public enum  FunctionCode {
    crtGenererOtp("CRTGO01","success","generer.otp.fail"),
    crtListOtp("CTRLO002","list.otp.success","list.otp.fail"),
    crtVerifyOtp("CRTGVO02","success","failed"),
    rsmBuildResult("RSMBR","build.result.success","build.result.fail"),
    lgcNewOtp("LGCNO03","new.otp.success","new.otp.fail"),
    lgcListOtp("LGCLO04","list.otp.success","list.otp.fail"),
    lgcVerifierOtp("LGCVO05","verify.otp.success","verify.otp.fail"),
    lgcGenerateOtpId("LGCGOI06","generate.otpid.success","generate.otpid.fail"),
    lgcGenerateOtpCode("LGCGOC07","generate.otp.code.success","generate.otp.code.fail"),
    lgcCheckPhoneFormat("LGCCPF08","check.phone.format.success","check.phone.format.fail"),
    lgcCheckOtpValidity("LGCCOV09","check.otp.validity.success","check.otp.validity.fail"),
    lgcSaveOtp("LGCSO11","save.otp.success","save.otp.fail"),
    lgcCheckOtpDuplicate("LGCCOD10","check.duplicate.true","check.duplicate.true");

    private String code;
    private String successMessage;
    private String errorMessage;
    private String message;
    FunctionCode(){
        this.callTree = "Root";
    }
    FunctionCode(String codeFunction)
    {
        this();
        this.code = codeFunction;
    }

    FunctionCode(String codeFunction, String sucessMessage){
        this(codeFunction);
        this.successMessage = sucessMessage;
    }

    FunctionCode(String codeFunction, String successMessage, String erorMessage){
        this(codeFunction,successMessage);
        this.errorMessage = erorMessage;
    }

    public String getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    private String callTree = "Root";

    private FunctionCode codeparent;

    public String getCallTree()
    {
        return  callTree;
    }

    public void setCodeparent(FunctionCode codeparent) {
        this.codeparent = codeparent;
        String parent = codeparent.getCode() != null ? codeparent.getCallTree() : "Root";
        String name = this.getCode();
        this.callTree = parent +"->"+ name;
    }

    public FunctionCode updateParent(FunctionCode parentFunctionCode){
        if(parentFunctionCode != null){
            this.setCodeparent(parentFunctionCode);
        }
        return this;
    }
}
