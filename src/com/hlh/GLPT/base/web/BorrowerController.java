package com.hlh.GLPT.base.web;

import com.hlh.GLPT.base.domain.Borrower;
import com.hlh.GLPT.base.domain.StudentBorrower;
import com.hlh.GLPT.base.service.BorrowerService;
import com.hlh.GLPT.base.service.StudentBorrowerService;
import com.hlh.GLPT.core.service.SessionService;
import com.hlh.util.DESUtil;
import com.hlh.util.StrUtil;
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
 * Date: 14-4-8
 * Time: 下午6:51
 */
@Controller
@RequestMapping("/GLPT/BASE")
public class BorrowerController {
    @Autowired
    private BorrowerService borrowerService;
    @Autowired
    private StudentBorrowerService studentBorrowerService;
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "BorrowerList")
    public ModelAndView list(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/base/BorrowerList");
        return mav;
    }

    @RequestMapping(value = "BorrowerAdd")
    public ModelAndView add(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.addObject("SortCode", borrowerService.GetRowCount() + 1);
        mav.setViewName("GLPT/base/BorrowerAdd");
        return mav;
    }

    @RequestMapping(value = "BorrowerEdit")
    public ModelAndView edit(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("BorrowerId") != null) {
            Borrower borrower = borrowerService.findById(req.getParameter("BorrowerId"));
            mav.addObject("borrower", borrower);
        }
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/base/BorrowerEdit");
        return mav;
    }

    @RequestMapping(value = "BorrowerImport")
    public ModelAndView studentImport(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/base/BorrowerImport");
        return mav;
    }

    @RequestMapping(value = "BorrowerQRCode")
    public ModelAndView QRCode(HttpServletRequest req,String EnCode,int ImgWidth,int ImgHeight) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.addObject("EnCode",EnCode);
        mav.addObject("ImgWidth",ImgWidth);
        mav.addObject("ImgHeight",ImgHeight);
        mav.setViewName("GLPT/base/BorrowerQRCode");
        return mav;
    }


    @RequestMapping(value = "BorrowerAddOK")
    @ResponseBody
    public String addOK(HttpServletRequest req, Borrower borrower) {
        borrower.setBorrowerId(StrUtil.GetUUID());
        String msg = borrowerService.VerifyAdd(borrower);
        if ("OK".equals(msg)) {
            borrower.setCreateDate(new Date());
            borrower.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
            borrower.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
            msg = borrowerService.add(borrower);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }


    @RequestMapping(value = "BorrowerThoroughDelOK")
    @ResponseBody
    public String ThoroughDelOK(String BorrowerId) {
        String msg = "";
        if ("".equals(BorrowerId)) {
            msg = "请选择要操作的记录";
        } else {
            msg = borrowerService.ThoroughDel(BorrowerId);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "True";
        } else {
            RValue = "false";
        }
        return RValue;
    }

    @RequestMapping(value = "BorrowerBatchThoroughDelOK")
    @ResponseBody
    public String BatchThoroughDelOK(String BatchID) {
        String msg = "";
        if ("".equals(BatchID)) {
            msg = "请选择要操作的记录";
        } else {
            int YesCount = 0;
            String[] strings1 = BatchID.split("\\|");
            for (int i1 = 0; i1 < strings1.length; i1++) {
                msg = borrowerService.ThoroughDel(strings1[i1]);
                if ("OK".equals(msg)) {
                    YesCount++;
                }
            }
            if (YesCount == strings1.length) {
                msg = "OK";
            } else {
                msg = "删除结束,共选中[" + strings1.length + "],成功删除[" + YesCount + "]";
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

    @RequestMapping(value = "BorrowerDelOK")
    @ResponseBody
    public String delOK(HttpServletRequest req, String BorrowerId) {
        String msg = "";
        if ("".equals(BorrowerId)) {
            msg = "请选择要操作的记录";
        } else {
            msg = borrowerService.del(BorrowerId, new Date()
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

    @RequestMapping(value = "BorrowerEditOK")
    @ResponseBody
    public String editOK(HttpServletRequest req, Borrower borrower) {
        String msg = borrowerService.VerifyEdit(borrower);
        if ("OK".equals(msg)) {
            borrower.setModifyDate(new Date());
            borrower.setModifyUserCode(sessionService.GetUserLoginCode(req.getSession()));
            borrower.setModifyUserName(sessionService.GetUserLoginName(req.getSession()));
            msg = borrowerService.edit(borrower);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }


    @RequestMapping(value = "BorrowerResetPassword")
    public ModelAndView resetPassword(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("BorrowerId") != null) {
            mav.addObject("BorrowerId", req.getParameter("BorrowerId"));
        }
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/base/BorrowerResetPassword");
        return mav;
    }


    @RequestMapping(value = "BorrowerResetPasswordOK")
    @ResponseBody
    public String resetPasswordOK(HttpServletRequest req, String BorrowerId, String NewPassword, String ConfirmNewPassword) {
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
            msg = borrowerService.resetPassword(BorrowerId, DESUtil.encrypt(NewPassword), date1, date1
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

    @RequestMapping(value = "BorrowerEditPassword")
    public ModelAndView editPassword(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("BorrowerId") != null) {
            mav.addObject("BorrowerId", req.getParameter("BorrowerId"));
        }
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/base/BorrowerEditPassword");
        return mav;
    }


    @RequestMapping(value = "BorrowerEditPasswordOK")
    @ResponseBody
    public String editPasswordOK(HttpServletRequest req, String BorrowerId, String OldPassword, String NewPassword, String ConfirmNewPassword) {
        String msg = "";
        if ("".equals(NewPassword) || NewPassword == null) {
            msg = "新密码不能为空";
        } else if ("".equals(ConfirmNewPassword) || ConfirmNewPassword == null) {
            msg = "确认新密码不能为空";
        } else if (!NewPassword.equals(ConfirmNewPassword)) {
            msg = "密码不一致";
        } else if (!borrowerService.VerifyOldPassword(BorrowerId, OldPassword)) {
            msg = "旧密码不正确";
        }
        if ("".equals(msg)) {
            Date date1 = new Date();
            msg = borrowerService.resetPassword(BorrowerId, DESUtil.encrypt(NewPassword), date1, date1
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


    @RequestMapping(value = "BorrowerShow")
    public ModelAndView show(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("BorrowerId") != null) {
            Borrower borrower = borrowerService.findById(req.getParameter("BorrowerId"));
            mav.addObject("borrower", borrower);
        }
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/base/BorrowerShow");
        return mav;
    }

    @RequestMapping(value = "BorrowerFindPage")
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
            json1 = "{\"total\":" + borrowerService.GetRowCount(findValue1, Enabled1) + ",\"rows\":" + borrowerService.findByPage(findValue1, Enabled1, start, number) + "}";
        } else {
            json1 = "{\"total\":" + borrowerService.GetRowCount(fieldName1, findValue1, Enabled1) + ",\"rows\":" + borrowerService.findByPage(fieldName1, findValue1, Enabled1, start, number) + "}";
        }
        return json1;
    }

    @RequestMapping(value = "BorrowerFindPageEx")
    @ResponseBody
    public String findPageEx(String Enabled, String DeleteMark,String fField, String fValue, String rows, String page) throws Exception {
        Boolean Enabled1 = StrUtil.toBoolean(Enabled);
        Boolean DeleteMark1=StrUtil.toBoolean(DeleteMark);
        if(fField==null)fField="";
        if(fValue==null)fValue="";
        //当前页
        int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
        //每页显示条数
        int number = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
        //每页的开始记录  第一页为1  第二页为number +1
        int start = (intPage - 1) * number;
        return "{\"total\":" + borrowerService.GetRowCount(Enabled1,DeleteMark1,fField,fValue)
                + ",\"rows\":" + borrowerService.findByPage(Enabled1,DeleteMark1,fField,fValue, start, number) + "}";
    }

    @RequestMapping(value = "BorrowerSelect")
    public ModelAndView BorrowerSelect(String CallModule,String CallType,String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        mav.addObject("CallModule",CallModule);
        mav.addObject("CallType",CallType);
        mav.setViewName("GLPT/dm/BorrowerSelect");
        return mav;
    }


    @RequestMapping(value = "BorrowerImportOK")
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
            msg = borrowerService.BorrowerImport(realPath + "/other/" + fileName1, new Date(), sessionService.GetUserLoginCode(req.getSession()), sessionService.GetUserLoginName(req.getSession()));
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

    @RequestMapping(value = "BorrowerGetData")
    @ResponseBody
    public String GetData() {
        return borrowerService.findAll("BorrowerId,Code,FullName", new String[]{"BorrowerId", "Code", "FullName"});
    }

    @RequestMapping(value = "BorrowerGetDataEx")
    @ResponseBody
    public String GetDataEx() {
        return borrowerService.findAllEx("BorrowerId,Code,FullName", new String[]{"BorrowerId", "Code", "FullName"});
    }

    @RequestMapping(value = "BorrowerSelectOK")
    @ResponseBody
    public String BorrowerSelectOK(HttpServletRequest req) {
        StringBuilder sb1 = new StringBuilder();
        String fieldName1 = ""; //字段名
        if (req.getParameter("FName") != null) {
            fieldName1 = req.getParameter("FName");
        }
        String findValue1 = "";
        if (req.getParameter("FValue") != null) {
            findValue1 = req.getParameter("FValue");
        }
        if (!"".equals(fieldName1) && !"".equals(findValue1)) {
            List<Borrower> borrowerList = borrowerService.findBorrower(fieldName1, findValue1);
            if (borrowerList.size() == 1) {
                Borrower borrower1 = borrowerList.get(0);
                if (borrower1.isEnabled() == true) {
                    sb1.append("<div class='btnbarcontetn' style='margin-top: 1px; background: #fff'>");
                    sb1.append("<input type='hidden' id='BorrowerType' name='BorrowerType' value='教师'/>");
                    sb1.append("<input type='hidden' id='BorrowerCode' name='BorrowerCode' value='").append(borrower1.getCode()).append("'/>");
                    sb1.append("<input type='hidden' id='BorrowerName' name='BorrowerName' value='").append(borrower1.getFullName()).append("'/>");
                    sb1.append("<input type='hidden' id='BorrowerMobile' name='BorrowerMobile' value='").append(borrower1.getMobile()).append("'/>");
                    sb1.append("<table border='0' class='frm-find'>");
                    sb1.append("<tr>");
                    sb1.append("<td>");
                    sb1.append("<table border='0' class='frm-find'>");
                    sb1.append("<tr><th>教师工号：</th><td>").append(borrower1.getCode()).append("</td></tr>");
                    sb1.append("<tr><th>教师姓名：</th><td>").append(borrower1.getFullName()).append("</td></tr>");
                    sb1.append("<tr><th>性别：</th><td>").append(borrower1.getGender()).append("</td></tr>");
                    sb1.append("<tr><th>手机号码：</th><td>").append(borrower1.getMobile()).append("</td></tr>");
                    sb1.append("</table>");
                    sb1.append("</td>");
                    sb1.append("<td>");
                    sb1.append("<fieldset>");
                    sb1.append("<legend>相片</legend>");
                    sb1.append("<div>");
                    if ("".equals(borrower1.getPhotoImg()) || borrower1.getPhotoImg() == null) {
                        sb1.append("<label style='color:red;'>无相片</label>");
                    } else {
                        sb1.append("<img src='/upload/PhotoImg/").append(borrower1.getPhotoImg()).append("' alt='找不到相片' style='height: 200px;width: 180px;'/>");
                    }
                    sb1.append("</div>");
                    sb1.append("</fieldset>");
                    sb1.append("</td>");
                    sb1.append("</tr>");
                    sb1.append("</div>");
                } else { //当前获取的借用者已经被停用
                    sb1.append("<div class='WarmPrompt-Info'>当前获取的借用者已经被停用</div>");
                }
            } else if (borrowerList.size() == 0) {
                List<StudentBorrower> studentBorrowerList = studentBorrowerService.findStudentBorrower(fieldName1, findValue1);
                if (studentBorrowerList.size() == 1) {
                    StudentBorrower studentBorrower1 = studentBorrowerList.get(0);
                    if (studentBorrower1.isEnabled() == true) {
                        sb1.append("<div class='btnbarcontetn' style='margin-top: 1px; background: #fff'>");
                        sb1.append("<input type='hidden' id='BorrowerType' name='BorrowerType' value='学生'/>");
                        sb1.append("<input type='hidden' id='BorrowerCode' name='BorrowerCode' value='").append(studentBorrower1.getCode()).append("'/>");
                        sb1.append("<input type='hidden' id='BorrowerName' name='BorrowerName' value='").append(studentBorrower1.getFullName()).append("'/>");
                        sb1.append("<input type='hidden' id='BorrowerMobile' name='BorrowerMobile' value='").append(studentBorrower1.getMobile()).append("'/>");
                        sb1.append("<table border='0' class='frm-find'>");
                        sb1.append("<tr>");
                        sb1.append("<td>");
                        sb1.append("<table border='0' class='frm-find'>");
                        sb1.append("<tr><th>学生学号：</th><td>").append(studentBorrower1.getCode()).append("</td></tr>");
                        sb1.append("<tr><th>学生姓名：</th><td>").append(studentBorrower1.getFullName()).append("</td></tr>");
                        sb1.append("<tr><th>性别：</th><td>").append(studentBorrower1.getGender()).append("</td></tr>");
                        sb1.append("<tr><th>手机号码：</th><td>").append(studentBorrower1.getMobile()).append("</td></tr>");
                        sb1.append("</table>");
                        sb1.append("</td>");
                        sb1.append("<td>");
                        sb1.append("<fieldset>");
                        sb1.append("<legend>相片</legend>");
                        sb1.append("<div>");
                        if ("".equals(studentBorrower1.getPhotoImg()) || studentBorrower1.getPhotoImg() == null) {
                            sb1.append("<label style='color:red;'>无相片</label>");
                        } else {
                            sb1.append("<img src='/upload/StudentPhotoImg/").append(studentBorrower1.getPhotoImg()).append("' alt='找不到相片' style='height: 200px;width: 180px;'/>");
                        }
                        sb1.append("</div>");
                        sb1.append("</fieldset>");
                        sb1.append("</td>");
                        sb1.append("</tr>");
                        sb1.append("</div>");
                    } else { //当前获取的借用者已经被停用
                        sb1.append("<div class='WarmPrompt-Info'>当前获取的借用者已经被停用</div>");
                    }
                } else if (studentBorrowerList.size() == 0) {
                    sb1.append("<div class='WarmPrompt-Info'>请输入正确的借用者信息</div>");
                } else {   //当前获取的借用者不是唯一的，不能选择
                    sb1.append("<div class='WarmPrompt-Info'>当前获取的借用者不是唯一的，不能选择</div>");
                }
            } else {   //当前获取的借用者不是唯一的，不能选择
                sb1.append("<div class='WarmPrompt-Info'>当前获取的借用者不是唯一的，不能选择</div>");
            }
        } else { //请输入正确的借用者识别码、姓名、手机号码
            sb1.append("<div class='WarmPrompt-Info'>请输入正确的借用者识别码、姓名、手机号码</div>");
        }
        return sb1.toString();
    }


}
