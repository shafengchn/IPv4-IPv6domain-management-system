<%--
  User: 黄良辉
  Date: 14-3-19
  Time: 上午9:53
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色管理</title>
    <jsp:include page="/Common/ListCss.jsp"></jsp:include>
    <jsp:include page="/Common/ListJs.jsp"></jsp:include>
</head>
<body class="easyui-layout">
<div region='center'>
    <table id="tt1"></table>
</div>
<div id="tb1" style="padding:1px; height:auto;">
    <div class="tools_bar">
        <a title="刷新当前页面" onclick="fun_refresh1()" class="tools_btn">
            <span><b
                    style="background: url('/skins/Default/images/16/arrow_refresh.png') 50% 4px no-repeat;">刷新</b></span>
        </a>

        <div class="tools_separator"></div>
        <a title="" onclick="fun_add1()" class="tools_btn">
            <span><b
                    style="background: url('/skins/Default/images/16/application_add.png') 50% 4px no-repeat;">新增</b></span>
        </a>
        <a title="" onclick="fun_edit1()" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application_edit.png') 50% 4px no-repeat;">编辑</b></span>
        </a>
        <a title="" onclick="fun_del1()" class="tools_btn">
            <span><b
                    style="background: url('/skins/Default/images/16/application_delete.png') 50% 4px no-repeat;">删除</b></span>
        </a>

        <div class="tools_separator"></div>
        <a title="" onclick="fun_SetAuthority()" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application_add.png') 50% 4px no-repeat;">设置权限</b></span>
        </a>

        <div class="tools_separator"></div>
        <a title="关闭当前窗口" onclick="ThisCloseTab();" class="tools_btn">
            <span class=""><b
                    style="background: url('/skins/Default/images/16/back.png') 50% 4px no-repeat;">离开</b></span>
        </a>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $('#tt1').datagrid({
            title: '角色列表',
            fit: true,
            url: '/GLPT/CORE/RoleFindPage.do',
            singleSelect: true,
            remoteSort: false,
            rownumbers: true,
            idField: 'RoleId',
            frozenColumns: [
                [
                    {field: 'ck', checkbox: true}
                ]
            ],
            columns: [
                [
                    {field: 'Code', title: '角色代码', width: 120, align: 'center'},
                    {field: 'FullName', title: '角色名称', width: 120, align: 'center'},
                    {field: 'Category', title: '角色分类', width: 120, align: 'center'},
                    {field: 'Enabled', title: '有效', width: 150, align: 'center', formatter: fun_FormatEnabled},
                    {field: 'Description', title: '说明', width: 150, align: 'left'}
                ]
            ],
            pagination: true,
            toolbar: '#tb1'
        });
        var p = $('#tt1').datagrid('getPager');
        $(p).pagination({
            onBeforeRefresh: function () {
                $('#tt1').datagrid('clearSelections');
                $('#tt1').datagrid('reload');
            }
        });
    });
    var fun_add1 = function () {
        var url = "/GLPT/CORE/RoleAdd.do?IframeId=${IframeId}";
        top.openDialog(url, 'RoleAdd', '角色管理 - 添加', 410, 320, 50, 50);
    }

    var fun_del1 = function () {
        var row = $('#tt1').datagrid('getSelected');
        if (row) {
            delConfig("/GLPT/CORE/RoleThoroughDelOK.do", "RoleId=" + row.RoleId);
        } else {
            showFaceMsg("请选择要操作的记录");
        }
    }
    var fun_edit1 = function () {
        var row = $('#tt1').datagrid('getSelected');
        if (row) {
            var url = "/GLPT/CORE/RoleEdit.do?RoleId=" + row.RoleId + "&IframeId=${IframeId}";
            top.openDialog(url, 'RoleAdd', '角色管理 - 修改', 410, 320, 50, 50);
        } else {
            showFaceMsg("请选择要操作的记录");
        }

    }

    var fun_SetAuthority = function () {
        var row = $('#tt1').datagrid('getSelected');
        if (row) {
            var url = "/GLPT/CORE/RoleSetAuthority.do?RoleCode=" + row.Code + "&PTCode=1000&IframeId=${IframeId}";
            top.openDialog(url, 'RoleSetAuthority', '角色管理 - 设置权限', 600, 500, 50, 50);
        } else {
            showFaceMsg("请选择要操作的记录");
        }
    }

    var fun_refresh1 = function () {
        $('#tt1').datagrid('clearSelections');
        $('#tt1').datagrid('reload');
    }
</script>
</body>

</html>