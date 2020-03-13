<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 평점 리스트 ::
	담당자 : 장세진
*/
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<!-- 마이페이지 내 페이지별 js/css -->
<link rel="stylesheet" href="${ contextPath }/css/member/myp_assess.css">
<!-- //마이페이지 내 페이지별  js, css -->
<%@ include file="./myp_submenu.jsp" %>
<!-- 서브메뉴 이하 담당자 내용 -->
<!-- 평가 목록 -->
	<!-- 평가 목록 편집 메뉴 -->
	<form class="myp-con-assess" method="post" action="" onsubmit="return chk_cnt();">
		<div class="con-assess-wrap">
			<nav class="con-menu">
				<div class="con-menu-modifyoff con-menu-dropdown-rel">
					<div class="con-menu-sub">${detail_info.nickName}님이 작성한 한줄평</div>
					<ul class="con-menu-list">
						<li><a onclick="searchbar('on');">검색</a></li>
						<li class="con-menu-dropdown-rel con-menu-dropdown-here">
							<a class="con-menu-dropdown-a">목록정렬</a>
							<div class="con-menu-dropdown-wrap">
						        <ul class="con-menu-dropdown">
						            <li><a onmouseenter="textAlign('reg')" onclick="sorting('reg');">등록 순</a></li>
						            <li><a onmouseenter="textAlign('star')" onclick="sorting('star');">평점 순</a></li>
						            <li><a onmouseenter="textAlign('sub')" onclick="sorting('sub');">제목 순</a></li>
						        </ul>
						        <div class="menu-description">
						     		<p class="menu-description-subject">항목 정렬을 설정합니다.</p>
						     	</div>
						     </div>						   
						</li>
						<c:if test="${memberInfo[0].number==detail_info.number}">
							<li><a onclick="modifyOn();">편집</a></li>
						</c:if>					
					</ul>
					<input type="text" class="con-menu-input"  placeholder="검색하실 영화제목을 입력하세요."  onkeyup="search();">
					<div class="con-menu-search" onclick="searchbar('off');"><img src="${ contextPath }/images/member/back.png"></div>
				</div>
				<c:if test="${memberInfo[0].number==detail_info.number}">
					<div class="con-menu-modifyon">
						<div class="con-menu-sub">
							<span class="con-chkbox-cnt">0</span>
							<span>개 선택</span>
						</div>
						<ul class="con-menu-list">
							<li><a onclick="allcheck();">전체선택</a></li>
							<li><a onclick="modifyOff();">취소</a></li>
							<li><a onclick="selChkbox();"><input type="submit" id="assess-list-delete" class="con-list-delete"><label for="assess-list-delete">삭제</label></a></li>
						</ul>
					</div>
				</c:if>	
			</nav>
			<!-- // 평가 목록 편집 메뉴 -->
			
								
			
			<c:choose>
	   			<c:when test="${detail_info.assessment_flag=='Y'}">
			   		<!-- 평가항목 없을때 -->
					<c:if test="${assess_cnt == 0}">
						<div class="con-assess con-assess-none">평가된 항목이 없습니다.</div>
					</c:if>
					
					<!-- 평가 틀 -->
					<c:forEach items="${assessList}" var="assess">
					<article class="con-assess" style="background-image: url(${ contextPath }/images/mov_poster/${assess.poster});" onclick="chk_enable(this);">
					<input class="con-chkbox" type="checkbox" name="chkbox" value="${assess.assessNum}">
					<div class="con-assess-bg">			
						<div class="con-assess-img-wrap con-assess-float">
							<img class="con-assess-img-poster" src="${ contextPath }/images/mov_poster/${assess.poster}">
							<!-- 140x200 -->
						</div>
						<div class="con-assess-cont-wrap con-assess-float">
							<div class="con-assess-cont-right-wrap con-assess-float">
								<div class="con-search-target con-assess-cont-subject">${assess.movieTitle}</div>
								<div class="con-assess-cont-genre">${assess.kind} | ${assess.release} 개봉 | ${assess.playTime}분 | ${assess.grade}세 관람가</div>
							</div>
							<div class="con-assess-stars con-assess-float-right">
								<div class="clear"></div>
								<div class="con-th-mintcho"></div>
								<div class="con-th-mintcho2 con-assess-float" style="width: ${20*assess.stars}px;"></div>
								<div class="con-th-mintcho-label con-assess-float-right">${assess.stars}</div>								
							</div>
							<div class="clear"></div>					
						</div>
							<div class="con-assess-float">	
								<div class="con-assess-cont-line-wrap con-assess-float">
									<div class="con-assess-cont-line-contents-wrap con-assess-float">
										<div class="con-line-th con-th con-th-mint con-assess-float">
											<span><img src="${ contextPath }/images/member/est_good.png"></span>
											<span class="con-assess-like">${assess.likes}</span>
											<span class="con_th2"><img src="${ contextPath }/images/member/est_bad.png"></span>
											<span>${assess.hates}</span>
											<div class="con-assess-stars-date con-assess-float-right">
												<div class="con-th-gray"><img src="${ contextPath }/images/member/cal.png" class="con-fav-cal">&nbsp;${assess.assessIndate}</div>
											</div>
										</div>
										<div class="clear"></div>
										<div class="con-assess-cont-line-content"><p>${assess.aContent}</p></div>
									</div>
								</div>
								<div class="con-assess-img-stars con-assess-float">							
									<img src="${ contextPath }/images/member/mintchoco.png" class="con-assess-mintchoco">
									<div class="con-assess-img-stars-score">${assess.astars}</div>							
								</div>
							</div>
						<div class="clear"></div>
						</div>
					</article>
					</c:forEach>
					<!-- //평가 틀 -->
				</c:when>
				<c:otherwise>
					<div class="con-assess con-assess-none">해당 정보가 비공개되어있습니다.</div>
				</c:otherwise>
			</c:choose>	
			
			<!-- 검색결과가 없을때 -->
			<div class="con-search-none">검색이 완료되었습니다.</div>
					
		</div>
		<div class="clear"></div>
	</form>
	<form class="con-sortList" name="alignForm" method="POST" action="assessment.do">
		<input type="hidden" name="sort" class="con-alignInput">
	</form>
<!-- //서브메뉴 이하 담당자 내용 -->
</div>
<!-- //담당자 내용 -->
<%@ include file="../_footer.jsp" %>
</body>
</html>