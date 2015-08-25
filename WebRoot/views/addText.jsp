<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String username=(String)session.getAttribute("username");
%>


<!DOCTYPE html>
<html>
<head>
    <title>添加题目</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" media="screen" href="/rs/resources/css/bootstrap.min.css">
    <link rel="stylesheet" media="screen" href="/rs/resources/css/style.css">
	<script src="/rs/resources/js/jquery-1.8.2.min.js"></script>
	<script src="/rs/resources/js/bootstrap.js"></script>
    <!-- Bootstrap Admin Theme -->
    <link rel="stylesheet" media="screen" href="/rs/resources/css/bootstrap-admin-theme.css">
  </head>
  <body>
  <a class="sr-only sr-only-focusable" href="#content">Skip to main content</a>
    <header class="navbar navbar-static-top bs-docs-nav" id="top" role="banner">
  <div class="container">
    <div class="navbar-header">
      <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a href="../" class="navbar-brand">员工招聘管理系统</a>
    </div>
    <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
      <ul class="nav navbar-nav">
                        <li><a href="addPosition.html">职位管理</a></li>
                        <li><a href="department.html">部门管理</a></li>
                        <li class="active"><a href="addText.html">题库管理</a></li>
                        <li><a href="authority.html">权限管理</a></li>
                        <li><a href="chart.html">报表统计</a></li>
                        <li><a href="personCenter_admin.html">个人中心</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      	<li><p style="padding:16px 5px 0px 0px;color:#428bca">Welcome</p></li>
        <li><a href="register.html"><%=username %></a></li>
      </ul>
    </nav>
  </div>
</header>
    <!--头部导航-->
<div class="bs-docs-header" id="content" style="height:1px;padding:0;background:#428bca">
</div>

<div class="container">
    <!-- left, vertical navbar & content -->
    <div class="row">
    
        <div class="col-md-12 bootstrap-admin-col-left">
            <ul class="nav nav-pills pull-left">
                <li name="addText" onclick="changeView(this)"><a href="#addText" >添加题目</a></li>
                <li name="allText" onclick="changeView(this)"><a href="#allText" >查看题目</a></li>
            </ul>
        </div>
        <br>
        <br>
		<br>
			<!--
            	作者：79250013@qq.com
            	时间：2015-08-19
            	描述：增加问题
            -->
        <div class="col-md-12" id="addText" style="display:none;">

            <div class="panel panel-default bootstrap-admin-no-table-panel">
	            <div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
		            <form class="form-horizontal">
		                <fieldset>
		                    <legend>添加题目</legend>
		            
		                    <div class="form-group">
		                        <label class="col-lg-2 control-label" for="title">题目</label>
		                        <div class="col-lg-10">
		                            <textarea class="form-control"  id="title" type="text" placeholder="请输入题目" style="height:200px;"></textarea>
		                        </div>
		                    </div>
		          
		                    <div class="form-group">
		                        <label class="col-lg-2 control-label" for="answer">答案</label>
		                        <div class="col-lg-10">
		                            <textarea class="form-control" id= "answer" type="text" placeholder="请输入题目答案" ></textarea>
		                        </div>
		                    </div>
		                    
		                   
		                    <button type="button" class="col-lg-offset-2 btn btn-primary" id="loginbtn" onclick="ajaxHandIn()">提交</button>
		                    <button type="reset" class="btn btn-default">重置</button>
		                </fieldset>
		            </form>
	        	</div>
        	</div>
   	 	</div>
	   	 	
        <div class="col-md-12" id="allText" style="display:none;">
            <div class="panel panel-default bootstrap-admin-no-table-panel">
	            <div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
	                <fieldset>
	                    <legend>查找题目:</legend>
	                    <div class="form-inline" style="padding-left:30%">                    
	                           <input type="text" id="searchValue" class="form-control" placeholder="题号/题目关键字" size="30">
	                           <button  type="button" class="btn btn-primary" id="search" onclick="searchQuestion()">
	                               <span class="glyphicon glyphicon-search"></span>
	                           </button>
	                    </div>
	                    <br>
	                    <br>
	                    <button type="button" class="btn btn-danger" onclick="deleteSelected()">删除</button>
	                    <button type="button" class="btn btn-default" onclick="saveQuestion()">保存全部</button>
	                    <table class="table bootstrap-admin-table-with-actions">
	
	                        <thead>
	                        <tr>
	                        	<th>选择</th>
	                            <th>题号</th>
	                            <th>题目内容</th>
	                            <th>答案</th>
	                            <th>操作</th>                          
	                        </tr>
	                        </thead>
	                        <!--
                            	作者：79250013@qq.com
                            	时间：2015-08-19
                            	描述：显示所有问题
                            -->
	                        <tbody id="tbody">
	                           <!--<tr>
	                            <td><input type="checkbox" class="checkbox"></td>   
	                            <td>998</td>
	                            <td>什么是 Servlet？</td>
	                            <td width="400px">
								Servlet 是用来处理客户端请求并产生动态网页内容的 Java 类。Servlet 主要是用来处理或者
								是存储 HTML 表单提交的数据，产生动态内容，在无状态的 HTTP 协议下管理状态信息			                            	
	                            </td>
	                            <td>                                
	                               <button  class="btn btn-primary" 
	                        data-toggle="modal" data-backdrop="static" 
	                        data-target="#modify" onclick="fixPop(this)"><span class="glyphicon glyphicon-edit">修改</span></button>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td><input type="checkbox" class="checkbox"></td>   
	                            <td>999</td>
	                            <td>什么是 Applet？</td>
	                            <td width="400px">
								java applet 是能够被包含在 HTML 页面中并且能被启用了 java 的客户端浏览器执行的程序。
								Applet 主要用来创建动态交互的 web 应用程序。			                            	
	                            </td>
	                            <td>                                
	                               <button  class="btn btn-primary" 
	                        data-toggle="modal" data-backdrop="static" 
	                        data-target="#modify" onclick="fixPop(this)"><span class="glyphicon glyphicon-edit">修改</span></button>
	                            </td>
	                        </tr>	      -->
	                        </tbody>
	                    </table>
	                </fieldset>
	        	</div>
        	</div>
   	 	</div>	 	   	 	
  	</div>
