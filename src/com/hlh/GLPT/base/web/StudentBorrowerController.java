package com.hlh.GLPT.base.web;

import com.hlh.GLPT.base.domain.Borrower;
import com.hlh.GLPT.base.domain.StudentBorrower;
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
public class StudentBorrowerController {
    @Autowired
    private StudentBorrowerService studentBorrowerService;
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "StudentBorrowerList")
    public ModelAndView list(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/base/StudentBorrowerList");
        return mav;
    }

    @RequestMapping(value = "StudentBorrowerAdd")
    public ModelAndView add(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.addObject("SortCode", studentBorrowerService.GetRowCount() + 1);
        mav.setViewName("GLPT/base/StudentBorrowerAdd");
        return mav;
    }

    @RequestMapping(value = "StudentBorrowerEdit")
    public ModelAndView edit(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("StudentBorrowerId") != null) {
            StudentBorrower studentBorrower = studentBorrowerService.findById(req.getParameter("StudentBorrowerId"));
            mav.addObject("studentBorrower", studentBorrower);
        }
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/base/StudentBorrowerEdit");
        return mav;
    }

    @RequestMapping(value = "StudentBorrowerImport")
    public ModelAndView studentImport(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/base/StudentBorrowerImport");
        return mav;
    }

    @RequestMapping(value = "StudentBorrowerQRCode")
    public ModelAndView QRCode(HttpServletRequest req,String EnCode,int ImgWidth,int ImgHeight) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.addObject("EnCode",EnCode);
        mav.addObject("ImgWidth",ImgWidth);
        mav.addObject("ImgHeight",ImgHeight);
        mav.setViewName("GLPT/base/StudentBorrowerQRCode");
        return mav;
    }


    @RequestMapping(value = "StudentBorrowerAddOK")
    @ResponseBody
    public String addOK(HttpServletRequest req, StudentBorrower studentBorrower) {
        studentBorrower.setStudentBorrowerId(StrUtil.GetUUID());
        String msg = studentBorrowerService.VerifyAdd(studentBorrower);
        if ("OK".equals(msg)) {
            studentBorrower.setCreateDate(new Date());
            studentBorrower.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
            studentBorrower.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
            msg = studentBorrowerService.add(studentBorrower);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }


    @RequestMapping(value = "StudentBorrowerThoroughDelOK")
    @ResponseBody
    public String ThoroughDelOK(String StudentBorrowerId) {
        String msg = "";
        if ("".equals(StudentBorrowerId)) {
            msg = "请选择要操作的记录";
        } else {
            msg = studentBorrowerService.ThoroughDel(StudentBorrowerId);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "True";
        } else {
            RValue = "false";
        }
        return RValue;
    }

    @RequestMapping(value = "StudentBorrowerBatchThoroughDelOK")
    @ResponseBody
    public String BatchThoroughDelOK(String BatchID) {
        String msg = "";
        if ("".equals(BatchID)) {
            msg = "请选择要操作的记录";
        } else {
            int YesCount = 0;
            String[] strings1 = BatchID.split("\\|");
            for (int i1 = 0; i1 < strings1.length; i1++) {
                msg = studentBorrowerService.ThoroughDel(strings1[i1]);
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

    @RequestMapping(value = "StudentBorrowerDelOK")
    @ResponseBody
    public String delOK(HttpServletRequest req, String StudentBorrowerId) {
        String msg = "";
        if ("".equals(StudentBorrowerId)) {
            msg = "请选择要操作的记录";
        } else {
            msg = studentBorrowerService.del(StudentBorrowerId, new Date()
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

    @RequestMapping(value = "StudentBorrowerEditOK")
    @ResponseBody
    public String editOK(HttpServletRequest req, StudentBorrower studentBorrower) {
        String msg = studentBorrowerService.VerifyEdit(studentBorrower);
        if ("OK".equals(msg)) {
            studentBorrower.setModifyDate(new Date());
            studentBorrower.setModifyUserCode(sessionService.GetUserLoginCode(req.getSession()));
            studentBorrower.setModifyUserName(sessionService.GetUserLoginName(req.getSession()));
            msg = studentBorrowerService.edit(studentBorrower);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }


    @RequestMapping(value = "StudentBorrowerResetPassword")
    public ModelAndView resetPassword(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("StudentBorrowerId") != null) {
            mav.addObject("StudentBorrowerId", req.getParameter("StudentBorrowerId"));
        }
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/base/StudentBorrowerResetPassword");
        return mav;
    }


    @RequestMapping(value = "StudentBorrowerResetPasswordOK")
    @ResponseBody
    public String resetPasswordOK(HttpServletRequest req, String StudentBorrowerId, String NewPassword, String ConfirmNewPassword) {
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
            msg = studentBorrowerService.resetPassword(StudentBorrowerId, DESUtil.encrypt(NewPassword), date1, date1
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

    @RequestMapping(value = "StudentBorrowerEditPassword")
    public ModelAndView editPassword(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("StudentBorrowerId") != null) {
            mav.addObject("StudentBorrowerId", req.getParameter("StudentBorrowerId"));
        }
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/base/StudentBorrowerEditPassword");
        return mav;
    }


    @RequestMapping(value = "StudentBorrowerEditPasswordOK")
    @ResponseBody
    public String editPasswordOK(HttpServletRequest req, String StudentBorrowerId, String OldPassword, String NewPassword, String ConfirmNewPassword) {
        String msg = "";
        if ("".equals(NewPassword) || NewPassword == null) {
            msg = "新密码不能为空";
        } else if ("".equals(ConfirmNewPassword) || ConfirmNewPassword == null) {
            msg = "确认新密码不能为空";
        } else if (!NewPassword.equals(ConfirmNewPassword)) {
            msg = "密码不一致";
        } else if (!studentBorrowerService.VerifyOldPassword(StudentBorrowerId, OldPassword)) {
            msg = "旧密码不正确";
        }
        if ("".equals(msg)) {
            Date date1 = new Date();
            msg = studentBorrowerService.resetPassword(StudentBorrowerId, DESUtil.encrypt(NewPassword), date1, date1
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


    @RequestMapping(value = "StudentBorrowerShow")
    public ModelAndView show(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("StudentBorrowerId") != null) {
            StudentBorrower studentBorrower = studentBorrowerService.findById(req.getParameter("StudentBorrowerId"));
            mav.addObject("studentBorrower", studentBorrower);
        }
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/base/StudentBorrowerShow");
        return mav;
    }

    @RequestMapping(value = "StudentBorrowerFindPage")
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
            json1 = "{\"total\":" + studentBorrowerService.GetRowCount(findValue1, Enabled1) + ",\"rows\":" + studentBorrowerService.findByPage(findValue1, Enabled1, start, number) + "}";
        } else {
            json1 = "{\"total\":" + studentBorrowerService.GetRowCount(fieldName1, findValue1, Enabled1) + ",\"rows\":" + studentBorrowerService.findByPage(fieldName1, findValue1, Enabled1, start, number) + "}";
        }
        return json1;
    }

    @RequestMapping(value = "StudentBorrowerImportOK")
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
            msg = studentBorrowerService.StudentBorrowerImport(realPath + "/other/" + fileName1, new Date(), sessionService.GetUserLoginCode(req.getSession()), sessionService.GetUserLoginName(req.getSession()));
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

    @RequestMapping(value = "StudentBorrowerGetData")
    @ResponseBody
    public String GetData() {
        return studentBorrowerService.findAll("StudentBorrowerId,Code,FullName", new String[]{"StudentBorrowerId", "Code", "FullName"});
    }

    @RequestMapping(value = "StudentBorrowerSelectOK")
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
            List<StudentBorrower> borrowerList = studentBorrowerService.findStudentBorrower(fieldName1, findValue1);
            if (borrowerList.size() == 1) {
                StudentBorrower studentBorrower1 = borrowerList.get(0);
                if (studentBorrower1.isEnabled() == true) {
                    sb1.append("<div class='btnbarcontetn' style='margin-top: 1px; background: #fff'>");
                    sb1.append("<input type='hidden' id='BorrowerCode' name='BorrowerCode' value='").append(studentBorrower1.getCode()).append("'/>");
                    sb1.append("<input type='hidden' id='BorrowerName' name='BorrowerName' value='").append(studentBorrower1.getFullName()).append("'/>");
                    sb1.append("<table border='0' class='frm-find'>");
                    sb1.append("<tr><th>学生学号：</th><td>").append(studentBorrower1.getCode());
                    sb1.append("</td><th>学生姓名：</th><td>").append(studentBorrower1.getFullName());
                    sb1.append("</td><th>性别：</th><td>").append(studentBorrower1.getGender());
                    sb1.append("</td><th>手机号码：</th><td>").append(studentBorrower1.getMobile()).append("</td></tr>");
                    sb1.append("</table>");
                    sb1.append("</div>");
                } else { //当前获取的借用者已经被停用
                    sb1.append("<div class='WarmPrompt-Info'>当前获取的借用者已经被停用</div>");
                }
            }else if(borrowerList.size()==0){
                sb1.append("<div class='WarmPrompt-Info'>请输入正确的借用者信息</div>");
            } else {   //当前获取的借用者不是唯一的，不能选择
                sb1.append("<div class='WarmPrompt-Info'>当前获取的借用者不是唯一的，不能选择</div>");
            }
        } else { //请输入正确的借用者识别码、姓名、手机号码
            sb1.append("<div class='WarmPrompt-Info'>请输入正确的借用者识别码、姓名、手机号码</div>");
        }
        return sb1.toString();
    }


}
