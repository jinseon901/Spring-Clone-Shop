<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/menu" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
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
	.status-list .col span{
		color: gray;
	}
	.status-list .col{
		padding: 0 20px 0 20px;
	}
	.bi-arrow-right-circle{
	    font-size: 30px;
	    line-height: 30px;
	    color:gray;
	}
	.order-table{
		margin-top: 50px;
	}
</style>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container text-center">
	<div class="row justify-content-start mt-3">
		<div class="col-2">
			<h2>My Order List</h2>
		</div>
	</div>
	
	<div class="row justify-content-center">
		<div class="col-5"><hr></div>
	</div>
	<div class="status-list row justify-content-center row-cols-auto align-items-center"> <!-- row-cols-auto이거 추가하니까 col 길이 줄여짐.. 뭐임 -->
		<div class="col">
			<h1 class="payment-completed">0</h1>
			<span>결제 완료</span>
		</div>
		<div class="col">
			<i class="bi bi-arrow-right-circle"></i>
		</div>
		<div class="col">
			<h1 class="in-delivery">0</h1>
			<span>배송 중</span>
		</div>
		<div class="col">
			<i class="bi bi-arrow-right-circle"></i>
		</div>
		<div class="col">
			<h1 class="delivery-completed">0</h1>
			<span>배송 완료</span>
		</div>
	</div>
	<div class="row justify-content-center">
		<div class="col-5"><hr></div>
	</div>
	
	<div class="row justify-content-center">
		<div class="col">
			<table class="table align-middle order-table">
				<thead>
					<tr>
						<th>Order List</th>
						<th>Total Price</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody class="accordion accordion-flush" id="accordionFlushExample">
					<c:forEach var="order" items="${orderList }">
						<tr data-option-id="${order.id}">
							<td>
								<div class="accordion-item">
									<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#order-id-${order.id }" aria-expanded="false" aria-controls="flush-collapseOne">
										${order.orderName }
									</button>
									<div id="order-id-${order.id }" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
										<div class="accordion-body">
								    	<table class="table detail-table">
								    		<thead>
								    			<tr>
								    				<th></th>
								    				<th></th>
								    				<th>quantity</th>
								    				<th>price</th>
								    			</tr>
								    		</thead>
								    		<tbody>
									    		<c:forEach var="detail" items="${order.orderDetails }">
										    		<tr data-unit-price="${detail.product.price}">
										    			<td><div class="img"><img src="/images/${detail.product.img_s }"></div></td>
										    			<td>${detail.product.name }</td>
										    			<td class="quantity">${detail.quantity }</td>
										    			<td class="price">0</td>
										    		</tr>
										    	</c:forEach>
									    	</tbody>
								    	</table>
								    	<div class="address text-start">
								    		<h4>Address:</h4>
									    	우편번호: ${address.postCode }<br>
									    	${address.address }
									    	${address.detailAddress }
									    	${address.extraAddress }
								    	</div>
								    	</div>
								    </div>
								</div>
							</td>
							<td class="totalAmount">${order.totalAmount }</td>
							<td class="status">${order.deliveryStatus.status }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript" src="/script/orderList.js"></script>
</body>
</html>
<jsp:include page="/footer" />