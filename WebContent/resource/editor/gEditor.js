
	var G_COMM = {
			wrapLayer : 'wrap_layer',						/* layer의 최상위 class */
			layerClass : 'layer-wiki',						/* layer의 className */
			layerStyle : 'style="left:100px;top:100px"',	/* layer-wiki의 inline style */
			preview : 'preview',			    			/* 레이어 내용..*/
			apply : 'apply',								/* 적용 (	레이어) */
			btnOk : 'btnOk',								/* layer 확인 */
			btnClose : 'btnClose', 							/* layer 닫기 */
	};
	
	var G_TABLE = {
			classNm : 'table',
			title : '테이블 생성',
			rowId : 'table_row',
			cellId : 'table_cell',
			btnClassNm : 'btn-table'
	};
	
	var G_CHART = {
			classNm : 'table',
			title : '차트',
			add : 'add-chart',
			__tag : '[%]'	
	};
	
	var G_LINK = {
			classNm : 'link',
			title : '하이퍼 링크',
			btnClassNm : 'btn-link',
			type : 'linkType',
			target : 'linkTarget',
			url : 'linkUrl',
			urlTitle : 'linkTitle',
			__start : '[@:',
			__division : '|',
			__end : ']'
	};
	
	var G_MEDIA = {
			classNm : 'media',
			title : {
				media : '미디어 넣기',
				flash : '플래쉬 넣기'
			},
			_id : {
				_url : 'media_url',
				_width : 'media_width',
				_height : 'media_height',
				_title : 'media_title',
				_type : 'media_type'
			},
			_class : {
				_align : 'media_align',
			}
	};
	
	var G_IMAGE = {
			classNm : 'media',
			title : '이미지 넣기',
			_form : {
				_id : 'imgForm',
				_fileNm : 'file',
				_uploadBtn : 'img_upload'
			},
			_id : {
				_url : 'img_url',
				_width : 'img_width',
				_height : 'img_height',
				_title : 'img_title',
				_preImg : 'preImg',
				_preview : 'previewImage'
			},
			_class : {
				_align : 'img_align',
				_type : 'image_type',
				_image : 'image_image',
				_upload : 'image_upload'
			},
			_row : {
				_divId : 'img_list',
				_class : 'img',
				_input_url : 'input_url',
				_input_width : 'input_width',
				_input_height : 'input_height',
				_input_title : 'input_title'
			},
			_tag : {
				_pre : '[!',
				_split : '|',
				_post : ']'
			}
	};

	var COLOR_BOX = {
			colors : [
						'#ffffff', '#000000', '#eeece1', '#1f497d', '#4f81bd', '#c0504d', '#9bbb59', '#8064a2', '#4bacc6', '#f79646', '#ffff00',
						'#f2f2f2', '#7f7f7f', '#ddd9c3', '#c6d9f0', '#dbe5f1', '#f2dcdb', '#ebf1dd', '#e5e0ec', '#dbeef3', '#fdeada', '#fff2ca',
						'#d8d8d8', '#595959', '#c4bd97', '#8db3e2', '#b8cce4', '#e5b9b7', '#d7e3bc', '#ccc1d9', '#b7dde8', '#fbd5b5', '#ffe694',
						'#bfbfbf', '#3f3f3f', '#938953', '#548dd4', '#95b3d7', '#d99694', '#c3d69b', '#b2a2c7', '#b7dde8', '#fac08f', '#f2c314',
						'#a5a5a5', '#262626', '#494429', '#17365d', '#366092', '#953734', '#76923c', '#5f497a', '#92cddc', '#e36c09', '#c09100',
						'#7f7f7f', '#0c0c0c', '#1d1b10', '#0f243e', '#244061', '#632423', '#4f6128', '#3f3151', '#31859b', '#974806', '#7f6000'
					 ]
			
	};
	
	var gUpload = (function () {
		
		return {
			tempImage : function () {
				$("#" + G_IMAGE._form._id).unbind();
				$("#" + G_IMAGE._form._id).ajaxForm(this.editorFileuploadCallback);
				$("#" + G_IMAGE._form._id).attr("action","/wiki/imageUpload");
				$("#" + G_IMAGE._form._id).submit();
			},
			editorFileuploadCallback : function (data,state) {
				 
				var jsonStr = data;
				if(jsonStr != null) {
					if(jsonStr.result == 1 ) {
						isUpload = 1;		// 업로드 상태 변경 
						$("#weFileIdx").val(jsonStr.fileIndex);		// 파일 인덱스 세팅 
						
						
						var fileSrc = jsonStr.thumbPath+jsonStr.thumbName;
						
						// 섬네일을 출력한 후 리사이징 한다.
						
						var imgPreview = $("<img>").attr("src", "/resource/real"+fileSrc).css({height:"500px", width:"500px"});
						$(".preview",'.' + G_IMAGE.classNm).html(imgPreview);
						
						$("#" + G_IMAGE._form._urlId).val("/resource/real"+fileSrc);
					} else {
						alert(jsonStr.msg);
					}
				} else {
					alert('에러가 발생하였습니다.');
				}				
			}
		};
	})();
	
	
	var gEditor = (function () {
 		var $toolbarArea = {}, $editor = {}, $layer = {};
 		
 		return {
 			__init : function ( editorId, toolbarClass, layerId ) {
 				$editor = $("#"+editorId);
 				$toolbarArea = $("."+toolbarClass);
 				$layer = $("#"+layerId);
 				gCore.__init( $editor );
 				gLayer.__init( $layer );
 			}
 		};
 	})();
	
	var gLayer = (function () {
		var $layer={}, $preview = {};
		
		return {
			__init : function ( layer ) {
				var me = this;
				
				$layer = layer;
				$preview = ('.'+G_COMM.preview, $layer);
				
				me.closeModalEvent();
			},
			
			showLayerTable : function () {
				var me = this;
				
				$layer.empty();
				$layer.html(gLayerHtml.modal_table);
				
				me.operationTable();
			},
			
			showLayerLink : function () {
				var me = this;
				
				$layer.empty();
				$layer.html(gLayerHtml.modal_link);
				
				me.operationLink();
			},
			
			showLayerChart : function () {
				var me = this;
				
				$layer.empty();
				$layer.html(gLayerHtml.modal_chart);
				
				//Event chart del
				$preview.on('click', '.del', function (e) { e.preventDefault(); $(this).parent().remove(); });
				
				me.operationChart();
			},
			
			showLayerMedia : function () {
				var me = this;
				$layer.empty();
				$layer.html(gLayerHtml.modal_media);
				me.operationMedia();
			},
			
			showLayerFlash : function () {
				var me = this;
				$layer.empty();
				$layer.html(gLayerHtml.modal_flash);
				me.operationMedia();
			},
			
			showLayerImage : function () {
				var me = this;
				$layer.empty();
				$layer.html(gLayerHtml.modal_image);
				me.operationImage();
			},
			
			dropdownColor : function ( $obj ) {
				
				this.hideAllDropdownColor();
				
				var mode = '';
				
				if ( $obj.hasClass('font-color') ) {
					mode = 'color';
				} else if ( $obj.hasClass('bg-color') ) {
					mode = 'bg';
				} else if ( $obj.hasClass('box-bg-color') ) {
					mode = 'box';
				}
				
				var colorItems = $("<div>").addClass('geditor_dropdown').css({'width':'210px', 'display' : 'none'});

				var colorLength = COLOR_BOX.colors.length;

				for( var i=0 ; i < colorLength ; i += 1) {
					var color = COLOR_BOX.colors[i];
					var swatch = $('<a rel="' + color + '" href="javascript:void(null);" class="geditor_color_link" ></a>').css({ 'backgroundColor': color });
					
					swatch.on('click', function (e) {
						e.preventDefault();
						var $me = $(this);
						var tagColor = $me.attr('rel').replace('#','');
						gCore.setSelectText('[' + mode + '|' + tagColor + ']','[' + mode + ']', '');
						
						gLayer.hideAllDropdownColor();
					});
					
					colorItems.append(swatch);

				}
				
				var top = $obj.offset().top;
				var left = $obj.offset().left;
				
				colorItems.appendTo($(document.body));
				
				colorItems.css({ position: 'absolute', left: left + 'px', top: top+30 + 'px' }).show();
				
				$(document.body).click(function () {
					if($(this).hasClass('geditor_color_link') == false ) {
						gLayer.hideAllDropdownColor();
					} 
				});
			},
			
			hideAllDropdownColor : function () {
				$('.geditor_dropdown').remove();
			},
			
			operationImage : function () {
				var me = this;
				var _url, _width, _height, _align, _title;
			
				//upload image
				$("#" + G_IMAGE._form._id + "> input[name=" + G_IMAGE._form._fileNm + "]").on("change", function () {
					var fileVal = $("#" + G_IMAGE._form._id + "> input[name=" + G_IMAGE._form._fileNm + "]");
					if( typeof fileVal === 'undefined' || $.trim(fileVal) === '' ) {
						alert("선택된 이미지가 존재하지 않습니다.");
						return false;
					}
					
					gUpload.tempImage();
				});
				
				$("#" + G_IMAGE._id._preview).click(function (e) {
					e.preventDefault();
					_url = $("#"+G_IMAGE._form._urlId).val();
					
					if(typeof _url === 'undefiend' || $.trim(_url) === '') {
						alert('url를 입력하지 않으셨습니다.');
						$("#"+G_IMAGE._form._urlId).focus();
						return false;
					}
					
					var imgPreview = $("<img>").attr("src", _url).css({height:"500px", width:"500px"});
					$(".preview",'.' + G_IMAGE.classNm).html(imgPreview);
				});
				
				$("." + G_IMAGE._class._type).change(function (e) {
					e.preventDefault();
					var thisVal = $(this).val();
					if(thisVal === 'upload') {
						$('.' + G_IMAGE._class._upload).css({'display': 'block'});
						$('.' + G_IMAGE._class._image).css({'display': 'none'});
					} else {
						$('.' + G_IMAGE._class._image).css({'display': 'block'});
						$('.' + G_IMAGE._class._upload).css({'display': 'none'});
					}
				});
				
				$("#" + G_COMM.btnOk).on("click", function () {
					_url = $("#"+G_IMAGE._form._urlId).val();
					_width = $("#"+G_IMAGE._id._width).val();
					_height = $("#"+G_IMAGE._id._height).val();
					_align = $("input[name="+G_IMAGE._class._align+"]:checked").val();
					_title = $("#"+G_IMAGE._id._title).val();
					
					if( typeof _url === 'undefined' || $.trim(_url) === '') {
						alert("업로드된 이미지가 존재 하지 않습니다.");
						return false;
					}
					
					var imgLink = G_IMAGE._tag._pre + G_IMAGE._tag._split + _url + G_IMAGE._tag._split + _title + G_IMAGE._tag._split + _align  + G_IMAGE._tag._split + _width + G_IMAGE._tag._split + _height + G_IMAGE._tag._post;
					
					gCore.insertText( imgLink );
					me.closeModalTrigger();
				});
			},
			
			operationMedia : function () {
				var me = this, _url, _width, _height, _align;
				
				//미리보기 버튼 click
				$("#" + G_MEDIA._id._preview).on("click", function () {
					_url = $("#"+G_MEDIA._id._url).val();
					if ( typeof _url === 'undefined' || $.trim(_url) === '' || $.trim(_url) === 'http://' ) {
						alert('URL을 입력해주세요.');
						$("#"+G_MEDIA._id._url).focus();
						return false;
					}
					var embed = '<embed src="' + _url + '"> </embed>';
					$('.preview', '.mediaWrap').html(embed);
				});
				
				//확인버튼 click
				$("#" + G_COMM.btnOk).on("click", function () {
					
					_url = $("#"+G_MEDIA._id._url).val();
					_width = $("#"+G_MEDIA._id._width).val();
					_height = $("#"+G_MEDIA._id._height).val();
					_align = $("input[name="+G_MEDIA._class._align+"]:checked").val();
					
					if ( typeof _url === 'undefined' || $.trim(_url) === '' || $.trim(_url) === 'http://' ) {
						alert('URL을 입력해주세요.');
						$("#"+G_MEDIA._id._url).focus();
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
					
					gCore.insertText( embed );
					
					me.closeModalTrigger();
				});
			},
			
			operationChart : function () {
				var me = this;
				
				var addChart = ''+
					'<div class="row">                                              ' +
					'	<textarea class="cel" name="title" placeholder="title"></textarea>      ' +
					'	<textarea class="cel" name="val" placeholder="value"></textarea>      ' +
					'	<button type="button" class="btn-table del">행삭제</button>   ' +
					'</div>															';
			
				//Event chart add
				$('.' + G_CHART.add, $layer).click(function (e) {
					e.preventDefault();
					$("." + G_COMM.preview).append( addChart );
				});
				
				
				$("#" + G_COMM.btnOk).on("click", function () {
					
					if( $preview.find('.row').length < 1 ) {
						alert('차트 값을 추가해주세요.');
						return false;
					}
					
					
					var regexp = /^[0-9]{1,50}$/i;
					
					var chartHtml = G_CHART.__tag + '\n';
					var chartType = $layer.find('input[name=chart]:checked').val();
					var nextBoolean = true;
					$("." + G_COMM.preview).find('div.row').each(function () {

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
					
					chartHtml += chartType + '[' + me.randomString() + '] \n';
					chartHtml += G_CHART.__tag + '\n';
					
					gCore.insertText( chartHtml );
					
					me.closeModalTrigger();
				});
				
			},
			
			operationLink : function () {
				var me = this;
				var type, target, urlTitle, url, linkHtml = '';
				
				$("#" + G_COMM.btnOk).on("click", function () {
					type 	 = $("#" + G_LINK.type, $layer).val();
					target 	 = $("#" + G_LINK.target, $layer).val();
					urlTitle = $("#" + G_LINK.urlTitle, $layer).val();
					url 	 = $("#" + G_LINK.url, $layer).val();
					
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
					
					gCore.insertText( linkHtml );
					
					me.closeModalTrigger();
				});
			},
			
			operationTable : function () {
				var me = this;
				var row = 0, cel = 0;
				
				$("#" + G_COMM.apply ).on("click", function () {
					row = $.trim($("#" + G_TABLE.rowId).val());
					cel = $.trim($("#" + G_TABLE.cellId).val());
					
					if( ( row === '' ||  cel === '' )) {
						alert('행/열 개수에 값을 입력해주세요.');
						return false;
					} else {
						row = parseInt(row);
						cel = parseInt(cel);
						
						if( (typeof row !== 'number' || typeof cel !== 'number') || ( row === 'NaN' || cel === 'NaN') ){
							alert('행/열 이 숫자가 아닙니다. 다시 입력해주세요.');
							return false;
						}
						
						var tableHtml = '';
						
						for(var i =0 ; i < row ; i += 1) {
							tableHtml += me.makeTableRow( cel ); 
						}
						
						$("." + G_COMM.preview).empty();
						$("." + G_COMM.preview).append( tableHtml );
						
						//행삭제
						$(".row", "." + G_COMM.wrapLayer).on("click", ".row-del", function () {
							$(this).parent().remove(); 
							me.calcTableRows();
						});
					}
				});
				
				
				$(".foot", "." + G_COMM.wrapLayer).on("click", "#" + G_COMM.btnOk, function () { 
					var markupHtml = '';
					
					if( $("." + G_COMM.preview).find("div").length < 1 ){
						alert("입력된 행/열 이 없습니다. ");
						return false;
					}
					
					for (var i =0 ; i < cel ; i += 1) {
						markupHtml += "||셀제목";
						if( i == (cel-1) ) {
							markupHtml += "|| \n";							
						}
					}
					
					$('.' + G_COMM.preview ).find('div').each(function ( idx ) {
						var $me = $(this);
						$me.find("textarea").each(function ( index ) {
							var value = $(this).val();
							if($(this).val() === '' || typeof $(this).val() === 'undefiend' ){
								value = ' ';
							}
							markupHtml += "|" + value;
							if ( index == (cel-1) ) {
								markupHtml += "| \n";
							}
						});
					});
					
					gCore.insertText( markupHtml );
					
					me.closeModalTrigger();
					return false; 
				});
				
			},
			
			calcTableRows : function () {
				var rowLength = $("." + G_COMM.preview).find('.row').length;
				$("#" + G_TABLE.rowId).val(rowLength);
				
			},
			
			makeTableRow : function ( celCount ) {
				var celHtml= '';
				celHtml += '<div class="row" >';
				for(var i=0; i < celCount ; i += 1) {
					celHtml += '<textarea class="cel" name="cel_'+i+'"></textarea>';
				}
				celHtml += '<button type="button" class="btn-table row-del">행삭제</button>';
				celHtml += '</div>';
				
				return celHtml;
			},
			
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
			closeModalTrigger : function () {
				$("#" + G_COMM.btnClose, $layer).trigger("click");
			},
			
			closeModalEvent : function () {
				$layer.on("click", "#" + G_COMM.btnClose, function () {
					$layer.empty();
					return false; 
				});
			}
		};
	})();
	
	var gLayerHtml = (function () {
		return {
			
			modal_table : new String() +  
			'<div class="loadingWrap" id="loadingWrap">' +
    		'	<div class="loadingBg"></div>' +
    		'	<div id="load_editor_chart">' +
			'<div class="'+G_COMM.wrapLayer+'">' +
			'	<div class="'+G_COMM.layerClass+' '+G_TABLE.classNm+'" '+G_COMM.layerStyle+' >'+
			'		<div class="head" style="background-color:#4f81bd">'+
			'			<h3>'+G_TABLE.title+'</h3>'+
			'		</div>'+
			'		<div class="body">'+
			'			<h4>행/열 개수</h4>'+
			'			<div class="row">'+
			'				<input type="input" id="'+ G_TABLE.rowId +'" value="" /> 행 x <input type="input" id="'+ G_TABLE.cellId +'" value="" /> 열 '+
			'				<button type="button" class="'+G_TABLE.btnClassNm+'" id="'+G_COMM.apply+'" >적용</button>'+
			'			</div>'+
			'			<div class="'+G_COMM.preview+'">'+
			
			'			</div>'+
			'		</div>'+
			'		<div class="foot">'+
			'			<button type="button" class="btn" id="'+G_COMM.btnOk+'">확인</button> <button type="button" class="btn" id="'+ G_COMM.btnClose +'">닫기</button>'+
			'		</div>'+
			'	</div>'+
			'   <div class="bg"></div>'+
			'</div>' +
			'</div>' +
			'</div>',
			
			
			modal_link : new String() +
			'<div class="loadingWrap" id="loadingWrap">' +
    		'	<div class="loadingBg"></div>' +
    		'	<div id="load_editor_chart">' +
			'<div class="'+G_COMM.wrapLayer+'">                                           '    +
			'	<div class="'+G_COMM.layerClass+' '+G_LINK.classNm+'" '+G_COMM.layerStyle+' >'+
			'		<div class="head" style="background-color:#4f81bd">'+
			'				<h3>'+G_LINK.title+'</h3>                               '      +
			'			</div>                                                              '    +
			'			<div class="body">                                                  '    +
			'				<table>                                                         '    +
			'					<tbody>                                                     '    +
			'						<tr>                                                    '    +
			'							<th>유형</th>                                         '   +
			'							<td>                                                '    +
			'								<select id="'+G_LINK.type+'">                                        '    +
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
			'								<select id="'+ G_LINK.target +'">                                        '    +
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
			'								<input type="text" id="'+ G_LINK.url +'"/>         '    +
			'							</td>                                               '    +
			'						</tr>                                                   '    +
			'						<tr>                                                    '    +
			'							<th>타이틀</th>                                       '    +
			'							<td colspan="3">                                    '    +
			'								<input type="text" id="'+ G_LINK.urlTitle +'"/>    '    +
			'							</td>                                               '    +
			'						</tr>                                                   '    +
			'					</tbody>                                                    '    +
			'				</table>                                                        '    +
			'			</div>                                                              '    +
			'		<div class="foot">'+
			'			<button type="button" class="btn" id="'+G_COMM.btnOk+'">확인</button> <button type="button" class="btn" id="'+ G_COMM.btnClose +'">닫기</button>'+
			'		</div>'+
			'	</div>                                                                      '    +
			'	<div class="bg"></div>                                                      '    +
			'</div>' +
			'</div>' +
			'</div>',
			
			modal_chart : new String() +
			'<div class="loadingWrap" id="loadingWrap">' +
    		'	<div class="loadingBg"></div>' +
    		'	<div id="load_editor_chart">' +
			'<div class="'+G_COMM.wrapLayer+'">                                           '    +
			'	<div class="'+G_COMM.layerClass+' '+G_CHART.classNm+'" '+G_COMM.layerStyle+' >'+
			'		<div class="head" style="background-color:#4f81bd">'+
			'				<h3>'+G_CHART.title+'</h3>                               '    +
			'			</div>                                                              '    +
			'			<div class="body">                                                                                      '  +
			'				<h4>차트 선택</h4>                                                                                    '  +
			'				<div class="row">                                                                                   '  +
			'					<input type="radio" name="chart" value="pie" checked /><label style="padding-left:7px;">원형</label>  '  +
			'					<input type="radio" name="chart" value="bar"  style="margin-left:20px" /><label style="padding-left:7px;">막대</label> '  +
			'					<button type="button" class="btn-table '+ G_CHART.add +'">추가</button>                   '  +
			'				</div>                                                                                              '  +
			'				<div class="'+G_COMM.preview+'">                                                                  '  +
			'					<div class="row">                                              									' +
			'						<textarea class="cel" name="title" placeholder="title"></textarea>      								' +
			'						<textarea class="cel" name="val" placeholder="value"></textarea>      									' +
			'						<button type="button" class="btn-table del">행삭제</button>   								' +
			'					</div>																							' +
			'				</div>                                                                                              '  +
			'			</div>                                                                                                  '  +
			'		<div class="foot">'+
			'			<button type="button" class="btn" id="'+G_COMM.btnOk+'">확인</button> <button type="button" class="btn" id="'+ G_COMM.btnClose +'">닫기</button>'+
			'		</div>																										'+
			'		</form>                                                                                                     '  +
			'	</div>                                                                                                          '  +
			'	<div class="bg"></div>                                                                                          '  +
			'</div>' +
			'</div>' +
			'</div>',
			
			modal_media : new String() +
			'<div class="loadingWrap" id="loadingWrap">' +
    		'	<div class="loadingBg"></div>' +
    		'	<div id="load_media">' +
			'<div class="'+G_COMM.wrapLayer+'">                                           '    +
			'	<div class="'+G_COMM.layerClass+' '+G_MEDIA.classNm+'">'+
			'		<div class="head" style="background-color:#4f81bd">'+
			'				<h3>'+G_MEDIA.title.media+'</h3>                                           '  +
			'		</div>                                                                          '  +
			'		<div class="body">                                                              '  +
			'			<div class="preview">                                                       '  +
			'									                                                        '  +
			'			</div>                                                                      '  +
			'			<div class="row">                                                           '  +
			'				<strong class="tit">URL</strong>                                        '  +
			'				<div class="cont">                                                      '  +
			'					<input type="text" id="'+G_MEDIA._id._url+'" value="http://" /> '  +
			'					<button type="button" class="btn-preview" id="'+G_MEDIA._id._preview+'">미리보기</button> '    +
			'				</div>                                                                  '  +
			'			</div>                                                                      '  +
			'			<div class="row">                                                           '  +
			'				<strong class="tit">크기</strong>                                        '   +
			'				<div class="cont size">                                                 '  +
			'					<label for="inpSizeWidth">가로</label>                               '   +
			'					<input type="text" id="'+G_MEDIA._id._width+'" />            '  +
			'					<label for="inpSizeHeight">세로</label>                              '   +
			'					<input type="text" id="'+G_MEDIA._id._height+'" />		    '  +
			'				</div>                                                                  '  +
			'			</div>                                                                      '  +
			'		</div>                                                                          '  +
			'		<div class="foot">'+
			'			<button type="button" class="btn" id="'+G_COMM.btnOk+'">확인</button> <button type="button" class="btn" id="'+ G_COMM.btnClose +'">닫기</button>'+
			'		</div>																										'+
			'	</div>                                                                                  '  +
			'	<div class="bg"></div>                                                                  '  +
			'</div>' +
			'</div>' +
			'</div>',
			
			modal_flash : new String() +
			'<div class="loadingWrap" id="loadingWrap">' +
    		'	<div class="loadingBg"></div>' +
    		'	<div id="load_media">' +
			'<div class="'+G_COMM.wrapLayer+'">                                           '    +
			'	<div class="'+G_COMM.layerClass+' '+G_MEDIA.classNm+'">'+
			'		<div class="head" style="background-color:#4f81bd">'+
			'				<h3>'+G_MEDIA.title.flash+'</h3>                                           '  +
			'			</div>                                                                          '  +
			'			<div class="body">                                                              '  +
			'				<div class="preview">                                                       '  +
			'									                                                        '  +
			'				</div>                                                                      '  +
			'				<div class="row">                                                           '  +
			'					<strong class="tit">URL</strong>                                        '  +
			'					<div class="cont">                                                      '  +
			'						<input type="text" id="'+G_MEDIA._id._url+'" value="http://" /> '  +
			'						<button type="button" class="btn-preview" id="'+G_MEDIA._id._preview+'">미리보기</button> '    +
			'					</div>                                                                  '  +
			'				</div>                                                                      '  +
			'				<div class="row">                                                           '  +
			'					<strong class="tit">크기</strong>                                        '   +
			'					<div class="cont size">                                                 '  +
			'						<label for="inpSizeWidth">가로</label>                               '   +
			'						<input type="text" id="'+G_MEDIA._id._width+'" />            '  +
			'						<label for="inpSizeHeight">세로</label>                              '   +
			'						<input type="text" id="'+G_MEDIA._id._height+'" />		    '  +
			'					</div>                                                                  '  +
			'				</div>                                                                      '  +
			'			</div>                                                                          '  +
			'		<div class="foot">'+
			'			<button type="button" class="btn" id="'+G_COMM.btnOk+'">확인</button> <button type="button" class="btn" id="'+ G_COMM.btnClose +'">닫기</button>'+
			'		</div>																										'+
			'	</div>                                                                                  '  +
			'	<div class="bg"></div>                                                                  '  +
			'</div>' +
			'</div>' +
			'</div>',
			
			modal_image : new String() +
			'<div class="loadingWrap" id="loadingWrap">' +
    		'	<div class="loadingBg"></div>' +
    		'	<div id="load_image">' +
			'<div class="'+G_COMM.wrapLayer+'">                                           '    +
			'	<div class="'+G_COMM.layerClass+' '+G_MEDIA.classNm+'">'+
			'		<div class="head" style="background-color:#4f81bd">'+
			'				<h3>'+G_IMAGE.title+'</h3>                                                                                                  '   +
			'			</div>                                                                                                                          '   +
			'			<div class="body">                                                                                                              '   +
			'				<div class="preview">                                                                                                       '   +
			'				</div>                                                                                                                      '   +
			'				<div class="row">                                                                                                           '   +
			'					<strong class="tit">타입</strong>                                                                                        '   +
			'					<div class="cont">                                                                                                 '   +
			'						<input type="radio" id="type_upload" class="'+G_IMAGE._class._type+'" name="type" value="upload" checked/> <label for="type_upload">파일첨부</label>'   +
			'						<input type="radio" id="type_image" class="'+G_IMAGE._class._type+'" name="type" value="image" /> <label for="type_image">웹사진</label>'   +
			'					</div>                                                                                                                  '   +
			'				</div>                                                                                                                      '   +
			'				<div class="row ' + G_IMAGE._class._upload +'">                                                                                                           '   +
			'					<strong class="tit">파일첨부</strong>                                                                                     '   +
			'					<div class="cont">                                                                                                      '   +
			'						<form id="'+G_IMAGE._form._id+'" name="'+G_IMAGE._form._id+'" method="post" enctype="multipart/form-data">          '   +
			'							<input type="file" name="'+G_IMAGE._form._fileNm+'" id="'+G_IMAGE._form._fileNm+'" />                           '   +
			'						</form>                                                                                                             '   +
			'					</div>                                                                                                                  '   +
			'				</div>                                                                                                                      '   +
			'				<div class="row ' + G_IMAGE._class._image +'" style="display:none;">                                                                                                           '   +
			'					<strong class="tit">URL</strong>                                                                                     '   +
			'					<div class="cont">                                                                                                      '   +
			'							<input type="text"  id="'+G_IMAGE._form._urlId+'" />                           '   +
			'							<button type="button" id="'+G_IMAGE._id._preview+'" class="btn-preview">미리보기</button>' +
			'					</div>                                                                                                                  '   +
			'				</div>                                                                                                                      '   +
			'				<div class="row">                                                                                                           '   +
			'					<strong class="tit">타이틀</strong>                                                                                        '   +
			'					<div class="cont">                                                                                                 '   +
			'						<input type="text" id="'+G_IMAGE._id._title+'" />                                                                  '   +
			'					</div>                                                                                                                  '   +
			'				</div>                                                                                                                      '   +
			'               <div class="row ' + G_IMAGE._class._upload +'">                                          '  +
			'               	<strong class="tit">크기</strong>                        '  +
			'               	<div class="cont size">                                 '  +
			'               		<label for="'+G_IMAGE._id._width+'">가로</label>               '  +
			'               		<input type="text" name="img_width" value="" id="'+G_IMAGE._id._width+'" style="text-align:right;"/>             '  +
			'               		<label for="'+G_IMAGE._id._height+'">세로</label>              '  +
			'               		<input type="text" name="img_height" value="" id="'+G_IMAGE._id._height+'" style="text-align:right;" />		    '  +
			'               	</div>                                                  '  +
			'               </div>														'  +
			'				<div class="row">                                                                                                           '   +
			'					<strong class="tit">정렬</strong>                                                                                        '   +
			'					<div class="cont">                                                                                                      '   +
			'						<input type="radio" class="'+G_IMAGE._class._align+'" id="'+G_IMAGE._class._align+'1" name="'+G_IMAGE._class._align+'" value="left"checked/>           '  +
			'						<label for="'+G_IMAGE._class._align+'1">왼쪽</label>                                '   +
			'						<input type="radio" class="'+G_IMAGE._class._align+'" id="'+G_IMAGE._class._align+'2" name="'+G_IMAGE._class._align+'" value="center"/>           '  +
			'						<label for="'+G_IMAGE._class._align+'2">가운데</label>                               '   +
			'						<input type="radio" class="'+G_IMAGE._class._align+'" id="'+G_IMAGE._class._align+'3" name="'+G_IMAGE._class._align+'" value="right"/>           '  +
			'						<label for="'+G_IMAGE._class._align+'3">오른쪽</label>                               '   +
			'					</div>                                                                                                                  '   +
			'				</div>                                                                                                                      '   +
			'			</div>                                                                                                                          '   +
			'			<div class="foot">'+
			'				<button type="button" class="btn" id="'+G_COMM.btnOk+'">확인</button> <button type="button" class="btn" id="'+ G_COMM.btnClose +'">닫기</button>'+
			'			</div>																										'+
			'	</div>                                                                                                                                  '   +
			'	<div class="bg"></div>                                                                                                                  '   +
			'</div>' +
			'</div>' +
			'</div>'
			
			
		};
	})();
	
	
 	var gCore = (function () {
 		var $editor = {};
 		
 		return {
 			__init : function ( editor ) {
 				$editor = editor;
 			},
 			
 			setSelectText : function ( beforeText, afterText, centerText ) {
 				var me = this;
 				
 				if(document.selection){    //IE
 					setIeSelectText(beforeText, afterText, centerText);
 		 			return;
 				}
 		 		var range = $wikiEditor.getSelection();
 		 		
 		 		var centerTxt = "";
 		 		
 		 		if(range.text == '' || range.text.length < 1){
 		 			centerTxt = centerText;
 		 		}
 		 		
 		 		$editor.surroundSelectedText(beforeText+centerTxt, afterText, true);
 		 		$editor.focus();
 			},
 			
 			/* text insert */
 			insertText : function ( insertTxt ) {

 				if(document.selection){//IE
 					setIeSelectText(insertTxt);
 		 			return;
 				}
 				
 		 		$editor.surroundSelectedText(insertTxt, '', true);
 		 		$editor.focus();	
 			}
 		};
 	})();