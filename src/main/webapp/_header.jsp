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
/*
MemberDAO memberDAO = new MemberDAO();
MemberVO memberVO = memberDAO.loginMember("admin@gmail.com", "ac9689e2272427085e35b9d3e3e8bed88cb3434828b43b86fc0596cad4c6e270");
session.setAttribute("memberInfo", memberVO);*/
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<c:if test="${ empty sessionScope.memberInfo }">
<div id="loginLayer" style="display: none;">
	<form method="post" action="${ contextPath }/login.do" id="loginForm" name="loginForm">
		<div id="loginWindow">
			<h3>LOGIN</h3>
			<input type="text" id="emailForm" name="email" placeholder="EMAIL">
			<input type="password" id="pwdForm" name="pwd" placeholder="PASSWORD">
			<input type="hidden" id="currentURL" name="currentURL">
			<input type="submit" id="loginBtn" value="Login">
			<div id="joinBtn" onclick="checkJoin();">Join</div>
			<div id="loginCancelBtn" onclick="closeLogin();">Close</div>
		</div>
	</form>
</div>
</c:if>
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
			<li><a href="${ contextPath }/main.do">HOME</a></li>
			<li><a href="${ contextPath }/service/notice.do">NOTICE</a></li>
			<c:choose>
				<c:when test="${ empty sessionScope.memberInfo }">
					<li id="loginMenu" onclick="openLogin();">LOGIN</li>
					<li id="joinMenu" onclick="checkJoin();">JOIN</li>
				</c:when>
				<c:otherwise>
					<li><a href="${ contextPath }/mypage.do">MYPAGE</a></li>
					<li><a href="${ contextPath }/logout.do">LOGOUT</a></li>
				</c:otherwise>
			</c:choose>
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
				<input type="text" class="text-230" name="searchKeyWord" placeholder="keyword">
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
		<li><a href="${ contextPath }/main.do">HOME</a></li>
		<li><a href="${ contextPath }/service/notice.do">NOTICE</a></li>
		<c:choose>
			<c:when test="${ empty sessionScope.memberInfo }">
				<li id="loginMenu" onclick="openLogin();">LOGIN</li>
				<li id="joinMenu" onclick="checkJoin();">JOIN</li>
			</c:when>
			<c:otherwise>
				<li><a href="${ contextPath }/mypage.do">MYPAGE</a></li>
				<li><a href="${ contextPath }/logout.do">LOGOUT</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>

<script>
	sessionStorage.setItem('contextPath', '${ contextPath }');
</script>