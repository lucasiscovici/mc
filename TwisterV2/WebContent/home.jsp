<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="util.Usefull" %>
<%@page import="util.Parameters" %>
<%@page import="util.Dico" %>
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
<link rel="stylesheet" href="http://lagencewebetudiante.fr/midter.css" type="text/css" />
<title>Twister</title>

<!-- JS TO IMPORT -->
<%
	request.setAttribute("importJSGlobal", Usefull.ListFromStringA(
			"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js",
			"http://lagencewebetudiante.fr/only.js",
			Base.baseJS+"compressed_global.js"
			));
	
%>
<script>
bases = {
		'base2':'${base2}',
		'base':'${base}',
		'baseCSS':'${baseCSS}',
		'baseVue':'${baseVue}',
		'baseTemplates':'${baseTemplates}',
		'baseVue2':'${baseVue2}',
		'baseTemplates2':'${baseTemplates2}',
		'baseCSSLib':'${baseCSSLib}',
		'baseJS':'${baseJS}',
		'baseJSLib':'${baseJSLib}',
		'baseJSConfigLib':'${baseJSConfigLib}',
		'baseJSClasses':'${baseJSClasses}',
		'baseImg':'${baseImg}',
}
</script>
<!-- IMPORT JSP FILE -->
<c:import url="${request.getAttribute('jsp_file')}.jsp"/>
<input type="hidden" id="key" value="${key}"/>
</body>
</html>