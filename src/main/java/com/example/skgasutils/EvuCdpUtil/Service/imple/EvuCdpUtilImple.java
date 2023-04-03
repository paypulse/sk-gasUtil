package com.example.skgasutils.EvuCdpUtil.Service.imple;

import com.example.skgasutils.EvuCdpUtil.Service.EvuCdpUtilService;
import com.example.skgasutils.EvuCdpUtil.evucdpVo.EvuCdpVo;
import com.example.skgasutils.Utils.CommonUtil;
import com.example.skgasutils.mapper.CommonMapper;
import com.example.skgasutils.mapper.EvuCdppUtilMapper;
import com.example.skgasutils.repository.EvuCdp;
import com.example.skgasutils.repository.EvuCdpComp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
        List<EvuCdpVo> insertCdp = new ArrayList<>();
        List<EvuCdpVo> insertCdpComp = new ArrayList<>();

        EvuCdpVo vo = new EvuCdpVo();

        int result =0;

        //cdp 직무
        List<EvuCdp> cdp = commonMapper.getEvuCdp(lastEvuStdId);
        System.out.println("cdp count :" + cdp.size());
        cdp.stream().forEach(n ->{
            vo.setNowEvuStdId(nowEvuStdId);
            vo.setLastEvuStdId(lastEvuStdId);
            vo.setInsUserId("00812");
            vo.setInsYmdhms(date);
            insertCdp.add(vo);
        });
        System.out.println("check :" + insertCdp);

        //cdp_comp cdp직무별 역량 평가 항목 관리
        List<EvuCdpComp> cdpComp = commonMapper.getEvuCdpComp(lastEvuStdId);
        System.out.println("cdpComp size : " + cdpComp.size());
        //cdpComp 안에서 바꿀때
        cdpComp.stream().forEach(n->{
            vo.setEvuCdpCompId("COM2023"+n.getEvuCdpCompId().substring(8,20));
            vo.setInsUserId("00812");
            vo.setInsYmdhms(date);
            insertCdpComp.add(vo);
        });
        System.out.println("check1 :" + insertCdpComp);
     //   System.out.println(insertCdp);


        //insert
        result = evuCdppUtilMapper.insertEvuCdp(insertCdp);
        result += evuCdppUtilMapper.insertEvuCdpComp(insertCdpComp);

  //      System.out.println("result : "+ result );

        return result;
    }
}
