<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>

<div class="contents">
	<div class="wrap-cont">
		
		<h2 class="tit-cont">페이지 생성</h2>
		
		
<form action="/">
	<div class="body-cont wiki">
		<div class="edit">
			<div class="main">
				<div class="lab">
					<strong>제목</strong>
				</div>
				<input type="text" class="inp_txt" />
				<div class="lab">
					<strong>내용</strong>
				</div>
				<div class="editor">
					<div class="e-type">
						<button type="button" class="e-type-btn on">WYSIWYG</button>
						<button type="button" class="e-type-btn">HTML</button>
						<button type="button" class="e-type-btn">TEXT</button>
					</div>
					<div class="e-toolbar">
						<ul class="e-tb-group">
							<li class="e-tb-btn print" title="인쇄">
								<i class="e-tb-icon"></i>
							</li>
<!-- 							<li class="e-tb-btn undo" title="실행취소"> -->
<!-- 								<i class="e-tb-icon"></i> -->
<!-- 							</li> -->
<!-- 							<li class="e-tb-btn redo" title="다시실행"> -->
<!-- 								<i class="e-tb-icon"></i> -->
<!-- 							</li> -->
<!-- 							<li class="e-tb-btn copy" title="복사"> -->
<!-- 								<i class="e-tb-icon"></i> -->
<!-- 							</li> -->
<!-- 							<li class="e-tb-btn cut" title="잘라내기"> -->
<!-- 								<i class="e-tb-icon"></i> -->
<!-- 							</li> -->
<!-- 							<li class="e-tb-btn paste" title="붙여넣기"> -->
<!-- 								<i class="e-tb-icon"></i> -->
<!-- 							</li> -->
							<li class="e-tb-btn table" title="표만들기">
								<i class="e-tb-icon"></i>
							</li>
							<li class="e-tb-btn select-all" title="전체선택">
								<i class="e-tb-icon"></i>
							</li>
							<li class="e-tb-clr"></li>
							<li class="e-tb-btn hr" title="구분선" data-before="[[hr]]" data-center=""  data-after="">
								<i class="e-tb-icon"></i>
							</li>
							<li class="e-tb-btn link" title="링크">
								<i class="e-tb-icon"></i>
							</li>
							<li class="e-tb-btn image" title="이미지">
								<i class="e-tb-icon"></i>
							</li>
							<li class="e-tb-btn flash" title="플래시">
								<i class="e-tb-icon"></i>
							</li>
							<li class="e-tb-btn media" title="미디어">
								<i class="e-tb-icon"></i>
							</li>
							<li class="e-tb-btn chart" title="차트">
								<i class="e-tb-icon"></i>
							</li>
							<li class="e-tb-btn alert" title="주의" data-before="[alert]" data-center=" " data-after="[alert]">
								<i class="e-tb-icon"></i>
							</li>
							<li class="e-tb-btn info" title="정보" data-before="[info]"  data-center=" "  data-after="[info]">
								<i class="e-tb-icon"></i>
							</li>
						</ul>
						<div class="e-tb-bar"></div>
						<ul class="e-tb-group">
							<li class="e-tb-btn bold" title="굵게" data-before="**" 	data-center=" "  data-after="**">
								<i class="e-tb-icon"></i>
							</li>
							<li class="e-tb-btn italic" title="이탤릭" data-before="//" 	data-center=" "   data-after="//">
								<i class="e-tb-icon"></i>
							</li>
							<li class="e-tb-btn underline" title="밑줄" data-before="__" 	data-center=" "  data-after="__">
								<i class="e-tb-icon"></i>
							</li>
							<li class="e-tb-btn strike" title="취소선" data-before="&lt;del&gt;"  data-center=" " data-after="&lt;/del&gt;">
								<i class="e-tb-icon"></i>
							</li>
							<li class="e-tb-btn sub" title="아래첨자" data-before="&lt;sub&gt;"  data-center=" " 	data-after="&lt;/sub&gt;">
								<i class="e-tb-icon"></i>
							</li>
							<li class="e-tb-btn sup" title="위첨자" data-before="&lt;sup&gt;"  data-center=" "  data-after="&lt;/sup&gt;">
								<i class="e-tb-icon"></i>
							</li>
							<li class="e-tb-btn font-color" title="글자색">
								<i class="e-tb-icon"></i>
								<i class="e-tb-color" style="background-color:#faa"></i>
							</li>
							<li class="e-tb-btn bg-color" title="배경색">
								<i class="e-tb-icon"></i>
								<i class="e-tb-color" style="background-color:#afa"></i>
							</li>
							<li class="e-tb-btn box-bg-color" title="박스배경색">
								<i class="e-tb-icon"></i>
								<i class="e-tb-color" style="background-color:#aaf"></i>
							</li>
							<li class="e-tb-clr"></li>
							<li class="e-tb-select font-family">
								<span class="e-tb-select-txt" style="font-family:dotum;">글자체</span>
								<i class="e-tb-select-box">
									<i class="e-tb-select-arr"></i>
								</i>
								<div class="e-tb-select-layer" style="display:none;">
									<ul>
										<li>
											<a data-before="[font|돋움]" data-center=" "  data-after=" [/font]">
												<span style="font-family:'돋움', dotum;">돋움</span>
											</a>
										</li>
										<li>
											<a data-before="[font|굴림]" data-center=" "  data-after=" [/font]">
												<span style="font-family:'굴림', gulim;">굴림</span>
											</a>
										</li>
										<li>
											<a data-before="[font|궁서]" data-center=" "  data-after=" [/font]">
												<span style="font-family:'궁서', gungsuh;">궁서</span>
											</a>
										</li>
										<li>
											<a data-before="[font|바탕]" data-center=" "  data-after=" [/font]">
												<span style="font-family:'바탕', batang;">바탕</span>
											</a>
										</li>
									</ul>
								</div>
							</li>
