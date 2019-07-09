<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-13
  Time: 上午11:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>开发方资料管理</title>
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
        <a title="" onclick="fun_show();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">显示</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="" onclick="fun_add();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application_add.png') 50% 4px no-repeat;">添加</b></span>
        </a>
        <a title="" onclick="fun_edit();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application_edit.png') 50% 4px no-repeat;">修改</b></span>
        </a>
        <a title="" onclick="fun_del();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application_delete.png') 50% 4px no-repeat;">删除</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="关闭当前窗口" onclick="ThisCloseTab();" class="tools_btn">
            <span class=""><b style="background: url('/skins/Default/images/16/back.png') 50% 4px no-repeat;">离开</b></span>
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
                                <th>关键字：</th>
                                <td>
                                    <input name="MFFindValue" id="txt_MFFindValue" class="txt" style="width: 200px;"/>
                                </td>
                                <td>
                                    <input id="btnSearch" type="button" class="btnSearch" value="搜 索"
                                           onclick="fun_DevelopingPartyFind();"/>
                                </td>
                            </tr>
                            </tbody>
                        </table>
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
        $('#tt').datagrid({
            title: '开发方资料列表',
            fit: true,
            nowrap: false,
            striped: true,
            url: '/GLPT/DM/DevelopingPartyFindCurrUserPage.do',
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
                    {field: 'FullName', title: '名称', width: 250, align: 'left'},
                    {field: 'ContactPerson', title: '联系人', width: 100, align: 'center'},
                    {field: 'QQ', title: 'QQ', width: 100, align: 'center'},
                    {field: 'EMail', title: '电子邮件', width: 100, align: 'center'},
                    {field: 'Phone', title: '电话号码', width: 100, align: 'center'},
                    {field: 'Mobile', title: '手机号码', width: 100, align: 'center'},
                    {field: 'Address', title: '联系地址', width: 100, align: 'center'},
                    {field: 'CooperationDate', title: '合作日期', width: 100, align: 'center'},
                    {field: 'Enabled', title: '有效', width: 80, align: 'center', formatter: fun_FormatEnabled},
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
    var fun_add=function(){
            var url="/GLPT/DM/DevelopingPartyAdd.do?IframeId=${IframeId}";
            top.openDialog(url, 'DevelopingPartyAdd', '开发方资料管理 - 添加', 600, 350, 50, 50);
    }
    var fun_del=function(){
        var row = $('#tt').datagrid('getSelected');
        if(row){
            showConfirmMsg("注：删除操作不可恢复，您确定要继续么？", function (r) {
                if (r) {
                    $.ajax({
                        type: "POST",
                        url: "/GLPT/DM/DevelopingPartyThoroughDelOK.do",
                        processData: true,
                        dataType: "html",
                        data: {Id:row.Id},
                        success: function (data) {
                            if (data.toLocaleLowerCase() == 'true') {
                                showFaceMsg("删除成功");
                                fun_refresh();
                            } else if (data.toLocaleLowerCase() == 'false') {
                                showFaceMsg("删除失败，请稍后重试");
                            } else {
                                showFaceMsg(data);
                            }
                        }
                    });
                }
            });
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }
    var fun_edit=function(){
        var row = $('#tt').datagrid('getSelected');
        if(row){
            var url="/GLPT/DM/DevelopingPartyEdit.do?Id="+row.Id+"&IframeId=${IframeId}";
            top.openDialog(url, 'DevelopingPartyEdit', '开发方资料管理 - 修改', 600, 350, 50, 50);
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }
    var fun_show=function(){
        var row = $('#tt').datagrid('getSelected');
        if(row){
            var url="/GLPT/DM/DevelopingPartyShow.do?Id="+row.Id+"&IframeId=${IframeId}";
            top.openDialog(url, 'DevelopingPartyShow', '开发方资料管理 - 显示', 600, 350, 50, 50);
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }
    var fun_refresh=function(){
        $('#tt').datagrid('clearSelections');
        $('#tt').datagrid('reload');
    }

    var fun_DevelopingPartyFind=function(){
        var queryParams=$('#tt').datagrid('options').queryParams;
        queryParams.Enabled=$('#Enabled').attr('checked');
        queryParams.fValue=$('#txt_MFFindValue').val();
        fun_CustomFind("tt","开发方资料列表",queryParams);
    }
    var fun_Import = function () {
        var url = "/GLPT/DM/DevelopingPartyImport.do?IframeId=${IframeId}";
        top.openDialog(url, 'DevelopingPartyImport', '开发方资料 - 导入', 450, 280, 50, 50);
    }

    var fun_Export = function () {
        window.open("/GLPT/DM/DevelopingPartyExportToExcel.do?Enabled="+$("#Enabled").attr('checked')+"&fName=&fValue=" + $('#txt_MFFindValue').val());
    }
</script>
</body>
</html>