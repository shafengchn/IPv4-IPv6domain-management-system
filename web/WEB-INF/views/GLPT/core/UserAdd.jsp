<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-13
  Time: 下午9:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理-添加</title>
    <jsp:include page="/Common/PageCss.jsp"></jsp:include>
    <jsp:include page="/Common/PageJs.jsp"></jsp:include>
</head>
<body>
<form id="ff" action="/GLPT/CORE/UserAddOK.do" method="post">
    <table border="0" cellpadding="0" cellspacing="0" class="frm">
        <tr>
            <th>工号[<font face="宋体">*</font>]：</th>
            <td><input name="Code" type="text" id="Code" maxlength="50" class="txt" style="width:180px"/></td>
            <th>登录账户[<font face="宋体">*</font>]：</th>
            <td><input name="Account" type="text" id="Account" maxlength="50" class="txt" style="width:180px"/></td>
        </tr>
        <tr>
            <th>姓名[<font face="宋体">*</font>]：</th>
            <td><input name="RealName" type="text" id="RealName" maxlength="50" class="txt" style="width:180px"/></td>
            <th>登录密码[<font face="宋体">*</font>]：</th>
            <td><input name="Password" type="password" id="Password" maxlength="50" class="txt" style="width:180px"/>
            </td>

        </tr>
        <tr>
            <th>性别[<font face="宋体">*</font>]：</th>
            <td>
                <select name="Gender" id="Gender" class="select" style="width:180px">
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </td>
            <th>手机：</th>
            <td><input name="Mobile" type="text" id="Mobile" maxlength="50" class="txt" style="width:180px"/></td>
        </tr>
        <tr>
            <th>出生日期：</th>
            <td><input name="Birthday" type="text" id="Birthday" style="width:180px" class="easyui-datebox"/></td>
            <th>电话：</th>
            <td><input name="Telephone" type="text" id="Telephone" maxlength="50" class="txt" style="width:180px"/>
            </td>
        </tr>
        <tr>
            <th>QQ号码：</th>
            <td><input name="OICQ" type="text" id="OICQ" maxlength="50" class="txt" style="width:180px"/></td>
            <th>电子邮件：</th>
            <td><input name="Email" type="text" id="Email" maxlength="50" class="txt" style="width:180px"/></td>
        </tr>
        <tr>
            <th>默认部门[<font face="宋体">*</font>]：</th>
            <td>
                <input class="easyui-combobox"
                       name="DepartmentCode"
                       id="DepartmentCode"
                       url="/GLPT/BASE/DepartmentGetData.do"
                       valueField="Code"
                       textField="FullName" maxlength="50" style="width:180px"/>
            </td>
            <th>默认职务：</th>
            <td>
                <input class="easyui-combobox"
                       name="TitleCode"
                       id="TitleCode"
                       url="/GLPT/CORE/ItemDetailsFindData.do?ItemsName=职务"
                       valueField="Code"
                       textField="FullName" maxlength="50" style="width:180px"/>
            </td>
        </tr>
        <tr>
            <th>选项[<font face="宋体">*</font>]：</th>
            <td colspan="3">
            <span class="item">
                    <input id="Enabled" type="checkbox" style="vertical-align: middle;" checked="checked"
                           name="Enabled">
                    <label style="vertical-align: middle;" for="Enabled">有效</label>
            </span>
            </td>
        </tr>
        <tr>
            <th>说明：</th>
            <td colspan="3">
                <textarea id="Description" class="txtArea" style="width: 90%" rows="8" type="text" maxlength="200"
                          name="Description"></textarea>
            </td>
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
        var win = $.messager.progress({title: '请稍等...', msg: '添加中...'});
        var formData = $("#ff").serialize();
        $.ajax({
            type: "POST",
            url: "/GLPT/CORE/UserAddOK.do",
            processData: true,
            dataType: "json",
            data: formData,
            success: function (data) {
                $.messager.progress('close');
                if (data.success == true) {
                    var p1 = parent.document.getElementById("${IframeId}");
                    if (p1 != null) {
                        p1.contentWindow.fun_refresh();
                    }
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