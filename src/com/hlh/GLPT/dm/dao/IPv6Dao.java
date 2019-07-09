package com.hlh.GLPT.dm.dao;

import com.hlh.GLPT.dm.domain.IPv6;
import com.hlh.common.domain.DataPage;
import com.hlh.common.service.DataPageService;
import com.hlh.common.service.DbToolsService;
import com.hlh.util.DbUtilsTemplate;
import com.hlh.util.IPAddressUtil;
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
 * Package: com.hlh.GLPT.dm.dao
 * User: 黄良辉  15-6-3  下午10:27
 */
@Repository
public class IPv6Dao {
    @Autowired
    private DbToolsService dbToolsService;
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;
    @Autowired
    private DataPageService dataPageService;

    /**
     * 添加
     *
     * @param iPv6
     * @return
     */
    public String add(IPv6 iPv6) {
        String[] fField = new String[]{"Address", "State", "Remarks", "Enabled", "SortCode"
                , "DeleteMark", "CallMark", "CreateDate", "CreateUserCode", "CreateUserName"};
        Object[] fVal = new Object[]{iPv6.getAddress(), iPv6.isState(), iPv6.getRemarks(), iPv6.isEnabled(), iPv6.getSortCode()
                , iPv6.isDeleteMark(), iPv6.isCallMark(), iPv6.getCreateDate(), iPv6.getCreateUserCode(), iPv6.getCreateUserName()};
        return dbToolsService.add("dm_IPv6", fField, fVal);
    }


    /**
     * 批量添加
     * @param addressRang      地址集 用"|"分隔
     * @param CreateDate
     * @param CreateUserCode
     * @param CreateUserName
     * @return
     */
    public int BatchAdd(String addressRang,Date CreateDate, String CreateUserCode, String CreateUserName){
        String[] tmpIPv6Address1 = addressRang.split("\\|");
        int jsAddCount1=0;
        for (int i1 = 0; i1 < tmpIPv6Address1.length; i1++) {
            if (IPAddressUtil.isIPv6Address(tmpIPv6Address1[i1])) {
                IPv6 tmp_ip2 = findByAddress(tmpIPv6Address1[i1]);
                if (tmp_ip2 != null) { //存在
                    SetCallMark(tmp_ip2.getId(), true);
                    SetState(tmp_ip2.getId(), true);
                } else { //不存在
                    IPv6 tmp_iPv6 = new IPv6();
                    tmp_iPv6.setAddress(tmpIPv6Address1[i1]);
                    tmp_iPv6.setSortCode(GetRowCount(null, null, null, null, "", "") + 1);
                    tmp_iPv6.setState(true);
                    tmp_iPv6.setCallMark(true);
                    tmp_iPv6.setEnabled(true);
                    tmp_iPv6.setDeleteMark(false);
                    tmp_iPv6.setCreateDate(CreateDate);
                    tmp_iPv6.setCreateUserCode(CreateUserCode);
                    tmp_iPv6.setCreateUserName(CreateUserName);
                    if("OK".equals(add(tmp_iPv6))){
                        jsAddCount1++;
                    }
                }
            }
        }
        return jsAddCount1;
    }
    /**
     * 彻底删除
     *
     * @param Id
     * @return
     */
    public String ThoroughDel(int Id) {
        return dbToolsService.ThoroughDel("dm_IPv6", "Id", StrUtil.toStr(Id), "int");
    }

    /**
     * 修改
     *
     * @param iPv6
     * @return
     */
    public String edit(IPv6 iPv6) {
        String[] fField = new String[]{"Address", "State", "Remarks", "Enabled", "SortCode"
                , "ModifyDate", "ModifyUserCode", "ModifyUserName"};
        Object[] fVal = new Object[]{iPv6.getAddress(), iPv6.isState(), iPv6.getRemarks(), iPv6.isEnabled(), iPv6.getSortCode()
                , iPv6.getModifyDate(), iPv6.getModifyUserCode(), iPv6.getModifyUserName()};
        return dbToolsService.edit("dm_IPv6", fField, fVal, "Id", StrUtil.toStr(iPv6.getId()), "int");
    }

