<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page import="model.Event"%>
<%@page import="orm.paging.Page"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Page dashboard</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/button.css">
</head>
<header>
	<img class="imgheader" src="resources/image/UsolLogo.jpg">
</header>
<body>
	<!--Search Condition-->
	<%
		HttpSession sessionUser = request.getSession();
		String userLogin = sessionUser.getAttribute("username").toString();
	%>
	<div class="body_form">
		<br> &emsp;<i class="fa-solid fa-tag">&emsp;<b>Search
				Condition</b></i>
		<div id="form_wrapper">
			<div id="form_left">
				<label><b>Tên/mô tả event </b></label> <input type="text"
					placeholder="nhap event" name="" id="" required><br> <label><b>Thời
						gian</b></label>&emsp;&emsp;&emsp; <input type="date" name="" id="" required>
				-- <input type="date" name="" id="" required><br> <label><b>Địa
						điểm </b></label>&emsp;&emsp;&emsp; <input type="text"
					placeholder="nhap   event" name="" id="" required><br>
				<button class="button-search" role="button">
					<i class="fa-solid fa-magnifying-glass"> Search</i>
				</button>
			</div>
			<div id="form_right">
				<label><b>Bộ phận</b></label>&emsp; <input type="text"
					placeholder="nhap   event" name="" id="" required><br>
				<label><b>Người tạo </b></label> <input type="text"
					placeholder="nhap   event" name="" id="" required><br>
				<label><b>Trạng thái</b></label> <select name="">
					<option>option 1</option>
					<option>option 2</option>
					<option>option 3</option>
				</select>
			</div>
			<hr>
		</div>
		<br>
		<hr>
		<div id="form_wrapper_mini">
			<div id="form_left_mini">
				&emsp;<i class="fa-solid fa-list">&emsp;<b>Event List</b></i>
			</div>
			<div id="form_right_mini">
				<button class="button-add">
					<a href="./add-new"><i class="fa-solid fa-plus">Add Event</i></a>
				</button>
			</div>
		</div>
		<div class="table-responsive"></div>
		<Table class="Table">
			<thead>
				<tr>
					<th>Tên Event</th>
					<th>Mô tả</th>
					<th>Thời gian</th>
					<th>Địa điểm</th>
					<th>Bộ phận</th>
					<th>Người tạo</th>
					<th>Trạng thái</th>
					<th>Đã tham gia</th>
					<th>Không tham gia</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
			</tbody>

			<%
				Page<Event> pages = (Page<Event>) request.getAttribute("pages");
				List<Event> events = pages.getData();
				int totalsPage = pages.getSize();
				int currentPage = pages.getPage();
				String disableFirst = currentPage == 1 ? "disabled" : "";
				String disableLast = currentPage == totalsPage ? "disabled" : "";

				if (events.size() > 0) {
					for (Event e : events) {
			%>
			<tr>
				<th>event Name</th>
				<th>getDescription</th>
				<th>getEventType</th>
				<th>getCreator</th>
				<th>getCreator</th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th><i class="fa-solid fa-info"></i>&emsp;| <i
					class="fa-solid fa-pen-to-square"></i>&emsp;| <i
					class="fa-solid fa-eraser"></i>&emsp;| <i
					class="fa-solid fa-check-to-slot"></i></th>
			</tr>
			<%
				}
				} else {
			%>
			<tr class="no-data">
				<td colspan="5">User not found</td>
			</tr>
			<%
				}
			%>
		</Table>
	</div>
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item <%=disableFirst%>"><a class="page-link"
				href="./dashboard?page=<%=currentPage - 1%>">Previous</a></li>
			<%
				if (totalsPage > 0) {
					for (int i = 1; i <= totalsPage; i++) {
			%>
			<%
				if (i == currentPage) {
			%>
			<li class="page-item active"><a class="page-link"
				href="./dashboard?page=<%=i%>"><%=i%></a></li>
			<%
				} else {
			%>
			<li class="page-item"><a class="page-link"
				href="./dashboard?page=<%=i%>"><%=i%></a></li>
			<%
				}
			%>
			<%
				}
			%>
			<%
				}
			%>
			<li class="page-item <%=disableLast%>"><a class="page-link"
				href="./dashboard?page=<%=currentPage + 1%>">Next</a></li>
		</ul>
	</nav>
</body>


<%-- <body>
<h1 class="text-center">Page Dashboard</h1>
	<%
		HttpSession sessionUser = request.getSession();
		String userLogin = sessionUser.getAttribute("username").toString();
	%>
	<div class="container">
		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Username</th>
						<th scope="col">Password</th>
						<th scope="col">Update</th>
						<th scope="col">Delete</th>
					</tr>
				</thead>
				<tbody>
				</tbody>

				<%
					Page<Event> pages = (Page<Event>) request.getAttribute("pages");
					List<Event> events = pages.getData();
					int totalsPage = pages.getSize();
					int currentPage = pages.getPage();
					String disableFirst = currentPage == 1 ? "disabled" : "";
					String disableLast = currentPage == totalsPage ? "disabled" : "";

					if (events.size() > 0) {
						for (Event e : events) {
				%>
				<tr>
					<th><%=e.getId()%></th>
					<th><%=e.getEventName()%></th>
					<td><%=e.getEventType()%></td>
					<td><a href="./update?id=<%=e.getId()%>"><button
								type="button" class="btn btn-primary">Update</button></a></td>
					<td>
						<button class="btn btn-danger" id=<%=e.getId()%>
							onclick="clickDelete(<%=e.getId()%>)"
							username=<%=e.getEventName()%>>Delete</button>
					</td>

				</tr>
				<%
					}
					} else {
				%>
				<tr class="no-data">
					<td colspan="5">User not found</td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
		<nav aria-label="Page navigation example">
			<ul class="pagination">
				<li class="page-item <%=disableFirst%>"><a class="page-link"
					href="./dashboard?page=<%=currentPage - 1%>">Previous</a></li>
				<%
					if (totalsPage > 0) {
						for (int i = 1; i <= totalsPage; i++) {
				%>
				<%
					if (i == currentPage) {
				%>
				<li class="page-item active"><a class="page-link"
					href="./dashboard?page=<%=i%>"><%=i%></a></li>
				<%
					} else {
				%>
				<li class="page-item"><a class="page-link"
					href="./dashboard?page=<%=i%>"><%=i%></a></li>
				<%
					}
				%>
				<%
					}
				%>
				<%
					}
				%>
				<li class="page-item <%=disableLast%>"><a class="page-link"
					href="./dashboard?page=<%=currentPage + 1%>">Next</a></li>
			</ul>
		</nav>
		<button class="btn btn-dark">
			<a href="./">logout</a>
		</button>
		<button class="btn btn-success">
			<a href="./add-new">Add User</a>
		</button>
</body> --%>
<script type="text/javascript" src="resources/js/dashboard.js"></script>
</html>