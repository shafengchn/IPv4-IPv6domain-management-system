package com.hlh.GLPT.core.dao;

import com.hlh.GLPT.core.domain.Role;
import com.hlh.util.DbUtilsTemplate;
import com.hlh.util.StrUtil;
import com.hlh.util.jsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * User: 黄良辉
 * Date: 14-3-14
 * Time: 下午7:44
 */
@Repository
public class RoleDao {
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;

    /*
    * 增加
    * */
    public String add(Role role) {
        String sqlStr = "INSERT INTO CORE_Role (RoleId,Code,FullName,Category,Description" +
                ",AllowEdit,AllowDelete,Enabled,SortCode,CreateDate" +
                ",CreateUserCode,CreateUserName)" +
                " VALUES(?,?,?,?,?" +
                ",?,?,?,?,?" +
                ",?,?)";
        Object[] objects = new Object[]{role.getRoleId(),role.getCode(),role.getFullName()
        ,role.getCategory(),role.getDescription(),role.isAllowEdit(),role.isAllowDelete(),role.isEnabled()
        ,role.getSortCode(),role.getCreateDate(),role.getCreateUserCode(),role.getCreateUserName()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "添加操作失败,请联系管理员";
    }

    /*
     * 彻底删除
     * */
    public String ThoroughDel(String RoleId) {
        String sqlStr = "DELETE FROM CORE_Role  WHERE RoleId=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{RoleId});
        if (RValue1 > 0) {
            return "OK";
        }
        return "彻底删除操作失败,请联系管理员";
    }

    /*
    * 删除
    * */
    public String del(String RoleId, Date ModifyDate, String ModifyUserCode, String ModifyUserName) {
        String sqlStr = "UPDATE CORE_Role SET DeleteMark=?,ModifyDate=?,ModifyUserCode=?,ModifyUserName=? WHERE RoleId=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{true, ModifyDate, ModifyUserCode, ModifyUserName, RoleId});
        if (RValue1 > 0) {
            return "OK";
        }
        return "删除操作失败,请联系管理员";
    }

    /*
    * 修改
    * */
    public String edit(Role role) {
        String sqlStr = "UPDATE CORE_Role SET Code=?,FullName=?,Category=?" +
                ",Description=?,AllowEdit=?,AllowDelete=?,Enabled=?,SortCode=?" +
                ",ModifyDate=?,ModifyUserCode=?,ModifyUserName=? WHERE RoleId=?";
        Object[] objects = new Object[]{role.getCode(),role.getFullName(),role.getCategory()
                                        ,role.getDescription(),role.isAllowEdit(),role.isAllowDelete(),role.isEnabled(),role.getSortCode()
                                        ,role.getModifyDate(),role.getModifyUserCode(),role.getModifyUserName(),role.getRoleId()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "修改操作失败,请联系管理员";
    }

    /*
    * 按Id查询
    * */
    public Role findById(String RoleId) {
        String sqlStr = "SELECT * FROM CORE_Role WHERE RoleId=?";
        return dbUtilsTemplate.findFirst(Role.class, sqlStr, RoleId);
    }

    /*
   * 按Code查询
   * */
    public Role findByCode(String Code) {
        String sqlStr = "SELECT * FROM CORE_Role WHERE Code=?";
        return dbUtilsTemplate.findFirst(Role.class, sqlStr, Code);
    }


    //获取总行数
    public int GetRowCount() {
        String sSql1 = "SELECT COUNT(RoleId) as RowCount FROM CORE_Role";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1);
        if(objects.length>0){
            return Integer.parseInt(objects[0].toString());
        }
        return 0;
    }

    /*
 * 查询
 * */
    public List<Role> findAll() {
        String sqlStr = "SELECT * FROM CORE_Role ORDER BY SortCode";
        return dbUtilsTemplate.find(Role.class, sqlStr);
    }


    /*
 * 按字段获取数据记录
 * */
    public List findByField(String ShowFieldName) {
        String sqlStr = "SELECT " + ShowFieldName + " FROM CORE_Role WHERE Enabled=true AND (DeleteMark=false OR DeleteMark is NULL) ORDER BY SortCode";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sqlStr);
        return tmpList1;
    }


