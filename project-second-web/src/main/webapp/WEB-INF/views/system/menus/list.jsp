<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<style>
	.td{
		text-align: center;
	}
</style>
</head>
<body>

	<div class="operation-box">
		<a href="${pageContext.request.contextPath}/system/users/insert"><span></span>新增</a>
	</div>
	<table border="1"  class="td">
		<tr>
			<td>ID</td>
			<td>用户名</td>
			<td>密码</td>
			<td>昵称</td>
			<td>归属角色</td>
			<td>性别</td>
			<td>年龄</td>
			<td>注册时间</td>
			<td>登录时间</td>
			<td>IP</td>
			<td>管理</td>
		</tr>
		<c:forEach items="${users}" var="u" >
		<tr>
			<td>${u.id}</td>
			<td>${u.username}</td>
			<td>${u.password}</td>
			<td>${u.nickname}</td>
			<td></td>
			<!--'man' eq users.gender ? "男" : "女" users.roles.rolename-->
			<td>${u.gender.name}</td>
			<td>${u.age}</td>
			<td><fmt:formatDate value="${u.createTime}" pattern="yyyy-MM-dd HH:mm" type="date" /></td>
			<td><fmt:formatDate value="${u.loginTime}" pattern="yyyy-MM-dd HH:mm" type="date" /></td>
			<td>${u.ip}</td>
			<td>
				<a href="${pageContext.request.contextPath}/system/users/update/${u.id}"><i></i>编辑</a>
				<a href="${pageContext.request.contextPath}/system/users/delete/${u.id}" class="deleteuser"><i></i>删除</a>
				<!-- hidden -->
				<input type="hidden" value="${u.username}"/>
			</td>
		</tr>
		</c:forEach>
	</table>
</body>

<script type="text/javascript" src="js/jquery-2.0.0.js"></script>
<script type="text/javascript">
$(function(){
	$(".deleteuser").click(function(){
		var name = $(this).next(":hidden").val();
		var flag = confirm("确定删除"+name);
		if (flag) {
			return true;
		}
		return false;
	});
});
</script>
</html>