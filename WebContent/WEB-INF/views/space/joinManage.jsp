<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>
<link rel="stylesheet" href="/resource/glider/admin/css/admin.css" />
<section class="contents " role="main">
	<div class="wrap-cont">
		
		<h2 class="tit-cont">초대 및 가입신청</h2>
				
		<div class="body-cont my">
			<div class="relation activity">
				<div class="wrap-box">
					<div class="box left">
						<h3 class="tit-sub">가입신청목록</h3>
						<c:choose>
						<c:when test="${empty entryList}">
						<ul class="list type1 top-line">
						<div class="box-empty">
							 가입신청목록이 없습니다.
						</div>
						</ul>
						</c:when>
						<c:otherwise>
						<ul class="list type1 top-line">
							<c:forEach items="${entryList}" var="list" varStatus="stat">
							<li>
								<div class="title">
									${list.we_user_nick}
								</div>
								<div class="time">
									${gf:articleDate(list.we_ins_date,'yyyy.MM.dd')}
								</div>
								<div class="action">
									<a style="cursor:pointer" data-join-idx="${list.we_space_join_idx}" data-space-idx="${list.we_space_idx}" data-user-idx="${list.we_user_idx}" class="btn-accept">승인</a>
									<a style="cursor:pointer" data-join-idx="${list.we_space_join_idx}" data-space-idx="${list.we_space_idx}" class="btn-reject">거절</a>
								</div>
							</li>
							</c:forEach>
						</ul>
						</c:otherwise>
						</c:choose>
					</div>
					<div class="box right">
						<h3 class="tit-sub">초대내역</h3>
						<c:choose>
						<c:when test="${empty inviteList}">
						<ul class="list type1 top-line">
						<div class="box-empty">
							초대한 내역이 없습니다.
						</div>
						</ul>
						</c:when>
						<c:otherwise>
						<ul class="list type1 top-line">
							<c:forEach items="${inviteList}" var="list" varStatus="stat">	 
							<li>
								<div class="title">
									<a href="#">${list.we_user_nick}</a>
								</div>
								<div class="time">
									${gf:articleDate(list.we_ins_date,'yyyy.MM.dd')}
								</div>
								<div class="action">
									<c:choose>
									<c:when test="${list.we_join_status == 'APPROVE'}">
										<a class="btn-accept">승인</a>
									</c:when>
									<c:when test="${list.we_join_status == 'REQUEST'}">
										<a class="btn-reject">대기</a>
									</c:when>
									<c:when test="${list.we_join_status == 'REJECT'}">
										<a class="btn-reject">거절</a>
									</c:when>
									<c:otherwise>
										<a class="btn-accept">재신청</a>
									</c:otherwise>
									</c:choose>
								</div>
							</li>
							</c:forEach>
						</ul>
						</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
		<div class="foot-cont">
		</div>
		
	</div>
</section>


<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-group-layer.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.dateFormat-1.0.js"></script>
<script type="text/javascript">
//<![CDATA[
$(document).ready(function() {
	//가입신청 승인
	$(".btn-accept").on("click", function(e) {
		e.preventDefault();
		var joinIdx = $(this).data("joinIdx");
		var spaceIdx = $(this).data("spaceIdx");
		var userIdx = $(this).data("userIdx");
		
		if(GliderWiki.confirm('확인', '선택한 사용자의 가입신청을 승인하시겠습니까?',  function() {
			
			$.post("/space/entryApprove", {spaceJoinIdx:joinIdx,spaceIdx:spaceIdx,userIdx:userIdx}, function(data){
				if(data == 'SUCCESS') {
					GliderWiki.alert("공간","가입신청 승인이 처리되었습니다.");
					$(location).attr('href',"/space/joinManage/"+spaceIdx);
				}else{
					GliderWiki.alert("에러메세지","가입신청 승인이 처리되지 않았습니다.");
				}
			});
		}));
	});
	
	//가입신청 거절
	$(".btn-reject").on("click", function(e) {
		e.preventDefault();
		var joinIdx = $(this).data("joinIdx");
		var spaceIdx = $(this).data("spaceIdx");
		
		if(GliderWiki.confirm('확인', '선택한 사용자의 가입신청을 거절하시겠습니까?',  function() {
			$.post("/space/entryReject", {spaceJoinIdx:joinIdx}, function(data){
				if(data == 'SUCCESS') {
					GliderWiki.alert("공간","가입신청 거절이 처리되었습니다.");
					$(location).attr('href',"/space/joinManage/"+spaceIdx);
				}else{
					GliderWiki.alert("에러메세지","가입신청 거절이 처리되지 않았습니다.");
				}
			});
		}));
	});
});

//]]>
</script>
</js:scripts>

