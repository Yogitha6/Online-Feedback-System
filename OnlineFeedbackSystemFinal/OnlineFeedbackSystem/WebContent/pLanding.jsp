<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<!DOCTYPE HTML>
<html>

<head>
<title>OFS | Participant</title>
<meta name="description" content="website description" />
<meta name="keywords" content="website keywords, website keywords" />
<meta http-equiv="content-type"
	content="text/html; charset=windows-1252" />
<link rel="stylesheet" type="text/css" href="style/style.css"
	title="style" />
</head>

<body>
	<%@include file="restrict.jsp"%>
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
					<li class="selected"><a href="participant_view.jsp">Scheduled training for the Day</a></li>
				</ul>
			</div>
			<div id="logout">
				<form action="logout" method="post">
					<button type="submit"><img src="style/logoutbutton.png"/></button>
				</form>
			</div>
		</div>

		<div id="site_content">
			<div id="content">
				<h4 style="color: black">
					Hello
					<%=session.getAttribute("username")%>
					!!
				</h4>
				<br />

				<%
					ArrayList<String> trainingList = (ArrayList<String>) session
							.getAttribute("trainings");
					int i = 0;
				%>

				<form action="getFeedbackForm" method="post">
					<select name="trainingname">
						<%
							for (String tr : trainingList) {
						%>
						<option value=<%=i%>><%=tr%></option>
						<%
							i++;
							}
						%>
					</select> <br /> <br /> <br /> <input id="ofs-btn" type="submit" name="getfeedback"
						value="Get Feedback form" />
				</form>
			</div>
		</div>
		<div id="content_footer"></div>
		<div id="footer">Copyright &copy; |MAHYO01|</div>
	</div>
</body>
</html>
