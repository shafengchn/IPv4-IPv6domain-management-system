<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-13
  Time: 上午11:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>借用者管理</title>
    <jsp:include page="/Common/ListCss.jsp"></jsp:include>
    <jsp:include page="/Common/ListJs.jsp"></jsp:include>
</head>
<body class="easyui-layout">
<div id="tmpDiv" style="height: 1000px;"></div>
<div id="tb" style="height: 95px;">
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
        <a title="查看相关详细信息" onclick="fun_show('tt1');" class="tools_btn">
            <span class=""><b
                    style="background: url('/skins/Default/images/16/page_white_find.png') 50% 4px no-repeat;">查看详细</b></span>
        </a>
        <a title="重新设置查询密码" onclick="fun_ResetPassword();" class="tools_btn">
            <span><b
                    style="background: url('/skins/Default/images/16/group_key.png') 50% 4px no-repeat;">重置查询密码</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="设置所属部门" onclick="fun_BorrowerAndDepartment()" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">设置所属部门</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="" onclick="fun_Import();" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">导入</b></span>
        </a>
        <div class="tools_separator"></div>
        <a title="" onclick="fun_QRCode('tt1');" class="tools_btn">
            <span><b style="background: url('/skins/Default/images/16/application.png') 50% 4px no-repeat;">生成二维码</b></span>
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
                                           onclick="fun_MFFindEx2('tt1','借用者列表'
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
                                        <option value="Code">识别码</option>
                                        <option value="FullName">姓名</option>
                                        <option value="Gender">性别</option>
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
                                           onclick="fun_JQFindEx2('tt1','借用者列表'
                                           ,$('#query').val()
                                           ,$('#txt_JQFindValue').val()
                                           ,$('#Enabled').attr('checked'))">
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
    <table id="tt1"></table>
</div>

<script type="text/javascript">
    $(function(){
        $('#tt1').datagrid({
            title: '借用者列表',
            fit: true,
            url: '/GLPT/BASE/BorrowerFindPage.do',
            remoteSort: false,
            rownumbers: true,
            idField: 'BorrowerId',
            frozenColumns:[[
                {field:'ck',checkbox:true}
            ]],
            columns: [
                [
                    {field: 'Code', title: '识别码', width: 80, align: 'center'},
                    {field: 'FullName', title: '姓名', width: 100, align: 'center'},
                    {field: 'Alias', title: '别名', width: 100, align: 'center'},
                    {field: 'Gender', title: '性别', width: 100, align: 'center'},
                    {field: 'Email', title: '电子邮件', width: 80, align: 'center'},
                    {field: 'Mobile', title: '手机号码', width: 100, align: 'center'},
                    {field: 'SortCode', title: '排序', width: 80, align: 'center'},
                    {field: 'Enabled', title: '有效', width: 80, rowspan: 2, align: 'center', formatter: fun_FormatEnabled},
                    {field: 'Description', title: '说明', width: 150, rowspan: 2, align: 'center'}
                ]
            ],
            pagination: true,
            toolbar: '#tb'
        });
        var p = $('#tt1').datagrid('getPager');
        $(p).pagination({
            onBeforeRefresh: function () {
                $('#tt1').datagrid('clearSelections');
                $('#tt1').datagrid('reload');
            }
        });
    });
    var fun_add1=function(){
         var url="/GLPT/BASE/BorrowerAdd.do?IframeId=${IframeId}";
         top.openDialog(url, 'BorrowerAdd', '借用者管理 - 添加', 750, 480, 50, 50);
    }

    var fun_del1=function(){
        var row = $('#tt1').datagrid('getSelections');
        var row1="";
        if(row.length>1){
            for(var i=0;i<row.length;i++){
                if(i==0){
                    row1=row[i].BorrowerId;
                }else{
                    row1=row1+"|"+row[i].BorrowerId;
                }
            }
            top.showConfirmMsg('真的要删除选中的['+row.length+']条记录吗？', function (r) {
                if (r) {
                    var win = $.messager.progress({title: '请稍等...', msg: '删除中...'});
                    $.ajax({
                        type: "POST",
                        url: "/GLPT/BASE/BorrowerBatchThoroughDelOK.do",
                        processData: true,
                        dataType: "json",
                        data: {BatchID:row1},
                        success: function (data) {
                            $.messager.progress('close');
                            if (data.success == true) {
                                var p1 = parent.document.getElementById("${IframeId}");
                                if (p1 != null) {
                                    p1.contentWindow.fun_refresh1();
                                }
                            } else {
                                showFaceMsg(data.info);
                            }
                        }
                    });
                }
            });
        }else if(row.length==1){
            delConfig("/GLPT/BASE/BorrowerThoroughDelOK.do","BorrowerId="+row[0].BorrowerId);
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }
    var fun_edit1=function(){
        var row = $('#tt1').datagrid('getSelected');
        if(row){
            var url="/GLPT/BASE/BorrowerEdit.do?BorrowerId="+row.BorrowerId+"&IframeId=${IframeId}";
            top.openDialog(url, 'EmployeeEdit', '借用者管理 - 修改', 750, 480, 50, 50);
        }else{
            showFaceMsg("请选择要操作的记录");
        }

    }

    var fun_show = function (gridID) {
        var row = $('#' + gridID).datagrid('getSelected');
        if (row) {
            var url = "/GLPT/BASE/BorrowerShow.do?BorrowerId=" + row.BorrowerId + "&IframeId=${IframeId}";
            top.openDialog(url, 'BorrowerShow', '借用者信息 - 查看详细信息', 500, 430, 50, 50);
        } else {
            showFaceMsg("请选择要操作的记录");
        }
    }

    var fun_QRCode = function (gridID) {
        var row = $('#' + gridID).datagrid('getSelected');
        if (row) {
            var url = "/GLPT/BASE/BorrowerQRCode.do?EnCode=" + row.Code + "&ImgWidth=200&ImgHeight=200&IframeId=${IframeId}";
            top.openDialog(url, 'BorrowerQRCode', '借用者管理 - 生成二维码', 300, 250, 50, 50);
        } else {
            showFaceMsg("请选择要操作的记录");
        }
    }

    var fun_refresh1=function(){
        $('#tt1').datagrid('clearSelections');
        $('#tt1').datagrid('reload');
    }

    var fun_BorrowerAndDepartment=function(){
        var row = $('#tt1').datagrid('getSelected');
        if(row){
            var url = "/GLPT/BASE/BorrowerAndDepartmentBatchAdd.do?BorrowerId="+row.BorrowerId+"&IframeId=${IframeId}";
            top.openDialog(url, 'BorrowerAndDepartmentBatchAdd', '设置所属部门 - ['+row.FullName+']', 500, 280, 50, 50);
        }else{
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

    var fun_Import = function () {
        var url = "/GLPT/BASE/BorrowerImport.do?IframeId=${IframeId}";
        top.openDialog(url, 'BorrowerImport', '借用者管理 - 导入', 450, 280, 50, 50);
    }

    var fun_ResetPassword = function () {
        var row = $('#tt1').datagrid('getSelected');
        if (row) {
            var url = "/GLPT/BASE/BorrowerResetPassword.do?BorrowerId=" + row.BorrowerId + "&IframeId=${IframeId}";
            top.openDialog(url, 'BorrowerResetPassword', '借用者管理 - 重置查询密码', 320, 110, 50, 50);
        } else {
            showFaceMsg("请选择要操作的记录");
        }
    }

</script>
</body>
</html>