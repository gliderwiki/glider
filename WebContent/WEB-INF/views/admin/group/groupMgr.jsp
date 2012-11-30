<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/admin/include/adminHeader.jsp"%><%@
taglib prefix="gf" uri="http://www.gliderwiki.org/gftags"%>

<section class="contents" role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">그룹관리</h2>
	
			<div class="body-cont">
				<div class="group">
					<div class="box has-btn">
						<h3>그룹생성</h3>	
						<div class="btnbox-rt">
							<button type="button" class="btn-down" style="width: 120px;" onclick="javascript:downloadFileServer('group_sample.xlsx');">그룹관리샘플.xls</button>
							<button type="button" class="btn-down" style="width: 130px;" onclick="javascript:downloadFileServer('group_user_sample.xlsx');">그룹사용자샘플.xls</button>
							<button type="button" class="btn-down" style="width: 100px;" onclick="divOpen('group')">그룹일괄등록</button>
							<button type="button" class="btn-down" style="width: 100px;" onclick="divOpen('groupmember')">사용자일괄등록</button>   
						</div>
						<div id="excelUpload" style="display:none">
							<form id="frmFile" name="frmFile" method="post" enctype="multipart/form-data">
							<input type="hidden" name="dataType" id="dataType" value="" />
							<input type="file" size="30" name="file" id="file" />
							<button type="button" class="btn-down" onclick="FileUpload()">확인</button><br>
							</form>
						</div>
						<div class="box-create">
							<form action="/">
							
								<table>
									<tr>
										<th>그룹명</th>
										<td>
											<input type="text" name="group_name" id="group_name"/>
											<input type="hidden" name="group_idx" id="group_idx"/>
										</td>
									</tr>
									<tr>
										<th>그룹타입</th>
										<td>
											<select id="group_type" class="select-list" title="대분류선택">
												<option value="">선택하세요.</option>
												<option value="0">조직</option>
												<option value="1">사용자 생성 그룹</option>
											</select>
										</td>
									</tr>
									<tr>
										<th>그룹설명</th>
										<td>
											<input type="text" name="group_info" id="group_info"/>
										</td>
									</tr>
									<tr>
										<th>그룹관리자</th>
										<td>
											<input type="text" class="inp-srch" name="group_admin" id="group_admin" disabled="disabled" />
											<input type="hidden" name="group_admin_idx" id="group_admin_idx" />
											<button type="button" id="searchAdmin" class="btn-srch">찾기</button>
										</td>
									</tr>
								</table>
								<div id="action_save">
								<button type="button" class="btn btn-submit"  id="saveBtn" title="저장">그룹생성</button>
								</div>
							</form>
							
							
						</div>
					</div>
				
					<div class="wrap-box">
						<div class="box left"  id="totalGroup">
							<h3>전체그룹</h3>
							<form action="/">
								<table>
									<thead>
										<tr>
											<td class="check"></td>
											<th class="title">그룹명</th>
											<th class="admin">그룹타입</th>
											<th class="admin">관리자</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
										<c:when test="${groupListSize eq 0}">
											<tr><td colspan="4">생성된 그룹이 없습니다.</td></tr>
										</c:when>
										<c:otherwise>
										<c:forEach items="${groupList }" var="groupList" varStatus="i">
										<tr>
											<td class="check">
												<input type="checkbox" name="groupAllCheck" id="checkGroup_${groupList.weGroupIdx }"  value="${groupList.weGroupIdx }" disabled/>
											</td>
											<td class="title"><a href="javascript:groupUserView('${groupList.weGroupIdx }', '${groupList.weGroupName }');">${groupList.weGroupName }</a></td>
											<td class="admin">${groupList.weGroupType }</td>
											<td class="admin">${groupList.weUserNick }</td>
										</tr>
										</c:forEach>
										</c:otherwise>
										</c:choose>
									</tbody>
								</table>
								<div class="wrap-btn">
									<button type="button" class="btn" id="updateGroupInfo">그룹정보수정</button> &nbsp;
									<button type="button" class="btn" id="deleteGroupInfo">선택그룹삭제</button>
								</div>
							</form>
						</div>
						<div class="box right has-btn" id="userList">
							<h3>그룹 사용자</h3>
							<form action="/">
								<div class="btnbox-rt">
									<button type="button" class="btn-add" id="addUserGroup">사용자추가</button>
								</div>
									
								<table id="subMenu">
									<thead>
										<tr>
											<td class="check"></td>
											<th class="tit_id">회원아이디</th> 
											<th class="admin">닉네임</th>
											<th class="admin">적용일</th>
										</tr>
									</thead>
									<tbody id="groupUserList">
										<tr>
											<td colspan="4">그룹에 속한 사용자가 없습니다.</td>
										</tr>
										<!--  
										<tr>
											<td class="check">
												<input type="checkbox" />
											</td>
											<td class="title">공간1</td>
											<td class="admin">이남희</td>
											<td class="admin">이남희</td>
										</tr>
										 -->
										
										
									</tbody>
								</table>
								<div class="wrap-btn">
									<button type="button" class="btn" id="deleteGroupUser">선택사용자삭제</button>
								</div>
							</form>
						</div>
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
<script type="text/javascript" src="/resource/libs/plugin/jquery-group-layer.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.dateFormat-1.0.js"></script>
<script type="text/javascript" src="/resource/glider/common/wiki.common.util.js"></script>

