//폐기 사용 안 함
function orderSheet(){
	//const optionId = $('#productOptionSelect.select');
	const optionId = document.getElementById("productOptionSelect").value;
	const optionIdText = optionId.options[selectElement.selectedIndex].text;
	window.location.href='ShoppingServlet?command=order-sheet&optionId=' + optionIdText;
}