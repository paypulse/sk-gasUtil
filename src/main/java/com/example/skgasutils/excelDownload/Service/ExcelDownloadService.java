package com.example.skgasutils.excelDownload.Service;

import com.example.skgasutils.excelDownload.downloadVo.*;

import java.util.List;

public interface ExcelDownloadService {

    /**
     * 개인 평가 결과 리스트에 나오는 최종 등급과 상세 팝업에 나오는 최종 등급이 다른 경우
    **/
    public List<EvuTotDiffVo>  selectEvuTotDiff(String evuStdId, String evuType);


    /**
     *  연말 평가 기준 엑셀 다운로드
     * */
    public List<EvuTotStandVo> selectEndOfYearStandard(String evuStdId);

    /**
     * 피평가자 정보 데이터 추출
     * **/
    public List<EvuEmpMngVo> selectEvuEmpInfoList(String evuStdId);


    /**
     * 목표 수립 관련 데이터 한 눈에 보기
     * */
    public List<EvuTds1Vo> selectTds1PersonInfoList(String evuStdId);


    public List<EvuTds2> selectTds2FeedbackNotd(String evuStdId);

    public List<EvuTds2> selectTds2FeedbackResult(String evuStdId);


    /**
     *  연말 평가 기준 엑셀 다운로드
     * */
    public List<EvuTotStandVo> selectTds2Cdp(String evuStdId);


}

