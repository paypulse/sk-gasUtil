package com.example.skgasutils.Utils;

import com.example.skgasutils.excelDownload.downloadVo.EvuTotDiffVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
    }










}
