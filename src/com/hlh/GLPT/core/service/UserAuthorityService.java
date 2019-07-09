package com.hlh.GLPT.core.service;

import com.hlh.GLPT.core.dao.UserAuthorityDao;
import com.hlh.GLPT.core.domain.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: 黄良辉
 * Date: 14-7-21
 * Time: 下午8:35
 */
@Service
public class UserAuthorityService {
    @Autowired
    private UserAuthorityDao userAuthorityDao;

    /*
* 添加
* */
    public String add(UserAuthority userAuthority) {
        return userAuthorityDao.add(userAuthority);
    }

    /*
  * 彻底删除 按UserCode删除用户权限记录
  * */
    public String ThoroughDel(String UserCode) {
        return userAuthorityDao.ThoroughDel(UserCode);
    }

    /*
  * 查询 按UserCode查询用户权限记录
  * */
    public List<UserAuthority> FindUserAuthority(String UserCode) {
        return userAuthorityDao.FindUserAuthority(UserCode);
    }

    /*
    * 查询 按UserCode,AuthorityCode查询用户权限记录是否存在
    * */
    public boolean findUserAuthorityIsCheck(String UserCode, String AuthorityCode) {
        return userAuthorityDao.findUserAuthorityIsCheck(UserCode, AuthorityCode);
    }


}
