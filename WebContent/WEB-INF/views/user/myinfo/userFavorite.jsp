<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/include/tags.jspf" %>
 

<body>


<section class="contents " role="main">
	<div class="wrap-cont">
		
		<h2 class="tit-cont">즐겨찾기 목록</h2>
				
		<div class="body-cont my">
			<c:choose>
			<c:when test="${spaceSize == 0}">
			<div class="sub">
				<div class="box-empty">
					즐겨찾기한 공간 목록이 없습니다.
				</div>
			</div>
			</c:when>
			<c:otherwise>
			<div class="sub favorite">
				
				<div class="box">
					<h4 class="tit-sec">공간 목록</h4>
					
					<ul class="list type1">
						<c:forEach items="${spaceList }" var="spaceList" varStatus="stat">	
						<li id="tab_space_${spaceList.weSpaceIdx }">
							<div class="title">
								<a href="/space/${spaceList.weSpaceIdx }">${spaceList.weSpaceName }</a>
							</div>
							<div class="time">
								${spaceList.weAddDate }
							</div>
							<div class="info">
								<a style="cursor:pointer" name="space_list" title="${spaceList.weSpaceIdx }" id="space_${spaceList.weSpaceIdx }" role="button" class="btn-del">삭제</a>
							</div>
						</li>
						</c:forEach>
					</ul>
					
				</div>
			</div>
			<c:if test="${spaceSize > 20 }">			
			<div class="wrap_btn">
				<a href="#" class="btn">더 살펴보기</a>
			</div>
			</c:if>
			</c:otherwise>
			</c:choose>
			
			<c:choose>
			<c:when test="${wikiSize == 0}">
			<div class="sub">
				<div class="box-empty">
					즐겨찾기한 위키 목록이 없습니다.
				</div>
			</div>
			</c:when>
			<c:otherwise>
			<div class="sub favorite">
				
				<div class="box">
					<h4 class="tit-sec">위키 목록</h4>
					
					<ul class="list type1">
						<c:forEach items="${wikiList }" var="wikiList" varStatus="stat">	
						<li id="tab_wiki_${wikiList.weWikiIdx  }">
							<div class="title">
								<a href="/wiki/${wikiList.weWikiIdx }">${wikiList.weWikiTitle }</a>
							</div>
							<div class="time">
								${wikiList.weAddDate }
							</div>
							<div class="info">
								<a style="cursor:pointer" name="wiki_list" title="${wikiList.weWikiIdx }" id="wiki_${wikiList.weWikiIdx }" role="button" class="btn-del">삭제</a>
							</div>
						</li>
						</c:forEach>
					</ul>
					
				</div>
			</div>
			<c:if test="${wikiSize > 20 }">
			<div class="wrap_btn">
				<a href="#" class="btn">더 살펴보기</a>
			</div>
			</c:if>
			</c:otherwise>
			</c:choose>
			
		</div>
		<div class="foot-cont">
		</div>
		
	</div>
</section>





<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/interface/CommonService.js'></script>
<script type='text/javascript' src='/dwr/util.js'></script>
<script type="text/javascript">
//<![CDATA[
	$(document).ready(function() {
		$('a[name="space_list"]').each(function(i) {
			var code = "";
			var userIdx = "${loginUser.weUserIdx}";
			var type = "SPACE";
			var weSpaceIdx = 0;
			$(this).bind("click", function(){
				code = $(this).attr("id");
				weSpaceIdx = $(this).attr("title");
				CommonService.delFavoriteDWR(userIdx, type, weSpaceIdx, callBackFavorite);
			});
		});
		$('a[name="wiki_list"]').each(function(i) {
			var code = "";
			var userIdx = "${loginUser.weUserIdx}";
			var type = "WIKI";
			var weSpaceIdx = 0;
			$(this).bind("click", function(){
				code = $(this).attr("id");
				weSpaceIdx = $(this).attr("title");
				CommonService.delFavoriteDWR(userIdx, type, weSpaceIdx, callBackFavorite);
			});
		});
		
		
		function callBackFavorite(outData) {
			if(outData == 1) {
				GliderWiki.alert("처리완료","삭제되었습니다.");
				
				$("#okBtn").click(function () {
					$(location).attr('href',"/user/favorite");
				});
			} else {
				alert('[결과 : ' + outData + '] 에러가 발생하였습니다. 다시 시도 하세요');
				return;
			}
		}
	});


//]]>
</script>
</js:scripts>
</body>