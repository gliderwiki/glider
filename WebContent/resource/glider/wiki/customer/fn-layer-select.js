(function() 
{
	
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
	
	jQuery.layer_select = function(){
	};
	/**
	 * 사용자 프로필 레이어 팝업을 출력한다. 
	 */
	jQuery.layer_select.color = {

		dropdownColor : function ( textEditor, $me, data ) {
				
			this.hideAllDropdownColor();

			var colorItems = $("<div>").addClass('geditor_dropdown').css({'width':'210px', 'display' : 'none'});

			var colorLength = COLOR_BOX.colors.length;

			for( var i=0 ; i < colorLength ; i += 1) {
				var color = COLOR_BOX.colors[i];
				var swatch = $('<a rel="' + color + '" href="javascript:void(null);" class="geditor_color_link" ></a>').css({ 'backgroundColor': color });
				
				swatch.on('click', function (e) {
					e.preventDefault();
					var $me = $(this);
					var tagColor = $me.attr('rel').replace('#','');
					var param = ({
							'before' : '[' + data.before + '|' + tagColor + ']',
							'center' : data.center,
							'after' : '[' + data.after + ']'
						});
					
					$.appendTag( textEditor, $me, param );
				});
				
				colorItems.append(swatch);

			}
			
			var top = $me.offset().top;
			var left = $me.offset().left;
			
			colorItems.appendTo($(document.body));
			
			colorItems.css({ position: 'absolute', left: left + 'px', top: top+30 + 'px' }).show();
			
			var hasClass = $me.attr("class");
			$(document.body).click(function () {
				if($(this).hasClass(hasClass) == false ) {
					$.layer_select.color.hideAllDropdownColor();
				} 
			});
		},

		hideAllDropdownColor : function () {
			$('.geditor_dropdown').remove();
		}
	};
	
})(jQuery);
