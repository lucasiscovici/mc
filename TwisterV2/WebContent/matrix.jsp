<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="util.Usefull"%>
<!-- JSP TO IMPORT -->

<%
	request.setAttribute("importJSP", Usefull.ListFromStringA(
			"util.Dico",
			"beans.util.Post",
			"db.db_User_Helper"
			));
%>
<c:forEach items="${importJSP}" var="importItem">
<%@page import="${importItem}.jsp"%>
</c:forEach>


<!-- CSS TO IMPORT -->
<%
	
    request.setAttribute("importCSS", Usefull.ListFromStringA(
    		"bootstrap.min",
    		"styles",
    		"http://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.9.0/styles/default.min.css",
    		"matrix"
    		));
%>


<c:forEach items="${importCSS}" var="importItem">
<link href="${baseCSS} }${importItem}" rel="stylesheet">
</c:forEach>
</head>
<body style='background-color:black'>
						
<!-- HTML -->             
<div class="container-fluid" style="padding: 0;">
	<div class="overlay hidden" style="position:fixed;width:100%;height:100%;z-index:99;">
	</div>
	<c:import url="nav.jsp"/>
    <div style="margin-top: 70px;z-index: 88; top: 0; position: fixed;"> 
    	<div class="bare bleu" onclick="$('#postModal').toggleClass('hidden');"><span class="centerH"><i class="glyphicon glyphicon-plus"></i></span>
    	</div>
	</div>
    <div class="row">
    	<div class="grid" style="margin-top:140px">
        </div>
    </div>

</div>

<!-- TEMPLATES TO IMPORT -->
<%
	request.setAttribute("importTemplates", Usefull.ListFromStringA(
			"message"
			));
%>
<c:forEach items="${importTemplates}" var="importTemplatesItem">
	<div class="hidden" data-template="${importTemplatesItem}">
		<c:import url="${baseTemplates}${importTemplatesItem}.html" />
	</div>
</c:forEach>

<!-- HTML TO IMPORT -->
<%
	request.setAttribute("importHTML", Usefull.ListFromStringA(
			"modalpost",
			"modalpostLecture"
			));
%>
<c:forEach items="${importHTML}" var="importHTMLItem">
	<c:import url="${baseVue}${importHTMLItem}.html" />
</c:forEach>


<!-- JS TO IMPORT -->
<!-- JS GLOBAL TO IMPORT -->
<c:forEach items="${importJSGlobal}" var="importJSGlobalItem">
	<script type="text/javascript" src="${importJSGlobalItem}"></script>	
</c:forEach>
<%
	request.setAttribute("importJS", Usefull.ListFromStringA(
			"${baseJSLib}jquery.cookie.js",
			"${baseJSClasses}objects.js",
			"http://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.9.0/highlight.min.js",
			"${baseJSConfigLib}config_highlighth.js",
			"http://cdnjs.cloudflare.com/ajax/libs/ace/1.2.6/ace.js",
			"https://cdnjs.cloudflare.com/ajax/libs/ace/1.2.6/ext-language_tools.js",
			"${baseJSConfigLib}config_ace.js",
			"https://cdnjs.cloudflare.com/ajax/libs/masonry/4.1.1/masonry.pkgd.min.js",
			"${baseJSConfigLib}config_masonry.js",
			"${baseJSClasses}like.js",
			"${baseJSClasses}user.js",
			"${baseJS}matrix.js"
			));
%>

<c:forEach items="${importJS}" var="importJSItem">
	<script type="text/javascript" src="${importJSItem}"></script>	
</c:forEach>

