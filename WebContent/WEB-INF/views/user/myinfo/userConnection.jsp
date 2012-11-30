<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>
<link rel="stylesheet" href="/resource/glider/admin/css/admin.css" />
<section class="contents " role="main">
	<div class="wrap-cont">
		
		<h2 class="tit-cont">내 인맥</h2>
				
		<div class="body-cont space">
			<div class="relation">
				<div class="wrap-box">
					<div class="boxConnection left">
						<h3 class="tit-sub">내가 추가한 인맥 <a style="cursor:pointer" id="addFriends">[인맥추가하기]</a></h3>
						<c:choose>
						<c:when test="${myFriendListSize == 0}">
						<ul class="list type1 top-line">
						<div class="box-empty">
							내가 추가한 인맥이 없습니다. 
						</div>
						</ul>
						</c:when>
						<c:otherwise>
						<ul class="list type1 top-line">
							<c:forEach items="${myFriendList }" var="myFriendList" varStatus="stat">
							<li>
								<div class="title">
									<a href="javascript:viewProfile('${myFriendList.weTargetUserIdx }')">${myFriendList.weUserNick }</a>
								</div>
								<div class="time">
									${myFriendList.weAddDate }
								</div>
								<div class="action">
									<a style="cursor:pointer" name="delele_relation" title="${myFriendList.weTargetUserIdx }" user-name="${myFriendList.weUserNick }" role="button" class="btn-relation">인맥끊기</a>
								</div>
							</li>
							</c:forEach>
						</ul>
						</c:otherwise>
						</c:choose>
					</div>
					<div class="boxConnection right">
						<h3 class="tit-sub">나를 추가한 인맥</h3>
						<c:choose>
						<c:when test="${myConnectionListSize == 0}">
						<ul class="list type1 top-line">
						<div class="box-empty">
							나를 추가한 인맥이 없습니다.
						</div>
						</ul>
						</c:when>
						<c:otherwise>
						<ul class="list type1 top-line">
							<c:forEach items="${myConnectionList }" var="myConnectionList" varStatus="stat">	 
							<li>
								<div class="title">
									<a href="javascript:viewProfile('${myConnectionList.weUserIdx }')">${myConnectionList.weUserNick }</a>
								</div>
								<div class="time">
									${myConnectionList.weAddDate }
								</div>
								<div class="action">
									<a href="javascript:viewProfile('${myConnectionList.weUserIdx }')" style="cursor:pointer" name="view_profile" role="button" class="btn-relation">프로필보기</a>
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
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/interface/CommonService.js'></script>
<script type='text/javascript' src='/dwr/util.js'></script>
<script type="text/javascript">
//<![CDATA[
$(document).ready(function() {
	
	/**
	 * 1. 인맥삭제 
	 */
	$('a[name="delele_relation"]').each(function(i) {
		$(this).bind("click", function() {
			var userName = $(this).attr("user-name");
			var targetUserIdx = $(this).attr("title")
			var	code = $(this).attr("id");
			var userIdx = "${loginUser.weUserIdx}";
			GliderWiki.confirm("확인", userName + "님을 인맥에서 삭제하겠습니까?", function () {
				CommonService.delRelationDWR(userIdx,  targetUserIdx, callBackRelation);
			});
		});
	});
	
	// 1. 인맥삭제 콜백 
	function callBackRelation(outData) {
		if(outData == 1) {
			GliderWiki.alert("알림", "처리 되었습니다.");
			
			$("#okBtn").click(function () {
				$(location).attr('href',"/user/connection");
			});
		} else {
			alert();
			GliderWiki.alert("알림", '[결과 : ' + outData + '] 에러가 발생하였습니다. 다시 시도 하세요');
			return;
		}
	}
	
	
	$("#addFriends").click(function(){
		var userNick = "";		// 닉네임
		var userEmail = "";		// 이메일
		var userName ="";		// 이름
		
		var weUserIdx = "${loginUser.weUserIdx}";
		
		if(weUserIdx == '' || weUserIdx == '0') {
			GliderWiki.alert("알림", '로그인 후 이용해주세요.');
			return;
		}
		
		CommonService.getWeUserList(weUserIdx, userNick, userEmail, userName, callBackWeUserList);
	});
	
});


//회원 리스트 콜백 
function callBackWeUserList(obj) {
	var weUserList = eval(obj); 
	if(weUserList != null) {
		$.groupLayer({
			'userList' : weUserList,
			'type'     : 'front'
		});	
	}
}

function addUserListInFriend(arrayCheckId) {
	
	if(GliderWiki.confirm('알림', '선택한 사용자를 인맥에 추가합니까?', function(){
		$.ajax({
			type:"GET"
			,url:"/user/addfriend"
			,data:{"arrayCheckId":arrayCheckId}
			,dataType:"json"
			,success:function(rtnObj){					
				if(rtnObj.result == 'SUCCESS'){
					GliderWiki.alert("알림", rtnObj.rtnMsg);
					$.groupLayer.hide();
					$("#okBtn").click(function () {
						$(location).attr('href',"/user/connection");
					});
				} else {
					GliderWiki.alert("알림", rtnObj.rtnMsg);
					$.groupLayer.hide();
					return;
				}
			}
		});		
	}));
}


//]]>
</script>
</js:scripts>

