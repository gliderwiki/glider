<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/admin/include/adminHeader.jsp"%>
<%@taglib prefix="gf" uri="http://www.gliderwiki.org/gftags"%>
<section class="contents " role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">회원 정보관리</h2>
	
			<div class="body-cont">
			
			
				<div class="user has-btn">
					<h3>사용자 조회</h3>
					<div class="btnbox-rt">
						<button type="button" class="btn-down" onclick="javascript:downloadFileServer('user_sample.xlsx');">회원샘플.xls</button>
						<button type="button" class="btn-down" onclick="divOpen()">회원일괄등록</button>
						<button type="button" class="btn-down" id="awayUser">탈퇴회원보기</button>
					</div>
					<div id="excelUpload" style="display:none">
						<form id="frmFile" name="frmFile" method="post" enctype="multipart/form-data">
						<input type="hidden" name="dataType" id="dataType" value="member" />
						<input type="file" size="30" name="file" id="file" />
						<button type="button" class="btn-down" onclick="FileUpload()">확인</button>
						</form>
					</div>
					<br />
					<div id="previewImg">
						
					</div>
					<div class="box-srch">
						<form name="weUser" id="weUser"  method="POST" action="/admin/user">
						<input type="hidden" name="awayYn" id="awayYn" value="" />
							<table>
								<tr>
									<th>닉네임</th>
									<td>
										<input type="text" name="we_user_nick" id="we_user_nick" value="${weUser.we_user_nick }" />
									</td>
								</tr>
								<tr>
									<th>회원명</th>
									<td>
										<input type="text" name="we_user_name" id="we_user_name" value="${weUser.we_user_name }" />
									</td>
								</tr>
								<tr>
									<th>이메일</th>
									<td>
										<input type="text" name="we_user_id" id="we_user_id" value="${weUser.we_user_id }" />
									</td>
								</tr>
								<tr>
									<th>등급별</th>
									<td>
										<select class="select-list" id="search_we_grade" name="search_we_grade">
											<option value="">선택하세요.</option>
											<option value="1">일반사용자</option>
											<option value="3">그룹관리자</option>
											<option value="8">사이트운영자</option>
											<option value="9">시스템어드민</option>
										</select>
									</td>
								</tr>
								<tr>
									<th>그룹별</th>
									<td>
										<select class="select-list" id="search_we_group_idx" name="search_we_group_idx">
										<c:choose>
										<c:when test="${groupListSize eq 0}">
											<option value="">선택하세요.</option>
										</c:when>
										<c:otherwise>
											<option value="">선택하세요.</option>
											<c:forEach items="${groupList }" var="groupList" varStatus="i">
											<option value="${groupList.weGroupIdx }" title="${groupList.weGroupName }">${groupList.weGroupName } - 관리자:${groupList.weUserNick } </option>
											</c:forEach>
										</c:otherwise>
										</c:choose>
										</select>
									</td>
								</tr>
							</table>
							<button type="submit" class="btn btn-send" id="searchUser">조회</button>
						</form>
					</div>
					<table class="tbl-result">
						<thead>
							<tr>
								<th width="30"><input type="checkbox" id="" value=""/></th>
								<th width="60">순번</th>
								<th>메일주소</th>
								<th>닉네임</th>
								<th width="70">회원명</th>
								<th>가입일</th>
								<th width="50">등급</th>
								<th width="50">포인트</th>
								<th width="50">인증</th>
								<th width="120">구분</th>
							</tr>
						</thead>
						<tbody id="user_body">
							<c:choose>
							<c:when test="${mailListSize eq 0}">
								<tr><td colspan="10">데이터가 없습니다.</td></tr>
							</c:when>
							<c:otherwise>
							<c:forEach items="${mailUserList }" var="mailUserList" varStatus="i">
							<tr class="dtlView">
								<td><input type="checkbox" id="chkUser_${mailUserList.weUserIdx }" value="${mailUserList.weUserIdx }"/></td>
								<td>${mailUserList.weUserIdx }</td>
								<td><a style="cursor:pointer" name="userDetail"  id="userId_${mailUserList.weUserIdx }" title="${mailUserList.weUserIdx }">${mailUserList.weUserId }</a></td>
								<td>${mailUserList.weUserNick }</td>
								<td>${mailUserList.weUserName }</td>
								<td>${gf:articleDate(mailUserList.weUserJoinDate,'yyyy.MM.dd')}</td>
								<td>${mailUserList.weGrade }</td>
								<td>${mailUserList.wePoint }</td>
								<td>${mailUserList.weUserAuthYn }</td>
								<td>${mailUserList.weSendStatus }</td>
							</tr>
							</c:forEach>
							</c:otherwise>
							</c:choose>
							
						</tbody>
					</table>
					<!-- 
					<div class="wrap-btn">
						<a style="cursor:pointer" class="btn" id="sendMail" style="display:none;">가입메일재전송</a>
					</div>
					  -->
					  
					<div class="mem-info" id="mem-info">
						
					</div>
					<div class="wrap-btn">
						<a style="cursor:pointer;display:none;" class="btn" id="memBtn" >회원정보수정</a>
					</div>
				</div>
				
			</div>					
	</div>
