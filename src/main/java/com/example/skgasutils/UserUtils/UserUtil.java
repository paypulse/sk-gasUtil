package com.example.skgasutils.UserUtils;


import com.example.skgasutils.UserUtils.Service.UserUtilService;
import com.example.skgasutils.UserUtils.UserUtilVo.UserReqVo;
import com.example.skgasutils.Utils.CommonRes;
import io.swagger.v3.oas.annotations.Operation;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * 인의적으로 인사 db에 등록을 해야 할때
     * */
    @Operation(summary = "인사 DB 임의 등록 " ,description = "인사 DB에 없는 인사를 임의로 등록해야 할때")
    @GetMapping(path ="/userRegist")
    public ResponseEntity<CommonRes> userRegist(@RequestBody UserReqVo vo){
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
            if(rv >0){
                return ResponseEntity.ok(CommonRes.builder()
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





        return null;
    }











}
