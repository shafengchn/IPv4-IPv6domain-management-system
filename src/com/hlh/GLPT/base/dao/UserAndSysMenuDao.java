package com.hlh.GLPT.base.dao;

import com.hlh.GLPT.base.domain.UserAndSysMenu;
import com.hlh.GLPT.core.domain.UserRole;
import com.hlh.GLPT.core.service.RoleAuthorityService;
import com.hlh.GLPT.core.service.UserAuthorityService;
import com.hlh.GLPT.core.service.UserRoleService;
import com.hlh.KZ.domain.SysMenu;
import com.hlh.util.DbUtilsTemplate;
import com.hlh.util.StrUtil;
import com.hlh.util.jsonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 教师执教班级
 * User: 黄良辉
 * Date: 14-5-30
 * Time: 上午9:58
 */
@Repository
public class UserAndSysMenuDao {
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;
    @Autowired
    private RoleAuthorityService roleAuthorityService;
    @Autowired
    private UserAuthorityService userAuthorityService;
    @Autowired
    private UserRoleService userRoleService;

    /*
    * 增加
    * */
    public String add(UserAndSysMenu userAndSysMenu) {
        String sqlStr = "INSERT INTO base_UserAndSysMenu (UserCode,SysMenuCode,Enabled,SortCode,CreateDate" +
                ",CreateUserCode,CreateUserName)" +
                " VALUES(?,?,?,?,?" +
                ",?,?)";
        Object[] objects = new Object[]{userAndSysMenu.getUserCode(), userAndSysMenu.getSysMenuCode(), userAndSysMenu.isEnabled(), userAndSysMenu.getSortCode(), userAndSysMenu.getCreateDate()
                , userAndSysMenu.getCreateUserCode(), userAndSysMenu.getCreateUserName()};
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
        String sqlStr = "DELETE FROM base_UserAndSysMenu  WHERE ID=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{ID});
        if (RValue1 > 0) {
            return "OK";
        }
        return "彻底删除操作失败,请联系管理员";
    }

    /*
     * 彻底删除
     * */
    public String ThoroughByUserCodeDel(String UserCode) {
        String sqlStr = "DELETE FROM base_UserAndSysMenu  WHERE UserCode=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{UserCode});
        if (RValue1 > 0) {
            return "OK";
        }
        return "彻底删除操作失败,请联系管理员";
    }

    /*
    * 删除
    * */
    public String del(int ID, Date ModifyDate, String ModifyUserCode, String ModifyUserName) {
        String sqlStr = "UPDATE Base_UserAndSysMenu SET DeleteMark=?,ModifyDate=?,ModifyUserCode=?,ModifyUserName=? WHERE ID=?";
        long RValue1 = dbUtilsTemplate.update(sqlStr, new Object[]{true, ModifyDate, ModifyUserCode, ModifyUserName, ID});
        if (RValue1 > 0) {
            return "OK";
        }
        return "删除操作失败,请联系管理员";
    }

    /*
    * 修改
    * */
    public String edit(UserAndSysMenu userAndSysMenu) {
        String sqlStr = "UPDATE Base_UserAndSysMenu SET UserCode=?,SysMenuCode=?,Enabled=?,SortCode=?,ModifyDate=?,ModifyUserCode=?" +
                ",ModifyUserName=? WHERE ID=?";
        Object[] objects = new Object[]{userAndSysMenu.getUserCode(), userAndSysMenu.getSysMenuCode(), userAndSysMenu.isEnabled()
                , userAndSysMenu.getSortCode(), userAndSysMenu.getModifyDate(), userAndSysMenu.getModifyUserCode()
                , userAndSysMenu.getModifyUserName(), userAndSysMenu.getID()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "修改操作失败,请联系管理员";
    }

    /*
    * 按Id查询
    * */
    public UserAndSysMenu findById(int ID) {
        String sqlStr = "SELECT * FROM Base_UserAndSysMenu WHERE ID=?";
        return dbUtilsTemplate.findFirst(UserAndSysMenu.class, sqlStr, ID);
    }

    //获取总行数
    public int GetRowCount() {
        String sSql1 = "SELECT COUNT(ID) as RowCount FROM Base_UserAndSysMenu";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1);
        if (objects.length > 0) {
            return Integer.parseInt(objects[0].toString());
        }
        return 0;
    }

