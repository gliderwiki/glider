<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>
<jsp:scriptlet>
pageContext.setAttribute("crlf", "\r\n");
pageContext.setAttribute("lf", "\n");
pageContext.setAttribute("cr", "\r");
</jsp:scriptlet>
<section class="contents " role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">게시판 글보기</h2>
		<div class="body-cont board">
			<div class="view-board">
				<h2 class="title" style="word-wrap :break-word">${bbs.we_bbs_title}</h2>
				<div class="meta">
					<span class="item">작성일 : ${gf:articleDate(bbs.we_ins_date,'yyyy.MM.dd')}</span>
					<span class="item">조회수 : ${bbs.we_hit_count}</span>
					<span class="item">작성자 : ${bbs.we_ins_name}</span>
				</div>
				<div class="cont">
					 ${fn:replace(bbs.we_bbs_text, lf, "<br/>")}
				</div>
				<div class="wrap-btn">
					<a style="cursor:pointer" id="bbsList" class="btn">목록</a>
					<a style="cursor:pointer" id="bbsUpdate" data-bbs-idx="${bbs.we_bbs_idx}" data-space-idx="${bbs.we_space_idx}" data-user-idx="${bbs.we_ins_user}" class="btn">수정</a>
					<a style="cursor:pointer" class="btn articleDelete" data-bbs-idx="${bbs.we_bbs_idx}" data-space-idx="${bbs.we_space_idx}" data-user-idx="${bbs.we_ins_user}" >삭제</a>
				</div>				
				<div class="form-cmt" id="_comment">
					<form:form modelAttribute="WeBbsComment" name="WeBbsComment" id="WeBbsComment" method="POST" action="/space/${spaceIdx}/board/${boardIdx}/insertComment">
					<input type="hidden" name="weBbsCommentIdx" id="weBbsCommentIdx" />
						<table>
							<colgroup>
								<col style="width:15%;" />
								<col style="width:35%;" />
								<col style="width:15%;" />
								<col style="width:35%;" />
							</colgroup>
							<tbody>
								<tr>
									<th scope="row">작성자</th>
									<td colspan="3">
										<input type="text" name="userNick" value="${loginUser.weUserNick}" disabled />
										<form:hidden path="we_ins_name" class="title" value="${loginUser.weUserNick}"/>
									</td>
								</tr>
								<tr>
									<th scope="row">보안문자</th>
									<td>
										<input type="text" name="noSpam" id="noSpam" />
										<input type="hidden" name="randomKey" id="randomKey" value="${randomKey}"/>
									</td>
									<th scope="row" colspan="2">스팸 방지 보안문자 [<b>${randomKey}</b>]</th>
								</tr>
								<tr class="content">
									<th scope="row">내용</th>
									<td colspan="3">
										<form:textarea path="we_bbs_text" id="we_bbs_text" value="" />
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center" id="commentStatus"><button type="button" id="commentInsert" class="btn">댓글저장</button></th>
								</tr>
							</tbody>
						</table>
					</form:form>
				</div>
				<ul class="list-cmt">
					<c:forEach var="commentList" items="${commentList}" varStatus="row">
					<li>
						<div class="head">
							<strong>
								<a href="#">${commentList.we_ins_name} </a>
							</strong>
							<span class="time">${gf:articleDate(commentList.we_ins_date,'yyyy.MM.dd HH:mm:ss')}</span>
							<div class="util">
								<a href="javascript:callModify('${commentList.we_bbs_comment_idx}', '${commentList.we_bbs_idx}','${commentList.we_ins_user}');" class="btn-edit" title="수정">수정</a>
								<a href="javascript:callDelete('${commentList.we_bbs_comment_idx}', '${commentList.we_ins_user}');" class="btn-del" title="삭제">삭제</a>
							</div>
						</div>
						<div class="content">
							${fn:replace(commentList.we_bbs_text, lf, "<br/>")}
						</div>
					</li>
					</c:forEach>
				</ul>
				
			</div>
		</div>
	</div>
