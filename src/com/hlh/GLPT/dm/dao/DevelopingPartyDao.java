package com.hlh.GLPT.dm.dao;

import com.hlh.GLPT.dm.domain.DevelopingParty;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * Package: com.hlh.GLPT.dm.dao
 * User: 黄良辉  15-6-3  下午10:27
 */
@Repository
public class DevelopingPartyDao {
    @Autowired
    private DbToolsService dbToolsService;
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;
    @Autowired
    private DataPageService dataPageService;

    /**
     * 添加
     *
     * @param dParty
     * @return
     */
    public String add(DevelopingParty dParty) {
        String[] fField = new String[]{"Code", "FullName", "ContactPerson", "QQ", "EMail"
                , "Phone", "Mobile", "Address", "CooperationDate", "Remarks"
                , "Enabled", "SortCode", "DeleteMark", "CallMark", "CreateDate"
                , "CreateUserCode", "CreateUserName"};
        Date CooperationDate1 = null;
        if (!"".equals(dParty.getCooperationDate()) && dParty.getCooperationDate() != null) {
            CooperationDate1 = VeDate.strToDate(dParty.getCooperationDate());
        }
        Object[] fVal = new Object[]{dParty.getCode(), dParty.getFullName(), dParty.getContactPerson(), dParty.getQQ(), dParty.getEMail()
                , dParty.getPhone(), dParty.getMobile(), dParty.getAddress(), CooperationDate1, dParty.getRemarks()
                , dParty.isEnabled(), dParty.getSortCode(), dParty.isDeleteMark(), dParty.isCallMark(), dParty.getCreateDate()
                , dParty.getCreateUserCode(), dParty.getCreateUserName()};
        return dbToolsService.add("dm_DevelopingParty", fField, fVal);
    }

    /**
     * 彻底删除
     *
     * @param Id
     * @return
     */
    public String ThoroughDel(int Id) {
        return dbToolsService.ThoroughDel("dm_DevelopingParty", "Id", StrUtil.toStr(Id), "int");
    }

    /**
     * 修改
     *
     * @param dParty
     * @return
     */
    public String edit(DevelopingParty dParty) {
        String[] fField = new String[]{"Code", "FullName", "ContactPerson", "QQ", "EMail"
                , "Phone", "Mobile", "Address", "CooperationDate", "Remarks"
                , "Enabled", "SortCode", "DeleteMark", "CallMark", "ModifyDate"
                , "ModifyUserCode", "ModifyUserName"};
        Date CooperationDate1 = null;
        if (!"".equals(dParty.getCooperationDate()) && dParty.getCooperationDate() != null) {
            CooperationDate1 = VeDate.strToDate(dParty.getCooperationDate());
        }
        Object[] fVal = new Object[]{dParty.getCode(), dParty.getFullName(), dParty.getContactPerson(), dParty.getQQ(), dParty.getEMail()
                , dParty.getPhone(), dParty.getMobile(), dParty.getAddress(), CooperationDate1, dParty.getRemarks()
                , dParty.isEnabled(), dParty.getSortCode(), dParty.isDeleteMark(), dParty.isCallMark(), dParty.getModifyDate()
                , dParty.getModifyUserCode(), dParty.getModifyUserName()};
        return dbToolsService.edit("dm_DevelopingParty", fField, fVal, "Id", StrUtil.toStr(dParty.getId()), "int");
    }

    /**
     * 标识删除
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetDeleteMark(int Id, boolean MarkVal) {
        return dbToolsService.edit("dm_DevelopingParty", new String[]{"DeleteMark"}, new Object[]{MarkVal}, "Id", StrUtil.toStr(Id), "int");
    }

    /**
     * 标识调用
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetCallMark(int Id, boolean MarkVal) {
        return dbToolsService.edit("dm_DevelopingParty", new String[]{"CallMark"}, new Object[]{MarkVal}, "Id", StrUtil.toStr(Id), "int");
    }

    /**
     * 按ID查询单条记录
     *
     * @param Id
     * @return
     */
    public DevelopingParty findById(int Id) {
        String sqlStr = "SELECT * FROM dm_DevelopingParty WHERE Id=?";
        return dbUtilsTemplate.findFirst(DevelopingParty.class, sqlStr, Id);
    }

