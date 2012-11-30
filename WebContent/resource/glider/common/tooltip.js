(function ($) {
	"use strict";

	GliderWiki.namespace("ToolTip");

	GliderWiki.ToolTip.Action = Class.extend({
		init: function($loginUserIdx) {
			var me = this;
			me.$loginUserIdx = $loginUserIdx;
		},
		process: function() {
			var me = this;

			me.showTooltip();
		},
		showTooltip: function() {
			var me = this;

			$(".tooltip").live("click", function(evt) {
				evt.preventDefault();

				me.closeToolTip();

				var userIdx = $(this).data("userIdx");
				var tooltip = $(this).parent().append(me.makeUserLayer(userIdx));

				return false;
			});

			$(document).on("click", function() {
				me.closeToolTip();
			});
		},
		closeToolTip: function() {
			var me = this;

			$(".layer_user").remove();
		},
		makeUserLayer: function(targetUserIdx) {
			var me = this;
			var tooltip = "<div class=\"layer_user\">";
			tooltip += "<ul>";

			if(me.$loginUserIdx != 0 && me.$loginUserIdx != targetUserIdx) {
				tooltip += "	<li>";
				tooltip += "		<a href=\"javascript:addUserListInFriend("+targetUserIdx+")\">";
				tooltip += "			인맥추가";
				tooltip += "		</a>";
				tooltip += "	</li>";
			}

			tooltip += "	<li>";
			tooltip += "		<a href=\"javascript:viewProfile("+targetUserIdx+")\">";
			tooltip += "			프로필보기";
			tooltip += "		</a>";
			tooltip += "	</li>";
			tooltip += "</ul>";
			tooltip += "</div>";

			return tooltip;
		}
	});
})(jQuery);