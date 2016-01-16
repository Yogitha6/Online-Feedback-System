<%@ page import="java.io.*"%>
<%@ page pageEncoding="UTF-8"%>
<%
	if (session.getAttribute("username") == null) {
%>
<jsp:forward page="infoPages/noaccess.html" />
<%
	}
%>
