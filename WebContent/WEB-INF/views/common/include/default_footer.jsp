<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>

<footer class="footer">
	<div class="wrap-cont">
		<ul class="navi">
			<li>
				<a href="/index" id="home">홈으로...</a>
			</li>
			<li class="bar"> | </li>
			<li>
				<a href="javascript:aboutWiki();">GLiDER™ Wiki</a>
			</li>
			<li class="bar"> | </li>
			<li>
				<a href="javascript:alert('준비중입니다.');"  id="menual">사용메뉴얼</a>
			</li>
			<li class="bar"> | </li>
			<li>
				<a href="javascript:licensePolicy();">라이센스 정책</a>
			</li>
			<li class="bar"> | </li>
			<li>
				<a href="javascript:supportTech();">기술지원 및 교육</a>
			</li>
		</ul>
		<p class="copyright bluelink"><b>Copyright &copy; 2012 <a href="http://www.gliderwiki.org" target="_blank">GLiDERWiki™ OpenSource Team</a></b></p>
	</div>
</footer>


<script type="text/javascript">
//<![CDATA[

function aboutWiki() {
	$.about_glider();
}

function licensePolicy() {
	$.glider_license();
}

function supportTech() {
	$.glider_support();
}

//]]>
</script>


<!-- 로그인했다면 알림바 나오도록 함 -->
<c:if test="${loginUser.guest eq false}">
	<br>
	<%@include file="/WEB-INF/views/common/notification/taskbar.jsp"%>
</c:if>
