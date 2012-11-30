<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %><%@
taglib prefix="space" tagdir="/WEB-INF/tags/space" %>
<link rel="stylesheet" href="/resource/glider/admin/css/admin.css" />
<link rel="stylesheet" href="/resource/libs/plugin/tip-yellow/tip-yellow.css" type="text/css" />
<link rel="stylesheet" href="/resource/libs/plugin/tip-violet/tip-violet.css" type="text/css" />
<link rel="stylesheet" href="/resource/libs/plugin/tip-darkgray/tip-darkgray.css" type="text/css" />
<link rel="stylesheet" href="/resource/libs/plugin/tip-skyblue/tip-skyblue.css" type="text/css" />
<link rel="stylesheet" href="/resource/libs/plugin/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
<link rel="stylesheet" href="/resource/libs/plugin/tip-twitter/tip-twitter.css" type="text/css" />
<link rel="stylesheet" href="/resource/libs/plugin/tip-green/tip-green.css" type="text/css" />



<section class="contents space-main" role="main">
	<div class="wrap-cont">
	<div class="space-intro">
		<h2>${gf:cut(spaceInfo.spaceName,50)}</h2>
		<div class="txt">
			${spaceInfo.spaceDesc}
		</div>
		<img src="/resource/real${spaceInfo.profileImg}" alt="" class="symbol" onerror="this.src='/resource/glider/front/images/default_big.jpg';">
	</div>
	<div class="body-cont space" style="padding: 46px 45px; ">
		<div class="main">
			<div class="col left" style="width:440px">
				<div class="box">
					<h3 class="tit-sec">
						<em>${spaceInfo.spaceName}</em> 기본정보
					</h3>
					<ul class="list type1 info">
					<c:choose>
					<c:when test="${not empty wikiList}">
						<c:forEach items="${wikiList}" var="wikiList" varStatus="stat">
						<li>
							<div class="title">
								<c:forEach begin="1" end="${wikiList.we_wiki_order_idx}">
								&nbsp;&nbsp;&nbsp;
								</c:forEach>
								<a href="/wiki/${wikiList.we_wiki_idx}">${wikiList.we_wiki_title}</a>
							</div>
							<div class="user" style="text-align: center; width:90px">
								<span class="name" style="text-align: center;">
									<a href="#" role="button" class="tooltip" data-user-idx="${wikiList.we_ins_user}">${wikiList.we_user_nick}</a>
								</span>
							</div>
							<div class="time" style="text-align: center;">
								${gf:articleDate(wikiList.we_ins_date,'yyyy.MM.dd')}
							</div>

						</li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="box-empty">
							등록된 위키 목록이 없습니다.
						</div>
					</c:otherwise>
					</c:choose>
					</ul>
				</div>
			</div>
			<div class="col right" style="width:400px">
				<c:if test="${!empty boardList}">
				<div class="box">
					<h3 class="tit-sec">최근소식</h3>
					<ul class="list type1 title-only">
						<c:forEach items="${boardList}" var="boardList" varStatus="stat">
						<li>
							<div class="title">
								<a href="/space/${spaceInfo.spaceIdx}/board/${boardList.we_bbs_idx}">${boardList.we_bbs_title}</a>
							</div>
						</li>
						</c:forEach>
					</ul>
					<div class="box-expand">
						<a href="/space/${spaceInfo.spaceIdx}/board" role="button" class="more" title="펼치기">
						<i class="arr"></i>MORE
						</a>
					</div>
				</div>
				</c:if>
				<div class="box">
					<h3 class="tit-sec">최근 업데이트</h3>
					<ul class="list type3" id="recentUpdate">
						<space:updatedlist list="${updatedList}" />
					</ul>
					<div class="box-expand">
						<a style="cursor:pointer" id="getUpdate" rold="button" class="ico expand" title="펼치기">펼치기</a>
						<a style="cursor:pointer" id="setUpdate" rold="button" class="ico unexpand" title="접기">접기</a>
					</div>
				</div>
				<div class="box">
					<h3 class="tit-sec">최근 업로드 자료</h3>
					<ul class="list type1 upload">
					<c:choose>
					<c:when test="${not empty fileList}">
						<c:forEach items="${fileList}" var="list" varStatus="stat">
						<li>
							<div class="title">
								<img src="/resource/glider/front/images/file/ic_file_${fn:toUpperCase(list.we_file_type)}.jpg">
								<a style="cursor:pointer" name="targetFiles" onclick="javascript:downloadFileServer('${list.we_file_idx }', '${list.we_file_real_name }');" title="${list.we_file_real_name }">${gf:cut(list.we_file_real_name,16)}</a>
							</div>
							<div class="time">
								${gf:articleDate(list.we_ins_date,'yyyy.MM.dd')}
							</div>
							<div class="user">
								<span class="name">
									<a href="#" role="button">${list.we_user_nick}</a>
								</span>
							</div>
							<div class="info">
								다운:${list.we_file_down}
							</div>
						</li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="box-empty">
							최근에 업로드된 자료가 없습니다.
						</div>
					</c:otherwise>
					</c:choose>
					</ul>
					<div class="box-expand">
						<a href="#" rold="button" class="ico expand" title="펼치기">펼치기</a>
						<a href="#" rold="button" class="ico unexpand" title="접기">접기</a>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>
