package com.example.skgasutils.mapper;

import com.example.skgasutils.repository.EvuCdp;
import com.example.skgasutils.repository.EvuEmp;
import com.example.skgasutils.repository.EvuEmpCdp;
import com.example.skgasutils.repository.EvuMng;
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
     * EvuMng check
     * */
    public List<EvuMng> getEvuMngList();

    /**
     * CDP 팹핑
     * */
    public List<EvuCdp> getEvuCdp(String evuStdId);


    /**
     * EVU_EMP_CDP CHECK
     * */
    public List<EvuEmpCdp> getEvuEmpCdp();




}
