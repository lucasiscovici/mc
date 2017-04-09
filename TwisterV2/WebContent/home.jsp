<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
#t:hover {
filter: grayscale(100%);
}
</style>
<link href="https://fonts.googleapis.com/css?family=Caveat+Brush" rel="stylesheet">
<title>Twister</title>
</head>
<body style='background-color:white'>


<% if (request.getAttribute("type").equals("home") || request.getAttribute("type").equals("matrix")){%>
	<%-- Copie le contenu du fichier ciblé dans la page actuelle --%>
<c:import url="matrix.jsp"/>
	<%
}%>
</body>
</html>