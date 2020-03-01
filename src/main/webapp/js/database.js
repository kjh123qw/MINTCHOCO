/**
 * 
 */
function submitSetup() {
	if(confirm("데이터 베이스 설치를 시작할까요?\n기존에 직접 입력한 데이터는 날아가고 새버전이 설치됩니다.")){
		location.href="dbSetup.do";
	}
}

function submitDelete() {
	if(confirm("모든 데이터 베이스 삭제할까요?")){
		location.href="dbDelete.do";
	}
}
