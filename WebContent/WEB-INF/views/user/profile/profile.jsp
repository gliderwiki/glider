<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/include/tags.jspf" %>
    
<body>

<section class="contents " role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">프로필 수정</h2>
				
		<form id="frmFile" name="frmFile" method="post" enctype="multipart/form-data">
		<input type="hidden" id="isUpload" name="isUpload" title="파일업로드확인" value=""/>
		<input type="hidden" id="weUserName" name="weUserName" title="이름" value="${weUser.we_user_name }"/>
		<input type="hidden" id="weFileIdx" name="weFileIdx" title="파일인덱스" value=""/>
		<input type="hidden" id="weUserIdx" name="weUserIdx" title="회원번호" value="${weUser.we_user_idx }"/>
		
			<div class="body-cont my">
				<div class="sub edit-profile">
					<div class="row type1">
						<strong class="tit">닉네임</strong>
						<input type="text" id="weUserNick" name="weUserNick" title="닉네임" value="${weUser.we_user_nick }"/>
					</div>
					<div class="row type1">
						<strong class="tit">이름</strong>
						<input type="text" id="userName" name="userName" title="이름"  value="${weUser.we_user_name }" disabled /></p>
					</div>
					<div class="row type1">
						<strong class="tit">홈페이지</strong>
						<input type="text" id="weUserSite" name="weUserSite" title="홈페이지"  value="${weProfile.we_user_site }" /></p>
					</div>
					<div class="profile-img">
						<strong class="tit">나를 표현할 수 있는 프로필 이미지를 등록하세요.</strong>
						<span class="box-img" id="previewImg">
						<c:set var="thumbImg" value="${weProfile.we_thumb_path }${weProfile.we_thumb_name }"/>
						<c:choose>
							<c:when test="${thumbImg eq ''}">
								<img id="preImg" src="" alt="" />
							</c:when>
							<c:otherwise>
								<img id="preImg" src="/resource/real${thumbImg }" height="98px" width="168px" alt="${weUser.we_user_nick }님의 프로필 이미지" />
							</c:otherwise> 
						</c:choose>
						</span>
						<input type="file" size="30" name="file" id="file" />
					</div>
					<div class="change-pw">
						<strong class="tit">비밀번호 변경하기</strong>
						<div class="wrap-row">
							<div class="row">
								<strong class="lab">이전 비밀번호</strong>
								<input type="password" id="userPassword" name="userPassword" title="비밀번호입력" value=""/>
								<span class="txt">현재 로그인 비밀번호를 입력하세요</span>
							</div>
							<div class="row">
								<strong class="lab">새 비밀번호</strong>
								<input type="password" id="weUserPwd" name="weUserPwd" title="비밀번호입력" value=""/>
								<span class="txt">새롭게 적용 비밀번호를 입력하세요</span>
							</div>
							<div class="row">
								<strong class="lab">비밀번호 확인</strong>
								<input type="password" id="userPassCheck" name="userPassCheck" title="비밀번호입력" value=""/>
								<span class="txt">새 비밀번호를 다시 한번 입력하세요</span>
							</div>
						</div>
					</div>
					
					<div class="change-pw">
						<strong class="tit">회원정보 삭제 및 탈퇴</strong>
						<div class="wrap-row">
							<div class="row">
								<strong class="lab"> 비밀번호</strong>
								<input type="password" id="awayPassword" name="awayPassword" title="비밀번호입력" value=""/>
								<span class="txt">비밀번호 확인 후 회원정보를 삭제처리 합니다.</span>
								<button type="button" class="btn-down" id="userAway">회원탈퇴</button>
							</div>
							
						</div>
					</div>
				</div>
			</div>
			<div class="foot-cont">
				<button type="button" class="btn" id="saveBtn" title="저장">저장</button>
			</div>
		</form>		
	</div>
</section>

