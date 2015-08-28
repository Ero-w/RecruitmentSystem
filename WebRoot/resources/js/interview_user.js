function showPosition(res){	
	var tbody = document.getElementById("tbody_interview_user");
	tbody.innerHTML="";
	for(var i = 0;i < res.length;i++){			
		var tr = document.createElement("TR");
		var td1 = document.createElement("TD");
		var td2 = document.createElement("TD");
		var td3 = document.createElement("TD");
		var td4 = document.createElement("TD");		
		var td5 = document.createElement("TD");
		var td6 = document.createElement("TD");
		var pid = res[i];
		var trid =  pid+"tr";		
		tr.setAttribute("id",trid);	
		td1.innerHTML = res[i+"pname"];	
		td2.innerHTML = res[i+"dname"];
		td3.innerHTML = res[i+"amount"];
		td4.innerHTML = res[i+"requirement"];
		var date = res[i+"deadline"].toString().substring(0,10);
		td5.innerHTML = date;	
		if(res[i+"isApply"]){
			td6.innerHTML = "<button type='button' class='btn btn-danger' >已申请</button>";
		}else{
			td6.innerHTML = "<button type='button' class='btn btn-primary' onclick='applyPosition("+pid+")'>申请</button>";
		}		
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		tr.appendChild(td5);	
		tr.appendChild(td6);
		tbody.appendChild(tr);
	
	}		
}

//求职中心中显示所有的面试岗位
function showAllPosition(){
	$.post('/rs/position_showAllPosition',function(res){		
		showPosition(res);
	});	
}

//求职中心中申请岗位
function applyPosition(pid){
	var obj = {pid:pid};
	$.post('/rs/position_applyPosition',{json:JSON.stringify(obj)});	
	location.href="interView_user.html";//刷新界面
	//alert("申请成功！");	
}
//面试记录中显示所有的面试记录
function showAllRecord(){
	$.post('/rs/position_showAllRecord',function(res){		
		showRecord(res);
	});
}
//面试记录中显示所有的面试记录：具体实现
function showRecord(res){
	var tbody = document.getElementById("tbody_interview_record");
	tbody.innerHTML = "";
	for(var i = 0;i < res.length;i++){
		var tr = document.createElement("TR");		
		var td1 = document.createElement("TD");
		var td2 = document.createElement("TD");
		var td3 = document.createElement("TD");
		var td4 = document.createElement("TD");	
		var pid = res[i];
		var trid =  pid+"tr";		
		tr.setAttribute("id",trid);	
		td1.setAttribute("classname","num");
		td1.setAttribute("data-id", "$%{it.departmentId}");
		td2.setAttribute("classname", "name");
		td3.setAttribute("classname", "name");
		td1.innerHTML = res[i+"pname"];
		td2.innerHTML = res[i+"time"].toString().substring(0,10);
		td3.innerHTML = res[i+"state"];
		td4.innerHTML = res[i+"pass"];
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);	
		tbody.appendChild(tr);
		
	}
}


