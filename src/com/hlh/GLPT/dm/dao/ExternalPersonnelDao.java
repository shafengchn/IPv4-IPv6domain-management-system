package com.hlh.GLPT.dm.dao;

import com.hlh.GLPT.dm.domain.ExternalPersonnel;
import com.hlh.common.domain.DataPage;
import com.hlh.common.service.DataPageService;
import com.hlh.common.service.DbToolsService;
import com.hlh.util.DbUtilsTemplate;
import com.hlh.util.StrUtil;
import com.hlh.util.VeDate;
import com.hlh.util.jsonUtil;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * 外聘人员资料
 * Package: com.hlh.GLPT.dm.dao
 * User: 黄良辉  15-6-3  下午10:27
 */
@Repository
public class ExternalPersonnelDao {
    @Autowired
    private DbToolsService dbToolsService;
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;
    @Autowired
    private DataPageService dataPageService;

    /**
     * 添加
     *
     * @param ePersonnel
     * @return
     */
    public String add(ExternalPersonnel ePersonnel) {
        String[] fField = new String[]{"Code", "FullName", "QQ", "EMail", "Phone"
                , "Mobile", "Address", "EmployDate", "Remarks"  , "Enabled"
                , "SortCode", "DeleteMark", "CallMark", "CreateDate"  , "CreateUserCode"
                , "CreateUserName"};
        Object[] fVal = new Object[]{ePersonnel.getCode(), ePersonnel.getFullName(), ePersonnel.getQQ(), ePersonnel.getEMail() , ePersonnel.getPhone()
                , ePersonnel.getMobile(), ePersonnel.getAddress(), ePersonnel.getEmployDate(), ePersonnel.getRemarks() , ePersonnel.isEnabled()
                , ePersonnel.getSortCode(), ePersonnel.isDeleteMark(), ePersonnel.isCallMark(), ePersonnel.getCreateDate(), ePersonnel.getCreateUserCode()
                , ePersonnel.getCreateUserName()};
        return dbToolsService.add("dm_ExternalPersonnel", fField, fVal);
    }

    /**
     * 彻底删除
     *
     * @param Id
     * @return
     */
    public String ThoroughDel(int Id) {
        return dbToolsService.ThoroughDel("dm_ExternalPersonnel", "Id", StrUtil.toStr(Id), "int");
    }

    /**
     * 修改
     *
     * @param ePersonnel
     * @return
     */
    public String edit(ExternalPersonnel ePersonnel) {
        String[] fField = new String[]{"Code", "FullName", "QQ", "EMail", "Phone"
                , "Mobile", "Address", "EmployDate", "Remarks"  , "Enabled"
                , "SortCode", "DeleteMark", "CallMark", "ModifyDate"  , "ModifyUserCode"
                , "ModifyUserName"};
        Object[] fVal = new Object[]{ePersonnel.getCode(), ePersonnel.getFullName(), ePersonnel.getQQ(), ePersonnel.getEMail() , ePersonnel.getPhone()
                , ePersonnel.getMobile(), ePersonnel.getAddress(), ePersonnel.getEmployDate(), ePersonnel.getRemarks() , ePersonnel.isEnabled()
                , ePersonnel.getSortCode(), ePersonnel.isDeleteMark(), ePersonnel.isCallMark(), ePersonnel.getModifyDate(), ePersonnel.getModifyUserCode()
                , ePersonnel.getModifyUserName()};
        return dbToolsService.edit("dm_ExternalPersonnel", fField, fVal, "Id", StrUtil.toStr(ePersonnel.getId()), "int");
    }

    /**
     * 标识删除
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetDeleteMark(int Id, boolean MarkVal) {
        return dbToolsService.edit("dm_ExternalPersonnel", new String[]{"DeleteMark"}, new Object[]{MarkVal}, "Id", StrUtil.toStr(Id), "int");
    }

    /**
     * 标识调用
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetCallMark(int Id, boolean MarkVal) {
        return dbToolsService.edit("dm_ExternalPersonnel", new String[]{"CallMark"}, new Object[]{MarkVal}, "Id", StrUtil.toStr(Id), "int");
    }

    /**
     * 按ID查询单条记录
     *
     * @param Id
     * @return
     */
    public ExternalPersonnel findById(int Id) {
        String sqlStr = "SELECT * FROM dm_ExternalPersonnel WHERE Id=?";
        return dbUtilsTemplate.findFirst(ExternalPersonnel.class, sqlStr, Id);
    }

    /**
     * 按Code查询单条记录
     *
     * @param Code
     * @return
     */
    public ExternalPersonnel findByCode(String Code) {
        String sqlStr = "SELECT * FROM dm_ExternalPersonnel WHERE Code=?";
        return dbUtilsTemplate.findFirst(ExternalPersonnel.class, sqlStr, Code);
    }