<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/glider/common/wiki.common.util.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-loadingbar.js"></script>
<script type="text/javascript"> 
//<![CDATA[
var isUpload = 0;
$(document).ready(function() {
	//사진 변경시
	$("#file").change(function(){
		$.loadingBar();
		$("#frmFile").unbind();
		$("#frmFile").ajaxForm(FileuploadCallback);
		$("#frmFile").attr("action","/user/profileUpload");
		$("#frmFile").submit();
	});
	
	// 내용 수정
	$("#saveBtn").bind("click", function() {
		// form validation 
		// weUserNick 중복체크해야함  
		var weUserNick = $("#weUserNick").val();
		if(weUserNick.length < 2) {
			GliderWiki.alert("알림",'닉네임은 2글자 이상 입력하셔야 합니다.');
			return;
		}
		
		var passVal = $("#userPassword").val();
		if(passVal != '' && passVal != null) {	// 이전 비밀번호 값이 존재 할 경우 
			var pwd = $("#weUserPwd").val();
			var checkPwd = $("#userPassCheck").val();
			if(pwd.length < 4) {
				GliderWiki.alert("알림",'비밀번호는 4글자 이상으로 입력하세요.');
				return;
			} else if (pwd != checkPwd) {
				GliderWiki.alert("알림",'새 비밀번호가 일치하지 않습니다. 다시 확인하세요.');
				return;
			}
		}
		
		var userName = $("#userName").val();
		var weUserName = $("#weUserName").val();
		
		console.log("userName : "  +userName);
		console.log("weUserName : "  +weUserName);
		$("#frmFile").unbind();
		$("#isUpload").val(isUpload);
		$("#frmFile").ajaxForm(UpdateUserCallback);
		$("#frmFile").attr("action","/user/updateUserProfile");
		$("#frmFile").submit();
	});
	
	$("#userAway").bind("click", function() {
		var awayPassword = $("#awayPassword").val();
		var weUserIdx = "${loginUser.weUserIdx}";
		if(awayPassword == '' || awayPassword.length < 4) {
			GliderWiki.alert("알림",'비밀번호를 입력해주세요.');
			return;
		}
		
		GliderWiki.confirm('알림 ', '탈퇴하시겠습니까?\n회원 정보는 모두 삭제되며  같은 메일 주소로는 한 달간 회원 가입이 되지 않습니다.\n확인 버튼을 클릭하면 탈퇴 처리 됩니다.',  function() {
			callAwayUser(awayPassword, weUserIdx);
		});
	});
});

// 파일 업로드 콜백 
function FileuploadCallback(data,state){
	$.loadingBar.fadeOut();
	
	var jsonStr = eval(data);
	if(jsonStr != null) {
		if(jsonStr.result == 1 ) {
			isUpload = 1;		// 업로드 상태 변경 
			$("#weFileIdx").val(jsonStr.fileIndex);		// 파일 인덱스 세팅 
			
			GliderWiki.alert("알림",'파일 업로드가 완료되었습니다.');
			var fileSrc = jsonStr.thumbPath+jsonStr.thumbName;
			// 섬네일을 출력한 후 리사이징 한다. 
			$("#preImg").attr("src", "/resource/temp/"+fileSrc).css({height:"98px", width:"168px"}); 
			
		} else {
			alert(jsonStr.msg);
		}
	} else {
		GliderWiki.alert("알림",'에러가 발생하였습니다.');
	}
}

/**
 * 사용자 정보 수정 콜백 
 */
function UpdateUserCallback(data,state){
	//data = data.removePre();

	var jsonStr = eval(data);
	if(jsonStr != null) {
		if(jsonStr.result == 1 ) {
			GliderWiki.alert("알림",'수정 되었습니다.');
		} else {
			GliderWiki.alert("알림", jsonStr.msg);
		}
	} else {
		GliderWiki.alert("알림",'에러가 발생하였습니다.');
	}
}


function callAwayUser(awayPassword, weUserIdx) {
	var timer = null;
	
	$.ajax({
		type:"POST"
		,url:"/user/awayUser"
		,data:{"awayPassword":awayPassword,"weUserIdx":weUserIdx }
		,dataType:"json"
		,success:function(rtnObj){					
			var result = rtnObj.result;
			var msg = rtnObj.msg;
			if(result > 0){
				GliderWiki.alert("알림", msg);
				
				clearTimeout(timer); 
				timer = null;	
				timer = setTimeout(function () {  $(location).attr('href',"/logout");	 }, 3000); 	
			}else{
				GliderWiki.alert("알림", msg);
				return;
			}
		}
	});
}

//]]>
</script>
</js:scripts>
</body>