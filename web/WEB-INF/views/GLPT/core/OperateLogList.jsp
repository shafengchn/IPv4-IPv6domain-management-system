<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-13
  Time: 上午11:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>操作日志</title>
    <jsp:include page="/Common/ListCss.jsp"></jsp:include>
    <jsp:include page="/Common/ListJs.jsp"></jsp:include>
</head>
<body class="easyui-layout">
<div id="tmpDiv" style="height: 1000px;"></div>
<div id="tb" style="height: 46px;">
    <div class="tools_bar">
        <a title="刷新当前页面" onclick="fun_refresh();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/arrow_refresh.png') 50% 4px no-repeat;">刷新</b></span>
        </a>
        <div class="tools_separator"></div>
        <div style="float: left; padding-top: 10px;">
        <span>关键字：
                    <input name="FindValue" id="txt_FindValue" class="txt" style="width: 150px;"/>
                    <input id="btnSearch1" type="button" class="btnSearch" value="搜 索"  onclick="fun_MFFind('tt',$('#txt_FindValue').val());" />
        </span>
        </div>
        <div class="tools_separator"></div>
        <a title="关闭当前窗口" onclick="ThisCloseTab();" class="tools_btn">
            <span class=""><b style="background: url('/skins/Default/images/16/back.png') 50% 4px no-repeat;">离开</b></span>
        </a>
    </div>

</div>
<div id="mainPanel" region="center" style=" overflow-y:hidden">
    <table id="tt"></table>
</div>

<script type="text/javascript">
    $(function () {
        $('#tt').datagrid({
            title: '登录记录',
            fit: true,
            nowrap: false,
            striped: true,
            url: '/GLPT/CORE/OperateLogFindPage.do',
            singleSelect: true,
            remoteSort: false,
            rownumbers: true,
            idField: 'ID',
            columns: [
                [
                    {field: 'OperateDate', title: '操作时间', width: 200, align: 'center',fixed:'true'},
                    {field: 'OperateIP', title: '操作IP', width: 200, align: 'center',fixed:'true'},
                    {field:'OperateUserName',title:'操作用户',width:200,align:'center',fixed:'true'},
                    {field:'OperateContent',title:'操作内容',width:400,align:'left'}
                ]
            ],
            pagination: true,
            toolbar: '#tb'
        });
        var p = $('#tt').datagrid('getPager');
        $(p).pagination({
            onBeforeRefresh: function () {
                $('#tt').datagrid('clearSelections');
                $('#tt').datagrid('reload');
            }
        });
        $("#tmpDiv").hide();
    });

    var fun_refresh=function(){
        $('#tt').datagrid('clearSelections');
        $('#tt').datagrid('reload');
    }
</script>
</body>
</html>