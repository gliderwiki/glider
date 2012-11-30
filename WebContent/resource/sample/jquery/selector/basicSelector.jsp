<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>basic Selector</title>
<link type="text/css" rel="stylesheet" href="../css/import.css" />
<style type="text/css">
    * { font-size:12px; font-family:돋움 }
</style>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/base.js"></script>
<script type="text/javascript" charset="UTF-8">
	$(document).ready(
      function(){
          $("#song").css("border", "solid 1px silver");
          $("a[href^='mailto:']").css("background", "lightblue");
          $("input[type='button']").css("background", "yellow");

          $("div ~ b").css("background", "#efefef");
          $("div > b").css("border", "1px");
      });
</script>
</head>
<body>
	
	<h2 class="title"> 기본 selector </h2>
	
	<div>
        <span><a href="http://androider.co.kr" target="_blank">안드리이더</a></span>
        <br />
        <a href="mailto:choiye84@gmail.com">용은이의 메일주소</a>
        <p>
            <input type="button" value="클릭~" />
            <input type="checkbox" />
            <input type="radio" />
        </p>
        
        <div id="song">요즘 노래방에서 부르는 노래 목록입니다</div><br />
        <b>노라조</b>
        <span>수퍼맨</span><br />
        <b>이적</b>
        <span>다행이다</span><br />
        <b>현진영</b>
        <span>Break me down</span>        
    </div>
    
	<pre>
	 
	$(document).ready(
        function(){
            $("#song").css("border", "solid 1px silver");
            $("a[href^='mailto:']").css("background", "lightblue");
            $("input[type='button']").css("background", "yellow");

            $("div ~ b").css("background", "#efefef");
            $("div > b").css("border", "1px");
        });
		
	</pre>
	
	
	<ul style="margin-top: 50px;">	
		<li><a href="javascript:history.back();">[뒤로가기]</a></li>
	</ul>
</body>
</html>