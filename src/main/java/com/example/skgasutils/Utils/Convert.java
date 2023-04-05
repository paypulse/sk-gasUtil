package com.example.skgasutils.Utils;


import com.example.skgasutils.mapper.CommonMapper;
import com.example.skgasutils.repository.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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

    public static String getAFgrd(String grd){
        return COMP_NEW_AF_GRD.get(Integer.parseInt(grd));
    }


    /**
     * 우선 순위
     * **/
    private static final Map<Integer, String> PRIORITY;
    static {
        PRIORITY = new HashMap<Integer, String>();
        PRIORITY.put(0,"N");
        PRIORITY.put(1,"Y");
    }
    public static String getPriorityChk(String priority){
        return PRIORITY.get(Integer.parseInt(priority));
    }


    /**
     * 피평가자의 평가자
     * **/
//    public static String setMngNm(String empId){
//        User mng = commonMapper.getEmpList(empId);
//        String result = empId + "/" + mng.getEmpNm();
//        return result;
//    }


    /**
     * insert check
     * */
    private static final Map<Integer, String> insertCheck;
    static {
        insertCheck = new HashMap<>();
        insertCheck.put(1,"인사에 이미 등록된 ID입니다.");
        insertCheck.put(2,"피평가자에 이미 등록된 ID입니다.");
        insertCheck.put(3, "역량에 등록이 안되어 있습니다.역량 확인 해주세요");
        insertCheck.put(4, "역량 매핑이 이미 되어 있습니다.");
    }



}
