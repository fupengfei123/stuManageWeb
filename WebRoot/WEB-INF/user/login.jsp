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

<title>login</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<style>
#main {
	width: 300px;
	height: 300px;
	background-image: url("D:/images/imagegroup/green.jpg");
	margin: 50px auto;
	border: solid red 1px;
}
</style>
<script type="text/javascript">
	if (self != top) {
		top.location = self.location;
	}
	$(document).ready(
			function() {
				$("#image").click(
						function() {
							$(this).attr("src",
									"user?type=randomImage&" + Math.random());
						});
				$("#login").click(
						function() {
							$.ajax({
								type : "post",
								url : "user?type=doLogin",
								data : $("form").serialize(),
								dataType : "text",
								success : function(data) {
									if (data == "success") {
										location.href = "main";
									} else {
										$(mes).html(data);
										$("#image").attr(
												"src",
												"user?type=randomImage&"
														+ Math.random());
									}
								}
							});
						});
			});
</script>

</head>

<body>
	<div style="width:1350px; height:635px;background-image: url('green.jpg');">
	<div style="position:absolute; width:500px;margin-left: 450px;margin-top: 140px">
		<form id="form" action="user?type=doLogin" method="post"
			class="form-horizontal" role="form">
			<div class="form-group">
				<label class="col-sm-2 control-label">用户名：</label>
				<div class="col-sm-6">
					<input type="text" name="username" class="form-control"
						placeholder="请输入用户名" />
				</div>
				<div id="mes" style="height:40px"></div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">密码：</label>
				<div class="col-sm-6">
					<input type="password" name="password" class="form-control"
						placeholder="请输入密码" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">验证：</label>
				<div class="col-sm-6">
					<input type="password" name="random" class="form-control"
						placeholder="请输入验证码" />
				</div>
				<div class="col-sm-4">
					<img id="image" src="user?type=randomImage" />
				</div>
			</div>

			<div class="form-group" style="width:200px;margin-left:170px">
				<input id="login" type="button" class="btn btn-primary" value="登录" />

			</div>
		</form>
	</div>
	</div>
</body>
</html>
