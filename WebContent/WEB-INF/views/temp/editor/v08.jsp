<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Editor</title>
<meta name="robots" content="noindex,nofollow" />
<link rel="stylesheet" type="text/css" href="/resource/editor/css/editor@style.css"></link>
<script type="text/javascript"	src="/resource/libs/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript"	src="/resource/libs/jquery/jquery_textinputs.js"></script>
<script type="text/javascript"	src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript"	src="/resource/libs/jquery/jquery.MultiFile.js"></script>
<script type="text/javascript">
	var command = "";

	var uploadCnt = 0;
	var accept_attachment_ext = new Array('gif','jpg','bmp','png','jpeg');
	$(function(){
		$('#uploadFile').MultiFile({
			max:5,
			accept:'gif|jpg|bmp|png|jpeg',
			STRING: {
				remove: '<img src="resource/wiki/image/remove.gif" height="16" width="16" alt="x"/>',
				duplicate:'$file은 이미 추가된 파일입니다',
				selected:'Selecionado: $file',
				denied:'$ext는 업로드할 수 없는 확장자입니다'
			},
			onFileRemove: function(element, val, master_element){
				$imgList = $('.previewList a');
				$selectedFileName =  val.substring(val.lastIndexOf("\\")+1,val.length);
	
				$.each($imgList, function(index, value) {
					$list = $(value);
					if($list.attr("img-data") == $selectedFileName) {
						$list.remove();
					}
				});
			}
	
		});
	});	
	
	function preViewImg() {

		if(uploadCnt == 0) {
			$imgFullPath = $("#uploadFile").val();
		}else{
			$imgFullPath = $("#uploadFile_F"+uploadCnt).val();
		}

		$imgName = $imgFullPath.substring($imgFullPath.lastIndexOf("\\")+1,$imgFullPath.length);
		$imgExt = $imgName.substring($imgName.lastIndexOf(".")+1,$imgName.length);

		if(check_uploadFile_ext($imgExt)) {
			$imgList = $("#imgPreviewList");
			$imgList.append("<a onClick=\"showPreImage('"+$imgName+"')\" style=\"cursor:hand;\" img-data="+$imgName+">"+$imgName+"<br></a>");

			var options ={
				//success: showPreImage,
				url:"/ajax/preUpload",
				type:"post",
				contentType : "application/x-www-form-urlencoded;charset=UTF-8"
			};

			$("#frmFile").ajaxSubmit(options);
			uploadCnt ++;
		}
	}
	
	function preView(){
		
	}
	
	function showPreImage(fileName) {
		var preViewDiv = $("#imgPreview");

		var arrFileName = fileName.split(".");
		preViewDiv.html("<img src=/attachments/"+arrFileName[0]+"?ext="+arrFileName[1]+">");
		
		var imgPath = "/attachments/"+arrFileName[0]+"?ext="+arrFileName[1];
		
		editBtn('InsertImage', false, imgPath);
	}

	function upload() {
		$("#frmFile").submit();
	}

	function onUploadDone(msg) {
		alert(msg);
	}

	function check_uploadFile_ext(ext) {
		for(var i in accept_attachment_ext) {
			if(accept_attachment_ext[i] == ext) {
				return true;
			}
		}

		return false;
	}
	
	function editorDocument() {
		return document.getElementById('editor').contentDocument || document.getElementById('editor').contentWindow.document;
	}

	/**
	 *  문서가 로딩될 때까지는 execCommand 메서드가 수행되지 않는다.
	 *  [bVal=]object.execCommand(sCommand  [,bUserInterface]  [,vValue])
	 *
	 *  sCommand       : 필수적인 요소이며, 수행할 명령을 지정하는 문자열이다.
	 *
	 *  bUserInterface : true 명령어가 지원되면 사용자 인터페이스를 디스플레이한다.
	 *                 : false 디폴트이며 사용자 인터페이스를 디스플레이하지 않는다. 
	 *  
	 *  vValue         : 선택적인 요소이며, 지정되는 문자열, 수치 혹은 다른 값이다.
	 *                   가능한 값은 sCommand 명령에 따라 다르다. 
	 *
	 *  반환값 bVal     : bVal은 성공적으로 수행되었는가를 나타내는 부울값이다
	 */

	function editBtn(commendId, ifDisplay, value) {
		editorDocument().execCommand(commendId, ifDisplay, value);
		return;
	}
	
	function htmlSource() {
		/**
		*	editor.document.body.innerHTML 여기서 editor. <== 요넘은 editor
		*	
		*/
		alert('소스 : ' +	editor.document.body.innerHTML);   
//		alert('소스 : ' +	document.getElementById("editor").contentWindow.document);
		return;
	}
	
	function initToolBarButtons(){
		var doc = window.document;
		var kids = doc.getElementsByTagName("span");
		
		for(var i=0; i < kids.length; i++){
			if (kids[i].className == "imagebutton") {
		      kids[i].onmouseover = tbmouseover;
		      kids[i].onmouseout = tbmouseout;
		      kids[i].onmousedown = tbmousedown;
		      kids[i].onmouseup = tbmouseup;
		      kids[i].onclick = tbclick;
		    }
		}
	}
	
	function tbmousedown(e)
	{
	  var evt = e ? e : window.event; 

	  this.firstChild.style.left = 2;
	  this.firstChild.style.top = 2;
	  this.style.border="inset 2px";
	  if (evt.returnValue) {
	    evt.returnValue = false;
	  } else if (evt.preventDefault) {
	    evt.preventDefault( );
	  } else {
	    return false;
	  }
	}

	function tbmouseup()
	{
	  this.firstChild.style.left = 1;
	  this.firstChild.style.top = 1;
	  this.style.border="outset 2px";
	}

	function tbmouseout()
	{
	  this.style.border="solid 2px #C0C0C0";
	}

	function tbmouseover()
	{
	  this.style.border="outset 2px";
	}
	
	function insertNodeAtSelection(win, insertNode)
	  {
	      // get current selection
	      var sel = win.getSelection();
	      // get the first range of the selection
	      // (there's almost always only one range)
	      var range = sel.getRangeAt(0);
	      // deselect everything
	      sel.removeAllRanges();
	      // remove content of current selection from document
	      range.deleteContents();
	      // get location of current selection
	      var container = range.startContainer;
	      var pos = range.startOffset;
	      // make a new range for the new selection
	      range=document.createRange();
	      if (container.nodeType==3 && insertNode.nodeType==3) {
	        // if we insert text in a textnode, do optimized insertion
	        container.insertData(pos, insertNode.nodeValue);
	        // put cursor after inserted text
	        range.setEnd(container, pos+insertNode.length);
	        range.setStart(container, pos+insertNode.length);
	      } 
	      else {
	        var afterNode;
	        if (container.nodeType==3) {
	          // when inserting into a textnode
	          // we create 2 new textnodes
	          // and put the insertNode in between
	          var textNode = container;
	          container = textNode.parentNode;
	          var text = textNode.nodeValue;
	          // text before the split
	          var textBefore = text.substr(0,pos);
	          // text after the split
	          var textAfter = text.substr(pos);
	          var beforeNode = document.createTextNode(textBefore);
	          afterNode = document.createTextNode(textAfter);
	          // insert the 3 new nodes before the old one
	          container.insertBefore(afterNode, textNode);
	          container.insertBefore(insertNode, afterNode);
	          container.insertBefore(beforeNode, insertNode);
	          // remove the old node
	          container.removeChild(textNode);
	        } else {
	          // else simply insert the node
	          afterNode = container.childNodes[pos];
	          container.insertBefore(insertNode, afterNode);
	        }
	        range.setEnd(afterNode, 0);
	        range.setStart(afterNode, 0);
	      }
	      sel.addRange(range);
	  };
	
	var xyPoint = {X:0,Y:0};
	
	$(".toolbar").mousemove(function(event) {               
    	xyPoint.X = event.pageX;
    	xyPoint.Y = event.pageY;
	});
	
	function tbclick()
	{
	  if ((this.id == "forecolor") || (this.id == "hilitecolor")) {
	    parent.command = this.id;
	    $("#colorpalette").css("top",(xyPoint.Y+110)+"px").css("left",(xyPoint.X+450)+"px").show();
	    
	  } 
	  else if (this.id == "createlink") {
	    var szURL = prompt("Enter a URL:", "http://");
	    if ((szURL != null) && (szURL != "")) {
	    	editBtn("CreateLink",false,szURL);
	    }
	  } 
	  else if (this.id == "createimage") {
	    imagePath = prompt('Enter Image URL:', 'http://');
	    if ((imagePath != null) && (imagePath != "")) {
	    	editBtn('InsertImage', false, imagePath);
	    }
	  } 
	  else if (this.id == "createtable") {
	    rowstext = prompt("enter rows");
	    colstext = prompt("enter cols");
	    rows = parseInt(rowstext);
	    cols = parseInt(colstext);
	    if ((rows > 0) && (cols > 0)) {
	      table = editorDocument().createElement("table");
	      table.setAttribute("border", "1");
	      table.setAttribute("cellpadding", "2");
	      table.setAttribute("cellspacing", "2");
	      table.setAttribute("width", "400px");
	      table.setAttribute("height", "100px");
	      tbody = editorDocument().createElement("tbody");
	      for (var i=0; i < rows; i++) {
	        tr =editorDocument().createElement("tr");
	        for (var j=0; j < cols; j++) {
	          td =editorDocument().createElement("td");
	          br =editorDocument().createElement("br");
	          td.appendChild(br);
	          tr.appendChild(td);
	        }
	        tbody.appendChild(tr);
	      }
	      table.appendChild(tbody);      
	      insertNodeAtSelection(document.getElementById("editor").contentWindow, table);
	    }
	  } 
	  else {
		  editBtn(this.id, false, null);
	  }
	}
	
	function Select(selectname)
	{
	  var cursel = document.getElementById(selectname).selectedIndex;
	  /* First one is always a label */
	  if (cursel != 0) {
	    var selected = document.getElementById(selectname).options[cursel].value;
	    editBtn(selectname, false, selected);
	    document.getElementById(selectname).selectedIndex = 0;
	  }
//	  document.getElementById("edit").contentWindow.focus();
	}
	
	function dismisscolorpalette()
	{
	  $("#colorpalette").hide();
	}
	
	function selectColor(color)
	{
		editBtn(parent.command, false, color);
		dismisscolorpalette();
	}

	function InitColorPalette() {
		
	  var x = $("#colorpalette table td");
	  
//	  if (document.getElementsByTagName) x = document.getElementsByTagName('td');
//	  else if (document.all) 			 x = document.all.tags('td');
	  
	  for (var i=0;i<x.length;i++)
	  {
	    x[i].onmouseover = over;
	    x[i].onmouseout = out;
	    x[i].onclick = click;
	  }
	}

	function over()
	{
		this.style.border='2px dotted white';
	}

	function out()
	{
		this.style.border='1px solid gray';
	}

	function click()
	{
	  selectColor(this.id);
	}
	
	
	function start(){
		
		initToolBarButtons();
		InitColorPalette();
		/*
		if (document.addEventListener) {
		    document.addEventListener("mousedown", dismisscolorpalette, true);
		    editorDocument().addEventListener("mousedown", dismisscolorpalette, true);
		    document.addEventListener("keypress", dismisscolorpalette, true);
		    editorDocument().addEventListener("keypress", dismisscolorpalette, true);
		  } else if (document.attachEvent) {
		    document.attachEvent("mousedown", dismisscolorpalette, true);
		    editorDocument().attachEvent("mousedown", dismisscolorpalette, true);
		    document.attachEvent("keypress", dismisscolorpalette, true);
		    editorDocument().attachEvent("keypress", dismisscolorpalette, true);
		  }
		*/
	}
	
	$(function(){
		$("#colorpalette").live("mouseout",dismisscolorpalette);
	});
