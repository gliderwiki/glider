<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>

<div class="contents">
	<div class="wrap-cont">

		<h2 class="tit-cont">페이지 </h2>


		<div class="editarea">
		<form:form modelAttribute="wikiForm" method="POST" action="" enctype="multipart/form-data">
		
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
								<li class="e-tb-btn undo disabled" title="실행취소">
									<i class="e-tb-icon"></i>
								</li>
								<li class="e-tb-btn redo disabled" title="다시실행">
									<i class="e-tb-icon"></i>
								</li>
								<li class="e-tb-btn copy disabled" title="복사">
									<i class="e-tb-icon"></i>
								</li>
								<li class="e-tb-btn cut disabled" title="잘라내기">
									<i class="e-tb-icon"></i>
								</li>
								<li class="e-tb-btn paste disabled" title="붙여넣기">
									<i class="e-tb-icon"></i>
								</li>
								<li class="e-tb-btn table" title="표만들기" data-mode="layer" data-type="table">
									<i>표만들기</i>
								</li>
								<li class="e-tb-btn select-all" title="필드" data-mode="append" data-before="[field|타이틀]" data-center=""  data-after="[field]">
									<i>필드</i>
								</li>
								<li class="e-tb-btn note" title="각주" data-mode="append" data-before="[note|" data-center="각주"  data-after="#설명]">
									<i>각주</i>
								</li>
								<li class="e-tb-clr"></li>
								<li class="e-tb-btn hr" title="구분선" data-mode="insert" data-before="[hr]" data-center=""  data-after="">
									<i>구분선</i>
								</li>
								<li class="e-tb-btn link" title="링크" data-mode="layer" data-type="link">
									<i>링크</i>
								</li>
								<li class="e-tb-btn image" title="이미지" data-mode="layer" data-type="image">
									<i>이미지</i>
								</li>
								<li class="e-tb-btn flash" title="플래시" data-mode="layer" data-type="flash">
									<i>플래시</i>
								</li>
								<li class="e-tb-btn media" title="미디어" data-mode="layer" data-type="media">
									<i>미디어</i>
								</li>
								<li class="e-tb-btn chart" title="차트" data-mode="layer" data-type="chart">
									<i>차트</i>
								</li>
								<li class="e-tb-btn alert" title="주의" data-mode="append" data-before="[alert]" data-center=" " data-after="[alert]">
									<i>주의</i>
								</li>
								<li class="e-tb-btn info" title="정보" data-mode="append" data-before="[info]"  data-center=" "  data-after="[info]">
									<i>정보</i>
								</li>
							</ul>
							<div class="e-tb-bar"></div>
							<ul class="e-tb-group">
								<!-- value="굵게" id="bold" data-mode="append" data-before="**" data-center=" " data-after="**"  -->
								<li class="e-tb-btn" title="굵게" data-mode="append" data-before="**" data-center=" " data-after="**">
									<i>굵게</i>
								</li>
								<li class="e-tb-btn" title="이탤릭" data-mode="append" data-before="//" 	data-center=" "   data-after="//">
									<i>이탤릭</i>
								</li>
								<li class="e-tb-btn" title="밑줄" data-mode="append" data-before="__" 	data-center=" "  data-after="__">
									<i>밑줄</i>
								</li>
								<li class="e-tb-btn" title="취소선" data-mode="append" data-before="[d]"  data-center=" " data-after="[d]">
									<i>취소선</i>
								</li>
								<li class="e-tb-btn" title="아래첨자" data-mode="append" data-before="[sb]"  data-center=" " 	data-after="[sb]">
									<i>아래첨자</i>
								</li>
								<li class="e-tb-btn" title="위첨자" data-mode="append" data-before="[sp]"  data-center=" "  data-after="[sp]">
									<i>위첨자</i>
								</li>
								<li class="e-tb-btn colorlayer font-color" title="글자색">
									<i class="e-tb-icon"></i>
									<i class="e-tb-color" style="background-color:#faa"></i>
								</li>
								<li class="e-tb-btn colorlayer bg-color" title="배경색">
									<i class="e-tb-icon"></i>
									<i class="e-tb-color" style="background-color:#afa"></i>
								</li>
								<li class="e-tb-btn colorlayer box-bg-color" title="박스배경색">
									<i class="e-tb-icon"></i>
									<i class="e-tb-color" style="background-color:#aaf"></i>
								</li>
								<li class="e-tb-clr"></li>
								<li class="e-tb-select font-family">
									<span class="e-tb-select-txt" style="font-family:dotum;" id="selectFont" title="selectFont">글자체</span>
									<i class="e-tb-select-box">
										<i class="e-tb-select-arr"></i>
									</i>
									<div class="e-tb-select-layer" style="display:none;">
										<ul>
											<li>
												<a data-before="[font|돋움]" data-center=" "  data-after=" [font]">
													<span style="font-family:'돋움', dotum;">돋움</span>
												</a>
											</li>
											<li>
												<a data-before="[font|굴림]" data-center=" "  data-after=" [font]">
													<span style="font-family:'굴림', gulim;">굴림</span>
												</a>
											</li>
											<li>
												<a data-before="[font|궁서]" data-center=" "  data-after=" [font]">
													<span style="font-family:'궁서', gungsuh;">궁서</span>
												</a>
											</li>
											<li>
												<a data-before="[font|바탕]" data-center=" "  data-after=" [font]">
													<span style="font-family:'바탕', batang;">바탕</span>
												</a>
											</li>
											<li>
												<a data-before="[font|Arial]" data-center=" "  data-after=" [font]">
													<span style="font-family: Arial; -webkit-user-select: none; ">Arial</span>
												</a>
											</li>
											<li>
												<a data-before="[font|Comic Sans MS]" data-center=" "  data-after=" [font]">
													<span style="font-family: 'Comic Sans MS'; -webkit-user-select: none; ">Comic Sans MS</span>
												</a>
											</li>
											<li>
												<a data-before="[font|Courier New]" data-center=" "  data-after=" [font]">
													<span style="font-family: 'Courier New'; -webkit-user-select: none; ">Courier New</span>
												</a>
											</li>
											<li>
												<a data-before="[font|Georgia]" data-center=" "  data-after=" [font]">
													<span style="font-family: Georgia; -webkit-user-select: none; ">Georgia</span>
												</a>
											</li>
											<li>
												<a data-before="[font|Tahoma]" data-center=" "  data-after=" [font]">
													<span style="font-family: Tahoma; -webkit-user-select: none; ">Tahoma</span>
												</a>
											</li>
											<li>
												<a data-before="[font|Verdana]" data-center=" "  data-after=" [font]">
													<span style="font-family: Verdana; -webkit-user-select: none; ">Verdana</span>
												</a>
											</li>
										</ul>
									</div>
								</li>
								<li class="e-tb-select font-size">
									<span class="e-tb-select-txt" id="selectSize" title="selectSize">글자크기(12)</span>
									<i class="e-tb-select-box">
										<i class="e-tb-select-arr"></i>
									</i>
									<div class="e-tb-select-layer" style="display:none;">
										<ul>
											<li>
												<a data-before="[size|11]" data-center=" "  data-after=" [size]">
													<span style="font-size:11px;">11px</span>
												</a>
											</li>
											<li>
												<a data-before="[size|12]" data-center=" "  data-after=" [size]">
													<span style="font-size:12px;">12px</span>
												</a>
											</li>
											<li>
												<a data-before="[size|14]" data-center=" "  data-after=" [size]">
													<span style="font-size:14px;">14px</span>
												</a>
											</li>
											<li>
												<a data-before="[size|18]" data-center=" "  data-after=" [size]">
													<span style="font-size:18px;">18px</span>
												</a>
											</li>
										</ul>
									</div>
								</li>
								<li class="e-tb-select style">
									<span class="e-tb-select-txt" id="selectStyle" title="selectStyle">스타일</span>
									<i class="e-tb-select-box">
										<i class="e-tb-select-arr"></i>
									</i>
									<div class="e-tb-select-layer" style="display:none;">
										<ul>
											<li>
												<a data-before="h1. " data-center=""  data-after="">
													<h1 style="padding-top:5px">h1. 큰 헤드라인</h1>
												</a>
												<a data-before="h2. " data-center=""  data-after="">
													<h2 style="padding-top:5px">h2. 중간 헤드라인</h2>
												</a>
												<a data-before="h3. " data-center=""  data-after="">
													<h3 style="padding-top:1px">h3. 작은 헤드라인</h3>
												</a>
											</li>
										</ul>
									</div>
								</li>
							</ul>
							<div class="e-tb-bar"></div>
							<ul class="e-tb-group">
								<li class="e-tb-btn align-left" title="왼쪽정렬" data-mode="append" data-before="[align:left]" data-center=""  data-after="[align]">
									<i>왼쪽정렬</i>
								</li>
								<li class="e-tb-btn align-center" title="중앙정렬" data-mode="append" data-before="[align:center]" data-center=""  data-after="[align]">
									<i>중앙정렬</i>
								</li>
								<li class="e-tb-btn align-right" title="오른쪽정렬" data-mode="append" data-before="[align:right]" data-center=""  data-after="[align]">
									<i>오른쪽정렬</i>
								</li>
								<li class="e-tb-btn align-justify" title="균등정렬" data-mode="append" data-before="[align:justify]" data-center=""  data-after="[align]">
									<i>균등정렬</i>
								</li>
								<li class="e-tb-clr"></li>
								<li class="e-tb-btn list-ol" title="숫자순번" data-mode="insert" data-before="--" data-center=""  data-after="">
									<i>숫자순번</i>
								</li>
								<li class="e-tb-btn list-ul" title="사각순번" data-mode="insert" data-before="##" data-center=""  data-after="">
									<i>사각순번</i>
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
							<textarea id="wikiEditor" name="we_wiki_text"></textarea>
						</div>
					</div>
				</div>
			</div>
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

