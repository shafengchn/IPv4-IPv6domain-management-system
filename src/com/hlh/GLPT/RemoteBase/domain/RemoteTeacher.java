package com.hlh.GLPT.RemoteBase.domain;

/**
 * Package: com.hlh.GLPT.RemoteBase.domain
 * User: 黄良辉  16-4-23  下午11:17
 */
public class RemoteTeacher {
    private int RowNumber; //行号
    private String Code;  //教工号
    private String FullName;     //姓名
    private String IdNumber;    //身份证号
    private String Mobile;     //手机号
    private String DepartmentCode;   //部门代码
    private String DepartmentName;   //部门名称

    public RemoteTeacher() {
    }

    public int getRowNumber() {
        return RowNumber;
    }

    public void setRowNumber(int rowNumber) {
        RowNumber = rowNumber;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getDepartmentCode() {
        return DepartmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        DepartmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }
}
