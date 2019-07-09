package com.hlh.GLPT.base.web;

import com.hlh.GLPT.base.domain.Borrower;
import com.hlh.GLPT.base.domain.BorrowerAndDepartment;
import com.hlh.GLPT.base.service.BorrowerAndDepartmentService;
import com.hlh.GLPT.base.service.BorrowerService;
import com.hlh.GLPT.core.service.SessionService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * User: 黄良辉
 * Date: 14-5-30
 * Time: 上午11:16
 */

@Controller
@RequestMapping("/GLPT/BASE")
public class BorrowerAndDepartmentController {

    @Autowired
    private BorrowerAndDepartmentService borrowerAndDepartmentService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private BorrowerService borrowerService;

    @RequestMapping(value = "BorrowerAndDepartmentList")
    public ModelAndView list(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        if (req.getParameter("BorrowerCode") != null) {
            mav.addObject("BorrowerCode", req.getParameter("BorrowerCode"));
        }
        mav.setViewName("GLPT/base/BorrowerAndDepartmentList");
        return mav;
    }

    @RequestMapping(value = "BorrowerAndDepartmentAdd")
    public ModelAndView add(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        if (req.getParameter("BorrowerCode") != null) {
            String BorrowerCode1 = req.getParameter("BorrowerCode");
            mav.addObject("BorrowerCode", BorrowerCode1);
            mav.addObject("BorrowerName", borrowerService.CodeToFullName(BorrowerCode1));
        }
        mav.setViewName("GLPT/base/BorrowerAndDepartmentAdd");
        return mav;
    }

