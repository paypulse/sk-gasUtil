package com.example.skgasutils.mapper;

import com.example.skgasutils.repository.EvuEmp;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
@Component
public interface CommonMapper {
    /**
     * EvuEmp check
     * */
    public List<EvuEmp> getEvuEmpList(String evuStdId);
}
