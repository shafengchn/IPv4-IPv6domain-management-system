<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-13
  Time: 下午9:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>域名管理-显示签到记录</title>
    <jsp:include page="/Common/PageCss.jsp"></jsp:include>
    <jsp:include page="/Common/PageJs.jsp"></jsp:include>
</head>
<body class="easyui-layout">
<div region="center">
    <table id="tt_SignRecord"></table>
</div>
<div region="south" style="text-align: center; padding-top: 5px; height: 40px;">
    <a class="l-btn" onclick="OpenClose();" href="javascript:void(0)">
        <span class="l-btn-left">
                <img alt="" src="/skins/Default/images/16/cancel.png">
                    关 闭
        </span>
    </a>
</div>
<script type="text/javascript">
    $(function(){
        $('#tt_SignRecord').datagrid({
            fit: true,
            nowrap: false,
            striped: true,
            url: '/GLPT/DM/SignRecordFindPage.do?DomainNameId=${DomainNameId}',
            singleSelect: true,
            remoteSort: false,
            rownumbers: true,
            idField: 'ID',
            columns: [
                [
                    {field: 'SignDate', title: '签到日期', width: 100, align: 'center'},
                    {field: 'RealName', title: '签到人', width: 100, align: 'center'},
                    {field: 'SignContent', title: '签到内容', width: 200, align: 'center'},
                    {field: 'Remarks', title: '备注', width: 200, align: 'center'}
                ]
            ],
            pagination: true
        });
        var p1 = $('#tt_SignRecord').datagrid('getPager');
        $(p1).pagination({
            onBeforeRefresh: function () {
                $('#tt_SignRecord').datagrid('clearSelections');
                $('#tt_SignRecord').datagrid('reload');
            }
        });
    });
</script>
</body>
</html>