    @RequestMapping(value = "BorrowerAndDepartmentEdit")
    public ModelAndView edit(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("ID") != null) {
            BorrowerAndDepartment borrowerAndDepartment = borrowerAndDepartmentService.findById(Integer.parseInt(req.getParameter("ID")));
            mav.addObject("borrowerAndDepartment", borrowerAndDepartment);
            mav.addObject("BorrowerName", borrowerService.CodeToFullName(borrowerAndDepartment.getBorrowerCode()));
        }
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/base/BorrowerAndDepartmentEdit");
        return mav;
    }

    @RequestMapping(value = "BorrowerAndDepartmentAddOK")
    @ResponseBody
    public String addOK(HttpServletRequest req, BorrowerAndDepartment borrowerAndDepartment) {
        String msg = borrowerAndDepartmentService.VerifyAdd(borrowerAndDepartment);
        if ("OK".equals(msg)) {
            borrowerAndDepartment.setCreateDate(new Date());
            borrowerAndDepartment.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
            borrowerAndDepartment.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
            msg = borrowerAndDepartmentService.add(borrowerAndDepartment);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }


    @RequestMapping(value = "BorrowerAndDepartmentThoroughDelOK")
    @ResponseBody
    public String ThoroughDelOK(String ID) {
        String msg = "";
        if ("".equals(ID)) {
            msg = "请选择要操作的记录";
        } else {
            msg = borrowerAndDepartmentService.ThoroughDel(Integer.parseInt(ID));
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "True";
        } else {
            RValue = "false";
        }
        return RValue;
    }

    @RequestMapping(value = "BorrowerAndDepartmentEditOK")
    @ResponseBody
    public String editOK(HttpServletRequest req, BorrowerAndDepartment borrowerAndDepartment) {
        String msg = borrowerAndDepartmentService.VerifyEdit(borrowerAndDepartment);
        if ("OK".equals(msg)) {
            borrowerAndDepartment.setModifyDate(new Date());
            borrowerAndDepartment.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
            borrowerAndDepartment.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
            msg = borrowerAndDepartmentService.edit(borrowerAndDepartment);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "BorrowerAndDepartmentFindPage")
    @ResponseBody
    public String findPage(HttpServletRequest req) throws Exception {
        String findValue1 = "";
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
        if ("".equals(findValue1)) {
            json1 = "{\"total\":" + borrowerAndDepartmentService.GetRowCount() + ",\"rows\":" + borrowerAndDepartmentService.findByPage("", start, number) + "}";
        } else {
            json1 = "{\"total\":" + borrowerAndDepartmentService.GetRowCount(findValue1) + ",\"rows\":" + borrowerAndDepartmentService.findByPage(findValue1, start, number) + "}";
        }
        return json1;
    }

    @RequestMapping(value = "BorrowerAndDepartmentFindBorrowerByPage")
    @ResponseBody
    public String findBorrowerByPage(HttpServletRequest req) throws Exception {
        String findValue1 = "";
        if (req.getParameter("FValue") != null) {
            findValue1 = req.getParameter("FValue");
        }
        String BorrowerCode1 = "";
        if (req.getParameter("BorrowerCode") != null) {
            BorrowerCode1 = req.getParameter("BorrowerCode");
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
        if ("".equals(findValue1)) {
            json1 = "{\"total\":" + borrowerAndDepartmentService.GetBorrowerRowCount(BorrowerCode1, "") + ",\"rows\":" + borrowerAndDepartmentService.findBorrowerByPage(BorrowerCode1, "", start, number) + "}";
        } else {
            json1 = "{\"total\":" + borrowerAndDepartmentService.GetBorrowerRowCount(BorrowerCode1, findValue1) + ",\"rows\":" + borrowerAndDepartmentService.findBorrowerByPage(BorrowerCode1, findValue1, start, number) + "}";
        }
        return json1;
    }


    @RequestMapping(value = "BorrowerAndDepartmentBatchAdd")
    public ModelAndView BorrowerAndDepartmentBatchAdd(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("BorrowerId") != null) {
            Borrower borrower1 = borrowerService.findById(req.getParameter("BorrowerId"));
            mav.addObject("borrower", borrower1);
            //所有部门
            JSONArray departmentJsonArray = borrowerAndDepartmentService.GetBorrowerAndDepartmentChecked(borrower1.getCode());
            mav.addObject("allDepartment", departmentJsonArray);
        }

        //所有
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/base/BorrowerAndDepartmentBatchAdd");
        return mav;
    }

    @RequestMapping(value = "BorrowerAndDepartmentBatchAddOK")
    @ResponseBody
    public String BorrowerAndDepartmentBatchAddK(HttpServletRequest req, String BorrowerId, String[] chk_Department) {
        String msg = "";
        Date date1 = new Date();
        int YesCount1 = 0;
        if ("".equals(BorrowerId)) {
            msg = "无法获取取用户信息,请重新登录!";
        }
        if (chk_Department != null) {
            if ("".equals(msg)) {
                Borrower borrower1 = borrowerService.findById(BorrowerId);
                borrowerAndDepartmentService.ThoroughByBorrowerCodeDel(borrower1.getCode());
                int i1 = 0;
                for (String myDepartment : chk_Department) {
                    i1++;
                    BorrowerAndDepartment borrowerAndDepartment1 = new BorrowerAndDepartment();
                    borrowerAndDepartment1.setBorrowerCode(borrower1.getCode());
                    borrowerAndDepartment1.setDepartmentCode(myDepartment);
                    borrowerAndDepartment1.setCreateDate(date1);
                    borrowerAndDepartment1.setEnabled(true);
                    borrowerAndDepartment1.setSortCode(i1);
                    borrowerAndDepartment1.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
                    borrowerAndDepartment1.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
                    String RValue1 = borrowerAndDepartmentService.add(borrowerAndDepartment1);
                    if ("OK".equals(RValue1)) {
                        YesCount1++;
                    }
                }
                if (YesCount1 == chk_Department.length) {
                    msg = "OK";
                } else {
                    msg = "设置的部门与选中的部门个数不一致";
                }
            }
        } else {
            Borrower borrower1 = borrowerService.findById(BorrowerId);
            borrowerAndDepartmentService.ThoroughByBorrowerCodeDel(borrower1.getCode());
            msg = "OK";
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }
}
