<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
</head>
<body>
<h3>Multi File Upload Example</h3>
<div class="uploadArea">
	<form name="uploadForm" id="uploadForm" action="/upload" id="frmFile" method="post" enctype="multipart/form-data">
		파일 : <input type="file" name="uploadFile" id="uploadFile" style="width:400">
		
		<input type="button" value="파일 업로드" class="uploadBtn" size="10">
	</form>

	<b>이미지 미리보기</b><br>
	<div id="imgPreview" style="display:block;">
	</div>
</div>

<script type="text/javascript" src="/resource/libs/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.MultiFile.js"></script>
<script type="text/javascript" src="/resource/glider/common/wiki.js"></script>
<script type="text/javascript" src="/resource/glider/common/multiFileUpload.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	var fileUploader = new GliderWiki.File.Upload($(".uploadArea")).action();
});

</script>
</body>
</html>