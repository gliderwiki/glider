(function() 
{
	var G_TABLE = {
			row : 0,
			cel : 0,
			textEditor : '',
			tempLayer : ''
	};
	/**
	 * 사용자 프로필 레이어 팝업을 출력한다. 
	 */
	jQuery.layer_table = function(params){
		G_TABLE.textEditor = params.textEditor;
		G_TABLE.tempLayer = params.tempLayer;
		
		if($('#loadLayer').length){
			return false;
		}
		
		var resourceHtml = [
		            		'<div class="loadingWrap" id="loadingWrap">' +
		            		'	<div class="loadingBg"></div>' +
		            		'	<div id="load_editor_chart">' +
		            		'<div class="wrap_layer">' +
		            		'	<div class="layer-wiki table" style="left:100px;top:100px" >'+
		            		'		<div class="head" style="background-color:#4f81bd">'+
		            		'			<h3>테이블 생성</h3>'+
		            		'		</div>'+
		            		'		<div class="body">'+
		            		'			<h4>행/열 개수</h4>'+
		            		'			<div class="row">'+
		            		'				<input type="input" id="table_row" value="" /> 행 x <input type="input" id="table_cell" value="" /> 열 '+
		            		'				<button type="button" class="btn-table" id="insertTableApply" >적용</button>'+
		            		'			</div>'+
		            		'			<div class="preview">'+
		            		
		            		'			</div>'+
		            		'		</div>'+
		            		'		<div class="foot">'+
		            		'			<button type="button" class="btn" id="btnLayerOk">확인</button> <button type="button" class="btn" id="btnLayerClose">닫기</button>'+
		            		'		</div>'+
		            		'	</div>'+
		            		'   <div class="bg"></div>'+
		            		'</div>' +
		            		'</div>' +
		            		'</div>'
		].join('');

		$("#" + G_TABLE.tempLayer ).html(resourceHtml);
		$.layer_table.event.eventTable();
	};
	
	jQuery.layer_table.event = {
			
			makeTableRow : function( celCount ){
				var celHtml= '';
				celHtml += '<div class="row" >';
				for(var i=0; i < celCount ; i += 1) {
					celHtml += '<textarea class="cel" name="cel_'+i+'"></textarea>';
				}
				celHtml += '<button type="button" class="btn-table row-del">행삭제</button>';
				celHtml += '</div>';
				
				return celHtml;
			},
			
			eventTable : function () {
				
				$("#insertTableApply").on("click", function () {
					var row = $.trim($("#table_row").val());
					var cel = $.trim($("#table_cell").val());
					
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
							tableHtml += $.layer_table.event.makeTableRow( cel );; 
						}
						
						$(".preview").empty();
						$(".preview").append( tableHtml );
						
						//행삭제
						$(".row", ".wrap_layer").on("click", ".row-del", function () {
							$(this).parent().remove(); 
							var rowLength = $(".preview").find('.row').length;
							$("#table_row").val(rowLength);
						});
						G_TABLE.row = row;
						G_TABLE.cel = cel;
					}
				});
				
				$("#btnLayerOk").on("click", function () {
					var markupHtml = '';
					var cel = G_TABLE.cel;
					if( $(".preview").find("div").length < 1 ){
						alert("입력된 행/열 이 없습니다. ");
						return false;
					}
					
					for (var i =0 ; i < cel ; i += 1) {
						markupHtml += "||셀제목";
						if( i == (cel-1) ) {
							markupHtml += "|| \n";							
						}
					}
					
					$('.preview').find('div').each(function ( ) {
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
					
					$.textInsert(G_TABLE.textEditor, markupHtml, "", "");
					
					$("#" + G_TABLE.tempLayer ).empty();
					return false; 
				});	
				
				$("#btnLayerClose").on("click", function () {
					$("#" + G_TABLE.tempLayer ).empty();
					return false;
				});
			}
			
	};
})(jQuery);