    /**
     * 按Code查询单条记录
     *
     * @param Code
     * @return
     */
    public DevelopingParty findByCode(String Code) {
        String sqlStr = "SELECT * FROM dm_DevelopingParty WHERE Code=?";
        return dbUtilsTemplate.findFirst(DevelopingParty.class, sqlStr, Code);
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
        dataPage1.setTableName("dm_DevelopingParty");
        dataPage1.setColumns("Id,Code,FullName,ContactPerson,QQ,EMail,Phone,Mobile,Address,CooperationDate,Remarks,Enabled,SortCode,DeleteMark,CallMark");
        String[] strings1 = new String[]{"Id", "Code", "FullName", "ContactPerson", "QQ", "EMail", "Phone", "Mobile", "Address", "CooperationDate", "Remarks", "Enabled", "SortCode", "DeleteMark", "CallMark"};
        dataPage1.setOrder_field("SortCode DESC");
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
            sb1.append(" OR ContactPerson LIKE '%").append(fValue).append("%'");
            sb1.append(" OR QQ LIKE '%").append(fValue).append("%'");
            sb1.append(" OR EMail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Phone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Mobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR CooperationDate LIKE '%").append(fValue).append("%'");
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
            sb1.append(" OR ContactPerson LIKE '%").append(fValue).append("%'");
            sb1.append(" OR QQ LIKE '%").append(fValue).append("%'");
            sb1.append(" OR EMail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Phone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Mobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR CooperationDate LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        return dataPageService.GetRowCount("Id", "dm_DevelopingParty", sb1.toString());
    }

    /**
     * 查询记录 分页
     *
     * @param UserCode   用户代码
     * @param Enabled    有效性
     * @param DeleteMark 删除标识
     * @param CallMark   调用标识
     * @param fField     查询字段
     * @param fValue     查询值
     * @param start      开始记录
     * @param number     记录数
     * @return
     */
    public String findCurrUserByPage(String UserCode, Boolean Enabled, Boolean DeleteMark, Boolean CallMark, String fField, String fValue, int start, int number) {
        DataPage dataPage1 = new DataPage();
        dataPage1.setTableName("dm_DevelopingParty");
        dataPage1.setColumns("Id,Code,FullName,ContactPerson,QQ,EMail,Phone,Mobile,Address,CooperationDate,Remarks,Enabled,SortCode,DeleteMark,CallMark");
        String[] strings1 = new String[]{"Id", "Code", "FullName", "ContactPerson", "QQ", "EMail", "Phone", "Mobile", "Address", "CooperationDate", "Remarks", "Enabled", "SortCode", "DeleteMark", "CallMark"};
        dataPage1.setOrder_field("SortCode DESC");
        dataPage1.setGroup_field("");
        dataPage1.setCurrPage(start);
        dataPage1.setPageSize(number);
        StringBuilder sb1 = new StringBuilder();
        if (UserCode != null) {
            sb1.append("CreateUserCode='").append(UserCode).append("'");
        }
        if (CallMark != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(" CallMark=").append(CallMark);
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
            sb1.append(" OR ContactPerson LIKE '%").append(fValue).append("%'");
            sb1.append(" OR QQ LIKE '%").append(fValue).append("%'");
            sb1.append(" OR EMail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Phone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Mobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR CooperationDate LIKE '%").append(fValue).append("%'");
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
     * @param UserCode   用户代码
     * @param Enabled    有效性
     * @param DeleteMark 删除标识
     * @param CallMark   调用标识
     * @param fField     查询字段
     * @param fValue     查询值
     * @return
     */
    public int GetCurrUserRowCount(String UserCode, Boolean Enabled, Boolean DeleteMark, Boolean CallMark, String fField, String fValue) {
        StringBuilder sb1 = new StringBuilder();
        if (UserCode != null) {
            sb1.append("CreateUserCode='").append(UserCode).append("'");
        }
        if (CallMark != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(" CallMark=").append(CallMark);
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
            sb1.append(" OR ContactPerson LIKE '%").append(fValue).append("%'");
            sb1.append(" OR QQ LIKE '%").append(fValue).append("%'");
            sb1.append(" OR EMail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Phone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Mobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR CooperationDate LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        return dataPageService.GetRowCount("Id", "dm_DevelopingParty", sb1.toString());
    }

    /**
     * 验证添加
     *
     * @param dParty
     * @return
     */
    public String VerifyAdd(DevelopingParty dParty) {
        String msg = "";
        if (!StrUtil.trimNotEmpty(dParty.getCode())) {
            msg = "开发方代码不能为空";
        } else if (!StrUtil.trimNotEmpty(dParty.getFullName())) {
            msg = "开发方名称不能为空";
        } else if (dbToolsService.IsRepeat("dm_DevelopingParty", "Code", "WHERE Code='" + dParty.getCode() + "'")) {
            msg = "开发方代码已经存在";
        } else if (dbToolsService.IsRepeat("dm_DevelopingParty", "FullName", "WHERE FullName='" + dParty.getFullName() + "'")) {
            msg = "开发方名称已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }

    /**
     * 验证修改
     *
     * @param dParty
     * @return
     */
    public String VerifyEdit(DevelopingParty dParty) {
        String msg = "";
        if (!StrUtil.trimNotEmpty(dParty.getCode())) {
            msg = "开发方代码不能为空";
        } else if (!StrUtil.trimNotEmpty(dParty.getFullName())) {
            msg = "开发方名称不能为空";
        } else if (dbToolsService.IsRepeat("dm_DevelopingParty", "Code", "WHERE Code='" + dParty.getCode() + "' AND Id<>" + dParty.getId())) {
            msg = "开发方代码已经存在";
        } else if (dbToolsService.IsRepeat("dm_DevelopingParty", "FullName", "WHERE FullName='" + dParty.getFullName() + "' AND Id<>" + dParty.getId())) {
            msg = "开发方名称已经存在";
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
        return dbToolsService.GetFieldValue("dm_DevelopingParty", "FullName", "Code='" + code + "'");
    }

    /**
     * fullName转Code
     *
     * @param fullName
     * @return
     */
    public String FullNameToCode(String fullName) {
        return dbToolsService.GetFieldValue("dm_DevelopingParty", "Code", "FullName='" + fullName + "'");
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
        String titleName = "dm_DevelopingParty";
        String[] fFieldStr = new String[]{"FullName", "ContactPerson", "QQ", "EMail", "Phone", "Mobile", "Address", "CooperationDate", "Enabled", "Remarks"};
        String[] fFieldFullName = new String[]{"开发方", "联系人", "QQ", "EMail", "电话号码", "手机号码", "联系地址", "合作日期", "是否有效", "备注"};
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
            sb1.append(" OR ContactPerson LIKE '%").append(fValue).append("%'");
            sb1.append(" OR QQ LIKE '%").append(fValue).append("%'");
            sb1.append(" OR EMail LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Phone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Mobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Address LIKE '%").append(fValue).append("%'");
            sb1.append(" OR CooperationDate LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        String orderByStr = "SortCode ASC";
        String ExcelHeaderName = "开发方资料";
        dbToolsService.ExportToExcel(os, titleName, fFieldStr, fFieldFullName, ColumnWidth, sb1.toString(), orderByStr, ExcelHeaderName);
    }

    //导入 fileName 源文件
    public String DevelopingPartyImport(String fileName, Date CreateDate, String CreateUserCode, String CreateUserName) {
        int RowCount = 0;
        int YesCount = 0;
        int ErrCount = 0;
        try {
            Workbook workbook = Workbook.getWorkbook(new File(fileName));
            Sheet sheet = workbook.getSheet(0);
            for (int i1 = 2; i1 < sheet.getRows(); i1++) {
                RowCount++;
                // Cell cell1 = sheet.getCell(1, i1); //开发方代码
                Cell cell2 = sheet.getCell(1, i1); //开发方名称
                Cell cell3 = sheet.getCell(2, i1); //联系人
                Cell cell4 = sheet.getCell(3, i1); //QQ
                Cell cell5 = sheet.getCell(4, i1); //EMail
                Cell cell6 = sheet.getCell(5, i1); //电话号码
                Cell cell7 = sheet.getCell(6, i1); //手机号码
                Cell cell8 = sheet.getCell(7, i1); //联系地址
                Cell cell9 = sheet.getCell(8, i1); //合作日期
                Cell cell10 = sheet.getCell(9, i1); //备注
                if (!"".equals(cell2.getContents())) {
                    DevelopingParty developingParty1 = new DevelopingParty();
                    //developingParty1.setCode(StrUtil.toStr(cell1.getContents()));
                    developingParty1.setCode("KFF" + StrUtil.GetUUID());
                    developingParty1.setFullName(StrUtil.toStr(cell2.getContents()));
                    developingParty1.setContactPerson(StrUtil.toStr(cell3.getContents()));
                    developingParty1.setQQ(StrUtil.toStr(cell4.getContents()));
                    developingParty1.setEMail(StrUtil.toStr(cell5.getContents()));
                    developingParty1.setPhone(StrUtil.toStr(cell6.getContents()));
                    developingParty1.setMobile(StrUtil.toStr(cell7.getContents()));
                    developingParty1.setAddress(StrUtil.toStr(cell8.getContents()));
                    String CooperationDate1 = StrUtil.toStr(cell9.getContents());
                    if (VeDate.isDate(CooperationDate1)) {
                        developingParty1.setCooperationDate(CooperationDate1);
                    }
                    developingParty1.setRemarks(StrUtil.toStr(cell10.getContents()));
                    developingParty1.setSortCode(GetRowCount(null, null, null, "", "") + 1);
                    developingParty1.setCallMark(true);
                    developingParty1.setEnabled(true);
                    developingParty1.setDeleteMark(false);
                    developingParty1.setCreateDate(CreateDate);
                    developingParty1.setCreateUserCode(CreateUserCode);
                    developingParty1.setCreateUserName(CreateUserName);
                    String msg = VerifyAdd(developingParty1);
                    if ("OK".equals(msg)) {
                        String RValue = add(developingParty1);
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
