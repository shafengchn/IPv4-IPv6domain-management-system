<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  User: 黄良辉
  Date: 14-3-20
  Time: 下午1:50
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生借用者管理-生成二维码</title>
    <jsp:include page="/Common/PageCss.jsp"></jsp:include>
</head>
<body>
<center>
<img alt="${EnCode}" name="QRCodeImg"
     id="QRCodeImg" src="/COMMON/QRCodeCreateImg.do?EnCode=${EnCode}&width=${ImgWidth}&height=${ImgHeight}"
     width="${ImgWidth}" height="${ImgHeight}"/>
    <br/>
    <br/>
    <a class="l-btn" href="/COMMON/QRCodeCreateImg.do?EnCode=${EnCode}&width=${ImgWidth}&height=${ImgHeight}" target="_blank">
        <span class="l-btn-left">
                     下载
        </span>
    </a>
</center>
</body>
</html>