<script type="text/javascript">
//<![CDATA[
$(document).ready(function() {
	
	$("#frmFile").ajaxForm(FileuploadCallback);
	$("#frmFile").attr("action","/excelUpload");
	$("#frmFile").submit(function(){return false;});
	
	/**
	 * 01.그룹 내용 저장 (생성)
	 */
	$("#saveBtn").click(function() {
		var group_name = $("#group_name").val();
		var group_type = $("#group_type option:selected").val(); // 선택한 group_id
		var group_info = $("#group_info").val();
		var group_admin = $("#group_admin").val();			// 아이디 
		var group_admin_idx = $("#group_admin_idx").val();	// 순번
		
		
		var user_idx = "1";				// 등록자 (admin)
		// DWR 호출
		if(checkForm(group_name, group_type, group_info, group_admin, group_admin_idx)) { 
			AdminGroupService.insertGroupInfoDWR(group_name, group_type, group_info, group_admin_idx, user_idx, callBackGroupInfo);		
		}
	});
	
	$("#saveBtn_1").click(function() {
		var group_name = $("#group_name").val();
		var group_type = $("#group_type option:selected").val(); // 선택한 group_id
		var group_info = $("#group_info").val();
		var group_admin = $("#group_admin").val();			// 아이디 
		var group_admin_idx = $("#group_admin_idx").val();	// 순번
		
		
		var user_idx = "1";				// 등록자 (admin)
		// DWR 호출
		if(checkForm(group_name, group_type, group_info, group_admin, group_admin_idx)) { 
			AdminGroupService.insertGroupInfoDWR(group_name, group_type, group_info, group_admin_idx, user_idx, callBackGroupInfo);		
		}
	});


	// 코드 마스터 저장 콜백
	function callBackGroupInfo(obj) {
		if(obj != null) {
			var jResult = eval("("+obj+")");
			if(jResult.rtnResult != 0) {
				// jResult.rtnResult 가 we_group_info.we_group_idx 임 
				alert(jResult.rtnMsg );  // jResult.rtnResult 
				$(location).attr('href',"/admin/group");
			} else {
				alert('Error 상태코드 ['+jResult.rtnStatus+'] : ' + jResult.rtnMsg);
			}
		} else {
			alert('결과값 없음');
		}
	}
	
	/**
	 * 02. 회원 리스트 조회 한다. - 그룹명 클릭  
	 */  
	$("#addUserGroup").click(function() {
		// ajax로 조직사용자 호출하여 json으로 넘겨줌
		var userNick = "";		// 닉네임
		var userEmail = "";		// 이메일
		var userName ="";		// 이름
		var loginUserIdx = "";  // 로그인 유저 Idx
		
		AdminGroupService.getWeUserList(loginUserIdx, userNick, userEmail, userName, callBackWeUserList);
	});
	
	/*
	 * 03. 관리자 찾기 - 찾기 버튼 
	 */
	$("#searchAdmin").click(function() {	
		AdminGroupService.getWeAdminList(callBackWeAdminList);
	});
	
	/*
	 * 04. 그룹정보를 수정한다.  - 그룹 정보 수정 
	 */
	$("#updateGroupInfo").click(function() {
	    var checkGroupIdx = $("input[name=groupAllCheck]:checked").val();
		 
		if(typeof checkGroupIdx == 'undefined') {
		    alert('수정할 그룹명을 클릭하세요.');
		    return;
		}
		 
		AdminGroupService.getGroupInfo(checkGroupIdx, callBackGroupInfoForm);
	});
	
	/*
	 * 05. 그룹정보 수정 저장 - 그룹정보 수정 버튼  
	 */
	$("#updateBtn").live("click", function() {
		var group_name = $("#group_name").val();
		var group_type = $("#group_type option:selected").val(); // 선택한 group_id
		var group_info = $("#group_info").val();
		var group_admin = $("#group_admin").val();			// 아이디 
		var group_admin_idx = $("#group_admin_idx").val();	// 관리자 순번
		var group_idx = $("#group_idx").val();				// 그룹순번 
		
		
		var user_idx = "1";				// 등록자 (admin)
		// DWR 호출
		if(checkForm(group_name, group_type, group_info, group_admin, group_admin_idx)) { 
			AdminGroupService.updateGroupInfoDWR(group_idx, group_name, group_type, group_info, group_admin_idx, user_idx, callBackUpdateGroupInfo);		
		}
	});
	
	/*
	 * 06. 그룹선택 삭제 
	 */ 
	$("#deleteGroupInfo").click(function() {
		
		var checkGroupIdx = $("input[name=groupAllCheck]:checked").val();
		var user_idx = "1";				// 등록자 (admin)
		 
		if(typeof checkGroupIdx == 'undefined') {
			alert('삭제할 그룹명을 클릭하세요.');
			return;
		}
		if(confirm('선택한 그룹을 삭제하겠습니까?')) {
			AdminGroupService.deleteGroupInfoDWR(checkGroupIdx, user_idx, callBackGroupDelete);		
		}		
	});
	 
	/*
	 * 07. 그룹에 속한 사용자 삭제 처리 
	 */
	$("#deleteGroupUser").click(function() {
		var checkGroupIdx = $("input[name=groupAllCheck]:checked").val();
		var user_idx = "1";				// 등록자 (admin)
		 
		if(typeof checkGroupIdx == 'undefined') {
			alert('수정할 그룹명을 클릭하세요.');
			return;
		}
		
    	var checkUsersId = $('input[name=checkUserGroup]:checked').map(function() {
		    return this.value;			
		}).get().join(',');
			
		
		if(checkUsersId == '' || checkUsersId == null) {
		    alert('삭제할 사용자를 선택하세요');
			return;
		}
		
		if(confirm('선택한 사용자를 삭제하시겠습니까?')) {
			var user_idx = "1";			// 등록자 (admin)
	 		 
			AdminGroupService.deleteGroupInUserDWR(checkGroupIdx, checkUsersId, user_idx, callBackGroupUserDelete);
		}
	});
	 
	
});


