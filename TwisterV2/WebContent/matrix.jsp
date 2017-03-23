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
.bleu{
	background-color: #3b5999 !important;
	width: 100%;
	text-align: center;
	color: #FFFFFF;
	position: fixed;
	height: 35px;
	z-index: 9;
	vertical-align: center;
}
</style>

<div class="wrapper">
<div class="overlay hidden" style="position:fixed;width:100%;height:100%;z-index:99;"></div>
    <div class="box">
        <div class="row row-offcanvas row-offcanvas-left">
                      
          
            <div class="column col-sm-12 col-xs-12" id="main">
                
              	<c:import url="nav.jsp"/>
              	<div style="margin-top: 70px; top: 0; position: fixed;"> 
              <div class="bleu"><span class="centerH"><a href="#postModal" style="color: #ffffff;" role="button" data-toggle="modal"><i class="glyphicon glyphicon-plus"></i></a></span></div>
</div>
                <div class="padding" style="margin-top:50px;">
                    <div class="grid">
                      
                      	<div class="grid-item well col-xs-offset-1 col-xs-10 col-lg-8 col-lg-offset-2 col-md-offset-2 col-md-8" style="padding:0;">
                          
                     <style>
                     .hh{
                     height:90vh !important;
                     }
#editor{
z-index: 999;
    height: 150px;
    }
    #form_post{
    z-index: 999;
    
    }
    .langageSelect {
		margin-left: 30px;
	    background-color: #3B5999;
	    color: #ffffff;
	    height: 35px;
	}
	.inputTitle {
		border: 0;
	    color: #000000;
	    left: 50%;
	    width: 100%;
	    text-align: center;
	    height: 30px;
	    vertical-align: middle;
	    background-color: rgb(229, 229, 229);
	}
</style>							
									<div style="height: 15px;"></div>
                                   <form id="form_post" class="form-horizontal" role="form" action="addpost" method="GET" >
                                    <h4><input class="inputTitle" type="text" id="form_title" placeholder="Post's Title" name="title" /></h4>
                                   <div id="editor" style="width:100%;"></div>
                                     <div class="form-group" style="padding:1px;">
<!--                                       <div id="editor" class="">Java...</div> -->
                                    <input type="hidden" id="key" value="${key}" name="key"/>
                                    </div>
                                      <div class="form-group">
                                    <div style="float: left;">
	                                    <select name="lg" id="form_lg" class="langageSelect">
	                                    	<option value="rien">Quel Language ?</option>
	                                    	<option value="java">Java</option>
	                                    	<option value="javascript">Javascript</option>
	                                    	<option value="c">C</option>
	                                    	<option value="php">Php</option>
	                                    	<option value="bash">Bash</option>
	                                    </select>
                                    </div>
                                    <div style="width: 100%; height: 35px;"><input type="text" name="description" placeholder="Description...." /></div>
                                    <button style="margin-right: 30px; width: 80px;" class="btn btn-primary pull-right" type="button" id="post">Post</button>
                                 </div>
                                  </form>
                           
                       </div>
                    
                       </div>
                    
                      
                    </div>
                </div>
            </div>
            
          
        </div>
</div>

<div class="hidden" data-template="message">
<c:import url="vue/message.html" />
</div>
<c:import url="modalpost.jsp"/>

<c:import url="importjs.jsp"/>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js"></script>
<script type="text/javascript">

messages_list=null;
$(function(){
	
o=0;
fd="${que}";
function init(){
	d ={};
	if(fd=="posts") {
		d={key:$.cookie("key")}
	}else{
		d= {
				key:$.cookie("key"),
				type:"TOTAL"
			};
	}
	$.get("listposts",d,function(d){
		console.log(d);
    if ("response" in d) {
    	a=new Messages(d);
    	messages_list=a;
    	console.log(a);
    	    // add and lay out newly prepended items
    	   $items=a.HTML()
		$(".grid").append($items).masonry( 'appended', $items ).masonry();
		setTimeout(function(){
			$('pre code').each(function(i, e) {hljs.highlightBlock(e);}); 
		},1000);
		
  }
	});
}
init();
// $("#editor").click(function(){
// 	$(this).addClass("hh");

// 	$(".overlay").removeClass("hidden");
// 	setTimeout(function(){
// 		$(".grid").masonry();
		
// 	},500);
// 	setTimeout(function(){
// 		if(o==0) {
// 			window.dispatchEvent(new Event('resize'))
// 		}
// 		o+=1;
// 	},1000);
// });
// $(".overlay").click(function(){
// 	console.log($(this));
// 	$("#editor").removeClass("hh");
// 	$(this).addClass("hidden");
// 	setTimeout(function(){
// 		$(".grid").masonry();

// 	},500);

// });

})
</script>
