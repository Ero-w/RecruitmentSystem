//��������
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
	//ɾ��ѡ������
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
	//�޸ļ�ɾ���������浽���ݿ�
	function callbackSave(){
        if(xmlHttp.readyState==4){
            if(xmlHttp.status==200){
                var xmlDoc=xmlHttp.responseText;
                if(xmlDoc="ok")alert("����ɹ�")
                else alert("����ʧ��")
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
   //��ӺͲ鿴��ť���л�
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
    //�򵯴��������
    var pop_tr=null;//��¼tr�����д��table��
    function fixPop(which){
        var tr = which.parentNode.parentNode;
        var tds = tr.getElementsByTagName("td");
        var inputs = modify.getElementsByTagName("input");
        var textareas = modify.getElementsByTagName("textarea");
        pop_tr = tr;//��¼����һ���޸���
        for(var i=1;i<tds.length-1;i++){
            if(i == 1){//��һ��input
                inputs[0].value = tds[i].innerText;
                continue;
            }
            textareas[i-2].value = tds[i].innerText;
        }
    }
    //�ѵ����ڵ���Ϣ��д��table��
    function save2table(){
        var inputs = modify.getElementsByTagName("input");
        var textareas = modify.getElementsByTagName("textarea");
        var tds = pop_tr.getElementsByTagName("td");//Ҫд�ص�ҳ�浥Ԫ��
        for(var i = 1;i<tds.length-1;i++){
            if(i==1){
                tds[1].innerText = inputs[0].value;
            }else{
              tds[i].innerText = textareas[i-2].value;
            }
            
        }
    }
    //ȡ����������
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
         	span.innerText="�޸�"
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
    //�ύ��������
    function callbackAdd(){
        if(xmlHttp.readyState==4){
            if(xmlHttp.status==200){
                var xmlDoc=xmlHttp.responseText;
                if(xmlDoc="ok")alert("��ӳɹ�")
                else alert("���ʧ��")
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