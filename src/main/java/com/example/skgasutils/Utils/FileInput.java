package com.example.skgasutils.Utils;

import com.example.skgasutils.excelUpload.excelVo.ExcelEmpVo;
import com.example.skgasutils.mapper.ExcelUploadMapper;
import com.example.skgasutils.repository.EvuEmp;
import com.example.skgasutils.repository.EvuMng;
import com.example.skgasutils.repository.User;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileInput {

    /**
     * file input stream check
     * */
    public Boolean filecheck(MultipartFile file) throws IOException {
        InputStream is  = file.getInputStream();
        Tika tika  = new Tika();
        String mimeType = tika.detect(is);

        if(mimeType.equals(null)){
            return false;
        }else{
            return true;
        }
    }


    public Sheet worksheet(MultipartFile file) throws  IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet worksheet = workbook.getSheetAt(0);

        return worksheet;
    }


    public List<ExcelEmpVo> insertMngList(int chasu, Map<String,Object> info){
        List<ExcelEmpVo> excelMngList = new ArrayList<>();
        List<ExcelEmpVo> insertMngList  = new ArrayList<>();

        DataFormatter formatter = new DataFormatter();
        Row row = null;

        Sheet worksheet = (Sheet) info.get("workSheet");
        List<EvuEmp> empList = (List<EvuEmp>) info.get("empList");
        List<EvuMng> mngList = (List<EvuMng>) info.get("mngList");

        //인사DB인 userList
        List<User> userList = (List<User>) info.get("userList");



        //1차 평가자
        List<EvuMng> mngList1 = mngList.stream().filter(n -> {
            return (n.getChasu() == 1);
        }).collect(Collectors.toList());

        //3차 평가자
        List<EvuMng> mngList3 = mngList.stream().filter(n -> {
            return (n.getChasu() == 3);
        }).collect(Collectors.toList());



        for(int i=2;i<worksheet.getPhysicalNumberOfRows();i++){
            row = worksheet.getRow(i);

            ExcelEmpVo data = new ExcelEmpVo();
            data.setEmpId(row.getCell(1).getStringCellValue());
            data.setEvuEmpId(row.getCell(1).getStringCellValue());

            Optional<EvuEmp> emp = empList.stream().filter(s -> s.getEvuEmpId().equals(data.getEmpId())).findAny();
            data.setEvuEmpNo(Integer.toString(emp.get().getEvuEmpNo()));
            data.setChasu(chasu);
            //chasu
            if(chasu == 1){
                //1차 평가자
                data.setMng1Id(row.getCell(9).getStringCellValue());
                //1차 평가자의 orgId setting
                userList.forEach(s ->{
                    if(s.getEmpId().equals(data.getMng1Id())){
                        data.setMngOrgId(s.getOrgId());
                    }
                });
            }else{
                //최종 평가자
                data.setMng3Id(formatter.formatCellValue(row.getCell(11)));
                //1차 평가자의 orgId setting
                userList.forEach(s ->{
                    if(s.getEmpId().equals(data.getMng3Id())){
                        data.setMngOrgId(s.getOrgId());
                    }
                });

            }
            data.setInsUserId("00812");
            data.setMngStatCd("E1");

            excelMngList.add(data);
        }

        if(chasu ==1){
            insertMngList = excelMngList.stream()
                    .filter(n -> mngList1.stream().anyMatch(b ->{
                        return (b.getChasu() != 3) && (b.getChasu() == chasu);
                    }))
                    .filter(n-> mngList1.stream().noneMatch(c->{
                        return c.getEvuEmpNo().equals(n.getEvuEmpNo());
                    })).collect(Collectors.toList());
        }else{

            insertMngList = excelMngList.stream()
                    .filter(n -> mngList3.stream().anyMatch(d ->{
                        return (d.getChasu() != 1) && (d.getChasu() == chasu);
                    }))
                    .filter(n-> mngList3.stream().noneMatch(c->{
                        return c.getEvuEmpNo().equals(n.getEvuEmpNo());
                    })).collect(Collectors.toList());
        }
        return insertMngList;
    }












}