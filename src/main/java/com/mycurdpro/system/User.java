package com.mycurdpro.system;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.mycurdpro.system.model.SysUser;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试将 Model 导出
 */
public class User implements Serializable {


    private static final long serialVersionUID = 1775203812288883720L;
    @Excel(name = "用户名", height = 20, width = 30, isImportField = "true_st")
    private String username;
    @Excel(name = "密码", height = 20, width = 30, isImportField = "true_st")
    private String password;

    @Excel(name = "姓名", height = 20, width = 30, isImportField = "true_st")
    private String name;

    @Excel(name = "职位级别", height = 20, width = 30, isImportField = "true_st")
    private String jobLevel;
    private String joblevel;

    @Excel(name = "最后登录时间",  format = "yyyy-MM-dd")
    private Date lastLoginTime;

    @Excel(name = "创建时间", format = "yyyy-MM-dd")
    private Double createTime;

    private String  xxx;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public String getJoblevel() {
        return joblevel;
    }

    public void setJoblevel(String joblevel) {
        this.joblevel = joblevel;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Double getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Double createTime) {
        this.createTime = createTime;
    }

    public String getXxx() {
        return xxx;
    }

    public void setXxx(String xxx) {
        this.xxx = xxx;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", jobLevel='" + jobLevel + '\'' +
                ", joblevel='" + joblevel + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", createTime=" + createTime +
                ", xxx='" + xxx + '\'' +
                '}';
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("zhangchuang").setPassword("123456");
        sysUser.setName("张国最帅").setJobLevel("manager").setLastLoginTime(new Date()).setCreateTime(new Date());
        sysUser.set("xxx","yyy");
        User user = new User();
        BeanUtils.copyProperties(user,sysUser);

        System.out.println(user);


        List<User> list = new ArrayList<User>(){{
            add(user);
            add(user);
            add(user);
        }};
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生","学生"), User.class, list);
        try {
            workbook.write(new FileOutputStream(new File("D://test.xlsx")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
