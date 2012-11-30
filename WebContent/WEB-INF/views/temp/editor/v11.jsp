<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>

<style type="text/css">
	.contents {width: 100%;}
	.contents .editArea {width: 100%;}
	.contents .editArea textarea{width: 100%;}
	.contents .editArea .btn{height: 25px; margin: 3px 0px 1px 0px;}
	.contents .editArea .select{height: 24px; margin: 3px 0px 1px 0px;}
	.contents #layerArea {width: 80%;}			
	.toolbars {background-color: #DCDCDC; width: 100%; height: 60px; float: left; padding: 1px 0px 1px 0px;}
</style>


<h2>샘플 에디터</h2>
<div class="contents">
	<table>
		<colgroup>
			<col style="width:50%"/>
			<col style="width:50%"/>
		</colgroup>
		<tr>
			<td>
				<div class="editArea">
					<div class="toolbars">
						<button class="tool-icon" data-before="**" 			 data-center="굵은글씨" 	data-after="**" 			data-text="굵은글씨"></button>
						<button class="tool-icon" data-before="//" 			 data-center="기울임" 	data-after="//" 			data-text="기울임"></button>
						<button class="tool-icon" data-before="__" 			 data-center="밑줄" 		data-after="__" 			data-text="밑줄"></button>
						<button class="tool-icon" data-before="&lt;sub&gt;"  data-center="아래첨자" 	data-after="&lt;/sub&gt;" 	data-text="아래첨자"></button>
						<button class="tool-icon" data-before="&lt;sup&gt;"  data-center="첨자" 		data-after="&lt;/sup&gt;" 	data-text="첨자"></button>
						<button class="tool-icon" data-before="&lt;del&gt;"  data-center="취소선" 	data-after="&lt;/del&gt;" 	data-text="취소선"></button>
						<button class="tool-icon" data-before="&lt;br /&gt;" data-center="" 		data-after="" 				data-text="줄바꿈"></button>
						<button class="tool-icon" data-before="[[note:1|" 	 data-center="각주" 		data-after="]]"	  			data-text="각주"></button>
						<button class="tool-icon" data-before="&lt;pre&gt;"  data-center="non Html" data-after="&lt;/pre&gt;"	data-text="pre"></button>
						<button class="tool-icon" data-before="[info]" 		 data-center="정보입력" 	data-after="[info]"	  		data-text="info"></button>
						<button class="tool-icon" data-before="[alert]" 	 data-center="정보입력" 	data-after="[alert]"	  	data-text="alert"></button>
						<button class="tool-icon" data-before="[[hr]]" 		 data-center="" 		data-after=""	  			data-text="hr"></button>
						<button class="tool-icon addLink" 		  		     data-text="링크연결"></button>
						<button class="tool-icon addOutsideImgLink" 		 data-text="외부이미지링크"></button>
						<button class="tool-icon addInsideImgLink"  		 data-text="내부이미지링크"></button>
						<button class="tool-icon addTable" 		  			 data-text="테이블"></button>
						<button class="tool-icon addChart" 		  			 data-text="차트"></button>
						<button class="tool-icon previewHtml" 		  		 data-text="미리보기"></button>
						
						<select class="select literaryCircles">
							<option value="">문단제목</option>
							<option value="-">레벨1 </option>
							<option value="--">레벨2 </option>
							<option value="---">레벨3 </option>
							<option value="----">레벨4 </option>
						</select>

						<select class="select literaryCircles">
							<option value="">문단번호</option>
							<option value="##">레벨1 </option>
							<option value="###">레벨2 </option>
							<option value="####">레벨3 </option>
						</select>

						<select class="select literaryCircles">
							<option value="">글씨크기</option>
							<option value="h1. ">Large Heading </option>
							<option value="h2. ">Medium Heading</option>
							<option value="h3. ">Small Heading</option>									
						</select>

					</div>
					<textarea id="wikiEditor" rows="20" cols="90"></textarea>
				</div>			
			</td>
			<td>
				<div id="layerArea">										
	
				</div>
			</td>
		</tr>
	</table>			
</div>









<div id="linkTemplate" style="display:none;">
	링크 : <input type="text" name="link" value="" /> <br />
	링크명 : <input type="text" name="linkName" value="" /> <br />
	창 타입 : <input type="text" name="linkTarget" value="_target" /> <br />
	<button class="layerAreaOff">취소</button><button class="submit addLink">확인</button>
</div>

<div id="outsideImgLinkTemplate" style="display:none;">
	이미지링크 : <input type="text" name="imgLink" value="" /> <br />
	이미지링크명 : <input type="text" name="imgTitle" value="" /> <br />
	
	<button class="layerAreaOff">취소</button><button class="submit addOutsideImgLink">확인</button>
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
	
	<button class="layerAreaOff">취소</button><button class="submit addInsideImgLink">확인</button>
</div>

<div id="tableTemplate" style="display:none;">
	행갯수 : <input type="text" name="rowCount" value="" /> <br />
	열갯수 : <input type="text" name="celCount" value="" /> <br />			
	<button class="layerAreaOff">취소</button><button class="submit addTable">확인</button>
	
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
	타이틀 : <input type="text" name="tempTitle" value="" style="width:70px"/> &nbsp; 값 : <input type="text" name="tempValue" value="" style="width:70px"/> <button id="addChartProp">추가</button><br />
		
	<div id="appendChart">

	</div>
	<button class="layerAreaOff">취소</button><button class="submit addChart">확인</button>
</div>



<script id="tmpl" type="text/x-jquery-tmpl">
			
</script>

<script id="chartTmpl" type="text/x-jquery-tmpl">
	<div>
	타이틀 <input type="text" name="chartTitle" value="{{= title}}" readonly style='width:70px;'/> &nbsp; 값 <input type="text" name="chartValue" value="{{= value}}" readonly style='width:70px;'/> &nbsp; <button class="delChart">제거</button>
	</div>
</script>










<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery_textinputs.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.pieChart.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.templates/beta1/jquery.tmpl.js"></script>

<script type="text/javascript">
	
	var $wikiEditor = $("#wikiEditor");
	var $toolbars = $(".toolbars");
	var $layerArea = $("#layerArea");

	$(function(){
		
		loadToolBars();
		
		$toolbars.on("click",".tool-icon",function(event){
			var $me = $(this);
			var markup = $me.data();
			

			if($me.hasClass("addLink")){
				$layerArea.html($("#linkTemplate").html());
			}
			else if($me.hasClass("addOutsideImgLink")){
				$layerArea.html($("#outsideImgLinkTemplate").html());
			}
			else if($me.hasClass("addInsideImgLink")){
				$layerArea.html($("#insideImgLinkTemplate").html());
			}
			else if($me.hasClass("addTable")){
				$layerArea.html($("#tableTemplate").html());
			}
			else if($me.hasClass("addChart")){
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

		$toolbars.on("change", ".select", function(event){
			var $me = $(this);

			if($me.hasClass("literaryCircles")){
				var tag = $me.val();
				var examStr = $me.find("option:selected").text();
				if(tag != null && tag != ''){
					setSurroundSelectedText(tag,'' ,examStr);
					$me.children("option:eq(0)").attr("selected", true);
				}
				
			}
		});


		$layerArea.on("click",".submit", function(event){
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

	var loadToolBars = function(){
		$(".toolbars").find(".tool-icon").each(function(){
			var me = $(this);
			var textName = me.data("text");
			me.text(textName);
		});
	};


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
						innerTable +=preCellSplit+"<button class='cellRemove'>제거</button>"+postCellSplit;
					}
	 			}
	 			innerTable +="</tr>";
	 		}
			
			innerTable +="</table>";
			innerTable +="<button class='submit generateTable'>확인</button>";
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