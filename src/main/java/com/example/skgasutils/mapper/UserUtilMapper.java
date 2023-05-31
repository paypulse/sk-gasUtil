package com.example.skgasutils.mapper;


import com.example.skgasutils.UserUtils.UserUtilVo.UserReqVo;
import com.example.skgasutils.repository.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
     *
     * */






}
