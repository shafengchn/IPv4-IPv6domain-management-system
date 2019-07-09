<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-13
  Time: 下午9:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>域名管理-显示续订记录</title>
    <jsp:include page="/Common/PageCss.jsp"></jsp:include>
    <jsp:include page="/Common/PageJs.jsp"></jsp:include>
</head>
<body class="easyui-layout">
<div region="center">
    <table id="tt_RenewRecord"></table>
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
        $('#tt_RenewRecord').datagrid({
            fit: true,
            nowrap: false,
            striped: true,
            url: '/GLPT/DM/RenewRecordFindPage.do',
            singleSelect: true,
            remoteSort: false,
            rownumbers: true,
            idField: 'ID',
            columns: [
                [
                    {field: 'RenewDate', title: '续订日期', width: 100, align: 'center'},
                    {field: 'RealName', title: '续订人', width: 100, align: 'center'},
                    {field: 'RenewContent', title: '续订内容', width: 200, align: 'center'},
                    {field: 'Remarks', title: '备注', width: 200, align: 'center'}
                ]
            ],
            pagination: true
        });
        var p1 = $('#tt_RenewRecord').datagrid('getPager');
        $(p1).pagination({
            onBeforeRefresh: function () {
                $('#tt_RenewRecord').datagrid('clearSelections');
                $('#tt_RenewRecord').datagrid('reload');
            }
        });
    });
</script>
</body>
</html>