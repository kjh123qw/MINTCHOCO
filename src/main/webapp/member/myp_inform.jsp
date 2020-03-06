<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 마이페이지 메인/개인 정보 ::
	담당자 : 장세진
*/
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<!-- 마이페이지 내 페이지별 js/css -->

<!-- 슬라이더 플러그인 js/css -->
<!-- <link rel="stylesheet" href="https://unpkg.com/swiper/css/swiper.min.css">
<script src="https://unpkg.com/swiper/js/swiper.min.js"></script> -->
<link rel="stylesheet" href="./swiper/swiper.min.css">
<script src="./swiper/swiper.min.js"></script>
<!-- //슬라이더 플러그인 js/css -->
<link rel="stylesheet" href="${ contextPath }/css/member/myp_info.css">

<!-- //마이페이지 내 페이지별  js, css -->
<%@ include file="./myp_submenu.jsp" %>
<!-- 서브메뉴 이하 담당자 내용 -->
<!-- 개인정보 -->
	<form class="myp_con_info" method="post" action="" onsubmit="return ;">
		<article class="con_info">
			<div class="con_info_intro con_info_wrap">			
				<div class="con_info_subject">최근 추가한 목록</div>
				<div class="swiper-container">
			    <div class="swiper-wrapper">
			      <div class="swiper-slide" style="background-image:url(../images/member/poster1.jpg)"></div>
			      <div class="swiper-slide" style="background-image:url(../images/member/poster2.jpg)"></div>
			      <div class="swiper-slide" style="background-image:url(../images/member/poster3.jpg)"></div>
			      <div class="swiper-slide" style="background-image:url(../images/member/poster4.jpg)"></div>
			      <div class="swiper-slide" style="background-image:url(../images/member/poster5.jpg)"></div>
			    </div>
			    <!-- Add Pagination -->
			    <div class="swiper-pagination"></div>
			  </div>
			</div>
			<div class="con_info_intro con_info_wrap">			
				<div class="con_info_subject">소개</div>
				<div class="con_info_content">
					안녕하십니까. 저는 누구입니다.<br>
					잘부탁드립니다.
				</div>
			</div>
			<div class="con_info_detail con_info_wrap">			
				<div class="con_info_subject">정보</div>
				<table class="con_info_table">
					<tr>
						<th>성별</th>
						<td>남</td>						
					</tr>
					<tr>
						<th>이메일 주소</th>
						<td>wkdtpwls@haha.com</td>						
					</tr>
					<tr>
						<th>평가</th>
						<td>10건</td>						
					</tr>
					<tr>
						<th>가입일</th>
						<td>2020.02.27.</td>						
					</tr>
				</table>
			</div>
		</article>
	</form>
<!-- //서브메뉴 이하 담당자 내용 -->
</div>
<!-- //담당자 내용 -->
<%@ include file="../_footer.jsp" %>
<script>
    var swiper = new Swiper('.swiper-container', {
      effect: 'coverflow',
      grabCursor: true,
      centeredSlides: true,
      slidesPerView: 'auto',
      coverflowEffect: {
        rotate: 50,
        stretch: 0,
        depth: 100,
        modifier: 1,
        slideShadows : true,
      },
      pagination: {
        el: '.swiper-pagination',
      },
    });
</script>
</body>
</html>