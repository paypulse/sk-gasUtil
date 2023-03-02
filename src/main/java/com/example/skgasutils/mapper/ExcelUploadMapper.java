package com.example.skgasutils.mapper;

import com.example.skgasutils.excelUpload.excelVo.ExcelEmpVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExcelUploadMapper {

    /**
     * Excelupload후 insert
     * */
    int insertEvuEmpId(List<ExcelEmpVo> totalEmpList);
}
