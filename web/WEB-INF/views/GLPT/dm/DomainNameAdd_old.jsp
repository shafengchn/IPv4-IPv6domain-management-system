<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-13
  Time: 下午9:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>域名管理-添加</title>
    <jsp:include page="/Common/PageCss.jsp"></jsp:include>
    <jsp:include page="/Common/PageJs.jsp"></jsp:include>
</head>
<body class="easyui-layout">
<div region="center">
<div id="tt" class="easyui-tabs" fit="true">
<div title="域名资料" style="overflow:auto;">
    <form id="ff" action="/GLPT/DM/DomainNameAddOK.do" method="post">
        <input type="hidden" name="Id" id="Id" value="${Id}"/>
        <input name="WebSiteFullName" type="hidden" id="WebSiteFullName"/>
        <input name="SysFLCode" type="hidden" id="SysFLCode"/>
        <input name="SysFLChildCode" type="hidden" id="SysFLChildCode"/>
        <input name="KFFCode" type="hidden" id="KFFCode"/>
        <input name="OpenDate" type="hidden" id="OpenDate"/>
        <input name="SysRemarks" type="hidden" id="SysRemarks"/>
        <input name="GLYCode" type="hidden" id="GLYCode"/>
        <input name="GLYName" type="hidden" id="GLYName"/>
        <input name="GLYMobile" type="hidden" id="GLYMobile"/>
        <input name="GLYPhone" type="hidden" id="GLYPhone"/>
        <input name="GLYEmail" type="hidden" id="GLYEmail"/>
        <input type="hidden" name="GLYIdType" id="GLYIdType"/>
        <input type="hidden" name="GLYIdNumber" id="GLYIdNumber"/>
        <input type="hidden" name="GLYIdImgPath1" id="GLYIdImgPath1"/>
        <input type="hidden" name="GLYIdImgPath2" id="GLYIdImgPath2"/>
        <input type="hidden" name="GLYIdImgPath3" id="GLYIdImgPath3"/>
        <input name="ECPCode" type="hidden" id="ECPCode"/>
        <input name="ECPName" type="hidden" id="ECPName"/>
        <input name="ECPMobile" type="hidden" id="ECPMobile"/>
        <input name="ECPPhone" type="hidden" id="ECPPhone"/>
        <input name="ECPEmail" type="hidden" id="ECPEmail"/>
        <input type="hidden" name="ECPIdType" id="ECPIdType"/>
        <input type="hidden" name="ECPIdNumber" id="ECPIdNumber"/>
        <input type="hidden" name="ECPIdImgPath1" id="ECPIdImgPath1"/>
        <input type="hidden" name="ECPIdImgPath2" id="ECPIdImgPath2"/>
        <input type="hidden" name="ECPIdImgPath3" id="ECPIdImgPath3"/>
        <input name="FZRCode" type="hidden" id="FZRCode"/>
        <input name="FZRName" type="hidden" id="FZRName"/>
        <input name="FZRMobile" type="hidden" id="FZRMobile"/>
        <input name="FZRPhone" type="hidden" id="FZRPhone"/>
        <input name="FZREmail" type="hidden" id="FZREmail"/>
        <input name="ApprovalCode" type="hidden" id="ApprovalCode"/>
        <input name="ApprovalName" type="hidden" id="ApprovalName"/>
        <input name="ApprovalMobile" type="hidden" id="ApprovalMobile"/>
        <input name="ApprovalPhone" type="hidden" id="ApprovalPhone"/>
        <input name="ApprovalEmail" type="hidden" id="ApprovalEmail"/>
        <table border="0" cellpadding="0" cellspacing="0" class="frm">
            <tr>
                <th>域名[<font face="宋体" color="red">*</font>]：</th>
                <td colspan="3">
                    <input name="DomainName" type="text" id="DomainName" maxlength="500" class="txt"
                           style="width: 450px"/>
                    <span class="item">
                    <input id="NoDomain" type="checkbox" style="vertical-align: middle;" name="NoDomain">
                    <label style="vertical-align: middle;" for="NoDomain">无域名系统</label>
            </span>
                </td>
            </tr>
            <tr>
                <th>访问地址[<font face="宋体" color="red">*</font>]：</th>
                <td colspan="3">
                    <input name="url" type="text" id="url" maxlength="500" class="txt" value="http://"
                           style="width: 545px"/>
                </td>
            </tr>
            <tr>
                <th>IPv4地址[<font face="宋体" color="red">*</font>]：</th>
                <td colspan="3">
                    <input name="IPv4Address" type="text" id="IPv4Address" maxlength="50" class="txt"
                           style="width: 500px"/>
                    <a href="#" onclick="fun_IPv4Select()" style="color: blue;">请选择</a>

                    <div style="color: red;">注：手动填写时请用“|”分隔</div>
                </td>
            </tr>
            <tr>
                <th>IPv6地址：</th>
                <td colspan="3">
                    <input name="IPv6Address" type="text" id="IPv6Address" maxlength="50" class="txt"
                           style="width: 500px"/>
                    <a href="#" onclick="fun_IPv6Select()" style="color: blue;">请选择</a>

                    <div style="color: red;">注：手动填写时请用“|”分隔</div>
                </td>
            </tr>
            <tr>
                <th>TTL：</th>
                <td>
                    <input id="TTL" class="easyui-numberspinner" style="width:80px;" value="3600"
                           name="TTL" min="0"/>
                </td>
                <th>记录类型：</th>
                <td>
                    <input class="easyui-combobox" name="RecordType" id="RecordType" maxlength="50"
                           style="width: 200px"/>
                </td>
            </tr>
            <tr>
                <th>记录值：</th>
                <td colspan="3">
                    <input name="RecordVal" type="text" id="RecordVal" maxlength="500" class="txt" style="width: 545px"/>
                </td>
            </tr>
            <tr>
                <th>管理部门：</th>
                <td>
                    <input class="easyui-combobox" name="GLDepartmentCode" id="GLDepartmentCode" maxlength="50"
                           style="width: 200px"/>
                </td>
                <th>选项[<font face="宋体" color="red">*</font>]：</th>
                <td>
            <span class="item">
                    <input id="Disable" type="checkbox" style="vertical-align: middle;" name="Disable">
                    <label style="vertical-align: middle;" for="Disable">停用</label>
            </span>
                </td>
              <!--  <th>业务类型[<font face="宋体" color="red">*</font>]：</th>
                <td>
                    <input class="easyui-combobox"
                           name="BusinessTypeCode"
                           id="BusinessTypeCode" maxlength="50" style="width: 200px"/>
                </td> -->
            </tr>
            <tr>
                <th>签到间隔天数[<font face="宋体" color="red">*</font>]：</th>
                <td>
                    <input id="SignInInterval" class="easyui-numberspinner" style="width:80px;" value="90"
                           name="SignInInterval" min="1"/>
                </td>
                <th>续订间隔天数[<font face="宋体" color="red">*</font>]：</th>
                <td>
                    <input id="RenewInterval" class="easyui-numberspinner" style="width:80px;"
                           name="RenewInterval" min="1" value="90"/>
                </td>
            </tr>
            <tr>

            </tr>
            <tr>
                <th>备注：</th>
                <td colspan="3">
                    <textarea id="Remarks" class="txtArea" style="width: 545px" rows="6" type="text"
                              maxlength="200"
                              name="Remarks"></textarea>
                </td>
            </tr>
        </table>
    </form>
