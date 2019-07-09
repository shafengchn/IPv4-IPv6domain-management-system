package com.hlh.GLPT.core.service;

import com.hlh.GLPT.core.dao.RoleDao;
import com.hlh.GLPT.core.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * User: 黄良辉
 * Date: 14-7-20
 * Time: 上午11:19
 */
@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;

    /*
  * 增加
  * */
    public String add(Role role) {
        return roleDao.add(role);
    }

    /*
     * 彻底删除
     * */
    public String ThoroughDel(String RoleId) {
        return roleDao.ThoroughDel(RoleId);
    }

    /*
    * 删除
    * */
    public String del(String RoleId, Date ModifyDate, String ModifyUserCode, String ModifyUserName) {
        return roleDao.del(RoleId, ModifyDate, ModifyUserCode, ModifyUserName);
    }

    /*
    * 修改
    * */
    public String edit(Role role) {
        return roleDao.edit(role);
    }

    /*
    * 按Id查询
    * */
    public Role findById(String RoleId) {
        return roleDao.findById(RoleId);
    }

    /*
* 按Code查询
* */
    public Role findByCode(String Code) {
        return roleDao.findByCode(Code);
    }

    //获取总行数
    public int GetRowCount() {
        return roleDao.GetRowCount();
    }

    /*
 * 查询
 * */
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    /*
* 按字段获取数据记录
* */
    public List findByField(String ShowFieldName) {
        return roleDao.findByField(ShowFieldName);
    }

    /*
 * 按字段获取数据记录
 * */
    public List findByField(String ShowFieldName,String Category) {
        return roleDao.findByField(ShowFieldName, Category);
    }

    public String findAll(String ShowFieldName, String[] strings) {
        return roleDao.findAll(ShowFieldName, strings);
    }

    public String findByData() {
        return roleDao.findByData();
    }

    /*
    * 查询精简数据　供下拉列表框使用
    * */
    public String findBySimpleData() {
        return roleDao.findBySimpleData();
    }

    //分页
    public String findByPage(String findValue, int start, int number) {
        return roleDao.findByPage(findValue, start, number);
    }

    //获取总行数(带查询参数)
    public int GetRowCount(String findValue) {
        return roleDao.GetRowCount(findValue);
    }


    //验证添加
    public String VerifyAdd(Role role) {
        return roleDao.VerifyAdd(role);
    }

    //验证修改
    public String VerifyEdit(Role role) {
        return roleDao.VerifyEdit(role);
    }

    //代码转名称
    public String CodeToFullName(String Code) {
        return roleDao.CodeToFullName(Code);
    }
}
