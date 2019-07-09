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
    <title>我的空间</title>
    <jsp:include page="/Common/ListCss.jsp"></jsp:include>
    <jsp:include page="/Common/ListJs.jsp"></jsp:include>
</head>
<body class="easyui-layout">
<div id="tmpDiv" style="height: 1000px;"></div>
<div id="tb" style="height: 96px;">
    <div class="tools_bar">
        <a title="刷新当前页面" onclick="fun_refresh();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/arrow_refresh.png') 50% 4px no-repeat;">刷新</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="" onclick="fun_Show_SignRecord();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">签到记录</b></span>
        </a>
        <a title="" onclick="fun_Show_RenewRecord();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">续订记录</b></span>
        </a>
        <a title="" onclick="fun_Show_Info();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">详细信息</b></span>
        </a>

        <div class="tools_separator"></div>
        <a title="" onclick="fun_CancelClaim();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">取消认领</b></span>
        </a>
        <a title="" onclick="fun_ClaimEdit();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">修改</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="导出到XLS" onclick="fun_Export();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/enter.png') 50% 4px no-repeat;">导出</b></span>
        </a>
    </div>
    <div class="btnbarcontetn" style="margin-top: 1px; background: #fff">
        <table border="0"  style="height: 45px;">
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
<div id="tb_NoSign" style="height: 50px;">
    <div class="tools_bar">
        <a title="刷新当前页面" onclick="fun_refresh_NoSignRecord();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/arrow_refresh.png') 50% 4px no-repeat;">刷新</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="" onclick="fun_SignRecordAdd();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">签到</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="" onclick="fun_Show_Info_SignRecord();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">详细信息</b></span>
        </a>
    </div>
</div>
<div id="tb_NoRenew" style="height: 50px;">
    <div class="tools_bar">
        <a title="刷新当前页面" onclick="fun_refresh_NoRenewRecord();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/arrow_refresh.png') 50% 4px no-repeat;">刷新</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="" onclick="fun_RenewRecordAdd();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">续订</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="" onclick="fun_Show_Info_RenewRecord();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">详细信息</b></span>
        </a>
    </div>
</div>
<div id="mainPanel" region="center" style=" overflow-y:hidden">

    <div id="div_tab" class="easyui-tabs" fit="true">
        <div title="域名列表<span>(${myDmRowCount})</span>">
            <table id="tt"></table>
        </div>
        <div title="待签到<span id='div_NoSignCount' style='color:red;'>(${NoSignRowCount})</span>">
            <table id="tt_NoSignRecord"></table>
        </div>
        <div title="待续订<span id='div_NoRenewCount' style='color:red;'>(${NoRenewRowCount})</span>">
            <table id="tt_NoRenewRecord"></table>
        </div>
    </div>


</div>

