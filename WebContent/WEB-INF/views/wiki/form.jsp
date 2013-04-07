<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>

<div class="contents">
	<div class="wrap-cont">

		<spring:url var="actionUrl" value="/wiki/regist" />
		<c:set var="pageName" value="저장" />

		<c:if test="${!empty wikiForm.we_wiki_idx}">
			<spring:url var="actionUrl" value="/wiki/edit" />
		</c:if>
		
		<c:if test="${editMode eq 'edit'}">
			<c:set var="pageName" value="수정" />
		</c:if>

		<h2 class="tit-cont">페이지 ${pageName}</h2>


		<div class="editarea">
		<form:form modelAttribute="wikiForm" name="wikiForm" method="POST" action="${actionUrl}" enctype="multipart/form-data">
		<input type="hidden" id="isUpload" name="isUpload" title="파일업로드확인" value=""/>
		<form:hidden path="we_wiki_idx"/>
		<form:hidden path="we_space_idx"/>
		<form:hidden path="we_wiki_parent_idx"/>
		<form:hidden path="we_wiki_order_idx"/>
		<form:hidden path="we_wiki_depth_idx"/>
		<form:hidden path="we_ins_user"/>

		<div class="body-cont wiki">
			<div class="edit">
				<div class="main">
					<div class="lab">
						<strong>제목</strong>
					</div>
					<form:input path="we_wiki_title" cssClass="inp_txt"/>
					<div class="lab">
						<strong>내용</strong>
					</div>
					<div class="editor">
						<div class="e-type">
							<button type="button" class="e-type-btn on">MARKUP</button>
						</div>
						<div class="e-toolbar">
							<ul class="e-tb-group">
								<li class="e-tb-btn undo disabled" title="실행취소" data-mode="rich" data-type="undo">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn redo disabled" title="다시실행" data-mode="rich" data-type="redo">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn copy disabled" title="복사" data-mode="rich" data-type="copy">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn cut disabled" title="잘라내기" data-mode="rich" data-type="cut">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn paste disabled" title="붙여넣기" data-mode="rich" data-type="paste">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn table" title="표만들기" data-mode="layer" data-type="table" unselectable="on">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn select-all" title="필드" data-mode="append" data-before="[field|타이틀]" data-center=""  data-after="[field]">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn note" title="각주" data-mode="append" data-before="[note|" data-center="각주"  data-after="#설명]">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-clr"></li>
								<li class="e-tb-btn hr" title="구분선" data-mode="insert" data-before="[hr]" data-center=""  data-after="">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn link" title="링크" data-mode="layer" data-type="link">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn image" title="이미지" data-mode="layer" data-type="image">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn flash" title="플래시" data-mode="layer" data-type="flash">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn media" title="미디어" data-mode="layer" data-type="media">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn chart" title="차트" data-mode="layer" data-type="chart">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn alert" title="주의" data-mode="append" data-before="[alert]" data-center=" " data-after="[alert]">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn info" title="정보" data-mode="append" data-before="[info]"  data-center=" "  data-after="[info]">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
							</ul>
							<div class="e-tb-bar"></div>
							<ul class="e-tb-group">
								<!-- value="굵게" id="bold" data-mode="append" data-before="**" data-center=" " data-after="**"  -->
								<li class="e-tb-btn bold" title="굵게" data-mode="append" data-before="**" data-center=" " data-after="**" >
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn italic" title="이탤릭" data-mode="append" data-before="//" 	data-center=" "   data-after="//">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn underline" title="밑줄" data-mode="append" data-before="__" 	data-center=" "  data-after="__">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn strike" title="취소선" data-mode="append" data-before="[d]"  data-center=" " data-after="[d]">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn sub" title="아래첨자" data-mode="append" data-before="[sb]"  data-center=" " 	data-after="[sb]">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn sup" title="위첨자" data-mode="append" data-before="[sp]"  data-center=" "  data-after="[sp]">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn colorlayer font-color" title="글자색" data-mode="color" data-before="color"  data-center=" "  data-after="color">
									<i class="e-tb-icon" unselectable="on"></i>
									<i class="e-tb-color" style="background-color:#faa"></i>
								</li>
								<li class="e-tb-btn colorlayer bg-color" title="배경색" data-mode="color" data-before="bg"  data-center=" "  data-after="bg">
									<i class="e-tb-icon" unselectable="on"></i>
									<i class="e-tb-color" unselectable="on" style="background-color:#afa"></i>
								</li>
								<li class="e-tb-btn colorlayer box-bg-color" title="박스배경색" data-mode="color" data-before="box"  data-center=" "  data-after="box">
									<i class="e-tb-icon" unselectable="on"></i>
									<i class="e-tb-color" unselectable="on" style="background-color:#aaf"></i>
								</li>
								<li class="e-tb-clr"></li>
								<li class="e-tb-select font-family">
									<span class="e-tb-select-txt" style="font-family:dotum;" id="selectFont" title="selectFont" unselectable="on">글자체</span>
									<i class="e-tb-select-box" unselectable="on">
										<i class="e-tb-select-arr"></i>
									</i>
									<div class="e-tb-select-layer" style="display:none;">
										<ul>
											<li>
												<a data-mode="font" data-before="[font|돋움]" data-center=" "  data-after=" [font]" unselectable="on">
													<span style="font-family:'돋움', dotum;" unselectable="on">돋움</span>
												</a>
											</li>
											<li>
												<a data-mode="font" data-before="[font|굴림]" data-center=" "  data-after=" [font]" unselectable="on">
													<span style="font-family:'굴림', gulim;" unselectable="on">굴림</span>
												</a>
											</li>
											<li>
												<a data-mode="font" data-before="[font|궁서]" data-center=" "  data-after=" [font]" unselectable="on">
													<span style="font-family:'궁서', gungsuh;" unselectable="on">궁서</span>
												</a>
											</li>
											<li>
												<a data-mode="font" data-before="[font|바탕]" data-center=" "  data-after=" [font]" unselectable="on">
													<span style="font-family:'바탕', batang;" unselectable="on">바탕</span>
												</a>
											</li>
											<li>
												<a data-mode="font" data-before="[font|Arial]" data-center=" "  data-after=" [font]" unselectable="on">
													<span style="font-family: Arial; -webkit-user-select: none; " unselectable="on">Arial</span>
												</a>
											</li>
											<li>
												<a data-mode="font" data-before="[font|Comic Sans MS]" data-center=" "  data-after=" [font]" unselectable="on">
													<span style="font-family: 'Comic Sans MS'; -webkit-user-select: none; " unselectable="on">Comic Sans MS</span>
												</a>
											</li>
											<li>
												<a data-mode="font" data-before="[font|Courier New]" data-center=" "  data-after=" [font]" unselectable="on">
													<span style="font-family: 'Courier New'; -webkit-user-select: none; " unselectable="on">Courier New</span>
												</a>
											</li>
											<li>
												<a data-mode="font" data-before="[font|Georgia]" data-center=" "  data-after=" [font]" unselectable="on">
													<span style="font-family: Georgia; -webkit-user-select: none; " unselectable="on">Georgia</span>
												</a>
											</li>
											<li>
												<a data-mode="font" data-before="[font|Tahoma]" data-center=" "  data-after=" [font]" unselectable="on">
													<span style="font-family: Tahoma; -webkit-user-select: none; " unselectable="on">Tahoma</span>
												</a>
											</li>
											<li>
												<a data-mode="font" data-before="[font|Verdana]" data-center=" "  data-after=" [font]" unselectable="on">
													<span style="font-family: Verdana; -webkit-user-select: none; " unselectable="on">Verdana</span>
												</a>
											</li>
										</ul>
									</div>
								</li>
								<li class="e-tb-select font-size">
									<span class="e-tb-select-txt" id="selectSize" title="selectSize" unselectable="on">글자크기(12)</span>
									<i class="e-tb-select-box" unselectable="on">
										<i class="e-tb-select-arr"></i>
									</i>
									<div class="e-tb-select-layer" style="display:none;">
										<ul>
											<li>
												<a data-mode="font" data-before="[size|11]" data-center=" "  data-after=" [size]" unselectable="on">
													<span style="font-size:11px;" unselectable="on">11px</span>
												</a>
											</li>
											<li>
												<a data-mode="font" data-before="[size|12]" data-center=" "  data-after=" [size]" unselectable="on">
													<span style="font-size:12px;" unselectable="on">12px</span>
												</a>
											</li>
											<li>
												<a data-mode="font" data-before="[size|14]" data-center=" "  data-after=" [size]" unselectable="on">
													<span style="font-size:14px;" unselectable="on">14px</span>
												</a>
											</li>
											<li>
												<a data-mode="font" data-before="[size|18]" data-center=" "  data-after=" [size]" unselectable="on">
													<span style="font-size:18px;" unselectable="on">18px</span>
												</a>
											</li>
										</ul>
									</div>
								</li>
								<li class="e-tb-select style">
									<span class="e-tb-select-txt" id="selectStyle" title="selectStyle" unselectable="on">스타일</span>
									<i class="e-tb-select-box" unselectable="on">
										<i class="e-tb-select-arr"></i>
									</i>
									<div class="e-tb-select-layer" style="display:none;">
										<ul>
											<li>
												<a data-mode="font" data-before="h1. " data-center=""  data-after="" unselectable="on">
													<h1 style="padding-top:5px" unselectable="on">h1. 큰 헤드라인</h1>
												</a>
												<a data-mode="font" data-before="h2. " data-center=""  data-after="" unselectable="on">
													<h2 style="padding-top:5px" unselectable="on">h2. 중간 헤드라인</h2>
												</a>
												<a data-mode="font" data-before="h3. " data-center=""  data-after="" unselectable="on">
													<h3 style="padding-top:1px" unselectable="on">h3. 작은 헤드라인</h3>
												</a>
											</li>
										</ul>
									</div>
								</li>
							</ul>
							<div class="e-tb-bar"></div>
							<ul class="e-tb-group">
								<li class="e-tb-btn align-left" title="왼쪽정렬" data-mode="append" data-before="[align:left]" data-center=""  data-after="[align]">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn align-center" title="중앙정렬" data-mode="append" data-before="[align:center]" data-center=""  data-after="[align]">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn align-right" title="오른쪽정렬" data-mode="append" data-before="[align:right]" data-center=""  data-after="[align]">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn align-justify" title="균등정렬" data-mode="append" data-before="[align:justify]" data-center=""  data-after="[align]">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-clr"></li>
								<li class="e-tb-btn list-ol" title="숫자순번" data-mode="insert" data-before="--" data-center=""  data-after="">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn list-ul" title="사각순번" data-mode="insert" data-before="##" data-center=""  data-after="">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn indent" title="들여쓰기" data-mode="append" data-before="[t]" data-center="" data-after="[t]" >
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
								<li class="e-tb-btn outdent" title="내어쓰기" data-mode="rich" data-type="outdent">
									<i class="e-tb-icon" unselectable="on"></i>
								</li>
							</ul>
						</div>
						<div class="e-textarea">
							<div id='richHidden' style='display:none'></div>
							<textarea id="wikiEditor" name="we_wiki_text"><c:if test="${fn:length(wikiForm.we_wiki_text) gt 0}">${wikiForm.we_wiki_text}</c:if></textarea>
							<!-- <div id="wikiEditor" contenteditable="true"><p>Lorem ipsum</p></div> -->
						</div>
					</div>
				</div>

				<div class="sub">
					<div class="place-layer">
					<button type="button" class="btn-pink" id="template_list" style="cursor:pointer">템플릿불러오기 ▼</button>
					</div>
					<div class="tips">
						<strong class="tit">Wiki Markup Tips</strong>
						<div>- 기본활용<br/>
						    <h2>h1. 큰 헤드라인</h2>
							<h3>h2. 중간 헤드라인</h3>
							<h4>h3. 작은 헤드라인</h4>
							<br/><div style="padding-top:2px"><B>굵게</B> : **굵게**</div>
							<br/><div style="padding-top:2px"><I>기울임</I> : //기울임// </div>
							<br/><div style="padding-top:2px"><U>밑줄</U> : __밑줄__ </div>
							<br/><div style="padding-top:2px"><B><U><I>조합</I></U></B> : **__//조합//__** </div>
							<br/><div style="padding-top:2px"><del>취소선</del> : [del]취소선[del] </div>
							<br/><div style="padding-top:2px">구분라인: [hr]━━━━ </div>
							<br/><div style="padding-top:2px"><li>일반목록 : ##목록 </li> </div>
							<br/><div style="padding-top:2px"><li>순서목록 : --목록 </li> </div>
							<br/><div style="padding-top:2px">- 고급활용 </div>
							<br/><div style="padding-top:2px">[@:링크주소|링크타이틀]</div>
							<br/><div style="padding-top:2px">[!|이미지주소|타이틀][!s|넓이|높이][!]</div>
							<br/><div style="padding-top:2px">[align:left]왼쪽정렬[align] </div>
							<br/><div style="padding-top:2px">[align:center]센터정렬[align] </div>
							<br/><div style="padding-top:2px">[align:right]오른쪽정렬[align] </div>
							<br/><div style="padding-top:2px">[align:justify]균등정렬[align] </div>
							<br/><div style="padding-top:2px">[info]정보[info] </div>
							<br/><div style="padding-top:2px">[field|타이틀]필드테두리[field] </div>
							<br/><div style="padding-top:2px">[alert]경고[alert] </div>
							<br/><div style="padding-top:2px">[note|각주#각주의 설명] </div>
							<br/><div style="padding-top:2px">|| 표 제목 1 || 표 제목 2 ||</div>
							<br/>| 표 내용 1 &nbsp;| 표 내용 2 &nbsp;&nbsp;|
							<br/><div style="padding-top:2px">[syntax]하이라이트[syntax]</div>
						</div>
					</div>
				</div>
				<div class="edit-bottom">
					<div class="main">
						<div class="lab">
							<strong>연관키워드</strong>
						</div>

						<input type="text" class="inp_txt" name="weTag" value="${weTag }"/>
						<c:if test="${editMode eq 'edit'}">
						<div class="lab">
							<strong>수정사유 </strong>
						</div>
						<input type="text" class="inp_txt" name="weEditText" id="weEditText" value=""/>
						</c:if>
						<div class="lab">
							<strong>파일첨부</strong>
							<fieldset id="file_list" style="padding:10px;background-color:whiteSmoke;font-size:9pt;color: #828282;font-weight: bold;font-family: tohoma;">
								<c:choose>
								<c:when test="${!empty fileList }">
								<c:forEach items="${fileList }" var="fileList" varStatus="idx">
									<div id="display_items_${fileList.we_file_idx }" style="padding: 4px;">
									${fileList.we_file_real_name }
									<a href="javascript:removeFile('1', '${fileList.we_file_idx }')" title="삭제">삭제</a>
									</div>
									<input type="hidden" name="weFileIdx" id="weFileIdx_${fileList.we_file_idx }" value='${fileList.we_file_idx }'>
								</c:forEach>
								</c:when>
								<c:otherwise>
								<div id="display_items">첨부파일 없음</div>
								</c:otherwise>
								</c:choose>
							</fieldset>
						</div>
						<input type="file" name="file" id="file" />

					</div>
					<div class="sub">
						<div class="place-layer">
							<button class="btn-pink" id="view_list">공개레벨 :
								<c:choose>
								<c:when test="${weSpace.we_view_privacy == 'ALLGROUP' }">
									전체
								</c:when>
								<c:when test="${weSpace.we_view_privacy == 'GROUP' }">
									그룹
								</c:when>
								<c:otherwise>
									구성원
								</c:otherwise>
								</c:choose>
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="foot-cont">
			<a style="cursor:pointer" class="btn previewHtml" id="preSaveView">미리보기</a>
			<a style="cursor:pointer" id="saveWiki" class="btn">${pageName}하기</a>
			<a href="/space/main/${wikiForm.we_space_idx}" class="btn">공간메인목록</a>
		</div>


	</form:form>

	<div id="temp-layer"></div>
	<div id="preview-layer"></div>
	</div>

	</div>
</div>

<js:scripts>

<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.validate.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-loadingbar.js"></script>
<script type="text/javascript" src="/resource/glider/wiki/wikiService.js"></script>
<script type="text/javascript" src="/resource/glider/common/wiki.common.util.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.dateFormat-1.0.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-preview-layer.js"></script>

<script type="text/javascript">
//<![CDATA[
    var fileMaxCount = 3;

    if('${fileSize}' == '' || '${fileSize}' == 0) {
    	var fileCount = 0;
    } else {
    	var fileCount = parseInt('${fileSize}');
    }
    
    var editMode = '${editMode}';

	$(function () {

		// 파일 업로드
		$("#file").change(function(){
			if(fileCount == fileMaxCount) {
				GliderWiki.alert('알림', '파일은 최대 ' +fileMaxCount+'개 까지 등록할 수 있습니다.');
				return;
			}

			$("#wikiForm").unbind();
			$("#wikiForm").ajaxForm(FileuploadCallback);
			$("#wikiForm").attr("action","/wiki/fileUpload");
			$("#wikiForm").submit();
		});


		// 템플릿 목록
		$("#template_list").bind("click", function() { 
			
			if($('#templateBox').length > 0){
				$('#templateBox').remove();
				return false;
			}

			var rtnObj = eval('${template}');


			var resourceHtml = "";
			if(typeof rtnObj == 'undefined' || rtnObj == '') {
				resourceHtml = [
			            		'<div class="layer-edit" id="templateBox">',
			            		'	<ul>',
			            		'		<li style="padding:5px">등록된 템플릿이 없습니다.</li>',
			            		'	</ul>',
			            		'</div>',
				].join('');
			} else {
				var rtnSize = rtnObj.length;

				// 서버에서
				resourceHtml += "<div class=\"layer-edit\" id=\"templateBox\">";
				resourceHtml += "	<ul>";
				for(var i=0 ; i < rtnSize ; i++){
					resourceHtml += "		<li id=\"temp_"+rtnObj[i].we_template_idx+"\"><a href=\"javascript:callTemplate("+rtnObj[i].we_template_idx+")\">"+rtnObj[i].we_template_name+"</a></li>";
				}
				resourceHtml += "	</ul>";
				resourceHtml += "</div>";
			}

			$("#template_list").after(resourceHtml);
			return false;

		});

		// 공개레벨 설정
		$("#view_list").bind("click", function() {
			return false;
		});
		// 미리보기
		$("#preSaveView").bind("click", function() {
			var wikiHtml = $("#wikiEditor").val();
			var temp = wikiHtml.replace(/\n\r?/g, '\r\n');
	
			
			$.ajax({
				type:"POST"
				,url:"/wikiPreviewTag"
				,data:{"tagHtml":temp}
				,dataType:"json"
				,success:function(rtnObj){
					
					var wikiMarkup = rtnObj.wikiMarkup;
					var wikiTitle = rtnObj.wikiTitle;
					var wikiRevision = rtnObj.wikiRevision;
					var weWikiIdx = rtnObj.weWikiIdx;
					
					if(rtnObj.result == 'SUCCESS'){
						callBackPreview(wikiMarkup, wikiTitle, wikiRevision, weWikiIdx);
					} else {
						GliderWiki.alert('알림', '에러 - 정보가 존재하지 않습니다.');
					}
				}
			});


		});


	   /*
		*  editMode : 'new':생성, 'edit':수정, 'reply': 답글, 'temp': 임시저장
		*
		*/
		var write = new GliderWiki.Wiki.Write(".editarea", "${editMode}", "${wikiForm.we_space_idx}", "${wikiForm.we_wiki_idx}").create();
	});


	/**
	 * 위키 내용 조회 
	 */
	function callBackPreview(wikiMarkup, wikiTitle, wikiRevision, weWikiIdx) {
		$.preview_layer({
			'markup' : wikiMarkup,
			'title'  : wikiTitle, 
			'revision' : wikiRevision,
			'wikiIdx' : weWikiIdx
			
		});
	}

	
	// 파일 업로드 콜백
	function FileuploadCallback(data,state){

		console.log("FileuploadCallback data :" + data);
		console.log("FileuploadCallback data :" + data.result);

		if(data.result < 1) {
			GliderWiki.alert('경고', data.msg);
			return;
		}
		
		var jsonStr = eval(data);
		
		console.log("jsonStr data :" + jsonStr);
		
		var resourceHtml = "";
		
		if(jsonStr != null) {
			if(jsonStr.result == 1 ) {
				var file_items =  $("#display_items");


				if(file_items.length > 0) {
					$("#file_list").html("");
				}

				fileCount = fileCount + 1;
				$("#isUpload").val(fileCount);


				resourceHtml = [
					'<div id="display_items_',jsonStr.fileIndex,'" style="padding: 4px;">',jsonStr.realFileName, ' ',
					'<a href="javascript:removeFile(',0,', ',jsonStr.fileIndex,')">삭제</a></div>',
					'<input type="hidden" name="weFileIdx" id="weFileIdx_',jsonStr.fileIndex,'" value=',jsonStr.fileIndex,'>',
				].join('');

				$("#file_list").append(resourceHtml);

			} else {
				GliderWiki.alert('에러', jsonStr.msg);
			}
		} else {
			GliderWiki.alert('에러', '에러가 발생하였습니다.');
		}
	}

	function removeFile(chkType, fileIdx) {
		GliderWiki.confirm('알림 ', '해당 파일을 삭제하겠습니까?',  function() { callRemoveFile(chkType, fileIdx) });
	}

	function callRemoveFile(chkType, fileIdx) {
		var targetId = "display_items_"+fileIdx;
		var formId = "weFileIdx_"+fileIdx;

		var weFileIdx = $("#"+formId).val();
		$.ajax({
			type:"GET"
			,url:"/wiki/removeFile"
			,data:{"chkType":chkType, "weFileIdx":weFileIdx, "targetId":targetId, "formId":formId}
			,dataType:"json"
			,success:function(rtnObj){
				if(rtnObj.result == 'SUCCESS'){

					$("#"+targetId).remove();
					$("#"+formId).remove();

					fileCount = fileCount - 1;

					if(fileCount == 0) {
						$("#file_list").append("<div id=\"display_items\">첨부파일 없음</div>");
						$("#isUpload").val("0");
					}
				} else {
					GliderWiki.alert('에러', '파일삭제가 처리되지 않았습니다. 다시 시도하세요.');
				}
			}
		});
	}

	
	/**
	 * 템플릿 내용을 불러온다. 
	 */
	function callTemplate(weTemplateIdx) {
		
		$.ajax({
			type:"GET"
			,url:"/template/call/"+weTemplateIdx
			,success:function(response){
				var status = response.status;
				var result = response.result;
				console.log("status : " + status);
				console.log("result : " + result);
				
				if(status == 'SUCCESS'){
					$("#wikiEditor").val(result);
					$('#templateBox').remove();
				}else{
					GliderWiki.alert('에러', '에러가 발생하였습니다.');
					$('#templateBox').remove();
				}
			}
		});
		
	}
//]]>
</script>



<!-- 에디터 javascript -->
<script type="text/javascript" src="/resource/libs/jquery/jquery_textinputs.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.pieChart.js"></script>

<script type="text/javascript" src="/resource/glider/wiki/customer/fn-layer-table.js"></script>
<script type="text/javascript" src="/resource/glider/wiki/customer/fn-layer-link.js"></script>
<script type="text/javascript" src="/resource/glider/wiki/customer/fn-layer-image.js"></script>
<script type="text/javascript" src="/resource/glider/wiki/customer/fn-layer-flash.js"></script>
<script type="text/javascript" src="/resource/glider/wiki/customer/fn-layer-media.js"></script>
<script type="text/javascript" src="/resource/glider/wiki/customer/fn-layer-chart.js"></script>
<script type="text/javascript" src="/resource/glider/wiki/customer/fn-layer-select.js"></script>

<script type="text/javascript" src="/resource/glider/wiki/edit/fn-editor-selector-v4.js"></script>
<script type="text/javascript" src="/resource/glider/wiki/edit/fn-editor-util.js"></script>
<script type="text/javascript" src="/resource/glider/wiki/edit/fn-editor.js"></script>
<script type="text/javascript" src="/resource/glider/wiki/edit/fn.editor.layer.js"></script>
<script type="text/javascript" src="/resource/glider/wiki/edit/fn-editor-rich.js"></script>


<script type="text/javascript">
var wikiEditor = document.getElementById("wikiEditor");		// [object HTMLTextAreaElement]
var $toolbars = $(".e-toolbar");

var tempLayer = "temp-layer";
$.makeLayer.init();
$.makeLayer.addLayer ( $.layer_link, "link" );
$.makeLayer.addLayer ( $.layer_image, "image" );
$.makeLayer.addLayer ( $.layer_flash, "flash" );
$.makeLayer.addLayer ( $.layer_media, "media" );
$.makeLayer.addLayer ( $.layer_chart, "chart" );
		
$(function(){

	$toolbars.on("click",".e-tb-btn",function(event){
		var $me = $(this);
		var markup = $me.data();
		$.editorAction(wikiEditor, $me, markup, tempLayer);
		//$.editorAction(wikiEditor.get(0), $me, markup, tempLayer);
		return false; 
	});
	
	$toolbars.on("click", ".e-tb-select-layer a", function(event){
		var $me = $(this);
		var markup = $me.data();
		$.editorAction(wikiEditor, $me, markup, tempLayer);
		//$.editorAction(wikiEditor.get(0), $me, markup, tempLayer);
		
		return false; 
	});


	/**
	 * 레이어 클릭시 선택된 레이어만 화면에 출력한다. 
	 */
	$(".e-tb-select").on("click",function(){
		$(".e-tb-select-layer").css("display", "none");
		
		var $me = $(this);
		if($me.find(".e-tb-select-layer").css("display") === 'block'){
			$(".e-tb-select-layer").css("display", "none");
		}else{
			$me.find(".e-tb-select-layer").css("display", "block");
		}
	});

	/**
	 * 레이어 별로 사이즈를 다르게 설정한다. 
	 */
	$(".e-tb-select-txt").on("click",function(){
		var attrId     = $(this).attr("id");
		var title  = $(this).attr("title");

		if(attrId == 'selectSize' || attrId == 'selectFont') {
			$(".e-tb-select-layer").css("width", "90px");
		} else if(attrId == 'selectStyle'){
			$(".e-tb-select-layer").css("width", "174px");
		} else {
			$(".e-tb-select-layer").css("width", "174px");
		}
	});
	
	/**
	 * 본문 클릭시 펼쳐진 레이어는 다 닫는다. 
	 */
	$("#wikiEditor").on("click",function(){
		$(".e-tb-select-layer").css("display", "none");
	});
	
	$(document.body).click(function ( e ) {
		$(this).find('div[name != e-tb-select]').each(function (idx, item){
			
		});
	});
	
	
});
</script>
</js:scripts>
