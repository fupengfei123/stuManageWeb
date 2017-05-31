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

<title>添加科目</title>

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
</head>

<body>
	<div style="width:500px;margin:50px auto">
		<form action="subject?type=add" method="post" class="form-horizontal"
			role="form">
			<div class="form-group">
				<label class="col-sm-2 control-label">科目名：</label>
				<div class="col-sm-6">
					<input type="text" name="name" class="form-control"
						placeholder="请输入科目名称" />
				</div>
			</div>

			<div class="form-group" style="width:200px;margin-left:170px">
				<input type="submit" class="btn btn-primary" value="保存" />

			</div>
		</form>
	</div>
</body>
</html>
