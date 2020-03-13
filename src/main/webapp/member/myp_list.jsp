<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 찜 한 리스트 ::
	담당자 : 장세진
*/
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<!-- 마이페이지 내 페이지별 js/css -->
<link rel="stylesheet" href="${ contextPath }/css/member/myp_fav.css">
<!-- //마이페이지 내 페이지별  js, css -->
<%@ include file="./myp_submenu.jsp" %>
<!-- 서브메뉴 이하 담당자 내용 -->
<!-- 찜한 목록 -->
	<form class="myp-con-fav" method="post" action="favorite.do" onsubmit="return chk_cnt();">
		<div class="con-fav-wrap">
			<nav class="con-menu">
				<div class="con-menu-modifyoff  con-menu-dropdown-rel"">
					<div class="con-menu-sub">${detail_info.nickName}님의 관심영화목록</div>
					<ul class="con-menu-list con-menu-dropdown-rel">
						<li><a onclick="searchbar('on');">검색</a></li>
						<li class="con-menu-dropdown-rel con-menu-dropdown-here">
							<a class="con-menu-dropdown-a">목록정렬</a>
							<div class="con-menu-dropdown-wrap">
						        <ul class="con-menu-dropdown">
						            <li><a onmouseenter="textAlign('reg');" onclick="sorting('reg');">등록 순</a></li>
						            <li><a onmouseenter="textAlign('rel');" onclick="sorting('rel');">개봉 순</a></li>
						            <li><a onmouseenter="textAlign('sub');" onclick="sorting('sub');">제목 순</a></li>
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
					<input type="text" class="con-menu-input"  placeholder="검색하실 영화제목을 입력하세요." onkeyup="search();">
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
							<li><a><input type="submit" id="fav-list-delete" class="con-list-delete"><label for="fav-list-delete">삭제</label></a></li>
						</ul>
					</div>
				</c:if>
			</nav>
			<div class="clear"></div>
						
			<c:choose>
	   			<c:when test="${detail_info.like_flag=='Y'}">
			   		<c:if test="${fav_cnt == 0}">
						<div class="con-fav-none">찜한 항목이 없습니다.</div>
					</c:if>
					<c:forEach items="${favoriteList}" var="favorite">
						<article class="con-fav con-fav-float" onclick="chk_enable(this);">
						<input class="con-chkbox" type="checkbox" name="chkbox" value="${favorite.favNum}">
						<div class="con-fav-bg">			
							<div class="con-fav-img-wrap">
								<img class="con-fav-poster" src="${ contextPath }/images/mov_poster/${favorite.poster}">
								<!-- 140x200 -->
								<center class="con-fav-contents-wrap">
									<div class="con-search-target con-th-mint">${favorite.movieTitle}</div>
									<div class="con-fav-movie-kind">${favorite.kind}</div>
									<div class="con-fav-movie-detail con-th-gray">${favorite.playtime}분, ${favorite.grade}세 관람가</div>
									<div class="con-fav-movie-indate con-th-gray"><img src="${ contextPath }/images/member/cal.png" class="con-fav-cal">&nbsp;${favorite.favIndate}</div>
								</center>
							</div>				
						</div>
						</article>
					</c:forEach>			
					<div class="clear"></div>
				</c:when>
				<c:otherwise>
					<div class="con-fav-none">해당 정보가 비공개되어있습니다.</div>
				</c:otherwise>
			</c:choose>
			<!-- 검색결과가 없을때 -->
			<div class="con-search-none">검색이 완료되었습니다.</div>
			
		</div>
		<div class="clear"></div>
	</form>
	<form class="con-sortList" name="alignForm" method="POST" action="favorite.do">
		<input type="hidden" name="chkbox" value="none">
		<input type="hidden" name="sort" class="con-alignInput">
	</form>
<!-- //서브메뉴 이하 담당자 내용 -->
</div>
<!-- //담당자 내용 -->
<%@ include file="../_footer.jsp" %>
</body>
</html>