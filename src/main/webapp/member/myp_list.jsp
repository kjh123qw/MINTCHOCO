<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 찜 한 리스트 ::
	담당자 : 장세진
*/
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<!-- 마이페이지 내 페이지별 js/css -->
<link rel="stylesheet" href="${ contextPath }/css/member/myp_fav.css">
<!-- //마이페이지 내 페이지별  js, css -->
<%@ include file="./myp_submenu.jsp" %>
<!-- 서브메뉴 이하 담당자 내용 -->
<!-- 찜한 목록 -->
	<form class="myp_con_fav" method="post" action="" onsubmit="return  chk_cnt();">
		<div class="con_fav_wrap">
			<nav class="con_menu">
				<div class="con_menu_modifyoff  con_menu_dropdown_rel"">
					<div class="con_menu_sub">SEZI 님의 관심영화목록</div>
					<ul class="con_menu_list con_menu_dropdown_rel">
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
					<input type="text" class="con_menu_input"  placeholder="검색하실 영화제목을 입력하세요." onkeyup="search();">
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
						<li><a><input type="submit" id="fav_list_delete"><label for="fav_list_delete">삭제</label></a></li>
					</ul>
				</div>
			</nav>
			<div class="clear"></div>
			
			<article class="con_fav con_fav_float" onclick="chk_enable(this);">
			<input class="con_chkbox" type="checkbox" name="chkbox">
			<div class="con_fav_bg">			
				<div class="con_fav_img_wrap">
					<img class="con_fav_poster" src="../images/member/poster1.jpg">
					<!-- 140x200 -->
					<center class="con_fav_contents_wrap">
						<div class="con_search_target con_th_mint">뺑반</div>
						<div>범죄, 액션, 133분</div>
						<div class="con_th_gray">한국, 15세 관람가</div>
						<div class="con_th_gray"><img src="../images/member/cal.png" class="con_fav_cal">&nbsp;2020-02-27</div>
					</center>
				</div>				
			</div>
			</article>
			
			<article class="con_fav con_fav_float" onclick="chk_enable(this);">
			<input class="con_chkbox" type="checkbox" name="chkbox">
			<div class="con_fav_bg">			
				<div class="con_fav_img_wrap">
					<img class="con_fav_poster" src="../images/member/poster1.jpg">
					<!-- 140x200 -->
					<center class="con_fav_contents_wrap">
						<div class="con_search_target con_th_mint">뺑반</div>
						<div>범죄, 액션, 133분</div>
						<div class="con_th_gray">한국, 15세 관람가</div>
						<div class="con_th_gray"><img src="../images/member/cal.png" class="con_fav_cal">&nbsp;2020-02-27</div>
					</center>
				</div>				
			</div>
			</article>
			
			<article class="con_fav con_fav_float" onclick="chk_enable(this);">
			<input class="con_chkbox" type="checkbox" name="chkbox">
			<div class="con_fav_bg">			
				<div class="con_fav_img_wrap">
					<img class="con_fav_poster" src="../images/member/poster1.jpg">
					<!-- 140x200 -->
					<center class="con_fav_contents_wrap">
						<div class="con_search_target con_th_mint">뺑반</div>
						<div>범죄, 액션, 133분</div>
						<div class="con_th_gray">한국, 15세 관람가</div>
						<div class="con_th_gray"><img src="../images/member/cal.png" class="con_fav_cal">&nbsp;2020-02-27</div>
					</center>
				</div>				
			</div>
			</article>
			
			<article class="con_fav con_fav_float" onclick="chk_enable(this);">
			<input class="con_chkbox" type="checkbox" name="chkbox">
			<div class="con_fav_bg">			
				<div class="con_fav_img_wrap">
					<img class="con_fav_poster" src="../images/member/poster1.jpg">
					<!-- 140x200 -->
					<center class="con_fav_contents_wrap">
						<div class="con_search_target con_th_mint">뺑반</div>
						<div>범죄, 액션, 133분</div>
						<div class="con_th_gray">한국, 15세 관람가</div>
						<div class="con_th_gray"><img src="../images/member/cal.png" class="con_fav_cal">&nbsp;2020-02-27</div>
					</center>
				</div>				
			</div>
			</article>
			
			<article class="con_fav con_fav_float" onclick="chk_enable(this);">
			<input class="con_chkbox" type="checkbox" name="chkbox">
			<div class="con_fav_bg">			
				<div class="con_fav_img_wrap">
					<img class="con_fav_poster" src="../images/member/poster1.jpg">
					<!-- 140x200 -->
					<center class="con_fav_contents_wrap">
						<div class="con_search_target con_th_mint">뺑반</div>
						<div>범죄, 액션, 133분</div>
						<div class="con_th_gray">한국, 15세 관람가</div>
						<div class="con_th_gray"><img src="../images/member/cal.png" class="con_fav_cal">&nbsp;2020-02-27</div>
					</center>
				</div>				
			</div>
			</article>
			
			<article class="con_fav con_fav_float" onclick="chk_enable(this);">
			<input class="con_chkbox" type="checkbox" name="chkbox">
			<div class="con_fav_bg">			
				<div class="con_fav_img_wrap">
					<img class="con_fav_poster" src="../images/member/poster1.jpg">
					<!-- 140x200 -->
					<center class="con_fav_contents_wrap">
						<div class="con_search_target con_th_mint">뺑반</div>
						<div>범죄, 액션, 133분</div>
						<div class="con_th_gray">한국, 15세 관람가</div>
						<div class="con_th_gray"><img src="../images/member/cal.png" class="con_fav_cal">&nbsp;2020-02-27</div>
					</center>
				</div>				
			</div>
			</article>
			
			<article class="con_fav con_fav_float" onclick="chk_enable(this);">
			<input class="con_chkbox" type="checkbox" name="chkbox">
			<div class="con_fav_bg">			
				<div class="con_fav_img_wrap">
					<img class="con_fav_poster" src="../images/member/poster1.jpg">
					<!-- 140x200 -->
					<center class="con_fav_contents_wrap">
						<div class="con_search_target con_th_mint">뺑반</div>
						<div>범죄, 액션, 133분</div>
						<div class="con_th_gray">한국, 15세 관람가</div>
						<div class="con_th_gray"><img src="../images/member/cal.png" class="con_fav_cal">&nbsp;2020-02-27</div>
					</center>
				</div>				
			</div>
			</article>
			
			<article class="con_fav con_fav_float" onclick="chk_enable(this);">
			<input class="con_chkbox" type="checkbox" name="chkbox">
			<div class="con_fav_bg">			
				<div class="con_fav_img_wrap">
					<img class="con_fav_poster" src="../images/member/poster1.jpg">
					<!-- 140x200 -->
					<center class="con_fav_contents_wrap">
						<div class="con_search_target con_th_mint">뺑반</div>
						<div>범죄, 액션, 133분</div>
						<div class="con_th_gray">한국, 15세 관람가</div>
						<div class="con_th_gray"><img src="../images/member/cal.png" class="con_fav_cal">&nbsp;2020-02-27</div>
					</center>
				</div>				
			</div>
			</article>
			
			<article class="con_fav con_fav_float" onclick="chk_enable(this);">
			<input class="con_chkbox" type="checkbox" name="chkbox">
			<div class="con_fav_bg">			
				<div class="con_fav_img_wrap">
					<img class="con_fav_poster" src="../images/member/poster1.jpg">
					<!-- 140x200 -->
					<center class="con_fav_contents_wrap">
						<div class="con_search_target con_th_mint">뺑반</div>
						<div>범죄, 액션, 133분</div>
						<div class="con_th_gray">한국, 15세 관람가</div>
						<div class="con_th_gray"><img src="../images/member/cal.png" class="con_fav_cal">&nbsp;2020-02-27</div>
					</center>
				</div>				
			</div>
			</article>
			
			<article class="con_fav con_fav_float" onclick="chk_enable(this);">
			<input class="con_chkbox" type="checkbox" name="chkbox">
			<div class="con_fav_bg">			
				<div class="con_fav_img_wrap">
					<img class="con_fav_poster" src="../images/member/poster1.jpg">
					<!-- 140x200 -->
					<center class="con_fav_contents_wrap">
						<div class="con_search_target con_th_mint">뺑반</div>
						<div>범죄, 액션, 133분</div>
						<div class="con_th_gray">한국, 15세 관람가</div>
						<div class="con_th_gray"><img src="../images/member/cal.png" class="con_fav_cal">&nbsp;2020-02-27</div>
					</center>
				</div>				
			</div>
			</article>
			
			<article class="con_fav con_fav_float" onclick="chk_enable(this);">
			<input class="con_chkbox" type="checkbox" name="chkbox">
			<div class="con_fav_bg">			
				<div class="con_fav_img_wrap">
					<img class="con_fav_poster" src="../images/member/poster1.jpg">
					<!-- 140x200 -->
					<center class="con_fav_contents_wrap">
						<div class="con_search_target con_th_mint">뺑반</div>
						<div>범죄, 액션, 133분</div>
						<div class="con_th_gray">한국, 15세 관람가</div>
						<div class="con_th_gray"><img src="../images/member/cal.png" class="con_fav_cal">&nbsp;2020-02-27</div>
					</center>
				</div>				
			</div>
			</article>
			
			<div class="clear"></div>
		</div>
	</form>
<!-- //서브메뉴 이하 담당자 내용 -->
</div>
<!-- //담당자 내용 -->
<%@ include file="../_footer.jsp" %>
</body>
</html>