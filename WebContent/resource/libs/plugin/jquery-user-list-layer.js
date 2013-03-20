(function() 
{
	/**
	 * 사용자 프로필 레이어 팝업을 출력한다. 
	 */
	jQuery.userListLayer = function(params){
		
		var list = params.userList;
		var checkType = params.type;
		var authorityType  = params.authorityType;
		var listSize = params.userList.length;
		
		if($('#groupWrap').length){
			return false;
		}
		
		var resourceHtml = "";
			resourceHtml = "<div class=\"groupWrap\" id=\"groupWrap\">";
			resourceHtml	+= "<div class=\"loadingBg\"></div>";
			resourceHtml	+= "<div id=\"groupDiv\">"; 
			resourceHtml	+= "<section class=\"layer-select\">";
			resourceHtml	+= "<div class=\"tit\"><strong>구성원을 선택하세요</strong></div>";
			resourceHtml	+= "<div class=\"cont\">";
			resourceHtml	+= "	<table>";
			resourceHtml	+= "	<thead>";
			resourceHtml	+= "		<tr>";
			resourceHtml	+= "			<th class=\"name\" style=\"width:240px\">회원아이디</th>";
			resourceHtml	+= "			<th class=\"name\" style=\"width:100px\">닉네임</th>";
			resourceHtml	+= "			<th class=\"name\" style=\"width:160px\">가입일</th>";
			resourceHtml	+= "			<th class=\"name\" style=\"width:100px\">이름</th>"; 
			resourceHtml	+= "		</tr>";
			resourceHtml	+= "	</thead>";
			resourceHtml	+= "	<tbody>";
			for (var i = 0 ; i < listSize ; i++){
				resourceHtml	+= "		<tr>";
				if(checkType == 'update') {	// 공간 정보 수정일 경우 기 선택 된 사항 checked 처리 
					if(authorityType == 'view') {
						if(list[i].we_view_permit == 0) {
							resourceHtml	+= "<td class=\"first\"><input type=\"checkbox\" name=\"user_idx\" value=\""+list[i].we_user_idx+"\" id=\"check_"+list[i].we_user_idx+"\"  title=\""+list[i].we_user_nick+"\" />"+list[i].we_user_id+"</td>";
						} else {
							resourceHtml	+= "<td class=\"first\"><input type=\"checkbox\" name=\"user_idx\" value=\""+list[i].we_user_idx+"\" id=\"check_"+list[i].we_user_idx+"\"  title=\""+list[i].we_user_nick+"\" checked />"+list[i].we_user_id+"</td>";
						}
					} else if(authorityType == 'edit') {
						if(list[i].we_insert_permit == 0) {
							resourceHtml	+= "<td class=\"first\"><input type=\"checkbox\" name=\"user_idx\" value=\""+list[i].we_user_idx+"\" id=\"check_"+list[i].we_user_idx+"\"  title=\""+list[i].we_user_nick+"\" />"+list[i].we_user_id+"</td>";
						} else {
							resourceHtml	+= "<td class=\"first\"><input type=\"checkbox\" name=\"user_idx\" value=\""+list[i].we_user_idx+"\" id=\"check_"+list[i].we_user_idx+"\"  title=\""+list[i].we_user_nick+"\" checked />"+list[i].we_user_id+"</td>";
						}
					}
				} else {
					resourceHtml	+= "<td class=\"first\"><input type=\"checkbox\" name=\"user_idx\" value=\""+list[i].we_user_idx+"\" id=\"check_"+list[i].we_user_idx+"\"  title=\""+list[i].we_user_nick+"\" />"+list[i].we_user_id+"</td>";
				}
				
				resourceHtml	+= "			<td class=\"name\">"+list[i].we_user_nick+"</td>";
				resourceHtml	+= "			<td class=\"name\">"+$.format.date(list[i].we_user_join_date, "yyyy.MM.dd")+"</td>";
				resourceHtml	+= "			<td class=\"name\">"+list[i].we_user_name+"</td>";
				resourceHtml	+= "		</tr>";
			}
			resourceHtml	+= "	</tbody>";
			resourceHtml	+= "	</table>";
			resourceHtml	+= "</div>";
			resourceHtml	+= "<div class=\"wrap-btn\">";
			resourceHtml	+= "	<a style=\"cursor:pointer\" role=\"button\" id=\"checkUserId\" class=\"btn\" title=\""+authorityType+"\">선택완료</button>";
			resourceHtml	+= "	<a style=\"cursor:pointer\" role=\"button\" class=\"btn\" id=\"close_btn\">닫기</a>";
			resourceHtml	+= "</div>";
			resourceHtml	+= "</section>";
			resourceHtml	+= "</div>";
			resourceHtml	+= "</div>";
		

		$('body').after(resourceHtml);
		wrapWindowMask();
	};
	
	$.userListLayer.hide = function(){
		$('#groupWrap').fadeOut(function(){
			$(this).remove();
			return false;
		});
	};
	
	$('#close_btn').live("click" , function(e) {
		$.userListLayer.hide();
	});
	$('#checkUserId').live("click" , function(e) {
		var resultData = $('input[name=user_idx]:checked').map(function() {
		    return this.value;			
		}).get().join(',');
		
		var resultName = $('input[name=user_idx]:checked').map(function() {
			return this.title;			
		}).get().join(',');
		
		//console.log("resultData : " + resultData);
		//console.log("resultName : " + resultName);
		var authorityType = $(this).attr("title"); 
		if(resultData == '' || resultData == null) {
			GliderWiki.alert("알림","구성원을 선택하세요"); 
			return;
		}
		
		//console.log("authorityType : "  +authorityType);
		
		if(authorityType == 'view') {	// 조회권한 
			WeSpace.we_view_data.value = resultData;
			WeSpace.we_view_name.value = resultName;			
		} else {	// 수정권한 
			WeSpace.we_edit_data.value = resultData;
			WeSpace.we_edit_name.value = resultName;
		}
		
		//console.log("#authorityType : " +authorityType);
		//console.log($("#we_view_data").val());
		//console.log($("#we_view_name").val());
		$.userListLayer.hide();
		
	});
	
	function wrapWindowMask(){
		var POSLEFT = ( ($(window).width() - $("#groupDiv").width()) / 2 );
		var POSTOP =  ( ($(window).height() - $("#groupDiv").height()) / 2 );
		
		//Set the popup window to center         
		$("#groupDiv").css('top',  POSTOP);         
		$("#groupDiv").css('left', POSLEFT);		
	}
	
		
})(jQuery);




