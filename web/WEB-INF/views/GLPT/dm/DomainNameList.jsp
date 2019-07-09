<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-13
  Time: 上午11:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Domain库</title>
    <jsp:include page="/Common/ListCss.jsp"></jsp:include>
    <jsp:include page="/Common/ListJs.jsp"></jsp:include>
</head>
<body class="easyui-layout">
<div id="tmpDiv" style="height: 1000px;"></div>
<div id="tb" style="height: 96px;">
    <div class="tools_bar">
        <a title="刷新当前页面" onclick="fun_refresh();" class="tools_btn">
            <span><b
                    style="background: url('/skins/Default/images/16/arrow_refresh.png') 50% 4px no-repeat;">刷新</b></span>
        </a>

        <div class="tools_separator"></div>
        <a title="" onclick="fun_add();" class="tools_btn">
            <span><b
                    style="background: url('/skins/Default/images/16/application_add.png') 50% 4px no-repeat;">添加</b></span>
        </a>
        <a title="" onclick="fun_edit();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application_edit.png') 50% 4px no-repeat;">修改</b></span>
        </a>
        <a title="" onclick="fun_del();" class="tools_btn">
            <span><b
                    style="background: url('/skins/Default/images/16/application_delete.png') 50% 4px no-repeat;">删除</b></span>
        </a>

        <div class="tools_separator"></div>
        <a title="" onclick="fun_show();" class="tools_btn">
            <span><b
                    style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">详细信息</b></span>
        </a>

        <div class="tools_separator"></div>
        <a title="" onclick="fun_RenewRecordAdd();" class="tools_btn">
            <span><b
                    style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">续订</b></span>
        </a>
        <a title="" onclick="fun_SignRecordAdd();" class="tools_btn">
            <span><b
                    style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">签到</b></span>
        </a>

        <div class="tools_separator"></div>
        <a title="" onclick="fun_DM_Enabled();" class="tools_btn">
            <span><b
                    style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">启用</b></span>
        </a>
        <a title="" onclick="fun_DM_Disable();" class="tools_btn">
            <span><b
                    style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">停用</b></span>
        </a>

        <div class="tools_separator"></div>
        <a title="" onclick="fun_IdImgDownload();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/enter.png') 50% 4px no-repeat;">证件下载</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="" onclick="fun_Import();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/out.png') 50% 4px no-repeat;">导入</b></span>
        </a>
        <a title="导出到XLS" onclick="fun_Export();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/enter.png') 50% 4px no-repeat;">导出</b></span>
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
                    <table>
                        <tbody>
                        <tr>
                            <td>
                                <select id="Disable" name="Disable">
                                    <option value="null" selected="selected">全部</option>
                                    <option value="true">已停用</option>
                                    <option value="false">未停用</option>
                                </select>
                            </td>
                            <th>关键字：</th>
                            <td>
                                <input name="MFFindValue" id="txt_MFFindValue" class="txt" style="width: 200px;"/>
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

<div id="tb_SignRecord" style="height: 50px;">
    <div class="tools_bar">
        <a title="刷新当前页面" onclick="fun_DomainSignRecordRefresh();" class="tools_btn">
            <span><b
                    style="background: url('/skins/Default/images/16/arrow_refresh.png') 50% 4px no-repeat;">刷新</b></span>
        </a>
        <a title="导出到XLS" onclick="fun_Export();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/enter.png') 50% 4px no-repeat;">导出</b></span>
        </a>
    </div>
</div>
<div id="tb_RenewRecord" style="height: 50px;">
    <div class="tools_bar">
        <a title="刷新当前页面" onclick="fun_DomainRenewRecordRefresh();" class="tools_btn">
            <span><b
                    style="background: url('/skins/Default/images/16/arrow_refresh.png') 50% 4px no-repeat;">刷新</b></span>
        </a>
        <a title="导出到XLS" onclick="fun_Export();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/enter.png') 50% 4px no-repeat;">导出</b></span>
        </a>
    </div>
