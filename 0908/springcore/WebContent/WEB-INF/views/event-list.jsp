
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page import="model.EventList"%>
<%@page import="orm.paging.Page"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Event List</title>
<meta charset="UTF-8">
<meta name="viewport" con t="width=device-width, initial-scale=1.0">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700;800;900&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.3.1/css/all.min.css">
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/button.css">
<link rel="stylesheet" href="resources/css/inputstyle.css">
<link rel="stylesheet" href="resources/css/pagelist.css">


</head>
<header>
	<div id="form_wrapper_header">
		<div id="form_left_header">
			<img class="imgheader" src="resources/image/UsolLogo.jpg">
		</div>
		<div id="form_right_header">
			<div class="right_form_header">
				<h2>Hello <%=session.getAttribute("username") %></h2>
				<a id="logout" href="logout">Logout</a>
			</div>
		</div>
	</div>
</header>



<body>
	<!--Search Condition-->
	<div class="body_form">
		<br> &emsp;<i onclick="displayProperty()" style="cursor: pointer"
			class="fa-solid fa-tag">&emsp;<b>Search Condition</b></i>
		<div id="displayP" class="content">




			<form id="form_wrapper" >
			
			<div id="form_left">
					<label><b>Event name</b></label> <input type="text"
						placeholder="nhap event" id="EventName" name="eventName"><br>

					<label><b>Time </b></label>&emsp;&emsp;&emsp; 
					<select class="selectTime" name="startTime" id="Time24">  </select>--
						<select class="selectTime" name="endTime" id="Time24h"> </select> <br>
					<label><b>Date</b></label>&emsp;&emsp;&emsp; <input type="date"
						id="timestart" name="startDate"> -- <input type="date"
						name="endDate" id="timeend"><br>
					<div style="text-align: center;">
						
					<button onclick="search_alert()" class="button-search"
							id="btn-search-event" type="button" role="button">
							<i class="fa-solid fa-magnifying-glass"> Search</i>
						</button>
						&emsp;
						<button onclick="btn_reset()" class="button-search" role="button">
							<i class="fa-regular fa-trash-can"> Reset</i>
						</button>
					</div>
				</div>

				<div id="form_middle">
					<label><b>Place: </b></label>&emsp;&emsp; <input type="text"
						placeholder="input place" name="place" id="Phase"><br>

					<label><b>Department </b></label> <select name="departID">
							<option value="">Department</option>
						<c:forEach var="department" items="${departments}">
							<option value=${department.id}>${department.departName}</option>
						</c:forEach>
					</select> <br> <label><b>Creater</b></label>&emsp;&emsp; <input
						type="text" placeholder="nguoi tao" name="creator" id="Creater">

				</div>
				<div id="form_right">
					<label><b>Event type</b></label>&emsp; <select name="eventType">
						<option value="">EventName</option>
						<c:forEach var="event" items="${eventTypes}">
							<option value=${event.id}>${event.eventName}</option>
						</c:forEach>
					</select> <br> <label><b>Event Status </b></label> <select
						name="status">
						<option value="">Status</option>
						<option value="1">Open</option>
						<option value="2">Closed</option>
					</select><br>

				</div>
				<hr>
			</form>




		</div>
		<br>
		<hr>
		<div id="form_wrapper_mini">
			<div id="form_left_mini">
				&emsp;<i class="fa-solid fa-list">&emsp;<b>Event List</b></i>
			</div>
			<div id="form_right_mini">
				<button onclick="location.href='add-form.html'" class="button-add">
					<a class="btn-add" href="./addEvent"><i
						class="fa-solid fa-plus">Add Event</i></a>
				</button>
			</div>
		</div>
		<div id="ajax-response">
			<jsp:include page="event-list-content.jsp"></jsp:include>
		</div>


	

	</div>

</body>

<script>
	let contentsStart = " ";
	let contentsEnd = " ";
	let start = document.getElementById("Time24");
	let end = document.getElementById("Time24h");
	let index = 1;
	let firtdata=" ";
	let firtContentStart="Start Time";
	let firtContentEnd = "End Time";
	let firtValue = "";
	for (let i = -1; i < 24; i++) {
		let data;
		
		if(i==-1){
			contentsStart += '<option value = '+ firtValue+ '>' + firtContentStart + '</option>';
			contentsEnd += '<option value = '+ firtValue+ '>' + firtContentEnd + '</option>';
		} else {
			if (i < 10) {
				data = "0" + i;
				contentsStart += '<option value = '+ data+ '>' + data + '</option>';
				contentsEnd += '<option value = '+ data+ '>' + data + '</option>';
			} else {
				data = i + "";
				contentsStart += '<option value = '+ data+ '>' + data + '</option>';
				contentsEnd += '<option value = '+ data+ '>' + data + '</option>';
			}
		}
		
		
	}
	start.innerHTML = contentsStart;
	end.innerHTML = contentsEnd;
</script>
<script src="https://kit.fontawesome.com/96ed116afc.js"
	crossorigin="anonymous"></script>
<script type="text/javascript" src="resources/js/js.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/sweetalert2@10.10.1/dist/sweetalert2.all.min.js"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	/* function test(){
		alert('123');
	}
	
	$('#pagination a').click(function() {
		test();
	}); */
	
	$(document).ready(function() {

		 $('#pagination a').click(function() {
			let current = 1;
			if($(this).text().trim()!=null){
				current = $(this).text().trim();
				console.log($(this).text().trim())
			}
			
			
			let form = $("#form_wrapper").serialize();
			console.log(form);
			$.ajax({
				type : "GET",
				contentType : "application/json; charset=utf-8",
				url : "getByPage?page="+current,
				data :  form,
				timeout : 1000,
				success : function(data) {
					$("#ajax-response").html(data);
				},
				error : function(e) {
					console.log("ERROR: ", e);
				}
			});
		}); 

		$('#btn-search-event').click(function() {
 			
			let form = $("#form_wrapper").serialize();
			console.log(form);
			$.ajax({
				type : "GET",
				contentType : "application/json; charset=utf-8",
				url : "getByPage",
				data :  form,
				timeout : 1000,
				success : function(data) {
					$("#ajax-response").html(data);
				},
				error : function(e) {
					console.log("ERROR: ", e);
				}
			});

		});

		/* 		form.addEventListener("submit", function(e) {
		 e.preventDefault();
		 let form = $("#form_wrapper").serialize();
		 console.log(form);
		 $.ajax({
		 type : "POST",
		 contentType : "application/json; charset=utf-8",
		 url : "getByPage",
		 data : form,
		 timeout : 1000,
		 success : function(data) {
		 console.log("SUCCESS: ", data);
		 $("#ajax-response").html(data);
		 },
		 error : function(e) {
		 console.log("ERROR: ", e);
		 }
		 });

		 }) */

	});
</script>
<footer> </footer>