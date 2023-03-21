package com.example.skgasutils.Utils;

import com.example.skgasutils.excelDownload.downloadVo.EvuTotDiffVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;  //만들 스타일
import org.apache.poi.hssf.usermodel.HSSFRow; //열
import org.apache.poi.hssf.usermodel.HSSFSheet;  //시트
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  //워크북
import org.apache.poi.ss.usermodel.CellStyle;  //셀 스타일
import org.apache.poi.ss.util.CellRangeAddress;  //병합

import java.io.*;
import java.net.URLEncoder;
import java.util.List;

public class FileOutput {

    public InputStream file;
    public XSSFWorkbook xssfWorkbook;
    public XSSFSheet xssfSheet;

    public FileOutput(String formPath, HttpServletRequest request) throws IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String path = request.getServletContext().getRealPath(formPath);
            file =  new FileInputStream(path);
            xssfWorkbook = new XSSFWorkbook(file);
            excelStyle(xssfWorkbook);
            xssfSheet = xssfWorkbook.getSheetAt(0);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 엑셀 양식 다운로드
     * */
    public void makeFile(HttpServletResponse response, XSSFSheet sheet, String fileName) throws IOException {
        fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+","%20");
        response.setContentType("application/vsd.ms-excel");
        response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
        xssfWorkbook.write(response.getOutputStream());
        xssfWorkbook.close();
    }


    /**
     * poi 엑셀 스타일
     * */
    public void excelStyle(XSSFWorkbook workbook){

       //셀 스타일 및 트 설정
       CellStyle  style= workbook.createCellStyle();
       style.setWrapText(true);
       //가운데 정렬
       style.setVerticalAlignment(VerticalAlignment.CENTER);
       //선 그리기
       style.setBorderLeft(BorderStyle.THICK);
       style.setBorderRight(BorderStyle.THICK);
       style.setBorderTop(BorderStyle.THICK);
       style.setBorderBottom(BorderStyle.THICK);

    }









}