</div>
<div id="mainPanel" region="center" style=" overflow-y:hidden">
    <table id="tt"></table>
</div>
<div region="east" style="width: 300px;" split="true" title="域名信息" collapsed="true">
    <div id="tabs1" class="easyui-tabs" fit="true">
        <div title="续订记录">
            <table id="tt_RenewRecord"></table>
        </div>
        <div title="签到记录">
            <table id="tt_SignRecord"></table>
        </div>
    </div>
</div>

<!--<div>
    <form id="ff_IdImgDownload" action="/GLPT/DM/DomainNameIdImgDownloadOK.do" method="post" target="_blank">
              <input type="hidden" id="DomainNameId" name="DomainNameId" value=""/>
        </form>
</div> -->
<script type="text/javascript">
$(function () {
    $('#tt').datagrid({
        title: '域名列表',
        fit: true,
        url:"/GLPT/DM/DomainNameFindPage.do",
        nowrap: false,
        striped: true,
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
                {field: 'DomainName', title: '域名', width: 200, align: 'left'},
                {field: 'Url', title: '访问地址', width: 200, align: 'left', formatter: fun_DomainName},
                {field: 'IPv4Address', title: 'IPv4地址', width: 100, align: 'left'},
                {field: 'TTL', title: 'TTL', width: 100, align: 'center'},
                {field: 'RecordType', title: '记录类型', width: 100, align: 'center'},
                {field: 'RecordVal', title: '记录值', width: 100, align: 'left'},
                {field: 'WebSiteFullName', title: '网站、系统名称', width: 200, align: 'left'},
                {field: 'GLDepartmentName', title: '管理部门', width: 120, align: 'center'},
                {field: 'GLYName', title: '管理员姓名', width: 80, align: 'center'},
                {field: 'GLYPhone', title: '电话号码', width: 100, align: 'left'},
                {field: 'GLYMobile', title: '手机号码', width: 100, align: 'left'},
                {field: 'SignInInterval', title: '签到间隔天数', width: 80, align: 'center'},
                {field: 'RenewInterval', title: '续订间隔天数', width: 80, align: 'center'},
                {field: 'LastSignTime', title: '最后签到时间', width: 140, align: 'center', formatter: fun_FormatDateTime},
                {field: 'LastRenewTime', title: '最后续订时间', width: 140, align: 'center', formatter: fun_FormatDateTime},
                {field: 'Disable', title: '停用', width: 50, align: 'center', formatter: fun_FormatEnabled},
                {field: 'Remarks', title: '备注', width: 200, align: 'center'}
            ]
        ],
        pagination: true,
        toolbar: '#tb',
        onClickRow: function (rowIndex, rowData) {
            fun_DomainSignRecordFind(rowData.Id);
            fun_DomainRenewRecordFind(rowData.Id);
        }
    });
    var p = $('#tt').datagrid('getPager');
    $(p).pagination({
        onBeforeRefresh: function () {
            $('#tt').datagrid('clearSelections');
            $('#tt').datagrid('reload');
        }
    });


    $('#tt_SignRecord').datagrid({
        fit: true,
        nowrap: false,
        striped: true,
        singleSelect: true,
        remoteSort: false,
        rownumbers: true,
        idField: 'ID',
        columns: [
            [
                {field: 'SignDate', title: '签到日期', width: 100, align: 'center'},
                {field: 'RealName', title: '签到人', width: 100, align: 'center'}
            ]
        ],
        pagination: true,
        toolbar: '#tb_SignRecord'
    });
    var p1 = $('#tt_SignRecord').datagrid('getPager');
    $(p1).pagination({
        onBeforeRefresh: function () {
            $('#tt_SignRecord').datagrid('clearSelections');
            $('#tt_SignRecord').datagrid('reload');
        }
    });
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
                {field: 'RealName', title: '续订人', width: 100, align: 'center'}
            ]
        ],
        pagination: true,
        toolbar: '#tb_RenewRecord'
    });
    var p2 = $('#tt_RenewRecord').datagrid('getPager');
    $(p2).pagination({
        onBeforeRefresh: function () {
            $('#tt_RenewRecord').datagrid('clearSelections');
            $('#tt_RenewRecord').datagrid('reload');
        }
    });
    $("#tmpDiv").hide();
});

