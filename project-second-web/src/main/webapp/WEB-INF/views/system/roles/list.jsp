<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/systems.js"></script>
<title>用户列表</title>
<style>
	.td{
		text-align: center;
	}
	.roleclass{
		text-align: left
	}
</style>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/system/roles">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
          	<td width="100px">条件检索</td>
            <td width="900px">角色名称：<input type="text" name="rolename" style="width: 200px"/></td>
            <td>&nbsp;&nbsp;<input  type="submit" name="submit" value="查询" style="width:50px"/></td>
            <td>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/system/roles/insert"><input  type="button" value="添加" style="width:50px"/></a></td>            
          </tr>
        </table>
	</form>
	<hr/>
	<table  border="1"  class="td" width="1200px" style="font-size: 8px;">
		<tr>
			<td>序号</td>
			<td>角色名称</td>
			<td>描述</td>
			<td>管理用户</td>
			<td>管理</td>
		</tr>
		<c:forEach items="${roles}" var="r" >
		<tr>
			<td>${r.id}</td>
			<td>${r.rolename}</td>
			<td>${r.counts}</td>
			<td class="roleclass">
				<c:forEach items="${userRole}" var="ru">
				<c:if test="${ru.roleId eq r.id}">
					${ru.users}
				</c:if>
				</c:forEach>
			</td>
			<td>
				<a href="${pageContext.request.contextPath}/system/roles/update/${r.id}"><i></i>编辑</a>
				<a href="${pageContext.request.contextPath}/system/roles/delete/${r.id}" class="deleterole"><i></i>删除</a>
				<!-- hidden -->
				<input type="hidden" value="${r.rolename}"/>
				<c:forEach items="${userRole}" var="ru">
				<c:if test="${ru.roleId eq r.id}">
				<input type="hidden" value="${ru.users}"/>
				</c:if>
				</c:forEach>
			</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>