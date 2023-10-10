function show_menu(){
	document.getElementById('month-target').style.visibility="hidden";
	document.getElementById('menu-target').style.visibility="visible";
	document.getElementById('month-target').style.position="absolute";
	document.getElementById('menu-target').style.position="relative";
}
function show_month(){
	document.getElementById('month-target').style.visibility="visible";
	document.getElementById('menu-target').style.visibility="hidden";
	document.getElementById('menu-target').style.position="absolute";
	document.getElementById('month-target').style.position="relative";
}