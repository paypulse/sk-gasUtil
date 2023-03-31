package com.example.skgasutils.mapper;

import com.example.skgasutils.EvuCdpUtil.evucdpVo.EvuCdpVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EvuCdppUtilMapper {

    //evu_cdp insert
    int insertEvuCdp(List<EvuCdpVo> param);

    //evu_cdp_comp insert
    int insertEvuCdpComp(List<EvuCdpVo> param);


}
