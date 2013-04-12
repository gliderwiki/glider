(function() 
{
	/**
	 * @description : 블럭 대상의 시작점과 종료점에 지정된 태그를 감싼다. 
	 * @param : textEditor( object - textarea ID )
	 * @param : before - 시작점 
	 * @param : after - 종료점   
	 * @param : text - 감싸야 할 대상 문자열     
	 * @return : None
	 * @example : 
	 * 
	 * var editor = document.getElementById("markupEditor");
	 * $.appendTag(editor, $(this), $(this).data());
	 */
	jQuery.richEditTag = function(textEditor, $me, data) {
		if ( typeof( $("#richHidden").val() ) == 'undefined' ){
			var hidden = "<div id='richHidden' style='display:none'></div>";
			$(textEditor).append(hidden);
		}
		
        var sel = $.textLocation(textEditor);
        var val = textEditor.value;
    	var startIndex = sel.start;							// 블럭 시작 지점 
        var endIndex = startIndex + sel.text.length;		// 블럭 종료 지점 
        //console.info(sel);
        //console.info("val ::"+val);
        //console.info("#richHidden1 ::"+$("#richHidden").val());
        
        if( !(sel.start == 0 && sel.end == 0) && data.type == 'outdent' ) {
        	sel.text = sel.text.replaceAll("[t]", "");
        	textEditor.value = val.slice(0, sel.start) + sel.text + val.slice(sel.end);
            $.rangeTag(textEditor, startIndex, endIndex);
            
        }else if( !(sel.start == 0 && sel.end == 0) && (data.type == 'copy'||data.type == 'cut')  ) {
        	$("#richHidden").val(sel.text);
        	if( data.type == 'cut' ){
        		sel.text = '';
        		textEditor.value = val.slice(0, sel.start) + sel.text + val.slice(sel.end);
                $.rangeTag(textEditor, startIndex, endIndex);
        	}
         
        }else if( sel.start == 0 && sel.end == 0 && data.type == 'paste' ) {
        	// 블럭이 없는 경우 현재 커서에서 태그를 삽입 한다.
        	$.textInsert(textEditor, $("#richHidden").val(), "", "");
        	
        }else if( !(sel.start == 0 && sel.end == 0) && data.type == 'paste' ) {
        	sel.text = $("#richHidden").val();
        	textEditor.value = val.slice(0, sel.start) + sel.text + val.slice(sel.end);
            $.rangeTag(textEditor, startIndex, endIndex);
            
        }
        //console.info("#richHidden2 ::"+$("#richHidden").val());
    };
    
	
})(jQuery);   