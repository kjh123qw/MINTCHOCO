/**
 *  담당자 : 김정호
 */

$(function() {
	$('#testBtn').click(function() {
	    $.ajax({
	        url :"nextpage.do",
	        type :"POST",
	        data :{ pageNum: '한글1' },
	        dataType: 'json',
	        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        success :function(data){
	        	
	        	console.log(data[0]);
//	            var html ="";
//	            for(var i=0; i<response.length; i++){
//	                html +="<option value='"+response[i].versionInfo+"' label='"+response[i].versionInfo+"'/>";
//	            }
//	            $("#pushVersion").html(html);
//	            $("#pushVersion").selectpicker("refresh");
	        }
	    });


//		$.ajax("nextpage.do").done(function() { alert("성공"); }).fail(function() { alert("실패"); }).always(function() { alert("완료"); });
//        $.ajax({
//            url:'nextpage.do',
//            type:'get',
//            data:{pageNum:1},
//            success:function(data){
//                console.log(data);
//            }
//        })

		
//		$.ajax({ 
//			url: "nextpage.do", // 클라이언트가 HTTP 요청을 보낼 서버의 URL 주소
//			data: { pageNum: 1 }, // HTTP 요청과 함께 서버로 보낼 데이터 
//			method: "GET", // HTTP 요청 메소드(GET, POST 등) 
//			dataType: "json" // 서버에서 보내줄 데이터의 타입 
//		})
//		// HTTP 요청이 성공하면 요청한 데이터가 done() 메소드로 전달됨. 
//		.done(function(json) {
//			$("<h1>").text(json.title).appendTo("body"); 
//			$("<div class=\"content\">").html(json.html).appendTo("body"); 
//		})
//		// HTTP 요청이 실패하면 오류와 상태에 관한 정보가 fail() 메소드로 전달됨. 
//		.fail(function(xhr, status, errorThrown) {
//			$("#text").html("오류가 발생했다.<br>") 
//			.append("오류명: " + errorThrown + "<br>") 
//			.append("상태: " + status); 
//		})
//		// 
//		.always(function(xhr, status) {
//			$("#text").html("요청이 완료되었습니다!"); 
//		});
	});
});