    /**
     * 标识状态
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetState(int Id, boolean MarkVal) {
        return dbToolsService.edit("dm_IPv6", new String[]{"State"}, new Object[]{MarkVal}, "Id", StrUtil.toStr(Id), "int");
    }

    /**
     * 批量设置标识状态
     *
     * @param IPAddressRang       IP地址范围
     * @param MarkVal 标识值
     * @return
     */
    public String BatchSetState(String IPAddressRang, boolean MarkVal) {
        String[] tmpIPv6Address1 = IPAddressRang.split("\\|");
        StringBuilder sb1=new StringBuilder();
        for(int i1=0;i1<tmpIPv6Address1.length;i1++){
            if(i1>0){
                sb1.append(",");
            }
            sb1.append("\"").append(tmpIPv6Address1[i1]).append("\"");
        }
        String sql1="UPDATE dm_IPv6 SET State=? WHERE Address IN ("+sb1.toString()+")";
        long RValue1 = dbUtilsTemplate.update(sql1, MarkVal);
        if (RValue1 > 0) {
            return "OK";
        }
        return "修改状态值失败,请联系管理员";
    }


    /**
     * 标识删除
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetDeleteMark(int Id, boolean MarkVal) {
        return dbToolsService.edit("dm_IPv6", new String[]{"DeleteMark"}, new Object[]{MarkVal}, "Id", StrUtil.toStr(Id), "int");
    }

    /**
     * 标识调用
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetCallMark(int Id, boolean MarkVal) {
        return dbToolsService.edit("dm_IPv6", new String[]{"CallMark"}, new Object[]{MarkVal}, "Id", StrUtil.toStr(Id), "int");
    }

    /**
     * 按ID查询单条记录
     *
     * @param Id
     * @return
     */
    public IPv6 findById(int Id) {
        String sqlStr = "SELECT * FROM dm_IPv6 WHERE Id=?";
        return dbUtilsTemplate.findFirst(IPv6.class, sqlStr, Id);
    }

    /**
     * 按IPv6地址查询单条记录
     *
     * @param Address
     * @return
     */
    public IPv6 findByAddress(String Address) {
        String sqlStr = "SELECT * FROM dm_IPv6 WHERE Address=?";
        return dbUtilsTemplate.findFirst(IPv6.class, sqlStr, Address);
    }

