<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<header class="header" role="banner">
<div class="main">
	<div class="wrap-cont">
		<h1><a href="/dashboard" title="home">GLiDERWiki</a></h1>

		<div class="search-global">
			<h2 class="ir">검색</h2>
			<form name="searchText" id="searchText" method="GET" action="/search">
				<fieldset>
					<legend class="ir">검색폼</legend>
					<input type="text" id="qry" name="qry" value="${wiki_text }" />
					<button type="button" onclick="javascript:searchQuery();">검색</button>
				</fieldset>
			</form>
		</div>

		<!-- 최 상단 메인메뉴 -->
		<c:import url="/menu/M">
		<c:param name="weUserIdx" value="${loginUser.weUserIdx}" />
		</c:import>
	</div>
</div>


<!-- 타이틀메뉴 -->

<c:import url="/menu/sub/T">
<c:param name="weUserIdx" value="${loginUser.weUserIdx}" />
<c:param name="weMenuGroup" value="${weMenuGroup}" />
</c:import>

</header>

<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-about-glider.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-glider-license.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-glider-support.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-markup-layer.js"></script>
<script type="text/javascript">
//<![CDATA[
	$(document).ready(function() {
		var weUserIdx = '${loginUser.weUserIdx}';
		// 메인 메뉴의 서브 리스트를 조회한다.
		$('a[name="head_menu"]').each(function(i) {
			var menuId = "";
			var weMenuIdx = "";
			$($('a[name="head_menu"]').eq(i)).mouseenter(function() {
				menuId = $(this).attr("id");					// head_${we_menu_idx}
				weMenuIdx = $("#input_"+menuId).val();			// we_menu_idx

				callAjaxMainMenu(weMenuIdx, menuId, weUserIdx);
			}).mouseleave(function(){

		    });
		});

		/**
		 * 글라이더 위키에 대하여
		 */
		$("#aboutWiki").bind("click", function() {
			$.about_glider();
		});

		/**
		 * 라이센스 정책
		 */
		$("#licensePolicy").bind("click", function() {
			$.glider_license();
		});

		/**
		 * 기술지원 및 교육
		 */
		$("#support").bind("click", function() {
			$.glider_support();
		});


		// 서브 메뉴를 닫는다.
		$('.layer-navi').live("mouseleave" , function(e) {
			$(".layer-navi").fadeOut(800);
		});

		// 메인 메뉴의 차일드 메뉴를 조회한다.
		function callAjaxMainMenu(weMenuIdx, menuId, weUserIdx) {
			$.ajax({
				type:"GET"
				,url:"/menu/headerMenu"
				,data:{"weMenuIdx":weMenuIdx, "menuId":menuId, "weUserIdx":weUserIdx}
				,dataType:"json"
				,success:function(rtnObj){

					var weMenu = eval("("+rtnObj.menuList+")");


					if(rtnObj.result == 'SUCCESS'){
						callBackSubMainMenu(weMenu, menuId);
					} else {
					}
				}
			});
		}

		// 메인 메뉴의 서브 메뉴 리스트를 출력한다.
		function callBackSubMainMenu(weMenu, menuId) {
			if (weMenu != null) {
				// 서브 메뉴 세팅
				var inHtml = "";
				var listSize = 0;
				$("#"+menuId).after("");				// 현재 그릴 레이어는 초기화 한다.
				$(".layer-navi").fadeOut(500);			// 기존 네비 레이어는 닫는다.
				listSize = weMenu.length;
				if(listSize > 0 ) {
					inHtml += "<div class=\"layer-navi\">";
					inHtml += "<ul>";
					for(var i=0 ; i < listSize ; i++){
						inHtml += "<li>";
						inHtml += "<a href=\""+weMenu[i].we_menu_url+"\">"+weMenu[i].we_menu_name+"</a>";
						inHtml += "</li>";
					}
					inHtml += "</ul>";
					inHtml += "<i class=\"tail\">";
					inHtml += "	<i class=\"tail_inner\"></i>";
					inHtml += "	<i class=\"tail_outer\"></i>";
					inHtml += "</i>";
					inHtml += "</div>";

					// 디테일에 동적으로 화면 생성
					$("#"+menuId).after(inHtml);
				}
			} else {
				alert('에러 발생 : 조회된 값이 없음 ');
			}
		}

		// 타이틀 메뉴의 서브리스트를 조회한다.
		$('a[name="title_menu"]').each(function(i) {
			var menuId = "";
			var weMenuIdx = "";
			var title = "";
			$($('a[name="title_menu"]').eq(i)).mouseenter(function() {
				menuId = $(this).attr("id");					// head_${we_menu_idx}
				title = $(this).attr("title");					// title
				weMenuIdx = $("#input_"+menuId).val();			// we_menu_idx
				thisUrl = $(this).attr("url");

				var weSpaceIdx = "${spaceInfo.spaceIdx}";
				var weWikiIdx = "${weWiki.we_wiki_idx}";


				if(title == '위키생성하기') {
					$("#"+menuId).attr('href', thisUrl+weSpaceIdx);
				}


				if(title == '공간정보조회') {
					$("#"+menuId).attr('href', thisUrl+weSpaceIdx);
				}

				if(title == '초대/가입신청관리') {
					$("#"+menuId).attr('href', thisUrl+weSpaceIdx);
				}

				if(title == '공간정보수정') {
					$("#"+menuId).attr('href', thisUrl+weSpaceIdx+"/form");
				}

				if(title == '새소식만들기') {
					$("#"+menuId).attr('href', "/space/"+weSpaceIdx+"/board");
				}

				if(title == '공간메인') {
					var spaceIdx = "${weWiki.we_space_idx}";
					$("#"+menuId).attr('href', thisUrl+spaceIdx);
				}

				if(title == '변경내역') {
					$("#"+menuId).attr('href', thisUrl+weWikiIdx);
				}

				if(title == '위키마크업활용') {
					$(this).bind("click", function() {
						$.markup_layer();
						return;
					});
				}

				if(title == 'EXPORT PDF') {
					var uri = '${pageContext.request.requestURI}';	// 현재 URI
					var pdfUrl = "/wiki/export/pdf/"+weWikiIdx+"?pdfView=ok";
					var regexPattern = /\/wiki\/[0-9]*$/g;

					if(!regexPattern.test(uri)) {
						$(this).bind("click", function() {
							$("#"+menuId).attr('href', "javascript:void(null);");
							GliderWiki.alert('확인','PDF Export는 위키 상세 조회에서만 사용가능 합니다.');
							return;
						});
					} else {
						$("#"+menuId).attr('href', pdfUrl);
					}
				}

				//callAjaxMainMenu(weMenuIdx, menuId, weUserIdx);
			}).mouseleave(function(){

			});


		});

	});

	function searchQuery(){
		$("#searchText").attr("method", "GET");
		$("#searchText").attr("action","/search");
		$("#searchText").submit();

	}
//]]>
</script>
</js:scripts>
