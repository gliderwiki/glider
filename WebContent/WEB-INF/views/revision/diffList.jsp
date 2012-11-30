<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>
<%@taglib prefix="gf" uri="http://www.gliderwiki.org/gftags" %>
<body>

<section class="contents " role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">페이지 변경내역</h2>

		<form action="/">
			<div class="body-cont control">
				<h3 class="tit-sec"><em>GLiDER Wiki</em> 페이지</h3>
				<table id="diffList" class="history">
					<colgroup>
						<col style="width:40px;" />
						<col style="width:auto;" />
						<col style="width:120px;" />
						<col style="width:130px;" />
						<col style="width:80px;" />
						<col style="width:100px;" />
					</colgroup>
					<thead>
						<tr>
							<th>
								<span class="ir">선택</span>
							</th>
							<th>내용</th>
							<th>날짜</th>
							<th>수정 사유</th>
							<th>버전</th>
							<th>작성자</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${wikiList}" var="wikiList">
						<tr>
							<td>
								<input name="chk" type="checkbox" />
							</td>
							<td style="text-align: left;">${wikiList.we_wiki_title}</td>
							<td>${gf:articleDate(wikiList.we_ins_date, 'yyyy.MM.dd')}</td>
							<td>${wikiList.we_edit_text}</td>
							<td>${wikiList.we_wiki_revision}</td>
							<td>${wikiList.we_user_nick}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="foot-cont">
				<a id="diff" class="btn">비교하기</a>
			</div>
		</form>
		
	</div>
</section>

<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript">
//<![CDATA[
	$(document).ready(function() {
		$("#diff").bind("click", function() {
			var checked = $("input[name=chk]:checkbox:checked");
			var checkCount = checked.length;
			
			if (checkCount == 2) {
/*
				// 최신버전 위키만 비교한 것을 이전버전 위키로도 비교할 수 있게 수정
				var checkedTr = checked.parent().parent().get(0);
				var checkedTd = $(checkedTr).children();
				var revTd = checkedTd.get(4);
				var revVal = $(revTd).text();

				// 첫번째 선택한 위키는 제일 위에 체크한 위키이어만 한다.
				if (revVal != "${wikiRev}") {
					GliderWiki.alert("경고", "현재 위키 버전(${wikiRev})은 꼭 선택해야 합니다.");
					$("input[name=chk]").attr("checked", false);	// 모든 체크박스 해제

					var firstTr = $("input[name=chk]:first").parent().parent();
					var chkTd = $(firstTr).children().get(0);	// 현재 위키 버전 : 체크박스 선택
					$(chkTd).children().attr("checked", true);
					return false;
				}
*/
				var rev = "";
				checked.parent().parent().each(function() {
					var val = $(this).find("td").get(4);
					rev += "/" + $(val).text();
				});

				$.form.get("/wiki/diff/${wikiIdx}" + rev);
			}
			else {
				GliderWiki.alert("경고", "버전을 비교할 대상 위키 문서를 선택하세요.");
				$("input[name=chk]").attr("checked", false);	// 모든 체크박스 해제
				return false;
			}
		});		
	});
//]]>
</script>
</js:scripts>
</body>
