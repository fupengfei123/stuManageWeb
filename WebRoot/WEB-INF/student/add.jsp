<%@ page language="java" import="java.util.*,entity.*"
	pageEncoding="utf-8"%>
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

<title>新增学生</title>

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("form").submit(function() {

			var age = $("[name=age]").val();
			//用正则表达式对年龄进行限制
			var reg = /^[0-9]+$/;

			if (reg.test(age)) {
				return true;
			}
			$("#msg").html("年龄只能是数字!");
			return false;
			$("#msg").focus(function() {
				$("#msg").html("");

			});
		});
	});
</script>
</head>

<body>
	<%
		List<Banji> bjList = (List<Banji>) request.getAttribute("bjs");
	%>
	<div style="width:400px;margin:50px auto">
		<form action="student?type=add" method="post"
			enctype="multipart/form-data" class="form-horizontal" role="form">
			<div class="form-group">
				<label class="col-sm-2 control-label">姓名：</label> <label
					class="col-sm-6"> <input type="text" name="name"
					class="form-control" placeholder="请输入姓名" /> </label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">性别：</label> <label
					class="col-sm-6 radio"> <label> <input type="radio"
						name="gender" value="男" />男</label> <label> <input type="radio"
						name="gender" value="女" />女</label> </label>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">年龄：</label> <label
					class="col-sm-6"> <input type="text" name="age"
					class="form-control" placeholder="请输入年龄" /> </label>
				<div id="msg" class="col-sm-4" style="line-height:32px;color:red"></div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">照片：</label>
				<label class="col-sm-6">
					<input type="file" name="photo" class="form-control" />
				</label>
				<div id="msg" class="col-sm-4" style="line-height:32px;color:red"></div>
			</div>

			<label class="col-sm-2 control-label" style="margin-left: -30px">班级名：</label>
			<label class="col-sm-6"> <select name="banji"
				class="form-control" style="width:215px;">
					<%
						for (int i = 0; i < bjList.size(); i++) {
					%>
					<option value="<%=bjList.get(i).getId()%>"><%=bjList.get(i).getName()%></option>
					<%
						}
					%>
			</select> </label>

			<div class="form-group" style="width:200px;margin-left:170px">
				<input type="submit" class="btn btn-primary" value="保存" />

			</div>
		</form>
	</div>
</body>
</html>
