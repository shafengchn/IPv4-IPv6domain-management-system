<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-13
  Time: 上午11:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>选择IPv4地址</title>
    <jsp:include page="/Common/PageCss.jsp"></jsp:include>
    <jsp:include page="/Common/PageJs.jsp"></jsp:include>
</head>
<body class="easyui-layout">
<div id="tmpDiv" style="height: 1000px;"></div>
<div id="tb" style="height: 48px;">
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
                                        <option value="null" >全部</option>
                                        <option value="true">已使用</option>
                                        <option value="false" selected="selected">未使用</option>
                                    </select>
                                </td>
                                <th>关键字：</th>
                                <td>
                                    <input name="MFFindValue" id="txt_MFFindValue" class="txt" style="width: 150px;"/>
                                </td>
                                <td>
                                    <input id="btnSearch" type="button" class="btnSearch" value="搜 索"
                                           onclick="fun_IPv4Find();"/>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                </td>
               <!-- <td>
                       <span class="item">
                                <input name="Enabled" type="checkbox" id="Enabled" style="vertical-align: middle;">
                                <label for="Enabled" style="vertical-align: middle;">只显示有效的</label>
                                &nbsp;&nbsp;
                       </span>
                </td> -->
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div id="mainPanel" region="center" style=" overflow-y:hidden">
    <table id="tt"></table>
</div>
<div region="south" style="text-align: center; padding-top: 5px; height: 40px;">
    <a id="Accept" class="l-btn" onclick="fun_SelectIPv4()" href="javascript:void(0)">
        <span class="l-btn-left">
                <img alt="" src="/skins/Default/images/16/accept.png">
                     确定
        </span>
    </a>
    <a class="l-btn" onclick="OpenClose();" href="javascript:void(0)">
        <span class="l-btn-left">
                <img alt="" src="/skins/Default/images/16/cancel.png">
                    关 闭
        </span>
    </a>
</div>
<script type="text/javascript">
    $(function () {
        $('#tt').datagrid({
            fit: true,
            nowrap: false,
            striped: true,
            url: '/GLPT/DM/IPv4FindPage.do?Enabled=true',
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
        fun_IPv4Find();
        $("#tmpDiv").hide();
    });
    var fun_SelectIPv4=function(){
        var row = $('#tt').datagrid('getSelections');
        if(row){
            var tmpStr1="";
            for(var i=0;i<row.length;i++){
                tmpStr1+=row[i].Address;
                if(i<row.length-1){
                    tmpStr1+="|";
                }
            }
           if(GetQuery("CallType")=="Add"){
               top.DomainNameAdd.fun_SetIPv4(tmpStr1);
           }else{
               top.DomainNameEdit.fun_SetIPv4(tmpStr1);
           }
            OpenClose();
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }

    var fun_refresh=function(){
        $('#tt').datagrid('clearSelections');
        $('#tt').datagrid('reload');
    }

    var fun_IPv4Find=function(){
        var queryParams=$('#tt').datagrid('options').queryParams;
        //queryParams.Enabled=$('#Enabled').attr('checked');
        queryParams.fValue=$('#txt_MFFindValue').val();
        queryParams.State=$("#state").val();
        fun_CustomFind("tt","",queryParams);
    }
</script>
</body>
</html>