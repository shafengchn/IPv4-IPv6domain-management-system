<%--
  User: 黄良辉
  Date: 14-8-12
  Time: 下午3:47
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>我的信息</title>
    <script type="text/javascript" src="/js/EasyUI/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="/js/EasyUI/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/EasyUI/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="/js/EasyUI/themes/gray/easyui.css"/>
    <script type="text/javascript" src="/js/FunctionJS.js"></script>
    <style type="text/css">
        .table1 th {
            text-align: right;
            padding-top: 5px;
            padding-right: 5px;
        }

        .table1 td {
            text-align: left;
            padding-top: 5px;
            padding-left: 5px;
        }

        .spanLeft {
            width: 20%;
            text-align: right;
            clear: left;
            float: left;
            line-height: 20px;
            margin-bottom: 5px;
        }

        .spanRight {
            line-height: 20px;
            float: left;
            width: 80%;
        }
    </style>
</head>
<body class="easyui-layout">
<div id="tmpDiv" style="height: 1000px;"></div>
<div region="north" style="height: 80px; padding-top: 10px;padding-left: 10px;" border="false" title="系统设置">
    <a id="btn_EditPassword" href="javascript:void(0)" class="easyui-linkbutton" onclick="fun_EditPassword();">修改密码</a>
    <a id="btn_Save1" href="javascript:void(0)" class="easyui-linkbutton"
       onclick="fun_submit()">保存</a>
</div>

<div region="center">

    <div id="aa" class="easyui-accordion" fit="true">
        <div title="我的信息">
            <div style="padding:20px;overflow: auto;">
                <form id="ff" action="/GLPT/CORE/UserPersonalEditOK.do" method="post">
                    <input type="hidden" id="UserId" name="UserId" value="${myUser.userId}"/>
                    <input type="hidden" id="SortCode" name="SortCode" value="${myUser.sortCode}"/>
                    <input type="hidden" id="IdType" name="IdType" value="${myUser.idType}"/>
                    <input type="hidden" id="IdNumber" name="IdNumber" value="${myUser.idNumber}"/>
                    <input type="hidden" id="IdImgPath1" name="IdImgPath1" value="${myUser.idImgPath1}"/>
                    <input type="hidden" id="IdImgPath2" name="IdImgPath2" value="${myUser.idImgPath2}"/>
                    <input type="hidden" id="IdImgPath3" name="IdImgPath3" value="${myUser.idImgPath3}"/>
                    <div>
                        <span class="spanLeft">工号[<font face="宋体" style="color: red;">*</font>]：</span>
                        <span class="spanRight">${myUser.code}</span>
                    </div>
                    <div>
                        <span class="spanLeft">登录账户[<font face="宋体" style="color: red;">*</font>]：</span>
                        <span class="spanRight">${myUser.account}</span>
                    </div>
                    <div>
                        <span class="spanLeft">姓名[<font face="宋体" style="color: red;">*</font>]：</span>
                        <span class="spanRight"><input name="RealName" type="text" id="RealName" maxlength="50"
                                                       class="txt"
                                                       style="width: 220px" value="${myUser.realName}"/></span>
                    </div>
                    <div>
                        <span class="spanLeft">性别[<font face="宋体" style="color: red;">*</font>]：</span>
                        <span class="spanRight"> <select name="Gender" id="Gender" class="select" style="width: 220px">
                            <option value="男">男</option>
                            <option value="女">女</option>
                        </select></span>
                    </div>
                    <div>
                        <span class="spanLeft">手机：</span>
                        <span class="spanRight"><input name="Mobile" type="text" id="Mobile" maxlength="50" class="txt"
                                                       style="width: 220px"
                                                       value="${myUser.mobile}"/></span>
                    </div>
                    <div>
                        <span class="spanLeft">出生日期：</span>
                        <span class="spanRight"><input name="Birthday" type="text" id="Birthday" style="width: 220px"
                                                       class="easyui-datebox"
                                                       value="${myUser.birthday}"/></span>
                    </div>
                    <div>
                        <span class="spanLeft">电话：</span>
                        <span class="spanRight"><input name="Telephone" type="text" id="Telephone" maxlength="50"
                                                       class="txt"
                                                       style="width: 220px" value="${myUser.telephone}"/></span>
                    </div>
                    <div>
                        <span class="spanLeft">QQ号码：</span>
                        <span class="spanRight"><input name="OICQ" type="text" id="OICQ" maxlength="50" class="txt"
                                                       style="width: 220px"
                                                       value="${myUser.OICQ}"/></span>
                    </div>
                    <div>
                        <span class="spanLeft">电子邮件：</span>
                        <span class="spanRight"><input name="Email" type="text" id="Email" maxlength="50" class="txt"
                                                       style="width: 220px"
                                                       value="${myUser.email}"/></span>
                    </div>
                    <div>
                        <span class="spanLeft">默认部门[<font face="宋体" style="color: red;">*</font>]：</span>
                        <span class="spanRight"><input class="easyui-combobox"
                                                       name="DepartmentCode"
                                                       id="DepartmentCode"
                                                       url="/GLPT/BASE/DepartmentGetData.do"
                                                       valueField="Code"
                                                       textField="FullName" maxlength="50" style="width: 220px"
                                                       value="${myUser.departmentCode}"/></span>
                    </div>
                    <div>
                        <span class="spanLeft">默认职务：</span>
                        <span class="spanRight"> <input class="easyui-combobox"
                                                        name="TitleCode"
                                                        id="TitleCode"
                                                        url="/GLPT/CORE/ItemDetailsFindData.do?ItemsName=职务"
                                                        valueField="Code"
                                                        textField="FullName" maxlength="50" style="width: 220px"
                                                        value="${myUser.titleCode}"/></span>
                    </div>
                    <div>
                        <span class="spanLeft">说明：</span>
                        <span class="spanRight"> <textarea id="Description" class="txtArea" style="width: 595px"
                                                           rows="3" type="text"
                                                           maxlength="200"
                                                           name="Description">${myUser.description}</textarea></span>
                    </div>

                </form>
            </div>
        </div>
        <div title="我的证件">
            <div style="padding:20px;overflow: auto;">
                <div>
                    <span class="spanLeft">证件类型[<font face="宋体" style="color: red;">*</font>]：</span>
                    <span class="spanRight">
                        <input class="easyui-combobox"
                               name="IdType"
                               id="tmp_IdType"
                               url="/GLPT/CORE/ItemDetailsFindDataEx.do?ItemsCode=IdType"
                               valueField="Code"
                               textField="FullName" maxlength="50" style="width: 220px" value="${myUser.idType}"/>
                    </span>
                </div>
                <div>
                    <span class="spanLeft">证件号[<font face="宋体" style="color: red;">*</font>]：</span>
                    <span class="spanRight">
                        <input name="IdNumber" type="text" id="tmp_IdNumber" maxlength="50" class="txt" style="width: 220px"
                               value="${myUser.idNumber}"/>
                    </span>
                </div>
                <div>
                    <span class="spanLeft">证件(正面)[<font face="宋体" style="color: red;">*</font>]：</span>
                    <span class="spanRight">
                    <iframe id="iframe_fileUpload_IdImgPath1" width="100%" height="150px"
                            frameborder="0"
                            <c:if test="${myUser.idImgPath1==null}">
                                src="/COMMON/fileUploadImgEx.do?filePath=dm_attrFile&imgShow=true&imgHeight=100&imgWidth=100"
                            </c:if>
                            <c:if test="${myUser.idImgPath1!=null}">
                                src="/COMMON/fileUploadImgEx.do?filePath=dm_attrFile&fileName=${myUser.idImgPath1}&imgShow=true&imgHeight=100&imgWidth=100"
                            </c:if>
                            scrolling="no"
                            name="iframe_fileUpload_IdImgPath1"></iframe>
                    </span>
                </div>
                <div>
                    <span class="spanLeft">证件(反面)[<font face="宋体" style="color: red;">*</font>]：</span>
                    <span class="spanRight">
                           <iframe id="iframe_fileUpload_IdImgPath2" width="100%" height="150px"
                                   frameborder="0"
                                   <c:if test="${myUser.idImgPath2==null}">
                                       src="/COMMON/fileUploadImgEx.do?filePath=dm_attrFile&imgShow=true&imgHeight=100&imgWidth=100"
                                   </c:if>
                                   <c:if test="${myUser.idImgPath2!=null}">
                                       src="/COMMON/fileUploadImgEx.do?filePath=dm_attrFile&fileName=${myUser.idImgPath2}&imgShow=true&imgHeight=100&imgWidth=100"
                                   </c:if>
                                   scrolling="no"
                                   name="iframe_fileUpload_IdImgPath2"></iframe>
                    </span>
                </div>
                <div>
                    <span class="spanLeft">证件(手持)[<font face="宋体" style="color: red;">*</font>]：</span>
                    <span class="spanRight">
                            <iframe id="iframe_fileUpload_IdImgPath3" width="100%" height="150px"
                                    frameborder="0"
                                    <c:if test="${myUser.idImgPath3==null}">
                                        src="/COMMON/fileUploadImgEx.do?filePath=dm_attrFile&imgShow=true&imgHeight=100&imgWidth=100"
                                    </c:if>
                                    <c:if test="${myUser.idImgPath3!=null}">
                                        src="/COMMON/fileUploadImgEx.do?filePath=dm_attrFile&fileName=${myUser.idImgPath3}&imgShow=true&imgHeight=100&imgWidth=100"
                                    </c:if>
                                    scrolling="no"
                                    name="iframe_fileUpload_IdImgPath3"></iframe>
                    </span>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
