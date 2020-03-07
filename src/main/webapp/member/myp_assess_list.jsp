<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 평점 리스트 ::
	담당자 : 장세진
*/
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<!-- 마이페이지 내 페이지별 js/css -->
<link rel="stylesheet" href="${ contextPath }/css/member/myp_assess.css">
<!-- //마이페이지 내 페이지별  js, css -->
<%@ include file="./myp_submenu.jsp" %>
<!-- 서브메뉴 이하 담당자 내용 -->
<!-- 평가 목록 -->
	<!-- 평가 목록 편집 메뉴 -->
	<form class="myp_con_assess" method="post" action="" onsubmit="return chk_cnt();">
		<div class="con_assess_wrap">
			<nav class="con_menu">
				<div class="con_menu_modifyoff con_menu_dropdown_rel">
					<div class="con_menu_sub">SEZI 님이 작성한 한줄평</div>
					<ul class="con_menu_list">
						<li><a onclick="searchbar('on');">검색</a></li>
						<li class="con_menu_dropdown_rel con_menu_dropdown_here">
							<a class="con_menu_dropdown_a">목록정렬</a>
							<div class="con_menu_dropdown_wrap">
						        <ul class="con_menu_dropdown">
						            <li><a onmouseenter="textAlign('reg')">등록 순</a></li>
						            <li><a onmouseenter="textAlign('rel')">개봉 순</a></li>
						            <li><a onmouseenter="textAlign('sub')">제목 순</a></li>
						        </ul>
						         <div class="menu_description">
						     		<p class="menu_description_subject">항목 정렬을 설정합니다.</p>
						     	</div>
						     </div>						   
						</li>
						<li><a onclick="modifyOn();">편집</a></li>						
					</ul>
					<input type="text" class="con_menu_input"  placeholder="검색하실 영화제목을 입력하세요."  onkeyup="search();">
					<div class="con_menu_search" onclick="searchbar('off');"><img src="../images/member/back.png"></div>
				</div>
				<div class="con_menu_modifyon">
					<div class="con_menu_sub">
						<span class="con_chkbox_cnt">0</span>
						<span>개 선택</span>
					</div>
					<ul class="con_menu_list">
						<li><a onclick="allcheck();">전체선택</a></li>
						<li><a onclick="modifyOff();">취소</a></li>
						<li><a><input type="submit" id="assess_list_delete"><label for="assess_list_delete">삭제</label></a></li>
					</ul>
				</div>
			</nav>
			<!-- // 평가 목록 편집 메뉴 -->
			
			<!-- 평가 틀 -->
			<article class="con_assess" style="background-image: url(../images/member/poster1.jpg);" onclick="chk_enable(this);">
			<input class="con_chkbox" type="checkbox" name="chkbox">
			<div class="con_assess_bg">			
				<div class="con_assess_img_wrap con_assess_float">
					<img class="con_assess_img_poster" src="../images/member/poster1.jpg">
					<!-- 140x200 -->
				</div>
				<div class="con_assess_cont_wrap con_assess_float">
					<div class="con_assess_cont_right_wrap con_assess_float">
						<div class="con_search_target con_assess_cont_subject">뺑반</div>
						<div class="con_assess_cont_genre">범죄, 액션 | 2019.01.30. 개봉 | 133분 | 한국 | 15세 관람가</div>
					</div>
					<table class="con_assess_stars con_assess_float_right">
						<tr>
							<td class="con_th_mintcho"><img src="../images/member/mintchoco.png"></td>
							<td class="con_th_mintcho"><img src="../images/member/mintchoco.png"></td>
							<td class="con_th_mintcho"><img src="../images/member/mintchoco.png"></td>
							<td class="con_th_mintcho"><img src="../images/member/mintchoco.png"></td>
							<td class="con_th_mintcho"><img src="../images/member/mintchoco.png"></td>
							<td>10.0</td>
						</tr>
					</table>
					<div class="clear"></div>					
				</div>
					<div class="con_assess_float">	
						<div class="con_assess_cont_line_wrap con_assess_float">
							<div class="con_assess_cont_line_contents_wrap con_assess_float">
								<div class="con_line_th con_th con_th_mint con_assess_float">
									<span><img src="../images/member/est_good.png"></span>
									<span>200</span>
									<span class="con_th2"><img src="../images/member/est_bad.png"></span>
									<span>12</span>
									<div class="con_assess_stars_date con_assess_float_right">
										<div class="con_th_gray"><img src="../images/member/cal.png" class="con_fav_cal">&nbsp;2020-02-27</div>
									</div>
								</div>
								<div class="clear"></div>
								<div class="con_assess_cont_line_content"><p>재밌었습니다.재밌었습니다.재밌었습니다.재밌었습니다.재밌었습니다.재밌었습니다.재밌었습니다.재밌었습니다.재밌었습니다.재밌었습니다.재밌었습니다.재밌었습니다.재밌었습니다.</p></div>
							</div>
						</div>
						<div class="con_assess_img_stars con_assess_float">							
							<img src="../images/member/mintchoco.png" class="con_assess_mintchoco">
							<div class="con_assess_img_stars_score">10</div>							
						</div>
					</div>
				<div class="clear"></div>
				</div>
			</article>
			<!-- //평가 틀 -->
			
		</div>
		<div class="clear"></div>
	</form>
<!-- //서브메뉴 이하 담당자 내용 -->
</div>
<!-- //담당자 내용 -->
<%@ include file="../_footer.jsp" %>
</body>
</html>