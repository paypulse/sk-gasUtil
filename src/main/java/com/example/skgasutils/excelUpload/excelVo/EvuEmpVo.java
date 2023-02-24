package com.example.skgasutils.excelUpload.excelVo;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class EvuEmpVo {
    //평가 년도
    private String evuStdId;
    //사번
    private String evuEmpId;

    //현재 stepCD

    //2차 평가 여부 (Y/N)
    private String evu2Yn;



}
