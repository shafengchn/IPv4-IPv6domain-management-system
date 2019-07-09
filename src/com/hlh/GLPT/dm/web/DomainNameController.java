package com.hlh.GLPT.dm.web;

import com.hlh.GLPT.RemoteBase.service.RemoteDepartmentService;
import com.hlh.GLPT.base.domain.Borrower;
import com.hlh.GLPT.base.domain.Teacher;
import com.hlh.GLPT.base.service.BorrowerService;
import com.hlh.GLPT.base.service.DepartmentService;
import com.hlh.GLPT.base.service.TeacherService;
import com.hlh.GLPT.core.service.ItemDetailsService;
import com.hlh.GLPT.core.service.SessionService;
import com.hlh.GLPT.dm.domain.DevelopingParty;
import com.hlh.GLPT.dm.domain.DomainName;
import com.hlh.GLPT.dm.domain.RenewRecord;
import com.hlh.GLPT.dm.domain.SignRecord;
import com.hlh.GLPT.dm.service.*;
import com.hlh.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

import org.apache.tools.zip.ZipEntry;


/**
 * User: 黄良辉
 * Date: 14-6-8
 * Time: 上午11:00
 */
@Controller
@RequestMapping("/GLPT/DM")
public class DomainNameController {
    @Autowired
    private DomainNameService dmService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private BusinessTypeService btService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DevelopingPartyService dPartyService;
    @Autowired
    private BorrowerService borrowerService;
    @Autowired
    private ItemDetailsService itemDetailsService;
    @Autowired
    private IPv4Service iPv4Service;
    @Autowired
    private IPv6Service iPv6Service;
    @Autowired
    private SignRecordService sRecordService;
    @Autowired
    private RenewRecordService rRecordService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private RemoteDepartmentService remoteDepartmentService;

    @RequestMapping(value = "DomainNameList")
    public ModelAndView list(String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/DomainNameList");
        return mav;
    }

    @RequestMapping(value = "DomainNameClaimList")
    public ModelAndView DomainNameClaimList(HttpServletRequest req, String IframeId) {
        ModelAndView mav1 = new ModelAndView();
        mav1.addObject("IframeId", IframeId);
        mav1.setViewName("GLPT/dm/DomainNameClaimList");
        return mav1;
    }
    @RequestMapping(value = "DomainNameClaimSituationList")
    public ModelAndView DomainNameClaimSituationList(HttpServletRequest req, String IframeId) {
        ModelAndView mav1 = new ModelAndView();
        mav1.addObject("IframeId", IframeId);
        mav1.setViewName("GLPT/dm/DomainNameClaimSituationList");
        return mav1;
    }


    @RequestMapping(value = "DomainNameClaim")
    public ModelAndView DomainNameClaim(HttpServletRequest req, String Id, String IframeId) {

        ModelAndView mav = new ModelAndView();
        if (Id != null) {
            String UserCode1 = sessionService.GetUserLoginCode(req.getSession());
            String UserName1 = sessionService.GetUserLoginRealName(req.getSession());
            mav.addObject("GLYCode", UserCode1);
            mav.addObject("GLYName", UserName1);
            Teacher teacher1=teacherService.findByCode(UserCode1);
            if(teacher1!=null){
                mav.addObject("teacher",teacher1);
            }
          /*  Borrower borrower1 = borrowerService.findByCode(UserCode1);

            if (borrower1 != null) {
                mav.addObject("GLYMobile", borrower1.getMobile());
                mav.addObject("GLYPhone", borrower1.getHomePhone());
                mav.addObject("GLYEmail", borrower1.getEmail());
            }*/
            DomainName dm = dmService.findById(Id);
            mav.addObject("dm", dm);
            mav.addObject("SysFLName", itemDetailsService.CodeToFullName(dm.getSysFLCode()));
            mav.addObject("SysFLChildName", itemDetailsService.CodeToFullName(dm.getSysFLChildCode()));
            mav.addObject("BusinessTypeName", btService.CodeToFullName(dm.getBusinessTypeCode()));
            //mav.addObject("GLDepartmentName", departmentService.DepartmentCodeToDepartmentName(dm.getGLDepartmentCode()));
            mav.addObject("KFFName", dPartyService.CodeToFullName(dm.getKFFCode()));
            mav.addObject("GLYIdTypeName", itemDetailsService.CodeToFullName(dm.getGLYIdType()));
        }
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/DomainNameClaim");
        return mav;
    }

