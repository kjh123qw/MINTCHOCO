/**
 * 담당 : 장세진
 */
var modify_mode = 0;
var chkbox_count = 0;

function searchbar(element) {
	var m = element;
	var list = document.getElementsByClassName('con-menu-list')[0];
	var searchInput = document.getElementsByClassName('con-menu-input')[0];
	var searchBack = document.getElementsByClassName('con-menu-search')[0];
	var eos = document.getElementsByClassName("con-search-none")[0];
	
	if (element == 'on') {
		document.getElementsByClassName("con-menu-input")[0].focus();
		searchInput.style.display = 'block';
		searchBack.style.display = 'block';
		list.style.display = 'none';
	} else if (element == 'off') {
		document.getElementsByClassName("con-menu-input")[0].value = "";
		search();
		eos.style.display = "none";
		searchInput.style.display = 'none';
		searchBack.style.display = 'none';
		list.style.display = 'block';
	}
}

function search(){

    var input, search_target, item, i, eos;

    input = document.getElementsByClassName("con-menu-input")[0].value.toUpperCase();
    item = document.getElementsByTagName("article");
    eos = document.getElementsByClassName("con-search-none")[0];
    
    for(i=0;i<item.length;i++){
    	search_target = item[i].getElementsByClassName("con-search-target");
	    if(search_target[0].innerHTML.toUpperCase().indexOf(input) > -1){
	      item[i].style.display = "flex";
	      eos.style.display = "block";
	    } else {
	      item[i].style.display = "none";
	      eos.style.display = "block";
	    }
    }
}

function textAlign(element) {
	var m = element;
	var text = document.getElementsByClassName('menu-description-subject')[0];
	if (m == 'reg') {
		text.innerText='회원님이 등록한 날짜 순으로 정렬합니다.';	
	} else if(m == 'rel') {
		text.innerText='영화 개봉 날짜 순으로 정렬합니다.';
	} else if(m == 'sub') {
		text.innerText='영화 제목 순으로 정렬합니다.';
	} else if(m == 'star') {
		text.innerText='부여한 점수 순으로 정렬합니다.';
	} 
	
}

function modifyOn() {
	var modifyon = document.getElementsByClassName('con-menu-modifyon')[0];
	var modifyoff = document.getElementsByClassName('con-menu-modifyoff')[0];
	modifyon.style.display = 'block';
	modifyoff.style.display = 'none';
	modify_mode = 1;
}

function modifyOff() {
	var modifyon = document.getElementsByClassName('con-menu-modifyon')[0];
	var modifyoff = document.getElementsByClassName('con-menu-modifyoff')[0];
	var chkbox = document.getElementsByClassName("con-chkbox");
	var leng = chkbox.length;
    for (i=0; i<leng; i++) {
    	document.getElementsByClassName("con-chkbox")[i].checked = false;
    }
    chkbox_count = 0;
	modifyoff.style.display = 'block';
	modifyon.style.display = 'none';
	modify_mode = 0;

    var display_cnt = document.getElementsByClassName('con-chkbox-cnt')[0];
    display_cnt.innerText = '0';
}

function allcheck() {
	chkbox_count = 0;
	var chkbox = document.getElementsByClassName("con-chkbox");    
	var leng = chkbox.length;    
	
    for (i=0; i<leng; i++) {
    	if (document.getElementsByClassName("con-chkbox")[i].checked == true) {
    		chkbox_count++;
    	}
    }
    
    if (leng == chkbox_count) {
    	for (i=0; i<leng; i++) {
    		document.getElementsByClassName("con-chkbox")[i].checked = false;
    	}
    	
    	chkbox_count = 0;
    } else {
    	for (i=0; i<leng; i++) {
    		document.getElementsByClassName("con-chkbox")[i].checked = true;
    	}
    	chkbox_count = leng;
    }
    
    var display_cnt = document.getElementsByClassName('con-chkbox-cnt')[0];
    display_cnt.innerText = chkbox_count;
}

