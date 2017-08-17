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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/systemValida.js"></script>
<style type="text/css">
	.td1{
		width: 80px;text-align: right;
	}
	.tr1{
		height: 50px;
	}
	table{  
     border-collapse: collapse;  
     border: none;  
	} 
</style>
</head>
<body>
	<div>
		<h2>${empty roles.id ? "新增" : "编辑" }角色</h2><hr/>
		<form method="post" onsubmit="return validator(this)">
		<input type="hidden" name="id" value="${roles.id}"/>
		<table>
			<tr class="tr1"><td class="td1">角色名称：</td><td><input id="rolenamevalid" type="text" name="rolename" value="${roles.rolename}" maxlength="10" valid="required"  errmsg="角色名称不能为空!" placeholder="角色名称"><span id="errrolename"></span></td></tr>
			<tr class="tr1"><td class="td1">角色描述：</td><td><input type="text" name="counts" value="${roles.counts}" valid="required"  errmsg="角色描述不能为空!" placeholder="角色描述"></td></tr>
			<tr class="tr1"><td class="td1" valign="top">授权：</td>
				<td>
				<table>
				<c:choose>
					<c:when test="${empty roles.id}">
						<!-- 一级 -->
						<c:forEach items="${menu}" var="m">
							<c:if test="${m.type == 'MENU'}">
							<c:set value="${m.href}" var="href"></c:set>
								<tr><td>
								<!-- <input type="checkbox" name="checkmenus" value="${m.id}">-->${m.name}</td>
								</tr>
							</c:if>
							<!-- 二级 -->
							<tr>
							<c:forEach items="${button}" var="mx">
								<c:if test="${mx.type == 'BUTTON'}">
									<c:if test="${href == mx.href}">
										<td width="150px;">&nbsp;&nbsp;
										<input type="checkbox" name="menubutton" value="${m.id}-${mx.id}">${mx.name}
										</td>
									</c:if>
								</c:if>
							</c:forEach>	
							</tr>				
						</c:forEach>
					</c:when>
					<c:otherwise>
						<!-- 一级 -->
						
						<c:forEach items="${menu}" var="m">
							<c:if test="${m.type == 'MENU'}">
								<c:set value="${m.href}" var="href"></c:set>
								<c:set value="0" var="bian"></c:set>
								<tr><td>
								<!-- 角色与权限关联 -->
								<c:forEach items="${menubutton}" var="menuId">
									<c:if test="${menuId.menuId eq m.id}">
									<c:set value="1" var="bian"></c:set>
									</c:if>
								</c:forEach>
								
								<c:if test="${bian == '0'}">
									<!-- <input type="checkbox" name="checkmenus" value="${m.id}">-->${m.name}
								</c:if>
								<c:if test="${bian == '1'}">
									<!-- <input type="checkbox" name="checkmenus" value="${m.id}" checked="checked">-->${m.name}
								</c:if>
								
								</td>
								</tr>
							</c:if>
							<!-- 二级 -->
							<tr>
							<c:forEach items="${button}" var="mx">
								<c:if test="${mx.type == 'BUTTON'}">
									<c:set value="2" var="bian"></c:set>
									<!-- 确保权限不重复 -->
									<c:if test="${href == mx.href}">
										<!-- 角色与权限关联 -->
										<c:forEach items="${menubutton}" var="buttonId">
											<c:if test="${buttonId.buttonId eq mx.id}">
												<c:set value="3" var="bian"></c:set>
											</c:if>
										</c:forEach>
										
										<td width="150px;">&nbsp;&nbsp;
										<c:if test="${bian == '2'}">
											<input type="checkbox" name="menubutton" value="${m.id}-${mx.id}">${mx.name}
										</c:if>
										<c:if test="${bian == '3'}">
											<input type="checkbox" name="menubutton" value="${m.id}-${mx.id}" checked="checked">${mx.name}
										</c:if>
										</td>
									</c:if>
								</c:if>
							</c:forEach>	
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				</table>
				</td>
			</tr>
			<tr class="tr1">
				<td class="td1"><button id="rolesumbit" type="submit">${empty roles.id ? "新增" : "编辑"}</button></td>
				<td><button type="button" onclick="javascript:history.back();">返回</button></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>