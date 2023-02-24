package com.example.skgasutils.excelUpload.mapper;

import com.example.skgasutils.excelUpload.excelVo.EvuEmpVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExcelUploadMapper {

    /**
     * Excelupload후 insert
     * */
    int insertEvuEmpId(EvuEmpVo emp);
}
