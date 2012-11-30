<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %><%@
taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>::Enterprise OpenSource Wiki – GLiDER™ </title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />

	<meta http-equiv="Expires" content="0"/>
	<meta http-equiv="Pragma" content="no-cache"/>
	<meta name="Subject" content="GLiDER, OpenSource Wiki, OpenSource KMS, 한국형 오픈소스 위키, 협업툴, 지식정보공유툴 ">
	<meta name="Title" content="OpenSource Wiki GLiDER, 한국형 오픈소스 위키  글라이더 ">
	<meta name="Description" content="위키 기반의 한국형 오픈소스 협업 툴, 프로젝트 관리 및 산출물 작성, 지식관리도구 , 지식정보공유, 교육자료 및 프리젠테이션 ">
	<meta name="Keywords" content="OpenSource, Wiki, Java, JSP, Spring, MySQL, HSQL, Enterprise OpenSource Wiki, KMS, 지식관리도구 , 지식정보공유">
	<meta name="Author" content="GLiDERWiki™ OpenSource Team">
	<meta name="Publisher" content="GLiDERWiki™ OpenSource Team">
	<meta name="Other Agent" content="GLiDERWiki™ OpenSource Team">
	<meta name="Classification" content=" Wiki, Java, JSP, Spring, MySQL, HSQL, KMS">
	<meta name="Author-Date(Date)" content="2012.10.1">
	<meta name="Location" content="Seoul, KOREA, South KOREA">
	<meta name="Distribution" content="GLiDERWiki™ OpenSource Team">
	<meta name="Copyright" content="GLiDERWiki™ OpenSource Team">
	<meta name="Robots" content="ALL">
	<!--[if IE]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
</head>

<body>
<!--[if lt IE 8]>
<p style="padding:1em;border-bottom:10px dashed #fff;background-color:#efefef;">
	<strong>낡은 브라우저</strong>를 사용하고 계십니다.<br />
	<a href="http://browsehappy.com/">새로운 브라우저로 업그레이드</a> 하거나 <a href="http://www.google.com/chromeframe/?redirect=true">구글 크롬프레임을 설치</a>해서 더 빠르고 아름다운 웹을 경험해 보세요.
</p>
<![endif]-->

<section class="contents main" role="main">
	<div class="wrap-cont">
		<div class="body-cont">
			<div class="box-top">
				<div class="about">
					<h2>About GLiDER Wiki</h2>
					<p>- <a href="http://www.gliderwiki.org" target="_blank">GLiDER™ Wiki</a>는 글(Contents)을 작성하는 사람(Writer), 즉 컨텐츠 혹은 지식을 생성하는 이를 뜻하는 협업 기반의 기업형 Enterprise <a href="http://terms.co.kr/wiki.htm" target="_blank">Wiki</a> 입니다.</p>
					<p>- <a href="http://www.gliderwiki.org" target="_blank">GLiDER™ Wiki</a>는 손쉬운 태그 생성 기반의 마크업 에디터를 제공합니다. 설치 및 사용법은 <a href="#">사용메뉴얼</a> 페이지를 통해 GLiDER Wiki의 사용법을 확인할 수 있습니다.  </p>
					<p>- <a href="http://www.gliderwiki.org" target="_blank">GLiDER™ Wiki</a>는 빠르게 HTML 문서를 작성하여 특정 (혹은 회원 전체) 그룹, 특정 사용자에게 공유하는  지식공유관리  <a href="http://terms.co.kr/KMS.htm" target="_blank">KMS플랫폼</a>입니다.</p>
					<p>- <a href="http://www.gliderwiki.org" target="_blank">GLiDER™ Wiki</a>는 오픈소스이므로 커뮤니티 버전은 무료로 이용할 수 있습니다. 단, 하단의 Copyright 를 삭제하거나 변경할 수 없습니다. <a href="#">라이센스 정책</a>을 참고해주세요.</p>
					<p>- <a href="http://www.gliderwiki.org" target="_blank">GLiDER™ Wiki</a>의 설치 및 사용이 어려우시면  <a href="#">기술지원 및 교육안내</a>를  참고하세요.</p>
				</div>
				<div class="login">
				<c:choose>
					<c:when test="${loginUser.weUserIdx == null or loginUser.weUserIdx == 0}">
					<form:form id="loginForm" name="loginForm" action="/user/login" method="post">
					<input type="hidden" name="spring-security-fail-redirect" value="/index?login_error=true" />
					<input type="hidden" name="spring-security-redirect" value="/dashboard" />

						<div class="wrap-chk">
							<input type="checkbox" id="checkSaveId" name="checkSaveId" value="Y" />
							<label for="checkSaveId">Save ID</label>
							<input type="checkbox" id="_spring_security_remember_me" name="_spring_security_remember_me" value="true">
							<label for="_spring_security_remember_me">자동로그인</label>
						</div>
						<div class="row">
							<input type="text" id="j_username" name="j_username" title="아이디입력"  value="" />
						</div>
						<div class="row">
							<input type="password" id="j_password" name="j_password" title="비밀번호입력" value=""/>
						</div>

						<div class="row util">
							<a style="cursor:pointer" id="searchUserInfo">로그인이 안되나요?</a>
							<a href="/join">계정이 없으신가요?</a>
						</div>
						<div class="wrap-btn">
							<button type="button" id="loginBtn" title="로그인" class="btn">로그인</button>
						</div>
					</form:form>
					</c:when>
					<c:otherwise>
						<div style="font-size:14px;">
							<div class="row">
								<b>환영합니다.</b> ${loginUser.weUserNick } 님!
							</div>
							<div class="row">
								<b>현재 활동 포인트</b> : ${loginUser.wePoint } Point
							</div>
							<div class="row">
								<b>회원 레벨 </b> :
								<c:choose>
								<c:when test="${loginUser.weGrade eq '1'}">위키레벨</c:when>
								<c:when test="${loginUser.weGrade eq '3'}">공간레벨</c:when>
								<c:when test="${loginUser.weGrade eq '8'}">운영레벨</c:when>
								<c:when test="${loginUser.weGrade eq '9'}">어드민</c:when>
								</c:choose>
							</div>
							<div class="row">
								<b>내가 작성한 위키</b> : 총 ${myWikiCnt}개
							</div>
							<div class="row">
								<b>내 인맥</b> : ${myPeopleCnt} 명 / ${toMePeopleCnt} 명
								<button type="button" class="btn-info" id="myConnect" onclick="location.href='/user/connection'" class="btn">인맥조회</button>
							</div>
						</div>
						<div class="wrap-btn" style="padding-top:15px">
							<button type="button" id="loginBtn" title="내 정보 수정" onclick="location.href='/user/profile'"class="btn">프로필 수정</button>
							<button type="button" id="loginBtn" title="로그아웃" onclick="location.href='/logout'" class="btn">로그아웃</button>
						</div>
					</c:otherwise>
				</c:choose>
				</div>
			</div>
			<div class="wrap-box">
				<div class="left">
					<div class="box">
						<h2>베스트 Wiki</h2>
						<ul class="list">
							<common:wikihomelist wikiList="${bestWikiList}" />
						</ul>
					</div>
					<div class="box">
						<h2>최신 Wiki</h2>
						<ul class="list">
							<common:wikihomelist wikiList="${recentWikiList}" />
						</ul>
					</div>
				</div>
				<div class="right">
					<div class="count">
						<h2>통계</h2>
						<div class="box-count">
							<div class="visit box">
								<h3>방문</h3>
								<div class="num">${visitCountInfo.totalVisitCount} / ${visitCountInfo.todayVisitCount}</div>
							</div>
							<div class="wiki box">
								<h3>개설된 Wiki</h3>
								<div class="num">${allWikiCnt}</div>
							</div>
							<div class="tag box">
								<h3>총태그</h3>
								<div class="num">${allTagCnt}</div>
							</div>
						</div>
					</div>
					<div class="wrap-btn">
						<a href="/dashboard" class="btn-main preview">둘러보기</a>
						<a href="javascript:alert('준비중...')" class="btn-main apply">다운로드</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>


