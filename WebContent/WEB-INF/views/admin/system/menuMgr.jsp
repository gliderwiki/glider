<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/admin/include/adminHeader.jsp"%><%@
taglib prefix="gf" uri="http://www.gliderwiki.org/gftags"%>

<section class="contents" role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">메뉴관리</h2>
	
			<div class="body-cont">
				<div class="group">
					<div class="box has-btn">
						<h3>메뉴생성</h3>	
						
						<div class="box-create">
							<form action="/">
							
								<table>
									<tr>
										<th>메뉴명</th>
										<td>
											<input type="text" name="we_menu_name" id="we_menu_name"/>
											<input type="hidden" name="we_menu_idx" id="we_menu_idx"/>
										</td>
									</tr>
									<tr>
										<th>메뉴타입</th>
										<td>
											<select id="menu_type" class="select-list" title="대분류선택">
												<option value="">선택하세요.</option>
												<option value="S">시스템</option>
												<option value="M">메인메뉴</option>
												<option value="T">타이틀메뉴</option>
											</select>
										</td>
									</tr>
									<tr>
										<th>메뉴주소</th>
										<td>
											<input type="text" name="we_menu_url" id="we_menu_url"/>
											
										</td>
									</tr>
									<tr>
										<th>정렬순서</th>
										<td>
											<input type="text" class="inp-srch" name="we_menu_order_idx" id="we_menu_order_idx"/>
										</td>
									</tr>
									<tr>
										<th>접근레벨</th>
										<td>
											<select id="access_level" class="select-list" title="접근레벨">
												<option value="0">선택하세요.</option>
												<option value="1">일반사용자</option>
												<option value="3">그룹관리자</option>
												<option value="8">사이트운영자</option>
												<option value="9">시스템어드민</option>
											</select>
										</td>
									</tr>
								</table>
								<div id="action_save">
								<button type="button" class="btn btn-submit"  id="saveBtn" title="저장">메뉴생성</button>
								</div>
							</form>
						</div>
					</div>
				
					<div class="wrap-box">
						<div class="box left has-btn"  id="totalGroup">
							<h3>전체메뉴 조회</h3>
							<form action="/">
								<div class="btnbox-rt">
									<select id="menuType" title="메뉴 조회" STYLE="width:200;">
										<option value="">조회할 메뉴를 선택하세요.</option>
										<option value="S">시스템</option>
										<option value="M">메인메뉴</option>
										<option value="T">타이틀메뉴</option>
									</select>
								</div>
								
								<table>
									<thead>
										<tr>
											<td class="check"></td>
											<th style="width:140px;text-align:center">메뉴명</th>
											<th style="text-align:center">메뉴주소</th>
											<th style="width:80px;text-align:center">순서</th>
										</tr>
									</thead>
									<tbody  id="menuTitle">
										<c:choose>
										<c:when test="${empty menuList}">
											<tr><td colspan="4">생성된 메뉴가 없습니다.</td></tr>
										</c:when>
										<c:otherwise>
											<c:forEach items="${menuList }" var="menuList" varStatus="i">
											<tr>
												<td class="check">
													<input type="checkbox" name="menuCheck" id="menuCheck${menuList.we_menu_idx }"  value="${menuList.we_menu_idx }" disabled/>
												</td>
												<td style="text-align:left"><a href="javascript:subMenuView('${menuList.we_menu_idx }', '${menuList.we_menu_name }')">${menuList.we_menu_name }</a></td>
												<td style="text-align:left">${menuList.we_menu_url }</td>
												<td>${menuList.we_menu_order_idx }</td>
											</tr>
											</c:forEach>
										</c:otherwise>
										</c:choose>
									</tbody>
								</table>
								<div class="wrap-btn">
									<button type="button" class="btn" id="updateMenuInfo">메뉴수정</button> &nbsp;
									<button type="button" class="btn" id="deleteMenuInfo">메뉴삭제</button>
								</div>
							</form>
						</div>
						<div class="box right has-btn" id="userList">
							<h3>서브 메뉴관리</h3>
							<form action="/">
								<!-- 
								<div class="btnbox-rt">
									<button type="button" class="btn-add" id="addUserGroup">서브메뉴추가</button>
								</div>
								 -->
								 
								<table id="subMenu" >
									<thead>
										<tr>
											<td class="check"></td>
											<th style="text-align:center">순서</th> 
											<th style="width:150px;text-align:center">메뉴명</th>
											<th style="width:180px;text-align:center">메뉴주소</th>
										</tr>
									</thead>
									<tbody id="subMenuTitle">
										
									</tbody>
								</table>
								<div class="wrap-btn">
								<!-- 
									<button type="button" class="btn" id="updateSubmenu">선택메뉴수정</button> &nbsp;
									<button type="button" class="btn" id="deleteSubmenu">선택메뉴삭제</button>
								 -->
								</div>
							</form>
						</div>
					</div>
				</div>	
	
		</div>			
	</div>
