package com.example.skgasutils.excelUpload;

import com.example.skgasutils.Utils.CommonRes;
import com.example.skgasutils.Utils.FileInput;
import com.example.skgasutils.excelUpload.excelVo.ExcelEmpVo;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
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

    /**
     * Excel upload
     * **/
    @PostMapping("/upload")
    public ResponseEntity<CommonRes> uploadExcel(@RequestParam("file")MultipartFile file, Model model) throws TikaException, IOException {


        FileInput check = new FileInput();

        //inputStream 나중에 따로 빼기
        InputStream is = file.getInputStream();
        Tika tika = new Tika();
        String mimeType = tika.detect(is);
        log.info("mimType:  ", mimeType);



        Workbook workbook= new XSSFWorkbook(file.getInputStream());
        log.info("workbook check : " , workbook);
        Sheet worksheet = workbook.getSheetAt(0);



        Row row;
        List<ExcelEmpVo> infos = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

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
    }

}

