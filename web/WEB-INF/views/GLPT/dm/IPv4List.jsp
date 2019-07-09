<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-13
  Time: 上午11:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>IPv4库</title>
    <jsp:include page="/Common/ListCss.jsp"></jsp:include>
    <jsp:include page="/Common/ListJs.jsp"></jsp:include>
</head>
<body class="easyui-layout">
<div id="tmpDiv" style="height: 1000px;"></div>
<div id="tb_left" style="padding:1px; height:auto;">
    <div  class="tools_bar">
        <a title="刷新当前页面" onclick="fun_refresh_left()" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/arrow_refresh.png') 50% 4px no-repeat;">刷新</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="" onclick="fun_add_left()" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application_add.png') 50% 4px no-repeat;">新增</b></span>
        </a>
        <a title="" onclick="fun_edit_left()" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application_edit.png') 50% 4px no-repeat;">编辑</b></span>
        </a>
        <a title="" onclick="fun_del_left()" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application_delete.png') 50% 4px no-repeat;">删除</b></span>
        </a>
    </div>
</div>
<div id="tb" style="height: 96px;">
    <div class="tools_bar">
        <a title="刷新当前页面" onclick="fun_refresh();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/arrow_refresh.png') 50% 4px no-repeat;">刷新</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="显示未分组记录" onclick="fun_IPv4FindNoGroup();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">未分组</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="设置分组" onclick="fun_SetGroup();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">设置分组</b></span>
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
        <a title="" onclick="fun_Import();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/out.png') 50% 4px no-repeat;">导入</b></span>
        </a>
        <a title="导出到XLS" onclick="fun_Export();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/enter.png') 50% 4px no-repeat;">导出</b></span>
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
                                <td>
                                    <select id="state" name="state">
                                        <option value="null" selected="selected">全部</option>
                                        <option value="true">已使用</option>
                                        <option value="false">未使用</option>
                                    </select>
                                </td>
                                <th>关键字：</th>
                                <td>
                                    <input name="MFFindValue" id="txt_MFFindValue" class="txt" style="width: 200px;"/>
                                </td>
                                <td>
                                    <input id="btnSearch" type="button" class="btnSearch" value="搜 索"
                                           onclick=" fun_IPv4FindEx('tt','IPv4地址-['+CurrGroupName+']',$('#Enabled').attr('checked'),$('#txt_MFFindValue').val(),$('#state').val(),null,CurrGroupCode);"/>
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
<div id="leftPanel" region="west" title="分组" style="width: 250px;" split="true">
    <table id="tt_left"></table>
</div>
<div id="mainPanel" region="center" style=" overflow-y:hidden">
    <table id="tt"></table>
</div>

<script type="text/javascript">
    var CurrGroupCode="${iPv4Group.groupCode}";
    var CurrGroupName="${iPv4Group.groupName}";
    $(function () {
        $('#tt_left').datagrid({
            title: '',
            fit: true,
            url: '/GLPT/DM/IPv4GroupData.do',
            singleSelect:true,
            remoteSort: false,
            idField: 'Id',
            columns: [
                [
                    {field: 'GroupName', title: '组名', width: 200, align: 'left'}
                ]
            ],
            toolbar: '#tb_left',
            onClickRow:function(rowIndex, rowData){
                CurrGroupCode=rowData.GroupCode;
                CurrGroupName=rowData.GroupName;
                fun_IPv4FindEx("tt","IPv4地址-["+rowData.GroupName+"]",null,"",null,null,rowData.GroupCode);
            }
        });
        $('#tt').datagrid({
            title: 'IPv4列表',
            fit: true,
            nowrap: false,
            striped: true,
            url: '/GLPT/DM/IPv4FindPage.do',
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
                    {field: 'Address', title: 'IPv4地址', width: 100, align: 'center'},
                    {field: 'State', title: '状态', width: 80, align: 'center', formatter: fun_FormatEnabled},
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
    var fun_add_left=function(){
            var url="/GLPT/DM/IPv4GroupAdd.do?IframeId=${IframeId}";
            top.openDialog(url, 'IPv4GroupAdd', '添加分组', 400, 280, 50, 50);
    }
    var fun_del_left=function(){
        var row = $('#tt_left').datagrid('getSelected');
        if(row){
            delConfig("/GLPT/DM/IPv4GroupThoroughDelOK.do","Id="+row.Id);
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }
    var fun_edit_left=function(){
        var row = $('#tt_left').datagrid('getSelected');
        if(row){
            var url="/GLPT/DM/IPv4GroupEdit.do?Id="+row.Id+"&IframeId=${IframeId}";
            top.openDialog(url, 'IPv4GroupEdit', '修改分组', 400, 280, 50, 50);
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }
    var fun_refresh_left=function(){
        $('#tt_left').datagrid('clearSelections');
        $('#tt_left').datagrid('reload');
    }

    var fun_add=function(){
            var url="/GLPT/DM/IPv4Add.do?IframeId=${IframeId}";
            top.openDialog(url, 'IPv4Add', 'IPv4 - 添加', 400, 280, 50, 50);
    }
    var fun_del=function(){
        var row = $('#tt').datagrid('getSelected');
        if(row){
            delConfig("/GLPT/DM/IPv4ThoroughDelOK.do","Id="+row.Id);
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }
    var fun_edit=function(){
        var row = $('#tt').datagrid('getSelected');
        if(row){
            var url="/GLPT/DM/IPv4Edit.do?Id="+row.Id+"&IframeId=${IframeId}";
            top.openDialog(url, 'IPv4Edit', 'IPv4管理 - 修改', 400, 280, 50, 50);
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }
    var fun_refresh=function(){
        $('#tt').datagrid('clearSelections');
        $('#tt').datagrid('reload');
    }

    var fun_IPv4FindNoGroup=function(){
        var queryParams=$('#tt').datagrid('options').queryParams;
        //queryParams.Enabled=$('#Enabled').attr('checked');
        //queryParams.fValue=$('#txt_MFFindValue').val();
        queryParams.State="null";
        queryParams.GroupCode="null";
        fun_CustomFind("tt","IPv4列表-[未分组]",queryParams);
    }
    var fun_IPv4FindEx=function(gridId,title,enabled,fValue,state,group,groupCode){
        var queryParams=$("#"+gridId).datagrid('options').queryParams;
        queryParams.Enabled=enabled;
        queryParams.fValue=fValue;
        queryParams.State=state;
        queryParams.GroupCode=groupCode;
        fun_CustomFind(gridId,title,queryParams);
    }

    var fun_SetGroup=function(){
        var rows = $('#tt').datagrid('getSelections');
        if(rows){
            var RowId="";
            for(var i1=0;i1<rows.length;i1++){
                 if(RowId.length>0&&i1<rows.length){
                     RowId=RowId+",";
                 }
                RowId=RowId+rows[i1].Id;
            }
            var url="/GLPT/DM/IPv4GroupSet.do?RowId="+RowId+"&CurrGroupCode="+CurrGroupCode+"&IframeId=${IframeId}";
            top.openDialog(url, 'IPv4GroupSet', '分组设置', 400, 280, 50, 50);
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }

    var fun_Import = function () {
        var url = "/GLPT/DM/IPv4Import.do?IframeId=${IframeId}";
        top.openDialog(url, 'IPv4Import', 'IPv4 - 导入', 450, 280, 50, 50);
    }

    var fun_Export = function () {
        window.open("/GLPT/DM/IPv4ExportToExcel.do?Enabled="+$("#Enabled").attr('checked')+"&fName=&fValue=" + $('#txt_MFFindValue').val())+"&State="+$("#state").val();
    }
</script>
</body>
</html>