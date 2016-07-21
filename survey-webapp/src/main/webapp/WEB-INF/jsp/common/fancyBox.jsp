<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
</head>
<body>
	<h3>请选择项目 ：</h3>
	 <ul>
		  <c:forEach var="menu" items="${menuData.data}" varStatus="status">
		      <c:if test="${menu.children != null && menu.name == name}">	
	      		<c:forEach var="menu1" items="${menu.children}" varStatus="status1">
	      			<c:if test="${menu1.children != null }">
	      				<c:forEach var="menu2" items="${menu1.children}" varStatus="status2">
	      					<a href="${menu2.href}" title="${menu2.name}"><li>${menu2.name}</li></a>
	      				</c:forEach>
	      			</c:if>
	      			<c:if test="${menu1.children == null }">
		      			<a href="${menu1.href}" title="${menu1.name}"><li>${menu1.name}</li></a>
	      			</c:if>
	      		</c:forEach>
		      </c:if>
		      <c:if test="${menu.children == null && menu.name == name}">	
	      			<a href="${menu.href}" title="${menu.name}"><li>${menu.name}</li></a>
		      </c:if>
		  </c:forEach>
		  <li></li>
	  </ul>
</body>
</html>