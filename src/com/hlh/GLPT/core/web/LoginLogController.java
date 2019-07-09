package com.hlh.GLPT.core.web;

import com.hlh.GLPT.core.service.LoginLogService;
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
public class LoginLogController {
   @Autowired
    private LoginLogService loginLogService;

    @RequestMapping(value = "LoginLogList")
    public ModelAndView list(HttpServletRequest req) {
        ModelAndView mav=new ModelAndView();
        if(req.getParameter("IframeId")!=null){
            mav.addObject("IframeId",req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/core/LoginLogList");
        return mav;
    }

    @RequestMapping(value = "LoginLogFindPage")
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
        return "{\"total\":" + loginLogService.GetRowCount(findValue1) + ",\"rows\":" + loginLogService.findByPage(findValue1, start, number) + "}";
    }

}
