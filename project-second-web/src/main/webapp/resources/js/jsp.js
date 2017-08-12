/***************************************************************************************************
 *************************************      USER     ***********************************************
 ***************************************************************************************************/
$('#usersubmit').on("click", function(){
	var checkArr = $('input[name=roleId]:checked');
	if(checkArr.length == 0){
		alert("请勾选需要操作的数据");
		return false;
	}
	var newss = [];
	checkArr.each(function(i){
		newss.push("Roles[" + i + "].id=" + $(this).data("id"));
	});
	window.location.href = "${pageContext.request.contextPath}/system/users/insert?" + newss.join('&');
});