package com.example.skgasutils.EvuCdpUtil.Service.imple;

import com.example.skgasutils.EvuCdpUtil.Service.EvuCdpUtilService;
import com.example.skgasutils.mapper.CommonMapper;
import com.example.skgasutils.mapper.EvuCdppUtilMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skgasutils.repository.EvuMng;


@Slf4j
@Service
public class EvuCdpUtilImple implements EvuCdpUtilService {


    @Autowired
    private EvuCdppUtilMapper evuCdppUtilMapper;

    @Autowired
    private CommonMapper commonMapper;



    @Override
    public int insertEvuCdpCapa(String lastEvuStdId, String nowEvuStdId) {
        //공통
        System.out.println(commonMapper.getEvuCdpComp(lastEvuStdId));
        List<EvuCdpComp> test = commonMapper.getEvuCdpComp(lastEvuStdId);




        //evu_cdp insert




        //evu_cdp_comp insert


        return 0;
    }
}
