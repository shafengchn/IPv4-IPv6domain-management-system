package com.hlh.GLPT.main.web;

import com.hlh.GLPT.core.service.SessionService;
import com.hlh.GLPT.core.service.UserService;
import com.hlh.KZ.domain.SysMenu;
import com.hlh.KZ.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User_back: 黄良辉
 * Date: 14-3-9
 * Time: 下午12:14
 */
@Controller
@RequestMapping("/GLPT")
public class MainSubMenuController {
    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping(value = "MainTouchSubMenu")
    public ModelAndView MainTouchSubMenu(HttpServletRequest req,String ParentId) {
        ModelAndView mav1=new ModelAndView();
        SysMenu parentMenu=sysMenuService.findById(ParentId);
        mav1.addObject("parentMenu",parentMenu);
        List<SysMenu> sysMenuList=userService.UserGetChildMenuDataByParentID(sessionService.GetUserLoginCode(req.getSession()),ParentId);
        mav1.addObject("sysMenuList",sysMenuList);
        mav1.setViewName("GLPT/MainTouchSubMenu");
         return mav1;
    }
}
