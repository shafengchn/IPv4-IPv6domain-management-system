<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  User: 黄良辉
  Date: 14-3-13
  Time: 下午9:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>域名管理-显示</title>
    <jsp:include page="/Common/PageCss.jsp"></jsp:include>
    <jsp:include page="/Common/PageJs.jsp"></jsp:include>
</head>
<body class="easyui-layout">
<div region="center">
    <div id="tt" class="easyui-tabs" fit="true">
        <div title="域名资料" style="overflow:auto;">
            <table border="0" cellpadding="0" cellspacing="0" class="frm">
                <tr>
                    <th>域名[<font face="宋体" color="red">*</font>]：</th>
                    <td colspan="3">${dm.domainName}
                        <c:if test="${dm.noDomain==true}">
                            <label>无域名系统</label>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th>访问地址[<font face="宋体" color="red">*</font>]：</th>
                    <td colspan="3"><a href="${dm.url}" target="_blank">${dm.url}</a></td>
                </tr>
                <tr>
                    <th>IPv4地址[<font face="宋体" color="red">*</font>]：</th>
                    <td colspan="3">${dm.IPv4Address}</td>
                </tr>
                <tr>
                    <th>IPv6地址：</th>
                    <td colspan="3">${dm.IPv6Address}</td>
                </tr>
                <tr>
                    <th>TTL：</th>
                    <td>${dm.TTL}</td>
                    <th>记录类型：</th>
                    <td>${dm.recordType}</td>
                </tr>
                <tr>
                    <th>记录值：</th>
                    <td>${dm.recordVal}</td>
                    <th>选项：</th>
                    <td>
                        <c:if test="${dm.disable==true}">
                            <label>已停用</label>
                        </c:if>
                        <c:if test="${dm.disable==false}">
                            <label>正常使用</label>
                        </c:if>

                    </td>
                </tr>
                <tr>
                    <th>签到间隔天数[<font face="宋体" color="red">*</font>]：</th>
                    <td>${dm.signInInterval}</td>
                    <th>续订间隔天数[<font face="宋体" color="red">*</font>]：</th>
                    <td>${dm.renewInterval}</td>
                </tr>
                <tr>
                    <th>备注：</th>
                    <td colspan="3">${dm.remarks}</td>
                </tr>
            </table>
        </div>

        <div title="网站、系统信息" style="overflow:auto;">
            <table border="0" cellpadding="0" cellspacing="0" class="frm">
                <tr>
                    <th>名称：</th>
                    <td>${dm.webSiteFullName}</td>
                </tr>
                <tr>
                    <th>分类：</th>
                    <td>${SysFLName}->${SysFLChildName}</td>
                </tr>
                <tr>
                    <th>开通日期：</th>
                    <td>${dm.openDate}</td>
                </tr>
                <tr>
                    <th>备注：</th>
                    <td>${dm.sysRemarks}
                    </td>
                </tr>
            </table>
        </div>


        <div title="开发方" style="overflow:auto;">
            <table border="0" cellpadding="0" cellspacing="0" class="frm">
                <tr>
                    <th>开发方：</th>
                    <td><span id="div_KFFName">${KFFName}</span></td>
                </tr>
                <tr>
                    <th>联系人：</th>
                    <td><span id="div_ContactPerson">${kff.contactPerson}</span></td>
                </tr>
                <tr>
                    <th>QQ：</th>
                    <td><span id="div_QQ">${kff.QQ}</span></td>
                </tr>
                <tr>
                    <th>EMail：</th>
                    <td><span id="div_EMail">${kff.EMail}</span></td>
                </tr>
                <tr>
                    <th>电话号码：</th>
                    <td><span id="div_Phone">${kff.phone}</span> </td>
                </tr>
                <tr>
                    <th>手机号码：</th>
                    <td><span id="div_Mobile">${kff.mobile}</span></td>
                </tr>
                <tr>
                    <th>公司地址：</th>
                    <td><span id="div_Address">${kff.address}</span></td>
                </tr>
                <tr>
                    <th>合作日期：</th>
                    <td><span id="div_CooperationDate">${kff.cooperationDate}</span></td>
                </tr>
                <tr>
                    <th>备注：</th>
                    <td><span id="div_KFFRemarks">${kff.remarks}</span></td>
                </tr>
            </table>
        </div>
        <div title="管理员" style="overflow:auto;">
            <table border="0" cellpadding="0" cellspacing="0" class="frm">
                <tr>
                    <th>管理部门[<font face="宋体" color="red">*</font>]：</th>
                    <td>${GLDepartmentName}</td>
                </tr>
                <tr>
                    <th>姓名[<font face="宋体" color="red">*</font>]：</th>
                    <td>${dm.GLYName}</td>
                </tr>
                <tr>
                    <th>手机号码[<font face="宋体" color="red">*</font>]：</th>
                    <td>${dm.GLYMobile}</td>
                </tr>
                <tr>
                    <th>电话号码：</th>
                    <td>${dm.GLYPhone}</td>
                </tr>
                <tr>
                    <th>EMAIL：</th>
                    <td>${dm.GLYEmail}</td>
                </tr>
                <tr>
                    <th>证件类型：</th>
                    <td>${GLYIdTypeName}</td>
                </tr>
                <tr>
                    <th>证件号：</th>
                    <td>${dm.GLYIdNumber}</td>
                </tr>
                <tr>
                    <th>证件（正面）[<font face="宋体" color="red">*</font>]：</th>
                    <td>
                        <c:if test="${dm.GLYIdImgPath1==null}">
                            <label style="color: red;">无图片</label>
                        </c:if>
                        <c:if test="${dm.GLYIdImgPath1!=null}">
                            <a href="/upload/dm_attrFile/${dm.GLYIdImgPath1}" target="_blank"><img
                                    src="/upload/dm_attrFile/${dm.GLYIdImgPath1}" alt="#"
                                    style="height: 100px;width: 100px;"/></a>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th>证件（反面）[<font face="宋体" color="red">*</font>]：</th>
                    <td>
                        <c:if test="${dm.GLYIdImgPath2==null}">
                            <label style="color: red;">无图片</label>
                        </c:if>
                        <c:if test="${dm.GLYIdImgPath2!=null}">
                            <a href="/upload/dm_attrFile/${dm.GLYIdImgPath2}" target="_blank"><img
                                    src="/upload/dm_attrFile/${dm.GLYIdImgPath2}" alt="#"
                                    style="height: 100px;width: 100px;"/></a>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th>证件（手持）[<font face="宋体" color="red">*</font>]：</th>
                    <td>
                        <c:if test="${dm.GLYIdImgPath3==null}">
                            <label style="color: red;">无图片</label>
                        </c:if>
                        <c:if test="${dm.GLYIdImgPath3!=null}">
                            <a href="/upload/dm_attrFile/${dm.GLYIdImgPath3}" target="_blank"><img
                                    src="/upload/dm_attrFile/${dm.GLYIdImgPath3}" alt="#"
                                    style="height: 100px;width: 100px;"/></a>
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
        <div title="应急联络人" style="overflow:auto;">
            <table border="0" cellpadding="0" cellspacing="0" class="frm">
                <tr>
                    <th>姓名[<font face="宋体" color="red">*</font>]：</th>
                    <td>${dm.ECPName}</td>
                </tr>
                <tr>
                    <th>手机号码[<font face="宋体" color="red">*</font>]：</th>
                    <td>${dm.ECPMobile}</td>
                </tr>
                <tr>
                    <th>电话号码：</th>
                    <td>${dm.ECPPhone}</td>
                </tr>
                <tr>
                    <th>EMAIL：</th>
                    <td>${dm.ECPEmail}</td>
                </tr>
                <tr>
                    <th>证件类型：</th>
                    <td>${ECPIdTypeName}</td>
                </tr>
                <tr>
                    <th>证件号：</th>
                    <td>${dm.ECPIdNumber}</td>
                </tr>
                <tr>
                    <th>证件（正面）[<font face="宋体" color="red">*</font>]：</th>
                    <td>
                        <c:if test="${dm.ECPIdImgPath1==null}">
                            <label style="color: red;">无图片</label>
                        </c:if>
                        <c:if test="${dm.ECPIdImgPath1!=null}">
                            <a href="/upload/dm_attrFile/${dm.ECPIdImgPath1}" target="_blank"><img
                                    src="/upload/dm_attrFile/${dm.ECPIdImgPath1}" alt="#"
                                    style="height: 100px;width: 100px;"/></a>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th>证件（反面）[<font face="宋体" color="red">*</font>]：</th>
                    <td>
                        <c:if test="${dm.ECPIdImgPath2==null}">
                            <label style="color: red;">无图片</label>
                        </c:if>
                        <c:if test="${dm.ECPIdImgPath2!=null}">
                            <a href="/upload/dm_attrFile/${dm.ECPIdImgPath2}" target="_blank"><img
                                    src="/upload/dm_attrFile/${dm.ECPIdImgPath2}" alt="#"
                                    style="height: 100px;width: 100px;"/></a>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th>证件（手持）[<font face="宋体" color="red">*</font>]：</th>
                    <td>
                        <c:if test="${dm.ECPIdImgPath3==null}">
                            <label style="color: red;">无图片</label>
                        </c:if>
                        <c:if test="${dm.ECPIdImgPath3!=null}">
                            <a href="/upload/dm_attrFile/${dm.ECPIdImgPath3}" target="_blank"><img
                                    src="/upload/dm_attrFile/${dm.ECPIdImgPath3}" alt="#"
                                    style="height: 100px;width: 100px;"/></a>
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>

        <div title="信息审批员" style="overflow:auto;">
            <table border="0" cellpadding="0" cellspacing="0" class="frm">
                <tr>
                    <th>姓名[<font face="宋体" color="red">*</font>]：</th>
                    <td>${dm.approvalName}</td>
                </tr>
                <tr>
                    <th>手机号码[<font face="宋体" color="red">*</font>]：</th>
                    <td>${dm.approvalMobile}</td>
                </tr>
                <tr>
                    <th>电话号码：</th>
                    <td>${dm.approvalPhone}</td>
                </tr>
                <tr>
                    <th>EMAIL：</th>
                    <td>${dm.approvalEmail}</td>
                </tr>
            </table>
        </div>
        <div title="党政负责人" style="overflow:auto;">
            <table border="0" cellpadding="0" cellspacing="0" class="frm">
                <tr>
                    <th>姓名[<font face="宋体" color="red">*</font>]：</th>
                    <td>${dm.FZRName}</td>
                </tr>
                <tr>
                    <th>手机号码[<font face="宋体" color="red">*</font>]：</th>
                    <td>${dm.FZRMobile}</td>
                </tr>
                <tr>
                    <th>电话号码：</th>
                    <td>${dm.FZRPhone}</td>
                </tr>
                <tr>
                    <th>EMAIL：</th>
                    <td>${dm.FZREmail}</td>
                </tr>
            </table>
        </div>
    </div>
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