package com.example.skgasutils.EvuCdpUtil.Service;

public interface EvuCdpUtilService {


    /**
     * 이전 년도 기준 역량 동일 적용시
     * */
    public int insertEvuCdpCapa(String lastEvuStdId, String nowEvuStdId);

}
