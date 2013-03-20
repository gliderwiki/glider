(function() 
{
	/**
	 * 사용자 프로필 레이어 팝업을 출력한다. 
	 */
	jQuery.groupInfoLayer = function(params){
		
		var list = params.weGroupList;
		var checkType = params.type;
		var authorityType  = params.authorityType;
		var listSize = params.weGroupList.length;
		
		if($('#groupWrap').length){
			return false;
		}
		var group_type = "";
		var resourceHtml = "";
			resourceHtml = "<div class=\"groupWrap\" id=\"groupWrap\">";
			resourceHtml	+= "<div class=\"loadingBg\"></div>";
			resourceHtml	+= "<div id=\"groupDiv\">"; 
			resourceHtml	+= "<section class=\"layer-select\">";
			resourceHtml	+= "<div class=\"tit\"><strong>그룹을 선택하세요</strong></div>";
			resourceHtml	+= "<div class=\"cont\">";
			resourceHtml	+= "	<table>";
			resourceHtml	+= "	<thead>";
			resourceHtml	+= "		<tr>";
			resourceHtml	+= "			<th class=\"name\" style=\"width:240px\">그룹명</th>";
			resourceHtml	+= "			<th class=\"name\" style=\"width:100px\">그룹관리자</th>";
			resourceHtml	+= "			<th class=\"name\" style=\"width:160px\">그룹타입</th>";
			resourceHtml	+= "			<th class=\"name\" style=\"width:100px\">그룹개설일</th>"; 
			resourceHtml	+= "		</tr>";
			resourceHtml	+= "	</thead>";
			resourceHtml	+= "	<tbody>";
			for (var i = 0 ; i < listSize ; i++){
				
				if(list[i].we_group_type == 0) {
					group_type = "조직";
				} else {
					group_type = "사용자 생성 그룹";
				}
				resourceHtml	+= "	<tr>";
				
				if(checkType == 'update' && authorityType == 'view') {		// 공간 정보 수정일 경우 기 선택 된 사항 checked 처리 
					
					if(list[i].we_view_permit == '1') {
						//console.log("permit "+list[i].we_view_permit+" = " + list[i].we_group_idx);
						resourceHtml	+= "<td class=\"first\"><input type=\"checkbox\" name=\"group_idx\" value=\""+list[i].we_group_idx+"\" id=\"check_"+list[i].we_group_idx+"\"  title=\""+list[i].we_group_name+"\" checked />"+list[i].we_group_name+"</td>";
					} else {
						//console.log("-permit "+list[i].we_view_permit+" = " + list[i].we_group_idx);
						resourceHtml	+= "<td class=\"first\"><input type=\"checkbox\" name=\"group_idx\" value=\""+list[i].we_group_idx+"\" id=\"check_"+list[i].we_group_idx+"\"  title=\""+list[i].we_group_name+"\" />"+list[i].we_group_name+"</td>";
					}
				} else if(checkType == 'update' && authorityType == 'edit') {
					if(list[i].we_insert_permit == '1') {
						resourceHtml	+= "<td class=\"first\"><input type=\"checkbox\" name=\"group_idx\" value=\""+list[i].we_group_idx+"\" id=\"check_"+list[i].we_group_idx+"\"  title=\""+list[i].we_group_name+"\" checked />"+list[i].we_group_name+"</td>";
					} else {
						resourceHtml	+= "<td class=\"first\"><input type=\"checkbox\" name=\"group_idx\" value=\""+list[i].we_group_idx+"\" id=\"check_"+list[i].we_group_idx+"\"  title=\""+list[i].we_group_name+"\" />"+list[i].we_group_name+"</td>";
					}
				} else {
					resourceHtml	+= "<td class=\"first\"><input type=\"checkbox\" name=\"group_idx\" value=\""+list[i].we_group_idx+"\" id=\"check_"+list[i].we_group_idx+"\"  title=\""+list[i].we_group_name+"\" />"+list[i].we_group_name+"</td>";
				}
				resourceHtml	+= "	<td class=\"name\">"+list[i].we_user_nick+"</td>";
				resourceHtml	+= "	<td class=\"name\">"+group_type+"</td>";
				resourceHtml	+= "	<td class=\"name\">"+$.format.date(list[i].we_ins_date, "yyyy.MM.dd")+"</td>";
				resourceHtml	+= "	</tr>";
			}
			resourceHtml	+= "	</tbody>";
			resourceHtml	+= "	</table>";
			resourceHtml	+= "</div>";
			resourceHtml	+= "<div class=\"wrap-btn\">";
			resourceHtml	+= "	<a style=\"cursor:pointer\" role=\"button\" id=\"checkGroupId\" class=\"btn\" title=\""+authorityType+"\">선택완료</button>";
			resourceHtml	+= "	<a style=\"cursor:pointer\" role=\"button\" class=\"btn\" id=\"close_btn\">닫기</a>";
			resourceHtml	+= "</div>";
			resourceHtml	+= "</section>";
			resourceHtml	+= "</div>";
			resourceHtml	+= "</div>";
		

		$('body').after(resourceHtml);
		wrapWindowMask();
	};
	
	$.groupInfoLayer.hide = function(){
		$('#groupWrap').fadeOut(function(){
			$(this).remove();
			return false;
		});
	};
	
	$('#close_btn').live("click" , function(e) {
		$.groupInfoLayer.hide();
	});
	$('#checkGroupId').live("click" , function(e) {
		var resultData = $('input[name=group_idx]:checked').map(function() {
		    return this.value;			
		}).get().join(',');
		
		var resultName = $('input[name=group_idx]:checked').map(function() {
			return this.title;			
		}).get().join(',');
		
		//console.log("resultData : " + resultData);
		//console.log("resultName : " + resultName);
		var authorityType = $(this).attr("title"); 
		if(resultData == '' || resultData == null) {
			GliderWiki.alert("알림","그룹을 선택하세요"); 
			return;
		}
		
		//console.log("authorityType : "  +authorityType);
		
		if(authorityType == 'view') {	// 조회 권한 
			WeSpace.we_view_data.value = resultData;
			WeSpace.we_view_name.value = resultName;
		} else {	// 수정 권한 
			WeSpace.we_edit_data.value = resultData;
			WeSpace.we_edit_name.value = resultName;
		}
		
		//console.log("#authorityType : " +authorityType);
		//console.log($("#we_view_data").val());
		//console.log($("#we_view_name").val());
		$.groupInfoLayer.hide();
	});
	
	function wrapWindowMask(){
		var POSLEFT = ( ($(window).width() - $("#groupDiv").width()) / 2 );
		var POSTOP =  ( ($(window).height() - $("#groupDiv").height()) / 2 );
		
		//Set the popup window to center         
		$("#groupDiv").css('top',  POSTOP);         
		$("#groupDiv").css('left', POSLEFT);		
	}
})(jQuery);