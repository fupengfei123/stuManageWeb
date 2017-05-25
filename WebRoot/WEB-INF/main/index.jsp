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
<title>教务管理系统</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

#continer {
	width: 1024px;
	margin: 0px auto;
}
#continer #header {
	height: 60px;
	overflow: hidden;
}

#left,#right {
	height: 550px;
}
</style>
</head>

<body>
	<div id="continer">
		<div id="header" class="row">
			<iframe src="main?type=header" class="col-sm-12" scrolling="no"
				frameborder="0"></iframe>
		</div>
		<div id="main" class="row">
			<iframe src="main?type=left" scrolling="no" frameborder="0" id="left"
				class="col-sm-3"></iframe>

			<iframe src="student" scrolling="no" frameborder="0" id="right" name="show"
				class="col-sm-9"></iframe>
		</div>
		<div id="footer" class="row">
			<iframe src="main?type=footer" class="col-sm-12" scrolling="no"
				frameborder="0"></iframe>
		</div>
	</div>
</body>
</html>
