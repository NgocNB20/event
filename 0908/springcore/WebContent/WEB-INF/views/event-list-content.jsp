 

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="table-responsive">
	<Table class="Table" id="tableList">
		<tbody>
			<tr>
				<th>Event name</th>
				<th>Description</th>
				<th>Time</th>
				<th>Place</th>
				<th>Department</th>
				<th>Creator</th>
				<th>Status</th>
				<th>Joined</th>
				<th>Not Join</th>
				<th>Action</th>
			</tr>
			<c:forEach var="event" items="${pages}">
				<tr>
					<th>${event.eventName }</th>
					<th>${event.description }</th>
					<th>${event.time }</th>
					<th>${event.place}</th>
					<th>${event.department }</th>
					<th>${event.createtor }</th>
					<th>${event.status?'Opening':'Closed' }</th>
					<th>${event.joined }</th>
					<th>${event.notJoined }</th>
					<th> ${event.statusDelete }</th>
					<th style="width=150px">
					 <a href="./details?id=${event.id }"> <i class="fa-solid fa-info"> </i></a>|&emsp;
					
					 <a href="./details?id=${event.id }"> <i class="fa-solid fa-pen-to-square"></i></a> &emsp;|
					 <a  class="${event.statusDelete?'':'disabled' }" href="" ><i class="fa-solid fa-eraser"></i></a> &emsp;|					 		  
					 <a href="./vote" ><i class="fa-solid fa-check-to-slot" ></i></a> 
					</th>
				</tr>
			</c:forEach>
		</tbody>
	</Table>
</div>
	<br>
		<div style="height: 50px; display: flex; justify-content: center;">

			<div class="pagination" id="pagination">

				<c:if test="${not empty indexs}">
				<c:if test="${not empty pages}">
					<a href="#">&laquo;</a>
					<a href="#">&lsaquo;</a>
				</c:if>
					
					<c:forEach var="index" items="${indexs}">
						<a>${index}</a>
					</c:forEach>
				<c:if test="${not empty pages}">
					<a href="#">&rsaquo;</a>
					<a href="#">&raquo;</a>
				</c:if>


				</c:if>
				<c:if test="${empty pages}">
						<tr class="no-data">
							<a>Không event nào</a>
						</tr>
				</c:if>


			</div>
		</div>
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<script type="text/javascript">
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
	});
</script>