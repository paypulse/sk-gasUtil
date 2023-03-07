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
public class EvuCdp implements Serializable {

    private int evuCdpSeq;
    private String evuStdId;
    private String cdpCd;
    private String cdpNm;
    private String cdpMission;
    private String cdpType;
    private int dpOrder;
    private String insUserId;
    private String insYmdhms;
    private String modUserId;
    private String modYmdhms;
    private String postCd;
}
