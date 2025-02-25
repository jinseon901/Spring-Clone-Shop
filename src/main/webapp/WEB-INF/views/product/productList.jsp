<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<jsp:include page="/menu" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product List</title>
<link rel="stylesheet" type="text/css" href="/css/fletta.css">
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div id="product-top">
	<div class="category">${sort }</div>
	<div class="sort">sort</div>
</div>
<div id="product-middle">
    <c:forEach var="pVo" items="${productList }">
        <div class="product-item">
        	<a href="/product/product_view?productId=${pVo.id }">
            	<div class="img"><img src="/images/${pVo.img_s }" alt="${pVo.name}"></div>
         	</a>
            <a href="/product_view?productId=${pVo.id }">
            	<div class="name">${pVo.name }</div>
            </a>
            <div class="price">${pVo.price }원</div>
        </div>
    </c:forEach>
</div>
<div id="product-bottom">
	<div></div>
</div>

</body>
</html>
<jsp:include page="/footer" />
