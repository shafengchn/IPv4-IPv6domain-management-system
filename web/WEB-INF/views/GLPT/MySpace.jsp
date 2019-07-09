<%--
  User: 黄良辉
  Date: 14-3-9
  Time: 下午12:04
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9"/>
    <title>我的空间</title>
    <link href="<%=basePath%>skins/Default/Styles/Style-2.css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=basePath%>js/EasyUI/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/EasyUI/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/EasyUI/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/EasyUI/themes/gray/easyui.css"/>
    <script type="text/javascript" src="<%=basePath%>js/EasyUI/plugins/jquery.portal.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/EasyUI/themes/portal.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/EasyUI/themes/icon.css"/>
    <link href="<%=basePath%>skins/Default/Styles/Touch.css" rel="stylesheet" />
    <script src="<%=basePath%>js/FunctionJS.js"></script>
    <script src="<%=basePath%>js/MainTouch.js"></script>
    <style type="text/css">
        #table_map{
          border-color: #ffffff;
        }
        #table_map tr{
            height:35px;
        }
        #table_map td{
            border-color: #ffffff;
        }
    </style>
</head>
<body>
<div id="tmpDiv" style="height: 1000px;"></div>
    <div id="pp" fit="true">
        <div style="width:100%;" >
            <div title="快捷操作" style="height:290px;padding:5px;"  tools="#tt">
               <div id="htmlsubmenu">
                    <c:forEach var="menu" items="${UserAndMenuJSONArray}">
                        <div title="${menu.Description}"
                             onclick="AddTabMenu('${menu.MenuId}','${menu.NavigateUrl}','${menu.FullName}','${menu.Img}','true')"
                             class="shortcuticons">
                            <b class="b1"></b><b class="b2 d1"></b><b class="b3 d1"></b><b class="b4 d1"></b>

                            <div class="b d1 k">
                                <img src="/skins/Default/images/32/${menu.Img}" alt=""/><br/>${menu.FullName}
                            </div>
                            <b class="b4b d1"></b><b class="b3b d1"></b><b class="b2b d1"></b><b class="b1b"></b>
                        </div>
                    </c:forEach>
               </div>
            </div>
            <div title="逾期未签到记录" style="height: 300px;" collapsible="true">
                <table id="tt_NoSignRecord"></table>
            </div>
            <div title="逾期未续订记录" style="height: 300px;" collapsible="true">
                <table id="tt_NoRenewRecord"></table>
            </div>
        </div>
    </div>
<div id="tt">
    <a href="javascript:void(0)" onclick="fun_SetPersonalShortcuts();">
        <img alt="" src="/js/EasyUI/themes/icons/settings.png">
    </a>
</div>

