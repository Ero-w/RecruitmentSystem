function showInterview(res){
	//console.log(res.length);
	//console.log(res[0].rank);
	var tbody = document.getElementById("tbody_score");
	tbody.innerHTML="";
	for(var i = 0;i < res.length;i++){			
		var tr = document.createElement("TR");
		var td1 = document.createElement("TD");
		var td2 = document.createElement("TD");
		var td3 = document.createElement("TD");
		var td4 = document.createElement("TD");
		var td5 = document.createElement("TD");	
		var trid =  res[i].iid+"tr";		
		tr.setAttribute("id",trid);		
		//设置节点的属性
		td1.setAttribute("classname","num");
		td1.setAttribute("data-id","$%{it.departmentId}");
		td2.setAttribute("classname","name");	
		td5.setAttribute("classname", "actions");
		td1.innerHTML = res[i].iid;			
		var time=res[i].time.toString().substring(0,10);
		//var time2 = "\""+time+"\"";//要转换为字符串格式
		//console.log(time2);
		/*td2.innerHTML = "<input type='date' value="+time2+" disabled style='color:#333'/>";*/
		td2.innerHTML = time;
		var  id3 = "\""+res[i].iid+"a\"";
		td3.innerHTML = "<select id="+id3+" class='form-control' style='width:130px;postion:relative;top:-3px;' >" +					
				"<option>----请评分-----</option>"+
				"<option>A</option>"+
				"<option>B</option>"+
				"<option>C</option>"+
				"</select>";			
		var id4 =  "\""+res[i].iid+"b\"";
		td4.innerHTML = "<input type='text' id="+id4+"  placeholder='输入评价' />";
		td5.innerHTML = "<button class='btn btn-sm btn-primary modify' data-id='$%{it.departmentId}' " +
				"id="+res[i].iid+" onclick='saveScore("+res[i].iid+")'>" +
				"保存</button>" +
				 "<button class='btn btn-sm btn-danger delete' data-id='$%{it.departmentId}' onclick='deleteInterview("+res[i].iid+")'>" +
				 "删除</button>";
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		tr.appendChild(td5);
		tbody.appendChild(tr);
		
	}		
}

//保存给出的评分和评价
function saveScore(id){	
	//alert("保存成功！");
	var rankjsp = document.getElementById(id+"a").value;
	var evaluatejsp = document.getElementById(id+"b").value;
	if(rankjsp == "----请评分-----"){
		document.getElementById(id+"a").focus();
		return;
	}
	if(evaluatejsp == null || evaluatejsp == ""){
		document.getElementById(id+"b").focus();
		return;
	}
	var obj = {rank:rankjsp,evaluate:evaluatejsp,iid:id};
	$.post('/rs/saveScore',{json:JSON.stringify(obj)});
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
	$.post('/rs/in_showAllInterview',function(res){		
		showInterview(res);
	})	
}
//查找某面试人
function findInterview(){
	//alert("");	
	var realname = realName.value.trim();
	if(realname == "" || realname == null)
		return;
	//console.log(realname);
	$.post('/rs/findInterview',{sname:realname},function(res){
		showInterview(res);
	})
}


