package com.example.skgasutils.excelUpload.Service.imple;

import com.example.skgasutils.Utils.FileInput;
import com.example.skgasutils.excelUpload.Service.ExcelUploadService;
import com.example.skgasutils.excelUpload.excelVo.ExcelEmpVo;
import com.example.skgasutils.mapper.CommonMapper;
import com.example.skgasutils.mapper.ExcelUploadMapper;
import com.example.skgasutils.mapper.UserUtilMapper;
import com.example.skgasutils.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;



@Service
@Slf4j
public class ExcelUploadImple implements ExcelUploadService {

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private ExcelUploadMapper excelUpoadMaper;



    @Override
    public int insertEvuEmp(Sheet worksheet, String evuStdId) {

        Row row = null;

        //evuEmpList
        List<EvuEmp> empList = commonMapper.getEvuEmpList(evuStdId);
        //new empList
        List<ExcelEmpVo> newEmpList = new ArrayList<>();
        //중복 제거된 empList
        List<ExcelEmpVo> totalEmpList = new ArrayList<>();
        //USER LIST
        List<User> userList = commonMapper.getUserList();

        System.out.println(userList);

        //index
        int index = 0;

        //포함 체크 여부
        boolean isContainEmpId = false;
        //System.out.println("empList : " + empList);

        for (int i = 2; i < worksheet.getPhysicalNumberOfRows(); i++) {

            row = worksheet.getRow(i);
            ExcelEmpVo  emp = new ExcelEmpVo();
            emp.setEmpId(row.getCell(1).getStringCellValue());
            emp.setEvuEmpId(row.getCell(1).getStringCellValue());
            emp.setEvuStdId(evuStdId);
            emp.setEvu2Yn("N");
            emp.setCurStepCd("A0");
            emp.setEvuStatCd("E1");
            emp.setChasu(0);
            emp.setCdpCd("");
            emp.setInsUserId("00812");
            newEmpList.add(emp);
        }

        /**
         * id 값을 비교하여 중복 되지 않는 객체 찾기
         * */
        totalEmpList =  newEmpList.stream().filter(o -> empList.stream().noneMatch(n -> {
            return o.getEvuEmpId().equals(n.getEvuEmpId());
        })).collect(Collectors.toList());

        //user에 있는 orgId -> empOrgId에 맵핑해주기
        totalEmpList.forEach(id ->{
            userList.forEach(empId ->{
                if(id.getEvuEmpId().equals(empId.getEmpId())){
                    id.setEmpOrgId(empId.getOrgId());
                }
            });
        });

        System.out.println(totalEmpList.size());

        if(totalEmpList.size()>0){
            /**
             * 피평가자 등록
             * **/
            return excelUpoadMaper.insertEvuEmpId(totalEmpList);
        }else{
            return totalEmpList.size();
        }


    }


    /**
     * 피평가자 <-> 평가자 맵핑
     * **/
    @Override
    public int insertMngEmp(Sheet worksheet, String evuStdId) {
        //평가자 list
        List<EvuEmp> empList = commonMapper.getEvuEmpList(evuStdId);
        //피평가자 list
        List<EvuMng> mngList = commonMapper.getEvuMngList();
        //인사DB
        List<User> userList = commonMapper.getUserList();


        //1차 평가자
        int resultMng1 =0;
        //최종 평가자
        int resultMng3 =0;



        Map<String,Object> insertInfo = new HashMap<>();
        insertInfo.put("empList", empList);
        insertInfo.put("mngList", mngList);
        insertInfo.put("workSheet", worksheet);
        insertInfo.put("evuStdId",evuStdId);
        insertInfo.put("userList", userList);

        FileInput fileInput = new FileInput();

        //1차 평가자 insert 결과
        List<ExcelEmpVo> mng1 = fileInput.insertMngList(1, insertInfo);
        //최종 평가자 insert 결과
        List<ExcelEmpVo> mng3 = fileInput.insertMngList(3, insertInfo);

        System.out.println("check mng3 : " + mng3);
        System.out.println("check mng3 size : " + mng3.size());
        System.out.println("check mng1 : " + mng1);
        System.out.println("check mng1 size : " + mng1.size());


        ///평가자 mng_org_id ->  user에서 가져 온다.


        if(mng1.size() >0){
            resultMng1 = excelUpoadMaper.insertEvuMng1(mng1);
        }

        if(mng3.size() >0){
            resultMng3 = excelUpoadMaper.insertEvuMng3(mng3);
        }


        return resultMng1 + resultMng3;
    }


    /**
     * 피 평가자 CDP 맵핑
     * */
    @Override
    public int insertEmpCdp(Sheet worksheet, String evuStdId) {
        //cdpNm  -> cdpCd를 찾아 내기위해
        List<EvuCdp> cdpList = commonMapper.getEvuCdp(evuStdId);

        List<EvuEmp> empList = commonMapper.getEvuEmpList(evuStdId);
        List<EvuEmpCdp> empCdpList = commonMapper.getEmpCdpList(evuStdId);


        List<ExcelEmpVo> excelList = new ArrayList<>();
        List<ExcelEmpVo> insertEmpCdpList = new ArrayList<>();

        int resultCdpMapping =0;


        //formatter
        DataFormatter formatter = new DataFormatter();

        //cdp_nm에서 cdp_cd 가져 오기
        Row row = null;
          for(int i=2;i<worksheet.getPhysicalNumberOfRows();i++){
            row = worksheet.getRow(i);

            ExcelEmpVo data = new ExcelEmpVo();

            data.setEmpId(row.getCell(1).getStringCellValue());
            data.setEvuEmpId(row.getCell(1).getStringCellValue());
            Optional<EvuEmp> emp = empList.stream().filter(s -> s.getEvuEmpId().equals(data.getEmpId())).findAny();
            data.setEvuEmpNo(Integer.toString(emp.get().getEvuEmpNo()));

            //cdp_cd
            Row finalRow = row;
            String cdpNm = formatter.formatCellValue(finalRow.getCell(13));
            Optional<EvuCdp> cdp = cdpList.stream().filter(s -> s.getCdpNm().equals(cdpNm)).findAny();
            if(cdp.isPresent()){
                data.setCdpCd(cdp.get().getCdpCd());
            }else{
                data.setCdpCd("");
            }

            data.setStepCd("B0");
            data.setEvuStdId(evuStdId);
            data.setInsUserId("00812");
            excelList.add(data);
          }


        resultCdpMapping = excelUpoadMaper.insertCdpCd(excelList);

        return resultCdpMapping;
    }











}
