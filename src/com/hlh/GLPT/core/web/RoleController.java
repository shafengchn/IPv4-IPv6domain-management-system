package com.hlh.GLPT.core.web;

import com.hlh.GLPT.core.domain.Role;
import com.hlh.GLPT.core.domain.RoleAuthority;
import com.hlh.GLPT.core.service.RoleAuthorityService;
import com.hlh.GLPT.core.service.RoleService;
import com.hlh.GLPT.core.service.SessionService;
import com.hlh.KZ.service.SysMenuService;
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
import java.util.UUID;

/**
 * User: 黄良辉
 * Date: 14-3-19
 * Time: 上午9:55
 */
@Controller
@RequestMapping("/GLPT/CORE")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private RoleAuthorityService roleAuthorityService;

    @RequestMapping(value = "RoleList")
    public ModelAndView list(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/core/RoleList");
        return mav;
    }

    @RequestMapping(value = "RoleAdd")
    public ModelAndView add(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/core/RoleAdd");
        return mav;
    }

    @RequestMapping(value = "RoleEdit")
    public ModelAndView edit(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("RoleId") != null) {
            Role role = roleService.findById(req.getParameter("RoleId"));
            mav.addObject("role", role);
        }
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/core/RoleEdit");
        return mav;
    }

    @RequestMapping(value = "RoleAddOK")
    @ResponseBody
    public String addOK(HttpServletRequest req, Role role) {
        role.setRoleId(UUID.randomUUID().toString());
        String msg = roleService.VerifyAdd(role);
        if ("OK".equals(msg)) {
            role.setCreateDate(new Date());
            role.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
            role.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
            msg = roleService.add(role);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "RoleThoroughDelOK")
    @ResponseBody
    public String ThoroughDelOK(String RoleId) {
        String msg = "";
        if ("".equals(RoleId)) {
            msg = "请选择要操作的记录";
        } else {
            msg = roleService.ThoroughDel(RoleId);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "True";
        } else {
            RValue = "false";
        }
        return RValue;
    }

    @RequestMapping(value = "RoleDelOK")
    @ResponseBody
    public String delOK(HttpServletRequest req, String RoleId) {
        String msg = "";
        if ("".equals(RoleId)) {
            msg = "请选择要操作的记录";
        } else {
            msg = roleService.del(RoleId, new Date()
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

    @RequestMapping(value = "RoleEditOK")
    @ResponseBody
    public String editOK(HttpServletRequest req, Role role) {
        String msg = roleService.VerifyEdit(role);
        if ("OK".equals(msg)) {
            role.setModifyDate(new Date());
            role.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
            role.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
            msg = roleService.edit(role);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "RoleFindPage")
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
            json1 = "{\"total\":" + roleService.GetRowCount() + ",\"rows\":" + roleService.findByPage("", start, number) + "}";
        } else {
            json1 = "{\"total\":" + roleService.GetRowCount(findValue1) + ",\"rows\":" + roleService.findByPage(findValue1, start, number) + "}";
        }
        return json1;
    }

    @RequestMapping(value = "RoleFindByData")
    @ResponseBody
    public String findByData() {  //FValue 为OrganizationId
        return roleService.findByData();
    }


    @RequestMapping(value = "RoleFindBySimpleData")
    @ResponseBody
    public String findBySimpleData() {  //FValue 为OrganizationId
        return roleService.findBySimpleData();
    }

    @RequestMapping(value = "RoleSetAuthority")
    public ModelAndView setAuthority(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        String RoleCode1 = "";
        if (req.getParameter("RoleCode") != null) {
            RoleCode1 = req.getParameter("RoleCode");
            mav.addObject("RoleCode", RoleCode1);
        }
        String PTCode1 = "";
        if (req.getParameter("PTCode") != null) {
            PTCode1 = req.getParameter("PTCode");
            mav.addObject("PTCode", PTCode1);
        }
        JSONArray jsonArray1 = null;
        if ("".equals(PTCode1)) {
            jsonArray1 = sysMenuService.FindAllTreeAuthorityJSONData(RoleCode1);
        } else {
            jsonArray1 = sysMenuService.FindAllTreeAuthorityJSONData(RoleCode1, PTCode1);
        }
        mav.addObject("SysMenuJson", jsonArray1);
        mav.setViewName("GLPT/core/RoleSetAuthority");
        return mav;
    }

    @RequestMapping(value = "RoleSetAuthorityOK")
    @ResponseBody
    public String setAuthorityOK(HttpServletRequest req) {
        String msg = "OK";
        String RoleCode1 = "";
        if (req.getParameter("RoleCode") != null) {
            RoleCode1 = req.getParameter("RoleCode");
            roleAuthorityService.ThoroughDel(RoleCode1);
        } else {
            msg = "请选择角色";
        }
        //模块
        Date CurrentDate1 = new Date();
        List menuList1 = sysMenuService.findByField("Code");
        for (int i1 = 0; i1 < menuList1.size(); i1++) {
            Object[] ob1 = (Object[]) menuList1.get(i1);
            String str1 = StrUtil.toStr(ob1[0]);
            if (!"".equals(str1)) {
                if (req.getParameter(str1) != null) {
                    RoleAuthority roleAuthority1 = new RoleAuthority();
                    roleAuthority1.setRoleCode(RoleCode1);
                    roleAuthority1.setAuthorityCode(str1);
                    roleAuthority1.setCreateDate(CurrentDate1);
                    roleAuthority1.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
                    roleAuthority1.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
                    roleAuthorityService.add(roleAuthority1);
                    //数据安全
                    if (req.getParameter(str1 + "_DataSafe_All") != null) {
                        RoleAuthority roleAuthority2 = new RoleAuthority();
                        roleAuthority2.setRoleCode(RoleCode1);
                        roleAuthority2.setAuthorityCode(str1 + "_DataSafe_All");
                        roleAuthority2.setCreateDate(CurrentDate1);
                        roleAuthority2.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
                        roleAuthority2.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
                        roleAuthorityService.add(roleAuthority2);
                    }
                    if (req.getParameter(str1 + "_DataSafe_Private") != null) {
                        RoleAuthority roleAuthority2 = new RoleAuthority();
                        roleAuthority2.setRoleCode(RoleCode1);
                        roleAuthority2.setAuthorityCode(str1 + "_DataSafe_Private");
                        roleAuthority2.setCreateDate(CurrentDate1);
                        roleAuthority2.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
                        roleAuthority2.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
                        roleAuthorityService.add(roleAuthority2);
                    }
                    if (req.getParameter(str1 + "_DataSafe_Department") != null) {
                        RoleAuthority roleAuthority2 = new RoleAuthority();
                        roleAuthority2.setRoleCode(RoleCode1);
                        roleAuthority2.setAuthorityCode(str1 + "_DataSafe_Department");
                        roleAuthority2.setCreateDate(CurrentDate1);
                        roleAuthority2.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
                        roleAuthority2.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
                        roleAuthorityService.add(roleAuthority2);
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
