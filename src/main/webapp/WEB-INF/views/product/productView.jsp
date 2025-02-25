<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/menu"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/script/buy.js"></script>

<style>
#wrap{
	display: grid;
	grid-template-columns: repeat(2, 1fr);
	gap: 20px;
	width: 100%;
	margin: 0 auto;
	padding: 30px;
	height: 100vh;
	overflow: hidden;
}

#product-img, 
#product-info {
    height: 100vh; /* 부모 높이에 맞춤 */
    overflow-y: auto; /* 세로 스크롤 활성화 */
}

#product-img img {
    width: 100%;
    height: auto; /* 이미지의 원래 비율 유지 */
    object-fit: contain; /* 이미지가 잘리지 않도록 설정 */
}

#product-info{
	padding: 5px;
	position: sticky;
	top: 50px;
}

#wrap .sbm input{
	margin-top: 5px;
	width: 500px;
	justify-content: center;
	border-style: solid;
	border-width: 1.3px;
	background-color: white;
}
#wrap .sbm submit{
	background-color: black;
}

.product-name{
	font-size: 13px;
}

#description{
	font-size: 13px;
	color: gray;
}

#description hr{
	width: 500px;
}
</style>

<script>
	// Action.java에서 받아온 optList를 JSON으로 변환하여 JavaScript로 전달
	const optList = [
	    <c:forEach var="opt" items="${optionList}" varStatus="status">
	        {
	            "optionId": "${opt.id}",
	            "color": "${opt.color}",
	            "size": "${opt.size}",
	            "quantity": "${opt.quantity}"
	        }<c:if test="${!status.last}">,</c:if>
	    </c:forEach>
	];
</script>

<script>
	window.isLoggedIn = ${not empty sessionScope.loginId ? "true" : "false"};
	
	window.addEventListener('DOMContentLoaded', function () {
		/* handleLoginStatus(); */
		
	    const productOptionSelect = document.getElementById('productOptionSelect');
	    const quantityFieldsContainer = document.getElementById('quantityFieldsContainer');
	    
	    const quantityCheck = document.getElementById("quantityCheck");
	    let quantityInput = document.querySelector('#quantityCheck input[type="number"]');
	    
    	productOptionSelect.addEventListener('change', function () {
    		
	        if (productOptionSelect.value !== "0") {
	        	const selectedOption = optList.find(opt => opt.optionId == productOptionSelect.value);
	        	console.log('optList:', optList);
	        	//console.log('productOptionSelect.optionId: ' productOptionSelect.optionId);
	        	console.log(productOptionSelect);
	        	
	        	/* 선택한 옵션 저장하는 과정 */
	        	const selectedOptionText = productOptionSelect.options[productOptionSelect.selectedIndex].text;
	            const optionLabel = document.createElement('p');
	            optionLabel.textContent = selectedOptionText;
	            
	           	/* 옵션 초기화 */
	            quantityFieldsContainer.innerHTML = '';
	           	quantityInput.value = 1;
	            
	          	/* 디스플레이 보여주기 */
	            quantityFieldsContainer.appendChild(optionLabel);
	            quantityFieldsContainer.style.display = 'block';
	            quantityCheck.style.display='block';
	            quantityInput.max = selectedOption.quantity;
	        } else {
	            quantityFieldsContainer.style.display = 'none';
	            quantityCheck.style.display='none';
	        }
	    });	
	});

</script>

</head>
<body>
<div id="wrap">
	<div id="product-img">
	    <img src="/images/${product.img_w }">
	</div>
	
	<div id="product-info">
	    <div class="title">${product.name}</div>
	    <form method="post" name="frm">
	        <input type="hidden" name="productId" value="${product.id }">
	        <select name="optionId" id="productOptionSelect">
	        <option value="0">선택하세요</option>
	            <c:forEach var="opt" items="${optionList }">
	                <option value="${opt.id }">${opt.color }/${opt.size }</option>
	            </c:forEach>
	        </select>
	        
	    <div id="quantityFieldsContainer" style="display: none;"></div>
	    <div id="quantityCheck" style="display: none;">
	    	<input type="number" min="1" name="quantity" value="1">
	    </div>

	    
	    <div class="sbm">
	        <input type="submit" formaction="/user/order_sheet/one" value="BUY IT NOW">
	        <input type="submit" formaction="/user/cart/add" value="ADD TO CART">
	    </div>
	    </form>
	    <div id="description">
	    	<hr>
	    	${product.description }
	    </div>
	</div>
</div>

</body>
</html>
<jsp:include page="/footer"/>