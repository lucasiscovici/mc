<%@page import="util.io"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="util.Usefull"%>
<%@page import="Web.Base" %>
<%
Base.fromRequest(request);
%>
<!-- JAVA CLASS TO IMPORT -->



<!-- CSS TO IMPORT -->
<%
	
    request.setAttribute("importCSS", Usefull.ListFromStringA(
    		Base.baseCSSLib+"bootstrap.min.css",
    		Base.baseCSS+"styles.css",
    		"http://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.9.0/styles/default.min.css",
    		Base.baseCSS+"matrix.css"
    		));
%>

<c:forEach items="${importCSS}" var="importItem">
<link href="${importItem}" rel="stylesheet">
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



