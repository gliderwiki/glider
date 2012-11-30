<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width">
<title>GLiDERWiki™ Web Installer </title>
<link rel="stylesheet" href="/resource/glider/admin/css/install.css">
<style type="text/css">
#signup {
	width: 404px;
	padding-bottom: 2px;
	display: none;
	background: #FFF;
	border-radius: 5px;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	box-shadow: 0px 0px 4px rgba(0, 0, 0, 0.7);
	-webkit-box-shadow: 0 0 4px rgba(0, 0, 0, 0.7);
	-moz-box-shadow: 0 0px 4px rgba(0, 0, 0, 0.7);
}
#signup-header { padding: 1px 18px 14px 18px; border-bottom: 1px solid #CCC; border-top-left-radius: 5px; -moz-border-radius-topleft: 5px; -webkit-border-top-left-radius: 5px; border-top-right-radius: 5px; -moz-border-radius-topright: 5px; -webkit-border-top-right-radius: 5px; }
#signup-header  h2 { color: #444; font-size: 2em; font-weight: 700; margin-bottom: 3px; text-shadow: 1px 1px 0 rgba(255, 255, 255, 0.5); }
#signup-header  p { color: #444; font-size: 1.3em; margin: 0; text-shadow: 1px 1px 0 rgba(255, 255, 255, 0.5); text-shadow: none; }
#signup .txt-fld { position: relative; width: 364px; padding: 14px 20px; border-bottom: 1px solid #EEE; text-align: right; }
#signup .btn-fld { width: 254px; overflow: hidden; padding: 22px 20px 12px 140px; }
#signup .txt-fld label { display: block; float: left; width: 90px; padding-top: 8px; color: #222; font-size: 1.3em; text-align: left;  }
#signup .txt-fld input { width: 244px; padding: 8px; border-radius: 4px; -moz-border-radius: 4px; -webkit-border-radius: 4px; font-size: 1.2em; color: #222; background: #F7F7F7; font-family: "Helvetica Neue"; outline: none; border-top: 1px solid #CCC; border-left: 1px solid #CCC; border-right: 1px solid #E7E6E6; border-bottom: 1px solid #E7E6E6; }
#signup .txt-fld input.good_input { background: #DEF5E1 236px center no-repeat; }
#signup .txt-fld input.error_input { background: #FDE0E0; }
</style>
</head>

<body>
<form:form id="loginForm" name="loginForm" action="/user/login" method="post">
<input type="hidden" name="spring-security-fail-redirect" value="/admin/login?login_error=true" />
<input type="hidden" name="spring-security-redirect" value="/admin/index" />
<div id="signup" style="display: block; position: fixed; opacity: 1; z-index: 11000; left: 50%; margin-left: -202px; top: 150px;">
	<div id="signup-ct">
		<div id="signup-header" align="center">
			<h2>GLiDER Wiki™ Admin Mode</h2>
			<a class="modal_close" href="#"></a>
		</div>

		<div class="txt-fld">
			<label for="">ID</label> <input type="text" id="j_username" class="good_input" name="j_username" value="" >
		</div>
		<div class="txt-fld">
			<label for="">Password</label> <input id="j_password" name="j_password" type="password">
		</div>
		<c:if test="${not empty param.login_error}">
		<div style="padding-top: 8px; padding-left: 20px;" >
			<label for="login" class="error">
			<i class="ico_comm alert"></i>
			아이디 또는 비밀번호가 틀립니다.
			<!-- fmt:message key="account.authenticationFailure" /-->
			</label>
		</div>
		</c:if>
		<div class="btn-fld">
			<a style="cursor:pointer"  role="button" class="btn" id="loginBtn" title="로그인">Login</a>
		</div>
	</div>
</div>
</form:form>

<script type="text/javascript" src="/resource/libs/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/glider/common/wiki.js"></script>
<script type="text/javascript">
//<![CDATA[
	$(document).ready(function() {
		//로그인 버튼 클릭
		$("#loginBtn").bind("click", function() {
			loginCheckSubmit();
		});

		$("#j_password").keydown(function(e) {
			if (e.keyCode == 13) {
				loginCheckSubmit();
			}
		});
	});

	function loginCheckSubmit() {
		if ($("#j_username").val() == "") {
			GliderWiki.alert('경고', '아이디를 입력해 주세요.');
			$('#j_username').focus(); 
		} else if ($("#j_password").val() == "") {
			GliderWiki.alert('경고', '비밀 번호를 입력해 주세요.');
			$('#j_password').focus();
		} else {
			var frm;
			frm = $('#loginForm');
			frm.submit();
		}
	}

//]]>
</script>
</body>
</html>