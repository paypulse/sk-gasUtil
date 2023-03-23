package com.example.skgasutils.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvuMng implements Serializable {
    private int evuMngSeq;
    private String evuMngId;
    private String evuEmpNo;
    private int chasu;
    private String mngOrgId;
    private String mngorgNm;
    private String applyDate;
    private String insUserId;
    private String insYmdhms;
    private String modUserId;
    private String modYmdhms;
    private String mngStatCd;
    private String temporaryYn;
    private String evuMngNm;

}
