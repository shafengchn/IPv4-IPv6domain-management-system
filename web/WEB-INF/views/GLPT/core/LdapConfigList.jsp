<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-13
  Time: 上午11:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>LDAP配置</title>
    <jsp:include page="/Common/PageCss.jsp"></jsp:include>
    <jsp:include page="/Common/PageJs.jsp"></jsp:include>
</head>
<body class="easyui-layout">
<div id="tmpDiv" style="height: 1000px;"></div>
<div id="mainPanel" region="center" style=" overflow-y:hidden;padding: 10px;" title="LDAP配置">
    <div>
        <form id="ff" action="/GLPT/CORE/LapConfigSaveOK.do" method="post">
            <input type="hidden" id="ID" name="ID" value="${ldapConfig.ID}"/>
            <table border="0" cellpadding="0" cellspacing="0" class="frm">
                <tr>
                    <th>主机IP/域名[<font face="宋体" color="red">*</font>]：</th>
                    <td><input name="HostIP" type="text" id="HostIP" maxlength="50" class="txt" style="width: 220px"
                               value="${ldapConfig.hostIP}"/></td>
                </tr>
                <tr>
                    <th>端口号[<font face="宋体" color="red">*</font>]：</th>
                    <td><input name="PortNum" type="text" id="PortNum" maxlength="50" class="txt" style="width: 220px"
                               value="${ldapConfig.portNum}"/></td>
                </tr>
                <tr>
                    <th>基本判断别名[<font face="宋体" color="red">*</font>]：</th>
                    <td><input name="BaseDN" type="text" id="BaseDN" maxlength="50" class="txt" style="width: 220px"
                               value="${ldapConfig.baseDN}"/></td>
                </tr>
                <tr>
                    <th>用户判断别名[<font face="宋体" color="red">*</font>]：</th>
                    <td><input name="UserDN" type="text" id="UserDN" maxlength="50" class="txt" style="width: 220px"
                               value="${ldapConfig.userDN}"/></td>
                </tr>
                <tr>
                    <th>登录密码[<font face="宋体" color="red">*</font>]：</th>
                    <td><input name="LoginPassword" type="password" id="LoginPassword" maxlength="50" class="txt"
                               style="width: 220px" value="${ldapConfig.loginPassword}"/></td>
                </tr>
                <tr>
                    <th>判断用户组[<font face="宋体" color="red">*</font>]：</th>
                    <td><input name="UserGroupName" type="text" id="UserGroupName" maxlength="50" class="txt"
                               style="width: 220px" value="${ldapConfig.userGroupName}"/></td>
                </tr>

                <tr>
                    <th>选项[<font face="宋体">*</font>]：</th>
                    <td>
            <span class="item">
                    <input id="LdapUserLogin" type="checkbox" style="vertical-align: middle;" checked="checked"
                           name="LdapUserLogin">
                    <label style="vertical-align: middle;" for="LdapUserLogin">启用LDAP用户登录</label>
            </span>
                    </td>
                </tr>
            </table>
            <div style="padding-top: 10px;">
                <a id="Accept" class="l-btn" onclick="fun_submit()" href="javascript:void(0)">
        <span class="l-btn-left">
                <img alt="" src="/skins/Default/images/16/accept.png">
                     保存
        </span>
                </a>
                <a class="l-btn" onclick="ThisCloseTab();" href="javascript:void(0)">
        <span class="l-btn-left">
                <img alt="" src="/skins/Default/images/16/cancel.png">
                    关 闭
        </span>
                </a>
            </div>
        </form>
    </div>
    <div style="padding-top: 10px;">
        <form id="ff1" action="/GLPT/CORE/LapConnTestOK.do" method="post">
            <fieldset>
                <legend>登录测试</legend>
                <table>
                    <tr>
                        <th>用户名[<font face="宋体" color="red">*</font>]：</th>
                        <td><input name="UserName" type="text" id="UserName" maxlength="50" class="txt"
                                   style="width: 220px"/></td>
                    </tr>
                    <tr>
                        <th>密码[<font face="宋体" color="red">*</font>]：</th>
                        <td><input name="password" type="password" id="password" maxlength="50" class="txt"
                                   style="width: 220px"/></td>
                    </tr>
                </table>
                <div style="padding-top: 10px;">
                    <a id="connTest" class="l-btn" onclick="fun_ConnTest()" href="javascript:void(0)">
        <span class="l-btn-left">
                <img alt="" src="/skins/Default/images/16/accept.png">
                      测试
        </span>
                    </a>
                </div>
            </fieldset>
        </form>
    </div>
</div>

<script type="text/javascript">

    var fun_submit = function () {
        var win = $.messager.progress({title: '请稍等...', msg: '保存中...'});
        var formData = $("#ff").serialize();
        $.ajax({
            type: "POST",
            url: "/GLPT/CORE/LdapConfigSaveOK.do",
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
    var fun_ConnTest = function () {
        var win = $.messager.progress({title: '请稍等...', msg: '连接测试中...'});
        var formData = $("#ff").serialize();
        $.ajax({
            type: "POST",
            url: "/GLPT/CORE/LdapConnTestOK.do",
            processData: true,
            dataType: "json",
            data: formData,
            success: function (data) {
                $.messager.progress('close');
                if (data.success == true) {
                    showFaceMsg("连接成功");
                } else {
                    showFaceMsg("连接失败");
                }
            }
        });
    }
</script>
</body>
</html>