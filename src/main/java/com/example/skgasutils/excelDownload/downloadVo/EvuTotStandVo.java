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
    private String evuStdId;
    private String postCd;
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
       this.mng1Score1q = Convert.getGrdShort(mng1Score1q);
    }
    /**
     * TDS1 1차  변동
     * **/
    public void setMng1Afscore1q(String mng1Afscore1q) {
        this.mng1Afscore1q = Convert.getAFgrd(mng1Afscore1q);
    }


    /**
     * TDS2 1차 평가
     * **/
    public void setMng1Score2q(String mng1Score2q) {
        this.mng1Score2q = Convert.getGrdShort(mng1Score2q);
    }

    /**
     * TDS2 1차 변동
     * **/
    public void setMng1Afscore2q(String mng1Afscore2q) {
        this.mng1Afscore2q = Convert.getAFgrd(mng1Afscore2q);
    }

    /**
     * TDS3 1차 평가
     * **/
    public void setMng1Score3q(String mng1Score3q) {
        this.mng1Score3q = Convert.getGrdShort(mng1Score3q);
    }

    /**
     * TDS3 1차 변동
     * **/
    public void setMng1Afscore3q(String mng1Afscore3q) {
        this.mng1Afscore3q = Convert.getAFgrd(mng1Afscore3q);
    }
}
