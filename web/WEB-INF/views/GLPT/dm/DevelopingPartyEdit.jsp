<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-13
  Time: 下午9:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>开发方资料管理-修改</title>
    <jsp:include page="/Common/PageCss.jsp"></jsp:include>
    <jsp:include page="/Common/PageJs.jsp"></jsp:include>
    <style type="text/css">
        .ContactPerson_jg {
            margin-left: 2px;
        }

        #div_ContactPerson ul {
            text-align: left;
            padding: 0;
            margin: 0;
            line-height: 20px;
        }

        #div_ContactPerson li {
            float: left;
            list-style: none;
            padding-left: 5px;
        }


    </style>
</head>
<body class="easyui-layout">
<div region="center">
    <form id="ff" action="/GLPT/DM/DevelopingPartyEditOK.do" method="post">
        <input type="hidden" id="Id" name="Id" value="${dParty.id}">
        <input type="hidden" id="Code" name="Code" value="${dParty.code}">
        <table border="0" cellpadding="0" cellspacing="0" class="frm">
            <tr>
                <th>开发方名称[<font face="宋体" color="red">*</font>]：</th>
                <td colspan="3">
                    <input name="FullName" type="text" id="FullName" maxlength="500" class="txt"
                           style="width: 440px" value="${dParty.fullName}"/>
                </td>
            </tr>
            <tr>
                <th>联系人：</th>
                <td colspan="3">
                    <form id="frm_ContactPerson" method="post">
                        <div id="div_ContactPerson">
                            <c:if test="${!empty dPartyContactPersonList}">
                                <c:forEach var="dpcp" items="${dPartyContactPersonList}" varStatus="rowNum">
                                    <div id="div_cp_${rowNum.index}">
                                        <ul>
                                            <li><input name="ContactPersonRoleCode"
                                                       id="ContactPersonRoleCode_${rowNum.index}" maxlength="50"
                                                       style="width: 80px" value="${dpcp.contactPersonRoleCode}"/></li>
                                            <li><input type="text" name="ContactPersonFullName"
                                                       id="ContactPersonFullName_${rowNum.index}"
                                                       maxlength="50" class="txt"
                                                       style="width: 100px;" value="${dpcp.contactPersonFullName}"/>
                                            </li>
                                            <li><input name="ContactMethodCode"
                                                       id="ContactMethodCode_${rowNum.index}"
                                                       maxlength="50"
                                                       style="width: 80px" value="${dpcp.contactMethodCode}"/></li>
                                            <li><input type="text" name="ContactMethodContent"
                                                       id="ContactMethodContent_${rowNum.index}"
                                                       maxlength="500" class="txt"
                                                       style="width: 100px" value="${dpcp.contactMethodContent}"/></li>
                                            <c:if test="${rowNum.index==0}">
                                                <li><a href="#" onclick="fun_DevelopingPartyContactPersonAdd();"
                                                       style="color: blue;">新建</a></li>
                                            </c:if>
                                            <li><a href="#"
                                                   onclick="fun_DevelopingPartyContactPersonClear('${rowNum.index}')"
                                                   style="color: red;">清空</a></li>
                                            <c:if test="${rowNum.index>0}">
                                                <li><a href="#"
                                                       onclick="fun_DevelopingPartyContactPersonDel('div_cp_${rowNum.index}')"
                                                       style="color: red;">删除</a></li>
                                            </c:if>
                                        </ul>
                                        <script type="text/javascript">
                                            $(function () {
                                                $("#ContactPersonRoleCode_${rowNum.index}").combobox({
                                                    url: '/GLPT/CORE/ItemDetailsFindDataEx.do?ItemsCode=KFF_LXRJS',
                                                    valueField: 'Code',
                                                    textField: 'FullName'
                                                });
                                                $("#ContactMethodCode_${rowNum.index}").combobox({
                                                    url: '/GLPT/CORE/ItemDetailsFindDataEx.do?ItemsCode=ContactMethodCode',
                                                    valueField: 'Code',
                                                    textField: 'FullName'
                                                });
                                            });
                                        </script>
                                    </div>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty dPartyContactPersonList}">
                                <div id="div_cp_0">
                                    <ul>
                                        <li><input name="ContactPersonRoleCode"
                                                   id="ContactPersonRoleCode_0" maxlength="50"
                                                   style="width: 80px"/></li>
                                        <li><input type="text" name="ContactPersonFullName" id="ContactPersonFullName_0"
                                                   maxlength="50" class="txt"
                                                   style="width: 100px;"/></li>
                                        <li><input name="ContactMethodCode"
                                                   id="ContactMethodCode_0"
                                                   maxlength="50"
                                                   style="width: 80px"/></li>
                                        <li><input type="text" name="ContactMethodContent" id="ContactMethodContent_0"
                                                   maxlength="500" class="txt"
                                                   style="width: 100px"/></li>
                                        <li><a href="#" onclick="fun_DevelopingPartyContactPersonAdd();"
                                               style="color: blue;">新建</a></li>
                                        <li><a href="#" onclick="fun_DevelopingPartyContactPersonClear('0')"
                                               style="color: red;">清空</a></li>
                                    </ul>
                                    <script type="text/javascript">
                                        $(function () {
                                            $("#ContactPersonRoleCode_0").combobox({
                                                url: '/GLPT/CORE/ItemDetailsFindDataEx.do?ItemsCode=KFF_LXRJS',
                                                valueField: 'Code',
                                                textField: 'FullName'
                                            });
                                            $("#ContactMethodCode_0").combobox({
                                                url: '/GLPT/CORE/ItemDetailsFindDataEx.do?ItemsCode=ContactMethodCode',
                                                valueField: 'Code',
                                                textField: 'FullName'
                                            });
                                        });
                                    </script>
                                </div>
                            </c:if>
                        </div>
                    </form>
                </td>
            </tr>
            <tr>
                <th>QQ：</th>
                <td>
                    <input name="QQ" type="text" id="QQ" maxlength="50" class="txt"
                           style="width: 150px" value="${dParty.QQ}"/>
                </td>
                <th>EMail：</th>
                <td>
                    <input name="EMail" type="text" id="EMail" maxlength="50" class="txt"
                           style="width: 150px" value="${dParty.EMail}"/>
                </td>
            </tr>
            <tr>
                <th>电话号码：</th>
                <td>
                    <input name="Phone" type="text" id="Phone" maxlength="50" class="txt"
                           style="width: 150px" value="${dParty.phone}"/>
                </td>
                <th>手机号码：</th>
                <td>
                    <input name="Mobile" type="text" id="Mobile" maxlength="50" class="txt"
                           style="width: 150px" value="${dParty.mobile}"/>
                </td>
            </tr>
            <tr>
                <th>公司地址：</th>
                <td colspan="3">
                    <textarea id="Address" class="txtArea" style="width: 440px" rows="6" type="text" maxlength="200"
                              name="Address">${dParty.address}</textarea>
                </td>
            </tr>
            <tr>
                <th>合作日期：</th>
                <td>
                    <input name="CooperationDate" type="text" id="CooperationDate" class="easyui-datebox"
                           style="width: 150px" value="${dParty.cooperationDate}"/>
                </td>
                <th>排序号[<font face="宋体" color="red">*</font>]：</th>
                <td><input name="SortCode" type="text" id="SortCode" class="easyui-numberbox" min="0"
                           style="width: 150px" value="${dParty.sortCode}"/></td>
            </tr>
            <tr>
                <th>选项[<font face="宋体" color="red">*</font>]：</th>
                <td colspan="3">
            <span class="item">
                    <input id="Enabled" type="checkbox" style="vertical-align: middle;" checked="checked"
                           name="Enabled">
                    <label style="vertical-align: middle;" for="Enabled">有效</label>
            </span>
                </td>
            </tr>
            <tr>
                <th>备注：</th>
                <td colspan="3">
                    <textarea id="Remarks" class="txtArea" style="width: 440px" rows="4" type="text" maxlength="200"
                              name="Remarks">${dParty.remarks}</textarea>
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
    $(function () {
        $("#Enabled").attr("checked", ${dParty.enabled});
    });
    var fun_submit = function () {
        var win = $.messager.progress({title: '请稍等...', msg: '保存中...'});
        var formData = $("#ff").serialize();
        $.ajax({
            type: "POST",
            url: "/GLPT/DM/DevelopingPartyEditOK.do",
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


    var CPId1 = ${dPartyContactPersonCount};

    var fun_DevelopingPartyContactPersonAdd = function () {
        CPId1 = CPId1 + 1;
        var html1 = "<div id=\"div_cp_" + CPId1 + "\">"
                + "<ul><li><input class=\"easyui-combobox\" name=\"ContactPersonRoleCode\" id=\"ContactPersonRoleCode_" + CPId1 + "\" maxlength=\"50\" style=\"width: 80px\"/></li>"
                + "<li><input type=\"text\" name=\"ContactPersonFullName\" id=\"ContactPersonFullName_" + CPId1 + "\" maxlength=\"50\" class=\"txt\" style=\"width: 100px;\"/></li>"
                + "<li><input class=\"easyui-combobox\" name=\"ContactMethodCode\" id=\"ContactMethodCode_" + CPId1 + "\" maxlength=\"50\" style=\"width: 80px;\"/></li>"
                + "<li><input type=\"text\" name=\"ContactMethodContent\" id=\"ContactMethodContent_" + CPId1 + "\" maxlength=\"500\" class=\"txt\"  style=\"width: 100px;\"/></li>"
                + "<li><a href=\"#\" onclick=\"fun_DevelopingPartyContactPersonClear('" + CPId1 + "')\" style=\"color: red;\">清空</a></li>"
                + "<li><a href=\"#\" onclick=\"fun_DevelopingPartyContactPersonDel('div_cp_" + CPId1 + "')\" style=\"color: red;\">删除</a></li>"
                + "</div>";
        $("#div_ContactPerson").append(html1);
        $("#ContactPersonRoleCode_" + CPId1).combobox({
            url: '/GLPT/CORE/ItemDetailsFindDataEx.do?ItemsCode=KFF_LXRJS',
            valueField: 'Code',
            textField: 'FullName',
            onLoadSuccess: function (data) {
                if (data) {
                    // $("#ContactPersonRoleCode_"+CPId1).combobox('setValue', data[0].Code);
                }
            }
        });
        $("#ContactPersonFullName_" + CPId1).addClass("txt");
        $("#ContactMethodCode_" + CPId1).combobox({
            url: '/GLPT/CORE/ItemDetailsFindDataEx.do?ItemsCode=ContactMethodCode',
            valueField: 'Code',
            textField: 'FullName',
            onLoadSuccess: function (data) {
                if (data) {
                    // $("#ContactMethodCode_"+CPId1).combobox('setValue', data[0].Code);
                }
            }
        });
        $("#ContactMethodContent_" + CPId1).addClass("txt");

    }

    var fun_DevelopingPartyContactPersonDel = function (divcpid) {
        $('#' + divcpid).remove();
    }
    var fun_DevelopingPartyContactPersonClear = function (cpIdVal) {
        $("#ContactPersonRoleCode_" + cpIdVal).combobox('clear');
        $("#ContactPersonFullName_" + cpIdVal).val("");
        $("#ContactMethodCode_" + cpIdVal).combobox("clear");
        $("#ContactMethodContent_" + cpIdVal).val("");
    }
</script>
</body>
</html>