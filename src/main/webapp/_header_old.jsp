<%@page import="com.kosmo.mintchoco.member.MemberDAO"%>
<%@page import="com.kosmo.mintchoco.member.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 헤더 ::
	담당자 : 김정호
*/
/* 임시 로그인 */

MemberDAO memberDAO = new MemberDAO();
MemberVO memberVO = memberDAO.loginMember("admin@gmail.com", "ac9689e2272427085e35b9d3e3e8bed88cb3434828b43b86fc0596cad4c6e270");
session.setAttribute("memberInfo", memberVO);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<header>
	<div id="headerNav">
		<div id="homeBtn">
			<a href="${ contextPath }/main.do">
				<i class="fas fa-ice-cream"></i>
				<!--<img src="${ contextPath }/images/mincho/mincho2.png" alt="home button">-->
			</a>
		</div>
		<ul id="navList">
			<li>
				<div id="searchWrap">
					<form name="searchForm" action="${ contextPath }/movie/search.do">
						<div class="cst-text-st1 search-form-textbox">
							<input type="text" class="text-210" name="searchKeyWord" placeholder="검색어 입력">
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
			<li>
				<div id="showTagBtn">
					<div id="showTagBtnUp" style="display: none;">
						<i class="fas fa-caret-up"></i>
					</div>
					<div id="showTagBtnDown">
						<i class="fas fa-caret-down"></i>
					</div>
				</div>
			</li>
			<li><a href="${ contextPath }/rank.do">영화순위</a></li>
			<li><a href="${ contextPath }/movie/recommend.do">영화목록</a></li>
			<li><a href="${ contextPath }/service/notice.do">공지사항</a></li>
			<li><a href="${ contextPath }/my_page.do">마이페이지</a></li>
			<li><a href="${ contextPath }/logout.do">로그인</a></li>
			<li></li>
		</ul>
	</div>
	<div id="mobMenuBtn">
		<i class="fas fa-bars"></i>
	</div>
	<div id="mobShowSearchBtn">
		<i class="fas fa-search"></i>
	</div>
	<div id="mobShowTagBtn">
		<div id="mobShowTagBtnUp" style="display: none;">
			<i class="fas fa-caret-up"></i>
		</div>
		<div id="mobShowTagBtnDown">
			<i class="fas fa-caret-down"></i>
		</div>
	</div>
</header>
<div id="tagWrap">
	<form name="searchTagForm" action="${ contextPath }/movie/tagSearch.do" onsubmit="return checkTagList();">
		<div id="searchTagListBox"></div>
		<div id="searchMoreTagListBox"></div>
		<div id="serachTagBtn">
			<div id="searchTagText"></div>
			<div class="cst-btn">
				<div id="moreTag">
					<div id="morePlus">
						<i class="fas fa-plus-square"></i>
					</div>
					<div id="moreMinus">
						<i class="fas fa-minus-square"></i>
					</div>
				</div>
				<input type="submit" class="st1-120-40" value="검색">
			</div>
		</div>
	</form>
</div>
<div id="mobSearchWrap">
	<form name="mobSearchForm" action="${ contextPath }/movie/search.do">
		<div class="search-form-wrap">
			<div class="cst-text-st1 search-form-textbox">
				<input type="text" class="text-230" name="searchKeyWord" placeholder="검색어 입력">
			</div>
			<div id="mobSearchHeaderBtn" onclick="document.mobSearchForm.submit();">
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
		<li><a href="${ contextPath }/main.do">홈</a></li>
		<li><a href="${ contextPath }/rank.do">영화순위</a></li>
		<li><a href="${ contextPath }/movie/recommend.do">추천영화</a></li>
		<li><a href="${ contextPath }/service/notice.do">공지사항</a></li>
		<li><a href="${ contextPath }/my_page.do">마이페이지</a></li>
		<li><a href="${ contextPath }/logout.do">로그인</a></li>
	</ul>
</div>


<script>
	sessionStorage.setItem('contextPath', '${ contextPath }');
</script>