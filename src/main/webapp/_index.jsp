<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 메인(동영상) ::
	담당자 : 유지상
*/
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>INDEX.JSP</title>
<script src="./js/jquery.2.1.3.min.js"></script>
<script src="./js/jquery-ui.min.js"></script>
<script src="./js/jquery.easing.1.3.js"></script>
<script src="./js/jquery.mousewheel.min.js"></script>
<script src="./js/public.js"></script>
<link rel="stylesheet" href="./css/public.css">
<!-- 담당자 js/css -->

<script src="https://use.fontawesome.com/releases/v5.8.0/js/all.js"></script>


<script src="./js/index/index.js"></script>
<link rel="stylesheet" href="./css/index/index.css">

<!-- //담당자 js, css -->
</head>
<body>
<%-- <%@ include file="./_header.jsp" %> --%>
<!-- 담당자 내용 -->

<div id="contentBox">

		<div id="mainDiv1">
			<video id="video" width="100%" height="100%" preload="auto"
				autoplay="true" loop="loop" muted="muted" volume="0">
				<source src="./images/index/indexIntro.mp4">
			</video>
			<h2 id="skipBtn1">
				Skip <span id="skipBtn2">→</span>
			</h2>
		</div>
		
		<div id = "mainDiv2">
		
			<!-- 로그인 Div -->
			<div id ="loginDiv">
				<h1 class = "title">MINTCHOCO</h1>
				<form action = "login.do" method="POST" name = "loginForm" class = "loginForm">

					<div class="cst-text-st1">
  						<input type="text" name = "email" class="text-200" placeholder="EMAIL" value = "${rememberEmail }">
  						<input type="password" name = "pwd" class="text-200" placeholder = "PASSWORD">
					</div>
					
					<div class="cst-chkbox-st2">
						<c:if test="${rememberEmail == null}">
  							<input type="checkbox" name = "rememberCheck">
  						</c:if>
  						<c:if test = "${rememberEmail != null }">
  							<input type="checkbox" name = "rememberCheck" checked >
  						</c:if>
  						<span class="text-250">REMEMBER ME</span>
					</div>

					<div class="cst-btn">
  						<input type="button" value="확인" class="st1-150-50" onclick = "loginCheck();">
					</div>

                </form>
                
                <div id = "optionDiv">
       		        <a onclick = "joinMember();">회원가입</a>
         	        <span>&nbsp;&nbsp;/&nbsp;&nbsp;</span>
        	        <a onclick = "findPassword();">비밀번호 찾기</a>
                </div>
                              
			</div>
			
			<div id = "upBtn" class = "heightArrow"><i class="fas fa-arrow-up"></i></div>
		<!-- 로그인 Div -->
		</div>
		
		
		<!-- 회원가입 div -->
		<div id ="joinDiv">
			<h1 class = "title">JOIN</h1>
			
			<form action = "join.do" method = "POST" name = "joinForm" class = "joinForm">
			
				<div class="cst-text-st1">
  					<input type="text" name = "email" class="text-200" placeholder="EMAIL">
  					<input type="password" name = "pwd" class="text-200" placeholder = "PASSWORD">
  					<input type="password" name = "pwdCheck" class="text-200" placeholder = "PASSWORD CHECK">
  					<input type="text" name = "name" class="text-200" placeholder="NAME">
  					<input type="text" name = "nickname" class="text-200" placeholder="NICKNAME">
  					<input type="number" name = "age" class="text-200" placeholder="AGE" min="0" max="100">
				</div>
				
				<div class="cst-radio-st1">
				  <input type="radio" name="gender" value = "M">
				  <span class="text-100">남자</span>
				  <input type="radio" name="gender" value = "F">
				  <span class="text-100">여자</span>
				</div>
				
				<hr id = "joinHr">
				
				<div class="cst-chkbox-st2">
  					<input type="checkbox" name = "serviceCheck">
  					<span class="text-200">서비스이용약관에동의합니다.</span>
				</div>
				
				<div class="cst-btn">
  					<input type="button" value="JOIN" class="st1-150-50" onclick = "join();">
				</div>
			
			</form>
		
		</div>
		<!-- 회원가입 div -->

		<!-- 비밀번호 찾기 div -->
        <div id = "findDiv">
        	<h1 class = "title">FORGOT YOUR PASSWORD</h1>
        	<h3 id = "findDivSubTitle">임시비밀번호를 보내드립니다.</h3>
        	
        	<form action = "find.do" method = "POST" name = "findForm" class = "findForm">
        		<div class="cst-text-st1">
  					<input type="text" name = "email" class="text-200" placeholder="EMAIL">
  					<input type="text" name = "name" class="text-200" placeholder="NAME">
				</div>
				
				<div class="cst-btn">
  					<input type="button" value="확인" class="st1-150-50" onclick = "find();">
				</div>
        	</form>
        </div>
        <!-- 비밀번호 찾기 div -->
</div>

<!-- 담당자 내용 -->
<%-- <%@ include file="./_footer.jsp" %> --%>
</body>
</html>