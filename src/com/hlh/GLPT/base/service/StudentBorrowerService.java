package com.hlh.GLPT.base.service;

import com.hlh.GLPT.base.dao.StudentBorrowerDao;
import com.hlh.GLPT.base.domain.StudentBorrower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * User: 黄良辉
 * Date: 14-10-11
 * Time: 下午8:14
 */
@Service
public class StudentBorrowerService {
    @Autowired
    private StudentBorrowerDao studentBorrowerDao;

    /*
  * 增加
  * */
    public String add(StudentBorrower studentBorrower) {
        return studentBorrowerDao.add(studentBorrower);
    }

    /*
     * 彻底删除
     * */
    public String ThoroughDel(String StudentBorrowerId) {
        return studentBorrowerDao.ThoroughDel(StudentBorrowerId);
    }

    /*
    * 删除
    * */
    public String del(String StudentBorrowerId, Date ModifyDate, String ModifyUserCode, String ModifyUserName) {
        return studentBorrowerDao.del(StudentBorrowerId, ModifyDate, ModifyUserCode, ModifyUserName);
    }

    /*
    * 修改
    * */
    public String edit(StudentBorrower studentBorrower) {
        return studentBorrowerDao.edit(studentBorrower);
    }

    /*
   * 按Id查询
   * */
    public StudentBorrower findById(String StudentBorrowerId) {
        return studentBorrowerDao.findById(StudentBorrowerId);
    }


    /*
    * 按StudentBorrowerCode查询
    * */
    public StudentBorrower findByCode(String StudentBorrowerCode) {
        return studentBorrowerDao.findByCode(StudentBorrowerCode);
    }

    //获取总行数
    public int GetRowCount() {
        return studentBorrowerDao.GetRowCount();
    }

    //获取总行数
    public int GetRowCountEx() {
        return studentBorrowerDao.GetRowCountEx();
    }


    /*
 * 查询
 * */
    public List<StudentBorrower> findAll() {
        return studentBorrowerDao.findAll();
    }
    /*
* 查询
* */
    public List<StudentBorrower> findStudentBorrower(String filedName,String findValue) {
        return studentBorrowerDao.findStudentBorrower(filedName, findValue);
    }


    /*
* 查询(有效的教师信息)
* */
    public List<StudentBorrower> findAllEx() {
        return studentBorrowerDao.findAllEx();
    }


    public String findAll(String ShowFieldName, String[] strings) {
        return studentBorrowerDao.findAll(ShowFieldName, strings);
    }

    //分页 模糊查找
    public String findByPage(String findValue, boolean enabled, int start, int number) {
        return studentBorrowerDao.findByPage(findValue, enabled, start, number);
    }

    //获取总行数 模糊查找
    public int GetRowCount(String findValue, boolean enabled) {
        return studentBorrowerDao.GetRowCount(findValue, enabled);
    }


    //分页 精确查找
    public String findByPage(String fieldName, String findValue, boolean enabled, int start, int number) {
        return studentBorrowerDao.findByPage(fieldName, findValue, enabled, start, number);
    }

    //获取总行数 精确查找
    public int GetRowCount(String fieldName, String findValue, boolean enabled) {
        return studentBorrowerDao.GetRowCount(fieldName, findValue, enabled);
    }

    //验证添加
    public String VerifyAdd(StudentBorrower studentBorrower) {
        return studentBorrowerDao.VerifyAdd(studentBorrower);
    }

    //验证修改
    public String VerifyEdit(StudentBorrower studentBorrower) {
        return studentBorrowerDao.VerifyEdit(studentBorrower);
    }

    //教师代码转教师名称
    public String CodeToFullName(String code) {
        return studentBorrowerDao.CodeToFullName(code);
    }

    //教师导入 fileName 源文件
    public String StudentBorrowerImport(String fileName, Date CreateDate, String CreateUserCode, String CreateUserName) {
        return studentBorrowerDao.StudentBorrowerImport(fileName, CreateDate, CreateUserCode, CreateUserName);
    }

    //重置密码
    public String resetPassword(String StudentBorrowerId, String password, Date ChangePasswordDate, Date ModifyDate, String ModifyUserCode, String ModifyUserName) {
         return studentBorrowerDao.resetPassword(StudentBorrowerId, password, ChangePasswordDate, ModifyDate, ModifyUserCode, ModifyUserName);
    }

    //验证旧密码是否正确
    public Boolean VerifyOldPassword(String StudentBorrowerId, String OldPassword) {
        return studentBorrowerDao.VerifyOldPassword(StudentBorrowerId, OldPassword);
    }

}
