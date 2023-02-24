package com.example.skgasutils.excelUpload.Service;


import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;


public interface ExcelUploadService {

    /**
     * 1. EMP 테이블에 등록
     * */
    public int insertEvuEmp(Row row, String evuStdId);


    /**
     *
     * */


}
