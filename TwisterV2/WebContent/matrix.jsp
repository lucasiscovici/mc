<%@page import="util.Dico"%>
<%@page import="beans.util.Post"%>
<%@page import="db.db_User_Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <style>
 .pp{
 width: 300px;
    background-color: gray;
    height: 60px;
    height: 100%;
    margin-bottom: 5px;
    border-radius: 20px;
    }
    </style>   
<div style="z-index:9;top:0px;width:100vw;left:0px;height:100vh;position:absolute;text-align:center;background-image:url(/TwisterV2/img/matrix.jpeg);"> 
<p style="font-size:40px; color:white;">
HELLO THIS IS THE MATRIX
<div style="color:white">
HELLO ${qui}
<% if (request.getAttribute("erreurs") != null) { %>
<script type="text/javascript">
alert(request.getAttribute("erreurs"));
</script>

<% }else{ %>

<c:forEach var="pays" items="${posts}"> 
<div class="pp">
 <%
   pageContext.setAttribute("name",db_User_Helper.c().getXWithX("login",Dico.toP("id",((Post)pageContext.getAttribute("pays")).id_user) )); 

 %>
				<span>${name}</span>
				<p>${pays.text}</p>
				<span>${pays.date}</span>
		</div>		
	</c:forEach>

<%} %>
</div>
</p>
</div>