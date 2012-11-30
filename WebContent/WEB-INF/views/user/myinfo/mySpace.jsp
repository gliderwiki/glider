<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>

<body>
<section class="contents " role="main">
	<div class="wrap-cont">
		
		<h2 class="tit-cont">공간 가입 신청/초대 목록</h2>
				
		<div class="body-cont my">
			<div class="activity">
	
				<div class="sub">
					<h3 class="tit-sub">가입 신청 내역</h3>
					<div class="wrap-box">
						
						<c:choose>
						<c:when test="${joinSize eq 0 or empty joinList}">
						<div class="sub">
							<div class="box-empty">
								가입 신청 내역이 없습니다.
							</div>
						</div>
						</c:when>
						<c:otherwise>
						<c:forEach items="${joinReqSpaceList }" var="joinReqSpaceList" varStatus="stat">
						<div class="box" id="join_req_${joinReqSpaceList.weSpaceIdx}">
							<h4 class="tit-sec">${joinReqSpaceList.weSpaceName}</h4>
							<ul class="list type1">
								<c:forEach items="${joinList }" var="joinList" varStatus="i">
								<c:if test="${joinReqSpaceList.weSpaceIdx == joinList.we_space_idx }">
								<li>
									<div class="title">
										${joinReqSpaceList.weSpaceName} 공간에
										<c:choose>
										<c:when test="${joinList.we_join_status == 'REQUEST'}">
											<b>가입신청</b>되었습니다.
										</c:when>
										<c:otherwise>
											<b>승인</b>되었습니다.
										</c:otherwise>
										</c:choose>
										<c:if test="${joinList.we_join_status != 'REJECT'}">
										<a style="cursor:pointer" role="button" name="cancelJoin" class="btn-cancel" id="cancelJoin_${joinList.we_space_join_idx }" title="${joinList.we_space_join_idx }">가입취소</a>
										<input type="hidden" id="joinSpaceIdx_${joinList.we_space_join_idx }" value="${joinReqSpaceList.weSpaceIdx }">
										</c:if>								
									</div>
									<div class="time">
										${joinList.we_ins_date }
									</div>
								</li>
								</c:if>
								</c:forEach>
							</ul>					
						</div>
						</c:forEach>
						</c:otherwise>
						</c:choose>
						
					</div>
				</div>
				<div class="sub">
					<h2 class="tit-sub">초대 내역 </h2>
					<div class="wrap-box">
						<c:choose>
						<c:when test="${inviteSize eq 0 or empty inviteList}">
						<div class="sub">
							<div class="box-empty">
								초대 내역이 없습니다.
							</div>
						</div>
						</c:when>
						<c:otherwise>
							<c:forEach items="${inviteSpaceList }" var="inviteSpaceList" varStatus="stat">
							<div class="box" id="invite_req_${inviteSpaceList.weSpaceIdx}">
								<h4 class="tit-sec">${inviteSpaceList.weSpaceName}</h4>
								<ul class="list type1">
									<c:forEach items="${inviteList }" var="inviteList" varStatus="i">
									<c:if test="${inviteSpaceList.weSpaceIdx == inviteList.we_space_idx }">
									<li>
										<div class="title">
											${inviteSpaceList.weSpaceName} 공간에  
											<c:choose>
											<c:when test="${inviteList.we_join_status == 'REQUEST'}">
												<b>초대</b>되었습니다.
											</c:when>
											<c:when test="${inviteList.we_join_status == 'REJECT'}">
												<b>초대거절</b>하셨습니다.
											</c:when>
											<c:otherwise>
												<b>가입완료</b>되었습니다.
											</c:otherwise>
											</c:choose> 
											<c:if test="${inviteList.we_join_status != 'REJECT' and inviteList.we_join_status != 'APPROVE'}">
											<a style="cursor:pointer" role="button" name="acceptInvite" id="inviteJoin_${inviteList.we_space_join_idx }" title="${inviteList.we_space_join_idx }" class="btn-accept">초대승인</a>
										    <a style="cursor:pointer" role="button" name="rejectInvite" id="rejectJoin_${inviteList.we_space_join_idx }" title="${inviteList.we_space_join_idx }" class="btn-reject">초대거절</a>
										    <input type="hidden" id="inviteSpaceIdx_${inviteList.we_space_join_idx }" value="${inviteSpaceList.weSpaceIdx }">
										    </c:if>
										</div>
										<div class="time">
											${inviteList.we_ins_date }
										</div>
									</li>
									</c:if>
									</c:forEach>
								</ul>
							</div>
							</c:forEach>
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
						GliderWiki.alert('확인',jResult.rtnMsg);
					} else if(jResult.rtnStatus == 'APPROVE') {
						// 초대 승인 후 상태 변경  - TODOLIST row 삭제 하지 않고 버튼만 삭제한 후 text만 변경하는걸로 
						GliderWiki.alert('확인',jResult.rtnMsg);
					} else if(jResult.rtnStatus == 'REJECT') {
						// 초대 거절 후 상태 변경 
						GliderWiki.alert('확인',jResult.rtnMsg);
					}
				} else {
					GliderWiki.alert('확인', 'Error 상태코드 ['+jResult.rtnStatus+'] : ' + jResult.rtnMsg);
				}
				// 페이지 이동 
				$(location).attr('href',"/user/myspace");
			} else {
				GliderWiki.alert('확인', 'Error 결과값 없음');
			}
		}
		
	});
//]]>
</script>
</js:scripts>
</body>