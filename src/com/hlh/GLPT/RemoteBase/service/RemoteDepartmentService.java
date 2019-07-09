package com.hlh.GLPT.RemoteBase.service;

import com.hlh.GLPT.RemoteBase.dao.RemoteDepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Package: com.hlh.GLPT.RemoteBase.service
 * User: 黄良辉  16-4-24  下午5:18
 */
@Service
public class RemoteDepartmentService {
    @Autowired
    private RemoteDepartmentDao remoteDepartmentDao;

    /**
     * 查询
     * @param fName  字段名  如果null时是模糊查询，否则为精确查询
     * @param fValue 字段值
     * @return
     */
    public String findByData(String fName, String fValue) {
        return remoteDepartmentDao.findByData(fName, fValue);
    }

    //部门代码转部门名称
    public String DepartmentCodeToDepartmentName(String DepartmentCode) {
        return remoteDepartmentDao.DepartmentCodeToDepartmentName(DepartmentCode);
    }
}
