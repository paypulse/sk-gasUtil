package com.example.skgasutils.excelUpload.Service.imple;

import com.example.skgasutils.excelUpload.Service.ExcelUploadService;
import com.example.skgasutils.excelUpload.excelVo.EvuEmpVo;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

@Service
public class ExcelUploadImple implements ExcelUploadService {
    @Override
    public int insertEvuEmp(Row row ,String evuStdId) {

        EvuEmpVo emp = new EvuEmpVo();
        emp.setEvu2Yn("Y");
        emp.setEvuStdId(evuStdId);
        emp.setEvuEmpId(row.getCell(1).getStringCellValue());

       // System.out.println("check cell : " + emp.setEvuEmpId(row.getCell(1).getStringCellValue()) );




        return 0;
    }
}
