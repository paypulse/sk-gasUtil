package com.example.skgasutils.UserUtils.Service.imple;


import com.example.skgasutils.UserUtils.Service.UserUtilService;
import com.example.skgasutils.UserUtils.UserUtilVo.UserReqVo;
import com.example.skgasutils.Utils.CommonUtil;
import com.example.skgasutils.mapper.CommonMapper;
import com.example.skgasutils.mapper.ExcelUploadMapper;
import com.example.skgasutils.mapper.UserUtilMapper;
import com.example.skgasutils.repository.EvuCdp;
import com.example.skgasutils.repository.EvuEmp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserUtilServiceImpl implements UserUtilService {

    @Autowired
    private CommonMapper commonMapper;


    @Autowired
    private UserUtilMapper userUtilMapper;



    /**
     * TODO.
     * user에 등록 하고
     * cdp_cd도 등록 해줘야 하는 경우
     * user, evu_emp, evu_emp_cdp에 등록 해주기 한번에 등록 해주기
     * cdp_nm이 존재 하는지 확인 해주는 로직 추가 하기
     * **/
    @Override
    public int userRegistService(UserReqVo vo) {

        int result = 0;
        CommonUtil util = new CommonUtil();
        String date = util.nowDate1("yyyy-MM-dd HH:mm:ss");

        //1. user에 있는지 없는지 확인
         int userCount = commonMapper.getUserInfo(vo.getEmpId());
         System.out.println("userCount : " + userCount);

        vo.setInsUserId("00812");
        vo.setInsYmdhms(date);
        vo.setEvuStdId("202301");
        vo.setCurStepCd("A0");
        vo.setEvuStatCd("E1");

         if(userCount < 1){
             //user에 등록
             result = userUtilMapper.userRegist(vo);
         }

        //2. evu_emp에 있는지 없는지 확인
        int evuEmpCount = commonMapper.getEvuEmpCount(vo.getEmpId());
        if(evuEmpCount <1){
            result += userUtilMapper.evuEmpRegist(vo);
        }

        //3. evu_cdp에서 cdp_cd가 있는지 없는지 확인
        //화면이 있다면, 동일한 단어 검색 추천 기능 넣기
        Map<String,String> map = new HashMap<>();
        map.put("evuStdId", vo.getEvuStdId());
        map.put("cdpNm", vo.getCdpNm());
        List<EvuCdp> cdpCheck = commonMapper.getEvuCdpCount(map);
        vo.setCdpCd(cdpCheck.get(0).getCdpCd());


        //TODO. 제발 commonMapper 정리 좀 하자. 많이 지저분하네, 왜 이렇게 짠거지?

        //4. evu_emp_cdp에 있는지 없는지 확인
        //evu_emp_no값
        List<EvuEmp> empList =commonMapper.getEvuEmpList(vo.getEvuStdId());
        List<EvuEmp> emp = empList.stream().filter(n->{
            return n.getEvuEmpId().equals(vo.getEmpId());
        }).collect(Collectors.toList());

        vo.setEvuEmpNo(String.valueOf(emp.get(0).getEvuEmpNo()));

        if(!emp.isEmpty()){

            //evu_emp_cdp 체크
            Map<String,String> param = new HashMap<>();
            param.put("evuStdId", vo.getEvuStdId());
            param.put("evuEmpNo", vo.getEvuEmpNo());
            int evuEmpCdpCheck = commonMapper.getEvuEmpCdpCount(param);

            if(evuEmpCdpCheck<1){
                result += userUtilMapper.evuEmpCdpRegist(vo);
            }
        }


        return result;
    }

    @Override
    public int onlyUserRegist(UserReqVo vo) {
        // 1. 일단 먼저 등록이 되어 있는지 check
        // 2. 등록이 되어 있지 않았다면 등록




        return 0;
    }






}
