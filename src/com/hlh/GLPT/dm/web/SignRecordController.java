package com.hlh.GLPT.dm.web;

import com.hlh.GLPT.core.service.SessionService;
import com.hlh.GLPT.dm.domain.DomainName;
import com.hlh.GLPT.dm.domain.SignRecord;
import com.hlh.GLPT.dm.service.DomainNameService;
import com.hlh.GLPT.dm.service.SignRecordService;
import com.hlh.util.StrUtil;
import com.hlh.util.VeDate;
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
public class SignRecordController {
    @Autowired
    private SignRecordService sRecordService;
    @Autowired
    private DomainNameService domainNameService;
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "SignRecordList")
    public ModelAndView list(String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/SignRecordList");
        return mav;
    }

    @RequestMapping(value = "SignRecordAdd")
    public ModelAndView add(String IframeId, String DomainNameId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        if (DomainNameId == null) {
            mav.addObject("ErrorInfo", "请选择要签到的有效域名");
            mav.setViewName("Common/ErrorInfo");
        } else {
            if ("".equals(DomainNameId)) {
                mav.addObject("ErrorInfo", "请选择要签到的有效域名");
                mav.setViewName("Common/ErrorInfo");
            } else {
                DomainName domainName1 = domainNameService.findById(DomainNameId);

                if (domainName1 == null) {
                    mav.addObject("ErrorInfo", "请选择要签到的有效域名");
                    mav.setViewName("Common/ErrorInfo");
                } else {
                    mav.addObject("domainName", domainName1.getDomainName());
                    mav.addObject("DomainNameId", domainName1.getId());
                    if (domainName1.getLastSignTime() == null) {
                        mav.setViewName("GLPT/dm/SignRecordAdd");
                    } else {
                        //System.out.println("----------------------------------------------------");
                        //System.out.println(VeDate.getTwoDayEx(VeDate.getStringDate(), domainName1.getLastSignTime()));
                        if (VeDate.getTwoDayEx(VeDate.getStringDate(), domainName1.getLastSignTime()) >= domainName1.getSignInInterval()) {
                            mav.setViewName("GLPT/dm/SignRecordAdd");
                        } else {
                            mav.addObject("ErrorInfo", "还未达到签到的间隔天数,不能签到");
                            mav.setViewName("Common/ErrorInfo");
                        }
                    }
                }
            }
        }
        return mav;
    }

    @RequestMapping(value = "SignRecordAddOK")
    @ResponseBody
    public String addOK(HttpServletRequest req, SignRecord sRecord) {
        String msg = "";
        String UserCode1 = sessionService.GetUserLoginCode(req.getSession());
        if ("".equals(UserCode1)) {
            msg = "无法获取当前登录的用户信息,请重新登录";
        } else {
            sRecord.setSignCode(UserCode1);
            sRecord.setSignDate(VeDate.getStringDateShort());
            sRecord.setSignTime(VeDate.getStringDate());
            msg = sRecordService.VerifyAdd(sRecord);
            if ("OK".equals(msg)) {
                msg = sRecordService.add(sRecord);
                if("OK".equals(msg)){
                    domainNameService.SetLastSignTime(sRecord.getDomainNameId(),sRecord.getSignTime());
                    domainNameService.SetCallMark(sRecord.getDomainNameId(),true);
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
    @RequestMapping(value = "SignRecordAddClickUrlOK")
    @ResponseBody
    public String AddClickUrlOK(HttpServletRequest req, String DomainNameId) {
        String msg = "";
        if (DomainNameId==null)DomainNameId="";
        String UserCode1 = sessionService.GetUserLoginCode(req.getSession());
        if ("".equals(UserCode1)) {
            msg = "无法获取当前登录的用户信息,请重新登录";
        }else if("".equals(DomainNameId)){
            msg="请点击要访问地址";
        } else {
            SignRecord sRecord=new SignRecord();
            sRecord.setDomainNameId(DomainNameId);
            sRecord.setSignCode(UserCode1);
            sRecord.setSignDate(VeDate.getStringDateShort());
            sRecord.setSignTime(VeDate.getStringDate());
            sRecord.setSignContent("点击访问地址自动签到");
            msg = sRecordService.VerifyAdd(sRecord);
            if ("OK".equals(msg)) {
                msg = sRecordService.add(sRecord);
                if("OK".equals(msg)){
                    domainNameService.SetLastSignTime(sRecord.getDomainNameId(),sRecord.getSignTime());
                    domainNameService.SetCallMark(sRecord.getDomainNameId(),true);
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

    @RequestMapping(value = "SignRecordThoroughDelOK")
    @ResponseBody
    public String ThoroughDelOK(String Id) {
        String msg = "";
        if ("".equals(Id)) {
            msg = "请选择要操作的记录";
        } else {
            msg = sRecordService.ThoroughDel(Integer.parseInt(Id));
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "True";
        } else {
            RValue = "false";
        }
        return RValue;
    }


    @RequestMapping(value = "SignRecordFindPage")
    @ResponseBody
    public String findPage(String DomainNameId, String SignDate, String SignCode, String SignPeopleType, String fField, String fValue, String rows, String page) throws Exception {
        if (fField == null) fField = "";
        if (fValue == null) fValue = "";
        //当前页
        int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
        //每页显示条数
        int number = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
        //每页的开始记录  第一页为1  第二页为number +1
        int start = (intPage - 1) * number;
        return "{\"total\":" + sRecordService.GetRowCount(DomainNameId, SignDate, SignCode, SignPeopleType, fField, fValue)
                + ",\"rows\":" + sRecordService.findByPage(DomainNameId, SignDate, SignCode, SignPeopleType, fField, fValue, start, number) + "}";
    }

    @RequestMapping(value = "SignRecordSelect")
    public ModelAndView SignRecordSelect(String CallType, String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        mav.addObject("CallType", CallType);
        mav.setViewName("GLPT/dm/SignRecordSelect");
        return mav;
    }

    @RequestMapping(value = "SignRecordExportToExcel")
    public void ExportToExcel(HttpServletResponse resp, String State, String Enabled, String DeleteMark, String CallMark, String fField, String fValue) throws Exception {
        Boolean State1 = StrUtil.toBoolean(State);
        Boolean Enabled1 = StrUtil.toBoolean(Enabled);
        Boolean DeleteMark1 = StrUtil.toBoolean(DeleteMark);
        Boolean CallMark1 = StrUtil.toBoolean(CallMark);
        if (fField == null) fField = "";
        if (fValue == null) fValue = "";
        resp.reset();
        resp.setContentType("application/vnd.ms-excel");
        resp.addHeader("Content-disposition", "attachment;filename=" + new String("SignRecord".getBytes(), "ISO8859-1") + ".xls");
        OutputStream os = resp.getOutputStream();
        //sRecordService.ExportToExcel(os,State1, Enabled1, DeleteMark1, CallMark1, fField, fValue);
    }

}
