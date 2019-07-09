package com.hlh.GLPT.core.dao;

import com.hlh.GLPT.core.domain.ItemDetails;
import com.hlh.GLPT.core.service.ItemsService;
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
public class ItemDetailsDao {
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;

    @Autowired
    private ItemsService itemsService;
    /*
    * 增加
    * */
    public String add(ItemDetails itemDetails) {
        String sqlStr = "INSERT INTO CORE_ItemDetails (ItemDetailsId,ItemsId,ParentId,FullName,Code" +
                ",Description,Enabled,SortCode,CreateDate,CreateUserCode" +
                ",CreateUserName)" +
                " VALUES(?,?,?,?,?" +
                ",?,?,?,?,?" +
                ",?)";
        Object[] objects = new Object[]{itemDetails.getItemDetailsId(),itemDetails.getItemsId(),itemDetails.getParentId(),itemDetails.getFullName(),itemDetails.getCode()
        ,itemDetails.getDescription(),itemDetails.isEnabled(),itemDetails.getSortCode()
        ,itemDetails.getCreateDate(),itemDetails.getCreateUserCode(),itemDetails.getCreateUserName()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "添加操作失败,请联系管理员";
    }

    /*
     * 彻底删除
     * */
    public String ThoroughDel(String ItemDetailsId) {
        String sqlStr = "DELETE FROM CORE_ItemDetails  WHERE ItemDetailsId=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{ItemDetailsId});
        if (RValue1 > 0) {
            return "OK";
        }
        return "彻底删除操作失败,请联系管理员";
    }

    /*
    * 删除
    * */
    public String del(String ItemDetailsId, Date ModifyDate, String ModifyUserCode, String ModifyUserName) {
        String sqlStr = "UPDATE CORE_ItemDetails SET DeleteMark=?,ModifyDate=?,ModifyUserCode=?,ModifyUserName=? WHERE ItemDetailsId=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{true, ModifyDate, ModifyUserCode, ModifyUserName, ItemDetailsId});
        if (RValue1 > 0) {
            return "OK";
        }
        return "删除操作失败,请联系管理员";
    }

    /*
    * 修改
    * */
    public String edit(ItemDetails itemDetails) {
        String sqlStr = "UPDATE CORE_ItemDetails SET ItemsId=?,ParentId=?,FullName=?,Code=?,Description=?" +
                ",Enabled=?,SortCode=?,ModifyDate=?,ModifyUserCode=?,ModifyUserName=? WHERE ItemDetailsId=?";
        Object[] objects = new Object[]{itemDetails.getItemsId(),itemDetails.getParentId(),itemDetails.getFullName(),itemDetails.getCode(),itemDetails.getDescription()
       ,itemDetails.isEnabled(),itemDetails.getSortCode()
        ,itemDetails.getModifyDate(),itemDetails.getModifyUserCode(),itemDetails.getModifyUserName(),itemDetails.getItemDetailsId()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "修改操作失败,请联系管理员";
    }

    /*
    * 按Id查询
    * */
    public ItemDetails findById(String ItemDetailsId) {
        String sqlStr = "SELECT * FROM CORE_ItemDetails WHERE ItemDetailsId=?";
        return dbUtilsTemplate.findFirst(ItemDetails.class, sqlStr, ItemDetailsId);
    }

    /*
 * 按Code查询
 * */
    public ItemDetails findByCode(String Code) {
        String sqlStr = "SELECT * FROM CORE_ItemDetails WHERE Code=?";
        return dbUtilsTemplate.findFirst(ItemDetails.class, sqlStr, Code);
    }

    //获取总行数
    public int GetRowCount() {
        String sSql1 = "SELECT COUNT(ItemDetailsId) as RowCount FROM CORE_ItemDetails";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1);
        if(objects.length>0){
            return Integer.parseInt(objects[0].toString());
        }
        return 0;
    }

    /*
   * 按数据字典主表的主键 返回数据字典明细的记录　Code,FullName
   * */
    public String findByItemDetails(String ItemsId){
        String sqlStr = "SELECT Code,FullName FROM CORE_ItemDetails WHERE ItemsId=? AND DeleteMark=false AND Enabled = true ORDER BY SortCode";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sqlStr,ItemsId);
        String[] strings=new String[]{"Code","FullName"};
        String tmpStr1 = jsonUtil.ListToJson(tmpList1,strings);
        return tmpStr1;
    }

    /*
 * 按数据字典主表的主键 返回数据字典明细的记录　ItemDetailsId,FullName
 * */
    public List<ItemDetails> findListByItemDetails(String ItemsId){
        String sqlStr = "SELECT * FROM CORE_ItemDetails WHERE ItemsId=? AND DeleteMark=false AND Enabled = true ORDER BY SortCode";
        return dbUtilsTemplate.find(ItemDetails.class, sqlStr,ItemsId);
    }

    /*
 * 查询
 * */
    public List<ItemDetails> findAll() {
        String sqlStr = "SELECT * FROM CORE_ItemDetails  ORDER BY SortCode";
        return dbUtilsTemplate.find(ItemDetails.class, sqlStr);
    }

