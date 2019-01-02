package com.mycurdpro.system.vo;

/**
 * 定时任务 信息 vo
 */
public class Cron4jTaskIntro {

    private String name;
    private String cron;
    private String className;
    private String daemon;
    private String enable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDaemon() {
        return daemon;
    }

    public void setDaemon(String daemon) {
        this.daemon = daemon;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}
