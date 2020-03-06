<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 마이페이지 메인/서브헤더 ::
	담당자 : 장세진
*/
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	<div class="myp_hd">
		<div class="hd_stbar_wrap">
			<div class="hd_stbar_nick">NickName</div>
			<div class="hd_stbar_introduce">NickName님의 마이페이지입니다.</div>
			<div class="clear"></div>
			<div class="hd_stbar_an">평가 작성 10건</div>
		</div>
		<div class="clear"></div>
		<div class="hd_menu_wrap">
			<nav class="hd_menu">
				<ul class="hd_menu_list">
					<li><a class="icon_privacy_wrap" href="./myp_inform.jsp"><img src="../images/member/privacy.png" class="icon_privacy"><span>&nbsp;정보</span></a></li>
					<li><a class="icon_fav_wrap" href="./myp_list.jsp"><img src="../images/member/fav.png" class="icon_fav"><span>&nbsp;찜한목록</span></a></li>
					<li><a class="icon_assess_wrap" href="./myp_assess_list.jsp"><img src="../images/member/assess1.png" class="icon_assess1"><img src="../images/member/assess2.png" class="icon_assess2"><span>&nbsp;평가목록</span></a></li>
					<li><a class="icon_config_wrap" href="./myp_config.jsp"><img src="../images/member/config.png" class="icon_config"><span>&nbsp;계정설정</span></a></li>					
				</ul>
			</nav>
		</div>
		<div class="clear"></div>
	</div>