</section>

<iframe name="fileDownload" width="0" height="0" frameborder="0"  style="display:hidden"></iframe>

<form id="downloadForm" name="downloadForm" method="post" action="">
<input type="hidden" id="filaName" name="filaName" value="" />
</form>
<iframe name="ifrmView" id="ifrmView" frameborder="0" scrolling="no" hspace="0" vspace="0"></iframe>


<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/interface/AdminGroupService.js'></script>
<script type='text/javascript' src='/dwr/interface/AdminUserService.js'></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.dateFormat-1.0.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-loadingbar.js"></script>
<script type="text/javascript" src="/resource/glider/common/wiki.common.util.js"></script>

<script type="text/javascript">
//<![CDATA[
var timer = null;
$(document).ready(function() {
	
	$("#frmFile").ajaxForm(FileuploadCallback);
	$("#frmFile").attr("action","/excelUpload");
	$("#frmFile").submit(function(){return false;});
	
	/**
	 * 02.등급별 조회 
	 */
	$("#search_we_grade").change(function() {
		var weGrade = $("option:selected", this).val();
		$.loadingBar();
		
		clearTimeout(timer); 
		timer = null;	
		timer = setTimeout(function () {  AdminUserService.getUserListByGrade(weGrade,  callBackUserGradeList); }, 500); 	
	
	});

	/**
	 * 03.그룹별 조회 
	 */
	$("#search_we_group_idx").change(function() {
		$.loadingBar();
		var weGroupIdx = $("option:selected", this).val();
		$.loadingBar();
		
		clearTimeout(timer); 
		timer = null;	
		timer = setTimeout(function () {  AdminUserService.getUserListByGroup(weGroupIdx,  callBackUserGradeList); }, 500); 
		
	});

	/**
	 * 04.인증 메일 재전송 
	 */
	$("#saveBtn").click(function() {
		
	});

	/**
	 * 05.회원 강제 탈퇴 
	 */
	$("#we_user_away_ok").click(function() {
		
	});
	
	/**
	 * 06.회원정보 조회 
	 */
	
	$('a[name="userDetail"]').each(function(i) {
		var userIdx = "";
		var attrId = "";
		$(this).bind("click", function(){
			attrId = $(this).attr("id");
			userIdx = $(this).attr("title");
			
			userDetailData(attrId, userIdx);
		});
	});
	
	
	/**
	 * 07.회원정보 수정  
	 */
	$("#memBtn").click(function() {
		var userIdx = $("#we_user_idx").val();				// 아이디 
		var weGrade = $("#we_grade option:selected").val(); // 등급 
		var weTechYn = $("#we_tech_yn").val();				// 전문가여부 
		var wePoint = $("#we_point").val();					// 포인트 
		
		
		
		AdminGroupService.updateUserProfileInfo(userIdx, weGrade, weTechYn, wePoint, callBackUpdateUserInfo);
		
	});
	
	/**
	 * 08. 강제 인증 처리 
	 */
	$("#we_user_auth_ok").click(function() {
		
	});
	
	
	/**
	 * 탈퇴 회원 보기 
	 */
	$("#awayUser").click(function() {
		$("#awayYn").val("Y");
		frm = $('#weUser');
		frm.submit();
	});
	
});

