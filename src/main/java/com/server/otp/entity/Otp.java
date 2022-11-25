package com.server.otp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reference;

    @Column(nullable = false)
    private Long otpid;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    @CreationTimestamp
    private Date creationdate;

    @Column(nullable = false)
    private Date expirationdate;


    /*
    public Date getExpirationdate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(creationdate);
        calendar.add(Calendar.MINUTE, 5);
        expirationdate = calendar.getTime();
        this.setExpirationdate(expirationdate);
        return expirationdate;
    }*/
}
