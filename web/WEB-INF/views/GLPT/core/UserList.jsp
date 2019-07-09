<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-13
  Time: 上午11:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>用户管理</title>
    <jsp:include page="/Common/ListCss.jsp"></jsp:include>
    <jsp:include page="/Common/ListJs.jsp"></jsp:include>
</head>
<body class="easyui-layout">
<div id="tmpDiv" style="height: 1000px;"></div>
<!--<div region='north' style="height:100px; padding: 1px;">-->
<div id="tb" style="height: 95px;">
    <div class="tools_bar">
        <a title="刷新当前页面" onclick="Replace();" class="tools_btn">
            <span><b
                    style="background: url('/skins/Default/images/16/arrow_refresh.png') 50% 4px no-repeat;">刷新</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="" onclick="fun_add();" class="tools_btn">
            <span><b
                    style="background: url('/skins/Default/images/16/application_add.png') 50% 4px no-repeat;">新增</b></span>
        </a>
        <a title="" onclick="fun_edit();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application_edit.png') 50% 4px no-repeat;">编辑</b></span>
        </a>
        <a title="" onclick="fun_del();" class="tools_btn">
            <span><b
                    style="background: url('/skins/Default/images/16/application_delete.png') 50% 4px no-repeat;">删除</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="查看相关详细信息" onclick="fun_show();" class="tools_btn">
            <span class=""><b
                    style="background: url('/skins/Default/images/16/page_white_find.png') 50% 4px no-repeat;">查看详细</b></span>
        </a>
        <a title="重新设置新密码" onclick="fun_ResetPassword();" class="tools_btn">
            <span><b
                    style="background: url('/skins/Default/images/16/group_key.png') 50% 4px no-repeat;">重置密码</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="用户分配角色" onclick="fun_AllotRole();" class="tools_btn">
            <span class=""><b
                    style="background: url('/skins/Default/images/16/AllotRole.png') 50% 4px no-repeat;">分配角色</b></span>
        </a>
        <a title="" onclick="fun_SetAuthority()" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application_add.png') 50% 4px no-repeat;">设置权限</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="关闭当前窗口" onclick="ThisCloseTab();" class="tools_btn">
            <span class=""><b
                    style="background: url('/skins/Default/images/16/back.png') 50% 4px no-repeat;">离开</b></span>
        </a>
    </div>
    <div class="btnbarcontetn" style="margin-top: 1px; background: #fff">
        <table border="0" class="frm-find" style="height: 45px;">
            <tbody>
            <tr>
                <td>
                    <select class="select" onchange="fun_selectFind(this)">
                        <option value="0">模糊</option>
                        <option value="1">精确</option>
                    </select>
                </td>
                <td>
                    <div id="div_MF">
                        <table>
                            <tbody>
                            <tr>
                                <th>关键字：</th>
                                <td>
                                    <input name="MFFindValue" id="txt_MFFindValue" class="txt" style="width: 200px;"/>
                                </td>
                                <td>
                                    <input id="btnSearch1" type="button" class="btnSearch" value="搜 索"
                                           onclick="fun_MFFindEx2('tt','用户列表'
                                           ,$('#txt_MFFindValue').val()
                                           ,$('#Enabled').attr('checked'))"/>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div id="div_JQ" style="display: none;">
                        <table>
                            <tbody>
                            <tr>
                                <th>查询条件：
                                </th>
                                <td>
                                    <select name="query" id="query" class="select" style="width: 70px">
                                        <option value="Code">编号</option>
                                        <option value="Account">登录账号</option>
                                        <option value="RealName">姓名</option>
                                        <option value="Mobile">手机号码</option>
                                    </select>
                                </td>
                                <th>关键字：
                                </th>
                                <td>
                                    <input name="JQFindValue" type="text" id="txt_JQFindValue" class="txt"
                                           style="width: 200px">
                                </td>
                                <td>
                                    <input id="btnSearch" type="button" class="btnSearch" value="搜 索"
                                           onclick="fun_JQFindEx2('tt','用户列表'
                                           ,$('#query').val()
                                           ,$('#txt_JQFindValue').val()
                                           ,$('#Enabled').attr('checked'))"/>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </td>
                <td>
                       <span class="item">
                                <input name="Enabled" type="checkbox" id="Enabled" style="vertical-align: middle;">
                                <label for="Enabled" style="vertical-align: middle;">只显示有效的</label>
                                &nbsp;&nbsp;
                       </span>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div id="mainPanel" region="center" style=" overflow-y:hidden">
    <table id="tt"></table>
