package com.hlh.GLPT.core.service;

import com.hlh.GLPT.core.dao.UserDao;
import com.hlh.GLPT.core.domain.User;
import com.hlh.KZ.domain.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * User: 黄良辉
 * Date: 14-3-12
 * Time: 上午10:40
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    /*
    * 增加
    * */
    public String add(User user) {
        return userDao.add(user);
    }

    /*
    * 彻底删除
    * */
    public String ThoroughDel(String UserId) {
        return userDao.ThoroughDel(UserId);
    }

    /*
    * 删除
    * */
    public String del(String UserId, Date ModifyDate, String ModifyUserCode, String ModifyUserName) {
        return userDao.del(UserId, ModifyDate, ModifyUserCode, ModifyUserName);
    }

    /*
    * 修改
    * */
    public String edit(User user) {
        return userDao.edit(user);
    }

    /*
* 修改-用户登录修改自已的用户信息
* */
    public String PersonalEdit(User user) {
        return userDao.PersonalEdit(user);
    }

    /*
* 按Id查询
* */
    public User findById(String UserId) {
        return userDao.findById(UserId);
    }

    /*
* 按Code查询
* */
    public User findByCode(String UserCode) {
        return userDao.findByCode(UserCode);
    }

    /*
    * 查询
    * */
    public List<User> findAll() {
        return userDao.findAll();
    }

    public String findAll(String ShowFieldName, String[] strings) {
        return userDao.findAll(ShowFieldName, strings);
    }

    //获取总行数
    public int GetRowCount() {
        return userDao.GetRowCount();
    }

    //分页 模糊查找
    public String findByPage(String findValue, boolean enabled, int start, int number) {
        return userDao.findByPage(findValue, enabled, start, number);
    }

    //获取总行数 模糊查找
    public int GetRowCount(String findValue, boolean enabled) {
        return userDao.GetRowCount(findValue, enabled);

    }

    //分页 精确查找
    public String findByPage(String fieldName, String findValue, boolean enabled, int start, int number) {
        return userDao.findByPage(fieldName, findValue, enabled, start, number);
    }

    //获取总行数 精确查找
    public int GetRowCount(String fieldName, String findValue, boolean enabled) {
        return userDao.GetRowCount(fieldName, findValue, enabled);

    }

    //验证添加
    public String VerifyAdd(User user) {
        return userDao.VerifyAdd(user);
    }

    //验证修改
    public String VerifyEdit(User user) {
        return userDao.VerifyEdit(user);
    }

    //验证用户名和密码是否正确
    public boolean getMatchCount(String account, String password) {
        return userDao.getMatchCount(account, password);
    }

    //更新登录信息
    public int updateLoginInfo(User user) {
        return userDao.updateLoginInfo(user);
    }

    //按用户名查询记录
    public User findUserByUserName(final String account) {
        return userDao.findUserByUserName(account);
    }

    //重置密码
    public String resetPassword(String UserId, String password, Date ChangePasswordDate, Date ModifyDate, String ModifyUserCode, String ModifyUserName) {
        return userDao.resetPassword(UserId, password, ChangePasswordDate, ModifyDate, ModifyUserCode, ModifyUserName);
    }

    //验证旧密码是否正确
    public Boolean VerifyOldPassword(String UserId, String OldPassword) {
        return userDao.VerifyOldPassword(UserId, OldPassword);
    }

    //获取的所有可用角色并获取已选中的角色
    public List FindUserAllotRole(String UserCode) {
        return userDao.FindUserAllotRole(UserCode);
    }

    //获取的所有可用角色并获取已选中的角色
    public List FindUserAllotRole(String UserCode,String Category) {
        return userDao.FindUserAllotRole(UserCode, Category);
    }

    //用户权限验证
    public Boolean UserAuthorityVerify(String UserCode, String ResourcesCode) {
        return userDao.UserAuthorityVerify(UserCode, ResourcesCode);
    }

    //用户获取平台的模块数据
    public List<SysMenu> UserGetChildMenuData(String UserCode, String PTCode) {
        return userDao.UserGetChildMenuData(UserCode, PTCode);
    }

    //用户获取平台的模块数据
    public List<SysMenu> UserGetChildMenuDataByParentID(String UserCode, String ParentId) {
        return userDao.UserGetChildMenuDataByParentID(UserCode, ParentId);
    }

    //按用户姓名转用户代码
    public String RealNameToCode(String RealName) {
        return userDao.RealNameToCode(RealName);
    }
}
