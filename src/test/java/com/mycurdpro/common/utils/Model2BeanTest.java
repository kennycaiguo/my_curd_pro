package com.mycurdpro.common.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.mycurdpro.system.User;
import com.mycurdpro.system.model.SysUser;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


public class Model2BeanTest {

    Model2Bean<SysUser, User> m2b = new Model2Bean<>();

    @Test
    public void modelToBean() {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("zhangchuang").setName("张闯").setPassword("123456").setCreateTime(new Date());

        Map<String, String> mapping = new HashMap<String, String>() {{
            put("USERNAME", "username");
            put("PASSWORD", "password");
            put("CREATE_TIME", "lastLoginTime");
        }};

        User user = m2b.toBean(sysUser, User.class, mapping);
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(new DateTime(user.getLastLoginTime()).toString("yyyy-MM-dd HH:mm:ss.S"));
        Assert.assertEquals("zhangchuang", user.getUsername());
    }

    @Test
    public void modelsToBeans() {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("zhangchuang").setName("张闯").setPassword("123456").setCreateTime(new Date());
        List<SysUser> sysUsers = new ArrayList<SysUser>() {{
            add(sysUser);
            add(sysUser);
            add(sysUser);
        }};
        Map<String, String> mapping = new HashMap<String, String>() {{
            put("USERNAME", "username");
            put("PASSWORD", "password");
            put("NAME", "name");
            put("CREATE_TIME", "lastLoginTime");
        }};

        List<User> users = m2b.toBeans(sysUsers, User.class, mapping);
        Assert.assertEquals(3,users.size());
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生", "学生"), User.class, users);
        try {
            workbook.write(new FileOutputStream(new File("D://test.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 一旦输出list 会被清空
        Assert.assertEquals(0,users.size());
    }
}