<script type="text/javascript">
    $(function () {
        $("#Gender").val("${myUser.gender}");
        $("#tmpDiv").hide();
    });
    var fun_submit = function () {
        var win = $.messager.progress({title: '请稍等...', msg: '保存中...'});
        $("#IdType").val($('#tmp_IdType').combobox('getValues'));
        $("#IdNumber").val($('#tmp_IdNumber').val());
        var iframe1 = document.getElementById("iframe_fileUpload_IdImgPath1");
        if (iframe1 != null) {
            $("#IdImgPath1").val(iframe1.contentWindow.fun_GetFileName());
        }
        var iframe2 = document.getElementById("iframe_fileUpload_IdImgPath2");
        if (iframe2 != null) {
            $("#IdImgPath2").val(iframe2.contentWindow.fun_GetFileName());
        }
        var iframe3 = document.getElementById("iframe_fileUpload_IdImgPath3");
        if (iframe3 != null) {
            $("#IdImgPath3").val(iframe3.contentWindow.fun_GetFileName());
        }
        var formData = $("#ff").serialize();
        $.ajax({
            type: "POST",
            url: "/GLPT/CORE/UserPersonalEditOK.do",
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
    var fun_EditPassword = function () {
        var url = "/GLPT/CORE/UserEditPassword.do?UserId=${myUser.userId}&IframeId=${IframeId}";
        top.openDialog(url, 'UserEditPassword', '修改密码', 350, 150, 50, 50);
    }
</script>
</html>