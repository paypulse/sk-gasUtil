package com.example.skgasutils.excelUpload;

import com.example.skgasutils.Utils.CommonRes;
import com.example.skgasutils.Utils.FileInput;
import com.example.skgasutils.excelUpload.Service.ExcelUploadService;
import com.example.skgasutils.excelUpload.excelVo.ExcelEmpVo;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@NoArgsConstructor
@Controller
public class ExcelUpload {



    @Autowired
    private ExcelUploadService excelUploadService;


    /**
     * Excel upload
     * **/
    @PostMapping("/upload")
    public ResponseEntity<CommonRes> uploadExcel(@RequestParam("file")MultipartFile file, Model model) throws TikaException, IOException {


        FileInput check = new FileInput();
        Row row;
        List<ExcelEmpVo> infos = new ArrayList<>();
        //등록 피평가자


        DataFormatter formatter = new DataFormatter();

        if(check.filecheck(file)){

            Sheet worksheet = check.worksheet(file);
            for (int i = 2; i < worksheet.getPhysicalNumberOfRows(); i++) { // 1번째 행부터 끝까지
                row = worksheet.getRow(i);
                ExcelEmpVo data = new ExcelEmpVo();
                data.setEmpId(row.getCell(1).getStringCellValue());
                data.setMng1Id(row.getCell(9).getStringCellValue());
                data.setMng3Id(formatter.formatCellValue(row.getCell(11)));
                data.setCdpNm(formatter.formatCellValue(row.getCell(13)));
                infos.add(data);
            }

            return ResponseEntity.ok(CommonRes.builder()
                    .data(infos)
                    .status("SUCCESS")
                    .msg("check test")
                    .build());

        }else{
            return ResponseEntity.ok(CommonRes.builder()
                    .data("{}")
                    .status("FALSE")
                    .msg("no file")
                    .build());
        }

    }


    /**
     * Excel upload and Emp save
     * **/
    @PostMapping("/uploadEmpSave")
    public ResponseEntity<CommonRes> uploadEmpSave(@RequestParam(value="file", required = false)MultipartFile file,@RequestParam String evuStdId,Model model) throws IOException {


        FileInput check = new FileInput();


        DataFormatter formatter = new DataFormatter();

        int rv=0;

        if(check.filecheck(file)){

            Sheet worksheet = check.worksheet(file);

            try{

                rv =excelUploadService.insertEvuEmp(worksheet, evuStdId);

                if(rv >=1){
                    //insert 됨
                    return ResponseEntity.ok(CommonRes.builder()
                            .data(rv)
                            .msg("insert success")
                            .build());
                }else{
                    //insert 안됨
                    return ResponseEntity.ok(CommonRes.builder()
                            .data(rv)
                            .msg("insert fail")
                            .build());
                }


            }catch (Exception e){
                return ResponseEntity.ok(CommonRes.builder()
                        .data(e)
                        .msg(e.getMessage())
                        .build());
            }


        }else{
            return ResponseEntity.ok(CommonRes.builder()
                    .status("FALSE")
                    .msg("no file")
                    .build());
        }


    }







}

