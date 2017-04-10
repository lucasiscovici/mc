<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="util.Usefull" %>
<%
	String base = "/TwisterV2/";
	String baseCSS = base.concat("css/");
	String baseVue = base.concat("vue/");
	String baseTemplates = base.concat("templates/");
	String baseCSSLib = baseCSS.concat("lib/");
	String baseJS = base.concat("js/");
	String baseJSLib = baseJS.concat("lib/");
	String baseJSConfigLib = baseJS.concat("config_lib/");
	String baseJSClasses = baseJS.concat("classes/");

	request.setAttribute("base", base);
	request.setAttribute("baseCSS", baseCSS);
	request.setAttribute("baseCSSLib", baseCSSLib);
	request.setAttribute("baseJS", baseJS);
	request.setAttribute("baseJSLib", baseJSLib);
	request.setAttribute("baseJSConfigLib", baseJSConfigLib);
	request.setAttribute("baseJSClasses", baseJSClasses);
	request.setAttribute("baseVue", baseVue);
	request.setAttribute("baseTemplates", baseTemplates);


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
			"${baseJSLib}bootstrap.min.js",
			"${baseJS}global.js",
			"${baseJS}func_tools.js",
			"${baseJSLib}moment.js",
			"https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js",
			"nav.js"
			));
%>
<input type="hidden" id="key" value="${key}"/>

<!-- IMPORT JSP FILE -->
<c:import url="${request.getAttribute('jsp_file')}.jsp"/>
</body>
</html>