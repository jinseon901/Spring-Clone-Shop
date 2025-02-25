function loginCheck(){
	if(document.frm.userId.value.length == 0){
		frm.id.focus();
		alert("아이디를 입력하세요");
		return false;
	}
	if(document.frm.pass.value.length == 0){
		frm.pass.focus();
		alert("비밀번호를 입력하세요");
		return false;
	}
	return true;
}

function joinCheck(){
	if(document.frm.userId.value.length == 0){
		frm.id.focus();
		alert("아이디를 입력하세요");
		return false;
	}
	if(document.frm.pass.value.length == 0){
		frm.pass.focus();
		alert("비밀번호를 입력하세요");
		return false;
	}
	if(document.frm.name.value.length == 0){
		frm.name.focus();
		alert("이름을 입력하세요");
		return false;
	}
	if(document.frm.pass.value != document.frm.passconfirm.value){
		frm.pass.focus();
		alert("비밀번호가 맞지 않습니다");
		return false;
	}
	//개귀찮으니까 여기까지만 하겟솨
	return true;
}