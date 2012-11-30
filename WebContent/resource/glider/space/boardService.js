(function ($) {
	"use strict";

	GliderWiki.namespace("Board");

	GliderWiki.Board.Write = Class.extend({
		init: function($writeForm) {
			var me = this;
			me.$writeForm = $writeForm;			
		},
		create: function() {
			var me = this;
			
			me.writeFormSubmit();			
		},
		writeFormSubmit: function() {
			var me = this;
			
			me.$writeForm.on("submit", function(e) {
				e.preventDefault();
				
				var title = $("#we_bbs_title", me.$writeForm).val();
				var text = $("#we_bbs_text", me.$writeForm).val();
				var bbsIdx = $("#we_bbs_idx", me.$writeForm).val();
				var method = $("#_method", me.$writeForm);
				var noSpam = $("#noSpam").val();
				var randomKey = $("#randomKey").val();
				
				if(GliderWiki.Utils.isEmpty(title)) {
					GliderWiki.alert("에러","제목을 입력하셔야 합니다.");
					return false;
				}
				
				if(GliderWiki.Utils.isEmpty(noSpam) || noSpam != randomKey) {
					GliderWiki.alert("에러","보안 문자가 올바르지 않습니다.");
					return false;
				}
				
				if(GliderWiki.Utils.isEmpty(text)) {
					GliderWiki.alert("에러","내용을 입력하셔야 합니다.");
					return false;
				}
				
				if(!GliderWiki.Utils.isEmpty(bbsIdx)) {
					method.val("PUT");
				}
				
				this.submit();
			});			
		}		
	});
})(jQuery);