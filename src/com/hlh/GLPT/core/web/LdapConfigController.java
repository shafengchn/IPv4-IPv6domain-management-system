package com.hlh.GLPT.core.web;

import com.hlh.GLPT.core.domain.LdapConfig;
import com.hlh.GLPT.core.service.LdapConfigService;
import com.hlh.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * User: 黄良辉
 * Date: 14-9-1
 * Time: 下午8:24
 */
@Controller
@RequestMapping("/GLPT/CORE")
public class LdapConfigController {
   @Autowired
    private LdapConfigService lConfigService;

    @RequestMapping(value = "LdapConfigList")
    public ModelAndView list(HttpServletRequest req) {
        ModelAndView mav=new ModelAndView();
        if(req.getParameter("IframeId")!=null){
            mav.addObject("IframeId",req.getParameter("IframeId"));
        }
        LdapConfig lConfig1=lConfigService.findById(1);
        if(lConfig1!=null){
            mav.addObject("ldapConfig",lConfig1);
        }else{
            LdapConfig tmpLdapConfig1=new LdapConfig();
            tmpLdapConfig1.setID(1);
            mav.addObject("ldapConfig",tmpLdapConfig1);
        }
        mav.setViewName("GLPT/core/LdapConfigList");
        return mav;
    }

    @RequestMapping(value = "LdapConfigSaveOK")
    @ResponseBody
    public String SaveOK(HttpServletRequest req, LdapConfig ldapConfig) {
        String msg = lConfigService.save(ldapConfig);
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

}
