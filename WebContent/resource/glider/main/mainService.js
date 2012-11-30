(function ($) {
	"use strict";

	GliderWiki.namespace("Main");

	GliderWiki.Main.DashBoard = Class.extend({
		init: function($mainArea, $loginUserIdx) {
			var me = this;

			me.$mainArea = $mainArea;
			me.$loginUserIdx = $loginUserIdx;
		},
		action: function() {
			var me = this;
			/* 즐겨찾기 추가  */
			me.addFavorite();
			/* 즐겨찾기 삭제  */
			me.delFavorite();
			/* 최근 위키 목록 펼치기 */
			me.moreWikiList();
			/* 전체 위키 목록 펼치기 */
			me.moreAllWikiList();
			/* 최근 위키 목록 닫힘 */
			me.setInitRecentWikiList();
			/* 전체 위키 목록 닫힘 */
			me.setInitAllWikiList();
			/* 최근 생성된 공간 펼치기  */
			me.moreSpaceList();
			/* 최근 생성된 공간 닫힘 */
			me.setInitSpaceList();
			/* 최근 공지사항 펼치기 */
			me.moreNoticeList();
			/* 최근 공지사항 닫힘  */
			me.setNoticeList();
			/* 최근 업데이트 펼치기 */
			me.moreUpdate();
			/* 최근 업데이트 닫힘  */
			me.setUpdate();
			
		},
		addFavorite: function() {
			var me = this;

			$(document).on("click", ".addFavorBtn", function(e) {
				e.preventDefault();
				var spaceIdx = $(this).data("spaceIdx");
				var wikiIdx = $(this).data("wikiIdx");
				var type = $(this).data("favoriteType");
				var obj = $(this);

				if(type == 'SPACE') {
					GliderWiki.confirm("공간 즐겨찾기추가","해당공간을 즐겨찾기에 추가하시겠습니까?",
						function() {
							$.post("/space/addFavorite", {favoriteType:type,spaceIdx:spaceIdx,wikiIdx:0}, function(data){
								if(data == 1) {
									obj.removeClass("unstar");
									obj.removeClass("addFavorBtn");
									obj.addClass("star");
									obj.addClass("delFavorBtn");
									GliderWiki.alert("공간 즐겨찾기","추가되었습니다");
								}else{
									GliderWiki.alert("공간 즐겨찾기","추가되지 않았습니다");
								}
							});
						}
					);
				}else{
					GliderWiki.confirm("위키 즐겨찾기추가","해당위키를 즐겨찾기에 추가하시겠습니까?",
						function() {
							$.post("/space/addFavorite", {favoriteType:type,spaceIdx:spaceIdx,wikiIdx:wikiIdx}, function(data){
						 		if(data != null) {
						 			obj.removeClass("unstar");
									obj.removeClass("addFavorBtn");
									obj.addClass("star");
									obj.addClass("delFavorBtn");
									GliderWiki.alert("위키 즐겨찾기","추가되었습니다");
								} else {
									GliderWiki.alert("위키 즐겨찾기","추가되지 않았습니다");
								}
							})
						}
					);
				}
			});
		},
		delFavorite: function() {
			var me = this;

			$(document).on("click", ".delFavorBtn", function(e) {
				e.preventDefault();

				var spaceIdx = $(this).data("spaceIdx");
				var wikiIdx = $(this).data("wikiIdx");
				var type = $(this).data("favoriteType");
				var obj = $(this);

				if(type == 'SPACE') {
					GliderWiki.confirm("공간 즐겨찾기제거","해당공간을 즐겨찾기에서 삭제하시겠습니까?",
						function() {
							$.post("/delFavorite", {spaceIdx:spaceIdx, favoriteType:type}, function(data){
								if(data == 1) {
									obj.removeClass("star");
									obj.removeClass("delFavorBtn");
									obj.addClass("unstar");
									obj.addClass("addFavorBtn");
									GliderWiki.alert("공간 즐겨찾기","삭제되었습니다");
								}else{
									GliderWiki.alert("공간 즐겨찾기","삭제되지 않았습니다");
								}
							});
						}
					);
				}else{
					GliderWiki.confirm("위키 즐겨찾기제거","해당위키를 즐겨찾기에서 삭제하시겠습니까?",
						function() {
							$.post("/delFavorite", {wikiIdx:wikiIdx, favoriteType:type}, function(data){
								if(data == 1) {
									obj.removeClass("star");
									obj.removeClass("delFavorBtn");
									obj.addClass("unstar");
									obj.addClass("addFavorBtn");
									GliderWiki.alert("위키 즐겨찾기","삭제되었습니다");
								}else{
									GliderWiki.alert("위키 즐겨찾기","삭제되지 않았습니다");
								}
							});
						}
					);
				}

			});
		}, 
		setInitRecentWikiList : function() {
			var me = this;
			$(document).on("click", "#setRecent", function(e) {
				e.preventDefault();				
				var rowCount = $('#recentWiki > li').length;
				
				if(rowCount < 6) {
					GliderWiki.alert('알림', '최근 위키 목록을 더 이상 접어보기 할 수 없습니다.');
					return;
				}
				
				var userIdx = me.$loginUserIdx;
				var url =  "/moreitem/recent";
				var type = "recentWiki";
				
				// 초기 화면 세팅 공통 메소드 콜
				initList(userIdx, url, type);
			});
		}, 
		setInitAllWikiList : function() {
			var me = this;
			
			$(document).on("click", "#setAll", function(e) {
				e.preventDefault();				
				var rowCount = $('#allWiki > li').length;
				
				if(rowCount < 21) {
					GliderWiki.alert('알림', '전체 위키 목록을 더 이상 접어보기 할 수 없습니다.');
					return;
				}
				
				var userIdx = me.$loginUserIdx;
				var url =  "/moreitem/all";
				var type = "allWiki";
				
				// 초기 화면 세팅 공통 메소드 콜 
				initList(userIdx, url, type);				
			});
		}, 
		moreWikiList : function() {
			var me = this;
			
			$(document).on("click", "#getRecent", function(e) {
				e.preventDefault();				
				var rowCount = $('#recentWiki > li').length;
				var type = "recentWiki";
				var userIdx = me.$loginUserIdx;
				var url =  "/moreitem/recent";
				
				if(rowCount < 5) {
					GliderWiki.alert('알림', '더이상 조회 건수가 없습니다.');
					return;
				}
				// 내용 펼침 공통 메소드 콜 
				ajaxWiki(userIdx, url, type, rowCount);
			});
		}, 
		moreAllWikiList : function() {
			var me = this;
			
			$(document).on("click", "#getAll", function(e) {
				e.preventDefault();				
				var rowCount = $('#allWiki > li').length;
				var type = "allWiki";
				var userIdx = me.$loginUserIdx;
				var url =  "/moreitem/all";
				
				if(rowCount < 20) {
					GliderWiki.alert('알림', '더이상 조회 건수가 없습니다.');
					return;
				}
				// 내용 펼침 공통 메소드 콜
				ajaxWiki(userIdx, url, type, rowCount);				
			});
		}, 
		moreSpaceList : function() {
			var me = this;
			
			$(document).on("click", "#getSpace", function(e) {
				e.preventDefault();				
				var rowCount = $('#recentSpace > li').length;
				var userIdx = me.$loginUserIdx;
				
				if(rowCount < 5) {
					GliderWiki.alert('알림', '더이상 조회 건수가 없습니다.');
					return;
				}
				
				ajaxSpace(userIdx, rowCount, 5);
				
			});
		},
		setInitSpaceList : function() {
			var me = this;
			
			$(document).on("click", "#setSpace", function(e) {
				e.preventDefault();				
				var rowCount = $('#recentSpace > li').length;
				var userIdx = me.$loginUserIdx;
				
				if(rowCount < 6) {
					GliderWiki.alert('알림', '최근 생성 공간을  더 이상 접어보기 할 수 없습니다.');
					return;
				}
				
				GliderWiki.confirm("알림", "최근 생성 공간  목록 펼침을 초기화 하겠습니까?",
					function() {
						$('#recentSpace > li').remove();
						ajaxSpace(userIdx, 0, 5);
					}
				);
				
			});
		},
		setNoticeList : function() {
			var me = this;
			$(document).on("click", "#setNotice", function(e) {
				e.preventDefault();				
				var rowCount = $('#recentNotice > li').length;
				
				if(rowCount < 6) {
					GliderWiki.alert('알림', '최근 공지사항 목록을 더이상 접어보기 할 수 없습니다.');
					return;
				}
				
				var userIdx = me.$loginUserIdx;
				var url =  "/moreitem/notice";
				var type = "recentNotice";
				
				// 초기 화면 세팅 공통 메소드 콜
				initNotice(userIdx, url, type);
			});
		}, 
		moreNoticeList : function() { 
			var me = this;
			
			$(document).on("click", "#getNotice", function(e) {
				e.preventDefault();				
				var rowCount = $('#recentNotice > li').length;
				var userIdx = me.$loginUserIdx;
				
				if(rowCount < 5) {
					GliderWiki.alert('알림', '더이상 조회 건수가 없습니다.');
					return;
				}
				
				ajaxNotice(userIdx, rowCount, 5);
				
			});
		},
		moreUpdate : function() {
			var me = this;
			
			$(document).on("click", "#getUpdate", function(e) {
				e.preventDefault();				
				var rowCount = $('#recentUpdate > li').length;
				var userIdx = me.$loginUserIdx;
				
				if(rowCount < 5) {
					GliderWiki.alert('알림', '더이상 조회 건수가 없습니다.');
					return;
				}
				
				ajaxUpdate(userIdx, rowCount, 5);
				
			});
		}, 
		setUpdate : function() {
			var me = this;
			$(document).on("click", "#setUpdate", function(e) {
				e.preventDefault();				
				var rowCount = $('#recentUpdate > li').length;
				
				if(rowCount < 6) {
					GliderWiki.alert('알림', '최근 업데이트 목록을 더이상 접어보기 할 수 없습니다.');
					return;
				}
				
				var userIdx = me.$loginUserIdx;
				var url =  "/moreitem/update";
				var type = "recentUpdate";
				
				// 초기 화면 세팅 공통 메소드 콜
				initUpdate(userIdx, url, type);
			});
		}
	});
	
	
	/**
	 * ajax 콜 공통 함수 (space)
	 */	
	function ajaxSpace(userIdx, startRow, endRow) {
		$.ajax({
			type:"GET"
			,url:"/moreitem/space"
			,data:{"userIdx":userIdx,"startRow":startRow,"endRow":endRow}
			,dataType:"json"
			,success:function(rtnObj){						
				if(rtnObj.result == '1'){
					callBackSpace(rtnObj);
				} else if(rtnObj.result == '0'){
					GliderWiki.alert('알림', '더이상 조회 건수가 없습니다.');
				} else if(rtnObj.result == '-1'){
					GliderWiki.alert('알림', '에러가 발생하였습니다.');
				}
			}
		});
	}
	
	
	// 펼침기능 초기화 
	function initList(userIdx, url, type) {
		var msg;
		
		if(type == "allWiki") {
			msg = "전체 위키 목록 펼침을 초기화 하겠습니까?"; 
		} else {
			msg = "최근 위키 목록 펼침을 초기화 하겠습니까?";
		}
		
		GliderWiki.confirm("알림", msg,
			function() {
				$('#'+type+' > li').remove();
				ajaxWiki(userIdx, url, type, 0); 
			}
		);
	}
	
	//공지사항 초기화 
	function initNotice(userIdx, url, type) {
		GliderWiki.confirm("알림", "최근 공지사항을 초기화 하겠습니까?",
			function() {
			$('#'+type+' > li').remove();
			ajaxNotice(userIdx, 0, 5);
		});
	}
	
	//최근 업데이트 초기화 
	function initUpdate(userIdx, url, type) {
		GliderWiki.confirm("알림", "최근 업데이트 목록을 초기화 하겠습니까?",
				function() {
			$('#'+type+' > li').remove();
			ajaxUpdate(userIdx, 0, 5);
		});
	}
	
	// 전체 위키, 최근 위키 내용 펼침 
	function ajaxWiki(userIdx, url, type, rowCount) {
		var endRow;
		
		if(type == "allWiki") {
			endRow = 20;
		} else {
			endRow = 5;
		}
		
		$.ajax({
			type:"GET"
			,url:url
			,data:{"userIdx":userIdx,"startRow":rowCount,"endRow":endRow}
			,dataType:"json"
			,success:function(rtnObj){					
				console.log("rtnObj : " + rtnObj);
				console.log("type : " + type);				
				if(rtnObj.result == '1'){
					callBackRecentWiki(rtnObj, type);
				} else if(rtnObj.result == '0'){
					GliderWiki.alert('알림', '더이상 조회 건수가 없습니다.');
				} else if(rtnObj.result == '-1'){
					GliderWiki.alert('알림', '에러가 발생하였습니다.');
				}
			}
		});
	}
	
	
	//ajax 콜 공통 함수 (notice)
	function ajaxNotice(userIdx, startRow, endRow) {
		$.ajax({
			type:"GET"
			,url:"/moreitem/notice"
			,data:{"userIdx":userIdx,"startRow":startRow,"endRow":endRow}
			,dataType:"json"
			,success:function(rtnObj){					
				if(rtnObj.result == '1'){
					callBackRecentNotice(rtnObj);
				} else if(rtnObj.result == '0'){
					GliderWiki.alert('알림', '더이상 조회 건수가 없습니다.');
				} else if(rtnObj.result == '-1'){
					GliderWiki.alert('알림', '에러가 발생하였습니다.');
				}
			}
		});
	}
	//ajax 콜 공통 함수 (update)
	function ajaxUpdate(userIdx, startRow, endRow) {
		$.ajax({
			type:"GET"
			,url:"/moreitem/update"
			,data:{"userIdx":userIdx,"startRow":startRow,"endRow":endRow}
			,dataType:"json"
			,success:function(rtnObj){					
				if(rtnObj.result == '1'){
					callBackRecentUpdate(rtnObj);
				} else if(rtnObj.result == '0'){
					GliderWiki.alert('알림', '더이상 조회 건수가 없습니다.');
				} else if(rtnObj.result == '-1'){
					GliderWiki.alert('알림', '에러가 발생하였습니다.');
				}
			}
		});
	}
	
	
	/**
	 * 최근 위키 row 출력 
	 */
	function callBackRecentWiki(rtnObj, type) { 
		if(rtnObj != null) {
			var recentWiki = rtnObj.recentList;
			var inHtml = "";
			var listSize = recentWiki.length;
			
			for(var i = 0 ; i < listSize ; i++){
				inHtml += "<li>";
				inHtml += "    <div class=\"title\">";
				inHtml += "    <a style=\"cursor:pointer\" class=\"checkAccessBtn\" data-space-idx=\""+recentWiki[i].we_space_idx+"\" data-wiki-idx=\""+recentWiki[i].idx+"\" data-authority-type=\""+recentWiki[i].we_view_privacy+"\" data-admin-user=\""+recentWiki[i].we_ins_user+"\">"+recentWiki[i].title+"</a>";
				inHtml += "    </div>";
				inHtml += "    <div class=\"time textCenter\">"+recentWiki[i].insert_date+"</div>";
				inHtml += "    <div class=\"user textCenter\">";
				inHtml += "    <span class=\"name\">";
				inHtml += "    <a style=\"cursor:pointer\" role=\"button\" class=\"tooltip\" data-user-idx=\""+recentWiki[i].we_ins_user+"\">"+recentWiki[i].we_user_nick+"</a>";
				inHtml += "    </span>";
				inHtml += "    </div>";
				inHtml += "    <div class=\"info\">";
				if(recentWiki[i].favorited > 0) {					
					inHtml += "    <a style=\"cursor:pointer\" role=\"button\" class=\"ico star delFavorBtn\" data-space-idx=\""+recentWiki[i].we_space_idx+"\" data-favorite-type=\"WIKI\" data-wiki-idx=\""+recentWiki[i].idx+"\"></a>";
				} else {
					inHtml += "    <a style=\"cursor:pointer\" role=\"button\" class=\"ico unstar addFavorBtn\" title=\"즐겨찾기추가\" data-favorite-type=\"WIKI\" data-wiki-idx=\""+recentWiki[i].idx+"\" data-space-idx=\""+recentWiki[i].we_space_idx+"\">즐겨찾기추가</a>";
				}
				
				if(recentWiki[i].we_view_privacy == 'ALLGROUP') {
					inHtml += "    <img src=\"/resource/glider/front/images/ic_all.jpg\" title=\"전체공개\">";
				} else if(recentWiki[i].we_view_privacy == 'GROUP') {
					inHtml += "    <a style=\"cursor:pointer\" role=\"button\" class=\"ico private\" title=\"멤버공개\">멤버공개</a>";
				} else {
					inHtml += "    <a style=\"cursor:pointer\" role=\"button\" class=\"ico mem-only\" title=\"구성원공개\">구성원공개</a>";
				}
				inHtml += "    </div>";
				inHtml += "</li>";
			}
			
			if(type == "allWiki") {
				$("#allWiki").append(inHtml);
			} else {
				$("#recentWiki").append(inHtml);
			}
		}
	}
	
	/**
	 * 최근 공간 row 출력 
	 */
	function callBackSpace(rtnObj) { 
		if(rtnObj != null) {
			var recentSpaceList = rtnObj.recentList;
			var inHtml = "";
			var listSize = recentSpaceList.length;
			
			for(var i = 0 ; i < listSize ; i++){
				inHtml += "<li>";
				inHtml += "    <div class=\"title\">";
				inHtml += "    <a style=\"cursor:pointer\" class=\"checkAccessBtn\" data-space-idx=\""+recentSpaceList[i].we_space_idx+"\" data-space-name=\""+recentSpaceList[i].we_space_name+"\" data-authority-type=\""+recentSpaceList[i].we_view_privacy+"\" data-admin-user=\""+recentSpaceList[i].we_ins_user+"\">"+recentSpaceList[i].we_space_name+"</a>";
				inHtml += "    </div>";
				inHtml += "    <div class=\"time textCenter\">"+recentSpaceList[i].insert_date+"</div>";
				inHtml += "    <div class=\"user textCenter\">";
				inHtml += "    <a style=\"cursor:pointer\" class=\"name tooltip\" data-user-idx=\""+recentSpaceList[i].we_ins_user+"\">"+recentSpaceList[i].we_user_nick+"</a>";
				inHtml += "    </div>";
				inHtml += "    <div class=\"info textCenter\">";
				if(recentSpaceList[i].favorited > 0) {					
					inHtml += "    <a style=\"cursor:pointer\" role=\"button\" class=\"ico star delFavorBtn\" data-space-idx=\""+recentSpaceList[i].we_space_idx+"\" data-favorite-type=\"SPACE\"></a>";
				} else {
					inHtml += "    <a style=\"cursor:pointer\" role=\"button\" class=\"ico unstar addFavorBtn\" title=\"즐겨찾기추가\" data-favorite-type=\"SPACE\" data-space-idx=\""+recentSpaceList[i].we_space_idx+"\">즐겨찾기제거</a>";
				}
				
				if(recentSpaceList[i].we_view_privacy == 'ALLGROUP') {
					inHtml += "    <img src=\"/resource/glider/front/images/ic_all.jpg\" title=\"전체공개\">";
				} else if(recentSpaceList[i].we_view_privacy == 'GROUP') {
					inHtml += "    <a style=\"cursor:pointer\" role=\"button\" class=\"ico private\" title=\"멤버공개\">멤버공개</a>";
				} else {
					inHtml += "    <a style=\"cursor:pointer\" role=\"button\" class=\"ico mem-only\" title=\"구성원공개\">구성원공개</a>";
				}
				inHtml += "    </div>";
				inHtml += "</li>";
			}

			$("#recentSpace").append(inHtml);

		}
	}
	
	/**
	 * 최근 공지사항 row 출력 
	 */
	function callBackRecentNotice(rtnObj) {
		if(rtnObj != null) {
			var myNotiList = rtnObj.recentList;
			var inHtml = "";
			var listSize = myNotiList.length;
			
			for(var i = 0 ; i < listSize ; i++){
				inHtml += "<li>";
				inHtml += "    <div class=\"title\">";
				inHtml += "    <a href=\"/space/"+myNotiList[i].we_space_idx+"/board/"+myNotiList[i].we_bbs_idx+"\">"+myNotiList[i].we_bbs_title+"</a>"
				inHtml += "    </div>";
				inHtml += "    <div class=\"time textRight\">"+myNotiList[i].we_ins_date+"</div>";
				inHtml += "</li>";
				
			}
			$("#recentNotice").append(inHtml);
		}
	}
	
	
	function callBackRecentUpdate(rtnObj) {
		if(rtnObj != null) {
			var recentUpdate = rtnObj.recentList;
			var inHtml = "";
			var listSize = recentUpdate.length;
			var title;
			var text; 
			for(var i = 0 ; i < listSize ; i++){
				if(recentUpdate[i].we_wiki_title.length > 19 ) { 
					title = recentUpdate[i].we_wiki_title.substr(0,19) + "...";
				} else { 
					title = recentUpdate[i].we_wiki_title; 
				}
				
				if(recentUpdate[i].we_wiki_text.length > 40) {
					text = recentUpdate[i].we_wiki_text.substr(0,40) + "...";
				} else {
					text = recentUpdate[i].we_wiki_text;
				}
				inHtml += "<li>";
				inHtml += "    <div class=\"thumb\">";
				inHtml += "        <img src=\"/resource/real"+recentUpdate[i].profileImg+"\" width=\"40px\" height=\"40px\" onerror=\"this.src='/resource/glider/front/images/default_img.jpg';\"/>";
				inHtml += "    </div>";
				inHtml += "    <div class=\"user\">";
				inHtml += "        <span class=\"name\">"; 
				inHtml += "            <a href=\"#\" role=\"button\" class=\"tooltip\" data-user-idx=\""+recentUpdate[i].we_user_idx+"\">"+recentUpdate[i].we_user_nick+"</a>";
				inHtml += "        </span>";
				inHtml += "        <span class=\"time\">"+recentUpdate[i].insert_date+"</span>";
				inHtml += "    </div>";
				inHtml += "    <div class=\"cont\">";
				inHtml += "    <div style=\"font-weight:bold; color : #666;\"><a href=\"/wiki/"+recentUpdate[i].we_wiki_idx+"\">"+title+"</a></div>";
				inHtml += text;
				inHtml += "    </div>";				
				inHtml += "</li>";
				
			}
			$("#recentUpdate").append(inHtml);
		}
	}
})(jQuery);