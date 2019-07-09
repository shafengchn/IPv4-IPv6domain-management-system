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
    <title>借用者管理-编辑</title>
    <jsp:include page="/Common/PageCss.jsp"></jsp:include>
    <jsp:include page="/Common/PageJs.jsp"></jsp:include>
</head>
<body class="easyui-layout">
<div region="center" style="border: 0;">
    <div id="tt" class="easyui-tabs" fit="true">
        <div title="基本资料" style="padding:20px;">
<form id="ff" action="/GLPT/BASE/BorrowerEditOK.do" method="post">
    <input type="hidden" id="BorrowerId" name="BorrowerId" value="${borrower.borrowerId}"/>
    <input type="hidden" id="PhotoImg" name="PhotoImg" value="${borrower.photoImg}"/>
    <table border="0" cellpadding="0" cellspacing="0" class="frm">
        <tr>
            <th>姓名[<font face="宋体" style="color: red;">*</font>]：</th>
            <td><input name="FullName" type="text" id="FullName" maxlength="50" class="txt" style="width: 220px" value="${borrower.fullName}"/></td>
            <th>工号[<font face="宋体" style="color: red;">*</font>]：</th>
            <td><input name="Code" type="text" id="Code" maxlength="50" class="txt" style="width: 220px" value="${borrower.code}"/></td>
        </tr>
        <tr>
            <th>手机[<font face="宋体" style="color: red;">*</font>]：</th>
            <td><input name="Mobile" type="text" id="Mobile" style="width: 220px" class="txt" value="${borrower.mobile}"/></td>
            <th>查询密码：</th>
            <td><input name="FindPassword" type="password" id="FindPassword" maxlength="50" class="txt" style="width: 220px" value="${borrower.findPassword}"/></td>
        </tr>
        <tr>
            <th>性别[<font face="宋体">*</font>]：</th>
            <td>
                <select name="Gender" id="Gender" class="select" style="width: 220px">
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </td>
            <th>别名：</th>
            <td>
                <input type="text" name="Alias" id="Alias" panelHeight="auto" maxlength="50"
                       style="width: 220px" value="${borrower.alias}"  class="txt"/>
            </td>
        </tr>
        <tr>
            <th>出生日期：</th>
            <td><input name="Birthday" type="text" id="Birthday" style="width: 220px" class="easyui-datebox" value="${borrower.birthday}"/></td>
            <th>年龄：</th>
            <td><input class="easyui-numberbox" name="age" id="age" min="0" style="width: 220px" value="${borrower.age}"/></td>
        </tr>
        <tr>
            <th>身份证号码：</th>
            <td><input name="IDCard" type="text" id="IDCard" maxlength="50" class="txt" style="width: 220px" value="${borrower.IDCard}"/></td>
            <th>电子邮件：</th>
            <td><input name="Email" type="text" id="Email" maxlength="50" class="txt" style="width: 220px" value="${borrower.email}"/></td>
        </tr>
        <tr>
            <th>QQ号码：</th>
            <td><input name="OICQ" type="text" id="OICQ" style="width: 220px" class="txt" value="${borrower.OICQ}"/></td>
            <th>排序：</th>
            <td><input name="SortCode" type="text" id="SortCode" class="easyui-numberbox" value="${borrower.sortCode}"/></td>
        </tr>
        <tr>
            <th>住宅电话：</th>
            <td><input name="HomePhone" type="text" id="HomePhone" style="width: 220px" class="txt" value="${borrower.homePhone}"/></td>
            <th>家庭地址：</th>
            <td><input name="HomeAddress" type="text" id="HomeAddress" maxlength="50" class="txt" style="width: 220px" value="${borrower.homeAddress}"/></td>
        </tr>
        <tr>
            <th>有效性：</th>
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
                <textarea id="Description" class="txtArea" style="width: 595px" rows="5" type="text" maxlength="200"
                          name="Description">${borrower.description}</textarea>
            </td>
        </tr>
    </table>
</form>
        </div>
        <div title="个人相片" style="overflow:auto;padding:20px;">
            <fieldset>
                <legend>个人相片</legend>
                <iframe id="iframe_fileUpload_BorrowerPhotoImg" width="100%" height="300px"
                        frameborder="0" src="/COMMON/fileUploadImgEx.do?filePath=PhotoImg&fileName=${borrower.photoImg}" scrolling="no"
                        name="iframe_fileUpload_BorrowerPhotoImg"></iframe>
            </fieldset>
        </div>
    </div>
</div>
<div region="south" style="height: 50px;text-align: center;padding-top: 10px; ">
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
    $(function(){
        $("#Enabled").attr("checked",${borrower.enabled});
        $("#Gender").val("${borrower.gender}");
    });
    var fun_submit = function () {
       var win = $.messager.progress({title: '请稍等...', msg: '保存中...'});
        var fileName1 = "";
        var iframe1 = document.getElementById("iframe_fileUpload_BorrowerPhotoImg");
        if (iframe1 != null) {
            fileName1 = iframe1.contentWindow.fun_GetFileName();
        }
        $("#PhotoImg").val(fileName1);
        var formData = $("#ff").serialize();
        $.ajax({
            type: "POST",
            url: "/GLPT/BASE/BorrowerEditOK.do",
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