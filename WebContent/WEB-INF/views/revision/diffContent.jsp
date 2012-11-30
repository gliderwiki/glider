<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>
<%@taglib prefix="gf" uri="http://www.gliderwiki.org/gftags"%>
<style type="text/css">
.line {
	text-align: right !important;
	padding: 0 0.5em 0 1em !important;
	white-space : pre !important
}
</style>
<body>

<section class="contents" role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">버전비교 - "${wikiTitle}"</h2>
		
		<div class="body-cont control">
			<div class="diff">
				<div class="box left">
					<div class="box-tit">
						<span class="time">${gf:articleDate(wikiInsDate,'yyyy.MM.dd hh:mm:ss')} </span>
						<span class="ver">v.${wikiRevision}</span>
					</div>
					<div class="cont">
						<table>
							<tbody>	 
								<tr>
									<td class="source" style="vertical-align: top; ">
										${wikiText}
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="box right">
					<div class="box-tit">
						<span class="time">${gf:articleDate(wikiBakInsDate,'yyyy.MM.dd hh:mm:ss')}</span>
						<span class="ver">v.${wikiBakRevision}</span>
					</div>
					<div class="cont">
						<table>
							<tbody>
								<tr>
									<td class="source" style="vertical-align: top; ">
										${wikiBakText}
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="wrap-btn">
					<a id="replace" class="btn-rollback">버전 교체</a>
				</div>
			</div>
		</div>
		<div class="foot-cont">
			<a id="list" class="btn">목 록</a>
		</div>
	</div>
</section>

<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript">
//<![CDATA[
	$(document).ready(function() {
		// 버전 교체
		$("#replace").bind("click", function() {
			var wikiBaseRev = ${wikiBaseRev};
			var wikiRevision = ${wikiRevision};

			if (wikiBaseRev == wikiRevision) {
				GliderWiki.confirm("알림", "v.${wikiBakRevision} 버전으로 교체 하겠습니까?\n확인을 클릭하면 현재 버전이 v.${wikiBakRevision} 버전으로 저장됩니다.", function() {
					$.form.get("/wiki/replace/${wikiIdx}/${wikiRevision}/${wikiBakRevision}");
				});				
			} else {
				GliderWiki.alert("경고", "버전 교체는 최신 버전만 가능합니다.");
			}
		});

		// 목록
		$("#list").bind("click", function() {
			$.form.get("/wiki/engine/${wikiIdx}");
		});
	});
//]]>
</script>
</js:scripts>

</body>
 