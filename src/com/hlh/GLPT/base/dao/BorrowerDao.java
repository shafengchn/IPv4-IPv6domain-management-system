package com.hlh.GLPT.base.dao;

import com.hlh.GLPT.base.domain.Borrower;
import com.hlh.common.domain.DataPage;
import com.hlh.common.service.DataPageService;
import com.hlh.util.DbUtilsTemplate;
import com.hlh.util.StrUtil;
import com.hlh.util.jsonUtil;
import jxl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * User: 黄良辉
 * Date: 14-3-14
 * Time: 下午7:44
 */
@Repository
public class BorrowerDao {
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;
    @Autowired
    private DataPageService dataPageService;

    /*
    * 增加
    * */
    public String add(Borrower borrower) {
        String sqlStr = "INSERT INTO base_Borrower (BorrowerId,UserCode,Code,FullName,Alias" +
                ",Gender,FindPassword,IDCard,Email,Mobile" +
                ",Telephone,OICQ,Age,Birthday,HomeAddress" +
                ",HomePhone,PhotoImg,Description,Enabled,SortCode" +
                ",CreateDate,CreateUserCode,CreateUserName)" +
                " VALUES(?,?,?,?,?" +
                ",?,?,?,?,?" +
                ",?,?,?,?,?" +
                ",?,?,?,?,?" +
                ",?,?,?)";
        if ("".equals(borrower.getAge())) {
            borrower.setAge("0");
        }
        if ("".equals(borrower.getBirthday())) {
            borrower.setBirthday(null);
        }
        Object[] objects = new Object[]{borrower.getBorrowerId(), borrower.getUserCode(), borrower.getCode(), borrower.getFullName(), borrower.getAlias()
                , borrower.getGender(), borrower.getFindPassword(), borrower.getIDCard(), borrower.getEmail(), borrower.getMobile()
                , borrower.getTelephone(), borrower.getOICQ(), Integer.parseInt(borrower.getAge()), borrower.getBirthday(), borrower.getHomeAddress()
                , borrower.getHomePhone(), borrower.getPhotoImg(), borrower.getDescription(), borrower.isEnabled(), borrower.getSortCode(), borrower.getCreateDate()
                , borrower.getCreateUserCode(), borrower.getCreateUserName()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "添加操作失败,请联系管理员";
    }

    /*
     * 彻底删除
     * */
    public String ThoroughDel(String BorrowerId) {
        Borrower borrower1 = findById(BorrowerId);
        if ("1000".equals(borrower1.getCode())) {
            return "当前记录不能删除";
        } else {
            String sqlStr = "DELETE FROM base_Borrower  WHERE BorrowerId=?";
            long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{BorrowerId});
            if (RValue1 > 0) {
                return "OK";
            }
            return "彻底删除操作失败,请联系管理员";
        }
    }

    /*
    * 删除
    * */
    public String del(String BorrowerId, Date ModifyDate, String ModifyUserCode, String ModifyUserName) {
        String sqlStr = "UPDATE Base_Borrower SET DeleteMark=?,ModifyDate=?,ModifyUserCode=?,ModifyUserName=? WHERE BorrowerId=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{true, ModifyDate, ModifyUserCode, ModifyUserName, BorrowerId});
        if (RValue1 > 0) {
            return "OK";
        }
        return "删除操作失败,请联系管理员";
    }

