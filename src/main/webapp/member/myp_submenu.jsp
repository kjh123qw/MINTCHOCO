<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 마이페이지 서브헤더 ::
	담당자 : 장세진
*/
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MY_MAIN.JSP</title>
<script src="${ contextPath }/js/jquery.2.1.3.min.js"></script>
<script src="${ contextPath }/js/jquery-ui.min.js"></script>
<script src="${ contextPath }/js/jquery.easing.1.3.js"></script>
<script src="${ contextPath }/js/jquery.mousewheel.min.js"></script>
<script src="${ contextPath }/js/public.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/public.css">
<!-- 담당자 js/css -->
<script src="${ contextPath }/js/member.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/member/myp_common.css">
<!-- //담당자 js, css -->
</head>
<body>
<%@ include file="../_header.jsp" %>
<!-- 담당자 내용 -->
<div class="clear"></div>
<div class="myp">
	<div class="myp-hd">
		<div class="hd-stbar-wrap">
			<div class="hd-stbar-nick">${detail_info.nickName}</div>
			<div class="hd-stbar-introduce">${detail_info.nickName}님의 마이페이지입니다.</div>
			<div class="clear"></div>
			<div class="hd-stbar-an">평가 작성 ${assess_cnt}건</div>
		</div>
		<div class="clear"></div>
		<div class="hd-menu-wrap">
			<nav class="hd-menu">
				<ul class="hd-menu-list">
					<li><a class="icon-privacy-wrap" href="my_page.do"><img src="${ contextPath }/images/member/privacy.png" class="icon-privacy"><span>&nbsp;정보</span></a></li>
					<li><a class="icon-fav-wrap" href="favorite.do"><img src="${ contextPath }/images/member/fav.png" class="icon-fav"><span>&nbsp;찜한목록</span></a></li>
					<li><a class="icon-assess-wrap" href="assessment.do"><img src="${ contextPath }/images/member/assess1.png" class="icon-assess1"><img src="${ contextPath }/images/member/assess2.png" class="icon-assess2"><span>&nbsp;평가목록</span></a></li>
					<li><a class="icon-config-wrap" href="config.do"><img src="${ contextPath }/images/member/config.png" class="icon-config"><span>&nbsp;계정설정</span></a></li>					
				</ul>
			</nav>
		</div>
		<div class="clear"></div>
	</div>