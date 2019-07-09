package com.hlh.GLPT.base.service;

import com.hlh.GLPT.base.dao.BorrowerDao;
import com.hlh.GLPT.base.domain.Borrower;
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
public class BorrowerService {
    @Autowired
    private BorrowerDao borrowerDao;

    /*
  * 增加
  * */
    public String add(Borrower borrower) {
        return borrowerDao.add(borrower);
    }

    /*
     * 彻底删除
     * */
    public String ThoroughDel(String BorrowerId) {
        return borrowerDao.ThoroughDel(BorrowerId);
    }

    /*
    * 删除
    * */
    public String del(String BorrowerId, Date ModifyDate, String ModifyUserCode, String ModifyUserName) {
        return borrowerDao.del(BorrowerId, ModifyDate, ModifyUserCode, ModifyUserName);
    }

    /*
    * 修改
    * */
    public String edit(Borrower borrower) {
        return borrowerDao.edit(borrower);
    }

    /*
   * 按Id查询
   * */
    public Borrower findById(String BorrowerId) {
        return borrowerDao.findById(BorrowerId);
    }


    /*
    * 按BorrowerCode查询
    * */
    public Borrower findByCode(String BorrowerCode) {
        return borrowerDao.findByCode(BorrowerCode);
    }

    //获取总行数
    public int GetRowCount() {
        return borrowerDao.GetRowCount();
    }

    //获取总行数
    public int GetRowCountEx() {
        return borrowerDao.GetRowCountEx();
    }


    /*
 * 查询
 * */
    public List<Borrower> findAll() {
        return borrowerDao.findAll();
    }


    /*
* 查询
* */
    public List<Borrower> findBorrower(String filedName,String findValue) {
        return borrowerDao.findBorrower(filedName, findValue);
    }


    /*
* 查询(有效的教师信息)
* */
    public List<Borrower> findAllEx() {
        return borrowerDao.findAllEx();
    }


    public String findAll(String ShowFieldName, String[] strings) {
        return borrowerDao.findAll(ShowFieldName, strings);
    }

    /**
     * 查询有效的教师信息
     * @param ShowFieldName
     * @param strings
     * @return
     */
    public String findAllEx(String ShowFieldName, String[] strings) {
        return borrowerDao.findAllEx(ShowFieldName, strings);
    }

    //分页 模糊查找
    public String findByPage(String findValue, boolean enabled, int start, int number) {
        return borrowerDao.findByPage(findValue, enabled, start, number);
    }

    //获取总行数 模糊查找
    public int GetRowCount(String findValue, boolean enabled) {
        return borrowerDao.GetRowCount(findValue, enabled);
    }


    //分页 精确查找
    public String findByPage(String fieldName, String findValue, boolean enabled, int start, int number) {
        return borrowerDao.findByPage(fieldName, findValue, enabled, start, number);
    }

    //获取总行数 精确查找
    public int GetRowCount(String fieldName, String findValue, boolean enabled) {
        return borrowerDao.GetRowCount(fieldName, findValue, enabled);
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
    return borrowerDao.findByPage(Enabled, DeleteMark, fField, fValue, start, number);
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
         return borrowerDao.GetRowCount(Enabled, DeleteMark, fField, fValue);
    }


    //验证添加
    public String VerifyAdd(Borrower borrower) {
        return borrowerDao.VerifyAdd(borrower);
    }

    //验证修改
    public String VerifyEdit(Borrower borrower) {
        return borrowerDao.VerifyEdit(borrower);
    }

    //教师代码转教师名称
    public String CodeToFullName(String code) {
        return borrowerDao.CodeToFullName(code);
    }

    //教师导入 fileName 源文件
    public String BorrowerImport(String fileName, Date CreateDate, String CreateUserCode, String CreateUserName) {
        return borrowerDao.BorrowerImport(fileName, CreateDate, CreateUserCode, CreateUserName);
    }

    //重置密码
    public String resetPassword(String BorrowerId, String password, Date ChangePasswordDate, Date ModifyDate, String ModifyUserCode, String ModifyUserName) {
         return borrowerDao.resetPassword(BorrowerId, password, ChangePasswordDate, ModifyDate, ModifyUserCode, ModifyUserName);
    }

    //验证旧密码是否正确
    public Boolean VerifyOldPassword(String BorrowerId, String OldPassword) {
        return borrowerDao.VerifyOldPassword(BorrowerId, OldPassword);
    }

}
