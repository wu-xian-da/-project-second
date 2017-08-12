<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
<div id="panel">

	<div id="login_view">

		<form method="post">

			<table >

				<tr height="40px">
					<td>昵称:</td>
					<td><input type="text" id="nickname" name="nickname"></td>
					
				</tr>
				<tr height="40px">
					<td>密码:</td>
					<td><input type="text" id="password" name="password"></td>

				</tr>
				<tr>
					<td rowspan="2"><input type="submit" name="submit" value="登录"></td>
				</tr>
			</table>
		</form>

	</div>
</div>
</body>
</html>