var checkedGroupName = "";
/**
 * 02.그룹에 속한 사용자 리스트 조회
 */
function groupUserView(grouIdx, groupName) {
	clearForm();						// 폼 클리어
	$("#action_save").html("<button type=\"button\" class=\"btn btn-submit\"  id=\"saveBtn_1\" title=\"저장\">그룹생성</button>");
	
	this.checkedGroupName = groupName;	// 그룹명 세팅 
	
	// 전체 해제 한 후 
	//$("input[name=groupAllCheck]").attr('checked', '');
	$('input[name=groupAllCheck]').each(function(){
		$(this).attr('checked', false); 				// 가져온 체크박스를 미체크상태로
	});
		
	var checker = "checkGroup_"+grouIdx;
	// 선택된 그룹명만 체크 한다 
	$("#"+checker).attr('checked', true);
	
	
	AdminGroupService.getGroupUserList(grouIdx,  callBackUserList);
}

// 그룹에 속한 사용자 조회  콜백
function callBackUserList(obj) {
	var inHtml = "";
	if(obj != '') {
		var weUserList = obj;
		listSize = weUserList.length;

		$(".dtlView").remove();
		for(i=0 ; i < listSize ; i++){
			inHtml += "<tr class=\"dtlView\">";
			inHtml += "	   <td class=\"check\"><input type=\"checkbox\" name=\"checkUserGroup\" id=\"groupUser_"+weUserList[i].weUserIdx+"\" value=\""+weUserList[i].weUserIdx+"\" /></td>";
			inHtml += "	   <td class=\"tit_id\">"+weUserList[i].weUserId+"</td>"; 
			inHtml += "	   <td class=\"admin\">"+weUserList[i].weUserNick+"</td>";
			inHtml += "	   <td class=\"admin\">"+$.format.date(weUserList[i].weInsDate, "yyyy.MM.dd hh:mm:ss")+"</td>";
			inHtml += "</tr>";
		}

		$("#groupUserList").html(inHtml);
	} else {
		$(".dtlView").remove();

		inHtml = "<tr class=\"dtlView\"><td colspan=\"4\">그룹에 속한 사용자가 없습니다.</td></tr>";
		$("#groupUserList").html(inHtml);
	}
}

