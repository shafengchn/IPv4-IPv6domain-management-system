package com.hlh.GLPT.base.web;

import com.hlh.GLPT.base.domain.Department;
import com.hlh.GLPT.base.service.DepartmentService;
import com.hlh.GLPT.core.service.ItemDetailsService;
import com.hlh.GLPT.core.service.SessionService;
import com.hlh.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * User: 黄良辉
 * Date: 14-6-8
 * Time: 上午11:00
 */
@Controller
@RequestMapping("/GLPT/BASE")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private ItemDetailsService itemDetailsService;

    @RequestMapping(value = "DepartmentList")
    public ModelAndView list(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/base/DepartmentList");
        return mav;
    }

    @RequestMapping(value = "DepartmentAdd")
    public ModelAndView add(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.addObject("SortCode",departmentService.GetRowCount()+1);
        mav.setViewName("GLPT/base/DepartmentAdd");
        return mav;
    }

    @RequestMapping(value = "DepartmentEdit")
    public ModelAndView edit(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if (req.getParameter("DepartmentId") != null) {
            Department department = departmentService.findById(req.getParameter("DepartmentId"));
            mav.addObject("department", department);
        }
        if (req.getParameter("IframeId") != null) {
            mav.addObject("IframeId", req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/base/DepartmentEdit");
        return mav;
    }

    @RequestMapping(value = "DepartmentAddOK")
    @ResponseBody
    public String addOK(HttpServletRequest req, Department department) {
        department.setDepartmentId(StrUtil.GetUUID());
        String msg = departmentService.VerifyAdd(department);
        if ("OK".equals(msg)) {
            department.setCreateDate(new Date());
            department.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
            department.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
            msg = departmentService.add(department);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "DepartmentThoroughDelOK")
    @ResponseBody
    public String ThoroughDelOK(String DepartmentId) {
        String msg = "";
        if ("".equals(DepartmentId)) {
            msg = "请选择要操作的记录";
        } else {
            msg = departmentService.ThoroughDel(DepartmentId);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "True";
        } else {
            RValue = "false";
        }
        return RValue;
    }

    @RequestMapping(value = "DepartmentDelOK")
    @ResponseBody
    public String delOK(HttpServletRequest req, String DepartmentId) {
        String msg = "";
        if ("".equals(DepartmentId)) {
            msg = "请选择要操作的记录";
        } else {
            msg = departmentService.del(DepartmentId, new Date()
                    , sessionService.GetUserLoginCode(req.getSession())
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

    @RequestMapping(value = "DepartmentEditOK")
    @ResponseBody
    public String editOK(HttpServletRequest req, Department department) {
        String msg = departmentService.VerifyEdit(department);
        if ("OK".equals(msg)) {
            department.setModifyDate(new Date());
            department.setModifyUserCode(sessionService.GetUserLoginCode(req.getSession()));
            department.setModifyUserName(sessionService.GetUserLoginName(req.getSession()));
            msg = departmentService.edit(department);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "DepartmentFindPage")
    @ResponseBody
    public String findPage(HttpServletRequest req) throws Exception {
        String findValue1 = "";
        if (req.getParameter("FValue") != null) {
            findValue1 = req.getParameter("FValue");
        }
        boolean Enabled1 = false; //只显有效
        if (req.getParameter("Enabled") != null) {
            if ("checked".equals(req.getParameter("Enabled"))) {
                Enabled1 = true;
            }
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
        return "{\"total\":" + departmentService.GetRowCount(findValue1,Enabled1) + ",\"rows\":" + departmentService.findByPage(findValue1,Enabled1, start, number) + "}";
    }

    @RequestMapping(value = "DepartmentGetData")
    @ResponseBody
    public String GetData() {
        return departmentService.findAll("DepartmentId,Code,FullName", new String[]{"DepartmentId", "Code", "FullName"});
    }

    @RequestMapping(value = "DepartmentGetDataEx")
    @ResponseBody
    public String GetDataEx(String BMLXName) {
        //BMLXName 部门类型名称
        String BMLXCode1=itemDetailsService.FullNameToCode("部门类型",BMLXName);
        return departmentService.findAllEx("DepartmentId,Code,FullName", new String[]{"DepartmentId", "Code", "FullName"},BMLXCode1);
    }

    @RequestMapping(value = "DepartmentGetCheckBoxHtml")
    @ResponseBody
    public String GetGetCheckBoxHtml() {
        List<Department> departmentList = departmentService.findAllEx();
        StringBuilder sb1 = new StringBuilder();
        int i1 = 0;
        sb1.append("<table>");
        sb1.append("<tr>");
        for(int j1=0;j1<departmentList.size();j1++){
            Department department1=departmentList.get(j1);
            if(i1==5){
                sb1.append("</tr>");
                sb1.append("<tr>");
                i1=0;
            }else{
                sb1.append("<td style='border:0px;'>");
                sb1.append("<input type=\"checkbox\" name=\"DLName\" id=\"").append(department1.getCode()).append("\" value=\"").append(department1.getCode()).append("\">");
                sb1.append("<label style=\"vertical-align: middle;\"");
                sb1.append(" for=\"").append(department1.getCode()).append("\">").append(department1.getFullName()).append("</label>");
                sb1.append("</td>");
                i1++;
            }
        }
        if (i1 < 5 && i1 > 0) {
            for(int j1=i1;j1<5;j1++){
                sb1.append("<td></td>");
            }
            sb1.append("</tr>");
        }
        sb1.append("</table>");
        return sb1.toString();
    }

    @RequestMapping(value = "DepartmentGetRadioHtml")
    @ResponseBody
    public String GetGetRadioHtml() {
        List<Department> departmentList = departmentService.findAllEx();
        StringBuilder sb1 = new StringBuilder();
        int i1 = 0;
        sb1.append("<table>");
        sb1.append("<tr>");
        for(int j1=0;j1<departmentList.size();j1++){
            Department department1=departmentList.get(j1);
            if(i1==5){
                sb1.append("</tr>");
                sb1.append("<tr>");
                i1=0;
            }else{
                sb1.append("<td style='border:0px;'>");
                sb1.append("<input type=\"radio\" name=\"DLCode\" id=\"").append(department1.getCode()).append("\" value=\"").append(department1.getCode()).append("\">");
                sb1.append("<label style=\"vertical-align: middle;\"");
                sb1.append(" for=\"").append(department1.getCode()).append("\">").append(department1.getFullName()).append("</label>");
                sb1.append("</td>");
                i1++;
            }
        }
        if (i1 < 5 && i1 > 0) {
            for(int j1=i1;j1<5;j1++){
                sb1.append("<td></td>");
            }
            sb1.append("</tr>");
        }
        sb1.append("</table>");
        return sb1.toString();
    }
}
