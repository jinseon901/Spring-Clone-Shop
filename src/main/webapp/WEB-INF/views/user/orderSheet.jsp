<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/menu" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<style>
	.orderSheet{
		max-width: 50%;
	}

	.img img {
	    max-width: 100%; /* 부모 요소의 너비에 맞추어 축소 */
	    max-height: 90px; /* 원하는 최대 높이로 설정 */
	    object-fit: contain; /* 비율을 유지하며 이미지 크기 조정 */
	    width: auto; /* 자동으로 너비를 조정 */
	    height: auto; /* 자동으로 높이를 조정 */
	}	
	
	br{
		mso-data-placement:same-cell;
	}
	
	#frm-shipping{
		width: 400px;
	}
	
	hr{
		color: gray;
	}
	
	h2{
		padding-bottom: 10px;
	}
	
	.row{
		margin: 2px;
	}
	
	#shipping-fee{
		color: gray;
	}
	
</style>
<script type="text/javascript" src="/script/findPostCodeApi.js"></script>
</head>
<body>
<div class="container orderSheet">
	<h2>Order Sheet</h2><hr>
	<h3>주문 내역:</h3><hr>
	<table class="table">
		<thead>
			<tr>
				<th></th>
				<th></th>
				<th>Option</th>
				<th>Quantity</th>
				<th>Price</th>
			</tr>
		</thead>
		<c:if test="${command.equals('cart') }">
			<tbody>
				<c:forEach var="cart" items="${cartList }">
					<tr id="cart-${cart.productOption.id}" data-unit-price="${cart.product.price}">
						<td><div class="img"><img src="/images/${cart.product.img_s }"></div></td>
						<td>${cart.product.name }</td>
						<td>${cart.productOption.color }/${cart.productOption.size }</td>
						<td><span id="cart-${cart.productOption.id}" class="quantity">${cart.quantity}</span></td>
						<td><span id="cart-${cart.productOption.id }" class="price">${cart.product.price }</span></td>
					</tr>
				</c:forEach>
			</tbody>
		</c:if>
	</table>
	<hr>
	<h3>배달:</h3><hr>
		<form name="frm-shipping" action="/order_sheet">
			<div class="row justify-content-start">
				<div class="col-5">
					<h5>배송지 정보</h5>
				</div>
			</div>
			<div class="row justify-content-start">
				<div class="col-2">
					<input id="sample6_postcode" class="form-control" type="text" placeholder="우편번호">
				</div>
				<div class="col-3 align-self-center">
					<input type="button" class="btn btn-outline-secondary btn-sm"
							onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
				</div>
			</div>
			<div class="row justify-content-start">
				<div class="col-5">
					<input id="sample6_address" class="form-control" type="text" placeholder="주소">
				</div>
			</div>
			<div class="row justify-content-start">
				<div class="col-5">
					<input id="sample6_detailAddress" class="form-control" type="text" placeholder="상세주소">
				</div>
			</div>
			<div class="row justify-content-start">
				<div class="col-2">
					<input type="text" class="form-control" id="sample6_extraAddress" placeholder="참고항목">
				</div>
				<div class="col-3"></div>
			</div>

			
			<div class="mt-4">
			<div class="row justify-content-start">
				<div class="col-5">
					<h5>주문자 정보</h5>
				</div>
			</div>
			<div class="row justify-content-start">
				<div class="col-2">
					<input id="oderer" class="form-control" type="text" placeholder="이름">
				</div>
				<div class="col-3"></div> <!-- 여백 맞추기용 -->
			</div>
			<div class="row justify-content-start">
				<div class="col-3">
					<input id="phone" class="form-control" type="text" placeholder="전화번호">
				</div>
				<div class="col-2"></div>
			</div>
			<div class="row justify-content-start">
				<div class="col-3">
					<input id="email" class="form-control" type="text" placeholder="이메일 주소">
				</div>
				<div class="col-2"></div>
			</div>
			</div>
		</form><hr>
		
		<span id="shipping-fee">배송비: 무료</span>
		<h3>총 결제 금액: <span id="total-price">0</span>원</h3><hr>
		<div class="d-grid gap-2 col-6 mx-auto">
			<button class="btn btn-dark btn-lg" type="submit" onclick="requestPayment()">결제하기</button>
		</div>
		
</div>
	<jsp:include page="/footer" />
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
		<script type="text/javascript" src="/script/cartQuantity.js"></script>
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script>
			$(document).ready(function() {
			    updateTotalPrice();
			});
	</script>
	<!-- 포트원 추가 -->
	<script src="https://cdn.portone.io/v2/browser-sdk.js"></script>
	<script type="text/javascript" src="/script/mainPay.js"></script>
</body>
</html>