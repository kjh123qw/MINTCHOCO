<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 페이지 명 ::
	담당자 : 천세문
*/
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 영화 정보 입력 </title>
<script src="${ contextPath }/js/jquery.2.1.3.min.js"></script>
<script src="${ contextPath }/js/jquery-ui.min.js"></script>
<script src="${ contextPath }/js/jquery.easing.1.3.js"></script>
<script src="${ contextPath }/js/jquery.mousewheel.min.js"></script>
<script src="${ contextPath }/js/public.js"></script>
<script src="https://use.fontawesome.com/releases/v5.8.0/js/all.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/public.css">
<!-- 담당자 js, css -->

<script src="${ contextPath }/js/movie/mov_insert.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/movie/mov_insert.css">

<!-- //담당자 js, css -->
</head>
<body>
<jsp:include page="../_header.jsp"></jsp:include>
<!-- 담당자 내용 -->

	<h1><a href="${ contextPath }/movie/insertForm.do"> 영화 정보 입력 </a></h1>

	<div id="movInsert">

		<form action="insert.do" method="post">
		
			<table>
			
				<tr>
					<td colspan="2">
						<h3>영화 정보를 하나도 빠짐 없이 입력 해주시기 바랍니다.</h3>
					</td>
				</tr>
				<tr>
					<td>
						티저 링크
					</td>
					<td>
						<input type="text" name="movieTeaser" placeholder="예시) https://youtu.be/TtD7xcruYAk 의 경우  'TtD7xcruYAk' 부분만 입력 해주시기 바랍니다.">
					</td>
				</tr>
				<tr>
					<td>
						영화 제목
					</td>
					<td>
						<input type="text" name="movieTitle" placeholder="주의) 띄어쓰기에 주의하며 한글로만 입력 해주시기 바랍니다.">
					</td>
				</tr>
				<tr>
					<td>
						영화 분류
					</td>
					<td>
						<input type="text" name="movieKind" placeholder="주의) 장르가 여러 개일 경우에는 ','로 구분 해주시기 바랍니다.">
					</td>
				</tr>
				<tr>
					<td>
						감독 이름
					</td>
					<td>
						<input type="text" name="movieDirector" placeholder="주의) 감독이 여러 명일 경우에는 ','로 구분 해주시기 바랍니다.">
					</td>
				</tr>
				<tr>
					<td>
						주연 배우
					</td>
					<td>
						<input type="text" name="movieActor" placeholder="주의) 배우가 여러 명일 경우에는 ','로 구분 해주시기 바랍니다.">
					</td>
				</tr>
				<tr>
					<td>
						상영 등급
					</td>
					<td>
						<input type="text" name="movieGrade" placeholder="예시) 국내 기준에 맞추어 ALL, 12, 15, 19 중 하나만 입력 해주시기 바랍니다.">
					</td>
				</tr>
				<tr>
					<td>
						상영 시간
					</td>
					<td>
						<input type="text" name="movieTime" placeholder="주의) 상영시간은 분 단위, 반올림으로 입력 해주시기 바랍니다.">
					</td>
				</tr>
				<tr>
					<td>
						개봉 연도
					</td>
					<td>
						<input type="text" name="movieDate" placeholder="예시) 개봉 연도는 '20XX 년'으로 숫자와 글자 사이에 띄어쓰기를 주의하며 입력 해주시기 바랍니다.">
					</td>
				</tr>
				<tr>
					<td>
						유튜브 링크
					</td>
					<td>
						<input type="text" name="movieYoutubeUrl" placeholder="예시) https://youtu.be/6yZWJPndzLs 의 경우 'https://youtu.be/6yZWJPndzLs' 전체를 입력 해주시기 바랍니다.">
					</td>
				</tr>
				<tr>
					<td>
						네이버 링크
					</td>
					<td>
						<input type="text" name="movieNaverUrl" placeholder="주의) 위와 동일합니다. 다시 한번 확인 해주시기 바랍니다.">
					</td>
				</tr>
				<tr>
					<td>
						영화 내용
					</td>
					<td>
						<input type="text" name="movieContent" placeholder="주의) 지나치게 개인적인 의견은 삼가 해주시기 바랍니다.">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input id="submit" type="submit" value="입력 완료">
					</td>
					
				</tr>
				
			</table>
		
		</form>
	
	</div>

<!-- 담당자 내용 -->
<jsp:include page="../_footer.jsp"></jsp:include>
</body>
</html>