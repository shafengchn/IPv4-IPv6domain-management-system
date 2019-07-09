<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-13
  Time: 下午9:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>域名管理-修改</title>
    <jsp:include page="/Common/PageCss.jsp"></jsp:include>
    <jsp:include page="/Common/PageJs.jsp"></jsp:include>
</head>
<body class="easyui-layout">
<div region="center">
    <form id="ff" action="/GLPT/DM/DomainNameEditOK.do" method="post">
        <input type="hidden" id="Id" name="Id" value="${dm.id}"/>
        <table border="0" cellpadding="0" cellspacing="0" class="frm">
            <tr>
                <th>域名[<font face="宋体" color="red">*</font>]：</th>
                <td colspan="3">
                    <input name="DomainName" type="text" id="DomainName" maxlength="500" class="txt"
                           style="width: 545px" value="${dm.domainName}"/>
                </td>
            </tr>
            <tr>
                <th>访问地址[<font face="宋体" color="red">*</font>]：</th>
                <td colspan="3">
                    <input name="url" type="text" id="url" maxlength="500" class="txt"
                           style="width: 545px" value="${dm.url}"/>
                </td>
            </tr>
            <tr>
                <th>IPv4地址[<font face="宋体" color="red">*</font>]：</th>
                <td>
                    <input name="IPv4Address" type="text" id="IPv4Address" maxlength="50" class="txt"
                           style="width: 160px"  value="${dm.IPv4Address}" />
                    <input id="btn_SelectIPv4" type="button" value="..." onclick="fun_IPv4Select();"/>
                </td>
                <th>IPv6地址：</th>
                <td>
                    <input name="IPv6Address" type="text" id="IPv6Address" maxlength="50" class="txt"
                           style="width: 160px" value="${dm.IPv6Address}"/>
                    <input id="btn_SelectIPv6" type="button" value="..." onclick="fun_IPv6Select();"/>
                </td>
            </tr>
            <tr>
                <th>MAC地址：</th>
                <td>
                    <input name="MACAddress" type="text" id="MACAddress" maxlength="50" class="txt"
                           style="width: 200px" value="${dm.MACAddress}" />
                </td>
                <th>业务类型[<font face="宋体" color="red">*</font>]：</th>
                <td>
                    <input name="BusinessTypeCode" type="hidden" id="BusinessTypeCode" maxlength="50" value="${dm.businessTypeCode}"/>
                    <input name="BusinessTypeName" type="text" id="BusinessTypeName" maxlength="50" class="txt"
                           style="width: 160px" value="${BusinessTypeName}"/>
                    <input id="btn_SelectBusinessType" type="button" value="..." onclick="fun_BusinessTypeSelect();"/>
                </td>
            </tr>
            <tr>
                <th>网站、系统名称[<font face="宋体" color="red">*</font>]：</th>
                <td colspan="3">
                    <input name="WebSiteFullName" type="text" id="WebSiteFullName" maxlength="500" class="txt"
                           style="width: 545px" value="${dm.webSiteFullName}"/>
                </td>
            </tr>
            <tr>
                <th>开发方：</th>
                <td>
                    <input name="KFFCode" type="hidden" id="KFFCode" maxlength="50" value="${dm.KFFCode}"/>
                    <input name="KFFName" type="text" id="KFFName" maxlength="50" class="txt"
                           style="width: 160px" value="${KFFName}"/>
                    <input id="btn_SelectKFF" type="button" value="..." onclick="fun_DevelopingPartySelect();"/>
                </td>
                <th>管理部门：</th>
                <td>
                    <input class="easyui-combobox"
                           name="GLDepartmentCode"
                           id="GLDepartmentCode"
                           url="/GLPT/BASE/DepartmentGetDataEx.do?BMLXName=辅助教学"
                           valueField="Code"
                           textField="FullName" maxlength="50" style="width: 200px" value="${dm.GLDepartmentCode}"/>
                </td>
            </tr>
            <tr>
                <th>技术管理员类型：</th>
                <td>
                    <select id="GLYType" name="GLYType">
                        <option value="学校教师" selected="selected">学校教师</option>
                        <option value="外聘人员">外聘人员</option>
                    </select>
                </td>
                <th>技术管理员：</th>
                <td>
                    <input name="GLYCode" type="hidden" id="GLYCode" value="${dm.GLYCode}"/>
                    <input name="GLYName" type="text" id="GLYName" maxlength="50" class="txt"
                           style="width: 160px" value="${GLYName}"/>
                    <input id="btn_GLYCode" type="button" value="..." onclick="fun_GLYSelect();"/>
                </td>
            </tr>
            <tr>
                <th>负责人类型：</th>
                <td>
                    <select id="FZRType" name="FZRType">
                        <option value="学校教师" selected="selected">学校教师</option>
                        <option value="外聘人员">外聘人员</option>
                    </select>
                </td>
                <th>负责人：</th>
                <td>
                    <input name="FZRCode" type="hidden" id="FZRCode" maxlength="50" value="${dm.FZRCode}"/>
                    <input name="FZRName" type="text" id="FZRName" maxlength="50" class="txt"
                           style="width: 160px" value="${FZRName}"/>
                    <input id="btn_FZRCode" type="button" value="..." onclick="fun_FZRSelect();"/>
                </td>
            </tr>
            <tr>
                <th>签到间隔天数[<font face="宋体" color="red">*</font>]：</th>
                <td>
                    <input id="SignInInterval" class="easyui-numberspinner" style="width:80px;"  value="${dm.signInInterval}"
                           name="SignInInterval" min="1"/>
                </td>
                <th>续订间隔天数[<font face="宋体" color="red">*</font>]：</th>
                <td>
                    <input id="RenewInterval" class="easyui-numberspinner" style="width:80px;"
                           name="RenewInterval" min="1" value="${dm.renewInterval}"/>
                </td>
            </tr>
            <tr>
                <th>选项[<font face="宋体" color="red">*</font>]：</th>
                <td colspan="3">
            <span class="item">
                    <input id="Disable" type="checkbox" style="vertical-align: middle;" name="Disable">
                    <label style="vertical-align: middle;" for="Disable">停用</label>
            </span>
                </td>
            </tr>
            <tr>
                <th>备注：</th>
                <td colspan="3">
                    <textarea id="Remarks" class="txtArea" style="width: 545px" rows="6" type="text" maxlength="200"
                              name="Remarks">${dm.remarks}</textarea>
                </td>
            </tr>
        </table>
    </form>
