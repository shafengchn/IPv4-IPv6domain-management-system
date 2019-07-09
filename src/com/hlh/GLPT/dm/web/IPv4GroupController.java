package com.hlh.GLPT.dm.web;

import com.hlh.GLPT.core.service.SessionService;
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
import java.util.Date;

/**
 * Package: com.hlh.GLPT.dm.web
 * User: 黄良辉  15-12-3  下午8:04
 */
@Controller
@RequestMapping("/GLPT/DM")
public class IPv4GroupController {
    @Autowired
    private IPv4Service iPv4Service;
    @Autowired
    private IPv4GroupService iPv4GroupService;
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "IPv4GroupAdd")
    public ModelAndView add(String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/IPv4GroupAdd");
        return mav;
    }

    @RequestMapping(value = "IPv4GroupEdit")
    public ModelAndView edit(String Id, String IframeId) {
        ModelAndView mav = new ModelAndView();
        if (Id != null) {
            IPv4Group iPv4Group1 = iPv4GroupService.findById(Integer.parseInt(Id));
            mav.addObject("iPv4Group", iPv4Group1);
        }
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/IPv4GroupEdit");
        return mav;
    }

    @RequestMapping(value = "IPv4GroupAddOK")
    @ResponseBody
    public String addOK(HttpServletRequest req, IPv4Group iPv4Group1) {
        iPv4Group1.setEnabled(true);
        iPv4Group1.setDeleteMark(false);
        iPv4Group1.setSortCode(iPv4GroupService.GetRowCount()+1);
        String msg = iPv4GroupService.VerifyAdd(iPv4Group1);
        if ("OK".equals(msg)) {
            iPv4Group1.setModifyDate(new Date());
            iPv4Group1.setModifyUserCode(sessionService.GetUserLoginName(req.getSession()));
            iPv4Group1.setModifyUserName(sessionService.GetUserLoginRealName(req.getSession()));
            msg = iPv4GroupService.add(iPv4Group1);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "IPv4GroupThoroughDelOK")
    @ResponseBody
    public String ThoroughDelOK(String Id) {
        String msg = "";
        if ("".equals(Id)) {
            msg = "请选择要操作的记录";
        } else {
            IPv4Group iPv4Group1 = iPv4GroupService.findById(Integer.parseInt(Id));
            if(iPv4Service.GetRowCount(iPv4Group1.getGroupCode())<=0){
                msg = iPv4Service.ThoroughDel(Integer.parseInt(Id));
            } else {
                msg = "分组已被调用,不能删除";
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

    @RequestMapping(value = "IPv4GroupDelOK")
    @ResponseBody
    public String delOK(String Id) {
        String msg = "";
        if ("".equals(Id)) {
            msg = "请选择要操作的记录";
        } else {
            msg = iPv4GroupService.del(Integer.parseInt(Id),true);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "True";
        } else {
            RValue = "false";
        }
        return RValue;
    }

    @RequestMapping(value = "IPv4GroupEditOK")
    @ResponseBody
    public String editOK(HttpServletRequest req, IPv4Group iPv4Group1) {
        iPv4Group1.setEnabled(true);
        iPv4Group1.setDeleteMark(false);
        String msg = iPv4GroupService.VerifyEdit(iPv4Group1);
        if ("OK".equals(msg)) {
            iPv4Group1.setModifyDate(new Date());
            iPv4Group1.setModifyUserCode(sessionService.GetUserLoginName(req.getSession()));
            iPv4Group1.setModifyUserName(sessionService.GetUserLoginRealName(req.getSession()));
            msg = iPv4GroupService.edit(iPv4Group1);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "IPv4GroupData")
    @ResponseBody
    public String findGroupData() throws Exception {
        String[] fieldName1=new String[]{"Id","GroupCode","GroupName","Remarks"};
        return "{\"total\":" + iPv4GroupService.GetRowCount(true, false)
                + ",\"rows\":" + iPv4GroupService.findAllEx(fieldName1, true, false) + "}";
    }


    @RequestMapping(value = "IPv4GroupDataEx")
    @ResponseBody
    public String GetDataEx() {
        String[] fieldName1=new String[]{"Id","GroupCode","GroupName"};
        return iPv4GroupService.findAllEx(fieldName1,true,false);
    }
}
