(function() 
{
	/**
	 * 사용자 프로필 레이어 팝업을 출력한다. 
	 */
	jQuery.glider_support = function(){
		
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
						'		<div class="tit" style="padding-top:15px;padding-left:10px">GLiDER™ Wiki 기술 지원 및 교육</div>',
						'		<div class="cont" style="overflow-y: auto; max-height: 400px; padding:15px ">', 
						'       <h3><a id="기술지원 항목 " name="기술지원 항목 ">기술지원 항목 </a></h3>',
						'       <div class="box-check"> GLiDER™ Wiki 의 사내 적용을 위해 오픈 소스 개발팀에 기능 추가, 변경 및 수정사항등을 적용하고 싶을 경우 아래와 같은 산출 기준을 토대로 기술지원을 요청할 수 있습니다. </div>', 
						'       <table>',
						'       <tr>',
						'        <th class="">기술지원 항목 </th>',
						'        <th class=""> 구분 </th>',
						'        </tr>',
						'        <tr>',
						'        <td class=""> GLiDER™ Wiki 설치 지원 </td>',
						'        <td class=""> 일별 산정 협의 </td>',
						'        </tr>',
						'        <tr>',
						'        <td class=""> GLiDER™ Wiki 화면 수정  (디자인 변경)  </td>',
						'        <td class=""> 주 단위 일정 산정 후 협의 </td>',
						'        </tr>',
						'        <tr>',
						'        <td class=""> GLiDER™ Wiki 기능 추가 및 변경 </td>',
						'        <td class=""> 주 단위 일정 산정 후 협의 </td>',
						'        </tr>',
						'        </table>',
						'        <br class="br"/>',
						'        <h3><a id="활용사례 및 사용자 교육 " name="활용사례 및 사용자 교육 ">활용사례 및 사용자 교육 </a></h3>',
						'        <div class="box-check"> 어드민 및 사용자 교육시 일 1회 (2시간) 부터,  최대 3회 (6시간) 기준으로 사전 협의 후 진행합니다.', 
						'        <br class="br"/>',
						'        <ol>',
						'        <li > 교육 사항 </li>',
						'        </ol>',
						'        <ol>',
						'        <li class="lv3"> 설치 및 어드민 툴 사용법 교육, 시연</li>',
						'        <li class="lv3"> 그룹개설, 권한 관리, 공간 개설, 위키 사용법 교육, 시연</li>',
						'        <li class="lv3"> 템플릿 생성, 적용 교육 시연 </li>',
						'        <li class="lv3"> 공간 초대, 공간 관리, 공간 참여 방식을 통한 그룹 및 조직 운영방법 교육 및 시연 </li>',
						'        </ol>',
						'        </div>',
						'        <h3><a id="하단 라이센스 구문 변경 및 삭제 문의 " name="하단 라이센스 구문 변경 및 삭제 문의 ">하단 라이센스 구문 변경 및 삭제 문의 </a></h3>',
						'        <div class="box-alert"> GLiDER™ Wiki 는 하단의 <I><B><U> 라이센스 구문</U></B></I> <sup>하단 별도 각주 참조</sup> (인덱스 및 세부 페이지) 을 임의로 변경하거나 삭제 할 수 없습니다.', 
						'        <br class="br"/>',
						'        만약, 삭제 및 수정을 해야 할 경우 <a href="http://www.gliderwiki.org">GLiDER™ Wiki 공식 웹 사이트</a> 의 문의 게시판을 통해 요청하시기 바랍니다.', 
						'        </div>',
						'        <br class="br"/>',
						'        1. 라이센스 구문 : Copyright © 2012 GLiDERWiki™ OpenSource Team 을 포함한 하단 메뉴 전체를 지칭합니다.',
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
	
	$.glider_support.hide = function(){
		$('#previewWrap').fadeOut(function(){
			$(this).remove();
			return false;
		});
	};
	
	$('#close_preview_box').live("click" , function(e) {
		$.glider_support.hide();
	});
	$('#close_preview_btn').live("click" , function(e) {
		$.glider_support.hide();
	});
	
})(jQuery);

