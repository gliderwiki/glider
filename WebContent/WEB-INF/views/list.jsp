<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>

<body>
<section class="contents" role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">공통 페이지 목록</h2>


		<div class="body-cont">
		html목록 - <a href="/resource/html/list.html">/resource/html/list.html</a><br />

		<br />
		DWR 테스트 - <a href="/temp/dwrtest">/temp/dwrtest</a><br />
		Syntax Highlighter - <a href="/syntax">/syntax</a><br />
		페이지변경내역 - <a href="/engine/list/67">/engine/list/67</a><br />
		Text Revision - <a href="/engine/diff/67/3/1">/engine/diff/67/3/1</a><br />
		Editor - <a href="/editor">/editor</a><br />
		코드 조회 및 입력 - <a href="/codelist">/codelist</a> <br />
		로그인 - <a href="/login">/login</a><br />
		회원가입 - <a href="/join">/join</a><br />
		아이디 비번 찾기 - <a href="/searchInfo">/searchInfo</a><br />
		프로필수정 - <a href="/profile">/profile</a><br />
		활동내역 - <a href="/user/action">/user/action</a><br />
		알람관리 - <a href="/user/alarm">/user/alarm</a><br />
		임시파일업로드 - <a href="/tempupload">/tempupload</a><br />
		즐겨찾기 - <a href="/user/favorite">/user/favorite</a><br />
		내인맥 - <a href="/user/connection">/user/connection</a><br />
		프로필보기 - <a href="javascript:viewProfile(1)">프로필보기</a><br />
		태그 클라우드 - <a href="/tag">/tag</a><br />
		위키 목록 - <a href="/wiki/list/1">/wiki/list/1</a><br />
		위키 인스톨러  - <a href="/admin/install">/admin/install</a><br />
		위키 공감 작업(손지성) - <a href="/wiki/list/agree">/agree</a><br />
		<br />
		<br />[공간, FILE UPLOAD, PDF&DOC EXPORT]<br />
		redis 값 테스트 - ${redisValue}<br />
		redis 리스트값 테스트 - <br />
		<c:forEach var="list" items="${redisListValue}">
			${list.we_space_name} : ${list.we_space_desc}<br/>
		</c:forEach>
		<br />

		공간생성 - <a href="/space/form">공간생성</a><br />
		파일업로드 - <a href="/upload">파일업로드</a><br />
		pdf export - <a href="/exp/pdf?url=www.bluepoet.me">html=>pdf export</a><br />
		html=>doc export<br />
		<br />
		공통 <br />
		<br />
		<a style="cursor:pointer" name="previewJs">GliderWiki.preview("미리보기를 클릭합니다.");</a><br />
		<a style="cursor:pointer" name="alertJs">GliderWiki.alert('제목내용', '경고 내용 - 경고창을 출력합니다');</a><br />
		<a style="cursor:pointer" name="messageJs">GliderWiki.confirm('제목 타이틀을 입력함 ', 'Confirm메세지를입력함', okAlert);</a><br />
		<a href="/resource/html/common/error.html">에러페이지</a><br/>
		<a href="/sysinfo">시스템정보</a><br/>
		<a href="/tablecreate">테이블생성(we_user_job)</a><br/>
		<a style="cursor:pointer" id="loadingbar">로딩바 출력</a><br/>
		프로필보기 - <a href="javascript:viewProfile(1)">프로필보기</a><br />

		
		</div>
		<div class="foot-cont">
			<a href="#" role="button" class="btn">버튼이 있다면</a>
			<a href="#" role="button" class="btn">여기에</a>
		</div>

	</div>
</section>

<!--
<B>SpEL 표기법 테스트</B> <BR>
auth.id : <spring:eval expression="@config['auth.id']" /> <BR>
auth.pw : <spring:eval expression="@config['auth.pw']" />
-->

<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-loadingbar.js"></script>

<script type="text/javascript">
//<![CDATA[
	$(document).ready(function() {

		$('a[name="previewJs"]').bind("click", function() {
			GliderWiki.preview("미리보기를 클릭합니다.");
		});
		$('a[name="alertJs"]').bind("click", function() {
			GliderWiki.alert('제목내용', '경고 내용 - 경고창을 출력합니다');
		});
		$('a[name="messageJs"]').bind("click", function() {
			GliderWiki.confirm('제목 타이틀을 입력함 ', 'Confirm메세지를입력함', okAlert);
		});
		
		$('#loadingbar').bind("click", function() {
			$.loadingBar();  // $.loadingBar.fadeOut();
		});
		
		function okAlert() {
			alert('confirm ok click');
			return;
		}


	});


//]]>
</script>
</js:scripts>
</body>