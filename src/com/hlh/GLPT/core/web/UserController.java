package com.hlh.GLPT.core.web;

import com.hlh.GLPT.base.service.DepartmentService;
import com.hlh.GLPT.core.domain.UserAuthority;
import com.hlh.GLPT.core.domain.User;
import com.hlh.GLPT.core.domain.UserRole;
import com.hlh.GLPT.core.service.*;
import com.hlh.KZ.service.SysMenuService;
import com.hlh.util.DESUtil;
import com.hlh.util.StrUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * User: 黄良辉
 * Date: 14-3-12
 * Time: 上午11:37
 */
@Controller
@RequestMapping("/GLPT/CORE")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private ItemDetailsService itemDetailsService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private UserAuthorityService userAuthorityService;
    @Autowired
    private RoleService roleService;


    @RequestMapping(value = "UserList")
    public ModelAndView list(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/core/UserList");
        return mav;
    }

    @RequestMapping(value = "UserAdd")
    public ModelAndView add(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/core/UserAdd");
        return mav;
    }

    @RequestMapping(value = "UserEdit")
    public ModelAndView edit(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("UserId") != null) {
            User user = userService.findById(req.getParameter("UserId"));
            mav.addObject("user", user);
        }
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/core/UserEdit");
        return mav;
    }

    @RequestMapping(value = "UserAddOK")
    @ResponseBody
    public String addOK(HttpServletRequest req, User user) {
        user.setUserId(StrUtil.GetUUID());
        String msg = userService.VerifyAdd(user);
        if ("OK".equals(msg)) {
            user.setCreateDate(new Date());
            user.setPassword(DESUtil.encrypt(user.getPassword()));
            user.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
            user.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
            user.setSortCode(userService.GetRowCount() + 1);
            msg = userService.add(user);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "UserThoroughDelOK")
    @ResponseBody
    public String ThoroughDelOK(String UserId) {
        String msg = "";
        if ("".equals(UserId)) {
            msg = "请选择要操作的记录";
        } else {
            msg = userService.ThoroughDel(UserId);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "True";
        } else {
            RValue = "false";
        }
        return RValue;
    }

    @RequestMapping(value = "UserDelOK")
    @ResponseBody
    public String delOK(HttpServletRequest req, String UserId) {
        String msg = "";
        if ("".equals(UserId)) {
            msg = "请选择要操作的记录";
        } else {
            msg = userService.del(UserId, new Date()
                    , sessionService.GetUserLoginId(req.getSession())
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


    @RequestMapping(value = "UserEditOK")
    @ResponseBody
    public String editOK(HttpServletRequest req, User user) {
        String msg = userService.VerifyEdit(user);
        if ("OK".equals(msg)) {
            user.setModifyDate(new Date());
            user.setModifyUserCode(sessionService.GetUserLoginCode(req.getSession()));
            user.setModifyUserName(sessionService.GetUserLoginName(req.getSession()));
            msg = userService.edit(user);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "UserPersonalEditOK")
    @ResponseBody
    public String PersonalEditOK(HttpServletRequest req, User user) {
        String msg = userService.VerifyEdit(user);
        if ("OK".equals(msg)) {
            user.setModifyDate(new Date());
            user.setModifyUserCode(sessionService.GetUserLoginCode(req.getSession()));
            user.setModifyUserName(sessionService.GetUserLoginName(req.getSession()));
            msg = userService.PersonalEdit(user);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "UserFindPage")
    @ResponseBody
    public String findPage(HttpServletRequest req) throws Exception {
        String findValue1 = "";
        String findMethod1 = "MF"; //查找方法　MF 模糊 JQ 精确
        if (req.getParameter("FMethod") != null) {
            findMethod1 = req.getParameter("FMethod");
        }
        boolean Enabled1 = false; //只显有效
        if (req.getParameter("Enabled") != null) {
            if ("checked".equals(req.getParameter("Enabled"))) {
                Enabled1 = true;
            }
        }
        String fieldName1 = ""; //精确查找 字段名
        if (req.getParameter("FName") != null) {
            fieldName1 = req.getParameter("FName");
        }
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
        if ("MF".equals(findMethod1)) {
                json1 = "{\"total\":" + userService.GetRowCount(findValue1, Enabled1) + ",\"rows\":" + userService.findByPage(findValue1, Enabled1, start, number) + "}";
        } else {
                json1 = "{\"total\":" + userService.GetRowCount(fieldName1, findValue1, Enabled1) + ",\"rows\":" + userService.findByPage(fieldName1, findValue1, Enabled1, start, number) + "}";
        }
        return json1;
    }

    @RequestMapping(value = "UserResetPassword")
    public ModelAndView resetPassword(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("UserId") != null) {
            mav.addObject("UserId", req.getParameter("UserId"));
        }
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/core/UserResetPassword");
        return mav;
    }


    @RequestMapping(value = "UserResetPasswordOK")
    @ResponseBody
    public String resetPasswordOK(HttpServletRequest req, String UserId, String NewPassword, String ConfirmNewPassword) {
        String msg = "";
        if ("".equals(NewPassword) || NewPassword == null) {
            msg = "新密码不能为空";
        }
        if ("".equals(ConfirmNewPassword) || ConfirmNewPassword == null) {
            msg = "确认新密码不能为空";
        }
        if (!NewPassword.equals(ConfirmNewPassword)) {
            msg = "密码不一致";
        }
        if ("".equals(msg)) {
            Date date1 = new Date();
            msg = userService.resetPassword(UserId, DESUtil.encrypt(NewPassword), date1, date1
                    , sessionService.GetUserLoginCode(req.getSession())
                    , sessionService.GetUserLoginName(req.getSession()));
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "UserEditPassword")
    public ModelAndView editPassword(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("UserId") != null) {
            mav.addObject("UserId", req.getParameter("UserId"));
        }
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/core/UserEditPassword");
        return mav;
    }


    @RequestMapping(value = "UserEditPasswordOK")
    @ResponseBody
    public String editPasswordOK(HttpServletRequest req, String UserId, String OldPassword, String NewPassword, String ConfirmNewPassword) {
        String msg = "";
        if ("".equals(NewPassword) || NewPassword == null) {
            msg = "新密码不能为空";
        } else if ("".equals(ConfirmNewPassword) || ConfirmNewPassword == null) {
            msg = "确认新密码不能为空";
        } else if (!NewPassword.equals(ConfirmNewPassword)) {
            msg = "密码不一致";
        } else if (!userService.VerifyOldPassword(UserId, OldPassword)) {
            msg = "旧密码不正确";
        }
        if ("".equals(msg)) {
            Date date1 = new Date();
            msg = userService.resetPassword(UserId, DESUtil.encrypt(NewPassword), date1, date1
                    , sessionService.GetUserLoginCode(req.getSession())
                    , sessionService.GetUserLoginName(req.getSession()));
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "UserShow")
    public ModelAndView show(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("UserId") != null) {
            User user = userService.findById(req.getParameter("UserId"));
            mav.addObject("user", user);
            mav.addObject("DutyCode", itemDetailsService.CodeToFullName(user.getDutyCode()));
            mav.addObject("TitleCode", itemDetailsService.CodeToFullName(user.getTitleCode()));
            mav.addObject("DepartmentName", departmentService.DepartmentCodeToDepartmentName(user.getDepartmentCode()));
        }
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/core/UserShow");
        return mav;
    }

    @RequestMapping(value = "UserAllotRole")
    public ModelAndView AllotRole(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("UserCode") != null) {
            User user = userService.findByCode(req.getParameter("UserCode"));
            mav.addObject("selectUserInfo", user);
            mav.addObject("roleList", userService.FindUserAllotRole(req.getParameter("UserCode"),"系统角色"));
        }
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/core/UserAllotRole");
        return mav;
    }

    @RequestMapping(value = "UserAllotRoleOK")
    @ResponseBody
    public String AllotRoleOK(HttpServletRequest req, String UserCode) {
        String msg = "OK";
        if ("".equals(UserCode) || UserCode == null) {
            msg = "请选择要操作的记录";
        }
        int RowCount1 = 0;
        Date date1 = new Date();
        if ("OK".equals(msg)) {
            List roleList1 = roleService.findByField("Code,FullName");
            userRoleService.ThoroughDel(UserCode); //删除以前选中的角色
            for (int i1 = 0; i1 < roleList1.size(); i1++) {
                Object[] objects = (Object[]) roleList1.get(i1);
                if (objects != null) {
                    String str1 = StrUtil.toStr(objects[0]);
                    if (req.getParameter(str1) != null) {
                        UserRole userRole1 = new UserRole();
                        userRole1.setUserCode(UserCode);
                        userRole1.setRoleCode(str1);
                        userRole1.setCreateDate(date1);
                        userRole1.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
                        userRole1.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
                        userRoleService.add(userRole1);
                        RowCount1++;
                    }
                }
            }
        }
        if (RowCount1 > 0) {
            msg = "OK";
        } else {
            msg = "请至少选择一个角色!";
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "UserSetAuthority")
    public ModelAndView setAuthority(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        String UserCode1 = "";
        if (req.getParameter("UserCode") != null) {
            UserCode1 = req.getParameter("UserCode");
            mav.addObject("UserCode", UserCode1);
        }
        String PTCode1="";
        if(req.getParameter("PTCode")!=null){
            PTCode1=req.getParameter("PTCode");
            mav.addObject("PTCode",PTCode1);
        }
        JSONArray jsonArray1 =null;
        if("".equals(PTCode1)){
             jsonArray1=  sysMenuService.FindAllTreeUserAuthorityJSONData(UserCode1);
        }else{
             jsonArray1=  sysMenuService.FindAllTreeUserAuthorityJSONData(UserCode1,PTCode1);
        }
        mav.addObject("SysMenuJson", jsonArray1);
        mav.setViewName("GLPT/core/UserSetAuthority");
        return mav;
    }

    @RequestMapping(value = "UserSetAuthorityOK")
    @ResponseBody
    public String setAuthorityOK(HttpServletRequest req) {
        String msg = "OK";
        String UserCode1 = "";
        if (req.getParameter("UserCode") != null) {
            UserCode1 = req.getParameter("UserCode");
            userAuthorityService.ThoroughDel(UserCode1);
        } else {
            msg = "请选择用户";
        }
        //模块
        Date CurrentDate1 = new Date();
        List menuList1 = sysMenuService.findByField("Code");
        for (int i1 = 0; i1 < menuList1.size(); i1++) {
            Object[] ob1 = (Object[]) menuList1.get(i1);
            String str1 = StrUtil.toStr(ob1[0]);
            if (!"".equals(str1)) {
                if (req.getParameter(str1) != null) {
                    UserAuthority userAuthority1 = new UserAuthority();
                    userAuthority1.setUserCode(UserCode1);
                    userAuthority1.setAuthorityCode(str1);
                    userAuthority1.setCreateDate(CurrentDate1);
                    userAuthority1.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
                    userAuthority1.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
                    userAuthorityService.add(userAuthority1);
                    //数据安全
                    if (req.getParameter(str1 + "_DataSafe_All") != null) {
                        UserAuthority roleAuthority2 = new UserAuthority();
                        roleAuthority2.setUserCode(UserCode1);
                        roleAuthority2.setAuthorityCode(str1 + "_DataSafe_All");
                        roleAuthority2.setCreateDate(CurrentDate1);
                        roleAuthority2.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
                        roleAuthority2.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
                        userAuthorityService.add(roleAuthority2);
                    }
                    if (req.getParameter(str1 + "_DataSafe_Private") != null) {
                        UserAuthority roleAuthority2 = new UserAuthority();
                        roleAuthority2.setUserCode(UserCode1);
                        roleAuthority2.setAuthorityCode(str1 + "_DataSafe_Private");
                        roleAuthority2.setCreateDate(CurrentDate1);
                        roleAuthority2.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
                        roleAuthority2.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
                        userAuthorityService.add(roleAuthority2);
                    }
                    if (req.getParameter(str1 + "_DataSafe_Department") != null) {
                        UserAuthority roleAuthority2 = new UserAuthority();
                        roleAuthority2.setUserCode(UserCode1);
                        roleAuthority2.setAuthorityCode(str1 + "_DataSafe_Department");
                        roleAuthority2.setCreateDate(CurrentDate1);
                        roleAuthority2.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
                        roleAuthority2.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
                        userAuthorityService.add(roleAuthority2);
                    }
                }
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
