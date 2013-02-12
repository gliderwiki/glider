(function() 
{
	
	var G_FLASH = {
			textEditor : '',
			tempLayer : '',
			wrapLayer : 'wrap_layer',						/* layer의 최상위 class */
			layerClass : 'layer-wiki',						/* layer의 className */
			layerStyle : 'style="left:100px;top:100px"',	/* layer-wiki의 inline style */
			preview : 'preview',			    			/* 레이어 내용..*/
			_id : {
				_url : 'media_url',
				_width : 'media_width',
				_height : 'media_height',
				_preview : 'previewImage'
			},
	};
	
	/**
	 * 사용자 프로필 레이어 팝업을 출력한다. 
	 */
	jQuery.layer_flash = function(params){
		G_FLASH.textEditor = params.textEditor;
		G_FLASH.tempLayer = params.tempLayer;
		
		var resourceHtml = [
		        			'<div class="loadingWrap" id="loadingWrap">' +
		            		'	<div class="loadingBg"></div>' +
		            		'	<div id="load_media">' +
		        			'<div class="'+G_FLASH.wrapLayer+'">                                           '    +
		        			'	<div class="'+G_FLASH.layerClass+' media">'+
		        			'		<div class="head" style="background-color:#4f81bd">'+
		        			'				<h3>플래쉬 넣기</h3>                                           '  +
		        			'			</div>                                                                          '  +
		        			'			<div class="body">                                                              '  +
		        			'				<div class="preview">                                                       '  +
		        			'									                                                        '  +
		        			'				</div>                                                                      '  +
		        			'				<div class="row">                                                           '  +
		        			'					<strong class="tit">URL</strong>                                        '  +
		        			'					<div class="cont">                                                      '  +
		        			'						<input type="text" id="'+G_FLASH._id._url+'" value="http://" /> '  +
		        			'						<button type="button" class="btn-preview" id="'+G_FLASH._id._preview+'">미리보기</button> '    +
		        			'					</div>                                                                  '  +
		        			'				</div>                                                                      '  +
		        			'				<div class="row">                                                           '  +
		        			'					<strong class="tit">크기</strong>                                        '   +
		        			'					<div class="cont size">                                                 '  +
		        			'						<label for="inpSizeWidth">가로</label>                               '   +
		        			'						<input type="text" id="'+G_FLASH._id._width+'" />            '  +
		        			'						<label for="inpSizeHeight">세로</label>                              '   +
		        			'						<input type="text" id="'+G_FLASH._id._height+'" />		    '  +
		        			'					</div>                                                                  '  +
		        			'				</div>                                                                      '  +
		        			'			</div>                                                                          '  +
		        			'		<div class="foot">'+
		        			'			<button type="button" class="btn" id="btnLayerOk">확인</button> <button type="button" class="btn" id="btnLayerClose">닫기</button>'+
		        			'		</div>																										'+
		        			'	</div>                                                                                  '  +
		        			'	<div class="bg"></div>                                                                  '  +
		        			'</div>' +
		        			'</div>' +
		        			'</div>'
		].join('');
		
		$("#" + G_FLASH.tempLayer ).html(resourceHtml);
		$.layer_flash.event.eventFlash();
	};
	
	jQuery.layer_flash.event = {
			
			eventFlash : function(){
				var _url, _width, _height, _align;
				
				//미리보기 버튼 click
				$("#" + G_FLASH._id._preview).on("click", function () {
					_url = $("#"+G_FLASH._id._url).val();
					if ( typeof _url === 'undefined' || $.trim(_url) === '' || $.trim(_url) === 'http://' ) {
						alert('URL을 입력해주세요.');
						$("#"+G_FLASH._id._url).focus();
						return false;
					}
					var embed = '<embed src="' + _url + '"> </embed>';
					$('.preview', '.mediaWrap').html(embed);
				});
				
				//확인버튼 click
				$("#btnLayerOk").on("click", function () {
					
					_url = $("#"+G_FLASH._id._url).val();
					_width = $("#"+G_FLASH._id._width).val();
					_height = $("#"+G_FLASH._id._height).val();
					_align = $("input[name=media_align]:checked").val();
					
					if ( typeof _url === 'undefined' || $.trim(_url) === '' || $.trim(_url) === 'http://' ) {
						alert('URL을 입력해주세요.');
						$("#"+G_FLASH._id._url).focus();
						return false;
					}
					
					var embed = '<embed src="' + _url + '" hidden = "false" ';
					
					if ( typeof _width !== 'undefined' && $.trim(_width) !== '' ) {
						embed += ' width = "'+ _width +'" ';
					}
					if ( typeof _height !== 'undefined' && $.trim(_height) !== '' ) {
						embed += ' height = "'+ _height +'" ';
					}
					embed += '></embed>';
					$.textInsert(G_FLASH.textEditor, embed, "", "");
					
					$("#" + G_FLASH.tempLayer ).empty();
				});
				
				$("#btnLayerClose").on("click", function () {
					$("#" + G_FLASH.tempLayer ).empty();
					return false;
				});	
				
			}
	};
	
})(jQuery);