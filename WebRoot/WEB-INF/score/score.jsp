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

<title>显示成绩页</title>

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
		List<Score> scoreList = (List<Score>) request.getAttribute("scoreList");
		List<Banji> bjList = (List<Banji>) request.getAttribute("bjList");
		Score condition = (Score)request.getAttribute("condition");
		String name = "";
		int bjId = 0;
		if(condition!=null){
			name= condition.getStu().getName();
			bjId = condition.getStu().getBj().getId();
		}
		int subId = 0;
		if(condition!=null){
			subId = condition.getSub().getId();
		}
		int ye = (Integer) request.getAttribute("page");
		int maxPage = (Integer) request.getAttribute("maxPage");
		List<Subject> subList = (List<Subject>)request.getAttribute("subList");
	%>
	<div id="a">
		<div
			style="position:absolute;width:700px;height:70px;text-align:center">
			<h1>成绩管理页</h1>
		</div>
		<div
			style="position:absolute; border:red solid 1px;width:700px;margin-top:70px"></div>

		<div>
			<form action="score?type=search" method="post">
				<input type="hidden" name="type" value="search" />
				<div style="position:absolute; width:700px;height:100px;line-height:25px; margin-top:50px;margin-left:50px;">
					姓名：<input type="text" style="width:80px;margin-top:30px;"value="<%=name%>" name="name" /> 
					班级：<select name="banji"class="form-control" style="width:120px;margin-left:170px;margin-top:-32px;">
						<option value="-1">所有班级</option>
						<%
							for (int i = 0; i < bjList.size(); i++) {
						%>
						<option value="<%=bjList.get(i).getId()%>" <%if(bjList.get(i).getId()==bjId){%>selected="selected"<%}%>><%=bjList.get(i).getName()%></option>
						<%
							}
						%>
					</select>
					<div style="position:absolute;margin-left:300px;margin-top:-28px;">
					科目：<select name="subject"class="form-control" style="width:120px;margin-left:40px;margin-top:-32px;">
						<option value="-1">所有科目</option>
						<%
							for (int i = 0; i < subList.size(); i++) {
						%>
						<option value="<%=subList.get(i).getId()%>" <%if(subList.get(i).getId()==subId){%>selected="selected"<%}%>><%=subList.get(i).getName()%></option>
						<%
							}
						%>
					</select>
					</div>
					<input type="submit" class="btn btn-primary"
						style="margin-left:500px;margin-top:-35px;" value="查询" />
					</div>
			</form>
		</div>
		<div style="margin-top: -50px;">
			<table style="width:600px;margin:190px auto;"
				class="table table-striped table-bordered table-hover table-condensed ">
				<tr align=center class="info">
					<td>id</td>
					<td>姓名</td>
					<td>班级</td>
					<td>科目</td>
					<td>成绩</td>
				</tr>
				<%
					for (Score sc : scoreList) {
				%>
				<tr align=center>
					<td class="active"><%=sc.getStu().getId()%></td>
					<td class="success"><%=sc.getStu().getName()%></td>
					<td class="danger"><%=sc.getStu().getBj().getName()%></td>
					<td class="warning"><%=sc.getSub().getName()%></td>
					<td class="warning"><%=sc.getScore()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
		<div style="position:absolute;margin-left:194px;margin-top:-180px">
			<ul class="pagination">

				<li><a href="score?type=search&page=1">首页</a>
				</li>

				<li <%if (ye <= 1) {%> class="disabled" <%}%>><a
					href="score?type=search&page=<%=ye - 1%>">《上一页</a>
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
					href="score?type=search&page=<%=i%>" style="width:38px"><%=i%></a>
				</li>
				<%
					}
				%>
				<li <%if (ye >= maxPage) {%> class="disabled" <%}%>><a
					href="score?type=search&page=<%=ye + 1%>">下一页》</a></li>
				<li><a href="score?type=search&page=<%=maxPage%>">尾页</a>
				</li>
			</ul>
		</div>
	</div>

</body>
</html>
