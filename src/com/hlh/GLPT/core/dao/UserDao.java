package com.hlh.GLPT.core.dao;

import com.hlh.GLPT.core.domain.User;
import com.hlh.GLPT.core.domain.UserRole;
import com.hlh.GLPT.core.service.RoleAuthorityService;
import com.hlh.GLPT.core.service.RoleService;
import com.hlh.GLPT.core.service.UserAuthorityService;
import com.hlh.GLPT.core.service.UserRoleService;
import com.hlh.KZ.domain.SysMenu;
import com.hlh.KZ.service.SysMenuService;
import com.hlh.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: 黄良辉
 * Date: 14-3-14
 * Time: 下午7:44
 */
@Repository
public class UserDao {
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserAuthorityService userAuthorityService;
    @Autowired
    private RoleAuthorityService roleAuthorityService;
    @Autowired
    private SysMenuService sysMenuService;

    /*
    * 增加
    * */
    public String add(User user) {
        String sqlStr = "INSERT INTO CORE_User (UserId,Code,Account,Password,RealName" +
                ",Spell,Alias,Gender,Mobile,Telephone" +
                ",Birthday,Email,OICQ,DutyCode,TitleCode" +
                ",DepartmentCode,Description,Enabled,SortCode,CreateDate" +
                ",CreateUserCode,CreateUserName)" +
                " VALUES(?,?,?,?,?" +
                ",?,?,?,?,?" +
                ",?,?,?,?,?" +
                ",?,?,?,?,?" +
                ",?,?)";
        Object[] objects = new Object[]{user.getUserId(), user.getCode(), user.getAccount(), user.getPassword(), user.getRealName()
                , user.getSpell(), user.getAlias(), user.getGender(), user.getMobile(), user.getTelephone()
                , VeDate.strToDate(user.getBirthday()), user.getEmail(), user.getOICQ(), user.getDutyCode()
                , user.getTitleCode(), user.getDepartmentCode(), user.getDescription()
                , user.isEnabled(), user.getSortCode(), user.getCreateDate(), user.getCreateUserCode()
                , user.getCreateUserName()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "添加操作失败,请联系管理员";
    }

    /*
     * 彻底删除
     * */
    public String ThoroughDel(String UserId) {
        String sqlStr = "DELETE FROM CORE_User  WHERE UserId=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{UserId});
        if (RValue1 > 0) {
            return "OK";
        }
        return "彻底删除操作失败,请联系管理员";
    }

    /*
    * 删除
    * */
    public String del(String UserId, Date ModifyDate, String ModifyUserCode, String ModifyUserName) {
        String sqlStr = "UPDATE CORE_User SET DeleteMark=?,ModifyDate=?,ModifyUserCode=?,ModifyUserName=? WHERE UserId=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{true, ModifyDate, ModifyUserCode, ModifyUserName, UserId});
        if (RValue1 > 0) {
            return "OK";
        }
        return "删除操作失败,请联系管理员";
    }

    /*
    * 修改
    * */
    public String edit(User user) {
        String sqlStr = "UPDATE CORE_User SET RealName=?,Spell=?,Alias=?" +
                ",Gender=?,Mobile=?,Telephone=?,Birthday=?" +
                ",Email=?,OICQ=?,DutyCode=?,TitleCode=?" +
                ",DepartmentCode=?,Description=?,Enabled=?,SortCode=?,ModifyDate=?" +
                ",ModifyUserCode=?,ModifyUserName=? WHERE UserId=?";
        Object[] objects = new Object[]{user.getRealName(), user.getSpell(), user.getAlias()
                , user.getGender(), user.getMobile(), user.getTelephone(), VeDate.strToDate(user.getBirthday())
                , user.getEmail(), user.getOICQ(), user.getDutyCode(), user.getTitleCode()
                , user.getDepartmentCode(), user.getDescription(), user.isEnabled(), user.getSortCode(), user.getModifyDate()
                , user.getModifyUserCode(), user.getModifyUserName(), user.getUserId()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "修改操作失败,请联系管理员";
    }

    /*
   * 修改-用户登录修改自已的用户信息
   * */
    public String PersonalEdit(User user) {
        String sqlStr = "UPDATE CORE_User SET RealName=?,Spell=?,Alias=?" +
                ",Gender=?,Mobile=?,Telephone=?,Birthday=?" +
                ",Email=?,OICQ=?,DutyCode=?,TitleCode=?" +
                ",DepartmentCode=?,Description=?,IdType=?,IdNumber=?,IdImgPath1=?,IdImgPath2=?,IdImgPath3=?,ModifyDate=?" +
                ",ModifyUserCode=?,ModifyUserName=? WHERE UserId=?";
        Object[] objects = new Object[]{user.getRealName(), user.getSpell(), user.getAlias()
                , user.getGender(), user.getMobile(), user.getTelephone(), VeDate.strToDate(user.getBirthday())
                , user.getEmail(), user.getOICQ(), user.getDutyCode(), user.getTitleCode()
                , user.getDepartmentCode(),  user.getDescription(),user.getIdType(),user.getIdNumber(),user.getIdImgPath1(),user.getIdImgPath2(),user.getIdImgPath3(),  user.getModifyDate()
                , user.getModifyUserCode(), user.getModifyUserName(), user.getUserId()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "修改操作失败,请联系管理员";
    }

    /*
 * 按Id查询
 * */
    public User findById(String UserId) {
        String sqlStr = "SELECT * FROM CORE_User WHERE UserId=?";
        return dbUtilsTemplate.findFirst(User.class, sqlStr, UserId);
    }

    /*
* 按Code查询
* */
    public User findByCode(String UserCode) {
        String sqlStr = "SELECT * FROM CORE_User WHERE Code=?";
        return dbUtilsTemplate.findFirst(User.class, sqlStr, UserCode);
    }


    public boolean UserIdIsRepeat(String UserId) {
        if (!"".equals(UserId)) {
            String sSql1 = "SELECT UserId FROM CORE_User WHERE UserId=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, UserId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean CodeIsRepeat(String Code) {
        if (!"".equals(Code)) {
            String sSql1 = "SELECT Code FROM CORE_User WHERE Code=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, Code);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean CodeIsRepeat(String Code, String UserId) {
        if ("".equals(UserId)) {
            return false;
        }
        if (!"".equals(Code)) {
            String sSql1 = "SELECT Code FROM CORE_User WHERE Code=? AND UserId<>?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, Code, UserId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean AccountIsRepeat(String account) {
        if (!"".equals(account)) {
            String sSql1 = "SELECT Account FROM CORE_User WHERE Account=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, account);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean AccountIsRepeat(String account, String UserId) {
        if ("".equals(UserId)) {
            return false;
        }
        if (!"".equals(account)) {
            String sSql1 = "SELECT Account FROM CORE_User WHERE Account=? AND UserId<>?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, account, UserId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }


    //验证添加
    public String VerifyAdd(User user) {
        String msg = "";
        if ("".equals(user.getUserId()) || user.getUserId() == null) {
            msg = "用户主键不能为空";
        } else if ("".equals(user.getCode()) || user.getCode() == null) {
            msg = "用户编号不能为空";
        } else if ("".equals(user.getAccount()) || user.getAccount() == null) {
            msg = "登录账户不能为空";
        } else if ("".equals(user.getRealName()) || user.getRealName() == null) {
            msg = "姓名不能为空";
        } else if (UserIdIsRepeat(user.getUserId())) {
            msg = "用户主键已经存在";
        } else if (CodeIsRepeat(user.getCode())) {
            msg = "编号已经存在";
        } else if (AccountIsRepeat(user.getAccount())) {
            msg = "登录账户已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }

    //验证修改
    public String VerifyEdit(User user) {
        String msg = "";
        if ("".equals(user.getRealName()) || user.getRealName() == null) {
            msg = "姓名不能为空";
        } else if (AccountIsRepeat(user.getAccount(), user.getUserId())) {
            msg = "登录账户已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }

    /*
   * 查询
   * */
    public List<User> findAll() {
        String sqlStr = "SELECT * FROM CORE_User WHERE DeleteMark=false ORDER BY SortCode";
        return dbUtilsTemplate.find(User.class, sqlStr);
    }

    public String findAll(String ShowFieldName, String[] strings) {
        String sqlStr = "SELECT " + ShowFieldName + " FROM CORE_User WHERE DeleteMark=false ORDER BY SortCode";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sqlStr);
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings);
        return tmpStr1;
    }

    //获取总行数
    public int GetRowCount() {
        String sSql1 = "SELECT COUNT(UserId) as RowCount FROM CORE_User WHERE DeleteMark=false ";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1);
        if (objects.length > 0) {
            return Integer.parseInt(objects[0].toString());
        }
        return 0;
    }

    //分页 模糊查找
    public String findByPage(String findValue, boolean enabled, int start, int number) {
        String sSql1 = "";
        if (enabled) { //只显示有效
            sSql1 = "SELECT UserId,Code,Account,RealName,Gender" +
                    ",Mobile" +
                    ",DepartmentCode,(SELECT FullName FROM BASE_Department WHERE Code=CORE_User.DepartmentCode) as DepartmentName" +
                    ",Enabled,LogOnCount" +
                    ",LastVisit,Description" +
                    " FROM CORE_User"
                    + " WHERE DeleteMark=false AND Enabled=true AND (Code LIKE ? OR Account LIKE ? OR RealName LIKE ? OR Gender LIKE ? OR Mobile LIKE ?)"
                    + " ORDER BY SortCode LIMIT ?,?";
        } else {
            sSql1 = "SELECT UserId,Code,Account,RealName,Gender" +
                    ",Mobile" +
                    ",DepartmentCode,(SELECT FullName FROM BASE_Department WHERE Code=CORE_User.DepartmentCode) as DepartmentName" +
                    ",Enabled,LogOnCount" +
                    ",LastVisit,Description" +
                    " FROM CORE_User"
                    + " WHERE DeleteMark=false AND (Code LIKE ? OR Account LIKE ? OR RealName LIKE ? OR Gender LIKE ? OR Mobile LIKE ?)"
                    + " ORDER BY SortCode LIMIT ?,?";
        }
        List tmpList1 = dbUtilsTemplate.executeQueryList(sSql1, "%" + findValue + "%", "%" + findValue + "%"
                , "%" + findValue + "%", "%" + findValue + "%", "%" + findValue + "%", start, number);
        String[] strings1 = new String[]{"UserId", "Code", "Account", "RealName", "Gender"
                , "Mobile", "DepartmentCode", "DepartmentName", "Enabled", "LogOnCount"
                , "LastVisit", "Description"};
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    //获取总行数 模糊查找
    public int GetRowCount(String findValue, boolean enabled) {
        String sSql1 = "";
        if (enabled) {
            sSql1 = "SELECT COUNT(UserId) as RowCount FROM CORE_User"
                    + " WHERE DeleteMark=false AND Enabled=true AND (Code LIKE ? OR Account LIKE ? OR RealName LIKE ? OR Gender LIKE ? OR Mobile LIKE ?)";

        } else {
            sSql1 = "SELECT COUNT(UserId) as RowCount FROM CORE_User"
                    + " WHERE DeleteMark=false AND (Code LIKE ? OR Account LIKE ? OR RealName LIKE ? OR Gender LIKE ? OR Mobile LIKE ?)";
        }
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, "%" + findValue + "%", "%" + findValue + "%"
                , "%" + findValue + "%", "%" + findValue + "%", "%" + findValue + "%");
        if (objects.length > 0) {
            return Integer.parseInt(objects[0].toString());
        }
        return 0;
    }


    //分页 精确查找
    public String findByPage(String fieldName, String findValue, boolean enabled, int start, int number) {
        String sSql1 = "";
        if (enabled) { //只显示有效
            sSql1 = "SELECT UserId,Code,Account,RealName,Gender" +
                    ",Mobile" +
                    ",DepartmentCode,(SELECT FullName FROM BASE_Department WHERE Code=CORE_User.DepartmentCode) as DepartmentName" +
                    ",Enabled,LogOnCount" +
                    ",LastVisit,Description" +
                    " FROM CORE_User"
                    + " WHERE DeleteMark=false AND Enabled=true AND " + fieldName + "='" + findValue + "'"
                    + " ORDER BY SortCode LIMIT ?,?";
        } else {
            sSql1 = "SELECT UserId,Code,Account,RealName,Gender" +
                    ",Mobile" +
                    ",DepartmentCode,(SELECT FullName FROM BASE_Department WHERE Code=CORE_User.DepartmentCode) as DepartmentName" +
                    ",Enabled,LogOnCount" +
                    ",LastVisit,Description" +
                    " FROM CORE_User"
                    + " WHERE DeleteMark=false AND " + fieldName + "='" + findValue + "'"
                    + " ORDER BY SortCode LIMIT ?,?";
        }
        List tmpList1 = dbUtilsTemplate.executeQueryList(sSql1, start, number);
        String[] strings1 = new String[]{"UserId", "Code", "Account", "RealName", "Gender"
                , "Mobile", "DepartmentId", "DepartmentName", "Enabled", "LogOnCount"
                , "LastVisit", "Description"};
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    //获取总行数 精确查找
    public int GetRowCount(String fieldName, String findValue, boolean enabled) {
        String sSql1 = "";
        if (enabled) { //只显示有效
            sSql1 = "SELECT COUNT(UserId) as RowCount FROM CORE_User"
                    + " WHERE DeleteMark=false AND Enabled=true AND " + fieldName + "='" + findValue + "'";
        } else {
            sSql1 = "SELECT COUNT(UserId) as RowCount FROM CORE_User"
                    + " WHERE DeleteMark=false AND " + fieldName + "='" + findValue + "'";
        }
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1);
        if (objects.length > 0) {
            return Integer.parseInt(objects[0].toString());
        }
        return 0;
    }

    //验证用户名和密码是否正确
    public boolean getMatchCount(String account, String password) {
        String tmpPassWord = DESUtil.encrypt(password);
        String sqlStr = "SELECT Account,Password FROM CORE_User WHERE Account=? AND Password=? AND DeleteMark=false AND Enabled=true";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sqlStr, account, tmpPassWord);
        if (objects != null) {
            if (StrUtil.toStr(objects[0]).equals(account) && StrUtil.toStr(objects[1]).equals(tmpPassWord)) {
                return true;
            }
        }
        return false;
    }

    //更新登录信息
    public int updateLoginInfo(User user) {
        String sqlStr = "UPDATE CORE_User SET IPAddress=?,MACAddress=?,LogOnCount=?,FirstVisit=?,PreviousVisit=?,LastVisit=? WHERE UserId=?";
        Object[] object = new Object[]{user.getIPAddress(), user.getMACAddress(), user.getLogOnCount(), user.getFirstVisit()
                , user.getPreviousVisit(), user.getLastVisit(), user.getUserId()};
        return dbUtilsTemplate.update(sqlStr, object);
    }

    //按用户名查询记录
    public User findUserByUserName(final String account) {
        String sqlStr = "SELECT * FROM CORE_User WHERE Account=?";
        return (User) dbUtilsTemplate.findFirst(User.class, sqlStr, account);
    }

    //重置密码
    public String resetPassword(String UserId, String password, Date ChangePasswordDate, Date ModifyDate, String ModifyUserCode, String ModifyUserName) {
        String sqlStr = "UPDATE CORE_User SET Password=?,ChangePasswordDate=?,ModifyDate=?" +
                ",ModifyUserCode=?,ModifyUserName=? WHERE UserId=?";
        Object[] objects = new Object[]{password, ChangePasswordDate, ModifyDate, ModifyUserCode, ModifyUserName, UserId};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "重置密码操作失败,请联系管理员";
    }

    //验证旧密码是否正确
    public Boolean VerifyOldPassword(String UserId, String OldPassword) {
        String sSql1 = "SELECT COUNT(UserId) as RowCount FROM CORE_User WHERE UserId=? AND Password=? ";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, UserId, OldPassword);
        if (objects != null && objects.length > 0) {
            return true;
        }
        return false;
    }

    //获取的所有可用角色并获取已选中的角色
    public List FindUserAllotRole(String UserCode) {
        List RoleList1 = roleService.findByField("Code,FullName");
        ArrayList tmpList1 = new ArrayList();
        for (int i1 = 0; i1 < RoleList1.size(); i1++) {
            Object[] objects = (Object[]) RoleList1.get(i1);
            ArrayList arrayList1 = new ArrayList();
            arrayList1.add(objects[0]);
            arrayList1.add(objects[1]);
            if (userRoleService.findUserRoleIsCheck(UserCode, StrUtil.toStr(objects[0])) == true) {
                arrayList1.add("Checked=\"checked\"");
            } else {
                arrayList1.add("");
            }
            tmpList1.add(arrayList1);
        }
        return tmpList1;
    }

    //获取的所有可用角色并获取已选中的角色
    public List FindUserAllotRole(String UserCode,String Category) {
        List RoleList1 = roleService.findByField("Code,FullName",Category);
        ArrayList tmpList1 = new ArrayList();
        for (int i1 = 0; i1 < RoleList1.size(); i1++) {
            Object[] objects = (Object[]) RoleList1.get(i1);
            ArrayList arrayList1 = new ArrayList();
            arrayList1.add(objects[0]);
            arrayList1.add(objects[1]);
            if (userRoleService.findUserRoleIsCheck(UserCode, StrUtil.toStr(objects[0])) == true) {
                arrayList1.add("Checked=\"checked\"");
            } else {
                arrayList1.add("");
            }
            tmpList1.add(arrayList1);
        }
        return tmpList1;
    }

    //用户权限验证
    public Boolean UserAuthorityVerify(String UserCode, String ResourcesCode) {
        //UserCode 用户代码 ResourcesCode 资源代码（平台代码、模块代码、事件操作代码、数据安全代码）
        if (userAuthorityService.findUserAuthorityIsCheck(UserCode, ResourcesCode) == true) {    //用户权限
            return true;
        } else {  //用户角色
            List<UserRole> userRoleList = userRoleService.FindUserRole(UserCode);
            for (UserRole userRole : userRoleList) {
                if (roleAuthorityService.findRoleAuthorityIsCheck(userRole.getRoleCode(), ResourcesCode) == true) {
                    return true;
                }
            }
        }
        return false;
    }

    //用户获取平台的模块数据
    public List<SysMenu> UserGetChildMenuData(String UserCode, String PTCode) {
        SysMenu sysMenu1 = sysMenuService.findByCode(PTCode);
        List<SysMenu> sysMenuList = sysMenuService.GetChildMenu(sysMenu1.getMenuId());
        List<SysMenu> sysMenuList1 = new ArrayList<SysMenu>();
        for (SysMenu sysMenu : sysMenuList) {
            if (UserAuthorityVerify(UserCode, sysMenu.getCode()) == true) {
                sysMenuList1.add(sysMenu);
            }
        }
        return sysMenuList1;
    }

    //用户获取平台的模块数据
    public List<SysMenu> UserGetChildMenuDataByParentID(String UserCode, String ParentId) {
        List<SysMenu> sysMenuList = sysMenuService.GetChildMenu(ParentId);
        List<SysMenu> sysMenuList1 = new ArrayList<SysMenu>();
        for (SysMenu sysMenu : sysMenuList) {
            if (UserAuthorityVerify(UserCode, sysMenu.getCode()) == true) {
                sysMenuList1.add(sysMenu);
            }
        }
        return sysMenuList1;
    }

    //按用户姓名转用户代码
    public String RealNameToCode(String RealName) {
        String sSql1 = "SELECT Code FROM CORE_User WHERE RealName=?";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, RealName);
        if (objects != null) {
            return StrUtil.toStr(objects[0]);
        }
        return "";
    }

}
