
$(document).ready(function() {
	
	//가게 전화번호
	var el = document.getElementById('choiceNumber');
	var str = document.getElementById('firstNumber').value;
	selectedBox(el, str);
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