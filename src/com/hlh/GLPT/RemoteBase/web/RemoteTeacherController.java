package com.hlh.GLPT.RemoteBase.web;

import com.hlh.GLPT.RemoteBase.service.RemoteTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Package: com.hlh.GLPT.RemoteBase.web
 * User: 黄良辉  16-4-24  上午9:35
 */
@Controller
@RequestMapping("/GLPT/RemoteBASE")
public class RemoteTeacherController {
    @Autowired
    private RemoteTeacherService remoteTeacherService;

    @RequestMapping(value = "RemoteTeacherFindPage")
    @ResponseBody
    public String findPage(String fField, String fValue, String rows, String page) throws Exception {
        if(fValue==null)fValue="";
        //当前页
        int intPage = Integer.parseInt((page == null || page == "0") ? "0" : page);
        //每页显示条数
        int number = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
        //每页的开始记录  第一页为1  第二页为number +1
        int start = (intPage - 1) * number;
        return "{\"total\":" + remoteTeacherService.GetRowCount(fField,fValue)
                + ",\"rows\":" + remoteTeacherService.findByPage(fField,fValue, start, number) + "}";
    }


    @RequestMapping(value = "RemoteTeacherSelect")
    public ModelAndView TeacherSelect(String CallModule,String CallType,String IframeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("IframeId", IframeId);
        mav.addObject("CallModule",CallModule);
        mav.addObject("CallType",CallType);
        mav.setViewName("GLPT/dm/RemoteTeacherSelect");
        return mav;
    }

}
