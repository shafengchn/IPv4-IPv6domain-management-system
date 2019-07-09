package com.hlh.GLPT.base.dao;

import com.hlh.GLPT.base.domain.Teacher;
import com.hlh.util.DbUtilsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Package: com.hlh.GLPT.base.dao
 * User: 黄良辉  16-4-24  上午10:39
 */
@Repository
public class TeacherDao {
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;

    /**
     * 添加
     * @param teacher
     * @return
     */
    public String add(Teacher teacher) {
        String sqlStr = "INSERT INTO base_Teacher (Code,FullName,Mobile,Phone,Email" +
                ",IdTypeCode,IdNumber,IdImgPath1,IdImgPath2,IdImgPath3)" +
                " VALUES(?,?,?,?,?" +
                ",?,?,?,?,?)";
        Object[] objects = new Object[]{teacher.getCode(),teacher.getFullName(),teacher.getMobile(),teacher.getPhone(),teacher.getEmail()
        ,teacher.getIdTypeCode(),teacher.getIdNumber(),teacher.getIdImgPath1(),teacher.getIdImgPath2(),teacher.getIdImgPath3()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "添加操作失败,请联系管理员";
    }


    /**
     * 修改
     * @param teacher
     * @return
     */
    public String edit(Teacher teacher) {
        String sqlStr = "UPDATE Base_Teacher SET FullName=?,Mobile=?,Phone=?,Email=?,IdTypeCode=?" +
                ",IdNumber=?,IdImgPath1=?,IdImgPath2=?,IdImgPath3=? WHERE Code=?";
        Object[] objects = new Object[]{teacher.getFullName(),teacher.getMobile(),teacher.getPhone(),teacher.getIdTypeCode()
        ,teacher.getIdNumber(),teacher.getIdImgPath1(),teacher.getIdImgPath2(),teacher.getIdImgPath3()};
        long RValue1 = dbUtilsTemplate.update(sqlStr, objects);
        if (RValue1 > 0) {
            return "OK";
        }
        return "修改操作失败,请联系管理员";
    }

    /**
     * 查询一条记录
     * @param Code
     * @return
     */
    public Teacher findByCode(String Code) {
        String sqlStr = "SELECT * FROM Base_Teacher WHERE Code=? LIMIT 1";
        return dbUtilsTemplate.findFirst(Teacher.class, sqlStr, Code);
    }

    public boolean CodeIsRepeat(String code) {
        if (!"".equals(code)) {
            String sSql1 = "SELECT Id FROM Base_Teacher WHERE Code=?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, code);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }

    public boolean CodeIsRepeat(String code, String Id) {
        if (!"".equals(code)) {
            String sSql1 = "SELECT Id FROM Base_Teacher WHERE Code=? AND Id<>?";
            Object[] objects = dbUtilsTemplate.executeQueryObject(sSql1, code, Id);
            if (objects != null) {
                return true;
            }
        }
        return false;
    }


    //验证添加
    public String VerifyAdd(Teacher teacher) {
        String msg = "";
         if ("".equals(teacher.getCode()) || teacher.getCode() == null) {
            msg = "教工号不能为空";
        } else if ("".equals(teacher.getFullName()) || teacher.getFullName() == null) {
            msg = "姓名不能为空";
        } else if (CodeIsRepeat(teacher.getCode())) {
            msg = "教工信息已经存在";
        } else {
            msg = "OK";
        }
        return msg;
    }
}