</section>		


<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/interface/AdminMenuService.js'></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-group-layer.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.dateFormat-1.0.js"></script>
<script type="text/javascript" src="/resource/glider/common/wiki.common.util.js"></script>

<script type="text/javascript">
//<![CDATA[
$(document).ready(function() {
	
	/**
	 * 01.메뉴 저장  : 메인 메뉴 저장 
	 */
	$("#saveBtn").click(function() {
		var menu_name = $("#we_menu_name").val();
		var menu_type = $("#menu_type option:selected").val();  // 선택한  메뉴 타입 
		var menu_url = $("#we_menu_url").val();					// 메뉴 주소 
		var menu_order_idx = $("#we_menu_order_idx").val();  		// 메뉴 정렬 순서 
		var access_level = $("#access_level option:selected").val();  // 접근레벨 
		
		// DWR 호출
		if(menu_type != '' && menu_name != '' && menu_url != '') {
			AdminMenuService.insertMenuDWR(menu_name, menu_type, menu_url, menu_order_idx, access_level, callBackMenuList);
		}
	});


	// 메뉴 저장 콜백
	function callBackMenuList(obj) {
		if(obj != null) {
			var jResult = eval("("+obj+")");
			if(jResult.rtnResult != 0) {
				alert(jResult.rtnMsg);
				$(location).attr('href',"/admin/menu");
			} else {
				alert('Error 상태코드 ['+jResult.rtnStatus+'] : ' + jResult.rtnMsg);
			}
		} else {
			alert('결과값 없음');
		}
	}
	
	
	/**
	 * 02.메뉴 타입조회 : 전체 메뉴 타입별 조회 
	 */
	$("#menuType").click(function() {
		var menuType = $("#menuType option:selected").val(); // 선택한 group_id
		// DWR 호출
		if(menuType != '') {
			AdminMenuService.getWeMenuTypeDWR(menuType, callBackMenuTypeList);
		}
	});

	// 코드 마스터 저장 콜백
	function callBackMenuTypeList(obj) {
		if(obj != null && obj != '') {
			var weMenu = obj;
			listSize = weMenu.length;
			var inHtml = "";

			for(i=0 ; i < listSize ; i++){
				inHtml += "<tr>";
				inHtml += "	   <td class=\"check\">";
				inHtml += "    <input type=\"checkbox\" name=\"menuCheck\" id=\"menuCheck"+weMenu[i].we_menu_idx+"\" value=\""+weMenu[i].we_menu_idx+"\" disabled/>";
				inHtml += "    </td>";
				inHtml += "    <td style=\"text-align:left\"><a href=\"javascript:subMenuView('"+weMenu[i].we_menu_idx+"', '"+weMenu[i].we_menu_name+"')\">"+weMenu[i].we_menu_name+"</a></td>";
				inHtml += "    <td style=\"text-align:left\">"+weMenu[i].we_menu_url+"</td>";
				inHtml += "    <td style=\"text-align:center\">"+weMenu[i].we_menu_order_idx+"</td>";
				inHtml += "</tr>";
			}
			
			
			$("#menuTitle").children().remove();
			$("#menuTitle").html(inHtml);

		} else {
			alert('조회된 결과값이 없습니다.');
		}
	}

	
	/*
	 * 03. 메뉴정보를 수정한다. 
	 */
	$("#updateMenuInfo").click(function() {
	    var checkMenuIdx = $("input[name=menuCheck]:checked").val();
		 
		if(typeof checkMenuIdx == 'undefined') {
		    alert('수정할 메뉴명을 클릭하세요.');
		    return;
		}
		 
		AdminMenuService.getMenuInfo(checkMenuIdx, callBackMenuInfoForm);
	});
	

	/*
	 * 04. 메뉴선택 삭제 
	 */ 
	$("#deleteMenuInfo").click(function() {
		var checkMenuIdx = $("input[name=menuCheck]:checked").val();
		 
		if(typeof checkMenuIdx == 'undefined') {
		    alert('삭제할  메뉴명을 클릭하세요.');
		    return;
		}
		 
		if(confirm('선택한 메뉴를 삭제하겠습니까?')) {
			AdminMenuService.deleteMenuInfoDWR(checkMenuIdx, callBackMenuList);		
		}		
	});
	
	/*
	 * 05. 메뉴선택 수정 
	 */ 
	$("#updateBtn").live("click", function() {
		var menu_name = $("#we_menu_name").val();
		var menu_type = $("#menu_type option:selected").val();  // 선택한  메뉴 타입 
		var menu_url = $("#we_menu_url").val();					// 메뉴 주소 
		var menu_order_idx = $("#we_menu_order_idx").val();  		// 메뉴 정렬 순서 
		var access_level = $("#access_level option:selected").val();  // 접근레벨 
		var menu_idx = $("#we_menu_idx").val();
		// DWR 호출
		if(menu_type != '' && menu_name != '' && menu_url != '') {
			AdminMenuService.updateMenuDWR(menu_idx, menu_name, menu_type, menu_url, menu_order_idx, access_level, callBackMenuList);
		}
	});
	
	/*
	 * 06. 서브 메뉴정보를 수정한다. 
	 */
	$("#updateSubmenu").click(function() {
	    var checkMenuIdx = $("input[name=subMenuCheck]:checked").val();
		 
		if(typeof checkMenuIdx == 'undefined') {
		    alert('수정할 메뉴를 선택하세요.');
		    return;
		}
		 
		//AdminMenuService.getMenuInfo(checkMenuIdx, callBackMenuInfoForm);
	});
	
	/*
	 * 07. 서브 메뉴정보를 삭제한다. 
	 */
	$("#deleteSubmenu").click(function() {
	    var checkMenuIdx = $("input[name=subMenuCheck]:checked").val();
		 
		if(typeof checkMenuIdx == 'undefined') {
		    alert('삭제할 메뉴를 선택하세요.');
		    return;
		}
		 
		//AdminMenuService.getMenuInfo(checkMenuIdx, callBackMenuInfoForm);
	});
	
	
	
});


