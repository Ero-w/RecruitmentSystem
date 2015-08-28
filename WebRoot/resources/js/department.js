var allDepartments;
$(document).ready(function(){
	 $.post("/rs/de_getDepartments",
			 //回调函数
			 function(data , statusText)
			 {
		 		allDepartments=data.allDepartmes;
		 		for(var i=0;i<allDepartments.length;i++){
		 			$("#tbody").append(
		 					"<tr data-id='"+allDepartments[i].DId+"'>"+
	                            "<td>"+allDepartments[i].DId+"</td>"+
	                            "<td>"+allDepartments[i].DName+"</td>"+
	                            "<td>"+allDepartments[i].DTel+"</td>"+
	                            "<td>"+allDepartments[i].DLocation+"</td>"+
	                            "<td>"+
	                                "<button type=\"button\" class=\"btn btn-danger\" onclick=\"delete_depart(this)\">删除</button>"+
	                            "</td>"+
                            " </tr>"
		 			);
		 		}
		 	},
	 	"json");
 });



//增加数据
function addDepartment(){
	var dept_name = $("#dept-name").val();
	var dept_tel = $("#dept-tel").val();
	var dept_address = $("#dept-address").val();
	var flag=0;	
	
	if(dept_name.trim()==""){
		alert("部门名称不能为空！");
	}
	else if(dept_tel==""){
		alert("部门号码不能为空！");
	}
	else if(dept_address==""){
		alert("部门地址不能为空！");
	}
	else{
		for(var i=0;i<allDepartments.length;i++){
			if(allDepartments[i].DName==dept_name){
				flag=1;
				break;
			}
		}
		if(flag==1){
			alert("该部门名称已存在！");
		}
		else{
			var data={DName:dept_name,DTel:dept_tel,DAddress:dept_address};
			$.post('/rs/de_addDepartment',{json:JSON.stringify(data)},function(res){
				if(res.isAdd==1){
					alert("保存成功");
					location.reload();
				}
				else{
					alert("保存出错");
				}
			},"json");
		}
	}	
}


//删除数据
function delete_depart(which){
	var tr = which.parentNode.parentNode;//获取到那一行的tr
	var DId=tr.getAttribute("data-id");
	var data={DId:DId};
	$.post('/rs/de_deleteDepartment',{json:JSON.stringify(data)},
			function(res,statusText){
		if(res.isDelete==1){
			alert("删除成功");
			location.reload();
		}
		else{
			alert("删除出错");
		}
	},"json");
}