</div>

<div title="网站、系统信息" style="overflow:auto;">
    <table border="0" cellpadding="0" cellspacing="0" class="frm">
        <tr>
            <th>名称：</th>
            <td><input name="WebSiteFullName" type="text" id="tmp_WebSiteFullName" maxlength="500" class="txt"
                       style="width: 545px"/></td>
        </tr>
        <tr>
            <th>分类：</th>
            <td><input class="easyui-combobox" name="SysFLCode" id="tmp_SysFLCode" maxlength="50" style="width: 200px"/>
                <input class="easyui-combobox" name="SysFLChildCode" id="tmp_SysFLChildCode" maxlength="50"
                       style="width: 290px"/></td>
        </tr>
        <tr>
            <th>开通日期：</th>
            <td><input id="tmp_OpenDate" name="OpenDate" type="text" class="easyui-datebox" style="width: 200px;"/></td>
        </tr>
        <tr>
            <th>备注：</th>
            <td>
                <textarea id="tmp_SysRemarks" class="txtArea" style="width: 545px" rows="6" type="text" maxlength="200"
                          name="SysRemarks"></textarea>
            </td>
        </tr>
    </table>
</div>

<div title="开发方" style="overflow:auto;">
    <table border="0" cellpadding="0" cellspacing="0" class="frm">
        <tr>
            <th>开发方：</th>
            <td><span id="div_KFFName"></span>
                <a href="#" onclick="fun_DevelopingPartySelect()" style="color: blue;">请选择</a>
                <a href="#" onclick="fun_DevelopingPartyAdd()" style="color: blue;">新建</a>
            </td>
        </tr>
        <tr>
            <th>联系人：</th>
            <td><span id="div_ContactPerson"></span></td>
        </tr>
        <tr>
            <th>QQ：</th>
            <td><span id="div_QQ"></span></td>
        </tr>
        <tr>
            <th>EMail：</th>
            <td><span id="div_EMail"></span></td>
        </tr>
        <tr>
            <th>电话号码：</th>
            <td><span id="div_Phone"></span> </td>
        </tr>
        <tr>
            <th>手机号码：</th>
            <td><span id="div_Mobile"></span></td>
        </tr>
        <tr>
            <th>公司地址：</th>
            <td><span id="div_Address"></span></td>
        </tr>
        <tr>
            <th>合作日期：</th>
            <td><span id="div_CooperationDate"></span></td>
        </tr>
        <tr>
            <th>备注：</th>
            <td><span id="div_KFFRemarks"></span></td>
        </tr>
    </table>
