<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<base href="${base}/" />
<title></title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
</head>
<body>
	<form method="post" action="">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder"> 班级列表</strong>
			</div>
			<div class="padding border-bottom">
				<button type="button" class="button border-yellow"
					onclick="javascript:location='klass?opr=initadd'">
					<span class="icon-plus-square-o"></span>增加班级
				</button>
			</div>
			<table class="table table-hover text-center">
				<tr>
					<th>序号</th>
					<th>班级名称</th>
					<th>学生数</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${pageBean.data}" var="klass" varStatus="s">
					<tr>
						<td>${s.count}</td>
						<td>${klass.name}</td>
						<td>${klass.stuNum}</td>
						<td>
						    <div class="button-group">
								<a class="button border-main"
									href="dept?opr=initupdate&id=${klass.id}"><span
									class="icon-edit"></span> 修改</a> <a class="button border-red"
									href="javascript:void(0)"
									onclick="javascript:location.href='klass?opr=del&id=${klass.id}'"><span
									class="icon-trash-o"></span> 删除</a>
							</div>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="8">
						<div class="pagelist">
						    <c:forEach begin="1" end="${pageBean.totalPage}" var="p">
						    	<a href="">${p}</a>
						    </c:forEach>
						    <select>
						    	 <c:forEach begin="1" end="${pageBean.totalPage}" var="p">
						    		<option>${p}</option>
						    	 </c:forEach>
						    </select>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>