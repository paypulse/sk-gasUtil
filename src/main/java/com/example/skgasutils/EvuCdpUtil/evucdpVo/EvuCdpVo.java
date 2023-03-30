package com.example.skgasutils.EvuCdpUtil.evucdpVo;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class EvuCdpVo {
    private String evuStdId;

    // evu_cdp_cd 와 cdp_cd 동일
    private String cdpCd;
    private String cateCd1;
    private String cateCd2;
    private String cateCd3;

    private String cdpNm;
    private String cdpType;
    private String cdpMission;
    private String postCd;

    private String actDesc;
    private int score;
    private String priority;
    private int dpOrder;
    private String evuCompDefineSeq;
    private String defineCd;
    private String visible;

    private String insUserId;
    private String insYmdhms;


}