</div>
<div title="管理员" style="overflow:auto;">
    <table border="0" cellpadding="0" cellspacing="0" class="frm">
        <tr>
            <th>姓名：</th>
            <td>
                <span id="div_GLYName"></span>
                <a href="#" onclick="fun_GLYSelect()" style="color: blue;">请选择</a>
            </td>
        </tr>
        <tr>
            <th>手机号码：</th>
            <td><input name="GLYMobile" type="text" id="tmp_GLYMobile" maxlength="50" class="txt"
                       style="width: 220px"/></td>
        </tr>
        <tr>
            <th>电话号码：</th>
            <td><input name="GLYPhone" type="text" id="tmp_GLYPhone" maxlength="50" class="txt"
                       style="width: 220px"/></td>
        </tr>
        <tr>
            <th>EMAIL：</th>
            <td><input name="GLYEmail" type="text" id="tmp_GLYEmail" maxlength="50" class="txt"
                       style="width: 220px"/></td>
        </tr>
        <tr>
            <th>证件类型：</th>
            <td>
                <input class="easyui-combobox" name="GLYIdType" id="tmp_GLYIdType" maxlength="50" style="width: 220px"/>
            </td>
        </tr>
        <tr>
            <th>证件号：</th>
            <td><input name="GLYIdNumber" type="text" id="tmp_GLYIdNumber" maxlength="50" class="txt"
                       style="width: 220px"/></td>
        </tr>
        <tr>
            <th>证件（正面）：</th>
            <td>
                <iframe id="iframe_fileUpload_GLYIdImgPath1" width="100%" height="150px"
                        frameborder="0"
                        src="/COMMON/fileUploadImgEx.do?filePath=dm_attrFile&imgShow=true&imgHeight=100&imgWidth=100"
                        scrolling="no"
                        name="iframe_fileUpload_GLYIdImgPath1"></iframe>
            </td>
        </tr>
        <tr>
            <th>证件（反面）：</th>
            <td>
                <iframe id="iframe_fileUpload_GLYIdImgPath2" width="100%" height="150px"
                        frameborder="0"
                        src="/COMMON/fileUploadImgEx.do?filePath=dm_attrFile&imgShow=true&imgHeight=100&imgWidth=100"
                        scrolling="no"
                        name="iframe_fileUpload_GLYIdImgPath2"></iframe>
            </td>
        </tr>
        <tr>
            <th>证件（手持）：</th>
            <td>
                <iframe id="iframe_fileUpload_GLYIdImgPath3" width="100%" height="150px"
                        frameborder="0"
                        src="/COMMON/fileUploadImgEx.do?filePath=dm_attrFile&imgShow=true&imgHeight=100&imgWidth=100"
                        scrolling="no"
                        name="iframe_fileUpload_GLYIdImgPath3"></iframe>
            </td>
        </tr>
    </table>