function callBackUserDetail(obj) {
	if(obj != '' && obj != null) {
		var inHtml = "";
		var weUser	    = obj[0];
		var weProfile   = obj[1];
		var attrId   	= obj[2];
		var weGroupList	= obj[3];
		var listSize = weGroupList.length;
		
		$(".tbl-info").remove();
		
		inHtml += "<table class=\"tbl-info\">";
		inHtml += "	   <tr>";
		inHtml += "        <th>아이디</th>";
		inHtml += "        <td>"+weUser.we_user_id+"</td>";
		inHtml += "        <input type=\"hidden\" name=\"we_user_idx\" id=\"we_user_idx\" value=\""+weUser.we_user_idx+"\"/>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>닉네임</th>";
		inHtml += "        <td>"+weUser.we_user_nick+"</td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>회원명</th>";
		inHtml += "        <td>"+weUser.we_user_name+"</td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>가입일</th>";
		inHtml += "        <td>"+$.format.date(weUser.we_user_join_date, "yyyy.MM.dd hh:mm:ss") + "</td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>회원등급</th>";
		inHtml += "        <td>";
		inHtml += "        <select class=\"select-list\" id=\"we_grade\" name=\"we_grade\">";
		if(weProfile.we_grade == '1') {
			inHtml += "            <option value=\"\">선택하세요.</option>";
			inHtml += "            <option value=\"1\" selected>일반사용자</option>";
			inHtml += "            <option value=\"3\">그룹관리자</option>";
			inHtml += "            <option value=\"8\">사이트운영자</option>";
			inHtml += "            <option value=\"9\">시스템어드민</option>";
		} else if(weProfile.we_grade == '3') {
			inHtml += "            <option value=\"\">선택하세요.</option>";
			inHtml += "            <option value=\"1\">일반사용자</option>";
			inHtml += "            <option value=\"3\" selected>그룹관리자</option>";
			inHtml += "            <option value=\"8\">사이트운영자</option>";
			inHtml += "            <option value=\"9\">시스템어드민</option>";
		} else if(weProfile.we_grade == '8') {
			inHtml += "            <option value=\"\">선택하세요.</option>";
			inHtml += "            <option value=\"1\">일반사용자</option>";
			inHtml += "            <option value=\"3\">그룹관리자</option>";
			inHtml += "            <option value=\"8\" selected>사이트운영자</option>";
			inHtml += "            <option value=\"9\">시스템어드민</option>";
		} else if(weProfile.we_grade == '9') {
			inHtml += "            <option value=\"\">선택하세요.</option>";
			inHtml += "            <option value=\"1\">일반사용자</option>";
			inHtml += "            <option value=\"3\">그룹관리자</option>";
			inHtml += "            <option value=\"8\">사이트운영자</option>";
			inHtml += "            <option value=\"9\" selected>시스템어드민</option>";
		} else {
			inHtml += "            <option value=\"\">선택하세요.</option>";
			inHtml += "            <option value=\"1\">일반사용자</option>";
			inHtml += "            <option value=\"3\">그룹관리자</option>";
			inHtml += "            <option value=\"8\">사이트운영자</option>";
			inHtml += "            <option value=\"9\">시스템어드민</option>";
		}
		inHtml += "        </select>";
		inHtml += "        </td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>전문사용자여부</th>";
		inHtml += "        <td>";
		inHtml += "        <select class=\"select-list\" id=\"we_tech_yn\" name=\"we_tech_yn\">";
		if(weProfile.we_tech_yn == 'N') {
			inHtml += "            <option value=\"N\" selected>N</option>";
			inHtml += "            <option value=\"Y\">Y</option>";
		} else if(weProfile.we_tech_yn == 'Y') {
			inHtml += "            <option value=\"N\">N</option>";
			inHtml += "            <option value=\"Y\" selected>Y</option>";
		} else {
			inHtml += "            <option value=\"N\">N</option>";
			inHtml += "            <option value=\"Y\">Y</option>";
		}
		inHtml += "        </select>";
		inHtml += "        </td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>활동포인트</th>";
		inHtml += "        <td><input type=\"text\" name=\"we_point\" id=\"we_point\" value=\""+weProfile.we_point+"\"/></td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>인증여부</th>";
		inHtml += "        <td>";
		if(weUser.we_user_auth_yn == 'N') {
			inHtml += "   미인증 <button type=\"button\" class=\"btn-down\" id=\"we_user_auth_ok\">인증처리</button></td>";
		} else if(weUser.we_user_auth_yn == 'Y') {
			inHtml += "   인증완료";
		}
		inHtml += "        </td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>최종방문일</th>";
		inHtml += "        <td>"+$.format.date(weProfile.we_visit_date, "yyyy.MM.dd hh:mm:ss") +"</td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>탈퇴처리</th>";
		inHtml += "        <td><button type=\"button\" class=\"btn-down\" id=\"weUserAway\">강제탈퇴</button></td>";
		inHtml += "    </tr>";
		inHtml += "	   <tr>";
		inHtml += "        <th>가입그룹목록 </th>";
		inHtml += "        <td>";
		for (var i = 0 ; i < listSize ; i++){
			inHtml += " " + weGroupList[i].we_group_name+"<br/>";
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


function callBackUserGradeList(obj) {
	var inHtml = "";
	if(obj != '') {
		var weUserList = obj;
		listSize = weUserList.length;
		

		$(".dtlView").remove();
		for(var i=0 ; i < listSize ; i++){
			
			inHtml += "<tr class=\"dtlView\">";
			inHtml += "	   <td class=\"check\"><input type=\"checkbox\" id=\"chkUser_"+weUserList[i].weUserIdx+"\" value=\""+weUserList[i].weUserIdx+"\" /></td>";
			inHtml += "	   <td>"+weUserList[i].weUserIdx+"</td>"; 
			inHtml += "	   <td><a href=\"javascript:userDetailData('userId_"+weUserList[i].weUserIdx+ "', "+weUserList[i].weUserIdx+")\" name=\"userDetail\"  id=\"userId_"+weUserList[i].weUserIdx+"\" title=\""+weUserList[i].weUserIdx+"\">"+weUserList[i].weUserId+"</a></td>"; 
			inHtml += "	   <td>"+weUserList[i].weUserNick+"</td>";
			inHtml += "	   <td>"+weUserList[i].weUserName+"</td>";
			inHtml += "	   <td>"+$.format.date(weUserList[i].weUserJoinDate, "yyyy.MM.dd")+"</td>";
			inHtml += "	   <td>"+weUserList[i].weGrade+"</td>";
			inHtml += "	   <td>"+weUserList[i].wePoint+"</td>";
			inHtml += "	   <td>"+weUserList[i].weUserAuthYn+"</td>";
			if(weUserList[i].weSendStatus == null) {
				inHtml += "	   <td><button type=\"button\" class=\"btn-down\" id=\"weUserAway\">강제탈퇴</button></td>";
			} else {
				inHtml += "	   <td>"+weUserList[i].weSendStatus+" <button type=\"button\" class=\"btn-down\" id=\"weUserAway\">강제탈퇴</button></td>";		
			}
			inHtml += "</tr>";
		
		}

		$("#user_body").html(inHtml);
		$.loadingBar.fadeOut();
	} else {
		$(".dtlView").remove();

		inHtml = "<tr><td colspan=\"10\">데이터가 없습니다.</td></tr>";
		$("#user_body").html(inHtml);
		$.loadingBar.fadeOut();
	}
}


function userDetailData(attrId, userIdx) {
	AdminGroupService.getUserProfileInfo(userIdx, attrId,  callBackUserDetail);
}

/**
 * 07. 회원정보 수정 콜백 
 */
function callBackUpdateUserInfo(obj) {
	if(obj == 1) {
		alert('처리 되었습니다'); 
	} else {
		alert('Error 상태코드 ['+obj+'] : 에러가 발생했습니다. 다시 시도하세요.');
	}
	
}

function downloadFileServer(fileName){
	$('#downloadForm input[name=filaName]').val(fileName);
	$("#downloadForm").attr("target", "fileDownload");
	$("#downloadForm").attr("action", "/common/download").submit();
}	


//파일업로드 show
function divOpen() {
	$('#excelUpload').show();
}
//파일업로드 이벤트
function FileUpload(){
	var IMG_FORMAT = "\.(xlsx|xls)$"; 
	if(!$("#file").val()){
		alert("파일을 선택하세요.");
		$("#file").focus();
		return;
	}
	
    if(!(new RegExp(IMG_FORMAT, "i")).test($("#file").val())) {
	    alert("엑셀 파일만 첨부하실 수 있습니다.   ");
	    return;
    }
	
	//파일전송
	var frm;
	frm = $('#frmFile'); 
	frm.attr("action","/excelUpload");
	frm.submit(); 
						
}

//파일 업로드 콜백 
function FileuploadCallback(data,state){
	//console.log('data : ' + data);
	//console.log('state : ' + state);
	
	$("#file").val("");
	
	var jsonStr = eval(data);
	if(jsonStr != null) {
		if(jsonStr.result == 1 ) {
			
			alert('파일 업로드가 완료되었습니다.');
			var appendPreview = "";
			//console.log("jsonStr.filePath : " + jsonStr.filePath);
			//console.log("jsonStr.thumbPath : " + jsonStr.thumbPath);
			//console.log("jsonStr.thumbName : " + jsonStr.thumbName);
			var fileSrc = jsonStr.filePath+jsonStr.saveFileName;
			appendPreview += "<li id='"+jsonStr.saveFileName+"'>"; 
			appendPreview += jsonStr.realFileName;
			appendPreview += " <a href=\"javascript:delItem('"+jsonStr.fileIndex+"', '"+jsonStr.saveFileName+"');\" title='삭제'>[삭제]</a>";
			appendPreview += " | " + jsonStr.fileSize;
			appendPreview += "</li>";
			
			//$("#previewImg").append(appendPreview);
		} else {
			alert(jsonStr.msg);
		
		}
	} else {
		alert('에러가 발생하였습니다.');
		
	}
}

function delItem(fileIndex, fileName) {
	//console.log("fileIndex : " + fileIndex);
	//console.log("fileName : " + fileName);
	// DB에서 이미지 삭제 처리함. 
	
	// 아이 프레임에서 이미지 삭제 
	hiddenItem();	
}

/**
* 이미지 삭제 버튼 후 아이프레임에 이미지 삭제 
*/
function hiddenItem() {
	oFrame = document.getElementById('ifrmView').contentWindow.document;
	oFrame.open();
	oFrame.close();
	ifrmView.document.write(""); 
}
//]]>
</script>

<%@include file="/WEB-INF/views/admin/include/adminFooter.jsp"%>