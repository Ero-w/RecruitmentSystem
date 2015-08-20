$(function(){
	var html = "<tr><td>Iven</td><td>男</td><td>21</td><td>13570498469</td><td>1570052573@qq.com</td></tr>";
	$(html).appendTo("#staff>tbody");
});

$(function(){
	var html = "<tr><td>Iven</td><td>本科</td><td>软件工程</td></tr>" +
	   		   "<tr><td>Alice</td><td>本科</td><td>软件工程</td></tr>";
	$(html).appendTo("#resume>tbody");
});

$(function(){
	var html = "<tr><td>七夕是什么节日？</td><td>情人节</td><td>1</td></tr>";
	$(html).appendTo("#question>tbody");
});

$("#btn").click(function(){
	var target = $("#realName>option:selected").attr("data-target");
	$(target).show().siblings().hide();
});