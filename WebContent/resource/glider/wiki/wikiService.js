(function ($) {
	"use strict";

	GliderWiki.namespace("Wiki");

	GliderWiki.Wiki.Write = Class.extend({
		init : function ($writeArea, $editMode, $we_space_idx, $we_wiki_idx){
			var me = this;
			me.$writeArea = $writeArea;
			me.$editMode = $editMode+"Mode";
			me.$writeForm = $("#wikiForm", me.$writeArea);

			me.$we_space_idx = $we_space_idx;
			me.$we_wiki_idx = $we_wiki_idx;

		},

		create : function () {
			var me = this;

			me.writeFormSubmit();
		},
		writeFormSubmit : function () {
			var me = this;
			var saveWiki = $("#saveWiki", me.$writeArea);

			saveWiki.bind("click", function (e) {
				e.preventDefault();

				var title = $("#we_wiki_title", me.$writeArea).val();
				var contents = $("#wikiEditor", me.$writeArea).val();

				if(GliderWiki.Utils.isEmpty(title) || title.length < 3) {
					GliderWiki.alert('알림', '제목은 3글자 이상 입력하셔야 합니다. ');
					return false;
				}

				if(GliderWiki.Utils.isEmpty(contents) || contents.length < 4) {
					GliderWiki.alert('알림', '내용은 4글자 이상 입력 하셔야 합니다.');
					return false;
				}
				
				if (me.$editMode === 'editMode') {
					var weEditText = $("#weEditText").val();
					if(GliderWiki.Utils.isEmpty(weEditText) || weEditText.length < 2) {
						GliderWiki.alert('알림', '위키 수정시 수정 사유를 반드시 입력해야 합니다.');
						return false;
					}
				}

				if(me.$editMode === 'newMode') {
					me.newMode();
				}else if (me.$editMode === 'editMode') {
					me.editMode();
				}else if (me.$editMode === 'replyMode') {
					me.replyMode();
				}else if (me.$editMode === 'tempMode') {
					me.tempMode();
				}

			});
		},

		newMode : function () {
			var me = this;


			GliderWiki.confirm("확인창", "위키를 저장하시겠습니까?", function () {

				$.ajax({
					type : "POST",
					url : '/wiki/new',
					data : me.$writeForm.serialize(),
					dataType : "json",
					beforeSend : function (jqXHR, settings) {
						$.loadingBar();
					},
					success : function (res) {
						var status = res.status;
						var response = res.response;

						GliderWiki.alert("저장성공",response.message);

						if(status === 'SUCCESS'){
							$("#okBtn").click(function () {
								$.form.get(response.redirectUrl);
							});
						}
					},
					complete : function (jqXHR, settings){
						$.loadingBar.fadeOut();
					}

				});

			} );
		},

		editMode : function () {
			var me = this;
			
			GliderWiki.confirm("확인창", "위키를 수정하시겠습니까?", function () {

				$.ajax({
					type : "POST",
					url : '/wiki/edit',
					data : me.$writeForm.serialize(),
					dataType : "json",
					beforeSend : function (jqXHR, settings) {
						$.loadingBar();
					},
					success : function (res) {
						var status = res.status;
						var response = res.response;

						GliderWiki.alert("수정성공",response.message);

						if(status === 'SUCCESS'){
							$("#okBtn").click(function () {
								$.form.get(response.redirectUrl);
							});
						}
					},
					complete : function (jqXHR, settings){
						$.loadingBar.fadeOut();
					}

				});

			} );
		},

		replyMode : function () {
			var me = this;
			GliderWiki.confirm("확인창", "위키를 저장하시겠습니까?", function () {

				$.ajax({
					type : "POST",
					url : '/wiki/sub/'+me.$we_wiki_idx,
					data : me.$writeForm.serialize(),
					dataType : "json",
					beforeSend : function (jqXHR, settings) {
						$.loadingBar();
					},
					success : function (res) {
						var status = res.status;
						var response = res.response;

						GliderWiki.alert("저장성공",response.message);

						if(status === 'SUCCESS'){
							$("#okBtn").click(function () {
								$.form.get(response.redirectUrl);
							});
						}
					},
					complete : function (jqXHR, settings){
						$.loadingBar.fadeOut();
					}

				});

			} );
		},

		tempMode : function () {
			var me = this;
			GliderWiki.confirm("확인창", "위키를 임시저장하시겠습니까?", function () {

				$.ajax({
					type : "POST",
					url : '/wiki/new/temp',
					data : me.$writeForm.serialize(),
					dataType : "json",
					beforeSend : function (jqXHR, settings) {
						$.loadingBar();
					},
					success : function (res) {
						var status = res.status;
						var response = res.response;

						GliderWiki.alert("임시저장성공",response.message);

						if(status === 'SUCCESS'){
							$.form.get(response.redirectUrl);
						}
					},
					complete : function (jqXHR, settings){
						$.loadingBar.fadeOut();
					}

				});

			} );
		}
	});

})(jQuery);