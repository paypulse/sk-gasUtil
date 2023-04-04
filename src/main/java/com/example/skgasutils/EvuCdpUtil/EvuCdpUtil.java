package com.example.skgasutils.EvuCdpUtil;


import com.example.skgasutils.EvuCdpUtil.Service.EvuCdpUtilService;
import com.example.skgasutils.Utils.CommonRes;
import io.swagger.v3.oas.annotations.Operation;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@NoArgsConstructor
@RestController
public class EvuCdpUtil {


    @Autowired
    private EvuCdpUtilService evuCdpUtilService;

    /**
     * 기준 설정 -> 역량 평가 관리
     * 이전 년도 기준 역량 동일 적용시
     * */
    @Operation(summary = "이전 년도 기준 동일 역량 등록", description="이전 년도 기준 동일역량 등록시")
    @PostMapping(path = "/evuCapaRegist ")
    public ResponseEntity<CommonRes> uploadCdpCd(@RequestParam String lastEvuStdId, @RequestParam String nowEvuStdId){
        int rv =0;
        try{
            rv = evuCdpUtilService.insertEvuCdpCapa(lastEvuStdId, nowEvuStdId);
            if(rv >0){
                return ResponseEntity.ok(CommonRes.builder()
                        .status("SUCCESS")
                        .msg("success cdp insert")
                        .build());

            }else{
                return ResponseEntity.ok(CommonRes.builder()
                        .status("FALSE")
                        .msg("fail cdp insert")
                        .build());
            }

        }catch (Exception e){

            return ResponseEntity.ok(CommonRes.builder()
                    .data(e)
                    .msg(e.getMessage())
                    .build());


        }


    }




}
