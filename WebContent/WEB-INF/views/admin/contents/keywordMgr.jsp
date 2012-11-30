<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/admin/include/adminHeader.jsp"%>
<%@taglib prefix="gf" uri="http://www.gliderwiki.org/gftags"%>
<c:set var="startRow">0</c:set>

<section class="contents" role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">키워드 관리</h2>
	
			<div class="body-cont">
			
				<div class="srch">
					<h3>최근 등록된 키워드 목록 </h3>	
					
						<table class="tbl-result-top">
							<thead>
								<tr>
									<th width="30"><input type="checkbox" /></th>
									<th width="50">번호</th>
									<th>위키명</th>
									<th width="80">키워드명</th>
									<th width="100">등록일</th>
									<th width="110">구분</th>
								</tr>
							</thead>
							<tbody  id="keyword_body">
								<c:choose>
								<c:when test="${keywordSize  eq 0}">
									<tr><td colspan="6">데이터가 없습니다.</td></tr>
								</c:when>
								<c:otherwise>
								<c:forEach items="${keywordList }" var="keywordList" varStatus="i">
								<c:if test="${i.count <=  limit }">  
								<c:set var="startRow" value="${startRow+1}" />
								<tr class="dtlView" id="keyword_list_${keywordList.weWikiTagIdx }">
									<td><input type="checkbox" id="chkUser_${keywordList.weWikiTagIdx}" value="${keywordList.weWikiTagIdx}"/></td>
									<td>${keywordList.weWikiTagIdx }</td>
									<td style="text-align:left">${keywordList.weWikiTitle  } (ver.${keywordList.weWikiRevision  })</td>
									<td>${keywordList.weTag} </td>
									<td>${gf:articleDate(keywordList.weInsDate,'yyyy.MM.dd')}</td>
									<td><button type="button" class="btn-down" name="deleteKeyword" id="deleteKeywor_${keywordList.weWikiTagIdx }" title="${keywordList.weWikiTagIdx}">키워드삭제</button></td>
								</tr>
								</c:if>
								</c:forEach>
								
								</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					
				</div>
				
				<div class="wrap-btn" id="limit_btn">				
				
					<a style="cursor:pointer;" class="btn" id="getData" title="${startRow }">더보기</a> 
				
				</div>
					
			</div>	
		</div>			
</section>

<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/interface/AdminKeywordService.js'></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.dateFormat-1.0.js"></script>
<script type="text/javascript">
//<![CDATA[
$(document).ready(function() {
	/**
	 * 01. 키워드  삭제 
	 */
	$('button[name]="deleteKeyword"').each(function(index){
		
		$(this).click(function(){
			if(confirm('등록된 키워드를 삭제하시겠습니까?')) {
				var attrId     = $(this).attr("id");
				var weWikiTagIdx  = $(this).attr("title");
				var weUserIdx = 9;
				AdminKeywordService.deleteKeyword(weWikiTagIdx, weUserIdx, callBackDeleteKeyword);
			} else {
				return;
			}
		});	
		
		
	});

	/**
	 * 02. 추가 데이터를 가져온다. 
	 */
	$("#getData").click(function(){
		var startRow = $(this).attr("title");
		var endRow = "${fetch_limit_row}";
		AdminKeywordService.getMoreKeyword(startRow, endRow, callBackMoreData);
	});
});

function callBackDeleteKeyword(obj) {
	if(obj != '' && obj != null) {
		if(obj > 0 ) {
			alert('처리 되었습니다.');
			$(location).attr('href',"/admin/keyword");
		}
	} else {
		alert('에러가 발생하였습니다.');
	}
}

function callBackMoreData(obj) {
	var inHtml = "";
	if(obj != null) {
		var keywordList = obj;
		listSize = keywordList.length;

		for(var i=0 ; i < listSize ; i++){
			inHtml += "<tr class=\"dtlView\" id=\"keyword_list_"+keywordList[i].weWikiTagIdx+"\">";
			inHtml += "	   <td><input type=\"checkbox\" id=\"chkUser_"+keywordList[i].weWikiTagIdx+"\" value=\""+keywordList[i].weWikiTagIdx+"\" /></td>";
			inHtml += "	   <td>"+keywordList[i].weWikiTagIdx+"</td>"; 
			inHtml += "	   <td style=\"text-align:left\">"+keywordList[i].weWikiTitle +" (ver."+keywordList[i].weWikiRevision+")</td>"; 
			inHtml += "	   <td>"+keywordList[i].weTag+"</td>";
			inHtml += "	   <td>"+$.format.date(keywordList[i].weInsDate, "yyyy.MM.dd")+"</td>";
			inHtml += "	   <td><button type=\"button\" class=\"btn-down\" name=\"deleteKeyword\" id=\"deleteKeywor_"+keywordList[i].weWikiTagIdx+"\" title=\""+keywordList[i].weWikiTagIdx+"\">키워드삭제</button></td>";
			inHtml += "</tr>";
		}
		
		$("#keyword_body").append(inHtml);
		var changeRow = parseInt($("#getData").attr("title")) +parseInt(listSize);
		$("#getData").attr('title', changeRow);
	} else {
		alert('결과값 없음');
	}	
}
//]]>
</script>



<%@include file="/WEB-INF/views/admin/include/adminFooter.jsp"%>				