<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<title>Insert title here</title>
</head>
<style type="text/css">
.container-custom {
	width: 600px;
}

#message {
	transition-delay: 2s;
}
</style>
<body>
	<h1 class="text-center">Page Update User</h1>
	<%
		User user = (User) request.getAttribute("user");
	%>
	<%
		String message = (String) request.getAttribute("message");
		String messagenew = " ";
		if (message == "false") {
			messagenew = "Username or Password is not null";
		}
	%>


	<div class="container container-custom">
		<form action="./updateafter.do?id=<%=user.getId()%>" method="post">
			<div class="form-outline mb-4">
				<label class="form-label">User name</label> <input id="username"
					name="username" class="form-control" onchange="deleteMessage()"
					value="<%=user.getUsername()%>" />
			</div>
			<!-- Password input -->
			<div class="form-outline mb-4">
				<label class="form-label">Password</label> <input id="password"
					name="password" type="password" class="form-control"
					onchange="deleteMessage()" value="<%=user.getPassword()%>" />
			</div>
			<p id="message" class='text-danger text-center'></p>
			<!-- Submit button -->
			<button id="update-user" type="button"
				class="btn btn-primary btn-block mb-4" onclick="dislayMessage()">Update</button>
		</form>
	</div>
</body>
<script type="text/javascript">
	
	const dislayMessage = ()=>{
		let username = document.getElementById("username").value;
		let password = document.getElementById("password").value;
		let id = <%=user.getId()%>;
		if(username.trim().length==0 || password.trim().length==0 ||username==null ||password==null){
			document.getElementById("message").innerText = "Username or Password is not null !";
		} else {
  			window.location="./update-after?id="+id+"&username="+username+"&password="+password;  	
		}	
	};
    const deleteMessage = ()=>{
    	const message = document.getElementById("message").innerText="";
    	 
    };
 
	
</script>
</html>