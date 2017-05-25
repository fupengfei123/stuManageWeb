<%@ page language="java" import="java.util.*,entity.*"
	pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'student.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
</head>

<body>
	<table style="width:600px;margin:20px auto" class="table table-striped table-bordered table-hover table-condensed ">
	
	<tr align=center class="info">
	<td>id</td>
	<td>姓名</td>
	<td>年龄</td>
	<td>性别</td>
	</tr>
	
		<c:forEach items="${stus}" var="stu">
		<tr align=center>
			<td class="active">${stu.id}</td>
			<td class="success">${stu.name}</td>
			<td class="danger">${stu.age}</td>
			<td class="warning">${stu.gender}</td>
		</tr>
		</c:forEach>

	</table>

</body>
</html>
