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
<title></title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<style type="text/css">
#accordion {
margin-left:35px;
	margin-top: 50px;
}

#accordion a {
	text-decoration: none;
}

.panel-title,.panel-body {
	text-align: center;
}

.panel-body a {
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="panel-group" id="accordion">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion" href="#student">学生管理</a>
				</h4>
			</div>
			<div id="student" class="panel-collapse collapse in">
				<div class="panel-body">
					<a href="student?type=search" target="show">显示学生</a>
				</div>
				<div class="panel-body">
					<a href="student?type=showAdd" target="show">增加学生</a>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion" href="#banji">班级管理</a>
					</h4>
				</div>
				<div id="banji" class="panel-collapse collapse">
					<div class="panel-body">
						<a href="banji" target="show">显示班级</a>
					</div>
					<div class="panel-body">
						<a href="banji?type=showAdd" target="show">增加班级</a>
					</div>
					<div class="panel-body">
						<a href="banji?type=showModifySubject" target="show">管理课程</a>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#subject">科目管理</a>
						</h4>
					</div>
					<div id="subject" class="panel-collapse collapse">
						<div class="panel-body">
							<a href="subject" target="show">显示科目</a>
						</div>
						<div class="panel-body">
							<a href="subject?type=showAdd" target="show">增加科目</a>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion" href="#score">成绩管理</a>
							</h4>
						</div>
						<div id="score" class="panel-collapse collapse">
							<div class="panel-body">
								<a href="score" target="show">显示成绩</a>
							</div>
							<div class="panel-body">
								<a href="score?type=showInput" target="show">录入成绩</a>
							</div>
						</div>
					</div>
</body>
</html>
