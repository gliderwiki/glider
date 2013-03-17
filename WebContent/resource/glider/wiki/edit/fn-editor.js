(function() 
{
	/**
	 * @description : Editor의 버튼별 Action을 구현한다.  
	 * @param : textEditor( object - textarea ID )
	 * @return :  
	 * @example : 
	 * 
	 * $.editorAction(editor, $me, data);
	 */
    jQuery.editorAction = function(textEditor, $me, data, tempLayer) {
    	$.print("textEditor : " + textEditor);
    	$.print("$me : " + $me);
		$.print("data.mode : " + data.mode);
		
		if(data.mode == 'append') {
			// 현재 블럭 위치에 지정된 태그를 감싼다. Textarea, btn-object, btn-data 
			$.appendTag(textEditor, $me, data);
		} else if(data.mode == 'insert') {
			// 현재 커서 위치에 지정된 태그를 추가한다.
			$.insertTag(textEditor, $me, data);
		} else if(data.mode == 'font') {
			$.appendTag(textEditor, $me, data);
		} else if(data.mode == 'color') {
			$.layer_select.color.dropdownColor(textEditor, $me, data);
		} else if(data.mode == 'layer') {
			// 현재 커서 위치에 레이어 입력 태그를 추가한다.
			if (document.selection) { //IE
				textEditor.currentPos = document.selection.createRange().duplicate();	
			}
			var $layer = $.makeLayer.getLayer( data.type );
			$layer({
				'textEditor' : textEditor,
				'tempLayer' : tempLayer
			});
		}
    };
    
	
})(jQuery);   