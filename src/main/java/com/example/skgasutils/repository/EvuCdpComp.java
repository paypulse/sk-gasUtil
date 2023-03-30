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
public class EvuCdpComp implements Serializable {

    private String evuCdpCompId;
    private String evuStdId;
    private String evuCdpCd;
    private String postCd;
    private String cateCd1;
    private String cateCd2;
    private String cateCd3;
    private String actDesc;
    private int score;
    private int priority;
    private int dpOrder;
    private String insUserId;
    private String insYmdhms;
    private String modUserId;
    private String modYmdhms;
    private int evuCompDefineSeq;
    private String defineCd;
    private String visible;


}