<div class="foot-cont">
	<sec:authorize access="hasRole('ROLE_USER')">
		<c:if test="${spaceInfo.viewPrivacy != 'ALLGROUP' && spaceInfo.adminUserIdx != loginUser.weUserIdx}">
			<a style="cursor:pointer" role="button" class="btn joinRequest" data-space-idx="${spaceInfo.spaceIdx}">공간가입하기</a>
		</c:if>
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_USER') and leastLevel(3)">
		<c:if test="${spaceInfo.adminUserIdx == loginUser.weUserIdx}">
			<a style="cursor:pointer" role="button" class="btn" id="invite" data-space-idx="${spaceInfo.spaceIdx}">사용자초대하기</a>
		</c:if>
	</sec:authorize>
</div>
</section>


<iframe name="fileDownload" width="0" height="0" frameborder="0"  style="display:hidden"></iframe>

<form id="downloadForm" name="downloadForm" method="post" action="">
<input type="hidden" id="we_file_idx" name="we_file_idx" value="" />
</form>



<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/glider/space/spaceService.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-group-layer.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.dateFormat-1.0.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery.poshytip.js"></script>
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/interface/CommonService.js'></script>

<script type="text/javascript">
$(document).ready(function() {
	
	$('a[name="targetFiles"]').each(function(i) {
		$(this).live("mouseenter", function(){}).poshytip({
			className: 'tip-twitter',
			bgImageFrameSize: 15,
			showTimeout: 200,
			alignTo: 'target',
			alignX: 'center',
			offsetY: 10,
			allowTipHover: false,
			fade: false,
			slide: false
		});
	});
	
	
	var write = new GliderWiki.Space.Show().action();

	$("#invite").click(function(){
		var userNick = "";		// 닉네임
		var userEmail = "";		// 이메일
		var userName ="";		// 이름

		CommonService.getWeUserList("${loginUser.weUserIdx}", userNick, userEmail, userName, callBackWeUserList);
	});

	function callBackWeUserList(obj) {
		var weUserList = eval(obj);


		if(weUserList != null) {
			$.groupLayer({
				'userList' : weUserList,
				'type'     : 'invite'
			});
		}
	}


});
function inviteUserCallBack(arrayCheckId) {

	var spaceIdx = "${spaceInfo.spaceIdx}";

	if(GliderWiki.confirm('알림', '선택한 사용자를 초대하겠습니까?', function(){
		$.post("/space/inviteRequest", {arrUserIdx:arrayCheckId,spaceIdx:spaceIdx}, function(data){

			$(".groupWrap").remove();

			if(data == 'SUCCESS') {
				GliderWiki.alert("동료초대 결과","동료초대에 성공하였습니다.");
			}else{
				GliderWiki.alert("동료초대 결과","동료초대가 되지 않았습니다.");
			}
		});
	}));
}


function downloadFileServer(we_file_idx, real_name){
	GliderWiki.confirm("확인", real_name + " 파일을 지금 다운로드 하겠습니까?", function () {
		$('#downloadForm input[name=we_file_idx]').val(we_file_idx);
		$("#downloadForm").attr("target", "fileDownload");
		$("#downloadForm").attr("action", "/wiki/download").submit();		
	});
}	


</script>
</js:scripts>