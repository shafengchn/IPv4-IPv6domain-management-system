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
    <title>开发方资料管理-显示</title>
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
            clear: left;
        }

        #div_ContactPerson li {
            float: left;
            list-style: none;
            padding-left: 5px;
            width: 100px;
        }


    </style>
</head>
<body class="easyui-layout">
<div region="center">
        <table border="0" cellpadding="0" cellspacing="0" class="frm">
            <tr>
                <th>开发方名称[<font face="宋体" color="red">*</font>]：</th>
                <td colspan="3">${dParty.fullName}</td>
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
                                            <li>${dpcp[1]}</li>
                                            <li>${dpcp[2]}</li>
                                            <li>${dpcp[3]}</li>
                                            <li>${dpcp[4]}</li>
                                        </ul>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                    </form>
                </td>
            </tr>
            <tr>
                <th>QQ：</th>
                <td>${dParty.QQ}</td>
                <th>EMail：</th>
                <td>${dParty.EMail}</td>
            </tr>
            <tr>
                <th>电话号码：</th>
                <td>${dParty.phone}</td>
                <th>手机号码：</th>
                <td>${dParty.mobile}</td>
            </tr>
            <tr>
                <th>公司地址：</th>
                <td colspan="3">${dParty.address}</td>
            </tr>
            <tr>
                <th>合作日期：</th>
                <td>${dParty.cooperationDate}</td>
                <th>排序号[<font face="宋体" color="red">*</font>]：</th>
                <td>${dParty.sortCode}</td>
            </tr>
            <tr>
                <th>选项[<font face="宋体" color="red">*</font>]：</th>
                <td colspan="3">
                    <c:if test="${dParty.enabled==true}">
                        <label>有效</label>
                    </c:if>
                    <c:if test="${dParty.enabled==false}">
                        <label style="background-color: red;">无效</label>
                    </c:if>
                </td>
            </tr>
            <tr>
                <th>备注：</th>
                <td colspan="3">${dParty.remarks}</td>
            </tr>
        </table>
</div>
<div region="south" style="text-align: center; padding-top: 5px; height: 40px;">
    <a class="l-btn" onclick="OpenClose();" href="javascript:void(0)">
        <span class="l-btn-left">
                <img alt="" src="/skins/Default/images/16/cancel.png">
                    关 闭
        </span>
    </a>
</div>
</body>
</html>