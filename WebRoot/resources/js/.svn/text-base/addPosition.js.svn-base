//插入部门的字符串
var insert;
$(document).ready(function(){
	 $.post("/rs/po_getDepartmentName",
			 //回调函数
			 function(data , statusText)
			 {
		 		var allDNames=data.allDepartments;
		 		insert="<option value=\"\">--请选择--</option>";
		 		for(var i=0;i<allDNames.length;i++){
		 			insert=insert+"<option value=\""+allDNames[i].DId+"\">"+allDNames[i].DName+"</option>";
		 		}
		 		$("#department").html(insert);
		 		$("#department1").html(insert);
		 	},
	 	"json");
 });



//添加和查看按钮的切换
function changeView(which){
    var myname =which.getAttribute("name");
    which.setAttribute("class","active");
    if(myname=="add"){            
        var checkeds = document.getElementsByName("check");
        checkeds[0].setAttribute("class","");
        check.setAttribute("style","display:none;");
        add.setAttribute("style","display:block;");

    }else if(myname=="check"){
        var checkeds = document.getElementsByName("add");
        checkeds[0].setAttribute("class","");
        add.setAttribute("style","display:none;");
        check.setAttribute("style","display:block;");
        getAllPos();
    }
}
//向弹窗添加内容
var pop_tr=null;//记录tr方便回写到table上
function fixPop(which){
	//将记录在修改按钮上的职位id记录在保存按钮中
	var pos_id=which.getAttribute("data-id");
	savebutton.setAttribute("data-id",pos_id);
	
    var tr = which.parentNode.parentNode;
    var tds = tr.getElementsByTagName("td");
    var selects = popChange.getElementsByTagName("select");
    var inputs = popChange.getElementsByTagName("input");
    var textareas = popChange.getElementsByTagName("textarea");
    pop_tr = tr;//记录是哪一行修改了

    for(var i=1;i<tds.length-1;i++){
        if(i==tds.length-2){//最后一个textarea
            textareas[0].value = tds[i].innerText;
        }
        else if(i==1){
        	selects[0].value=tds[i].getAttribute("data-id");
        }
        else{
           inputs[i-2].value=tds[i].innerText;
        }
       
    }

}
//把弹窗内的信息回写到table上（保存成功后再将信息写回）
function save2table(){
	var selects = popChange.getElementsByTagName("select");
    var inputs = popChange.getElementsByTagName("input");
    var textareas = popChange.getElementsByTagName("textarea");
    var tds = pop_tr.getElementsByTagName("td");
    for(var i = 1;i<tds.length-1;i++){
        if(i==tds.length-2){
            tds[i].innerText = textareas[0].value;
        }
        else if(i==1){
        	tds[i].setAttribute("data-id",selects[0].value);//获取实际值即id
        	var text=selects[0].options[selects[0].selectedIndex].innerHTML;

        	tds[i].innerText=selects[0].options[selects[0].selectedIndex].innerHTML;//获取文本
        }
        else{
          tds[i].innerText = inputs[i-2].value;
        }
        
    }
}



//表单验证和添加数据
function checkadd(){
	var DId = $("#department").val();
	var position = $("#position").val();
	var number = $("#number").val();
	var date = $("#date").val();
	var departmentRequire = $("#departmentRequire").val();
	
	if(department==""){
		alert("部门不能为空！");
	}
	else if(position.trim()==""){
		alert("职位名称不能为空！");
	}
	else if(number==""){
		alert("人数不能为空！");
	}
	else if(isNaN(number)){
		alert("人数需为数字！");
	}
	else if(date==""){
		alert("截止日期不能为空！");
	}
	else if(departmentRequire.trim()==""){
		alert("职位要求不能为空！");
	}
	else{
		var data={DId:DId,position:position,number:number,date:date,departmentRequire:departmentRequire};
		$.post('/rs/po_InsertPos',{json:JSON.stringify(data)},function(res){
			if(res.isAdd==1){
				alert("添加职位成功！");
				changeView((document.getElementsByName("check"))[0]);
				//清空数据
				$("#department").val("");
				$("#position").val("");
				$("#number").val("");
				$("#date").val("");
				$("#departmentRequire").val("");
			}
			else{
				alert("添加职位失败！");
			}
		},"json");
		
	}
}

//获取所有的职位
function getAllPos(){
	$.post('/rs/po_getAllPos',
			function(data){
			var allPos=data.allPos;
			var string="";
			for(var i=0;i<allPos.length;i++){
		   string=string+"<tr>"+
                         "<td><input type=\"checkbox\" class=\"checkbox\" data-id=\""+allPos[i].pId+"\"></td>"+  
                         "<td data-id=\""+allPos[i].DId+"\">"+allPos[i].DName +"</td>"+
                         "<td>"+allPos[i].Pname +"</td>"+
                         "<td>"+allPos[i].Count +"</td>"+
                         "<td>"+allPos[i].DeadLine.substring(0,10) +"</td>"+
                         "<td>"+allPos[i].requirement +"</td>"+
                         "<td>"+                                
                            "<button  class=\"btn btn-default\" "+
                     "data-toggle=\"modal\" data-target=\"#popChange\" data-id=\""+allPos[i].pId+
                     "\" data-backdrop = \"static\" onclick=\"fixPop(this)\"><span class=\"glyphicon glyphicon-edit\">修改</span></button>"+
                         "</td>"+
                     "</tr>";//将职位的id记录在修改按钮的data-id中
			}
			$("#tbody").html(string);
		},"json");
}

