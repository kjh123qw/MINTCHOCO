/**
 * 담당 : 천세문
 */

$(function () {
	
	/* 출력 영화의 수  */
	
	var setAction = document.getElementsByClassName("action").length;
	var setDrama = document.getElementsByClassName("drama").length;
	var setThriller = document.getElementsByClassName("thriller").length;
	var setAnimation = document.getElementsByClassName("animation").length;
	
	/* 실제 이동 값 */
	
	var moveAction = 0;
	var moveDrama = 0;
	var moveThriller = 0;
	var moveAnimation = 0;
	

    $(window).resize(function(){
    	$(".action").show();
    	$(".drama").show();
    	$(".thriller").show();
    	$(".animation").show();
    });
	
	
	
	/* 좌우 버튼 관련*/
	
	/* 액션 버튼 */
	
	$(".r-action-button2").mouseover(function() {
		$(".r-action-button2").css("color", "#F5FFFA");
		$(".r-action-button2").css("top", "65px");
		$(".r-action-button2").css("right", "40px");
		$(".r-action-button2").css("font-size", "7em");
	});
	$(".r-action-button2").mouseout(function() {
		$(".r-action-button2").css("color", "#F5FFFA");
		$(".r-action-button2").css("top", "115px");
		$(".r-action-button2").css("right", "-20px");
		$(".r-action-button2").css("font-size", "50px");
	});
	
	$(".l-action-button2").mouseover(function() {
		$(".l-action-button2").css("color", "#F5FFFA");
		$(".l-action-button2").css("top", "65px");
		$(".l-action-button2").css("font-size", "7em");
	});
	$(".l-action-button2").mouseout(function() {
		$(".l-action-button2").css("color", "#F5FFFA");
		$(".l-action-button2").css("top", "115px");
		$(".l-action-button2").css("font-size", "50px");
	});

	$(".r-action-button2").click(function() {
		
		if(moveAction >= setAction - 4){
			alert('가장 뒤 입니다.');
		}else{
		
		$(".action").eq(moveAction).hide();
		$(".action").eq(moveAction+1).hide();
		$(".action").eq(moveAction+2).hide();
		$(".action").eq(moveAction+3).hide();
		
		moveAction = moveAction + 4;
		
		}
		
	});

	$(".l-action-button2").click(function() {
		
		if(moveAction <= 0){
			alert('가장 앞 입니다.');
		}else{
		
		$(".action").eq(moveAction-4).show();
		$(".action").eq(moveAction-3).show();
		$(".action").eq(moveAction-2).show();
		$(".action").eq(moveAction-1).show();
		
		moveAction = moveAction - 4;
		
		}
		
    });
	
	/* 드라마 버튼 */
	
	$(".r-drama-button2").mouseover(function() {
		$(".r-drama-button2").css("color", "#F5FFFA");
		$(".r-drama-button2").css("top", "345px");
		$(".r-drama-button2").css("right", "40px");
		$(".r-drama-button2").css("font-size", "7em");
	});
	$(".r-drama-button2").mouseout(function() {
		$(".r-drama-button2").css("color", "#F5FFFA");
		$(".r-drama-button2").css("top", "395px");
		$(".r-drama-button2").css("right", "-20px");
		$(".r-drama-button2").css("font-size", "50px");
	});
	
	$(".l-drama-button2").mouseover(function() {
		$(".l-drama-button2").css("color", "#F5FFFA");
		$(".l-drama-button2").css("top", "345px");
		$(".l-drama-button2").css("font-size", "7em");
	});
	$(".l-drama-button2").mouseout(function() {
		$(".l-drama-button2").css("color", "#F5FFFA");
		$(".l-drama-button2").css("top", "395px");
		$(".l-drama-button2").css("font-size", "50px");
	});

	$(".r-drama-button2").click(function() {
		
		if(moveDrama >= setDrama - 4){
			alert('가장 뒤 입니다.');
		}else{
		
		$(".drama").eq(moveDrama).hide();
		$(".drama").eq(moveDrama+1).hide();
		$(".drama").eq(moveDrama+2).hide();
		$(".drama").eq(moveDrama+3).hide();
		
		moveDrama = moveDrama + 4;
		
		}
		
	});

	$(".l-drama-button2").click(function() {
		
		if(moveDrama <= 0){
			alert('가장 앞 입니다.');
		}else{
		
		$(".drama").eq(moveDrama-4).show();
		$(".drama").eq(moveDrama-3).show();
		$(".drama").eq(moveDrama-2).show();
		$(".drama").eq(moveDrama-1).show();
		
		moveDrama = moveDrama - 4;
		
		}
		
    });
	
	/* 스릴러 버튼 */
	
	$(".r-thriller-button2").mouseover(function() {
		$(".r-thriller-button2").css("color", "#F5FFFA");
		$(".r-thriller-button2").css("top", "625px");
		$(".r-thriller-button2").css("right", "40px");
		$(".r-thriller-button2").css("font-size", "7em");
	});
	$(".r-thriller-button2").mouseout(function() {
		$(".r-thriller-button2").css("color", "#F5FFFA");
		$(".r-thriller-button2").css("top", "675px");
		$(".r-thriller-button2").css("right", "-20px");
		$(".r-thriller-button2").css("font-size", "50px");
	});
	
	$(".l-thriller-button2").mouseover(function() {
		$(".l-thriller-button2").css("color", "#F5FFFA");
		$(".l-thriller-button2").css("top", "625px");
		$(".l-thriller-button2").css("font-size", "7em");
	});
	$(".l-thriller-button2").mouseout(function() {
		$(".l-thriller-button2").css("color", "#F5FFFA");
		$(".l-thriller-button2").css("top", "675px");
		$(".l-thriller-button2").css("font-size", "50px");
	});

	$(".r-thriller-button2").click(function() {
		
		if(moveThriller >= setThriller - 4){
			alert('가장 뒤 입니다.');
		}else{
		
		$(".thriller").eq(moveThriller).hide();
		$(".thriller").eq(moveThriller+1).hide();
		$(".thriller").eq(moveThriller+2).hide();
		$(".thriller").eq(moveThriller+3).hide();
		
		moveThriller = moveThriller + 4;
		
		}
		
	});

	$(".l-thriller-button2").click(function() {
		
		if(moveThriller <= 0){
			alert('가장 앞 입니다.');
		}else{
		
		$(".thriller").eq(moveThriller-4).show();
		$(".thriller").eq(moveThriller-3).show();
		$(".thriller").eq(moveThriller-2).show();
		$(".thriller").eq(moveThriller-1).show();
		
		moveThriller = moveThriller - 4;
		
		}
		
    });
	
	/* 애니메이션 버튼 */
	
	$(".r-animation-button2").mouseover(function() {
		$(".r-animation-button2").css("color", "#F5FFFA");
		$(".r-animation-button2").css("top", "905px");
		$(".r-animation-button2").css("right", "40px");
		$(".r-animation-button2").css("font-size", "7em");
	});
	$(".r-animation-button2").mouseout(function() {
		$(".r-animation-button2").css("color", "#F5FFFA");
		$(".r-animation-button2").css("top", "955px");
		$(".r-animation-button2").css("right", "-20px");
		$(".r-animation-button2").css("font-size", "50px");
	});
	
	$(".l-animation-button2").mouseover(function() {
		$(".l-animation-button2").css("color", "#F5FFFA");
		$(".l-animation-button2").css("top", "905px");
		$(".l-animation-button2").css("font-size", "7em");
	});
	$(".l-animation-button2").mouseout(function() {
		$(".l-animation-button2").css("color", "#F5FFFA");
		$(".l-animation-button2").css("top", "955px");
		$(".l-animation-button2").css("font-size", "50px");
	});

	$(".r-animation-button2").click(function() {
		
		if(moveAnimation >= setAnimation - 4){
			alert('가장 뒤 입니다.');
		}else{
		
		$(".animation").eq(moveAnimation).hide();
		$(".animation").eq(moveAnimation+1).hide();
		$(".animation").eq(moveAnimation+2).hide();
		$(".animation").eq(moveAnimation+3).hide();
		
		moveAnimation = moveAnimation + 4;
		
		}
		
	});

	$(".l-animation-button2").click(function() {
		
		if(moveAnimation <= 0){
			alert('가장 앞 입니다.');
		}else{
		
		$(".animation").eq(moveAnimation-4).show();
		$(".animation").eq(moveAnimation-3).show();
		$(".animation").eq(moveAnimation-2).show();
		$(".animation").eq(moveAnimation-1).show();
		
		moveAnimation = moveAnimation - 4;
		
		}
		
    });
	
	/* 액션 버튼 */
	
	$(".r-action-button").mouseover(function() {
		$(".r-action-button").css("color", "#F5FFFA");
		$(".r-action-button").css("top", "160px");
		$(".r-action-button").css("right", "40px");
		$(".r-action-button").css("font-size", "7em");
	});
	$(".r-action-button").mouseout(function() {
		$(".r-action-button").css("color", "#F5FFFA");
		$(".r-action-button").css("top", "210px");
		$(".r-action-button").css("right", "-20px");
		$(".r-action-button").css("font-size", "50px");
	});
	
	$(".l-action-button").mouseover(function() {
		$(".l-action-button").css("color", "#F5FFFA");
		$(".l-action-button").css("top", "160px");
		$(".l-action-button").css("font-size", "7em");
	});
	$(".l-action-button").mouseout(function() {
		$(".l-action-button").css("color", "#F5FFFA");
		$(".l-action-button").css("top", "210px");
		$(".l-action-button").css("font-size", "50px");
	});

	$(".r-action-button").click(function() {
		
		if(moveAction >= setAction - 4){
			alert('가장 뒤 입니다.');
		}else{
		
		$(".action").eq(moveAction).hide();
		$(".action").eq(moveAction+1).hide();
		$(".action").eq(moveAction+2).hide();
		$(".action").eq(moveAction+3).hide();
		
		moveAction = moveAction + 4;
		
		}
		
	});

	$(".l-action-button").click(function() {
		
		if(moveAction <= 0){
			alert('가장 앞 입니다.');
		}else{
		
		$(".action").eq(moveAction-4).show();
		$(".action").eq(moveAction-3).show();
		$(".action").eq(moveAction-2).show();
		$(".action").eq(moveAction-1).show();
		
		moveAction = moveAction - 4;
		
		}
		
    });
	
	/* 드라마 버튼 */
	
	$(".r-drama-button").mouseover(function() {
		$(".r-drama-button").css("color", "#F5FFFA");
		$(".r-drama-button").css("top", "660px");
		$(".r-drama-button").css("right", "40px");
		$(".r-drama-button").css("font-size", "7em");
	});
	$(".r-drama-button").mouseout(function() {
		$(".r-drama-button").css("color", "#F5FFFA");
		$(".r-drama-button").css("top", "710px");
		$(".r-drama-button").css("right", "-20px");
		$(".r-drama-button").css("font-size", "50px");
	});
	
	$(".l-drama-button").mouseover(function() {
		$(".l-drama-button").css("color", "#F5FFFA");
		$(".l-drama-button").css("top", "660px");
		$(".l-drama-button").css("font-size", "7em");
	});
	$(".l-drama-button").mouseout(function() {
		$(".l-drama-button").css("color", "#F5FFFA");
		$(".l-drama-button").css("top", "710px");
		$(".l-drama-button").css("font-size", "50px");
	});

	$(".r-drama-button").click(function() {
		
		if(moveDrama >= setDrama - 4){
			alert('가장 뒤 입니다.');
		}else{
		
		$(".drama").eq(moveDrama).hide();
		$(".drama").eq(moveDrama+1).hide();
		$(".drama").eq(moveDrama+2).hide();
		$(".drama").eq(moveDrama+3).hide();
		
		moveDrama = moveDrama + 4;
		
		}
		
	});

	$(".l-drama-button").click(function() {
		
		if(moveDrama <= 0){
			alert('가장 앞 입니다.');
		}else{
		
		$(".drama").eq(moveDrama-4).show();
		$(".drama").eq(moveDrama-3).show();
		$(".drama").eq(moveDrama-2).show();
		$(".drama").eq(moveDrama-1).show();
		
		moveDrama = moveDrama - 4;
		
		}
		
    });
	
	/* 스릴러 버튼 */
	
	$(".r-thriller-button").mouseover(function() {
		$(".r-thriller-button").css("color", "#F5FFFA");
		$(".r-thriller-button").css("top", "1160px");
		$(".r-thriller-button").css("right", "40px");
		$(".r-thriller-button").css("font-size", "7em");
	});
	$(".r-thriller-button").mouseout(function() {
		$(".r-thriller-button").css("color", "#F5FFFA");
		$(".r-thriller-button").css("top", "1210px");
		$(".r-thriller-button").css("right", "-20px");
		$(".r-thriller-button").css("font-size", "50px");
	});
	
	$(".l-thriller-button").mouseover(function() {
		$(".l-thriller-button").css("color", "#F5FFFA");
		$(".l-thriller-button").css("top", "1160px");
		$(".l-thriller-button").css("font-size", "7em");
	});
	$(".l-thriller-button").mouseout(function() {
		$(".l-thriller-button").css("color", "#F5FFFA");
		$(".l-thriller-button").css("top", "1210px");
		$(".l-thriller-button").css("font-size", "50px");
	});

	$(".r-thriller-button").click(function() {
		
		if(moveThriller >= setThriller - 4){
			alert('가장 뒤 입니다.');
		}else{
		
		$(".thriller").eq(moveThriller).hide();
		$(".thriller").eq(moveThriller+1).hide();
		$(".thriller").eq(moveThriller+2).hide();
		$(".thriller").eq(moveThriller+3).hide();
		
		moveThriller = moveThriller + 4;
		
		}
		
	});

	$(".l-thriller-button").click(function() {
		
		if(moveThriller <= 0){
			alert('가장 앞 입니다.');
		}else{
		
		$(".thriller").eq(moveThriller-4).show();
		$(".thriller").eq(moveThriller-3).show();
		$(".thriller").eq(moveThriller-2).show();
		$(".thriller").eq(moveThriller-1).show();
		
		moveThriller = moveThriller - 4;
		
		}
		
    });
	
	/* 애니메이션 버튼 */
	
	$(".r-animation-button").mouseover(function() {
		$(".r-animation-button").css("color", "#F5FFFA");
		$(".r-animation-button").css("top", "1660px");
		$(".r-animation-button").css("right", "40px");
		$(".r-animation-button").css("font-size", "7em");
	});
	$(".r-animation-button").mouseout(function() {
		$(".r-animation-button").css("color", "#F5FFFA");
		$(".r-animation-button").css("top", "1710px");
		$(".r-animation-button").css("right", "-20px");
		$(".r-animation-button").css("font-size", "50px");
	});
	
	$(".l-animation-button").mouseover(function() {
		$(".l-animation-button").css("color", "#F5FFFA");
		$(".l-animation-button").css("top", "1660px");
		$(".l-animation-button").css("font-size", "7em");
	});
	$(".l-animation-button").mouseout(function() {
		$(".l-animation-button").css("color", "#F5FFFA");
		$(".l-animation-button").css("top", "1710px");
		$(".l-animation-button").css("font-size", "50px");
	});

	$(".r-animation-button").click(function() {
		
		if(moveAnimation >= setAnimation - 4){
			alert('가장 뒤 입니다.');
		}else{
		
		$(".animation").eq(moveAnimation).hide();
		$(".animation").eq(moveAnimation+1).hide();
		$(".animation").eq(moveAnimation+2).hide();
		$(".animation").eq(moveAnimation+3).hide();
		
		moveAnimation = moveAnimation + 4;
		
		}
		
	});

	$(".l-animation-button").click(function() {
		
		if(moveAnimation <= 0){
			alert('가장 앞 입니다.');
		}else{
		
		$(".animation").eq(moveAnimation-4).show();
		$(".animation").eq(moveAnimation-3).show();
		$(".animation").eq(moveAnimation-2).show();
		$(".animation").eq(moveAnimation-1).show();
		
		moveAnimation = moveAnimation - 4;
		
		}
		
    });
	
	/* 992px 미만 장르 버튼 */
	
	$(".action").show();
	$(".drama").show();
	$(".thriller").show();
	$(".animation").show();
	
	$('.select1').click(function(){
		
		$(".drama").hide();
		$(".thriller").hide();
		$(".animation").hide();
		$(".action").show();
		
	});
	
	$('.select2').click(function(){
		
		$(".action").hide();
		$(".thriller").hide();
		$(".animation").hide();
		$(".drama").show();
		
	});
	
	$('.select3').click(function(){
		
		$(".action").hide();
		$(".drama").hide();
		$(".animation").hide();
		$(".thriller").show();
		
	});
	
	$('.select4').click(function(){
		
		$(".action").hide();
		$(".drama").hide();
		$(".thriller").hide();
		$(".animation").show();
		
	});
	
});