<script type="text/javascript">
    $(function () {
        $('#tt').datagrid({
            fit: true,
            nowrap: false,
            striped: true,
            url: '/GLPT/DM/DomainNameFindGLYCodePage.do?GLYCode=${user.code}',
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
                    {field: 'Url', title: '访问地址', width: 200, align: 'center',formatter:fun_DomainName},
                    {field: 'IPv4Address', title: 'IPv4地址', width: 100, align: 'center'},
                    {field: 'IPv6Address', title: 'IPv6地址', width: 100, align: 'center'},
                    {field: 'TTL', title: 'TTL', width: 100, align: 'center'},
                    {field: 'RecordType', title: '记录类型', width: 100, align: 'center'},
                    {field: 'RecordVal', title: '记录值', width: 100, align: 'left'},
                    {field: 'WebSiteFullName', title: '网站、系统名称', width: 200, align: 'center'},
                    {field: 'GLDepartmentName', title: '管理部门', width: 120, align: 'center'},
                    {field: 'GLYName', title: '管理员姓名', width: 100, align: 'center'},
                    {field: 'GLYPhone', title: '电话号码', width: 100, align: 'center'},
                    {field: 'GLYMobile', title: '手机号码', width: 100, align: 'center'},
                    {field: 'FZRName', title: '负责人姓名', width: 100, align: 'center'},
                    {field: 'FZRPhone', title: '电话号码', width: 100, align: 'center'},
                    {field: 'FZRMobile', title: '手机号码', width: 100, align: 'center'},
                    {field: 'SignInInterval', title: '签到间隔天数', width: 100, align: 'center'},
                    {field: 'RenewInterval', title: '续订间隔天数', width: 100, align: 'center'},
                    {field: 'LastSignTime', title: '最后签到时间', width: 140, align: 'center',formatter:fun_FormatDateTime},
                    {field: 'LastRenewTime', title: '最后续订时间', width: 140, align: 'center',formatter:fun_FormatDateTime},
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

        $('#tt_NoSignRecord').datagrid({
            fit: true,
            nowrap: false,
            striped: true,
            url: '/GLPT/DM/DomainNameFindNoSignRecordPage.do?GLYCode=${user.code}',
            singleSelect: true,
            remoteSort: false,
            rownumbers: true,
            idField: 'Id',
            columns: [
                [
                    {field: 'DomainName', title: '域名', width: 200, align: 'left'},
                    {field: 'Url', title: '访问地址', width: 200, align: 'left',formatter:fun_DomainName},
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
                    {field: 'LastSignTime', title: '最后签到时间', width: 140, align: 'center',formatter:fun_FormatDateTime},
                    {field: 'Remarks', title: '备注', width: 200, align: 'center'}
                ]
            ],
            pagination: true,
            toolbar: '#tb_NoSign'
        });
        var p1 = $('#tt_NoSignRecord').datagrid('getPager');
        $(p1).pagination({
            onBeforeRefresh: function () {
                $('#tt_NoSignRecord').datagrid('clearSelections');
                $('#tt_NoSignRecord').datagrid('reload');
            }
        });
        $('#tt_NoRenewRecord').datagrid({
            fit: true,
            nowrap: false,
            striped: true,
            url: '/GLPT/DM/DomainNameFindNoRenewRecordPage.do?GLYCode=${user.code}',
            singleSelect: true,
            remoteSort: false,
            rownumbers: true,
            idField: 'Id',
            columns: [
                [
                    {field: 'DomainName', title: '域名', width: 200, align: 'left'},
                    {field: 'Url', title: '访问地址', width: 200, align: 'left',formatter:fun_DomainName},
                    {field: 'IPv4Address', title: 'IPv4地址', width: 100, align: 'left'},
                    {field: 'TTL', title: 'TTL', width: 100, align: 'center'},
                    {field: 'RecordType', title: '记录类型', width: 100, align: 'center'},
                    {field: 'RecordVal', title: '记录值', width: 100, align: 'left'},
                    {field: 'WebSiteFullName', title: '网站、系统名称', width: 200, align: 'left'},
                    {field: 'GLDepartmentName', title: '管理部门', width: 120, align: 'center'},
                    {field: 'GLYName', title: '管理员姓名', width: 80, align: 'center'},
                    {field: 'GLYPhone', title: '电话号码', width: 100, align: 'left'},
                    {field: 'GLYMobile', title: '手机号码', width: 100, align: 'left'},
                    {field: 'RenewInterval', title: '续订间隔天数', width: 80, align: 'center'},
                    {field: 'LastRenewTime', title: '最后续订时间', width: 140, align: 'center',formatter:fun_FormatDateTime},
                    {field: 'Remarks', title: '备注', width: 200, align: 'center'}
                ]
            ],
            pagination: true,
            toolbar: '#tb_NoRenew'
        });
        var p2 = $('#tt_NoRenewRecord').datagrid('getPager');
        $(p2).pagination({
            onBeforeRefresh: function () {
                $('#tt_NoRenewRecord').datagrid('clearSelections');
                $('#tt_NoRenewRecord').datagrid('reload');
            }
        });
        $("#tmpDiv").hide();
    });

    var fun_DomainName = function (val, row) {
       if(val===undefined){
          return "";
       }else{
        return "<a href=\""+val+"\" target=\"_blank\" onclick=\"fun_ClickUrl_SignRecordAddOK('"+row.Id+"','"+val+"')\">"+val+"</a>";
       }
    }

    var fun_ClickUrl_SignRecordAddOK=function(DomainNameId,url){
        $.ajax({
            type: "POST",
            url: "/GLPT/DM/SignRecordAddClickUrlOK.do",
            processData: true,
            dataType: "json",
            data: {DomainNameId:DomainNameId},
            success: function (data) {
                if (data.success == true) {
                    fun_refresh();
                    fun_refresh_NoSignRecord();
                    fun_refresh_NoRenewRecord();
                } else {
                    showFaceMsg(data.info);
                }
            }
        });
    }

    var fun_Show_Info_SignRecord=function(){
        var row = $('#tt_NoSignRecord').datagrid('getSelected');
        if(row){
            var url="/GLPT/DM/DomainNameShow.do?Id="+row.Id+"&IframeId=${IframeId}";
            top.openDialog(url, 'DomainNameShow', '域名资料管理 - 详细信息-['+row.DomainName+']', 700, 450, 50, 50);
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }

    var fun_Show_Info_RenewRecord=function(){
        var row = $('#tt_NoRenewRecord').datagrid('getSelected');
        if(row){
            var url="/GLPT/DM/DomainNameShow.do?Id="+row.Id+"&IframeId=${IframeId}";
            top.openDialog(url, 'DomainNameShow', '域名资料管理 - 详细信息-['+row.DomainName+']', 700, 450, 50, 50);
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }

    var fun_Show_Info=function(){
        var row = $('#tt').datagrid('getSelected');
        if(row){
            var url="/GLPT/DM/DomainNameShow.do?Id="+row.Id+"&IframeId=${IframeId}";
            top.openDialog(url, 'DomainNameShow', '域名资料管理 - 详细信息-['+row.DomainName+']', 700, 450, 50, 50);
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }
    var fun_Show_SignRecord=function(){
        var row = $('#tt').datagrid('getSelected');
        if(row){
            var url="/GLPT/DM/DomainNameSignRecordShow.do?DomainNameId="+row.Id+"&IframeId=${IframeId}";
            top.openDialog(url, 'DomainNameSignRecordShow', '域名资料管理 - 签到记录-['+row.DomainName+']', 700, 450, 50, 50);
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }
    var fun_Show_RenewRecord=function(){
        var row = $('#tt').datagrid('getSelected');
        if(row){
            var url="/GLPT/DM/DomainNameRenewRecordShow.do?DomainNameId="+row.Id+"&IframeId=${IframeId}";
            top.openDialog(url, 'DomainNameRenewRecordShow', '域名资料管理 - 续订记录-['+row.DomainName+']', 700, 450, 50, 50);
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }

   var fun_CancelClaim=function(){
       var row = $('#tt').datagrid('getSelected');
       if(row){
            top.showConfirmMsg("真的要取消认领吗？",function(){
                $.ajax({
                    type: "POST",
                    url: "/GLPT/DM/DomainNameCancelClaimOK.do",
                    processData: true,
                    dataType: "json",
                    data: {Id:row.Id},
                    success: function (data) {
                        $.messager.progress('close');
                        if (data.success == true) {
                            fun_refresh();
                            fun_refresh_NoSignRecord();
                            fun_refresh_NoRenewRecord();
                        } else {
                            showFaceMsg(data.info);
                        }
                    }
                });
            });
       }else{
           showFaceMsg("请选择要操作的记录");
       }
   }

   var fun_ClaimEdit=function(){
       var row = $('#tt').datagrid('getSelected');
       if (row) {
           var url = "/GLPT/DM/DomainNameClaimEdit.do?Id=" + row.Id + "&IframeId=${IframeId}";
           top.openDialog(url, 'DomainNameClaimEdit', '我的域名 - 修改['+row.DomainName+']', 700, 450, 50, 50);
       } else {
           showFaceMsg("请选择要操作的记录");
       }
   }

    var fun_refresh=function(){
        $('#tt').datagrid('clearSelections');
        $('#tt').datagrid('reload');
    }
 var fun_refresh_NoSignRecord=function(){
        $('#tt_NoSignRecord').datagrid('clearSelections');
        $('#tt_NoSignRecord').datagrid('reload');
    }
 var fun_refresh_NoRenewRecord=function(){
        $('#tt_NoRenewRecord').datagrid('clearSelections');
        $('#tt_NoRenewRecord').datagrid('reload');
    }

    var fun_DomainFind=function(){
        var queryParams=$('#tt').datagrid('options').queryParams;
        queryParams.fValue=$('#txt_MFFindValue1').val();
        //queryParams.Disable=$("#Disable").val();
        fun_CustomFind("tt","域名列表",queryParams);
    }

    var fun_Export = function () {
        window.open("/GLPT/DM/DomainNameExportToExcel.do?Disable="+$("#Disable").val()+"&fName=&fValue=" + $('#txt_MFFindValue').val());
    }

    var fun_SignRecordAdd=function(){
        var row = $('#tt_NoSignRecord').datagrid('getSelected');
        if(row){
            var url="/GLPT/DM/SignRecordAdd.do?DomainNameId="+row.Id+"&IframeId=${IframeId}";
            top.openDialog(url, 'SignRecordAdd', '域名资料管理 - 签到', 400, 250, 50, 50);
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }

    var fun_RenewRecordAdd=function(){
        var row = $('#tt_NoRenewRecord').datagrid('getSelected');
        if(row){
            var url="/GLPT/DM/RenewRecordAdd.do?DomainNameId="+row.Id+"&IframeId=${IframeId}";
            top.openDialog(url, 'RenewRecordAdd', '域名资料管理 - 续订', 400, 250, 50, 50);
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }
</script>
</body>
</html>