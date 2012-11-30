<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>
<body>
위키 메인 <BR />
<table border="1">
<thead>
	<tr>
		<td>위키번호</td>
		<td>부모순번</td>
		<td>위키명</td>
		<td>작성자</td>
		<td>작성일</td>
	</tr>
</thead> 
<c:forEach items="${wikiList }" var="wikiList" varStatus="stat">	 
	<tr>
		<td>${wikiList.we_wiki_idx}</td>
		<td>${wikiList.we_wiki_parent_idx}</td>
		<td><c:forEach begin="1" end="${wikiList.we_wiki_order_idx}">&nbsp;&nbsp;&nbsp;</c:forEach><a href="/wiki/${wikiList.we_wiki_idx}">${wikiList.we_wiki_title }</a></td>
		<td>${wikiList.we_ins_user }</td>
		<td>${wikiList.we_ins_date }</td>
	</tr>
</c:forEach>
</table>

<a href="/wiki/new/${spaceIdx }">새 위키 만들기</a> <BR/>
<a href="/wiki/test/exam1/${spaceIdx }">예제 1 만들기</a> <BR/>
<a href="/wiki/test/exam2/${spaceIdx }">예제 2 만들기</a> <BR/>
<a href="/wiki/test/exam3/${spaceIdx }">예제 3 만들기</a> <BR/>
<a href="/wiki/test/exam4/${spaceIdx }">예제 4 만들기</a> <BR/>

<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript">
//<![CDATA[
	$(document).ready(function() {
		
	});
//]]>
</script>
</js:scripts>
</body>
 