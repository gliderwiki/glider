<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%><%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"
%><%@include file="/WEB-INF/views/common/include/tags.jspf"%>
<c:set var="siteTitle">::Enterprise OpenSource Wiki – GLiDER™</c:set>

<!DOCTYPE html>
<html lang="ko" xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="robots" content="index,follow" />
	<c:choose>
	<c:when test="${empty title }">
	<meta property="og:title" content="${siteTitle }" />
	<meta name="description" content="Enterprise OpenSource WIKI - KMS Platform for collaboration" />
	</c:when>
	<c:otherwise>
	<meta property="og:title" content="${title }" />
	<meta name="description" content="${htmlContent }" />
	</c:otherwise>
	</c:choose>
	<meta property="og:site_name" content="GLiDER Wiki" />
	<meta property="og:image" content="http://www.gliderwiki.org/resource/glider/front/images/bg-cont.png" />
	
	<link rel="stylesheet" href="/resource/glider/front/css/common.css">
	<link rel="stylesheet" href="/resource/glider/front/css/modal.css">
	<link rel="stylesheet" href="/resource/glider/front/css/alimi.css">
	<link rel="stylesheet" href="/resource/glider/front/css/wiki.css" />
	<link rel="stylesheet" href="/resource/glider/front/css/dashboard.css" />
	<link rel="stylesheet" href="/resource/glider/front/css/space.css" />
	<link rel="stylesheet" href="/resource/glider/front/css/taglist.css" />
	<link rel="stylesheet" href="/resource/glider/front/css/my.css" />
	<link rel="stylesheet" href="/resource/glider/front/css/control.css" />
	<link rel="stylesheet" href="/resource/glider/front/css/main.css" />
	<link rel="stylesheet" href="/resource/glider/front/css/board.css" />

	<script type="text/javascript" src="/resource/glider/code/js/shCore.js"></script>
	<script type="text/javascript" src="/resource/glider/code/js/shBrushJScript.js"></script>

	<decorator:head />
	<!--[if IE]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->


</head>
<body <decorator:getProperty property="body.class" writeEntireProperty="true" />>
<!--[if lt IE 8]>
<p style="padding:1em;border-bottom:10px dashed #fff;background-color:#efefef;">
	<strong>낡은 브라우저</strong>를 사용하고 계십니다.<br />
	<a href="http://browsehappy.com/">새로운 브라우저로 업그레이드</a> 하거나 <a href="http://www.google.com/chromeframe/?redirect=true">구글 크롬프레임을 설치</a>해서 더 빠르고 아름다운 웹을 경험해 보세요.
</p>
<![endif]-->
<%@include file="/WEB-INF/views/common/include/default_header.jsp"%>

<decorator:body />

<%@include file="/WEB-INF/views/common/include/default_footer.jsp"%>

<script type="text/javascript" src="/resource/libs/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery-ui-1.8.17.custom.min.js"></script>
<script type="text/javascript" src="/resource/glider/common/wiki.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.pieChart.js"></script>


<%@ include file="/WEB-INF/views/common/include/jsconstants.jspf" %>
<script type="text/javascript" src="/resource/glider/common/socket.io.js"></script>
<script type="text/javascript" src="/resource/glider/common/notification.js"></script>
<script type="text/javascript" src="/resource/glider/common/tooltip.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-profile.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.dateFormat-1.0.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	<c:if test="${loginUser.guest eq false}">
		var noti = new GliderWiki.Notification.Perception($(".layer_alimi"), GliderWiki.LoginUser.alarmChannel).process();
	</c:if>
	var tooltip = new GliderWiki.ToolTip.Action("${loginUser.weUserIdx}").process();
});

/**
 * 프로파일 보기 (공통)
 */
function viewProfile(targetUserIdx) {
	var weUserIdx = targetUserIdx;
	$.ajax({
		type:"GET"
		,url:"/user/viewProfile"
		,data:{"weUserIdx":weUserIdx}
		,dataType:"json"
		,success:function(rtnObj){
			var friends = rtnObj.connectionListSize;
			var weProfile = rtnObj.weProfile;
			var weUser = rtnObj.weUser;
			var lastVisit = rtnObj.lastVisitDate;

			if(rtnObj.result == 'SUCCESS'){
				callBackProfile(weProfile, weUser, friends, lastVisit);
			} else {
				GliderWiki.alert('에러', '사용자 정보가 존재하지 않습니다.');
			}
		}
	});
}

/**
 * 프로파일 보기 콜백 
 */
function callBackProfile(weProfile, weUser, friends, lastVisit) {
	// '/resource/real'+weProfile.we_thumb_path+weProfile.we_thumb_name;
	var thumbImg = '';

	if(weProfile.we_thumb_path == null || weProfile.we_thumb_path == '') {
		thumbImg = '/resource/glider/front/images/bg-cont.png';
	} else {
		thumbImg = '/resource/real'+weProfile.we_thumb_path+weProfile.we_thumb_name;
	}
	
	$.userProfile({
		'email' : weUser.we_user_id,
		'nick'	: weUser.we_user_nick,
		'homepage' : weProfile.we_user_site,
		'point' : weProfile.we_point,
		'joinDt' : weUser.we_user_join_date,
		'visitDt' : $.format.date(lastVisit, "yyyy.MM.dd hh:mm:ss"),
		'friends' : friends,
		'image' : thumbImg
	});

}

function addUserListInFriend(targetUserIdx) {

	if(GliderWiki.confirm('알림', '선택한 사용자를 인맥에 추가합니까?', function(){
		$.ajax({
			type:"GET"
			,url:"/user/addfriend"
			,data:{"arrayCheckId":targetUserIdx}
			,dataType:"json"
			,success:function(rtnObj){
				if(rtnObj.result == 'SUCCESS'){
					GliderWiki.alert("알림", "내 인맥에 추가되었습니다.");
				} else if(rtnObj.result == 'ALREADY') {
					GliderWiki.alert("알림", "이미 추가되었습니다.");
				}else {
					GliderWiki.alert("알림", "내 인맥에 추가되지 않았습니다.");
				}
			}
		});
	}));
}
</script>
<js:load/>
</body>
</html>