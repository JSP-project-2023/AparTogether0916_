//유효성 검사
function validation(){
		if ($("input[name=stname]").val() == "") {
			alert("가게 이름은 필수 입니다.");
			$("input[name=stname]").focus();
			return false;
		}
		if ($("select[name=category]").val() == "-") {
			alert("카테고리 선택은 필수 입니다.");
			$("input[name=category]").focus();
			return false;
		}
		if ($("input[name=stplace]").val() == "") {
			alert("가게 위치는 필수 입니다.");
			$("input[name=stplace]").focus();
			return false;
		}
		if ($("select[name=areacode1]").val() == "-") {
			alert("전화번호는 필수 입니다.");
			$("select[name=areacode1]").focus();
			return false;
		}
		if ($("input[name=areacode2]").val() == "" || $("input[name=areacode3]").val() == "") {
			alert("전화번호는 필수 입니다.");
			$("input[name=areacode1]").focus();
			return false;
		}
		/*if ($("input[name=ceofile]").val() == "" || $("input[name=ceofile]").val() == null) {
			alert("사업자 등록증은 필수 입니다.");
			$("input[name=ceofile]").focus();
			return false;
		}*/
		/*if ($("input[name=stlogo]").val() == "" || $("input[name=stlogo]").val() == null) {
			alert("가게 로고는 필수 입니다.");
			$("input[name=stlogo]").focus();
			return false;
		}*/
		if ($("input[name=fee]").val() == "") {
			alert("배달비 필수 입니다.");
			$("input[name=fee]").focus();
			return false;
		}
		if($("input[name=fee]").val() <= '0'){
			$("input[name=fee]").val("0");
		}
		if($("input[name=redday]").val() == ""){
			$("input[name=redday]").val("휴무일 없음");
		}
		if($("input[name=ceono]").val() == ""){
			alert("사업자 등록번호는 필수 입니다.");
			$("input[name=ceono]").focus();
			return false;
		}
		//사업자 등록증 유효성 검사
		var ceofile = $('input[name=ceofile]').val();
		var isCheck = false;
			const imgCheck = ['.png', '.jpg'];
			for(var i=0 ; i < imgCheck.length ; i++){
				if(ceofile.endsWith(imgCheck[i]) || ceofile == ""){
					isCheck = true;
					break;	
				}
			}
			
			if(isCheck == false){
				alert('이미지의 확장자는 png 또는 jpg 형식이어야 합니다.1');
				return false ;
			}
		//가게 로고 유효성 검사
		var stlogo = $('input[name=stlogo]').val();
		var isCheck = false;
		for(var i=0 ; i < imgCheck.length ; i++){
			if(stlogo.endsWith(imgCheck[i]) || stlogo == ""){
				isCheck = true;
				break;	
			}
		}
		if(isCheck == false){
			alert('이미지의 확장자는 png 또는 jpg 형식이어야 합니다.2');
			return false ;
		}
		//번호 유효성 검사
		var areacode1= $("input[name=areacode1]").val();
		var areacode2= $("input[name=areacode2]").val();
		
		if(areacode1 < 0){
			alert("숫자만 입력가능합니다.")
			$("input[name=areacode1]").focus();
			return false;
		}
		if(areacode2 < 0){
			alert("숫자만 입력가능합니다.");
			$("input[name=areacode2]").focus();
			return false;
		}
	
	
	if (confirm("가게 정보를 수정하시겠습니까?")) {
		alert("수정되었습니다.");
		return true;
	} else {
		return false;
	}
		
			
	}

$(document).ready(function() {
	//주소 api
	document.getElementById("stplace1").addEventListener("click", function() {
		new daum.Postcode({
			oncomplete: function(data) {
				document.getElementById("stplace1").value=data.address;
				document.getElementById("stplace2").focus();
			}
		}).open()
	});
	
	//가게 카테고리 selected
	var el = document.getElementById('categoryFood');
	var str = document.getElementById('selectedCategory').value;
	selectedBox(el, str);
	
	//가게 전화번호
	var el = document.getElementById('choiceNumber');
	var str = document.getElementById('firstNumber').value;
	selectedBox(el, str);
	
	/*가게운영시간 코드 시작*/
	
	//시작시간 Am or Pm
	var el = document.getElementById('startShopAmPm');
	var str = document.getElementById('startAmPm').value;
	selectedBox(el, str);
	
	//시작 시간
	var el = document.getElementById('startTimeB');
	var str = document.getElementById('startTime').value;
	selectedBox(el, str);
	
	
	//종료시간 Am or Pm
	var el = document.getElementById('endShopAmPm');
	var str = document.getElementById('endAmPm').value;
	selectedBox(el, str);
	
	
	//종료시간
	var el = document.getElementById('endTimeB');
	var str = document.getElementById('endTime').value;
	selectedBox(el, str);
	
	/*가게운영시간 코드 종료*/
	

	
});

// select 조건 찾는 함수
function selectedBox(el , str){
	var len = el.options.length;
	for (let i = 0; i < len; i++) {
		if (el.options[i].value == str) {
			el.options[i].selected = true;
		}
	}	
}