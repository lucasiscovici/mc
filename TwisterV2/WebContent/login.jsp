<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@page import="util.io"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="util.Usefull"%>
<%@page import="Web.Base" %>
<%
Base.fromRequest(request);
%>

<link rel="stylesheet" href="${baseCSS}login.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/jquery.terminal/1.0.10/css/jquery.terminal.min.css" rel="stylesheet"/>
<link href="https://fonts.googleapis.com/css?family=Caveat+Brush" rel="stylesheet">

</head>
<body style='background-color:black; margin: 0; height: 100%;'>

<div style='height: 100%;' align="center">
<div id="i" style="padding: 0; width: 100%; height: 100%;">
<img width="300px" src="/TwisterV3/img/logo.png" />
</div>
</div>
<!-- JS TO IMPORT  -->
<!-- JS GLOBAL TO IMPORT -->
<c:forEach items="${importJSGlobal}" var="importJSGlobalItem">
	<script type="text/javascript" src="${importJSGlobalItem}"></script>	
</c:forEach>

<%
	request.setAttribute("importJS", Usefull.ListFromStringA(
			Base.baseJSLib+"polyfill.js",
			Base.baseJSConfigLib+"config_polyfill.js",
			"https://cdnjs.cloudflare.com/ajax/libs/jquery.terminal/1.0.10/js/jquery.terminal.min.js",
			Base.baseJS+"login.js",
			Base.baseJSConfigLib+"config_terminal.js",
			Base.baseJSClasses+"session.js",
			Base.baseJSClasses+"user.js"
			));
%>

<c:forEach items="${importJS}" var="importJSItem">
	<script type="text/javascript" src="${importJSItem}"></script>	
</c:forEach>

</body>
</html>