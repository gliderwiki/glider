<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/admin/include/adminHeader.jsp"%>
<%@taglib prefix="gf" uri="http://www.gliderwiki.org/gftags"%>

<section class="contents " role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">공간 관리</h2>
	
			<div class="body-cont">
			
				<div class="srch">
					<h3>조회조건</h3>	
					<div class="box-srch">
					
						<form name="weSpace" method="POST" action="/admin/space">
							<table>
								<tr>
									<th>최근개설공간</th>
									<td>
										<select id="spaceInfoList" title="공간정보목록" STYLE="width:400px;">
											<option value="">최근 한달간 개설된 공간 목록</option>
											<c:forEach items="${spaceList }" var="spaceList" varStatus="stat">
											<option value="${spaceList.we_space_idx}">${spaceList.we_space_name }</option>
									        </c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<th>관리자명</th>
									<td>
										<input type="text" class="inp-srch" style="width:430px" name="we_admin_nick" id="we_admin_nick" value="${weSpace.we_space_admin_nick }" disabled="disabled" />
										<input type="hidden" name="we_admin_idx" id="we_admin_idx" />
										<button type="button" id="searchAdmin" class="btn-srch">찾기</button>
									</td>
								</tr>
								<tr>
									<th>공간명</th>
									<td>
										<input type="text" name="we_space_name" id="we_space_name" value="${weSpace.we_space_name }"/>
									</td>
								</tr>
								
							</table>
							<button type="submit" class="btn btn-submit" id="searchSpace">검색</button>
						</form>
					</div>
					
					<table class="tbl-result">
						<thead>
							<tr>
								<th width="30"><input type="checkbox" /></th>
								<th width="60">공간번호</th>
								<th>공간명</th>
								<th width="120">관리자</th>
								<th width="130">개설일</th>
								<th width="120">구분</th>
							</tr>
						</thead>
						<tbody id="space_body">
							<c:choose>
							<c:when test="${spaceSize  eq 0}">
								<tr><td colspan="6">데이터가 없습니다.</td></tr>
							</c:when>
							<c:otherwise>
							<c:forEach items="${spaceSearchList }" var="spaceSearchList" varStatus="i">
							<tr class="dtlView" id="space_list_${spaceSearchList.we_space_idx }">
								<td><input type="checkbox" id="chkUser_${spaceSearchList.we_space_idx }" value="${spaceSearchList.we_space_idx }"/></td>
								<td>${spaceSearchList.we_space_idx }</td>
								<td style="text-align:left"> <a href="javascript:spaceDetailData('userId_${spaceSearchList.we_space_idx }', '${spaceSearchList.we_space_idx }' );" name="spaceDetail" id="userId_${spaceSearchList.we_space_idx }" title="${spaceSearchList.we_space_idx }">${spaceSearchList.we_space_name}</a></td>
								<td>${spaceSearchList.we_space_admin_nick  }</td>
								<td>${gf:articleDate(spaceSearchList.we_ins_date,'yyyy.MM.dd hh:mm:ss')}</td>
								<td><button type="button" class="btn-down" name="deleteSpace" id="deleteSpace_${spaceSearchList.we_space_idx }" title="${spaceSearchList.we_space_idx }">공간삭제</button></td>
							</tr>
							</c:forEach>
							</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					
					<div class="mem-info" id="mem-info">
												
					</div>
					<div class="wrap-btn">
						<a style="cursor:pointer;display:none" class="btn" id="memBtn">공간정보수정</a>
					</div>
					
				</div>
				
			</div>					
	</div>
</section>

