package com.hlh.GLPT.dm.web;

import com.hlh.GLPT.core.service.SessionService;
import com.hlh.GLPT.dm.domain.IPv6;
import com.hlh.GLPT.dm.service.IPv6Service;
import com.hlh.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Date;

/**
 * User: 黄良辉
 * Date: 14-6-8
 * Time: 上午11:00
 */
@Controller
@RequestMapping("/GLPT/DM")
public class IPv6Controller {
    @Autowired
    private IPv6Service iPv6Service;
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "IPv6List")
    public ModelAndView list(String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/IPv6List");
        return mav;
    }

    @RequestMapping(value = "IPv6Add")
    public ModelAndView add(String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        mav.addObject("SortCode",iPv6Service.GetRowCount(null,null,null,null,"","")+1);
        mav.setViewName("GLPT/dm/IPv6Add");
        return mav;
    }

    @RequestMapping(value = "IPv6Edit")
    public ModelAndView edit(String Id, String IframeId) {
        ModelAndView mav = new ModelAndView();
        if (Id != null) {
            IPv6 iPv6 = iPv6Service.findById(Integer.parseInt(Id));
            mav.addObject("iPv6", iPv6);
        }
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/IPv6Edit");
        return mav;
    }

    @RequestMapping(value = "IPv6AddOK")
    @ResponseBody
    public String addOK(HttpServletRequest req, IPv6 iPv6) {
        String msg = iPv6Service.VerifyAdd(iPv6);
        if ("OK".equals(msg)) {
            iPv6.setModifyDate(new Date());
            iPv6.setModifyUserCode(sessionService.GetUserLoginName(req.getSession()));
            iPv6.setModifyUserName(sessionService.GetUserLoginRealName(req.getSession()));
            msg = iPv6Service.add(iPv6);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "IPv6ThoroughDelOK")
    @ResponseBody
    public String ThoroughDelOK(String Id) {
        String msg = "";
        if ("".equals(Id)) {
            msg = "请选择要操作的记录";
        } else {
            IPv6 iPv6 = iPv6Service.findById(Integer.parseInt(Id));
            if (!iPv6.isCallMark()) {
                msg = iPv6Service.ThoroughDel(Integer.parseInt(Id));
            } else {
                msg = "IP地址已被调用,不能删除";
            }
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "True";
        } else {
            RValue = "false";
        }
        return RValue;
    }

    @RequestMapping(value = "IPv6DelOK")
    @ResponseBody
    public String delOK(String Id) {
        String msg = "";
        if ("".equals(Id)) {
            msg = "请选择要操作的记录";
        } else {
            msg = iPv6Service.SetDeleteMark(Integer.parseInt(Id), true);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "True";
        } else {
            RValue = "false";
        }
        return RValue;
    }

    @RequestMapping(value = "IPv6EditOK")
    @ResponseBody
    public String editOK(HttpServletRequest req, IPv6 iPv6) {
        String msg = iPv6Service.VerifyEdit(iPv6);
        if ("OK".equals(msg)) {
            iPv6.setModifyDate(new Date());
            iPv6.setModifyUserCode(sessionService.GetUserLoginName(req.getSession()));
            iPv6.setModifyUserName(sessionService.GetUserLoginRealName(req.getSession()));
            msg = iPv6Service.edit(iPv6);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "IPv6FindPage")
    @ResponseBody
    public String findPage(String State, String Enabled, String DeleteMark, String CallMark, String fField, String fValue, String rows, String page) throws Exception {
        Boolean State1 = StrUtil.toBoolean(State);
        Boolean Enabled1 = StrUtil.toBoolean(Enabled);
        Boolean DeleteMark1 = StrUtil.toBoolean(DeleteMark);
        Boolean CallMark1 = StrUtil.toBoolean(CallMark);
        if (fField == null) fField = "";
        if (fValue == null) fValue = "";
        //当前页
        int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
        //每页显示条数
        int number = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
        //每页的开始记录  第一页为1  第二页为number +1
        int start = (intPage - 1) * number;
        return "{\"total\":" + iPv6Service.GetRowCount(State1, Enabled1, DeleteMark1, CallMark1, fField, fValue)
                + ",\"rows\":" + iPv6Service.findByPage(State1, Enabled1, DeleteMark1, CallMark1, fField, fValue, start, number) + "}";
    }

    @RequestMapping(value = "IPv6Select")
    public ModelAndView IPv6Select(String CallType,String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        mav.addObject("CallType",CallType);
        mav.setViewName("GLPT/dm/IPv6Select");
        return mav;
    }

    @RequestMapping(value = "IPv6ExportToExcel")
    public void ExportToExcel(HttpServletResponse resp,String State, String Enabled, String DeleteMark, String CallMark, String fField, String fValue) throws Exception {
        Boolean State1 = StrUtil.toBoolean(State);
        Boolean Enabled1 = StrUtil.toBoolean(Enabled);
        Boolean DeleteMark1 = StrUtil.toBoolean(DeleteMark);
        Boolean CallMark1 = StrUtil.toBoolean(CallMark);
        if (fField == null) fField = "";
        if (fValue == null) fValue = "";
        resp.reset();
        resp.setContentType("application/vnd.ms-excel");
        resp.addHeader("Content-disposition", "attachment;filename=" + new String("IPv6".getBytes(), "ISO8859-1") + ".xls");
        OutputStream os = resp.getOutputStream();
        iPv6Service.ExportToExcel(os,State1, Enabled1, DeleteMark1, CallMark1, fField, fValue);
    }

    @RequestMapping(value = "IPv6Import")
    public ModelAndView studentImport(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/dm/IPv6Import");
        return mav;
    }

    @RequestMapping(value = "IPv6ImportOK")
    @ResponseBody
    public String importOK(HttpServletRequest req) {
        String fileName1 = "";
        if (req.getParameter("fileName") != null) {
            fileName1 = req.getParameter("fileName");
        }
        String msg = "";
        String RValue = "";
        String realPath = req.getSession().getServletContext().getRealPath("/upload");
        if (!"".equals(fileName1)) {
            msg = iPv6Service.IPv6Import(realPath + "/other/" + fileName1, new Date(), sessionService.GetUserLoginName(req.getSession()), sessionService.GetUserLoginRealName(req.getSession()));
        } else {
            msg = "请上传正确的文件";
        }
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }
}
