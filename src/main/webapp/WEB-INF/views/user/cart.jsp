<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/menu" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<style>
	.img img {
	    max-width: 100%; /* 부모 요소의 너비에 맞추어 축소 */
	    max-height: 90px; /* 원하는 최대 높이로 설정 */
	    object-fit: contain; /* 비율을 유지하며 이미지 크기 조정 */
	    width: auto; /* 자동으로 너비를 조정 */
	    height: auto; /* 자동으로 높이를 조정 */
	}	
	.quantity-button{
		border: none;
		outline: none;
		background-color: inherit ;
		cursor: pointer;
	}
</style>

</head>
<body>
	<div class="container">
		<div class="row justify-content-start mt-3">
			<div class="col-2">
				<h2>My Cart</h2>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col">
				<table class="table">
					<thead>
						<tr>
							<th></th>
							<th></th>
							<th>Option</th>
							<th>Quantity</th>
							<th>Price</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="cart" items="${cartList }">
							<tr id="cart-${cart.productOption.id}" data-unit-price="${cart.product.price}">
								<td><div class="img"><img src="/images/${cart.product.img_s }"></div></td>
								<td>${cart.product.name }</td>
								<td>${cart.productOption.color }/${cart.productOption.size }</td>
								<td>
									<button class="quantity-button" type="button" onclick="decreaseQuantity(${cart.productOption.id})">-</button> 
									<span id="cart-${cart.productOption.id}" class="quantity">${cart.quantity}</span>
									<button class="quantity-button" type="button" onclick="increaseQuantity(${cart.productOption.id})">+</button>
								</td>
								<td><span id="cart-${cart.productOption.id }" class="price">${cart.product.price }</span></td>
								<td>
									<button class="btn-close" type="button" onclick="deleteItem(${cart.productOption.id})"></button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row justify-content-end">
			<div class="col-2">
				<h4>Total Price:</h4>
				<h1><span id="total-price">0</span></h1>	
			</div>
		</div>
		<div class="row justify-content-center m-4">
			<div class="d-grid gap-2 col-6 mx-auto">
				<div class="btn btn-outline-dark" onclick="location.href='/user/order_sheet/cart'">BUY IT NOW</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<script type="text/javascript" src="/script/cartQuantity.js"></script>
	<script>
		$(document).ready(function() {
		    updateTotalPrice();
		});
</script>
</body>
</html>
<jsp:include page="/footer" />
