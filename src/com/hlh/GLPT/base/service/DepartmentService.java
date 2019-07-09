package com.hlh.GLPT.base.service;

import com.hlh.GLPT.base.dao.DepartmentDao;
import com.hlh.GLPT.base.domain.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * User: 黄良辉
 * Date: 14-6-8
 * Time: 上午10:54
 */
@Service
public class DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;

    /*
    * 增加
    * */
    public String add(Department department) {
        return departmentDao.add(department);
    }

    /*
     * 彻底删除
     * */
    public String ThoroughDel(String DepartmentId) {
        return departmentDao.ThoroughDel(DepartmentId);
    }

    /*
    * 删除
    * */
    public String del(String DepartmentId, Date ModifyDate, String ModifyUserId, String ModifyUserName) {
        return departmentDao.del(DepartmentId, ModifyDate, ModifyUserId, ModifyUserName);
    }

    /*
    * 修改
    * */
    public String edit(Department department) {
        return departmentDao.edit(department);
    }

    /*
    * 按Id查询
    * */
    public Department findById(String DepartmentId) {
        return departmentDao.findById(DepartmentId);
    }

    //获取总行数
    public int GetRowCount() {
        return departmentDao.GetRowCount();
    }

    /*
 * 查询
 * */
    public List<Department> findAll() {
        return departmentDao.findAll();
    }

    /*
* 查询
* */
    public List<Department> findAllEx() {
        return departmentDao.findAllEx();
    }

    public String findAll(String ShowFieldName, String[] strings) {
        return departmentDao.findAll(ShowFieldName, strings);
    }

    public String findAllEx(String ShowFieldName, String[] strings) {
        return departmentDao.findAllEx(ShowFieldName, strings);
    }

    //按部门类型获取部门数据
    public String findAllEx(String ShowFieldName, String[] strings,String BMLXCode) {
return departmentDao.findAllEx(ShowFieldName, strings, BMLXCode);
    }


    //分页
    public String findByPage(String findValue,boolean enabled, int start, int number) {
        return departmentDao.findByPage(findValue,enabled, start, number);
    }

    //获取总行数(带查询参数)
    public int GetRowCount(String findValue,boolean enabled) {
        return departmentDao.GetRowCount(findValue,enabled);
    }

    //验证添加
    public String VerifyAdd(Department department) {
        return departmentDao.VerifyAdd(department);
    }

    //验证修改
    public String VerifyEdit(Department department) {
        return departmentDao.VerifyEdit(department);
    }

    //部门代码转部门名称
    public String DepartmentCodeToDepartmentName(String DepartmentCode) {
        return departmentDao.DepartmentCodeToDepartmentName(DepartmentCode);
    }


    //部门名称转部门代码
    public String DepartmentNameToDepartmentCode(String DepartmentName){
        return departmentDao.DepartmentNameToDepartmentCode(DepartmentName);
    }
}
