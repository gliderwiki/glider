<%@tag language="java" pageEncoding="UTF-8"
%><%@tag body-content="empty"
%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@taglib prefix="gf" uri="http://www.gliderwiki.org/gftags"
%><%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%><%@attribute name="wikiList" required="true" rtexprvalue="true" type="java.lang.Object" description="위키리스트객체"
%>
<c:choose> 
<c:when test="${empty wikiList}">
등록된 위키 목록이 없습니다.
</c:when>
<c:otherwise>
	<c:forEach items="${wikiList}" var="list" varStatus="stat">
   		<li>
			<div class="title">
				<a href="/wiki/${list.we_wiki_idx}">${gf:cut(list.we_wiki_title,35)}</a>
			</div>
			<div class="info">
				조회 ${list.we_wiki_view_cnt}
			</div>
		</li>
     </c:forEach>
</c:otherwise>
</c:choose>