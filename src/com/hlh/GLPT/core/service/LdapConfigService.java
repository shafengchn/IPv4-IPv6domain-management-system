package com.hlh.GLPT.core.service;

import com.hlh.GLPT.core.dao.LdapConfigDao;
import com.hlh.GLPT.core.domain.LdapConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Package: com.hlh.GLPT.core.service
 * User: 黄良辉  16-1-6  上午9:18
 */
@Service
public class LdapConfigService {
    @Autowired
    private LdapConfigDao ldapConfigDao;
    /**
     * 保存配置
     * @param lConfig
     * @return
     */
    public String save(LdapConfig lConfig) {
        return  ldapConfigDao.save(lConfig);
    }

    /**
     * 按ID查询单条记录
     *
     * @param ID
     * @return
     */
    public LdapConfig findById(int ID) {
        return ldapConfigDao.findById(ID);
    }
}
