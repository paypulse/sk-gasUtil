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

import java.util.*;
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

        if(totalEmpList.size()>0){
            /**
             * 피평가자 등록
             * **/
            return excelUpoadMaper.insertEvuEmpId(totalEmpList);
        }else{
            return totalEmpList.size();
        }


    }


    /**
     * 피평가자 <-> 평가자 맵핑
     * **/
    @Override
    public int insertMngEmp(Sheet worksheet, String evuStdId) {
        Row row = null;
        //evuEmpList
        List<EvuEmp> empList = commonMapper.getEvuEmpList(evuStdId);

        //newEmpList
        List<ExcelEmpVo> newEmpList = new ArrayList<>();

        //empList에서 row에서 가져온 id를가지고 empno를 찾아 오기
        System.out.println("empList :" + empList);

        for(int i=2;i<worksheet.getPhysicalNumberOfRows();i++){
            row = worksheet.getRow(i);
            //row에서 id를 가져 오자.
            ExcelEmpVo data  = new ExcelEmpVo();
            data.setEmpId(row.getCell(1).getStringCellValue());

            //가지고 온 row의 id와 empList의 id를 비교해서 해당 empNo를 가져 오자.



            newEmpList.add(data);
        }






        return 0;
    }







}
