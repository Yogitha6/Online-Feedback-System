<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ca.ofs.beans.Training,com.ca.ofs.utils.DBHelper"%>
<!DOCTYPE HTML>
<html>

<head>
<title>OFS | REPORTS</title>
<meta name="description" content="website description" />
<meta name="keywords" content="website keywords, website keywords" />
<meta http-equiv="content-type"
	content="text/html; charset=windows-1252" />
<link rel="stylesheet" type="text/css" href="style/style.css"
	title="style" />
</head>

<body>
	<div id="main">
		<div id="header">
			<div id="logo">
				<div id="logo_text">
					<h1> Welcome <%=session.getAttribute("name")%> !! </h1>
					<h3><%=session.getAttribute("desg")%></h3>
				</div>
			</div>
			<div id="menubar">
				<ul id="menu">
					<li><a href="cLanding.jsp">Add Training Schedule</a></li>
					<li class="selected"><a href="coord_genreports.jsp">Generate Reports</a></li>
				</ul>
			</div>
			 <div id="logout" ><form action="logout" method="post"> <button type="submit"><img src="style/logoutbutton.png"/></button></form></div>
		</div>
		<div id="content_header"></div>
		<div id="site_content">
			<div id="content">
				<%
					ArrayList<Training> trainingList = (ArrayList<Training>) DBHelper.getAllTrainings();
					int i = 0;
				%>
				<h4>Select a training :</h4>
				<form action="reportPerTraining" method="post">
					<select name="f1TrainingId">
						<%
							for (Training tr : trainingList) {
								String t = tr.getTrainingName() + " by " + tr.getTrainerName() + " (" + tr.getStartDate() + " to " + tr.getEndDate() + ")";
						%>
						<option value=<%=tr.getId()%>><%=t%></option>
						<%
							i++;
							}
						%>
					</select> <input id="ofs-btn" type="submit" value="Generate Consolidated Report" />
				</form>
				<br/>
				
				
				<hr />
				<br/>

				<%
					ArrayList<String> trainerList = (ArrayList<String>) DBHelper.getAllTrainers();
				%>
				<h4>Select a trainer:</h4>
				<form action="reportPerTrainer" method="post">
					<select name="f2Trainer">
						<% for (String trainer : trainerList) { %>
						<option value=<%=trainer%>><%=trainer%></option>
						<% } %>
					</select> <br />
					Between &nbsp;<input id="date1" name="date1" type="date" />&nbsp;and&nbsp;<input id="date2" name="date2" type="date" />
					<input id="ofs-btn" type="submit" value="Generate Consolidated Report" />
				</form>
				<br/>
				
				<hr />
	<br/>			<h4>Click here to get the consolidated report for all the trainings</h4>
				<form action="reportAllTrainings" method="post">
					<input id="ofs-btn" type="submit" value="Generate the final Consolidated Report" />
				</form>
			</div>
		</div>
		<div id="content_footer"></div>
		<div id="footer">Copyright &copy; |MAHYO01|</div>
	</div>
</body>
</html>
