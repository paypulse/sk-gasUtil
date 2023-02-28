package com.example.skgasutils.excelUpload.excelVo;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ExcelEmpVo {

    //empId
    private String empId;
    //mng1Id
    private String mng1Id;
    //mng2Id
    private String mng3Id;
    //cdpNm
    private String cdpNm;
    //2차 평가 여부 (Y/N)
    private String evu2Yn;
    //피평가자 번호
    private String evuEmpNo;
    //평가자 상태 코드
    private String mngStatCd;
    //평가 년도
    private String evuStdId;
    //사번
    private String evuEmpId;
    //curStepCd
    private String curStepCd;
    //state Cd
    private String evuStatCd;
    //chasu
    private int chasu;
    //cdpCd
    private String cdpCd;
    //등록id
    private String insUserId;







}
