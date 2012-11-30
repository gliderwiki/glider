<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>


<style type="text/css">
.pic60 img{width:60px; height:60px; border:1px solid #dcdcdc; }

</style>

</head>

<body>
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

<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/glider/common/wiki.common.util.js"></script>
<script type="text/javascript"> 
//<![CDATA[

$(document).ready(function() {

	$("#frmFile").ajaxForm(FileuploadCallback);
	$("#frmFile").attr("action","/registUpload");
	$("#frmFile").submit(function(){return false;});
	
	//사진 변경시
	$("#file").change(function(){

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

//파일 업로드 콜백 
function FileuploadCallback(data,state){


	
	data = data.removePre();

	var jsonStr = eval("("+data+")");
	if(jsonStr != null) {
		if(jsonStr.result == 1 ) {
			alert('파일 업로드가 완료되었습니다.');
			var appendPreview = "";



			var fileSrc = jsonStr.filePath+jsonStr.saveFileName;
			appendPreview += "<li id='"+jsonStr.saveFileName+"'>"; 
			appendPreview += "<a href=\"javascript:viewItem('"+fileSrc+"');\" title='미리보기'>"+jsonStr.realFileName+"</a>";
			appendPreview += " <a href=\"javascript:delItem('"+jsonStr.fileIndex+"', '"+jsonStr.saveFileName+"');\" title='삭제'>[삭제]</a>";
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

/**
* 파일 업로드 후 엔트리 클릭시 아이프레임에 미리보기 
*/
function viewItem(src) {
	oFrame = document.getElementById('ifrmView').contentWindow.document;
	oFrame.open();
	oFrame.close();
	var imgsrc = '/resource/temp'+src;
	ifrmView.document.write("<img src='"+imgsrc+"' width=\"200\">"); 
}

/**
* 이미지 삭제 버튼 후 아이프레임에 이미지 삭제 
*/
function hiddenItem() {
	oFrame = document.getElementById('ifrmView').contentWindow.document;
	oFrame.open();
	oFrame.close();
	ifrmView.document.write(""); 
}

/**
* 이미지 삭제 
*/
function delItem(fileIndex, fileName) {


	// DB에서 이미지 삭제 처리함. 
	
	// 아이 프레임에서 이미지 삭제 
	hiddenItem();	
}

//]]>
</script>
</js:scripts>
</body>

