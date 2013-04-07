(function() 
{
	var G_IMAGE = {
			textEditor : '',
			tempLayer : '',
			wrapLayer : 'wrap_layer',						/* layer의 최상위 class */
			layerClass : 'layer-wiki',						/* layer의 className */
			layerStyle : 'style="left:100px;top:100px"',	/* layer-wiki의 inline style */
			preview : 'preview',			    			/* 레이어 내용..*/
			_form : {
				_id : 'imgForm',
				_fileNm : 'file',
				_uploadBtn : 'img_upload',
				_urlId : 'imgUrl'
			},
			_class : {
				_align : 'img_align',
				_type : 'image_type',
				_image : 'image_image',
				_upload : 'image_upload'
			},
			_id : {
				_url : 'img_url',
				_width : 'img_width',
				_height : 'img_height',
				_title : 'img_title',
				_preImg : 'preImg',
				_preview : 'previewImage'
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
				_post : ']',
				_size :  '[!s',
				_align : '[align'					
			},
	};
	/**
	 * 사용자 프로필 레이어 팝업을 출력한다. 
	 */
	jQuery.layer_image = function(params){
		G_IMAGE.textEditor = params.textEditor;
		G_IMAGE.tempLayer = params.tempLayer;
		
		var resourceHtml = [
	        			'<div class="loadingWrap" id="loadingWrap">' +
	            		'	<div class="loadingBg"></div>' +
	            		'	<div id="load_image">' +
	        			'<div class="'+G_IMAGE.wrapLayer+'">                                           '    +
	        			'	<div class="'+G_IMAGE.layerClass+' media">'+
	        			'		<div class="head" style="background-color:#4f81bd">'+
	        			'				<h3>이미지 넣기</h3>                                                                                                  '   +
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
	        			'			<button type="button" class="btn" id="btnLayerOk">확인</button> <button type="button" class="btn" id="btnLayerClose">닫기</button>'+
	        			'			</div>																										'+
	        			'	</div>                                                                                                                                  '   +
	        			'	<div class="bg"></div>                                                                                                                  '   +
	        			'</div>' +
	        			'</div>' +
	        			'</div>'
	].join('');

	$("#" + G_IMAGE.tempLayer ).html(resourceHtml);
	$.layer_image.event.eventLink();
	};
	
	jQuery.layer_image.event = {
		
			eventLink : function(){
				//upload image 파일첨부시 프리뷰 기능
				$("#" + G_IMAGE._form._id + "> input[name=" + G_IMAGE._form._fileNm + "]").on("change", function () {
					var fileVal = $("#" + G_IMAGE._form._id + "> input[name=" + G_IMAGE._form._fileNm + "]");
					if( typeof fileVal === 'undefined' || $.trim(fileVal) === '' ) {
						alert("선택된 이미지가 존재하지 않습니다.");
						return false;
					}
					
					$.layer_image.event.tempImage();
				});
				
				// 타입-웹사진에서 프리뷰 기능
				$("#" + G_IMAGE._id._preview).click(function (e) {
					//console.info(e);
					e.preventDefault();
					_url = $("#"+G_IMAGE._form._urlId).val();
					
					if(typeof _url === 'undefiend' || $.trim(_url) === '') {
						alert('url를 입력하지 않으셨습니다.');
						$("#"+G_IMAGE._form._urlId).focus();
						return false;
					}
					//console.info(_url);
					var imgPreview = $("<img>").attr("src", _url).css({height:"500px", width:"500px"});
					//console.info(imgPreview);
					$(".preview").html(imgPreview);
				});
				
				// 타입에 따른 블록 변경
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
				
				//확인
				$("#btnLayerOk").on("click", function () {
					_url = $("#"+G_IMAGE._form._urlId).val();
					_width = $("#"+G_IMAGE._id._width).val();
					_height = $("#"+G_IMAGE._id._height).val();
					_align = $("input[name="+G_IMAGE._class._align+"]:checked").val();
					_title = $("#"+G_IMAGE._id._title).val();
					
					if( typeof _url === 'undefined' || $.trim(_url) === '') {
						alert("업로드된 이미지가 존재 하지 않습니다.");
						return false;
					}
					//[!|http://asdf.com|aaa][!a|left][!s|30|40][!]
					var imgLink = G_IMAGE._tag._align + ":" +  _align + G_IMAGE._tag._post
								+ _align + G_IMAGE._tag._pre + G_IMAGE._tag._split + _url + G_IMAGE._tag._split + _title + G_IMAGE._tag._post
								+ G_IMAGE._tag._size + G_IMAGE._tag._split + _width + G_IMAGE._tag._split + _height + G_IMAGE._tag._post
								+ G_IMAGE._tag._pre + G_IMAGE._tag._post
								+ G_IMAGE._tag._align + G_IMAGE._tag._post ;
					
					$.textInsert(G_IMAGE.textEditor, imgLink, "", "" );
					$("#" + G_IMAGE.tempLayer ).empty();
				});
				
				$("#btnLayerClose").on("click", function () {
					$("#" + G_IMAGE.tempLayer ).empty();
					return false;
				});	
			},
			
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
						//console.info(imgPreview);
						$(".preview" ).html(imgPreview);
						
						$("#" + G_IMAGE._form._urlId).val("/resource/real"+fileSrc);
					} else {
						alert(jsonStr.msg);
					}
				} else {
					alert('에러가 발생하였습니다.');
				}				
			}
			
	};
	

})(jQuery);