package com.hlh.GLPT.main.web;

import com.hlh.GLPT.base.domain.UserAndSysMenu;
import com.hlh.GLPT.base.service.UserAndSysMenuService;
import com.hlh.GLPT.core.domain.User;
import com.hlh.GLPT.core.service.SessionService;
import com.hlh.GLPT.core.service.UserService;
import com.hlh.GLPT.dm.service.DomainNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * User_back: 黄良辉
 * Date: 14-3-9
 * Time: 下午12:14
 */
@Controller
@RequestMapping("/GLPT")
public class MySpaceController {
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserAndSysMenuService userAndSysMenuService;
    @Autowired
    private UserService userService;
    @Autowired
    private DomainNameService dmService;

    @RequestMapping(value = "MySpace")
    public ModelAndView show(HttpServletRequest req) {
        ModelAndView mav1=new ModelAndView();
        String UserCode1 = sessionService.GetUserLoginCode(req.getSession());
        mav1.addObject("UserAndMenuJSONArray",userAndSysMenuService.GetUserSelectSysMenu(UserCode1));
        mav1.setViewName("GLPT/MySpace");
        return mav1;
    }
    @RequestMapping(value = "LdapUserIndex")
    public ModelAndView ldapUserIndex(HttpServletRequest req, String IframeId) {
        ModelAndView mav1=new ModelAndView();
        mav1.addObject("IframeId",IframeId);
        String UserCode1 = sessionService.GetUserLoginCode(req.getSession());
        int MyDMRowCount=dmService.GetGLYCodeRowCount(UserCode1,"","");
        int NoSignRowCount=dmService.GetNoSignRecordRowCount(UserCode1,"","");
        int NoRenewRowCount=dmService.GetNoRenewRecordRowCount(UserCode1,"","");
        mav1.addObject("myDmRowCount",MyDMRowCount);
        mav1.addObject("NoSignRowCount",NoSignRowCount);
        mav1.addObject("NoRenewRowCount",NoRenewRowCount);
        mav1.setViewName("GLPT/LdapUserIndex");
        return mav1;
    }

    @RequestMapping(value = "MySpacePersonalShortcuts")
    public ModelAndView shortcuts(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        String UserCode1 = sessionService.GetUserLoginCode(req.getSession());
        User user1 = userService.findByCode(UserCode1);
        mav.addObject("myUser", user1);
       // mav.addObject("UserAndSysMenuJSONArray", userAndSysMenuService.GetUserAndSysMenuChecked(UserCode1));
        mav.setViewName("GLPT/MySpacePersonalShortcuts");
        return mav;
    }

    @RequestMapping(value = "MySpacePersonalShortcutsOK")
    @ResponseBody
    public String shortcutsOK(HttpServletRequest req, String UserCode, String SysMenuCode) {
        String msg = "";
        if ("".equals(UserCode)) {
            msg = "无法获取用户信息,请重新登录";
        } else if ("".equals(SysMenuCode)) {
            msg = "请至少选择一个系统功能";
        } else {
            String[] strings1 = SysMenuCode.split("\\|");
            Date date1 = new Date();
            int YesCount1 = 0;
            userAndSysMenuService.ThoroughByUserCodeDel(UserCode);
            for (int i1 = 0; i1 < strings1.length; i1++) {
                UserAndSysMenu userAndSysMenu1 = new UserAndSysMenu();
                userAndSysMenu1.setUserCode(UserCode);
                userAndSysMenu1.setSysMenuCode(strings1[i1]);
                userAndSysMenu1.setEnabled(true);
                userAndSysMenu1.setSortCode(i1 + 1);
                userAndSysMenu1.setCreateDate(date1);
                userAndSysMenu1.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
                userAndSysMenu1.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
                msg = userAndSysMenuService.add(userAndSysMenu1);
                if ("OK".equals(msg)) {
                    YesCount1++;
                }
            }
            if (YesCount1 == strings1.length) {
                msg = "OK";
            } else {
                msg = "设置结束,结果与设置系统功能的个数不一致";
            }
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }
}
