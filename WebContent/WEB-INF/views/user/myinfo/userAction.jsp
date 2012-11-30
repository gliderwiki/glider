<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>

<body>
<section class="contents " role="main">
	<div class="wrap-cont">
		
		<h2 class="tit-cont">활동 내역</h2>
				
		<div class="body-cont my">
			<div class="activity">
				<div class="sub">
					<h3 class="tit-sub">내 활동 내역 (활동포인트 ${loginUser.wePoint } 점)</h3>
					<div class="wrap-box">
						
						<c:choose>
						<c:when test="${spaceInfoList eq null}">
						<div class="sub">
							<div class="box-empty">
							내 활동 내역이 없습니다.
							</div>
						</div>
						</c:when>
						<c:otherwise>
						
						<c:forEach items="${spaceInfoList }" var="spaceInfoList" varStatus="stat">
						<div class="box">
							<h4 class="tit-sec">${spaceInfoList.weSpaceName}</h4>
							<ul class="list type1">
								<c:forEach items="${wikiLogVoList }" var="wikiLogVoList" varStatus="i">
								<c:if test="${spaceInfoList.weSpaceIdx == wikiLogVoList.weSpaceIdx }">
								<li>
									<div class="title">
										<a href="/wiki/${wikiLogVoList.weWikiIdx}">${wikiLogVoList.weWikiTitle}</a>
									</div>
									<div class="time">
										${wikiLogVoList.weInsDate }
									</div>
								</li>
								</c:if>
								</c:forEach>
							</ul>						
						</div>
						</c:forEach>
						
						<!-- TODOLIST:추후개발  
						<div class="wrap_btn">
							<a href="#" class="btn">더 살펴보기</a>
						</div>
						 -->
						</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
	
		<div class="foot-cont">
		</div>

		
	</div>
</section>

<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/interface/UserActionService.js'></script>
<script type='text/javascript' src='/dwr/util.js'></script>
<script type="text/javascript">
//<![CDATA[
	$(document).ready(function() {
		// 가입 취소 
		$('a[name="cancelJoin"]').each(function(i) {
			var code = "";
			var joinIdx = "";		// 신청 IDX 
			var spaceIdx = "";		// 공간번호 (join_req_ 삭제시 필요)
			var userIdx = "${loginUser.weUserIdx}";
			var joinStatus = "CANCEL";
			$(this).bind("click", function(){
				code = $(this).attr("id");
				joinIdx = $(this).attr("title");
				spaceIdx = $("#joinSpaceIdx_"+joinIdx).val();
				UserActionService.cancelJoinToSpaceDWR(userIdx, joinIdx, spaceIdx, joinStatus, callBackUpdateJoin);
			});
		});
		// 초대 승인 
		$('a[name="acceptInvite"]').each(function(i) {
			var code = ""; 
			var inviteIdx = "";		// 신청 IDX 
			var spaceIdx = "";		// 공간번호 (invite_req_ 삭제시 필요)
			var userIdx = "${loginUser.weUserIdx}";
			var joinStatus = "APPROVE";
			$(this).bind("click", function(){
				code = $(this).attr("id");
				inviteIdx = $(this).attr("title");
				spaceIdx = $("#inviteSpaceIdx_"+inviteIdx).val();
				UserActionService.cancelJoinToSpaceDWR(userIdx, inviteIdx, spaceIdx, joinStatus, callBackUpdateJoin);
			});
		});
		// 초대 거절 
		$('a[name="rejectInvite"]').each(function(i) {
			var code = ""; 
			var inviteIdx = "";		// 신청 IDX 
			var spaceIdx = "";		// 공간번호 (invite_req_ 삭제시 필요)
			var userIdx = "${loginUser.weUserIdx}";
			var joinStatus = "REJECT";
			$(this).bind("click", function(){
				code = $(this).attr("id");
				inviteIdx = $(this).attr("title");
				spaceIdx = $("#inviteSpaceIdx_"+inviteIdx).val();
				UserActionService.cancelJoinToSpaceDWR(userIdx, inviteIdx, spaceIdx, joinStatus, callBackUpdateJoin);
			});
		});
		
		
		// 콜백 
		function callBackUpdateJoin(outData) {
			if(outData != null) {
				var jResult = eval("("+outData+")");
				if(jResult.rtnResult > 0) {
					
					if(jResult.rtnStatus == 'CANCEL') {
						// 가입 취소 후 상태 변경 
						alert(jResult.rtnMsg);
						$("#join_req_"+jResult.rtnResult).remove();	
					} else if(jResult.rtnStatus == 'APPROVE') {
						// 초대 승인 후 상태 변경  - TODOLIST row 삭제 하지 않고 버튼만 삭제한 후 text만 변경하는걸로 
						alert(jResult.rtnMsg);
						$("#invite_req_"+jResult.rtnResult).remove();	
					} else if(jResult.rtnStatus == 'REJECT') {
						// 초대 거절 후 상태 변경 
						alert(jResult.rtnMsg);
						$("#invite_req_"+jResult.rtnResult).remove();
					}
					
				} else {
					alert('Error 상태코드 ['+jResult.rtnStatus+'] : ' + jResult.rtnMsg);
				}
			} else {
				alert('결과값 없음');
			}
		}
		
	});
//]]>
</script>
</js:scripts>
</body>