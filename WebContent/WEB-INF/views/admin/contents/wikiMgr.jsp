<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/admin/include/adminHeader.jsp"%>
<%@taglib prefix="gf" uri="http://www.gliderwiki.org/gftags"%>

<link rel="stylesheet" href="/resource/glider/front/css/wiki.css" />

<section class="contents" role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">위키 관리</h2>
	
			<div class="body-cont">
			
				<div class="srch has-btn">
					<h3>조회조건</h3>
					<!-- 
					<div class="btnbox-rt">
						<button type="button" class="btn-down" style="width: 110px;">신고위키조회</button>
						<button type="button" class="btn-down" style="width: 110px;">삭제위키조회</button>
					</div>
					 -->	
					<div class="box-srch">
						<form name="weWikiSearch" id="weWikiSearch" method="POST" action="/admin/wiki">
							<table>
							
								<tr>
									<th>작성자</th>
									<td>
										<input type="text" name="we_user_nick" id="we_user_nick" value="${weUserNick }"/>
									</td>
								</tr>
								<tr>
									<th>제목</th>
									<td>
										<input type="text" name="we_wiki_title" id="we_wiki_title" value="${weWikiTitle }" />
									</td>
								</tr>
								<tr>
									<th>내용</th>
									<td>
										<input type="text" name="we_wiki_text" id="we_wiki_text" value="${weWikiText }" />
									</td>
								</tr>
								<tr>
									<th>공간명</th>
									<td>
										<input type="text" name="we_space_name" id="we_space_name" value="${weSpaceName }" />
									</td>
								</tr>
							</table>
							<button type="submit" class="btn btn-submit-wiki">검색</button>
						</form>
					</div>
				</div>
							
				<table class="tbl-result">
					<thead>
						<tr>
							<th width="30"><input type="checkbox" /></th>
							<th width="50">번호</th>
							<th>위키명</th>
							<th width="110">개설자</th>
							<th width="100">개설일</th>
							<th width="60">신고수</th>
							<th width="70">글상태</th>
							<th width="100">구분</th>
						</tr>
					</thead>
					<tbody id="wiki_body">
						<c:choose>
							<c:when test="${wikiSize  eq 0}">
								<tr><td colspan="8">데이터가 없습니다.</td></tr>
							</c:when>
							<c:otherwise>
							<c:forEach items="${searchWikiList }" var="searchWikiList" varStatus="i">
							<tr class="dtlView">
								<td><input type="checkbox" name="chkWiki" id="chkUser_${searchWikiList.we_wiki_idx }" value="${searchWikiList.we_wiki_idx }"/></td>
								<td>${searchWikiList.we_wiki_idx }</td>
								<td style="text-align:left"> <a href="javascript:wikiDetailData('userId_${searchWikiList.we_wiki_idx }', '${searchWikiList.we_wiki_idx }' );" name="spaceDetail" id="userId_${searchWikiList.we_wiki_idx }" title="${searchWikiList.we_wiki_idx }">${searchWikiList.we_wiki_title}</a></td>
								<td>${searchWikiList.we_user_nick  }</td>
								<td>${gf:articleDate(searchWikiList.we_ins_date,'yyyy.MM.dd')}</td>
								<td>${searchWikiList.we_wiki_quota  }</td>
								<td>${searchWikiList.we_wiki_protect  }</td>
								<td><button type="button" class="btn-down" name="viewWiki" id="viewWiki_${searchWikiList.we_wiki_idx }" title="${searchWikiList.we_wiki_idx }">위키내용조회</button></td>
							</tr>
							</c:forEach>
							</c:otherwise>
						</c:choose>					
					</tbody>
				</table>
				
				<div class="wrap-btn">
					<a style="cursor:pointer" class="btn" id="deleteWiki">선택위키삭제</a>
				</div>
				
				<div class="mem-info" id="mem-info">	
					
				</div>
				<div class="wrap-btn">
					<a style="cursor:pointer;display:none" class="btn" id="memBtn">위키정보수정</a>
				</div>
					
			</div>	
		</div>			
