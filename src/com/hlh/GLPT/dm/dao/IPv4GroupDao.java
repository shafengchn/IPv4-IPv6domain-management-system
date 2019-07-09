package com.hlh.GLPT.dm.dao;

import com.hlh.GLPT.dm.domain.IPv4Group;
import com.hlh.util.DbUtilsTemplate;
import com.hlh.util.StrUtil;
import com.hlh.util.jsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * IPv4地址分组
 * User: 黄良辉
 * Date: 2015-12-03
 * Time: 19:17
 */
@Repository
public class IPv4GroupDao {
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;

    /*添加*/
    public String add(IPv4Group _IPv4Group) {
        String sqlStr1 = "INSERT INTO dm_Ipv4group (GroupCode,GroupName,Remarks,Enabled,SortCode,DeleteMark,CreateDate,CreateUserCode,CreateUserName) VALUES (?,?,?,?,?,?,?,?,?)";
        Object[] obj1 = new Object[]{_IPv4Group.getGroupCode(), _IPv4Group.getGroupName(), _IPv4Group.getRemarks(), _IPv4Group.isEnabled(), _IPv4Group.getSortCode()
                , _IPv4Group.isDeleteMark(), _IPv4Group.getCreateDate(), _IPv4Group.getCreateUserCode(), _IPv4Group.getCreateUserName()};
        long RValue1 = dbUtilsTemplate.update(sqlStr1, obj1);
        if (RValue1 > 0) {
            return "OK";
        }
        return "添加操作失败,请联系管理员";
    }

