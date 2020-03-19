$(function(){
	
	/*스크롤 위치 리턴*/
	$(window).on('mousewheel',function(e){
		
		var scrollPosition = window.scrollY;

		if(scrollPosition > 300){
			$('#upBtn').show(500);
		}else{
			$('#upBtn').hide(500);
		}
		
	});
	/*스크롤 위치 리턴*/
	
	//슬라이드 메뉴
	$('.slideMovie').slick({
		  slidesToShow: 3,
		  slidesToScroll: 1,
		  dots: false,
		  centerMode: true,
		  arrows:false,
		  focusOnSelect: true,	  
		  autoplay: true,
		  autoplaySpeed:2000
		});
	//슬라이드 메뉴
	
	//처음 메뉴리스트 숨기기
	$("#menuList").hide();
	$('#upBtn').hide();
	//처음 메뉴리스트 숨기기
	
	
	//영화포스터 마우스오버
	$(".mainMovie").mouseover(function(){
		$(this).children(".movieInfo").stop().animate({
            bottom:"0"
           },500)
	})
	//영화포스터 마우스오버
	
	//영화포스터 마우스리브
	$(".mainMovie").mouseleave(function(){
		$(this).children(".movieInfo").stop().animate({
            bottom:"-200px"
           },500)
	})
	//영화포스터 마우스리브
	
})

function menuOpen(){ //메뉴 토글 함수
    
    var classValue = $('#bar').attr('class');
    var bar= $("#bar");
    var flag = $("#menuCheck").is(":checked");
    
    if(flag == true){ //체크
        setTimeout(function(){
            bar.attr('class','ooo');
        $("#menuCheck").prop("checked", false);
        },300)
    }else{
        setTimeout(function(){
            bar.attr('class','xxx');
        $("#menuCheck").prop("checked", true);
        },300)
    }

    if(flag == true){ //div 올라갈때
    	$('html').css('overflow-y','visible')
    	$('#menuList').fadeOut( 500 );
        $("#menuDiv").stop().delay(300).animate({
            top:"-100%"
           },500,function(){
        	   $('#mainTitle').css('color', '#39313b');
        	   
           })       
    }else{ //div 내려갈때
    	$('html').css('overflow-y','hidden')
           $("#menuDiv").stop().animate({
            top:"0"
           },500,function(){
        	   $('#mainTitle').css('color', '#70ad92');
        	   $('#menuList').fadeIn( 500 );
           })

           
    }

}


function goTop(){
	$('html').animate( { scrollTop : 0 }, 1000 ) 
	$('#upBtn').hide(500);
}

function returnWidth(){
	function returnWidth(){
		var windowWidth;
		windowWidth = $( window ).width();
		return windowWidth;
	}
}

function snsEvent(){
	alert("준비중입니다.");
}

function detailMember(number){

	location.href='/mintchoco/my_page.do';
}

function detailMovie(number){
	
	location.href='/mintchoco/movie/detail.do?movieNumber=' + number;
}

function goMypage(number){
	location.href="my_page.do";
}

function goMovie(){
	location.href="movie/recommend.do";
}

function goNotice(){
	location.href="service/notice.do";
}

function goLogout(){
	
	var logoutFlag = confirm("로그아웃 하시겠습니까?");
	
	if(logoutFlag){
		location.href = "logout.do";		
	}
	
}