    /*
 * 查询
 * */
    public List<UserAndSysMenu> findAll() {
        String sqlStr = "SELECT * FROM Base_UserAndSysMenu ORDER BY SortCode";
        return dbUtilsTemplate.find(UserAndSysMenu.class, sqlStr);
    }

    public String findAll(String ShowFieldName, String[] strings) {
        String sqlStr = "SELECT " + ShowFieldName + " FROM Base_UserAndSysMenu ORDER BY SortCode";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sqlStr);
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings);
        return tmpStr1;
    }

    //分页
    public String findByPage(String findValue, int start, int number) {
        String sSql1 = "SELECT ID,UserCode" +
                ",(SELECT FullName FROM Base_Teacher WHERE Code=Base_UserAndSysMenu.UserCode) as TeacherName" +
                ",SysMenuCode,(SELECT FullName FROM Base_Class WHERE Code=Base_UserAndSysMenu.SysMenuCode) as ClassName" +
                ",Enabled,SortCode,DeleteMark" +
                " FROM Base_UserAndSysMenu"
                + " WHERE UserCode LIKE ? OR SysMenuCode LIKE ?"
                + " ORDER BY SortCode LIMIT ?,?";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sSql1, "%" + findValue + "%", "%" + findValue + "%", start, number);
        String[] strings1 = new String[]{"ID", "UserCode", "TeacherName"
                , "SysMenuCode", "ClassName", "Enabled", "SortCode", "DeleteMark"};
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    //获取总行数(带查询参数)
    public int GetRowCount(String findValue) {
        String sSql1 = "SELECT COUNT(ID) as RowCount FROM Base_UserAndSysMenu"
                + " WHERE UserCode LIKE ? OR SysMenuCode LIKE ?";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, "%" + findValue + "%", "%" + findValue + "%");
        if (objects.length > 0) {
            return Integer.parseInt(objects[0].toString());
        }
        return 0;
    }

    //分页(查询教师所属部门）
    public String findUserByPage(String UserCode, String findValue, int start, int number) {
        String sSql1 = "SELECT ID,UserCode" +
                ",(SELECT FullName FROM Base_Teacher WHERE Code=Base_UserAndSysMenu.UserCode) as TeacherName" +
                ",SysMenuCode,(SELECT FullName FROM Base_Class WHERE Code=Base_UserAndSysMenu.SysMenuCode) as ClassName" +
                ",Enabled,SortCode,DeleteMark" +
                " FROM Base_UserAndSysMenu"
                + " WHERE UserCode= ? AND SysMenuCode LIKE ?"
                + " ORDER BY SortCode LIMIT ?,?";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sSql1, UserCode, "%" + findValue + "%", start, number);
        String[] strings1 = new String[]{"ID", "UserCode", "TeacherName"
                , "SysMenuCode", "ClassName", "Enabled", "SortCode", "DeleteMark"};
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    //获取总行数(查询教师所属部门)(带查询参数)
    public int GetUserRowCount(String UserCode, String findValue) {
        String sSql1 = "SELECT COUNT(ID) as RowCount FROM Base_UserAndSysMenu"
                + " WHERE UserCode = ? AND SysMenuCode LIKE ?";
        Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, UserCode, "%" + findValue + "%");
        if (objects.length > 0) {
            return Integer.parseInt(objects[0].toString());
        }
        return 0;
    }

    //记录是否重复
    public boolean RecordIsRepeat(String UserCode, String SysMenuCode) {
        if (!"".equals(UserCode) && !"".equals(SysMenuCode)) {
            String sSql1 = "SELECT ID FROM Base_UserAndSysMenu WHERE UserCode=? AND SysMenuCode=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, UserCode, SysMenuCode);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    //记录是否重复
    public boolean RecordIsRepeat(String UserCode, String SysMenuCode, int ID) {
        if (!"".equals(UserCode) && !"".equals(SysMenuCode)) {
            String sSql1 = "SELECT ID FROM Base_UserAndSysMenu WHERE UserCode=? AND SysMenuCode=? AND ID<>?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, UserCode, SysMenuCode, ID);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    //验证添加
    public String VerifyAdd(UserAndSysMenu userAndSysMenu) {
        String msg = "";
        if ("".equals(userAndSysMenu.getUserCode()) || userAndSysMenu.getUserCode() == null) {
            msg = "请选择教师";
        } else if ("".equals(userAndSysMenu.getSysMenuCode()) || userAndSysMenu.getSysMenuCode() == null) {
            msg = "请选择班级";
        } else if (RecordIsRepeat(userAndSysMenu.getUserCode(), userAndSysMenu.getSysMenuCode())) {
            msg = "班级已经设置过了,不能重复设置";
        } else {
            msg = "OK";
        }
        return msg;
    }

    //验证修改
    public String VerifyEdit(UserAndSysMenu userAndSysMenu) {
        String msg = "";
        if ("".equals(userAndSysMenu.getUserCode()) || userAndSysMenu.getUserCode() == null) {
            msg = "请选择教师";
        } else if ("".equals(userAndSysMenu.getSysMenuCode()) || userAndSysMenu.getSysMenuCode() == null) {
            msg = "请选择班级";
        } else if (RecordIsRepeat(userAndSysMenu.getUserCode(), userAndSysMenu.getSysMenuCode(), userAndSysMenu.getID())) {
            msg = "班级已经设置过了,不能重复设置";
        } else {
            msg = "OK";
        }
        return msg;
    }

    //按教师代码获取系统功能(以,号间隔)
    public String UserCodeToSysMenu(String UserCode) {
        if (!"".equals(UserCode)) {
            String sSql1 = "SELECT SysMenuCode FROM Base_UserAndSysMenu WHERE UserCode=?";
            List tmpList1 = dbUtilsTemplate.executeQueryList(sSql1, UserCode);
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

    //查询所有数据并返回树结构
    public JSONArray FindAllTreeJSONData(String UserCode) {
        // List<UserRole> userRoleList = userRoleService.FindUserRole(UserCode);
        JSONArray jsonArray = new JSONArray();
        String sqlStr = "SELECT * FROM KZ_SysMenu WHERE ParentId='0' AND Code='1000'";
        List<SysMenu> sysMenuList = dbUtilsTemplate.find(SysMenu.class, sqlStr);
        if (sysMenuList.size() > 0) {
            SysMenu sysMenu = sysMenuList.get(0);
            boolean BValue1 = false;
            // for (UserRole userRole : userRoleList) {
            //    if (roleAuthorityService.findRoleAuthorityIsCheck(userRole.getRoleCode(), sysMenu.getCode()) == true) {
            JSONObject jsonObject = new JSONObject();
            //jsonObject.put("id", sysMenu.getMenuId());
            jsonObject.put("id", sysMenu.getCode());
            jsonObject.put("text", sysMenu.getFullName());
            jsonObject.put("category", sysMenu.getCategory());
            if (RecordIsRepeat(UserCode, sysMenu.getCode()) == true) {
                jsonObject.put("checked", "checked='checked'");
            } else {
                jsonObject.put("checked", "");
            }
            jsonObject.put("children", FindAuthorityChildNode(UserCode, sysMenu.getMenuId()));
            jsonArray.add(jsonObject);
            BValue1 = true;
            //          break;
            //      }
            //  }
        /*    if (BValue1 == false) {
                if (userAuthorityService.findUserAuthorityIsCheck(UserCode, sysMenu.getCode()) == true) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", sysMenu.getMenuId());
                    jsonObject.put("code", sysMenu.getCode());
                    jsonObject.put("text", sysMenu.getFullName());
                    jsonObject.put("category", sysMenu.getCategory());
                    if (RecordIsRepeat(UserCode, sysMenu.getCode()) == true) {
                        jsonObject.put("checked", "checked='checked'");
                    } else {
                        jsonObject.put("checked", "");
                    }
                    jsonObject.put("children", FindAuthorityChildNode(UserCode, sysMenu.getMenuId()));
                    jsonArray.add(jsonObject);
                }
            }*/
        }
        return jsonArray;
    }

    //查询子节点
    public JSONArray FindAuthorityChildNode(String UserCode, String ParentId) {
        List<UserRole> userRoleList = userRoleService.FindUserRole(UserCode);
        JSONArray jsonArray = new JSONArray();
        String sqlStr = "SELECT * FROM KZ_SysMenu WHERE ParentId=? AND DeleteMark=false AND Enabled=true order by SortCode";
        List<SysMenu> sysMenuList = dbUtilsTemplate.find(SysMenu.class, sqlStr, ParentId);
        if (sysMenuList.size() > 0) {
            for (SysMenu sysMenu : sysMenuList) {
                boolean BValue1 = false;
                for (UserRole userRole : userRoleList) {
                    if (roleAuthorityService.findRoleAuthorityIsCheck(userRole.getRoleCode(), sysMenu.getCode()) == true) {
                        JSONObject jsonObject = new JSONObject();
                        //jsonObject.put("id", sysMenu.getMenuId());
                        jsonObject.put("id", sysMenu.getCode());
                        jsonObject.put("text", sysMenu.getFullName());
                        jsonObject.put("category", sysMenu.getCategory());
                        //jsonObject.put("state","closed");
                        if (RecordIsRepeat(UserCode, sysMenu.getCode()) == true) {
                            jsonObject.put("checked", "checked='checked'");
                        } else {
                            jsonObject.put("checked", "");
                        }
                        jsonObject.put("children", FindAuthorityChildNode(UserCode, sysMenu.getMenuId()));
                        BValue1 = true;
                        jsonArray.add(jsonObject);
                        break;
                    }
                }
                if (BValue1 == false) {
                    if (userAuthorityService.findUserAuthorityIsCheck(UserCode, sysMenu.getCode()) == true) {
                        JSONObject jsonObject = new JSONObject();
                        //jsonObject.put("id", sysMenu.getMenuId());
                        jsonObject.put("id", sysMenu.getCode());
                        jsonObject.put("text", sysMenu.getFullName());
                        jsonObject.put("category", sysMenu.getCategory());
                        if (RecordIsRepeat(UserCode, sysMenu.getCode()) == true) {
                            jsonObject.put("checked", "checked='checked'");
                        } else {
                            jsonObject.put("checked", "");
                        }
                        jsonObject.put("children", FindAuthorityChildNode(UserCode, sysMenu.getMenuId()));
                        jsonArray.add(jsonObject);
                    }
                }

            }
            return jsonArray;
        }
        return null;
    }

    //按UserCode获取系统功能的选中情况
    public JSONArray GetUserAndSysMenuChecked(String UserCode) {
        return FindAllTreeJSONData(UserCode);
    }

    //获取用户选中的系统功能
    public JSONArray GetUserSelectSysMenu(String UserCode) {
        JSONArray jsonArray = new JSONArray();
        String sSql1="SELECT a.ID,a.UserCode,b.MenuId,b.NavigateUrl,b.FullName,b.Img,b.Description FROM base_userandsysmenu as a,kz_sysmenu as b WHERE a.SysMenuCode=b.code AND a.UserCode=? order by a.SortCode";
        List tmpList1 = dbUtilsTemplate.executeQueryList(sSql1, UserCode);
        if(tmpList1!=null){
            for(int i1=0;i1<tmpList1.size();i1++){
                Object[] objects=(Object[])tmpList1.get(i1);
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("ID",StrUtil.toStr(objects[0]));
                jsonObject.put("UserCode",StrUtil.toStr(objects[1]));
                jsonObject.put("MenuId",StrUtil.toStr(objects[2]));
                jsonObject.put("NavigateUrl",StrUtil.toStr(objects[3]));
                jsonObject.put("FullName",StrUtil.toStr(objects[4]));
                jsonObject.put("Img",StrUtil.toStr(objects[5]));
                jsonObject.put("Description",StrUtil.toStr(objects[6]));
                jsonArray.add(jsonObject);
            }
        }
        return jsonArray;
    }
}
