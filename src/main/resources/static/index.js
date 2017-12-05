$(function(){
	$("#logout-btn").bind("click",loginService.logout);
	$("#login-btn").bind("click",loginService.login);
	$("#sendNeedAuthRequest-btn").bind("click",test.sendNeedAuthRequest);
})
var loginService={
	authToken:null,
	login:function login(){
		alert($("#username").val());
		var username =$("#username").val();
		var password =$("#password").val();
		var loginPara=new Object();
		loginPara.username=username;
		loginPara.password=password;
		$.ajax({
			type:"post",
			url:"auth/token",
			contentType : 'application/json',
			data:JSON.stringify(loginPara),
			success:function(data){
				if(data.success){
					alert(data.data);
					localStorage.setItem("token", JSON.stringify(data.data));
					window.location.reload();
				}else{
					alert(data.message);
				}
			}
		});
	},
	logout:function(){
		localStorage.removeItem("token");
		window.location.reload();
	}
}
var test={
	sendNeedAuthRequest:function(){
		$.ajax({
			type:"get",
			url:"index/about",
			success:function(data){
				if(data.success){
					alert(data.data);
				}else{
					alert(data.message);
				}
			}
		});
	}
}