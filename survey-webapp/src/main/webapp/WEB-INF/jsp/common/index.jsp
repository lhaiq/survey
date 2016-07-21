<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp"/>
<body class="no-skin">

<jsp:include page="navbar.jsp"/>
<div class="main-container" id="main-container">
    <jsp:include page="left_navbar.jsp"/>
    <!-- /section:basics/sidebar 界面主体-->
    <div class="main-content">
        <%--导航--%>
        <div class="breadcrumbs" id="breadcrumbs">
            <ul class="breadcrumb" id="breadcrumbli">
                <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">主页</a></li>
                <input type="hidden" id="breadvalue112" value=""/>
            </ul>
        </div>
        <!-- /section:basics/content.breadcrumbs-->
        <div class="page-content">
            <jsp:include page="layout_setting.jsp"/>
            <!-- /.ace-settings-container -->
            <div class="page-content-area">
            </div>

            <jsp:include page="footer.jsp"/>
        </div>

    </div>

</div>

<%--调用公共JS上--%>
<jsp:include page="common_top.jsp"/>

<%--调用公共JS下--%>
<jsp:include page="common_down.jsp"/>
</body>
</html>