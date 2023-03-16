package com.example.skgasutils.excelDownload.downloadVo;


import com.example.skgasutils.Utils.Convert;
import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
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

    /**
     * chasu
    **/
    private String chasu;

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


    public void setPriority(String priority) {
        this.priority = Convert.getPriorityChk(priority);
    }

    public void setMng1Score1q(String mng1Score1q) {
        this.mng1Score1q = mng1Score1q;
    }
}