<!-- 							<li class="e-tb-select font-size"> -->
<!-- 								<span class="e-tb-select-txt">글자크기(12)</span> -->
<!-- 								<i class="e-tb-select-box"> -->
<!-- 									<i class="e-tb-select-arr"></i> -->
<!-- 								</i> -->
<!-- 								<div class="e-tb-select-layer"> -->
<!-- 									<ul> -->
<!-- 										<li> -->
<!-- 											<a href="#"> -->
<!-- 												<span style="font-size:11px;">11px</span> -->
<!-- 											</a> -->
<!-- 										</li> -->
<!-- 										<li> -->
<!-- 											<a href="#"> -->
<!-- 												<span style="font-size:12px;">12px</span> -->
<!-- 											</a> -->
<!-- 										</li> -->
<!-- 										<li> -->
<!-- 											<a href="#"> -->
<!-- 												<span style="font-size:14px;">14px</span> -->
<!-- 											</a> -->
<!-- 										</li> -->
<!-- 										<li> -->
<!-- 											<a href="#"> -->
<!-- 												<span style="font-size:18px;">18px</span> -->
<!-- 											</a> -->
<!-- 										</li> -->
<!-- 									</ul> -->
<!-- 								</div> -->
<!-- 							</li> -->
							<li class="e-tb-select style">
								<span class="e-tb-select-txt">스타일</span>
								<i class="e-tb-select-box">
									<i class="e-tb-select-arr"></i>
								</i>
								<div class="e-tb-select-layer" style="display:none;">
									<ul>
										<li>
											<a data-before="h1." data-center=" "  data-after="">
												<h1>제목.h1</h1>
											</a>
											<a data-before="h2." data-center=" "  data-after="">
												<h2>부제목.h2</h2>
											</a>
											<a data-before="h3." data-center=" "  data-after="">
												<h3>제목1.h3</h3>
											</a>
										</li>
									</ul>
								</div>
							</li>
						</ul>
						<div class="e-tb-bar"></div>
						<ul class="e-tb-group">
							<li class="e-tb-btn align-left" title="왼쪽정렬">
								<i class="e-tb-icon"></i>
							</li>
							<li class="e-tb-btn align-center" title="중앙정렬">
								<i class="e-tb-icon"></i>
							</li>
							<li class="e-tb-btn align-right" title="오른쪽정렬">
								<i class="e-tb-icon"></i>
							</li>
							<li class="e-tb-btn align-justify" title="균등정렬">
								<i class="e-tb-icon"></i>
							</li>
							<li class="e-tb-clr"></li>
							<li class="e-tb-btn list-ol" title="구분선">
								<i class="e-tb-icon"></i>
							</li>
							<li class="e-tb-btn list-ul" title="링크">
								<i class="e-tb-icon"></i>
							</li>
							<li class="e-tb-btn indent" title="들여쓰기">
								<i class="e-tb-icon"></i>
							</li>
							<li class="e-tb-btn outdent" title="내어쓰기">
								<i class="e-tb-icon"></i>
							</li>
						</ul>
					</div>
					<div class="e-textarea">
						<div class="e-">
						</div>
						<textarea id="wikiEditor"></textarea>
					</div>
					<div id="layerArea">
					
					</div>
				</div>
			</div>
			<div class="sub">
				<div class="place-layer">
					<button class="btn-pink">템플릿불러오기 ▼</button>
					<div class="layer-edit">
						<ul>
							<li>
								<a href="#">회의록 템플릿</a>
							</li>
							<li>
								<a href="#">회계예산 템플릿</a>
							</li>
							<li>
								<a href="#">주간업무보고 템플릿</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="tips">
					<strong class="tit">Wiki Markup Tips</strong>
					<div>
						블라블라<br />
						블라
					</div>
				</div>
			</div>
			<div class="edit-bottom">
				<div class="main">
					<div class="lab">
						<strong>태그</strong>
					</div>
					<input type="text" class="inp_txt" />
				</div>
				<div class="sub">
					<div class="place-layer">
						<button class="btn-pink">공개레벨설정 ▼</button>
						<div class="layer-edit">
							<ul>
								<li>
									<a href="#">전체공개</a>
								</li>
								<li>
									<a href="#">그룹공개</a>
								</li>
								<li>
									<a href="#">구성원공개</a>
								</li>
								<li>
									<a href="#">비공개</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="foot-cont">
		<a href="#" class="btn">미리보기</a>
		<button type="submit" class="btn">수정하기</button>
		<button type="button" class="btn">임시저장</button>
	</div>
