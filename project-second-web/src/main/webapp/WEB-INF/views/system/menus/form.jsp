<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h2>${empty users.id ? "新增" : "编辑" }用户</h2><hr/>
		<form method="post">
		<input type="hidden" name="id" value="${users.id }">
		<table>
			<tr><td>姓名：</td><td><input type="text" name="username" value="${users.username}" placeholder="姓名"></td></tr>
			<tr><td>密码：</td><td><input type="password" name="password" value="${users.password}" placeholder="密码"></td></tr>
			<tr><td>昵称：</td><td><input type="text" name="nickname" value="${users.nickname}" placeholder="昵称"></td></tr>
			<tr><td>性别：</td>
				<td>
				<c:forEach items="${gender}" var="gender">
					<c:choose>
						<c:when test="${empty users.id}">
							<input type="radio" name="gender" value="${gender}" <c:if test="${'NAN' eq gender}">checked="checked"</c:if>/>${gender.name}	
						</c:when>
						<c:otherwise>
							<input type="radio" name="gender" value="${gender}" <c:if test="${users.gender eq gender}">checked="checked"</c:if>/>${gender.name}
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</td>
			</tr>
			<tr><td>年龄：</td><td><input type="text" name="age" value="${users.age}" placeholder="年龄"></td></tr>
			<tr><td>角色：</td><td></td></tr>
			<tr>
				<td><button type="submit">${empty users.id ? "新增" : "编辑"}</button></td>
				<td><button type="button" onclick="javascript:history.back();">返回</button></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>