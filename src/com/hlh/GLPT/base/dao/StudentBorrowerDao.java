package com.hlh.GLPT.base.dao;

import com.hlh.GLPT.base.domain.StudentBorrower;
import com.hlh.util.DbUtilsTemplate;
import com.hlh.util.StrUtil;
import com.hlh.util.jsonUtil;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
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
public class StudentBorrowerDao {
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;

    /*
    * 增加
    * */
    public String add(StudentBorrower studentBorrower) {
        String sqlStr = "INSERT INTO base_StudentBorrower (StudentBorrowerId,UserCode,Code,FullName,Alias" +
                ",Gender,FindPassword,IDCard,Email,Mobile" +
                ",Telephone,OICQ,Age,Birthday,HomeAddress" +
                ",HomePhone,PhotoImg,Description,Enabled,SortCode" +
                ",CreateDate,CreateUserCode,CreateUserName)" +
                " VALUES(?,?,?,?,?" +
                ",?,?,?,?,?" +
                ",?,?,?,?,?" +
                ",?,?,?,?,?" +
                ",?,?,?)";
        if ("".equals(studentBorrower.getAge())) {
            studentBorrower.setAge("0");
        }
        if ("".equals(studentBorrower.getBirthday())) {
            studentBorrower.setBirthday(null);
        }
        Object[] objects = new Object[]{studentBorrower.getStudentBorrowerId(), studentBorrower.getUserCode(), studentBorrower.getCode(), studentBorrower.getFullName(), studentBorrower.getAlias()
                , studentBorrower.getGender(),studentBorrower.getFindPassword(), studentBorrower.getIDCard(), studentBorrower.getEmail(), studentBorrower.getMobile()
                , studentBorrower.getTelephone(), studentBorrower.getOICQ(), Integer.parseInt(studentBorrower.getAge()), studentBorrower.getBirthday(), studentBorrower.getHomeAddress()
                , studentBorrower.getHomePhone(),studentBorrower.getPhotoImg() , studentBorrower.getDescription(), studentBorrower.isEnabled(), studentBorrower.getSortCode(), studentBorrower.getCreateDate()
                , studentBorrower.getCreateUserCode() , studentBorrower.getCreateUserName()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "添加操作失败,请联系管理员";
    }

    /*
     * 彻底删除
     * */
    public String ThoroughDel(String StudentBorrowerId) {
        StudentBorrower studentBorrower1 = findById(StudentBorrowerId);
        if ("1000".equals(studentBorrower1.getCode())) {
            return "当前记录不能删除";
        } else {
            String sqlStr = "DELETE FROM base_StudentBorrower  WHERE StudentBorrowerId=?";
            long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{StudentBorrowerId});
            if (RValue1 > 0) {
                return "OK";
            }
            return "彻底删除操作失败,请联系管理员";
        }
    }

    /*
    * 删除
    * */
    public String del(String StudentBorrowerId, Date ModifyDate, String ModifyUserCode, String ModifyUserName) {
        String sqlStr = "UPDATE Base_StudentBorrower SET DeleteMark=?,ModifyDate=?,ModifyUserCode=?,ModifyUserName=? WHERE StudentBorrowerId=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{true, ModifyDate, ModifyUserCode, ModifyUserName, StudentBorrowerId});
        if (RValue1 > 0) {
            return "OK";
        }
        return "删除操作失败,请联系管理员";
    }

