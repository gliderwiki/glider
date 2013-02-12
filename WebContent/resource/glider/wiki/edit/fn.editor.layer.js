(function() 
{
	
	var layerArray = new Array();
	
	jQuery.makeLayer = {
		
		init : function ( ) {
			layerArray[layerArray.length] = {id:$.layer_table,name:"table"};
		},
		
		addLayer : function ( $layer ,layerName ){
			layerArray[layerArray.length] = {id:$layer,name:layerName};
		},
		
		getLayer : function ( layerName ){
			for( var i=0; i<layerArray.length; i++ ){
				if( layerArray[i].name == layerName ){
					return layerArray[i].id;
				}
			}
		}
		
	};
	
})(jQuery);