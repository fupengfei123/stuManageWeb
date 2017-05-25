<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<title>头部</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<style type="text/css">
*{
margin:0;
padding:0;
}
#title{
width:1024px;
background-color:#337ab7;
height:60px;
font-size:40px;
color:#ccc;
font-weight: bold;
text-align:center;
}
</style>
</head>
<body>
	<div id="title">教务管理系统</div>
</body>
</html>
