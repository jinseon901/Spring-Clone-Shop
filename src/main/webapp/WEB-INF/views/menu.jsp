<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<style>
	body{
		padding-top: 70px;
	}
</style>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary fixed-top">
  <div class="container-fluid">
    <a class="navbar-brand" href="/">fletta</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/product/product_list?sort=new">New</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/product/product_list?sort=dress">fletta dress</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/product/product_list?sort=outer">Outer</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/product/product_list?sort=knit">knit</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/product/product_list?sort=top">top</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/product/product_list?sort=bottom">bottom</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/product/product_list?sort=acc">Accessory</a>
         </li>
         </ul>
         </div>
         <ul class="navbar-nav">
         <li class="d-flex">
        	<a class="nav-link" href="ShoppingServlet?command=community">community</a>
        </li>
        <c:choose>
        	<c:when test="${loginId == null }">
        		<li class="d-flex">
		        	<a class="nav-link" href="/login">account</a>
		        </li>
        	</c:when>
        	<c:otherwise>
        		<li class="d-flex">
		        	<a class="nav-link" href="/logout">logout</a>
		        </li>
        	</c:otherwise>
        </c:choose>
        <li class="d-flex">
        	<a class="nav-link" href="/user/order_list">mypage</a>
        </li>
        <li class="d-flex">
        	<a class="nav-link" href="ShoppingServlet?command=search">search</a>
        </li>
         <li class="d-flex">
        	<a class="nav-link" href="/user/cart">bag</a>
        </li>
      </ul>
    </div>
</nav>


</body>
</html>