function chk_enable(element) {
	if (modify_mode == 0) {
		return;
	}
	var m = element;
	var assess = m.children[0];
	if (assess.checked == true) {
		chkbox_count--;
		assess.checked = false;
	} else {
    	chkbox_count++;
		assess.checked = true;
	}
	
    var display_cnt = document.getElementsByClassName('con-chkbox-cnt')[0];
    display_cnt.innerText = chkbox_count;
}

function chk_cnt() {
    var chkbox = document.getElementsByClassName("con-chkbox");
    var leng = chkbox.length;
    var checked = 0;
    for (i=0; i<leng; i++) {
        if (chkbox[i].checked == true) {
            checked += 1;
        }
    }
    
    if (checked<1) {
        alert("항목을 1개 이상 선택해주세요.");
        return false;
    }
    
    if (confirm(checked+"건의 항목을 삭제하시겠습니까?")) {
		return true;
	} else {
		return false;
	}
}

function modal(element){
	var m = element;
	var inf = document.getElementsByClassName('con-conf-modifyINF-wrap')[0];
	var pwd = document.getElementsByClassName('con-conf-modifyPW-wrap')[0];
	var ope = document.getElementsByClassName('con-conf-modifyOPE-wrap')[0];
	var del = document.getElementsByClassName('con-conf-modifyDEL-wrap')[0];
	var opeyn = document.getElementsByClassName('con-conf-OPEYN')[0];
	var bg = document.getElementsByClassName('con-modal-bg')[0];
	var inputText = document.getElementsByClassName('text-200');

	
	if (m == 'INF') {
		inf.style.display = 'block';
		pwd.style.display = 'none';
		ope.style.display = 'none';
		del.style.display = 'none';
		bg.style.display = 'block';
		window.scroll(0, 0);
	} else if(m == 'PWD') {
		inf.style.display = 'none';
		pwd.style.display = 'block';
		ope.style.display = 'none';
		del.style.display = 'none';
		bg.style.display = 'block';
		window.scroll(0, 0);
	} else if(m == 'OPE') {
		inf.style.display = 'none';
		pwd.style.display = 'none';
		ope.style.display = 'block';
		del.style.display = 'none';
		bg.style.display = 'block';
		opeyn.value = "opeyn-y";
		window.scroll(0, 0);
	} else if(m == 'DEL') {
		inf.style.display = 'none';
		pwd.style.display = 'none';
		ope.style.display = 'none';
		del.style.display = 'block';
		bg.style.display = 'block';
		opeyn.value = "opeyn-y";
		window.scroll(0, 0);
	} else if(m == 'X') {
		inf.style.display = 'none';
		pwd.style.display = 'none';	
		ope.style.display = 'none';
		del.style.display = 'none';
		bg.style.display = 'none';
		opeyn.value = null;
	}
}


