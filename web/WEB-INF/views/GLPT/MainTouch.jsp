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
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
    <title>${SoftName}</title>
    <link href="<%=basePath%>skins/Default/Styles/Touch.css" rel="stylesheet" />
    <link href="<%=basePath%>skins/Default/Styles/style.css" rel="stylesheet" />
    <script src="<%=basePath%>js/jquery-1.8.2.min.js"></script>
    <link href="<%=basePath%>skins/blue.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath%>js/artDialog/artDialog.source.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/artDialog/iframeTools.source.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/FunctionJS.js"></script>
    <script src="<%=basePath%>js/MainTouch.js"></script>
    <script type="text/javascript">
        Loading(true);
        /**初始化**/
        $(function () {
            AddTabMenu('MySpace', '/GLPT/MySpace.do?', '首页主控台', '4963_home.png', 'false');
            $("#tabs_iframe_MySpace").css('margin-top', '0px').css('margin-left', '0px');
            iframeresize();
            writeDateInfo();
            readyIndex();
            var X = $('.leftselected').offset().top;
            $("#side_switch").css("top", X).show();
        });
        //Replace
        function hostInfo(){
            AddTabMenu('MySpace', '/GLPT/MySpace.do?', '首页主控台', '4963_home.png', 'false');
        }
        //控制面板
        function myInfo() {
            AddTabMenu('myInfo', '/GLPT/MyInfo.do', '我的信息', '4968_config.png', 'true');
        }

        function SetSubMenu(url){
            $("#tabs_iframe_MySpace").attr("src",url);
        }
    </script>
</head>
<body>
    <div id="Container">
            <div id="Header">
                <div id="HeaderLogo">
                   <label style="font-size: 24pt;color: #ffffff;">${SoftName}</label>
                </div>
                <div id="Headermenu">
                    <ul id="topnav">
                        <li id="metnav_1" class="list">
                            <a href="javascript:;" id="nav_1" class="onnav" onclick="hostInfo();">
                                <span class="c1"></span>
                                首页信息
                            </a>
                        </li>
                        <li id="metnav_3" class="list" onclick="myInfo();">
                            <a href="javascript:;" id="nav_3">
                                <span class="c3"></span>
                                我的信息
                            </a>
                        </li>
                        <li id="metnav_5" class="list" onclick="IndexOut();">
                            <a href="javascript:;" id="nav_4">
                                <span class="c4"></span>
                                安全退出
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div id="Headerbotton">
                <div id="left_title">
                    <img src="/skins/Default/images/32/clock_32.png" alt="" width="20" height="20" style="vertical-align: middle; padding-bottom: 3px;" />
                    <span id="datetime">1900年01月01日 00:00:00</span>
                </div>
                <div id="dww-menu" class="mod-tab">
                    <div class="mod-hd" style="float: left">
                        <ul class="tab-nav" id="tabs_container">
                        </ul>
                    </div>
                    <div id="toolbar" style="float: right; width: 75px; padding-right: 5px; text-align: right;">
                        <img src="/skins/Default/images/16/arrow_refresh.png" title="刷新当前窗口" alt=""
                             onclick="Loading(true);top.$('#' + Current_iframeID())[0].contentWindow.window.location.reload();return false;"
                            width="16" height="16" style="padding-bottom: 3px; cursor: pointer; vertical-align: middle;" />
                        &nbsp;
                        <img id="full_screen" src="/skins/Default/images/16/arrow_out.gif" title="最大化" alt="" onclick="Maximize();"
                            width="16" height="16" style="padding-bottom: 3px; cursor: pointer; vertical-align: middle;" />
                        &nbsp;
                    </div>
                </div>
            </div>
            <div id="MainContent">
                <div class="side_switch" id="side_switch">
                </div>
                <div class="navigation">
                    <div class="box-title">
                        主功能菜单导航
                    </div>
                    <div style="height: 638px;" id="Sidebar">
                        <ul id="htmlMenu">
                            <li>
                                <div class="leftselected" onclick="SetSubMenu('/GLPT/MySpace.do');" title="">
                                    <img width="32" height="32" src="/skins/Default/images/32/4963_home.png">
                                    我的空间
                                </div>
                            </li>

                           <c:forEach var="menu1" items="${sysMenuList}">
                                <li>
                                    <div class="" onclick="SetSubMenu('/GLPT/MainTouchSubMenu.do?ParentId=${menu1.menuId}');" title="">
                                        <img width="32" height="32" src="/skins/Default/images/32/${menu1.img}">
                                            ${menu1.fullName}
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div id="ContentPannel">
                </div>
            </div>
            <div id="botton_toolbar">
                <div style="width: 35%; text-align: left; float: left;">
                    &nbsp;V1.0
                    <span class="south-separator"></span>
                </div>
                <div style="width: 30%; text-align: left; float: left;">
                    &nbsp;
                </div>
                <div style="width: 35%; text-align: right; float: left;">
                    <span class="south-separator"></span>
                    操作员：${user.realName}[${user.account}]
                    <span class="south-separator"></span>
                    所属部门：${DepartmentName}
                </div>
            </div>
        </div>
        <!--载进度条start-->
        <div id="loading" onclick="Loading(false);">
            <img src="/skins/Default/images/loading.gif" style="padding-bottom: 4px; vertical-align: middle;" />&nbsp;正在处理，请稍待&nbsp;
        </div>
        <div id="Toploading">
        </div>
        <!--载进度条end-->
</body>
</html>