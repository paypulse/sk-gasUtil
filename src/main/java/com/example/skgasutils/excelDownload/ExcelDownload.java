package com.example.skgasutils.excelDownload;


import com.example.skgasutils.Utils.CommonRes;
import com.example.skgasutils.Utils.FileOutput;
import com.example.skgasutils.excelDownload.Service.ExcelDownloadService;
import com.example.skgasutils.excelDownload.downloadVo.EvuTotDiffVo;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@NoArgsConstructor
@RestController
public class ExcelDownload {

    @Autowired
    private ExcelDownloadService excelDownloadService;

    /**
     * 1. 개인 평가 결과 리스트에 나오는 최종 등급과 상세 팝업에 나오는 최종 등급이 다른 경우
     * **/
    @GetMapping(value = "/selectTot3Diff")
    public void selectTot3Diff(@RequestParam("evuStdId")String evuStdId, @RequestParam("evuType")String evuType, HttpServletRequest request, HttpServletResponse response) throws IOException {

        //list 출력
        List<EvuTotDiffVo> result = excelDownloadService.selectEvuTotDiff(evuStdId, evuType);

        //excel 템플릿 양식 위치
        String formPath ="/excelTemplate/evuTotDiff.xlsx";

        //excel file down 명
        String fileName = "evuTotDiff.xlsx";

        FileOutput fileOutput = new FileOutput(formPath, request);

        int idx = 2;
        for(EvuTotDiffVo vo :result){
            fileOutput.xssfSheet.getRow(idx).getCell(1).setCellValue(vo.getEmpId());
            fileOutput.xssfSheet.getRow(idx).getCell(2).setCellValue(vo.getEmpNm());
            fileOutput.xssfSheet.getRow(idx).getCell(3).setCellValue(vo.getTotGrd3q());
            fileOutput.xssfSheet.getRow(idx).getCell(4).setCellValue(vo.getTotCfmGrd3Q());
            idx ++;
        }


        fileOutput.makeFile(response, fileOutput.xssfSheet, fileName);

    }


    /**
     * 연말 평가 기준 엑셀 다운로드
     * */





    /**
     * 2022 개인 역량 평가 엑셀 다운로드
     * */

    


}
