package com.example.skgasutils.Utils;


import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class Convert {

    /**
     * 역량 평가 등급 정의
     * */
    private static final Map<String, String> COMP_NEW_GRD;
    static {
        COMP_NEW_GRD = new LinkedHashMap<String, String>();
        COMP_NEW_GRD.put("","");
        COMP_NEW_GRD.put("0","");
        COMP_NEW_GRD.put("1","Unsatisfactory");
        COMP_NEW_GRD.put("2", "Need");
        COMP_NEW_GRD.put("3", "Meet");
        COMP_NEW_GRD.put("4", "Exceed");
        COMP_NEW_GRD.put("5", "SUPEX");
    }

    public static String getGrdShort(String grd){
        String grdText = COMP_NEW_GRD.get(grd);

        if(grdText == null)
            return grd;
        return grdText.length()>0?String.valueOf(grdText.charAt(0)):"";
    }








}
