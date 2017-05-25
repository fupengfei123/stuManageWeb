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
<title>修改班级</title>

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
</head>

<body>
	<%
		Banji bj = (Banji) request.getAttribute("bj");
	%>
	<div style="width:500px;margin:50px auto">
		<form action="banji?type=modify" method="post" class="form-horizontal" role="form">
			<input name="id" type="hidden" value="<%=bj.getId()%>" />
			<div class="form-group">
				<label class="col-sm-2 control-label">班级名：</label>
				<div class="col-sm-6">
					<input type="text" name="name" class="form-control"
						placeholder="请输入班级名" value="<%=bj.getName()%>" />
				</div>
			</div>
			<div id="msg" class="col-sm-4" style="line-height:32px;color:red"></div>
	</div>

	<div class="form-group" style="width:200px;margin-left:600px">
		<input type="submit" class="btn btn-primary" value="保存" />
	</div>
	</form>
	</div>
</body>
</html>
