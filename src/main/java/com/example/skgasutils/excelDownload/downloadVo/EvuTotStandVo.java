package com.example.skgasutils.excelDownload.downloadVo;


import com.example.skgasutils.Utils.Convert;
import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class EvuTotStandVo {

    //evuStdId
    @Nullable
    private String evuStdId;
    @Nullable
    private String postCd;
    @Nullable
    private String empId;
    private String empNm;
    private String postNm;
    private String cdpNm;
    private String cdpCd;
    private String priority;

    private String customYn;
    private String cateNm1;
    private String cateNm2;
    private String compTitle;
    private String defineCd;
    private String mng1Score1q;
    private String mng1Afscore1q;
    private String mng1Score2q;
    private String mng1Afscore2q;
    private String mng1Score3q;
    private String mng1Afscore3q;


    /**
     * priority
    * */
    public void setPriority(String priority) {

        this.priority = Convert.getPriorityChk(priority);
    }

    /**
     * TDS1  1차 평가
     * */
    public void setMng1Score1q(String mng1Score1q) {

        if(mng1Score1q.equals(null)){
            System.out.println("check the null ");
        }


       this.mng1Score1q = mng1Score1q;
    }
    /**
     * TDS1 1차  변동
     * **/







}
