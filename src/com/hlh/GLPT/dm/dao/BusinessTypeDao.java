package com.hlh.GLPT.dm.dao;

import com.hlh.GLPT.dm.domain.BusinessType;
import com.hlh.common.domain.DataPage;
import com.hlh.common.service.DataPageService;
import com.hlh.common.service.DbToolsService;
import com.hlh.util.DbUtilsTemplate;
import com.hlh.util.StrUtil;
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
 * 业务类型
 * Package: com.hlh.GLPT.dm.dao
 * User: 黄良辉  15-6-3  下午10:27
 */
@Repository
public class BusinessTypeDao {
    @Autowired
    private DbToolsService dbToolsService;
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;
    @Autowired
    private DataPageService dataPageService;

    /**
     * 添加
     *
     * @param bType
     * @return
     */
    public String add(BusinessType bType) {
        String[] fField = new String[]{"Code", "FullName", "Remarks", "Enabled", "SortCode"
                , "DeleteMark", "CallMark", "CreateDate", "CreateUserCode", "CreateUserName"};
        Object[] fVal = new Object[]{bType.getCode(), bType.getFullName(), bType.getRemarks(), bType.isEnabled(), bType.getSortCode()
                , bType.isDeleteMark(), bType.isCallMark(), bType.getCreateDate(), bType.getCreateUserCode(), bType.getCreateUserName()};
        return dbToolsService.add("dm_BusinessType", fField, fVal);
    }

    /**
     * 彻底删除
     *
     * @param Id
     * @return
     */
    public String ThoroughDel(int Id) {
        return dbToolsService.ThoroughDel("dm_BusinessType", "Id", StrUtil.toStr(Id), "int");
    }

    /**
     * 修改
     *
     * @param bType
     * @return
     */
    public String edit(BusinessType bType) {
        String[] fField = new String[]{"Code", "FullName", "Remarks", "Enabled", "SortCode"
                , "ModifyDate", "ModifyUserCode", "ModifyUserName"};
        Object[] fVal = new Object[]{bType.getCode(), bType.getFullName(), bType.getRemarks(), bType.isEnabled(), bType.getSortCode()
                , bType.getModifyDate(), bType.getModifyUserCode(), bType.getModifyUserName()};
        return dbToolsService.edit("dm_BusinessType", fField, fVal, "Id", StrUtil.toStr(bType.getId()), "int");
    }

    /**
     * 标识删除
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetDeleteMark(int Id, boolean MarkVal) {
        return dbToolsService.edit("dm_BusinessType", new String[]{"DeleteMark"}, new Object[]{MarkVal}, "Id", StrUtil.toStr(Id), "int");
    }

    /**
     * 标识调用
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetCallMark(int Id, boolean MarkVal) {
        return dbToolsService.edit("dm_BusinessType", new String[]{"CallMark"}, new Object[]{MarkVal}, "Id", StrUtil.toStr(Id), "int");
    }

    /**
     * 按ID查询单条记录
     *
     * @param Id
     * @return
     */
    public BusinessType findById(int Id) {
        String sqlStr = "SELECT * FROM dm_BusinessType WHERE Id=?";
        return dbUtilsTemplate.findFirst(BusinessType.class, sqlStr, Id);
    }

    /**
     * 按代码查询单条记录
     *
     * @param Code
     * @return
     */
    public BusinessType findByCode(String Code) {
        String sqlStr = "SELECT * FROM dm_BusinessType WHERE Code=?";
        return dbUtilsTemplate.findFirst(BusinessType.class, sqlStr, Code);
    }

