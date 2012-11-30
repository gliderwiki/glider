<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/resource/editor/css/editor@style.css" rel="stylesheet" type="text/css">
<script type="text/javascript"	src="/resource/libs/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript"	src="/resource/libs/jquery/jquery_textinputs.js"></script>
<script type="text/javascript"	src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript"	src="/resource/libs/jquery/jquery.MultiFile.js"></script>
<script type="text/javascript">
		var wikiText = $("#wikiText");	
    	
    	$(document).ready(function(){
    		$("#wikiText").bind('keyup mousedown mousemove mouseup', function(e) {
    			var range = $(this).getSelection();

    			contentId = this.id;
    			startText = range.start;
    			endText = range.end;
    			textLen = range.length;
    			contentText = range.text;
    			
    			// just dump the values
    			$('#output').html(
    				  "@id: "+ contentId + "<BR>"
    				+ "@start: "	+ range.start + "<BR>"
    				+ "@length: " + range.length + "<BR>"
    				+ "@end: " + range.end + "<BR>"
    				+ ((typeof(range['row']) != 'undefined') ? "caret row: " + range.row + "<BR>" : '')
    				+ ((typeof(range['col']) != 'undefined') ? "caret col: "	+ range.col + "<BR>" : '')
    				+ "@selected text:<span class=\"txt\">" + range.text + "</span><BR>"
    				+ "@range : "+range
    				);	
 		   	});
    	});
    	
    	
    	/*
    	 *
    	 *
    	 */
    	function setSurroundSelectedText(beforeText, afterText, centerText){
    		
    		if(document.selection){//IE
    			ieSetSurroundSelectedText(beforeText, afterText, centerText);
    			return;
			}
    		var range = $("#wikiText").getSelection();
    		
    		var centerTxt = "";
    		
    		if(range.text == '' || range.text.length < 1){
    			centerTxt = centerText;
    		}
    		
    		
    		$("#wikiText").surroundSelectedText(beforeText+centerTxt, afterText, true);
    		$("#wikiText").focus();
    	}

    	function ieSetSurroundSelectedText(beforTag,afterTag,defaultVal){
			
			var ctrl =document.getElementById("wikiText");
			ctrl.focus();
			var selectedText=document.selection.createRange();
			var newText = selectedText.text;
			
			if(selectedText.text == '' || selectedText.text == null){
				newText = defaultVal;
			}
			newText=beforTag+''+newText+''+afterTag;

			selectedText.text=newText;
			/*
			var selectedText=document.selection?document.selection.createRange().text:el.value.substring(el.selectionStart,el.selectionEnd);// IE:Moz
			
			var newText=tag+''+selectedText+''+tag;
			if(document.selection){//IE
				document.selection.createRange().text=newText;
			}
			else{//Moz
				el.value=el.value.substring(0,el.selectionStart)+newText+el.value.substring(el.selectionEnd,el.value.length);
			}
			*/
		}
    	
    	/*
    	 * text inert 시키기
    	 */
    	function setInsertText(insertTxt){
    		if(document.selection){					//IE
    			ieSetSurroundSelectedText(insertTxt, '', '');
    			return;
			}
    		$("#wikiText").surroundSelectedText(insertTxt, '', true);
    		$("#wikiText").focus();
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
		
		
		var showOnHide;
    	$(document).ready(function(){
			$("#activitor").click(function(){    			
    			
    			$("#noticeViewYn").toggle(showOnHide);
    			
   				//$("#activitor").empty();    			
    			if(showOnHide){
    				$("#noticeVeiwYn").addClass("noticeTodoList");
    			}else{
    				$("#activitor").text("[내용닫기]");
    				$("#noticeVeiwYn").addClass("hidden");
    			}
    		});

	    	$('.layer .bg').click(function() {
	    		$('.layer').fadeOut();
	    	});
	    	
	    	$('#layer_close').click(function() {
	    		$('.layer').fadeOut();
	    		return false;
	    	});
    		
    	});
		
    	
    	/**
    	*	미리보기
    	*/
    	function preView(){
    		var wikiHtml = $("#wikiText").val();
    		$("#wikiHtml").html(wikiHtml);
	   		layer_open("layer1");
    	}
		
    	
    	function layer_open(el) {
    		$('.layer').fadeIn();
    		var temp = $('#'+el);
    		if(temp.outerHeight() < $(document).height()) temp.css('margin-top', '-'+temp.outerHeight() / 2 + 'px');
    		else temp.css('top', '0px');
    		
    		if(temp.outerWidth() < $(document).width()) temp.css('margin-left', '-'+temp.outerWidth() / 2 + 'px');
    		else temp.css('left', '0px');
    	}	
    	
    	
    	function setImgSrc(){
    		var imgStr = $("#testImgScr").val();
    		setInsertText("[[[["+imgStr+"]]]]");
    	}
    	
	</script>
</head>
<body>
	<h3>@editor v0.4</h3>
		<div>가상으로 이미지업로드시 이미지경로를 뿌려준다.</div>
		<p class="bold">* 추가 및 변경 해야할 사항</p>
		<div style="font-size: 11px;"><span id="activitor">[내용보기]</span></div>
		<div id="noticeViewYn" class="hidden">
			<p>1. html 태그 이용해서 테스트 후  미리보기 기능 구현 [해결여부 : O]</p>
			<p>2. 파일 업로드 시 해당 wiki태그 생성! [해결여부 : X]</p>
			<p>3. 크로스플랫폼에서 같은 동작 발생 시키기 [해결여부 : X]</p>
		</div>
	<div class="inputcontainer">
		<div class="wikiIcon">
			<input type="button" value="**굵은글씨**" onclick="setSurroundSelectedText('**', '**', '굵은글씨')" />
			<input type="button" value="@@IE테스트@@" onclick="setSurroundSelectedText('@@','@@', 'IE테스트')" />
			<input type="button" value="{code}코드{code}" onclick="setSurroundSelectedText('{code}','{code}', '코드')" />
			<input type="button" value="표추가" onclick="setTable();" />
			<input type="button" value="미리보기" onclick="preView();" />
		</div>
		
		<div class="wikiEditorArea"> 
			<textarea rows="25" cols="100" id="wikiText"></textarea>		
		</div>
	</div>
	
	<div>
		가상 : 이미지 업로드 <input type="text" id="testImgScr" name="testImgScr" value="" /> <a onclick="setImgSrc();">[확인]</a>
	</div>
	
	<pre id="output"></pre>
	
	<div id="tableLayer">
	</div>
		
	<div id="testTableView"></div>
	
	
	<div class="layer">
		<div class="bg"></div>
		<div class="layer_preView" id="layer1">
			<p id="wikiHtml" style="text-align:justify; font-family:Malgun gothic;"></p><br />
			<span class="button medium"><a href="#" id="layer_close">미리보기 닫기</a></span>
		</div>
		
	</div>

</body>
</html>