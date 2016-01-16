<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.ca.ofs.beans.Training,com.ca.ofs.logic.ConsolidatedFeedback,com.ca.ofs.utils.OFSCommons,java.text.DecimalFormat"%>
<!DOCTYPE HTML>
<html>

<head>
<title>OFS | Consolidated Report</title>
<meta name="description" content="website description" />
<meta name="keywords" content="website keywords, website keywords" />
<meta http-equiv="content-type"
	content="text/html; charset=windows-1252" />
<link rel="stylesheet" type="text/css" href="style/style.css"
	title="style" />
<style>
table,td,th #basic
{
border:0.2px solid black;
}
table
{
width:70%;
padding: 3px;
}
th
{
height:50px;
}
td
{
padding-left: 7px;
font-size: medium;
}
table,td,th #form
{
border:0px;
}
</style>
</head>

<body>
	<div id="main">
		<div id="header">
			<div id="logo">
		        <div id="logo_text">
		          <h1>Welcome <%= session.getAttribute("name") %> !! </h1>
		          <h3><%=session.getAttribute("desg")%></h3>
		        </div>
			</div>
			<div id="menubar">
				<ul id="menu">
					<li><a href="cLanding.jsp">Home</a></li>
					<li class="selected"><a href="#">Consolidated Feedback Report</a></li>
				</ul>
			</div>
			<div id="logout" ><form action="logout" method="post"> <button type="submit"><img src="style/logoutbutton.png"/></button></form></div>
		</div>
		<%
			ConsolidatedFeedback cf = (ConsolidatedFeedback) session.getAttribute("report");
			Training tr = (Training) session.getAttribute("selectedTraining");
			DecimalFormat df = new DecimalFormat(OFSCommons.DECIMAL_FORMAT);
		%>
		<div id="site_content">
			<table id="basic">
				<tr>
					<td>Trainer Name</td>
					<td><%=tr.getTrainerName() %></td>
				</tr>
				<tr>
					<td>Training Subject</td>
					<td><%=tr.getTrainingName() %></td>
				</tr>
				<tr>
					<td>Start Date</td>
					<td><%=tr.getStartDate() %></td>
				</tr>
				<tr>
					<td>End Date</td>
					<td><%=tr.getEndDate() %></td>
				</tr>
				<tr>
					<td>No. of Participants</td>
					<td><%=cf.getTotalParticipants() %></td>
				</tr>
				<tr>
					<td>No. of Responses</td>
					<td><%=cf.getTotalFeedbacks() %></td>
				</tr>
				<tr>
					<td>Percentage</td>
					<td><%=df.format( (cf.getTotalFeedbacks() * 100.0)/cf.getTotalParticipants() )%></td>
				</tr>
			</table>
			<br />
			
			<table id="form">
			<tr> <td><h4>
				Item </h4></td> <td> <h4> Average 	</h4></td>
		
			</tr>
	
			<td><h5>General</h5></td></tr>
			<tr> <td>Were the objectives of the program clear to you?
				</td><td style="color:blue">N/A </td></tr>
			

			<tr><td><h5>Trainer/Facilitator</h5></td></tr>
			<tr><td>The subject knowledge of the trainer</td><td style="color:blue">
			<%=df.format(cf.getAnsTrainer1()) %></td></tr>
			
			<tr><td>
			The usage of productive discussions and exmamples by the trainer</td>
			<td style="color:blue"><%=df.format(cf.getAnsTrainer2()) %></td></tr>
			

			<tr><td><h5>Course Material [If applicable]</h5> </td></tr>
			<tr><td>Course material provided for the program</td><td style="color:blue">
			<%=df.format(cf.getAnsMaterial1()) %></td>
			</tr>
			<tr><td>The activities (exercises and assignments) employed to contribute to your learning</td><td style="color:blue">
			<%=df.format(cf.getAnsMaterial2()) %></td>
			
			<tr><td><h5>Facilities</h5></td></tr>
			<tr><td>
			The training facility was adequate and comfortable</td><td style="color:blue">
			<%=df.format(cf.getAnsFacilities()) %></td></tr>
			
			<tr><td><h5>Overall Rating</h5></td></tr>
			<tr><td>Overall feedback rating of the program</td><td style="color:red">
			<%=df.format(cf.getOverallScore()) %></td></tr>
			
			<tr><td><h4>Comments</h4></td></tr>
			<tr><td><h5>Which part of the program was of the most value to you - why?</h5></td></tr>
			<% for(String comment : cf.getAnsValueMostList()) { %>
			<tr><td style="color:blue"><%=comment %></td></tr>
			<% } %>
			<tr><td>
			<h5>Which part of the program was of the least value to you - why?</h5></td></tr>
			<% for(String comment : cf.getAnsValueLeastList()) { %>
			<tr><td style="color:blue"> <%=comment %> </td> </tr>
			<% } %>
			<tr><td>
			<h5>Your feedback and suggestions to improve this program.</h5></td></tr>
			<% for(String comment : cf.getSuggestionList()) { %>
			<tr><td style="color:blue"><%=comment %> </td></tr>
			<% } %>
			</table>
			<br />
			<br />
			
			<div id="download"><form action="downloadReport" method="post"><input id="ofs-btn" type="submit" value="Download" /></form></div>
		</div>
		<div id="content_footer"></div>
		<div id="footer">Copyright &copy; |MAHYO01|</div>
	</div>
</body>
</html>
