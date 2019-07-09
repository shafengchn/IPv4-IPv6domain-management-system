package com.hlh.GLPT.core.web;

import com.hlh.GLPT.core.domain.LdapConfig;
import com.hlh.GLPT.core.service.LdapConfigService;
import com.hlh.util.LdapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Package: com.hlh.GLPT.core.web
 * User: 黄良辉  16-1-6  上午10:02
 */
@Controller
@RequestMapping("/GLPT/CORE")
public class LdapController {
    @Autowired
    private LdapConfigService lConfigService;

    @RequestMapping(value = "LdapConnTestOK")
    @ResponseBody
    public String ConnTestOK(HttpServletRequest req, String UserName,String password) {
        LdapConfig lConfig1=lConfigService.findById(1); //读取配置
        LdapUtil ldapUtil1=new LdapUtil();
        ldapUtil1.initConfig(lConfig1.getHostIP(), lConfig1.getPortNum(), lConfig1.getUserDN(), lConfig1.getBaseDN(), lConfig1.getLoginPassword());
        if (ldapUtil1.authenricate(lConfig1.getBaseDN(),lConfig1.getUserGroupName(), UserName, password)) {
           return  "{\"success\":true,\"info\":\"OK\"}";
        } else {
           return  "{\"success\":false,\"info\":\"ERROR\"}";
        }
    }
}
