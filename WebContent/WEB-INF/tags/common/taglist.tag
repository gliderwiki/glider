<%@tag language="java" pageEncoding="UTF-8"
%><%@tag body-content="empty"
%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" 
%><%@taglib prefix="gf" uri="http://www.gliderwiki.org/gftags"
%><%@attribute name="taglist" required="true" rtexprvalue="true" type="java.lang.Object" description="태그리스트객체" 
%>
<c:choose>
<c:when test="${empty taglist}">
등록된 태그 목록이 없습니다. 
</c:when>
<c:otherwise>
	<c:forEach items="${taglist}" var="tagVoList" varStatus="stat">	
       <a href="/space/tag/${tagVoList.weTag}" class="tag"><strong>${tagVoList.weTag} (${tagVoList.tagCnt})</strong></a>,
       </c:forEach>	
   </c:otherwise>
</c:choose>	 