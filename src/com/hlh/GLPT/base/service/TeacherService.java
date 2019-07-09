package com.hlh.GLPT.base.service;

import com.hlh.GLPT.base.dao.TeacherDao;
import com.hlh.GLPT.base.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Package: com.hlh.GLPT.base.service
 * User: 黄良辉  16-4-24  上午11:20
 */
@Service
public class TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    /**
     * 添加
     *
     * @param teacher
     * @return
     */
    public String add(Teacher teacher) {
        return teacherDao.add(teacher);
    }


    /**
     * 修改
     *
     * @param teacher
     * @return
     */
    public String edit(Teacher teacher) {
        return teacherDao.edit(teacher);
    }

    /**
     * 查询一条记录
     *
     * @param Code
     * @return
     */
    public Teacher findByCode(String Code) {
        return teacherDao.findByCode(Code);
    }

    public boolean CodeIsRepeat(String code) {
        return teacherDao.CodeIsRepeat(code);
    }

    public boolean CodeIsRepeat(String code, String Id) {
        return teacherDao.CodeIsRepeat(code, Id);
    }


    //验证添加
    public String VerifyAdd(Teacher teacher) {
        return teacherDao.VerifyAdd(teacher);
    }
}
