<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/admin/include/adminHeader.jsp"%>
<%@taglib prefix="gf" uri="http://www.gliderwiki.org/gftags"%>
<section class="contents " role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">최근 통계 정보</h2>
	
			<div class="body-cont">
				<div class="count">
					<h3>전체 통계</h3>
					<div class="box-count">
						<div class="item" style="width:130px">
							<strong class="num">${visitCountInfo.totalVisitCount} / ${visitCountInfo.todayVisitCount}</strong>
							<span class="txt">방문</span>
						</div>
						<div class="item" style="width:100px">
							<strong class="num">${intTotalUser }</strong>
							<span class="txt">총회원</span>
						</div>
						<div class="item" style="width:100px">
							<strong class="num">${intTotalSpace }</strong>
							<span class="txt">개설된 공간</span>
						</div>
						
						<div class="item" style="width:100px">
							<strong class="num">${intTotalWiki }</strong>
							<span class="txt">개설된 Wiki</span>
						</div>
						<div class="item" style="width:80px">
							<strong class="num">${intTotalGroup }</strong>
							<span class="txt">개설된 그룹</span>
						</div>
						<div class="item" style="width:90px">
							<strong class="num">${intTotalTag }</strong>
							<span class="txt">총태그</span>
						</div>
						
					</div>
					
					
				</div>
				
				<div class="srch has-btn">
					<h3>최근 개설된 공간 </h3>	
					<div class="btnbox-rt">
					<!-- 
						<button type="button" class="btn-down">버튼1</button>
						<button type="button" class="btn-down">버튼2</button>
					-->
					</div>
					<div class="extension">
						<table class="tbl-result">
							<thead>
								<tr>
									<th>공간번호</th>
									<th>공간</th>
									<th>관리자</th>
									<th>개설일</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
								<c:when test="${spaceSize eq 0}">
									<tr><td colspan="4">생성된 공간이 없습니다.</td></tr>
								</c:when>
								<c:otherwise>
								<c:forEach items="${spaceList }" var="spaceList" varStatus="i">
								<tr>
									<td>${spaceList.we_space_idx }</td>
									<td style="text-align: left;">${spaceList.we_space_name }</td>
									<td>${spaceList.we_space_admin_nick }</td>
									<td>${gf:articleDate(spaceList.we_ins_date ,'yyyy.MM.dd hh:mm:ss')}</td>									
								</tr>
								</c:forEach>
								</c:otherwise>
								</c:choose>
								
							</tbody>
						</table>
					</div>
				</div>
				
				<div class="srch has-btn">
					<h3>최근 개설된 위키 </h3>	
					<div class="btnbox-rt">
						
					</div>
					<div class="extension">
						<table class="tbl-result">
							<thead>
								<tr>
									<th>제목 </th>
									<th>작성자</th>
									<th>글상태</th>
									<th>개설일</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
								<c:when test="${wikiSize eq 0}">
									<tr><td colspan="4">생성된 공간이 없습니다.</td></tr>
								</c:when>
								<c:otherwise>
								<c:forEach items="${wikiList }" var="wikiList" varStatus="i">
								<tr>
									<td style="text-align: left;"><a style="cursor:pointer;" name="viewWiki" id="viewWiki_${wikiList.we_wiki_idx }" title="${wikiList.we_wiki_idx }">${wikiList.we_wiki_title }</a></td>
									<td>${wikiList.we_user_nick }</td>
									<td>${wikiList.we_wiki_protect }</td>
									<td>${gf:articleDate(wikiList.we_ins_date ,'yyyy.MM.dd hh:mm:ss')}</td>									
								</tr>
								</c:forEach>
								</c:otherwise>
								</c:choose>
								
							</tbody>
						</table>
					</div>
				</div>
			
			</div>					
		</div>
</section>



<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/interface/AdminWikiService.js'></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.dateFormat-1.0.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-preview-layer.js"></script>
<script type="text/javascript">
//<![CDATA[
$(document).ready(function() {
	/**
	 * 01.위키 상세 내용 조회 
	 */
	$('a[name]="viewWiki"').each(function(index){
		$(this).click(function(){
			var attrId     = $(this).attr("id");
			var weWikiIdx  = $(this).attr("title");
			
			$.ajax({
				type:"GET"
				,url:"/admin/preview/"+weWikiIdx
				,data:{"attrId":attrId}
				,dataType:"json"
				,success:function(rtnObj){					
					
					var wikiMarkup = rtnObj.wikiMarkup;
					var wikiTitle = rtnObj.wikiTitle;
					var wikiRevision = rtnObj.wikiRevision;
					var weWikiIdx = rtnObj.weWikiIdx;
					
					if(rtnObj.result == 'SUCCESS'){
						callBackPreview(wikiMarkup, wikiTitle, wikiRevision, weWikiIdx);
					} else {
						alert('에러 - 정보가 존재하지 않습니다.');
					}
				}
			});
		});
	});

});


/**
 * 위키 내용 조회 
 */
function callBackPreview(wikiMarkup, wikiTitle, wikiRevision, weWikiIdx) {
	$.preview_layer({
		'markup' : wikiMarkup,
		'title'  : wikiTitle, 
		'revision' : wikiRevision,
		'wikiIdx' : weWikiIdx
		
	});
}



//]]>
</script>	



<%@include file="/WEB-INF/views/admin/include/adminFooter.jsp"%>