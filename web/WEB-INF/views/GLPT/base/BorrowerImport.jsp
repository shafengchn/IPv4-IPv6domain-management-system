<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-13
  Time: 下午9:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>借用者管理-导入</title>
    <jsp:include page="/Common/PageCss.jsp"></jsp:include>
    <jsp:include page="/Common/PageJs.jsp"></jsp:include>
</head>
<body class="easyui-layout">
<div region="center">
    <form id="ff" action="/GLPT/BASE/BorrowerImportOK.do" method="post">
        <table border="0" cellpadding="0" cellspacing="0" class="frm">
            <tr>
                <td>借用者资料模板：</td>
                <td style="text-align: left;">
                    <a class="l-btn" href="/COMMON/DownloadDataTemplate.do?fileCode=BorrowerImport&filePath=DataTemplate"
                       target="_blank">
        <span class="l-btn-left">
                     下载
        </span>
                    </a>
                </td>
            </tr>
            <tr>
                <td colspan="2">选择要导入的文件[<font face="宋体">*</font>]：</td>
            </tr>
            <tr>
                <td colspan="2">
                    <iframe id="iframe_fileUpload_BorrowerImport" width="100%" height="120px"
                            frameborder="0" src="/COMMON/fileUploadEx.do?filePath=other" scrolling="no"
                            name="iframe_fileUpload_BorrowerImport"></iframe>
                </td>
            </tr>
        </table>
    </form>
</div>
<div region="south" style="height: 50px;text-align: center;padding-top: 10px;">
    <a id="Accept" class="l-btn" onclick="fun_submit()" href="javascript:void(0)">
        <span class="l-btn-left">
                <img alt="" src="/skins/Default/images/16/accept.png">
                     导入
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
    var fun_submit = function () {
        var win = $.messager.progress({title: '请稍等...', msg: '导入中...'});
        var formData = $("#ff").serialize();
        var fileName1 = "";
        var iframe1 = document.getElementById("iframe_fileUpload_BorrowerImport");
        if (iframe1 != null) {
            fileName1 = iframe1.contentWindow.fun_GetFileName();
        }
        $.ajax({
            type: "POST",
            url: "/GLPT/BASE/BorrowerImportOK.do",
            processData: true,
            dataType: "json",
            data: {fileName: fileName1},
            success: function (data) {
                $.messager.progress('close');
                if (data.success == true) {
                    if (data.info == "OK") {
                        showFaceMsg("导入成功");
                    } else {
                        showFaceMsg(data.info);
                    }
                } else {
                    showFaceMsg(data.info);
                }
            }
        });
    }
</script>
</body>
</html>