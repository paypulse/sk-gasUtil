package com.example.skgasutils.Utils;


import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
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


    /**
     * 역량 평가 변동 결과
     * */
    private static final Map<Integer, String> COMP_NEW_AF_GRD;
    static{
        COMP_NEW_AF_GRD = new HashMap<Integer, String>();
        COMP_NEW_AF_GRD.put(0,"-");
        COMP_NEW_AF_GRD.put(1,"하락");
        COMP_NEW_AF_GRD.put(2,"유지");
        COMP_NEW_AF_GRD.put(3, "개선");
    }
    public static String getGrdAfShort(String score, int chasu){
        String grdAfText = null;
        //score  null 체크
        if(score.equals("")){
            //널이면
            grdAfText = "TDS"+ chasu+ "변동 방향" ;

        }else{
            grdAfText = COMP_NEW_AF_GRD.get(Integer.parseInt(score));
        }
        return grdAfText;
    }


    /**
     * 우선 순위
     * **/
    private static final Map<Integer, String> PRIORITY;
    static {
        PRIORITY = new HashMap<Integer, String>();
        PRIORITY.put(0,"Y");
        PRIORITY.put(1,"N");
    }
    public static String getPriorityChk(String priority){
        return PRIORITY.get(Integer.parseInt(priority));
    }












}
