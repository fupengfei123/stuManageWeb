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

<title>班级管理系统</title>

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

<script>
	$(document).ready(function() {
		var selectId = 0;
		var selectName = "";
		$("#add").click(function() {
			window.location.href = "banji?type=showAdd";
		});
		$("#modify").click(function() {
			if (selectId != 0) {
				window.location.href = "banji?type=showModify&id="+ selectId;
			} else {
				alert("请选择需要修改的班级！");
				window.location.href = "banji?type=show";
			}
		});
		$("tr").click(function() {
			$("tr").removeClass("info");
			$(this).addClass("info");
				selectId = $(this).data("id");
				selectName = $(this).data("name");
			});
		$("#delete").click(function() {
			if (selectId != 0) {
				if (confirm("确定删除？")) {
					window.location.href = "banji?type=delete&id="+ selectId;
				} else {
				    alert("取消删除");
					window.location.href = "banji?type=show";
					}
				} else {
					alert("请选择需要删除的班级！");
					window.location.href = "banji?type=show";
				}
			});
		$("#modifySubject").click(function() {
			if (selectId != 0) {
				window.location.href = "banji?type=showModifySubject&bjId="+ selectId;
			} else {
				alert("请选择需要管理课程的班级！");
				window.location.href = "banji?type=show";
			}
		});
		});
</script>

</head>

<body>
	<div id="a">
	<div style="position:absolute;margin-top:10px;width:700px;height:100px;text-align:center" >
	<h1>班级管理页</h1></div>
		<div
			style="position:absolute; border:red solid 1px;width:700px;margin-top:100px"></div>

		<div>
			<form action="banji" method="post">
				<input type="hidden" name="type" value="search" />
				<div style="position:absolute; width:700px;height:100px;line-height:25px; margin-top:85px;text-align:center;">
					班级名：<input type="text" style="width:120px;margin-top:30px;" name="name" />
					<input type="submit" class="btn btn-primary"
						style="margin-left:48px" value="查询"/>
						</div>
			</form>
		</div>
		<%
			List<Banji> list = (List<Banji>) request.getAttribute("bjs");
				int ye = (Integer) request.getAttribute("page");
			int maxPage = (Integer) request.getAttribute("maxPage");
		%>
			<table style="width:600px;margin:190px auto"
				class="table table-striped table-bordered table-hover table-condensed ">
				<tr align=center class="info">
					<td>id</td>
					<td>班级名</td>
				</tr>
				<%
					for (Banji bj : list) {
				%>
				<tr align=center data-id="<%=bj.getId()%>" data-name="<%=bj.getName()%>">
					<td class="active"><%=bj.getId()%></td>
					<td class="success"><%=bj.getName()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
		<div style="position:absolute;margin-left:194px;margin-top:-180px">
			<ul class="pagination">

				<li><a href="banji?type=show&page=1">首页</a>
				</li>

				<li <%if (ye <= 1) {%> class="disabled" <%}%>><a
					href="banji?type=show&page=<%=ye - 1%>">《上一页</a>
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
					href="banji?type=show&page=<%=i%>" style="width:38px"><%=i%></a>
				</li>
				<%
					}
				%>
				<li <%if (ye >= maxPage) {%> class="disabled" <%}%>><a
					href="banji?type=show&page=<%=ye + 1%>">下一页》</a></li>
				<li><a href="banji?type=show&page=<%=maxPage%>">尾页</a>
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
			<div style="margin-left:55px;float:left">
				<button id="modifySubject" type="button" class="btn btn-primary">管理课程</button>
			</div>
		</div>
</body>
</html>
