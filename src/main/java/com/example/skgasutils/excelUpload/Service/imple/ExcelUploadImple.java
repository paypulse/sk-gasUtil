package com.example.skgasutils.excelUpload.Service.imple;

import com.example.skgasutils.excelUpload.Service.ExcelUploadService;
import com.example.skgasutils.excelUpload.excelVo.ExcelEmpVo;
import com.example.skgasutils.mapper.CommonMapper;
import com.example.skgasutils.mapper.ExcelUploadMapper;
import com.example.skgasutils.repository.EvuEmp;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExcelUploadImple implements ExcelUploadService {

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private ExcelUploadMapper excelUpoadMaper;

    @Override
    public int insertEvuEmp(Sheet worksheet, String evuStdId) {

        Row row = null;
        //evuEmpList
        List<EvuEmp> empList = commonMapper.getEvuEmpList(evuStdId);
        //new empList
        List<ExcelEmpVo> newEmpList = new ArrayList<>();
        //중복 제거된 empList
        List<ExcelEmpVo> totalEmpList = new ArrayList<>();

        //index
        int index = 0;

        //포함 체크 여부
        boolean isContainEmpId = false;
        //System.out.println("empList : " + empList);

        for (int i = 2; i < worksheet.getPhysicalNumberOfRows(); i++) {
            row = worksheet.getRow(i);
            ExcelEmpVo  emp = new ExcelEmpVo();
            emp.setEmpId(row.getCell(1).getStringCellValue());
            emp.setEvuEmpId(row.getCell(1).getStringCellValue());
            emp.setEvuStdId(evuStdId);
            emp.setEvu2Yn("N");
            emp.setCurStepCd("A0");
            emp.setEvuStatCd("E1");
            emp.setChasu(0);
            emp.setCdpCd("");
            emp.setInsUserId("00812");
            newEmpList.add(emp);
        }

        /**
         * id 값을 비교하여 중복 되지 않는 객체 찾기
         * */
        totalEmpList =  newEmpList.stream().filter(o -> empList.stream().noneMatch(n -> {
            return o.getEvuEmpId().equals(n.getEvuEmpId());
        })).collect(Collectors.toList());






        return 0;
    }
}
