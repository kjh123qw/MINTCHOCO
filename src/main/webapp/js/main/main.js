$(function(){
	
	$(window).resize(function (){
		  // width값을 가져오기
		  var width_size = window.outerWidth;
		  
		  
		  console.log(width_size);

		  if(width_size <=320){
			  
		  }else if(width_size <=576){

			  
			  
		  }else if(width_size <=768){

			  
		  }else if(width_size <=992){
			  
		  }else if(width_size <=1200){

			  
		  }else{
			  
		  }
		  
		})
	
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