function checkModify(){
	var btn = document.getElementsByClassName('st1-80-50')[0];
	var modifyINF = document.getElementsByClassName('con-conf-modifyINF-wrap')[0];
	var sfmodal = document.getElementsByClassName('con-modal-bg-check')[0];
	var successWindow = document.getElementsByClassName('con-conf-confirmed-ok')[0];
	var failWindow = document.getElementsByClassName('con-conf-confirmed-failed')[0];
	var nickname = document.getElementsByClassName('conf-modify-nickname')[0];
	var male = document.getElementsByClassName('conf-modify-male')[0];
	var female = document.getElementsByClassName('conf-modify-female')[0];
	var age = document.getElementsByClassName('conf-modify-age')[0];
	var text = document.getElementsByClassName('con-conf-status')[0];
	var checkedNN = "";
	
	$.ajax({
        url:"usingNN.do",
        type:'post',
        data: "nickname="+nickname.value,
        async:false,
        success:function(data){
        	if(data == 'usingNN'){
        		checkedNN = "n";
        	} else {
        		checkedNN = "y";
        	}
        },
        error:function(){
        	btn.disabled = true;
    		sfmodal.style.display = 'block';
    		failWindow.style.display = 'block';
    		nickname.focus();
    		text.innerText = "오류가 발생했습니다."; 
    		hide('fail');
    		return false;
        }
    });
	
	if(nickname.value==""){
 		btn.disabled = true;
 		sfmodal.style.display = 'block';
 		failWindow.style.display = 'block';
 		nickname.focus();
 		text.innerText = "별명이 입력되지 않았습니다."; 
 		hide('fail');
 		return false;
 	} else if(!male.checked && !female.checked){
 		sfmodal.style.display = 'block';
 		failWindow.style.display = 'block';
 		text.innerText = "성별이 선택되지 않았습니다.";
 		hide('fail');
 		return false;
 	} else if(age.value==""){
 		sfmodal.style.display = 'block';
 		failWindow.style.display = 'block';
 		age.focus();
 		text.innerText = "나이가 선택되지 않았습니다."; 
 		hide('fail');
 		return false;
 	} else if(checkedNN == "n") {
 		btn.disabled = true;
		sfmodal.style.display = 'block';
		failWindow.style.display = 'block';
		nickname.focus();
		text.innerText = "이미 사용중인 별명입니다."; 
		hide('fail');
		return false;
 	} else {
 		successWindow.style.display = 'block'; 
 		hide('success');
 		return true;
 	}
}

function checkModifyPW(){
	var btn = document.getElementsByClassName('st1-80-50')[1];
	var modifyPW = document.getElementsByClassName('con-conf-modifyPW-wrap')[0];
	var sfmodal = document.getElementsByClassName('con-modal-bg-check')[1];
	var successWindow = document.getElementsByClassName('con-conf-confirmed-ok')[0];
	var failWindow = document.getElementsByClassName('con-conf-confirmed-failed')[0];
	var newPW = document.getElementsByClassName('conf-modify-newPW')[0];
	var newPWChk = document.getElementsByClassName('conf-modify-newPWChk')[0];
	var text = document.getElementsByClassName('con-conf-status')[0];
	if(newPW.value==""){
		btn.disabled = true;
		sfmodal.style.display = 'block';
		failWindow.style.display = 'block';
		newPW.focus();
		text.innerText = "새 비밀번호가 입력되지 않았습니다."; 
		hide('fail');
		return false;
	} else if(newPWChk.value==""){
		sfmodal.style.display = 'block';
		failWindow.style.display = 'block';
		newPWChk.focus();
		text.innerText = "확인용 비밀번호가 입력되지 않았습니다.";
		hide('fail');
		return false;
	} else if(newPW.value!=newPWChk.value){
		sfmodal.style.display = 'block';
		failWindow.style.display = 'block';
		newPWChk.focus();
		text.innerText = "비밀번호가 일치하지 않습니다."; 
		hide('fail');
		return false;
	} else {
		successWindow.style.display = 'block'; 
		hide('success');
		return true;
	} 
}

function checkModifyOPE(){
	var btn = document.getElementsByClassName('st1-80-50')[2];
	var modifyOPE = document.getElementsByClassName('con-conf-modifyOPE-wrap')[0];
	var sfmodal = document.getElementsByClassName('con-modal-bg-check')[2];
	var successWindow = document.getElementsByClassName('con-conf-confirmed-ok')[0];
	var failWindow = document.getElementsByClassName('con-conf-confirmed-failed')[0];
	var text = document.getElementsByClassName('con-conf-status')[0];
	
	btn.disabled = true;
	successWindow.style.display = 'block'; 
	hide('success');
	return true;
}

