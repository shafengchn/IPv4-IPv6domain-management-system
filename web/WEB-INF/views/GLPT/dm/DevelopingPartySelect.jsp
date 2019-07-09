<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-13
  Time: 上午11:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>选择开发方资料</title>
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
                                <th>关键字：</th>
                                <td>
                                    <input name="MFFindValue" id="txt_MFFindValue" class="txt" style="width: 150px;"/>
                                </td>
                                <td>
                                    <input id="btnSearch" type="button" class="btnSearch" value="搜 索"
                                           onclick="fun_DevelopingPartyFind();"/>
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
<div region="south" style="text-align: center; padding-top: 5px; height: 40px;">
    <a id="Accept" class="l-btn" onclick="fun_SelectDevelopingParty()" href="javascript:void(0)">
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
            url: '/GLPT/DM/DevelopingPartyFindPage.do?Enabled=true',
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
                    {field: 'FullName', title: '名称', width: 100, align: 'center'},
                    {field: 'ContactPerson', title: '联系人', width: 100, align: 'center'},
                    {field: 'QQ', title: 'QQ', width: 100, align: 'center'},
                    {field: 'EMail', title: '电子邮件', width: 100, align: 'center'},
                    {field: 'Phone', title: '电话号码', width: 100, align: 'center'},
                    {field: 'Mobile', title: '手机号码', width: 100, align: 'center'},
                    {field: 'Address', title: '联系地址', width: 100, align: 'center'},
                    {field: 'CooperationDate', title: '合作日期', width: 100, align: 'center'},
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
       // fun_DevelopingPartyFind();
        $("#tmpDiv").hide();
    });
    var fun_SelectDevelopingParty=function(){
        var row = $('#tt').datagrid('getSelected');
        if(row){
          /* if(GetQuery("CallType")=="Add"){
               top.DomainNameAdd.fun_SetDevelopingParty(row.Code,row.FullName);
           }else{
               top.DomainNameEdit.fun_SetDevelopingParty(row.Code,row.FullName);
           } */
            top.DomainName${CallType}.fun_SetDevelopingParty(row);
            OpenClose();
        }else{
            showFaceMsg("请选择要操作的记录");
        }
    }

    var fun_refresh=function(){
        $('#tt').datagrid('clearSelections');
        $('#tt').datagrid('reload');
    }

    var fun_DevelopingPartyFind=function(){
        var queryParams = $('#tt').datagrid('options').queryParams;
        //queryParams.Enabled = $('#Enabled').attr('checked');
        queryParams.fValue = $('#txt_MFFindValue').val();
        fun_CustomFind("tt","",queryParams);
    }
</script>
</body>
</html>