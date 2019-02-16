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
function validate(name){
	var xhr=new XMLHttpRequest();
	xhr.open("get", "student?opr=isexist&name="+name);
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 && xhr.status==200){
			if(xhr.responseText=="true"){
				document.getElementsByClassName("tips")[0].innerText="学生名已经存在";
			}else{
				document.getElementsByClassName("tips")[0].innerText="";
			}
		}
	}
	xhr.send();
}
</script>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>增加学生</strong>
		</div>
		<div class="body-content">
			<form method="post" class="form-x" action="student">
				<input type="hidden" name="opr" value="add" />
				<div class="form-group">
					<div class="label">
						<label>学生姓名：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" name="name" value="${student.name}" onblur="validate(this.value)" />
						<div class="tips">${errors.namemsg}</div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>出生年月：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" name="birthdate" value="${student.birthdate}"/>
						<div class="tips">${errors.birthdatemsg}</div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>所在班级：</label>
					</div>
					<div class="field">
						<select name="klassId" id="klassId" class="input w50">
							<option value="0">请选择所在班级</option>
							<c:forEach items="${klasses}" var="klass">
								<option value="${klass.id}">${klass.name}</option>
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