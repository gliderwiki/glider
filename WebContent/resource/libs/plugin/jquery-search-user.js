
(function()
{
	/**
	 * 사용자 아이디/패스워드 정보 레이어 팝업을 출력한다.
	 */
	jQuery.search_user = function(params){

		if($('#searchWrap').length){
			return false;
		}

		var resourceHtml = [
		            		'<div class="searchWrap" id="searchWrap">',
		            		'	<div class="loadingBg"></div>',
		            		'	<div class="load_search" id="load_search">',
		            		'	<div class="wrap_layer">',
		            		'	<section class="layer forgot" id="searchUserInfo">',
		            		'		<a style="cursor:pointer" id="close_search_box" role="button" class="btn_close" title="닫기"> &times;</a>',
		            		'		<ul class="menu">',
		            		'			<li class="active" id="tab_id">',
		            		'				<a style="cursor:pointer" name="findId" id="findId">ID 찾기</a>',
		            		'			</li>',
		            		'			<li id="tab_password">',
		            		'				<a style="cursor:pointer" name="findPassword" id="findPassword">비밀번호 찾기</a>',
		            		'			</li>',
		            		'		</ul>',
		            		'		<div class="box" id="searchId" style="display:block">',
		            		'			<div class="tit">',
		            		'				<strong>아이디 찾기</strong>',
		            		'			</div>',
		            		'			<div class="cont">',
		            		'				<div class="lab">비밀번호를 입력하세요</div>',
		            		'				<div class="row">',
		            		'					<input type="password" name="userPassword" id="userPassword" />',
		            		'				</div>',
		            		'				<div class="lab">사용중인 닉네임을 입력하세요.</div>',
		            		'				<div class="row">',
		            		'					<input type="text" name="userNick" id="userNick" />',
		            		'				</div>',
		            		'				<button type="button" class="btn-forgot" id="btnSearchId">찾기</button>',
		            		'				<div class="result" id="resultId">',
		            		'				</div>',
		            		'			</div>',
		            		'			<div class="wrap_btn">',
		            		'				<a style="cursor:pointer" id="closeId" role="button" class="btn">닫기</a>',
		            		'			</div>',
		            		'		</div>',
		            		'		<div class="box" id="searchPassword" style="display:none">',
		            		'			<div class="tit">',
		            		'				<strong>비밀번호 찾기</strong>',
		            		'			</div>',
		            		'			<div class="cont">',
		            		'				<div class="lab">아이디를 입력하세요</div>',
		            		'				<div class="row">',
		            		'					<input type="text" name="userId" id="userId" />',
		            		'				</div>',
		            		'				<div class="lab">사용중인 닉네임을 입력하세요.</div>',
		            		'				<div class="row">',
		            		'					<input type="text" name="weUserNick" id="weUserNick" />',
		            		'				</div>',
		            		'				<button type="button" class="btn-forgot" id="btnSearchPassword">찾기</button>',
		            		'				<div class="result" id="resultPassword"> * 비밀번호는 이메일로 전송됩니다.',
		            		'				</div>',
		            		'			</div>',
		            		'			<div class="wrap_btn">',
		            		'				<a style="cursor:pointer" id="closePassword" role="button" class="btn">닫기</a>',
		            		'			</div>',
		            		'		</div>',
		            		'	</section>',
		            		'	<div class="bg"></div>',
		            		'	</div>',
		            		'	</div>',
		            		'</div>',
		].join('');

		$('body').before(resourceHtml);
	};

	$.search_user.hide = function(){
		$('#searchWrap').fadeOut(function(){
			$(this).remove();
			return false;
		});
	};

	$('#close_search_box').live("click" , function(e) {
		$.search_user.hide();
	});
	$('#closePassword').live("click" , function(e) {
		$.search_user.hide();
	});

	$('#closeId').live("click" , function(e) {
		$.search_user.hide();
	});

	$("#findPassword").live("click", function() {
		$("#tab_password").addClass("active");
		$("#tab_id").removeClass("active");
		$("#searchId").hide();
		$("#searchPassword").show();
	});
	$("#findId").live("click", function() {
		$("#resultId").html("");
		$("#tab_id").addClass("active");
		$("#tab_password").removeClass("active");
		$("#searchId").show();
		$("#searchPassword").hide();
	});
	// 레이어 닫기
	$("#closeId").live("click", function() {
		$("#searchUserInfo").hide();
	});
	$("#closePassword").live("click", function() {
		$("#searchUserInfo").hide();
	});

	// 아이디 찾기
	$("#btnSearchId").live("click", function() {
		// 유효성체크
		var password = $("#userPassword").val();
		var userNick = $("#userNick").val();
		if(password == '') {
			$("#resultId").html("");
			$("#resultId").html("* 비밀번호를 입력하세요!");
			return;
		} else if(userNick == '') {
			$("#resultId").html("");
			$("#resultId").html("* 닉네임을 입력하세요!");
			return;
		} else {
			LoginService.findUserIdDWR(password, userNick, callbackUserId);
		}
	});


	// 비밀번호 찾기
	$("#btnSearchPassword").live("click", function() {
		// 유효성체크
		var userId = $("#userId").val();
		var weUserNick = $("#weUserNick").val();
		if(userId == '') {
			$("#resultPassword").html("");
			$("#resultPassword").html("* 아이디를 입력하세요!");
			return;
		} else if(weUserNick == '') {
			$("#resultPassword").html("");
			$("#resultPassword").html("* 닉네임을 입력하세요!");
			return;
		} else {
			$.loadingBar();
			$.post("/findIdPassword", {userId:userId, weUserNick:weUserNick}, function(rtnObj){
				var jResult = eval("("+rtnObj+")");
				
				if(jResult != null) {
					if(jResult.rtnCode  == '1') {
						// 비밀번호 전송 완료
						$("#resultPassword").html("");
						$("#resultPassword").html(jResult.rtnMsg);
						$.loadingBar.fadeOut();
					} else  {
						// 에러 처리
						$("#resultPassword").html("");
						$("#resultPassword").html(jResult.rtnMsg);
						$.loadingBar.fadeOut();
					}
				} else {
					$("#resultPassword").html("");
					$("#resultPassword").html("* 에러 : 정보가  없습니다. <br />다시 시도하세요.");
					$.loadingBar.fadeOut();
				}
			});
			
			
		}
	});

	function callbackUserId(outData) {
		if(outData != null) {
			var jResult = eval("("+outData+")");

			if(jResult.rtnResult == 1) {
				// 아이디 노출
				$("#resultId").html("");
				$("#resultId").html(jResult.rtnMsg);
			} else  {
				// 에러 처리
				$("#resultId").html("");
				$("#resultId").html(jResult.rtnMsg);
			}
		} else {
			$("#resultId").html("");
			$("#resultId").html("* 에러 : 정보가  없습니다. <br />다시 시도하세요.");
		}
	}


})(jQuery);

