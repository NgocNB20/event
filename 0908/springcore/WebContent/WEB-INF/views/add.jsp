 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add User</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="resources/add.css"
	media="screen" />
</head>
<body>
	<h1 class="text-center">Page Add User</h1>
	<div class="container container-custom">
		<form action="./updateafter?id=" method="post">
			<div class="form-outline mb-4">
				<label class="form-label">User name</label> <input id="username"
					name="username" class="form-control" onchange="deleteMessage()" />
			</div>
			<div class="form-outline mb-4">
				<label class="form-label">Password</label> <input id="password"
					name="password" type="password" class="form-control"
					onchange="deleteMessage()" />
			</div>
			<p id="message" class='text-danger text-center'></p>
			<!-- Submit button -->
			<button id="update-user" type="button"
				class="btn btn-primary btn-block mb-4" onclick="dislayMessage()">Add
				User</button>
		</form>
	</div>
</body>
<script type="text/javascript" src="resources/add.js"></script>
</html>