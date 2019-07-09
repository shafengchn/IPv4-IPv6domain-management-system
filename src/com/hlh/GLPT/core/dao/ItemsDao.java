package com.hlh.GLPT.core.dao;

import com.hlh.GLPT.core.domain.Items;
import com.hlh.util.DbUtilsTemplate;
import com.hlh.util.StrUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
public class ItemsDao {
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;

    /*
    * 增加
    * */
    public String add(Items items) {
        String sqlStr = "INSERT INTO CORE_Items (ItemsId,ParentId,Code,FullName,Category" +
                ",Enabled,SortCode,CreateDate,CreateUserCode,CreateUserName)" +
                " VALUES(?,?,?,?,?" +
                ",?,?,?,?,?)";
        Object[] objects = new Object[]{items.getItemsId(), items.getParentId(), items.getCode(), items.getFullName(), items.getCategory()
                , items.isEnabled(), items.getSortCode(), items.getCreateDate(), items.getCreateUserCode(), items.getCreateUserName()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "添加操作失败,请联系管理员";
    }

    /*
     * 彻底删除
     * */
    public String ThoroughDel(String ItemsId) {
        String sqlStr = "DELETE FROM CORE_Items  WHERE ItemsId=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{ItemsId});
        if (RValue1 > 0) {
            return "OK";
        }
        return "彻底删除操作失败,请联系管理员";
    }

    /*
    * 删除
    * */
    public String del(String ItemsId, Date ModifyDate, String ModifyUserCode, String ModifyUserName) {
        String sqlStr = "UPDATE CORE_Items SET DeleteMark=?,ModifyDate=?,ModifyUserCode=?,ModifyUserName=? WHERE ItemsId=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{true, ModifyDate, ModifyUserCode, ModifyUserName, ItemsId});
        if (RValue1 > 0) {
            return "OK";
        }
        return "删除操作失败,请联系管理员";
    }

    /*
    * 修改
    * */
    public String edit(Items items) {
        String sqlStr = "UPDATE CORE_Items SET ParentId=?,Code=?,FullName=?,Category=?,Enabled=?,SortCode=?" +
                ",ModifyDate=?,ModifyUserCode=?,ModifyUserName=? WHERE ItemsId=?";
        Object[] objects = new Object[]{items.getParentId(), items.getCode(), items.getFullName(), items.getCategory(), items.isEnabled(), items.getSortCode()
                , items.getModifyDate(), items.getModifyUserCode(), items.getModifyUserName(), items.getItemsId()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "修改操作失败,请联系管理员";
    }

    /*
    * 按Id查询
    * */
    public Items findById(String ItemsId) {
        String sqlStr = "SELECT * FROM CORE_Items WHERE ItemsId=?";
        return dbUtilsTemplate.findFirst(Items.class, sqlStr, ItemsId);
    }

    /*
    * 按数据字典主表的名称返回数据字典主表主键
    * */
    public String findByFullName(String fullName) {
        String sqlStr = "SELECT ItemsId FROM CORE_Items WHERE FullName=?";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sqlStr, fullName);
        if (objects != null){
            if(objects.length > 0) {
                return objects[0].toString();
            }
        }
        return "";
    }
    /*
    * 按数据字典主表的代码返回数据字典主表主键
    * */
    public String findByCode(String Code) {
        String sqlStr = "SELECT ItemsId FROM CORE_Items WHERE Code=?";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sqlStr, Code);
        if (objects != null){
            if(objects.length > 0) {
            return objects[0].toString();
            }
        }
        return "";
    }

