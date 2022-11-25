package com.server.otp.repository;

import com.server.otp.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {
    boolean existsByReference(String reference);
    boolean existsByCode(String code);
    boolean existsByOtpid(Long optid);
    boolean existsByOtpidOrCodeOrReference(Long optid,String code, String reference);
    boolean existsByCodeAndOtpidAndExpirationdateAfter(String code, Long otpid, Date expirydate);
    boolean existsByCodeAndOtpid(String code, Long otpid);
}
