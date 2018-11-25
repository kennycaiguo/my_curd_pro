package com.mycurdpro.system;

import com.mycurdpro.system.model.SysUser;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class User {
    private String username;
    private String password;
    private String name;
    private String jobLevel;
    private String joblevel;
    private Date lastLoginTime;
    private Double createTime;

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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", jobLevel='" + jobLevel + '\'' +
                ", joblevel='" + joblevel + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("zhangchuang").setPassword("123456");
        sysUser.setName("zhangdapao").setJobLevel("manager").setLastLoginTime(new Date()).setCreateTime(new Date());

        User user = new User();
        BeanUtils.copyProperties(user,sysUser);

        System.out.println(user);
    }
}
