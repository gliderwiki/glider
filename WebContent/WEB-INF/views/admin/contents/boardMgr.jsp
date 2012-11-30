<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/admin/include/adminHeader.jsp"%>
<%@taglib prefix="gf" uri="http://www.gliderwiki.org/gftags"%>

<section class="contents" role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">게시판 관리</h2>
	
			<div class="body-cont">
			
				<div class="srch">
					<h3>조회조건</h3>	
					<div class="box-srch">
						<form name="weBbs" id="weBbs" method="POST" action="/admin/board">
							<input type="hidden" name="we_space_idx" id="we_space_idx" value="" />
							<table>
								<!-- 
								<tr>
									<th>작성기간검색</th>
									<td>
										<input type="text" class="time" />년
										<input type="text" class="time" />월
										<input type="text" class="time" />일부터 
										<input type="text" class="time" />년
										<input type="text" class="time" />월
										<input type="text" class="time" />일까지 
									</td>
								</tr>
								-->
								<tr>
									<th>작성자</th>
									<td>
										<input type="text" name="we_ins_name" id="we_ins_name" value="${weUserNick }"/>
									</td>
								</tr>
								<tr>
									<th>제목</th>
									<td>
										<input type="text" name="we_bbs_title" id="we_bbs_title" value="${weWikiTitle }" />
									</td>
								</tr>
								<tr>
									<th>내용</th>
									<td>
										<input type="text" name="we_bbs_text" id="we_bbs_text" value="${weWikiText }" />
									</td>
								</tr>
								<tr>
									<th>공간별</th>
									<td>
										<select id="spaceInfoList" title="공간정보목록" STYLE="width:400px;">
											<option value="">공간을 선택하세요.</option>
											<c:forEach items="${spaceList }" var="spaceList" varStatus="stat">
											<option value="${spaceList.we_space_idx}">${spaceList.we_space_name }</option>
									        </c:forEach>
										</select>
									</td>
								</tr>
							</table>
							<button type="submit" class="btn btn-submit">검색</button>
						</form>
					</div>
				</div>
							
				<table class="tbl-result">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>입력일</th>
							<th>조회수</th>
							<th>구분</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty bbsSearchList}">
								<tr><td colspan="6">데이터가 없습니다.</td></tr>
							</c:when>
							<c:otherwise>
							<c:forEach items="${bbsSearchList }" var="bbsSearchList" varStatus="i">
							<tr>
								<td>${bbsSearchList.we_bbs_idx }</td>
								<td style="text-align:left"><a href="javascript:getContent('${bbsSearchList.we_bbs_idx }')">${bbsSearchList.we_bbs_title }</a></td>
								<td>${bbsSearchList.we_ins_name }</td>
								<td>${gf:articleDate(bbsSearchList.we_ins_date,'yyyy.MM.dd')}</td>
								<td>${bbsSearchList.we_hit_count }</td>
								<td>구분</td>
							</tr>
							</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				
				<!-- 
				<div class="mem-info">
					<table class="tbl-info">
						
						<tr>
							<th>항목</th>
							<td><input type="text" /></td>
						</tr>
						
						<tr>
							<th>항목</th>
							<td>
							<select class="select-list" >
								<option vale="0">- 사용자 생성 그룹 -</option>
							</select>
							</td>
						</tr>						
					</table>
					
				</div>
				 -->
			</div>	
		</div>			
</section>			



<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-preview-layer.js"></script>
<script type="text/javascript">
//<![CDATA[
var timer = null;
$(document).ready(function() {
	/**
	 * 01.최근 개설한 공간 목록 조회 
	 */
	$("#spaceInfoList").change(function() {
		var spaceIdx = $("option:selected", this).val(); // 선택한 공간번호 
		$("#we_space_idx").val(spaceIdx);
		var frm;
		frm = $('#weBbs'); 
		frm.submit(); 
		
	});
	
});

function getContent(we_bbs_idx) {
	console.log(we_bbs_idx);
	$.post("/admin/board/preview", {we_bbs_idx:we_bbs_idx}, function(data){
		console.log(data);
		if(data.result == 'SUCCESS'){
			$.preview_layer({
				'markup' : data.markup,
				'title'  : data.markup, 
				'revision' : 0,
				'wikiIdx' : we_bbs_idx
			});
		} 
	});
}
//]]>
</script>
<%@include file="/WEB-INF/views/admin/include/adminFooter.jsp"%>				