// 회원 리스트 콜백 
function callBackWeUserList(obj) {
	var weUserList = eval(obj); 
	
	var checkGroupIdx = $("input[name=groupAllCheck]:checked").val();
	if(typeof checkGroupIdx == 'undefined') {
		alert('사용자를 추가할 그룹을 선택하세요.');
		return;
	}
	if(weUserList != null) {
		$.groupLayer({
			'userList' : weUserList,
			'type'     : 'user'
		});	
	}
}

/*
 * 03.그룹에 사용자를 저장한다. 
 */
function addUserListInGroup(arrayCheckId) {
	var checkGroupIdx = $("input[name=groupAllCheck]:checked").val();
	if(confirm(checkedGroupName + '에 선택한 사용자를 추가합니까?')) {
		$.ajax({
			type:"GET"
			,url:"/admin/group/adduser"
			,data:{"arrayCheckId":arrayCheckId,"checkGroupIdx":checkGroupIdx}
			,dataType:"json"
			,success:function(rtnObj){					
				if(rtnObj.result == 'SUCCESS'){
					alert(rtnObj.rtnMsg);
					$.groupLayer.hide();
					AdminGroupService.getGroupUserList(checkGroupIdx,  callBackUserList);
					return;
				} else {
					alert(rtnObj.rtnMsg);
					$.groupLayer.hide();
					return;
				}
			}
		});		
	}	
}

// 레벨이 3이상인 관리자 리스트를 선택한다. 
function callBackWeAdminList(obj) {
	var weUserList = eval(obj);
	
	if(weUserList != null) {
		$.groupLayer({
			'userList'    : weUserList,
			'type'        : 'admin'
 		});	
	}
}
// 리턴 받은 관리자  값을 세팅한다.
function addAdminInGroup(arrayCheckIdx, checkUserId) {
	
	$("#group_admin").val(checkUserId);
	$("#group_admin_idx").val(arrayCheckIdx);
}

