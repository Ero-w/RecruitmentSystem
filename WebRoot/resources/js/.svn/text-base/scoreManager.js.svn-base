//定义一个全局变量保存所有的数据
var general;

function showInterview(res){	
	var tbody = document.getElementById("tbody_score");
	tbody.innerHTML="";	
	if(res.length == 0){
		tbody.innerHTML = "暂无";
	}
	for(var i = 0;i < res.length;i++){			
		var tr = document.createElement("TR");
		var td1 = document.createElement("TD");
		var td12 = document.createElement("TD");
		var td2 = document.createElement("TD");
		var td3 = document.createElement("TD");
		var td4 = document.createElement("TD");
		var td5 = document.createElement("TD");
		var td6 = document.createElement("TD");	
		var iid = res[i];			
		var trid = iid+"tr";		
		tr.setAttribute("id",trid);		
		//设置节点的属性
		td1.setAttribute("classname","num");
		td1.setAttribute("data-id","$%{it.departmentId}");
		td12.setAttribute("classname","name");
		td2.setAttribute("classname","name");	
		td6.setAttribute("classname", "actions");
		td1.innerHTML = res[i+"sname"];	
		td12.innerHTML = res[i+"pname"];
		//只取日期的前10位
		var time = res[i+"time"].toString().substring(0,10);		
		td2.innerHTML = time;
		var  id3 = "\""+iid+"a\"";
		td3.innerHTML = "<select id="+id3+" class='form-control' style='width:130px;postion:relative;top:-3px;' >" +					
				"<option>----请评分-----</option>"+
				"<option>A</option>"+
				"<option>B</option>"+
				"<option>C</option>"+
				"</select>";			
		var id4 =  "\""+iid+"b\"";
		td4.innerHTML = "<input type='text' id="+id4+"  placeholder='输入评价' />";
		var id5 =  "\""+iid+"c\"";
		td5.innerHTML = "<select id="+id5+" class='form-control' style='width:130px;postion:relative;top:-3px;' >" +					
				"<option>----请选择-----</option>"+
				"<option>通过</option>"+
				"<option>不通过</option>"+				
				"</select>";			
		td6.innerHTML = "<button class='btn btn-sm btn-primary modify' data-id='$%{it.departmentId}' " +
				"id="+iid+" onclick='saveScore("+iid+")'>" +
				"保存</button>" +
				 "<button class='btn btn-sm btn-danger delete' data-id='$%{it.departmentId}' onclick='deleteInterview("+iid+")'>" +
				 "删除</button>";
		tr.appendChild(td1);
		tr.appendChild(td12);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		tr.appendChild(td5);
		tr.appendChild(td6);
		tbody.appendChild(tr);
		
	}		
}

//保存给出的评分和评价
function saveScore(id){	
	//alert("保存成功！");
	var rankjsp = document.getElementById(id+"a").value;
	var evaluatejsp = document.getElementById(id+"b").value;
	var passjsp = document.getElementById(id+"c").value;
	if(rankjsp == "----请评分-----"){
		document.getElementById(id+"a").focus();
		return;
	}
	if(evaluatejsp == null || evaluatejsp == ""){
		document.getElementById(id+"b").focus();
		return;
	}
	if(passjsp == "----请选择-----"){
		document.getElementById(id+"c").focus();
		return;
	}
	if(passjsp == "通过"){
		passjsp = 1;
	}else{
		passjsp = 0;
	}
	
	var obj = {rank:rankjsp,evaluate:evaluatejsp,pass:passjsp,iid:id};
	$.post('/rs/saveScore',{json:JSON.stringify(obj)});
	//刷新界面
	location.href="scoreManager.html";
}
//删除该面试人
function deleteInterview(id){
	if(!confirm("是否删除")){		
		return;
	}	
	//刷新前台界面	
	var tbody = document.getElementById("tbody_score");
	var tr = document.getElementById(id+"tr");
	//console.log(tr);
	tbody.removeChild(tr);
	//删除后台数据
	var obj = {iid:id};
	$.post('/rs/deleteInterview',{json:JSON.stringify(obj)});
}
//显示所有的面试人
function showAllInterview(){
	//alert("");
	$.post('/rs/interview_showAllInterview',function(res){	
		general = res;
		showInterview(res);
	})	
}
//查找某面试人
function findInterview(){	
	var realname = realName.value.trim();
	//alert("realname="+realname);
	if(realname == "" || realname == null){
		showInterview(general);
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
	showInterview(res);
	/*$.post('/rs/findInterview',{sname:realname},function(res){
		showInterview(res);
	})*/
}