    /*
    * 修改
    * */
    public String edit(Borrower borrower) {
        String sqlStr = "UPDATE Base_Borrower SET UserCode=?,Code=?,FullName=?,Alias=?,Gender=?" +
                ",FindPassword=?,IDCard=?,Email=?,Mobile=?,Telephone=?,OICQ=?" +
                ",Age=?,Birthday=?,HomeAddress=?,HomePhone=?,PhotoImg=?,Description=?" +
                ",Enabled=?,SortCode=?,ModifyDate=?,ModifyUserCode=?,ModifyUserName=? WHERE BorrowerId=?";
        if ("".equals(borrower.getAge())) {
            borrower.setAge("0");
        }
        if ("".equals(borrower.getBirthday())) {
            borrower.setBirthday(null);
        }
        Object[] objects = new Object[]{borrower.getUserCode(), borrower.getCode(), borrower.getFullName(), borrower.getAlias(), borrower.getGender()
                , borrower.getFindPassword(), borrower.getIDCard(), borrower.getEmail(), borrower.getMobile(), borrower.getTelephone(), borrower.getOICQ()
                , Integer.parseInt(borrower.getAge()), borrower.getBirthday(), borrower.getHomeAddress(), borrower.getHomePhone(), borrower.getPhotoImg(), borrower.getDescription()
                , borrower.isEnabled(), borrower.getSortCode(), borrower.getModifyDate(), borrower.getModifyUserCode(), borrower.getModifyUserName()
                , borrower.getBorrowerId()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "修改操作失败,请联系管理员";
    }

    /*
   * 按Id查询
   * */
    public Borrower findById(String BorrowerId) {
        String sqlStr = "SELECT * FROM Base_Borrower WHERE BorrowerId=?";
        return dbUtilsTemplate.findFirst(Borrower.class, sqlStr, BorrowerId);
    }


    /*
    * 按BorrowerCode查询
    * */
    public Borrower findByCode(String BorrowerCode) {
        String sqlStr = "SELECT * FROM Base_Borrower WHERE code=?";
        return dbUtilsTemplate.findFirst(Borrower.class, sqlStr, BorrowerCode);
    }

    //获取总行数
    public int GetRowCount() {
        String sSql1 = "SELECT COUNT(BorrowerId) as RowCount FROM Base_Borrower";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1);
        if (objects != null) {
            if (objects.length > 0) {
                return Integer.parseInt(objects[0].toString());
            }
        }
        return 0;
    }

