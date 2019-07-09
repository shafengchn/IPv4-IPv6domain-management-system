<%--
  User: 黄良辉
  Date: 14-3-9
  Time: 下午12:04
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9"/>
    <title>域名认领情况</title>
    <jsp:include page="/Common/ListCss.jsp"></jsp:include>
    <jsp:include page="/Common/ListJs.jsp"></jsp:include>
</head>
<body class="easyui-layout">
<div id="tmpDiv" style="height: 1000px;"></div>
<div id="tb" style="height: 96px;">
    <div class="tools_bar">
        <a title="所有记录" onclick="fun_allRecord();" class="tools_btn">
            <span><b
                    style="background: url('/skins/Default/images/16/arrow_refresh.png') 50% 4px no-repeat;">所有记录</b></span>
        </a>
        <a title="已认领记录" onclick="fun_YesClaim();" class="tools_btn">
            <span><b
                    style="background: url('/skins/Default/images/16/arrow_refresh.png') 50% 4px no-repeat;">已认领</b></span>
        </a>

        <a title="未认领记录" onclick="fun_NoClaim();" class="tools_btn">
            <span><b
                    style="background: url('/skins/Default/images/16/arrow_refresh.png') 50% 4px no-repeat;">未认领</b></span>
        </a>

        <div class="tools_separator"></div>
        <a title="" onclick="fun_show();" class="tools_btn">
            <span><b
                    style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">详细信息</b></span>
        </a>

        <div class="tools_separator"></div>
        <a title="关闭当前窗口" onclick="ThisCloseTab();" class="tools_btn">
            <span class=""><b
                    style="background: url('/skins/Default/images/16/back.png') 50% 4px no-repeat;">离开</b></span>
        </a>
    </div>
    <div class="btnbarcontetn" style="margin-top: 1px; background: #fff">
        <table border="0" style="height: 45px;">
            <tbody>
            <tr>
                <td>
                    <table>
                        <tbody>
                        <tr>
                            <th>关键字：</th>
                            <td>
                                <input name="MFFindValue" id="txt_MFFindValue1" class="txt" style="width: 200px;"/>
                            </td>
                            <td>
                                <input id="btnSearch" type="button" class="btnSearch" value="搜 索"
                                       onclick="fun_DomainFind();"/>
                            </td>
                        </tr>
                        </tbody>
                    </table>
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
        $('#tt').datagrid({
            title: "域名列表",
            fit: true,
            nowrap: false,
            striped: true,
            url: '/GLPT/DM/DomainNameFindPageEx.do',
            singleSelect: true,
            remoteSort: false,
            rownumbers: true,
            idField: 'Id',
            frozenColumns: [
                [
                    {field: 'ck', checkbox: true}
                ]
            ],
            columns: [
                [
                    {field: 'DomainName', title: '域名', width: 200, align: 'center'},
                    {field: 'Url', title: '访问地址', width: 200, align: 'center', formatter: fun_DomainName},
                    {field: 'WebSiteFullName', title: '网站、系统名称', width: 200, align: 'center'},
                    {field: 'GLDepartmentName', title: '管理部门', width: 120, align: 'center'},
                    {field: 'GLYClaimState', title: '认领状态', width: 120, align: 'center', formatter: fun_FormatEnabled},
                    {field: 'Remarks', title: '备注', width: 200, align: 'center'}
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

    var fun_show = function () {
        var row = $('#tt').datagrid('getSelected');
        if (row) {
            var url = "/GLPT/DM/DomainNameShow.do?Id=" + row.Id + "&IframeId=${IframeId}";
            top.openDialog(url, 'DomainNameShow', '域名资料管理 - 详细信息-[' + row.DomainName + ']', 700, 450, 50, 50);
        } else {
            showFaceMsg("请选择要操作的记录");
        }
    }

    var fun_DomainName = function (val, row) {
        if (val === undefined) {
            return "";
        } else {
            return "<a href=\"" + val + "\" target=\"_blank\">" + val + "</a>";
        }
    }


    var fun_refresh = function () {
        $('#tt').datagrid('clearSelections');
        $('#tt').datagrid('reload');
    }

    var bsClaim = "";

    var fun_allRecord = function () {
        bsClaim = "";
        fun_DomainFind();
    }

    var fun_YesClaim = function () {
        bsClaim = "YesClaim";
        fun_DomainFind();
    }

    var fun_NoClaim = function () {
        bsClaim = "NoClaim";
        fun_DomainFind();
    }


    var fun_DomainFind = function () {
        var queryParams = $('#tt').datagrid('options').queryParams;
        if (bsClaim == "YesClaim") {
            queryParams.GLYClaimState = true
        } else if (bsClaim == "NoClaim") {
            queryParams.GLYClaimState = false;
        } else {
            queryParams.GLYClaimState = null;
        }
        queryParams.fValue = $('#txt_MFFindValue1').val();
        fun_CustomFind("tt", "域名列表", queryParams);
    }
</script>
</body>
</html>