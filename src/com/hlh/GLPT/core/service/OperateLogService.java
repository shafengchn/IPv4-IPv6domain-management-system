package com.hlh.GLPT.core.service;

import com.hlh.GLPT.core.dao.OperateLogDao;
import com.hlh.GLPT.core.domain.OperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: 黄良辉
 * Date: 14-8-31
 * Time: 下午8:36
 */
@Service
public class OperateLogService {
    @Autowired
    private OperateLogDao operateLogDao;

    /*
  * 增加
  * */
    public String add(OperateLog operateLog) {
        return operateLogDao.add(operateLog);
    }

    /*
     * 彻底删除
     * */
    public String ThoroughDel(int ID) {
        return operateLogDao.ThoroughDel(ID);
    }

    /*
    * 按Id查询
    * */
    public OperateLog findById(String ID) {
        return operateLogDao.findById(ID);
    }

    //获取总行数
    public int GetRowCount() {
        return operateLogDao.GetRowCount();
    }

    /*
 * 查询
 * */
    public List<OperateLog> findAll() {
        return operateLogDao.findAll();
    }

    public String findAll(String ShowFieldName, String[] strings) {
        return operateLogDao.findAll(ShowFieldName, strings);
    }

    //分页
    public String findByPage(String findValue, int start, int number) {
        return operateLogDao.findByPage(findValue, start, number);
    }

    //获取总行数(带查询参数)
    public int GetRowCount(String findValue) {
        return operateLogDao.GetRowCount(findValue);
    }
}