function checkModifyDEL(){
	var btn = document.getElementsByClassName('st1-80-50')[3];
	var modifyINF = document.getElementsByClassName('con-conf-modifyDEL-wrap')[0];
	var sfmodal = document.getElementsByClassName('con-modal-bg-check')[3];
	var successWindow = document.getElementsByClassName('con-conf-confirmed-bye')[0];
	var failWindow = document.getElementsByClassName('con-conf-confirmed-failed')[0];
	var text = document.getElementsByClassName('con-conf-status')[0];
	var bye = document.getElementsByClassName('con-conf-confirmed-ok')[0]
	var check = document.getElementsByClassName('con-conf-del-check')[0]
	var delForm = document.getElementsByClassName("con-conf-modifyDEL-content")[0];
 	var error = "n";

	$.ajax({
        url:"deleteMember.do",
        type:'post',
        data: "check="+"del",
        async:false,
        success:function(data){
        	if(data == 'DELerror'){
        		error = "y";
        	}
        },
        error:function(){
        	btn.disabled = true;
    		sfmodal.style.display = 'block';
    		failWindow.style.display = 'block';
    		nickname.focus();
    		text.innerText = "오류가 발생했습니다."; 
    		hide('fail');
    		error = "y";
    		return false;
        }
    });
	
	sfmodal.style.display = 'block';
	successWindow.style.display = 'block';
	
	setTimeout(function() {
		delForm.submit();
	},3000);
}

function hide(element){
	var btn = document.getElementsByClassName('st1-80-50')[0];
	var btn1 = document.getElementsByClassName('st1-80-50')[1];
	var btn2 = document.getElementsByClassName('st1-80-50')[2];
	var btn3 = document.getElementsByClassName('st1-80-50')[3];
	var sfmodal = document.getElementsByClassName('con-modal-bg-check')[0];
	var sfmodal1 = document.getElementsByClassName('con-modal-bg-check')[1];
	var sfmodal2 = document.getElementsByClassName('con-modal-bg-check')[2];
	var sfmodal3 = document.getElementsByClassName('con-modal-bg-check')[3];
	var e = element;
	var result;
	if (e == 'fail') {
		result = document.getElementsByClassName('con-conf-confirmed-failed')[0]
	} else if (e == 'success') {
		result = document.getElementsByClassName('con-conf-confirmed-ok')[0]
	} 
	
	setTimeout(function() {
		result.style.display = 'none';
		sfmodal.style.display = 'none';
		sfmodal1.style.display = 'none';
		sfmodal2.style.display = 'none';
		sfmodal3.style.display = 'none';
		btn.disabled = false;
		btn1.disabled = false;
		btn2.disabled = false;
		btn3.disabled = false;
	},2000);
}

function checkPW(){
	var btn = document.getElementsByClassName('con-conf-checkPW')[0];
	var bg = document.getElementsByClassName('con-modal-bg')[0];
	var successWindow = document.getElementsByClassName('con-conf-confirmed-ok')[0];
	var failWindow = document.getElementsByClassName('con-conf-confirmed-failed')[0];
	var text = document.getElementsByClassName('con-conf-status')[0];
	var password = document.getElementsByClassName("con-conf-text")[0];
	var confirm = "no";
	
	$.ajax({
        url:"checkPassword.do",
        type:'post',
        data: "password="+password.value,
        async:false,
        success:function(data){
        	if(data == 'success'){
        		confirm = "ok";
        	} else {
        		confirm = "no";
        	}
        },
        error:function(){
        	btn.disabled = true;
        	bg.style.display = 'block';
    		failWindow.style.display = 'block';
    		password.focus();
    		text.innerText = "오류가 발생했습니다."; 
    		hideCHK('fail');
    		confirm = "n";
    		return false;
        }
    });

	if(password.value==""){
 		btn.disabled = true;
 		bg.style.display = 'block';
 		failWindow.style.display = 'block';
 		password.focus();
 		text.innerText = "비밀번호가 입력되지 않았습니다."; 
 		hideCHK('fail');
 		return false;
 	} else if(confirm=="no"){
 		btn.disabled = true;
 		bg.style.display = 'block';
 		failWindow.style.display = 'block';
 		password.focus();
 		text.innerText = "비밀번호가 일치하지 않습니다."; 
 		hideCHK('fail');
 		return false;
 	} else {
		successWindow.style.display = 'block'; 
		hideCHK('success');
		return true;
	} 
}

