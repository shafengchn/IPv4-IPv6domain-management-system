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
    <title>用户管理-分配角色</title>
    <jsp:include page="/Common/PageCss.jsp"></jsp:include>
    <jsp:include page="/Common/PageJs.jsp"></jsp:include>
</head>
<body class="easyui-layout">
<div region='north' style="overflow-y:hidden;">
    <div class="WarmPrompt-Info">
        <img style="width: 38px; height: 38px;vertical-align: middle; margin-bottom:5px;"
             src="/skins/Default/images/woman.png"/>
        ${selectUserInfo.realName}（${selectUserInfo.code}）
    </div>
</div>
<div region="center">
    <form id="ff" action="/GLPT/CORE/UserAllotRoleOK.do" method="post">
        <input type="hidden" id="UserCode" name="UserCode" value="${selectUserInfo.code}"/>
        <c:forEach var="role" items="${roleList}">
            <div>
                <input id="${role[0]}" type="checkbox"
                       style="vertical-align: middle;"
                       name="${role[0]}" ${role[2]}>
                <label style="vertical-align: middle;"
                       for="${role[0]}">${role[1]}</label>
            </div>
        </c:forEach>
    </form>
    <div class="frmbottom">
        <a id="Accept" class="l-btn" onclick="fun_submit()" href="javascript:void(0)">
        <span class="l-btn-left">
                <img alt="" src="/skins/Default/images/16/accept.png">
                     确定
        </span>
        </a>
        <a class="l-btn" onclick="OpenClose();" href="javascript:void(0)">
        <span class="l-btn-left">
                <img alt="" src="/skins/Default/images/16/cancel.png">
                    关 闭
        </span>
        </a>
    </div>
</div>

<script type="text/javascript">
    var fun_submit = function () {
        var win = $.messager.progress({title: '请稍等...', msg: '保存中...'});
        var formData = $("#ff").serialize();
        $.ajax({
            type: "POST",
            url: "/GLPT/CORE/UserAllotRoleOK.do",
            processData: true,
            dataType: "json",
            data: formData,
            success: function (data) {
                $.messager.progress('close');
                if (data.success == true) {
                    showFaceMsg("保存成功");
                } else {
                    showFaceMsg(data.info);
                }
            }
        });
    }
</script>
</body>
</html>