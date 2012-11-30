(function() 
{
	/**
	 * 사용자 프로필 레이어 팝업을 출력한다. 
	 */
	jQuery.groupLayer = function(params){
		
		var list = params.userList;
		var checkType = params.type;
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
				resourceHtml	+= "			<td class=\"first\"><input type=\"checkbox\" name=\"user_idx\" value=\""+list[i].we_user_idx+"\" id=\"check_"+list[i].we_user_idx+"\"  title=\""+list[i].we_user_nick+"\" />"+list[i].we_user_id+"</td>";
				resourceHtml	+= "			<td class=\"name\">"+list[i].we_user_nick+"</td>";
				resourceHtml	+= "			<td class=\"name\">"+$.format.date(list[i].we_user_join_date, "yyyy.MM.dd")+"</td>";
				resourceHtml	+= "			<td class=\"name\">"+list[i].we_user_name+"</td>";
				resourceHtml	+= "		</tr>";
			}
			resourceHtml	+= "	</tbody>";
			resourceHtml	+= "	</table>";
			resourceHtml	+= "</div>";
			resourceHtml	+= "<div class=\"wrap-btn\">";
			resourceHtml	+= "	<a style=\"cursor:pointer\" role=\"button\" id=\"checkUserId\" class=\"btn\" title=\""+checkType+"\">선택완료</button>";
			resourceHtml	+= "	<a style=\"cursor:pointer\" role=\"button\" class=\"btn\" id=\"close_btn\">닫기</a>";
			resourceHtml	+= "</div>";
			resourceHtml	+= "</section>";
			resourceHtml	+= "</div>";
			resourceHtml	+= "</div>";
		

		$('body').after(resourceHtml);
	};
	
	$.groupLayer.hide = function(){
		$('#groupWrap').fadeOut(function(){
			$(this).remove();
			return false;
		});
	};
	
	$('#close_btn').live("click" , function(e) {
		$.groupLayer.hide();
	});
	$('#checkUserId').live("click" , function(e) {
		var arrayCheckId = $('input[name=user_idx]:checked').map(function() {
		    return this.value;			
		}).get().join(',');
		
		if(arrayCheckId == '' || arrayCheckId == null) {
			alert('사용자를 선택하세요');
			return;
		}
		
		var checkType = $(this).attr("title");
		
		var data = arrayCheckId.split(",");
		
		if(checkType == 'user') {
			addUserListInGroup(arrayCheckId);					
		} else if (checkType == 'front') {
			addUserListInFriend(arrayCheckId);
		} else if (checkType == 'invite') {
			inviteUserCallBack(arrayCheckId);
		} else if(checkType == 'admin') {
			if(typeof data[1] != 'undefined' || data[0] == '') { 
				alert('관리자는 한명만 선택할 수 있습니다.');
				return;
			}
			var check_id = $("#check_"+data[0]).attr("title");
			
			addAdminInGroup(data[0], check_id);
			$.groupLayer.hide();
		} else if(checkType == 'space') {
			if(typeof data[1] != 'undefined' || data[0] == '') { 
				alert('관리자는 한명만 선택할 수 있습니다.');
				return;
			}
			var check_id = $("#check_"+data[0]).attr("title");
			
			addAdminInSpace(data[0], check_id);
			$.groupLayer.hide();
		}
		
	});
		
})(jQuery);




