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
public class EvuEmpCdp implements Serializable {
    private int evuEmpCdpSeq;
    private int evuEmpNo;
    private String stepCd;
    private String evuStdId;
    private String cdpCd;
    private String insUserId;
    private String insYmdhms;
    private String modUserId;
    private String modYmdhms;
}