</form>
		
	</div>			
</div>















<div id="linkTemplate" style="display:none;">
	링크 : <input type="text" name="link" value="" /> <br />
	링크명 : <input type="text" name="linkName" value="" /> <br />
	창 타입 : <input type="text" name="linkTarget" value="_target" /> <br />
	<span class="layerAreaOff">취소</span><span class="confirm addLink">확인</span>
</div>

<div id="outsideImgLinkTemplate" style="display:none;">
	이미지링크 : <input type="text" name="imgLink" value="" /> <br />
	이미지링크명 : <input type="text" name="imgTitle" value="" /> <br />
	
	<span class="layerAreaOff">취소</span><span class="confirm addOutsideImgLink">확인</span>
</div>

<div id="insideImgLinkTemplate" style="display:none;">
	이미지링크 : <input type="text" name="imgLink" value="" /> <br />
	이미지링크명 : <input type="text" name="imgTitle" value="" /> <br />
	정렬 : <select name="imgAlign">
			<option value="center">가운데</option>
			<option value="left">왼쪽</option>
			<option value="right">오른쪽</option>
		  </select> <br />
  	width : <input type="text" name="width" value="" /> <br />
  	height : <input type="text" name="height" value="" /> <br />
	
	<span class="layerAreaOff">취소</span><span class="confirm addInsideImgLink">확인</span>
</div>

