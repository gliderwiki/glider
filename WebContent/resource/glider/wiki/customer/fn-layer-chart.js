(function() 
{
	
	var G_CHART = {
			classNm : 'table',
			wrapLayer : 'wrap_layer',						/* layer의 최상위 class */
			layerClass : 'layer-wiki',						/* layer의 className */
			layerStyle : 'style="left:100px;top:100px"',	/* layer-wiki의 inline style */
			preview : 'preview',			    			/* 레이어 내용..*/
			add : 'add-chart',
			__tag : '[%]',
			rowId : 'table_row',
			cellId : 'table_cell',
			textEditor : '',
			tempLayer : ''
	};
	
	/**
	 * 사용자 프로필 레이어 팝업을 출력한다. 
	 */
	jQuery.layer_chart = function(params){
		G_CHART.textEditor = params.textEditor;
		G_CHART.tempLayer = params.tempLayer;
		
		var resourceHtml = [
		        			'<div class="loadingWrap" id="loadingWrap">' +
		            		'	<div class="loadingBg"></div>' +
		            		'	<div id="load_editor_chart">' +
		        			'<div class="'+G_CHART.wrapLayer+'">                                           '    +
		        			'	<div class="'+G_CHART.layerClass+' '+G_CHART.classNm+'" '+G_CHART.layerStyle+' >'+
		        			'		<div class="head" style="background-color:#4f81bd">'+
		        			'				<h3>차트</h3>                               '    +
		        			'			</div>                                                              '    +
		        			'			<div class="body">                                                                                      '  +
		        			'				<h4>차트 선택</h4>                                                                                    '  +
		        			'				<div class="row">                                                                                   '  +
		        			'					<input type="radio" name="chart" value="pie" checked /><label style="padding-left:7px;">원형</label>  '  +
		        			'					<input type="radio" name="chart" value="bar"  style="margin-left:20px" /><label style="padding-left:7px;">막대</label> '  +
		        			'					<button type="button" class="btn-table '+ G_CHART.add +'">추가</button>                   '  +
		        			'				</div>                                                                                              '  +
		        			'				<div class="'+G_CHART.preview+'">                                                                  '  +
		        			'					<div class="row">                                                									' +
		        			'						<textarea class="cel" name="title" placeholder="title"></textarea>      								' +
		        			'						<textarea class="cel" name="val" placeholder="value"></textarea>      									' +
		        			'						<button type="button" class="btn-table del">행삭제</button>   								' +
		        			'					</div>																							' +
		        			'				</div>                                                                                              '  +
		        			'			</div>                                                                                                  '  +
		        			'		<div class="foot">'+
		        			'			<button type="button" class="btn" id="btnLayerOk">확인</button> <button type="button" class="btn" id="btnLayerClose">닫기</button>'+
		        			'		</div>																										'+
		        			'		</form>                                                                                                     '  +
		        			'	</div>                                                                                                          '  +
		        			'	<div class="bg"></div>                                                                                          '  +
		        			'</div>' +
		        			'</div>' +
		        			'</div>',
		].join('');
		
		$("#" + G_CHART.tempLayer ).html(resourceHtml);
		$.layer_chart.event.eventChart();
	};
	
	jQuery.layer_chart.event = {
			
			randomString : function () {
				var chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz";
				var string_length = 8;
				var randomstring = '';
				for (var i=0; i<string_length; i++) {
					var rnum = Math.floor(Math.random() * chars.length);
					randomstring += chars.substring(rnum,rnum+1);
				}
				
				return randomstring;
			},
			
			eventChart : function () {
				
				var addChart = ''+
				'<div class="row">                                              ' +
				'	<textarea class="cel" name="title" placeholder="title"></textarea>      ' +
				'	<textarea class="cel" name="val" placeholder="value"></textarea>      ' +
				'	<button type="button" class="btn-table del">행삭제</button>   ' +
				'</div>															';
				
				//행추가
				$('.' + G_CHART.add).click(function (e) {
					e.preventDefault();
					$("." + G_CHART.preview).append( addChart );
					//행삭제
					$(".row", "." + G_CHART.wrapLayer).on("click", ".del", function () {
						$(this).parent().remove(); 
						var rowLength = $("." + G_CHART.preview).find('.row').length;
						$("#" + G_CHART.rowId).val(rowLength);
					});
				});
				
				//행삭제
				$(".row", "." + G_CHART.wrapLayer).on("click", ".del", function () {
					$(this).parent().remove(); 
					var rowLength = $("." + G_CHART.preview).find('.row').length;
					$("#" + G_CHART.rowId).val(rowLength);
				});
				
				$("#btnLayerOk").on("click", function () {
					var $layer = $("#" + G_CHART.tempLayer );
					var $preview = ('.'+G_CHART.preview, $layer);
					if( $preview.find('.row').length < 1 ) {
						alert('차트 값을 추가해주세요.');
						return false;
					}
					
					var regexp = /^[0-9]{1,50}$/i;
					
					var chartHtml = G_CHART.__tag + '\n';
					var chartType = $layer.find('input[name=chart]:checked').val();
					var nextBoolean = true;
					$("." + G_CHART.preview).find('div.row').each(function () {

						var title = $(this).find('textarea[name=title]').val();
						var value = $(this).find('textarea[name=val]').val();
						
						chartHtml += "['"+title+"',"+value+"], \n";
						if( ($.trim(title) === '' || title === 'undefined') || ($.trim(value) === '' || value === 'undefined') ) {
							$(this).focus();
							return nextBoolean = false;
						}
						
						if( !regexp.test(value) ) {
							alert('차트의 값은 숫자만 입력해주세요.');
							$(this).focus();
							return nextBoolean = false;
						}
						
					});
					
					if( !nextBoolean ){
						alert('차트 값을 입력해주세요.');
						return nextBoolean;
					}
					
					chartHtml += chartType + '[' + $.layer_chart.event.randomString() + '] \n';
					chartHtml += G_CHART.__tag + '\n';
					
					$.textInsert(G_CHART.textEditor, chartHtml, "", "");
					
					$("#" + G_CHART.tempLayer ).empty();
					return false; 
				});	
				
				$("#btnLayerClose").on("click", function () {
					$("#" + G_CHART.tempLayer ).empty();
					return false;
				});
			}
	};
	
})(jQuery);