    /*
    * 按字段获取数据记录
    * */
    public List findByField(String ShowFieldName,String Category) {
        String sqlStr = "SELECT " + ShowFieldName + " FROM CORE_Role WHERE Category=? AND Enabled=true AND (DeleteMark=false OR DeleteMark is NULL) ORDER BY SortCode";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sqlStr,Category);
        return tmpList1;
    }


    public String findAll(String ShowFieldName, String[] strings) {
        String sqlStr = "SELECT " + ShowFieldName + " FROM CORE_Role ORDER BY SortCode";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sqlStr);
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings);
        return tmpStr1;
    }

    public String findByData(){
        String sqlStr = "SELECT RoleId,Code,FullName,Category,Description,AllowEdit,AllowDelete" +
                ",Enabled,SortCode,DeleteMark FROM CORE_Role ORDER BY SortCode";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sqlStr);
        String[] strings=new String[]{"RoleId","Code","FullName","Category"
                ,"Description","AllowEdit","AllowDelete","Enabled","SortCode","DeleteMark"};
        String tmpStr1 = jsonUtil.ListToJson(tmpList1,strings);
        return tmpStr1;
    }

    /*
    * 查询精简数据　供下拉列表框使用
    * */
    public String findBySimpleData(){
        String sqlStr = "SELECT RoleId,Code,FullName FROM CORE_Role WHERE Enabled = true AND DeleteMark=false";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sqlStr);
        String[] strings=new String[]{"RoleId","Code","FullName"};
        String tmpStr1 = jsonUtil.ListToJson(tmpList1,strings);
        return tmpStr1;
    }

    //分页
    public String findByPage(String findValue, int start, int number) {
        String sSql1 = "SELECT RoleId,Code,FullName,Category,Description,AllowEdit,AllowDelete,Enabled" +
                ",SortCode,DeleteMark" +
                " FROM CORE_Role"
                + " WHERE Code LIKE ? OR FullName LIKE ?"
                + " ORDER BY SortCode LIMIT ?,?";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sSql1,"%"+findValue+"%","%"+findValue+"%", start, number);
        String[] strings1 = new String[]{"RoleId","Code","FullName","Category"
                ,"Description","AllowEdit","AllowDelete","Enabled" ,"SortCode","DeleteMark"};
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    //获取总行数(带查询参数)
    public int GetRowCount(String findValue) {
        String sSql1 = "SELECT COUNT(RoleId) as RowCount FROM CORE_Role"
                + " WHERE Code LIKE ? OR FullName LIKE ?";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1,"%"+findValue+"%","%"+findValue+"%");
        if(objects.length>0){
            return Integer.parseInt(objects[0].toString());
        }
        return 0;
    }

    public boolean RoleIdIsRepeat(String RoleId) {
        if (!"".equals(RoleId)) {
            String sSql1 = "SELECT RoleId FROM CORE_Role WHERE RoleId=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, RoleId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean CodeIsRepeat(String code) {
        if (!"".equals(code)) {
            String sSql1 = "SELECT RoleId FROM CORE_Role WHERE Code=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, code);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean CodeIsRepeat(String code, String RoleId) {
        if (!"".equals(code)) {
            String sSql1 = "SELECT RoleId FROM CORE_Role WHERE code=? AND RoleId<>?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, code, RoleId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean FullNameIsRepeat(String fullName) {
        if (!"".equals(fullName)) {
            String sSql1 = "SELECT FullName FROM CORE_Role WHERE FullName=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, fullName);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean FullNameIsRepeat(String fullName, String RoleId) {
        if (!"".equals(fullName)) {
            String sSql1 = "SELECT FullName FROM CORE_Role WHERE FullName=? AND RoleId<>?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, fullName, RoleId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }


    //验证添加
    public String VerifyAdd(Role role) {
        String msg = "";
        if("".equals(role.getRoleId())||role.getRoleId()==null){
           msg="角色主键不能为空";
        } else if ("".equals(role.getCode())||role.getCode()==null) {
            msg = "代码不能为空";
        } else if ("".equals(role.getFullName())||role.getFullName()==null) {
            msg = "名称不能为空";
        } else if (RoleIdIsRepeat(role.getRoleId())) {
            msg = "角色主键已经存在";
        } else if (CodeIsRepeat(role.getCode())) {
            msg = "代码已经存在";
        } else if (FullNameIsRepeat(role.getFullName())) {
            msg = "名称已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }

    //验证修改
    public String VerifyEdit(Role role) {
        String msg = "";
        if ("".equals(role.getCode())||role.getCode()==null) {
            msg = "代码不能为空";
        } else if ("".equals(role.getFullName())||role.getFullName()==null) {
            msg = "名称不能为空";
        } else if (CodeIsRepeat(role.getCode(),role.getRoleId())) {
            msg = "代码已经存在";
        } else if (FullNameIsRepeat(role.getFullName(), role.getRoleId())) {
            msg = "名称已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }

    //代码转名称
    public String CodeToFullName(String Code) {
        if (!"".equals(Code)) {
            String sSql1 = "SELECT FullName FROM CORE_Role WHERE Code=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, Code);
            if (objects != null) {
                return StrUtil.toStr(objects[0]);
            }
        }
        return "";
    }



}
