package com.server.otp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    @JsonIgnore
    private String reference;

    @Column(nullable = false)
    private Long otpid;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    @CreationTimestamp
    @JsonIgnore
    private Date creationdate;

    @Column(nullable = false)
    @JsonIgnore
    private Date expirationdate;

}
