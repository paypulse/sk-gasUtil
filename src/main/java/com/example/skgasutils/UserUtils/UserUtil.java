package com.example.skgasutils.UserUtils;


import com.example.skgasutils.UserUtils.Service.UserUtilService;
import com.example.skgasutils.UserUtils.UserUtilVo.UserReqVo;
import com.example.skgasutils.Utils.CommonRes;
import io.swagger.v3.oas.annotations.Operation;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Operation(summary = "바뀐 계정으로 사용자 정보를 이관해야 할때", description = "이전 계정의 데이터를 바뀐 계정으로 데이터 이관해야 할때")
    @GetMapping(path="/alterUserData")
    public ResponseEntity<CommonRes> alterUserData(){

        /**
         * 이관 해야 될 테이블들
         * evu_emp
         * 	evu_mng
         * 	evu_task
         * 	evu_tot
         * 	evu_comp
         * 	evu_feedback
         * */


        return null;
    }











}
