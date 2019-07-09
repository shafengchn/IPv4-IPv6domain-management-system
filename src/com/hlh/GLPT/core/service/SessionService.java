package com.hlh.GLPT.core.service;

import com.hlh.GLPT.base.service.DepartmentService;
import com.hlh.GLPT.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;

/**
 * User: 黄良辉
 * Date: 14-3-15
 * Time: 上午9:48
 */
@Service
public class SessionService {
    @Autowired
    private DepartmentService departmentService;

    /*
    * 获取当前用户的登录信息
    * */
    public User GetUserLoginInfo(HttpSession httpSession){
        if (httpSession.getAttribute("user") !=null) {
           return (User)httpSession.getAttribute("user");
        }
        return null;
    }

    /*
    * 获取登录用户名
    * */
    public String GetUserLoginName(HttpSession httpSession){
        try {
            return GetUserLoginInfo(httpSession).getAccount();
        } catch (Exception err) {
            err.printStackTrace();
        }
        return "";
    }

    /*
  * 获取登录用户姓名
  * */
    public String GetUserLoginRealName(HttpSession httpSession){
        try {
            return GetUserLoginInfo(httpSession).getRealName();
        } catch (Exception err) {
            err.printStackTrace();
        }
        return "";
    }

    /*
   * 获取登录用户ID
   * */
    public String GetUserLoginId(HttpSession httpSession){
        try {
            return GetUserLoginInfo(httpSession).getUserId();
        } catch (Exception err) {
            err.printStackTrace();
        }
        return "";
    }

    /*
  * 获取登录用户Code
  * */
    public String GetUserLoginCode(HttpSession httpSession){
        try {
            return GetUserLoginInfo(httpSession).getCode();
        } catch (Exception err) {
            err.printStackTrace();
        }
        return "";
    }

    /*
* 获取登录用户的所属部门
* */
    public String GetUserDepartmentName(HttpSession httpSession){
        try {
            String DepartmentCode1=GetUserLoginInfo(httpSession).getDepartmentCode();
            return departmentService.DepartmentCodeToDepartmentName(DepartmentCode1);
        } catch (Exception err) {
            err.printStackTrace();
        }
        return "";
    }

    /*
    * 用户退出
    * */
    public void UserExit(HttpSession httpSession){
        if (httpSession.getAttribute("user") !=null) {
            httpSession.removeAttribute("user");
        }
        if(httpSession.getAttribute("DepartmentName")!=null){
            httpSession.removeAttribute("DepartmentName");
        }
    }

    /*
    * 获取在线人数
    * */
    public int GetOnline(HttpSession httpSession){
        return 0;
    }
}
