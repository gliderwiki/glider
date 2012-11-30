<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>위키에디터 v0.7</title>
<link href="/resource/editor/css/editor@style.css" rel="stylesheet" type="text/css">
<script type="text/javascript"	src="/resource/libs/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript"	src="/resource/libs/jquery/jquery_textinputs.js"></script>
<script type="text/javascript"	src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript"	src="/resource/libs/jquery/jquery.MultiFile.js"></script>

<script type="text/javascript">
    	
	var showOnHide;
	
	var xy={X:0,Y:0};
	
	var uploadCnt = 0;			//이미지업로드 갯수
	var accept_attachment_ext = new Array('gif','jpg','bmp','png','jpeg');
	$(document).ready(function(){
		
		$(".wikiIcon").mousemove(function(event) {               
             xy.X = event.pageX;
             xy.Y = event.pageY;
		});
		
		
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
		
		$("#activitor").click(function(){    			
  			$("#noticeViewYn").toggle(showOnHide);
  			
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
	
	//link 추가 layer띄우기
	function viewLinkAddr(){
		$("#linkLayer").css("top",(xy.Y)+"px")
        .css("left",(xy.X)+"px")
        .show();
		
	}
	
	//링크추가
	function setLinkAddr(){
		
		//setSurroundSelectedText(beforeText, afterText, centerText);
	}
	
	//이미지 미리보기
	function preViewImg() {
	
		if(uploadCnt == 0) 	$imgFullPath = $("#uploadFile").val();
		else				$imgFullPath = $("#uploadFile_F"+uploadCnt).val();
	
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
			if(accept_attachment_ext[i] == ext) return true;
		}
		return false;
	}
    	
    	
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
		
		console.log(selectedText);
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
	
  	
  	/**
	*	미리보기
	*/
	function preView(){
		var wikiHtml = $("#wikiText").val();
		
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

</head>
<body>
	<h3>@editor v0.7</h3>
		<div>이미지업로드시 이미지경로를 삽입해준다.</div>
		<p class="bold">* 추가 및 변경 해야할 사항</p>
		<div style="font-size: 11px;"><span id="activitor">[내용보기]</span></div>
		<div id="noticeViewYn" class="hidden">
			<p>1. html 태그 이용해서 테스트 후  미리보기 기능 구현 [해결여부 : O]</p>
			<p>2. 파일 업로드 시 해당 wiki태그 생성! [해결여부 : O]</p>
			<p>3. 크로스플랫폼에서 같은 동작 발생 시키기 [해결여부 : X]</p>
		</div>
	<div class="inputcontainer">
		<div class="wikiIcon">
			<input type="button" value="**굵은글씨**" onclick="setSurroundSelectedText('**', '**', '굵은글씨')" />
			<input type="button" value="//기울임//" onclick="setSurroundSelectedText('//','//', '기울임')" />
			<input type="button" value="__밑줄__" onclick="setSurroundSelectedText('__','__', '밑줄')" />
			<input type="button" value="링크추가" onclick="viewLinkAddr()" />
<!-- 			<input type="button" value="표추가" onclick="setTable();" /> -->
			<input type="button" value="미리보기" onclick="preView();" />
		</div>
		
		<div class="wikiEditorArea"> 
			<textarea rows="25" cols="100" id="wikiText"></textarea>		
		</div>
	</div>
	
	<div class="layer">
		<div class="bg"></div>
		<div class="layer_preView" id="layer1">
			<p id="wikiHtml" style="text-align:justify; font-family:Malgun gothic;"></p><br />
			<span class="button medium"><a href="#" id="layer_close">미리보기 닫기</a></span>
		</div>
	</div>
	
	
	<div id="viewUpload">
		<h3>File upload</h3>
		<form name="frmFile" action="/upload" id="frmFile" method="post" enctype="multipart/form-data">
			파일 : <input type="file" name="uploadFile" id="uploadFile" style="width:400" onchange="preViewImg()">
		
			<input type="button" value="파일 업로드" onclick="upload();" size="10">
		</form>
		<div id="imgPreviewList" class="previewList" style="border:#999 solid 3px; padding:10px;">
		<b>이미지 미리보기</b><br>
		</div>
		
		<div id="imgPreview" style="display:block;">
		</div>
	</div>
	
	
	
	<div id="linkLayer" style="display:none;width:200px;height: 90px; background-color:#DCDCDC; position: absolute; top:20px; left: 30%;z-index: 100;">
		<div style="padding: 5px 5px 5px 5px">
		
			링크삽입:<input type="text" id="linkAddr" value="" /><br />
			링크명:<input type="text" id="linkNm" value="" /><br />
			<input type="button" value="확인" onclick="setLinkAddr()">
		</div>
	</div>
</body>
</html>