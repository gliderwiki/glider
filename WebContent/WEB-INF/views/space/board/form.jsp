<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>
<html>
<head>
</head>
<body>
<section class="contents " role="main">
	<div class="wrap-cont">

		<h2 class="tit-cont">게시판 글쓰기</h2>
			<form:form modelAttribute="WeBbs" name="WeBbs" method="POST" action="/space/${spaceIdx}/board/create">
			<form:hidden path="we_bbs_idx"/>
			<form:hidden path="we_space_idx"/>
			<input type="hidden" name="_method" id="_method">
				<div class="body-cont board">
					<div class="write-board">
						<table>
							<colgroup>
								<col style="width:15%;" />
								<col style="width:35%;" />
								<col style="width:15%;" />
								<col style="width:35%;" />
							</colgroup>
							
							<tbody>
								<tr>
									<th scope="row">제목</th>
									<td colspan="3">
										<form:input path="we_bbs_title" class="title" value="${WeBbs.we_bbs_title}" />
									</td>
								</tr>
								<tr>
									<th scope="row">보안문자</th>
									<td>
										<input type="text" name="noSpam" id="noSpam" />
										<input type="hidden" name="randomKey" id="randomKey" value="${randomKey}"/>
									</td>
									<th scope="row" colspan="2">스팸 방지 보안 문자 [<b>${randomKey}</b>]</th>
								</tr>
							</tbody>
						</table>
						<div class="util">
						</div>
						<div class="wiki">
							<div class="editor">
								<div class="e-textarea">
									<div class="e-">
									</div>
									<form:textarea path="we_bbs_text" value="${WeSpace.we_bbs_text}" />
								</div>
							</div>
						</div>
				</div>
				<div class="foot-cont">
					<c:choose>
					<c:when test="${empty WeBbs.we_bbs_idx}">
						<button type="submit" class="btn">저장</button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn">수정</button>
					</c:otherwise>
					</c:choose>
					<a href="/space/${spaceIdx}/board" class="btn">목록으로</a>
				</div>
		</form:form>
	</div>
</section>

<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/glider/space/boardService.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var write = new GliderWiki.Board.Write($("#WeBbs")).create();
});
</script>
</js:scripts>
</body>
</html>