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
	 $(document).ready(function(){
			$("p").clone().appendTo($("#appendTo"));
			$("p").clone().appendTo($("#appendTo"));
			$("p").clone().appendTo($("#appendTo"));
			
			$(window).scroll(function () { 
				$("span").css("display", "inline").fadeOut("slow"); 
	    	});
	 });
</script>
</head>
<body>	
	
  	<p>Paragraph - <span>Scroll happened!</span></p>
  	
  	<div id="appendTo">
  	
  	</div>

</body>
</html>