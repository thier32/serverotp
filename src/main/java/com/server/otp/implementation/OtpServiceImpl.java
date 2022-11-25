package com.server.otp.implementation;

import com.server.otp.entity.Otp;
import com.server.otp.error.OtpException;
import com.server.otp.repository.OtpRepository;
import com.server.otp.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    OtpRepository otpRepository;

    @Override
    public Otp serviceNewOtp(Otp otp) throws OtpException {
        Otp savedOto;
        try {
            savedOto = otpRepository.save(otp);
        }catch (Exception e){
            throw new OtpException("serviceNewOtp",e.getMessage());
        }
        return savedOto;
    }


    @Override
    public <T> List<Otp> serviceListOtp(T critere) throws OtpException {
        List<Otp> results;
        try
        {
            results = this.otpRepository.findAll();
        }
        catch (NoResultException e)
        {
            results = new ArrayList<>(0);
        }
        catch (Exception e)
        {
            throw new OtpException("serviceListOtp",e.getMessage());
        }
        return results;
    }

    @Override
    public <T> List<Otp> serviceListOtp() throws OtpException {
        List<Otp> results = new ArrayList<>(0);
        try
        {
            results = this.otpRepository.findAll();
        }
        catch (NoResultException e)
        {

        }
        catch (Exception e)
        {
            throw new OtpException("serviceListOtp",e.getMessage());
        }
        return results;
    }

    @Override
    public  boolean serviceVerifierOtp(Long optiId, String optCode) throws OtpException
    {
        boolean result;
        try
        {
            result = this.otpRepository.existsByCodeAndOtpidAndExpirationdateAfter(optCode,optiId,new Date());
        }
        catch (Exception e)
        {
            throw new OtpException("serviceVerifierOtp",e.getMessage());
        }
        return result;
    }

    @Override
    public <T> boolean serviceExistOtp(Otp otp) throws OtpException
    {
        boolean result;
        try
        {
            result = this.otpRepository.existsByOtpidOrCodeOrReference(
                    otp.getOtpid(),otp.getCode(),otp.getReference());
        }
        catch (Exception e)
        {
            throw new OtpException("serviceExistOtp",e.getMessage());
        }
        return result;
    }

    @Override
    public boolean serviceVerifierOtpExpiry(Long optiId, String optCode) throws OtpException
    {
        boolean result;
        try{
            result = otpRepository.existsByCodeAndOtpidAndExpirationdateAfter(optCode,optiId,new Date());
        }catch (Exception e){
            throw new OtpException("serviceVerifierOtpExpiry",e.getMessage());
        }
        return result;
    }
}
