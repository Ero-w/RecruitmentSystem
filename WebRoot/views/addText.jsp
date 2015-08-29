<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String username=(String)session.getAttribute("username");
int sid=Integer.parseInt(session.getAttribute("sid").toString());
if(username==null||sid!=1){
	response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
  String newLocn = "/rs/views/login.html";
  response.setHeader("Location",newLocn);
}
if(sid==2)
  response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
  String newLocn = "/rs/views/addText_interview.jsp";
  response.setHeader("Location",newLocn);
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
<script src="/rs/resources/js/addText.js"></script>



</html>

