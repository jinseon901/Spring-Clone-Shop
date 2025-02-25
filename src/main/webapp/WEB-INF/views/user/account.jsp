<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/menu" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/fletta.css">
<link rel="stylesheet" href="/css/style.css">
<script type="text/javascript" src="/script/user.js"></script>
<style>
#sign-in{
	display: grid;
	grid-template-columns: repeat(2, 1fr);
	gap: 20px;
	width: 500px;
	margin: 0 auto;
}
#sign-in form{
	width: 100%;
	grid-column: 1 / span 2
}
#sign-in .id, #sign-in .password{
	margin-top: 5px;
	width: 100%;
	justify-content: start;
	border-bottom: 2px solid black; /* 하단에만 보더 추가 */
	border-width: 1.3px;
}

#sign-in .sbm input{
	margin-top: 5px;
	width: 100%;
	justify-content: center;
	border-style: solid;
	border-width: 1.3px;
	background-color: white;
}
</style>
</head>
<body>
<div id="sign-in">
	<a href="/user/account">SIGN IN</a>
	<a href="/user/join_form">CREATE ACCOUNT</a>
	<form action="/doLogin" method="post" name="frm">
		<div class="id">
			Account ID<br>
			<a href="/user/find_id?sort=id">Forgot id?</a><br>
			<input type="text" name="userId">
		</div>
		<div class="password">
			Account Password<br>
			<a href="/user/find_pwd?sort=pass">Forgot password?</a><br>
			<input type="password" name="password">
		</div>
		<div class="sbm">
			<input type="submit" value="SIGN IN" onclick="return loginCheck()"><br>
			<input type="button" value="JOIN US" onclick="location.href='/user/join'">
		</div>
	</form>
</div>

</body>
</html>
<jsp:include page="/footer" />
