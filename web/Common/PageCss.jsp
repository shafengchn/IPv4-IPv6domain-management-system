<%--
  User: 黄良辉
  Date: 14-3-3
  Time: 下午8:50
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String themesName = "gray";
    if (request.getParameter("themes") != null) {
        themesName = request.getParameter("themes");
        if ("".equals(themesName)) {
            themesName = "gray";
        }
    }
%>
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/EasyUI/themes/<%=themesName%>/easyui.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/EasyUI/themes/icon.css"/>
<link rel="stylesheet" href="<%=basePath%>skins/Default/Styles/style.css"/>
<!--<link href="/skins/blue.css" rel="stylesheet" type="text/css" />-->
<style type="text/css">
    .demo-info{
        background:#FFFEE6;
        color:#8F5700;
        padding:12px;
    }
    .demo-tip{
        width:16px;
        height:16px;
        margin-right:8px;
        float:left;
    }
</style>