var checkedMenuName = "";
/**
 * 03.서브메뉴 리스트 조회
 */
function subMenuView(menuIdx, menuName) {
	clearForm();						// 폼 클리어
	//$("#action_save").html("<button type=\"button\" class=\"btn btn-submit\"  id=\"saveBtn\" title=\"저장\">그룹생성</button>");
	
	this.checkedMenuName = menuName;	// 메뉴명 세팅 
	
	// 메뉴 index 세팅 
	$("#we_menu_idx").val(menuIdx);
	
	// 전체 해제후 클릭한 메뉴만 세팅 
	$('input[name=menuCheck]').each(function(){
		$(this).attr('checked', false); 				// 가져온 체크박스를 미체크상태로
	});
		
	var checker = "menuCheck"+menuIdx;
	// 선택된 그룹명만 체크 한다 
	$("#"+checker).attr('checked', true);
	
	AdminMenuService.getSubMenuListDWR(menuIdx, callBackSubMenuList);
	
}

// 서브 메뉴 리스트 조회  콜백
function callBackSubMenuList(obj) {
	var inHtml = "";
	if(obj != '') {
		var weSubMenu = obj;
		listSize = weSubMenu.length;
		
		

		$(".dtlView").remove();
		for(i=0 ; i < listSize ; i++){
			inHtml += "<tr class=\"dtlView\">";
			inHtml += "	   <td><input type=\"checkbox\" name=\"subMenuCheck\" id=\"menuIdx_"+weSubMenu[i].we_menu_idx+"\" value=\""+weSubMenu[i].we_menu_idx+"\" /></td>";
			inHtml += "	   <td>"+weSubMenu[i].we_menu_order_idx+"</td>";
			inHtml += "	   <td style=\"text-align:left\">"+weSubMenu[i].we_menu_name+"</td>";
			inHtml += "	   <td style=\"text-align:left\">"+weSubMenu[i].we_menu_url+"</td>";
			inHtml += "</tr>";
		}

		$("#subMenuTitle").after(inHtml);
	} else {
		$(".dtlView").remove();

		inHtml = "<tr class=\"dtlView\"><td colspan=\"4\">조회된 값이 없습니다.</td></tr>";
		$("#subMenuTitle").after(inHtml);
	}
}

//메뉴 정보 수정 폼 적용 콜백 
function callBackMenuInfoForm(obj) {
	
	if(obj != '' && obj != null) {
		var weMenu = obj;
		// 폼 클리어 
		clearForm();
		
		$("#we_menu_name").val(weMenu.we_menu_name);
		$("#menu_type option[value="+weMenu.we_menu_type+"]").attr("selected", "true");
		$("#we_menu_url").val(weMenu.we_menu_url);
		$("#we_menu_order_idx").val(weMenu.we_menu_order_idx);				// 아이디 
		$("#access_level option[value="+weMenu.we_access_level+"]").attr("selected", "true");
		
		
		// 버튼 생성 
		$("#action_save").html("<button type=\"button\" class=\"btn btn-submit\" id=\"updateBtn\" title=\"수정\">메뉴정보수정</button>");
		
	} else {
		alert('해당 정보가 존재하지 않습니다.');
		return;
	}
}


//그룹 생성 폼  클리어 
function clearForm() {
	$("#we_menu_name").val("");
	$("#menu_type option[value='']").attr("selected", "true");
	$("#we_menu_url").val("");				// 그룹정보 
	$("#we_menu_order_idx").val("");		// 아이디 
	$("#access_level").val("");				// 순번
}
//]]>
</script>
	
<%@include file="/WEB-INF/views/admin/include/adminFooter.jsp"%>