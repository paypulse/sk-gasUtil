package com.example.skgasutils.UserUtils.Service.imple;


import com.example.skgasutils.UserUtils.Service.UserUtilService;
import com.example.skgasutils.UserUtils.UserUtilVo.UserReqVo;
import com.example.skgasutils.mapper.CommonMapper;
import com.example.skgasutils.mapper.UserUtilMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserUtilServiceImpl implements UserUtilService {

    @Autowired
    private CommonMapper commonMapper;


    @Autowired
    private UserUtilMapper userUtilMapper;



    /**
     * TODO.
     * user에 등록 하고
     * cdp_cd도 등록 해줘야 하는 경우
     * user, evu_emp, evu_emp_cdp에 등록 해주기 한번에 등록 해주기
     * cdp_nm이 존재 하는지 확인 해주는 로직 추가 하기
     * **/
    @Override
    public int userRegistService(UserReqVo vo) {
        //1. user에 있는지 없는지 확인
         int userCount = commonMapper.getUserInfo(vo.getEmpId());
         if(userCount < 1){
             //user에 등록



         }

        //2. evu_emp에 있는지 없는지 확인
        //3. evu_cdp에서 cdp_cd가 있는지 없는지 확인
        //4. evu_emp_cdp에 있는지 없는지 확인










        return 0;
    }
}
