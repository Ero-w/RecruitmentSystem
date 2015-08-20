//先判断是否有空信息，然后添加
function addInterview(){
	var staffNamejsp = document.getElementById("staff_name").value.trim();
	//var select = document.getElementById("staff_name");
	//select.appendChild(newChild);
	var datejsp = document.getElementById("interview_date").value.trim();
	var remarkjsp = document.getElementById("remark").value.trim();
	
	if(staffNamejsp == null || staffNamejsp == ""){
		document.getElementById("staff_name").focus();
		return ;
	}
	if(datejsp == null || datejsp == ""){
		document.getElementById("interview_date").focus();
		return;
	}
	if(remarkjsp == null || remarkjsp == ""){
		document.getElementById("remark").focus();
		return;		
	}
	
	var obj = {staffName:staffNamejsp,date:datejsp,remark:remarkjsp};
	$.post('/rs/addInterview',{json:JSON.stringify(obj)});
	//alert("添加面试记录成功！");	
}

