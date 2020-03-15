<%@page import="com.kosmo.mintchoco.member.MemberDAO"%>
<%@page import="com.kosmo.mintchoco.member.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 평점 ::
	담당자 : 김정호
*/
/* 임시 로그인 */
	MemberDAO memberDAO = new MemberDAO();
	MemberVO memberVO = memberDAO.loginMember("user04@gmail.com", "831c237928e6212bedaa4451a514ace3174562f6761f6a157a2fe5082b36e2fb");
	HttpSession testSession = request.getSession();
	testSession.setAttribute("memberInfo", memberVO);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />

<hr class="rating-line">
<div id="ratingWrap">
	<div id="myCommentBox"></div>
	<div class="rating-header-wrap">
		<div class="rating-blank-underline"></div>
		<div id="commonList" class="rating-header-comment rating-header-selected">
			평점 목록
		</div>
		<div id="namedList" class="rating-header-named">
			네임드 평점
		</div>
		<div id="writeComment" class="rating-header-write">
			평점 작성
		</div>
		<div class="rating-blank-underline"></div>
	</div>
	<div id="ratingCommentBox"></div>
	<div id="ratingPage">
		&nbsp;&nbsp;<span class="page-arrow"><i class="fas fa-angle-left"></i></span>&nbsp;&nbsp;
		&nbsp;&nbsp;<span class="page-arrow"><i class="fas fa-angle-right"></i></span>&nbsp;&nbsp;
	</div>
	<div id="namedCommentBox">
		<div class="no-comment">준비 중 입니다...</div>
	</div>
	<div id="writeCommentBox">
		<form method="post" action="${ pageContext.request.contextPath }/comment/insert.do">
			<div class="rating-top">
				<div class="rating-nick">${ sessionScope.memberInfo.nickName }</div>
				<input type="hidden" name="memberNumber" value="${ sessionScope.memberInfo.number }" id="memberNumber">
				<input type="hidden" name="movieNumber" value="${ param.movieNumber }" id="selectedMovieNumber">
				<div class="score-selector">
					<span>평점&nbsp;</span>
					<select name="assessStars">
						<option value="1">1
						<option value="2">2
						<option value="3">3
						<option value="4">4
						<option value="5">5
						<option value="6">6
						<option value="7">7
						<option value="8">8
						<option value="9">9
						<option value="10">10
					</select>
				</div>
			</div>
			<div class="comment-text">
				<textarea name="assessContent"></textarea>
			</div>
			<div class="btn-area">
				<div class="cst-btn">
				  <input type="submit" value="등록" class="st1-100-35">
				</div>
			</div>
		</form>
	</div>
</div>
<hr class="rating-line-bottom">