<script type="text/javascript" src="/resource/glider/wiki/customer/fn-layer-table.js"></script>
<script type="text/javascript" src="/resource/glider/wiki/customer/fn-layer-link.js"></script>
<script type="text/javascript" src="/resource/glider/wiki/customer/fn-layer-image.js"></script>
<script type="text/javascript" src="/resource/glider/wiki/customer/fn-layer-flash.js"></script>
<script type="text/javascript" src="/resource/glider/wiki/customer/fn-layer-media.js"></script>
<script type="text/javascript" src="/resource/glider/wiki/customer/fn-layer-chart.js"></script>

<script type="text/javascript" src="/resource/glider/wiki/edit/fn-editor-selector-v4.js"></script>
<script type="text/javascript" src="/resource/glider/wiki/edit/fn-editor-util.js"></script>
<script type="text/javascript" src="/resource/glider/wiki/edit/fn-editor.js"></script>
<script type="text/javascript" src="/resource/glider/wiki/edit/fn.editor.layer.js"></script>


<script type="text/javascript">
		$("#wikiEditor").append("1234567890\r1234567890");	
		var $wikiEditor = $("#wikiEditor");
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
				var data = $me.data();
				$.editorAction(wikiEditor, $me, data, tempLayer);
				
				return false;
			});

			
		});

</script>
</js:scripts>
