package com.example.skgasutils.excelDownload.Service;

import com.example.skgasutils.excelDownload.downloadVo.EvuTotDiffVo;
import com.example.skgasutils.excelDownload.downloadVo.EvuTotStandVo;

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

}
