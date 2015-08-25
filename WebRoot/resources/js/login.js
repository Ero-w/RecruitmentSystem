if(!String.prototype.trim){
	String.prototype.trim=function(){
		return this.replace(/(?:^[ \t\n\r]+)|(?:[ \t\n\r]+$)/g, '');
	}
}

$(function(){
	
})

function login(){
	var user=$('#account').val();
	var password=$('#password').val();
	if(user.trim()==''){
		return alert('账号不能为空')
	}else if(password==''){
		return alert('密码不能为空')
	}else{
		var obj={user:user,password:password};
		$.post('/rs/as_login',{json:JSON.stringify(obj)},function(res){
			if(res.state>0){
				window.location.href="/rs/views/addText.jsp";
			}else{
				if(res.state==-1){
					alert('账号不存在');
				}else if(res.state==-2){
					alert('密码错误');
				}else{
					alert(res.state);
				}
			}
		})
	}
}