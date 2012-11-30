(function() 
{
	/**
	 * 사용자 프로필 레이어 팝업을 출력한다. 
	 */
	jQuery.about_glider = function(){
		
		
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
						'		<div class="tit" style="padding-top:15px;padding-left:10px">GLiDER™ Wiki 에 대하여...</div>',
						'		<div class="cont" style="overflow-y: auto; max-height: 400px; padding:15px ">', 
						
						'                GLiDER™ Wiki는 글(Contents)를 작성하는 (Writer) 사람, 즉 컨텐츠 혹은 지식을 생성하는 이를 뜻하는 두 단어를 조합한 것으로,                                                                                                                                                        ',
						'                2011년 9월부터 시작된 오픈소스 프로젝트 입니다.                                                                                                                                                                                                                             ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                GLiDER™ Wiki는 <B>"Useful - 유용하고, Simple -단순하고 , Easy - 쉽게 쓸수있는" </B> 이라는 모토 아래 개발된 협업기반의 지식관리 및 지식공유 툴 입니다.                                                                                                                             ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                Wiki는 HTML 문서를 빠르게 작성할 수 있는 서버 프로그램으로써 하와이 말로 빠르다 라는 의미인 "wikiwiki" 에서 유래 되었다고 합니다.                                                                                                                                                   ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                즉, 빠르게  정형화된 HTML 문서를 쉽고 편하게 생성함으로써 그 안의 컨텐츠를 정리하고, 공유하며, 전파하는데 목적이 있는 협업 시스템입니다.                                                                                                                                              ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                그러나, 모든 작성자들이 HTML 구문을 이해할 필요는 없습니다.                                                                                                                                                                                                                  ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                GLiDER™ Wiki는 내부적으로 손쉽게 HTML을 작성할 수 있도록 Mark Up 기반의 GLiDER Editor를 제공하고 있으며, 이미지 , 미디어 컨텐츠, 차트, 도표 등을                                                                                                                                   ',
						'                원 클릭으로 만들어 낼 수 있습니다. 자세한 기능은 Mark up & Editor의 사용 매뉴얼 페이지를 통하여 확인해 주시기 바랍니다.                                                                                                                                                                  ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                Wiki는 개방된 플랫폼으로써 누구나 자유롭게 컨텐츠를 작성하고 수정하며, 온라인 상에서 끊임없이 논의되고 발전되는 시스템이지만                                                                                                                                                          ',
						'                기업의 내부에서 쓰이거나 특정한 분야의 지식을 정리하고 전문성 있는 자료를 축적하기 위해서는 좀 더 시스템적인 관리체계를 도입할 필요가 있기 때문에                                                                                                                                       ',
						'                이를 보완한 형태의 GLiDER™ Wiki 를 개발하여 배포하게 되었습니다.                                                                                                                                                                                                             ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                따라서, GLiDER™  Wiki는 Wiki 자체의 성격보단, Wiki 의 기능적인 측면을 도입한 Knowledge Management System(KMS) 의 성격이 강한 웹 어플리케이션 입니다.                                                                                                                              ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                좀 더 많은 Start Up 벤처나 회사들이  GLiDER™ Wiki를 통하여 체계적이고 영속적인 협업시스템, 지식관리 시스템을 손쉽게 구현하고 폭 넓게 이용 되길 희망합니다. 또한 그 안에서  GLiDER™ Wiki가 사회적 기여, 사회적 가치를 공유하는 플랫폼이 되길 희망합니다.                                           ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                또한, <a href="http://www.gliderwiki.org">GLiDER™  Wiki의 홈페이지</a>는 오픈소스 개발을 위한 커뮤니티와 개발지식을                                                                                                                                                           ',
						'                공유하는 전문 사이트로 운영되고 있으며 아래와 같은 생각을 가진 이용자와 후원자를 환영합니다!.                                                                                                                                                                                     ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                <img alt="오픈소스 개발팀 글라이더의 웹 사이트가 추구하는 정치적 성향입니다." src="/resource/data/1/20121002/thumb/thumb_201210021349113401271524000.png" width="300" height="200" style="display:block; float:center; margin: 0 auto; clear: both;" >                         ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                <h3><a id="GLiDER™ Wiki의 특징은 다음과 같습니다." name="GLiDER™ Wiki의 특징은 다음과 같습니다.">GLiDER™ Wiki의 특징은 다음과 같습니다.</a></h3>                                                                                                                                ',
						'                <ol>                                                                                                                                                                                                                                                                   ',
						'                    <li > 웹 인스톨러 : 설치시 복잡한 설정을 배제한 직관적인 설치 화면을 통해 손쉽게 GLiDER™ Wiki를 이용할 수  있습니다.</li>                                                                                                                                                    ',
						'                    <li > 사내 조직 혹은 그룹을 반영하여 접근권한, 수정권한, 조회권한등을 부여를 통해 사용자를 관리 할 수 있습니다. </li>                                                                                                                                                          ',
						'                    <li > 생성된 그룹 혹은 공간에 가입신청, 초대하기 메뉴를 통해 신뢰도 있는 구성원간의 협업이 가능합니다.</li>                                                                                                                                                                   ',
						'                    <li > 엑셀을 통한 데이터 업로드로 부서나 구성원이 많아도 일괄 적용이 가능합니다</li>                                                                                                                                                                                        ',
						'                    <li > 빠르고 쉽게 HTML 문서를 생산 할 수 있도록 Wyswyg 에디터를 제공합니다. </li>                                                                                                                                                                                        ',
						'                    <li > 에디터를 통해 차트, 도표, 이미지, 첨부파일, 각주, 링크등을 이용할 수 있으며 PDF로 변환하여 보관할 수 있습니다.</li>                                                                                                                                                      ',
						'                    <li > Wiki의 확장 버전이나 패치파일을 통한 업그레이드를 어드민 시스템상에서 실시간으로 다운로드, 반영할 수 있습니다.</li>                                                                                                                                                       ',
						'                    <li > 자주 쓰는 Wiki 포맷을 템플릿으로 지정하여 빠르게 적용할 수 있습니다.</li>                                                                                                                                                                                           ',
						'                    <li > Wiki의 동시 편집 모드와 페이지 잠금기능, 저장 내역의 리비전 정보 비교를 통해 컨텐츠를 관리 할 수 있습니다. </li>                                                                                                                                                         ',
						'                    <li > 배우기 쉽고, 사용하기 편합니다. =)</li>                                                                                                                                                                                                                          ',
						'                </ol>                                                                                                                                                                                                                                                                  ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                <h3><a id="GLiDER™ Wiki의 업데이트 정보는 다음과 같습니다." name="GLiDER™ Wiki의 업데이트 정보는 다음과 같습니다.">GLiDER™ Wiki의 업데이트 정보는 다음과 같습니다.</a></h3>                                                                                                        ',
						'                <img alt="" src="/resource/data/1/20121002/thumb/thumb_201210021349115411851983000.png" width="420" height="280" style="display:block; float:center; margin: 0 auto; clear: both;" >                                                                                   ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                Wiki의 자세한 사용법이 궁금하시면  <a href="/wiki/2">사용매뉴얼</a> 을 참고하시기 바랍니다.                                                                                                                                                                                    ',
						'                <br class="br"/>                                                                                                                                                                                                                                                       ',
						'                GLiDER™ Wiki의 라이센스 정책이 궁금하시면 <a href="http://www.gliderwiki.org/wiki/3">여기</a>를 클릭하세요.                                                                                                                                                                  ',
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
	
	$.about_glider.hide = function(){
		$('#previewWrap').fadeOut(function(){
			$(this).remove();
			return false;
		});
	};
	
	$('#close_preview_box').live("click" , function(e) {
		$.about_glider.hide();
	});
	$('#close_preview_btn').live("click" , function(e) {
		$.about_glider.hide();
	});
	
})(jQuery);

