
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>left</title>
<style type="text/css">
	.leftsidebar_box dt{
		text-align: center;
		font-size: 25px;
	}
</style>

</head>

<body>

<div class="container">

	<div class="leftsidebar_box">
		<div class="line"></div>
		<dl class="syetem_management">
			<dt>系统管理</dt>
			<dd>
				<a href="http://localhost:8080/system/users" target="mainFrame">用户管理</a>
			</dd>
			<dd>
				<a href="http://localhost:8080/system/roles" target="mainFrame">角色管理</a>
			</dd>
			<dd>
				<a href="http://localhost:8080/system/menus" target="mainFrame">权限管理</a>
			</dd>
		</dl>
	</div>
</div>

<script src="http://localhost:8080/resources/js/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$(".leftsidebar_box dd").hide();
	$(".leftsidebar_box dt").click(function(){
		$(this).parent().find('dd').removeClass("menu_chioce");
		$(".menu_chioce").slideUp(); 
		$(this).parent().find('dd').slideToggle();
		$(this).parent().find('dd').addClass("menu_chioce");
	});
})
</script>
</body>
</html>
