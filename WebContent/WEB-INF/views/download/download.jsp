<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/include/tags.jspf" %>
    
<body>
<section class="contents " role="main">
<form id="installForm" name="installForm" method="post" action="">
<input type="hidden" id="we_active_key" name="we_active_key" value="${activeKey }" />
<input type="hidden" id="we_auth_yn" name="we_auth_yn" value="N" />
	<div class="wrap-cont">
		<h2 class="tit-cont">GLiDER Wiki™ 다운로드</h2>
			<div class="body-cont space">
				<div class="edit">
					<!-- 상단 간격조정  -->
					<div class="box" style="margin-top:-20px">
						<h3>이메일 주소를 입력하세요. 글라이더 위키의 Active Key가 전송됩니다.</h3>
						<input type="text" id="we_email" name="we_email" value="" class="inp-dup"/>
					</div>
					<div class="box">
						<h3>글라이더 위키를 적용하고자 하는 사이트 도메인 주소를 입력하세요.</h3>
						<input type="text" id="we_domain" name="we_domain" value="" class="inp-dup"/>
					</div>
					<div class="box">
						<h3>글라이더 위키를 적용하고자 하는 회사(혹은 단체명)를 입력하세요.</h3>
						<input type="text" id="we_company" name="we_company" value="" class="inp-dup"/>
					</div>
					
					<div class="box">
					<h3>글라이더 위키의 사용 목적을 선택하세요.</h3>
					<div class="box-group fst">
						<h4 class="tit">글라이더 위키의 용도를 알려주세요.</h4>
						<span class="item">
							<input type="radio" name="we_use_purpose" value="1" />
							<label for="read1">연구소/대학</label>
						</span>
						<span class="item">
							<input type="radio" name="we_use_purpose" value="2" />
							<label for="read2">개인/스터디</label>
						</span>
						<span class="item">
							<input type="radio" name="we_use_purpose" value="3" />
							<label for="read3">지식공유/커뮤니티</label>
						</span>
						<span class="item">
							<input type="radio" name="we_use_purpose" value="4" />
							<label for="read3">기업/업무용</label>
						</span>
						<span class="item">
							<input type="radio" name="we_use_purpose" value="5" />
							<label for="read3">TFT/프로젝트용</label>
						</span>
						<span class="item">
							<input type="radio" name="we_use_purpose" value="6" />
							<label for="read3">기타</label>
						</span>
					</div>
					<div class="box">
						<h3>글라이더 위키의 업데이터 정보와 뉴스레터를 받아보시겠습니까? </h3>
						<div style="text-align:center;">
						<input type="radio" name="we_new_yn" value="Y" checked/>
						<label for="read3" style="padding-right:20px">예</label>
						<input type="radio" name="we_new_yn" value="N" />
						<label for="read3">아니오</label>
						</div>
					</div>		
				</div>
		
			</div>
		</div>
		<div class="foot-cont">
			<a style="cursor:pointer" class="btn" id="wikiDownload">위키 다운로드</a>
		</div>
	</div>
</form>	
</section>

<iframe name="fileDownload" width="0" height="0" frameborder="0"  style="display:hidden"></iframe>

<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/glider/common/wiki.common.util.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-loadingbar.js"></script>
<script type="text/javascript"> 
//<![CDATA[
$(document).ready(function() {
	$("#wikiDownload").bind("click", function() {
		var checkVal = $(':radio[name="we_use_purpose"]:checked').val();
		var newsVal = $(':radio[name="we_new_yn"]:checked').val();
		if(GliderWiki.Utils.isEmpty($("#we_email").val())) {
			GliderWiki.alert("알림","이메일 주소를 입력하세요.");
			return;
		}
		if(!emailCheck($("#we_email").val())) { 
			GliderWiki.alert("알림","올바른 이메일 주소가 아닙니다.");
			return;
		}

		if(GliderWiki.Utils.isEmpty(checkVal)) {
			GliderWiki.alert("알림","글라이더 위키의 사용 목적을 선택하세요.");
			return;
		}
		
		$("#installForm").attr("action", "/downloadWiki").submit();
		
		GliderWiki.alert('파일 다운로드 중', '메일주소로 Active Key를 전송 중입니다. \n다소 시간이 걸릴 수 있으니 다운로드가 실행될 때 까지 기다려 주세요.\n다운로드가 완료 된 후 확인 버튼을 클릭하시면 메인 화면으로 이동 합니다.');
		$("#okBtn").on("click", function() {

			$(location).attr('href',"/index");
		});		
		
	});
});

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
</body>