    //获取总行数
    public int GetRowCountEx() {
        String sSql1 = "SELECT COUNT(BorrowerId) as RowCount FROM Base_Borrower WHERE Enabled=true AND DeleteMark=false";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1);
        if (objects != null) {
            if (objects.length > 0) {
                return Integer.parseInt(objects[0].toString());
            }
        }
        return 0;
    }


    /*
 * 查询
 * */
    public List<Borrower> findAll() {
        String sqlStr = "SELECT * FROM Base_Borrower ORDER BY SortCode";
        return dbUtilsTemplate.find(Borrower.class, sqlStr);
    }

    /*
* 查询
* */
    public List<Borrower> findBorrower(String filedName, String findValue) {
        String sqlStr = "SELECT * FROM Base_Borrower WHERE " + filedName + "='" + findValue + "'";
        return dbUtilsTemplate.find(Borrower.class, sqlStr);
    }

    /*
* 查询(有效的教师信息)
* */
    public List<Borrower> findAllEx() {
        String sqlStr = "SELECT * FROM Base_Borrower WHERE Enabled=true AND DeleteMark=false ORDER BY SortCode";
        return dbUtilsTemplate.find(Borrower.class, sqlStr);
    }


    public String findAll(String ShowFieldName, String[] strings) {
        String sqlStr = "SELECT " + ShowFieldName + " FROM Base_Borrower ORDER BY SortCode";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sqlStr);
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings);
        return tmpStr1;
    }

    /**
     * 查询有效的教师信息
     * @param ShowFieldName
     * @param strings
     * @return
     */
    public String findAllEx(String ShowFieldName, String[] strings) {
        String sqlStr = "SELECT " + ShowFieldName + " FROM Base_Borrower WHERE Enabled=true AND DeleteMark=false ORDER BY SortCode";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sqlStr);
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings);
        return tmpStr1;
    }

    //分页 模糊查找
    public String findByPage(String findValue, boolean enabled, int start, int number) {
        String sSql1 = "";
        if (enabled) { //只显示有效
            sSql1 = "SELECT BorrowerId,UserCode,Code,FullName,Alias,Gender,IDCard,Email,Mobile,Telephone,OICQ,Birthday,Description,Enabled,SortCode,DeleteMark"
                    + " FROM Base_Borrower"
                    + " WHERE DeleteMark=false AND Enabled=true AND (Code LIKE ? OR FullName LIKE ? OR Gender LIKE ? OR IDCard LIKE ? OR Email LIKE ? OR Mobile LIKE ? OR Telephone LIKE ? OR OICQ LIKE ? OR Birthday LIKE ?)"
                    + " ORDER BY SortCode LIMIT ?,?";
        } else {
            sSql1 = "SELECT BorrowerId,UserCode,Code,FullName,Alias,Gender,IDCard,Email,Mobile,Telephone,OICQ,Birthday,Description,Enabled,SortCode,DeleteMark"
                    + " FROM Base_Borrower"
                    + " WHERE DeleteMark=false AND (Code LIKE ? OR FullName LIKE ? OR Gender LIKE ? OR IDCard LIKE ? OR Email LIKE ? OR Mobile LIKE ? OR Telephone LIKE ? OR OICQ LIKE ? OR Birthday LIKE ?)"
                    + " ORDER BY SortCode LIMIT ?,?";
        }
        List tmpList1 = dbUtilsTemplate.executeQueryList(sSql1, "%" + findValue + "%", "%" + findValue + "%", "%" + findValue + "%"
                , "%" + findValue + "%", "%" + findValue + "%", "%" + findValue + "%"
                , "%" + findValue + "%", "%" + findValue + "%", "%" + findValue + "%"
                , start, number);
        String[] strings1 = new String[]{"BorrowerId", "UserCode", "Code", "FullName", "Alias", "Gender", "IDCard", "Email", "Mobile", "Telephone", "OICQ", "Birthday", "Description", "Enabled", "SortCode", "DeleteMark"};
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    //获取总行数 模糊查找
    public int GetRowCount(String findValue, boolean enabled) {
        String sSql1 = "";
        if (enabled) {
            sSql1 = "SELECT COUNT(BorrowerId) as RowCount FROM Base_Borrower"
                    + " WHERE DeleteMark=false AND Enabled=true AND (Code LIKE ? OR FullName LIKE ? OR Gender LIKE ? OR IDCard LIKE ? OR Email LIKE ? OR Mobile LIKE ? OR Telephone LIKE ? OR OICQ LIKE ? OR Birthday LIKE ?)";

        } else {
            sSql1 = "SELECT COUNT(BorrowerId) as RowCount FROM Base_Borrower"
                    + " WHERE DeleteMark=false AND (Code LIKE ? OR FullName LIKE ? OR Gender LIKE ? OR IDCard LIKE ? OR Email LIKE ? OR Mobile LIKE ? OR Telephone LIKE ? OR OICQ LIKE ? OR Birthday LIKE ?)";
        }
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, "%" + findValue + "%", "%" + findValue + "%", "%" + findValue + "%"
                , "%" + findValue + "%", "%" + findValue + "%", "%" + findValue + "%"
                , "%" + findValue + "%", "%" + findValue + "%", "%" + findValue + "%");
        if (objects != null) {
            if (objects.length > 0) {
                return Integer.parseInt(objects[0].toString());
            }
        }
        return 0;
    }


    //分页 精确查找
    public String findByPage(String fieldName, String findValue, boolean enabled, int start, int number) {
        String sSql1 = "";
        if (enabled) { //只显示有效
            sSql1 = "SELECT BorrowerId,UserCode,Code,FullName,Alias,Gender,IDCard,Email,Mobile,Telephone,OICQ,Birthday,Description,Enabled,SortCode,DeleteMark"
                    + " FROM Base_Borrower"
                    + " WHERE DeleteMark=false AND Enabled=true AND " + fieldName + "='" + findValue + "'"
                    + " ORDER BY SortCode LIMIT ?,?";
        } else {
            sSql1 = "SELECT BorrowerId,UserCode,Code,FullName,Alias,Gender,IDCard,Email,Mobile,Telephone,OICQ,Birthday,Description,Enabled,SortCode,DeleteMark"
                    + " FROM Base_Borrower"
                    + " WHERE DeleteMark=false AND " + fieldName + "='" + findValue + "'"
                    + " ORDER BY SortCode LIMIT ?,?";
        }
        List tmpList1 = dbUtilsTemplate.executeQueryList(sSql1, start, number);
        String[] strings1 = new String[]{"BorrowerId", "UserCode", "Code", "FullName", "Alias", "Gender", "IDCard", "Email", "Mobile", "Telephone", "OICQ", "Birthday", "Description", "Enabled", "SortCode", "DeleteMark"};
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    //获取总行数 精确查找
    public int GetRowCount(String fieldName, String findValue, boolean enabled) {
        String sSql1 = "";
        if (enabled) { //只显示有效
            sSql1 = "SELECT COUNT(BorrowerId) as RowCount FROM Base_Borrower"
                    + " WHERE DeleteMark=false AND Enabled=true AND " + fieldName + "='" + findValue + "'";
        } else {
            sSql1 = "SELECT COUNT(BorrowerId) as RowCount FROM Base_Borrower"
                    + " WHERE DeleteMark=false AND " + fieldName + "='" + findValue + "'";
        }
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1);
        if (objects != null) {
            if (objects.length > 0) {
                return Integer.parseInt(objects[0].toString());
            }
        }
        return 0;
    }

    /**
     * 分页查询
     *
     * @param Enabled    有效性
     * @param DeleteMark 标识删除
     * @param fField     查询字段
     * @param fValue     字段值
     * @param start
     * @param number
     * @return
     */
    public String findByPage(Boolean Enabled, Boolean DeleteMark, String fField, String fValue, int start, int number) {
        DataPage dataPage1 = new DataPage();
        dataPage1.setTableName("Base_Borrower");
        dataPage1.setColumns("BorrowerId,UserCode,Code,FullName,Alias,Gender,IDCard,Email,Mobile,Telephone,OICQ,Birthday,Description,Enabled,SortCode,DeleteMark");
        String[] strings1 = new String[]{"BorrowerId", "UserCode", "Code", "FullName", "Alias", "Gender", "IDCard", "Email", "Mobile", "Telephone", "OICQ", "Birthday", "Description", "Enabled", "SortCode", "DeleteMark"};
        dataPage1.setOrder_field("SortCode ASC");
        dataPage1.setGroup_field("");
        dataPage1.setCurrPage(start);
        dataPage1.setPageSize(number);
        StringBuilder sb1 = new StringBuilder();
        if (Enabled != null) {
            sb1.append(" Enabled=").append(Enabled);
        }
        if (DeleteMark != null) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(" DeleteMark=").append(DeleteMark);
        }
        if ("".equals(fField)) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(" (Code LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FullName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Alias LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Gender LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IDCard LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Email LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Mobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Telephone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR OICQ LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Birthday LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Description LIKE '%").append(fValue).append("%')");
        } else {
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
     * 获取记录数
     *
     * @param Enabled        有效性
     * @param DeleteMark     标识删除
     * @param fField         字段名
     * @param fValue         字段值
     * @return
     */
    public int GetRowCount(Boolean Enabled, Boolean DeleteMark, String fField, String fValue) {
        StringBuilder sb1 = new StringBuilder();
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
        if ("".equals(fField)) {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(" (Code LIKE '%").append(fValue).append("%'");
            sb1.append(" OR FullName LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Alias LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Gender LIKE '%").append(fValue).append("%'");
            sb1.append(" OR IDCard LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Email LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Mobile LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Telephone LIKE '%").append(fValue).append("%'");
            sb1.append(" OR OICQ LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Birthday LIKE '%").append(fValue).append("%'");
            sb1.append(" OR Description LIKE '%").append(fValue).append("%')");
        } else {
            if (sb1.length() > 0) {
                sb1.append(" AND ");
            }
            sb1.append(fField).append("='").append(fValue).append("'");
        }
        return dataPageService.GetRowCount("BorrowerId", "Base_Borrower", sb1.toString());
    }


    public boolean BorrowerIdIsRepeat(String BorrowerId) {
        if (!"".equals(BorrowerId)) {
            String sSql1 = "SELECT BorrowerId FROM Base_Borrower WHERE BorrowerId=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, BorrowerId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean CodeIsRepeat(String code) {
        if (!"".equals(code)) {
            String sSql1 = "SELECT BorrowerId FROM Base_Borrower WHERE Code=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, code);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean CodeIsRepeat(String code, String BorrowerId) {
        if (!"".equals(code)) {
            String sSql1 = "SELECT BorrowerId FROM Base_Borrower WHERE Code=? AND BorrowerId<>?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, code, BorrowerId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean FullNameIsRepeat(String fullName) {
        if (!"".equals(fullName)) {
            String sSql1 = "SELECT BorrowerId FROM Base_Borrower WHERE FullName=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, fullName);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean FullNameIsRepeat(String fullName, String BorrowerId) {
        if (!"".equals(fullName)) {
            String sSql1 = "SELECT BorrowerId FROM Base_Borrower WHERE FullName=? AND BorrowerId<>?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, fullName, BorrowerId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean MobileIsRepeat(String Mobile) {
        if (!"".equals(Mobile)) {
            String sSql1 = "SELECT BorrowerId FROM Base_Borrower WHERE Mobile=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, Mobile);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean MobileIsRepeat(String Mobile, String BorrowerId) {
        if (!"".equals(Mobile)) {
            String sSql1 = "SELECT BorrowerId FROM Base_Borrower WHERE FullName=? AND BorrowerId<>?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, Mobile, BorrowerId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    //验证添加
    public String VerifyAdd(Borrower borrower) {
        String msg = "";
        if ("".equals(borrower.getBorrowerId()) || borrower.getBorrowerId() == null) {
            msg = "主键不能为空";
        } else if ("".equals(borrower.getCode()) || borrower.getCode() == null) {
            msg = "工号不能为空";
        } else if ("".equals(borrower.getFullName()) || borrower.getFullName() == null) {
            msg = "姓名不能为空";
        } else if ("".equals(borrower.getMobile()) || borrower.getMobile() == null) {
            msg = "手机号码不能为空";
        } else if (BorrowerIdIsRepeat(borrower.getBorrowerId())) {
            msg = "主键已经存在";
        } else if (CodeIsRepeat(borrower.getCode())) {
            msg = "工号已经存在";
        } else if (MobileIsRepeat(borrower.getMobile())) {
            msg = "手机号码已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }

    //验证修改
    public String VerifyEdit(Borrower borrower) {
        String msg = "";
        if ("".equals(borrower.getBorrowerId()) || borrower.getBorrowerId() == null) {
            msg = "请选择要操作的记录";
        } else if ("".equals(borrower.getCode()) || borrower.getCode() == null) {
            msg = "工号不能为空";
        } else if ("".equals(borrower.getFullName()) || borrower.getFullName() == null) {
            msg = "姓名不能为空";
        } else if ("".equals(borrower.getMobile()) || borrower.getMobile() == null) {
            msg = "手机号码不能为空";
        } else if (CodeIsRepeat(borrower.getCode(), borrower.getBorrowerId())) {
            msg = "工号已经存在";
        } else if (MobileIsRepeat(borrower.getMobile(), borrower.getBorrowerId())) {
            msg = "手机号码已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }

    //教师代码转教师名称
    public String CodeToFullName(String code) {
        if (!"".equals(code)) {
            String sSql1 = "SELECT FullName FROM Base_Borrower WHERE Code=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, code);
            if (objects != null) {
                return StrUtil.toStr(objects[0]);
            }
        }
        return "";
    }


    //教师导入 fileName 源文件
    public String BorrowerImport(String fileName, Date CreateDate, String CreateUserCode, String CreateUserName) {
        int RowCount = 0;
        int YesCount = 0;
        int ErrCount = 0;
        try {
            Workbook workbook = Workbook.getWorkbook(new File(fileName));
            Sheet sheet = workbook.getSheet(0);
            for (int i1 = 1; i1 < sheet.getRows(); i1++) {
                RowCount++;
                Cell cell0 = sheet.getCell(0, i1); //序号
                Cell cell1 = sheet.getCell(1, i1); //识别号
                Cell cell2 = sheet.getCell(2, i1); //教师姓名
                Cell cell3 = sheet.getCell(3, i1); //别名
                Cell cell4 = sheet.getCell(4, i1); //性别
                Cell cell5 = sheet.getCell(5, i1); //年龄
                Cell cell6 = sheet.getCell(6, i1); //身份证号
                Cell cell7 = sheet.getCell(7, i1); //电子邮件
                Cell cell8 = sheet.getCell(8, i1); //QQ号码
                Cell cell9 = sheet.getCell(9, i1); //手机
                Cell cell10 = sheet.getCell(10, i1); //电话
                Cell cell11 = sheet.getCell(11, i1); //出生日期
                Cell cell12 = sheet.getCell(12, i1); //家庭住址
                Cell cell13 = sheet.getCell(13, i1); //住宅电话
                Cell cell14 = sheet.getCell(14, i1); //描述
                if (!"".equals(cell1.getContents()) && !"".equals(cell2.getContents()) && !"".equals(cell4.getContents())) {
                    Borrower borrower1 = new Borrower();
                    borrower1.setBorrowerId(StrUtil.GetUUID());
                    borrower1.setCode(cell1.getContents());
                    borrower1.setFullName(cell2.getContents());
                    borrower1.setAlias(cell3.getContents());
                    borrower1.setGender(cell4.getContents());
                    borrower1.setAge(cell5.getContents());
                    borrower1.setIDCard(cell6.getContents());
                    borrower1.setEmail(cell7.getContents());
                    borrower1.setOICQ(cell8.getContents());
                    borrower1.setMobile(cell9.getContents());
                    borrower1.setTelephone(cell10.getContents());
                    borrower1.setBirthday(cell11.getContents());
                    borrower1.setHomeAddress(cell12.getContents());
                    borrower1.setHomePhone(cell13.getContents());
                    borrower1.setDescription(cell14.getContents());
                    borrower1.setEnabled(true);
                    borrower1.setSortCode(GetRowCount() + 1);
                    borrower1.setCreateDate(CreateDate);
                    borrower1.setCreateUserCode(CreateUserCode);
                    borrower1.setCreateUserName(CreateUserName);
                    String msg = VerifyAdd(borrower1);
                    if ("OK".equals(msg)) {
                        String RValue = add(borrower1);
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


    //重置密码
    public String resetPassword(String BorrowerId, String password, Date ChangePasswordDate, Date ModifyDate, String ModifyUserCode, String ModifyUserName) {
        String sqlStr = "UPDATE Base_Borrower SET FindPassword=?,ChangePasswordDate=?,ModifyDate=?" +
                ",ModifyUserCode=?,ModifyUserName=? WHERE BorrowerId=?";
        Object[] objects = new Object[]{password, ChangePasswordDate, ModifyDate, ModifyUserCode, ModifyUserName, BorrowerId};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "重置密码操作失败,请联系管理员";
    }

    //验证旧密码是否正确
    public Boolean VerifyOldPassword(String BorrowerId, String OldPassword) {
        String sSql1 = "SELECT COUNT(BorrowerId) as RowCount FROM Base_Borrower WHERE BorrowerId=? AND FindPassword=? ";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, BorrowerId, OldPassword);
        if (objects != null && objects.length > 0) {
            return true;
        }
        return false;
    }
}
