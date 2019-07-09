package com.hlh.GLPT.dm.dao;

import com.hlh.GLPT.dm.domain.IPv4;
import com.hlh.GLPT.dm.service.IPv4GroupService;
import com.hlh.common.domain.DataPage;
import com.hlh.common.service.DataPageService;
import com.hlh.common.service.DbToolsService;
import com.hlh.util.*;
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
public class IPv4Dao {
    @Autowired
    private DbToolsService dbToolsService;
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;
    @Autowired
    private DataPageService dataPageService;
    @Autowired
    private IPv4GroupService iPv4GroupService;

    /**
     * 添加
     *
     * @param iPv4
     * @return
     */
    public String add(IPv4 iPv4) {
        String[] fField = new String[]{"GroupCode", "GroupName", "Address", "State", "Remarks", "Enabled", "SortCode"
                , "DeleteMark", "CallMark", "CreateDate", "CreateUserCode", "CreateUserName"};
        Object[] fVal = new Object[]{iPv4.getGroupCode(), iPv4.getGroupName(), iPv4.getAddress(), iPv4.isState(), iPv4.getRemarks(), iPv4.isEnabled(), iPv4.getSortCode()
                , iPv4.isDeleteMark(), iPv4.isCallMark(), iPv4.getCreateDate(), iPv4.getCreateUserCode(), iPv4.getCreateUserName()};
        return dbToolsService.add("dm_IPv4", fField, fVal);
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
        String[] tmpIPv4Address1 = addressRang.split("\\|");
        int jsAddCount1=0;
        for (int i1 = 0; i1 < tmpIPv4Address1.length; i1++) {
            if (IPAddressUtil.isIPv4Address(tmpIPv4Address1[i1])) {
                IPv4 tmp_ip1 = findByAddress(tmpIPv4Address1[i1]);
                if (tmp_ip1 != null) { //存在
                    SetCallMark(tmp_ip1.getId(), true);
                    SetState(tmp_ip1.getId(), true);
                } else { //不存在
                    IPv4 tmp_iPv4 = new IPv4();
                    tmp_iPv4.setAddress(tmpIPv4Address1[i1]);
                    tmp_iPv4.setSortCode(dbToolsService.GetFieldMaxVal("dm_IPv4","SortCode")+1);
                    tmp_iPv4.setState(true);
                    tmp_iPv4.setCallMark(true);
                    tmp_iPv4.setEnabled(true);
                    tmp_iPv4.setDeleteMark(false);
                    tmp_iPv4.setCreateDate(CreateDate);
                    tmp_iPv4.setCreateUserCode(CreateUserCode);
                    tmp_iPv4.setCreateUserName(CreateUserName);
                    if("OK".equals(add(tmp_iPv4))){
                        jsAddCount1++;
                    }
                }
            }
        }
        return jsAddCount1;
    }

