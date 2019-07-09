<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User_back: 黄良辉
  Date: 14-3-9
  Time: 上午11:18
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9"/>
    <title>${SoftName}</title>
    <script type="text/javascript" src="/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="/js/EasyUI/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/EasyUI/locale/easyui-lang-zh_CN.js"></script>
    <script src="/js/artDialog/artDialog.source.js" type="text/javascript"></script>
    <script src="/js/artDialog/iframeTools.source.js" type="text/javascript"></script>
    <script src="/js/FunctionJS.js"></script>
    <script src="/js/MainTouch.js"></script>
    <link href="/skins/blue.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="/js/EasyUI/themes/gray/easyui.css"/>
    <script type="text/javascript">
        $(function () {

        });
    </script>
    <style type="text/css">
        div, ul, li, dl, dt, dd, img, h1, h2 {
            border: 0 none;
            list-style-type: none;
            margin: 0;
            padding: 0;
        }

        .topHeader {
            background: #1e71b1 none repeat scroll 0 0;
            height: 70px;
        }

        #HeaderLogo {
            display: inline;
            float: left;
            margin-left: 20px;
            margin-top: 8px;
        }

        #HeaderMenu {
            float: right;
            margin-right: 0;
        }

        #topNav ul {
            position: relative;
        }

        #topNav li.list {
            display: inline;
            float: left;
            margin: 3px 12px 0 0;
        }

        #topNav li a {
            border: 1px solid #1e71b1;
            border-radius: 3px;
            color: #fff;
            display: inline;
            float: left;
            overflow: hidden;
            padding: 5px 7px 0;
            text-align: center;
            text-decoration: none;
            text-shadow: 0 -1px rgba(0, 0, 0, 0.2);
            width: 55px;
        }

        #topNav li a:hover {
            background: #105488 none repeat scroll 0 0;
            border: 1px solid #0f4e7f;
        }
        #topNav li a.onnav {
            background: #105488 none repeat scroll 0 0;
            border: 1px solid #0f4e7f;
        }

        #topNav li a span.c4{
            background: rgba(0,0,0,0) url("/skins/Default/images/32/n4.png") no-repeat scroll 0 0;
        }

        #topNav li a span{
            display: block;
            height: 30px;
            margin: 0 auto 2px;
            overflow: hidden;
            width: 32px;
        }

        .bottom_toolbar {
            background: #F7F7F7 none repeat scroll 0 0;
            border-top: 1px solid #ccc;
            height: 30px;
            line-height: 30px;
            margin-top: 1px;
            text-align: center;
            width: 100%;
        }

        .south-separator{
            border-left:1px solid #ccc;
            border-right: 1px solid #fff;
            margin-left: 3px;
            margin-right: 3px;
        }
    </style>
</head>
<body class="easyui-layout">
<div region="north" class="topHeader">
    <div id="HeaderLogo">
        <label style="font-size: 24pt;color: #ffffff;">${SoftName}</label>
    </div>
    <div id="HeaderMenu">
        <ul id="topNav">
            <li id="metnav_5" class="list" onclick="IndexOut();">
                <a href="javascript:;" id="nav_4">
                    <span class="c4"></span>
                    安全退出
                </a>
            </li>
        </ul>
    </div>
</div>
<div region="center">
    <div id="tt" class="easyui-tabs" fit="true">
        <div title="我的域名">
            <iframe id="tabs_frame_MySpace" height="100%" frameborder="0" width="100%" src="/GLPT/LdapUserIndex.do?&IframeId=tabs_frame_MySpace" name="tabs_frame_MySpace" style="margin-top: 0px; margin-left: 0px; display: block;"></iframe>
        </div>
        <div title="域名认领">
            <iframe id="tabs_frame_DomainNameClaim" height="100%" frameborder="0" width="100%" src="/GLPT/DM/DomainNameClaimList.do?&IframeId=tabs_frame_DomainNameClaim" name="tabs_frame_DomainNameClaim" style="margin-top: 0px; margin-left: 0px; display: block;"></iframe>
        </div>
        <div title="开发方资料">
            <iframe id="tabs_frame_DevelopingPartyList" height="100%" frameborder="0" width="100%" src="/GLPT/DM/DevelopingPartyCurrUserList.do?&IframeId=tabs_frame_DevelopingPartyList" name="tabs_frame_DevelopingPartyList" style="margin-top: 0px; margin-left: 0px; display: block;"></iframe>
        </div>
    </div>
</div>

<div region="south" class="bottom_toolbar">
    <div style="width: 35%; text-align: left; float: left;">
        &nbsp;V1.0
        <span class="south-separator"></span>
    </div>
    <div style="width: 30%; text-align: left; float: left;">
        &nbsp;
    </div>
    <div style="width: 35%; text-align: right; float: left;">
        <span class="south-separator"></span>
        操作员：${user.realName}
        <span class="south-separator"></span>
        所属部门：${DepartmentName}
    </div>
</div>
</body>

</html>