package com.example.skgasutils.mapper;

import com.example.skgasutils.excelUpload.excelVo.ExcelEmpVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExcelUploadMapper {

    /**
     * Excelupload후 insert
     * 다권 피평가자 등록
     * */
    int insertEvuEmpId(List<ExcelEmpVo> totalEmpList);

    /**
     * 1차 평가자 insert
     * **/
    int insertEvuMng1(List<ExcelEmpVo> mngList1);

    /**
     * 최종 평가자 insert
     * */
    int insertEvuMng3(List<ExcelEmpVo> mngList3);

    /**
     * cdp_cd mapping
     * **/
    int insertCdpCd(List<ExcelEmpVo> cdpList);

}
