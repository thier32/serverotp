package com.server.otp.util;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class GeneratorUtil {
    public static  String contryCode = "2376";
    public static  int numberOfDigit = 12;
    public static  String initialDigit = "6";

    public static String getSring(int size){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(size)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return  generatedString;
    }

    public static String getCode(int size,String prefix)
    {
        StringBuilder code = new StringBuilder();
        String result = null;
        Random random = new Random();
        Double rd = random.nextDouble();
        if(prefix != null){
            code.append(prefix);
        }
        code.append(rd.toString().replaceAll("\\.",""));
        if(code.length() > 1)
            code.delete(0,1);

        result = code.toString();
        int differ = code.length() -size;
        if(differ < 0)
        {
            int arrSize = Math.abs(differ) - (Math.abs(differ)-size);
            String[] di = new String[arrSize];
            Arrays.fill(di,random.nextInt());
            code.append(di);
            result = code.toString();
        }
        if(differ > 0){
            result = code.substring(0,size);
        }
       return result;
    }

    public static  Long getCode(int size){
        Long result = 0L;
        String code = getCode(size,null);
        try{
            result = Long.valueOf(code);
        }catch (Exception e)
        {

        }

        return result;
    }

    public static Date buildExpiryDate(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, 5);
        return calendar.getTime();
    }


}
