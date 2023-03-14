package com.example.skgasutils.mapper;


import com.example.skgasutils.excelDownload.downloadVo.EvuTotDiffVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExcelDownloadMapper {


    /**
     * 1. 개인 평가 결과 리스트에 나오는 최종 등급과 상세 팝업에 나오는 최종 등급이 다른 경우
     * */
    List<EvuTotDiffVo> selectEvuTotDiff(Map<String, Object> param);


}
