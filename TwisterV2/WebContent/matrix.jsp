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
.bare:hover{
	cursor:pointer;
}

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
                        
<div class="container-fluid" style="padding: 0;">
<div class="overlay hidden" style="position:fixed;width:100%;height:100%;z-index:99;"></div>

                      
          
                
              	<c:import url="nav.jsp"/>
              	     
              	
              	<div style="margin-top: 70px;z-index: 88; top: 0; position: fixed;"> 
              <div class="bare bleu" onclick="$('#postModal').toggleClass('hidden');"><span class="centerH"><i class="glyphicon glyphicon-plus"></i></span></div>
</div>
                <div class="row">
                    <div class="grid" style="margin-top:140px">
                      
                          
                               
                           
                    
                       </div>
                    
                      
                    </div>

</div>

<div class="hidden" data-template="message">
<c:import url="vue/message.html" />
</div>


<c:import url="modalpost.jsp"/>
<c:import url="modalpostLecture.jsp"/>

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
    	messages_list=a.mess;

    	   $items=a.HTML()
		$(".grid").append($items).masonry( 'appended', $items ).masonry();
		setTimeout(function(){
			$('pre code').each(function(i, e) {hljs.highlightBlock(e);}); 
			$(".title_mess").click(function(){
				id=$(this).attr('data-index');
				console.log(id);
				console.log(messages_list)
				console.log(messages_list[id])
				$("#postModalLecture").toggleClass("hidden");
				lg=messages_list[id].language;
				if(lg=="c") {
					lg="c_cpp";
				}
				setSessionMode2(lg);
				editor2.setValue(messages_list[id].text);
				$("#modal_title").html(messages_list[id].title);
				$("#modal_desc").html(messages_list[id].description);
				editor2.clearSelection();
			});
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
