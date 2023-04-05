package com.example.skgasutils.mapper;


import com.example.skgasutils.UserUtils.UserUtilVo.UserReqVo;
import org.apache.ibatis.annotations.Mapper;

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





}