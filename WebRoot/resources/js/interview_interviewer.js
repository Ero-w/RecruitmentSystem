//定义一个全局变量
var general;
function showInterviewer(res){	
	var tbody = document.getElementById("tbody_interviewer");
	tbody.innerHTML="";
	if(res.length == 0){
		tbody.innerHTML = "暂无";
	}
	for(var i = 0;i < res.length;i++){			
		var tr = document.createElement("TR");
		var td1 = document.createElement("TD");
		var td2 = document.createElement("TD");
		var td3 = document.createElement("TD");
		var td4 = document.createElement("TD");			
		var iid = res[i];
		var trid =  iid+"tr";		
		tr.setAttribute("id",trid);		
		//设置节点的属性
		td1.setAttribute("classname","num");
		td2.setAttribute("classname","name");
		td3.setAttribute("classname","name");	
		td4.setAttribute("classname", "actions");
		td1.innerHTML = res[i+"sname"];	
		td2.innerHTML = res[i+"pname"];
		//只取日期的前10位
		var time = res[i+"time"].toString().substring(0,10);		
		var time2 = "\""+time+"\"";//要转换为字符串格式
		var td3id = "\""+iid+"a\"";		
		td3.innerHTML = "<input type='date' value="+time2+" id="+td3id+" disabled onblur='changeTime("+iid+")'/>";		
		td4.innerHTML = "<button class='btn btn-sm btn-primary modify' data-id='$%{it.departmentId}' " +
				"id="+iid+" onclick='canChangeTime("+iid+")'>" +
				"修改</button>" +
				 "<button class='btn btn-sm btn-danger delete' data-id='$%{it.departmentId}' onclick='deleteInterviewer("+iid+")'>" +
				 "删除</button>";
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);	
		tbody.appendChild(tr);
	
	}		
}
//点击修改按钮，把日期设置为可修改
function canChangeTime(id){	
	document.getElementById(id+"a").disabled = false;
	document.getElementById(id+"a").focus();
	//获取当前系统时间,把添加面试记录中的日期最小值设置为当前日期。
	getDate(document.getElementById(id+"a"));
}
//修改面试时间
function changeTime(id){	
	var newTime = document.getElementById(id+"a").value;	
	var obj = {time:newTime,iid:id};
	$.post('/rs/saveTime',{json:JSON.stringify(obj)});
	document.getElementById(id+"a").disabled = true;//把日期设置为不可修改
}
//删除该面试人
function deleteInterviewer(id){
	if(!confirm("是否删除该信息")){
		return ;
	}
	//刷新前台界面	
	var tbody = document.getElementById("tbody_interviewer");
	var tr = document.getElementById(id+"tr");	
	tbody.removeChild(tr);
	//删除后台数据
	var obj = {iid:id};
	$.post('/rs/deleteInterview',{json:JSON.stringify(obj)});
}
//显示所有的面试人
function showAllInterviewer(){
	$.post('/rs/interview_showAllInterview',function(res){	
		general = res;
		showInterviewer(res);
	});	
}

//查找某面试人
function findInterviewer(){		
	var realname = realName.value.trim();
	//alert("realname="+realname);
	if(realname == "" || realname == null){
		showInterviewer(general);
		return;
	}
	var res = {};
	//var ch = "/""+realname+"/"";
	var reg = new RegExp(realname,"g");
	var j = 0;
	for(var i = 0;i < general.length;i++){		
		if(general[i+"sname"].search(reg) != -1){
			res[j+""] = general[i+""]
			res[j+"sname"] = general[i+"sname"];
			res[j+"pname"] = general[i+"pname"];
			res[j+"time"] = general[i+"time"];
			j++;
		}
	}
	res["length"] = j;	
	showInterviewer(res);
}

//获取当前系统时间,把添加面试记录中的日期最小值设置为当前日期。
function getDate(input){	
	var date = new Date();
	//var str = date.toLocaleDateString().replace(/\//g, "-");	
	//console.log(str);	
	year = date.getFullYear();
	 month = date.getMonth()+1;
	 day = date.getDate();	
	//转换为可用的格式
	if(month < 10){
		month = "0" + month;		
	}
	if(day < 10){
		day = "0" + day;		
	}
	var myDate = [year,month,day];	
	//console.log(myDate.join("-"));	
	input.min = myDate.join("-");
}
