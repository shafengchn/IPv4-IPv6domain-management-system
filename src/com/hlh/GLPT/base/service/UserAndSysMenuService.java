package com.hlh.GLPT.base.service;

import com.hlh.GLPT.base.dao.UserAndSysMenuDao;
import com.hlh.GLPT.base.domain.UserAndSysMenu;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * User: 黄良辉
 * Date: 14-6-9
 * Time: 下午12:47
 */
@Service
public class UserAndSysMenuService {
    @Autowired
    private UserAndSysMenuDao userAndSysMenuDao;

    /*
   * 增加
   * */
    public String add(UserAndSysMenu userAndSysMenu) {
        return userAndSysMenuDao.add(userAndSysMenu);
    }

    /*
     * 彻底删除
     * */
    public String ThoroughDel(int ID) {
        return userAndSysMenuDao.ThoroughDel(ID);
    }

    /*
 * 彻底删除
 * */
    public String ThoroughByUserCodeDel(String UserCode) {
        return userAndSysMenuDao.ThoroughByUserCodeDel(UserCode);
    }

    /*
    * 删除
    * */
    public String del(int ID, Date ModifyDate, String ModifyUserId, String ModifyUserName) {
        return userAndSysMenuDao.del(ID, ModifyDate, ModifyUserId, ModifyUserName);
    }

    /*
    * 修改
    * */
    public String edit(UserAndSysMenu userAndSysMenu) {
        return userAndSysMenuDao.edit(userAndSysMenu);
    }

    /*
    * 按Id查询
    * */
    public UserAndSysMenu findById(int ID) {
        return userAndSysMenuDao.findById(ID);
    }

    //获取总行数
    public int GetRowCount() {
        return userAndSysMenuDao.GetRowCount();
    }

    /*
 * 查询
 * */
    public List<UserAndSysMenu> findAll() {
        return userAndSysMenuDao.findAll();
    }

    public String findAll(String ShowFieldName, String[] strings) {
        return userAndSysMenuDao.findAll(ShowFieldName, strings);
    }

    //分页
    public String findByPage(String findValue, int start, int number) {
        return userAndSysMenuDao.findByPage(findValue, start, number);
    }

    //获取总行数(带查询参数)
    public int GetRowCount(String findValue) {
        return userAndSysMenuDao.GetRowCount(findValue);
    }

    //分页(查询教师所属部门）
    public String findUserByPage(String UserCode, String findValue, int start, int number) {
        return userAndSysMenuDao.findUserByPage(UserCode, findValue, start, number);
    }

    //获取总行数(查询教师所属部门)(带查询参数)
    public int GetUserRowCount(String UserCode, String findValue) {
        return userAndSysMenuDao.GetUserRowCount(UserCode, findValue);
    }

    //记录是否重复
    public boolean RecordIsRepeat(String UserCode, String SysMenuCode) {
        return userAndSysMenuDao.RecordIsRepeat(UserCode, SysMenuCode);
    }

    //记录是否重复
    public boolean RecordIsRepeat(String UserCode, String SysMenuCode, int ID) {
        return userAndSysMenuDao.RecordIsRepeat(UserCode, SysMenuCode, ID);
    }

    //验证添加
    public String VerifyAdd(UserAndSysMenu userAndSysMenu) {
        return userAndSysMenuDao.VerifyAdd(userAndSysMenu);
    }

    //验证修改
    public String VerifyEdit(UserAndSysMenu userAndSysMenu) {
        return userAndSysMenuDao.VerifyEdit(userAndSysMenu);
    }

    //按用户代码获取系统功能(以,号间隔)
    public String UserCodeToSysMenu(String UserCode) {
        return userAndSysMenuDao.UserCodeToSysMenu(UserCode);
    }

    //按UserCode获取系统功能的选中情况
    public JSONArray GetUserAndSysMenuChecked(String UserCode){
        return userAndSysMenuDao.GetUserAndSysMenuChecked(UserCode);
    }

    //获取用户选中的系统功能
    public JSONArray GetUserSelectSysMenu(String UserCode) {
        return userAndSysMenuDao.GetUserSelectSysMenu(UserCode);
    }
}
