<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<base href="${base}/" />
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script type="text/javascript">
    window.onload=function(){
    	document.getElementById("deptid").value="${employee.deptId}";
    }
</script>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>增加员工</strong>
		</div>
		<div class="body-content">
			<form method="post" class="form-x" action="emp">
				<input type="hidden" name="opr" value="add" />
				<div class="form-group">
					<div class="label">
						<label>员工姓名：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" name="name" value="${employee.name}" />
						<div class="tips">${errors.namemsg}</div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>出生年月：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" name="birthdate" value="${employee.birthdate}"/>
						<div class="tips">${errors.birthdatemsg}</div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>所在部门：</label>
					</div>
					<div class="field">
						<select name="deptid" id="deptid" class="input w50">
							<option value="0">请选择所在部门</option>
							<c:forEach items="${departments}" var="department">
								<option value="${department.id}">${department.name}</option>
							</c:forEach>
					</select>
					<div class="tips">${errors.deptidmsg}</div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<button class="button bg-main icon-check-square-o" type="submit">
							提交</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>