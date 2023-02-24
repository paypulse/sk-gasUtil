package com.example.skgasutils.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface ComMapper {

    /**
     * EVU_EMP_ID 중복 체크
     * */
    int countEvuEmpId(Map<String,Object> emp);

}
