<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>
</head>
	<frameset rows="*" cols="*">
		<frameset rows="*" cols="11%,*">
			<frame src="${pageContext.request.contextPath}/resources/jsp/left.jsp"   noresize="noresize" />
			<frame src="${pageContext.request.contextPath}/resources/jsp/bg.jsp"  name="mainFrame" class="bg-welcome" />
		</frameset>
	</frameset>
</html>