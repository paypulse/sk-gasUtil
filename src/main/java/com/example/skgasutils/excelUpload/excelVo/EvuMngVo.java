package com.example.skgasutils.excelUpload.excelVo;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class EvuMngVo {

    //평가자ID
    private String evuMngId;
    //피평가자 번호
    private String evuEmpNo;
    //평가자 상태 코드
    private String mngStatCd;

}