<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/interface/AdminGroupService.js'></script>
<script type='text/javascript' src='/dwr/interface/AdminSpaceService.js'></script>
<script type='text/javascript' src='/dwr/interface/CommonService.js'></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-group-layer.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.dateFormat-1.0.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-loadingbar.js"></script>
<script type="text/javascript">
//<![CDATA[
var timer = null;
$(document).ready(function() {
	/**
	 * 01.최근 개설한 공간 목록 조회 
	 */
	$("#spaceInfoList").change(function() {
		var spaceIdx = $("option:selected", this).val(); // 선택한 공간번호 
		
		// DWR 호출
		if(spaceIdx != '') {
			// 키 기반으로 공간정보를 조회한다.
			AdminSpaceService.getSpaceDetailInfo(spaceIdx, '',  callBackSpaceInfo);
			
		}
	});

	/*
	 * 02. 관리자 찾기 - 찾기 버튼 
	 */
	$("#searchAdmin").click(function() {	
		AdminGroupService.getWeAdminList(callBackWeAdminList);
	});
	
	/**
	 * 03. 관리자 변경 버튼 
	 */
	 
	$("#chgSpaceAdmin").live("click", function() {	
	    AdminGroupService.getWeAdminList(callBackWeSpaceAdmin);
	});
	
	/**
	 * 04.공간정보 수정 
	 */
	$("#memBtn").live("click", function() {	
		var spaceIdx 	  = $("#space_idx").val();
		var spaceName 	  = $("#space_name").val();
		var spaceDesc	  = $("#space_desc").val();
		var spaceAdminIdx = $("#space_admin_idx").val();
		var spaceExposed  = $("#space_exposed option:selected").val();
		var weUserIdx     = 9;
		
		AdminSpaceService.updateSpaceByAdmin(spaceIdx, spaceName, spaceDesc, spaceAdminIdx, spaceExposed, weUserIdx, callBackUpdateSpaceAdmin);
	});
	
	/**
	 * 05. 공간정보 삭제 
	 */
	$('button[name]="deleteSpace"').each(function(index){
		$(this).click(function(){
			var attrId     = $(this).attr("id");
			var weSpaceIdx  = $(this).attr("title");
			var weUserIdx = 9;
			AdminSpaceService.deleteSpaceInfo(weSpaceIdx,  weUserIdx, callBackDeleteSpace);
		});
	});

	
});

/**
 * 01.최근 개설한 공간 목록 조회  콜백 
 */
function callBackSpaceInfo(obj) {	
	var inHtml = "";
	if(obj != '') {
		var weSpace	    = obj[0];
		var weWikiList  = obj[1];
		var attrId   	= obj[2];
		var viewType   	= obj[3];
		var editType   	= obj[4];

		$(".dtlView").remove();
	
		
		inHtml += "<tr class=\"dtlView\" id=\"space_list_"+weSpace.we_space_idx+"\">";
		inHtml += "	   <td class=\"check\"><input type=\"checkbox\" id=\"chkUser_"+weSpace.we_space_idx+"\" value=\""+weSpace.we_space_idx+"\" /></td>";
		inHtml += "	   <td>"+weSpace.we_space_idx+"</td>"; 
		inHtml += "	   <td style=\"text-align:left\"> <a href=\"javascript:spaceDetailData('userId_"+weSpace.we_space_idx+ "', "+weSpace.we_space_idx+")\" name=\"spaceDetail\"  id=\"userId_"+weSpace.we_space_idx+"\" title=\""+weSpace.we_space_idx+"\">"+weSpace.we_space_name+"</a></td>"; 
		inHtml += "	   <td>"+weSpace.we_space_admin_nick+"</td>";
		inHtml += "	   <td>"+$.format.date(weSpace.we_ins_date, "yyyy.MM.dd hh:mm:ss")+"</td>";
		inHtml += "	   <td><button type=\"button\" class=\"btn-down\" id=\"weUserAway\">공간삭제</button></td>";
		inHtml += "</tr>";
		
		$("#space_body").html(inHtml);
		$.loadingBar.fadeOut();
	} else {
		$(".dtlView").remove();
		inHtml = "<tr><td colspan=\"6\">데이터가 없습니다.</td></tr>";
		$("#space_body").html(inHtml);
		$.loadingBar.fadeOut();
	}
	
}

/*
 * 02. 관리자 찾기 - 찾기 버튼 액션 
 */
function callBackWeAdminList(obj) {
	var weUserList = eval(obj);
	
	if(weUserList != null) {
		$.groupLayer({
			'userList'    : weUserList,
			'type'        : 'admin'
 		});	
	}
}
/*
 * 03. 관리자 변경 - 찾기 버튼 액션 
 */
function callBackWeSpaceAdmin(obj) {
	var weUserList = eval(obj);
	
	if(weUserList != null) {
		$.groupLayer({
			'userList'    : weUserList,
			'type'        : 'space'
 		});	
	}
}


// 02. 리턴 받은 관리자  값을 세팅한다. - 관리자 찾기 
function addAdminInGroup(arrayCheckIdx, checkUserId) {
	$("#we_admin_nick").val(checkUserId);
	$("#we_admin_idx").val(arrayCheckIdx);
}

