<%@page import="java.lang.Character.Subset"%>
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

<title>添加科目</title>

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
</head>

<body>
	<%
		String bjName = request.getParameter("bjName");
		int bjId = Integer.parseInt(request.getParameter("bjId"));
		List<Subject> subs = (List<Subject>) request.getAttribute("subs");
	%>
	<div style="width:500px;margin:50px auto">
	<div style="font-size: 30px;margin-left: 50px;margin-top:100px;"><%=bjName%></div>
		<form action="banji?type=addSubject" method="post" class="form-horizontal"
			role="form">
			<input type="hidden" name="bjId" value="<%=bjId%>"/>
			<div class="form-group">
				<label class="col-sm-2 control-label">科目名：</label>
				<div style="width:300px">
					<select name="subId" class="form-control">
						<%
							for (int i = 0; i < subs.size(); i++) {
						%>
						<option value="<%=subs.get(i).getId()%>"><%=subs.get(i).getName()%></option>

						<%
							}
						%>
					</select>
				</div>
			</div>

			<div class="form-group" style="width:200px;margin-left:170px">
				<input type="submit" class="btn btn-primary" value="保存" />

			</div>
		</form>
	</div>
</body>
</html>
