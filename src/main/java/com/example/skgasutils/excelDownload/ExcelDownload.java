package com.example.skgasutils.excelDownload;


import com.example.skgasutils.Utils.CommonRes;
import com.example.skgasutils.Utils.FileOutput;
import com.example.skgasutils.excelDownload.Service.ExcelDownloadService;
import com.example.skgasutils.excelDownload.downloadVo.EvuEmpMngVo;
import com.example.skgasutils.excelDownload.downloadVo.EvuTds1Vo;
import com.example.skgasutils.excelDownload.downloadVo.EvuTotDiffVo;
import com.example.skgasutils.excelDownload.downloadVo.EvuTotStandVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Iterator;
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
    @Operation(summary = "연말 평가 결과가 다른 경우", description = "개인 평가 결과 리스트에 나오는 최종 등급과 상세 팝업에 나오는 최종 등급이 다른 경우")
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
     * 2. (2022년 이후) 연말 평가 기준 엑셀 다운로드
     * */
    @Operation(summary = "연말평가기준 다운로드", description = " 2. (2022년 이후) 연말 평가 기준 엑셀 다운로드")
    @GetMapping(value = "/selectEndOfYearStandard")
    public void selectEndOfYearStandard(@RequestParam("evuStdId")String evuStdId, HttpServletRequest request, HttpServletResponse response) throws IOException {

        //list 출력
        List<EvuTotStandVo> result = excelDownloadService.selectEndOfYearStandard(evuStdId);

        //excel 템플릿 양식 위치
        String formPath ="/excelTemplate/indiviaul_capability.xlsx";

        //excel file down 명
        String fileName = "indivisual_capability.xlsx";

        FileOutput fileOutput = new FileOutput(formPath, request);

        int rownum=2;
        int columnIndex = 1;
        for(EvuTotStandVo vo :result){
            Row row = fileOutput.xssfSheet.createRow(rownum);
            row.createCell(1).setCellValue(vo.getEmpNm());
            row.createCell(2).setCellValue(vo.getEmpId());
            row.createCell(3).setCellValue(vo.getPostNm());
            row.createCell(4).setCellValue(vo.getCdpNm());
            row.createCell(5).setCellValue(vo.getCateNm1());
            row.createCell(6).setCellValue(vo.getCateNm2());
            row.createCell(7).setCellValue(vo.getCompTitle());
            row.createCell(8).setCellValue(vo.getDefineCd());
            row.createCell(9).setCellValue(vo.getCustomYn());
            row.createCell(10).setCellValue(vo.getPriority());
            row.createCell(11).setCellValue(vo.getMng1Score1q());
            row.createCell(12).setCellValue(vo.getMng1Afscore1q());
            row.createCell(13).setCellValue(vo.getMng1Score2q());
            row.createCell(14).setCellValue(vo.getMng1Afscore2q());
            row.createCell(15).setCellValue(vo.getMng1Score3q());
            row.createCell(16).setCellValue(vo.getMng1Afscore3q());


            rownum ++;
        }

        fileOutput.makeFile(response, fileOutput.xssfSheet, fileName);

    }



    /**
     * 피평가자 정보 데이터 추출
     * */
    @Operation(summary = "피평가자 정보 데이터 추출", description = "피평가자 정보 데이터 추출")
    @GetMapping(value = "/selectEvuEmpInfoList")
    public void selectEvuEmpInfoList(@RequestParam("evuStdId")String evuStdId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<EvuEmpMngVo>  result =  excelDownloadService.selectEvuEmpInfoList(evuStdId);

        //excel 템플릿 양식 위치
        String formPath ="/excelTemplate/empInfoList.xlsx";

        //excel file down 명
        String fileName = "empInfoList.xlsx";

        FileOutput fileOutput = new FileOutput(formPath, request);

        int rownum=2;
        int columnIndex = 1;
        for(EvuEmpMngVo vo :result){
            Row row = fileOutput.xssfSheet.createRow(rownum);
            row.createCell(1).setCellValue(vo.getEmpId());
            row.createCell(2).setCellValue(vo.getEmpNm());
            row.createCell(3).setCellValue(vo.getPostNm());
            row.createCell(4).setCellValue(vo.getJgNm());
            row.createCell(5).setCellValue(vo.getOrgNm());
            row.createCell(6).setCellValue(vo.getEvu2Yn());
            row.createCell(7).setCellValue(vo.getEvuMng1());
            row.createCell(8).setCellValue(vo.getEvuMng2());
            row.createCell(9).setCellValue(vo.getEvuMng3());

            rownum ++;
        }

        fileOutput.makeFile(response, fileOutput.xssfSheet, fileName);

    }


    /**
     * 목표 수립관련 데이터 한 눈에 보기
     * **/
    @Operation(summary = "목표 수립시 업적 평가 데이터", description = "목표 수립 업적 평가 관련 데이터, 목표 수립에서는 1차 평가자만 지정 평가자가 될 수 있다.")
    @GetMapping(value = "/selectTds1PersonInfo")
    public void selectTds1PersonInfo(@RequestParam("evuStdId")String evuStdId, HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<EvuTds1Vo>  result = excelDownloadService.selectTds1PersonInfoList(evuStdId);
        //excel 템플릿 양식 위치
        String formPath ="/excelTemplate/tds1.xlsx";
        //excel file down 명
        String fileName = "tds1.xlsx";
        FileOutput fileOutput = new FileOutput(formPath, request);
        //style 적용 cell
        int[] cellNum;

        int rownum=3;
        int columnIndex = 1;
        for(EvuTds1Vo vo :result){
            Row row = fileOutput.xssfSheet.createRow(rownum);
            row.createCell(1).setCellValue(vo.getEvuStdId());
            row.createCell(2).setCellValue(vo.getEmpNm());
            row.createCell(3).setCellValue(vo.getEvuEmpNo());
            row.createCell(4).setCellValue(vo.getEvuEmpId());
            row.createCell(5).setCellValue(vo.getPostNm());
            row.createCell(6).setCellValue(vo.getJobNm());
            row.createCell(7).setCellValue('P');
            row.createCell(8).setCellValue(vo.getTaskNm());
            row.createCell(9).setCellValue(vo.getWtScore());
            row.createCell(10).setCellValue(vo.getExecPlan());
            row.createCell(11).setCellValue(vo.getMng1AchvText1Q());
            row.createCell(12).setCellValue(vo.getEmpMng());

            //cell style을 적용할 cell 번호
            cellNum = new int[]{10, 11, 12};
            fileOutput.excelStyle(fileOutput.xssfWorkbook, row, cellNum);
            rownum ++;
        }

        fileOutput.makeFile(response, fileOutput.xssfSheet, fileName);

    }













}