//保存修改的内容
function updatePos(which){
	var DId = $("#department1").val();
	var position = $("#position1").val();
	var number = $("#people-number").val();
	var date = $("#deadline1").val();
	var departmentRequire = $("#departmentRequire1").val();
	
	if(department==""){
		alert("部门不能为空！");
	}
	else if(position.trim()==""){
		alert("职位名称不能为空！");
	}
	else if(number==""){
		alert("人数不能为空！");
	}
	else if(isNaN(number)){
		alert("人数需为数字！");
	}
	else if(date==""){
		alert("截止日期不能为空！");
	}
	else if(departmentRequire.trim()==""){
		alert("职位要求不能为空！");
	}
	else{
		var PId=which.getAttribute("data-id");
		var data={PId:PId,DId:DId,position:position,number:number,date:date,departmentRequire:departmentRequire};
		$.post('/rs/po_updatePos',{json:JSON.stringify(data)},function(res){
			if(res.isUpdate==1){
				//信息修改成功将数据写回到面板
				save2table();
				alert("修改信息成功！");
			}
			else{
				alert("修改信息失败！");
			}
		},"json");
	}
}

//删除职位
function del_Pos(){
    var arrays=new Array();
    var checks=document.getElementsByClassName("checkbox");
    var j=0;//要删除的数组的下标
    for(var i=0;i<checks.length;i++){//只能从后往前循环，否则删除一个后面的下标-1，删除不到
      if(checks[i].checked==true){
    	 arrays[j]=checks[i].getAttribute("data-id");
    	 j++;
      }
    }
    if(arrays.length>0){
        var data={delPos:arrays};
        $.post('/rs/po_delPos',{json:JSON.stringify(data)},function(res){
        	if(res.isDel==1){
    			//信息修改成功将数据写回到面板
    			changeView((document.getElementsByName("check"))[0]);
    			alert("删除职位成功！");
    		}
    		else{
    			alert("删除职位失败！");
    		}
        },"json");
    }
    else{
    	alert("请选择要删除的选项");
    }
}

//删除所有职位
function check_All(){
	var arrays=new Array();
    var checks=document.getElementsByClassName("checkbox");
    for(var i=0;i<checks.length;i++){
        checks[i].checked=true;
      }
}

//根据不同的查找方式给出不同的填写方法
function changeWay(which){
	var way=which.value;
	if(which.value==""){
		$("#search_key").html("<input type=\"text\" class=\"form-control\" size=\"30\" style=\"width:200px;\">");
	}
	else if(which.value=="1"){
		$("#search_key").html("<select class=\"form-control\" id=\"keyWord\" style=\"width:200px;\" value=\"\">" +insert+"</select>");
	}
	else if(which.value=="2"){
		$("#search_key").html("<input type=\"text\" class=\"form-control\" placeholder=\"请输入人数\" id=\"keyWord\" size=\"30\" style=\"width:200px;\" value=\"\">");
	}
	else if(which.value=="3"){
		$("#search_key").html("<input type=\"date\" class=\"form-control\" id=\"keyWord\" style=\"width:200px;\" value=\"\">");
	}
	else{
		$("#search_key").html("<input type=\"text\" class=\"form-control\" placeholder=\"请输入职位\" id=\"keyWord\" size=\"30\" style=\"width:200px;\" value=\"\">");
	}
}

//查找
function search(){
	var search_way=$("#search_way").val();
	if(search_way==""){
		//alert("请选择查询方式");
		getAllPos();
	}
	else {
		var key=keyWord.value;
		if(key==""){
			alert("请输入查询的关键字");
		}
		else if(search_way=="2"&&isNaN(key)){
			alert("人数需为数字");
		}
		else{
			var data={search_way:search_way,key:key};
			$.post('/rs/po_Search',{json:JSON.stringify(data)},function(res){
				var sear_pos=res.sear_pos;
				var string="";
				for(var i=0;i<sear_pos.length;i++){
			   string=string+"<tr>"+
		                     "<td><input type=\"checkbox\" class=\"checkbox\" data-id=\""+sear_pos[i].pId+"\"></td>"+  
		                     "<td data-id=\""+sear_pos[i].DId+"\">"+sear_pos[i].DName +"</td>"+
		                     "<td>"+sear_pos[i].Pname +"</td>"+
		                     "<td>"+sear_pos[i].Count +"</td>"+
		                     "<td>"+sear_pos[i].DeadLine.substring(0,10) +"</td>"+
		                     "<td>"+sear_pos[i].requirement +"</td>"+
		                     "<td>"+                                
		                        "<button  class=\"btn btn-default\" "+
		                 "data-toggle=\"modal\" data-target=\"#popChange\" data-id=\""+sear_pos[i].pId+
		                 "\" data-backdrop = \"static\" onclick=\"fixPop(this)\"><span class=\"glyphicon glyphicon-edit\">修改</span></button>"+
		                     "</td>"+
		                 "</tr>";//将职位的id记录在修改按钮的data-id中
				}
				$("#tbody").html(string);
			},"json");
		}
	}
}
