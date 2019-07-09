<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-20
  Time: 下午1:50
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>借用者管理-显示借用者资料</title>
    <jsp:include page="/Common/PageCss.jsp"></jsp:include>
    <jsp:include page="/Common/PageJs.jsp"></jsp:include>
</head>
<body  class="easyui-layout">
<div region='north' style="overflow-y:hidden;">
    <div class="WarmPrompt-Info">
        <c:if test="${borrower.gender=='男'}">
            <img style="width: 38px; height: 38px;vertical-align: middle; margin-bottom:5px;" src="/skins/Default/images/man.png"/>
        </c:if>
        <c:if test="${borrower.gender=='女'}">
            <img style="width: 38px; height: 38px;vertical-align: middle; margin-bottom:5px;" src="/skins/Default/images/woman.png"/>
        </c:if>
        ${borrower.fullName}（${borrower.code}）
    </div>
</div>
<div region="center" >
    <div id="tabs1" class="easyui-tabs" fit="true">
        <div title="基本信息">
            <table border="0" cellpadding="0" cellspacing="0" class="frm">
                <tr>
                    <th>姓名[<font face="宋体">*</font>]：</th>
                    <td>${borrower.fullName}</td>
                    <th>工号[<font face="宋体">*</font>]：</th>
                    <td>${borrower.code}</td>
                </tr>
                <tr>
                    <th>手机号码[<font face="宋体">*</font>]：</th>
                    <td>${borrower.mobile}</td>
                    <th>查询密码：</th>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <th>性别[<font face="宋体">*</font>]：</th>
                    <td>${borrower.gender}</td>
                    <th>别名：</th>
                    <td>${borrower.alias}</td>
                </tr>
                <tr>
                    <th>出生日期：</th>
                    <td>${borrower.birthday}</td>
                    <th>年龄[<font face="宋体">*</font>]：</th>
                    <td>${borrower.age}</td>
                </tr>
                <tr>
                    <th>身份证号码：</th>
                    <td>${borrower.IDCard}</td>
                    <th>电子邮件：</th>
                    <td>${borrower.email}</td>
                </tr>
                <tr>
                    <th>QQ号码：</th>
                    <td>${borrower.OICQ}</td>
                    <th>排序：</th>
                    <td>${borrower.sortCode}</td>
                </tr>
                <tr>
                    <th>住宅电话：</th>
                    <td>${borrower.homePhone}</td>
                    <th>家庭地址：</th>
                    <td>${borrower.homeAddress}</td>
                </tr>
                <tr>
                    <th>有效性：</th>
                    <td colspan="3">
                        <c:if test="${borrower.enabled==true}">
                            是
                        </c:if>
                        <c:if test="${borrower.enabled==false}">
                            否
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th>说明：</th>
                    <td colspan="3">${borrower.description}</td>
                </tr>
            </table>
        </div>
        <div title="所属部门">
            <table id="tt"></table>
        </div>
    </div>
</div>
 <script type="text/javascript">
     $(function(){
         $('#tt').datagrid({
             fit: true,
             url: '/GLPT/BASE/TeacherAndDepartmentFindTeacherByPage.do?TeacherCode=${borrower.code}',
             singleSelect:true,
             remoteSort: false,
             rownumbers: true,
             idField: 'ID',
             columns: [
                 [
                     {field: 'TeacherName', title: '借用者姓名', width: 80, align: 'center'},
                     {field: 'DepartmentName', title: '部门名称', width: 100, align: 'center'},
                     {field: 'Enabled', title: '有效', width: 80,  align: 'center', formatter: fun_FormatEnabled}
                 ]
             ],
             pagination: true
         });
         var p = $('#tt').datagrid('getPager');
         $(p).pagination({
             onBeforeRefresh: function () {
                 $('#tt').datagrid('clearSelections');
                 $('#tt').datagrid('reload');
             }
         });


     });

     /*
      * 有效状态格式化
      * */
     var fun_FormatEnabled= function(val, row) {
         if (val == true || val=='true') {
             return "<img src='/skins/Default/images/checkmark.gif' alt='有效'/>";
         } else {
             return "<img src='/skins/Default/images/checknomark.gif' alt='无效'/>";
         }
     }


 </script>

</body>
</html>