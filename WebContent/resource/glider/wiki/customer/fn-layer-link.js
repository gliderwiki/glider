(function() 
{
	
	var G_LINK = {
			textEditor : '',
			tempLayer : '',
			__start : '[@:',
			__division : '|',
			__end : ']',
	};
	
	/**
	 * 사용자 프로필 레이어 팝업을 출력한다. 
	 */
	jQuery.layer_link = function(params){
		G_LINK.textEditor = params.textEditor;
		G_LINK.tempLayer = params.tempLayer;
		
		var resourceHtml = [
		        			'<div class="loadingWrap" id="loadingWrap">' +
		            		'	<div class="loadingBg"></div>' +
		            		'	<div id="load_editor_chart">' +
		        			'<div class="wrap_layer">                                           '    +
		        			'	<div class="layer-wiki link" style="left:100px;top:100px" >'+
		        			'		<div class="head" style="background-color:#4f81bd">'+
		        			'				<h3>하이퍼 링크</h3>                               '      +
		        			'			</div>                                                              '    +
		        			'			<div class="body">                                                  '    +
		        			'				<table>                                                         '    +
		        			'					<tbody>                                                     '    +
		        			'						<tr>                                                    '    +
		        			'							<th>유형</th>                                         '   +
		        			'							<td>                                                '    +
		        			'								<select id="linkType">                                        '    +
		        			'									<option value="http://" selected="selected">http</option>   ' +
		        			'						            <option value="https://">https</option>                     ' +
		        			'						            <option value="mailto:">mailto</option>                     ' +
		        			'						            <option value="file://">file</option>                       ' +
		        			'						            <option value="ftp://">ftp</option>                         ' +
		        			'						            <option value="gopher://">gopher</option>                   ' +
		        			'						            <option value="news:">news</option>                         ' +
		        			'						            <option value="telnet:">telnet</option>                     ' +
		        			'						            <option value="wias:">wias</option>                         ' +
		        			'						            <option value="javascript:">javascript</option>             ' +
		        			'								</select>                                       '    +
		        			'							</td>                                               '    +
		        			'							<th>타겟</th>                                         '   +
		        			'							<td>                                                '    +
		        			'								<select id="linkTarget">                                        '    +
		        			'									<option value="">없음</option>								' +
		        			'						            <option value="_self">_self</option>                        ' +
		        			'						            <option value="_blank" selected="selected">_blank</option>  ' +
		        			'						            <option value="_parent">_parent</option>                    ' +
		        			'						            <option value="_top">_top</option>                          ' +
		        			'								</select>                                       '    +
		        			'							</td>                                               '    +
		        			'						</tr>                                                   '    +
		        			'						<tr>                                                    '    +
		        			'							<th>URL</th>                                        '    +
		        			'							<td colspan="3">                                    '    +
		        			'								<input type="text" id="linkUrl"/>         '    +
		        			'							</td>                                               '    +
		        			'						</tr>                                                   '    +
		        			'						<tr>                                                    '    +
		        			'							<th>타이틀</th>                                       '    +
		        			'							<td colspan="3">                                    '    +
		        			'								<input type="text" id="linkTitle"/>    '    +
		        			'							</td>                                               '    +
		        			'						</tr>                                                   '    +
		        			'					</tbody>                                                    '    +
		        			'				</table>                                                        '    +
		        			'			</div>                                                              '    +
		        			'		<div class="foot">'+
		        			'			<button type="button" class="btn" id="btnLayerOk">확인</button> <button type="button" class="btn" id="btnLayerClose">닫기</button>'+
		        			'		</div>'+
		        			'	</div>                                                                      '    +
		        			'	<div class="bg"></div>                                                      '    +
		        			'</div>' +
		        			'</div>' +
		        			'</div>'
		].join('');

		$("#" + G_LINK.tempLayer ).html(resourceHtml);
		$.layer_link.event.eventLink();
	};
	
	jQuery.layer_link.event = {
			
			eventLink : function(){
				
				$("#btnLayerOk").on("click", function () {
					var $layer	 = $("#" + G_LINK.tempLayer ); 
					var type 	 = $("#linkType", $layer).val();
					var target 	 = $("#linkTarget", $layer).val();
					var urlTitle = $("#linkTitle", $layer).val();
					var url 	 = $("#linkUrl", $layer).val();
					var linkHtml = "";
					
					if($.trim( url ) === ''){
						alert('URL를 입력해주세요.');
						return false;
					}
					
					linkHtml += G_LINK.__start;
					linkHtml += url;
					if( $.trim( urlTitle ) !== ''){
						linkHtml += G_LINK.__division + urlTitle;
					}
					linkHtml += G_LINK.__end;
					
					$.textInsert(G_LINK.textEditor, linkHtml, "", "");
					$("#" + G_LINK.tempLayer ).empty();
				});
				
				$("#btnLayerClose").on("click", function () {
					$("#" + G_LINK.tempLayer ).empty();
					return false;
				});	
			}
	};
	
})(jQuery);