package com.hlh.GLPT.RemoteBase.web;

import com.hlh.GLPT.RemoteBase.service.RemoteDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Package: com.hlh.GLPT.RemoteBase.web
 * User: 黄良辉  16-4-24  下午5:20
 */
@Controller
@RequestMapping("/GLPT/RemoteBASE")
public class RemoteDepartmentController {
    @Autowired
    private RemoteDepartmentService remoteDepartmentService;

    @RequestMapping(value = "RemoteTeacherFindByData")
    @ResponseBody
    public String findByData(String fField, String fValue) throws Exception {
        if(fValue==null)fValue="";
        return remoteDepartmentService.findByData(fField, fValue);
    }
}
