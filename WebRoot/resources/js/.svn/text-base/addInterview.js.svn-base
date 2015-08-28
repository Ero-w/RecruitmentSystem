//先判断是否有空信息，然后添加
function addInterview(){
	
	var selectjsp = document.getElementById("select_message").value;
	var datejsp = document.getElementById("interview_date").value.trim();
	var remarkjsp = document.getElementById("remark").value.trim();		
	
	if(selectjsp == "----请选择面试信息----"){
		document.getElementById("select_message").focus();
		return;
	}
	
	if(datejsp == null || datejsp == ""){
		document.getElementById("interview_date").focus();
		return;
	}
	if(remarkjsp == null || remarkjsp == ""){
		document.getElementById("remark").focus();
		return;		
	}
	
	var optionid = document.getElementById("select_message").selectedIndex;
	var apidjsp = document.getElementById("select_message").options[optionid].id;	
	
	var obj = {apid:apidjsp,date:datejsp,remark:remarkjsp};
	$.post('/rs/addInterview',{json:JSON.stringify(obj)},function(res){
		if(res.exist == 1){
			alert("该记录已存在！");
		}else{
			location.href="interView_interviewer.html";
		}
	});
	//alert("添加面试记录成功！");	
}

//面试管理中显示所有的面试人信息
function showAllMessage(){
	$.post('/rs/findAllMessage',function(res){
		var select = document.getElementById("select_message");	
		
		for(var i = 0;i < res.length;i++){
			var option = document.createElement("OPTION");			
			option.setAttribute("id",res[i+"apid"]);
			option.innerHTML = res[i+"sname"] +" 面试  " + res[i+"pname"];
			select.appendChild(option);
		}
	});
}

//获取当前系统时间,把添加面试记录中的日期最小值设置为当前日期。
function getDate(){	
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
	var datejsp = document.getElementById("interview_date");
	datejsp.min = myDate.join("-");
}
