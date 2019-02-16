<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title></title>
<base href="${base}/" />
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>修改部门</strong>
		</div>
		<div class="body-content">
			<form method="post" class="form-x" action="dept">
				<input type="hidden" name="opr" value="update" />
				<input type="hidden" name="id" value="${department.id}" />
				<div class="form-group">
					<div class="label">
						<label>部门名称：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="${department.name}" name="name" />
						<div class="tips">${msg}</div>
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