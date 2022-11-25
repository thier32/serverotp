package com.server.otp.entity;

import java.lang.annotation.*;
import java.util.Calendar;
import java.util.Date;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExpirationDate {
     int delayInMinutes() default 1250;

     //Date getExpirationdate() default new Date() ;
             /*{
                  Calendar calendar = Calendar.getInstance();
                  calendar.setTime(new Date());
                  calendar.add(Calendar.MINUTE, 5);
                  expirationdate = calendar.getTime();
                  this.setExpirationdate(expirationdate);
                  return expirationdate;
             }*/
}
