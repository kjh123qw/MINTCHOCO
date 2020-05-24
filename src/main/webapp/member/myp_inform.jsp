<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./myp_submenu.jsp" %>
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
<link rel="stylesheet" href="${ contextPath }/member/swiper/swiper.min.css">
<script src="${ contextPath }/member/swiper/swiper.min.js"></script>
<!-- //슬라이더 플러그인 js/css -->
<link rel="stylesheet" href="${ contextPath }/css/member/myp_info.css">

<!-- //마이페이지 내 페이지별  js, css -->

<!-- 서브메뉴 이하 담당자 내용 -->
<!-- 개인정보 -->
<script>
document.addEventListener("DOMContentLoaded", function(){
	var subject = document.getElementsByClassName("hd-menu-subject")[0];
	subject.innerHTML = "my page&nbsp;<img class='con-menuin' src='${ contextPath }/images/member/menuin.png'>";
});
</script>
	<form class="myp-con-info" method="post" action="" onsubmit="return ;">
	<c:if test="${detail_info.info_flag=='N' && memberInfo.number==detail_info.number}">
		<div class="con-info-none con-none-my">this is not available.</div>
	</c:if>
	<c:choose>
	   	<c:when test="${detail_info.info_flag=='Y' || memberInfo.number==detail_info.number}">
			<article class="con-info">
				<div class="con-info-intro con-info-wrap">			
					<div class="con-info-subject">recent favorite</div>
					<div class="swiper-container">
				    <div class="swiper-wrapper">
				    	<c:forEach items="${favorite_five}" var="favorite5">
				      		<div class="swiper-slide" style="background-image:url(${favorite5.poster})"></div>
				    	</c:forEach>
				    	<c:if test="${empty favorite_five}">
				    		<div class="swiper-slide" onclick="movieLink('${favorite5.movieNum}')" style="text-align: center; font-size: 18px; padding: 20px 0;">There is not a movie.</div>
				   		</c:if>
				    </div>
				    <!-- Add Pagination -->
				    <div class="swiper-pagination"></div>
				  </div>
				</div>
				<div class="con-info-intro con-info-wrap">			
					<div class="con-info-subject">introduce</div>
					<div class="con-info-content">
						${detail_info.introduce}
						<c:if test="${detail_info.introduce==null}">
							there is not a information.
						</c:if>
					</div>
				</div>
				<div class="con-info-detail con-info-wrap">			
					<div class="con-info-subject">information</div>
					<table class="con-info-table">
						<tr>
							<th>gender</th>
							<c:if test="${detail_info.gender == 'M'}">
								<td>male</td>
							</c:if>
							<c:if test="${detail_info.gender == 'F'}">
								<td>female</td>
							</c:if>			
						</tr>
						<tr>
							<th>email</th>
							<td>${detail_info.email}</td>						
						</tr>
						<tr>
							<th>comment</th>
							<td>${assess_cnt}</td>						
						</tr>
						<tr>
							<th>join date</th>
							<td>${detail_info.indate}</td>						
						</tr>
					</table>
				</div>
			</article>
		</c:when>
		<c:otherwise>
			<div class="con-info-none">this is not available.</div>
		</c:otherwise>
	</c:choose>
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