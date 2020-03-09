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
				
			})
		}
		inputReset();
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


function loginCheck(){
	
	 $.ajax({
	        url:"login.do",
	        type:'post',
	        datatype:'text',
	        success:function(msg){
	        	
	        	alert("성공")
	        	$(".cst-btn").effect( "bounce", {times:3}, 1000 );


	        },
	        error:function(){
	        	alert("실패")
	        	$(".cst-btn").effect( "shake", {times:3}, 1000 );
	        }
	    });
	 
	 
	 return false;

	
}

function join(){
	var email = joinForm.email.value;
	var pwd1 = joinForm.pwd.value;
	var pwd2 = joinForm.pwdCheck.value;
	var name = joinForm.name.value;
	var nickname = joinForm.nickname.value;
	var age = joinForm.age.value;
	var gender = joinForm.gender.value; //공백 or M or F
	var serviceCheck = $("input:checkbox[name='serviceCheck']").is(":checked"); //false or true
	
	/*공백확인*/
	if(email == "" || pwd1 == "" || pwd2 == "" || name == "" || nickname == "" || age == "" || gender == ""){
		alert("입력란의 빈칸을 확인해주세요")
		return false;
	}
	/*공백확인*/
	
	
	/*정규표현식*/
	
	var emailCheck = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	if(!emailCheck.test(email)){
		alert("이메일를 확인해주세요")
		return false;
	}
	
	var pwdCheck = /^[A-Za-z0-9]{6,18}$/;

	if(!pwdCheck.test(pwd1) || !pwdCheck.test(pwd2)){
		alert("비밀번호는 한글,영어 6글자에서 18글자로 작성해주세요")
		return false;
	}
	
	if(pwd1 != pwd2){
		alert("비밀번호를 동일하게 작성해주세요")
		return false;
	}
	
	var nameCheck = /^[가-힣]{2,4}$/;
	
	if(!nameCheck.test(name)){
		alert("이름은 한글 2글자에서 4글자로 작성해주세요")
		return false;
	}
	
	var nickNameCheck = /^[A-Za-z0-9가-힣]{2,18}$/;
	
	if(!nickNameCheck.test(nickname)){
		alert("별명은 2글자에서 18글자로 작성해주세요")
		return false;
	}

	
	/*정규표현식*/
	
	if(age < 0 || age > 100){
		alert("나이를 확인해주세요")
		return false;
	}
	
	
	/*약관동의*/
	if(!serviceCheck){
		alert("서비스약관에 동의해주세요")
		return false;
	}
	/*약관동의*/
	
	var allData = { "email": email, "nickname": nickname };
	
	$.ajax({
        url:"joinCheck.do",
        type:'post',
        data: allData,
        success:function(data){
        	if(data == 'overlapEmail'){
        		alert("중복되는 이메일입니다.")
        		$("input[name='email']").effect( "shake", {times:3}, 1000 );
        		return false;
        	}else if(data == 'overlapNickname'){
        		alert("중복되는 닉네임입니다.")
        		$("input[name='nickname']").effect( "shake", {times:3}, 1000 );
        		return false;
        	}else{        

        		var joinData = { "email": email, "pwd": pwd1,"gender": gender,
        						"name": name,"nickname": nickname ,  "age": age,};
        		
        		 $.ajax({
        		        url:"join.do",
        		        
        		        type:'post',
        		        data: joinData,
        		        datatype:'text',
        		        success:function(msg){
        		        	alert(msg)
        		        	
        		        	var width = returnWidth();     		    		
        		    			
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
        		        	
        		        },
        		        error:function(request,status,error){
        		            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);}

        		    });
        		
        	}
        },
        error:function(){
        	alert('실패')
        }
    });
}

function find(){
	var email = findForm.email.value;
	var name = findForm.name.value;
	
	/*공백확인*/
	if(email == "" || name == "" ){
		alert("입력란의 빈칸을 확인해주세요")
		return false;
	}
	/*공백확인*/
	
	
	/*정규표현식*/
	
	var emailCheck = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	if(!emailCheck.test(email)){
		alert("이메일를 확인해주세요")
		return false;
	}
	
	var nameCheck = /^[가-힣]{2,4}$/;
	
	if(!nameCheck.test(name)){
		alert("이름은 한글 2글자에서 4글자로 작성해주세요")
		return false;
	}
	
	/*정규표현식*/
	
	var findData = { "email": email, "name": name};

$.ajax({
    url:"findCheck.do",
    type:'post',
    data: findData,
    datatype:'text',
    success:function(data){
    	if(data == "null"){
    		alert("이메일과 이름을 확인해주세요")
    		$(".cst-btn").effect( "shake", {times:3}, 1000 );
    		return false;
    	}else{
    		
    		$.ajax({
    			url:"sendMail.do",
    			type:"post",
    			data:{"email":email, "name":name},
    			success:function(data){
    				alert("입력하신 이메일로 임시 비밀번호를 보내드립니다.")
    				
    				var width = returnWidth();     		    		
        		    			
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
    				
    			},
    			error:function(){
    				alert("실패")
    			}
    		})
    		
    	}
    },
    error:function(request,status,error){
    	alert("실패")
    }
	});
return false;
}