// 03. 리턴 받은 관리자  값을 세팅한다. - 관리자 변경 
function addAdminInSpace(arrayCheckIdx, checkUserId) {
	$("#space_admin_id").val(checkUserId);
	$("#space_admin_idx").val(arrayCheckIdx);
}

/**
 * 공간 정보 조회
 */
function spaceDetailData(attrId, spaceIdx) {
	AdminSpaceService.getSpaceDetailInfo(spaceIdx, attrId,  callBackSpaceDetail);
}

function callBackSpaceDetail(obj) {
	if(obj != '' && obj != null) {
		var inHtml = "";
		var weSpace	    = obj[0];
		var weWikiList  = obj[1];
		var attrId   	= obj[2];
		var viewType   	= obj[3];
		var editType   	= obj[4];

		var listSize = weWikiList.length;
		
		
		
		$(".tbl-info").remove();
		
		inHtml += "<table class=\"tbl-info\">";
		inHtml += "	   <tr>";
		inHtml += "        <th>공간명</th>";
		inHtml += "        <td><input type=\"text\" name=\"space_name\" id=\"space_name\" value=\""+weSpace.we_space_name+"\"/></td>";
		inHtml += "        <input type=\"hidden\" name=\"space_idx\" id=\"space_idx\" value=\""+weSpace.we_space_idx+"\"/>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>상세설명</th>";
		inHtml += "        <td><textarea id=\"space_desc\" name=\"space_desc\" rows=\"7\" cols=\"80\">"+weSpace.we_space_desc+"</textarea> </td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>조회권한</th>";
		inHtml += "        <td>"+viewType+"</td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>수정권한</th>";
		inHtml += "        <td>"+editType+"</td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>관리자</th>";
		inHtml += "        <td>";
		inHtml += "        <input type=\"text\" name=\"space_admin_id\" id=\"space_admin_id\" value=\""+weSpace.we_space_admin_nick+"\"/><button type=\"button\" class=\"btn-down\" id=\"chgSpaceAdmin\">관리자변경</button>";
		inHtml += "        <input type=\"hidden\" name=\"space_admin_idx\" id=\"space_admin_idx\" value=\""+weSpace.we_admin_idx+"\"/>";
		inHtml += "        </td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>정보노출여부</th>";
		inHtml += "        <td>";
		inHtml += "        <select class=\"select-list\" id=\"space_exposed\" name=\"space_exposed\">";
		if(weSpace.we_space_exposed == 'N') {
			inHtml += "            <option value=\"N\" selected>N</option>";
			inHtml += "            <option value=\"Y\">Y</option>";
		} else {
			inHtml += "            <option value=\"Y\" selected>Y</option>";
			inHtml += "            <option value=\"N\">N</option>";
		}
		inHtml += "        </select>";
		inHtml += "        </td>";		
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>개설일</th>";
		inHtml += "        <td>"+$.format.date(weSpace.we_ins_date, "yyyy.MM.dd hh:mm:ss") + "</td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>최종수정일</th>";
		inHtml += "        <td>"+$.format.date(weSpace.we_upd_date, "yyyy.MM.dd hh:mm:ss") + "</td>";
		inHtml += "    </tr>";
		
		inHtml += "	   <tr>";
		inHtml += "        <th>개설위키목록 </th>";
		inHtml += "        <td>";
		for (var i = 0 ; i < listSize ; i++){
			inHtml += " " + weWikiList[i].we_wiki_title+" ("+weWikiList[i].we_user_nick+")<br/>";
		}
		inHtml += "        </td>";
		inHtml += "    </tr>";
		inHtml += "</table>";

		$("#mem-info").html(inHtml);
		$("#memBtn").show();
	} else {
		alert('해당 정보가 존재하지 않습니다.');
		return;
	}
}

/**
 * 04. 공간정보 수정 
 */
function callBackUpdateSpaceAdmin(obj){
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

function callBackDeleteSpace(obj) {
	if(obj != '' && obj != null) {
		var inHtml = "";
		var result	    = obj[0];
		var spaceIdx    = obj[1];
		
		if(result > 0 ) {
			alert('처리 되었습니다.');
			$("#space_list_"+spaceIdx).remove();
		}
	} else {
		alert('에러가 발생하였습니다.');
	}
}
//]]>
</script>

<%@include file="/WEB-INF/views/admin/include/adminFooter.jsp"%>