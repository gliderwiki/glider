(function ($) {
	"use strict";

	GliderWiki.namespace("Notification");

	GliderWiki.Notification.Perception = Class.extend({
		init: function($alarmLayer, $encryptAChannel) {
			var me = this;
			me.$alarmLayer = $alarmLayer;
			me.$encryptAChannel = $encryptAChannel;
		},
		process: function() {
			var me = this;
			me.connectSocket();
			me.showNewNotiCount();
			me.realTimeViewCheckEvent();
		},
		showNewNotiCount: function() {
			var me = this;

			$.ajax({
				type: "get",
				url : "/noti/newNotiCount",
				dataType : "html",
				async:true,
				disableGlobalErrorProcessor:true,
				error: function() {	},
				success: function(data){
					var newNotiCount = data;
					$("#notificationCount").text(newNotiCount);
				}
			});
		},
		connectSocket: function() {
			var me = this;
			var timeOut;
			var socket;
			var socketCliConf = {
				'connect timeout' : 3000,
				'reconnect': false,
				'sync disconnect on unload' : false
			};

			try {
				socket = io.connect('http://gliderwiki.org:3000?myChannel='+me.$encryptAChannel, socketCliConf);
				socket.on(me.$encryptAChannel, function(data){
					me.showNewNotiCount();

					$.ajax({
						dataType : "text",
						type: "get",
						url : "/noti/view/check",
						async:true,
						disableGlobalErrorProcessor:true,
						error: function() {	},
						success: function(isRealView){
							if(isRealView == "Y") {
								var realNotiHtml = me.getRealTimeNotiHtml(data.message);
								me.$alarmLayer.html(realNotiHtml);
								me.$alarmLayer.css("display","block");

								if( me.$alarmLayer.css('display') != 'none' ){
									timeOut = setTimeout(function(){
										me.$alarmLayer.css("display","none");
									}, 10000);
								}
							}
						}
					});
				});
				socket.on('disconnect', function(data){
				});
				window.onbeforeunload = function() {
					socket.disconnect();
				};
			} catch(ex) {

			}
		},
		realTimeViewCheckEvent: function() {

			$("#checkRealTimeNotification").on("click", function() {
				var isView = $("#checkRealTimeNotification").is(":checked");
				var checked;

				if(isView == true) {
					checked = "Y";
				}else{
					checked="N";
				}

				$.post("/noti/view/update", {isChecked:checked});
			});
		},
		getRealTimeNotiHtml : function(message){
			var me = this;

			var liHtml = '<ul class="list_alimi">';
			liHtml += '<li>';
			liHtml += '<a href="#" class="cont">'+message+'</a>';
			liHtml += '</li>';
			liHtml += '</ul>';

			return liHtml;
		}
	});
})(jQuery);