    /**
     * 批量添加
     *
     * @param BeginAddress 开始地址
     * @param EndAddress   结束地址
     * @param Enabled      有效性
     * @param Remarks      备注
     * @return 添加的记录数
     */
    public int BatchAdd(String GroupCode, String GroupName, String BeginAddress, String EndAddress, Boolean Enabled, String Remarks, Date CreateDate, String CreateUserCode, String CreateUserName) {
        int YesCount = 0;
        if (!"".equals(BeginAddress) && "".equals(EndAddress)) {
            if (IPAddressUtil.isIPv4Address(BeginAddress)) {
                IPv4 iPv4 = new IPv4();
                iPv4.setGroupCode(GroupCode);
                iPv4.setGroupName(GroupName);
                iPv4.setAddress(BeginAddress);
                if (Enabled != null) iPv4.setEnabled(Enabled);
                iPv4.setRemarks(Remarks);
                iPv4.setSortCode(dbToolsService.GetFieldMaxVal("dm_IPv4", "SortCode")+1);
                iPv4.setCreateDate(CreateDate);
                iPv4.setCreateUserCode(CreateUserCode);
                iPv4.setCreateUserName(CreateUserName);
                String msg = VerifyAdd(iPv4);
                if ("OK".equals(msg)) {
                    msg = add(iPv4);
                    if ("OK".equals(msg)) YesCount++;
                }
            }
        } else if (!"".equals(BeginAddress) && !"".equals(EndAddress)) {
            if (BeginAddress.equals(EndAddress)) {
                if (IPAddressUtil.isIPv4Address(BeginAddress)) {
                    IPv4 iPv4 = new IPv4();
                    iPv4.setGroupCode(GroupCode);
                    iPv4.setGroupName(GroupName);
                    iPv4.setAddress(BeginAddress);
                    if (Enabled != null) iPv4.setEnabled(Enabled);
                    iPv4.setRemarks(Remarks);
                    iPv4.setSortCode(dbToolsService.GetFieldMaxVal("dm_IPv4", "SortCode")+1);
                    iPv4.setCreateDate(CreateDate);
                    iPv4.setCreateUserCode(CreateUserCode);
                    iPv4.setCreateUserName(CreateUserName);
                    String msg = VerifyAdd(iPv4);
                    if ("OK".equals(msg)) {
                        msg = add(iPv4);
                        if ("OK".equals(msg)) YesCount++;
                    }
                }
            } else {
                if (IPAddressUtil.isIPv4Address(BeginAddress) && IPAddressUtil.isIPv4Address(EndAddress)) {
                    String[] tmpIp1 = BeginAddress.split("\\.");
                    String[] tmpIp2 = EndAddress.split("\\.");
                    if (tmpIp1.length > 3 && tmpIp2.length > 3) {
                        int BVal1 = Integer.parseInt(tmpIp1[3]);
                        int EVal1 = Integer.parseInt(tmpIp2[3]);
                        if (EVal1 > BVal1) {
                            for (int i1 = BVal1; i1 <= EVal1; i1++) {
                                IPv4 iPv4 = new IPv4();
                                String tmpIpVal1 = tmpIp1[0] + "." + tmpIp1[1] + "." + tmpIp1[2] + "." + i1;
                                iPv4.setAddress(tmpIpVal1);
                                if (Enabled != null) iPv4.setEnabled(Enabled);
                                iPv4.setGroupCode(GroupCode);
                                iPv4.setGroupName(GroupName);
                                iPv4.setRemarks(Remarks);
                                iPv4.setSortCode(dbToolsService.GetFieldMaxVal("dm_IPv4", "SortCode")+1);
                                iPv4.setCreateDate(CreateDate);
                                iPv4.setCreateUserCode(CreateUserCode);
                                iPv4.setCreateUserName(CreateUserName);
                                String msg = VerifyAdd(iPv4);
                                if ("OK".equals(msg)) {
                                    msg = add(iPv4);
                                    if ("OK".equals(msg)) YesCount++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return YesCount;
    }


    /**
     * 彻底删除
     *
     * @param Id
     * @return
     */
    public String ThoroughDel(int Id) {
        return dbToolsService.ThoroughDel("dm_IPv4", "Id", StrUtil.toStr(Id), "int");
    }

    /**
     * 修改
     *
     * @param iPv4
     * @return
     */
    public String edit(IPv4 iPv4) {
        String[] fField = new String[]{"GroupCode", "GroupName", "Address", "State", "Remarks", "Enabled", "SortCode"
                , "ModifyDate", "ModifyUserCode", "ModifyUserName"};
        Object[] fVal = new Object[]{iPv4.getGroupCode(), iPv4.getGroupName(), iPv4.getAddress(), iPv4.isState(), iPv4.getRemarks(), iPv4.isEnabled(), iPv4.getSortCode()
                , iPv4.getModifyDate(), iPv4.getModifyUserCode(), iPv4.getModifyUserName()};
        return dbToolsService.edit("dm_IPv4", fField, fVal, "Id", StrUtil.toStr(iPv4.getId()), "int");
    }

    /**
     * 批量修改分组
     *
     * @param RowId
     * @param CurrGroupCode
     * @return
     */
    public int SetGroup(String[] RowId,String CurrGroupCode) {
        int OKCount1=0;
       String GroupName1=iPv4GroupService.FieldFValToFieldFVal("GroupCode",CurrGroupCode,"GroupName");
        if(!"".equals(GroupName1)){
            for(int i1=0;i1<RowId.length;i1++){
               if("OK".equals(dbToolsService.edit("dm_IPv4", new String[]{"GroupCode","GroupName"}, new Object[]{CurrGroupCode,GroupName1}, "Id", StrUtil.toStr(RowId[i1]), "int"))){
                   OKCount1++;
               }
            }
        }
        return OKCount1;
    }

    /**
     * 标识状态
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetState(int Id, boolean MarkVal) {
        return dbToolsService.edit("dm_IPv4", new String[]{"State"}, new Object[]{MarkVal}, "Id", StrUtil.toStr(Id), "int");
    }

    /**
     * 批量设置标识状态
     *
     * @param IPAddressRang       IP地址范围
     * @param MarkVal 标识值
     * @return
     */
    public String BatchSetState(String IPAddressRang, boolean MarkVal) {
        String[] tmpIPv4Address1 = IPAddressRang.split("\\|");
        StringBuilder sb1=new StringBuilder();
        for(int i1=0;i1<tmpIPv4Address1.length;i1++){
            if(i1>0){
                sb1.append(",");
            }
             sb1.append("\"").append(tmpIPv4Address1[i1]).append("\"");
        }
        String sql1="UPDATE dm_IPv4 SET State=? WHERE Address IN ("+sb1.toString()+")";
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
        return dbToolsService.edit("dm_IPv4", new String[]{"DeleteMark"}, new Object[]{MarkVal}, "Id", StrUtil.toStr(Id), "int");
    }

    /**
     * 标识调用
     *
     * @param Id      ID
     * @param MarkVal 标识值
     * @return
     */
    public String SetCallMark(int Id, boolean MarkVal) {
        return dbToolsService.edit("dm_IPv4", new String[]{"CallMark"}, new Object[]{MarkVal}, "Id", StrUtil.toStr(Id), "int");
    }

    /**
     * 按ID查询单条记录
     *
     * @param Id
     * @return
     */
    public IPv4 findById(int Id) {
        String sqlStr = "SELECT * FROM dm_IPv4 WHERE Id=?";
        return dbUtilsTemplate.findFirst(IPv4.class, sqlStr, Id);
    }

    /**
     * 按IPv4地址查询单条记录
     *
     * @param Address
     * @return
     */
    public IPv4 findByAddress(String Address) {
        String sqlStr = "SELECT * FROM dm_IPv4 WHERE Address=?";
        return dbUtilsTemplate.findFirst(IPv4.class, sqlStr, Address);
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
    public String findByPage(Boolean State,String GroupCode, Boolean Enabled, Boolean DeleteMark, Boolean CallMark, String fField, String fValue, int start, int number) {
        DataPage dataPage1 = new DataPage();
        dataPage1.setTableName("dm_IPv4");
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
        if(GroupCode!=null){
            if(sb1.length()>0){
                sb1.append(" AND ");
            }
            sb1.append(" GroupCode='").append(GroupCode).append("'");
        }else{
            if(sb1.length()>0){
                sb1.append(" AND ");
            }
            sb1.append(" (GroupCode='' OR GroupCode is null)");
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
     * @param GroupCode  分组代码
     * @param Enabled    有效性
     * @param DeleteMark 删除标识
     * @param CallMark   调用标识
     * @param fField     查询字段
     * @param fValue     查询值
     * @return
     */
    public int GetRowCount(Boolean State, String GroupCode, Boolean Enabled, Boolean DeleteMark, Boolean CallMark, String fField, String fValue) {
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
        if (GroupCode != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(" GroupCode='").append(GroupCode).append("'");
        }else{
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(" (GroupCode='' or GroupCode is null)");
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
        return dataPageService.GetRowCount("Id", "dm_IPv4", sb1.toString());
    }

    /**
     * 查询记录数
     *
     * @param GroupCode
     * @return
     */
    public int GetRowCount(String GroupCode) {
        return dataPageService.GetRowCount("Id", "dm_IPv4", "GroupCode='" + GroupCode + "'");
    }

    /**
     * 验证添加
     *
     * @param iPv4
     * @return
     */
    public String VerifyAdd(IPv4 iPv4) {
        String msg = "";
        if (!StrUtil.trimNotEmpty(iPv4.getAddress())) {
            msg = "请输入正确的IPv4地址";
        } else if (dbToolsService.IsRepeat("dm_IPv4", "Address", "WHERE Address='" + iPv4.getAddress() + "'")) {
            msg = "IPv4地址已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }

    /**
     * 验证修改
     *
     * @param iPv4
     * @return
     */
    public String VerifyEdit(IPv4 iPv4) {
        String msg = "";
        if (!StrUtil.trimNotEmpty(iPv4.getAddress())) {
            msg = "请输入正确的IPv4地址";
        } else if (dbToolsService.IsRepeat("dm_IPv4", "Address", "WHERE Address='" + iPv4.getAddress() + "' AND Id<>" + iPv4.getId())) {
            msg = "IPv4地址已经存在";
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
    public void ExportToExcel(OutputStream os, Boolean State, Boolean Enabled, Boolean DeleteMark, Boolean CallMark, String fField, String fValue) throws Exception {
        String titleName = "DM_IPv4";
        String[] fFieldStr = new String[]{"Address", "State", "Enabled", "Remarks"};
        String[] fFieldFullName = new String[]{"IPv4地址", "状态", "有效性", "备注"};
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
        String ExcelHeaderName = "IPv4";
        dbToolsService.ExportToExcel(os, titleName, fFieldStr, fFieldFullName, ColumnWidth, sb1.toString(), orderByStr, ExcelHeaderName);
    }


    //导入 fileName 源文件
    public String IPv4Import(String fileName, Date CreateDate, String CreateUserCode, String CreateUserName) {
        int RowCount = 0;
        int YesCount = 0;
        int ErrCount = 0;
        try {
            Workbook workbook = Workbook.getWorkbook(new File(fileName));
            Sheet sheet = workbook.getSheet(0);
            for (int i1 = 1; i1 < sheet.getRows(); i1++) {
                RowCount++;
                Cell cell1 = sheet.getCell(1, i1); //IPv4地址
                Cell cell2 = sheet.getCell(2, i1); //备注
                if (!"".equals(cell1.getContents())) {
                    IPv4 tmp_iPv4 = new IPv4();
                    tmp_iPv4.setAddress(StrUtil.toStr(cell1.getContents()));
                    tmp_iPv4.setRemarks(StrUtil.toStr(cell2.getContents()));
                    tmp_iPv4.setSortCode(dbToolsService.GetFieldMaxVal("dm_IPv4", "SortCode")+1);
                    tmp_iPv4.setState(true);
                    tmp_iPv4.setCallMark(true);
                    tmp_iPv4.setEnabled(true);
                    tmp_iPv4.setDeleteMark(false);
                    tmp_iPv4.setCreateDate(CreateDate);
                    tmp_iPv4.setCreateUserCode(CreateUserCode);
                    tmp_iPv4.setCreateUserName(CreateUserName);
                    String msg = VerifyAdd(tmp_iPv4);
                    if ("OK".equals(msg)) {
                        String RValue = add(tmp_iPv4);
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
