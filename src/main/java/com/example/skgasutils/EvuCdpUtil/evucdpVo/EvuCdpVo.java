package com.example.skgasutils.EvuCdpUtil.evucdpVo;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class EvuCdpVo {

    //이전년도 evuStdId
    private String lastEvuStdId;
    //현재 년도 evuStdId
    private String nowEvuStdId;

    private String evuCdpCompId;

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
    private int priority;
    private int dpOrder;
    private int evuCompDefineSeq;
    private String defineCd;
    private String visible;

    private String insUserId;
    private String insYmdhms;


}
