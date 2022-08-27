

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<c:forEach var="member" items="${members}">
	<option value=${member.id}>${member.username}</option>
</c:forEach>



<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
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
					console.log(data);
					console.log("ERROR: ", e);
				}
			});
});
		  
		  
 	
		 
})
		 
	</script>