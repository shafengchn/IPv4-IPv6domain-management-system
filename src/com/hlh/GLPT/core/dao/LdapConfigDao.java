package com.hlh.GLPT.core.dao;

import com.hlh.GLPT.core.domain.LdapConfig;
import com.hlh.common.service.DbToolsService;
import com.hlh.util.DbUtilsTemplate;
import com.hlh.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * LDAP配置
 * Package: com.hlh.GLPT.dm.dao
 * User: 黄良辉  15-6-3  下午10:27
 */
@Repository
public class LdapConfigDao {
    @Autowired
    private DbToolsService dbToolsService;
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;

    /**
     * 添加
     *
     * @param lConfig
     * @return
     */
    public String add(LdapConfig lConfig) {
        String[] fField = new String[]{"ID", "HostIP", "PortNum", "BaseDN", "UserDN", "LoginPassword","UserGroupName", "LdapUserLogin"};
        Object[] fVal = new Object[]{lConfig.getID(), lConfig.getHostIP(), lConfig.getPortNum(), lConfig.getBaseDN(), lConfig.getUserDN(), lConfig.getLoginPassword(),lConfig.getUserGroupName(), lConfig.isLdapUserLogin()};
        return dbToolsService.add("CORE_LdapConfig", fField, fVal);
    }

    /**
     * 修改
     *
     * @param lConfig
     * @return
     */
    public String edit(LdapConfig lConfig) {
        String[] fField = new String[]{"HostIP", "PortNum", "BaseDN", "UserDN", "LoginPassword","UserGroupName", "LdapUserLogin"};
        Object[] fVal = new Object[]{lConfig.getHostIP(), lConfig.getPortNum(), lConfig.getBaseDN(), lConfig.getUserDN(), lConfig.getLoginPassword(),lConfig.getUserGroupName(), lConfig.isLdapUserLogin()};
        return dbToolsService.edit("CORE_LdapConfig", fField, fVal, "Id", StrUtil.toStr(lConfig.getID()), "int");
    }

    /**
     * 标识删除
     *
     * @param ID            ID
     * @param LdapUserLogin 是否启用LDAP用户登录
     * @return
     */
    public String SetLdapUserLogin(int ID, boolean LdapUserLogin) {
        return dbToolsService.edit("CORE_LdapConfig", new String[]{"LdapUserLogin"}, new Object[]{LdapUserLogin}, "ID", StrUtil.toStr(ID), "int");
    }


    /**
     * 按ID查询单条记录
     *
     * @param ID
     * @return
     */
    public LdapConfig findById(int ID) {
        String sqlStr = "SELECT * FROM CORE_LdapConfig WHERE ID=?";
        return dbUtilsTemplate.findFirst(LdapConfig.class, sqlStr, ID);
    }

    /**
     * 保存配置
     * @param lConfig
     * @return
     */
    public String save(LdapConfig lConfig) {
        if (findById(lConfig.getID()) != null) {  //已存在
            return edit(lConfig);
        } else {   //不存在
            return add(lConfig);
        }
    }



}