    /*
    * 修改
    * */
    public String edit(StudentBorrower studentBorrower) {
        String sqlStr = "UPDATE Base_StudentBorrower SET UserCode=?,Code=?,FullName=?,Alias=?,Gender=?" +
                ",FindPassword=?,IDCard=?,Email=?,Mobile=?,Telephone=?,OICQ=?" +
                ",Age=?,Birthday=?,HomeAddress=?,HomePhone=?,PhotoImg=?,Description=?" +
                ",Enabled=?,SortCode=?,ModifyDate=?,ModifyUserCode=?,ModifyUserName=? WHERE StudentBorrowerId=?";
        if ("".equals(studentBorrower.getAge())) {
            studentBorrower.setAge("0");
        }
        if ("".equals(studentBorrower.getBirthday())) {
            studentBorrower.setBirthday(null);
        }
        Object[] objects = new Object[]{studentBorrower.getUserCode(), studentBorrower.getCode(), studentBorrower.getFullName(), studentBorrower.getAlias(), studentBorrower.getGender()
                ,studentBorrower.getFindPassword(), studentBorrower.getIDCard(), studentBorrower.getEmail(), studentBorrower.getMobile(), studentBorrower.getTelephone(), studentBorrower.getOICQ()
                , Integer.parseInt(studentBorrower.getAge()), studentBorrower.getBirthday(), studentBorrower.getHomeAddress(), studentBorrower.getHomePhone(),studentBorrower.getPhotoImg(), studentBorrower.getDescription()
                , studentBorrower.isEnabled(), studentBorrower.getSortCode(), studentBorrower.getModifyDate(), studentBorrower.getModifyUserCode(), studentBorrower.getModifyUserName()
                , studentBorrower.getStudentBorrowerId()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "修改操作失败,请联系管理员";
    }

    /*
   * 按Id查询
   * */
    public StudentBorrower findById(String StudentBorrowerId) {
        String sqlStr = "SELECT * FROM Base_StudentBorrower WHERE StudentBorrowerId=?";
        return dbUtilsTemplate.findFirst(StudentBorrower.class, sqlStr, StudentBorrowerId);
    }


    /*
    * 按StudentBorrowerCode查询
    * */
    public StudentBorrower findByCode(String StudentBorrowerCode) {
        String sqlStr = "SELECT * FROM Base_StudentBorrower WHERE code=?";
        return dbUtilsTemplate.findFirst(StudentBorrower.class, sqlStr, StudentBorrowerCode);
    }

    //获取总行数
    public int GetRowCount() {
        String sSql1 = "SELECT COUNT(StudentBorrowerId) as RowCount FROM Base_StudentBorrower";
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
        String sSql1 = "SELECT COUNT(StudentBorrowerId) as RowCount FROM Base_StudentBorrower WHERE Enabled=true AND DeleteMark=false";
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
    public List<StudentBorrower> findAll() {
        String sqlStr = "SELECT * FROM Base_StudentBorrower ORDER BY SortCode";
        return dbUtilsTemplate.find(StudentBorrower.class, sqlStr);
    }

    /*
* 查询
* */
    public List<StudentBorrower> findStudentBorrower(String filedName,String findValue) {
        String sqlStr = "SELECT * FROM Base_StudentBorrower WHERE "+filedName+"='"+findValue+"'";
        return dbUtilsTemplate.find(StudentBorrower.class, sqlStr);
    }

    /*
* 查询(有效的教师信息)
* */
    public List<StudentBorrower> findAllEx() {
        String sqlStr = "SELECT * FROM Base_StudentBorrower WHERE Enabled=true AND DeleteMark=false ORDER BY SortCode";
        return dbUtilsTemplate.find(StudentBorrower.class, sqlStr);
    }


    public String findAll(String ShowFieldName, String[] strings) {
        String sqlStr = "SELECT " + ShowFieldName + " FROM Base_StudentBorrower ORDER BY SortCode";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sqlStr);
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings);
        return tmpStr1;
    }

