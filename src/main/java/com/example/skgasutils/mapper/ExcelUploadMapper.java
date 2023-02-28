package com.example.skgasutils.mapper;

import com.example.skgasutils.excelUpload.excelVo.ExcelEmpVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExcelUploadMapper {

    /**
     * Excelupload후 insert
     * */
    int insertEvuEmpId(ExcelEmpVo emp);
}
