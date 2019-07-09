package com.hlh.GLPT.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * User: 黄良辉
 * Date: 14-8-31
 * Time: 下午8:23
 */
public class OperateLog implements Serializable {
    private int ID;
    private Date OperateDate;
    private String OperateIP;
    private String OperateUserName;
    private String OperateContent;

    public OperateLog() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getOperateDate() {
        return OperateDate;
    }

    public void setOperateDate(Date operateDate) {
        OperateDate = operateDate;
    }

    public String getOperateIP() {
        return OperateIP;
    }

    public void setOperateIP(String operateIP) {
        OperateIP = operateIP;
    }

    public String getOperateUserName() {
        return OperateUserName;
    }

    public void setOperateUserName(String operateUserName) {
        OperateUserName = operateUserName;
    }

    public String getOperateContent() {
        return OperateContent;
    }

    public void setOperateContent(String operateContent) {
        OperateContent = operateContent;
    }

    @Override
    public String toString() {
        return "OperateLog{" +
                "ID=" + ID +
                ", OperateDate=" + OperateDate +
                ", OperateIP='" + OperateIP + '\'' +
                ", OperateUserName='" + OperateUserName + '\'' +
                ", OperateContent='" + OperateContent + '\'' +
                '}';
    }
}