</section>
<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$(".articleDelete").bind("click",function(e) {
		e.preventDefault();
		var bbsIdx = $(this).data("bbsIdx");
		var spaceIdx = $(this).data("spaceIdx");
		
		var userIdx = $(this).data("userIdx");
		var isGuest = "${loginUser.guest}";
		var weUserIdx = '${loginUser.weUserIdx}';
		var weGrade = "${loginUser.weGrade}";
		
		if(weGrade == '9' || userIdx == weUserIdx) {
			GliderWiki.confirm('삭제 ', '해당글을 삭제하겠습니까?',  function() {
				 var actionUrl = "/space/"+spaceIdx+"/board/delete";
				 var form = $('<form name="deleteForm" method="post"/>').attr('action', actionUrl);
				 form.append('<input type="hidden" name="spaceIdx" value="' + spaceIdx + '" />');
				 form.append('<input type="hidden" name="boardIdx" value="' + bbsIdx + '" />');
				 $("body").append(form);
				 form.submit();
			});
		} else {
			if(isGuest == "true") {
				GliderWiki.alert("에러","로그인 정보가 없습니다. 로그인 하세요.");	
			} else {
				GliderWiki.alert("에러","권한이 없습니다.");
			}
			return;
		}
				
	});	
	
	$("#bbsList").bind("click", function() {
		var url = "/space/${bbs.we_space_idx}/board";
		$(location).attr('href', url);
	});
	
	$("#bbsUpdate").bind("click", function(e){
		e.preventDefault();
		var bbsIdx = $(this).data("bbsIdx");
		var spaceIdx = $(this).data("spaceIdx");
		var userIdx = $(this).data("userIdx");
		
		var url = "/space/${bbs.we_space_idx}/board/${bbs.we_bbs_idx}/form";
		
		var isGuest = "${loginUser.guest}";
		var weUserIdx = '${loginUser.weUserIdx}';
		
		//console.log("isGuest : " + isGuest);
		//console.log("weUserIdx : " + weUserIdx);
		
		if(isGuest == "true" || (userIdx != weUserIdx)) {
			GliderWiki.alert("에러","권한이 없습니다.");
			return;
		} else {
			$(location).attr('href', url);	
		}
	});
	
	$("#commentInsert").bind("click", function(e){
		e.preventDefault();
		
	
		var isGuest = "${loginUser.guest}";
		var noSpam = $("#noSpam").val();
		var randomKey = $("#randomKey").val();
		var text = $("#we_bbs_text").val();
		
		//console.log("#isGuest : " + isGuest);
		
		if(checkValid(isGuest, text, noSpam, randomKey)) {
			$("#WeBbsComment").submit();	
		}
		
	});
	
	$("#commentUpdate").live("click", function(){
		//console.log("update");
		var isGuest = "${loginUser.guest}";
		var noSpam = $("#noSpam").val();
		var randomKey = $("#randomKey").val();
		var text = $("#we_bbs_text").val();
		var weBbsCommentIdx = $("#weBbsCommentIdx").val();
		
		if(checkValid(isGuest, text, noSpam, randomKey)) {
			GliderWiki.confirm('확인 ', '해당글을 수정하겠습니까?',  function() {
				$.post("/space/${spaceIdx}/board/${boardIdx}/updateComment", {weBbsCommentIdx:weBbsCommentIdx,weBbsText:text}, function(data){
					//console.log("data : " + data);
					if(data.result == 'SUCCESS'){
						$(location).attr('href', "/space/${spaceIdx}/board/${boardIdx}");	
					} else {
						GliderWiki.alert("에러","에러가 발생 하였습니다. 다시 시도 하세요.");
					}
				});	
			});
		}
	});
});

/**
 * 댓글 수정 
 */
function callModify(weBbsCommentIdx, weBbsIdx, userIdx){
	//console.log("#commentIdx : " + weBbsCommentIdx);
	location.href = "#_comment";
	var weUserIdx = '${loginUser.weUserIdx}';
	var isGuest = "${loginUser.guest}";
	
	if(weUserIdx != userIdx || isGuest == "true") {
		GliderWiki.alert("에러","수정 권한이 없습니다.");
		return;
	}
	
	$.ajax({
		type:"GET"
		,url:"/space/${bbs.we_space_idx}/board/getComment"
		,data:{"weBbsCommentIdx":weBbsCommentIdx,"weUserIdx":weUserIdx,"weBbsIdx":weBbsIdx}
		,dataType:"json"
		,success:function(rtnObj){
			if(rtnObj.result == 1) {
				$("#we_bbs_text").val(rtnObj.contents);	
				$("#commentStatus").html("<button type=\"button\" id=\"commentUpdate\" class=\"btn\">댓글수정</button>");
				$("#weBbsCommentIdx").val(weBbsCommentIdx);
			} else {
				GliderWiki.alert("에러","잘못된 접근입니다");
				return;
			}
			
		}
	});
}

/**
 * 댓글 삭제 
 */
function callDelete(weBbsCommentIdx,  userIdx){
	var weUserIdx = '${loginUser.weUserIdx}';
	var weGrade = "${loginUser.weGrade}";
	var weUserIdx = '${loginUser.weUserIdx}';

	if(userIdx == weUserIdx || weGrade == '9') {
		GliderWiki.confirm('확인 ', '해당글을 삭제 하겠습니까?',  function() {
			$.post("/space/${spaceIdx}/board/${boardIdx}/deleteComment", {weBbsCommentIdx:weBbsCommentIdx,weUserIdx:weUserIdx}, function(data){
				//console.log("data : " + data);
				if(data.result == 'SUCCESS'){
					$(location).attr('href', "/space/${spaceIdx}/board/${boardIdx}");	
				} else {
					GliderWiki.alert("에러","에러가 발생 하였습니다. 다시 시도 하세요.");
				}
			});	
		});
	} else {
		GliderWiki.alert("에러","수정 권한이 없습니다.");
		return;
	}
}

/**
 * 댓글 체크 
 */
function checkValid(isGuest, text, noSpam, randomKey) {

	if(isGuest == "true") {
		GliderWiki.alert("에러","권한이 없습니다.로그인 후 이용하세요.");
		return false;
	}
	
	if(GliderWiki.Utils.isEmpty(text)) {
		GliderWiki.alert("에러","내용을  입력하셔야 합니다.");
		return false;
	}
	
	if(GliderWiki.Utils.isEmpty(noSpam) || noSpam != randomKey) {
		GliderWiki.alert("에러","보안 문자가 올바르지 않습니다.");
		return false;
	}
	
	return true;
}


</script>
</js:scripts>
