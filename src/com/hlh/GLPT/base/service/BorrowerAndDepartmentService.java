package com.hlh.GLPT.base.service;

import com.hlh.GLPT.base.dao.BorrowerAndDepartmentDao;
import com.hlh.GLPT.base.domain.BorrowerAndDepartment;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: 黄良辉
 * Date: 14-10-11
 * Time: 下午9:40
 */
@Service
public class BorrowerAndDepartmentService {
    @Autowired
    private BorrowerAndDepartmentDao borrowerAndDepartmentDao;

    /*
   * 增加
   * */
    public String add(BorrowerAndDepartment borrowerAndDepartment) {
        return borrowerAndDepartmentDao.add(borrowerAndDepartment);
    }

    /*
     * 彻底删除
     * */
    public String ThoroughDel(int ID) {
        return borrowerAndDepartmentDao.ThoroughDel(ID);
    }

    /*
     * 彻底删除
     * */
    public String ThoroughByBorrowerCodeDel(String BorrowerCode) {
        return borrowerAndDepartmentDao.ThoroughByBorrowerCodeDel(BorrowerCode);
    }

    /*
    * 修改
    * */
    public String edit(BorrowerAndDepartment borrowerAndDepartment) {
        return borrowerAndDepartmentDao.edit(borrowerAndDepartment);
    }

    /*
    * 按Id查询
    * */
    public BorrowerAndDepartment findById(int ID) {
        return borrowerAndDepartmentDao.findById(ID);
    }

    //获取总行数
    public int GetRowCount() {
        return borrowerAndDepartmentDao.GetRowCount();
    }

    /*
 * 查询
 * */
    public List<BorrowerAndDepartment> findAll() {
        return borrowerAndDepartmentDao.findAll();
    }

    public String findAll(String ShowFieldName, String[] strings) {
        return borrowerAndDepartmentDao.findAll(ShowFieldName, strings);
    }

    //分页
    public String findByPage(String findValue, int start, int number) {
        return borrowerAndDepartmentDao.findByPage(findValue, start, number);
    }

    //获取总行数(带查询参数)
    public int GetRowCount(String findValue) {
        return borrowerAndDepartmentDao.GetRowCount(findValue);
    }

    //分页(查询借用者所属部门）
    public String findBorrowerByPage(String BorrowerCode, String findValue, int start, int number) {
        return borrowerAndDepartmentDao.findBorrowerByPage(BorrowerCode, findValue, start, number);
    }

    //获取总行数(查询借用者所属部门)(带查询参数)
    public int GetBorrowerRowCount(String BorrowerCode, String findValue) {
        return borrowerAndDepartmentDao.GetBorrowerRowCount(BorrowerCode, findValue);
    }

    //验证添加
    public String VerifyAdd(BorrowerAndDepartment borrowerAndDepartment) {
        return borrowerAndDepartmentDao.VerifyAdd(borrowerAndDepartment);
    }

    //验证修改
    public String VerifyEdit(BorrowerAndDepartment borrowerAndDepartment) {
        return borrowerAndDepartmentDao.VerifyEdit(borrowerAndDepartment);
    }

    //按借用者代码获取所属部门(以,号间隔)
    public String BorrowerCodeToDepartment(String BorrowerCode) {
        return borrowerAndDepartmentDao.BorrowerCodeToDepartment(BorrowerCode);
    }

    //按BorrowerCode获取所属部门的选中情况
    public JSONArray GetBorrowerAndDepartmentChecked(String BorrowerCode) {
        return borrowerAndDepartmentDao.GetBorrowerAndDepartmentChecked(BorrowerCode);
    }
}