var fun_DomainName = function (val, row) {
    if (val === undefined) {
        return "";
    } else {
        return "<a href=\"" + val + "\" target=\"_blank\">" + val + "</a>";
    }
}

var fun_add = function () {
    var url = "/GLPT/DM/DomainNameAdd.do?IframeId=${IframeId}";
    top.openDialog(url, 'DomainNameAdd', '域名资料管理 - 添加', 700, 450, 50, 50);
}
var fun_del = function () {
    var row = $('#tt').datagrid('getSelected');
    if (row) {
        delConfig("/GLPT/DM/DomainNameThoroughDelOK.do", "Id=" + row.Id);
    } else {
        showFaceMsg("请选择要操作的记录");
    }
}
var fun_edit = function () {
    var row = $('#tt').datagrid('getSelected');
    if (row) {
        var url = "/GLPT/DM/DomainNameEdit.do?Id=" + row.Id + "&IframeId=${IframeId}";
        top.openDialog(url, 'DomainNameEdit', '域名资料管理 - 修改', 700, 450, 50, 50);
        //var url = "/GLPT/DM/DomainNameClaim.do?Id=" + row.Id + "&IframeId=${IframeId}";
       // top.openDialog(url, 'DomainNameClaim', '域名资料管理 - 认领', 700, 450, 50, 50);
    } else {
        showFaceMsg("请选择要操作的记录");
    }
}
var fun_show = function () {
    var row = $('#tt').datagrid('getSelected');
    if (row) {
        var url = "/GLPT/DM/DomainNameShow.do?Id=" + row.Id + "&IframeId=${IframeId}";
        top.openDialog(url, 'DomainNameShow', '域名资料管理 - 详细信息-[' + row.DomainName + ']', 700, 450, 50, 50);
    } else {
        showFaceMsg("请选择要操作的记录");
    }
}

var fun_refresh = function () {
    $('#tt').datagrid('clearSelections');
    $('#tt').datagrid('reload');
}

var fun_DomainFind = function () {
    var queryParams = $('#tt').datagrid('options').queryParams;
    queryParams.fValue = $('#txt_MFFindValue').val();
    queryParams.Disable = $("#Disable").val();
    fun_CustomFind("tt", "域名列表", queryParams);
}

var fun_SignRecordAdd = function () {
    var row = $('#tt').datagrid('getSelected');
    if (row) {
        var url = "/GLPT/DM/SignRecordAdd.do?DomainNameId=" + row.Id + "&IframeId=${IframeId}";
        top.openDialog(url, 'SignRecordAdd', '域名资料管理 - 签到', 400, 250, 50, 50);
    } else {
        showFaceMsg("请选择要操作的记录");
    }
}

var fun_DomainSignRecordFind = function (domainNameId) {
    $("#tt_SignRecord").datagrid({url:"/GLPT/DM/SignRecordFindPage.do"});
    var queryParams = $('#tt_SignRecord').datagrid('options').queryParams;

    queryParams.DomainNameId = domainNameId;
    fun_CustomFind("tt_SignRecord", "", queryParams);
}


var fun_DomainSignRecordRefresh = function () {
    $('#tt_SignRecord').datagrid('clearSelections');
    $('#tt_SignRecord').datagrid('reload');
}

var fun_DomainSignRecordExport = function () {
    var row = $('#tt').datagrid('getSelected');
    if (row) {
        window.open("/GLPT/DM/DomainNameSignRecordExportToExcel.do?DomainNameId=" + row.Id);
    } else {
        showFaceMsg("请选择要操作的记录");
    }
}

