/**
 * 담당 : 천세문
 */

$(function () {
	
	/* 좌우 버튼 관련*/
	
	/* 1번 */
	var set = 0;
	
	$(".r-button1").mouseover(function() {
		$(".r-button1").css("color", "#F5FFFA");
		$(".r-button1").css("top", "150px");
		$(".r-button1").css("font-size", "7em");
	});
	$(".r-button1").mouseout(function() {
		$(".r-button1").css("color", "#F5FFFA");
		$(".r-button1").css("top", "200px");
		$(".r-button1").css("font-size", "50px");
	});

	$(".l-button1").mouseover(function() {
		$(".l-button1").css("color", "#F5FFFA");
		$(".l-button1").css("top", "150px");
		$(".l-button1").css("font-size", "7em");
	});
	$(".l-button1").mouseout(function() {
		$(".l-button1").css("color", "#F5FFFA");
		$(".l-button1").css("top", "200px");
		$(".l-button1").css("font-size", "50px");
	});
	
	$(".r-button1").click(function() {
		
		if(set >= 8){
			alert('가장 뒤 입니다.')
		}else{
		
		$(".movie").eq(set).hide();
		$(".movie").eq(set+1).hide();
		$(".movie").eq(set+2).hide();
		$(".movie").eq(set+3).hide();
		
		set = set + 4;
		
		}
		
	});

	$(".l-button1").click(function() {
		
		if(set <= 0){
			alert('가장 앞 입니다.')
		}else{
		
		$(".movie").eq(set-4).show();
		$(".movie").eq(set-3).show();
		$(".movie").eq(set-2).show();
		$(".movie").eq(set-1).show();
		
		set = set - 4;
		
		}
		
    });
	
});
