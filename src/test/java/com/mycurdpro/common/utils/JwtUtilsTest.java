package com.mycurdpro.common.utils;

import base.DbTestBase;
import com.alibaba.fastjson.JSON;
import com.jfinal.kit.Ret;
import com.mycurdpro.system.model.SysUser;
import org.joda.time.DateTime;
import org.junit.Test;

public class JwtUtilsTest {

    @Test
    public void createToken() {
        DbTestBase.dbInit();
        String username = "admin";
        SysUser sysUser = SysUser.dao.findByUsername(username);
        String token = JwtUtils.createToken(sysUser.getId(),sysUser.getUsername(),sysUser.getName(),1000*30L);
        System.out.println(token);
    }

    @Test
    public void parseJWT() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJFU0JQIiwibmFtZSI6IuW8oOmXryIsImV4cCI6MTU0NzQ0Mzg1MCwiaWF0IjoxNTQ3NDQzODIwLCJqdGkiOiI1MjI0NjUxNDQ1MDUxMDY0MzIiLCJ1c2VybmFtZSI6ImFkbWluIn0.uZ8z5TIV3cDy074qrLFoFtVtjfWLcADbCtzoOc_qSq0";
        Ret ret  = JwtUtils.parseJWT(token);
        System.out.println(JSON.toJSONString(ret));
        if (ret.isOk()){
            UserClaim userClaim = (UserClaim) ret.get("userClaim");
            System.out.println(new DateTime(userClaim.getIat()).toString("yyyy-MM-dd HH:mm:ss"));
            System.out.println(new DateTime(userClaim.getExp()).toString("yyyy-MM-dd HH:mm:ss"));
        }
    }
}