    /*验证添加*/
    public String VerifyAdd(IPv4Group _IPv4Group) {
        String msg = "";
        if (!StrUtil.trimNotEmpty(_IPv4Group.getGroupCode())) {
            msg = "分组代码不能为空";
        } else if (!StrUtil.trimNotEmpty(_IPv4Group.getGroupName())) {
            msg = "分组名称不能为空";
        } else if (GroupCodeIsRepeat(_IPv4Group.getGroupCode())) {
            msg = "分组代码已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }

    /*标识删除*/
    public String del(int Id,boolean DeleteMark) {
        String sqlStr1 = "UPDATE dm_Ipv4group SET DeleteMark=? WHERE Id=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr1, new Object[]{DeleteMark,Id});
        if (RValue1 > 0) {
            return "OK";
        }
        return "删除操作失败,请联系管理员";
    }

    /*彻底删除*/
    public String ThoroughDel(int Id) {
        String sqlStr = "DELETE FROM dm_Ipv4group  WHERE Id=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{Id});
        if (RValue1 > 0) {
            return "OK";
        }
        return "彻底删除操作失败,请联系管理员";
    }

    /*修改*/
    public String edit(IPv4Group _IPv4Group) {
        String sqlStr = "UPDATE dm_Ipv4group SET GroupCode=?,GroupName=?,Remarks=?,Enabled=?,SortCode=?,DeleteMark=?,ModifyDate=?,ModifyUserCode=?,ModifyUserName=? WHERE Id=?";
        Object[] objVal = new Object[]{_IPv4Group.getGroupCode(), _IPv4Group.getGroupName(), _IPv4Group.getRemarks(), _IPv4Group.isEnabled(), _IPv4Group.getSortCode()
                , _IPv4Group.isDeleteMark(), _IPv4Group.getModifyDate(), _IPv4Group.getModifyUserCode(), _IPv4Group.getModifyUserName(), _IPv4Group.getId()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objVal);
        if (RValue1 > 0) {
            return "OK";
        }
        return "修改操作失败,请联系管理员";
    }

    /*验证修改*/
    public String VerifyEdit(IPv4Group _IPv4Group) {
        String msg = "";
        if (!StrUtil.trimNotEmpty(_IPv4Group.getGroupCode())) {
            msg = "分组代码不能为空";
        } else if (!StrUtil.trimNotEmpty(_IPv4Group.getGroupName())) {
            msg = "分组名称不能为空";
        } else if (GroupCodeIsRepeat(_IPv4Group.getGroupCode(), _IPv4Group.getId())) {
            msg = "分组代码已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }

    /*查询单条记录*/
    public IPv4Group findById(int Id) {
        String sqlStr = "SELECT * FROM dm_Ipv4group WHERE Id=?  LIMIT 1";
        return dbUtilsTemplate.findFirst(IPv4Group.class, sqlStr, new Object[]{Id});
    }

    /*查询第一条记录*/
    public IPv4Group findByTop1() {
        String sqlStr = "SELECT * FROM dm_Ipv4group Order by SortCode ASC  LIMIT 1";
        return dbUtilsTemplate.findFirst(IPv4Group.class, sqlStr);
    }

    /*获取总行数*/
    public int GetRowCount() {
        String sSql1 = "SELECT COUNT(Id) as RowCount FROM dm_Ipv4group";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1);
        if (objects.length > 0) {
            return Integer.parseInt(objects[0].toString());
        }
        return 0;
    }

    /*获取总行数*/
    public int GetRowCount(boolean Enabled, boolean DeleteMark) {
        String sSql1 = "SELECT COUNT(Id) as RowCount FROM dm_Ipv4group  WHERE Enabled=? AND DeleteMark=? ";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, new Object[]{Enabled, DeleteMark});
        if (objects.length > 0) {
            if(objects[0]!=null){
            return Integer.parseInt(objects[0].toString());
            }
        }
        return 0;
    }

    /*查询所有记录*/
    public List<IPv4Group> findAll() {
        String sqlStr = "SELECT * FROM dm_Ipv4group ORDER BY SortCode ASC";
        return dbUtilsTemplate.find(IPv4Group.class, sqlStr);
    }


    /*查询所有记录*/
    public List<IPv4Group> findAllEx(boolean Enabled, boolean DeleteMark) {
        String sqlStr = "SELECT * FROM dm_Ipv4group WHERE Enabled=? AND DeleteMark=? ORDER BY SortCode ASC";
        return dbUtilsTemplate.find(IPv4Group.class, sqlStr, new Object[]{Enabled, DeleteMark});
    }

    /*查询所有记录*/
    public String findAllEx(String[] fieldName, boolean Enabled, boolean DeleteMark) {
        StringBuilder sb1 = new StringBuilder();
        for (int i1 = 0; i1 < fieldName.length; i1++) {
            if (sb1.length() > 0 && i1 < fieldName.length) {
                sb1.append(",");
            }
            sb1.append(fieldName[i1]);
        }
        String sqlStr = "SELECT " + sb1.toString() + " FROM dm_Ipv4group WHERE Enabled=? AND DeleteMark=? ORDER BY SortCode ASC";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sqlStr, Enabled, DeleteMark);
        return jsonUtil.ListToJson(tmpList1, fieldName);
    }

    /*字段值转换*/
    public String FieldFValToFieldFVal(String pdFieldName, String pdFieldVal, String mbFieldName) {
        String sSql1 = "SELECT " + mbFieldName + " FROM dm_Ipv4group WHERE " + pdFieldName + "=? LIMIT 1";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, pdFieldVal);
        if (objects != null) {
            return StrUtil.toStr(objects[0]);
        }
        return "";
    }

    /*GroupCode是否重复*/
    public boolean GroupCodeIsRepeat(String GroupCode) {
        if (!"".equals(GroupCode)) {
            String sSql1 = "SELECT Id FROM dm_Ipv4group WHERE GroupCode=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, GroupCode);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    /*GroupCode是否重复*/
    public boolean GroupCodeIsRepeat(String GroupCode, int Id) {
        if (!"".equals(GroupCode)) {
            String sSql1 = "SELECT ClassId FROM dm_Ipv4group WHERE GroupCode=? AND Id<>?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, GroupCode, Id);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }
}