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
        <a title="" onclick="fun_add();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">续订</b></span>
        </a>
        <a title="" onclick="fun_edit();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">签到</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="" onclick="fun_show();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">详细信息</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="" onclick="fun_RenewRecordAdd();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">续订</b></span>
        </a>
        <a title="" onclick="fun_SignRecordAdd();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">签到</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="导出到XLS" onclick="fun_Export();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/enter.png') 50% 4px no-repeat;">导出</b></span>
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
                           <td>
                               <span style="height: 28px;background-color: #ff0000;"><label>待续订</label></span>

                               <span style="height: 28px;background-color: yellow;"><label>待签到</label></span>

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
            title: '域名列表',
            fit: true,
            nowrap: false,
            striped: true,
            url: '/GLPT/DM/DomainNameFindPage.do',
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
                    {field: 'MACAddress', title: 'MAC地址', width: 100, align: 'center'},
                    {field: 'WebSiteFullName', title: '网站、系统名称', width: 200, align: 'center'},
                    {field: 'BusinessTypeName', title: '业务类型', width: 100, align: 'center'},
                    {field: 'GLDepartmentName', title: '管理部门', width: 100, align: 'center'},
                    {field: 'GLYType', title: '技术管理员类型', width: 100, align: 'center'},
                    {field: 'GLYName', title: '技术管理员姓名', width: 100, align: 'center'},
                    {field: 'GLYPhone', title: '电话号码', width: 100, align: 'center'},
                    {field: 'GLYMobile', title: '手机号码', width: 100, align: 'center'},
                    {field: 'FZRType', title: '负责人类型', width: 100, align: 'center'},
                    {field: 'FZRName', title: '负责人姓名', width: 100, align: 'center'},
                    {field: 'FZRPhone', title: '电话号码', width: 100, align: 'center'},
                    {field: 'FZRMobile', title: '手机号码', width: 100, align: 'center'},
                    {field: 'SignInInterval', title: '签到间隔天数', width: 100, align: 'center'},
                    {field: 'RenewInterval', title: '续订间隔天数', width: 100, align: 'center'},
                    {field: 'LastSignIn', title: '最后签到时间', width: 100, align: 'center'},
                    {field: 'LastRenew', title: '最后续订时间', width: 100, align: 'center'},
                    {field: 'Disable', title: '停用', width: 80, align: 'center', formatter: fun_FormatEnabled},
                    {field: 'DeleteMark', title: '标识删除', width: 80, align: 'center', formatter: fun_FormatEnabled},
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

    var fun_DomainName = function (val, row) {
       if(val===undefined){
          return "";
       }else{
        return "<a href=\""+val+"\" target=\"_blank\">"+val+"</a>";
       }
    }

    var fun_add=function(){
        var url="/GLPT/DM/DomainNameAdd.do?IframeId=${IframeId}";
        top.openDialog(url, 'DomainNameAdd', '域名资料管理 - 添加', 700, 450, 50, 50);
    }
    var fun_del=function(){
        var row = $('#tt').datagrid('getSelected');
        if(row){
            delConfig("/GLPT/DM/DomainNameThoroughDelOK.do","Id="+row.Id);
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }
    var fun_edit=function(){
        var row = $('#tt').datagrid('getSelected');
        if(row){
            var url="/GLPT/DM/DomainNameEdit.do?Id="+row.Id+"&IframeId=${IframeId}";
            top.openDialog(url, 'DomainNameEdit', '域名资料管理 - 修改', 700, 450, 50, 50);
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }
    var fun_show=function(){
        var row = $('#tt').datagrid('getSelected');
        if(row){
            var url="/GLPT/DM/DomainNameShow.do?Id="+row.Id+"&IframeId=${IframeId}";
            top.openDialog(url, 'DomainNameShow', '域名资料管理 - 详细信息-['+row.DomainName+']', 700, 450, 50, 50);
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }
    var fun_refresh=function(){
        $('#tt').datagrid('clearSelections');
        $('#tt').datagrid('reload');
    }

    var fun_DomainFind=function(){
        var queryParams=$('#tt').datagrid('options').queryParams;
        queryParams.fValue=$('#txt_MFFindValue').val();
        queryParams.Disable=$("#Disable").val();
        fun_CustomFind("tt","域名列表",queryParams);
    }

    var fun_Import = function () {
        var url = "/GLPT/DM/DomainNameImport.do?IframeId=${IframeId}";
        top.openDialog(url, 'DomainNameImport', '域名资料管理 - 导入', 450, 280, 50, 50);
    }

    var fun_Export = function () {
        window.open("/GLPT/DM/DomainNameExportToExcel.do?Disable="+$("#Disable").val()+"&fName=&fValue=" + $('#txt_MFFindValue').val());
    }
</script>
</body>
</html>