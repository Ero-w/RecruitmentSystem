$("#btn").click(function(){
	var $option = $("#realName>option:selected");
	var target = $option.attr("data-target");
	$target = $(target);
	var length = $target[0].children.length;
	if(length==0 && $target.html()!="没有信息"){
		eval("display"+target.substr(1)+"Message()");
	}
	$("#title").html($option.html()+"信息");
	$target.show().siblings().hide();
});

var color = ["#f00", "#0f0", "#00f", "#789", "#abcdef"];

function addTableAndCanvas(header){
	var html = "<table class='table table-responsive table-bordered table-condensed'>"
		+ "<thead><tr>";
	for(var i=0; i<header.length; i++){
		html += "<th>" + header[i] + "</th>";
	}
	html += "<canvas width='480' height='300'></canvas>" 
		 + "</tr></thead>" 
		 + "<tbody></tbody>"
		 + "</table>";
	return html;
}

function displayStaffMessage(){
	$.post("/rs/authority_readAllStaff",function(data,statusText){
		if(data.map.length==0){
			$("#Staff").html("没有数据");
			return;
		}
		
		var header = ["姓名", "性别", "年龄", "电话", "邮箱"];
		var html = "<canvas width='480' height='300'></canvas>";
		html += addTableAndCanvas(header);
		$("#Staff").html(html);
		
		html = "";
		$tbody = $("#Staff tbody");
		var array1 = [0, 0, 0];
		var array2 = [0, 0, 0, 0, 0, 0];
		var length = data.map.length;
		array1[0] = array2[0] = length;
		
		for(var i=0; i<length; i++){
			html += "<tr>"
        		+"<td>"+data.map[i].sname +"</td>"
        		+"<td>"+ (data.map[i].sex==1?"女":"男") +"</td>"
        		+"<td>"+data.map[i].age +"</td>"
        		+"<td>"+data.map[i].tel +"</td>"
        		+"<td>"+data.map[i].mail +"</td>"
        		+"</tr>";
			data.map[i].sex==0 ? array1[1]++ : array1[2]++;
			
			var age = data.map[i].age;
			if(age >= 18 && age <= 28) array2[1]++;
			else if(age <= 40) array2[2]++;
			else if(age <= 48) array2[3]++;
			else if(age <= 55) array2[4]++;
			else array2[5]++;
		}
		$(html).appendTo($tbody);
		
		var message1 = ["男  ", "女  "];
		drawChart($("#Staff canvas:eq(0)"), "系统人员性别百分比", array1, message1);
		var message2 = ["18-28岁 ", "29-40岁 ", "41-48岁 ", "49-55岁 ", "56岁以上  "];
		drawChart($("#Staff canvas:eq(1)"), "系统人员年龄百分比", array2, message2);
	},"json");
};

function displayResumeMessage(){
	$.post("/rs/re_readAllResume",function(data,statusText){
		if(data.map.length==0){
			$("#Resume").html("没有数据");
			return;
		}
		
		var header = ["姓名", "学历", "专业技能"];
		var html = addTableAndCanvas(header);
		$("#Resume").html(html);
		
		html = "";
		$tbody = $("#Resume tbody");
		var array = [0, 0, 0, 0, 0];
		var length = data.map.length;
		array[0] = length;
		
		for(var i=0; i<length; i++){
			html += "<tr>"
        		+"<td>"+data.map[i].staff.sname +"</td>"
        		+"<td>"+ data.map[i].education+"</td>"
        		+"<td>"+data.map[i].skill +"</td>"
        		+"</tr>";
			
			switch(data.map[i].education.trim()){
				case "专科": array[1]++; break;
				case "大学": array[2]++; break;
				case "硕士": array[3]++; break;
				case "博士": array[4]++; break;
			}
		}
		$(html).appendTo($tbody);
		
		var message = ["专科 ", "大学 ", "硕士 ", "博士 "];
		drawChart($("#Resume canvas:eq(0)"), "简历上的学历百分比", array, message);
	},"json");
};

function displayQuestionMessage(){
	$.post("/rs/qu_readAllQuestion",function(data,statusText){
		if(data.map.length==0){
			$("#Question").html("没有数据");
			return;
		}
		
		var header = ["标题", "答案", "难度"];
		var html = addTableAndCanvas(header);
		$("#Question").html(html);

		html = "";
		$tbody = $("#Question tbody");
		var array = [0, 0, 0, 0, 0, 0];
		var length = data.map.length;
		array[0] = length;
		
		for(var i=0; i<length; i++){
			html += "<tr>"
        		+"<td>"+data.map[i].title +"</td>"
        		+"<td>"+ data.map[i].answer+"</td>"
        		+"<td>"+data.map[i].level +"</td>"
        		+"</tr>";
			array[data.map[i].level]++;
		}
		$(html).appendTo($tbody);
		
		var message = ["难度1 ", "难度2 ", "难度3 ", "难度4 ", "难度5 "];
		drawChart($("#Question canvas:eq(0)"), "题目难度等级百分比", array, message);
	},"json");
};

function drawChart($canvas, title, num, message){
	var canvas = $canvas[0];
	if(canvas.getContext){
		var ctx = canvas.getContext('2d');
		ctx.font="20px Arial";
		ctx.fillText(title,120,30);
		var percentage = new Array(num.length-1);
		for(var i=1; i<num.length; i++){
			percentage[i-1] = (num[i]/num[0]).toFixed(2);
			message[i-1] += 100*percentage[i-1];
			message[i-1] += "%";
		}
		addCircle(ctx, color, percentage);
		addIllustration(ctx, 320, 50, color, message);
	}
}

CanvasRenderingContext2D.prototype.sector = function (x, y, radius, sDeg, eDeg) {
	this.save();
	this.translate(x, y);
	this.beginPath();
	this.arc(0,0,radius,sDeg, eDeg);
	this.save();
	this.rotate(sDeg);
	this.moveTo(radius,0);
	this.lineTo(0,0);
	this.restore();
	this.rotate(eDeg);
	this.lineTo(radius,0);
	this.closePath();
	this.fill();
	this.restore();
	return this;
};

function addCircle(ctx, color, percentage){
	var x=200, y=150, radius=100;
	var angleOfACircle = 2*Math.PI;
	var startAngle=0.0, endAngle=0.0;
	for(var i=0; i<percentage.length; i++){
		ctx.fillStyle=color[i];
		endAngle = startAngle + percentage[i]*angleOfACircle;
		ctx.sector(x,y,radius,startAngle,endAngle);
		startAngle = endAngle;
	}
};

function addIllustration(ctx, x, y, color, message){
	ctx.font="20px Arial";
	for(var i=0; i<message.length; i++){
		ctx.fillStyle=color[i];
		ctx.fillRect(x,y+30*i,15,15); 
		ctx.fillText(message[i],x+20,y+30*i+15);
	}
};