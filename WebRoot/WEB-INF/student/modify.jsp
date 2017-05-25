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
<title>My JSP 'add.jsp' starting page</title>

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

			})

		})
	})
</script>
</head>

<body>
	<%
		List<Banji> bjList = (List<Banji>) request.getAttribute("bjs");
		Student stu = (Student) request.getAttribute("stu");
	%>
	<div style="width:500px;margin:50px auto">
		<form action="student?type=modify" method="post"
			class="form-horizontal" role="form">
			<input name="id" type="hidden" value="<%=stu.getId()%>" />
			<div class="form-group">
				<label class="col-sm-2 control-label">姓名：</label>
				<div class="col-sm-6">
					<input type="text" name="name" class="form-control"
						placeholder="请输入姓名" value="<%=stu.getName()%>" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">性别：</label>
				<div class="col-sm-6 radio">
					<input type="text" name="gender" value="<%=stu.getGender()%>" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">年龄：</label>
				<div class="col-sm-6">
					<input type="text" name="age" class="form-control"
						placeholder="请输入年龄" value="<%=stu.getAge()%>" />
				</div>
				<div id="msg" class="col-sm-4" style="line-height:32px;color:red"></div>
			</div>

			<label class="col-sm-2 control-label" style="margin-left: -30px">班级名：</label>
			<label class="col-sm-6"> <select name="banji"
				class="form-control" style="width:215px;">
					<%
						for (int i = 0; i < bjList.size(); i++) {
					%>
					<option <%if (bjList.get(i).getId() == stu.getBj().getId()) {%>
						selected <%}%> value="<%=bjList.get(i).getId()%>"><%=bjList.get(i).getName()%></option>
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
