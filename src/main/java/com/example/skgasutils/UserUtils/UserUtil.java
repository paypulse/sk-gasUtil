package com.example.skgasutils.UserUtils;


import com.example.skgasutils.UserUtils.Service.UserUtilService;
import com.example.skgasutils.UserUtils.UserUtilVo.UserReqVo;
import com.example.skgasutils.Utils.CommonRes;
import com.example.skgasutils.Utils.CommonUtil;
import com.example.skgasutils.Utils.FileInput;
import com.example.skgasutils.Utils.FileOutput;
import com.example.skgasutils.repository.EvuEmp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.tika.config.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 여기서 부텀 , 화면이 필요 하다.
 * **/



@Slf4j
@NoArgsConstructor
@RestController
public class UserUtil {

    @Autowired
    private UserUtilService userUtilService;

    /**
     * TODO. User 인사 정보 배치 및 스케쥴링 하는 로직 좀 추가 하자.
     * **/


    /**
     * 인의적으로 인사 db에 등록을 해야 할때
     * */
    @Operation(summary = "인사 DB 임의 등록 및 피평가자 등록 " ,description = "인사 DB, 피평가자, 역량을 임의로 등록 하고자 할때")
    @PostMapping(value ="/userRegist")
    public ResponseEntity<CommonRes> userRegist(@RequestBody UserReqVo vo){

        System.out.println("UserReqVo : " + vo );
        /**
         * TODO.
         * user에 등록 하고
         * cdp_cd도 등록 해줘야 하는 경우
         * user, evu_emp, evu_emp_cdp에 등록 해주기 한번에 등록 해주기
         * cdp_nm이 존재 하는지 확인 해주는 로직 추가 하기
         * **/
        int rv = 0;
        try{
            rv = userUtilService.userRegistService(vo);
            System.out.println("rv :" + rv);
            if(rv >0){

                return ResponseEntity.ok(CommonRes.builder()
                        .data(rv)
                        .status("SUCCESS")
                        .msg("success regist user")
                        .build());


            }else{
                return ResponseEntity.ok(CommonRes.builder()
                        .status("FALSE")
                        .msg("fail regist user")
                        .build());

            }

        }catch (Exception e){
            return ResponseEntity.ok(CommonRes.builder()
                    .data(e)
                    .msg(e.getMessage())
                    .build());
        }

    }


    /**
     * 인사 DB에만 저장 해야 할때
     * **/
    @Operation(summary = "인사 DB 에만 등록 하고자 할때 " ,description = "인사 DB에만  임의로 등록 하고자 할때")
    @GetMapping(value ="/onlyUserRegist")
    public ResponseEntity<CommonRes> onlyUserRegist(@RequestParam String evuStdId, @RequestParam String empNm){

        int rv =0;
        try{
            rv = userUtilService.onlyUserRegist(evuStdId,empNm);
            if(rv >0){
                return ResponseEntity.ok(CommonRes.builder()
                        .data(rv)
                        .status("SUCCESS")
                        .msg("success regist user")
                        .build());
            }else{
                return ResponseEntity.ok(CommonRes.builder()
                        .status("FALSE")
                        .msg("fail regist user")
                        .build());
            }

        }catch (Exception e){
            return ResponseEntity.ok(CommonRes.builder()
                    .data(e)
                    .msg(e.getMessage())
                    .build());


        }



    }




    /**
     * 바뀐 계정으로 사용자 정보를 이관해야 할때
     * */
//    @Operation(summary = "바뀐 계정으로 사용자 정보를 이관해야 할때", description = "이전 계정의 데이터를 바뀐 계정으로 데이터 이관해야 할때")
//    @GetMapping(path="/alterUserData")
//    public ResponseEntity<CommonRes> alterUserData(){
//
//        /**
//         * 이관 해야 될 테이블들
//         * evu_emp
//         * 	evu_mng
//         * 	evu_task
//         * 	evu_tot
//         * 	evu_comp
//         * 	evu_feedback
//         * */
//
//
//        return null;
//    }



    /**
     * 엑셀 파일 명단중  면수습/정규 회원 구분
     * */

    @Operation(summary = "면수습/정규 구분", description = "면수습/정규 구분")
    @PostMapping(value="/checkEmpGubun" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public void checkEmpGubun( @RequestParam("evuStdId")String evuStdId,@RequestParam(value="file", required = false)MultipartFile file,HttpServletRequest request, HttpServletResponse response) throws IOException {

        //excel 템플릿 양식위치
        String formPath = "/excelTemplate/userCareer.xlsx";
        //excel file 명
        String fileName = "userCareer.xlsx";
        FileOutput fileOutput = new FileOutput(formPath, request);
        FileInput check = new FileInput();


        //면수습인지 아닌지 check 하는 로직
        if(file.getResource().exists()) {

            Sheet worksheet = check.worksheet(file);


            //면수습 (flag  : 1)
            List<UserReqVo> probation = userUtilService.checkEmpAppntList(evuStdId, worksheet, 1);

            //계약직 (flag : 2)
            List<UserReqVo> partTime = userUtilService.checkEmpAppntList(evuStdId, worksheet, 2);

            System.out.println("probation : " + probation);
            System.out.println("partTime : " + partTime.size());


            /**
             * 면수습/계약직 출력
             * */
            int rownum = 4;
            Row voCnt = fileOutput.xssfSheet.getRow(2);
            voCnt.createCell(2).setCellValue(probation.size());
            voCnt.createCell(7).setCellValue(partTime.size());

            if (!probation.isEmpty() || !partTime.isEmpty()) {
                for (UserReqVo vo : probation) {
                    Row row = fileOutput.xssfSheet.createRow(rownum);
                    row.createCell(1).setCellValue(vo.getEmpId());
                    row.createCell(2).setCellValue(vo.getEmpNm());
                    row.createCell(3).setCellValue(vo.getAppntCd());
                    row.createCell(4).setCellValue(vo.getAppntNm());

                    rownum++;


                }

                for(UserReqVo vo : partTime){
                    Row row = fileOutput.xssfSheet.createRow(rownum);
                    row.createCell(1).setCellValue(vo.getEmpId());
                    row.createCell(2).setCellValue(vo.getEmpNm());
                    row.createCell(3).setCellValue(vo.getAppntCd());
                    row.createCell(4).setCellValue(vo.getAppntNm());

                    rownum++;
                }


                fileOutput.makeFile(response, fileOutput.xssfSheet, fileName);
            }




        }



    }












}
