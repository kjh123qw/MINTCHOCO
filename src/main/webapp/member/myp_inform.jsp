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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<%@ include file="./myp_submenu.jsp" %>
<!-- 서브메뉴 이하 담당자 내용 -->
<!-- 개인정보 -->
<script>
document.addEventListener("DOMContentLoaded", function(){
	var subject = document.getElementsByClassName("hd-menu-subject")[0];
	subject.innerHTML = "마이 페이지&nbsp;<img class='con-menuin' src='${ contextPath }/images/member/menuin.png'>";
});
</script>
	<form class="myp-con-info" method="post" action="" onsubmit="return ;">
	<c:choose>
	   	<c:when test="${detail_info.info_flag=='Y'}">
			<article class="con-info">
				<div class="con-info-intro con-info-wrap">			
					<div class="con-info-subject">최근 추가한 목록</div>
					<div class="swiper-container">
				    <div class="swiper-wrapper">
				    	<c:forEach items="${favorite_five}" var="favorite5">
				      		<div class="swiper-slide" style="background-image:url(${ contextPath }/images/mov_poster/${favorite5.poster})"></div>
				    	</c:forEach>
				    	<c:if test="${fn:length(favorite_five)==0}">
				    		<div class="swiper-slide" style="background-image:url(${ contextPath }/images/member/none.png)" onclick="movieLink('${favorite5.movieNum}')"></div>
				   		</c:if>
				    </div>
				    <!-- Add Pagination -->
				    <div class="swiper-pagination"></div>
				  </div>
				</div>
				<div class="con-info-intro con-info-wrap">			
					<div class="con-info-subject">소개</div>
					<div class="con-info-content">
						${detail_info.introduce}
					</div>
				</div>
				<div class="con-info-detail con-info-wrap">			
					<div class="con-info-subject">정보</div>
					<table class="con-info-table">
						<tr>
							<th>성별</th>
							<c:if test="${detail_info.gender == 'M'}">
								<td>남자</td>
							</c:if>
							<c:if test="${detail_info.gender == 'F'}">
								<td>여자</td>
							</c:if>			
						</tr>
						<tr>
							<th>이메일 주소</th>
							<td>${detail_info.email}</td>						
						</tr>
						<tr>
							<th>평가</th>
							<td>${assess_cnt}</td>						
						</tr>
						<tr>
							<th>가입일</th>
							<td>${detail_info.indate}</td>						
						</tr>
					</table>
				</div>
			</article>
		</c:when>
		<c:otherwise>
			<div class="con-info-none">회원정보가 비공개되어있습니다.</div>
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