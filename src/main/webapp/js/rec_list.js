/**
 * 담당 : 천세문
 */

var set;

$(function () {
	
	set = 0;
	
	$("#r_button").click(function() {
		$(".movie").eq(set).hide();
		$(".movie").eq(set+1).hide();
		$(".movie").eq(set+2).hide();
		$(".movie").eq(set+3).hide();
		
    });
	
	$("#l_button").click(function() {
		$(".movie").eq(set).show();
		$(".movie").eq(set+1).show();
		$(".movie").eq(set+2).show();
		$(".movie").eq(set+3).show();
		
    });

});


