<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>后台管理中心</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
</head>
<body style="background-color: #f2f9fd;">
	<div class="header bg-main">
		<div class="logo margin-big-left fadein-top">
			<h1>
				简易学生信息管理系统
			</h1>
		</div>
		<div class="head-l">
			<a class="button button-little bg-green" href="welcome.jsp"
				target="right"> <span class="icon-home"></span>管理首页
			</a> &nbsp;&nbsp; <a class="button button-little bg-red" href="user?opr=logout">
				<span class="icon-power-off"></span>退出登录
			</a>
			Welcome! ${sessionScope.user.name}!
		</div>
	</div>
	<div class="leftnav">
		<div class="leftnav-title">
			<strong><span class="icon-list"></span>菜单列表</strong>
		</div>
		<h2>
			<span class="icon-user"></span>班级管理
		</h2>
		<ul>
			<li><a href="klass?opr=initadd" target="right"><span
					class="icon-caret-right"></span>增加班级</a></li>
			<li><a href="klass?opr=query" target="right"><span
					class="icon-caret-right"></span>班级列表</a></li>
		</ul>
		<h2>
			<span class="icon-pencil-square-o"></span>学生管理
		</h2>
		<ul>
			<li><a href="student?opr=initadd" target="right"><span
					class="icon-caret-right"></span>增加学生</a></li>
			<li><a href="student?opr=query" target="right"><span
					class="icon-caret-right"></span>学生列表</a></li>
		</ul>

		<h2>
			<span class="icon-user"></span>课程管理
		</h2>
		<ul>
			<li><a href="klass/klass_add.jsp" target="right"><span
					class="icon-caret-right"></span>增加课程</a></li>
			<li><a href="klass/klass_list.jsp" target="right"><span
					class="icon-caret-right"></span>课程列表</a></li>
		</ul>
		<h2>
			<span class="icon-pencil-square-o"></span>个人中心
		</h2>
		<ul>
			<li><a href="user/avatar_update.jsp" target="right"><span
					class="icon-caret-right"></span>修改头像</a></li>
		</ul>
	</div>
	<div class="admin">
		<iframe src="welcome.jsp" name="right" width="100%" height="100%"></iframe>
	</div>
</body>
</html>