// 그룹 정보 수정 폼 적용 콜백 
function callBackGroupInfoForm(obj) {
	
	if(obj != '' && obj != null) {
		var weGroupInfo = obj[0];
		var weUser   	= obj[1];
				
		// 폼 클리어 
		clearForm();
		
		$("#group_name").val(weGroupInfo.we_group_name);
		$("#group_type option:selected").val(); 				// 선택한 group_id
		$("#group_type option[value="+weGroupInfo.we_group_type+"]").attr("selected", "true");
		$("#group_info").val(weGroupInfo.we_group_info);
		$("#group_admin").val(weUser.we_user_nick);				// 아이디 
		$("#group_admin_idx").val(weUser.we_user_idx);			// 회원순번
		$("#group_idx").val(weGroupInfo.we_group_idx);			// 그룹순번
		
		// 버튼 생성 
		$("#action_save").html("<button type=\"button\" class=\"btn btn-submit\" id=\"updateBtn\" title=\"수정\">그룹정보수정</button>");
		
	} else {
		alert('해당 정보가 존재하지 않습니다.');
		return;
	}
}

// 그룹 정보 수정 반영 콜백 
function callBackUpdateGroupInfo(obj) {
	if(obj != null) {
		var jResult = eval("("+obj+")");
		if(jResult.rtnResult == 1) {
			alert(jResult.rtnMsg );  // jResult.rtnResult 
			$(location).attr('href',"/admin/group");
		} else {
			alert('Error 상태코드 ['+jResult.rtnStatus+'] : ' + jResult.rtnMsg);
		}
	} else {
		alert('결과값 없음');
	}
}


// 그룹정보 삭제 반영 콜백 
function callBackGroupDelete(obj) {
	if(obj != null) {
		var jResult = eval("("+obj+")");
		if(jResult.rtnResult == 1) {
			alert(jResult.rtnMsg );  // jResult.rtnResult 
			$(location).attr('href',"/admin/group");
		} else {
			alert('Error 상태코드 ['+jResult.rtnStatus+'] : ' + jResult.rtnMsg);
		}
	} else {
		alert('결과값 없음');
	}
}

/*
 * 그룹의 구성원 삭제 콜백 
 */
function callBackGroupUserDelete( obj) {
	if(obj != null) {
		var jResult = eval("("+obj+")");
		if(jResult.rtnResult == 1) {
			alert(jResult.rtnMsg );  // jResult.rtnResult 
			$(location).attr('href',"/admin/group");
		} else {
			alert('Error 상태코드 ['+jResult.rtnStatus+'] : ' + jResult.rtnMsg);
		}
	} else {
		alert('결과값 없음');
	}	
}

// 폼값 체크 
function checkForm(group_name, group_type, group_info, group_admin, group_admin_idx) {
	if(group_name.trim() == '') {
		alert('그룹명을 선택하세요');
		return false;
	}
	if(group_info == '') {
		alert('그룹설명을 선택하세요');
		return false;
	}
	
	if(group_type == '') {
		alert('그룹타입을 선택하세요');
		return false;
	}
	
	if(group_admin == '' || group_admin_idx == '') {
		alert('사용자 레벨이 최소 3 이상인 관리자를 지정하세요.\n사용자 레벨 조정은 [회원정보관리] 메뉴를 이용하세요.');
		return false;
	}
	return true;
}

// 그룹 생성 폼  클리어 
function clearForm() {
	$("#group_name").val("");
	$("#group_type option[value='']").attr("selected", "true");
	$("#group_info").val("");				// 그룹정보 
	$("#group_admin").val("");				// 아이디 
	$("#group_admin_idx").val("");			// 순번
}

function downloadFileServer(fileName){
	$('#downloadForm input[name=filaName]').val(fileName);
	$("#downloadForm").attr("target", "fileDownload");
	$("#downloadForm").attr("action", "/common/download").submit();
}	

//파일업로드 show
function divOpen(dataType) {
	$('#excelUpload').show();
	$("#dataType").val(dataType);
	//alert($("#dataType").val());
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