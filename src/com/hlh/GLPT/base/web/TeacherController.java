package com.hlh.GLPT.base.web;

import com.hlh.GLPT.base.service.TeacherService;
import com.hlh.util.jsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Package: com.hlh.GLPT.base.web
 * User: 黄良辉  16-4-24  上午11:22
 */
@Controller
@RequestMapping("/GLPT/BASE")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "TeacherFindByCode")
    @ResponseBody
    public String TeacherFindByCode(String Code) throws Exception {
        if (Code == null) {
            return "";
        } else {
            return jsonUtil.BeanToJson(teacherService.findByCode(Code));
        }
    }


}