<iframe name="fileDownload" width="0" height="0" frameborder="0"  style="display:hidden"></iframe>

<form id="downloadForm" name="downloadForm" method="post" action="">
<input type="hidden" id="filaName" name="filaName" value="" />
</form>


<js:scripts>
<script type="text/javascript" charset="UTF-8" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" charset="UTF-8" src="/resource/libs/jquery/jquery.cookie.js"></script>
<script type="text/javascript" charset="UTF-8" src="/resource/libs/plugin/jquery-search-user.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/interface/LoginService.js'></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-about-glider.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-glider-license.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-glider-support.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-loadingbar.js"></script>

<script type="text/javascript">
//<![CDATA[
$(document).ready(function() {
	//$("#loginForm").ajaxForm(LoginUserCallBack);
	//쿠키 저장 여부에따라 아이디 저장된것 보여줌
	if ($.cookie("saveUserId") != null) {
		$("#j_username").val($.cookie("saveUserId"));
		$("#checkSaveId").attr("checked", "checked");
	}

	//로그인 버튼 클릭
	$("#loginBtn").bind("click", function() {
		loginCheckSubmit();
	});

	$("#j_password").keydown(function(e) {
		if (e.keyCode == 13) {
			loginCheckSubmit();
		}
	});

	$("#searchUserInfo").bind("click", function() {
		$.search_user();
	});

	<c:if test="${not empty param.login_error}">
	GliderWiki.alert('알림', '<fmt:message key="account.authenticationFailure" />');
	</c:if>
});


function aboutWiki() {
	$.about_glider();
}

function licensePolicy() {
	$.glider_license();
}

function supportTech() {
	$.glider_support();
}


function loginCheckSubmit() {
	if ($("#j_username").val() == "") {
		GliderWiki.alert('알림','아이디를 입력해 주세요.');
		return;
	} else if ($("#j_password").val() == "") {
		GliderWiki.alert('알림','비밀 번호를 입력해 주세요.');
		return;
	} else {
		if ($("#checkSaveId").attr("checked")) {
			$.cookie("saveUserId", $("#j_username").val());
		} else {
			$.cookie("saveUserId", null);
		}

		//로그온
		var frm;
		frm = $('#loginForm');
		frm.submit();
	}
}


function downloadFileServer(fileName){
	$('#downloadForm input[name=filaName]').val(fileName);
	$("#downloadForm").attr("target", "fileDownload");
	$("#downloadForm").attr("action", "/common/download").submit();
}	


//]]>
</script>
</js:scripts>
</body>
</html>