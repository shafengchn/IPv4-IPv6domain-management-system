<%--
  User: 黄良辉
  Date: 14-2-12
  Time: 上午10:56
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${SoftName}</title>
    <link href="<%=basePath%>skins/Default/images/Login/User_Login.css" type="text/css" rel="stylesheet" />
</head>
<body id="userlogin_body">
<div id="user_login">
    <dl>
        <dd id="user_top">
            <ul>
                <li class="user_top_l"></li>
                <li class="user_top_c"></li>
                <li class="user_top_r"></li>
            </ul>
        <dd id="user_main">
            <ul>
                <li class="user_main_l"></li>
                <li class="user_main_c">
                    <div class="user_main_box">
                        <form name="UserLogin" method="post" action="mainRand.do">
                            <table>
                                <tr>
                                    <td style="text-align: left;"><label class="labelCssClass">用户名：</label></td>
                                    <td style="text-align: left;"><input class="TxtUserNameCssClass" id="txt_UserName"
                                                                         maxLength=20 name="userName" onkeydown="OnLoginNameKeydown(event);"></td>
                                </tr>
                                <tr>
                                    <td style="text-align: left;"><label class="labelCssClass">密 码：</label></td>
                                    <td style="text-align: left;"><input class="TxtPasswordCssClass" id="txt_PassWord"
                                                                         type="password" name="password" onkeydown="OnPasswordKeydown(event);"/></td>
                                </tr>
                                <tr>
                                    <td style="text-align: left;"><label class="labelCssClass">验证码：</label></td>
                                    <td style="text-align: left;"><table><tr><td><input class="TxtValidateCodeCssClass" type="text" id="txt_rand"
                                                                                        name="rand" onkeydown="OnRandKeydown(event);"/></td><td><img alt="code..." name="randImage"
                                                                                                                                                     id="randImage" src="<%=basePath%>verifyImage.jsp"
                                                                                                                                                     width="60" height="20" border="1"
                                                                                                                                                     align="absmiddle"></td></tr></table></td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        &nbsp;<br/>
                                        <label style="color: red;"><s:property value="msg"/></label>
                                    </td>
                                </tr>
                            </table>
                            <c:if test="${!empty error}">
                                <font color="red"><c:out value="${error}"/></font>
                            </c:if>
                        </form>
                    </div>
                </li>
                <li class="user_main_r"><input class="IbtnEnterCssClass" id="IbtnEnter"
                                               style="border-top-width:  0px; border-left-width: 0px;border-bottom-width:  0px;border-right-width: 0px"
                                               onclick='javascript:Login();'
                                               type=image src="<%=basePath%>skins/Default/images/Login/user_botton.gif" name="IbtnEnter"></li>
            </ul>

        <dd id="user_bottom">
            <ul>
                <li class="user_bottom_l"></li>
                <li class="user_bottom_c"><span style="margin-top: 40px"></span></li>
                <li class="user_bottom_r"></li>
            </ul>
        </dd>
    </dl>
</div>
<span id="ValrUserName" style="display: none; color: red"></span>
<span id="ValrPassword" style="display: none; color: red"></span>
<span id="ValrValidateCode" style="display: none; color: red"></span>
<div id="ValidationSummary1" style="display: none; color: red"></div>
<div></div>
<script type="text/javascript" language="javascript">

    function Login() {
        document.forms[0].submit();
    }
    function OnLoginNameKeydown(evt) {
        evt = evt ? evt : (window.event ? window.event : null);//兼容IE和FF
        if (evt.keyCode == 13)
            document.getElementById("txt_PassWord").focus();
    }

    function OnPasswordKeydown(evt) {
        evt = evt ? evt : (window.event ? window.event : null);//兼容IE和FF
        if (evt.keyCode == 13)
            document.getElementById("txt_rand").focus();
    }


    function OnRandKeydown(evt){
        evt = evt ? evt : (window.event ? window.event : null);//兼容IE和FF
        if (evt.keyCode == 13)
            Login();
    }

    var sys_exit = function () {
        window.opener = null;
        window.open('', '_self');
        window.close();
    }
</script>
</body>
</html>