<div id="tableTemplate" style="display:none;">
	행갯수 : <input type="text" name="rowCount" value="" /> <br />
	열갯수 : <input type="text" name="celCount" value="" /> <br />			
	<span class="layerAreaOff">취소</span><span class="confirm addTable">확인</span>
	
	<div id="appendTableMarkup" style="overflow:auto">
	
	</div>
</div>

<div id="chartTemplate" style="display:none;">
	구분 : <span>
			<input type="radio" name="chartType" value="pie" checked/><lable>원형차트</lable>
			<input type="radio" name="chartType" value="bar" /><lable>막대차트</lable>
		  </span>
		  <br />
	화면에 보여줄 태그명 : <input type="text"	 name="chartTagName" value="" /> <br />
	타이틀 : <input type="text" name="tempTitle" value="" style="width:70px"/> &nbsp; 값 : <input type="text" name="tempValue" value="" style="width:70px"/> <span id="addChartProp">추가</span><br />
		
	<div id="appendChart">

	</div>
	<span class="layerAreaOff">취소</span><span class="confirm addChart">확인</span>
</div>



<script id="tmpl" type="text/x-jquery-tmpl">
			
</script>

<script id="chartTmpl" type="text/x-jquery-tmpl">
	<div>
	타이틀 <input type="text" name="chartTitle" value="{{= title}}" readonly style='width:70px;'/> &nbsp; 값 <input type="text" name="chartValue" value="{{= value}}" readonly style='width:70px;'/> &nbsp; <span class="delChart">제거</span>
	</div>
</script>










<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery_textinputs.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.pieChart.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.templates/beta1/jquery.tmpl.js"></script>