</div>
<script type="text/javascript">
    $(function () {
        $("#tmpDiv").hide();
        $('#tt').datagrid({
            title: '用户列表',
            fit: true,
            nowrap: false,
            striped: true,
            url: '/GLPT/CORE/UserFindPage.do',
            singleSelect: true,
            remoteSort: false,
            rownumbers: true,
            idField: 'UserId',
            frozenColumns: [
                [
                    {field: 'ck', checkbox: true}
                ]
            ],
            columns: [
                [
                    {title: '基本信息', colspan: 6},
                    {field: 'DepartmentName', title: '部门', width: 100, rowspan: 2, align: 'center'},
                    {field: 'Enabled', title: '有效', width: 80, rowspan: 2, align: 'center', formatter: fun_FormatEnabled},
                    {field: 'LogOnCount', title: '登录次数', width: 100, rowspan: 2, align: 'center'},
                    {field: 'LastVisit', title: '最后登录时间', width: 150, rowspan: 2, formatter: fun_FormatDateTime, align: 'center'},
                    {field: 'Description', title: '说明', width: 150, rowspan: 2, align: 'center'}
                ],
                [
                    {field: 'Code', title: '编号', width: 80, align: 'center'},
                    {field: 'Account', title: '登录账户', width: 100, align: 'center'},
                    {field: 'RealName', title: '姓名', width: 100, align: 'center'},
                    {field: 'Gender', title: '性别', width: 80, align: 'center'},
                    {field: 'Mobile', title: '手机号码', width: 100, align: 'center'},
                    {field: 'Email', title: '电子邮件', width: 100, align: 'center'}
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
    });

    var fun_add = function () {
        var url = "/GLPT/CORE/UserAdd.do?IframeId=${IframeId}";
        top.openDialog(url, 'UserAdd', '用户管理 - 添加', 600, 410, 50, 50);
    }

    var fun_del = function () {
        var row = $('#tt').datagrid('getSelected');
        if (row) {
            delConfig("/GLPT/CORE/UserDelOK.do", "UserId=" + row.UserId);
        } else {
            showFaceMsg("请选择要操作的记录");
        }
    }
    var fun_edit = function () {
        var row = $('#tt').datagrid('getSelected');
        if (row) {
            var url = "/GLPT/CORE/UserEdit.do?UserId=" + row.UserId + "&IframeId=${IframeId}";
            top.openDialog(url, 'UserEdit', '用户管理 - 修改', 600, 410, 50, 50);
        } else {
            showFaceMsg("请选择要操作的记录");
        }

    }

    var fun_refresh = function () {
        $('#tt').datagrid('clearSelections');
        $('#tt').datagrid('reload');
    }

    var fun_show = function () {
        var row = $('#tt').datagrid('getSelected');
        if (row) {
            var url = "/GLPT/CORE/UserShow.do?UserId=" + row.UserId + "&IframeId=${IframeId}";
            top.openDialog(url, 'UserShow', '用户管理 - 查看详细信息', 600, 400, 50, 50);
        } else {
            showFaceMsg("请选择要操作的记录");
        }
    }

    var fun_ResetPassword = function () {
        var row = $('#tt').datagrid('getSelected');
        if (row) {
            var url = "/GLPT/CORE/UserResetPassword.do?UserId=" + row.UserId + "&IframeId=${IframeId}";
            top.openDialog(url, 'UserResetPassword', '用户管理 - 重置密码', 320, 110, 50, 50);
        } else {
            showFaceMsg("请选择要操作的记录");
        }
    }

    var fun_AllotRole = function () {
        var row = $('#tt').datagrid('getSelected');
        if (row) {
            var url = "/GLPT/CORE/UserAllotRole.do?UserCode=" + row.Code + "&IframeId=${IframeId}";
            top.openDialog(url, 'UserAllotRole', '用户管理 - 分配角色', 400, 350, 50, 50);
        } else {
            showFaceMsg("请选择要操作的记录");
        }
    }

    var fun_selectFind = function (obj) {
        if (obj.value == "0") {
            document.getElementById("div_MF").style.display = "";
            document.getElementById("div_JQ").style.display = "none";
        } else {
            document.getElementById("div_MF").style.display = "none";
            document.getElementById("div_JQ").style.display = "";
        }
    }

    var fun_SetAuthority = function () {
        var row = $('#tt').datagrid('getSelected');
        if (row) {
            var url = "/GLPT/CORE/UserSetAuthority.do?UserCode=" + row.Code + "&PTCode=1000&IframeId=${IframeId}";
            top.openDialog(url, 'UserSetAuthority', '用户管理 - 设置权限', 600, 500, 50, 50);
        } else {
            showFaceMsg("请选择要操作的记录");
        }
    }


</script>
</body>
</html>