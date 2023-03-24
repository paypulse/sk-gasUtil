package com.example.skgasutils.excelDownload.Service.imple;

import com.example.skgasutils.Utils.Convert;
import com.example.skgasutils.excelDownload.Service.ExcelDownloadService;
import com.example.skgasutils.excelDownload.downloadVo.EvuEmpMngVo;
import com.example.skgasutils.excelDownload.downloadVo.EvuTds1Vo;
import com.example.skgasutils.excelDownload.downloadVo.EvuTotDiffVo;
import com.example.skgasutils.excelDownload.downloadVo.EvuTotStandVo;
import com.example.skgasutils.mapper.CommonMapper;
import com.example.skgasutils.mapper.ExcelDownloadMapper;
import com.example.skgasutils.repository.EvuMng;
import com.example.skgasutils.repository.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class ExcelDownloadImple  implements ExcelDownloadService {

    @Autowired
    private  ExcelDownloadMapper excelDownloadMapper;

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public List<EvuTotDiffVo> selectEvuTotDiff(String evuStdId, String evuType) {

        Map<String,Object> param = new HashMap<>();
        param.put("evuStdId" , evuStdId);
        param.put("evuType", evuType);

        return excelDownloadMapper.selectEvuTotDiff(param);

    }

    @Override
    public List<EvuTotStandVo> selectEndOfYearStandard(String evuStdId) {
        return excelDownloadMapper.selectEndOfYearStandard(evuStdId);
    }

    @Override
    public List<EvuEmpMngVo> selectEvuEmpInfoList(String evuStdId) {
        return excelDownloadMapper.selectEvuEmpInfoList(evuStdId);
    }

    @Override
    public List<EvuTds1Vo> selectTds1PersonInfoList(String evuStdId) {

        List<EvuTds1Vo> list = excelDownloadMapper.selectTds1PersonInfo(evuStdId);
        List<EvuMng> mng = commonMapper.getMngList();

        for(EvuTds1Vo vo :list){
            for(EvuMng vo1: mng){
                if(vo.getEvuEmpNo().equals(vo1.getEvuEmpNo())){
                    vo.setEmpMng(vo1.getEvuMngNm());
                }
            }
        }

        return list;
    }
}
