<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="../css/import.css" />
<style>
  div { color:blue; }
  p { color:green; }
  span { color:red; display:none; }
  </style>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/base.js"></script>
<script type="text/javascript" charset="UTF-8">

</script>
</head>
<body>
	
	<h2 class="title"> scrolling </h2>
	
	<div>Try scrolling the iframe.</div>
	
	<iframe src="/resource/sample/jquery/event/iframeScroll.jsp"  width="400px" height="200px"></iframe>
  	
	<pre>
	 	iframe 안에 있는 jquery
	 	
	 	$("p").clone().appendTo($("#appendTo"));
		$("p").clone().appendTo($("#appendTo"));
		$("p").clone().appendTo($("#appendTo"));
		
		$(window).scroll(function () { 
			$("span").css("display", "inline").fadeOut("slow"); 
    	});
	</pre>

	<p class="txt">
	  스크롤이 움직일때 쓰는 함수.
	  여기서는 iframe 안에 적용 시켰는데도 잘 작동합니다.
	</p>
	
	<ul style="margin-top: 50px;">	
		<li><a href="javascript:history.back();">[뒤로가기]</a></li>
	</ul>
</body>
</html>