</section>		


<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/interface/AdminWikiService.js'></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.dateFormat-1.0.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-preview-layer.js"></script>
<script type="text/javascript">
//<![CDATA[
           

var timer = null;
$(document).ready(function() {
	/**
	 * 01.위키 상세 내용 조회 
	 */
	$('button[name]="viewWiki"').each(function(index){
		$(this).click(function(){
			var attrId     = $(this).attr("id");
			var weWikiIdx  = $(this).attr("title");
			
			$.ajax({
				type:"GET"
				,url:"/admin/preview/"+weWikiIdx
				,data:{"attrId":attrId}
				,dataType:"json"
				,success:function(rtnObj){					
					
					var wikiMarkup = rtnObj.wikiMarkup;
					var wikiTitle = rtnObj.wikiTitle;
					var wikiRevision = rtnObj.wikiRevision;
					var weWikiIdx = rtnObj.weWikiIdx;
					
					if(rtnObj.result == 'SUCCESS'){
						callBackPreview(wikiMarkup, wikiTitle, wikiRevision, weWikiIdx);
					} else {
						alert('에러 - 정보가 존재하지 않습니다.');
					}
				}
			});
		});
	});
	
	/**
	 * 02.체크박스 위키 삭제 
	 */
	$("#deleteWiki").click(function(){
		var wikiIndexs = $('input[name=chkWiki]:checked').map(function() {
		    return this.value;			
		}).get().join(',');	
		var weUserIdx = 9;
		AdminWikiService.arrayDeleteWiki(wikiIndexs, weUserIdx, callBackDeleteWiki);
	});

	/**
	 * 03.위키 상태 변경 
	 */
	$("#memBtn").live("click", function() {	
		 var weUserIdx = 9;
		 var status = $("#we_wiki_protect option:selected").val();
		 var weWikiIdx = $("#wikiIdx").val();
		 AdminWikiService.updateWikiStatus(weWikiIdx, weUserIdx, status, callBackUpdateWiki);
	});

});


/**
 * 위키 내용 조회 
 */
function callBackPreview(wikiMarkup, wikiTitle, wikiRevision, weWikiIdx) {
	$.preview_layer({
		'markup' : wikiMarkup,
		'title'  : wikiTitle, 
		'revision' : wikiRevision,
		'wikiIdx' : weWikiIdx
		
	});
}


function wikiDetailData(attrId, spaceIdx) {
	AdminWikiService.getWikiDetailInfo(spaceIdx, attrId,  callBackWikiDetail);
}


