<%@page import="java.net.URL"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.util.Map"%>
<%@page import="model.VoteOption"%>
<%@page import="database.Dao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.VoteDetail"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="model.Detail"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.EventList"%>
<!DOCTYPE html>
<html>
<head>
    <title>Event detail</title>
    <meta name="viewport" con t="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700;800;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.3.1/css/all.min.css">
    <link rel="stylesheet" href="resources/css/style.css"> 
    <link rel="stylesheet" href="resources/css/button.css"> 
    <link rel="stylesheet" href="resources/css/inputstyle.css"> 
    <script type = "text/javascript" src="resources/js/js.js"></script>
    <script src="https://kit.fontawesome.com/96ed116afc.js" crossorigin="anonymous"></script>
</head>
<header>
    <div id="form_wrapper_header">
    <div id="form_left_header">
        <img class="imgheader" src="resources/image/UsolLogo.jpg">
    </div>
    <div id="form_right_header">
        <div class="right_form_header">
            <h2 id="usernameid" value="<%=session.getAttribute("username") %>"><%=session.getAttribute("username") %></h2>
            <a>logout</a>
        </div>
    </div>
    </div>
</header>
<body>
    <!--Search Condition-->
    <div class="body_form"><br>
        &emsp;<i onclick="displayProperty()" class="fa-solid fa-tag">&emsp;<b>Event information</b></i>
        <%EventList event =(EventList) request.getAttribute("event");%>
    <div id="displayP" class="content">
    <div id="form_wrapper_211">
        <div id="form_left_211">
            <label><b>Event name</b>&emsp;</label>
            <label id=""><b><u><%=event.getEventName() %></u></b></label><br><br>
            <label><b>Time</b>&emsp;</label>
            <label id=""><b><u><%=event.getStartDate() %>--<%=event.getEndDate() %></u></b></label><br><br>
            <label><b>Place</b>&emsp;</label>
            <label id=""><b><u><%=event.getPlace() %></u></b></label><br><br>
            <label><b>Creator</b>&emsp;</label>
            <label id=""><b><u><%=event.getDepartment()%>--<%=event.getCreatetor() %></u></b></label><br><br>
        </div>
        <div id="form_middle_211">
            <div style="text-align: center;">
                <input type="image" class="imgsize2" src="https://mygear.vn/wp-content/uploads/2022/04/Hinh-Anime-hoat-hinh-sac-net-scaled-2.jpg">
            </div>
        </div>
        <div id="form_right_211">
            <div style="text-align: center;">
                <button class="button-setting">
                    <a style="color:white" href="./update_details?id=<%=event.getId()%>"><i class="fa-solid fa-up-from-line">Update</i></a>
                </button><br><br>
                <button class="button-setting">
                    <i class="fa-solid fa-up-from-bracket">Delete</i>
                </button><br><br>
                <button class="button-setting">
                    <i class="fa-solid fa-up-from-bracket">Close</i>
                </button><br><br>
                <button class="button-setting">
                    <i class="fa-solid fa-up-from-bracket">Back list</i>
                </button>
            </div>
        </div>
        <hr>
    </div>
</div><br><hr>
    <div id="form_wrapper_mini">
        <div id="form_left_mini">
            &emsp;<i class="fa-solid fa-list">&emsp;<b>Detail Vote</b></i>
        </div>
        <div id="form_right_mini">
        </div>
    </div>
    <Table style="border-collapse: separate;">
    	<%List<VoteOption> listVoted = (List<VoteOption>)request.getAttribute("listOption"); %>
        <thead>
            <tr>
                <th>Thống kê</th>
                <th>Join: <b><p id="Thongke"></p></b></th>
                <%for(VoteOption vot :listVoted){ %>
                <th><%=vot.getStartDate() %>:</th>
                <%} %>
                <th>Adult: <b>getnumadult</b></th>
                <th>Children: <b>getnumchild</b></th>
            </tr>
            <tr>
                <th style="visibility: hidden;"></th>
                <th style="visibility: hidden;"></th>
<%--                  <%if(vot.getOptionImage()!=null) {%>
                         <%String realPath = request.getServletContext().getRealPath("/");
                 		/* ConstantData.URL_IMAGE */
                 		String path = realPath + "resources\\image\\"+vot.getOptionImage();%>
               			 <th><img src= "<%=path%>" alt="Girl in a jacket" width="500" height="600"></th>         
                    <%} %>   --%>
                
                
                <%for(VoteOption vot :listVoted){ %>
                    <%if(vot.getOptionImage()!=null) {%>
                         <%
                          String url="http://localhost:8080/springcore/resources/image/"+vot.getOptionImage();
                 		String realPath = request.getServletContext().getRealPath("/");
/*                 		String path = realPath + "resources\\image\\";
                         URL url = new URL(path+vot.getOptionImage()); */
                        

                         
                         %>
               			 <th><img src= "<%=url.toString()%>" alt="Girl in a jacket" width="500" height="600"></th>         
                    <%} %>

                <%} %>
            </tr>
        </thead>
    </Table><br><br>
    <Table class="Table" id="statisTable" >
    	<%List<VoteOption> listVote = (List<VoteOption>)request.getAttribute("listOption"); %>
        <thead>
            <tr>
                <th>No</th>
                <th>Member ID</th>
                <th>Full name</th>
                <th>Email</th>
                <th>Join</th>
                <%for(VoteOption vot :listVote){ %>
                <%if(vot.getStartDate()!=null) {%>
                  <th><%=vot.getStartDate() %></th>
                <%} %>
              
                <%} %>
                <th>Attach Adult</th>
                <th>Attach Children</th>
                <th>Note</th>
            </tr>        
            <%List<Detail> details = (List<Detail>)request.getAttribute("ListDetails");
			if (details.size() > 0) {
				for (Detail d : details) {
/* 					List<VoteDetail> votedetails= new ArrayList<>();
					votedetails=Dao.getVoteDetail(event.getEventType(),d.getUserId()); */
            %>        
            <tr>
                <th><%=d.getId() %></th>
                <th><%=d.getUserId() %></th>
                <th><%=d.getFullname() %></th>
                <th><%=d.getEmail() %></th>
                <th>
                    <% String j="X";
                    if (d.getIsJoined()==true){
                    	j="O";
                    	} 
                    %>
                       <h3><%=j%></h3>
                </th>  

				<%for(VoteOption vot :listVote){
					boolean flag = false;	
				%>			
                <th>				
				<%
               		for (Map.Entry<Integer, Integer> entry : d.getVoteMap().entrySet()) {
               			if(vot.getId() == entry.getKey())
               			{
               				flag = true;
               				if(entry.getValue() == 1){
               					System.out.println("Vote value :" + entry.getValue());
                %>
								<h3>O</h3>
				<%
                				}
               					else{
                %>
									<h3>x</h3>
    			<%
                					}
                		}
                	}
				
						if(!flag){
			    %>
							<h3>x</h3>
	   			<%
						}
                %>                     
                </th>
                <%}%>
                <th><%=d.getAttachedPersonAdult() %></th>
                <th><%=d.getAttachedPersonChild() %></th>
                <th><%=d.getNote() %></th>
            </tr>
			<% } %>
		<%} %>

        </thead>
    </Table>
</div>
</body>
<script>
	let refTab = document.getElementById("statisTable")
	let join;
	for(let i = 0; row = refTab.row[i]; i++){
		row = refTab.row[i];
		col = row.cells[4];
		if(col.fisrtChild.nodeValue == 'O'){
			join++;
		}
	}
	document.getElementById("Thongke") = join;
</script>
<footer>

</footer>