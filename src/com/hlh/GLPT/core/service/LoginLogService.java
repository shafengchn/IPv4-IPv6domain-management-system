package com.hlh.GLPT.core.service;

import com.hlh.GLPT.core.dao.LoginLogDao;
import com.hlh.GLPT.core.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: 黄良辉
 * Date: 14-9-1
 * Time: 下午8:18
 */
@Service
public class LoginLogService {
    @Autowired
    private LoginLogDao loginLogDao;

    /*
   * 增加
   * */
    public String add(LoginLog loginLog) {
        return loginLogDao.add(loginLog);
    }

    /*
     * 彻底删除
     * */
    public String ThoroughDel(int ID) {
        return loginLogDao.ThoroughDel(ID);
    }

    /*
    * 按Id查询
    * */
    public LoginLog findById(String ID) {
        return loginLogDao.findById(ID);
    }

    //获取总行数
    public int GetRowCount() {
        return loginLogDao.GetRowCount();
    }

    /*
 * 查询
 * */
    public List<LoginLog> findAll() {
        return loginLogDao.findAll();
    }

    public String findAll(String ShowFieldName, String[] strings) {
        return loginLogDao.findAll(ShowFieldName, strings);
    }

    //分页
    public String findByPage(String findValue, int start, int number) {
        return loginLogDao.findByPage(findValue, start, number);
    }

    //获取总行数(带查询参数)
    public int GetRowCount(String findValue) {
        return loginLogDao.GetRowCount(findValue);
    }
}
