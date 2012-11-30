(function() 
{
	/**
	 * 사용자 프로필 레이어 팝업을 출력한다. 
	 */
	jQuery.userProfile = function(params){
		var email = params.email;
		var nick = params.nick;
		var homepage = params.homepage;
		var friends = params.friends;
		var image = params.image;
		var joinDt = params.joinDt;
		var visitDt = params.visitDt;
		var point = params.point;
		
		if($('#profileWrap').length){
			return false;
		}
		
		var resourceHtml = [
		            		'<div class="profileWrap" id="profileWrap">',
		            		'	<div class="loadingBg"></div>',
		            		'	<div id="load_prof">',
		            		'	<div class="wrap_layer">',
		            		'	<section class="layer user">',
		            		'		<a style="cursor:pointer" role="button" class="btn_close" id="close_profile_box" title="닫기">&times;</a>',
		            		'		<div class="box">',
		            		'			<div class="tit">',
		            		'				<strong>사용자 정보</strong>',
		            		'			</div>',
		            		'			<div class="cont">',
		            		'				<div class="box-txt">',
		            		'					<div class="row">',
		            		'						<strong class="title">이메일</strong>',email,         		
		            		'					</div>',
		            		'					<div class="row">',
		            		'						<strong class="title">닉네임</strong>',nick,
		            		'					</div>',
		            		'					<div class="row">',
		            		'						<strong class="title">홈페이지</strong>',homepage,
		            		'					</div>',
		            		'					<div class="row">',
		            		'						<strong class="title">인맥</strong>총 ',friends, '명',
		            		'					</div>',
		            		'					<div class="row">',
		            		'						<strong class="title">활동점수</strong>',point, '점',
		            		'					</div>',
		            		'					<div class="row">',
		            		'						<strong class="title">최종방문일</strong>',visitDt,
		            		'					</div>',
		            		'				</div>',
		            		'				<div class="box-img">',
		            		'					<img src="',image,'" width="170" height="100" onerror="this.src=/resource/glider/front/images/default_big.jpg">',
		            		'				</div>',
		            		'			</div>',
		            		'			<div class="wrap_btn center">',
		            		'				<a style="cursor:pointer" role="button" class="btn" id="close_profile_btn">확 인</a>',
		            		'			</div>',
		            		'		</div>',
		            		'	</section>',
		            		'	<div class="bg"></div>',
		            		'	</div>',
		            		'	</div>',
		            		'</div>',
		].join('');
		
		$('body').before(resourceHtml);	
	};
	
	$.userProfile.hide = function(){
		$('#profileWrap').fadeOut(function(){
			$(this).remove();
			return false;
		});
	};
	
	$('#close_profile_box').live("click" , function(e) {
		$.userProfile.hide();
	});
	$('#close_profile_btn').live("click" , function(e) {
		$.userProfile.hide();
	});
	
})(jQuery);