</div>

<div title="应急联络人" style="overflow:auto;">
    <table border="0" cellpadding="0" cellspacing="0" class="frm">
        <tr>
            <th>姓名：</th>
            <td>
                <span id="div_ECPName"></span>
                <a href="#" onclick="fun_ECPSelect()" style="color: blue;">请选择</a>
            </td>
        </tr>
        <tr>
            <th>手机号码：</th>
            <td><input name="ECPMobile" type="text" id="tmp_ECPMobile" maxlength="50" class="txt"
                       style="width: 220px"/></td>
        </tr>
        <tr>
            <th>电话号码：</th>
            <td><input name="ECPPhone" type="text" id="tmp_ECPPhone" maxlength="50" class="txt"
                       style="width: 220px"/></td>
        </tr>
        <tr>
            <th>EMAIL：</th>
            <td><input name="ECPEmail" type="text" id="tmp_ECPEmail" maxlength="50" class="txt"
                       style="width: 220px"/></td>
        </tr>
        <tr>
            <th>证件类型：</th>
            <td><input class="easyui-combobox" name="ECPIdType" id="tmp_ECPIdType" maxlength="50" style="width: 220px"/>
            </td>
        </tr>
        <tr>
            <th>证件号：</th>
            <td><input name="ECPIdNumber" type="text" id="tmp_ECPIdNumber" maxlength="50" class="txt"
                       style="width: 220px"/></td>
        </tr>
        <tr>
            <th>证件（正面）：</th>
            <td>
                <iframe id="iframe_fileUpload_ECPIdImgPath1" width="100%" height="150px"
                        frameborder="0"
                        src="/COMMON/fileUploadImgEx.do?filePath=dm_attrFile&imgShow=true&imgHeight=100&imgWidth=100"
                        scrolling="no"
                        name="iframe_fileUpload_ECPIdImgPath1"></iframe>
            </td>
        </tr>
        <tr>
            <th>证件（反面）：</th>
            <td>
                <iframe id="iframe_fileUpload_ECPIdImgPath2" width="100%" height="150px"
                        frameborder="0"
                        src="/COMMON/fileUploadImgEx.do?filePath=dm_attrFile&imgShow=true&imgHeight=100&imgWidth=100"
                        scrolling="no"
                        name="iframe_fileUpload_ECPIdImgPath2"></iframe>
            </td>
        </tr>
        <tr>
            <th>证件（手持）：</th>
            <td>
                <iframe id="iframe_fileUpload_ECPIdImgPath3" width="100%" height="150px"
                        frameborder="0"
                        src="/COMMON/fileUploadImgEx.do?filePath=dm_attrFile&imgShow=true&imgHeight=100&imgWidth=100"
                        scrolling="no"
                        name="iframe_fileUpload_ECPIdImgPath3"></iframe>
            </td>
        </tr>
    </table>
</div>
<div title="信息审批员" style="overflow:auto;">
    <table border="0" cellpadding="0" cellspacing="0" class="frm">
        <tr>
            <th>姓名：</th>
            <td>
                <span id="div_ApprovalName"></span>
                <a href="#" onclick="fun_ApprovalSelect()" style="color: blue;">请选择</a>
            </td>
        </tr>
        <tr>
            <th>手机号码：</th>
            <td><input name="ApprovalMobile" type="text" id="tmp_ApprovalMobile" maxlength="50" class="txt"
                       style="width: 220px"/></td>
        </tr>
        <tr>
            <th>电话号码：</th>
            <td><input name="ApprovalPhone" type="text" id="tmp_ApprovalPhone" maxlength="50" class="txt"
                       style="width: 220px"/></td>
        </tr>
        <tr>
            <th>EMAIL：</th>
            <td><input name="ApprovalEmail" type="text" id="tmp_ApprovalEmail" maxlength="50" class="txt"
                       style="width: 220px"/></td>
        </tr>
    </table>
