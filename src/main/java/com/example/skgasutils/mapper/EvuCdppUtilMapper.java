package com.example.skgasutils.mapper;

import com.example.skgasutils.EvuCdpUtil.evucdpVo.EvuCdpVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EvuCdppUtilMapper {

    //evu_cdp insert
    int insertEvuCdp(EvuCdpVo param);

    //evu_cdp_comp insert
    int insertEvuCdpComp(EvuCdpVo param);


}
