package com.example.skgasutils.UserUtils.Service;

import com.example.skgasutils.UserUtils.UserUtilVo.UserReqVo;

public interface UserUtilService {

    /**
     * 인의적으로 인사 db에 등록을 해야 할때
     * */
    public int userRegistService(UserReqVo vo);


    /**
     *  인사 DB에만 저장 해야 할때
     * */
    public int onlyUserRegist(UserReqVo vo);


}
