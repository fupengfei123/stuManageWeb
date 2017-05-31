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

<title>科目管理系统</title>

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>

<style>
#a {
	width: 700px;
	height: 500px;
	border: solid red 2px;
	margin: 50px auto;
}
</style>
<%
			int bjId = Integer.parseInt(request.getParameter("bjId"));
			List<Subject> list = (List<Subject>) request.getAttribute("subs");
			Banji bj = (Banji) request.getAttribute("bj");
			String bjName = bj.getName();
		%>
<script>
	$(document).ready(function() {
		var selectId = 0;
		$("#add").click(function() {
			window.location.href = "banji?type=showAddSubject&bjId=<%=bjId%>&bjName=<%=bjName%>";
		});
		$("tr").click(function() {
			$("tr").removeClass("info");
			$(this).addClass("info");
				selectId = $(this).data("id");
			});
		$("#delete").click(function() {
			if (selectId != 0) {
				if (confirm("确定删除？")) {
					window.location.href = "banji?type=deleteSubject&subId="+ selectId+"&bjId=<%=bjId%>";
				} else {
				    alert("取消删除");
					window.location.href = "banji?type=show";
					}
				} else {
					alert("请选择需要删除的科目！");
					window.location.href = "banji?type=show";
				}
			});
		});
</script>

</head>

<body>
	<div id="a">
	<div style="position:absolute;margin-top:10px;width:700px;height:100px;text-align:center" >
	<h1>科目管理页</h1></div>
		<div
			style="position:absolute; border:red solid 1px;width:700px;margin-top:100px"></div>
		
			<div style="font-size: 30px;margin-left: 50px;margin-top:100px;"><%=bjName%></div>
			<table style="width:600px;margin:40px auto"
				class="table table-striped table-bordered table-hover table-condensed ">
				
				<tr align=center class="info">
					<td>id</td>
					<td>科目名</td>
				</tr>
				
				<%
					for (Subject sub : list) {
				%>
				<tr align=center data-id="<%=sub.getId()%>">
					<td class="active"><%=sub.getId()%></td>
					<td class="success"><%=sub.getName()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
		<div style="position:absolute;margin-top:-105px">
			<div style="margin-left:150px;float:left">
				<button id="add" type="button" class="btn btn-primary">增加</button>
			</div>
			<div style="margin-left:100px;float:left">
				<button id="delete" type="button" class="btn btn-danger">删除</button>
			</div>
		</div>
</body>
</html>
