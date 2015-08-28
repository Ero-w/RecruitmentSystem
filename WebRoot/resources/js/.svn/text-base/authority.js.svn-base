$(function(){
	var $searchBtn=$("#search-btn");
	var	$saveBtn=$("#save-btn");
	$searchBtn.bind("click", search);
	$saveBtn.bind("click", save);
});

function search(){
	var name=$("#sname").val();
	if(name==""){
		alert("姓名不能为空！");
	}else{
		var data={sname:name};
		$.post('/rs/authority_search',{json:JSON.stringify(data)},function(result){
			if(!result.size){
				alert("员工不存在！");
			}else{
				display(result.sid,result.sname,result.dname,result.authority);
			}
		});
	}
}

function display(sid,sname,dname,authority){
	var author;
	var $td=$("#tbody tr td");
	var $option=$("#tbody tr td select option")
	if(authority==1){
		author1="管理员";
		author2="考官";
	}else if(authority==2){
		author1="考官";
		author2="管理员";
	}
	$td.eq(0).html(sid);
	$td.eq(1).html(sname);
	$td.eq(2).html(dname);
	$option.eq(0).html(author1);
	$option.eq(1).html(author2);
}

function save(){
	var	$authority=$("select option:selected");
	var $td=$("#tbody tr td");
	var sid,sname,dname,authority,data;
	if($authority){
		authority=$authority.val();
	}
	if(authority=="管理员"){
		authority=1;
	}else{
		authority=2;
	}
	sid=$td.eq(0).html();
	sname=$td.eq(1).html();
	dname=$td.eq(2).html();
	data={sid:sid,sname:sname,dname:dname,authority:authority};
	$.post('/rs/authority_save',{json:JSON.stringify(data)},function(result){
		if(result.state==0){
			alert("你还没有修改！");
		}else if(result.state==1){
			alert("修改保存成功！");
		}else{
			alert("修改失败！");
		}
	});
}


