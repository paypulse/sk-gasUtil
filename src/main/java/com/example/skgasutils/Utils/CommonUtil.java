package com.example.skgasutils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

    /**
     * 현재 날짜 주어진 양식에 맞게 출력
    **/
    public String nowDate1(String format){
        SimpleDateFormat sFormat = new SimpleDateFormat(format);

        Date time = new Date();
        String result = sFormat.format(time);
        return result;
    }








}
