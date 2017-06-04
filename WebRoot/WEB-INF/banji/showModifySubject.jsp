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
	height: 480px;
	margin: 50px auto;
}
</style>
<%
	List<Subject> subs = (List<Subject>) request.getAttribute("subs");
	List<Banji> bjs = (List<Banji>) request.getAttribute("bjs");
	Banji bj = (Banji) request.getAttribute("bj");
	String bjName = bj.getName();
	int bjId = bj.getId();
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
					window.location.href = "banji?type=showModifySubject";
				}
			} else {
				alert("请选择需要删除的科目！");
				window.location.href = "banji?type=showModifySubject";
				}
		});
		$("#bjSelect").change(function() {
			window.location.href = "banji?type=showModifySubject&bjId="+ $("#bjSelect").val();
			});
	});
</script>

</head>

<body>
	<div id="a">
		<div
			style="width:700px;text-align:center">
			<h1>科目管理页</h1>
		</div>
		<div
			style="width:200px; font-size: 30px;margin-left: 50px;">
			<select id="bjSelect" name="bjId" class="form-control">
				<%
					for (int i = 0; i < bjs.size(); i++) {
				%>
				<option
					<%if (bj.getId() == bjs.get(i).getId()) {
					out.print("selected");
				}%>
					value="<%=bjs.get(i).getId()%>"><%=bjs.get(i).getName()%></option>

				<%
					}
				%>
			</select>
		</div>
		<table style="width:600px;margin:20px auto"
			class="table table-striped table-bordered table-hover table-condensed ">

			<tr align=center class="info">
				<td>id</td>
				<td>科目名</td>
			</tr>

			<%
				for (Subject sub : subs) {
			%>
			<tr align=center data-id="<%=sub.getId()%>">
				<td class="active"><%=sub.getId()%></td>
				<td class="success"><%=sub.getName()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<div style="margin-left:180px;float:left">
			<button id="add" type="button" class="btn btn-primary">增加</button>
		</div>
		<div style="margin-left:100px;float:left">
			<button id="delete" type="button" class="btn btn-danger">删除</button>
		</div>
	</div>
</body>
</html>
