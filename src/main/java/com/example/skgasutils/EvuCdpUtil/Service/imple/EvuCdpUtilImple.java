package com.example.skgasutils.EvuCdpUtil.Service.imple;

import com.example.skgasutils.EvuCdpUtil.Service.EvuCdpUtilService;
import com.example.skgasutils.EvuCdpUtil.evucdpVo.EvuCdpVo;
import com.example.skgasutils.Utils.CommonUtil;
import com.example.skgasutils.mapper.CommonMapper;
import com.example.skgasutils.mapper.EvuCdppUtilMapper;
import com.example.skgasutils.repository.EvuCdpComp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class EvuCdpUtilImple implements EvuCdpUtilService {

    @Autowired
    private EvuCdppUtilMapper evuCdppUtilMapper;

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public int insertEvuCdpCapa(String lastEvuStdId, String nowEvuStdId) {

        CommonUtil util = new CommonUtil();
        String date = util.nowDate1("yyyy-MM-dd HH:mm:ss");


        EvuCdpVo cdpInfo = new EvuCdpVo();
        List<String> evuCdpCompId = new ArrayList<>();

        int result =0;

        //cdp 직무
        cdpInfo.setNowEvuStdId(nowEvuStdId);
        cdpInfo.setLastEvuStdId(lastEvuStdId);
        cdpInfo.setInsUserId("00812");
        cdpInfo.setInsYmdhms(date);


        //cdp_comp cdp직무별 역량 평가 항목 관리
        List<EvuCdpComp> cdpComp = commonMapper.getEvuCdpComp(lastEvuStdId);

        cdpInfo.setEvuCdpCompId(evuCdpCompId);


        //insert
        result = evuCdppUtilMapper.insertEvuCdp(cdpInfo);
        result += evuCdppUtilMapper.insertEvuCdpComp(cdpInfo);

        return result;
    }
}
