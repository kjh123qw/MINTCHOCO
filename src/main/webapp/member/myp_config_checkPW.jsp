<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 계정 설정 패스워드 확인 ::
	담당자 : 장세진
*/
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<!-- 마이페이지 내 페이지별 js/css -->
<link rel="stylesheet" href="${ contextPath }/css/member/myp_config.css">
<!-- //마이페이지 내 페이지별  js, css -->
<%@ include file="./myp_submenu.jsp" %>
<!-- 서브메뉴 이하 담당자 내용 -->
<!-- 패스워드 확인 -->
	<form class="myp_con_conf_checkPW">
		<article class="con_conf_checkPW_wrap">
			<div class="con_conf_checkPW_subject">패스워드 확인</div>
			<div class="con_conf_checkPW_content">개인정보 보호를 위해 현재 패스워드를 확인합니다.</div>
			<input type="text" class="con_conf_text" placeholder="기존 패스워드 입력">			
			<br>
			<div class="checkPW_btn">
				<input type="submit" id="con_conf_submit">
				<label for="con_conf_submit" class="con_conf_submit_btn">확 인</label>
				<input type="button" id="con_conf_cancel">
				<label for="con_conf_cancel" class="con_conf_submit_btn checkPW_btn_cancel">취 소</label>
			</div>			
		</article>
	</form>
	<div class="con_conf_confirmed">
		<article class="con_conf_confirmed_ok con_conf_horizontalCenter">
			<div class="conf_confirmed_subject conf_detail_subject">
				<span class="icon_confirmed_wrap"><img src="../images/member/confirmed.svg" class="icon_confirmed"></span>
				<span>Confirm </span>
			</div>
			<div class="con_conf_confirmed conf_detail_content">패스워드 확인에 성공했습니다.</div>
		</article>
	</div>
<!-- //서브메뉴 이하 담당자 내용 -->
</div>
<!-- //담당자 내용 -->
<%@ include file="../_footer.jsp" %>
</body>
</html>