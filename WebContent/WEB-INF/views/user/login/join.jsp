<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>

<section class="contents " role="main">
	<div class="wrap-cont">
		
		<h2 class="tit-cont">회원 가입</h2>
		
		
		<form action="" id="joinForm" name="joinForm" method="post">
			<div class="body-cont my">
				<div class="join">
					<p class="lab">
						<span class="tit-sub">이메일</span> 주소를 입력하세요. 메일 인증을 통하여 회원 가입이 이루어집니다.
					</p>
					<div class="row">
						<input type="text" id="we_user_id" name="we_user_id" title="아이디입력" />
					</div>
					<p class="lab">
						<span class="tit-sub">이름</span>을 입력하세요. 이메일 아이디가 기억나지 않을 경우 이름을 통해 찾을 수 있습니다.
					</p>
					<div class="row">
						<input type="text" id="we_user_name" name="we_user_name" title="이름입력"/>
					</div>
					<p class="lab">
						<span class="tit-sub">닉네임</span>을 입력하세요. 닉네임은 로그인 후 변경할 수 있으며, 글 작성시 노출됩니다.
					</p>
					<div class="row">
						<input type="text" id="we_user_nick" name="we_user_nick" title="닉네임입력" />
					</div>
					<p class="lab">
						<span class="tit-sub">비밀번호</span>를 입력하세요. 보안을 위해 문자와 숫자 조합으로 6자리 이상을 권장합니다.
					</p>
					<div class="row">
						<input type="password" id="we_user_pwd" name="we_user_pwd" title="비밀번호입력" />
					</div>
					<p class="lab">
						<span class="tit-sub">비밀번호를 다시 확인</span>합니다. 비밀번호는 암호화하여  안전하게 보관합니다.
					</p>
					<div class="row">
						<input type="password" id="userPassCheck" name="userPassCheck" title="비밀번호입력"/>
					</div>
				</div>
			</div>
			<div class="foot-cont">
					<button type="button" id="joinBtn" class="btn">확인</button>
					<a href="/" class="btn">취소</a>
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
	$(document).ready(function() {
		var checkId = 0;
		var checkNickName = 0;
		// Ajax 폼 
		$("#joinForm").ajaxForm(RegistUserCallBack);

		$("#we_user_id").focusout(function() {
			var userId = $("#we_user_id").val();
			
			$("#errorEmailCheck").remove("");
			if(!emailCheck(userId)) { 
				$("#we_user_id").after(" <label class=\"error\" id=\"errorEmailCheck\">올바른 메일 주소가 아닙니다.메일주소를 확인하세요.</label>");
				return;
			} 
	
			$.ajax({
				type:"POST"
				,url:"/checkUesrId"
				,data:{"userId":userId}
				,success:function(response){
					var status = response.status;
					if(status == 'SUCCESS'){
						checkId = 1;
					}else{
						$("#errorEmailCheck").remove("");
						$("#we_user_id").after(" <label class=\"error\" id=\"errorEmailCheck\">중복된 메일 주소 입니다. 메일주소를 확인하세요.</label>");
						checkId = 0;
						return;
					}
				}
			});
			
		});
		
		// 이름 필수 
		$("#we_user_name").focusout(function() {
			var userName = $("#we_user_name").val();
			$("#errorNameCheck").remove("");
			if(userName == '') { 
				$("#we_user_name").after(" <label class=\"error\" id=\"errorNameCheck\">이름은 필수 입력 사항입니다.</label>");
				return;
			}
		});
		
		// 닉네임 필수 & 중복 체크 
		$("#we_user_nick").focusout(function() {
			var userNickName = $("#we_user_nick").val();
			$("#errorNickCheck").remove("");
			if(userNickName == '') { 
				$("#we_user_nick").after(" <label class=\"error\" id=\"errorNickCheck\">닉네임은 필수 입력 사항입니다.</label>");
				return;
			}
			
			$.ajax({
				type:"POST"
				,url:"/checkUesrNick"
				,data:{"userNickName":userNickName}
				,success:function(response){
					var status = response.status;
					if(status == 'SUCCESS'){
						checkNickName = 1;
					}else{
						$("#errorNickCheck").remove("");
						$("#we_user_nick").after(" <label class=\"error\" id=\"errorNickCheck\">닉네임이 중복되었습니다. 다시 입력하세요.</label>");
						checkNickName = 0;
						return;
					}
				}
			});
			
			
		});
		
		// 비밀번호 필수 
		$("#we_user_pwd").focusout(function() {
			var userPassword = $("#we_user_pwd").val();
			$("#errorPassCheck").remove("");
			if(userPassword == '') { 
				$("#we_user_pwd").after(" <label class=\"error\" id=\"errorPassCheck\">비밀번호를 입력하세요.</label>");
				return;
			}
		});
		


		//회원가입 버튼 클릭 
		$("#joinBtn").bind("click", function() {
			if(checkId == 1 && checkNickName == 1) {
				joinCheckSubmit();			
			} else {
				if(checkId == 0) {
					GliderWiki.alert('이메일 확인 에러', '이메일이 중복되었거나 올바르지 않습니다. 이메일 주소를 정확하게 입력해주세요. ');
					return;	
				} else if (checkNickName == 0) {
					GliderWiki.alert('닉네임 확인 에러', '닉네임이 중복되었거나 재확인이 필요합니다. 다시 입력해주세요. ');
					return;	
				}
				
			}
		});

		$("#userPassCheck").keydown(function(e) {
			if (e.keyCode == 13) {
				if(checkId == 1 && checkNickName == 1) {
					joinCheckSubmit();
				} else {
					if(checkId == 0) {
						GliderWiki.alert('이메일 확인 에러', '이메일이 중복되었거나 올바르지 않습니다. 이메일 주소를 정확하게 입력해주세요. ');
						return;	
					} else if (checkNickName == 0) {
						GliderWiki.alert('닉네임 확인 에러', '닉네임이 중복되었거나 재확인이 필요합니다. 다시 입력해주세요. ');
						return;	
					}
				}
			}
		});
	});

	function joinCheckSubmit() {
		if ($("#we_user_id").val() == "") {
			GliderWiki.alert('이메일 확인 에러', '이메일이  올바르지 않습니다. 이메일 주소를 정확하게 입력해주세요.');
			return;
		} else if ($("#we_user_name").val() == "") {
			GliderWiki.alert('회원가입 에러', '이름은 필수입력입니다. 비밀번호 혹은 아이디 분실시 이름을 통해 찾을 수 있습니다.');
			return;		
		} else if ($("#we_user_nick").val() == "") {
			GliderWiki.alert('회원가입 에러', '닉네임을 입력하세요. 닉네임은 로그인 후 변경할 수 있으며, 글 작성시 노출됩니다.');
			return;
		} else if ($("#we_user_pwd").val() == "") {
			GliderWiki.alert('회원가입 에러','비밀번호를 입력하세요. 보안을 위해 문자와 숫자 조합으로 6자리 이상을 권장합니다.');
			return;
		} else if ($("#userPassCheck").val() == "") {
			GliderWiki.alert('회원가입 에러','비밀번호를 다시 확인합니다. 비밀번호는 암호화하여 안전하게 보관합니다.');
			return;
		} else if ($("#we_user_pwd").val() != $("#userPassCheck").val()) {
			GliderWiki.alert('회원가입 에러','비밀번호가 맞지 않습니다. 비밀번호를 확인하세요.');
			return;
		} else {
			$.loadingBar();
			//파일전송
			var frm;
			frm = $('#joinForm');
			frm.attr("action", "/registUser");
			frm.submit();
		}
	}

	function RegistUserCallBack(data, status) {
		if (status == 'success') {
			if (data.result == 1) {
				$.loadingBar.fadeOut();
				GliderWiki.alert('회원가입 완료', '입력한 메일주소로 인증페이지가 전송되었습니다.\n메일을 확인하여 인증을 거친 후 로그인 하세요.');
				$("#okBtn").on("click", function() {

					$(location).attr('href',"/index");
				});				
			} else if (data.result == -3) {
				$.loadingBar.fadeOut();
				GliderWiki.alert('회원가입 에러', '결과['+data.result+'] - 회원 메일 인증에 문제가 발생하였습니다.\n관리자에게 문의하세요.');
				$(location).attr('href',"/index");
				
			} else if (data.result == -4) {
				$.loadingBar.fadeOut();
				GliderWiki.alert('회원가입 에러','결과['+data.result+'] - 인증 메일 전송중 메일 서버 연결이 제대로 되지 않아 에러가 발생했습니다.\n다시 시도하세요.');
			} else {
				GliderWiki.alert('회원가입 에러','결과['+data.result.rtnResult+'] - 회원 정보 저장이 제대로 되지 않았습니다.\n관리자에게 문의하세요.');
			}
			$.loadingBar.fadeOut();
		} else {
			$.loadingBar.fadeOut();
			GliderWiki.alert('회원가입 에러','통신에러가 발생하였습니다.');
		}
	}
	
	function emailCheck(values) {
		var regExp = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	    if (!regExp.test(values)) {
	        return false;
	    }
	    else {
	        return true;
	    }
	}
	
 
//]]>
</script>
</js:scripts>