</body>
<script type="text/javascript">
    $(function () {
        $('#pp').portal({
            border: false,
            fit: true
        });
        $('#htmlsubmenu div').hover(function () {
            $(this).find(".d1").addClass("d1select");
            $(this).find(".b1").css("background", "#ccc");
            $(this).find(".b1b").css("background", "#ccc");
            $(this).find(".b2").css("border-left", "1px solid #ccc");
            $(this).find(".b3").css("border-left", "1px solid #ccc");
            $(this).find(".b4").css("border-left", "1px solid #ccc");
            $(this).find(".b2b").css("border-left", "1px solid #ccc");
            $(this).find(".b3b").css("border-left", "1px solid #ccc");
            $(this).find(".b4b").css("border-left", "1px solid #ccc");
            $(this).find(".b").css("border-left", "1px solid #ccc");
            $(this).find(".b2").css("border-right", "1px solid #ccc");
            $(this).find(".b3").css("border-right", "1px solid #ccc");
            $(this).find(".b4").css("border-right", "1px solid #ccc");
            $(this).find(".b2b").css("border-right", "1px solid #ccc");
            $(this).find(".b3b").css("border-right", "1px solid #ccc");
            $(this).find(".b4b").css("border-right", "1px solid #ccc");
            $(this).find(".b").css("border-right", "1px solid #ccc");
        }, function () {
            $(this).find(".d1").removeClass("d1select");
            $(this).find(".b1").css("background", "#fff");
            $(this).find(".b1b").css("background", "#fff");
            $(this).find(".b2").css("border-left", "1px solid #fff");
            $(this).find(".b3").css("border-left", "1px solid #fff");
            $(this).find(".b4").css("border-left", "1px solid #fff");
            $(this).find(".b2b").css("border-left", "1px solid #fff");
            $(this).find(".b3b").css("border-left", "1px solid #fff");
            $(this).find(".b4b").css("border-left", "1px solid #fff");
            $(this).find(".b").css("border-left", "1px solid #fff");
            $(this).find(".b2").css("border-right", "1px solid #fff");
            $(this).find(".b3").css("border-right", "1px solid #fff");
            $(this).find(".b4").css("border-right", "1px solid #fff");
            $(this).find(".b2b").css("border-right", "1px solid #fff");
            $(this).find(".b3b").css("border-right", "1px solid #fff");
            $(this).find(".b4b").css("border-right", "1px solid #fff");
            $(this).find(".b").css("border-right", "1px solid #fff");
        });
        $('#tt_NoSignRecord').datagrid({
            fit: true,
            nowrap: false,
            striped: true,
            url: '/GLPT/DM/DomainNameFindNoSignRecordPage.do',
            singleSelect: true,
            remoteSort: false,
            rownumbers: true,
            idField: 'Id',
            columns: [
                [
                    {field: 'DomainName', title: '域名', width: 200, align: 'left'},
                    {field: 'Url', title: '访问地址', width: 200, align: 'left',formatter:fun_DomainName},
                    {field: 'IPv4Address', title: 'IPv4地址', width: 100, align: 'left'},
                    {field: 'WebSiteFullName', title: '网站、系统名称', width: 200, align: 'left'},
                    {field: 'BusinessTypeName', title: '业务类型', width: 80, align: 'center'},
                    {field: 'GLDepartmentName', title: '管理部门', width: 120, align: 'center'},
                    {field: 'GLYType', title: '管理员类型', width: 80, align: 'center'},
                    {field: 'GLYName', title: '管理员姓名', width: 80, align: 'center'},
                    {field: 'GLYPhone', title: '电话号码', width: 100, align: 'left'},
                    {field: 'GLYMobile', title: '手机号码', width: 100, align: 'left'},
                    {field: 'SignInInterval', title: '签到间隔天数', width: 80, align: 'center'},
                    {field: 'LastSignTime', title: '最后签到时间', width: 140, align: 'center',formatter:fun_FormatDateTime},
                    {field: 'Remarks', title: '备注', width: 200, align: 'center'}
                ]
            ],
            pagination: true
        });
        var p = $('#tt_NoSignRecord').datagrid('getPager');
        $(p).pagination({
            onBeforeRefresh: function () {
                $('#tt_NoSignRecord').datagrid('clearSelections');
                $('#tt_NoSignRecord').datagrid('reload');
            }
        });
        $('#tt_NoRenewRecord').datagrid({
            fit: true,
            nowrap: false,
            striped: true,
            url: '/GLPT/DM/DomainNameFindNoRenewRecordPage.do',
            singleSelect: true,
            remoteSort: false,
            rownumbers: true,
            idField: 'Id',
            columns: [
                [
                    {field: 'DomainName', title: '域名', width: 200, align: 'left'},
                    {field: 'Url', title: '访问地址', width: 200, align: 'left',formatter:fun_DomainName},
                    {field: 'IPv4Address', title: 'IPv4地址', width: 100, align: 'left'},
                    {field: 'WebSiteFullName', title: '网站、系统名称', width: 200, align: 'left'},
                    {field: 'BusinessTypeName', title: '业务类型', width: 80, align: 'center'},
                    {field: 'GLDepartmentName', title: '管理部门', width: 120, align: 'center'},
                    {field: 'GLYType', title: '管理员类型', width: 80, align: 'center'},
                    {field: 'GLYName', title: '管理员姓名', width: 80, align: 'center'},
                    {field: 'GLYPhone', title: '电话号码', width: 100, align: 'left'},
                    {field: 'GLYMobile', title: '手机号码', width: 100, align: 'left'},
                    {field: 'RenewInterval', title: '续订间隔天数', width: 80, align: 'center'},
                    {field: 'LastRenewTime', title: '最后续订时间', width: 140, align: 'center',formatter:fun_FormatDateTime},
                    {field: 'Remarks', title: '备注', width: 200, align: 'center'}
                ]
            ],
            pagination: true
        });
        var p1 = $('#tt_NoRenewRecord').datagrid('getPager');
        $(p1).pagination({
            onBeforeRefresh: function () {
                $('#tt_NoRenewRecord').datagrid('clearSelections');
                $('#tt_NoRenewRecord').datagrid('reload');
            }
        });
        $("#tmpDiv").hide();
    });
    function remove() {
        $('#pp').portal('remove', $('#pgrid'));
        $('#pp').portal('resize');
    }

    var fun_SetPersonalShortcuts = function () {
        var url = "/GLPT/MySpacePersonalShortcuts.do?IframeId=${IframeId}";
        top.openDialog(url, 'MySpacePersonalShortcuts', '设置快捷操作', 500, 400, 50, 50);
    }

    var fun_FormatDateTime= function(val, row) {
        var dateTime1=val.split(".");
        return dateTime1[0];
    }

    var fun_DomainName = function (val, row) {
        if(val===undefined){
            return "";
        }else{
            return "<a href=\""+val+"\" target=\"_blank\">"+val+"</a>";
        }
    }

</script>
</html>