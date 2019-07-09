<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-13
  Time: 下午9:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>设置所属部门</title>
    <jsp:include page="/Common/PageCss.jsp"></jsp:include>
    <jsp:include page="/Common/PageJs.jsp"></jsp:include>
    <style type="text/css">
        .mySpan {
            display: -moz-inline-box;
            display: inline-block;
            width: 150px;
        }
    </style>
</head>
<body  class="easyui-layout">
<div region="north" style="height: 45px;border: 0;">
    <div class="WarmPrompt-Info">
        ${borrower.code}（${borrower.fullName}）
    </div>
</div>
<div region="center" style="overflow: auto; border: 0;">
    <form id="ff" action="/GLPT/BASE/BorrowerAndDepartmentBatchAddOK.do" method="post">
        <input type="hidden" id="BorrowerId" name="BorrowerId" value="${borrower.borrowerId}"/>

        <div id="div_Department">
            <c:forEach var="myDepartment" items="${allDepartment}">
                                <span class="mySpan">
                                    <input type="checkbox" name="chk_Department" id="${myDepartment.DepartmentCode}"
                                           value="${myDepartment.DepartmentCode}" ${myDepartment.checked}>
                                    <label style="vertical-align: middle;"
                                           for="${myDepartment.DepartmentCode}">${myDepartment.DepartmentName}</label>
                                </span>
            </c:forEach>
        </div>
    </form>
</div>
<div region="south" style="height: 50px; text-align: center; padding-top: 10px; ">
    <a id="btn_AllSelect" class="l-btn" onclick="fun_SelectAll()"  href="javascript:void(0)">
        <span class="l-btn-left">
                     全选
        </span>
    </a>
    <a id="btn_AntiElection" class="l-btn" onclick="fun_SelectNo()"  href="javascript:void(0)">
        <span class="l-btn-left">
                     反选
        </span>
    </a>
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
<script type="text/javascript">
    var fun_SelectAll = function () {
        $("input[name='chk_Department']").attr("checked", true);
    }

    var fun_SelectNo = function () {
        var arrChk = $("input[name='chk_Department']");
        $(arrChk).each(function () {
            $(this).attr("checked", !this.checked);
        });
    }

    var fun_submit = function () {
        var win = $.messager.progress({title: '请稍等...', msg: '保存中...'});
        var formData = $("#ff").serialize();
        $.ajax({
            type: "POST",
            url: "/GLPT/BASE/BorrowerAndDepartmentBatchAddOK.do",
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