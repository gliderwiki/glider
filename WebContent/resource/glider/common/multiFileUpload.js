(function ($) {
	"use strict";

	GliderWiki.namespace("File");

	GliderWiki.File.Upload = Class.extend({
		init: function($uploadArea) {
			var me = this;
			me.$uploadArea = $uploadArea;
		},
		action: function() {
			var me = this;

			me.check();
			me.showPreImage();
			me.fileSubmit();
		},
		fileSubmit: function() {
			var me = this;
			var $uploadForm = $("#uploadForm", me.$uploadArea);
			var $uploadBtn = $(".uploadBtn", me.$uploadArea);
			
			$uploadBtn.bind("click", function() {
				var $uploadFileCnt = $(".MultiFile-label").length;
				
				if($uploadFileCnt == 0) {
					alert("업로드할 파일을 선택하세요!");
					return false;
				}

				if(confirm("파일을 업로드하시겠습니까?")) {
					$uploadForm.submit();
				}
			});
		},
		check: function() {
			var me = this;
			var $uploadSelectBtn = $("#uploadFile", me.$uploadArea);
			
			$uploadSelectBtn.MultiFile({
				max:5,
				accept:'gif|jpg|bmp|png|jpeg',
				STRING: {
					file: '<span class="uploadList" title="priview Image" style="cursor:hand">$file</span>',
					remove: '<img src="resource/wiki/image/remove.gif" height="16" width="16" alt="x"/>',
					duplicate:'$file은 이미 추가된 파일입니다',
					selected:'Selecionado: $file',
					denied:'$ext는 업로드할 수 없는 확장자입니다'
				}
			});
		},
		showPreImage: function() {
			var me = this;
			var $imgPreview = $("#imgPreview", me.$uploadArea);
			var $viewBtn = $("span.uploadList", me.$uploadArea);

			$viewBtn.live("click", function() {
				var fileName = $(this).text();
				var arrFileName = fileName.split(".");
				$imgPreview.html("<img src=/attachments/"+arrFileName[0]+"?ext="+arrFileName[1]+">");
			});

			return false;
		}
	});
})(jQuery);