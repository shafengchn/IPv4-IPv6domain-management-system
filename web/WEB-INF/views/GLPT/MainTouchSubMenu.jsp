<%--
  User: 黄良辉
  Date: 14-3-9
  Time: 下午12:04
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
    <title>子功能选项</title>
    <link href="<%=basePath%>skins/Default/Styles/Touch.css" rel="stylesheet" />
    <link href="<%=basePath%>skins/Default/Styles/Style-2.css" rel="stylesheet" />
    <script src="<%=basePath%>js/jquery-1.8.2.min.js"></script>
    <script src="<%=basePath%>js/FunctionJS.js"></script>
    <script src="<%=basePath%>js/MainTouch.js"></script>
    <script type="text/javascript">
        $(function () {
            resize_Table();
            $('#htmlsubmenu div').hover(function () {
                $(this).find(".d1").addClass("d1select");
                $(this).find(".b1").css("background", "#ccc");
                $(this).find(".b1b").css("background", "#ccc");
                $(this).find(".b2").css("border-left", "1px solid #ccc");
                $(this).find(".b3").css("border-left", "1px solid #ccc");
                $(this).find(".b4").css("border-left", "1px solid #ccc");
                $(this).find(".b2b").css("border-left", "1px solid #ccc");
                $(this).find(".b3b").css("border-left", "1px solid #ccc");
                $(this).find(".b4b").css("border-left", "1px solid #ccc");
                $(this).find(".b").css("border-left", "1px solid #ccc");
                $(this).find(".b2").css("border-right", "1px solid #ccc");
                $(this).find(".b3").css("border-right", "1px solid #ccc");
                $(this).find(".b4").css("border-right", "1px solid #ccc");
                $(this).find(".b2b").css("border-right", "1px solid #ccc");
                $(this).find(".b3b").css("border-right", "1px solid #ccc");
                $(this).find(".b4b").css("border-right", "1px solid #ccc");
                $(this).find(".b").css("border-right", "1px solid #ccc");
            }, function () {
                $(this).find(".d1").removeClass("d1select");
                $(this).find(".b1").css("background", "#fff");
                $(this).find(".b1b").css("background", "#fff");
                $(this).find(".b2").css("border-left", "1px solid #fff");
                $(this).find(".b3").css("border-left", "1px solid #fff");
                $(this).find(".b4").css("border-left", "1px solid #fff");
                $(this).find(".b2b").css("border-left", "1px solid #fff");
                $(this).find(".b3b").css("border-left", "1px solid #fff");
                $(this).find(".b4b").css("border-left", "1px solid #fff");
                $(this).find(".b").css("border-left", "1px solid #fff");
                $(this).find(".b2").css("border-right", "1px solid #fff");
                $(this).find(".b3").css("border-right", "1px solid #fff");
                $(this).find(".b4").css("border-right", "1px solid #fff");
                $(this).find(".b2b").css("border-right", "1px solid #fff");
                $(this).find(".b3b").css("border-right", "1px solid #fff");
                $(this).find(".b4b").css("border-right", "1px solid #fff");
                $(this).find(".b").css("border-right", "1px solid #fff");
            });
        })
        function resize_Table() {
            resizeU();
            $(window).resize(resizeU);
            function resizeU() {
                $("#htmlsubmenu").css("position", "absolute");
                $("#htmlsubmenu").css("left", 30);
                $("#htmlsubmenu").css("top", 50);
            }
        }
    </script>
</head>
<body  style="margin: 0px; padding: 0px;">
<div class="box-title" style="width: 100%;">
    子功能项 - ${parentMenu.fullName}
</div>
<div id="htmlsubmenu">
 <c:forEach var="menu" items="${sysMenuList}">
     <div title="${menu.description}" onclick="AddTabMenuEx('${menu.menuId}','${menu.navigateUrl}','${menu.code}','${menu.fullName}','${menu.img}','true')" class="shortcuticons">
         <b class="b1"></b><b class="b2 d1"></b><b class="b3 d1"></b><b class="b4 d1"></b>
         <div class="b d1 k">
             <img src="/skins/Default/images/32/${menu.img}" alt="" /><br/>${menu.fullName}
         </div>
         <b class="b4b d1"></b><b class="b3b d1"></b><b class="b2b d1"></b><b class="b1b"></b>
     </div>
 </c:forEach>
</div>
</body>
</html>