<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./myp_submenu.jsp" %>
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

<!-- 서브메뉴 이하 담당자 내용 -->
<!-- 평가 목록 -->
<script>
document.addEventListener("DOMContentLoaded", function(){
	var subject = document.getElementsByClassName("hd-menu-subject")[0];
	subject.innerHTML = "comment&nbsp;<img class='con-menuin' src='${ contextPath }/images/member/menuin.png'>";
});
</script>
	<!-- 평가 목록 편집 메뉴 -->
	<form class="myp-con-assess" method="post" action="" onsubmit="return chk_cnt();">
		<div class="con-assess-wrap">
			<nav class="con-menu">
				<div class="con-menu-modifyoff con-menu-dropdown-rel">
					<div class="con-menu-sub myp_pc">${detail_info.nickName} comment</div>
					<ul class="con-menu-list">
						<li><a onclick="searchbar('on');">search</a></li>
						<li class="con-menu-dropdown-rel con-menu-dropdown-here">
							<a class="con-menu-dropdown-a">sort</a>
							<div class="con-menu-dropdown-wrap">
						        <ul class="con-menu-dropdown">
						            <li><a onmouseenter="textAlign('reg')" onclick="sorting('reg');">registration</a></li>
						            <li><a onmouseenter="textAlign('star')" onclick="sorting('star');">score</a></li>
						            <li><a onmouseenter="textAlign('sub')" onclick="sorting('sub');">title</a></li>
						        </ul>
						        <div class="menu-description">
						     		<p class="menu-description-subject">sort</p>
						     	</div>
						     </div>						   
						</li>
						<c:choose>
	   						<c:when test="${memberInfo.number==detail_info.number}">
								<li><a onclick="modifyOn();">edit</a></li>
							</c:when>
							<c:otherwise>
								<li><a class="con-fakeModify">edit</a></li>
							</c:otherwise>
						</c:choose>					
					</ul>
					<input type="text" class="con-menu-input"  placeholder="keyword"  onkeyup="search();">
					<div class="con-menu-search" onclick="searchbar('off');"><img src="${ contextPath }/images/member/back.png"></div>
				</div>
				<c:if test="${memberInfo.number==detail_info.number}">
					<div class="con-menu-modifyon">
						<div class="con-menu-sub">
							<span class="con-chkbox-cnt">0</span>
							<span>개 선택</span>
						</div>
						<ul class="con-menu-list">
							<li><a onclick="allcheck();">whole</a></li>
							<li><a onclick="modifyOff();">cancel</a></li>
							<li><a onclick="selChkbox();"><input type="submit" id="assess-list-delete" class="con-list-delete"><label for="assess-list-delete">delete</label></a></li>
						</ul>
					</div>
				</c:if>	
			</nav>
			<!-- // 평가 목록 편집 메뉴 -->
			
			<c:if test="${detail_info.assessment_flag=='N' && memberInfo.number==detail_info.number}">
				<div class="con-assess con-assess-none con-none-my">This is not open.</div>
			</c:if>
			<c:choose>
	   			<c:when test="${detail_info.assessment_flag=='Y' || memberInfo.number==detail_info.number}">
			   		<!-- 평가항목 없을때 -->
					<c:if test="${assess_cnt == 0}">
						<div class="con-assess con-assess-none">There is not comment.</div>
					</c:if>
					
					<!-- 평가 틀 -->
					<c:forEach items="${assessList}" var="assess">
					<article class="con-assess" style="background-image: url(${assess.moviePoster});" onclick="chk_enable(this);">
					<input class="con-chkbox" type="checkbox" name="chkbox" value="${assess.assessId}">
					<div class="con-assess-bg">			
						<div class="con-assess-img-wrap con-assess-float">
							<img class="con-assess-img-poster" src="${assess.moviePoster}">
							<!-- 140x200 -->
						</div>
						<div class="con-assess-cont-wrap con-assess-float">
							<div class="con-assess-cont-right-wrap con-assess-float">
								<a href="${ contextPath }/movie/detail.do?movieNumber=${assess.movieNumber}" class="con-search-target con-assess-cont-subject con-movie-link">${assess.movieTitle}</a>
								<div class="con-assess-cont-genre">${assess.movieKind} | ${assess.movieDate} | ${assess.movieTime}m</div>
							</div>
							<div class="myp-assess-mobile con-assess-stars-date con-assess-float-right">
								<div class="con-th-gray"><img src="${ contextPath }/images/member/cal.png" class="con-fav-cal">&nbsp;${assess.assessRegdate}</div>
							</div>
							<div class="myp-assess-pc con-assess-stars con-assess-float-right">
								<div class="clear"></div>
								<div class="con-th-mintcho"></div>
								<div class="con-th-mintcho2 con-assess-float" style="width: ${20*assess.movieStars}px;"></div>
								<div class="con-th-mintcho-label con-assess-float-right"><fmt:formatNumber value="${assess.movieStars}" pattern=".0"/></div>								
							</div>
							<div class="clear"></div>					
						</div>
							<div class="con-assess-cont-table-wrap con-assess-float">	
								<div class="con-assess-cont-line-wrap con-assess-float">
									<div class="con-assess-cont-line-contents-wrap con-assess-float">
										<div class="con-line-th con-th con-th-mint con-assess-float">
											<span><img src="${ contextPath }/images/member/est_good.png"></span>
											<span class="con-assess-like">${assess.likes}</span>
											<span class="con_th2"><img src="${ contextPath }/images/member/est_bad.png"></span>
											<span>${assess.hates}</span>
											<div class="myp-assess-pc con-assess-stars-date con-assess-float-right">
												<div class="con-th-gray"><img src="${ contextPath }/images/member/cal.png" class="con-fav-cal">&nbsp;${assess.assessRegdate}</div>
											</div>
										</div>
										<div class="clear"></div>
										<div class="con-assess-cont-line-content">
											<p>${assess.assessContent}</p>
											<div class="myp-assess-mobile con-assess-img-stars con-assess-float">							
												<img src="${ contextPath }/images/member/mintchoco.png" class="con-assess-mintchoco">
											<div class="con-assess-img-stars-score">${assess.assessStars}</div>							
											</div>
										</div>
									</div>
								</div>
								<div class="myp-assess-pc con-assess-img-stars con-assess-float">							
									<img src="${ contextPath }/images/member/mintchoco.png" class="con-assess-mintchoco">
									<div class="con-assess-img-stars-score">${assess.assessStars}</div>							
								</div>
							</div>
						<div class="clear"></div>
						</div>
					</article>
					</c:forEach>
					<!-- //평가 틀 -->
				</c:when>
				<c:otherwise>
					<div class="con-assess con-assess-none">This is not available.</div>
				</c:otherwise>
			</c:choose>	
			
			<!-- 검색결과가 없을때 -->
			<div class="con-search-none">completed</div>
					
		</div>
		<div class="clear"></div>
	</form>
	<form class="con-sortList con-sortList-assess" name="alignForm" method="POST" action="assessment.do">
		<input type="hidden" name="sort" class="con-alignInput">
	</form>
<!-- //서브메뉴 이하 담당자 내용 -->
</div>
<!-- //담당자 내용 -->
<%@ include file="../_footer.jsp" %>
</body>
</html>