</div>
	<!--
    	作者：79250013@qq.com
    	时间：2015-08-19
    	描述：模态框
    -->
	<div id="modify" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">修改题目</h4>
                </div>

                <div class="modal-body">

                    <form class="form-horizontal">

                        <div class="form-group">
                            <label for="title1" class="control-label col-md-2">题号</label>
                            <div class="col-md-10">
                                <input type="text" id="" class="form-control" disabled="true"/>

                            </div>
                        </div>

                        <div class="form-group">
                            <label for="question-content" class="control-label col-md-2">题目内容</label>
                            <div class="col-md-10">
                                <textarea type="text" id="question-content" class="form-control" style="height: 200px"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="answer1" class="control-label col-md-2">答案</label>
                            <div class="col-md-10">
                                <textarea type="text" id="answer1" class="form-control"></textarea>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-12">
                                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="save2table()">保存</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            </div>
                        </div>                        

                    </form>

                </div>     

            </div>
        </div>
    </div>

</body>
<script type="text/javascript">
	//搜索问题
	function searchQuestion(){
		var tosearch=document.getElementById("searchValue").value;
		var question=document.getElementById("tbody");
		if(tosearch==""){
			var i=question.children.length-1;
			while(i>=0){
				question.childNodes[i].setAttribute("style","display:table-row;");
				i--;
			}
		}else{
			eval("var rep=/"+tosearch+"/");
			for(var count=0;count<question.children.length;count++){
				if((rep.test(question.childNodes[count].childNodes[2].innerText))||(rep.test(question.childNodes[count].childNodes[1].innerText))){
					question.childNodes[count].setAttribute("style","display:table-row;");
				}else{
					question.childNodes[count].setAttribute("style","display:none;");
				}
			}
		}
		
	}
	//删除选中问题
	function deleteSelected(){
		var tb=document.getElementById("tbody");
		var selected=tbody.getElementsByTagName("input");
		var todelete=new Array();
		for(var start=0,max=selected.length,count=0;start<max;start++){
			if(selected[start].checked==true){
				var tr=selected[start].parentElement.parentElement;
				todelete[count]=tr;
				count++;
			}
		}
		for(var i=0,end=count;i<end;i++){
			tb.removeChild(todelete[i]);
		}
	}
	//修改及删除操作保存到数据库
	function callbackSave(){
        if(xmlHttp.readyState==4){
            if(xmlHttp.status==200){
                var xmlDoc=xmlHttp.responseText;
                if(xmlDoc="ok")alert("保存成功")
                else alert("保存失败")
            }
        }
    }
	function ajaxSaveQuestion(dt){
		var url="/rs/save";
        if(window.XMLHttpRequest){
            xmlHttp=new XMLHttpRequest();
        }else if(window.ActiveXObject){
            xmlHttp=new ActiveXObject("Microsoft.XMLHTTP")
        }
        var x="question="+dt;
        xmlHttp.open("POST",url,true);
        xmlHttp.onreadystatechange=callbackSave;
        xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        xmlHttp.send(x);
	}
	function saveQuestion(){
		var tr=tbody.getElementsByTagName("tr");
		var toSave=new Array();
		for(var i=0;i<tr.length;i++){
			toSave[i]=new Array();
			for(var j=0;j<3;j++){
				toSave[i][j]=1;
			}
		}
		for(var i=0,end=tr.length;i<end;i++){
			toSave[i][0]=tr[i].childNodes[1].innerText;
			toSave[i][1]=tr[i].childNodes[2].innerText;
			toSave[i][2]=tr[i].childNodes[3].innerText;
		}
		var str=arrayToJson(toSave);
		ajaxSaveQuestion(str);
		
	}
	function arrayToJson(o) {   
        var r = [];   
        if (typeof o == "string") return "\"" + o.replace(/([\'\"\\])/g, "\\$1").replace(/(\n)/g, "\\n").replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\"";   
        if (typeof o == "object") {   
        if (!o.sort) {   
            for (var i in o)   
            r.push(i + ":" + arrayToJson(o[i]));   
            if (!!document.all && !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)) {   
            r.push("toString:" + o.toString.toString());   
            }   
            r = "{" + r.join() + "}";   
        } else {   
            for (var i = 0; i < o.length; i++) {   
            r.push(arrayToJson(o[i]));   
            }   
            r = "[" + r.join() + "]";   
        }   
        return r;   
        }   
        return o.toString();   
    }  
   //添加和查看按钮的切换
    function changeView(which){
        var myname =which.getAttribute("name");
        which.setAttribute("class","active");
        if(myname=="addText"){            
            var checkeds = document.getElementsByName("allText");
            checkeds[0].setAttribute("class","");
            allText.setAttribute("style","display:none;");
            addText.setAttribute("style","display:block;");

        }else if(myname=="allText"){
            var checkeds = document.getElementsByName("addText");
            checkeds[0].setAttribute("class","");
            addText.setAttribute("style","display:none;");
            allText.setAttribute("style","display:block;");
            ajaxCheckQuestion();
            
            }
        }
    //向弹窗添加内容
    var pop_tr=null;//记录tr方便回写到table上
    function fixPop(which){
        var tr = which.parentNode.parentNode;
        var tds = tr.getElementsByTagName("td");
        var inputs = modify.getElementsByTagName("input");
        var textareas = modify.getElementsByTagName("textarea");
        pop_tr = tr;//记录是哪一行修改了
        for(var i=1;i<tds.length-1;i++){
            if(i == 1){//第一个input
                inputs[0].value = tds[i].innerText;
                continue;
            }
            textareas[i-2].value = tds[i].innerText;
        }
    }
    //把弹窗内的信息回写到table上
    function save2table(){
        var inputs = modify.getElementsByTagName("input");
        var textareas = modify.getElementsByTagName("textarea");
        var tds = pop_tr.getElementsByTagName("td");//要写回的页面单元格
        for(var i = 1;i<tds.length-1;i++){
            if(i==1){
                tds[1].innerText = inputs[0].value;
            }else{
              tds[i].innerText = textareas[i-2].value;
            }
            
        }
    }
    //取得所有问题
    function checkQuestion(qt){
			var question=qt;
            var tb=document.getElementById("tbody");
            while(tb.hasChildNodes())tb.removeChild(tb.firstChild);
            for(var i=0;i<question.length;i++){
            var tr=document.createElement("tr");
            var td=document.createElement("td");
            var checkbox=document.createElement("input");
            checkbox.setAttribute("class","checkbox");
            checkbox.setAttribute("type","checkbox")
            td.appendChild(checkbox);
            tr.appendChild(td);
            td=document.createElement("td");
            td.innerText=question[i][0];
            tr.appendChild(td);
            td=document.createElement("td");
            td.innerText=question[i][1];
            tr.appendChild(td);
            td=document.createElement("td");
            td.innerText=question[i][2];
            td.setAttribute("width","400px");
            tr.appendChild(td);
         	td=document.createElement("td");
         	var button=document.createElement("button");
         	button.setAttribute("class","btn btn-primary");
         	button.setAttribute("onclick","fixPop(this)");
         	button.setAttribute("data-toggle","modal");
         	button.setAttribute("data-backdrop","static");
         	button.setAttribute("data-target","#modify");
         	var span=document.createElement("span");
         	span.setAttribute("class","glyphicon glyphicon-edit");
         	span.innerText="修改"
         	button.appendChild(span);
         	td.appendChild(button);
         	tr.appendChild(td);
            tb.appendChild(tr);
		}
	}
    function callbackCheck(){
        if(xmlHttp.readyState==4){
            if(xmlHttp.status==200){
                var xmlDoc=xmlHttp.responseText;
                var question=eval(xmlDoc);
                checkQuestion(question);
            }
        }
    }
    function ajaxCheckQuestion(){
        var url="/rs/qu_check";
        if(window.XMLHttpRequest){
            xmlHttp=new XMLHttpRequest();
        }else if(window.ActiveXObject){
            xmlHttp=new ActiveXObject("Microsoft.XMLHTTP")
        }
        xmlHttp.open("POST",url,true);
        xmlHttp.onreadystatechange=callbackCheck;
        xmlHttp.send();
    }
    //提交新增问题
    function callbackAdd(){
        if(xmlHttp.readyState==4){
            if(xmlHttp.status==200){
                var xmlDoc=xmlHttp.responseText;
                if(xmlDoc="ok")alert("添加成功")
                else alert("添加失败")
            }
        }
    }
	function ajaxHandIn(){
		var url="/rs/add";
        if(window.XMLHttpRequest){
            xmlHttp=new XMLHttpRequest();
        }else if(window.ActiveXObject){
            xmlHttp=new ActiveXObject("Microsoft.XMLHTTP")
        }
        var title=document.getElementById("title").value
        var answer=document.getElementById("answer").value;
        var x="title="+title+"&answer="+answer;
        xmlHttp.open("POST",url,true);
        xmlHttp.onreadystatechange=callbackAdd;
        xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        xmlHttp.send(x);
	}
    </script>



</html>