    /**
     * 查询有效的业务类型信息
     * @param ShowFieldName
     * @param strings
     * @return
     */
    public String findAllEx(String ShowFieldName, String[] strings) {
        String sqlStr = "SELECT " + ShowFieldName + " FROM dm_BusinessType WHERE Enabled=true AND DeleteMark=false ORDER BY SortCode";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sqlStr);
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings);
        return tmpStr1;
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
        dataPage1.setTableName("dm_BusinessType");
        dataPage1.setColumns("Id,Code,FullName,Remarks,Enabled,SortCode,DeleteMark,CallMark");
        String[] strings1 = new String[]{"Id", "Code", "FullName", "Remarks", "Enabled", "SortCode", "DeleteMark", "CallMark"};
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
            sb1.append(" OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        return dataPageService.GetRowCount("Id", "dm_BusinessType", sb1.toString());
    }

    /**
     * 验证添加
     *
     * @param bType
     * @return
     */
    public String VerifyAdd(BusinessType bType) {
        String msg = "";
        if (!StrUtil.trimNotEmpty(bType.getCode())) {
            msg = "业务类型代码不能为空";
        }else if(!StrUtil.trimNotEmpty(bType.getFullName())){
            msg="业务类型名称不能为空";
        } else if (dbToolsService.IsRepeat("dm_BusinessType", "Code", "WHERE Code='" + bType.getCode() + "'")) {
            msg = "业务类型代码已经存在";
        } else if (dbToolsService.IsRepeat("dm_BusinessType", "FullName", "WHERE FullName='" + bType.getFullName() + "'")) {
            msg = "业务类型名称已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }

    /**
     * 验证修改
     *
     * @param bType
     * @return
     */
    public String VerifyEdit(BusinessType bType) {
        String msg = "";
        if (!StrUtil.trimNotEmpty(bType.getCode())) {
            msg = "业务类型代码不能为空";
        }else if(!StrUtil.trimNotEmpty(bType.getFullName())){
            msg="业务类型名称不能为空";
        } else if (dbToolsService.IsRepeat("dm_BusinessType", "Code", "WHERE Code='" + bType.getCode() + "' AND Id<>" + bType.getId())) {
            msg = "业务类型代码已经存在";
        } else if (dbToolsService.IsRepeat("dm_BusinessType", "FullName", "WHERE FullName='" + bType.getFullName() + "' AND Id<>" + bType.getId())) {
            msg = "业务类型名称已经存在";
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
        return dbToolsService.GetFieldValue("dm_BusinessType", "FullName", "Code='" + code + "'");
    }

    /**
     * fullName转Code
     *
     * @param fullName
     * @return
     */
    public String FullNameToCode(String fullName) {
        return dbToolsService.GetFieldValue("dm_BusinessType", "Code", "FullName='" + fullName + "'");
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
        String titleName = "dm_BusinessType";
        String[] fFieldStr = new String[]{"Code", "FullName", "Enabled", "Remarks"};
        String[] fFieldFullName = new String[]{"业务类型代码", "业务类型名称", "是否有效", "备注"};
        int[] ColumnWidth = new int[]{20, 10, 10, 20};
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
            sb1.append(" OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        String orderByStr = "SortCode ASC";
        String ExcelHeaderName = "业务类型";
        dbToolsService.ExportToExcel(os, titleName, fFieldStr, fFieldFullName, ColumnWidth, sb1.toString(), orderByStr, ExcelHeaderName);
    }


    //导入 fileName 源文件
    public String BusinessTypeImport(String fileName, Date CreateDate, String CreateUserCode, String CreateUserName) {
        int RowCount = 0;
        int YesCount = 0;
        int ErrCount = 0;
        try {
            Workbook workbook = Workbook.getWorkbook(new File(fileName));
            Sheet sheet = workbook.getSheet(0);
            for (int i1 = 1; i1 < sheet.getRows(); i1++) {
                RowCount++;
                Cell cell1 = sheet.getCell(1, i1); //业务类型代码
                Cell cell2 = sheet.getCell(2, i1); //业务类型名称
                Cell cell3 = sheet.getCell(3, i1); //备注
                if (!"".equals(cell1.getContents())&&!"".equals(cell2.getContents())) {
                    BusinessType businessType1=new BusinessType();
                    businessType1.setCode(StrUtil.toStr(cell1.getContents()));
                    businessType1.setFullName(StrUtil.toStr(cell2.getContents()));
                    businessType1.setRemarks(StrUtil.toStr(cell3.getContents()));
                    businessType1.setSortCode(GetRowCount(null,null,null,"","")+1);
                    businessType1.setCallMark(true);
                    businessType1.setEnabled(true);
                    businessType1.setDeleteMark(false);
                    businessType1.setCreateDate(CreateDate);
                    businessType1.setCreateUserCode(CreateUserCode);
                    businessType1.setCreateUserName(CreateUserName);
                    String msg = VerifyAdd(businessType1);
                    if ("OK".equals(msg)) {
                        String RValue = add(businessType1);
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
