<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-20
  Time: 下午1:50
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>用户管理-显示用户资料</title>
    <jsp:include page="/Common/PageCss.jsp"></jsp:include>
    <jsp:include page="/Common/PageJs.jsp"></jsp:include>
</head>
<body  class="easyui-layout">
<div region='north' style="overflow-y:hidden;">
    <div class="WarmPrompt-Info">
        <c:if test="${user.gender=='男'}">
            <img style="width: 38px; height: 38px;vertical-align: middle; margin-bottom:5px;" src="/skins/Default/images/man.png"/>
        </c:if>
        <c:if test="${user.gender=='女'}">
            <img style="width: 38px; height: 38px;vertical-align: middle; margin-bottom:5px;" src="/skins/Default/images/woman.png"/>
        </c:if>
        ${user.realName}（${user.code}）
    </div>
</div>
<div region="center" >
    <div id="tabs1" class="easyui-tabs" fit="true">
        <div title="基本信息">
            <table border="0" cellpadding="0" cellspacing="0" class="frm">
                <tr>
                    <th class="highlight">编号：</th>
                    <td>${user.code}</td>
                    <th class="highlight">登录账号：</th>
                    <td>${user.account}</td>
                </tr>
                <tr>
                    <th class="highlight">姓名：</th>
                    <td>${user.realName}</td>
                    <th class="highlight">登录密码：</th>
                    <td>******</td>
                </tr>
                <tr>
                    <th class="highlight">性别：</th>
                    <td>${user.gender}</td>
                    <th class="highlight">手机号码：</th>
                    <td>${user.mobile}</td>
                </tr>
                <tr>
                    <th class="highlight">出生日期：</th>
                    <td>${user.birthday}</td>
                    <th class="highlight">固定电话：</th>
                    <td>${user.telephone}</td>
                </tr>
                <tr>
                    <th class="highlight">QQ号码：</th>
                    <td>${user.OICQ}</td>
                    <th class="highlight">电子邮件：</th>
                    <td>${user.email}</td>
                </tr>
                <tr>
                    <th class="highlight">默认部门：</th>
                    <td>${DepartmentName}</td>
                    <th class="highlight">默认职务：</th>
                    <td>${TitleName}</td>
                </tr>
                <tr>
                    <th class="highlight">有效性：</th>
                    <td colspan="3">
                        <c:if test="${user.enabled==true}">
                            是
                        </c:if>
                        <c:if test="${user.enabled==false}">
                            否
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th class="highlight">说明：</th>
                    <td colspan="3">${user.description}</td>
                </tr>
            </table>
        </div>
    </div>
</div>


</body>
</html>