package com.example.skgasutils.Utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

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

}