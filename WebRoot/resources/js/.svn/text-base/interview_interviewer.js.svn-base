function showInterviewer(res){	
	var tbody = document.getElementById("tbody_interviewer");
	tbody.innerHTML="";
	for(var i = 0;i < res.length;i++){			
		var tr = document.createElement("TR");
		var td1 = document.createElement("TD");
		var td2 = document.createElement("TD");
		var td3 = document.createElement("TD");	
		var trid =  res[i].iid+"tr";		
		tr.setAttribute("id",trid);		
		//设置节点的属性
		td1.setAttribute("classname","num");		
		td2.setAttribute("classname","name");	
		td3.setAttribute("classname", "actions");
		td1.innerHTML = res[i].iid;			
		var time=res[i].time.toString().substring(0,10);		
		var time2 = "\""+time+"\"";//要转换为字符串格式
		var td2id = "\""+res[i]+"a\"";
		//console.log(time2);
		td2.innerHTML = "<input type='date' value="+time2+" id="+td2id+" disabled onblur='changeTime()'/>";		
		td3.innerHTML = "<button class='btn btn-sm btn-primary modify' data-id='$%{it.departmentId}' " +
				"id="+res[i].iid+" onclick='canChangeTime("+res[i].iid+")'>" +
				"修改</button>" +
				 "<button class='btn btn-sm btn-danger delete' data-id='$%{it.departmentId}' onclick='deleteInterviewer("+res[i].iid+")'>" +
				 "删除</button>";
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);	
		tbody.appendChild(tr);
		
	}		
}
//点击修改按钮，把日期设置为可修改
function canChangeTime(id){
	
}
//修改面试时间
function changeTime(id){	
	var newTime = document.getElementById(id+"a").value;	
	var obj = {time:newTime,iid:id};
	$.post('/rs/saveScore',{json:JSON.stringify(obj)});
}
//删除该面试人
function deleteInterviewer(id){
	//刷新前台界面	
	var tbody = document.getElementById("tbody_interviewer");
	var tr = document.getElementById(id+"tr");
	//console.log(tr);
	tbody.removeChild(tr);
	//删除后台数据
	var obj = {iid:id};
	$.post('/rs/deleteInterview',{json:JSON.stringify(obj)});
}
//显示所有的面试人
function showAllInterviewer(){
	//alert("");
	$.post('/rs/in_showAllInterviewer',function(res){		
		showInterviewer(res);
	});	
}
//查找某面试人
function findInterviewer(){	
	var realname = realName.value.trim();	
	if(realname == null || realname == ""){
		return;
	}
	$.post('/rs/findInterview',{sname:realname},function(res){
		showInterviewer(res);
	});
}


