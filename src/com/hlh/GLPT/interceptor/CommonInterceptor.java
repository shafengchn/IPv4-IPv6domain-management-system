package com.hlh.GLPT.interceptor;

import com.hlh.GLPT.core.service.SessionService;
import com.hlh.GLPT.core.domain.OperateLog;
import com.hlh.GLPT.core.service.OperateLogService;
import com.hlh.KZ.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.Date;

/**
 * User: 黄良辉
 * Date: 14-8-31
 * Time: 下午7:42
 */
public class CommonInterceptor implements HandlerInterceptor {
    @Autowired
    private OperateLogService operateLogService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String url = request.getRequestURL().toString();
        URL url1 = new URL(url);
        //System.out.println(url);
        String MenuName1 = sysMenuService.NavigateUrlToFullName(url1.getFile());
        if (!"".equals(MenuName1)) {
            OperateLog operateLog1 = new OperateLog();
            operateLog1.setOperateDate(new Date());
            operateLog1.setOperateIP(request.getRemoteAddr());
            operateLog1.setOperateUserName(sessionService.GetUserLoginName(request.getSession()));
            operateLog1.setOperateContent(MenuName1);
            operateLogService.add(operateLog1);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
