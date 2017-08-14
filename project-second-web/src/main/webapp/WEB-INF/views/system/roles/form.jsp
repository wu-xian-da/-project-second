<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/FormValid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/system_s.js"></script>
</head>
<body>
	<div>
		<h2>${empty roles.id ? "新增" : "编辑" }角色</h2><hr/>
		<form method="post" onsubmit="return validator(this)">
		<input type="hidden" name="id" value="${roles.id}"/>
		<table>
			<tr><td>角色名称：</td><td><input id="rolenamevalid" type="text" name="rolename" value="${roles.rolename}" maxlength="10" valid="required"  errmsg="角色名称不能为空!" placeholder="角色名称"><span id="errrolename"></span></td></tr>
			<tr><td>角色描述：</td><td><input type="text" name="counts" value="${roles.counts}" valid="required"  errmsg="角色描述不能为空!" placeholder="角色描述"></td></tr>
			<tr><td>授权：</td><td></td></tr>
			<tr>
				<td><button id="rolesumbit" type="submit">${empty roles.id ? "新增" : "编辑"}</button></td>
				<td><button type="button" onclick="javascript:history.back();">返回</button></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>