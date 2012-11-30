(function() 
{
	/**
	 * @description : 로딩바를 출력한다.
	 * @return : None
	 * @example :
	 * 로딩 바를 출력할 부분에  
	 * $.loadingBar('메시지');
	 * 작성 후 완료 시점에 
	 * $.loadingBar.fadeOut();
	 */
	jQuery.loadingBar = function(){
		
		if($('#loadingWrap').length){
			return false;
		}
		
		var resourceHtml = [
		            		'<div class="loadingWrap" id="loadingWrap">',
		            		'	<div class="loadingBg"></div>',
		            		'	<div id="loading">',
		            		'	<img src="/resource/glider/front/images/ajax-loader.gif" alt="loading..." />',
		            		'	</div>',
		            		'</div>',
		].join('');
		
		$('body').before(resourceHtml);	
	};
	
	jQuery.loadingBar.fadeOut = function(){
		$('#loadingWrap').fadeOut(function(){
			$(this).remove();
		});
	};
	
})(jQuery);

