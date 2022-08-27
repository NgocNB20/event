<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="resources/css/loginform.css">
<%
	if (session.getAttribute("username") != null) {
%>
<c:redirect url="event-list?page"></c:redirect>
<jsp:forward page="event-list?page"></jsp:forward>

<%
	}
%>
</head>
<body>

	<div class="container-login100">
		<div class="wrap-login100 p-t-50 p-b-90 p-l-50 p-r-50">
			<s:form class="login100-form flex-sb flex-w" action="./login"
				method="POST" modelAttribute="user">
				<span class="login100-form-title">

					<h2 class="title-wellcome">WELCOME TO THE EVENT REGISTER!!!</h2>

				</span>
				<div class="wrap-input100 m-b-16">
					<input class="input100" type="text" placeholder="Username"
						name="username" id="username" onfocus="deleteMessage()"> <span
						class="focus-input100"></span>
				</div>
				<div class="wrap-input100 m-b-16">
					<input class="input100" type="password" placeholder="Password"
						name="password" id="password" onfocus="deleteMessage()"> <span
						class="focus-input100"></span>
				</div>
				<c:if test="${not empty message}">
					<p id="message" class='text-danger text-center'>${message}</p>
				</c:if>
				<div class="container-login100-form-btn m-t-17">
					<div class="w-full beforeNone text-center">
						<input type="submit" class="nv-login-submit login100-form-btn"
							name="submit" value="Login">
					</div>
				</div>
				<!-- <div class="container-login100-form-btn m-t-17">
					<a href="#">Forget Password?</a>
				</div> -->

			</s:form>
		</div>
	</div>
</body>
<script type="text/javascript" src="resources/js/login.js"></script>



</body>
</html>
