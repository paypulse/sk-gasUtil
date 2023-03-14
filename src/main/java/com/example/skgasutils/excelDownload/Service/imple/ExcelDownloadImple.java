package com.example.skgasutils.excelDownload.Service.imple;

import com.example.skgasutils.excelDownload.Service.ExcelDownloadService;
import com.example.skgasutils.excelDownload.downloadVo.EvuTotDiffVo;
import com.example.skgasutils.mapper.ExcelDownloadMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExcelDownloadImple  implements ExcelDownloadService {

    @Autowired
    private ExcelDownloadMapper excelDownloadMapper;

    @Override
    public List<EvuTotDiffVo> selectEvuTotDiff(String evuStdId, String evuType) {

        Map<String,Object> param = new HashMap<>();
        param.put("evuStdId" , evuStdId);
        param.put("evuType", evuType);

        return excelDownloadMapper.selectEvuTotDiff(param);

    }
}
