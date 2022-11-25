package com.server.otp.logic;

import com.server.otp.dto.CritereDto;
import com.server.otp.dto.OtpDto;
import com.server.otp.entity.Otp;
import com.server.otp.error.OtpException;
import com.server.otp.util.FunctionCode;
import com.server.otp.util.GeneratorUtil;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class OtpLogic extends BaseLogic {

    public <T,F>  Otp lgcNewOtp(T otpdto, F codeFonction) throws  OtpException {
        FunctionCode fonctionCode = FunctionCode.lgcNewOtp.updateParent((FunctionCode) codeFonction);

        if(!(otpdto instanceof OtpDto))
            throw new OtpException(fonctionCode);

        Otp otp;
        OtpDto otpDto = (OtpDto) otpdto;
        try
        {
            lgcCheckPhoneFormat(otpDto.getTelephone(),fonctionCode);
            otp = map(otpDto,Otp.class);
            boolean isNotGenerated = true;

            while (isNotGenerated)
            {
                otp.setCode(lgcGenerateOtpCode(fonctionCode));
                otp.setOtpid(lgcGenerateOtpId(fonctionCode));
                SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd/hh_mm_ss");

                otp.setReference("OTP_"+otpDto.getTelephone()+"_"+format.format(new Date()));
                isNotGenerated = lgcCheckOtpDuplicate(otp,fonctionCode);
            }
            Date date = new Date();
            otp.setCreationdate(date);
            otp.setExpirationdate(GeneratorUtil.buildExpiryDate(date));

            lgcSaveOtp(otp,fonctionCode);
        }
        catch (OtpException otpException)
        {
            throw new OtpException(otpException.getFunctionCode());
        }
        System.out.println(otp);
        return otp;
    }

    public <M,F,T> T lgcListOtp(M critere , F codeFonction) throws OtpException {
        FunctionCode fonctionCode = FunctionCode.lgcListOtp.updateParent((FunctionCode) codeFonction);
        List<Otp> otps;
        try
        {
            otps = serviceFactory.getOtpService().serviceListOtp();
        }
        catch (OtpException otpException)
        {
            throw new OtpException(fonctionCode);
        }
        return (T) otps;
    }

    public <T,F> boolean lgcVerifierOtp(T critere , F codeFonction) throws OtpException
    {
        FunctionCode fonctionCode = FunctionCode.lgcVerifierOtp.updateParent((FunctionCode) codeFonction);
        boolean result;
        if(!(critere instanceof CritereDto))
        {
            throw new OtpException(fonctionCode);
        }
        try {
            CritereDto critereDto = (CritereDto)critere;
            result = serviceFactory.getOtpService().serviceVerifierOtp(critereDto.getOtpid(),critereDto.getCode());
        }
        catch (OtpException otpException)
        {
            throw new OtpException(fonctionCode);
        }
        if(result == false)
        {
            throw new OtpException(fonctionCode);
        }
        return result;
    }

    Long lgcGenerateOtpId() throws OtpException
    {
        return lgcGenerateOtpId(null);
    }


    <T,F> Long lgcGenerateOtpId(F codeFonction ) throws OtpException
    {
        FunctionCode fonctionCode = FunctionCode.lgcGenerateOtpId.updateParent((FunctionCode) codeFonction);
        Long result = 0L;
        try {
            result = GeneratorUtil.getCode(8);
        }catch (Exception exception){
            throw new OtpException(fonctionCode);
        }

        return  result;
    }

    <T,F> String lgcGenerateOtpCode() throws OtpException{
        return lgcGenerateOtpCode(null);
    }

    <T,F> String lgcGenerateOtpCode(F codeFonction) throws OtpException
    {
        FunctionCode fonctionCode = FunctionCode.lgcGenerateOtpCode.updateParent((FunctionCode) codeFonction);
        String result = null;
        try {
             result = GeneratorUtil.getSring(16);
        }catch (Exception exception){
            throw new OtpException(fonctionCode);
        }

        if(result == null || (result != null &&  result.length() != 16))
        {
            throw new OtpException(fonctionCode);
        }
        return  result;
    }

    boolean lgcCheckOtpDuplicate(Otp otp) throws OtpException {
        return lgcCheckOtpDuplicate(otp,null);
    }

    boolean lgcCheckOtpDuplicate(Otp otp, FunctionCode codeFonction) throws OtpException {
        FunctionCode fonctionCode = FunctionCode.lgcCheckOtpDuplicate.updateParent(codeFonction);
        boolean exists = serviceFactory.getOtpService().serviceExistOtp(otp);
        if(exists){
            throw new OtpException(fonctionCode);
        }
        return exists;
    }

    <T, F> void lgcCheckPhoneFormat(T critere) throws OtpException {
         lgcCheckPhoneFormat(critere,null);
    }

    <T, F> void lgcCheckPhoneFormat(T critere, F codeFonction) throws OtpException {
        FunctionCode fonctionCode = FunctionCode.lgcCheckPhoneFormat.updateParent((FunctionCode) codeFonction);
        boolean matches = Pattern.matches("2376[0-9]{8}", (String)critere);
        if(!matches){
            throw new OtpException(fonctionCode);
        }
    }

    <T> void  lgcCheckOtpValidity(T critere, FunctionCode codeFonction) throws OtpException {
        FunctionCode fonctionCode = FunctionCode.lgcCheckPhoneFormat.updateParent((FunctionCode) codeFonction);
        boolean matches = Pattern.matches("2376[0-9]{8}", (String)critere);
        if(!matches){
            throw new OtpException(fonctionCode);
        }
    }

    Otp  lgcSaveOtp(Otp otp, FunctionCode codeFonction) throws OtpException {
        FunctionCode fonctionCode = FunctionCode.lgcSaveOtp.updateParent(codeFonction);
        try{
            otp = serviceFactory.getOtpService().serviceNewOtp(otp);
        }catch (OtpException otpException){
            throw new OtpException(fonctionCode);
        }
        return otp;
    }
}
