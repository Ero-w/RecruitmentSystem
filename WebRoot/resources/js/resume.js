/**
 * 简历管理
 */
/*增加简历*/
var personal_info 	= $("#personal-info");
var userId;
var personalInfo;
var resume_detail;
var rid;

//获取个人信息
function getPersonal(){
	$.post("/rs/as_getStaff",function(data,statusText){
		personalInfo = data;
		console.log(personalInfo);
		userId = 1;
	},
	"json"
	);
}

//获取个人信息
getPersonal();

//修改简历时，填充个人信息
function setPersonalInfo(sign){
	var inputs;
	if(sign=="0"){
		var changeForm = $("#change-Resume");
		inputs = changeForm.find("input");
	}
	else if(sign=="1"){
		inputs = personal_info.find("input");
	}
	inputs[0].value=personalInfo.name;
	inputs[1].value=personalInfo.mail;
	if(personalInfo.sex==0){					//性别
		inputs[2].checked=true;
	}
	else{
		inputs[3].checked=true;
	}
	inputs[4].value=personalInfo.age;
	inputs[5].value=personalInfo.tel;
}

//创建新简历
$("#save-resume").click(function(){
	var textareas = personal_info.find("textarea");
	var optionText = personal_info.find("option:selected").text();
	var json = {"userId":userId,"education":optionText,"skill":textareas[0].value,"experience":textareas[1].value};
	$.post("/rs/re_createResume",{"json":JSON.stringify(json)},function(data,statusText){
		console.log(data);
		console.log(statusText);
		alert("保存成功");
		window.location.href="resume.html";
	},
	"json");
});

/*查询简历*/
//读取个人简历
/*function readResume(){
	var json={"sign":0};
	$.post("/rs/re_readResume",{"json":JSON.stringify(json)},function(data,statusText){
		resume_detail=data.list;
		var s="";
		console.log(data.state>0);
		if(data.state>0){
			$("#addResumes").css("display","none");
			var i=0;
			data.list.forEach(function(f,i){
				console.log(f.createDate)
				s=s+"<tr><td id='"+f.rid+"'>"+(f.createDate).substring(0,10)+"</td>"
					  +"<td><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#resume-modal' data-backdrop='static' onclick='read("+i+")'>查看</button>&nbsp;"
	                  +"<button type='button' class='btn btn-primary' data-toggle='modal' data-target='#resume-modify-modal' data-backdrop='static' onclick='setPersonalInfo(0)'>修改</button>"
	           +" </td></tr>";
				i++;
			});
			$("#dateTbody").html(s);
		}
		else{
			$("#dateTbody").html("<tr><td>暂无简历，请先添加简历</td></tr>");
		}
	},"json");
}*/

//查看第i条简历
function read(i){
	var tbody = $("#resume-detail");
	var tds  = tbody.find("td");
	console.log(tds);
	console.log(personalInfo);
	console.log(resume_detail);
	tds[1].innerHTML=personalInfo.name;
	if(personalInfo.sex==0){					//性别
		tds[3].innerHTML="男";
	}
	else{
		tds[3].innerHTML="女";
	}
	tds[5].innerHTML=personalInfo.age;
	tds[7].innerHTML=personalInfo.tel;
	tds[9].innerHTML=personalInfo.mail;
	tds[11].innerHTML=resume_detail[i].education;
	tds[13].innerHTML=resume_detail[i].skill;
	tds[15].innerHTML=resume_detail[i].experience;
}
/*修改简历*/

//修改简历
function changeResume(){
	var changeForm = $("#change-Resume");
	var inputs = changeForm.find("input");
	console.log(inputs);
	var education = changeForm.find("option:selected").text();
	var skill = changeForm.find("textarea")[0].value;
	var experience = changeForm.find("textarea")[1].value;
//	var json = {"rid":rid,"skill":skill,"education":education,"experience":experience};
	var json = {"skill":skill,"education":education,"experience":experience};
	console.log(json)
	$.post("/rs/re_updateResume",{"json":JSON.stringify(json)},function(data,stateText){
		if(data.state=="1"){
			alert("修改成功");
			window.location.href="resume.html";
		}
		else{
			alert("修改失败");
		}
	},"json");
}

/*删除*/
//删除简历
function deleteResume(setId){
	rid=setId;
	var json={"rid":rid}
	$.post("/rs/re_deleteResume",{"json":JSON.stringify(json)},function(data,statusText){
		if(data.state=="1"){
			alert("删除成功");
			$("#dateTbody").html("");
			window.location.href="resumeManager.html"
		}
		else{
			alert("删除失败");
		}
	},"json");
}

/*管理员读取所有简历信息*/
function readResume(sign){
	console.log(personalInfo);
	var json={"sign":sign};
	$.post("/rs/re_readResume",{"json":JSON.stringify(json)},function(data,statusText){
		console.log(data);
		resume_detail=data.list;
		var s="";
		if(data.state>0){
			var i=0;
			//读取个人简历
			if(sign==0){
				$("#addResumes").css("display","none");
				data.list.forEach(function(f,i){
					console.log(f.createDate)
					s=s+"<tr><td id='"+f.rid+"'>"+(f.createDate).substring(0,10)+"</td>"
						  +"<td><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#resume-modal' data-backdrop='static' onclick='read("+i+")'>查看</button>&nbsp;"
		                  +"<button type='button' class='btn btn-primary' data-toggle='modal' data-target='#resume-modify-modal' data-backdrop='static' onclick='setPersonalInfo(0)'>修改</button>"
		           +" </td></tr>";
					i++;
				});
				$("#dateTbody").html(s);
			}
			//管理员读取所有简历信息
			else if(sign==1){
				data.list.forEach(function(f,i){
					s=s+"<tr><td id='"+f.rid+"'>"+(f.createDate).substring(0,10)+"</td>"
					   +"<td><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#resume-modal' data-backdrop='static' onclick='read("+i+")'>查看</button>&nbsp;"
					   +"<button type='button' class='btn btn-primary' data-toggle='modal' data-target='#resume-modify-modal' data-backdrop='static' onclick='deleteResume("+f.rid+")'>删除</button>"
					   +"</td></tr>";
					i++;
				});
			}
			//面试官读取特定简历信息
			else if(sign==2){
				data.list.forEach(function(f,i){
					s=s+"<tr><td id='"+f.rid+"'>"+(f.createDate).substring(0,10)+"</td>"
					   +"<td><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#resume-modal' data-backdrop='static' onclick='read("+i+")'>查看</button>&nbsp;"
					   +"</td></tr>";
					i++;
				});
			}
			
			$("#dateTbody").html(s);
		}
		else{
			$("#dateTbody").html("<tr><td>暂无简历</td></tr>");
		}
	},"json");
}

/*面试官读取特定简历信息*/
/*function readResumeInterview(){
//	getPersonal();
	console.log(personalInfo);
	var json={"sign":2};
	$.post("/rs/re_readResume",{"json":JSON.stringify(json)},function(data,statusText){
		console.log(data);
		resume_detail=data.list;
		var s="";
		if(data.state>0){
			var i=0;
			data.list.forEach(function(f,i){
					s=s+"<tr><td id='"+f.rid+"'>"+(f.createDate).substring(0,10)+"</td>"
					   +"<td><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#resume-modal' data-backdrop='static' onclick='read("+i+")'>查看</button>&nbsp;"
					   +"</td></tr>";
				i++;
			});
			$("#resumeManager").html(s);
		}
		else{
			$("#resumeManager").html("<tr><td>暂无简历</td></tr>");
		}
	},"json");
}*/