</div>
<div region="south" style="text-align: center; padding-top: 5px; height: 40px;">
    <a id="Accept" class="l-btn" onclick="fun_submit()" href="javascript:void(0)">
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
    $(function(){
        $("#GLYType").val("${dm.GLYType}");
        $("#FZRType").val("${dm.FZRType}");

        $("#Disable").attr("checked",${dm.disable});
    });
    var fun_submit = function () {
        var win = $.messager.progress({title: '请稍等...', msg: '保存中...'});
        var formData = $("#ff").serialize();
        $.ajax({
            type: "POST",
            url: "/GLPT/DM/DomainNameEditOK.do",
            processData: true,
            dataType: "json",
            data: formData,
            success: function (data) {
                $.messager.progress('close');
                if (data.success == true) {
                    var p1 = parent.document.getElementById("${IframeId}");
                    if (p1 != null) {
                        p1.contentWindow.fun_refresh();
                    }
                    OpenClose();
                } else {
                    showFaceMsg(data.info);
                }
            }
        });
    }

    var fun_IPv4Select=function(){
        var url="/GLPT/DM/IPv4Select.do?CallType=Edit&IframeId=${IframeId}";
        top.openDialog(url, 'IPv4Select', '选择IPv4地址', 600, 300, 50, 50);
    }
    var fun_SetIPv4=function(IPv4Address){
        $("#IPv4Address").val(IPv4Address);
    }

    var fun_IPv6Select=function(){
        var url="/GLPT/DM/IPv6Select.do?CallType=Edit&IframeId=${IframeId}";
        top.openDialog(url, 'IPv6Select', '选择IPv6地址', 600, 300, 50, 50);
    }
    var fun_SetIPv6=function(IPv6Address){
        $("#IPv6Address").val(IPv6Address);
    }
    var fun_BusinessTypeSelect=function(){
        var url="/GLPT/DM/BusinessTypeSelect.do?CallType=Edit&IframeId=${IframeId}";
        top.openDialog(url, 'BusinessTypeSelect', '选择业务类型', 600, 300, 50, 50);
    }
    var fun_SetBusinessType=function(BusinessTypeCode,BusinessTypeName){
        $("#BusinessTypeCode").val(BusinessTypeCode);
        $("#BusinessTypeName").val(BusinessTypeName);
    }
    var fun_DevelopingPartySelect=function(){
        var url="/GLPT/DM/DevelopingPartySelect.do?CallType=Edit&IframeId=${IframeId}";
        top.openDialog(url, 'DevelopingPartySelect', '选择开发方资料', 600, 300, 50, 50);
    }
    var fun_SetDevelopingParty=function(DevelopingPartyCode,DevelopingPartyName){
        $("#KFFCode").val(DevelopingPartyCode);
        $("#KFFName").val(DevelopingPartyName);
    }
    var fun_GLYSelect=function(){
        if($("#GLYType").val()=="外聘人员"){
        var url="/GLPT/DM/ExternalPersonnelSelect.do?CallModule=GLY&CallType=Edit&IframeId=${IframeId}";
        top.openDialog(url, 'ExternalPersonnelSelect', '选择外聘人员资料', 600, 300, 50, 50);
        }else{
            var url="/GLPT/BASE/BorrowerSelect.do?CallModule=GLY&CallType=Edit&IframeId=${IframeId}";
            top.openDialog(url, 'BorrowerSelect', '选择学校教师资料', 600, 300, 50, 50);
        }

    }
    var fun_SetGLY=function(GLYCode,GLYName){
        $("#GLYCode").val(GLYCode);
        $("#GLYName").val(GLYName);
    }
    var fun_FZRSelect=function(){
        if($("#FZRType").val()=="外聘人员"){
        var url="/GLPT/DM/ExternalPersonnelSelect.do?CallModule=FZR&CallType=Edit&IframeId=${IframeId}";
        top.openDialog(url, 'ExternalPersonnelSelect', '选择外聘人员资料', 600, 300, 50, 50);
        }else{
            var url="/GLPT/BASE/BorrowerSelect.do?CallModule=FZR&CallType=Edit&IframeId=${IframeId}";
            top.openDialog(url, 'BorrowerSelect', '选择学校教师资料', 600, 300, 50, 50);
        }

    }
    var fun_SetFZR=function(FZRCode,FZRName){
        $("#FZRCode").val(FZRCode);
        $("#FZRName").val(FZRName);
    }
</script>
</body>
</html>