    //获取总行数
    public int GetRowCount() {
        String sSql1 = "SELECT COUNT(ItemsId) as RowCount FROM CORE_Items";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1);
        if (objects.length > 0) {
            return Integer.parseInt(objects[0].toString());
        }
        return 0;
    }

    /*
    * 按分类查询树型数据
    * */
    public String findAllSimpleTreeData() {
        String sqlStr = "SELECT DISTINCT Category FROM CORE_Items ORDER BY Category";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sqlStr);
        JSONArray jsonArray = new JSONArray();
        for (int i1 = 0; i1 < tmpList1.size(); i1++) {
            Object[] objects = (Object[]) tmpList1.get(i1);
            String Category1 = StrUtil.toStr(objects[0]);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", i1 + 1);
            jsonObject.put("text", Category1);
            sqlStr = "SELECT * FROM CORE_Items WHERE Category=? AND (ParentId='0' OR ParentId='' OR ParentId is NULL) ORDER BY SortCode";
            List<Items> itemsList = dbUtilsTemplate.find(Items.class, sqlStr, Category1);
            if (itemsList.size() > 0) {
                JSONArray jsonArray1 = new JSONArray();
                for (Items items : itemsList) {
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("id", items.getItemsId());
                    jsonObject1.put("text", items.getFullName());
                    sqlStr = "SELECT * FROM CORE_Items WHERE ParentId=?  order by SortCode";
                    List<Items> itemsList1 = dbUtilsTemplate.find(Items.class, sqlStr, items.getItemsId());
                    if (itemsList1.size() > 0) {
                        JSONArray jsonArray2 = new JSONArray();
                        for (Items items1 : itemsList1) {
                            JSONObject jsonObject2 = new JSONObject();
                            jsonObject2.put("id", items1.getItemsId());
                            jsonObject2.put("text", items1.getFullName());
                            jsonObject2.put("children", FindSimpleChildNode(items1.getItemsId()));
                            jsonArray2.add(jsonObject2);
                        }
                    }
                    jsonArray1.add(jsonObject1);
                }
                jsonObject.put("children", jsonArray1);
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }

    //查询子节点
    public JSONArray FindSimpleChildNode(String ParentId) {
        JSONArray jsonArray = new JSONArray();
        String sqlStr = "SELECT * FROM CORE_Items WHERE ParentId=?  order by SortCode";
        List<Items> itemsList = dbUtilsTemplate.find(Items.class, sqlStr, ParentId);
        if (itemsList.size() > 0) {
            for (Items items : itemsList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", items.getItemsId());
                jsonObject.put("text", items.getFullName());
                jsonObject.put("children", FindSimpleChildNode(items.getItemsId()));
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        }
        return null;
    }

    public boolean ItemsIdIsRepeat(String ItemsId) {
        if (!"".equals(ItemsId)) {
            String sSql1 = "SELECT ItemsId FROM CORE_Items WHERE ItemsId=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, ItemsId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean CodeIsRepeat(String Code) {
        if (!"".equals(Code)) {
            String sSql1 = "SELECT ItemsId FROM CORE_Items WHERE Code=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, Code);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean CodeIsRepeat(String Code, String ItemsId) {
        if (!"".equals(Code)) {
            String sSql1 = "SELECT ItemsId FROM CORE_Items WHERE Code=? AND ItemsId<>?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, Code, ItemsId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean FullNameIsRepeat(String FullName) {
        if (!"".equals(FullName)) {
            String sSql1 = "SELECT FullName FROM CORE_Items WHERE FullName=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, FullName);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean FullNameIsRepeat(String FullName, String ItemsId) {
        if (!"".equals(FullName)) {
            String sSql1 = "SELECT FullName FROM CORE_Items WHERE FullName=? AND ItemsId<>?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, FullName, ItemsId);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }


    //验证添加
    public String VerifyAdd(Items items) {
        String msg = "";
        if ("".equals(items.getItemsId())) {
            msg = "数据字典主表主键不能为空";
        } else if ("".equals(items.getCode())) {
            msg = "编号不能为空";
        } else if ("".equals(items.getFullName())) {
            msg = "名称不能为空";
        } else if (ItemsIdIsRepeat(items.getItemsId())) {
            msg = "数据字典主表主键已经存在";
        } else if (CodeIsRepeat(items.getCode())) {
            msg = "编号已经存在";
        } else if (FullNameIsRepeat(items.getFullName())) {
            msg = "名称已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }

    //验证修改
    public String VerifyEdit(Items items) {
        String msg = "";
        if ("".equals(items.getCode())) {
            msg = "编号不能为空";
        } else if ("".equals(items.getFullName())) {
            msg = "名称不能为空";
        } else if (CodeIsRepeat(items.getCode(), items.getItemsId())) {
            msg = "编号已经存在";
        } else if (FullNameIsRepeat(items.getFullName(), items.getItemsId())) {
            msg = "名称已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }
}