    /**
     * 查询记录 分页
     *
     * @param State      状态
     * @param Enabled    有效性
     * @param DeleteMark 删除标识
     * @param CallMark   调用标识
     * @param fField     查询字段
     * @param fValue     查询值
     * @param start      开始记录
     * @param number     记录数
     * @return
     */
    public String findByPage(Boolean State, Boolean Enabled, Boolean DeleteMark, Boolean CallMark, String fField, String fValue, int start, int number) {
        DataPage dataPage1 = new DataPage();
        dataPage1.setTableName("dm_IPv6");
        dataPage1.setColumns("Id,Address,State,Remarks,Enabled,SortCode,DeleteMark,CallMark");
        String[] strings1 = new String[]{"Id", "Address", "State", "Remarks", "Enabled", "SortCode", "DeleteMark", "CallMark"};
        dataPage1.setOrder_field("SortCode ASC");
        dataPage1.setGroup_field("");
        dataPage1.setCurrPage(start);
        dataPage1.setPageSize(number);
        StringBuilder sb1 = new StringBuilder();
        if (State != null) {
            sb1.append("State=").append(State);
        }
        if (CallMark != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
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
            sb1.append("(Address LIKE '%").append(fValue).append("%' OR Remarks LIKE '%").append(fValue).append("%')");
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
     * @param State      状态
     * @param Enabled    有效性
     * @param DeleteMark 删除标识
     * @param CallMark   调用标识
     * @param fField     查询字段
     * @param fValue     查询值
     * @return
     */
    public int GetRowCount(Boolean State, Boolean Enabled, Boolean DeleteMark, Boolean CallMark, String fField, String fValue) {
        StringBuilder sb1 = new StringBuilder();
        if (State != null) {
            sb1.append("State=").append(State);
        }
        if (CallMark != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
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
            sb1.append("(Address LIKE '%").append(fValue).append("%' OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        return dataPageService.GetRowCount("Id", "dm_IPv6", sb1.toString());
    }

    /**
     * 验证添加
     *
     * @param iPv6
     * @return
     */
    public String VerifyAdd(IPv6 iPv6) {
        String msg = "";
        if (!StrUtil.trimNotEmpty(iPv6.getAddress())) {
            msg = "请输入正确的IPv6地址";
        } else if (dbToolsService.IsRepeat("dm_IPv6", "Address", "WHERE Address='" + iPv6.getAddress() + "'")) {
            msg = "IPv6地址已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }

    /**
     * 验证修改
     *
     * @param iPv6
     * @return
     */
    public String VerifyEdit(IPv6 iPv6) {
        String msg = "";
        if (!StrUtil.trimNotEmpty(iPv6.getAddress())) {
            msg = "请输入正确的IPv6地址";
        } else if (dbToolsService.IsRepeat("dm_IPv6", "Address", "WHERE Address='" + iPv6.getAddress() + "' AND Id<>" + iPv6.getId())) {
            msg = "IPv6地址已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }

    /**
     * 导出数据到Excel文件
     *
     * @param os
     * @param State
     * @param Enabled
     * @param DeleteMark
     * @param CallMark
     * @param fField
     * @param fValue
     * @throws Exception
     */
    public void ExportToExcel(OutputStream os,Boolean State, Boolean Enabled, Boolean DeleteMark, Boolean CallMark, String fField, String fValue) throws Exception {
        String titleName = "DM_IPv6";
        String[] fFieldStr = new String[]{ "Address", "State", "Enabled", "Remarks"};
        String[] fFieldFullName = new String[]{"IPv6地址", "是否使用", "是否有效", "备注"};
        int[] ColumnWidth = new int[]{20, 10, 10, 20};
        StringBuilder sb1 = new StringBuilder();
        if (State != null) {
            sb1.append("State=").append(State);
        }
        if (CallMark != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
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
            sb1.append("(Address LIKE '%").append(fValue).append("%' OR Remarks LIKE '%").append(fValue).append("%')");
        } else {  //精确
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        String orderByStr = "SortCode ASC";
        String ExcelHeaderName = "IPv6";
        dbToolsService.ExportToExcel(os, titleName, fFieldStr, fFieldFullName, ColumnWidth, sb1.toString(), orderByStr, ExcelHeaderName);
    }

    //导入 fileName 源文件
    public String IPv6Import(String fileName, Date CreateDate, String CreateUserCode, String CreateUserName) {
        int RowCount = 0;
        int YesCount = 0;
        int ErrCount = 0;
        try {
            Workbook workbook = Workbook.getWorkbook(new File(fileName));
            Sheet sheet = workbook.getSheet(0);
            for (int i1 = 1; i1 < sheet.getRows(); i1++) {
                RowCount++;
                Cell cell1 = sheet.getCell(1, i1); //IPv6地址
                Cell cell2 = sheet.getCell(2, i1); //备注
                if (!"".equals(cell1.getContents())) {
                    IPv6 tmp_iPv6=new IPv6();
                    tmp_iPv6.setAddress(StrUtil.toStr(cell1.getContents()));
                    tmp_iPv6.setRemarks(StrUtil.toStr(cell2.getContents()));
                    tmp_iPv6.setSortCode(GetRowCount(null,null,null,null,"","")+1);
                    tmp_iPv6.setState(true);
                    tmp_iPv6.setCallMark(true);
                    tmp_iPv6.setEnabled(true);
                    tmp_iPv6.setDeleteMark(false);
                    tmp_iPv6.setCreateDate(CreateDate);
                    tmp_iPv6.setCreateUserCode(CreateUserCode);
                    tmp_iPv6.setCreateUserName(CreateUserName);
                    String msg = VerifyAdd(tmp_iPv6);
                    if ("OK".equals(msg)) {
                        String RValue = add(tmp_iPv6);
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
