<!-- 실제 리치웹 에디터 임  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Editor</title>
<meta name="robots" content="noindex,nofollow" />
<style type="text/css">
.editor {
	border: 1px solid gray;
}

.editor iframe {
	width: 100%;
	height: 200px;
}
</style>
<script type="text/javascript"	src="/resource/libs/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript"	src="/resource/libs/jquery/jquery_textinputs.js"></script>
<script type="text/javascript"	src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript"	src="/resource/libs/jquery/jquery.MultiFile.js"></script>
<script type="text/javascript">
	var uploadCnt = 0;
	var accept_attachment_ext = new Array('gif','jpg','bmp','png','jpeg');
	$(document).ready(function(){
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
	
	
</script>
</head>
<body>
	<h1>에디터 화면</h1>

	<div class="toolbar">
		<button onclick="editBtn('BOLD', false)">굵게</button>
		<button onclick="editBtn('italic')">이탤릭</button>
		<button onclick="editBtn('underline')">언더라인</button>
		<button onclick="editBtn('fontSize','20')">글자크기</button>
		<button onclick="editBtn('fontName', '궁서')">궁서체</button>
		<button onclick="htmlSource()">소스보기</button>
	</div>
	<div class="editor">
		<iframe id="editor" name="editor" src="/resource/editor/plugin/richEditor.html" frameborder="0" scrolling="auto" style="" allowtransparency="true"></iframe>
	</div>
	
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
</body>
</html>


