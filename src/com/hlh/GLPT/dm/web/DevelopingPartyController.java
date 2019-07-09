package com.hlh.GLPT.dm.web;

import com.hlh.GLPT.core.service.SessionService;
import com.hlh.GLPT.dm.domain.DevelopingParty;
import com.hlh.GLPT.dm.domain.DevelopingPartyContactPerson;
import com.hlh.GLPT.dm.service.DevelopingPartyContactPersonService;
import com.hlh.GLPT.dm.service.DevelopingPartyService;
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
import java.util.List;

/**
 * User: 黄良辉
 * Date: 14-6-8
 * Time: 上午11:00
 */
@Controller
@RequestMapping("/GLPT/DM")
public class DevelopingPartyController {
    @Autowired
    private DevelopingPartyService dPartyService;
    @Autowired
    private DevelopingPartyContactPersonService dPartyContactPersonService;
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "DevelopingPartyList")
    public ModelAndView list(String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/DevelopingPartyList");
        return mav;
    }

    @RequestMapping(value = "DevelopingPartyCurrUserList")
    public ModelAndView CurrUserList(String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/DevelopingPartyCurrUserList");
        return mav;
    }

    @RequestMapping(value = "DevelopingPartyAdd")
    public ModelAndView add(String CallType, String IframeId) {
        ModelAndView mav = new ModelAndView();
        if (CallType == null) CallType = "Add";
        mav.addObject("CallType", CallType);
        mav.addObject("IframeId", IframeId);
        mav.addObject("Code", "KFF" + StrUtil.GetUUID());
        mav.addObject("SortCode", dPartyService.GetRowCount(null, null, null, "", "") + 1);
        mav.setViewName("GLPT/dm/DevelopingPartyAdd");
        return mav;
    }

    @RequestMapping(value = "DevelopingPartyEdit")
    public ModelAndView edit(String Id, String IframeId) {
        ModelAndView mav = new ModelAndView();
        if (Id != null) {
            DevelopingParty dParty = dPartyService.findById(Integer.parseInt(Id));
            mav.addObject("dParty", dParty);
            if (dParty != null) {
                List<DevelopingPartyContactPerson> developingPartyContactPersonList1 = dPartyContactPersonService.findByCode(dParty.getCode());
                mav.addObject("dPartyContactPersonList", developingPartyContactPersonList1);
                mav.addObject("dPartyContactPersonCount",developingPartyContactPersonList1.size());
            }
        }
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/DevelopingPartyEdit");
        return mav;
    }
    @RequestMapping(value = "DevelopingPartyShow")
    public ModelAndView show(String Id, String IframeId) {
        ModelAndView mav = new ModelAndView();
        if (Id != null) {
            DevelopingParty dParty = dPartyService.findById(Integer.parseInt(Id));
            mav.addObject("dParty", dParty);
            if (dParty != null) {
                List developingPartyContactPersonList1 = dPartyContactPersonService.findByList(dParty.getCode());
                mav.addObject("dPartyContactPersonList", developingPartyContactPersonList1);
                mav.addObject("dPartyContactPersonCount",developingPartyContactPersonList1.size());
            }
        }
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/DevelopingPartyShow");
        return mav;
    }

    @RequestMapping(value = "DevelopingPartyAddOK")
    @ResponseBody
    public String addOK(HttpServletRequest req, DevelopingParty dParty, String[] ContactPersonRoleCode, String[] ContactPersonFullName, String[] ContactMethodCode, String[] ContactMethodContent) {
        //dParty.setCode("KFF"+StrUtil.GetUUID());
        String msg = dPartyService.VerifyAdd(dParty);
        if ("OK".equals(msg)) {
            if (dParty.getCooperationDate() == null || "".equals(dParty.getCooperationDate())) {
                dParty.setCooperationDate(null);
            }
            dParty.setCreateDate(new Date());
            dParty.setCreateUserCode(sessionService.GetUserLoginName(req.getSession()));
            dParty.setCreateUserName(sessionService.GetUserLoginRealName(req.getSession()));
            msg = dPartyService.add(dParty);
            if ("OK".equals(msg)) {
                for (int i1 = 0; i1 < ContactPersonRoleCode.length; i1++) {
                    DevelopingPartyContactPerson developingPartyContactPerson1 = new DevelopingPartyContactPerson();
                    developingPartyContactPerson1.setKFFCode(dParty.getCode());
                    developingPartyContactPerson1.setContactPersonRoleCode(ContactPersonRoleCode[i1]);
                    developingPartyContactPerson1.setContactPersonFullName(ContactPersonFullName[i1]);
                    developingPartyContactPerson1.setContactMethodCode(ContactMethodCode[i1]);
                    developingPartyContactPerson1.setContactMethodContent(ContactMethodContent[i1]);
                    dPartyContactPersonService.add(developingPartyContactPerson1);
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

    @RequestMapping(value = "DevelopingPartyThoroughDelOK")
    @ResponseBody
    public String ThoroughDelOK(String Id) {
        String msg = "";
        if ("".equals(Id)) {
            msg = "请选择要操作的记录";
        } else {
            DevelopingParty dParty = dPartyService.findById(Integer.parseInt(Id));
            if (!dParty.isCallMark()) {
                msg = dPartyService.ThoroughDel(Integer.parseInt(Id));
            } else {
                msg = "开发方资料已被调用,不能删除";
            }
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "True";
        } else {
            RValue = msg;
        }
        return RValue;
    }

    @RequestMapping(value = "DevelopingPartyForceThoroughDelOK")
    @ResponseBody
    public String ForceThoroughDelOK(String Id) {
        String msg = "";
        if ("".equals(Id)) {
            msg = "请选择要操作的记录";
        } else {
            msg = dPartyService.ThoroughDel(Integer.parseInt(Id));
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "True";
        } else {
            RValue = msg;
        }
        return RValue;
    }

    @RequestMapping(value = "DevelopingPartyDelOK")
    @ResponseBody
    public String delOK(String Id) {
        String msg = "";
        if ("".equals(Id)) {
            msg = "请选择要操作的记录";
        } else {
            msg = dPartyService.SetDeleteMark(Integer.parseInt(Id), true);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "True";
        } else {
            RValue = "false";
        }
        return RValue;
    }

    @RequestMapping(value = "DevelopingPartyEditOK")
    @ResponseBody
    public String editOK(HttpServletRequest req, DevelopingParty dParty, String[] ContactPersonRoleCode, String[] ContactPersonFullName, String[] ContactMethodCode, String[] ContactMethodContent) {
        String msg = dPartyService.VerifyEdit(dParty);
        if ("OK".equals(msg)) {
            dParty.setModifyDate(new Date());
            dParty.setModifyUserCode(sessionService.GetUserLoginName(req.getSession()));
            dParty.setModifyUserName(sessionService.GetUserLoginRealName(req.getSession()));
            msg = dPartyService.edit(dParty);
            if ("OK".equals(msg)) {
                dPartyContactPersonService.ThoroughDelEx(dParty.getCode()); //删除旧联系人
                for (int i1 = 0; i1 < ContactPersonRoleCode.length; i1++) {
                    DevelopingPartyContactPerson developingPartyContactPerson1 = new DevelopingPartyContactPerson();
                    developingPartyContactPerson1.setKFFCode(dParty.getCode());
                    developingPartyContactPerson1.setContactPersonRoleCode(ContactPersonRoleCode[i1]);
                    developingPartyContactPerson1.setContactPersonFullName(ContactPersonFullName[i1]);
                    developingPartyContactPerson1.setContactMethodCode(ContactMethodCode[i1]);
                    developingPartyContactPerson1.setContactMethodContent(ContactMethodContent[i1]);
                    dPartyContactPersonService.add(developingPartyContactPerson1);
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

    @RequestMapping(value = "DevelopingPartyFindPage")
    @ResponseBody
    public String findPage(String Enabled, String DeleteMark, String CallMark, String fField, String fValue, String rows, String page) throws Exception {
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
        return "{\"total\":" + dPartyService.GetRowCount(Enabled1, DeleteMark1, CallMark1, fField, fValue)
                + ",\"rows\":" + dPartyService.findByPage(Enabled1, DeleteMark1, CallMark1, fField, fValue, start, number) + "}";
    }

    @RequestMapping(value = "DevelopingPartyFindCurrUserPage")
    @ResponseBody
    public String findCurrUserPage(HttpServletRequest req, String Enabled, String DeleteMark, String CallMark, String fField, String fValue, String rows, String page) throws Exception {
        String UserCode = sessionService.GetUserLoginName(req.getSession());
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
        return "{\"total\":" + dPartyService.GetCurrUserRowCount(UserCode, Enabled1, DeleteMark1, CallMark1, fField, fValue)
                + ",\"rows\":" + dPartyService.findCurrUserByPage(UserCode, Enabled1, DeleteMark1, CallMark1, fField, fValue, start, number) + "}";
    }

    @RequestMapping(value = "DevelopingPartySelect")
    public ModelAndView DevelopingPartySelect(String CallType, String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        mav.addObject("CallType", CallType);
        mav.setViewName("GLPT/dm/DevelopingPartySelect");
        return mav;
    }

    @RequestMapping(value = "DevelopingPartyExportToExcel")
    public void ExportToExcel(HttpServletResponse resp, String Enabled, String DeleteMark, String CallMark, String fField, String fValue) throws Exception {
        Boolean Enabled1 = StrUtil.toBoolean(Enabled);
        Boolean DeleteMark1 = StrUtil.toBoolean(DeleteMark);
        Boolean CallMark1 = StrUtil.toBoolean(CallMark);
        if (fField == null) fField = "";
        if (fValue == null) fValue = "";
        resp.reset();
        resp.setContentType("application/vnd.ms-excel");
        resp.addHeader("Content-disposition", "attachment;filename=" + new String("开发方资料".getBytes(), "ISO8859-1") + ".xls");
        OutputStream os = resp.getOutputStream();
        dPartyService.ExportToExcel(os, Enabled1, DeleteMark1, CallMark1, fField, fValue);
    }

    @RequestMapping(value = "DevelopingPartyImport")
    public ModelAndView studentImport(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/dm/DevelopingPartyImport");
        return mav;
    }

    @RequestMapping(value = "DevelopingPartyImportOK")
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
            msg = dPartyService.DevelopingPartyImport(realPath + "/other/" + fileName1, new Date(), sessionService.GetUserLoginName(req.getSession()), sessionService.GetUserLoginRealName(req.getSession()));
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
