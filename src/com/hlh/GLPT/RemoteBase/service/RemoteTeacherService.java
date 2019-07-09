package com.hlh.GLPT.RemoteBase.service;

import com.hlh.GLPT.RemoteBase.dao.RemoteTeacherDao;
import com.hlh.GLPT.RemoteBase.domain.RemoteTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package: com.hlh.GLPT.RemoteBase.service
 * User: 黄良辉  16-4-24  上午9:27
 */
@Service
public class RemoteTeacherService {
    @Autowired
    private RemoteTeacherDao remoteTeacherDao;

    /**
     * 查询
     *
     * @param fName  字段名  如果null时是模糊查询，否则为精确查询
     * @param fValue 字段值
     * @return
     */
    public List<RemoteTeacher> findByList(String fName, String fValue) {
        return remoteTeacherDao.findByList(fName, fValue);
    }


    /**
     * 分页查询
     *
     * @param fName  字段名  如果null时是模糊查询，否则为精确查询
     * @param fValue 字段值
     * @param start  开始页号
     * @param number 页行数
     * @return
     */
    public String findByPage(String fName, String fValue, int start, int number) {
        return remoteTeacherDao.findByPage(fName, fValue, start, number);
    }

    /**
     * 获取总行数
     *
     * @param fName  字段名  如果null时是模糊查询，否则为精确查询
     * @param fValue 字段值
     * @return
     */
    public int GetRowCount(String fName, String fValue) {
        return remoteTeacherDao.GetRowCount(fName, fValue);
    }
}
