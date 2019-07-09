<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-13
  Time: 下午9:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>设置快捷操作</title>
    <jsp:include page="/Common/PageCss.jsp"></jsp:include>
    <jsp:include page="/Common/PageJs.jsp"></jsp:include>
    <style type="text/css">
        .mySpan {
            display: -moz-inline-box;
            display: inline-block;
            width: 80px;
        }
    </style>
</head>
<body  class="easyui-layout">
<div region="north" style="height: 50px;" border="false">
<div class="WarmPrompt-Info">
    ${myUser.code}（${myUser.realName}）
</div>
</div>
<div  region="center" border="false">
<form id="ff" action="/GLPT/MySpacePersonalShortcutsOK.do" method="post">
    <input type="hidden" id="UserId" name="UserId" value="${myUser.userId}"/>
    <div id="div_1">
        <ul id="tt"></ul>
    </div>
</form>
</div>
<div region="south" border="false" style="height: 50px; text-align: center; padding-top: 10px;">
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
        $('#tt').tree({
            url: '/GLPT/BASE/UserAndSysMenuFindAllTreeData.do?UserCode=${myUser.code}',
            checkbox: true,
            onlyLeafCheck: true,
            onLoadSuccess: function () {
                $('#tt').tree('collapseAll');
            }
        });

    });
    var fun_SelectAll = function () {
        $("input[name='chk_Class']").attr("checked", true);
    }

    var fun_SelectNo = function () {
        var arrChk = $("input[name='chk_Class']");
        $(arrChk).each(function () {
            $(this).attr("checked", !this.checked);
        });
    }

    var fun_submit = function () {
        var nodes = $('#tt').tree('getChecked');
        //alert(nodes.length);
        var ZValue1="";
        for(i=0;i<nodes.length;i++){
           if(i==0){
               ZValue1=nodes[i].id;
           }else{
               ZValue1=ZValue1+"|"+nodes[i].id;
           }
        }
       // alert(ZValue1+"["+nodes.length+"]");
        var win = $.messager.progress({title: '请稍等...', msg: '保存中...'});
       // var formData = $("#ff").serialize();
        $.ajax({
            type: "POST",
            url: "/GLPT/MySpacePersonalShortcutsOK.do",
            processData: true,
            dataType: "json",
            data: {UserCode:'${myUser.code}',SysMenuCode:ZValue1},
            success: function (data) {
                $.messager.progress('close');
                if (data.success == true) {
                    //showFaceMsg("设置成功");
                    OpenClose();
                } else {
                    showFaceMsg(data.info);
                }
            }
        });
    }
</script>
</body>
</html>