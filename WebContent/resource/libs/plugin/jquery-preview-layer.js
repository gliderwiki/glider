(function() 
{
	/**
	 * 사용자 프로필 레이어 팝업을 출력한다. 
	 */
	jQuery.preview_layer = function(params){
		var markup   = params.markup;
		var title    = params.title;
		var revision = params.revision;
		var wikiIdx  = params.wikiIdx;
		
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
		            		'       <div class="body-cont wiki">',
		            		'       <article class="viewer">',
		            		'		<div class="tit" style="padding-top:15px;padding-left:10px">', title ,'</div>',
		            		//'		<div class="cont" style="overflow-y: auto; max-height: 420px; ">', markup,'</div>',
		            		'		<div class="cont" style="overflow-y: auto; max-height: 420px; " id="son">', markup,
		            		'		<script class="brush: js" type="syntaxhighlighter">바바밥</script>',
		            		'		</div>',
		            		'		<div class="wrap_btn" style="padding-top:10px">',
		            		'		<a style="cursor:pointer" role="button" class="btn_layer_close" id="close_preview_btn">닫 기</a>',
		            		'		</div>',
		            		'		</article>',
		            		'		</div>',
		            		'		</div>',
		            		'	</section>',
		            		'	<div class="bg"></div>',
		            		'	</div>',
		            		'	</div>',
		            		'</div>',
		].join('');
		
		
		$('body').before(resourceHtml);
	};
	
	$.preview_layer.hide = function(){
		$('#previewWrap').fadeOut(function(){
			$(this).remove();
			return false;
		});
	};
	
	$('#close_preview_box').live("click" , function(e) {
		$.preview_layer.hide();
	});
	$('#close_preview_btn').live("click" , function(e) {
		$.preview_layer.hide();
	});
	
})(jQuery);

