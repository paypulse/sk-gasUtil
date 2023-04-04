package com.example.skgasutils.mapper;


import com.example.skgasutils.UserUtils.UserUtilVo.UserReqVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserUtilMapper {

    /**
     * User등록
     * */
    int userRegist(UserReqVo vo);





}
