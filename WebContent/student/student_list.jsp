<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title></title>
<base href="${base}/" />
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script type="text/javascript">
	window.onload=function(){
	  document.getElementById("page").value="${pageBean.page}";

	  document.getElementById("klassId").value="${student.klassId}";
		}

	function del(id){
		if(confirm("确认删除吗?")){
			location.href="student?opr=del&id="+id;		
		}
	}
</script>
</head>
<body>

	<div class="panel admin-panel">
		<div class="panel-head">
			<strong class="icon-reorder"> 学生列表</strong>
		</div>
		<div class="padding border-bottom">
  		 		<button type="button"  class="button border-green" id="checkall"><span class="icon-check"></span> 全选</button>
         	 	<button type="submit" class="button border-red"><span class="icon-trash-o"></span> 批量删除</button>
   				<button type="button" class="button border-yellow" onclick="javascript:location='student?opr=initadd'"><span class="icon-plus-square-o"></span>增加学生</button>
  		</div>
  		
		<div class="padding border-bottom">
			<form method="post" action="student">
				<input type="hidden" name="opr" value="query" />
				<ul class="search" style="padding-left:10px;">
					<li>学生姓名：<input type="text" placeholder="请输入学生姓名"
						name="name" class="input" value="${student.name}"
						style="width:150px; line-height:17px;display:inline-block" />
					</li>
					<li>出生年月：<input type="text" 
						name="name" class="input" value=""
						style="width:150px; line-height:17px;display:inline-block" />-
						<input type="text" 
						name="name" class="input" value=""
						style="width:150px; line-height:17px;display:inline-block" />
					</li>
					<li><select name="klassId" class="input" id="klassId" 
						style="width:200px; line-height:17px;">
							<option value="0">不限</option>
							<c:forEach items="${klasses}" var="klass">
								<option value="${klass.id}">${klass.name}</option>
							</c:forEach>
					</select></li>
					<li><button class="button border-main icon-search">搜索</button></li>
				</ul>
			</form>
		</div>
		<table class="table table-hover text-center">
			<tr>
				<th>选择</th>
				<th>学生姓名</th>
				<th>出生年月</th>
				<th>所在班级</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${pageBean.data}" var="student" varStatus="s">
				<tr>
					<td><input type="checkbox"></td>
					<td>${student.name}</td>
					<td>${student.birthdate}</td>
					<td>${student.klassName}</td>
					<td><div class="button-group">
							<a class="button border-main" href="student?opr=initupdate&id=${student.id}"><span class="icon-edit"></span>
								修改</a> <a class="button border-red" href="javascript:void(0)"
								onclick="del('${student.id}')"><span class="icon-trash-o"></span> 删除</a>
						</div></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="8">
					<div class="pagelist">
					 	<c:forEach begin="1" end="${pageBean.totalPage}" var="p">
						    	<a href="student?opr=query&page=${p}&name=${student.name}&klassId=${student.klassId}">${p}</a>
						    </c:forEach>
						    <select onchange="location.href='student?opr=query&page='+this.value" id="page">
						    	 <c:forEach begin="1" end="${pageBean.totalPage}" var="p">
						    	<option value="${p}">${p}</option>
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