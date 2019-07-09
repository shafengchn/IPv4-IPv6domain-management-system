<%--
  User: 黄良辉
  Date: 14-7-20
  Time: 上午11:42
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>设置权限</title>
    <jsp:include page="/Common/PageCss.jsp"></jsp:include>
    <jsp:include page="/Common/PageJs.jsp"></jsp:include>
    <style type="text/css">
        .datagrid-body td, .datagrid-footer td {
            border-bottom: 1px dotted #ccc;
            border-right: 1px dotted #ccc;
            font-size: 12px;
            margin: 0;
            overflow: hidden;
            padding: 0;
        }
    </style>
</head>
<body>
<div style="height: 450px; width: 100%; overflow: auto;">
    <form id="ff" action="/GLPT/CORE/RoleSetAuthorityOK.do" method="post">
        <input type="hidden" id="RoleCode" name="RoleCode" value="${RoleCode}"/>
        <c:forEach var="myPT1" items="${SysMenuJson}">
            <div id="div_${myPT1.code}" class="easyui-panel" title="${myPT1.text}" style="width:550px;"
                 collapsible="true">
                <table style="width: 100%;" class="datagrid-body">
                    <tr class="datagrid-row">
                        <td style="width: 100px;" class="datagrid-cell">模块</td>
                        <td class="datagrid-cell">功能</td>
                        <td style="width: 150px;" class="datagrid-cell">数据安全</td>
                    </tr>
                    <c:forEach var="myMK1" items="${myPT1.children}">
                        <tr class="datagrid-row">
                            <td class="datagrid-cell"> <!--模块-->
                              <span class="item">
                                    <input id="${myMK1.code}" type="checkbox" style="vertical-align: middle;"
                                           name="${myMK1.code}"
                                           onchange="fun_selectParent('${myMK1.code}','${myMK1.code}');"  ${myMK1.checked}>
                                        <label style="vertical-align: middle;" for="${myMK1.code}">${myMK1.text}</label>
                              </span>
                            </td>
                            <td colspan="3"> <!--功能-->
                                <table style="width: 100%">
                                    <c:forEach var="myGL1" items="${myMK1.children}">
                                        <tr>
                                            <td class="datagrid-cell">
                                         <span class="item">
                                                <input id="${myGL1.code}" type="checkbox"
                                                       style="vertical-align: middle;"
                                                       name="${myGL1.code}" ParentID="${myMK1.code}"  ${myGL1.checked}>
                                                <label style="vertical-align: middle;"
                                                       for="${myGL1.code}">${myGL1.text}</label>
                                         </span>
                                            </td>
                                            <td style="width: 150px;" class="datagrid-cell">  <!--数据安全-->
                                                <c:forEach var="myDataSafe1" items="${myGL1.dataSafe}">
                                                    <div>
                                                        <input id="${myDataSafe1.code}" type="checkbox"
                                                               style="vertical-align: middle;"
                                                               name="${myDataSafe1.code}"
                                                               ParentID="${myMK1.code}"  ${myDataSafe1.checked}>
                                                        <label style="vertical-align: middle;"
                                                               for="${myDataSafe1.code}">${myDataSafe1.text}</label>
                                                    </div>
                                                </c:forEach>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:forEach>
    </form>
</div>

<div class="frmbottom">
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
    var fun_selectParent = function (nodeID, parentID) {
        if ($("#" + nodeID).attr("checked") == "checked") {
            $("input[ParentID='" + parentID + "']").attr("checked", true);
        } else {
            $("input[ParentID='" + parentID + "']").attr("checked", false);
        }
    }

    var fun_submit = function () {
        var win = $.messager.progress({title: '请稍等...', msg: '保存中...'});
        var formData = $("#ff").serialize();
        $.ajax({
            type: "POST",
            url: "/GLPT/CORE/RoleSetAuthorityOK.do",
            processData: true,
            dataType: "json",
            data: formData,
            success: function (data) {
                $.messager.progress('close');
                if (data.success == true) {
                    showFaceMsg("保存成功");
                } else {
                    showFaceMsg(data.info);
                }
            }
        });
    }
</script>
</body>
</html>