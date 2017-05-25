<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="entity.*" %>

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

<title>学生管理系统</title>

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<script src="bootstrap/js/jquery.min.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>

<style>
#a {
	width: 700px;
	height: 450px;
	border: solid red 2px;
	margin: 70px auto;
}
</style>

<script>
	$(document)
			.ready(
					function() {
						var selectId = 0;
						$("#add").click(function() {
							window.location.href = "student?type=showAdd";
						});
						$("#modify")
								.click(
										function() {
											if (selectId != 0) {
												window.location.href = "student?type=showModify&id="
														+ selectId;
											} else {
												alert("请选择需要修改的学生！");
												window.location.href = "student?type=show";
											}
										});
						$("tr").click(function() {
							$("tr").removeClass("info");
							$(this).addClass("info");
							selectId = $(this).data("id");
						});
						$("#delete")
								.click(
										function() {
											if (selectId != 0) {
												if (confirm("确定删除？")) {
													window.location.href = "student?type=delete&id="
															+ selectId;
												} else {
													alert("取消删除");
													window.location.href = "student?type=show";
												}
											} else {
												alert("请选择需要删除的学生！");
												window.location.href = "student?type=show";
											}
										});
					});
</script>

</head>

<body>
	<%
		List<Banji> bjList = (List<Banji>) request.getAttribute("bjs");
		List<Student> list = (List<Student>) request.getAttribute("stus");
	//	Student condition=(Student)request.getAttribute("condition");
	%>
	<div id="a">
		<div
			style="position:absolute;width:700px;height:70px;text-align:center">
			<h1>学生管理页</h1>
		</div>
		<div
			style="position:absolute; border:red solid 1px;width:700px;margin-top:70px"></div>

		<div>
			<form action="student?type=search" method="post">
				<input type="hidden" name="type" value="search" />
				<div 
				style="position:absolute; width:700px;height:100px;line-height:25px; margin-top:-80px;margin-left:40px;">
					姓名：<input type="text" style="width:80px;margin-top:30px;"
						name="name" /> 年龄：<input type="text" name="age"
						style="width:80px" /> 性别：<input type="text" name="gender"
						style="width:80px" /> 班级：<select name="banji"
						class="form-control" style="width:120px;margin-left:420px;margin-top:-32px;">
						<option value="-1">所有班级</option>
						<%
							for (int i = 0; i < bjList.size(); i++) {
						%>
						<option  selected value="<%=bjList.get(i).getId()%>"><%=bjList.get(i).getName()%></option>
						<%
							}
						%>
					</select> <input type="submit" class="btn btn-primary"
						style="margin-left:560px;margin-top:-35px;" value="查询" />
					</div>
			</form>
		</div>
		<%
			int ye = (Integer) request.getAttribute("page");
			int maxPage = (Integer) request.getAttribute("maxPage");
		%>
		<div style="margin-top: -50px;">
			<table style="width:600px;margin:190px auto;"
				class="table table-striped table-bordered table-hover table-condensed ">
				<tr align=center class="info">
					<td>id</td>
					<td>姓名</td>
					<td>年龄</td>
					<td>性别</td>
					<td>班级</td>
				</tr>
				<%
					for (Student stu : list) {
				%>
				<tr align=center data-id="<%=stu.getId()%>">
					<td class="active"><%=stu.getId()%></td>
					<td class="success"><%=stu.getName()%></td>
					<td class="danger"><%=stu.getAge()%></td>
					<td class="warning"><%=stu.getGender()%></td>
					<td class="warning"><%=stu.getBj().getName()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
		<div style="position:absolute;margin-left:194px;margin-top:-180px">
			<ul class="pagination">

				<li><a href="student?type=show&page=1">首页</a>
				</li>

				<li <%if (ye <= 1) {%> class="disabled" <%}%>><a
					href="student?type=show&page=<%=ye - 1%>">《上一页</a>
				</li>

				<%
					int end = ye + 4;
					int begin = ye;
					if (ye + 4 >= maxPage) {
						end = maxPage;
						begin = end - 4;
					}

					for (int i = begin; i <= end; i++) {
				%>
				<li <%if (ye == i) {%> class="active" <%}%>><a
					href="student?type=show&page=<%=i%>" style="width:38px"><%=i%></a>
				</li>
				<%
					}
				%>
				<li <%if (ye >= maxPage) {%> class="disabled" <%}%>><a
					href="student?type=show&page=<%=ye + 1%>">下一页》</a></li>
				<li><a href="student?type=show&page=<%=maxPage%>">尾页</a>
				</li>
			</ul>
		</div>
		<div style="position:absolute;margin-top:-105px">
			<div style="margin-left:150px;float:left">
				<button id="add" type="button" class="btn btn-primary">增加</button>
			</div>
			<div style="margin-left:100px;float:left">
				<button id="modify" type="button" class="btn btn-primary">修改</button>
			</div>
			<div style="margin-left:100px;float:left">
				<button id="delete" type="button" class="btn btn-danger">删除</button>
			</div>
		</div>
	</div>

</body>
</html>
