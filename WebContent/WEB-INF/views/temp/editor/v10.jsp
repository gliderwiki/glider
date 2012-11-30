<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>위키에디터 v0.10</title>
<link href="/resource/editor/css/editor@style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h3>@editor v0.10</h3>
	<div class="inputcontainer">
		<div class="wikiIcon">
			<input type="button" value="**굵은글씨**" onclick="setSurroundSelectedText('**', '**', '굵은글씨')" />
			<input type="button" value="//기울임//" onclick="setSurroundSelectedText('//','//', '기울임')" />
			<input type="button" value="__밑줄__" onclick="setSurroundSelectedText('__','__', '밑줄')" />
<!-- 			<input type="button" value="링크추가" onclick="viewLinkAddr()" /> -->
<!-- 			<input type="button" value="표추가" onclick="setTable();" /> -->
			<input type="button" value="미리보기" onclick="preView();" />
		</div>
		
		<div class="wikiEditorArea"> 
			<textarea rows="20" cols="90" id="wikiEditor"></textarea>		
		</div>
	</div>
	
	<div class="layer">
		<div class="bg"></div>
		<div class="layer_preView" id="layer1">
			<p id="wikiHtml" style="text-align:justify; font-family:Malgun gothic;"></p><br />
			<span class="button medium"><a href="#" id="layer_close">미리보기 닫기</a></span>
		</div>
	</div>
	
	
	
	
	<div id="linkLayer" style="display:none;width:200px;height: 90px; background-color:#DCDCDC; position: absolute; top:20px; left: 30%;z-index: 100;">
		<div style="padding: 5px 5px 5px 5px">
		
			링크삽입:<input type="text" id="linkAddr" value="" /><br />
			링크명:<input type="text" id="linkNm" value="" /><br />
			<input type="button" value="확인" onclick="setLinkAddr()">
		</div>
	</div>
	
<js:scripts>
	<script type="text/javascript" src="/resource/libs/jquery/jquery_textinputs.js"></script>
	<script type="text/javascript">
	var showOnHide;
	
	var xy={X:0,Y:0};
	
	$(document).ready(function() {
			$(".wikiIcon").mousemove(function(event) {               
	            xy.X = event.pageX;
	            xy.Y = event.pageY;
			});
		
		   	$('.layer .bg').click(function() {
		   		$('.layer').fadeOut();
		   	});
		   	
		   	$('#layer_close').click(function() {
		   		$('.layer').fadeOut();
		   		return false;
		   	});
	
		});	
		
		//link 추가 layer띄우기
		function viewLinkAddr(){
			$("#linkLayer").css("top",(xy.Y)+"px")
	       .css("left",(xy.X)+"px")
	       .show();
			
		}
		
		//링크추가
		function setLinkAddr(){
			setSurroundSelectedText(beforeText, afterText, centerText);
		}
	
		function setSurroundSelectedText(beforeText, afterText, centerText){
	 		
	 		if(document.selection){//IE
	 			ieSetSurroundSelectedText(beforeText, afterText, centerText);
	 			return;
			}
	 		var range = $("#wikiEditor").getSelection();
	 		
	 		var centerTxt = "";
	 		
	 		if(range.text == '' || range.text.length < 1){
	 			centerTxt = centerText;
	 		}
	 		
	 		$("#wikiEditor").surroundSelectedText(beforeText+centerTxt, afterText, true);
	 		$("#wikiEditor").focus();
	 	}
	
	 	function ieSetSurroundSelectedText(beforTag,afterTag,defaultVal){
		
			var ctrl =document.getElementById("wikiEditor");
			ctrl.focus();
			var selectedText=document.selection.createRange();
			var newText = selectedText.text;
			
			if(selectedText.text == '' || selectedText.text == null){
				newText = defaultVal;
			}
			newText=beforTag+''+newText+''+afterTag;
		
			selectedText.text=newText;
		}
	 	
	 	/*
	 	 * text inert 시키기
	 	 */
	 	function setInsertText(insertTxt){
	 		if(document.selection){					//IE
	 			ieSetSurroundSelectedText(insertTxt, '', '');
	 			return;
			}
	 		$("#wikiEditor").surroundSelectedText(insertTxt, '', true);
	 		$("#wikiEditor").focus();
	 	}
	 	
	 	/*
	 	 * 표 생성 
	 	 * 
	 	 * @생각하기 : 행이 바뀔때 구분해줘야할 태그가 필요할 것 같음
	 	 *
	 	 */
	 	function setTable(){
	 		var row =9;
	 		var col =6;
	 		var table="";
	 		for(var i=0; i<row; i++){
	 			for(var j=0; j<col; j++){
	 				//구분자 태그
	 				var cellSplit=" | ";
	 				
	 				//첫번째 row    				
	 				if(i == 0){
	 					cellSplit="|| ";
	 					table += cellSplit+"표제목";
	 				}
	 				else{
	 					table += cellSplit+"셀내용";
	 				}
	 				
						if(j == (col-1)){
							table +=cellSplit;
							table +="\n";
						}
	 			}
	 		}
	 		setInsertText(table);
	 	}
		
	 	
	 	/**
		*	미리보기
		*/
		function preView(){
			var wikiHtml = $("#wikiEditor").val();
			
			$.ajax({
				type:"POST"
				,url:"/testWikiPasering"
				,data:{html:wikiHtml}
				,success:function(response){
					var status = response.status;
					if(status == 'SUCCESS'){
						$("#wikiHtml").html(response.result);
				   		layer_open("layer1");
					}else{
						alert('실패');
					}
				}
			});
			
		}
		
	 	
	 	function layer_open(el) {
	 		$('.layer').fadeIn();
	 		var temp = $('#'+el);
	 		if(temp.outerHeight() < $(document).height()) temp.css('margin-top', '-'+temp.outerHeight() / 2 + 'px');
	 		else temp.css('top', '0px');
	 		
	 		if(temp.outerWidth() < $(document).width()) temp.css('margin-left', '-'+temp.outerWidth() / 2 + 'px');
	 		else temp.css('left', '0px');
	 	}
	</script>
</js:scripts>
</body>
</html>