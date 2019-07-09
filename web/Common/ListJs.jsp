<%--
  User: 黄良辉
  Date: 14-3-3
  Time: 下午8:50
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<script type="text/javascript" src="<%=basePath%>js/EasyUI/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/EasyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/EasyUI/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>js/FunctionJS.js"></script>
<script type="text/javascript">
/*
 * 有效状态格式化
 * */
var fun_FormatEnabled = function (val, row) {
    if (val == true || val == 'true') {
        return "<img src='<%=basePath%>skins/Default/images/checkmark.gif' alt='有效'/>";
    } else {
        return "<img src='<%=basePath%>skins/Default/images/checknomark.gif' alt='无效'/>";
    }
}


/*
 * 日期时间格式化yyyy-MM-dd HH:MM:SS
 * */
var fun_FormatDateTime = function (val, row) {
    var dateTime1 = val.split(".");
    return dateTime1[0];
}

var fun_setFirstPage= function(ids){
    var opts = $(ids).datagrid('options');
    var pager = $(ids).datagrid('getPager');
    opts.pageNumber = 1;
    opts.pageSize = opts.pageSize;
    $(pager).pagination({
        pageSize: opts.pageSize,
        pageNumber:1
    });
}

/**
 * 自定义条件查询
 * @param gridId   DataGrid ID
 * @param title     表名称
 * @param queryParams  条件
 */
var fun_CustomFind = function (gridId, title, queryParams) {
   fun_setFirstPage('#'+gridId);
    $('#' + gridId).datagrid('options').queryParams = queryParams;
    $('#' + gridId).datagrid("getPanel").panel("setTitle", title);
    $('#' + gridId).datagrid('clearSelections');
    $('#' + gridId).datagrid('reload');
}

/*
 * 列表分页查询
 * gridId DataGrid ID
 * fValue 查询值
 * */
var fun_MFFind = function (gridId, fValue) {
    fun_setFirstPage('#'+gridId);
    var queryParams = $('#' + gridId).datagrid('options').queryParams;
    queryParams.FValue = fValue;
    $('#' + gridId).datagrid('options').queryParams = queryParams;
    $('#' + gridId).datagrid('clearSelections');
    $('#' + gridId).datagrid('reload');
}

/*
 * 列表分页查询
 * gridId DataGrid ID
 * title 表名
 * fValue 查询值
 * */
var fun_MFFindEx1 = function (gridId, title, fValue) {
    fun_setFirstPage('#'+gridId);
    var queryParams = $('#' + gridId).datagrid('options').queryParams;
    queryParams.FValue = fValue;
    $('#' + gridId).datagrid('options').queryParams = queryParams;
    $('#' + gridId).datagrid("getPanel").panel("setTitle", title);
    $('#' + gridId).datagrid('clearSelections');
    $('#' + gridId).datagrid('reload');
}

/*
 * 列表分页查询
 * gridId DataGrid ID
 * title 表名
 * fValue 查询值
 * enabled true只显示有效
 * */
var fun_MFFindEx2 = function (gridId, title, fValue, enabled) {
    fun_setFirstPage('#'+gridId);
    var queryParams = $('#' + gridId).datagrid('options').queryParams;
    queryParams.FValue = fValue;
    queryParams.Enabled = enabled;
    $('#' + gridId).datagrid('options').queryParams = queryParams;
    $('#' + gridId).datagrid("getPanel").panel("setTitle", title);
    $('#' + gridId).datagrid('clearSelections');
    $('#' + gridId).datagrid('reload');
}

/*
 * 列表分页查询
 * gridId DataGrid ID
 * title 表名
 * fField 字段名
 * fValue 查询值
 * */
var fun_MFFindEx3 = function (gridId, title, fField, fValue) {
    fun_setFirstPage('#'+gridId);
    var queryParams = $('#' + gridId).datagrid('options').queryParams;
    queryParams.FField = fField;
    queryParams.FValue = fValue;
    $('#' + gridId).datagrid('options').queryParams = queryParams;
    $('#' + gridId).datagrid("getPanel").panel("setTitle", title);
    $('#' + gridId).datagrid('clearSelections');
    $('#' + gridId).datagrid('reload');
}


/*
 * 列表分页查询
 * gridId DataGrid ID
 * fName 字段名
 * fValue 查询值
 * */
var fun_JQFind = function (gridId, fName, fValue) {
    fun_setFirstPage('#'+gridId);
    var queryParams = $('#' + gridId).datagrid('options').queryParams;
    queryParams.FName = fName;
    queryParams.FValue = fValue;
    $('#' + gridId).datagrid('options').queryParams = queryParams;
    $('#' + gridId).datagrid('clearSelections');
    $('#' + gridId).datagrid('reload');
}


/*
 * 列表分页查询
 * gridId DataGrid ID
 * title DataGrid title
 * fName 字段名
 * fValue 查询值
 * */
