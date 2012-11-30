(function() 
{
	/**
	 * 사용자 프로필 레이어 팝업을 출력한다. 
	 */
	jQuery.glider_license = function(){
		
		if($('#previewWrap').length){
			return false;
		}
				
		var resourceHtml = [
						'<div class="previewWrap" id="previewWrap">',
						'	<div class="loadingBg"></div>',
						'	<div id="load_preview">',
						'	<div class="wrap_layer">',
						'	<section class="layer desc">',
						'		<a style="cursor:pointer;font-size:44px;" role="button" class="btn_close" id="close_preview_box" title="닫기">&times;</a>',
						'		<div class="box">',
						'		<div class="body-cont wiki">', 
						'       <article class="viewer">',
						'		<div class="tit" style="padding-top:15px;padding-left:10px">GLiDER™ Wiki의 라이센스 정책</div>',
						'		<div class="cont" style="overflow-y: auto; max-height: 400px; padding:15px ">', 
                        '		<ul>',
                        '    		<li style="padding-top:10px;"> GLiDER™ Wiki 커뮤니티 버전은(이하 GLiDER™ Wiki) 기업, 단체, 개인 등이 무료로 이용할 수 있는 커뮤니티 버전의 오프소스 입니다. </li>',
                        '    		<li style="padding-top:10px;"> 커뮤니티 버전은 GLiDERWiki™ 웹 어플리케이션의 하단의 Copyright를 수정하거나 삭제할 수 없습니다.</li>',
                        '    		<li style="padding-top:10px;"> GLiDER™ Wiki는 구글 코드(http://code.google.com/p/enterprise-gliderwiki/)에 소스가 오픈되어 있으며, 오픈되어 있는 소스를 다운받아 자유롭게 소스코드를 변경, 적용할 수 있습니다.</li>',
                        '    		<li style="padding-top:10px;"> GLiDER™ Wiki 웹 기반 설치형 어플리케이션 오픈소스로써, 설치 대행이나 어플리케이션의 유지보수 의무가 없습니다.</li>',
                        '    		<li style="padding-top:10px;"> GLiDER™ Wiki 웹 어플리케이션 설치 및 사용자 교육, 소스의 수정적용을 위한  지원등의 문의 사항이 있을 경우 <a href="http://www.gliderwiki.org">GLiDER™ Wiki의 공식 웹 사이트</a>로 문의 주시기 바랍니다.</li>',
                        '    		<li style="padding-top:10px;"> GLiDER™ Wiki 혹은 GLiDERWiki™ 은 오픈소스팀 글라이더에서 개발한 GLiDERWiki™ 웹 어플리케이션 오픈소스의 고유 트레이드 마크 입니다.</li>',
                        '    		<li style="padding-top:10px;"> GLiDER™ Wiki를 개인적인 목적 혹은 상업적인 목적으로 재배포하거나 판매할 수 없습니다.</li>',
                        '    		<li style="padding-top:10px;"> 만약, 하단의 Copyright를 변경해야 하거나 재배포 허가를 얻어야 할 경우 <a href="http://www.gliderwiki.org">GLiDER™ Wiki의 공식 웹 사이트</a>로 혹은 performizer@gmail.com 을 통해 문의해 주시기 바랍니다.</li>',
                        '    		<li style="padding-top:10px;"> 지원되는 서버 환경은 Linux(데비안 계열, 레드햇 계열)과 윈도우즈 서버입니다. DB는 MySQL 5.x 버전이며, Application Server는 Tomcat 7.x, JDK는 1.6이상 버전에서 동작합니다.	자세한 지원 스펙에 대해서는 GLiDERWiki™의 홈페이지를 통해 확인 가능합니다. </li>',
                        ' 		</ul>',
						'		</div>', 
						'		<div class="wrap_btn" style="padding-top:20px">',
						'		<a style="cursor:pointer" role="button" class="btn" id="close_preview_btn">닫 기</a>',
						'		</div>',
						'		</article>',
						'       </div>',
						'		</div>',
						'	</section>',
						'	<div class="bg"></div>',
						'	</div>',
						'	</div>',
						'</div>',
		].join('');
		
		$('body').before(resourceHtml);	
	};
	
	$.glider_license.hide = function(){
		$('#previewWrap').fadeOut(function(){
			$(this).remove();
			return false;
		});
	};
	
	$('#close_preview_box').live("click" , function(e) {
		$.glider_license.hide();
	});
	$('#close_preview_btn').live("click" , function(e) {
		$.glider_license.hide();
	});
	
})(jQuery);

