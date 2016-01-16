<%@page import="com.ca.ofs.beans.Training"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>

<head>
  <title>OFS | Feedback</title>
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
          <li class="selected"><a>Feedback Form</a></li>
        </ul>
      </div>
      <div id="logout" ><form action="logout" method="post"> <button type="submit"><img src="style/logoutbutton.png"/></button></form></div>
    </div>
    <%
  Training selectedTraining = (Training) request.getAttribute("selectedTraining");
    %>
    <div id="site_content">
 
	<table border='1'>
	<tr> <td>Training Name </td> <td><%= selectedTraining.getTrainingName()%></td> </tr> 
	<tr> <td>Trainer Name </td> <td> <%= selectedTraining.getTrainerName()%> </td> </tr>
	<tr> <td>Start Date </td> <td><%= selectedTraining.getStartDate() %> </td> </tr>
	<tr> <td>End Date </td> <td><%= selectedTraining.getEndDate()%> </td> </tr> </table><br/>
	<br/>
	<h4> General </h4>
	<br/>
	
	<form action="submitfeedback" method="post">
	<table> <tr> <td width="600">
	<h6>Were the objectives of the program clear to you? </h6></td>
	<td width="120">
	<input type="radio" name="usefulrating" value="Yes"/>Yes
	<input type="radio" name="usefulrating" value="No"/>No<br/></td> </tr> </table> <br/><br/>
	
	<h4> Trainer/Facilitator </h4>
	<table> <tr> <td width="600"><h6>The subject knowledge of the trainer</h6></td>
	<td width="120">
	<input type="radio" name="knowledgerating" value="5"/>5
	<input type="radio" name="knowledgerating" value="4"/>4
	<input type="radio" name="knowledgerating" value="3"/>3
	<input type="radio" name="knowledgerating" value="2"/>2
	<input type="radio" name="knowledgerating" value="1"/>1</td>
	</tr>
	<tr> <td width="600"><h6> The usage of productive discussions and exmamples by the trainer </h6> </td>
	<td>	<input type="radio" name="examplesrating" value="5"/>5
	<input type="radio" name="examplesrating" value="4"/>4
	<input type="radio" name="examplesrating" value="3"/>3
	<input type="radio" name="examplesrating" value="2"/>2
	<input type="radio" name="examplesrating" value="1"/>1 </td> 
	</tr> </table>

	<br/><br/>

	<h4> Course Material [if applicable] </h4> <br/>
	<table> <tr> <td width="600"><h6> Course material provided for the program </h6></td>
	<td width="120">
	<input type="radio" name="materialrating" value="5"/>5
	<input type="radio" name="materialrating" value="4"/>4
	<input type="radio" name="materialrating" value="3"/>3
	<input type="radio" name="materialrating" value="2"/>2
	<input type="radio" name="materialrating" value="1"/>1</td>
	</tr>
	<tr>
	<td width="600"><h6> The activities (exercises/assignments) employed to contribute to your learning </h6></td>
	<td width="120">
	<input type="radio" name="activityrating" value="5"/>5
	<input type="radio" name="activityrating" value="4"/>4
	<input type="radio" name="activityrating" value="3"/>3
	<input type="radio" name="activityrating" value="2"/>2
	<input type="radio" name="activityrating" value="1"/>1
	</td></tr>
	</table>
	<br/><br/>

	<h4> Facilities </h4><br/>
	<table> <tr> <td width="600">
	<h6> The training facility was adequate and comfortable </h6> </td>
	<td width="120">
	<input type="radio" name="facilityrating" value="5"/>5
	<input type="radio" name="facilityrating" value="4"/>4
	<input type="radio" name="facilityrating" value="3"/>3
	<input type="radio" name="facilityrating" value="2"/>2
	<input type="radio" name="facilityrating" value="1"/>1
	</td>
	</tr> </table>
	<br/> <br/> <br/>

	<h4> Which part of the program was of the most value to you - Why? </h4>
	<textarea rows="5" cols="50" name="mostvalue"></textarea>
	<br/>
	
	<h4> Which part of the program was of the least value to you - Why? </h4>
	<textarea rows="5" cols="50" name="leastvalue"></textarea>
	<br/>
	<h4> Your feedback and suggestions to improve this program </h4>
	<textarea rows="5" cols="50" name="suggestion"></textarea>
	<br/> <br/>
	<table> <tr> <td width="600"> <h4> Overall rating of the program </h4></td> <td>
	<input type="radio" name="overallrating" value="5"/>5
	<input type="radio" name="overallrating" value="4"/>4
	<input type="radio" name="overallrating" value="3"/>3
	<input type="radio" name="overallrating" value="2"/>2
	<input type="radio" name="overallrating" value="1"/>1 </td> </tr></table>
	<br/><br/>
	PMF Key: <%= session.getAttribute("username") %> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input id="ofs-btn" type="submit" value="Submit Feedback"/>
	</form>
		
	 </div>
    <div id="content_footer"></div>
    <div id="footer">Copyright &copy; |MAHYO01|
    </div> </div>
</body>
</html>
