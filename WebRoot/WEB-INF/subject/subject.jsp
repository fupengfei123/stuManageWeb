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
	margin: 10px auto;
}
</style>

<script>
	$(document)
			.ready(
					function() {
						var selectId = 0;
						$("#add").click(function() {
							window.location.href = "subject?type=showAdd";
						});
						$("#modify")
								.click(
										function() {
											if (selectId != 0) {
												window.location.href = "subject?type=showModify&id="
														+ selectId;
											} else {
												alert("请选择需要修改的科目！");
												window.location.href = "subject?type=show";
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
													window.location.href = "subject?type=delete&id="
															+ selectId;
												} else {
													alert("取消删除");
													window.location.href = "subject?type=show";
												}
											} else {
												alert("请选择需要删除的班级！");
												window.location.href = "subject?type=show";
											}
										});
					});
</script>

</head>

<body>
	<div id="a">
		<div style="width:700px;text-align:center">
			<h1>科目管理页</h1>
			<form action="subject" method="post">
				<input type="hidden" name="type" value="search" />
				<div style="width:700px;text-align:center;">
					科目名：<input type="text" style="width:120px;"
						name="name" /> <input type="submit" class="btn btn-primary"
						style="margin-left:48px" value="查询" />
				</div>
			</form>
		</div>
		<%
			List<Subject> list = (List<Subject>) request.getAttribute("subs");
			int ye = (Integer) request.getAttribute("page");
			int maxPage = (Integer) request.getAttribute("maxPage");
		%>
		<table style="width:600px;margin:0px auto"
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

	<div style="margin-left:194px;">
		<ul class="pagination">
			<li><a href="subject?type=show&page=1">首页</a>
			</li>
			<li <%if (ye <= 1) {%> class="disabled" <%}%>><a
				href="subject?type=show&page=<%=ye - 1%>">《上一页</a>
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
				href="subject?type=show&page=<%=i%>" style="width:38px"><%=i%></a>
			</li>
			<%
				}
			%>
			<li <%if (ye >= maxPage) {%> class="disabled" <%}%>><a
				href="subject?type=show&page=<%=ye + 1%>">下一页》</a></li>
			<li><a href="subject?type=show&page=<%=maxPage%>">尾页</a>
			</li>
		</ul>
	</div>
	<div>
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
