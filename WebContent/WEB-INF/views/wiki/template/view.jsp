<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>

<section class="contents " role="main">
	<div class="wrap-cont">

		<h2 class="tit-cont">${template.we_template_name }</h2>

		<div class="body-cont wiki" id="wikiViewArea">
			<article class="viewer">

			${template.we_template_markup }

			
			<br class="br"/><br class="br"/>
			<div class="util" id="utilArea">
				<div class="info">
					<table>
						<tr>
							<th scope="row">작성자</th>
							<th>${userNick}</th>
						</tr>
						<tr>
							<td>작성일</td>
							<td>${gf:articleDate(template.we_ins_date,'yyyy.MM.dd hh:mm:ss')}</td>
						</tr>
					</table>
				</div>
			</div>

			</article>
		</div>

		<div class="foot-cont">
			<a href="/template/list" class="btn">템플릿 목록</a>
		</div>

	</div>
</section>

<js:scripts>
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/interface/WikiFunctionService.js'></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.printArea.js"></script>
<link href="/resource/glider/code/css/shCore.css" rel="stylesheet" type="text/css" />
<link href="/resource/glider/code/css/shThemeDefault.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

//<![CDATA[
    SyntaxHighlighter.all();

	$(document).ready(function() {
		$("#hiddenContents").click(function() {
			$("#contentsLayer").hide();
		});
	});

//]]>
</script>
</js:scripts>
