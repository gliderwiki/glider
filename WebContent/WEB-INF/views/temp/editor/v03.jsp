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
	//<![CDATA[

	$(document).ready(function() {

		$("#frmFile").ajaxForm(FileuploadCallback);
		$("#frmFile").attr("action","/registUpload");
		$("#frmFile").submit(function(){return false;});
		
		//사진 변경시
		$("#file").change(function(){
			//
			$("#frmFile").submit();
			return false;
		});
	});

	//파일업로드 이벤트
	function FileUpload(){
		if(!$("#file").val()){
			alert("파일을 선택하세요.");
			$("#file").focus();
			return;
		}
		
		//파일전송
		var frm;
		frm = $('#frmFile'); 
		frm.attr("action","/registUpload");
		frm.submit(); 
							
	}

	// 파일 업로드 콜백 
	function FileuploadCallback(data,state){
		//
		//
		
		data = data.removePre();

		var jsonStr = eval("("+data+")");
		if(jsonStr != null) {
			if(jsonStr.result == 1 ) {
				alert('파일 업로드가 완료되었습니다.');
				var appendPreview = "";
				
				
				
				var fileSrc = jsonStr.filePath+jsonStr.saveFileName;
				appendPreview += "<li id='"+jsonStr.saveFileName+"'>"; 
				appendPreview += "<a href=\"javascript:viewItem('"+fileSrc+"');\" title='미리보기'>"+jsonStr.realFileName+"</a>";
				appendPreview += "<a href=\"javascript:delItem('"+jsonStr.saveFileName+"');\" title='삭제'>[삭제]</a>";
				appendPreview += " | " + jsonStr.fileSize;
				appendPreview += "</li>";
				
				
				$("#previewImg").append(appendPreview);
				//$("img[name=preImg]").attr("src", "/resource/temp/"+jsonStr.filePath+jsonStr.saveFileName);
				//$("img[name=preImg]").attr("display", "block"); 
			} else {
				alert(jsonStr.msg);
			}
		} else {
			alert('에러가 발생하였습니다.');
		}
	}


	function viewItem(src) {
		oFrame = document.getElementById('ifrmView').contentWindow.document;
		oFrame.open();
		oFrame.close();
		var imgsrc = '/resource/temp'+src;
		ifrmView.document.write("<img src='"+imgsrc+"'>"); 
	}

	function delItem(fileNm) {
		//	
	}

	//]]>
	</script>
</head>
<body>
	<h3>@editor v0.3 (이미지 업로드)</h3>
		<p class="bold">* 추가 및 변경 해야할 사항</p>
		<div style="font-size: 11px;"><span id="activitor">[내용보기]</span></div>
		<div id="noticeViewYn" class="hidden">
			<p>1. html 태그 이용해서 테스트 후  미리보기 기능 구현 [해결여부 : O]</p>
			<p>2. 파일 업로드 시 해당 wiki태그 생성! [해결여부 : X]</p>
			<p>3. 크로스플랫폼에서 같은 동작 발생 시키기 [해결여부 : X]</p>
		</div>
	
	
	<form id="frmFile" name="frmFile" method="post" enctype="multipart/form-data">
		파일 업로드 테스트 합니다.
		<br /><br />
		<input type="file" size="30" name="file" id="file" /><br /><br />
		<input type="button" class="btn menu" value="확인" onclick="FileUpload();" />
	</form>
	<br />
	<div id="previewImg">
		
	</div>
	
	<iframe width="200" height="200" name="ifrmView" id="ifrmView" frameborder="0" scrolling="no" hspace="0" vspace="0"></iframe>
</body>
</html>