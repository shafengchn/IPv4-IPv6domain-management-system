<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-13
  Time: 下午9:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色管理-修改</title>
    <jsp:include page="/Common/PageCss.jsp"></jsp:include>
    <jsp:include page="/Common/PageJs.jsp"></jsp:include>
</head>
<body>
<form id="ff" action="/GLPT/CORE/RoleEditOK.do" method="post">
    <input type="hidden" id="RoleId" name="RoleId" value="${role.roleId}"/>
    <table border="0" cellpadding="0" cellspacing="0" class="frm">
        <tr>
            <th>角色编号[<font face="宋体">*</font>]：</th>
            <td><input name="Code" type="text" id="Code" maxlength="50" class="txt" style="width: 220px" value="${role.code}"/></td>
        </tr>
        <tr>
            <th>角色名称[<font face="宋体">*</font>]：</th>
            <td><input name="FullName" type="text" id="FullName" maxlength="50" class="txt" style="width: 220px" value="${role.fullName}"/></td>
        </tr>
        <tr>
            <th>分类[<font face="宋体">*</font>]：</th>
            <td>
                <select name="Category" id="Category" class="select" style="width: 220px">
                    <option value="系统角色">系统角色</option>
                    <option value="学生角色">学生角色</option>
                </select>
            </td>
         </tr>
        <tr>
            <th>选项[<font face="宋体">*</font>]：</th>
            <td>
            <span class="item">
                    <input id="Enabled" type="checkbox" style="vertical-align: middle;" checked="checked"
                           name="Enabled">
                    <label style="vertical-align: middle;" for="Enabled">有效</label>
            </span>
            <span class="item">
                    <input id="AllowEdit" type="checkbox" style="vertical-align: middle;" checked="checked"
                           name="AllowEdit">
                    <label style="vertical-align: middle;" for="AllowEdit">允许编辑</label>
            </span>
            <span class="item">
                    <input id="AllowDelete" type="checkbox" style="vertical-align: middle;" checked="checked"
                           name="AllowDelete">
                    <label style="vertical-align: middle;" for="AllowDelete">允许删除</label>
            </span>
            </td>
        </tr>
        <tr>
            <th>说明：</th>
            <td colspan="3">
                <textarea id="Description" class="txtArea" style="width: 220px" rows="6" type="text" maxlength="200"
                          name="Description">${role.description}</textarea>
            </td>
        </tr>
    </table>
    <div class="frmbottom">
        <a id="Accept" class="l-btn" onclick="fun_submit()"  href="javascript:void(0)">
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
    $(function(){
        $("#Category").val("${role.category}");
        $("#Enabled").attr("checked",${role.enabled});
        $("#AllowEdit").attr("checked",${role.allowEdit});
        $("#AllowDelete").attr("checked",${role.allowDelete});
    });
    var fun_submit = function () {
        var win = $.messager.progress({title: '请稍等...', msg: '修改中...'});
        var formData = $("#ff").serialize();
        $.ajax({
            type: "POST",
            url: "/GLPT/CORE/RoleEditOK.do",
            processData: true,
            dataType: "json",
            data: formData,
            success: function (data) {
                $.messager.progress('close');
                if (data.success == true) {
                    var p1 =   parent.document.getElementById("${IframeId}");
                    if(p1!=null){
                        p1.contentWindow.fun_refresh1();
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