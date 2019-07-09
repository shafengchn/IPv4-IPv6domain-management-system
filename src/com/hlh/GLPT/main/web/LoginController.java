package com.hlh.GLPT.main.web;

import com.hlh.GLPT.base.service.DepartmentService;
import com.hlh.GLPT.core.domain.LdapConfig;
import com.hlh.GLPT.core.domain.LoginLog;
import com.hlh.GLPT.core.service.LdapConfigService;
import com.hlh.GLPT.core.service.LoginLogService;
import com.hlh.GLPT.core.service.SessionService;
import com.hlh.GLPT.main.domain.LoginCommand;
import com.hlh.GLPT.core.domain.User;
import com.hlh.KZ.domain.SysMenu;
import com.hlh.KZ.service.SysMenuService;
import com.hlh.GLPT.core.service.UserService;
import com.hlh.util.LdapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * User_back: 黄良辉
 * Date: 14-2-28
 * Time: 下午5:09
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private LdapConfigService ldapConfigService;

    @RequestMapping(value = "login")
    public ModelAndView loginPage() {
        ModelAndView mav1 = new ModelAndView();
        mav1.addObject("SoftName", sysMenuService.CodeToFullName("1000"));
        mav1.setViewName("GLPT/login");
        return mav1;
    }

    @RequestMapping(value = "main")
    public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand) {
        //登录验证
     /*   boolean isValidUser = userService.hasMatchUser(loginCommand.getUserName(), loginCommand.getPassword());
        if (!isValidUser) {
            return new ModelAndView("login", "error", "用户名或密码错误");
        } else {
            User_back user = userService.findUserByUserName(loginCommand.getUserName());
            user.setLastLoginDate(user.getLoginDate());
            user.setLastLoginIP(user.getLoginIP());
            user.setLoginDate(new Date());
            user.setLoginIP(request.getLocalAddr());
            userService.loginSuccess(user);
            request.getSession().setAttribute("user", user);
            return new ModelAndView("main/MainTouch");
        } */
        String ShowFieldName = "MenuId,ParentId,FullName,Description,Img,NavigateUrl,FormName,Target,unfold";
        String[] strings1 = new String[]{"MenuId", "ParentId", "FullName", "Description", "Img", "NavigateUrl", "FormName", "Target", "unfold"};
        String data1 = sysMenuService.findAll(ShowFieldName, strings1);
        ModelAndView mav1 = new ModelAndView();
        mav1.addObject("data", data1);
        mav1.setViewName("GLPT/MainTouch");
        return mav1;
    }

    /**
     * 用户登录验证+LDAP用户验证
     *
     * @param request
     * @param loginCommand
     * @return
     */
    @RequestMapping(value = "mainRand")
    public ModelAndView loginCheckRand(HttpServletRequest request, LoginCommand loginCommand) {
        boolean isLdapUser1 = false;
        LdapConfig ldapConfig1 = ldapConfigService.findById(1);
        if (ldapConfig1 != null) {
            if (ldapConfig1.isLdapUserLogin()) {
                isLdapUser1 = true;
            }
        }
        ModelAndView mav1 = new ModelAndView();
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            if (session.getAttribute("rand") == null) {
                return new ModelAndView("GLPT/login");
            }
            if (loginCommand.getUserName() != null && loginCommand.getPassword() != null && loginCommand.getRand() != null) {
                if (!loginCommand.getRand().equals(session.getAttribute("rand"))) {
                    return new ModelAndView("GLPT/login", "error", "验证码不正确");
                }
                boolean isValidUser = userService.getMatchCount(loginCommand.getUserName(), loginCommand.getPassword());
                if (!isValidUser) {  //系统检测不到用户信息
                    String UserFullName1=""; //用户姓名
                    if (isLdapUser1) {  //有开启Ldap用户验证
                        LdapUtil ldapUtil1 = new LdapUtil();
                        ldapUtil1.initConfig(ldapConfig1.getHostIP(), ldapConfig1.getPortNum(), ldapConfig1.getUserDN(), ldapConfig1.getBaseDN(), ldapConfig1.getLoginPassword());
                        if (ldapUtil1.authenricate(ldapConfig1.getBaseDN(), ldapConfig1.getUserGroupName(), loginCommand.getUserName(), loginCommand.getPassword())) {
                            isValidUser=true;
                            UserFullName1=ldapUtil1.GetUserCN(ldapConfig1.getBaseDN(), ldapConfig1.getUserGroupName(), loginCommand.getUserName());
                        }
                    }
                    if(isValidUser){
                        LoginLog loginLog1=new LoginLog(new Date(),request.getRemoteAddr(),loginCommand.getUserName(),"LDAP用户");
                        loginLogService.add(loginLog1);
                        User tmpUser1=new User();
                        tmpUser1.setIPAddress(request.getLocalAddr());
                        tmpUser1.setCode(loginCommand.getUserName());
                        tmpUser1.setAccount(loginCommand.getUserName());
                        tmpUser1.setRealName(UserFullName1);
                        session.setAttribute("user", tmpUser1);
                        session.setAttribute("SoftName", sysMenuService.CodeToFullName("1000"));
                        session.setAttribute("DepartmentName", "LDAP用户组");
                        session.setAttribute("LdapUserState",true);
                        session.setMaxInactiveInterval(-1);
                        mav1.setViewName("GLPT/LdapUserMain");
                        return mav1;
                    }else{
                        return new ModelAndView("GLPT/login", "error", "用户名或密码错误");
                    }
                } else {     //系统检测到用户信息
                    User user = userService.findUserByUserName(loginCommand.getUserName());
                    LoginLog loginLog1 = new LoginLog(new Date(), request.getRemoteAddr(), user.getAccount());
                    loginLogService.add(loginLog1); //添加登录记录
                    user.setIPAddress(request.getLocalAddr());
                    user.setPreviousVisit(user.getLastVisit());
                    user.setLastVisit(new Date());
                    if (user.getLogOnCount() == 0) { //第一次登录
                        user.setFirstVisit(new Date());
                    }
                    user.setLogOnCount(1 + user.getLogOnCount());
                    userService.updateLoginInfo(user);
                    session.setAttribute("user", user);
                    session.setAttribute("SoftName", sysMenuService.CodeToFullName("1000"));
                    session.setAttribute("DepartmentName", departmentService.DepartmentCodeToDepartmentName(user.getDepartmentCode()));
                    session.setAttribute("LdapUserState",false);
                    session.setMaxInactiveInterval(-1);
                    List<SysMenu> sysMenuList = userService.UserGetChildMenuData(user.getCode(), "1000");
                    mav1.addObject("sysMenuList", sysMenuList);
                    mav1.setViewName("GLPT/MainTouch");
                    //mav1.setViewName("GLPT/LdapUserMain");
                    return mav1;
                }
            } else {
                return new ModelAndView("GLPT/login");
            }
        } else {
            if(session.getAttribute("LdapUserState")!=null){
               boolean ldapUserState1=Boolean.parseBoolean(session.getAttribute("LdapUserState").toString());
               if(ldapUserState1){
                   session.setAttribute("SoftName", sysMenuService.CodeToFullName("1000"));
                   session.setAttribute("DepartmentName", "LDAP用户组");
                   session.setAttribute("LdapUserState",true);
                   session.setMaxInactiveInterval(-1);
                   mav1.setViewName("GLPT/LdapUserMain");
                   return mav1;
               }else{
                   List<SysMenu> sysMenuList1 = userService.UserGetChildMenuData(sessionService.GetUserLoginCode(request.getSession()), "1000");
                   session.setAttribute("SoftName", sysMenuService.CodeToFullName("1000"));
                   User user = userService.findUserByUserName(loginCommand.getUserName());
                   session.setAttribute("DepartmentName", departmentService.DepartmentCodeToDepartmentName(user.getDepartmentCode()));
                   mav1.addObject("sysMenuList", sysMenuList1);
                   session.setAttribute("LdapUserState",false);
                   session.setMaxInactiveInterval(-1);
                   mav1.setViewName("GLPT/MainTouch");
                   return mav1;
               }
            }else{
                return new ModelAndView("GLPT/login");
            }
        }
    }


    @RequestMapping(value = "mainRand1")
    public ModelAndView loginCheckRand1(HttpServletRequest request, LoginCommand loginCommand) {
        //登录验证带验证码
        ModelAndView mav1 = new ModelAndView();
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            if (session.getAttribute("rand") == null) {
                return new ModelAndView("GLPT/login");
            }
            if (loginCommand.getUserName() != null && loginCommand.getPassword() != null && loginCommand.getRand() != null) {
                if (!loginCommand.getRand().equals(session.getAttribute("rand"))) {
                    return new ModelAndView("GLPT/login", "error", "验证码不正确");
                }
                boolean isValidUser = userService.getMatchCount(loginCommand.getUserName(), loginCommand.getPassword());
                if (!isValidUser) {
                    return new ModelAndView("GLPT/login", "error", "用户名或密码错误");
                } else {
                    User user = userService.findUserByUserName(loginCommand.getUserName());
                    LoginLog loginLog1 = new LoginLog(new Date(), request.getRemoteAddr(), user.getAccount());
                    loginLogService.add(loginLog1); //添加登录记录
                    user.setIPAddress(request.getLocalAddr());
                    user.setPreviousVisit(user.getLastVisit());
                    user.setLastVisit(new Date());
                    if (user.getLogOnCount() == 0) { //第一次登录
                        user.setFirstVisit(new Date());
                    }
                    user.setLogOnCount(1 + user.getLogOnCount());
                    userService.updateLoginInfo(user);
                    session.setAttribute("user", user);
                    session.setAttribute("SoftName", sysMenuService.CodeToFullName("1000"));
                    session.setAttribute("DepartmentName", departmentService.DepartmentCodeToDepartmentName(user.getDepartmentCode()));
                    session.setMaxInactiveInterval(-1);
                    List<SysMenu> sysMenuList = userService.UserGetChildMenuData(user.getCode(), "1000");
                    mav1.addObject("sysMenuList", sysMenuList);
                    mav1.setViewName("GLPT/MainTouch");
                    return mav1;
                }
            } else {
                return new ModelAndView("GLPT/login");
            }
        } else {
            List<SysMenu> sysMenuList1 = userService.UserGetChildMenuData(sessionService.GetUserLoginCode(request.getSession()), "1000");
            session.setAttribute("SoftName", sysMenuService.CodeToFullName("1000"));
            User user = userService.findUserByUserName(loginCommand.getUserName());
            session.setAttribute("DepartmentName", departmentService.DepartmentCodeToDepartmentName(user.getDepartmentCode()));
            mav1.addObject("sysMenuList", sysMenuList1);
            mav1.setViewName("GLPT/MainTouch");
            return mav1;
        }

    }

    @RequestMapping(value = "UserExit")
    public ModelAndView exit(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        sessionService.UserExit(req.getSession());
        mav.setViewName("GLPT/login");
        return mav;
    }
}
