package com.hlh.GLPT.RemoteBase.dao;

import com.hlh.GLPT.RemoteBase.domain.RemoteTeacher;
import com.hlh.util.DbUtilsTemplate_Oracle;
import com.hlh.util.jsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Package: com.hlh.GLPT.RemoteBase.dao
 * User: 黄良辉  16-4-23  下午11:16
 */
@Repository
public class RemoteTeacherDao {
    @Autowired
    private DbUtilsTemplate_Oracle dbUtilsTemplateOracle;


    /**
     * 查询
     *
     * @param fName  字段名  如果null时是模糊查询，否则为精确查询
     * @param fValue 字段值
     * @return
     */
    public List<RemoteTeacher> findByList(String fName, String fValue) {
        String sSql = "";
        if (fName == null) {
            sSql = "select row_number() over(order by XM) as rownumber,t.ZGH as Code,t.XM as FullName,t.ZJH as IdNumber,t.SJ as Mobile,t.YXDM as DepartmentCode,t.YXMC as DepartmentName" +
                    " from(VIEW_TEACHER_YMGLXT) t" +
                    " WHERE (t.ZGH is not null OR t.ZGH<>'') AND (t.XM LIKE '%" + fValue + "%' OR ZJH LIKE '%" + fValue + "%' OR ZJH LIKE '%" + fValue + "%' OR SJ LIKE '%" + fValue + "%' OR YXDM LIKE '%" + fValue + "%' OR YXMC LIKE '%" + fValue + "%')";
        } else {
            sSql = "select row_number() over(order by XM) as rownumber,t.ZGH as Code,t.XM as FullName,t.ZJH as IdNumber,t.SJ as Mobile,t.YXDM as DepartmentCode,t.YXMC as DepartmentName" +
                    " from(VIEW_TEACHER_YMGLXT) t" +
                    " WHERE (t.ZGH is not null OR t.ZGH<>'') AND " + fName + "='" + fValue + "'";
        }
        return dbUtilsTemplateOracle.find(RemoteTeacher.class, sSql);
    }


    /**
     * 分页查询
     * @param fName  字段名  如果null时是模糊查询，否则为精确查询
     * @param fValue 字段值
     * @param start  开始页号
     * @param number 页行数
     * @return
     */
    public String findByPage(String fName, String fValue, int start, int number) {
        int StartRowNum = start * number; //开始行号
        String sSql = "";
        List tmpList1 = null;
        if (fName == null) {
            sSql = "select * from(select * from(select row_number() over(order by ZGH) as rownumber,t.ZGH as Code,t.XM as FullName,t.ZJH as IdNumber,t.SJ as Mobile,t.YXDM as DepartmentCode,t.YXMC as DepartmentName" +
                    " from(VIEW_TEACHER_YMGLXT) t" +
                    " WHERE (t.ZGH is not null OR t.ZGH<>'') AND  (t.XM LIKE ? OR ZJH LIKE ? OR ZJH LIKE ? OR SJ LIKE ? OR YXDM LIKE ? OR YXMC LIKE ?)) p where p.rownumber>?) where rownum<=?";
            Object[] ObjVal = new Object[]{"%" + fValue + "%", "%" + fValue + "%", "%" + fValue + "%", "%" + fValue + "%", "%" + fValue + "%", "%" + fValue + "%", StartRowNum, number};
            tmpList1 = dbUtilsTemplateOracle.executeQueryList(sSql, ObjVal);
        } else {
            sSql = "select * from(select * from(select row_number() over(order by ZGH) as rownumber,t.ZGH as Code,t.XM as FullName,t.ZJH as IdNumber,t.SJ as Mobile,t.YXDM as DepartmentCode,t.YXMC as DepartmentName" +
                    " from(VIEW_TEACHER_YMGLXT) t" +
                    " WHERE (t.ZGH is not null OR t.ZGH<>'') AND " + fName + "=?) p where p.rownumber>?) where rownum<=?";
            Object[] ObjVal = new Object[]{"%" + fValue + "%", StartRowNum, number};
            tmpList1 = dbUtilsTemplateOracle.executeQueryList(sSql, ObjVal);
        }
        String[] strings1 = new String[]{"rownumber", "Code", "FullName", "IdNumber", "Mobile", "DepartmentCode", "DepartmentName"};
        String tmpStr1 = jsonUtil.ListToJson(tmpList1, strings1);
        return tmpStr1;
    }

    /**
     * 获取总行数
     * @param fName   字段名  如果null时是模糊查询，否则为精确查询
     * @param fValue  字段值
     * @return
     */
    public int GetRowCount(String fName, String fValue) {
        String sSql = "";
        Object[] ObjVal = null;
        if (fName == null) {
            sSql = "select COUNT(t.ZGH) as RowCount from(VIEW_TEACHER_YMGLXT) t WHERE (t.ZGH is not null OR t.ZGH<>'') AND (t.XM LIKE ? OR ZJH LIKE ? OR ZJH LIKE ? OR SJ LIKE ? OR YXDM LIKE ? OR YXMC LIKE ?)";
            ObjVal = new Object[]{"%" + fValue + "%", "%" + fValue + "%", "%" + fValue + "%", "%" + fValue + "%", "%" + fValue + "%", "%" + fValue + "%"};
        } else {
            sSql = "select COUNT(t.ZGH) as RowCount from(VIEW_TEACHER_YMGLXT) t WHERE (t.ZGH is not null OR t.ZGH<>'') AND " + fName + "=?";
            ObjVal = new Object[]{"%" + fValue + "%"};
        }
        Object[] tmpObj1 = dbUtilsTemplateOracle.executeQueryObject(sSql, ObjVal);
        if (tmpObj1 != null) {
            if (tmpObj1.length > 0) {
                if (tmpObj1[0] != null) {
                    return Integer.parseInt(tmpObj1[0].toString());
                }
            }
        }
        return 0;
    }

}
