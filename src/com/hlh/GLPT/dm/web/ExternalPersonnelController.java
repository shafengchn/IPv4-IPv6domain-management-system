package com.hlh.GLPT.dm.web;

import com.hlh.GLPT.core.service.SessionService;
import com.hlh.GLPT.dm.domain.ExternalPersonnel;
import com.hlh.GLPT.dm.service.ExternalPersonnelService;
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
public class ExternalPersonnelController {
    @Autowired
    private ExternalPersonnelService ePersonnelService;
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "ExternalPersonnelList")
    public ModelAndView list(String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/ExternalPersonnelList");
        return mav;
    }

    @RequestMapping(value = "ExternalPersonnelAdd")
    public ModelAndView add(String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        mav.addObject("SortCode",ePersonnelService.GetRowCount(null,null,null,"","")+1);
        mav.setViewName("GLPT/dm/ExternalPersonnelAdd");
        return mav;
    }

    @RequestMapping(value = "ExternalPersonnelEdit")
    public ModelAndView edit(String Id, String IframeId) {
        ModelAndView mav = new ModelAndView();
        if (Id != null) {
            ExternalPersonnel ePersonnel = ePersonnelService.findById(Integer.parseInt(Id));
            mav.addObject("ePersonnel", ePersonnel);
        }
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/ExternalPersonnelEdit");
        return mav;
    }

    @RequestMapping(value = "ExternalPersonnelAddOK")
    @ResponseBody
    public String addOK(HttpServletRequest req, ExternalPersonnel ePersonnel) {
        String msg = ePersonnelService.VerifyAdd(ePersonnel);
        if ("OK".equals(msg)) {
            ePersonnel.setModifyDate(new Date());
            ePersonnel.setModifyUserCode(sessionService.GetUserLoginName(req.getSession()));
            ePersonnel.setModifyUserName(sessionService.GetUserLoginRealName(req.getSession()));
            msg = ePersonnelService.add(ePersonnel);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "ExternalPersonnelThoroughDelOK")
    @ResponseBody
    public String ThoroughDelOK(String Id) {
        String msg = "";
        if ("".equals(Id)) {
            msg = "请选择要操作的记录";
        } else {
            ExternalPersonnel ePersonnel = ePersonnelService.findById(Integer.parseInt(Id));
            if (!ePersonnel.isCallMark()) {
                msg = ePersonnelService.ThoroughDel(Integer.parseInt(Id));
            } else {
                msg = "外聘人员资料已被调用,不能删除";
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

    @RequestMapping(value = "ExternalPersonnelDelOK")
    @ResponseBody
    public String delOK(String Id) {
        String msg = "";
        if ("".equals(Id)) {
            msg = "请选择要操作的记录";
        } else {
            msg = ePersonnelService.SetDeleteMark(Integer.parseInt(Id), true);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "True";
        } else {
            RValue = "false";
        }
        return RValue;
    }

    @RequestMapping(value = "ExternalPersonnelEditOK")
    @ResponseBody
    public String editOK(HttpServletRequest req, ExternalPersonnel ePersonnel) {
        String msg = ePersonnelService.VerifyEdit(ePersonnel);
        if ("OK".equals(msg)) {
            ePersonnel.setModifyDate(new Date());
            ePersonnel.setModifyUserCode(sessionService.GetUserLoginName(req.getSession()));
            ePersonnel.setModifyUserName(sessionService.GetUserLoginRealName(req.getSession()));
            msg = ePersonnelService.edit(ePersonnel);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "ExternalPersonnelFindPage")
    @ResponseBody
    public String findPage( String Enabled, String DeleteMark, String CallMark, String fField, String fValue, String rows, String page) throws Exception {
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
        return "{\"total\":" + ePersonnelService.GetRowCount(Enabled1, DeleteMark1, CallMark1, fField, fValue)
                + ",\"rows\":" + ePersonnelService.findByPage(Enabled1, DeleteMark1, CallMark1, fField, fValue, start, number) + "}";
    }

    @RequestMapping(value = "ExternalPersonnelSelect")
    public ModelAndView ExternalPersonnelSelect(String CallModule,String CallType,String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        mav.addObject("CallModule",CallModule);
        mav.addObject("CallType",CallType);
        mav.setViewName("GLPT/dm/ExternalPersonnelSelect");
        return mav;
    }

    @RequestMapping(value = "ExternalPersonnelExportToExcel")
    public void ExportToExcel(HttpServletResponse resp, String Enabled, String DeleteMark, String CallMark, String fField, String fValue) throws Exception {
        Boolean Enabled1 = StrUtil.toBoolean(Enabled);
        Boolean DeleteMark1 = StrUtil.toBoolean(DeleteMark);
        Boolean CallMark1 = StrUtil.toBoolean(CallMark);
        if (fField == null) fField = "";
        if (fValue == null) fValue = "";
        resp.reset();
        resp.setContentType("application/vnd.ms-excel");
        resp.addHeader("Content-disposition", "attachment;filename=" + new String("外聘人员资料".getBytes(), "ISO8859-1") + ".xls");
        OutputStream os = resp.getOutputStream();
        ePersonnelService.ExportToExcel(os, Enabled1, DeleteMark1, CallMark1, fField, fValue);
    }

    @RequestMapping(value = "ExternalPersonnelImport")
    public ModelAndView studentImport(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/dm/ExternalPersonnelImport");
        return mav;
    }

    @RequestMapping(value = "ExternalPersonnelImportOK")
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
            msg = ePersonnelService.ExternalPersonnelImport(realPath + "/other/" + fileName1, new Date(), sessionService.GetUserLoginName(req.getSession()), sessionService.GetUserLoginRealName(req.getSession()));
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
