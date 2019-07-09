package com.hlh.GLPT.base.dao;

import com.hlh.GLPT.base.domain.Department;
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
public class DepartmentDao {
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;

    /*
    * 增加
    * */
    public String add(Department department) {
        String sqlStr = "INSERT INTO Base_Department (DepartmentId,Code,FullName,BMLXCode,Description" +
                ",Enabled,SortCode,CreateDate,CreateUserCode,CreateUserName)" +
                " VALUES(?,?,?,?,?" +
                ",?,?,?,?,?)";
        Object[] objects = new Object[]{department.getDepartmentId(), department.getCode(), department.getFullName(), department.getBMLXCode(), department.getDescription()
                , department.isEnabled(), department.getSortCode(), department.getCreateDate(), department.getCreateUserCode(), department.getCreateUserName()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "添加操作失败,请联系管理员";
    }

    /*
     * 彻底删除
     * */
    public String ThoroughDel(String DepartmentId) {
        String sqlStr = "DELETE FROM Base_Department  WHERE DepartmentId=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{DepartmentId});
        if (RValue1 > 0) {
            return "OK";
        }
        return "彻底删除操作失败,请联系管理员";
    }

    /*
    * 删除
    * */
    public String del(String DepartmentId, Date ModifyDate, String ModifyUserCode, String ModifyUserName) {
        String sqlStr = "UPDATE Base_Department SET DeleteMark=?,ModifyDate=?,ModifyUserCode=?,ModifyUserName=? WHERE DepartmentId=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{true, ModifyDate, ModifyUserCode, ModifyUserName, DepartmentId});
        if (RValue1 > 0) {
            return "OK";
        }
        return "删除操作失败,请联系管理员";
    }

    /*
    * 修改
    * */
    public String edit(Department department) {
        String sqlStr = "UPDATE Base_Department SET Code=?,FullName=?,BMLXCode=?,Description=?,Enabled=?,SortCode=?" +
                ",ModifyDate=?,ModifyUserCode=?,ModifyUserName=? WHERE DepartmentId=?";
        Object[] objects = new Object[]{department.getCode(), department.getFullName(), department.getBMLXCode(), department.getDescription(), department.isEnabled(), department.getSortCode()
                , department.getModifyDate(), department.getModifyUserCode(), department.getModifyUserName(), department.getDepartmentId()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "修改操作失败,请联系管理员";
    }

    /*
    * 按Id查询
    * */
    public Department findById(String DepartmentId) {
        String sqlStr = "SELECT * FROM Base_Department WHERE DepartmentId=?";
        return dbUtilsTemplate.findFirst(Department.class, sqlStr, DepartmentId);
    }

    //获取总行数
    public int GetRowCount() {
        String sSql1 = "SELECT COUNT(DepartmentId) as RowCount FROM Base_Department";
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
    public List<Department> findAll() {
        String sqlStr = "SELECT * FROM Base_Department ORDER BY SortCode";
        return dbUtilsTemplate.find(Department.class, sqlStr);
    }

    /*
* 查询
* */
    public List<Department> findAllEx() {
        String sqlStr = "SELECT * FROM Base_Department WHERE Enabled=true AND DeleteMark=false ORDER BY SortCode";
        return dbUtilsTemplate.find(Department.class, sqlStr);
    }

    public String findAll(String ShowFieldName, String[] strings) {
        String sqlStr = "SELECT " + ShowFieldName + " FROM Base_Department ORDER BY SortCode";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sqlStr);
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings);
        return tmpStr1;
    }

    public String findAllEx(String ShowFieldName, String[] strings) {
        String sqlStr = "SELECT " + ShowFieldName + " FROM Base_Department WHERE Enabled=true AND DeleteMark=false ORDER BY SortCode";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sqlStr);
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings);
        return tmpStr1;
    }

    //按部门类型获取部门数据
    public String findAllEx(String ShowFieldName, String[] strings, String BMLXCode) {
        String sqlStr = "SELECT " + ShowFieldName + " FROM Base_Department WHERE Enabled=true AND DeleteMark=false AND BMLXCode=? ORDER BY SortCode";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sqlStr, BMLXCode);
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings);
        return tmpStr1;
    }

    //分页
    public String findByPage(String findValue, boolean enabled, int start, int number) {
        String sSql1 = "";
        if (enabled) { //只显示有效
            sSql1 = "SELECT DepartmentId,Code,FullName,BMLXCode,(SELECT FullName FROM CORE_ItemDetails WHERE Code=Base_Department.BMLXCode) as BMLXName,Description,Enabled,SortCode,DeleteMark" +
                    " FROM Base_Department"
                    + " WHERE Enabled=true AND DeleteMark=false AND (Code LIKE ? OR FullName LIKE ?)"
                    + " ORDER BY SortCode LIMIT ?,?";
        } else {
            sSql1 = "SELECT DepartmentId,Code,FullName,BMLXCode,(SELECT FullName FROM CORE_ItemDetails WHERE Code=Base_Department.BMLXCode) as BMLXName,Description,Enabled,SortCode,DeleteMark" +
                    " FROM Base_Department"
                    + " WHERE DeleteMark=false AND (Code LIKE ? OR FullName LIKE ?)"
                    + " ORDER BY SortCode LIMIT ?,?";
        }
        List tmpList1 = dbUtilsTemplate.executeQueryList(sSql1, "%" + findValue + "%", "%" + findValue + "%", start, number);
        String[] strings1 = new String[]{"DepartmentId", "Code", "FullName", "BMLXCode", "BMLXName", "Description", "Enabled", "SortCode", "DeleteMark"};
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    //获取总行数(带查询参数)
    public int GetRowCount(String findValue, boolean enabled) {
        String sSql1 = "";
        if (enabled) {  //只显示有效
            sSql1 = "SELECT COUNT(DepartmentId) as RowCount FROM Base_Department"
                    + " WHERE Code LIKE ? OR FullName LIKE ?";
        } else {
            sSql1 = "SELECT COUNT(DepartmentId) as RowCount FROM Base_Department"
                    + " WHERE Code LIKE ? OR FullName LIKE ?";
        }
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, "%" + findValue + "%", "%" + findValue + "%");
        if (objects.length > 0) {
            return Integer.parseInt(objects[0].toString());
        }
        return 0;
    }

    public boolean DepartmentIdIsRepeat(String DepartmentId) {
        if (!"".equals(DepartmentId)) {
            String sSql1 = "SELECT DepartmentId FROM Base_Department WHERE DepartmentId=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, DepartmentId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean CodeIsRepeat(String code) {
        if (!"".equals(code)) {
            String sSql1 = "SELECT DepartmentId FROM Base_Department WHERE Code=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, code);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean CodeIsRepeat(String code, String DepartmentId) {
        if (!"".equals(code)) {
            String sSql1 = "SELECT DepartmentId FROM Base_Department WHERE Code=? AND DepartmentId<>?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, code, DepartmentId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean FullNameIsRepeat(String fullName) {
        if (!"".equals(fullName)) {
            String sSql1 = "SELECT DepartmentId FROM Base_Department WHERE FullName=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, fullName);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean FullNameIsRepeat(String fullName, String DepartmentId) {
        if (!"".equals(fullName)) {
            String sSql1 = "SELECT DepartmentId FROM Base_Department WHERE FullName=? AND DepartmentId<>?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, fullName, DepartmentId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }


    //验证添加
    public String VerifyAdd(Department department) {
        String msg = "";
        if ("".equals(department.getDepartmentId()) || department.getDepartmentId() == null) {
            msg = "部门主键不能为空";
        } else if ("".equals(department.getCode()) || department.getCode() == null) {
            msg = "部门编号不能为空";
        } else if ("".equals(department.getFullName()) || department.getFullName() == null) {
            msg = "部门名称不能为空";
        } else if (DepartmentIdIsRepeat(department.getDepartmentId())) {
            msg = "部门主键已经存在";
        } else if (CodeIsRepeat(department.getCode())) {
            msg = "部门编号已经存在";
        } else if (FullNameIsRepeat(department.getFullName())) {
            msg = "部门名称已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }

    //验证修改
    public String VerifyEdit(Department department) {
        String msg = "";
        if ("".equals(department.getDepartmentId()) || department.getDepartmentId() == null) {
            msg = "请选择要修改的记录";
        } else if ("".equals(department.getCode()) || department.getCode() == null) {
            msg = "部门编号不能为空";
        } else if ("".equals(department.getFullName()) || department.getFullName() == null) {
            msg = "部门名称不能为空";
        } else if (CodeIsRepeat(department.getCode(), department.getDepartmentId())) {
            msg = "部门编号已经存在";
        } else if (FullNameIsRepeat(department.getFullName(), department.getDepartmentId())) {
            msg = "部门名称已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }

    //部门代码转部门名称
    public String DepartmentCodeToDepartmentName(String DepartmentCode) {
        String sSql1 = "SELECT FullName FROM Base_Department WHERE Code=?";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, DepartmentCode);
        if (objects != null) {
            return StrUtil.toStr(objects[0]);
        }
        return "";
    }

    //部门名称转部门代码
    public String DepartmentNameToDepartmentCode(String DepartmentName) {
        String sSql1 = "SELECT Code FROM Base_Department WHERE FullName=?";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, DepartmentName);
        if (objects != null) {
            return StrUtil.toStr(objects[0]);
        }
        return "";
    }
}