</script>
</head>
<body onload="start()">
	<h1>에디터 화면</h1>

	<div id="toolbarList" class="toolbar">
		<span class="imagebutton" id="cut"><img class="image" src="/resource/editor/images/toolbar/cut.gif" alt="" title=""></span> 
		<span class="imagebutton" id="copy"><img class="image" src="/resource/editor/images/toolbar/copy.gif" alt="" title=""></span> 
		<span class="imagebutton" id="paste"><img class="image"src="/resource/editor/images/toolbar/paste.gif" alt="" title=""></span> 
		<span class="imagebutton" id="undo"><img class="image" src="/resource/editor/images/toolbar/undo.gif" alt="" title=""></span> 
		<span class="imagebutton" id="redo"><img class="image" src="/resource/editor/images/toolbar/redo.gif" alt="" title=""></span> 
		<span class="imagebutton" id="createlink"><img class="image" src="/resource/editor/images/toolbar/link.gif" alt="" title=""></span> 
		<span class="imagebutton" id="createimage"><img class="image"src="/resource/editor/images/toolbar/image.gif" alt="insert image"title="insert image"></span> 
		<span class="imagebutton" id="createtable"><img class="image" src="/resource/editor/images/toolbar/table.gif" alt="insert table"title="insert table"></span> 
		<span> 
			<select id="fontname" onchange="Select(this.id)">
				<option value="Font">Font</option>
				<option value="Arial">Arial</option>
				<option value="Courier">Courier</option>
				<option value="Times New Roman">Times New Roman</option>
			</select> 
		</span> 
		<span> 
			<select id="fontsize" onchange="Select(this.id)">
				<option value="Size">Size</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
			</select> 
		</span> 
		<span class="imagebutton" id="bold"><img class="image" src="/resource/editor/images/toolbar/bold.gif" alt="" title=""></span> 
		<span class="imagebutton" id="italic"><img class="image" src="/resource/editor/images/toolbar/italic.gif" alt="" title=""> </span> 
		<span class="imagebutton" id="underline"><img class="image" src="/resource/editor/images/toolbar/underline.gif" alt="" title=""></span> 
		<span class="imagebutton" id="forecolor"><img class="image"src="/resource/editor/images/toolbar/forecolor.gif" alt="" title=""></span> 
		<span class="imagebutton" id="hilitecolor"><img class="image"src="/resource/editor/images/toolbar/backcolor.gif" alt="" title=""></span> 
		<span class="imagebutton" id="justifyleft"><img class="image"src="/resource/editor/images/toolbar/justifyleft.gif" alt="" title=""></span> 
		<span class="imagebutton" id="justifycenter"><img class="image" src="/resource/editor/images/toolbar/justifycenter.gif" alt=""title=""></span> 
		<span class="imagebutton" id="justifyright"><img class="image" src="/resource/editor/images/toolbar/justifyright.gif" alt="" title=""></span>
		<span class="imagebutton" id="insertorderedlist"><img class="image" src="/resource/editor/images/toolbar/orderedlist.gif" alt="" title="" ></span>
		<span class="imagebutton" id="insertunorderedlist"><img class="image" src="/resource/editor/images/toolbar/unorderedlist.gif" alt="" title="" ></span>
		<span class="imagebutton" id="outdent"><img class="image" src="/resource/editor/images/toolbar/outdent.gif" alt="" title="" ></span>
		<span class="imagebutton" id="indent"><img class="image" src="/resource/editor/images/toolbar/indent.gif" alt="" title="" ></span>
	</div>
	<div class="editor">
		<iframe id="editor" name="editor" src="/resource/editor/plugin/richEditor.html" frameborder="0" scrolling="auto" style="" allowtransparency="true"></iframe>
	</div>

	<h3>File upload</h3>
	<form name="frmFile" action="/upload" id="frmFile" method="post" enctype="multipart/form-data">
		파일 : <input type="file" name="uploadFile" id="uploadFile" style="width: 400" onchange="preViewImg()"> <input type="button" value="파일 업로드" onclick="upload();" size="10">
	</form>
	<div id="imgPreviewList" class="previewList" style="border: #999 solid 3px; padding: 10px;">
		<b>이미지 미리보기</b><br>
	</div>

	<div id="imgPreview" style="display: block;"></div>
	
	<div id="colorpalette" style="position: absolute; display:none; background-color:white; z-index: 100;">
		<table border="1" cellpadding="1" cellspacing="1">
			<tr>
				<td id="#FFFFFF" bgcolor="#FFFFFF" width="20" height="20"><img width="1" height="1"></td>
				<td id="#FFCCCC" bgcolor="#FFCCCC" width="20" height="20"><img width="1" height="1"></td>
				<td id="#FFCC99" bgcolor="#FFCC99" width="20" height="20"><img width="1" height="1"></td>
				<td id="#FFFF99" bgcolor="#FFFF99" width="20" height="20"><img width="1" height="1"></td>
				<td id="#FFFFCC" bgcolor="#FFFFCC" width="20" height="20"><img width="1" height="1"></td>
				<td id="#99FF99" bgcolor="#99FF99" width="20" height="20"><img width="1" height="1"></td>
				<td id="#99FFFF" bgcolor="#99FFFF" width="20" height="20"><img width="1" height="1"></td>
				<td id="#CCFFFF" bgcolor="#CCFFFF" width="20" height="20"><img width="1" height="1"></td>
				<td id="#CCCCFF" bgcolor="#CCCCFF" width="20" height="20"><img width="1" height="1"></td>
				<td id="#FFCCFF" bgcolor="#FFCCFF" width="20" height="20"><img width="1" height="1"></td>
			</tr>
			<tr>
				<td id="#CCCCCC" bgcolor="#CCCCCC" width="20" height="20"><img width="1" height="1"></td>
				<td id="#FF6666" bgcolor="#FF6666" width="20" height="20"><img width="1" height="1"></td>
				<td id="#FF9966" bgcolor="#FF9966" width="20" height="20"><img width="1" height="1"></td>
				<td id="#FFFF66" bgcolor="#FFFF66" width="20" height="20"><img width="1" height="1"></td>
				<td id="#FFFF33" bgcolor="#FFFF33" width="20" height="20"><img width="1" height="1"></td>
				<td id="#66FF99" bgcolor="#66FF99" width="20" height="20"><img width="1" height="1"></td>
				<td id="#33FFFF" bgcolor="#33FFFF" width="20" height="20"><img width="1" height="1"></td>
				<td id="#66FFFF" bgcolor="#66FFFF" width="20" height="20"><img width="1" height="1"></td>
				<td id="#9999FF" bgcolor="#9999FF" width="20" height="20"><img width="1" height="1"></td>
				<td id="#FF99FF" bgcolor="#FF99FF" width="20" height="20"><img width="1" height="1"></td>
			</tr>
			<tr>
				<td id="#C0C0C0" bgcolor="#C0C0C0" width="20" height="20"><img width="1" height="1"></td>
				<td id="#FF0000" bgcolor="#FF0000" width="20" height="20"><img width="1" height="1"></td>
				<td id="#FF9900" bgcolor="#FF9900" width="20" height="20"><img width="1" height="1"></td>
				<td id="#FFCC66" bgcolor="#FFCC66" width="20" height="20"><img width="1" height="1"></td>
				<td id="#FFFF00" bgcolor="#FFFF00" width="20" height="20"><img width="1" height="1"></td>
				<td id="#33FF33" bgcolor="#33FF33" width="20" height="20"><img width="1" height="1"></td>
				<td id="#66CCCC" bgcolor="#66CCCC" width="20" height="20"><img width="1" height="1"></td>
				<td id="#33CCFF" bgcolor="#33CCFF" width="20" height="20"><img width="1" height="1"></td>
				<td id="#6666CC" bgcolor="#6666CC" width="20" height="20"><img width="1" height="1"></td>
				<td id="#CC66CC" bgcolor="#CC66CC" width="20" height="20"><img width="1" height="1"></td>
			</tr>
			<tr>
				<td id="#999999" bgcolor="#999999" width="20" height="20"><img width="1" height="1"></td>
				<td id="#CC0000" bgcolor="#CC0000" width="20" height="20"><img width="1" height="1"></td>
				<td id="#FF6600" bgcolor="#FF6600" width="20" height="20"><img width="1" height="1"></td>
				<td id="#FFCC33" bgcolor="#FFCC33" width="20" height="20"><img width="1" height="1"></td>
				<td id="#FFCC00" bgcolor="#FFCC00" width="20" height="20"><img width="1" height="1"></td>
				<td id="#33CC00" bgcolor="#33CC00" width="20" height="20"><img width="1" height="1"></td>
				<td id="#00CCCC" bgcolor="#00CCCC" width="20" height="20"><img width="1" height="1"></td>
				<td id="#3366FF" bgcolor="#3366FF" width="20" height="20"><img width="1" height="1"></td>
				<td id="#6633FF" bgcolor="#6633FF" width="20" height="20"><img width="1" height="1"></td>
				<td id="#CC33CC" bgcolor="#CC33CC" width="20" height="20"><img width="1" height="1"></td>
			</tr>
			<tr>
				<td id="#666666" bgcolor="#666666" width="20" height="20"><img width="1" height="1"></td>
				<td id="#990000" bgcolor="#990000" width="20" height="20"><img width="1" height="1"></td>
				<td id="#CC6600" bgcolor="#CC6600" width="20" height="20"><img width="1" height="1"></td>
				<td id="#CC9933" bgcolor="#CC9933" width="20" height="20"><img width="1" height="1"></td>
				<td id="#999900" bgcolor="#999900" width="20" height="20"><img width="1" height="1"></td>
				<td id="#009900" bgcolor="#009900" width="20" height="20"><img width="1" height="1"></td>
				<td id="#339999" bgcolor="#339999" width="20" height="20"><img width="1" height="1"></td>
				<td id="#3333FF" bgcolor="#3333FF" width="20" height="20"><img width="1" height="1"></td>
				<td id="#6600CC" bgcolor="#6600CC" width="20" height="20"><img width="1" height="1"></td>
				<td id="#993399" bgcolor="#993399" width="20" height="20"><img width="1" height="1"></td>
			</tr>
			<tr>
				<td id="#333333" bgcolor="#333333" width="20" height="20"><img width="1" height="1"></td>
				<td id="#660000" bgcolor="#660000" width="20" height="20"><img width="1" height="1"></td>
				<td id="#993300" bgcolor="#993300" width="20" height="20"><img width="1" height="1"></td>
				<td id="#996633" bgcolor="#996633" width="20" height="20"><img width="1" height="1"></td>
				<td id="#666600" bgcolor="#666600" width="20" height="20"><img width="1" height="1"></td>
				<td id="#006600" bgcolor="#006600" width="20" height="20"><img width="1" height="1"></td>
				<td id="#336666" bgcolor="#336666" width="20" height="20"><img width="1" height="1"></td>
				<td id="#000099" bgcolor="#000099" width="20" height="20"><img width="1" height="1"></td>
				<td id="#333399" bgcolor="#333399" width="20" height="20"><img width="1" height="1"></td>
				<td id="#663366" bgcolor="#663366" width="20" height="20"><img width="1" height="1"></td>
			</tr>
			<tr>
				<td id="#000000" bgcolor="#000000" width="20" height="20"><img width="1" height="1"></td>
				<td id="#330000" bgcolor="#330000" width="20" height="20"><img width="1" height="1"></td>
				<td id="#663300" bgcolor="#663300" width="20" height="20"><img width="1" height="1"></td>
				<td id="#663333" bgcolor="#663333" width="20" height="20"><img width="1" height="1"></td>
				<td id="#333300" bgcolor="#333300" width="20" height="20"><img width="1" height="1"></td>
				<td id="#003300" bgcolor="#003300" width="20" height="20"><img width="1" height="1"></td>
				<td id="#003333" bgcolor="#003333" width="20" height="20"><img width="1" height="1"></td>
				<td id="#000066" bgcolor="#000066" width="20" height="20"><img width="1" height="1"></td>
				<td id="#330099" bgcolor="#330099" width="20" height="20"><img width="1" height="1"></td>
				<td id="#330033" bgcolor="#330033" width="20" height="20"><img width="1" height="1"></td>
			</tr>
		</table>
	</div>
	
	
	
	
	<input type="button" value="미리보기" onclick="htmlSource();" />
</body>
</html>


