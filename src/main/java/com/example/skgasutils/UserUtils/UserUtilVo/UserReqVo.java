package com.example.skgasutils.UserUtils.UserUtilVo;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserReqVo {

    //평가번호
    private String evuStdId;
    //피평가자 번호
    private String evuEmpNo;

    //현재 평가 step
    private String curStepCd;
    //현재 stat
    private String evuStatCd;

    //user 사번
    private String empId;
    //사원명 
    private String empNm;
    //일련 번호
    private int seqNo;
    //조직 ID
    private String orgId;
    //조직 명
    private String orgNm;
    //직책 코드
    private String dutyCd;
    //직책 명
    private String dutyNm;
    //직군 명
    private String jgNm;
    //직군 ID
    private String jgId;
    //직무ID
    private String jobId;
    //직무 명
    private String jobNm;
    //직위코드
    private String postCd;
    //직위명
    private String postNm;
    private String bandCd;

    //등록 ID
    private String insUserId;
    //등록 년월일
    private String insYmdhms;

    //cdpNm
    private String cdpNm;
    private String cdpCd;

    //직급년차기준일
    private String gradeStaYmd;
    private String postAge;

}
