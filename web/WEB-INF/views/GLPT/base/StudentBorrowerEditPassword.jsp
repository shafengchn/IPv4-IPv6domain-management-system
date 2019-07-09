<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-20
  Time: 下午1:17
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生借用者管理-修改查询密码</title>
    <jsp:include page="/Common/PageCss.jsp"></jsp:include>
    <jsp:include page="/Common/PageJs.jsp"></jsp:include>
</head>
<body>
<form id="ff" action="/GLPT/BASE/StudentBorrowerEditPasswordOK.do" method="post">
    <input type="hidden" id="UserId" name="UserId" value="${UserId}"/>
    <table border="0" cellpadding="0" cellspacing="0" class="frm">
        <tr>
            <th>旧密码[<font face="宋体">*</font>]：</th>
            <td><input name="OldPassword" type="password" id="OldPassword" maxlength="50" class="txt" style="width: 180px"/></td>
        </tr>
        <tr>
            <th>新密码[<font face="宋体">*</font>]：</th>
            <td><input name="NewPassword" type="password" id="NewPassword" maxlength="50" class="txt" style="width: 180px"/></td>
        </tr>
        <tr>
            <th>确认新密码[<font face="宋体">*</font>]：</th>
            <td><input name="ConfirmNewPassword" type="password" id="ConfirmNewPassword" maxlength="50" class="txt" style="width: 180px"/></td>
        </tr>
    </table>
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
</form>
<script type="text/javascript">
    var fun_submit = function () {
        var win = $.messager.progress({title: '请稍等...', msg: '修改中...'});
        var formData = $("#ff").serialize();
        $.ajax({
            type: "POST",
            url: "/GLPT/BASE/StudentBorrowerEditPasswordOK.do",
            processData: true,
            dataType: "json",
            data: formData,
            success: function (data) {
                $.messager.progress('close');
                if (data.success == true) {
                    OpenClose();
                } else {
                    showFaceMsg(data.info);
                }
            }
        });
    }
</script>
</body>
</html>