</div>
<div title="部门领导" style="overflow:auto;">
    <table border="0" cellpadding="0" cellspacing="0" class="frm">
        <tr>
            <th>姓名：</th>
            <td>
                <span id="div_FZRName"></span>
                <a href="#" onclick="fun_FZRSelect()" style="color: blue;">请选择</a>
            </td>
        </tr>
        <tr>
            <th>手机号码：</th>
            <td><input name="FZRMobile" type="text" id="tmp_FZRMobile" maxlength="50" class="txt"
                       style="width: 220px"/></td>
        </tr>
        <tr>
            <th>电话号码：</th>
            <td><input name="FZRPhone" type="text" id="tmp_FZRPhone" maxlength="50" class="txt"
                       style="width: 220px"/></td>
        </tr>
        <tr>
            <th>EMAIL：</th>
            <td><input name="FZREmail" type="text" id="tmp_FZREmail" maxlength="50" class="txt"
                       style="width: 220px"/></td>
        </tr>
    </table>
</div>

</div>


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
    $(function () {
        $("#NoDomain").change(function () {
            if ($("#NoDomain").prop("checked")) {
                $("#DomainName").val("");
                $("#DomainName").attr("disabled", 'true');
            } else {
                $("#DomainName").removeAttr("disabled");
            }
        });
        $("#GLDepartmentCode").combobox({
            url: "/GLPT/BASE/DepartmentGetDataEx.do?BMLXName=辅助教学",
            valueField: 'Code',
            textField: 'FullName',
            onLoadSuccess: function (data) {
                if (data) {
                    $("#GLDepartmentCode").combobox('setValue', data[0].Code);
                }
            }
        });
        $("#RecordType").combobox({
            url: '/GLPT/CORE/ItemDetailsFindDataEx.do?ItemsCode=RecordType',
            valueField: 'Code',
            textField: 'FullName',
            onLoadSuccess: function (data) {
                if (data) {
                    $("#RecordType").combobox('setValue', data[0].Code);
                }
            }
        });

       /* $("#BusinessTypeCode").combobox({
            url: '/GLPT/DM/BusinessTypeGetDataEx.do',
            valueField: 'Code',
            textField: 'FullName',
            onLoadSuccess: function (data) {
                if (data) {
                    $("#BusinessTypeCode").combobox('setValue', data[0].Code);
                }
            }
        }); */
        $("#tmp_GLYIdType").combobox({
            url: '/GLPT/CORE/ItemDetailsFindDataEx.do?ItemsCode=IdType',
            valueField: 'Code',
            textField: 'FullName',
            onLoadSuccess: function (data) {
                if (data) {
                    $("#tmp_GLYIdType").combobox('setValue', data[0].Code);
                }
            }
        });
        $("#tmp_ECPIdType").combobox({
            url: '/GLPT/CORE/ItemDetailsFindDataEx.do?ItemsCode=IdType',
            valueField: 'Code',
            textField: 'FullName',
            onLoadSuccess: function (data) {
                if (data) {
                    $("#tmp_ECPIdType").combobox('setValue', data[0].Code);
                }
            }
        });
        $("#tmp_SysFLCode").combobox({
            url: "/GLPT/CORE/ItemDetailsFindDataEx.do?ItemsCode=SYSFL",
            valueField: 'Code',
            textField: 'FullName',
            onSelect: function (record) {
                $("#tmp_SysFLChildCode").combobox({
                    url: "/GLPT/CORE/ItemDetailsFindDataEx.do?ItemsCode=" + record.Code,
                    valueField: 'Code',
                    textField: 'FullName',
                    onLoadSuccess: function (data) {
                        if (data) {
                            $("#tmp_SysFLChildCode").combobox('setValue', data[0].Code);
                        }
                    }
                });
            }
        });

    });
    var fun_submit = function () {
        var win = $.messager.progress({title: '请稍等...', msg: '添加中...'});
        $("#WebSiteFullName").val($('#tmp_WebSiteFullName').val());
        $("#SysFLCode").val($('#tmp_SysFLCode').combobox('getValues'));
        $("#SysFLChildCode").val($('#tmp_SysFLChildCode').combobox('getValues'));
        $("#KFFCode").val($('#tmp_KFFCode').val());
        $("#OpenDate").val($('#tmp_OpenDate').val());
        $("#SysRemarks").val($('#tmp_SysRemarks').val());
        $("#GLYIdType").val($('#tmp_GLYIdType').combobox('getValues'));
        $("#GLYIdNumber").val($('#tmp_GLYIdNumber').val());
        var iframe1 = document.getElementById("iframe_fileUpload_GLYIdImgPath1");
        if (iframe1 != null) {
            $("#GLYIdImgPath1").val(iframe1.contentWindow.fun_GetFileName());
        }
        var iframe2 = document.getElementById("iframe_fileUpload_GLYIdImgPath2");
        if (iframe2 != null) {
            $("#GLYIdImgPath2").val(iframe2.contentWindow.fun_GetFileName());
        }
        var iframe3 = document.getElementById("iframe_fileUpload_GLYIdImgPath3");
        if (iframe3 != null) {
            $("#GLYIdImgPath3").val(iframe3.contentWindow.fun_GetFileName());
        }
        $("#GLYMobile").val($('#tmp_GLYMobile').val());
        $("#GLYPhone").val($('#tmp_GLYPhone').val());
        $("#GLYEmail").val($('#tmp_GLYEmail').val());
        $("#FZRMobile").val($('#tmp_FZRMobile').val());
        $("#ECPIdType").val($('#tmp_ECPIdType').combobox('getValues'));
        $("#ECPIdNumber").val($('#tmp_ECPIdNumber').val());
        var iframe11 = document.getElementById("iframe_fileUpload_ECPIdImgPath1");
        if (iframe11 != null) {
            $("#ECPIdImgPath1").val(iframe11.contentWindow.fun_GetFileName());
        }
        var iframe12 = document.getElementById("iframe_fileUpload_ECPIdImgPath2");
        if (iframe12 != null) {
            $("#ECPIdImgPath2").val(iframe12.contentWindow.fun_GetFileName());
        }
        var iframe13 = document.getElementById("iframe_fileUpload_ECPIdImgPath3");
        if (iframe13 != null) {
            $("#ECPIdImgPath3").val(iframe13.contentWindow.fun_GetFileName());
        }
        $("#ECPMobile").val($('#tmp_ECPMobile').val());
        $("#ECPPhone").val($('#tmp_ECPPhone').val());
        $("#ECPEmail").val($('#tmp_ECPEmail').val());
        $("#FZRPhone").val($('#tmp_FZRPhone').val());
        $("#FZREmail").val($('#tmp_FZREmail').val());
        $("#ApprovalMobile").val($('#tmp_ApprovalMobile').val());
        $("#ApprovalPhone").val($('#tmp_ApprovalPhone').val());
        $("#ApprovalEmail").val($('#tmp_ApprovalEmail').val());
        var formData = $("#ff").serialize();
        $.ajax({
            type: "POST",
            url: "/GLPT/DM/DomainNameAddOK.do",
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

    var fun_IPv4Select = function () {
        var url = "/GLPT/DM/IPv4Select.do?CallType=Add&IframeId=${IframeId}";
        top.openDialog(url, 'IPv4Select', '选择IPv4地址', 600, 300, 50, 50);
    }
    var fun_SetIPv4 = function (IPv4Address) {
        $("#IPv4Address").val(IPv4Address);
    }

    var fun_IPv6Select = function () {
        var url = "/GLPT/DM/IPv6Select.do?CallType=Add&IframeId=${IframeId}";
        top.openDialog(url, 'IPv6Select', '选择IPv6地址', 600, 300, 50, 50);
    }
    var fun_SetIPv6 = function (IPv6Address) {
        $("#IPv6Address").val(IPv6Address);
    }
    var fun_BusinessTypeSelect = function () {
        var url = "/GLPT/DM/BusinessTypeSelect.do?CallType=Add&IframeId=${IframeId}";
        top.openDialog(url, 'BusinessTypeSelect', '选择业务类型', 600, 300, 50, 50);
    }
    var fun_SetBusinessType = function (BusinessTypeCode, BusinessTypeName) {
        $("#BusinessTypeCode").val(BusinessTypeCode);
        $("#BusinessTypeName").val(BusinessTypeName);
    }
    var fun_DevelopingPartySelect = function () {
        var url = "/GLPT/DM/DevelopingPartySelect.do?CallType=Add&IframeId=${IframeId}";
        top.openDialog(url, 'DevelopingPartySelect', '选择开发方资料', 600, 300, 50, 50);
    }
    var fun_SetDevelopingParty = function (KKFData) {
        $("#KFFCode").val(KKFData.Code);
        $("#div_KFFName").html(KKFData.FullName);
        $("#div_ContactPerson").html(KKFData.ContactPerson);
        $("#div_QQ").html(KKFData.QQ);
        $("#div_EMail").html(KKFData.EMail);
        $("#div_Phone").html(KKFData.Phone);
        $("#div_Mobile").html(KKFData.Mobile);
        $("#div_Address").html(KKFData.Address);
        $("#div_CooperationDate").html(KKFData.CooperationDate);
        $("#div_KFFRemarks").html(KKFData.Remarks);
    }

    var fun_DevelopingPartyAdd=function(){
        var url="/GLPT/DM/DevelopingPartyAdd.do?CallType=Add&IframeId=${IframeId}";
        top.openDialog(url, 'DevelopingPartyAdd', '新建开发方资料', 600, 350, 50, 50);
    }
    var fun_GLYSelect = function () {
        var url = "/GLPT/BASE/BorrowerSelect.do?CallModule=GLY&CallType=Add&IframeId=${IframeId}";
        top.openDialog(url, 'BorrowerSelect', '选择学校教师资料', 600, 300, 50, 50);
    }
    var fun_SetGLY = function (GLYCode, GLYName, GLYMobile, GLYPhone, GLYEmail) {
        $("#GLYCode").val(GLYCode);
        $("input[name='GLYName']").val(GLYName);
        $("input[name='GLYMobile']").val(GLYMobile);
        $("input[name='GLYPhone']").val(GLYPhone);
        $("input[name='GLYEmail']").val(GLYEmail);
        $("#div_GLYName").html(GLYName);
    }

    var fun_ECPSelect = function () {
        var url = "/GLPT/BASE/BorrowerSelect.do?CallModule=ECP&CallType=Add&IframeId=${IframeId}";
        top.openDialog(url, 'BorrowerSelect', '选择学校教师资料', 600, 300, 50, 50);
    }
    var fun_SetECP = function (ECPCode, ECPName, ECPMobile, ECPPhone, ECPEmail) {
        $("#ECPCode").val(ECPCode);
        $("input[name='ECPName']").val(ECPName);
        $("input[name='ECPMobile']").val(ECPMobile);
        $("input[name='ECPPhone']").val(ECPPhone);
        $("input[name='ECPEmail']").val(ECPEmail);
        $("#div_ECPName").html(ECPName);
    }

    var fun_ApprovalSelect = function () {
        var url = "/GLPT/BASE/BorrowerSelect.do?CallModule=Approval&CallType=Add&IframeId=${IframeId}";
        top.openDialog(url, 'BorrowerSelect', '选择学校教师资料', 600, 300, 50, 50);
    }
    var fun_SetApproval = function (ApprovalCode, ApprovalName, ApprovalMobile, ApprovalPhone, ApprovalEmail) {
        $("#ApprovalCode").val(ApprovalCode);
        $("input[name='ApprovalName']").val(ApprovalName);
        $("input[name='ApprovalMobile']").val(ApprovalMobile);
        $("input[name='ApprovalPhone']").val(ApprovalPhone);
        $("input[name='ApprovalEmail']").val(ApprovalEmail);
        $("#div_ApprovalName").html(ApprovalName);
    }
    var fun_FZRSelect = function () {
        var url = "/GLPT/BASE/BorrowerSelect.do?CallModule=FZR&CallType=Add&IframeId=${IframeId}";
        top.openDialog(url, 'BorrowerSelect', '选择学校教师资料', 600, 300, 50, 50);
    }
    var fun_SetFZR = function (FZRCode, FZRName, FZRMobile, FZRPhone, FZREmail) {
        $("#FZRCode").val(FZRCode);
        $("input[name='FZRName']").val(FZRName);
        $("input[name='FZRMobile']").val(FZRMobile);
        $("input[name='FZRPhone']").val(FZRPhone);
        $("input[name='FZREmail']").val(FZREmail);
        $("#div_FZRName").html(FZRName);
    }
</script>
</body>
</html>