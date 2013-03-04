(function() 
{
	/**
	 * 사용자 프로필 레이어 팝업을 출력한다. 
	 */
	jQuery.groupInfoLayer = function(params){
		
		var list = params.weGroupList;
		var checkType = params.type;
		var listSize = params.weGroupList.length;
		
		if($('#groupWrap').length){
			return false;
		}
		
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
				resourceHtml	+= "		<tr>";
				resourceHtml	+= "			<td class=\"first\"><input type=\"checkbox\" name=\"group_idx\" value=\""+list[i].we_group_idx+"\" id=\"check_"+list[i].we_group_idx+"\"  title=\""+list[i].we_group_name+"\" />"+list[i].we_group_name+"</td>";
				resourceHtml	+= "			<td class=\"name\">"+list[i].we_user_nick+"</td>";
				resourceHtml	+= "			<td class=\"name\">"+list[i].we_group_type+"</td>";
				resourceHtml	+= "			<td class=\"name\">"+$.format.date(list[i].we_ins_date, "yyyy.MM.dd")+"</td>";
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
		wrapWindowMask();
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
		var arrayCheckId = $('input[name=group_idx]:checked').map(function() {
		    return this.value;			
		}).get().join(',');
		
		if(arrayCheckId == '' || arrayCheckId == null) {
			alert('사용자를 선택하세요');
			return;
		}
		
		var checkType = $(this).attr("title");
		
		var data = arrayCheckId.split(",");
		
		if(checkType == 'groupInfo') {
			inviteUserCallBack(arrayCheckId);
		}
	});
	
	function wrapWindowMask(){
		var POSLEFT = ( ($(window).width() - $("#groupDiv").width()) / 2 );
		var POSTOP =  ( ($(window).height() - $("#groupDiv").height()) / 2 );
		
		//Set the popup window to center         
		$("#groupDiv").css('top',  POSTOP);         
		$("#groupDiv").css('left', POSLEFT);		
	}
})(jQuery);