function hideCHK(element){
	var btn = document.getElementsByClassName('con-conf-checkPW')[0];
	var bg = document.getElementsByClassName('con-modal-bg')[0];
	var e = element;
	var result;
	if (e == 'fail') {
		result = document.getElementsByClassName('con-conf-confirmed-failed')[0]
	} else if (e == 'success') {
		result = document.getElementsByClassName('con-conf-confirmed-ok')[0]
	} 
	
	setTimeout(function() {
		result.style.display = 'none';
		bg.style.display = 'none';
		btn.disabled = false;
	},2000);
}



function ageCount() {
	for(var i=1; i<151; i++) {
		var age = document.createElement("option");
		var ageText = document.createTextNode(i);
		age.setAttribute('value',i);
		age.appendChild( ageText );
		document.getElementsByClassName("conf-modify-age")[0].appendChild(age);
	}
}

function sorting(element) {	
	var e = element;
	var sort = document.getElementsByClassName("con-alignInput")[0];
	var sortForm = document.getElementsByClassName("con-sortList")[0];
	if (e=="rel") {
		sort.setAttribute('value','rel');
		sortForm.submit();
	} else if (e=="sub") {
		sort.setAttribute('value','sub');
		sortForm.submit();
	} else if (e=="reg") {
		sort.setAttribute('value','reg');
		sortForm.submit();
	} else if (e=="star") {
		sort.setAttribute('value','star');
		sortForm.submit();
	}
}

function selChkbox() {
	var submitbtn = document.getElementsByClassName("con-list-delete");
	var chkbox = document.getElementsByClassName("con-chkbox");
	var leng = chkbox.length;
	var chkRow = '';
	var chkCnt = 0;
	var chkLast = '';
	var idVal = '';
	var cnt = 0;   
	
	for(var i=0; i<leng; i++) {
		if(chkbox[i].checked == true) {
			chkCnt++;
			chkLast = i;
		}
	} 
	
	for(var i=0; i<leng; i++){
		if(chkbox[i].checked == true){
			chkRow = chkbox[i].value;
			if(chkCnt == 1){
				idVal += ""+chkRow+"";
			} else {
				if(i == chkLast) {
					idVal += ""+chkRow+"";
				} else {	
					idVal += ""+chkRow+","; 			
				}
			}
			cnt++;
			chkRow = '';
		}
	submitbtn.setAttribute('value',idVal);
	}
}

var requestParam ="";
function Request(){		
	this.getParameter = function(param){
		 var url = decodeURI(location.href);
		 var paramArr = (url.substring(url.indexOf("?")+1,url.length)).split("&");

		 for(var i = 0 ; i < paramArr.length ; i++){
		     var temp = paramArr[i].split("=");		 
		     if(temp[0].toUpperCase() == param.toUpperCase()){
			     requestParam = paramArr[i].split("=")[1];
			     break;
		     }
		 }
		 return requestParam;
	 }
}


document.addEventListener("DOMContentLoaded", function(){
	var request = new Request();
	request.getParameter("nickName");
	if(requestParam.length==0) {
		requestParam= null;
	} else {
		requestParam = encodeURI(requestParam);
		document.getElementById('myp-inform-do').setAttribute('href','my_page.do?nickName='+requestParam);
		document.getElementById('myp-assessment-do').setAttribute('href','assessment.do?nickName='+requestParam);
		document.getElementById('myp-favorite-do').setAttribute('href','favorite.do?nickName='+requestParam);
	}	
});

