<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>

<div class="wrap_layer">
	<section class="layer forgot" style="top: 30%; left: 27%;" id="searchUserInfo">
		<a href="#" role="button" id="btn_close" class="btn_close" title="닫기"> &times;</a>
		<ul class="menu">
			<li class="active" id="tab_id"><a style="cursor:pointer" name="findId" id="findId">로그인</a></li>
		</ul>
		<div class="box" id="searchId" style="display:block">
			<form:form id="loginForm" name="loginForm" action="/user/login" method="post">
			<input type="hidden" name="spring-security-fail-redirect" value="/login?login_error=true" />
			<input type="hidden" name="spring-security-redirect" value="/dashboard" />
			<div class="tit"><strong>GLiDER™ Wiki 로그인</strong></div>
			<div class="cont">
				<div class="lab">회원 아이디를 입력하세요.</div>
				<div class="row">
					<input type="text" id="j_username" name="j_username" title="아이디입력"  value="" />
				</div>
				<div class="lab">비밀번호를 입력하세요.</div>
				<div class="row">
					<input type="password" id="j_password" name="j_password" title="비밀번호입력" value=""/>
				</div>
				<p>
					<input type="checkbox" id="checkSaveId" name="checkSaveId" value="Y" /><label for="checkSaveId"> 아이디 저장</label>
				</p>
				<button type="button" class="btn-forgot" id="loginBtn" title="로그인"><span>로그인</span></button>
				<div class="result" id="resultId">
					<c:if test="${not empty param.login_error}">
						<label for="login" class="error">
						<i class="ico_comm alert"></i>
						<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" escapeXml="false" />
						</label>
					</c:if>
				</div>
			</div>
			<div class="wrap_btn">
				<a style="cursor:pointer" id="closeId" role="button" class="btn">닫기</a>
			</div>
			</form:form>
		</div>

	</section>
	<div class="bg"></div>
</div>

<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>

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

		$('#btn_close').click(function() {
			$('#searchUserInfo').fadeOut();
			$(location).attr('href',"/index");
		});

		$('#closeId').click(function() {
			$('#searchUserInfo').fadeOut();
			$(location).attr('href',"/index");
		});


	});

	function loginCheckSubmit() {
		if ($("#j_username").val() == "") {
			alert("아이디를 입력해 주세요.");
		} else if ($("#j_password").val() == "") {
			alert("비밀 번호를 입력해 주세요.");
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
//]]>
</script>
</js:scripts>