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
</script>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>上传头像</strong>
		</div>
		<div class="body-content">
			<form method="post" class="form-x" action="user"
				enctype="multipart/form-data">
				<input type="hidden" name="opr" value="updateAvatar" />
				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<img src='<c:if test="${empty user.avatar}">images/avatar/default.jpg</c:if><c:if test="${not empty user.avatar}">${user.avatar}</c:if>' width="100" height="100"
							title="${user.name}" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>头像：</label>
					</div>
					<div class="field">
						<input type="file" class="input w50" name="avatar" />
						<div class="tips">${errors.namemsg}</div>
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