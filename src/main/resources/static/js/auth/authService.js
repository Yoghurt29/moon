$(function(){
	//登陆或注销后需刷新页面,注册新的ajax头,才生效
	$.ajaxSetup({
		headers:{
			Authorization:authService.getToken()
		}
	});
})
var authService={
	getToken:function(){
		var token=localStorage.getItem("token");
		if(null!=token)
		return JSON.parse(token).access_token;
		return null;
	}
}