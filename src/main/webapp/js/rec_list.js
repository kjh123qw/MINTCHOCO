/**
 * 담당 : 천세문
 */

$(function () {

var set = 0;
	
	$("#r_button").mouseover(function() {
		$("#r_button").css("color", "snow");
		$("#r_button").css("top", "125px");
		$("#r_button").css("font-size", "7em");
	});
	$("#r_button").mouseout(function() {
		$("#r_button").css("color", "#00d7cb");
		$("#r_button").css("top", "175px");
		$("#r_button").css("font-size", "50px");
	});

	$("#l_button").mouseover(function() {
		$("#l_button").css("color", "snow");
		$("#l_button").css("top", "125px");
		$("#l_button").css("font-size", "7em");
	});
	$("#l_button").mouseout(function() {
		$("#l_button").css("color", "#00d7cb");
		$("#l_button").css("top", "175px");
		$("#l_button").css("font-size", "50px");
	});
	
	$("#r_button").click(function() {
		
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

	$("#l_button").click(function() {
		
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