<script type="text/javascript">
	
	var $wikiEditor = $("#wikiEditor");
	var $toolbars = $(".e-toolbar");
	var $layerArea = $("#layerArea");

	$(function(){
		
		$toolbars.on("click",".e-tb-btn",function(event){
			var $me = $(this);
			var markup = $me.data();
			

			if($me.hasClass("link")){
				$layerArea.html($("#linkTemplate").html());
			}
			else if($me.hasClass("image")){
				$layerArea.html($("#outsideImgLinkTemplate").html());
			}
			/* 
			else if($me.hasClass("addInsideImgLink")){
				$layerArea.html($("#insideImgLinkTemplate").html());
			}
			 */
			else if($me.hasClass("table")){
				$layerArea.html($("#tableTemplate").html());
			}
			else if($me.hasClass("chart")){
				$layerArea.html($("#chartTemplate").html());
				//event for chart title,value
				registerChartEvent();
			}else if($me.hasClass("previewHtml")){
				previewHtml();
			}
			else{
				setSurroundSelectedText(markup.before, markup.after, markup.center);	
			}

			return false;					
		});

		$toolbars.on("click", ".e-tb-select-layer a", function(event){
			var $me = $(this);
			var markup = $me.data();
			setSurroundSelectedText(markup.before, markup.after, markup.center);
		});


		$layerArea.on("click",".confirm", function(event){
			var $me = $(this);

			if($me.hasClass("addLink")){
				addLink();
			}
			else if($me.hasClass("addOutsideImgLink")){
				addOutsideImgLink();							
			}
			else if($me.hasClass("addInsideImgLink")){
				addInsideImgLink();
			}
			else if($me.hasClass("addTable")){
				generateTableInputs();
			}
			else if($me.hasClass("generateTable")){
				addTable();
			}
			else if($me.hasClass("addChart")){
				addChart();
			}
			return false;
		});

		$layerArea.on("click",".layerAreaOff", function(){
			$layerArea.empty();
		});

	});

	$(".e-tb-select").on("click",function(){
		var $me = $(this);
		
		if($me.find(".e-tb-select-layer").css("display") === 'block'){
			$(".e-tb-select-layer").css("display", "none");
		}else{
			$me.find(".e-tb-select-layer").css("display", "block");
		}
	});

	var addLink = function(){
		var link = $layerArea.find("input[name=link]").val();
		var linkName = $layerArea.find("input[name=linkName]").val();
		var linkTarget = $layerArea.find("input[name=linkTarget]").val();
		
		if(linkName == '' || linkName == null)
			linkName = link;
		if(linkTarget == '' || linkTarget == null)
			linkTarget = "_blank";

		var linkMarkup = "[["+link+"|"+linkName+"]]";
		
		insertText(linkMarkup);

		$layerArea.empty();
	};

	var addOutsideImgLink = function(){
		var imgLink = $layerArea.find("input[name=imgLink]").val();
		var imgTitle = $layerArea.find("input[name=imgTitle]").val();

		if(imgTitle == '' || imgTitle == null){
			imgTitle = imgLink;
		}

		var outsideImgLink = "[[!|"+imgLink+"|"+imgTitle+"]]";

		insertText(outsideImgLink);

		$layerArea.empty();
	};

	var addInsideImgLink = function(){
		var imgLink = $layerArea.find("input[name=imgLink]").val();
		var imgTitle = $layerArea.find("input[name=imgTitle]").val();
		var imgAlign = $layerArea.find("select[name=imgAlign]").val();
		var width = $layerArea.find("input[name=width]").val();
		var height = $layerArea.find("input[name=height]").val();

		if(imgTitle == '' || imgTitle == null){
			imgTitle = imgLink;
		}

		var insideImgLink = "!"+imgLink+"|"+imgAlign;
		if(width != '' && height != ''){
			insideImgLink += "?"+width+"?"+height;
		}else{
			insideImgLink+="!";
		}

		insertText(insideImgLink);

		$layerArea.empty();
	};


	var addTable = function(){
		var $appendTableMarkup = $("#appendTableMarkup");
		var rowCount = $layerArea.find("input[name=rowCount]").val();
		var celCount = $layerArea.find("input[name=celCount]").val();
		var innerTable = "";
		$appendTableMarkup.find("tr").each(function(index, item){
			var $me = $(item);
			
			$me.find("th").each(function(idx){
				var $thMe = $(this);
				if(idx == celCount){
					innerTable+="||\n";
				}else{
					innerTable+="||"+$thMe.find(":input").val();
				}
			});

			$me.find("td").each(function(idx){
				var $thMe = $(this);
				if(idx == celCount){
					innerTable+="|\n";
				}else{
					innerTable+="|"+$thMe.find(":input").val();
				}
			});
		});
		
		insertText(innerTable);
		$layerArea.empty();	
	};

	
	var generateTableInputs = function(){
		var $appendTableMarkup = $("#appendTableMarkup");
		var rowCount = $layerArea.find("input[name=rowCount]").val();
		var celCount = $layerArea.find("input[name=celCount]").val();
		/* 


		 */
		if(rowCount > 0 && celCount > 0){
			var innerTable ="<table id='markupTable' style='border:1px solid black;'>";
			for(var i=0; i<rowCount; i++){
				innerTable +="<tr>";
	 			for(var j=0; j<celCount; j++){
	 				//구분자 태그
	 				var preCellSplit="<td>";
	 				var postCellSplit="</td>";
	 				
	 				//첫번째 row    				
	 				if(i == 0 && rowCount > 1){
	 					preCellSplit="<th>";
		 				postCellSplit="</th>";
	 					innerTable += preCellSplit+"<input type='text' class='th' name='th"+j+"' value='표제목' style='width:50px;'/> "+postCellSplit;
	 				}
	 				else{
	 					innerTable += preCellSplit+"<input type='text' class='td' name='td"+j+"' value='' style='width:50px;'/> "+postCellSplit;
	 				}
					if(j == (celCount-1)){
						innerTable +=preCellSplit+"<span class='cellRemove'>제거</span>"+postCellSplit;
					}
	 			}
	 			innerTable +="</tr>";
	 		}
			
			innerTable +="</table>";
			innerTable +="<span class='confirm generateTable'>확인</span>";
			$appendTableMarkup.html(innerTable);
			$appendTableMarkup.find("tr .cellRemove").click(function(){$(this).parent().parent().remove();});
		}else{
			alert("행갯수 또는 열 갯수를 입력해 주세요.");
			return false;
		}
	};

	var addChart = function(){
		var data ="";
		var charTitle = [];
		var chartValue = [];
		var chartType = $layerArea.find("input:radio[name=chartType]:checked").val();
		var chartTagName = $layerArea.find("input[name=chartTagName]").val();
		$layerArea.find("input[name=chartTitle]").each(function(idx, item){
			charTitle.push($(this).val());
		});
		$layerArea.find("input[name=chartValue]").each(function(idx, item){
			chartValue.push($(this).val());
		});

		var chartLength = charTitle.length; 
		data +="<chart:"+chartType+">\n";
		data +="data[\n";
		for(var i=0; i < chartLength; i++){
			data+="\t['"+charTitle[i]+"',"+chartValue[i]+"]\n";
		}
		data+="]\n";
		data+="tagName["+chartTagName+"]\n";
		data +="</chart:"+chartType+">\n";

		insertText(data);
	};


	var registerChartEvent = function(){
		var $appendChart = $("#appendChart");
		var chartData = {title:"", value:""};
		$layerArea.on("click","#addChartProp",function(){
			var tempTitle = $layerArea.find("input[name=tempTitle]").val();
			var tempValue = $layerArea.find("input[name=tempValue]").val();
			
			if((tempTitle == '' || tempTitle == null) || (tempValue == '' || tempValue == null)){
				alert("차트 값을 입력 해주세요.");
				$layerArea.find("input[name=tempTitle]").focus();
				return false;
			}
			if($layerArea.find("input[name=chartTitle][value="+tempTitle+"]").length > 0){
				alert("이미 존재하는 타이틀 입니다.");
				return false;
			}

			chartData.title = tempTitle;
			chartData.value = tempValue;					
			$layerArea.find("input[name=tempTitle]").val("");
			$layerArea.find("input[name=tempValue]").val("");

			var $chartInline = $("#chartTmpl").tmpl(chartData).appendTo($appendChart);

			$layerArea.on("click",".delChart", function(){$(this).parent().remove();});
			

		});
	};

	
	var previewHtml = function(){
		var wikiHtml = $("#wikiEditor").val();
		
		$.ajax({
			type:"POST"
			,url:"/testWikiPasering"
			,data:{html:wikiHtml}
			,success:function(response){
				var status = response.status;
				if(status == 'SUCCESS'){
					GliderWiki.preview(response.result);
				}else{
					GliderWiki.alert('실패');
				}
			}
		});
		
	};
	





	var setSurroundSelectedText = function(beforeText, afterText, centerText){
 		
 		if(document.selection){//IE
 			ieSetSurroundSelectedText(beforeText, afterText, centerText);
 			return;
		}
 		var range = $wikiEditor.getSelection();
 		
 		var centerTxt = "";
 		
 		if(range.text == '' || range.text.length < 1){
 			centerTxt = centerText;
 		}
 		
 		$wikiEditor.surroundSelectedText(beforeText+centerTxt, afterText, true);
 		$wikiEditor.focus();
 	};

 	var ieSetSurroundSelectedText = function(beforTag,afterTag,defaultVal){		
		
		var $ctrl =$wikiEditor;
		$ctrl.focus();

		var selectedText=document.selection.createRange();
		var newText = selectedText.text;
		
		if(selectedText.text == '' || selectedText.text == null){
			newText = defaultVal;
		}
		newText=beforTag+''+newText+''+afterTag;
	
		selectedText.text=newText;
	};
 	
 	/*
 	 * text inert 시키기
 	 */
 	var insertText = function(insertTxt){
 		if(document.selection){					//IE
 			ieSetSurroundSelectedText(insertTxt, '', '');
 			return;
		}
 		$wikiEditor.surroundSelectedText(insertTxt, '', true);
 		$wikiEditor.focus();
 	};
 			 
</script>
</js:scripts>