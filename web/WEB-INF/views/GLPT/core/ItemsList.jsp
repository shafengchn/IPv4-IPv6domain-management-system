<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-13
  Time: 上午11:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>数据字典</title>
    <jsp:include page="/Common/ListCss.jsp"></jsp:include>
    <jsp:include page="/Common/ListJs.jsp"></jsp:include>
</head>
<body  class="easyui-layout">
<div id="leftPanel" region="west" title="数据字典类别" style="width: 250px;" split="true">
     <table id="tt"></table>
</div>

<div id="mainPanel" region="center">
    <table id="tt1"></table>
</div>
<div id="tb" style="padding:1px; height:auto;">
    <div  class="tools_bar">
        <a title="刷新当前页面" onclick="fun_refresh()" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/arrow_refresh.png') 50% 4px no-repeat;">刷新</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="" onclick="fun_add()" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application_add.png') 50% 4px no-repeat;">新增</b></span>
        </a>
        <a title="" onclick="fun_edit()" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application_edit.png') 50% 4px no-repeat;">编辑</b></span>
        </a>
        <a title="" onclick="fun_del()" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application_delete.png') 50% 4px no-repeat;">删除</b></span>
        </a>
    </div>
</div>
<div id="tb1"  style="padding:1px; height:auto;">
    <div class="tools_bar">
        <a title="刷新当前页面" onclick="fun_refresh1()" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/arrow_refresh.png') 50% 4px no-repeat;">刷新</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="" onclick="fun_add1()" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application_add.png') 50% 4px no-repeat;">新增</b></span>
        </a>
        <a title="" onclick="fun_edit1()" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application_edit.png') 50% 4px no-repeat;">编辑</b></span>
        </a>
        <a title="" onclick="fun_del1()" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application_delete.png') 50% 4px no-repeat;">删除</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="关闭当前窗口" onclick="ThisCloseTab();" class="tools_btn">
            <span class=""><b style="background: url('/skins/Default/images/16/back.png') 50% 4px no-repeat;">离开</b></span>
        </a>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $("#tmpDiv").hide();
        $('#tt').treegrid({
            title: '',
            fit: true,
            url: '/GLPT/CORE/ItemsFindAllTreeSimpleData.do',
            singleSelect:true,
            remoteSort: false,
            idField: 'id',treeField:'text',
            columns: [
                [
                    {field: 'text', title: '项目名称', width: 200, align: 'left'}
                ]
            ],
            toolbar: '#tb',
            onClickRow:function(row){
                fun_MFFindEx1('tt1',"数据字典明细-"+row.text,row.id);
            }
        });
        $('#tt1').datagrid({
            title: '数据字典明细',
            fit: true,
            url: '/GLPT/CORE/ItemDetailsFindPage.do',
            singleSelect:true,
            remoteSort: false,
            rownumbers: true,
            idField: 'ItemDetailsId',
            frozenColumns:[[
                {field:'ck',checkbox:true}
            ]],
            columns: [
                [
                    {field: 'Code', title: '代码(值)', width: 200, align: 'center'},
                    {field: 'FullName', title: '名称', width: 200, align: 'center'},
                    {field: 'SortCode', title: '排序', width: 60, align: 'center'},
                    {field: 'Enabled', title: '有效', width: 60, align: 'center',formatter: fun_FormatEnabled},
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

    var fun_add=function(){
        var row = $('#tt').treegrid('getSelected');
        var parentNode="";
        if (row){
            parentNode=row.ItemsId;
        }
        var url="/GLPT/CORE/ItemsAdd.do?ParentId="+parentNode+"&IframeId=${IframeId}";
        top.openDialog(url, 'ItemsAdd', '数据字典类别 - 添加', 410, 215, 50, 50);
    }

    var fun_del=function(){
        var row = $('#tt').treegrid('getSelected');
        if(row){
            delConfig("/GLPT/CORE/ItemsThoroughDelOK.do","ItemsId="+row.id);
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }
    var fun_edit=function(){
        var row = $('#tt').treegrid('getSelected');
        if(row){
            var url="/GLPT/CORE/ItemsEdit.do?ItemsId="+row.id+"&IframeId=${IframeId}";
            top.openDialog(url, 'ItemsAdd', '数据字典类别 - 修改', 410, 215, 50, 50);
        }else{
            showFaceMsg("请选择要操作的记录");
        }

    }

    var fun_refresh=function(){
        fun_treeMFFind('tt','');
    }


    var fun_add1=function(){
        var row = $('#tt').treegrid('getSelected');
        var parentNode="";
        if (row){
            parentNode=row.id;
            var url="/GLPT/CORE/ItemDetailsAdd.do?ItemsId="+parentNode+"&IframeId=${IframeId}";
            top.openDialog(url, 'ItemDetailsAdd', '数据字典明细 - 添加', 410, 300, 50, 50);
        }else{
            showFaceMsg("请选择数据字典类别");
        }
    }

    var fun_del1=function(){
        var row = $('#tt1').datagrid('getSelected');
        if(row){
            delConfig("/GLPT/CORE/ItemDetailsThoroughDelOK.do","ItemDetailsId="+row.ItemDetailsId);
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }
    var fun_edit1=function(){
        var row = $('#tt1').datagrid('getSelected');
        if(row){
            var url="/GLPT/CORE/ItemDetailsEdit.do?ItemDetailsId="+row.ItemDetailsId+"&IframeId=${IframeId}";
            top.openDialog(url, 'ItemDetailsAdd', '数据字典明细 - 修改', 410, 300, 50, 50);
        }else{
            showFaceMsg("请选择要操作的记录");
        }

    }

    var fun_refresh1=function(){
        $('#tt1').datagrid('clearSelections');
        $('#tt1').datagrid('reload');
    }


    </script>
</body>
</html>