package com.example.skgasutils.excelUpload;

import com.example.skgasutils.Utils.CommonRes;
import com.example.skgasutils.Utils.FileInput;
import com.example.skgasutils.excelUpload.Service.ExcelUploadService;
import com.example.skgasutils.excelUpload.excelVo.ExcelEmpVo;

import io.swagger.v3.oas.annotations.Operation;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Slf4j
@NoArgsConstructor
@RestController
public class ExcelUpload {



    @Autowired
    private ExcelUploadService excelUploadService;


    /**
     * Excel upload
     * **/
    @Operation(summary = "엑셀 업로드 데이터 테스트", description = "엑셀 업로드 테스트 ")
    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CommonRes> uploadExcel(@RequestParam("file")MultipartFile file,@RequestParam String evuStdId) throws TikaException, IOException {


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
    @Operation(summary = "피평가자 업로드", description = "피평가자 업로드")
    @PostMapping(value = "/uploadEmpSave" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CommonRes> uploadEmpSave(@RequestParam(value="file", required = false)MultipartFile file,@RequestParam String evuStdId) throws IOException {


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
                            .status("SUCCESS")
                            .msg("insert success")
                            .build());
                }else{
                    if(rv == 0){
                        return ResponseEntity.ok(CommonRes.builder()
                                .data(rv)
                                .msg("이미 등록된 사용자 입니다.")
                                .status("SUCCESS")
                                .build());
                    }else{
                        //insert 안됨
                        return ResponseEntity.ok(CommonRes.builder()
                                .msg("insert fail")
                                .status("FAIL")
                                .build());
                    }


                }


            }catch (Exception e){
                return ResponseEntity.ok(CommonRes.builder()
                        .data(e)
                        .msg(e.getMessage())
                        .build());
            }


        }else{
            return ResponseEntity.ok(CommonRes.builder()
                    .status("FAIL")
                    .msg("no file")
                    .build());
        }


    }

    /**
     * 피평가자 <-> 평가자 맵핑
     * **/
    @Operation(summary = "평가자 업로드", description = "피평가자와 매칭 된 평가자 업로드")
    @PostMapping(value = "/uploadMngEmpSave", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CommonRes> uploadMngEmpSave(@RequestParam(value="file", required = false)MultipartFile file,@RequestParam String evuStdId) throws IOException {


        FileInput check = new FileInput();


        DataFormatter formatter = new DataFormatter();

        int rv=0;

        if(check.filecheck(file)){

            Sheet worksheet = check.worksheet(file);


            try{
                rv =excelUploadService.insertMngEmp(worksheet, evuStdId);

                if(rv >=1){
                    //insert 됨
                    return ResponseEntity.ok(CommonRes.builder()
                            .data(rv)
                            .status("SUCCESS")
                            .msg("insert success")
                            .build());
                }else{
                    if(rv == 0){
                        return ResponseEntity.ok(CommonRes.builder()
                                .data(rv)
                                .msg("이미 등록된 사용자 입니다.")
                                .status("SUCCESS")
                                .build());
                    }else{
                        //insert 안됨
                        return ResponseEntity.ok(CommonRes.builder()
                                .msg("insert fail")
                                .status("FAIL")
                                .build());
                    }

                }


            }catch (Exception e){
                return ResponseEntity.ok(CommonRes.builder()
                        .data(e)
                        .msg(e.getMessage())
                        .build());
            }


        }else{
            return ResponseEntity.ok(CommonRes.builder()
                    .status("FAIL")
                    .msg("no file")
                    .build());
        }


    }

    /**
     * 평가자 상태 값 변경 
     * */
    @Operation(summary = "평가자 상태값 변경 ", description = "평가자들의 상태값 변경 ")
    @PostMapping(value = "/updateMngStatCd", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CommonRes> updateMngStatCd(@RequestParam(value="file", required = false)MultipartFile file,@RequestParam String evuStdId) throws IOException {

        /**
         * 추가.
         * cdp_nm으로 cdp_cd를 찾아서 evu_emp_cdp 테이블에 맵칭
         * **/
        FileInput check = new FileInput();

        int rv=0;

        if(check.filecheck(file)){

            Sheet worksheet = check.worksheet(file);


            try{
                rv =excelUploadService.updateMngStatCd(worksheet, evuStdId);

                if(rv >=1){
                    //insert 됨
                    return ResponseEntity.ok(CommonRes.builder()
                            .data(rv)
                            .status("SUCCESS")
                            .msg("insert success")
                            .build());
                }else{
                    if(rv == 0){
                        return ResponseEntity.ok(CommonRes.builder()
                                .data(rv)
                                .msg("이미 등록된 사용자 입니다.")
                                .status("SUCCESS")
                                .build());
                    }else{
                        //insert 안됨
                        return ResponseEntity.ok(CommonRes.builder()
                                .msg("insert fail")
                                .status("FAIL")
                                .build());
                    }

                }


            }catch (Exception e){
                return ResponseEntity.ok(CommonRes.builder()
                        .data(e)
                        .msg(e.getMessage())
                        .build());
            }


        }else{
            return ResponseEntity.ok(CommonRes.builder()
                    .status("FAIL")
                    .msg("no file")
                    .build());
        }


    }
    
    

    /**
     * 피 평가자 CDP 맵핑
     * */
    @Operation(summary = "피평가자 cdpcd(역량 코드) 업로드 ", description = "피평가자들의 역량 코드 매칭")
    @PostMapping(value = "/uploadCdpEmpSave", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CommonRes> uploadCDPEmpSave(@RequestParam(value="file", required = false)MultipartFile file,@RequestParam String evuStdId) throws IOException {

        /**
         * 추가.
         * cdp_nm으로 cdp_cd를 찾아서 evu_emp_cdp 테이블에 맵칭
         * **/
        FileInput check = new FileInput();

        int rv=0;

        if(check.filecheck(file)){

            Sheet worksheet = check.worksheet(file);


            try{
                rv =excelUploadService.insertEmpCdp(worksheet, evuStdId);

                if(rv >=1){
                    //insert 됨
                    return ResponseEntity.ok(CommonRes.builder()
                            .data(rv)
                            .status("SUCCESS")
                            .msg("insert success")
                            .build());
                }else{
                    if(rv == 0){
                        return ResponseEntity.ok(CommonRes.builder()
                                .data(rv)
                                .msg("이미 등록된 사용자 입니다.")
                                .status("SUCCESS")
                                .build());
                    }else{
                        //insert 안됨
                        return ResponseEntity.ok(CommonRes.builder()
                                .msg("insert fail")
                                .status("FAIL")
                                .build());
                    }

                }


            }catch (Exception e){
                return ResponseEntity.ok(CommonRes.builder()
                        .data(e)
                        .msg(e.getMessage())
                        .build());
            }


        }else{
            return ResponseEntity.ok(CommonRes.builder()
                    .status("FAIL")
                    .msg("no file")
                    .build());
        }


    }



}

