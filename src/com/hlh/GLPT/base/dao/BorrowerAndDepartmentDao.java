package com.hlh.GLPT.base.dao;

import com.hlh.GLPT.base.domain.Department;
import com.hlh.GLPT.base.domain.BorrowerAndDepartment;
import com.hlh.GLPT.base.service.DepartmentService;
import com.hlh.util.DbUtilsTemplate;
import com.hlh.util.StrUtil;
import com.hlh.util.jsonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 借用者所属部门
 * User: 黄良辉
 * Date: 14-5-30
 * Time: 上午9:58
 */
@Repository
public class BorrowerAndDepartmentDao {
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;
    @Autowired
    private DepartmentService departmentService;

    /*
    * 增加
    * */
    public String add(BorrowerAndDepartment borrowerAndDepartment) {
        String sqlStr = "INSERT INTO base_BorrowerAndDepartment (BorrowerCode,DepartmentCode,Enabled,SortCode,CreateDate" +
                ",CreateUserCode,CreateUserName)" +
                " VALUES(?,?,?,?,?" +
                ",?,?)";
        Object[] objects = new Object[]{borrowerAndDepartment.getBorrowerCode(), borrowerAndDepartment.getDepartmentCode(), borrowerAndDepartment.isEnabled(), borrowerAndDepartment.getSortCode(), borrowerAndDepartment.getCreateDate()
                , borrowerAndDepartment.getCreateUserCode(), borrowerAndDepartment.getCreateUserName()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "添加操作失败,请联系管理员";
    }

    /*
     * 彻底删除
     * */
    public String ThoroughDel(int ID) {
        String sqlStr = "DELETE FROM base_BorrowerAndDepartment  WHERE ID=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{ID});
        if (RValue1 > 0) {
            return "OK";
        }
        return "彻底删除操作失败,请联系管理员";
    }

    /*
     * 彻底删除
     * */
    public String ThoroughByBorrowerCodeDel(String BorrowerCode) {
        String sqlStr = "DELETE FROM base_BorrowerAndDepartment  WHERE BorrowerCode=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{BorrowerCode});
        if (RValue1 > 0) {
            return "OK";
        }
        return "彻底删除操作失败,请联系管理员";
    }

    /*
    * 删除
    * */
    public String del(int ID, Date ModifyDate, String ModifyUserCode, String ModifyUserName) {
        String sqlStr = "UPDATE Base_BorrowerAndDepartment SET DeleteMark=?,ModifyDate=?,ModifyUserCode=?,ModifyUserName=? WHERE ID=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{true, ModifyDate, ModifyUserCode, ModifyUserName, ID});
        if (RValue1 > 0) {
            return "OK";
        }
        return "删除操作失败,请联系管理员";
    }

    /*
    * 修改
    * */
    public String edit(BorrowerAndDepartment borrowerAndDepartment) {
        String sqlStr = "UPDATE Base_BorrowerAndDepartment SET BorrowerCode=?,DepartmentCode=?,Enabled=?,SortCode=?,ModifyDate=?,ModifyUserCode=?" +
                ",ModifyUserName=? WHERE ID=?";
        Object[] objects = new Object[]{borrowerAndDepartment.getBorrowerCode(), borrowerAndDepartment.getDepartmentCode(), borrowerAndDepartment.isEnabled()
                , borrowerAndDepartment.getSortCode(), borrowerAndDepartment.getModifyDate(), borrowerAndDepartment.getModifyUserCode()
                , borrowerAndDepartment.getModifyUserName(), borrowerAndDepartment.getID()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "修改操作失败,请联系管理员";
    }

    /*
    * 按Id查询
    * */
    public BorrowerAndDepartment findById(int ID) {
        String sqlStr = "SELECT * FROM Base_BorrowerAndDepartment WHERE ID=?";
        return dbUtilsTemplate.findFirst(BorrowerAndDepartment.class, sqlStr, ID);
    }

    //获取总行数
    public int GetRowCount() {
        String sSql1 = "SELECT COUNT(ID) as RowCount FROM Base_BorrowerAndDepartment";
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
    public List<BorrowerAndDepartment> findAll() {
        String sqlStr = "SELECT * FROM Base_BorrowerAndDepartment ORDER BY SortCode";
        return dbUtilsTemplate.find(BorrowerAndDepartment.class, sqlStr);
    }

    public String findAll(String ShowFieldName, String[] strings) {
        String sqlStr = "SELECT " + ShowFieldName + " FROM Base_BorrowerAndDepartment ORDER BY SortCode";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sqlStr);
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings);
        return tmpStr1;
    }

    //分页
    public String findByPage(String findValue, int start, int number) {
        String sSql1 = "SELECT ID,BorrowerCode" +
                ",(SELECT FullName FROM Base_Borrower WHERE Code=Base_BorrowerAndDepartment.BorrowerCode) as BorrowerName" +
                ",DepartmentCode,(SELECT FullName FROM Base_Department WHERE Code=Base_BorrowerAndDepartment.DepartmentCode) as DepartmentName" +
                ",Enabled,SortCode,DeleteMark" +
                " FROM Base_BorrowerAndDepartment"
                + " WHERE BorrowerCode LIKE ? OR DepartmentCode LIKE ?"
                + " ORDER BY SortCode LIMIT ?,?";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sSql1, "%" + findValue + "%", "%" + findValue + "%", start, number);
        String[] strings1 = new String[]{"ID", "BorrowerCode", "BorrowerName"
                , "DepartmentCode", "DepartmentName", "Enabled", "SortCode", "DeleteMark"};
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    //获取总行数(带查询参数)
    public int GetRowCount(String findValue) {
        String sSql1 = "SELECT COUNT(ID) as RowCount FROM Base_BorrowerAndDepartment"
                + " WHERE BorrowerCode LIKE ? OR DepartmentCode LIKE ?";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, "%" + findValue + "%", "%" + findValue + "%");
        if (objects.length > 0) {
            return Integer.parseInt(objects[0].toString());
        }
        return 0;
    }

