<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>调研报告</title>
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="/assets/js/jquery.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
</head>
<body>

<ul id="myTab" class="nav nav-tabs">
    <c:forEach items="${data}" var="item" varStatus="i">
        <li <c:if test="${i.first}">class="active"</c:if>></li>
        <a href="#tab${item.value.content.id}" data-toggle="tab">
                ${item.key}
        </a>
        </li>
    </c:forEach>
</ul>
<div id="myTabContent" class="tab-content">
    <c:forEach items="${data}" var="item" varStatus="i">
        <div class="tab-pane fade <c:if test="${i.first}">in active</c:if>" id="tab${item.value.content.id}">
            <input type="hidden" name="templateId" value="${item.value.content.templateId}"/>
                ${item.value.template}
        </div>
    </c:forEach>

</div>

</body>
</html>
