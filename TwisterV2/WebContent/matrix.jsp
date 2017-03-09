<%@page import="util.Dico"%>
<%@page import="beans.util.Post"%>
<%@page import="db.db_User_Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="/TwisterV2/css/bootstrap.min.css" rel="stylesheet">
<link href="/TwisterV2/css/styles.css" rel="stylesheet">

 <style>
.glyphicon:empty {
    height: 20px;
}
</style>

<div class="wrapper">
<div class="overlay hidden" style="position:fixed;width:100%;height:100%;z-index:99;"></div>
    <div class="box">
        <div class="row row-offcanvas row-offcanvas-left">
                      
          
            <div class="column col-sm-12 col-xs-12" id="main">
                
              	<c:import url="nav.jsp"/>
              
                <div class="padding" style="margin-top:50px;">
                    <div class="grid">
                      
                      	<div class="grid-item well col-xs-12 col-lg-6 col-lg-offset-3 col-md-offset-3 col-md-6" style="padding:0;">
                          
                     <style>
                     .hh{
                     height:90vh !important;
                     }
#editor{
z-index: 999;
    height: 15px;
    }
    #form_post{
    z-index: 999;
    
    }
</style>
                                   <form id="form_post" class="form-horizontal" role="form" action="addpost" method="GET" >
                                    <h4>Publier un code</h4>
                                     <div class="form-group" style="padding:14px;">
                                      <div id="editor" class="">En java ...</div>
                                    <input type="hidden" id="key" value="${key}" name="key"/>
                                    </div>
                                    <button class="btn btn-primary pull-right" type="button" id="post">Post</button>
                                  </form>
                           
                       </div>
                     <c:forEach var="pays" items="${posts}"> 
 <%
   pageContext.setAttribute("name",db_User_Helper.c().getXWithX("login",Dico.toP("id",((Post)pageContext.getAttribute("pays")).id_user) ).getValue("login")); 

 %>
                          <div class="grid-item col-md-offset-3 col-md-6 col-lg-6 col-lg-offset-3">
                       
                               <div class="panel panel-default">
                                 <div class="panel-heading"><h4>${name}</h4></div>
                                  <div class="panel-body">
                                    <div class="clearfix"></div>
                                    
                                    <pre><code class="java">${pays.text}</code></pre>
                                    
                                    <form>
                                    <div class="input-group">
                                      <div class="input-group-btn">
                                      <button class="btn btn-default">like</button>
                                      </div>
                                      <input type="text" class="form-control" placeholder="Add a comment..">
                                    </div>
                                    </form>
                                    
                                  </div>
                               </div>

                        
                          </div>
                          </c:forEach>

                       </div>
                    
                      
                    </div>
                </div>
            </div>
            
          
        </div>
</div>

<c:import url="modalpost.jsp"/>

<c:import url="importjs.jsp"/>
		<script src="/TwisterV2/js/jquery.cookie.js"></script>

<script>
o=0;
$("#editor").click(function(){
	$(this).addClass("hh");

	$(".overlay").removeClass("hidden");
	setTimeout(function(){
		$(".grid").masonry();
		
	},500);
	setTimeout(function(){
		if(o==0) {
			window.dispatchEvent(new Event('resize'))
		}
		o+=1;
	},1000);
});
$.getJSON("listposts", {key:$.cookie('key'),type:"ALL"},function(e){
	console.log(e);
});
$(".overlay").click(function(){
	console.log($(this));
	$("#editor").removeClass("hh");
	$(this).addClass("hidden");
	setTimeout(function(){
		$(".grid").masonry();

	},500);

});
</script>
