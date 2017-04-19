<%@page import="util.io"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="util.Usefull"%>
<%@page import="Web.Base" %>
<%
Base.fromRequest(request);
%>


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

<body style='background-color:white'>
						
<!-- HTML -->             
<div class="container-fluid" style="padding: 0;">
	<div class="overlay hidden" style="position:fixed;width:100%;height:100%;z-index:99;">
	</div>
	
	<c:import url="nav.jsp"/>
	
    <div style="margin-top: 70px;z-index: 88; top: 0; position: fixed;"> 
    	<div class="bare bleu" onclick="openPostModal()"><span class="centerH"><i class="glyphicon glyphicon-plus"></i></span>
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
			"message",
			"commentaire"
			));
%>
<c:forEach items="${importTemplates}" var="importTemplatesItem">
	<div class="hidden" data-template="${importTemplatesItem}">
		<c:import url="${baseTemplates2}${importTemplatesItem}.html" />
	</div>
</c:forEach>

<!-- HTML TO IMPORT -->
<%
	request.setAttribute("importHTML", Usefull.ListFromStringA(
			"modalpost",
			"modalpostLecture",
			"modalcomments"
			));
%>
<c:forEach items="${importHTML}" var="importHTMLItem">
	<c:import url="${baseVue2}${importHTMLItem}.html" />
</c:forEach>


<!-- JS TO IMPORT -->
<script type="text/javascript">
fd="${que}";
</script>

<!-- JS GLOBAL TO IMPORT -->
<c:forEach items="${importJSGlobal}" var="importJSGlobalItem">
	<script type="text/javascript" src="${importJSGlobalItem}"></script>	
</c:forEach>
<%
if(Base.env=="PROD"){

	request.setAttribute("importJS", Usefull.ListFromStringA(
			"https://cdnjs.cloudflare.com/ajax/libs/ace/1.2.6/ace.js",
			"https://cdnjs.cloudflare.com/ajax/libs/ace/1.2.6/ext-language_tools.js",
			Base.baseJS+"compressed_matrix.js"
			));
}else if (Base.env=="DEV"){
	request.setAttribute("importJS", Usefull.ListFromStringA(
			"https://cdnjs.cloudflare.com/ajax/libs/ace/1.2.6/ace.js",
			"https://cdnjs.cloudflare.com/ajax/libs/ace/1.2.6/ext-language_tools.js",
			Base.baseJSConfigLib+"config_ace.js",
			Base.baseJSLib+"jquery.cookie.js",
			Base.baseJSClasses+"objects.js",
			Base.baseJSLib+"masonry.pkgd.min.js",
			Base.baseJSConfigLib+"config_masonry.js",
			Base.baseJSClasses+"like.js",
			Base.baseJSClasses+"user.js",
			Base.baseJSClasses+"post.js",
			Base.baseJSClasses+"comment.js",
			Base.baseJSClasses+"friends.js",
			Base.baseJSClasses+"session.js",
			Base.baseJS+"matrix.js",
			Base.baseJS+"modalpost.js",
			Base.baseJS+"modalpostLecture.js",
			Base.baseJS+"nav.js"
			));
}
%>

<c:forEach items="${importJS}" var="importJSItem">
	<script type="text/javascript" src="${importJSItem}"></script>	
</c:forEach>

