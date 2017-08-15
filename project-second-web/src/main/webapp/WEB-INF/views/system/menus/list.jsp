<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sys_urm.js"></script>
<title>用户列表</title>
<style>
	.td{
		text-align: center;
	}
</style>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/system/menus">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
          	<td width="100px">条件检索</td>
            <td width="250px">名称：<input type="text" name="name" style="width: 150px"/></td>
            <td width="650px">权限标识：<input type="text" name="permission" style="width: 150px"/></td>
            <td>&nbsp;&nbsp;<input  type="submit" name="submit" value="查询" style="width:50px"/></td>
            <td>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/system/menus/insert"><input  type="button" value="添加" style="width:50px"/></a></td>            
          </tr>
        </table>
	</form>
	<hr/>
	<table border="1"  class="td" width="1200px" style="font-size: 8px;">
		<tr>
			<td width="50px">序号</td>
			<td width="100px">名称</td>
			<td width="100px">权限标识</td>
			<td width="50px">排序号</td>
			<td>关联角色</td>
			<td width="100px">管理</td>
		</tr>
		<c:forEach items="${menus}" var="m" >
		<tr>
			<td>${m.id}</td>
			<td>${m.name}</td>
			<td>${m.permission}</td>
			<td>${m.sort}</td>
			<td align="left"  class="menuclass">
				<c:forEach items="${rolename}" var="rn">
					<c:if test="${rn.menuId eq m.id}">
						${rn.roles}
					</c:if>
					<c:if test="${rn.buttonId eq m.id}">
						${rn.roles}
					</c:if>
				</c:forEach>
			</td>
			<td>
				<a href="${pageContext.request.contextPath}/system/menus/update/${m.id}"><i></i>编辑</a>
				<a href="${pageContext.request.contextPath}/system/menus/delete/${m.id}" class="deletemenu"><i></i>删除</a>
				<!-- hidden -->
				<input type="hidden" value="${m.name}"/>
				<!-- hidden角色与权限的关联 -->
				<c:forEach items="${rolename}" var="ruhidden">
					<c:if test="${ruhidden.menuId eq m.id}"><input type="hidden" value="${ruhidden.roles}"/></c:if>
					<c:if test="${ruhidden.buttonId eq m.id}"><input type="hidden" value="${ruhidden.roles}"/></c:if>
				</c:forEach>
			</td>
		</tr>
		</c:forEach>
	</table>
	<table width="1200px;" style="font-size: 8px;" class="td">
		<tr>
		<td align="left">共${totalRecord}条数据|每页面记录${page.pageSize}条数据</td>
		<!-- 首页 -->
		<td width="35px;"><a href="${pageContext.request.contextPath}/system/menus?pn=0&ps=${page.pageSize}">首页</a></td>
		<!-- 上一页 -->
		<td width="50px;">
		<a id="shangyiye" href="${pageContext.request.contextPath}/system/menus?pn=${bianPageShang}&ps=${page.pageSize}">上一页
		<input id="pagePnShang" type="hidden" name="pn" value="${bianPageShang}"/>
		</a>
		</td>
		<!-- 页面的页数数字 -->
		<td align="center">
			<c:if test="${pageNo > 0}">
			<c:forEach begin="0" end="${pageNo-1}" step="1" var="pageIndex">
				<a <c:if test="${0 == pageIndex}">class="active"</c:if>
				href="${pageContext.request.contextPath}/system/menus?pn=${pageIndex}&ps=${page.pageSize}">${pageIndex+1}</a>
			</c:forEach>
			</c:if>	
		</td>
		<!-- 下一页 -->
		<td width="50px;">
		<a id="xiayiye" href="${pageContext.request.contextPath}/system/menus?pn=${bianPageXia}&ps=${page.pageSize}">
		<input id="pagePnXia" type="hidden" name="pn" value="${bianPageXia}"/>
		<input id="totalPage" type="hidden" name="pn" value="${pageNo}"/>下一页</a>
		</td>
		<!-- 末页 -->
		<td width="35px;"><a href="${pageContext.request.contextPath}/system/menus?pn=${pageNo-1}&ps=${page.pageSize}">末页</a></td>
		</tr>
	</table>
</body>
<script type="text/javascript">
	//页面跳转
	$(function(){
		$("#page_ps_select").change(function(){
			window.location.href = '${pageContext.request.contextPath}/system/users?ps='+$(this).val();
		});
	});
</script>
</html>