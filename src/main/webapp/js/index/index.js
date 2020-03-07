$(function(){
	
	var windowWidth;
	$( window ).resize(function() {
		windowWidth = $( window ).width();
		
		var loginDivLeft = $( "#loginDiv" ).css( "left" );
		var joinDivLeft = $( "#joinDiv" ).css( "left" );
		var findDivLeft = $( "#findDiv" ).css( "left" );
		
		var loginDivDisplay = $( "#loginDiv" ).css( "display" );
		var joinDivDisplay = $( "#joinDiv" ).css( "display" );
		var findDivDisplay = $( "#findDiv" ).css( "display" );
		
		
		if(windowWidth >= 992){
			
			if(loginDivLeft == '0px'){
				$("#loginDiv").stop().css({'left': '60%'});
				$("#joinDiv").stop().css({'left': '100%'});
				$("#findDiv").stop().css({'left': '100%'});
			}else if(joinDivLeft == '0px'){
				$("#loginDiv").stop().css({'left': '100%'});
				$("#joinDiv").stop().css({'left': '60%'});
				$("#findDiv").stop().css({'left': '100%'});
			}else if(findDivLeft == '0px'){
				$("#loginDiv").stop().css({'left': '100%'});
				$("#joinDiv").stop().css({'left': '100%'});
				$("#findDiv").stop().css({'left': '60%'});
			}
		}else{
			
			if(loginDivDisplay == 'block'){
				$("#loginDiv").stop().css({'left': '0px'});
				$("#joinDiv").stop().css({'left': '100%'});
				$("#findDiv").stop().css({'left': '100%'});
			}else if(joinDivDisplay == 'block'){
				$("#loginDiv").stop().css({'left': '100%'});
				$("#joinDiv").stop().css({'left': '0px'});
				$("#findDiv").stop().css({'left': '100%'});
			}else if(findDivDisplay == 'block'){
				$("#loginDiv").stop().css({'left': '100%'});
				$("#joinDiv").stop().css({'left': '100%'});
				$("#findDiv").stop().css({'left': '0px'});
			}

		}
		
	 });
	

	
	
	
	$("#loginDiv").hide();
	$("#joinDiv").hide();
	$("#findDiv").hide();
	
	$("#skipBtn1").click(function(){
		var width = returnWidth();
		
		$("#mainDiv1").stop().animate({bottom:"100%"},1000)
		$("#mainDiv2").stop().animate({top:"0"},1000,function(){
			$("#loginDiv").show();
			
			if(width >= 992){
				$('#loginDiv').stop().delay(300).animate({left:"60%"},500);
			}else{
				$('#loginDiv').stop().delay(300).animate({left:"0"},500);	
			}
			
		})
	})
	
	$("#upBtn").click(function(){
		
		var width = returnWidth();
		
		var className = $(this).attr('class');
		
		if(className == 'widthArrow'){ //로그인 화면 돌아가기
			
			$('#joinDiv').stop().delay(200).animate({left:"100%"},500)
			$('#findDiv').stop().delay(200).animate({left:"100%"},500,function(){
				$("#joinDiv").hide();
				$("#findDiv").hide();
				$("#loginDiv").show();
				
				if(width >= 992){
					$('#loginDiv').stop().delay(300).animate({left:"60%"},500);	
				}else{
					$('#loginDiv').stop().delay(300).animate({left:"0"},500);			
				}
				
				$("#upBtn").stop().css({'transform': 'rotate(0deg)'},1000);			
				$("#upBtn").removeClass('widthArrow').addClass('heightArrow');
			})
			
		}else if(className == 'heightArrow'){ // 동영상화면으로
			
			$("#mainDiv1").stop().animate({bottom:"0"},1000)
			$("#mainDiv2").stop().animate({top:"100%"},1000,function(){
				$('#loginDiv').stop().delay(300).animate({left:"100%"},500);
				$("#loginDiv").hide();
				inputReset();
				
			})
		}
		
	})

	
})


function returnWidth(){
	var windowWidth;
	windowWidth = $( window ).width();
	return windowWidth;
}

function inputReset(){
	$('input.text-200').val("");
}

function joinMember(){
	var width = returnWidth();
	
	$('#loginDiv').stop().delay(200).animate({left:"100%"},500,function(){
		$("#loginDiv").hide();
		$("#joinDiv").show();
		
		if(width >= 992){
			$('#joinDiv').stop().delay(200).animate({left:"60%"},500);
		}else{
			
			$('#joinDiv').stop().delay(200).animate({left:"0"},500);	
		}
		
		
		$("#upBtn").stop().css({'transform': 'rotate(-90deg)'},1000);
		$("#upBtn").removeClass('heightArrow').addClass('widthArrow');
	})
	
	

	
	
	
	
	
	
	
	
	
	
	
	
}


function findPassword(){
	var width = returnWidth();
	
	$('#loginDiv').stop().delay(200).animate({left:"100%"},500,function(){
		$("#loginDiv").hide();
		$("#findDiv").show();
		if(width >= 992){
			$('#findDiv').stop().delay(200).animate({left:"60%"},500);
		}else{
			$('#findDiv').stop().delay(200).animate({left:"0"},500);	
		}
		$("#upBtn").stop().css({'transform': 'rotate(-90deg)'},1000);
		$("#upBtn").removeClass('heightArrow').addClass('widthArrow');
	})
}

