<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 헤더 ::
	담당자 : 김정호
*/
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />

<header>
	<div id="headerNav">
		<ul id="navList">
			<li>
				<div id="searchWrap">
					<form name="searchForm" action="${ contextPath }/movie/search.do">
						<div class="cst-text-st1 search-form-textbox">
							<input type="text" class="text-230" name="searchKeyWord" placeholder="검색어 입력">
						</div>
						<div id="searchHeaderBtn" onclick="document.searchForm.submit();">
							<i class="fas fa-search"></i>
						</div>
					</form>
				</div>
				<div id="showSearchBtn">
					<i class="fas fa-search"></i>
				</div>
			</li>
			<li><a href="${ contextPath }/rank.do">영화순위</a></li>
			<li><a href="${ contextPath }/movie/recommend.do">영화목록</a></li>
			<li><a href="${ contextPath }/notice.do">공지사항</a></li>
			<li><a href="${ contextPath }/my_page.do">마이페이지</a></li>
			<li></li>
		</ul>
	</div>
	<div id="mobMenuBtn">
		<i class="fas fa-bars"></i>
	</div>
	<div id="mobShowSearchBtn">
		<i class="fas fa-search"></i>
	</div>
</header>
<div id="mobSearchWrap">
	<form name="mobSearchForm" action="${ contextPath }/movie/search.do">
		<div class="search-form-wrap">
			<div class="cst-text-st1 search-form-textbox">
				<input type="text" class="text-230" name="searchKeyWord" placeholder="검색어 입력">
			</div>
			<div id="mobSearchHeaderBtn" onclick="document.searchForm.submit();">
				<i class="fas fa-search"></i>
			</div>
		</div>
	</form>
	<div id="blackBackGround"></div>
</div>
<div id="mobMenuWrap">
	<div>
		<div><i class="fas fa-times"></i></div>
	</div>
	<ul id="mobNavList">
		<li><a href="${ contextPath }/">홈으로</a></li>
		<li><a href="${ contextPath }/rank.do">영화순위</a></li>
		<li><a href="${ contextPath }/movie/recommend.do">영화목록</a></li>
		<li><a href="${ contextPath }/notice.do">공지사항</a></li>
		<li><a href="${ contextPath }/my_page.do">마이페이지</a></li>
	</ul>
</div>