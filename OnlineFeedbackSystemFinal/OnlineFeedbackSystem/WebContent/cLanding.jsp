<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE>
<html>
<head>
<head>
  <title>OFS | Co-ordinator</title>
  <meta charset="ISO-8859-1">
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
  <link rel="stylesheet" type="text/css" href="style/style.css" title="style" />
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
        	<li class="selected"><a href="cLanding.jsp">Add Training Schedule</a></li>
          	<li><a href="coord_genreports.jsp">Generate Reports</a></li>
        </ul>
      </div>
      <div id="logout" ><form action="logout" method="post"> <button type="submit"><img src="style/logoutbutton.png"/></button></form></div>
    
    </div>
    <div id="content_header"></div>
    <div id="site_content">
      <div id="content">
      <form action="addschedule" method="POST">
		Training Subject : &nbsp;<input id="sub" name="sub" type="text" /><br />
		<br /> Trainer Name : &nbsp;<input id="name" name="name" type="text" /><br />
		<br /> Starting Date : &nbsp;<input id="sdate" name="sdate" type="date" /><br />
		<br /> Ending Date : &nbsp;<input id="edate" name="edate" type="date" /><br />
		<br /> Add Participants : &nbsp;<input id="add" name="add" type="text" /><br />
		OR 
		<br /> Upload Participants file : &nbsp;<input id="addfile" name="addfile" type="file" /><br />
		<input id="ofs-btn" type="submit" value="Add Details" />
	</form>
      
    </div>
</div>
    <div id="content_footer"></div>
    <div id="footer"> Copyright &copy; |MAHYO01|
     
  </div>
  </div>
	</body>
</html>