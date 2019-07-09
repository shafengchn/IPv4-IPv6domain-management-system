package com.hlh.GLPT.core.web;

import com.hlh.GLPT.core.domain.ItemDetails;
import com.hlh.GLPT.core.service.ItemDetailsService;
import com.hlh.GLPT.core.service.ItemsService;
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

/**
 * User: 黄良辉
 * Date: 14-3-12
 * Time: 上午11:37
 */
@Controller
@RequestMapping("/GLPT/CORE")
public class ItemDetailsController {
     @Autowired
    private ItemDetailsService itemDetailsService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private ItemsService itemsService;

    @RequestMapping(value = "ItemDetailsList")
    public ModelAndView list(HttpServletRequest req) {
        ModelAndView mav=new ModelAndView();
        if(req.getParameter("IframeId")!=null){
            mav.addObject("IframeId",req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/core/ItemDetailsList");
        return mav;
    }

    @RequestMapping(value = "ItemDetailsAdd")
    public ModelAndView add(HttpServletRequest req) {
        ModelAndView mav=new ModelAndView();
        if (req.getParameter("ItemsId") != null) {
            mav.addObject("ItemsId",req.getParameter("ItemsId"));
        }
        if(req.getParameter("IframeId")!=null){
            mav.addObject("IframeId",req.getParameter("IframeId"));
        }
        mav.addObject("SortCode",itemDetailsService.GetRowCount()+1);
        mav.setViewName("GLPT/core/ItemDetailsAdd");
        return mav;
    }

    @RequestMapping(value = "ItemDetailsEdit")
    public ModelAndView edit(HttpServletRequest req) {
        ModelAndView mav=new ModelAndView();
        if(req.getParameter("ItemDetailsId")!=null){
            ItemDetails itemDetails=itemDetailsService.findById(req.getParameter("ItemDetailsId"));
            mav.addObject("itemDetails",itemDetails);
        }
        if(req.getParameter("IframeId")!=null){
            mav.addObject("IframeId",req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/core/ItemDetailsEdit");
        return mav;
    }

    @RequestMapping(value = "ItemDetailsAddOK")
    @ResponseBody
    public String addOK(HttpServletRequest req, ItemDetails itemDetails){
        itemDetails.setItemDetailsId(StrUtil.GetUUID());
        String msg = itemDetailsService.VerifyAdd(itemDetails);
        if ("OK".equals(msg)) {
            itemDetails.setCreateDate(new Date());
            itemDetails.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
            itemDetails.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
            msg = itemDetailsService.add(itemDetails);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }
    @RequestMapping(value="ItemDetailsThoroughDelOK")
    @ResponseBody
    public String ThoroughDelOK(String ItemDetailsId){
        String msg="";
        if("".equals(ItemDetailsId)){
            msg="请选择要操作的记录";
        }else{
            msg=itemDetailsService.ThoroughDel(ItemDetailsId);
        }
        String RValue="";
        if("OK".equals(msg)){
            RValue="True";
        }else{
            RValue="false";
        }
        return RValue;
    }

    @RequestMapping(value="ItemDetailsDelOK")
    @ResponseBody
    public String delOK(HttpServletRequest req,String ItemDetailsId){
        String msg="";
        if("".equals(ItemDetailsId)){
            msg="请选择要操作的记录";
        }else{
            msg=itemDetailsService.del(ItemDetailsId,new Date()
                    ,sessionService.GetUserLoginCode(req.getSession())
                    ,sessionService.GetUserLoginName(req.getSession()));
        }
        String RValue="";
        if("OK".equals(msg)){
            RValue="True";
        }else{
            RValue="false";
        }
        return RValue;
    }

    @RequestMapping(value = "ItemDetailsEditOK")
    @ResponseBody
    public String editOK(HttpServletRequest req,ItemDetails itemDetails){
        String msg = itemDetailsService.VerifyEdit(itemDetails);
        if ("OK".equals(msg)) {
            itemDetails.setModifyDate(new Date());
            itemDetails.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
            itemDetails.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
            msg = itemDetailsService.edit(itemDetails);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "ItemDetailsFindPage")
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
            json1 = "{\"total\":" + itemDetailsService.GetRowCount() + ",\"rows\":" + itemDetailsService.findByPage("", start, number) + "}";
        } else {
            json1 = "{\"total\":" + itemDetailsService.GetRowCount(findValue1) + ",\"rows\":" + itemDetailsService.findByPage(findValue1, start, number) + "}";
        }
        return json1;
    }

    @RequestMapping(value = "ItemDetailsFindData")
    @ResponseBody
    public String findByItemDetails(String ItemsName){  //ItemsName 数据字典主表的项目名称
        String ItemsId=itemsService.findByFullName(ItemsName); //获取数据字典主表的ＩＤ
        return itemDetailsService.findByItemDetails(ItemsId);
    }
    @RequestMapping(value = "ItemDetailsFindDataEx")
    @ResponseBody
    public String findByItemDetailsEx(String ItemsCode){  //ItemsName 数据字典主表的项目名称
        String ItemsId=itemsService.findByCode(ItemsCode); //获取数据字典主表的ＩＤ
        return itemDetailsService.findByItemDetails(ItemsId);
    }

    @RequestMapping(value = "ItemDetailsGetCheckBoxHtml")
    @ResponseBody
    public String GetGetCheckBoxHtml(String ItemsName) {
        String ItemsId=itemsService.findByFullName(ItemsName); //获取数据字典主表的ＩＤ
        List<ItemDetails> itemDetailsList = itemDetailsService.findAllEx(ItemsId);
        StringBuilder sb1 = new StringBuilder();
        sb1.append("<table>");
        int p1=0;
        for(int z1=0;z1<itemDetailsList.size();z1++){
            ItemDetails itemDetails1=itemDetailsList.get(z1);
            if(p1==0){
                sb1.append("<tr>");
                sb1.append("<td style='border:0px;'>");
                sb1.append("<input type=\"checkbox\" name=\"DLName\" id=\"").append(itemDetails1.getCode()).append("\" value=\"").append(itemDetails1.getCode()).append("\">");
                sb1.append("<label style=\"vertical-align: middle;\"");
                sb1.append(" for=\"").append(itemDetails1.getCode()).append("\">").append(itemDetails1.getFullName()).append("</label>");
                sb1.append("</td>");
                p1++;
            }else if(p1==4){
                sb1.append("<td style='border:0px;'>");
                sb1.append("<input type=\"checkbox\" name=\"DLName\" id=\"").append(itemDetails1.getCode()).append("\" value=\"").append(itemDetails1.getCode()).append("\">");
                sb1.append("<label style=\"vertical-align: middle;\"");
                sb1.append(" for=\"").append(itemDetails1.getCode()).append("\">").append(itemDetails1.getFullName()).append("</label>");
                sb1.append("</td>");
                sb1.append("</tr>");
                p1=0;
            } else{
                sb1.append("<td style='border:0px;'>");
                sb1.append("<input type=\"checkbox\" name=\"DLName\" id=\"").append(itemDetails1.getCode()).append("\" value=\"").append(itemDetails1.getCode()).append("\">");
                sb1.append("<label style=\"vertical-align: middle;\"");
                sb1.append(" for=\"").append(itemDetails1.getCode()).append("\">").append(itemDetails1.getFullName()).append("</label>");
                sb1.append("</td>");
                p1++;
            }
            if(z1==itemDetailsList.size()-1){
                if(p1<4&&p1>0){
                    for(int j1=p1;j1<=4;j1++){
                        sb1.append("<td>").append("&nbsp;").append("</td>");
                    }
                    sb1.append("</tr>");
                }
            }
        }
        sb1.append("</table>");
        return sb1.toString();
    }

    @RequestMapping(value = "ItemDetailsGetRadioHtml")
    @ResponseBody
    public String GetGetRadioHtml(String ItemsName) {
        String ItemsId=itemsService.findByFullName(ItemsName); //获取数据字典主表的ＩＤ
        List<ItemDetails> itemDetailsList = itemDetailsService.findAllEx(ItemsId);
        StringBuilder sb1 = new StringBuilder();
        sb1.append("<table>");
        int p1=0;
        for(int z1=0;z1<itemDetailsList.size();z1++){
            ItemDetails itemDetails1=itemDetailsList.get(z1);
            if(p1==0){
                sb1.append("<tr>");
                sb1.append("<td style='border:0px;'>");
                sb1.append("<input type=\"radio\" name=\"DLCode\" id=\"").append(itemDetails1.getCode()).append("\" value=\"").append(itemDetails1.getCode()).append("\">");
                sb1.append("<label style=\"vertical-align: middle;\"");
                sb1.append(" for=\"").append(itemDetails1.getCode()).append("\">").append(itemDetails1.getFullName()).append("</label>");
                sb1.append("</td>");
                p1++;
            }else if(p1==4){
                sb1.append("<td style='border:0px;'>");
                sb1.append("<input type=\"radio\" name=\"DLCode\" id=\"").append(itemDetails1.getCode()).append("\" value=\"").append(itemDetails1.getCode()).append("\">");
                sb1.append("<label style=\"vertical-align: middle;\"");
                sb1.append(" for=\"").append(itemDetails1.getCode()).append("\">").append(itemDetails1.getFullName()).append("</label>");
                sb1.append("</td>");
                sb1.append("</tr>");
                p1=0;
            } else{
                sb1.append("<td style='border:0px;'>");
                sb1.append("<input type=\"radio\" name=\"DLCode\" id=\"").append(itemDetails1.getCode()).append("\" value=\"").append(itemDetails1.getCode()).append("\">");
                sb1.append("<label style=\"vertical-align: middle;\"");
                sb1.append(" for=\"").append(itemDetails1.getCode()).append("\">").append(itemDetails1.getFullName()).append("</label>");
                sb1.append("</td>");
                p1++;
            }
            if(z1==itemDetailsList.size()-1){
                if(p1<4&&p1>0){
                    for(int j1=p1;j1<=4;j1++){
                        sb1.append("<td>").append("&nbsp;").append("</td>");
                    }
                    sb1.append("</tr>");
                }
            }
        }
        sb1.append("</table>");
        return sb1.toString();
    }

}