    //分页(查询借用者所属部门）
    public String findBorrowerByPage(String BorrowerCode, String findValue, int start, int number) {
        String sSql1 = "SELECT ID,BorrowerCode" +
                ",(SELECT FullName FROM Base_Borrower WHERE Code=Base_BorrowerAndDepartment.BorrowerCode) as BorrowerName" +
                ",DepartmentCode,(SELECT FullName FROM Base_Department WHERE Code=Base_BorrowerAndDepartment.DepartmentCode) as DepartmentName" +
                ",Enabled,SortCode,DeleteMark" +
                " FROM Base_BorrowerAndDepartment"
                + " WHERE BorrowerCode= ? AND DepartmentCode LIKE ?"
                + " ORDER BY SortCode LIMIT ?,?";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sSql1, BorrowerCode, "%" + findValue + "%", start, number);
        String[] strings1 = new String[]{"ID", "BorrowerCode", "BorrowerName"
                , "DepartmentCode", "DepartmentName", "Enabled", "SortCode", "DeleteMark"};
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    //获取总行数(查询借用者所属部门)(带查询参数)
    public int GetBorrowerRowCount(String BorrowerCode, String findValue) {
        String sSql1 = "SELECT COUNT(ID) as RowCount FROM Base_BorrowerAndDepartment"
                + " WHERE BorrowerCode = ? AND DepartmentCode LIKE ?";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, BorrowerCode, "%" + findValue + "%");
        if (objects.length > 0) {
            return Integer.parseInt(objects[0].toString());
        }
        return 0;
    }

    //记录是否重复
    public boolean RecordIsRepeat(String BorrowerCode, String DepartmentCode) {
        if (!"".equals(BorrowerCode) && !"".equals(DepartmentCode)) {
            String sSql1 = "SELECT ID FROM Base_BorrowerAndDepartment WHERE BorrowerCode=? AND DepartmentCode=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, BorrowerCode, DepartmentCode);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    //记录是否重复
    public boolean RecordIsRepeat(String BorrowerCode, String DepartmentCode, int ID) {
        if (!"".equals(BorrowerCode) && !"".equals(DepartmentCode)) {
            String sSql1 = "SELECT ID FROM Base_BorrowerAndDepartment WHERE BorrowerCode=? AND DepartmentCode=? AND ID<>?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, BorrowerCode, DepartmentCode, ID);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    //验证添加
    public String VerifyAdd(BorrowerAndDepartment borrowerAndDepartment) {
        String msg = "";
        if ("".equals(borrowerAndDepartment.getBorrowerCode()) || borrowerAndDepartment.getBorrowerCode() == null) {
            msg = "请选择借用者";
        } else if ("".equals(borrowerAndDepartment.getDepartmentCode()) || borrowerAndDepartment.getDepartmentCode() == null) {
            msg = "请选择部门";
        } else if (RecordIsRepeat(borrowerAndDepartment.getBorrowerCode(), borrowerAndDepartment.getDepartmentCode())) {
            msg = "部门已经设置过了,不能重复设置";
        } else {
            msg = "OK";
        }
        return msg;
    }

    //验证修改
    public String VerifyEdit(BorrowerAndDepartment borrowerAndDepartment) {
        String msg = "";
        if ("".equals(borrowerAndDepartment.getBorrowerCode()) || borrowerAndDepartment.getBorrowerCode() == null) {
            msg = "请选择借用者";
        } else if ("".equals(borrowerAndDepartment.getDepartmentCode()) || borrowerAndDepartment.getDepartmentCode() == null) {
            msg = "请选择部门";
        } else if (RecordIsRepeat(borrowerAndDepartment.getBorrowerCode(), borrowerAndDepartment.getDepartmentCode(), borrowerAndDepartment.getID())) {
            msg = "部门已经设置过了,不能重复设置";
        } else {
            msg = "OK";
        }
        return msg;
    }

    //按借用者代码获取所属部门(以,号间隔)
    public String BorrowerCodeToDepartment(String BorrowerCode) {
        if (!"".equals(BorrowerCode)) {
            String sSql1 = "SELECT DepartmentCode FROM Base_BorrowerAndDepartment WHERE BorrowerCode=?";
            List tmpList1 = dbUtilsTemplate.executeQueryList(sSql1, BorrowerCode);
            if (tmpList1 != null) {
                String strResult = "";
                for (int i = 0; i < tmpList1.size(); i++) {
                    Object[] var2 = (Object[]) tmpList1.get(i);
                    if ("".equals(strResult)) {
                        strResult = "'" + StrUtil.toStr(var2[0]) + "'";
                    } else {
                        strResult = strResult + ",'" + StrUtil.toStr(var2[0]) + "'";
                    }
                }
                return strResult;
            }
        }
        return "";
    }

    //按BorrowerCode获取所属部门的选中情况
    public JSONArray GetBorrowerAndDepartmentChecked(String BorrowerCode) {
        //所有部门
        List<Department> departmentList = departmentService.findAllEx();
        JSONArray jsonArray = new JSONArray();
        for (Department department1 : departmentList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("DepartmentCode", department1.getCode());
            jsonObject.put("DepartmentName", department1.getFullName());
            if (RecordIsRepeat(BorrowerCode, department1.getCode()) == true) {
                jsonObject.put("checked", "checked='checked'");
            } else {
                jsonObject.put("checked", "");
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
