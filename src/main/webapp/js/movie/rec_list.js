/**
 * 담당 : 천세문
 */

$(function () {

var set = 0;
	
	$("#rButton").mouseover(function() {
		$("#rButton").css("color", "#F5FFFA");
		$("#rButton").css("top", "125px");
		$("#rButton").css("font-size", "7em");
	});
	$("#rButton").mouseout(function() {
		$("#rButton").css("color", "#F5FFFA");
		$("#rButton").css("top", "175px");
		$("#rButton").css("font-size", "50px");
	});

	$("#lButton").mouseover(function() {
		$("#lButton").css("color", "#F5FFFA");
		$("#lButton").css("top", "125px");
		$("#lButton").css("font-size", "7em");
	});
	$("#lButton").mouseout(function() {
		$("#lButton").css("color", "#F5FFFA");
		$("#lButton").css("top", "175px");
		$("#lButton").css("font-size", "50px");
	});
	
	$("#rButton").click(function() {
		
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

	$("#lButton").click(function() {
		
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
