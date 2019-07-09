package com.hlh.GLPT.dm.web;

import com.hlh.GLPT.core.service.SessionService;
import com.hlh.GLPT.dm.domain.IPv4;
import com.hlh.GLPT.dm.domain.IPv4Group;
import com.hlh.GLPT.dm.service.IPv4GroupService;
import com.hlh.GLPT.dm.service.IPv4Service;
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
public class IPv4Controller {
    @Autowired
    private IPv4Service iPv4Service;
    @Autowired
    private IPv4GroupService iPv4GroupService;
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "IPv4List")
    public ModelAndView list(String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        IPv4Group iPv4Group1=iPv4GroupService.findByTop1();
        mav.addObject("iPv4Group",iPv4Group1);
        mav.setViewName("GLPT/dm/IPv4List");
        return mav;
    }

    @RequestMapping(value = "IPv4Add")
    public ModelAndView add(String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/IPv4Add");
        return mav;
    }

    @RequestMapping(value = "IPv4Edit")
    public ModelAndView edit(String Id, String IframeId) {
        ModelAndView mav = new ModelAndView();
        if (Id != null) {
            IPv4 iPv4 = iPv4Service.findById(Integer.parseInt(Id));
            mav.addObject("iPv4", iPv4);
        }
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/IPv4Edit");
        return mav;
    }

    @RequestMapping(value = "IPv4AddOK")
    @ResponseBody
    public String addOK(HttpServletRequest req,String GroupCode,String GroupName, String BeginAddress, String EndAddress, String Enabled, String Remarks) {
        Boolean Enabled1 = StrUtil.toBoolean(Enabled);
        int YesCount = iPv4Service.BatchAdd(GroupCode,GroupName, BeginAddress, EndAddress, Enabled1, Remarks, new Date(), sessionService.GetUserLoginName(req.getSession()), sessionService.GetUserLoginRealName(req.getSession()));
        return "{\"success\":true,\"info\":\"" + YesCount + "\"}";
    }

    @RequestMapping(value = "IPv4ThoroughDelOK")
    @ResponseBody
    public String ThoroughDelOK(String Id) {
        String msg = "";
        if ("".equals(Id)) {
            msg = "请选择要操作的记录";
        } else {
            IPv4 iPv4 = iPv4Service.findById(Integer.parseInt(Id));
            if (!iPv4.isCallMark()) {
                msg = iPv4Service.ThoroughDel(Integer.parseInt(Id));
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

    @RequestMapping(value = "IPv4DelOK")
    @ResponseBody
    public String delOK(String Id) {
        String msg = "";
        if ("".equals(Id)) {
            msg = "请选择要操作的记录";
        } else {
            msg = iPv4Service.SetDeleteMark(Integer.parseInt(Id), true);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "True";
        } else {
            RValue = "false";
        }
        return RValue;
    }

    @RequestMapping(value = "IPv4EditOK")
    @ResponseBody
    public String editOK(HttpServletRequest req, IPv4 iPv4) {
        String msg = iPv4Service.VerifyEdit(iPv4);
        if ("OK".equals(msg)) {
            iPv4.setModifyDate(new Date());
            iPv4.setModifyUserCode(sessionService.GetUserLoginName(req.getSession()));
            iPv4.setModifyUserName(sessionService.GetUserLoginRealName(req.getSession()));
            msg = iPv4Service.edit(iPv4);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "IPv4FindPage")
    @ResponseBody
    public String findPage(String State,String GroupCode, String Enabled, String DeleteMark, String CallMark, String fField, String fValue, String rows, String page) throws Exception {
        Boolean State1 = StrUtil.toBoolean(State);
        Boolean Enabled1 = StrUtil.toBoolean(Enabled);
        Boolean DeleteMark1 = StrUtil.toBoolean(DeleteMark);
        Boolean CallMark1 = StrUtil.toBoolean(CallMark);
        if (fField == null) fField = "";
        if (fValue == null) fValue = "";
        if("null".equals(GroupCode))GroupCode=null;
        //当前页
        int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
        //每页显示条数
        int number = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
        //每页的开始记录  第一页为1  第二页为number +1
        int start = (intPage - 1) * number;
        return "{\"total\":" + iPv4Service.GetRowCount(State1,GroupCode, Enabled1, DeleteMark1, CallMark1, fField, fValue)
                + ",\"rows\":" + iPv4Service.findByPage(State1,GroupCode, Enabled1, DeleteMark1, CallMark1, fField, fValue, start, number) + "}";
    }

    @RequestMapping(value = "IPv4Select")
    public ModelAndView IPv4Select(String CallType,String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        mav.addObject("CallType",CallType);
        mav.setViewName("GLPT/dm/IPv4Select");
        return mav;
    }

    @RequestMapping(value = "IPv4ExportToExcel")
    public void ExportToExcel(HttpServletResponse resp,String State, String Enabled, String DeleteMark, String CallMark, String fField, String fValue) throws Exception {
        Boolean State1 = StrUtil.toBoolean(State);
        Boolean Enabled1 = StrUtil.toBoolean(Enabled);
        Boolean DeleteMark1 = StrUtil.toBoolean(DeleteMark);
        Boolean CallMark1 = StrUtil.toBoolean(CallMark);
        if (fField == null) fField = "";
        if (fValue == null) fValue = "";
        resp.reset();
        resp.setContentType("application/vnd.ms-excel");
        resp.addHeader("Content-disposition", "attachment;filename=" + new String("IPv4".getBytes(), "ISO8859-1") + ".xls");
        OutputStream os = resp.getOutputStream();
        iPv4Service.ExportToExcel(os,State1, Enabled1, DeleteMark1, CallMark1, fField, fValue);
    }

    @RequestMapping(value = "IPv4Import")
    public ModelAndView studentImport(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/dm/IPv4Import");
        return mav;
    }

    @RequestMapping(value = "IPv4ImportOK")
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
            msg = iPv4Service.IPv4Import(realPath + "/other/" + fileName1, new Date(), sessionService.GetUserLoginName(req.getSession()), sessionService.GetUserLoginRealName(req.getSession()));
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

    @RequestMapping(value = "IPv4GroupSet")
    public ModelAndView IPv4GroupSet(String RowId,String CurrGroupCode,String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        String[] RowIds=RowId.split(",");
        if(RowIds.length>0){
            mav.addObject("RowId",RowId);
            mav.addObject("RowNum",RowIds.length);
        }
        mav.addObject("CurrGroupCode",CurrGroupCode);
        mav.setViewName("GLPT/dm/IPv4GroupSet");
        return mav;
    }


    @RequestMapping(value = "IPv4GroupSetOK")
    @ResponseBody
    public String IPv4GroupSetOK(HttpServletRequest req, String RowId,String CurrGroupCode) {
        String[] RowIds=RowId.split(",");
        int RVal1=iPv4Service.SetGroup(RowIds,CurrGroupCode);
        String RValue = "";
       if(RVal1>0){
           RValue = "{\"success\":true,\"info\":\"OK\"}";
       }else{
           RValue = "{\"success\":false,\"info\":\"设置分组失败\"}";
       }
        return RValue;
    }


}
