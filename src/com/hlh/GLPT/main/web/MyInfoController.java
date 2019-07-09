package com.hlh.GLPT.main.web;

import com.hlh.GLPT.base.domain.Borrower;
import com.hlh.GLPT.core.domain.User;
import com.hlh.GLPT.core.service.SessionService;
import com.hlh.GLPT.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * User_back: 黄良辉
 * Date: 14-3-9
 * Time: 下午12:14
 */
@Controller
@RequestMapping("/GLPT")
public class MyInfoController {
    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;


    @RequestMapping(value = "MyInfo")
    public ModelAndView show(HttpServletRequest req) {
        ModelAndView mav1=new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav1.addObject("IframeId", req.getParameter("IframeId"));
            User user1=userService.findByCode(sessionService.GetUserLoginCode(req.getSession()));
            mav1.addObject("myUser",user1);
        }
        mav1.setViewName("GLPT/MyInfo");
         return mav1;
    }

    @RequestMapping(value = "MyInfoPersonalSelectClass")
    public ModelAndView PersonalSelectClass(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("TeacherId") != null) {
        }

        //所有
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/MyInfoPersonalSelectClass");
        return mav;
    }






}
