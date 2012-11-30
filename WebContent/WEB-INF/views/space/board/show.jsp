<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>
<jsp:scriptlet>
pageContext.setAttribute("crlf", "\r\n");
pageContext.setAttribute("lf", "\n");
pageContext.setAttribute("cr", "\r");
</jsp:scriptlet>


<html>
<head>
</head>
<body>
<section class="contents " role="main">
	<div class="wrap-cont">

		<h2 class="tit-cont">게시판 글보기</h2>



<div class="body-cont board">
	<div class="view-board">
		<h2 class="title">${bbs.we_bbs_title}</h2>
		<div class="meta">
			<span class="item">작성일 : ${gf:articleDate(bbs.we_ins_date,'yyyy.MM.dd')}</span>
			<span class="item">조회수 : ${bbs.we_hit_count}</span>
			<span class="item">작성자 : ${bbs.we_ins_name}</span>
		</div>
		<div class="cont">
			 ${fn:replace(bbs.we_bbs_text, lf, "<br/>")}
		</div>
		<div class="wrap-btn">
			<a href="/space/${bbs.we_space_idx}/board" class="btn">목록</a>
			<a href="/space/${bbs.we_space_idx}/board/${bbs.we_bbs_idx}/form" class="btn">수정</a>
			<a href="#" class="btn articleDelete" data-bbs-idx="${bbs.we_bbs_idx}" data-space-idx="${bbs.we_space_idx}">삭제</a>
		</div>		
	</div>
</div>
</div>
</section>
<js:scripts>
<script type="text/javascript">
$(document).ready(function() {
	
	$(".articleDelete").bind("click",function(e) {
		e.preventDefault();
		var bbsIdx = $(this).data("bbsIdx");
		var spaceIdx = $(this).data("spaceIdx");
		
		GliderWiki.confirm('삭제 ', '해당글을 삭제하겠습니까?',  function() {
			 var actionUrl = "/space/"+spaceIdx+"/board/delete";
			 var form = $('<form name="deleteForm" method="post"/>').attr('action', actionUrl);
			 form.append('<input type="hidden" name="spaceIdx" value="' + spaceIdx + '" />');
			 form.append('<input type="hidden" name="boardIdx" value="' + bbsIdx + '" />');
			 $("body").append(form);
			 form.submit();
		});
	});	
});

</script>
</js:scripts>
</body>
</html>