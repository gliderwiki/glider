<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %><%@
taglib prefix="space" tagdir="/WEB-INF/tags/space" %>
<section class="contents " role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">게시판 목록</h2>
		<div class="body-cont board">
			<div class="list-board">
				<div class="search-board">
					<form action="/">
						<select class="sel">
							<option>제목</option>
							<option>내용</option>
							<option>이름</option>
						</select>
						<input type="text" class="keyword" />
						<button type="button" class="btn-submit" id="searchBbs">검색</button>
					</form>
				</div>
				<table class="list" style="table-layout:fixed;" >
					<colgroup>
						<col style="width:80px;" />
						<col style="width:428px;" />
						<col style="width:100px;" />
						<col style="width:100px;" />
						<col style="width:80px;" />
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
						<c:when test="${empty list}">
						<tr>
							<td colspan="5">게시된 글이 없습니다.</td>
						</c:when>
						<c:otherwise>
						<c:forEach var="list" items="${list}" varStatus="row">
						<tr>
							<td>${((nowPage-1)*10)+row.count}</td>
							<td class="title">
								<a href="/space/${spaceIdx}/board/${list.we_bbs_idx}">${list.we_bbs_title} (${list.row_num})</a>
							</td>
							<td>${list.we_ins_name}</td>
							<td>${gf:articleDate(list.we_ins_date,'yyyy.MM.dd')}</td>
							<td>${list.we_hit_count}</td>
						</tr>
						</c:forEach>
						</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				<div class="paging">
					<c:if test="${pageIsPrev}">
						<a href="/space/${spaceIdx}/board?page=${pageStart - 1}" class="navi" title="첫페이지">◀</a>
					</c:if>
		
					<c:forEach var="page" begin="${pageStart}" end="${pageEnd}" varStatus="status">
						<a href="/space/${spaceIdx}/board?page=${page}">${page}</a>
		
						<c:if test="${!status.last}"><span class="bar"> | </span></c:if>
					</c:forEach>
		
					<c:if test="${pageIsNext}">
						<a href="/space/${spaceIdx}/board?page=${pageEnd + 1}" class="navi" title="다음페이지">▶</a>
					</c:if>
					<c:if test="${empty list}">
						&nbsp;&nbsp;&nbsp;
					</c:if>
				</div>
				<sec:authorize access="hasRole('ROLE_USER') and leastLevel(3)">
				<div class="wrap-btn">
					<a style="cursor:pointer" id="insertBbs" class="btn">쓰기</a>
				</div>
				</sec:authorize>
			</div>
		</div>
	</div>
</section>

<js:scripts>
<script type="text/javascript" src="/resource/glider/space/spaceService.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#insertBbs").bind("click", function(){
		var url = "/space/${spaceIdx}/board/form";
		var isGuest = "${loginUser.guest}";
		var accessPermit = "${loginUser.weGrade}";
		
		if(isGuest == "true" || (accessPermit < 3)) {
			GliderWiki.alert("에러","권한이 없습니다.");
		} else {
			$(location).attr('href', url);	
		}
	});
	
	$("#searchBbs").bind("click", function(){
		GliderWiki.alert("안내","준비중입니다.");
	});
});

</script>
</js:scripts>