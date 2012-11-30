(function ($) {
	"use strict";

	if(typeof window.GliderWiki == "undefined") {
		window.GliderWiki = {};
	}

	GliderWiki.namespace = function (ns) {
		var parts = ns.split("."),
			parent = GliderWiki,
			i;

		if(parts[0] === "GliderWiki") {
			parts = parts.slice(1);
		}

		for(i = 0; i < parts.length; i++) {
			if(typeof parent[parts[i]] === "undefined") {
				parent[parts[i]] = {};
			}

			parent = parent[parts[i]];
		}

		return parent;
	};

	GliderWiki.ajaxCommonErrorProcessor = function(event, jqXHR, ajaxSettings, error) {
		if(jqXHR.status == 403) {
			var errorMessage = jqXHR.getResponseHeader("errorMessage");
			if(errorMessage != undefined && errorMessage == "auth.restrict") {
				GliderWiki.alert("공간개설","회원님은 공간을 개설할 수 없습니다.\n 관리자에게 문의하세요.");
			}
		}
	};

	$.ajaxSetup({
		cache : false,
		dataType : "json",
		timeout : 60000, // 60 seconds
		type : "post"
	});

	$(document).ajaxError(GliderWiki.ajaxCommonErrorProcessor);

	// DIM으로 문구경고창 출력
	GliderWiki.alert = function (title, message) {
		var escaped = GliderWiki.Utils.escapeHtml(message);
		escaped = escaped.split("\n").join("<br/>");

		var $alertMessage = $("<section class='layer_comm'>"
				+ "<a href='#' role='button' class='btn_close alertModalClose' title='닫기'>X</a>"
				+ "<div class='box'>"
				+ "<div class='tit'>"
				+ "<strong>"+title+"</strong>"
				+ "</div>"
				+ "<div class='cont'>"
				+ "<p>"+escaped+"</p>"
				+ "</div>"
				+ "<div class='wrap_btn center'>"
				+ "	<a style='cursor:pointer' role='button' class='btn' id='okBtn'>확 인</a>"
				+ "</div>"
		+ "</div>"
		+ "</section>");

		$("#okBtn, .alertModalClose", $alertMessage).bind("click", function () {
			$alertMessage.aaModalDialog("destroy");
			$alertMessage.remove();
			return false;
		});

		GliderWiki.viewMoalWindow($alertMessage);
	};

	GliderWiki.confirm = function(title, message, okFunction, cancelFunction) {
		var escaped = GliderWiki.Utils.escapeHtml(message);
		escaped = escaped.split("\n").join("<br/>");

		var $confirmMessage = $("<section class='layer_comm'>"
				+ "<a href='#' role='button' class='btn_close alertModalClose' title='닫기'>X</a>"
				+ "<div class='box'>"
				+ "<div class='tit'>"
				+ "<strong>"+title+"</strong>"
				+ "</div>"
				+ "<div class='cont'>"
				+ "<p>"+escaped+"</p>"
				+ "</div>"
				+ "<div class='wrap_btn center'>"
				+ "	<a style='cursor:pointer' role='button' class='btn' id='okBtn'>확 인</a>"
				+ "	<a style='cursor:pointer' role='button' class='btn' id='cancelBtn'>취 소</a>"
				+ "</div>"
		+ "</div>"
		+ "</section>");

		$("#okBtn", $confirmMessage).unbind("click").bind("click", function() {
			$confirmMessage.aaModalDialog("destroy");
			$confirmMessage.remove();

			if (typeof okFunction == "function") {
				okFunction();
			}

			return false;
		});

		$("#cancelBtn, .alertModalClose", $confirmMessage).unbind("click").bind("click", function () {
			$confirmMessage.aaModalDialog("destroy");
			$confirmMessage.remove();

			if (typeof cancelFunction == "function") {
				cancelFunction();
			}

			return false;
		});

		GliderWiki.viewMoalWindow($confirmMessage);
	};

	// DIM으로 미리보기 출력
	GliderWiki.preview = function (str, title) {
		var $alertMessage = $("<section class='layer_comm desc'>"
				+ "<a href='#' role='button' class='btn_close alertModalClose' title='닫기'>X</a>"
				+ "<div class='box'>"
				+ "<div class='tit' style='padding-top:15px;padding-left:10px'>"
				+ title
				+ "</div>"
				+ "<div class='cont' style='overflow-y: auto; max-height: 420px;'>"
				+ "<p>"+str+"</p>"
				+ "</div>"
				+ "<div class='wrap_btn center'>"
				+ "<button type='button' class='btn big' id='okBtn'>확인</button>"
				+ "</div>"
				+ "</div>"
				+ "</section>");

		$("#okBtn, .alertModalClose", $alertMessage).bind("click", function () {
			$alertMessage.aaModalDialog("destroy");
			$alertMessage.remove();
			return false;
		});

		GliderWiki.viewMoalWindow($alertMessage);
	};

	GliderWiki.viewMoalWindow = function($alertMessage) {
		$("body").append($alertMessage);

		$alertMessage.aaModalDialog({
			zIndex : 50000
		});

		$alertMessage.aaModalDialog("open");

		$("#okBtn", $alertMessage).focus();
	};


	GliderWiki.checkAccessSpace = function (loginIdx) {

		var checkBtn = $(".checkAccessBtn");

		checkBtn.live("click", function(e) {
			e.preventDefault();
			var spaceIdx = $(this).data("spaceIdx");
			var spaceName = $(this).data("spaceName");
			var authorityType = $(this).data("authorityType");
			var wikiIdx = $(this).data("wikiIdx");
			var adminIdx = $(this).data("adminUser");
			var goUrl;
			var errorMsg;

			if(GliderWiki.Utils.isEmpty(wikiIdx)) {
				goUrl = "/space/main/"+spaceIdx;
				errorMsg = spaceName + "공간 조회권한이 없습니다.";
			}else{
				goUrl = "/wiki/"+wikiIdx;
				errorMsg = "해당 위키 조회권한이 없습니다.";
			}

			if (authorityType == 'ALLGROUP' && loginIdx == '0') {	// 게스트 유저일 경우 일반 공간은 조회 가능
				//alert("goUrl : " +goUrl);
				location.href=goUrl;
			} else if(authorityType == 'ALLGROUP' || adminIdx == loginIdx) {
				location.href=goUrl;
			} else{
				$.post("/space/checkSearchSpaceInfo", {spaceIdx:spaceIdx, authorityType:authorityType}, function(data){

					if(data == 'SUCCESS') {
						location.href=goUrl;
					}else{
						GliderWiki.alert("조회권한없음", errorMsg);
					}
				});
			}
		});
	};

	$.form = function(){
		var createForm = function(method, url, params) {
            var form = $('<form name="jquery.form" method="post"/>').attr('action', url);
            form.append('<input type="hidden" name="_method" value="' + method + '" />');
            form.appendTo('body');

            if(params) {
                for(var key in params)
                    form.append('<input type="hidden" name="' + key + '" value="' + params[key] + '" />');
            }

            return form;
        };
        return {
            get: function(url, params) {
            	createForm('get', url, params).submit();
            },
            post: function(url, params) {
                createForm('post', url, params).submit();
            }
        };
	}();


	GliderWiki.namespace("Utils");

	GliderWiki.Utils.escapeHtml = function (str) {
		if (str === null || str.length === 0) {
			return "";
		}

		if($ !== undefined) {
			//return $('<div/>').text(str).html()
		}

		var escaped = str.replace(/&/g, "&amp;");
		escaped = escaped.replace(/</g, "&lt;");
		escaped = escaped.replace(/>/g, "&gt;");
		escaped = escaped.replace(/"/g, "&quot;");
		escaped = escaped.replace(/'/g, "&#39;"); // Do Not use &apos; -
		// IE does not allow it.''
		return escaped;
	};

	GliderWiki.Utils.isEmpty = function (val) {
		var is_type = typeof val;

		if (val == null || val == 'undefined' || typeof val == 'undefined') {
			return true;
		} else if (is_type == 'string') {
			val = val.replace(/\s+/g, '');
			if (val == '' || val.length == 0) {
				return true;
			}
		}

		return false;
	};

	GliderWiki.namespace("Web");

	GliderWiki.Web.isLogined = function() {
		return !GliderWiki.LoginUser.isGuest;
	};

	/*
	 * 다이얼로그 지정 $(".dialog").aaModalDialog({zIndex: 10});
	 *
	 * 열기 $(".dialog").aaModalDialog("open");
	 *
	 * 닫기 $(".dialog").aaModalDialog("close");
	 *
	 * 다이얼로그 기능 삭제 $(".dialog").aaModalDialog("destroy");
	 *
	 * 기본 옵션 :
	 *
	 *   - zIndex : z-index
	 *
	 *   - windowWidth: 창 넓이, null 이면 브라우저 창 높이
	 *
	 *   - windowHeight : 창 높이, null 이면 브라우저 창 높이
	 *
	 *   - top : 위에서부터 위치 고정시 값 지정. 지정안하면 브라우저 창 높이와 다이얼로그 크기를 계산하여 중앙 배치
	 *
	 *   - position : fixed 혹은 absolute. fixed이면 고정 위치로 스크롤 불가, absolute이면 페이지에 대한 가변위치로 스크롤 가능.
	 *
	 */
	$.fn.aaModalDialog = function (userOptions) {
		if (typeof userOptions === "string") {
			$.fn.aaModalDialog.command(this, userOptions);
			return;
		}

		var options = $.extend({
			zIndex : 1,
			windowWidth : null,
			windowHeight : null,
			position: 'fixed'
		}, $.fn.aaModalDialog.defaults, userOptions);

		var $dialog = this;
		$dialog.css("position", options.position);
		$dialog.css("z-index", options.zIndex + 1);
		$dialog.data("options", options);

		var $modalContainer = $("<div class='aa_modal_container hidden'></div>");
		$modalContainer.css("z-index", options.zIndex);

		$dialog.data("modalContainer", $modalContainer);
		$("body").append($modalContainer);

		$.fn.aaModalDialog.resizeModalDialog($modalContainer, $dialog, options);

		function windowResizeEventListener() {
			$.fn.aaModalDialog.resizeModalDialog($modalContainer, $dialog, options);
		}

		$dialog.data("windowResizeEventListener", windowResizeEventListener);

		$(window).resize(windowResizeEventListener);
	};

	$.fn.aaModalDialog.defaults = {
		zIndex : 1,
		windowWidth : null,
		windowHeight : null,
		top: null
	};

	$.fn.aaModalDialog.resizeModalDialog = function ($modalContainer, $dialog, options) {

		var windowWidth = $(window).width();
		var windowHeight = $(window).height();

		var hasOptionWidthHeight = options.windowWidth !== null && options.windowHeight !== null;

		if (hasOptionWidthHeight) {
			windowWidth = options.windowWidth;
			windowHeight = options.windowHeight;
		}


		$modalContainer.width($(window).width());
		$modalContainer.height($(window).height());

		if (options.top == null) {
			var top = (windowHeight - $dialog.height()) / 2;
			if (top < 0) {
				top = 0;
			}
		} else {
			top = options.top;
		}

		var left = (windowWidth - $dialog.outerWidth()) / 2;
		if (left < 0) {
			left = 0;
		}

		$dialog.css("top", top + "px");
		$dialog.css("left", left + "px");
	};

	$.fn.aaModalDialog.command = function ($dialog, command) {
		var $modalContainer = $dialog.data("modalContainer");
		switch (command) {
		case "close":
			$dialog.addClass("hidden");
			$modalContainer.addClass("hidden");
			break;
		case "open":
			var windowResizeEventListener = $dialog.data("windowResizeEventListener");

			$dialog.removeClass("hidden");
			$modalContainer.removeClass("hidden");
			windowResizeEventListener();
			break;
		case "destroy":
			$dialog.data("modalContainer", null);
			$modalContainer.remove();
			break;
		}
	};
}(jQuery));


/* Simple JavaScript Inheritance
 * By John Resig http://ejohn.org/
 * MIT Licensed.
 */
// Inspired by base2 and Prototype
(function(){
  var initializing = false, fnTest = /xyz/.test(function(){xyz;}) ? /\b_super\b/ : /.*/;
  // The base Class implementation (does nothing)
  this.Class = function(){};

  // Create a new Class that inherits from this class
  Class.extend = function(prop) {
    var _super = this.prototype;

    // Instantiate a base class (but only create the instance,
    // don't run the init constructor)
    initializing = true;
    var prototype = new this();
    initializing = false;

    // Copy the properties over onto the new prototype
    for (var name in prop) {
      // Check if we're overwriting an existing function
      prototype[name] = typeof prop[name] == "function" &&
        typeof _super[name] == "function" && fnTest.test(prop[name]) ?
        (function(name, fn){
          return function() {
            var tmp = this._super;

            // Add a new ._super() method that is the same method
            // but on the super-class
            this._super = _super[name];

            // The method only need to be bound temporarily, so we
            // remove it when we're done executing
            var ret = fn.apply(this, arguments);
            this._super = tmp;

            return ret;
          };
        })(name, prop[name]) :
        prop[name];
    }

    // The dummy class constructor
    function Class() {
      // All construction is actually done in the init method
      if ( !initializing && this.init )
        this.init.apply(this, arguments);
    }

    // Populate our constructed prototype object
    Class.prototype = prototype;

    // Enforce the constructor to be what we expect
    Class.prototype.constructor = Class;

    // And make this class extendable
    Class.extend = arguments.callee;

    return Class;
  };
})();