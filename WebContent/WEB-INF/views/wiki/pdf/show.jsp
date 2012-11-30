<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>
<!DOCTYPE html>
<html lang="ko" xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko">
<head>
<link rel="stylesheet" href="/resource/glider/front/css/pdf_common.css">
<link rel="stylesheet" href="/resource/glider/front/css/wiki.css" />

<link href="/resource/glider/code/css/shCore.css" rel="stylesheet" type="text/css" />
<link href="/resource/glider/code/css/shThemeDefault.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="/resource/libs/plugin/tip-yellow/tip-yellow.css" type="text/css" />
<link rel="stylesheet" href="/resource/libs/plugin/tip-violet/tip-violet.css" type="text/css" />
<link rel="stylesheet" href="/resource/libs/plugin/tip-darkgray/tip-darkgray.css" type="text/css" />
<link rel="stylesheet" href="/resource/libs/plugin/tip-skyblue/tip-skyblue.css" type="text/css" />
<link rel="stylesheet" href="/resource/libs/plugin/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
<link rel="stylesheet" href="/resource/libs/plugin/tip-twitter/tip-twitter.css" type="text/css" />
<link rel="stylesheet" href="/resource/libs/plugin/tip-green/tip-green.css" type="text/css" />
</style>
<script type="text/javascript" src="/resource/libs/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.pieChart.js"></script>
<script type="text/javascript" src="/resource/glider/code/js/shCore.js"></script>
<script type="text/javascript" src="/resource/glider/code/js/shBrushJScript.js"></script>
<script type="text/javascript">
//<![CDATA[

    SyntaxHighlighter.all();

	$(document).ready(function() {
		$("#hiddenContents").click(function() {
			$("#contentsLayer").hide();
		});
		<c:if test="${!empty wikiGraph }">
			<c:forEach begin="1" end="${wikiGraph.we_graph_cnt }" var="i">
			graph${i }();
			</c:forEach>
		</c:if>
	});
//]]>
</script>
</head>
<body>
<section class="contents " role="main">
	<div class="wrap-cont">

		<h2 class="tit-cont">${weWiki.we_wiki_title }</h2>

		<div class="body-cont wiki" id="wikiViewArea">
			<article class="viewer">
			<c:if test="${!empty summaryList}">
				<div class="index" id="contentsLayer">
					<div class="inner">
						<h2><input type="checkbox" id="hiddenContents"> Table of Contents </h2>
						<div class="box">
							<ul>
								<c:forEach items="${summaryList }" var="summaryList">

								<c:if test="${summaryList.we_summary_tag eq 'h1' }">
								<li>
									<a href="#${summaryList.we_summary_title }">${summaryList.we_summary_title }</a>
								</li>
								</c:if>
								<c:if test="${summaryList.we_summary_tag eq 'h2' }">
								<li class="lv2" >
									<a href="#${summaryList.we_summary_title }">${summaryList.we_summary_title }</a>
								</li>
								</c:if>
								<c:if test="${summaryList.we_summary_tag eq 'h3' }">
								<li class="lv3" >
									<a href="#${summaryList.we_summary_title }">${summaryList.we_summary_title }</a>
								</li>
								</c:if>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</c:if>

			${weWiki.we_wiki_markup }

			<c:if test="${!empty noteList or !empty linkList or !empty tagList or !empty fileList}">
			<hr>
			</c:if>

			<div class="foot-info">

			<c:if test="${!empty linkList }">
			<h5 style="padding-bottom:5px">링크 목록</h5>
			<ul>
				<c:forEach items="${linkList }" var="linkList" varStatus="idx">
					<li>${linkList.we_link_text } - ${linkList.we_link_url }</li>
				</c:forEach>
			</ul>
			</c:if>

			<c:if test="${!empty tagList }">
			<h5 style="padding-bottom:5px">관련 키워드</h5>
			<span>
			<c:forEach items="${tagList }" var="tag" varStatus="idx">
			<a href="/space/tag/${tag.we_tag }">${tag.we_tag }</a>
			<c:choose>
				<c:when test="${idx.last}"></c:when>
				<c:otherwise>,</c:otherwise>
			</c:choose>
			</c:forEach>
			</span>
			</c:if>

			<c:if test="${!empty fileList }">
			<h5 style="padding-bottom:10px">첨부파일목록</h5>
			<span>
			<c:forEach items="${fileList }" var="fileList" varStatus="idx">
				<a style="cursor:pointer" name="targetFiles" onclick="javascript:downloadFileServer('${fileList.we_file_idx }', '${fileList.we_file_real_name }');" title="${fileList.we_file_real_name }"><img src="/resource/glider/front/images/file/ic_file_${fn:toUpperCase(fileList.we_file_type)}.jpg"></a> &nbsp;&nbsp;
			</c:forEach>
			</span>
			</c:if>
			</div>

			<c:if test="${!empty noteList }">
			<h5 style="margin-bottom: 0; padding-bottom:5px">주석</h5>
			<div>
			<c:set var="noteCnt" value="0" />
			<c:forEach items="${noteList }" var="noteList" varStatus="idx">
				<c:set var="noteCnt" value="${noteCnt + 1 }" />
				${noteCnt }. <a href="#note-sub-${noteCnt }" ><span id="note-${noteCnt }" name="note-${noteCnt }">${noteList.we_wiki_note_name }</span></a> : ${noteList.we_wiki_note_desc }<br>
			</c:forEach>
			</div>
			</c:if>


			<c:if test="${!empty noteList or !empty linkList or !empty tagList or !empty fileList}">
			<hr>
			</c:if>
		
			</article>
		</div>
		<div class="foot-cont">
		&nbsp;&nbsp;
		</div>	
	</div>
</section>
</body>
</html>