<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="util.Usefull" %>
<%@page import="Web.Base" %>
<%
Base.fromRequest(request);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Caveat+Brush" rel="stylesheet">
<title>Twister</title>

<!-- JS TO IMPORT -->
<%
	request.setAttribute("importJSGlobal", Usefull.ListFromStringA(
			"http://lagencewebetudiante.fr/lucux.js",
			Base.baseJSLib+"bootstrap.min.js",
			Base.baseJS + "global.js",
			Base.baseJS+"func_tools.js",
			Base.baseJSLib+"moment.js",
			"https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js",
			Base.baseJS+"nav.js"
			));
%>
<input type="hidden" id="key" value="${key}"/>

<!-- IMPORT JSP FILE -->
<c:import url="${request.getAttribute('jsp_file')}.jsp"/>
</body>
</html>