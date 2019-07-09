package com.hlh.GLPT.base.web;

import com.hlh.GLPT.base.domain.UserAndSysMenu;
import com.hlh.GLPT.base.service.UserAndSysMenuService;
import com.hlh.GLPT.core.domain.User;
import com.hlh.GLPT.core.service.UserService;
import com.hlh.GLPT.core.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * User: 黄良辉
 * Date: 14-5-30
 * Time: 上午11:16
 */

@Controller
@RequestMapping("/GLPT/BASE")
public class UserAndSysMenuController {

    @Autowired
    private UserAndSysMenuService userAndSysMenuService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "UserAndSysMenuList")
    public ModelAndView list(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        if(req.getParameter("UserCode")!=null){
            mav.addObject("UserCode",req.getParameter("UserCode"));
        }
        mav.setViewName("GLPT/base/UserAndSysMenuList");
        return mav;
    }

    @RequestMapping(value = "UserAndSysMenuAdd")
    public ModelAndView add(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        if(req.getParameter("UserCode")!=null){
            String UserCode1=req.getParameter("UserCode");
            User user1=userService.findByCode(UserCode1);
            mav.addObject("UserCode",UserCode1);
            mav.addObject("UserName",user1.getRealName());
        }
        mav.setViewName("GLPT/base/UserAndSysMenuAdd");
        return mav;
    }

    @RequestMapping(value = "UserAndSysMenuEdit")
    public ModelAndView edit(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("ID") != null) {
            UserAndSysMenu userAndSysMenu = userAndSysMenuService.findById(Integer.parseInt(req.getParameter("ID")));
            mav.addObject("userAndSysMenu", userAndSysMenu);
            User user1=userService.findByCode(userAndSysMenu.getUserCode());
            mav.addObject("UserName",user1.getRealName());
        }
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/base/UserAndSysMenuEdit");
        return mav;
    }

    @RequestMapping(value = "UserAndSysMenuAddOK")
    @ResponseBody
    public String addOK(HttpServletRequest req,UserAndSysMenu userAndSysMenu) {
        String msg = userAndSysMenuService.VerifyAdd(userAndSysMenu);
        if ("OK".equals(msg)) {
            userAndSysMenu.setCreateDate(new Date());
            userAndSysMenu.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
            userAndSysMenu.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
            msg = userAndSysMenuService.add(userAndSysMenu);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }



    @RequestMapping(value = "UserAndSysMenuThoroughDelOK")
    @ResponseBody
    public String ThoroughDelOK(String ID) {
        String msg = "";
        if ("".equals(ID)) {
            msg = "请选择要操作的记录";
        } else {
            msg = userAndSysMenuService.ThoroughDel(Integer.parseInt(ID));
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "True";
        } else {
            RValue = "false";
        }
        return RValue;
    }

    @RequestMapping(value = "UserAndSysMenuDelOK")
    @ResponseBody
    public String delOK(HttpServletRequest req, String ID) {
        String msg = "";
        if ("".equals(ID)) {
            msg = "请选择要操作的记录";
        } else {
            msg = userAndSysMenuService.del(Integer.parseInt(ID), new Date()
                    , sessionService.GetUserLoginCode(req.getSession())
                    , sessionService.GetUserLoginName(req.getSession()));
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "True";
        } else {
            RValue = "false";
        }
        return RValue;
    }

    @RequestMapping(value = "UserAndSysMenuEditOK")
    @ResponseBody
    public String editOK(HttpServletRequest req, UserAndSysMenu userAndSysMenu) {
        String msg = userAndSysMenuService.VerifyEdit(userAndSysMenu);
        if ("OK".equals(msg)) {
            userAndSysMenu.setModifyDate(new Date());
            userAndSysMenu.setModifyUserCode(sessionService.GetUserLoginCode(req.getSession()));
            userAndSysMenu.setModifyUserName(sessionService.GetUserLoginName(req.getSession()));
            msg = userAndSysMenuService.edit(userAndSysMenu);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "UserAndSysMenuFindPage")
    @ResponseBody
    public String findPage(HttpServletRequest req) throws Exception {
        String findValue1 = "";
        if (req.getParameter("FValue") != null) {
            findValue1 = req.getParameter("FValue");
        }
        String rows;//每页显示的记录数
        rows = req.getParameter("rows");
        String page;//当前第几页
        page = req.getParameter("page");
        //当前页
        int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
        //每页显示条数
        int number = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
        //每页的开始记录  第一页为1  第二页为number +1
        int start = (intPage - 1) * number;
        String json1 = "";
        if ("".equals(findValue1)) {
            json1 = "{\"total\":" + userAndSysMenuService.GetRowCount() + ",\"rows\":" + userAndSysMenuService.findByPage("", start, number) + "}";
        } else {
            json1 = "{\"total\":" + userAndSysMenuService.GetRowCount(findValue1) + ",\"rows\":" + userAndSysMenuService.findByPage(findValue1, start, number) + "}";
        }
        return json1;
    }

    @RequestMapping(value = "UserAndSysMenuFindUserByPage")
    @ResponseBody
    public String findUserByPage(HttpServletRequest req) throws Exception {
        String findValue1 = "";
        if (req.getParameter("FValue") != null) {
            findValue1 = req.getParameter("FValue");
        }
        String UserCode1="";
        if(req.getParameter("UserCode")!=null){
           UserCode1=req.getParameter("UserCode");
        }
        String rows;//每页显示的记录数
        rows = req.getParameter("rows");
        String page;//当前第几页
        page = req.getParameter("page");
        //当前页
        int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
        //每页显示条数
        int number = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
        //每页的开始记录  第一页为1  第二页为number +1
        int start = (intPage - 1) * number;
        String json1 = "";
        if ("".equals(findValue1)) {
            json1 = "{\"total\":" + userAndSysMenuService.GetUserRowCount(UserCode1,"") + ",\"rows\":" + userAndSysMenuService.findUserByPage(UserCode1,"", start, number) + "}";
        } else {
            json1 = "{\"total\":" + userAndSysMenuService.GetUserRowCount(UserCode1,findValue1) + ",\"rows\":" + userAndSysMenuService.findUserByPage(UserCode1,findValue1, start, number) + "}";
        }
        return json1;
    }

    @RequestMapping(value = "UserAndSysMenuFindAllTreeData")
    @ResponseBody
    public String findAllTreeData(String UserCode) throws Exception {
        return userAndSysMenuService.GetUserAndSysMenuChecked(UserCode).toString();
    }


}
