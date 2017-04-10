<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="util.Usefull"%>

<link rel="stylesheet" href="${baseCSS}login.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/jquery.terminal/1.0.10/css/jquery.terminal.min.css" rel="stylesheet"/>

<link href="https://fonts.googleapis.com/css?family=Caveat+Brush" rel="stylesheet">
</head>
<body style='background-color:black; margin: 0; height: 100%;'>

<div style='height: 100%;' align="center">
<div id="i" style="padding: 0; width: 100%; height: 100%;">
<img width="300px" src="http://li328.lip6.fr:8280/TwisterV2/img/logo.png" />
</div>
</div>
<!-- JS TO IMPORT  -->
<!-- JS GLOBAL TO IMPORT -->
<c:forEach items="${importJSGlobal}" var="importJSGlobalItem">
	<script type="text/javascript" src="${importJSGlobalItem}"></script>	
</c:forEach>
<%
	request.setAttribute("importJS", Usefull.ListFromStringA(
			"${baseJSLib}polyfill.js",
			"${baseJSConfigLib}config_polyfill.js",
			"https://cdnjs.cloudflare.com/ajax/libs/jquery.terminal/1.0.10/js/jquery.terminal.min.js",
			"${baseJSConfigLib}config_terminal.js",
			"${baseJSClasses}session.js",
			"${baseJSClasses}user.js",
			"${baseJS}login.js"
			));
%>

<c:forEach items="${importJS}" var="importJSItem">
	<script type="text/javascript" src="${importJSItem}"></script>	
</c:forEach>

</body>
</html>