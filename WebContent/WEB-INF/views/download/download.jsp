<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/include/tags.jspf" %>
    
<body>
<section class="contents " role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">GLiDER Wiki™ 다운로드</h2>
			<div class="body-cont space">
				<div class="edit">
					
					<input type="hidden" name="_method" id="_method">
					<!-- 상단 간격조정  -->
					<div class="box" style="margin-top:-20px">
						<h3>이메일 주소를 입력하세요. 글라이더 위키의 Active Key가 전송됩니다.</h3>
						<input type="text" value="" class="inp-dup"/>
					</div>
					<div class="box">
						<h3>사이트 주소를 입력하세요.</h3>
						<input type="text" value="" class="inp-dup"/>
					</div>
					
					<div class="box">
					<h3>사용자 정보</h3>
					<div class="box-group fst">
						<h4 class="tit">글라이더 위키의 용도를 알려주세요.</h4>
						<span class="item">
							<input type="radio" value="Y" />
							<label for="read1">연구소/대학</label>
						</span>
						<span class="item">
							<input type="radio" value="Y" />
							<label for="read2">개인/스터디</label>
						</span>
						<span class="item">
							<input type="radio" value="Y" />
							<label for="read3">지식공유/커뮤니티</label>
						</span>
						<span class="item">
							<input type="radio" value="Y" />
							<label for="read3">기업/업무용</label>
						</span>
						<span class="item">
							<input type="radio" value="Y" />
							<label for="read3">TFT/프로젝트용</label>
						</span>
						<span class="item">
							<input type="radio" value="Y" />
							<label for="read3">기타</label>
						</span>
					</div>
				</div>
		
	</div>
</div>
<div class="foot-cont">
	<a href="/space/main/${WeSpace.we_space_idx}" class="btn">위키 다운로드</a>
</div>
<iframe name="hiddenImgForm" id="hiddenImgForm" src="" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" height="0" width="0"  ></iframe>
</div>
</section>

<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/glider/common/wiki.common.util.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-loadingbar.js"></script>
<script type="text/javascript"> 
//<![CDATA[

//]]>
</script>
</js:scripts>
</body>