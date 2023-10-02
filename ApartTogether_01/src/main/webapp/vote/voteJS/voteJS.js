//투표 이미지 보여주기
function show_image() {
  var radioButtons = $('input[name=choice]');

   $.each(radioButtons, function(index, item){

	  if($(item).is(':checked')){
		  $('.choice-img').eq(index).css('visibility','visible');
	  }
	  else{
		  $('.choice-img').eq(index).css('visibility','hidden');
	  }
	  
   });
}

/*투표 알럿창*/
function alertInfo(){
	alert("투표 되었습니다!");
}
/*마감 컨펌창*/
function endVote(){
	alert("투표 되었습니다!");
}

$(document).ready(function() {
	//투표 항목 체크
	
	//radio 버튼
	var select_target = $('input[id=after-vote]');
	//값
	var select_value = $('input[id=selectVote]').val();
	
	$.each(select_target, function(index, item){
		if($(item).val() == select_value){
			select_target.eq(index).prop('checked', true);
			show_image();
		}
	});
	
});