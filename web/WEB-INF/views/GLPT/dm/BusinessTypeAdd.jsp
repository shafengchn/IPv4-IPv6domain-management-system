<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-13
  Time: 下午9:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>业务类型-添加</title>
    <jsp:include page="/Common/PageCss.jsp"></jsp:include>
    <jsp:include page="/Common/PageJs.jsp"></jsp:include>
</head>
<body class="easyui-layout">
<div region="center">
<form id="ff" action="/GLPT/DM/BusinessTypeAddOK.do" method="post">
    <table border="0" cellpadding="0" cellspacing="0" class="frm">
        <tr>
            <th>业务类型代码[<font face="宋体" color="red">*</font>]：</th>
            <td>
                <input name="Code" type="text" id="Code" maxlength="50" class="txt"
                       style="width: 220px"/>
            </td>
        </tr>
        <tr>
            <th>业务类型名称[<font face="宋体" color="red">*</font>]：</th>
            <td>
                <input name="FullName" type="text" id="FullName" maxlength="50" class="txt"
                       style="width: 220px"/>
            </td>
        </tr>
        <tr>
            <th>排序号[<font face="宋体" color="red">*</font>]：</th>
            <td><input name="SortCode" type="text" id="SortCode" class="easyui-numberbox" min="0" style="width: 220px" value="${SortCode}"/></td>
        </tr>
        <tr>
            <th>选项[<font face="宋体" color="red">*</font>]：</th>
            <td>
            <span class="item">
                    <input id="Enabled" type="checkbox" style="vertical-align: middle;" checked="checked"
                           name="Enabled">
                    <label style="vertical-align: middle;" for="Enabled">有效</label>
            </span>
            </td>
        </tr>
        <tr>
            <th>备注：</th>
            <td>
                <textarea id="Remarks" class="txtArea" style="width: 220px" rows="6" type="text" maxlength="200"
                          name="Remarks"></textarea>
            </td>
        </tr>
    </table>
</form>
</div>
<div region="south" style="text-align: center; padding-top: 5px; height: 40px;">
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
<script type="text/javascript">
    var fun_submit = function () {
        var win = $.messager.progress({title: '请稍等...', msg: '添加中...'});
        var formData = $("#ff").serialize();
        $.ajax({
            type: "POST",
            url: "/GLPT/DM/BusinessTypeAddOK.do",
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