function callBackWikiDetail(obj) {
	if(obj != '' && obj != null) {
		var inHtml = "";
		var weWiki	    = obj[0];
		var weSpace     = obj[1];
		var attrId   	= obj[2];
	
		
		$(".tbl-info").remove();
		
		inHtml += "<table class=\"tbl-info\">";
		inHtml += "	   <tr>";
		inHtml += "        <th>공간명</th>";
		inHtml += "        <td>"+weSpace.we_space_name+"</td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>위키번호</th>";
		inHtml += "        <td>"+weWiki.we_wiki_idx+"</td>";
		inHtml += "        <input type=\"hidden\" name=\"wikiIdx\" id=\"wikiIdx\" value=\""+weWiki.we_wiki_idx+"\"/>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>위키제목</th>";
		inHtml += "        <td>"+weWiki.we_wiki_title+"</td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>개설자</th>";
		inHtml += "        <td>"+weWiki.we_uesr_nick+"</td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>개설일</th>";
		inHtml += "        <td>"+$.format.date(weWiki.we_ins_date, "yyyy.MM.dd hh:mm:ss") + "</td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>버전정보</th>";
		inHtml += "        <td> ver."+weWiki.we_wiki_revision+"</td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>신고건수</th>";
		inHtml += "        <td>"+weWiki.we_wiki_quota+"</td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>공감수</th>";
		inHtml += "        <td>"+weWiki.we_wiki_agree+"</td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>조회수</th>";
		inHtml += "        <td>"+weWiki.we_wiki_view_cnt+"</td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>아이피</th>";
		inHtml += "        <td>"+weWiki.we_user_ip+"</td>";
		//inHtml += "        <td>"+weWiki.we_user_ip+" <button type=\"button\" class=\"btn-down\" name=\"ipBlock\" id=\"ipBlock\" title=\""+weWiki.we_user_ip+"\">아아피차단</button></td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>글상태</th>";
		inHtml += "        <td>";
		inHtml += "        <select class=\"select-list\" id=\"we_wiki_protect\" name=\"we_wiki_protect\">";
		if(weWiki.we_wiki_protect == '기본') {
			inHtml += "            <option value=\"0\" selected>기본</option>";
			inHtml += "            <option value=\"1\">준보호</option>";
			inHtml += "            <option value=\"2\">보호</option>";
			inHtml += "            <option value=\"3\">신고</option>";
			inHtml += "            <option value=\"4\">삭제예정</option>";
			inHtml += "            <option value=\"6\">이의제기</option>";
			inHtml += "            <option value=\"7\">토론</option>";
			inHtml += "            <option value=\"8\">삭제복구</option>";
			inHtml += "            <option value=\"9\">관리자삭제</option>";
		} else if(weWiki.we_wiki_protect == '준보호') {
			inHtml += "            <option value=\"0\">기본</option>";
			inHtml += "            <option value=\"1\" selected>준보호</option>";
			inHtml += "            <option value=\"2\">보호</option>";
			inHtml += "            <option value=\"3\">신고</option>";
			inHtml += "            <option value=\"4\">삭제예정</option>";
			inHtml += "            <option value=\"6\">이의제기</option>";
			inHtml += "            <option value=\"7\">토론</option>";
			inHtml += "            <option value=\"8\">삭제복구</option>";
			inHtml += "            <option value=\"9\">관리자삭제</option>";
		} else if(weWiki.we_wiki_protect == '보호') {
			inHtml += "            <option value=\"0\">기본</option>";
			inHtml += "            <option value=\"1\">준보호</option>";
			inHtml += "            <option value=\"2\" selected>보호</option>";
			inHtml += "            <option value=\"3\">신고</option>";
			inHtml += "            <option value=\"4\">삭제예정</option>";
			inHtml += "            <option value=\"6\">이의제기</option>";
			inHtml += "            <option value=\"7\">토론</option>";
			inHtml += "            <option value=\"8\">삭제복구</option>";
			inHtml += "            <option value=\"9\">관리자삭제</option>";
		} else if(weWiki.we_wiki_protect == '신고') {
			inHtml += "            <option value=\"0\">기본</option>";
			inHtml += "            <option value=\"1\">준보호</option>";
			inHtml += "            <option value=\"2\">보호</option>";
			inHtml += "            <option value=\"3\" selected>신고</option>";
			inHtml += "            <option value=\"4\">삭제예정</option>";
			inHtml += "            <option value=\"6\">이의제기</option>";
			inHtml += "            <option value=\"7\">토론</option>";
			inHtml += "            <option value=\"8\">삭제복구</option>";
			inHtml += "            <option value=\"9\">관리자삭제</option>";
		} else if(weWiki.we_wiki_protect == '삭제예정') {
			inHtml += "            <option value=\"0\">기본</option>";
			inHtml += "            <option value=\"1\">준보호</option>";
			inHtml += "            <option value=\"2\">보호</option>";
			inHtml += "            <option value=\"3\">신고</option>";
			inHtml += "            <option value=\"4\" selected>삭제예정</option>";
			inHtml += "            <option value=\"6\">이의제기</option>";
			inHtml += "            <option value=\"7\">토론</option>";
			inHtml += "            <option value=\"8\">삭제복구</option>";
			inHtml += "            <option value=\"9\">관리자삭제</option>";
		} else if(weWiki.we_wiki_protect == '이의제기') {
			inHtml += "            <option value=\"0\">기본</option>";
			inHtml += "            <option value=\"1\">준보호</option>";
			inHtml += "            <option value=\"2\">보호</option>";
			inHtml += "            <option value=\"3\">신고</option>";
			inHtml += "            <option value=\"4\">삭제예정</option>";
			inHtml += "            <option value=\"6\" selected>이의제기</option>";
			inHtml += "            <option value=\"7\">토론</option>";
			inHtml += "            <option value=\"8\">삭제복구</option>";
			inHtml += "            <option value=\"9\">관리자삭제</option>";
		} else if(weWiki.we_wiki_protect == '토론') {
			inHtml += "            <option value=\"0\">기본</option>";
			inHtml += "            <option value=\"1\">준보호</option>";
			inHtml += "            <option value=\"2\">보호</option>";
			inHtml += "            <option value=\"3\">신고</option>";
			inHtml += "            <option value=\"4\">삭제예정</option>";
			inHtml += "            <option value=\"6\">이의제기</option>";
			inHtml += "            <option value=\"7\" selected>토론</option>";
			inHtml += "            <option value=\"8\">삭제복구</option>";
			inHtml += "            <option value=\"9\">관리자삭제</option>";
		} else if(weWiki.we_wiki_protect == '삭제복구') {
			inHtml += "            <option value=\"0\">기본</option>";
			inHtml += "            <option value=\"1\">준보호</option>";
			inHtml += "            <option value=\"2\">보호</option>";
			inHtml += "            <option value=\"3\">신고</option>";
			inHtml += "            <option value=\"4\">삭제예정</option>";
			inHtml += "            <option value=\"6\">이의제기</option>";
			inHtml += "            <option value=\"7\">토론</option>";
			inHtml += "            <option value=\"8\" selected>삭제복구</option>";
			inHtml += "            <option value=\"9\">관리자삭제</option>";
		} else if(weWiki.we_wiki_protect == '관리자삭제') {
			inHtml += "            <option value=\"0\">기본</option>";
			inHtml += "            <option value=\"1\">준보호</option>";
			inHtml += "            <option value=\"2\">보호</option>";
			inHtml += "            <option value=\"3\">신고</option>";
			inHtml += "            <option value=\"4\">삭제예정</option>";
			inHtml += "            <option value=\"6\">이의제기</option>";
			inHtml += "            <option value=\"7\">토론</option>";
			inHtml += "            <option value=\"8\">삭제복구</option>";
			inHtml += "            <option value=\"9\" selected>관리자삭제</option>";
		} 
		inHtml += "        </select>";
		inHtml += "        </td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>최종수정일</th>";
		inHtml += "        <td>"+$.format.date(weWiki.we_upd_date, "yyyy.MM.dd hh:mm:ss") + "</td>";
		inHtml += "    </tr>";
		inHtml += "</table>";

		$("#mem-info").html(inHtml);
		$("#memBtn").show();
	} else {
		alert('해당 정보가 존재하지 않습니다.');
		return;
	}
}


function callBackDeleteWiki(obj) { 
	if(obj != '') {
		if(obj > 0) {
			alert('처리 되었습니다.');  
			$("#weWikiSearch").submit();
		} else {
			alert('Error 상태코드 ['+obj+'] : 에러가 발생하였습니다. 다시 시도 하세요');
		}
	} else {
		alert('에러가 발생하였습니다.');
	}
}

function callBackUpdateWiki(obj) { 
	if(obj != '') {
		if(obj > 0) {
			alert('처리 되었습니다.');  
		} else {
			alert('Error 상태코드 ['+obj+'] : 에러가 발생하였습니다. 다시 시도 하세요');
		}
	} else {
		alert('에러가 발생하였습니다.');
	}
}
//]]>
</script>	
<%@include file="/WEB-INF/views/admin/include/adminFooter.jsp"%>				