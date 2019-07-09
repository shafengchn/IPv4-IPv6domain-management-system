package com.hlh.GLPT.RemoteBase.dao;

import com.hlh.util.DbUtilsTemplate_Oracle;
import com.hlh.util.StrUtil;
import com.hlh.util.jsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Package: com.hlh.GLPT.RemoteBase.dao
 * User: 黄良辉  16-4-23  下午11:16
 */
@Repository
public class RemoteDepartmentDao {
    @Autowired
    private DbUtilsTemplate_Oracle dbUtilsTemplateOracle;


    /**
     * 查询
     *
     * @param fName  字段名  如果null时是模糊查询，否则为精确查询
     * @param fValue 字段值
     * @return
     */
    public String findByData(String fName, String fValue) {
        String sSql = "";
        List tmpList1 = null;
        if (fName == null) {
            sSql = "select DISTINCT YXDM as Code,YXMC as FullName FROM VIEW_TEACHER_YMGLXT WHERE YXDM LIKE ? order by YXDM";
            Object[] ObjVal = new Object[]{"%" + fValue + "%"};
            tmpList1 = dbUtilsTemplateOracle.executeQueryList(sSql, ObjVal);
        } else {
            sSql = "select DISTINCT YXDM as Code,YXMC as FullName FROM VIEW_TEACHER_YMGLXT WHERE " + fName + "= ? order by YXDM";
            Object[] ObjVal = new Object[]{"%" + fValue + "%"};
            tmpList1 = dbUtilsTemplateOracle.executeQueryList(sSql, ObjVal);
        }
        String[] strings1 = new String[]{"Code", "FullName"};
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    //部门代码转部门名称
    public String DepartmentCodeToDepartmentName(String DepartmentCode) {
        String sSql1 = "SELECT DISTINCT YXMC FROM VIEW_TEACHER_YMGLXT WHERE YXDM=?";
        Object[] objects = dbUtilsTemplateOracle.executeQueryObject(sSql1, DepartmentCode);
        if (objects != null) {
            if (objects.length > 0)
                return StrUtil.toStr(objects[0]);
        }
        return "";
    }

}
