package com.example.skgasutils.excelDownload.downloadVo;


import com.example.skgasutils.Utils.Convert;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class EvuTotDiffVo {
    //empId
    private String empId;
    //empnm
    private String empNm;
    //totGrd3q
    private String totGrd3q;
    //totGrd
    private String totCfmGrd3Q;


    public void setTotGrd3q(String totGrd3q) {
        this.totGrd3q = Convert.getGrdShort(totGrd3q);
    }

    public void setTotCfmGrd3Q(String totCfmGrd3Q) {
        this.totCfmGrd3Q = Convert.getGrdShort(totCfmGrd3Q);
    }
}
