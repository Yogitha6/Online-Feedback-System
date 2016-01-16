<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="com.ca.ofs.beans.Training,com.ca.ofs.logic.ConsolidatedFeedback,com.ca.ofs.utils.OFSCommons,java.text.DecimalFormat"%>
<!DOCTYPE HTML>
<html>

<head>
<title>OFS | Consolidated Report</title>
</head>

<body>
	<div id="main">
		<%
			ConsolidatedFeedback cf = (ConsolidatedFeedback) session.getAttribute("report");
			Training tr = (Training) session.getAttribute("selectedTraining");
			DecimalFormat df = new DecimalFormat(OFSCommons.DECIMAL_FORMAT);
		%>
		<table border='1'>
			<tr>
				<td>Trainer Name</td>
				<td><%=tr.getTrainerName()%></td>
			</tr>
			<tr>
				<td>Training Subject</td>
				<td><%=tr.getTrainingName()%></td>
			</tr>
			<tr>
				<td>Start Date</td>
				<td><%=tr.getStartDate()%></td>
			</tr>
			<tr>
				<td>End Date</td>
				<td><%=tr.getEndDate()%></td>
			</tr>
			<tr>
				<td>No. of Participants</td>
				<td><%=cf.getTotalParticipants()%></td>
			</tr>
			<tr>
				<td>No. of Responses</td>
				<td><%=cf.getTotalFeedbacks()%></td>
			</tr>
			<tr>
				<td>Percentage</td>
				<td><%=df.format((cf.getTotalFeedbacks() * 100.0)
					/ cf.getTotalParticipants())%></td>
			</tr>
		</table>
		<br />
		<table border="1">
			<tr>
				<td><h4><b>Item</b></h4></td>
				<td><h4><b>Average</b></h4></td>
			</tr>
			<tr>
				<td><h4><b>General</b></h4></td>	<td></td>
			</tr>
			<tr>
				<td>Were the objectives of the program clear to you?</td>
				<td><b>N/A</b></td>
			</tr>
			<tr>
				<td><h4><b>Trainer/Facilitator</b></h4></td>	<td></td>
			</tr>
			<tr>
				<td>The subject knowledge of the trainer</td>
				<td><b><%=df.format(cf.getAnsTrainer1())%></b></td>
			</tr>
			<tr>
				<td>The usage of productive discussions and examples by the trainer</td>
				<td><b><%=df.format(cf.getAnsTrainer2())%></b></td>
			</tr>
			<tr>
				<td><h4><b>Course Material [If applicable]</b></h4></td>	<td></td>
			</tr>
			<tr>
				<td>Course material provided for the program</td>
				<td><b><%=df.format(cf.getAnsMaterial1())%></b></td>
			</tr>
			<tr>
				<td>The activities (exercises and assignments) employed to contribute to your learning</td>
				<td><b><%=df.format(cf.getAnsMaterial2())%></b></td>
			</tr>
			<tr>
				<td><h4><b>Facilities</b></h4></td>	<td></td>
			</tr>
			<tr>
				<td>The training facility was adequate and comfortable</td>
				<td><b><%=df.format(cf.getAnsFacilities())%></b></td>
			</tr>
			<tr>
				<td><h4><b>Overall Rating</b></h4></td>	<td></td>
			</tr>
			<tr>
				<td>Overall feedback rating of the program</td>
				<td><b><%=df.format(cf.getOverallScore())%></b></td>
			</tr>
			<tr>
				<td><h4><b>Comments</b></h4></td> <td></td>
			</tr>
			<tr>
				<td><b><i>Which part of the program was of the most value to you - why?</i></b></td>
				<td></td>
			</tr>
			<% for (String comment : cf.getAnsValueMostList()) { %>
			<tr> <td><%= comment %></td> <td></td> </tr>
			<% } %>
			<tr>
				<td><b><i>Which part of the program was of the least value to you - why?</i></b></td>
				<td></td>
			</tr>
			<% for (String comment : cf.getAnsValueLeastList()) { %>
			<tr> <td><%= comment %></td> <td></td> </tr>
			<% } %>
			<tr>
				<td><b><i>Your feedback and suggestions to improve this program</i></b></td>
				<td></td>
			</tr>
			<% for (String comment : cf.getSuggestionList()) { %>
			<tr> <td><%= comment %></td> <td></td> </tr>
			<% } %>
		</table>
	</div>
</body>
</html>
