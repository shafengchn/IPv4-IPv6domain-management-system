package com.hlh.GLPT.dm.web;

import com.hlh.GLPT.core.service.SessionService;
import com.hlh.GLPT.dm.domain.BusinessType;
import com.hlh.GLPT.dm.service.BusinessTypeService;
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
public class BusinessTypeController {
    @Autowired
    private BusinessTypeService bTypeService;
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "BusinessTypeList")
    public ModelAndView list(String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/BusinessTypeList");
        return mav;
    }

    @RequestMapping(value = "BusinessTypeAdd")
    public ModelAndView add(String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        mav.addObject("SortCode",bTypeService.GetRowCount(null,null,null,"","")+1);
        mav.setViewName("GLPT/dm/BusinessTypeAdd");
        return mav;
    }

    @RequestMapping(value = "BusinessTypeEdit")
    public ModelAndView edit(String Id, String IframeId) {
        ModelAndView mav = new ModelAndView();
        if (Id != null) {
            BusinessType bType = bTypeService.findById(Integer.parseInt(Id));
            mav.addObject("bType", bType);
        }
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/BusinessTypeEdit");
        return mav;
    }

    @RequestMapping(value = "BusinessTypeAddOK")
    @ResponseBody
    public String addOK(HttpServletRequest req, BusinessType bType) {
        String msg = bTypeService.VerifyAdd(bType);
        if ("OK".equals(msg)) {
            bType.setModifyDate(new Date());
            bType.setModifyUserCode(sessionService.GetUserLoginName(req.getSession()));
            bType.setModifyUserName(sessionService.GetUserLoginRealName(req.getSession()));
            msg = bTypeService.add(bType);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "BusinessTypeThoroughDelOK")
    @ResponseBody
    public String ThoroughDelOK(String Id) {
        String msg = "";
        if ("".equals(Id)) {
            msg = "请选择要操作的记录";
        } else {
            BusinessType bType = bTypeService.findById(Integer.parseInt(Id));
            if (!bType.isCallMark()) {
                msg = bTypeService.ThoroughDel(Integer.parseInt(Id));
            } else {
                msg = "业务类型已被调用,不能删除";
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

    @RequestMapping(value = "BusinessTypeDelOK")
    @ResponseBody
    public String delOK(String Id) {
        String msg = "";
        if ("".equals(Id)) {
            msg = "请选择要操作的记录";
        } else {
            msg = bTypeService.SetDeleteMark(Integer.parseInt(Id), true);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "True";
        } else {
            RValue = "false";
        }
        return RValue;
    }

    @RequestMapping(value = "BusinessTypeEditOK")
    @ResponseBody
    public String editOK(HttpServletRequest req, BusinessType bType) {
        String msg = bTypeService.VerifyEdit(bType);
        if ("OK".equals(msg)) {
            bType.setModifyDate(new Date());
            bType.setModifyUserCode(sessionService.GetUserLoginName(req.getSession()));
            bType.setModifyUserName(sessionService.GetUserLoginRealName(req.getSession()));
            msg = bTypeService.edit(bType);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "BusinessTypeFindPage")
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
        return "{\"total\":" + bTypeService.GetRowCount( Enabled1, DeleteMark1, CallMark1, fField, fValue)
                + ",\"rows\":" + bTypeService.findByPage( Enabled1, DeleteMark1, CallMark1, fField, fValue, start, number) + "}";
    }

    @RequestMapping(value = "BusinessTypeSelect")
    public ModelAndView BusinessTypeSelect(String CallType,String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        mav.addObject("CallType",CallType);
        mav.setViewName("GLPT/dm/BusinessTypeSelect");
        return mav;
    }

    @RequestMapping(value = "BusinessTypeGetDataEx")
    @ResponseBody
    public String GetDataEx() {
        return bTypeService.findAllEx("Id,Code,FullName", new String[]{"Id", "Code", "FullName"});
    }

    @RequestMapping(value = "BusinessTypeExportToExcel")
    public void ExportToExcel(HttpServletResponse resp, String Enabled, String DeleteMark, String CallMark, String fField, String fValue) throws Exception {
         Boolean Enabled1 = StrUtil.toBoolean(Enabled);
        Boolean DeleteMark1 = StrUtil.toBoolean(DeleteMark);
        Boolean CallMark1 = StrUtil.toBoolean(CallMark);
        if (fField == null) fField = "";
        if (fValue == null) fValue = "";
        resp.reset();
        resp.setContentType("application/vnd.ms-excel");
        resp.addHeader("Content-disposition", "attachment;filename=" + new String("业务类型".getBytes(), "ISO8859-1") + ".xls");
        OutputStream os = resp.getOutputStream();
        bTypeService.ExportToExcel(os, Enabled1, DeleteMark1, CallMark1, fField, fValue);
    }

    @RequestMapping(value = "BusinessTypeImport")
    public ModelAndView studentImport(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/dm/BusinessTypeImport");
        return mav;
    }

    @RequestMapping(value = "BusinessTypeImportOK")
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
            msg = bTypeService.BusinessTypeImport(realPath + "/other/" + fileName1, new Date(), sessionService.GetUserLoginName(req.getSession()), sessionService.GetUserLoginRealName(req.getSession()));
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