    public String findAll(String ShowFieldName, String[] strings) {
        String sqlStr = "SELECT " + ShowFieldName + " FROM CORE_ItemDetails  ORDER BY SortCode";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sqlStr);
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings);
        return tmpStr1;
    }

    //分页
    public String findByPage(String findValue, int start, int number) {
        String sSql1 = "SELECT ItemDetailsId,ItemsId,ParentId,FullName,Code,Description,Enabled" +
                ",SortCode,DeleteMark" +
                " FROM CORE_ItemDetails"
                + " WHERE ItemsId = ?"
                + " ORDER BY SortCode LIMIT ?,?";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sSql1,findValue, start, number);
        String[] strings1 = new String[]{"ItemDetailsId","ItemsId","ParentId","FullName","Code"
                ,"Description","Enabled","SortCode","DeleteMark"};
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    //获取总行数(带查询参数)
    public int GetRowCount(String findValue) {
        String sSql1 = "SELECT COUNT(ItemDetailsId) as RowCount FROM CORE_ItemDetails"
                + " WHERE ItemsId = ?";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1,findValue);
        if(objects.length>0){
            return Integer.parseInt(objects[0].toString());
        }
        return 0;
    }

    public boolean ItemDetailsIdIsRepeat(String ItemDetailsId) {
        if (!"".equals(ItemDetailsId)) {
            String sSql1 = "SELECT ItemDetailsId FROM CORE_ItemDetails WHERE ItemDetailsId=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, ItemDetailsId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean CodeIsRepeat(String Code) {
        if (!"".equals(Code)) {
            String sSql1 = "SELECT ItemDetailsId FROM CORE_ItemDetails WHERE Code=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, Code);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean CodeIsRepeat(String Code, String ItemDetailsId) {
        if (!"".equals(Code)) {
            String sSql1 = "SELECT ItemDetailsId FROM CORE_ItemDetails WHERE Code=? AND ItemDetailsId<>?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, Code, ItemDetailsId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean FullNameIsRepeat(String FullName) {
        if (!"".equals(FullName)) {
            String sSql1 = "SELECT FullName FROM CORE_ItemDetails WHERE FullName=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, FullName);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean FullNameIsRepeat(String FullName, String ItemDetailsId) {
        if (!"".equals(FullName)) {
            String sSql1 = "SELECT FullName FROM CORE_ItemDetails WHERE FullName=? AND ItemDetailsId<>?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, FullName, ItemDetailsId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }


    //验证添加
    public String VerifyAdd(ItemDetails itemDetails) {
        String msg = "";
        if("".equals(itemDetails.getItemDetailsId())||itemDetails.getItemDetailsId()==null){
           msg="数据字典明细表主键不能为空";
        }else if ("".equals(itemDetails.getItemsId())||itemDetails.getItemsId()==null) {
            msg = "数据字典主表主键不能为空";
        } else if ("".equals(itemDetails.getCode())||itemDetails.getCode()==null) {
            msg = "编号不能为空";
        } else if ("".equals(itemDetails.getFullName())||itemDetails.getFullName()==null) {
            msg = "名称不能为空";
        } else if (ItemDetailsIdIsRepeat(itemDetails.getItemDetailsId())) {
            msg = "数据字典主表主键已经存在";
        } else if (CodeIsRepeat(itemDetails.getCode())) {
            msg = "编号已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }

    //验证修改
    public String VerifyEdit(ItemDetails itemDetails) {
        String msg = "";
        if("".equals(itemDetails.getItemsId())||itemDetails.getItemsId()==null){
           msg="请选择数据字典类别";
        }else if ("".equals(itemDetails.getCode())||itemDetails.getCode()==null) {
            msg = "编号不能为空";
        } else if ("".equals(itemDetails.getFullName())||itemDetails.getFullName()==null) {
            msg = "名称不能为空";
        } else if (CodeIsRepeat(itemDetails.getCode(), itemDetails.getItemDetailsId())) {
            msg = "编号已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }

    //代码转名称
    public String CodeToFullName(String Code) {
        if (!"".equals(Code)) {
            String sSql1 = "SELECT FullName FROM CORE_ItemDetails WHERE Code=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, Code);
            if (objects != null) {
                return StrUtil.toStr(objects[0]);
            }
        }
        return "";
    }

    //代码转名称
    public String FullNameToCode(String ItemsName, String FullName) {
        //ItemsName 数据字典类型
        //FullName 数据字典明细名称
        if (!"".equals(ItemsName)&&!"".equals(FullName)) {
           String ItemsId=  itemsService.findByFullName(ItemsName);
            if(!"".equals(ItemsId)){
            String sSql1 = "SELECT Code FROM CORE_ItemDetails WHERE ItemsId=? AND FullName=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1,ItemsId, FullName);
            if (objects != null) {
                return StrUtil.toStr(objects[0]);
            }
            }
        }
        return "";
    }


    /*
* 查询
* */
    public List<ItemDetails> findAllEx(String ItemsId) {
        String sqlStr = "SELECT * FROM CORE_ItemDetails WHERE ItemsId=?  ORDER BY SortCode";
        return dbUtilsTemplate.find(ItemDetails.class, sqlStr,ItemsId);
    }

}
