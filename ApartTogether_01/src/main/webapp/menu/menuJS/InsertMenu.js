//유효성 검사
function validation(){
	if ($("input[name=menuname]").val() == "") {
		alert("메뉴명을 입력해주세요.");
		$("input[name=menuname]").focus();
		return false;
	}
	if ($("input[name=price]").val() == '') {
		alert("가격을 입력해주세요.");
		$("input[name=price]").focus();
		return false;
	}
	if ($("input[name=menuimage]").val() == "" || $("input[name=menuimage]").val() == null) {
		alert("메뉴 이미지를 넣어주세요.");
		$("input[name=menuimage]").focus();
		return false;
	}
	
	//메뉴 이미지 유효성 검사
	var menuimg = $('input[name=menuimage]').val();
	var isCheck = false;
	const imgCheck = ['.png', '.jpg'];
	
	for(var i=0 ; i < imgCheck.length ; i++){
		if(menuimg.endsWith(imgCheck[i])){
			isCheck = true;
			break;	
		}
	}
	
	if(isCheck == false){
		alert('이미지의 확장자는 png 또는 jpg 형식이어야 합니다.');
		return false ;
	}
}