    @RequestMapping(value = "DomainNameClaimOK")
    @ResponseBody
    public String ClaimOK(HttpServletRequest req, DomainName dm) {
        String msg = "";
        String UserCode1 = sessionService.GetUserLoginCode(req.getSession());
        if ("".equals(UserCode1)) {
            msg = "无法获取当前登录的用户信息,请重新登录";
        } else {
            msg = dmService.VerifyClaim(dm);
            if ("OK".equals(msg)) {
                dm.setGLYClaimTime(new Date());
                dm.setGLYClaimState(true);
                dm.setFZRClaimTime(new Date());
                dm.setFZRClaimState(true);
                dm.setApprovalClaimTime(new Date());
                dm.setApprovalClaimState(true);
                msg = dmService.ClaimOK(dm);
                if ("OK".equals(msg)) {
                    //添加教师信息
                    Teacher teacher1=new Teacher();
                    teacher1.setCode(dm.getGLYCode());
                    teacher1.setFullName(dm.getGLYName());
                    teacher1.setMobile(dm.getGLYMobile());
                    teacher1.setPhone(dm.getGLYPhone());
                    teacher1.setEmail(dm.getGLYEmail());
                    teacher1.setIdTypeCode(dm.getGLYIdType());
                    teacher1.setIdNumber(dm.getGLYIdNumber());
                    teacher1.setIdImgPath1(dm.getGLYIdImgPath1());
                    teacher1.setIdImgPath2(dm.getGLYIdImgPath2());
                    teacher1.setIdImgPath3(dm.getGLYIdImgPath3());
                    if("OK".equals(teacherService.VerifyAdd(teacher1))){
                        teacherService.add(teacher1);
                    }
                    Teacher teacher2=new Teacher();
                    teacher2.setCode(dm.getECPCode());
                    teacher2.setFullName(dm.getECPName());
                    teacher2.setMobile(dm.getECPMobile());
                    teacher2.setPhone(dm.getECPPhone());
                    teacher2.setEmail(dm.getECPEmail());
                    teacher2.setIdTypeCode(dm.getECPIdType());
                    teacher2.setIdNumber(dm.getECPIdNumber());
                    teacher2.setIdImgPath1(dm.getECPIdImgPath1());
                    teacher2.setIdImgPath2(dm.getECPIdImgPath2());
                    teacher2.setIdImgPath3(dm.getECPIdImgPath3());
                    if("OK".equals(teacherService.VerifyAdd(teacher2))){
                        teacherService.add(teacher2);
                    }
                    //添加签到记录
                    SignRecord sRecord = new SignRecord();
                    sRecord.setDomainNameId(dm.getId());
                    sRecord.setSignCode(UserCode1);
                    sRecord.setSignDate(VeDate.getStringDateShort());
                    sRecord.setSignTime(VeDate.getStringDate());
                    sRecord.setSignContent("域名认领自动签到");
                    msg = sRecordService.VerifyAdd(sRecord);
                    if ("OK".equals(msg)) {
                        msg = sRecordService.add(sRecord);
                        if ("OK".equals(msg)) {
                            dmService.SetLastSignTime(sRecord.getDomainNameId(), sRecord.getSignTime());
                            dmService.SetCallMark(sRecord.getDomainNameId(), true);
                        }
                    }
                    //添加续订记录
                    RenewRecord rRecord = new RenewRecord();
                    rRecord.setDomainNameId(dm.getId());
                    rRecord.setRenewCode(UserCode1);
                    rRecord.setRenewDate(VeDate.getStringDateShort());
                    rRecord.setRenewTime(VeDate.getStringDate());
                    rRecord.setRenewContent("域名认领自动续订");
                    msg = rRecordService.VerifyAdd(rRecord);
                    if ("OK".equals(msg)) {
                        msg = rRecordService.add(rRecord);
                        if ("OK".equals(msg)) {
                            dmService.SetLastRenewTime(rRecord.getDomainNameId(), rRecord.getRenewTime());
                            dmService.SetCallMark(rRecord.getDomainNameId(), true);
                        }
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


    @RequestMapping(value = "DomainNameClaimEdit")
    public ModelAndView DomainNameClaimEdit(HttpServletRequest req, String Id, String IframeId) {

        ModelAndView mav = new ModelAndView();
        if (Id != null) {
            String UserCode1 = sessionService.GetUserLoginCode(req.getSession());
            String UserName1 = sessionService.GetUserLoginRealName(req.getSession());
            Borrower borrower1 = borrowerService.findByCode(UserCode1);
            mav.addObject("GLYCode", UserCode1);
            mav.addObject("GLYName", UserName1);
            if (borrower1 != null) {
                mav.addObject("GLYMobile", borrower1.getMobile());
                mav.addObject("GLYPhone", borrower1.getHomePhone());
                mav.addObject("GLYEmail", borrower1.getEmail());
            }
            DomainName dm = dmService.findById(Id);
            mav.addObject("dm", dm);
            mav.addObject("SysFLName", itemDetailsService.CodeToFullName(dm.getSysFLCode()));
            mav.addObject("SysFLChildName", itemDetailsService.CodeToFullName(dm.getSysFLChildCode()));
            mav.addObject("BusinessTypeName", btService.CodeToFullName(dm.getBusinessTypeCode()));
            mav.addObject("GLDepartmentName", remoteDepartmentService.DepartmentCodeToDepartmentName(dm.getGLDepartmentCode()));
            mav.addObject("KFFName", dPartyService.CodeToFullName(dm.getKFFCode()));
            DevelopingParty dParty1=dPartyService.findByCode(dm.getKFFCode());
            mav.addObject("kff",dParty1);
            mav.addObject("GLYIdTypeName", itemDetailsService.CodeToFullName(dm.getGLYIdType()));
        }
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/DomainNameClaimEdit");
        return mav;
    }


    @RequestMapping(value = "DomainNameClaimEditOK")
    @ResponseBody
    public String ClaimEditOK(HttpServletRequest req, DomainName dm) {
        String msg = "";
        String UserCode1 = sessionService.GetUserLoginCode(req.getSession());
        if ("".equals(UserCode1)) {
            msg = "无法获取当前登录的用户信息,请重新登录";
        } else {
            msg = dmService.VerifyClaimEdit(dm);
            if ("OK".equals(msg)) {
                msg = dmService.ClaimEditOK(dm);
                if("OK".equals(msg)){
                    //添加教师信息
                    Teacher teacher1=new Teacher();
                    teacher1.setCode(dm.getGLYCode());
                    teacher1.setFullName(dm.getGLYName());
                    teacher1.setMobile(dm.getGLYMobile());
                    teacher1.setPhone(dm.getGLYPhone());
                    teacher1.setEmail(dm.getGLYEmail());
                    teacher1.setIdTypeCode(dm.getGLYIdType());
                    teacher1.setIdNumber(dm.getGLYIdNumber());
                    teacher1.setIdImgPath1(dm.getGLYIdImgPath1());
                    teacher1.setIdImgPath2(dm.getGLYIdImgPath2());
                    teacher1.setIdImgPath3(dm.getGLYIdImgPath3());
                    if("OK".equals(teacherService.VerifyAdd(teacher1))){
                        teacherService.add(teacher1);
                    }
                    Teacher teacher2=new Teacher();
                    teacher2.setCode(dm.getECPCode());
                    teacher2.setFullName(dm.getECPName());
                    teacher2.setMobile(dm.getECPMobile());
                    teacher2.setPhone(dm.getECPPhone());
                    teacher2.setEmail(dm.getECPEmail());
                    teacher2.setIdTypeCode(dm.getECPIdType());
                    teacher2.setIdNumber(dm.getECPIdNumber());
                    teacher2.setIdImgPath1(dm.getECPIdImgPath1());
                    teacher2.setIdImgPath2(dm.getECPIdImgPath2());
                    teacher2.setIdImgPath3(dm.getECPIdImgPath3());
                    if("OK".equals(teacherService.VerifyAdd(teacher2))){
                        teacherService.add(teacher2);
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

    @RequestMapping(value = "DomainNameCancelClaimOK")
    @ResponseBody
    public String CancelClaimOK(HttpServletRequest req, String Id) {
        String msg = "";
        DomainName dm1 = dmService.findById(Id);
        if (dm1 != null) {
            msg = dmService.CancelClaimOK(Id);
        } else {
            msg = "请选择要操作的记录";
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }


    @RequestMapping(value = "DomainNameAdd")
    public ModelAndView add(String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("Id", "DM" + StrUtil.GetUUID());
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/DomainNameAdd");
        return mav;
    }

    @RequestMapping(value = "DomainNameEdit")
    public ModelAndView edit(String Id, String IframeId) {
        ModelAndView mav = new ModelAndView();
        if (Id != null) {
            DomainName dm = dmService.findById(Id);
            mav.addObject("dm", dm);
            mav.addObject("BusinessTypeName", btService.CodeToFullName(dm.getBusinessTypeCode()));
            mav.addObject("GLDepartmentName", departmentService.DepartmentCodeToDepartmentName(dm.getGLDepartmentCode()));
            DevelopingParty developingParty1 = dPartyService.findByCode(dm.getKFFCode());
            mav.addObject("kff", developingParty1);
            if (developingParty1 != null) {
                mav.addObject("KFFName", developingParty1.getFullName());
            } else {
                mav.addObject("KFFName", "");
            }
            mav.addObject("GLYIdTypeName", itemDetailsService.CodeToFullName(dm.getGLYIdType()));
        }
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/DomainNameEdit");
        return mav;
    }

    @RequestMapping(value = "DomainNameShow")
    public ModelAndView show(String Id, String IframeId) {
        ModelAndView mav = new ModelAndView();
        if (Id != null) {
            DomainName dm = dmService.findById(Id);
            mav.addObject("dm", dm);
            mav.addObject("SysFLName", itemDetailsService.CodeToFullName(dm.getSysFLCode()));
            mav.addObject("SysFLChildName", itemDetailsService.CodeToFullName(dm.getSysFLChildCode()));
            mav.addObject("BusinessTypeName", btService.CodeToFullName(dm.getBusinessTypeCode()));
            mav.addObject("GLDepartmentName", remoteDepartmentService.DepartmentCodeToDepartmentName(dm.getGLDepartmentCode()));
            DevelopingParty developingParty1 = dPartyService.findByCode(dm.getKFFCode());
            mav.addObject("kff", developingParty1);
            if (developingParty1 != null) {
                mav.addObject("KFFName", developingParty1.getFullName());
            } else {
                mav.addObject("KFFName", "");
            }
            mav.addObject("GLYIdTypeName", itemDetailsService.CodeToFullName(dm.getGLYIdType()));
            mav.addObject("ECPIdTypeName", itemDetailsService.CodeToFullName(dm.getECPIdType()));
        }
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/DomainNameShow");
        return mav;
    }

    @RequestMapping(value = "DomainNameSignRecordShow")
    public ModelAndView SignRecordShow(String DomainNameId, String IframeId) {
        ModelAndView mav = new ModelAndView();
        if (DomainNameId != null) {
            mav.addObject("DomainNameId", DomainNameId);
        } else {
            mav.addObject("DomainNameId", "");
        }
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/DomainNameSignRecordShow");
        return mav;
    }

    @RequestMapping(value = "DomainNameRenewRecordShow")
    public ModelAndView RenewRecordShow(String DomainNameId, String IframeId) {
        ModelAndView mav = new ModelAndView();
        if (DomainNameId != null) {
            mav.addObject("DomainNameId", DomainNameId);
        } else {
            mav.addObject("DomainNameId", "");
        }
        mav.addObject("IframeId", IframeId);
        mav.setViewName("GLPT/dm/DomainNameRenewRecordShow");
        return mav;
    }

    @RequestMapping(value = "DomainNameAddOK")
    @ResponseBody
    public String addOK(HttpServletRequest req, DomainName dm) {
        String msg = dmService.VerifyAdd(dm);
        if ("OK".equals(msg)) {
            if (dm.getGLYCode() != null && !"".equals(dm.getGLYCode().trim())) {
                dm.setGLYClaimTime(new Date());
                dm.setGLYClaimState(true);
            }
            if (dm.getFZRCode() != null && !"".equals(dm.getFZRCode().trim())) {
                dm.setFZRClaimTime(new Date());
                dm.setFZRClaimState(true);
            }
            if (dm.getApprovalCode() != null && !"".equals(dm.getApprovalCode().trim())) {
                dm.setApprovalClaimTime(new Date());
                dm.setApprovalClaimState(true);
            }
            if ("".equals(dm.getOpenDate())) {
                dm.setOpenDate(null);
            }
            dm.setCreateDate(new Date());
            dm.setSortCode(dmService.GetSortCodeMaxAdd1());
            dm.setCreateUserCode(sessionService.GetUserLoginName(req.getSession()));
            dm.setCreateUserName(sessionService.GetUserLoginRealName(req.getSession()));
            msg = dmService.add(dm);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            if (!"".equals(dm.getIPv4Address())) {
                iPv4Service.BatchAdd(dm.getIPv4Address(), new Date(), sessionService.GetUserLoginName(req.getSession()), sessionService.GetUserLoginRealName(req.getSession()));
                iPv4Service.BatchSetState(dm.getIPv4Address(), true);
            }
            if (!"".equals(dm.getIPv6Address())) {
                iPv6Service.BatchAdd(dm.getIPv6Address(), new Date(), sessionService.GetUserLoginName(req.getSession()), sessionService.GetUserLoginRealName(req.getSession()));
                iPv6Service.BatchSetState(dm.getIPv6Address(), true);
            }
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "DomainNameThoroughDelOK")
    @ResponseBody
    public String ThoroughDelOK(String Id) {
        String msg = "";
        if ("".equals(Id)) {
            msg = "请选择要操作的记录";
        } else {
            DomainName dm = dmService.findById(Id);
            if (!dm.isCallMark()) {
                msg = dmService.ThoroughDel(Id);
            } else {
                msg = "域名已被调用,不能删除";
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

    @RequestMapping(value = "DomainNameDelOK")
    @ResponseBody
    public String delOK(String Id) {
        String msg = "";
        if ("".equals(Id)) {
            msg = "请选择要操作的记录";
        } else {
            msg = dmService.SetDeleteMark(Id, true);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "True";
        } else {
            RValue = "false";
        }
        return RValue;
    }

    @RequestMapping(value = "DomainNameEnabledOK")
    @ResponseBody
    public String DomainNameEnabledOK(String DomainNameId) {
        String msg = "";
        if ("".equals(DomainNameId)) {
            msg = "请选择要操作的记录";
        } else {
            msg = dmService.SetDisable(DomainNameId, false, null, null, null);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "True";
        } else {
            RValue = msg;
        }
        return RValue;
    }


    @RequestMapping(value = "DomainNameDisable")
    public ModelAndView DomainNameDisable(String Id, String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        if (Id != null) {
            DomainName dm = dmService.findById(Id);
            if (dm != null) {
                mav.addObject("Id", Id);
                mav.setViewName("GLPT/dm/DomainNameDisable");
            } else {
                mav.addObject("ErrorInfo", "请选择要操作的记录");
                mav.setViewName("Common/ErrorInfo");
            }
        } else {
            mav.addObject("ErrorInfo", "请选择要操作的记录");
            mav.setViewName("Common/ErrorInfo");
        }
        return mav;
    }

    @RequestMapping(value = "DomainNameDisableOK")
    @ResponseBody
    public String DomainNameDisableOK(HttpServletRequest req, String Id, String DisableContent) {
        String msg = "";
        if ("".equals(Id)) {
            msg = "请选择要操作的记录";
        } else if ("".equals(DisableContent.trim())) {
            msg = "停用理由不能为空";
        } else {
            msg = dmService.SetDisable(Id, true, sessionService.GetUserLoginName(req.getSession()), new Date(), DisableContent);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "DomainNameEditOK")
    @ResponseBody
    public String editOK(HttpServletRequest req, DomainName dm) {
        DomainName dm_old1 = dmService.findById(dm.getId());
        String msg = dmService.VerifyEdit(dm);
        if ("OK".equals(msg)) {
            if (dm.getGLYCode() != null && !"".equals(dm.getGLYCode())) {
                if (dm.getGLYClaimTime() == null)
                    dm.setGLYClaimTime(new Date());
                dm.setGLYClaimState(true);
            }
            if (dm.getFZRCode() != null && !"".equals(dm.getFZRCode())) {
                if (dm.getFZRClaimTime() == null)
                    dm.setFZRClaimTime(new Date());
                dm.setFZRClaimState(true);
            }
            if (dm.getApprovalCode() != null && !"".equals(dm.getApprovalCode())) {
                if (dm.getApprovalClaimTime() == null)
                    dm.setApprovalClaimTime(new Date());
                dm.setApprovalClaimState(true);
            }
            if ("".equals(dm.getOpenDate())) {
                dm.setOpenDate(null);
            }
            dm.setModifyDate(new Date());
            dm.setModifyUserCode(sessionService.GetUserLoginName(req.getSession()));
            dm.setModifyUserName(sessionService.GetUserLoginRealName(req.getSession()));

            msg = dmService.edit(dm);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            if (!"".equals(dm_old1.getIPv4Address())) {
                iPv4Service.BatchSetState(dm_old1.getIPv4Address(), false);
            }
            if (!"".equals(dm_old1.getIPv6Address())) {
                iPv6Service.BatchSetState(dm_old1.getIPv6Address(), false);
            }
            if (!"".equals(dm.getIPv4Address())) {
                iPv4Service.BatchAdd(dm.getIPv4Address(), new Date(), sessionService.GetUserLoginName(req.getSession()), sessionService.GetUserLoginRealName(req.getSession()));
                iPv4Service.BatchSetState(dm.getIPv4Address(), true);
            }
            if (!"".equals(dm.getIPv6Address())) {
                iPv6Service.BatchAdd(dm.getIPv6Address(), new Date(), sessionService.GetUserLoginName(req.getSession()), sessionService.GetUserLoginRealName(req.getSession()));
                iPv6Service.BatchSetState(dm.getIPv6Address(), true);
            }
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "DomainNameFindPage")
    @ResponseBody
    public String findPage(String Disable, String DeleteMark, String CallMark, String fField, String fValue, String rows, String page) throws Exception {
        Boolean Disable1 = StrUtil.toBoolean(Disable);
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
        return "{\"total\":" + dmService.GetRowCount(Disable1, DeleteMark1, CallMark1, fField, fValue)
                + ",\"rows\":" + dmService.findByPage(Disable1, DeleteMark1, CallMark1, fField, fValue, start, number) + "}";
    }

    @RequestMapping(value = "DomainNameFindPageEx")
    @ResponseBody
    public String findPageEx(String GLYClaimState, String fField, String fValue, String rows, String page) throws Exception {
        Boolean GLYClaimState1 = StrUtil.toBoolean(GLYClaimState);
        if (fField == null) fField = "";
        if (fValue == null) fValue = "";
        //当前页
        int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
        //每页显示条数
        int number = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
        //每页的开始记录  第一页为1  第二页为number +1
        int start = (intPage - 1) * number;
        return "{\"total\":" + dmService.GetRowCountEx(GLYClaimState1, fField, fValue)
                + ",\"rows\":" + dmService.findByPageEx(GLYClaimState1, fField, fValue, start, number) + "}";
    }

    @RequestMapping(value = "DomainNameFindGLYCodePage")
    @ResponseBody
    public String findGLYCodePage(String GLYCode, String fField, String fValue, String rows, String page) throws Exception {
        if (GLYCode == null) GLYCode = "";
        if (fField == null) fField = "";
        if (fValue == null) fValue = "";
        //当前页
        int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
        //每页显示条数
        int number = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
        //每页的开始记录  第一页为1  第二页为number +1
        int start = (intPage - 1) * number;
        return "{\"total\":" + dmService.GetGLYCodeRowCount(GLYCode, fField, fValue)
                + ",\"rows\":" + dmService.findGLYCodeByPage(GLYCode, fField, fValue, start, number) + "}";
    }

    @RequestMapping(value = "DomainNameExportToExcel")
    public void ExportToExcel(HttpServletResponse resp, String Disable, String DeleteMark, String CallMark, String fField, String fValue) throws Exception {
        Boolean Disable1 = StrUtil.toBoolean(Disable);
        Boolean DeleteMark1 = StrUtil.toBoolean(DeleteMark);
        Boolean CallMark1 = StrUtil.toBoolean(CallMark);
        if (fField == null) fField = "";
        if (fValue == null) fValue = "";
        resp.reset();
        resp.setContentType("application/vnd.ms-excel");
        resp.addHeader("Content-disposition", "attachment;filename=" + new String("域名记录".getBytes(), "ISO8859-1") + ".xls");
        OutputStream os = resp.getOutputStream();
        dmService.ExportToExcel(os, Disable1, DeleteMark1, CallMark1, fField, fValue);
    }


    @RequestMapping(value = "DomainNameFindNoSignRecordPage")
    @ResponseBody
    public String findNoSignRecordPage(String GLYCode, String fField, String fValue, String rows, String page) throws Exception {
        if (GLYCode == null) GLYCode = "";
        if (fField == null) fField = "";
        if (fValue == null) fValue = "";
        //当前页
        int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
        //每页显示条数
        int number = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
        //每页的开始记录  第一页为1  第二页为number +1
        int start = (intPage - 1) * number;
        return "{\"total\":" + dmService.GetNoSignRecordRowCount(GLYCode, fField, fValue)
                + ",\"rows\":" + dmService.findNoSignRecordByPage(GLYCode, fField, fValue, start, number) + "}";
    }

    @RequestMapping(value = "DomainNameFindNoRenewRecordPage")
    @ResponseBody
    public String findNoRenewRecordPage(String GLYCode, String fField, String fValue, String rows, String page) throws Exception {
        if (GLYCode == null) GLYCode = "";
        if (fField == null) fField = "";
        if (fValue == null) fValue = "";
        //当前页
        int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
        //每页显示条数
        int number = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
        //每页的开始记录  第一页为1  第二页为number +1
        int start = (intPage - 1) * number;
        return "{\"total\":" + dmService.GetNoRenewRecordRowCount(GLYCode, fField, fValue)
                + ",\"rows\":" + dmService.findNoRenewRecordByPage(GLYCode, fField, fValue, start, number) + "}";
    }

    @RequestMapping(value = "DomainNameImport")
    public ModelAndView studentImport(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/dm/DomainNameImport");
        return mav;
    }

    @RequestMapping(value = "DomainNameImportOK")
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
            msg = dmService.DomainNameImport(realPath + "/other/" + fileName1, new Date(), sessionService.GetUserLoginName(req.getSession()), sessionService.GetUserLoginRealName(req.getSession()));
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


    @RequestMapping(value = "DomainNameIdImgDownloadOK")
    public void DomainNameIdImgDownloadOK(HttpServletRequest req, HttpServletResponse resp, String DomainNameId) throws IOException {
        req.setCharacterEncoding("utf-8");
        String realPath = req.getSession().getServletContext().getRealPath("/upload/");
        DomainName dm1=dmService.findById(DomainNameId);
        String filePath1="";
        if(!"".equals(dm1.getDomainName())){
            filePath1=dm1.getDomainName().substring(0,dm1.getDomainName().lastIndexOf("."));
        }else if(!"".equals(dm1.getIPv4Address())){
            filePath1=dm1.getIPv4Address();
        }else if(!"".equals(dm1.getIPv6Address())){
           filePath1=dm1.getIPv6Address();
        }else{
           filePath1=dm1.getId();
        }
        if (dm1 != null) {

            String NewDir1 = realPath + "\\dm_attrFile\\" + filePath1;
            if (!FileTools.checkDirExist(NewDir1)) {    //文件夹不存在
                (new File(NewDir1)).mkdirs();   //创建
            }else{
                FileTools.deleteFileOrDir(NewDir1);
                (new File(NewDir1)).mkdirs();   //创建
            }
            String[] sFile1= new String[]{realPath+"\\dm_attrFile\\"+dm1.getGLYIdImgPath1()
                    ,realPath+"\\dm_attrFile\\"+dm1.getGLYIdImgPath2()
                    ,realPath+"\\dm_attrFile\\"+dm1.getGLYIdImgPath3()
                    ,realPath+"\\dm_attrFile\\"+dm1.getECPIdImgPath1()
                    ,realPath+"\\dm_attrFile\\"+dm1.getECPIdImgPath2()
                    ,realPath+"\\dm_attrFile\\"+dm1.getECPIdImgPath3()};
            String[] tmpNewFile1=new String[]{NewDir1+"\\管理员_"+dm1.getGLYName()+"_证件正面."+FileTools.getFileExtention(dm1.getGLYIdImgPath1())
                    ,NewDir1+"\\管理员_"+dm1.getGLYName()+"_证件反面."+FileTools.getFileExtention(dm1.getGLYIdImgPath2())
                    ,NewDir1+"\\管理员_"+dm1.getGLYName()+"_证件手持."+FileTools.getFileExtention(dm1.getGLYIdImgPath3())
                    ,NewDir1+"\\应急联络人_"+dm1.getECPName()+"_证件正面."+FileTools.getFileExtention(dm1.getECPIdImgPath1())
                    ,NewDir1+"\\应急联络人_"+dm1.getECPName()+"_证件反面."+FileTools.getFileExtention(dm1.getECPIdImgPath2())
                    ,NewDir1+"\\应急联络人_"+dm1.getECPName()+"_证件手持."+FileTools.getFileExtention(dm1.getECPIdImgPath3())};
            String[] tmpNewFile2=new String[]{NewDir1+"\\管理员_"+dm1.getGLYName()+"_证件正面(1)."+FileTools.getFileExtention(dm1.getGLYIdImgPath1())
                    ,NewDir1+"\\管理员_"+dm1.getGLYName()+"_证件反面(1)."+FileTools.getFileExtention(dm1.getGLYIdImgPath2())
                    ,NewDir1+"\\管理员_"+dm1.getGLYName()+"_证件手持(1)."+FileTools.getFileExtention(dm1.getGLYIdImgPath3())
                    ,NewDir1+"\\应急联络人_"+dm1.getECPName()+"_证件正面(1)."+FileTools.getFileExtention(dm1.getECPIdImgPath1())
                    ,NewDir1+"\\应急联络人_"+dm1.getECPName()+"_证件反面(1)."+FileTools.getFileExtention(dm1.getECPIdImgPath2())
                    ,NewDir1+"\\应急联络人_"+dm1.getECPName()+"_证件手持(1)."+FileTools.getFileExtention(dm1.getECPIdImgPath3())};
            for (int i1 = 0; i1 < sFile1.length; i1++) {
                if (!FileTools.isExists(tmpNewFile1[i1])) {   //文件名不存在
                    FileTools.CopyFile(sFile1[i1], tmpNewFile1[i1]);
                } else {  //文件名存在
                    FileTools.CopyFile(sFile1[i1], tmpNewFile2[i1]);
                }
            }
            String tmpZipFile1=realPath+"\\dm_attrFile\\"+filePath1+".zip";
            if(FileTools.isExists(tmpZipFile1)){
                FileTools.deleteFile(tmpZipFile1);
            }
         //   ZipUtil.zip(NewDir1,tmpZipFile1);
            ZipCompressorByAnt zc = new  ZipCompressorByAnt(tmpZipFile1);
           zc.compressExe(NewDir1);
            resp.reset();
            File obj = new File(tmpZipFile1);
            if (!obj.exists()) {
                resp.setContentType("text/html;charset=GBK");
                resp.getWriter().print("文件不存在");
                return;
            }
            resp.setHeader("Content-disposition", "attachment;filename=" + new String(new String(filePath1+".zip").getBytes(), "ISO8859-1"));
            resp.addHeader("Content-Length", "" + obj.length());
            resp.setContentType("application/octet-stream;charset=UTF-8");
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            OutputStream out = resp.getOutputStream();
            try {
                bis = new BufferedInputStream(new FileInputStream(tmpZipFile1));
                bos = new BufferedOutputStream(out);
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
                    bos.write(buff, 0, bytesRead);
            } catch (IOException e) {
                throw e;
            } finally {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            }
        }else{
            resp.setContentType("text/html;charset=GBK");
            resp.getWriter().print("域名记录不存在");
            return;
        }
    }
}
