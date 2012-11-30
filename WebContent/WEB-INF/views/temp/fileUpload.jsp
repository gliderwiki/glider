<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/resource/wiki/uploadify/uploadify.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/resource/wiki/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="/resource/wiki/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#fileUploader').uploadify({
            'swf'           : '/resource/wiki/uploadify/uploadify.swf', 					//파일업로드 이벤트를 가로챌 플래쉬.
            'uploader'      : '/multiUploadFile', 													//비동기 파일 업로드 처리 페이지 url.
            'cancelImage'   : '/resource/wiki/uploadify/uploadify-cancel.png',  // 파일업로드시 취소 버튼 이미지.
            'checkExisting' : '',           																//기존 파일이 존재하는지 체크할 페이지 url. 
            'postData'      : {'fileNm' : 'attach_'+new Date().getTime() },       	//uploader 페이지 호출시 파라미터 추가. 
            'folder'        : '/resource/sample/temp',
            'buttonText'    : "파 일 첨 부",
            'auto'          : true,                         												//자동 파일 업로드 여부
            'onComplete': function(event, queueID, fileObj, response, data){
                var form = document.forms['form'];
                form.enctype  = "multipart/form-data";
                var i = 0;
                var el = document.createElement("input");
                    el.type = "hidden";
                    el.name = "test";
                    el.id = "test2";
                    el.value = fileObj.name;
                    form.appendChild(el); },
            'onUploadComplete'  : function(file, queue) {   								//업로드 완료후 처리 callback 함수
                for(var name in file) {
                    //업로드후 파일정보
                  //  alert(name + "=" + file[name]);
                }
            }
          });
	});
</script>
</head>
<body>

	
	<div>
		<fieldset>
			<legend>다중건 업로드</legend>
			<form action="" method="post" enctype="multipart/form-data">
				<input type="file"  id="fileUploader" name="fileData" />
			</form>
		</fieldset>
	</div>
</body>
</html>