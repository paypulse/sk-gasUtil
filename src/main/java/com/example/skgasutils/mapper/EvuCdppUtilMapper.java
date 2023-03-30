package com.example.skgasutils.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EvuCdppUtilMapper {

    //evu_cdp insert
    int insertEvuCdp(String evuStdId, String insUserId,String lastEvuStdId);

    //evu_cdp_comp insert
    int insertEvuCdpComp(String evuCdpCompId, String nowEvuStdId, String insUserId);


}
