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
<title>Add Event</title>
<meta name="viewport" con t="width=device-width, initial-scale=1.0">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700;800;900&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.3.1/css/all.min.css">
<link class="jsbin"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<script class="jsbin"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script class="jsbin"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>

<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/button.css">
<link rel="stylesheet" href="resources/css/inputstyle.css">
<link rel="stylesheet" href="resources/css/pagelist.css">

<script src="https://kit.fontawesome.com/96ed116afc.js"
	crossorigin="anonymous"></script>
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
		<br>
		
		
		<form id="form-add-event" action="./addEvent" method="POST" enctype="multipart/form-data"  modelAttribute="eventAdd">
			<div id="form_wrapper_max">
			<div id="form_left_max">
				<br>
				<div style="text-align: center;">
					<i class="fa-solid fa-circle-info">&emsp;<u>Event
							information</u></i><br> <br> &emsp;<label><b>Event
							name </b></label> 
							
							<input type="text" placeholder="nhap name event" name="eventName" id="EventName" required><br> &emsp;
							
							<label><b>Description </b></label>
							
							 <input type="text" placeholder="nhap Description" name="description" id="Description" required><br> 
						
						
					<select class="selectTime" id="department" name="departID">
						<c:forEach var="department" items="${departments}">
							<option value=${department.id}>${department.departName}</option>
						</c:forEach>
					</select>
					
					
					 &emsp; 
					
					<select class="selectTime" id="member" name="memberId">
						  <jsp:include page="members.jsp"></jsp:include>
					</select>
				    <br>


					<button type="button" onclick="tableGroup()" class="button-add2">Add</button>
					&emsp;&emsp;
					<button type="button" onclick="tableMember()" class="button-add2">Add</button>
					<br> <br>
					<div
						style="display: flex; align-items: center; height: 200px; overflow: auto;">
						
						&emsp;<label><b>Members </b></label>
						<table class="tablediv" id="listtable">
							<!--------------------------------------------------->
						</table>
						
					 
						
						
					</div>
					<br> <label><b>Place </b></label>&emsp;&emsp;&emsp; <input
						type="text" placeholder="nhap dia chi" name="place" id="Phase" required>
						<input id="file-input" onchange="readURL1(this);" type="file" name="imageMain" />
					<div class="image-upload" style="align-items: center;">
					
						<label for="file-input"> <img id="imgplace"
							src="https://mygear.vn/wp-content/uploads/2022/04/Hinh-Anime-hoat-hinh-sac-net-scaled-2.jpg" />
						</label> 
						
						
						
						
					</div>
					<br> <label><b>Event Type </b></label> <select
						name="eventType">
						<c:forEach var="event" items="${eventTypes}">
							<option value=${event.id}>${event.eventName}</option>
						</c:forEach>
					</select> <br> <label><b>Option Type </b></label> <select
						name="optionType" id="optionType" onclick="btn_disabled()">
						<option id="option1" value="1">option 1</option>
						<option id="optionn" value="2">option n</option> </select><br>
						 <label><b>Start </b></label>
						 
						  <select class="selectTime"name="startHour" id="Time24"> </select>
						  - <select class="selectTime" name="startMinute"id="Time60"></select>
						  
						  
						   - <input onkeydown="return false" name="startDate"
						id="timestart" type="date"><br> <label><b>End
					</b></label>
					
					 <select class="selectTime" name="endHour" id="Time24h"></select>
					- <select class="selectTime" name="endMinute" id="Time60h"></select> -
					
					   <input onkeydown="return false" name="endDate" id="timeend" type="date"><br>
				</div>
			</div>
			<div id="form_right_max">
				<br>
				<div style="text-align: center;">
					<i class="fa-solid fa-circle-info">&emsp;<u>Vote option</u></i><br>
					<br>
					<div id="hidden" style="display: none; text-align: center;">
						<button type="button" onclick="addTime()" class="button-search"
							id="btntime">Add Time Option</button>
						&emsp;&emsp;&emsp;&emsp;
						<button type="button" onclick="addPlace()" class="button-search"
							id="btnplace">Add Place Option</button>
					</div>
					<div id="form_wrapper_VoteOption">
						<div id="form_left_VoteOption"
							style="height: 700px; overflow: auto;">
							<table class="tablediv2" id="tableaddtime">
								<tbody class="tbodydiv">
								</tbody>
							</table>
						</div>
						<div id="form_right_VoteOption"
							style="height: 700px; overflow: auto;">
							<table class="tablediv2" id="tableaddplace">
								<tbody class="tbodydiv2"></tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>	
		<!-- onclick="addEvent()" -->
		<input type="hidden" id="txt-members" name="dataMembers" value ="0"/>
		<input type="hidden" id="txt-departments" name="dataDepartments" value ="0"/>
		<button id="btn-add-event"  type="submit" role="button" class="button-search">Add Event</button>
		</form>



		<div style="text-align: center;">
			
			
			&emsp;&emsp;&emsp;&emsp;
			<button type="button" class="button-search">Cancel</button>
			<br> <br>
		</div>
	</div>
</body>
<footer>
	<script>
	let contentsStart = " ";
	let contentsEnd = " ";
	
	let contentsStartMinute = " ";
	let contentsEndMinute = " ";
	
	
	let startHour = document.getElementById("Time24");
	let endHour = document.getElementById("Time24h");
	
	let firtContentStart="Start Hour";
	let firtContentEnd = "End Hour";
	
	let startMinute= document.getElementById("Time60");
	let endMinute = document.getElementById("Time60h");
	
	let firtContentStartMinute="Start Minute";
	let firtContentEndMinute = "End Minute";
	
	
	let index = 1;
	let firtdata=" ";
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
	
	

	for (let i = -1; i < 60; i++) {
		let data;
		
		if(i==-1){
			contentsStartMinute += '<option value = '+ firtValue+ '>' + firtContentStartMinute + '</option>';
			contentsEndMinute += '<option value = '+ firtValue+ '>' + firtContentEndMinute + '</option>';
		} else {
			if (i < 10) {
				data = "0" + i;
				contentsEndMinute += '<option value = '+ data+ '>' + data + '</option>';
				contentsStartMinute += '<option value = '+ data+ '>' + data + '</option>';
			} else {
				data = i + "";
				contentsStartMinute += '<option value = '+ data+ '>' + data + '</option>';
				contentsEndMinute += '<option value = '+ data+ '>' + data + '</option>';
			}
		}
		
		
	}
	
	startHour.innerHTML = contentsStart;
	endHour.innerHTML = contentsEnd;
	
	startMinute.innerHTML = contentsStartMinute;
	endMinute.innerHTML = contentsEndMinute;
	
	
	
	
	
	
	
	
	
	 
</script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
		integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
		crossorigin="anonymous" referrerpolicy="no-referrer">
		
	</script>


	<script type="text/javascript" src="resources/js/js.js"></script>
	<script type="text/javascript">
$(document).ready(function() {
	
	
	$('#department').on('change', function() {
		   
		  let departID = this.value;
			$.ajax({
				type : "GET",
				contentType : "application/json; charset=utf-8",
				url : "loadUser?departID="+departID,
				data :{   
					},
				timeout : 1000,
				success : function(data) {
					$("#member").html(data);
				},
				error : function(e) {
					console.log("ERROR: ", e);
				}
			});
});
		  
		  
 	
		 
})
		 
	</script>
</footer>