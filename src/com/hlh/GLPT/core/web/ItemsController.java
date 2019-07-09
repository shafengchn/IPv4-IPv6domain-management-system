package com.hlh.GLPT.core.web;

import com.hlh.GLPT.core.domain.Items;
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

/**
 * User: 黄良辉
 * Date: 14-3-12
 * Time: 上午11:37
 */
@Controller
@RequestMapping("/GLPT/CORE")
public class ItemsController {
     @Autowired
    private ItemsService itemsService;
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "ItemsList")
    public ModelAndView list(HttpServletRequest req) {
        ModelAndView mav=new ModelAndView();
        if(req.getParameter("IframeId")!=null){
            mav.addObject("IframeId",req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/core/ItemsList");
        return mav;
    }

    @RequestMapping(value = "ItemsAdd")
    public ModelAndView add(HttpServletRequest req) {
        ModelAndView mav=new ModelAndView();
        if (req.getParameter("ParentId") != null) {
            mav.addObject("ParentId",req.getParameter("ParentId"));
        }
        if(req.getParameter("IframeId")!=null){
            mav.addObject("IframeId",req.getParameter("IframeId"));
        }
        mav.addObject("SortCode",itemsService.GetRowCount()+1);
        mav.setViewName("GLPT/core/ItemsAdd");
        return mav;
    }

    @RequestMapping(value = "ItemsEdit")
    public ModelAndView edit(HttpServletRequest req) {
        ModelAndView mav=new ModelAndView();
        if(req.getParameter("ItemsId")!=null){
            Items items=itemsService.findById(req.getParameter("ItemsId"));
            mav.addObject("items",items);
        }
        if(req.getParameter("IframeId")!=null){
            mav.addObject("IframeId",req.getParameter("IframeId"));
        }
        mav.setViewName("GLPT/core/ItemsEdit");
        return mav;
    }

    @RequestMapping(value = "ItemsAddOK")
    @ResponseBody
    public String addOK(HttpServletRequest req, Items items){
        items.setItemsId(StrUtil.GetUUID());
        String msg = itemsService.VerifyAdd(items);
        if ("OK".equals(msg)) {
            items.setCreateDate(new Date());
            items.setCreateUserCode(sessionService.GetUserLoginCode(req.getSession()));
            items.setCreateUserName(sessionService.GetUserLoginName(req.getSession()));
            msg = itemsService.add(items);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }
    @RequestMapping(value="ItemsThoroughDelOK")
    @ResponseBody
    public String ThoroughDelOK(String ItemsId){
        String msg="";
        if("".equals(ItemsId)){
            msg="请选择要操作的记录";
        }else{
            msg=itemsService.ThoroughDel(ItemsId);
        }
        String RValue="";
        if("OK".equals(msg)){
            RValue="True";
        }else{
            RValue="false";
        }
        return RValue;
    }

    @RequestMapping(value="ItemsDelOK")
    @ResponseBody
    public String delOK(HttpServletRequest req,String ItemsId){
        String msg="";
        if("".equals(ItemsId)){
            msg="请选择要操作的记录";
        }else{
            msg=itemsService.del(ItemsId,new Date()
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

    @RequestMapping(value = "ItemsEditOK")
    @ResponseBody
    public String editOK(HttpServletRequest req,Items items){
        String msg = itemsService.VerifyEdit(items);
        if ("OK".equals(msg)) {
            items.setModifyDate(new Date());
            items.setModifyUserCode(sessionService.GetUserLoginCode(req.getSession()));
            items.setModifyUserName(sessionService.GetUserLoginName(req.getSession()));
            msg = itemsService.edit(items);
        }
        String RValue = "";
        if ("OK".equals(msg)) {
            RValue = "{\"success\":true,\"info\":\"OK\"}";
        } else {
            RValue = "{\"success\":false,\"info\":\"" + msg + "\"}";
        }
        return RValue;
    }

    @RequestMapping(value = "ItemsFindAllTreeSimpleData")
    @ResponseBody
    public String FindAllTreeSimpleData() throws Exception {
        return itemsService.FindAllTreeSimpleData();
    }
}
