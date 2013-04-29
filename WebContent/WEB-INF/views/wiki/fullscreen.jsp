<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf"%>
<!DOCTYPE html>
<html lang="ko" xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko">
<head>
<title>::Enterprise OpenSource Wiki – GLiDER™</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<link rel="stylesheet" href="/resource/glider/front/css/full_wiki.css" />
<link rel="stylesheet" href="/resource/glider/front/css/full_dashboard.css" />
<link rel="stylesheet" href="/resource/glider/front/css/full_common.css">
<link rel="stylesheet" href="/resource/libs/plugin/tip-yellow/tip-yellow.css" type="text/css" />
<link rel="stylesheet" href="/resource/libs/plugin/tip-violet/tip-violet.css" type="text/css" />
<link rel="stylesheet" href="/resource/libs/plugin/tip-darkgray/tip-darkgray.css" type="text/css" />
<link rel="stylesheet" href="/resource/libs/plugin/tip-skyblue/tip-skyblue.css" type="text/css" />
<link rel="stylesheet" href="/resource/libs/plugin/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
<link rel="stylesheet" href="/resource/libs/plugin/tip-twitter/tip-twitter.css" type="text/css" />
<link rel="stylesheet" href="/resource/libs/plugin/tip-green/tip-green.css" type="text/css" />
<script type="text/javascript" src="/resource/glider/code/js/shCore.js"></script>
<script type="text/javascript" src="/resource/glider/code/js/shBrushJScript.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery.poshytip.js"></script>
<link href="/resource/glider/code/css/shCore.css" rel="stylesheet" type="text/css" />
<link href="/resource/glider/code/css/shThemeDefault.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(document).ready(function() {
	SyntaxHighlighter.all();
	/*
	 * 풍선도움말 
	 */
	$('a[name="targetFiles"]').each(function(i) {
		$(this).live("mouseenter", function(){}).poshytip({
			className: 'tip-twitter',
			bgImageFrameSize: 15,
			showTimeout: 200,
			alignTo: 'target',
			alignX: 'center',
			offsetY: 10,
			allowTipHover: false,
			fade: false,
			slide: false
		});
	});
		
	/*
	 * 폰트 크기 조절 
	 */
	$('#box-expand .expend').click(function() {
		var $viewer = $('#viewer'); 				//본문 영역을 변수에 담기
		var fontSize = $viewer.css('font-size'); 	
		var bigTextSize = $("h2,h3,h4").css('font-size');
		var iconTop =  $("#box-expand").css('top');
		
		console.log("iconTop : " + iconTop);
		
		if (this.id == "fontPlus") {
			// 컨텐츠 폰트 크기 증가 
			var newSize = parseInt(fontSize.replace(/px/, "")) + 2;
		    $viewer.css("font-size", newSize + "px");
		 	// 헤더 폰트 크기 증가 
		    var newBigSize = parseInt(bigTextSize.replace(/px/, "")) + 1;
		    $("h2,h3,h4").css('font-size', newBigSize + "px");
		    //box-expand TOP 증가,RIGHT 감소
		    
		    var newTopPosition   = parseInt(iconTop.replace(/px/, "")) + 2;
		    $("#box-expand").css('top', newTopPosition + "px");
		    		    
		} else if (this.id == "fontMinus") {
			// 컨텐츠 폰트 크기 감 
			var newSize = parseInt(fontSize.replace(/px/, "")) - 2;
		    $viewer.css("font-size", newSize + "px");
		 	// 헤더 폰트 크기 감소 
		    var newBigSize = parseInt(bigTextSize.replace(/px/, "")) - 1;
		    $("h2,h3,h4").css('font-size', newBigSize+ "px");
		    
		    var newTopPosition   = parseInt(iconTop.replace(/px/, "")) - 2;
		    $("#box-expand").css('top', newTopPosition + "px");
		}
	});
	
	/*
	 * 컨텐츠 너비 조절 
	 */
	$('#box-expand .wide').click(function() {
		var $viewWidth = $('#wrap-cont');
		var wide = $viewWidth.width();
		var iconRight =  $("#box-expand").css('right');
		
		console.log(wide);
		if (this.id == "widthPlus") {
			var newSize = parseInt(wide + 20);
			$viewWidth.css("width", newSize + "px");
		    var newRightPosition = parseInt(iconRight.replace(/px/, "")) - 4;
		    $("#box-expand").css('right', newRightPosition + "px");
		} else if(this.id == 'widthMinus') {
			var newSize = parseInt(wide - 20);
			$viewWidth.css("width", newSize + "px");
		    var newRightPosition = parseInt(iconRight.replace(/px/, "")) + 4;
		    $("#box-expand").css('right', newRightPosition + "px");
		}
		
	});
	
});
</script>

</head>
<body>
	<section class="contents" role="main">
		<div class="wrap-cont" id="wrap-cont">
			<h2 class="tit-cont">${wikiHtml.we_wiki_title}</h2>
			

			<div class="body-cont wiki" id="wikiViewArea">
				<div class="box-expand" id="box-expand">
					<a style="cursor: pointer" name="targetFiles" id="widthPlus" title="화면 넓게" class="wide"><img src="/resource/glider/front/images/rewind.png" width="13px" title="화면넓게"></a>&nbsp;&nbsp;&nbsp;
					<a style="cursor: pointer" name="targetFiles" id="widthMinus" title="화면 좁게" class="wide"><img src="/resource/glider/front/images/forwardtoend.png" width="13px" title="화면좁게"></a>&nbsp;&nbsp;&nbsp;
					<a style="cursor: pointer" name="targetFiles" id="fontPlus" title="폰트 크게" class="expend"><img src="/resource/glider/front/images/plus.png" width="13px" title="폰트크게"></a>&nbsp;&nbsp;&nbsp;
					<a style="cursor: pointer" name="targetFiles" id="fontMinus" title="폰트 작게" class="expend"><img src="/resource/glider/front/images/minus.png" width="13px" title="폰트작게"></a>
				</div>
				<article class="viewer" id="viewer" style="word-wrap: break-word">
				${htmlSource }
				</article>
			</div>
			
			<div class="foot-cont">
			<a href="javascript:window.close();" class="btn">닫기</a>
			</div><br/>
		</div>
	</section>
</body>
</html>