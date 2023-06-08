package com.example.skgasutils.mapper;


import com.example.skgasutils.UserUtils.UserUtilVo.UserReqVo;
import com.example.skgasutils.repository.EvuEmp;
import com.example.skgasutils.repository.User;
import com.example.skgasutils.repository.UserCareer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserUtilMapper {

    /**
     * User등록
     * */
    int userRegist(UserReqVo vo);


    /**
     * 단권 피평가자 등록
     * */
    int evuEmpRegist(UserReqVo vo);

    /**
     * 단권 evuEmpCdp 등록 할때
     */
    int evuEmpCdpRegist(UserReqVo vo);


    /**
     *  면수습 /계약직 추출
     * */
    List<UserReqVo> checkEmpCareerList(String evuStdId);









}