    /**
     * 查询记录 分页
     *
     * @param Enabled    有效性
     * @param DeleteMark 删除标识
     * @param CallMark   调用标识
     * @param fField     查询字段
     * @param fValue     查询值
     * @param start      开始记录
     * @param number     记录数
     * @return
     */
    public String findByPage(Boolean Enabled, Boolean DeleteMark, Boolean CallMark, String fField, String fValue, int start, int number) {
        DataPage dataPage1 = new DataPage();
        dataPage1.setTableName("dm_ExternalPersonnel");
        dataPage1.setColumns("Id,Code,FullName,QQ,EMail,Phone,Mobile,Address,EmployDate,Remarks,Enabled,SortCode,DeleteMark,CallMark");
        String[] strings1 = new String[]{"Id", "Code", "FullName", "QQ", "EMail", "Phone", "Mobile", "Address", "EmployDate", "Remarks", "Enabled", "SortCode", "DeleteMark", "CallMark"};
        dataPage1.setOrder_field("SortCode ASC");
        dataPage1.setGroup_field("");
        dataPage1.setCurrPage(start);
        dataPage1.setPageSize(number);
        StringBuilder sb1 = new StringBuilder();
        if (CallMark != null) {
            sb1.append("CallMark=").append(CallMark);
        }
        if (Enabled != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(" Enabled=").append(Enabled);
        }
        if (DeleteMark != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(" DeleteMark=").append(DeleteMark);
        }
        if ("".equals(fField)) { //模糊查询
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append("(Code LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FullName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR QQ LIKE '%").append(fValue).append("%'");
            sb1.append(" OR EMail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Phone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Mobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR EmployDate LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        dataPage1.setsCondition(sb1.toString());
        List tmpList1 = dataPageService.DataByPage(dataPage1);
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    /**
     * 查询记录数
     *
     * @param Enabled    有效性
     * @param DeleteMark 删除标识
     * @param CallMark   调用标识
     * @param fField     查询字段
     * @param fValue     查询值
     * @return
     */
    public int GetRowCount(Boolean Enabled, Boolean DeleteMark, Boolean CallMark, String fField, String fValue) {
        StringBuilder sb1 = new StringBuilder();
        if (CallMark != null) {
            sb1.append("CallMark=").append(CallMark);
        }
        if (Enabled != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(" Enabled=").append(Enabled);
        }
        if (DeleteMark != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(" DeleteMark=").append(DeleteMark);
        }
        if ("".equals(fField)) { //模糊查询
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append("(Code LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FullName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR QQ LIKE '%").append(fValue).append("%'");
            sb1.append(" OR EMail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Phone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Mobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR EmployDate LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        return dataPageService.GetRowCount("Id", "dm_ExternalPersonnel", sb1.toString());
    }

    /**
     * 验证添加
     *
     * @param ePersonnel
     * @return
     */
    public String VerifyAdd(ExternalPersonnel ePersonnel) {
        String msg = "";
        if (!StrUtil.trimNotEmpty(ePersonnel.getCode())) {
            msg = "外聘人员工号不能为空";
        } else if (!StrUtil.trimNotEmpty(ePersonnel.getFullName())) {
            msg = "外聘人员姓名不能为空";
        } else if (dbToolsService.IsRepeat("dm_ExternalPersonnel", "Code", "WHERE Code='" + ePersonnel.getCode() + "'")) {
            msg = "外聘人员工号已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }

    /**
     * 验证修改
     *
     * @param ePersonnel
     * @return
     */
    public String VerifyEdit(ExternalPersonnel ePersonnel) {
        String msg = "";
        if (!StrUtil.trimNotEmpty(ePersonnel.getCode())) {
            msg = "外聘人员工号不能为空";
        } else if (!StrUtil.trimNotEmpty(ePersonnel.getFullName())) {
            msg = "外聘人员姓名不能为空";
        } else if (dbToolsService.IsRepeat("dm_ExternalPersonnel", "Code", "WHERE Code='" + ePersonnel.getCode() + "' AND Id<>" + ePersonnel.getId())) {
            msg = "外聘人员工号已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }

    /**
     * Code转fullName
     *
     * @param code
     * @return
     */
    public String CodeToFullName(String code) {
        return dbToolsService.GetFieldValue("dm_ExternalPersonnel", "FullName", "Code='" + code + "'");
    }

    /**
     * fullName转Code
     *
     * @param fullName
     * @return
     */
    public String FullNameToCode(String fullName) {
        return dbToolsService.GetFieldValue("dm_ExternalPersonnel", "Code", "FullName='" + fullName + "'");
    }

    /**
     * 导出数据到Excel文件
     *
     * @param os
     * @param Enabled
     * @param DeleteMark
     * @param CallMark
     * @param fField
     * @param fValue
     * @throws Exception
     */
    public void ExportToExcel(OutputStream os, Boolean Enabled, Boolean DeleteMark, Boolean CallMark, String fField, String fValue) throws Exception {
        String titleName = "dm_ExternalPersonnel";
        String[] fFieldStr = new String[]{"Code", "FullName", "QQ", "EMail", "Phone", "Mobile", "Address", "EmployDate", "Enabled", "Remarks"};
        String[] fFieldFullName = new String[]{"工号", "姓名","QQ","EMail","电话号码","手机号码","联系地址","聘用日期", "是否有效", "备注"};
        int[] ColumnWidth = new int[]{20, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        StringBuilder sb1 = new StringBuilder();
        if (CallMark != null) {
            sb1.append("CallMark=").append(CallMark);
        }
        if (Enabled != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(" Enabled=").append(Enabled);
        }
        if (DeleteMark != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(" DeleteMark=").append(DeleteMark);
        }
        if ("".equals(fField)) { //模糊查询
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append("(Code LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FullName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR QQ LIKE '%").append(fValue).append("%'");
            sb1.append(" OR EMail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Phone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Mobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR EmployDate LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        String orderByStr = "SortCode ASC";
        String ExcelHeaderName = "外聘人员资料";
        dbToolsService.ExportToExcel(os, titleName, fFieldStr, fFieldFullName, ColumnWidth, sb1.toString(), orderByStr, ExcelHeaderName);
    }

    //导入 fileName 源文件
    public String ExternalPersonnelImport(String fileName, Date CreateDate, String CreateUserCode, String CreateUserName) {
        int RowCount = 0;
        int YesCount = 0;
        int ErrCount = 0;
        try {
            Workbook workbook = Workbook.getWorkbook(new File(fileName));
            Sheet sheet = workbook.getSheet(0);
            for (int i1 = 1; i1 < sheet.getRows(); i1++) {
                RowCount++;
                Cell cell1 = sheet.getCell(1, i1); //工号
                Cell cell2 = sheet.getCell(2, i1); //姓名
                Cell cell3 = sheet.getCell(3, i1); //QQ
                Cell cell4 = sheet.getCell(4, i1); //EMail
                Cell cell5 = sheet.getCell(5, i1); //电话号码
                Cell cell6 = sheet.getCell(6, i1); //手机号码
                Cell cell7 = sheet.getCell(7, i1); //联系地址
                Cell cell8 = sheet.getCell(8, i1); //聘用日期
                Cell cell9 = sheet.getCell(9, i1); //备注
                if (!"".equals(cell1.getContents())&&!"".equals(cell2.getContents())) {
                    ExternalPersonnel ePersonnel1=new ExternalPersonnel();
                    ePersonnel1.setCode(StrUtil.toStr(cell1.getContents()));
                    ePersonnel1.setFullName(StrUtil.toStr(cell2.getContents()));
                    ePersonnel1.setQQ(StrUtil.toStr(cell3.getContents()));
                    ePersonnel1.setEMail(StrUtil.toStr(cell4.getContents()));
                    ePersonnel1.setPhone(StrUtil.toStr(cell5.getContents()));
                    ePersonnel1.setMobile(StrUtil.toStr(cell6.getContents()));
                    ePersonnel1.setAddress(StrUtil.toStr(cell7.getContents()));
                    String EmployDate1=StrUtil.toStr(cell8.getContents());
                    if(VeDate.isDate(EmployDate1)){
                    ePersonnel1.setEmployDate(EmployDate1);
                    }
                    ePersonnel1.setRemarks(StrUtil.toStr(cell9.getContents()));
                    ePersonnel1.setSortCode(GetRowCount(null,null,null,"","")+1);
                    ePersonnel1.setCallMark(true);
                    ePersonnel1.setEnabled(true);
                    ePersonnel1.setDeleteMark(false);
                    ePersonnel1.setCreateDate(CreateDate);
                    ePersonnel1.setCreateUserCode(CreateUserCode);
                    ePersonnel1.setCreateUserName(CreateUserName);
                    String msg = VerifyAdd(ePersonnel1);
                    if ("OK".equals(msg)) {
                        String RValue = add(ePersonnel1);
                        if ("OK".equals(RValue)) {
                            YesCount++;
                        } else {
                            ErrCount++;
                        }
                    } else {
                        ErrCount++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "导入失败,源文件不正确";
        }
        if (YesCount == RowCount) {
            return "OK";
        } else {
            return "导入结束,共导入" + RowCount + "条,有" + YesCount + "条导入成功,有" + ErrCount + "条导入失败.";
        }

    }
}
