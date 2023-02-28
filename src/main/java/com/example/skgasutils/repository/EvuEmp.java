package com.example.skgasutils.repository;


import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvuEmp implements Serializable {

    private int evuEmpNo;
    private String evuStdId;
    private String curStepCd;
    private String evuStatCd;
    private int chasu;
    private String evuEmpId;
    private String cdpCd;
    private String evu2Yn;
    private String pmYn;
    private String compYn;
    private String movePeriodCd;
    private String moveWishOrgId1;
    private String moveWishOrgId2;
    private String moveWishOrgId3;
    private String moveWishCmnt;
    private String moveWishEvuComnt;
    private String insUserId;
    private String insYmdhms;
    private String modUserId;
    private String modYmdhms;
    private String mng1PmYn;
    private String mng1CompYn;
    private String appplyDate;
    private String mngApplyDate;
    private String mng2PmYn;
    private String mng2CompYn;
    private String mng3PmYn;
    private String mng3CompYn;
    private String cfmStatCd1q;
    private String cfmStatCd2q;
    private String cfmStatCd3q;
    private String cfmStatCd4q;
    private String reviewPmYn;
    private String reviewCompYn;
    private String empOrgId;
    private String mng1Feedback;
    private String mng2Feedback;
    private String mng3Feedback;
}
