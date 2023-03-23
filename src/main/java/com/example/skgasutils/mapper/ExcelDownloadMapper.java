package com.example.skgasutils.mapper;


import com.example.skgasutils.excelDownload.downloadVo.EvuEmpMngVo;
import com.example.skgasutils.excelDownload.downloadVo.EvuTds1Vo;
import com.example.skgasutils.excelDownload.downloadVo.EvuTotDiffVo;
import com.example.skgasutils.excelDownload.downloadVo.EvuTotStandVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExcelDownloadMapper {


    /**
     * 1. 개인 평가 결과 리스트에 나오는 최종 등급과 상세 팝업에 나오는 최종 등급이 다른 경우
     * */
    List<EvuTotDiffVo> selectEvuTotDiff(Map<String, Object> param);


    /**
     * 2. 연말 평가 기준 엑셀 다운로드
     * */
    List<EvuTotStandVo> selectEndOfYearStandard(String evuStdId);


    /**
     * 3. 피평가자 정보 데이터 추출
     * */
    List<EvuEmpMngVo> selectEvuEmpInfoList(String evuStdId);


    /**
     * 목표 수립관련 데이터 한 눈에 보기
     * */
    List<EvuTds1Vo> selectTds1PersonInfo(String evuStdId);





}