var fun_JQFindEx1 = function (gridId, title, fName, fValue) {
    fun_setFirstPage('#'+gridId);
    var queryParams = $('#' + gridId).datagrid('options').queryParams;
    queryParams.FName = fName;
    queryParams.FValue = fValue;
    $('#' + gridId).datagrid('options').queryParams = queryParams;
    $('#' + gridId).datagrid("getPanel").panel("setTitle", title);
    $('#' + gridId).datagrid('clearSelections');
    $('#' + gridId).datagrid('reload');
}

/*
 * 列表分页查询
 * gridId DataGrid ID
 * title DataGrid title
 * fName 字段名
 * fValue 查询值
 * enabled true只显示有效
 * */
var fun_JQFindEx2 = function (gridId, title, fName, fValue, enabled) {
    fun_setFirstPage('#'+gridId);
    var queryParams = $('#' + gridId).datagrid('options').queryParams;
    queryParams.FName = fName;
    queryParams.FValue = fValue;
    queryParams.Enabled = enabled;
    $('#' + gridId).datagrid('options').queryParams = queryParams;
    $('#' + gridId).datagrid("getPanel").panel("setTitle", title);
    $('#' + gridId).datagrid('clearSelections');
    $('#' + gridId).datagrid('reload');
}

/*
 * 树列表页模糊查询
 * gridId DataGrid ID
 * fValue 查询值
 * */
var fun_treeMFFind = function (gridId, fValue) {
    var queryParams = $('#' + gridId).treegrid('options').queryParams;
    queryParams.FValue = fValue;
    $('#' + gridId).treegrid('options').queryParams = queryParams;
    $('#' + gridId).treegrid('clearSelections');
    $('#' + gridId).treegrid('reload');
}

/*
 * 树列表页模糊查询
 * gridId DataGrid ID
 * title DataGrid title
 * fValue 查询值
 * */
var fun_treeMFFindEx1 = function (gridId, title, fValue) {
    var queryParams = $('#' + gridId).treegrid('options').queryParams;
    queryParams.FValue = fValue;
    $('#' + gridId).treegrid('options').queryParams = queryParams;
    $('#' + gridId).datagrid("getPanel").panel("setTitle", title);
    $('#' + gridId).treegrid('clearSelections');
    $('#' + gridId).treegrid('reload');
}
/*
 * 树列表页模糊查询
 * gridId DataGrid ID
 * title DataGrid title
 * fValue 查询值
 * enabled true只显示有效
 * */
var fun_treeMFFindEx2 = function (gridId, title, fValue, enabled) {
    var queryParams = $('#' + gridId).treegrid('options').queryParams;
    queryParams.FValue = fValue;
    queryParams.Enabled = enabled;
    $('#' + gridId).treegrid('options').queryParams = queryParams;
    $('#' + gridId).datagrid("getPanel").panel("setTitle", title);
    $('#' + gridId).treegrid('clearSelections');
    $('#' + gridId).treegrid('reload');
}


/*
 * 树列表页模糊查询
 * gridId DataGrid ID
 * fName 字段名
 * fValue 查询值
 * */
var fun_treeJQFind = function (gridId, fName, fValue) {
    var queryParams = $('#' + gridId).treegrid('options').queryParams;
    queryParams.FName = fName;
    queryParams.FValue = fValue;
    $('#' + gridId).treegrid('options').queryParams = queryParams;
    $('#' + gridId).treegrid('clearSelections');
    $('#' + gridId).treegrid('reload');
}

/*
 * 树列表页模糊查询
 * gridId DataGrid ID
 * title DataGrid title
 * fName 字段名
 * fValue 查询值
 * enabled true只显示有效
 * */
var fun_treeJQFindEx1 = function (gridId, title, fName, fValue) {
    var queryParams = $('#' + gridId).treegrid('options').queryParams;
    queryParams.FName = fName;
    queryParams.FValue = fValue;
    $('#' + gridId).treegrid('options').queryParams = queryParams;
    $('#' + gridId).datagrid("getPanel").panel("setTitle", title);
    $('#' + gridId).treegrid('clearSelections');
    $('#' + gridId).treegrid('reload');
}
/*
 * 树列表页模糊查询
 * gridId DataGrid ID
 * title DataGrid title
 * fName 字段名
 * fValue 查询值
 * enabled true只显示有效
 * */
var fun_treeJQFindEx2 = function (gridId, title, fName, fValue, enabled) {
    var queryParams = $('#' + gridId).treegrid('options').queryParams;
    queryParams.FName = fName;
    queryParams.FValue = fValue;
    queryParams.Enabled = enabled;
    $('#' + gridId).treegrid('options').queryParams = queryParams;
    $('#' + gridId).datagrid("getPanel").panel("setTitle", title);
    $('#' + gridId).treegrid('clearSelections');
    $('#' + gridId).treegrid('reload');
}
</script>