    //分页 模糊查找
    public String findByPage(String findValue, boolean enabled, int start, int number) {
        String sSql1 = "";
        if (enabled) { //只显示有效
            sSql1 = "SELECT StudentBorrowerId,UserCode,Code,FullName,Alias,Gender,IDCard,Email,Mobile,Telephone,OICQ,Birthday,Description,Enabled,SortCode,DeleteMark"
                    + " FROM Base_StudentBorrower"
                    + " WHERE DeleteMark=false AND Enabled=true AND (Code LIKE ? OR FullName LIKE ? OR Gender LIKE ? OR IDCard LIKE ? OR Email LIKE ? OR Mobile LIKE ? OR Telephone LIKE ? OR OICQ LIKE ? OR Birthday LIKE ?)"
                    + " ORDER BY SortCode LIMIT ?,?";
        } else {
            sSql1 = "SELECT StudentBorrowerId,UserCode,Code,FullName,Alias,Gender,IDCard,Email,Mobile,Telephone,OICQ,Birthday,Description,Enabled,SortCode,DeleteMark"
                    + " FROM Base_StudentBorrower"
                    + " WHERE DeleteMark=false AND (Code LIKE ? OR FullName LIKE ? OR Gender LIKE ? OR IDCard LIKE ? OR Email LIKE ? OR Mobile LIKE ? OR Telephone LIKE ? OR OICQ LIKE ? OR Birthday LIKE ?)"
                    + " ORDER BY SortCode LIMIT ?,?";
        }
        List tmpList1 = dbUtilsTemplate.executeQueryList(sSql1, "%" + findValue + "%", "%" + findValue + "%", "%" + findValue + "%"
                , "%" + findValue + "%", "%" + findValue + "%", "%" + findValue + "%"
                , "%" + findValue + "%", "%" + findValue + "%", "%" + findValue + "%"
                , start, number);
        String[] strings1 = new String[]{"StudentBorrowerId", "UserCode", "Code", "FullName", "Alias", "Gender", "IDCard", "Email", "Mobile", "Telephone", "OICQ", "Birthday", "Description", "Enabled", "SortCode", "DeleteMark"};
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    //获取总行数 模糊查找
    public int GetRowCount(String findValue, boolean enabled) {
        String sSql1 = "";
        if (enabled) {
            sSql1 = "SELECT COUNT(StudentBorrowerId) as RowCount FROM Base_StudentBorrower"
                    + " WHERE DeleteMark=false AND Enabled=true AND (Code LIKE ? OR FullName LIKE ? OR Gender LIKE ? OR IDCard LIKE ? OR Email LIKE ? OR Mobile LIKE ? OR Telephone LIKE ? OR OICQ LIKE ? OR Birthday LIKE ?)";

        } else {
            sSql1 = "SELECT COUNT(StudentBorrowerId) as RowCount FROM Base_StudentBorrower"
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
            sSql1 = "SELECT StudentBorrowerId,UserCode,Code,FullName,Alias,Gender,IDCard,Email,Mobile,Telephone,OICQ,Birthday,Description,Enabled,SortCode,DeleteMark"
                    + " FROM Base_StudentBorrower"
                    + " WHERE DeleteMark=false AND Enabled=true AND " + fieldName + "='" + findValue + "'"
                    + " ORDER BY SortCode LIMIT ?,?";
        } else {
            sSql1 = "SELECT StudentBorrowerId,UserCode,Code,FullName,Alias,Gender,IDCard,Email,Mobile,Telephone,OICQ,Birthday,Description,Enabled,SortCode,DeleteMark"
                    + " FROM Base_StudentBorrower"
                    + " WHERE DeleteMark=false AND " + fieldName + "='" + findValue + "'"
                    + " ORDER BY SortCode LIMIT ?,?";
        }
        List tmpList1 = dbUtilsTemplate.executeQueryList(sSql1, start, number);
        String[] strings1 = new String[]{"StudentBorrowerId", "UserCode", "Code", "FullName", "Alias", "Gender", "IDCard", "Email", "Mobile", "Telephone", "OICQ", "Birthday", "Description", "Enabled", "SortCode", "DeleteMark"};
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    //获取总行数 精确查找
    public int GetRowCount(String fieldName, String findValue, boolean enabled) {
        String sSql1 = "";
        if (enabled) { //只显示有效
            sSql1 = "SELECT COUNT(StudentBorrowerId) as RowCount FROM Base_StudentBorrower"
                    + " WHERE DeleteMark=false AND Enabled=true AND " + fieldName + "='" + findValue + "'";
        } else {
            sSql1 = "SELECT COUNT(StudentBorrowerId) as RowCount FROM Base_StudentBorrower"
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

    public boolean StudentBorrowerIdIsRepeat(String StudentBorrowerId) {
        if (!"".equals(StudentBorrowerId)) {
            String sSql1 = "SELECT StudentBorrowerId FROM Base_StudentBorrower WHERE StudentBorrowerId=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, StudentBorrowerId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean CodeIsRepeat(String code) {
        if (!"".equals(code)) {
            String sSql1 = "SELECT StudentBorrowerId FROM Base_StudentBorrower WHERE Code=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, code);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean CodeIsRepeat(String code, String StudentBorrowerId) {
        if (!"".equals(code)) {
            String sSql1 = "SELECT StudentBorrowerId FROM Base_StudentBorrower WHERE Code=? AND StudentBorrowerId<>?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, code, StudentBorrowerId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean FullNameIsRepeat(String fullName) {
        if (!"".equals(fullName)) {
            String sSql1 = "SELECT StudentBorrowerId FROM Base_StudentBorrower WHERE FullName=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, fullName);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean FullNameIsRepeat(String fullName, String StudentBorrowerId) {
        if (!"".equals(fullName)) {
            String sSql1 = "SELECT StudentBorrowerId FROM Base_StudentBorrower WHERE FullName=? AND StudentBorrowerId<>?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, fullName, StudentBorrowerId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean MobileIsRepeat(String Mobile) {
        if (!"".equals(Mobile)) {
            String sSql1 = "SELECT StudentBorrowerId FROM Base_StudentBorrower WHERE Mobile=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, Mobile);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean MobileIsRepeat(String Mobile, String StudentBorrowerId) {
        if (!"".equals(Mobile)) {
            String sSql1 = "SELECT StudentBorrowerId FROM Base_StudentBorrower WHERE FullName=? AND StudentBorrowerId<>?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, Mobile, StudentBorrowerId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    //验证添加
    public String VerifyAdd(StudentBorrower studentBorrower) {
        String msg = "";
        if ("".equals(studentBorrower.getStudentBorrowerId()) || studentBorrower.getStudentBorrowerId() == null) {
            msg = "主键不能为空";
        } else if ("".equals(studentBorrower.getCode()) || studentBorrower.getCode() == null) {
            msg = "学号不能为空";
        } else if ("".equals(studentBorrower.getFullName()) || studentBorrower.getFullName() == null) {
            msg = "姓名不能为空";
        } else if ("".equals(studentBorrower.getMobile()) || studentBorrower.getMobile() == null) {
            msg = "手机号码不能为空";
        } else if (StudentBorrowerIdIsRepeat(studentBorrower.getStudentBorrowerId())) {
            msg = "主键已经存在";
        } else if (CodeIsRepeat(studentBorrower.getCode())) {
            msg = "学号已经存在";
        } else if (MobileIsRepeat(studentBorrower.getMobile())) {
            msg = "手机号码已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }

    //验证修改
    public String VerifyEdit(StudentBorrower studentBorrower) {
        String msg = "";
        if ("".equals(studentBorrower.getStudentBorrowerId()) || studentBorrower.getStudentBorrowerId() == null) {
            msg = "请选择要操作的记录";
        } else if ("".equals(studentBorrower.getCode()) || studentBorrower.getCode() == null) {
            msg = "学号不能为空";
        } else if ("".equals(studentBorrower.getFullName()) || studentBorrower.getFullName() == null) {
            msg = "姓名不能为空";
        } else if ("".equals(studentBorrower.getMobile()) || studentBorrower.getMobile() == null) {
            msg = "手机号码不能为空";
        } else if (CodeIsRepeat(studentBorrower.getCode(), studentBorrower.getStudentBorrowerId())) {
            msg = "学号已经存在";
        } else if (MobileIsRepeat(studentBorrower.getMobile(), studentBorrower.getStudentBorrowerId())) {
            msg = "手机号码已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }

    //教师代码转教师名称
    public String CodeToFullName(String code) {
        if (!"".equals(code)) {
            String sSql1 = "SELECT FullName FROM Base_StudentBorrower WHERE Code=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, code);
            if (objects != null) {
                return StrUtil.toStr(objects[0]);
            }
        }
        return "";
    }


    //教师导入 fileName 源文件
    public String StudentBorrowerImport(String fileName, Date CreateDate, String CreateUserCode, String CreateUserName) {
        int RowCount = 0;
        int YesCount = 0;
        int ErrCount = 0;
        try {
            Workbook workbook = Workbook.getWorkbook(new File(fileName));
            Sheet sheet = workbook.getSheet(0);
            for (int i1 = 1; i1 < sheet.getRows(); i1++) {
                RowCount++;
                Cell cell0 = sheet.getCell(0, i1); //序号
                Cell cell1 = sheet.getCell(1, i1); //学号
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
                    StudentBorrower studentBorrower1 = new StudentBorrower();
                    studentBorrower1.setStudentBorrowerId(StrUtil.GetUUID());
                    studentBorrower1.setCode(cell1.getContents());
                    studentBorrower1.setFullName(cell2.getContents());
                    studentBorrower1.setAlias(cell3.getContents());
                    studentBorrower1.setGender(cell4.getContents());
                    studentBorrower1.setAge(cell5.getContents());
                    studentBorrower1.setIDCard(cell6.getContents());
                    studentBorrower1.setEmail(cell7.getContents());
                    studentBorrower1.setOICQ(cell8.getContents());
                    studentBorrower1.setMobile(cell9.getContents());
                    studentBorrower1.setTelephone(cell10.getContents());
                    studentBorrower1.setBirthday(cell11.getContents());
                    studentBorrower1.setHomeAddress(cell12.getContents());
                    studentBorrower1.setHomePhone(cell13.getContents());
                    studentBorrower1.setDescription(cell14.getContents());
                    studentBorrower1.setEnabled(true);
                    studentBorrower1.setSortCode(GetRowCount() + 1);
                    studentBorrower1.setCreateDate(CreateDate);
                    studentBorrower1.setCreateUserCode(CreateUserCode);
                    studentBorrower1.setCreateUserName(CreateUserName);
                    String msg = VerifyAdd(studentBorrower1);
                    if ("OK".equals(msg)) {
                        String RValue = add(studentBorrower1);
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
    public String resetPassword(String StudentBorrowerId, String password, Date ChangePasswordDate, Date ModifyDate, String ModifyUserCode, String ModifyUserName) {
        String sqlStr = "UPDATE Base_StudentBorrower SET FindPassword=?,ChangePasswordDate=?,ModifyDate=?" +
                ",ModifyUserCode=?,ModifyUserName=? WHERE StudentBorrowerId=?";
        Object[] objects = new Object[]{password, ChangePasswordDate, ModifyDate, ModifyUserCode, ModifyUserName, StudentBorrowerId};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "重置密码操作失败,请联系管理员";
    }

    //验证旧密码是否正确
    public Boolean VerifyOldPassword(String StudentBorrowerId, String OldPassword) {
        String sSql1 = "SELECT COUNT(StudentBorrowerId) as RowCount FROM Base_StudentBorrower WHERE StudentBorrowerId=? AND FindPassword=? ";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, StudentBorrowerId, OldPassword);
        if (objects != null && objects.length > 0) {
            return true;
        }
        return false;
    }
}
