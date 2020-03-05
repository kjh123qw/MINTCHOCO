/**
 * 담당 : 천세문
 */

$(function () {

var set = 0;

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
