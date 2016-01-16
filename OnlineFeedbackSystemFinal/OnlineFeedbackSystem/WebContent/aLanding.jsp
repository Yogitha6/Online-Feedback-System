<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

  <title>OFS | Administrator</title>
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
        
      <%
  // Get the session object. If the incoming connection
  // is not associated with any existing session, the 
  // container constructs a new session object.
  // Session never expires. The expiration date/time can also 
  // be set in the servlet container's config file.
  session.setMaxInactiveInterval(-1);

  // Get username. If no username is found, set it to "anonymous".
  String username = (String) session.getAttribute("name");
  if ( username == null ) {
    username = "anonymous";
  }
%>
        
          <h1>Welcome <%=username%> !!</h1>
          <h3><%=session.getAttribute("desg")%></h3>
        </div>
      </div>
      <div id="menubar">
        <ul id="menu" >
          <li class="selected"><a href="admin_addcoordinator.html">Add Co-ordinator</a></li>
          <li><a href="admin_addparticipant.html">Add Participants</a></li>
          
        </ul>
      </div>
      <div id="logout" ><form action="logout" method="post"> <button type="submit"><img src="style/logoutbutton.png"/></button></form></div>
    </div>
    <div id="content_header"></div>
    <div id="site_content">
      <div id="content">
        <!-- insert the page content here -->
	<form action="addcoordinator" method="POST">
		PMF Key : &nbsp;<input id="sub" name="pmf_key" type="text" /><br />
		<br /> 
				<input id="ofs-btn" type="submit" value="Add Coordinator" />
	</form>
	
    </div>
</div>
    <div id="content_footer"></div>
    <div id="footer">Copyright &copy; |MAHYO01|
     </div>
  </div>
</body>
</html>