var fun_RenewRecordAdd = function () {
    var row = $('#tt').datagrid('getSelected');
    if (row) {
        var url = "/GLPT/DM/RenewRecordAdd.do?DomainNameId=" + row.Id + "&IframeId=${IframeId}";
        top.openDialog(url, 'RenewRecordAdd', '域名资料管理 - 续订', 400, 250, 50, 50);
    } else {
        showFaceMsg("请选择要操作的记录");
    }
}

var fun_DomainRenewRecordFind = function (domainNameId) {
    var queryParams = $('#tt_RenewRecord').datagrid('options').queryParams;
    queryParams.DomainNameId = domainNameId;
    fun_CustomFind("tt_RenewRecord", "", queryParams);
}


var fun_DomainRenewRecordRefresh = function () {
    $('#tt_RenewRecord').datagrid('clearSelections');
    $('#tt_RenewRecord').datagrid('reload');
}

var fun_DomainRenewRecordExport = function () {
    var row = $('#tt').datagrid('getSelected');
    if (row) {
        window.open("/GLPT/DM/DomainNameRenewRecordExportToExcel.do?DomainNameId=" + row.Id);
    } else {
        showFaceMsg("请选择要操作的记录");
    }
}

var fun_GetDmDetails = function (DomainNameId) {
    $.ajax({
        type: "POST",
        url: "/COMMON/EmptyClassInfoCurrToHtml.do",
        processData: true,
        dataType: "html",
        data: {DomainNameId: DomainNameId},
        success: function (data) {
            $("#div_ks1").html(data);
        }, complete: function () {

        }, error: function () {
            $("#div_ks1").html("获取数据错误");
        }

    });

}
var fun_IdImgDownload=function(){
    var row=$("#tt").datagrid("getSelected");
    if(row){
       // $("#DomainNameId").val(row.Id);
       // $("#ff_IdImgDownload").submit();
        window.open("/GLPT/DM/DomainNameIdImgDownloadOK.do?DomainNameId=" + row.Id);
    }else{
        showFaceMsg("请选择要操作的记录");
    }
}

var fun_Import = function () {
    var url = "/GLPT/DM/DomainNameImport.do?IframeId=${IframeId}";
    top.openDialog(url, 'DomainNameImport', '域名资料管理 - 导入', 450, 280, 50, 50);
}

var fun_Export = function () {
    window.open("/GLPT/DM/DomainNameExportToExcel.do?Disable=" + $("#Disable").val() + "&fName=&fValue=" + $('#txt_MFFindValue').val());
}

var fun_DM_Enabled = function () {
    var row = $('#tt').datagrid('getSelected');
    if (row) {
        if (row.Disable == false || row.Disable.toLocaleLowerCase() == 'false') {
            showFaceMsg("[" + row.WebSiteFullName + "]已经是启用状态");
        } else {
            showConfirmMsg("真的要启用[" + row.WebSiteFullName + "]吗？", function () {
                $.ajax({
                    type: "POST",
                    url: "/GLPT/DM/DomainNameEnabledOK.do",
                    processData: true,
                    dataType: "html",
                    data: {DomainNameId: row.Id},
                    success: function (data) {
                        if (data.toLocaleLowerCase() == "true") {
                            fun_refresh();
                            showFaceMsg("操作成功");
                        } else {
                            showFaceMsg(data);
                        }
                    }
                });
            });
        }
    } else {
        showFaceMsg("请选择要操作的记录");
    }
}

var fun_DM_Disable = function () {
    var row = $('#tt').datagrid('getSelected');
    if (row) {
        if (row.Disable == true || row.Disable.toLocaleLowerCase() == 'true') {
            showFaceMsg("[" + row.WebSiteFullName + "]已经是停用状态");
        } else {
            var url = "/GLPT/DM/DomainNameDisable.do?Id=" + row.Id + "&IframeId=${IframeId}";
            top.openDialog(url, 'DomainNameDisable', '域名资料管理 - 停用[' + row.WebSiteFullName + "]", 400, 200, 50, 50);
        }
    } else {
        showFaceMsg("请选择要操作的记录");
    }
}
</script>
</body>
</html>