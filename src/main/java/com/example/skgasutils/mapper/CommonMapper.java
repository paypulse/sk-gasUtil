package com.example.skgasutils.mapper;

import com.example.skgasutils.repository.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
@Component
public interface CommonMapper {
    /**
     * EvuEmp check
     * */
    public List<EvuEmp> getEvuEmpList(String evuStdId);

    /**
     * EvuEmp count check
     * */
    public int getEvuEmpCount(String evuEmpId);

    /**
     * EvuMng check
     * */
    public List<EvuMng> getEvuMngList();

    /**
     * CDP 팹핑
     * */
    public List<EvuCdp> getEvuCdp(String evuStdId);

    /**
     * cdp 체크
     * */
    public List<EvuCdp> getEvuCdpCount(Map<String, String> param);


    /**
     * EVU_EMP_CDP CHECK
     * */
    public List<EvuEmpCdp> getEvuEmpCdp();

    /**
     * EVU_EMP_CDP check count
     * */
    public int getEvuEmpCdpCount(Map<String, String> param);


    /**
     * EVU_EMP_CDP 에서 해당 cdp가 있는지 check
     * */
    public List<EvuEmpCdp> getEmpCdpList(String evuStdId);


    /**
     * 지정 평가자 가져오기
     * **/
    public List<EvuMng> getMngList();


    /**
     * CDP 직무별 역량 평가 항목 관리
     * */
    public List<EvuCdpComp> getEvuCdpComp(String evuStdId);


    /**
     * USER 인사 정보 check
     * */
    public int getUserInfo(String empId);

    /**
     * User 정보 가져 오기
     * **/
    public List<User> getUserList();




}
