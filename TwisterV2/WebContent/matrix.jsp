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

<div class="hidden" data-template="message">
	<c:import url="vue/message.html" />
</div>


<c:import url="modalpost.jsp"/>
<c:import url="modalpostLecture.jsp"/>

<c:import url="importjs.jsp"/>


<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js"></script>
<script type="text/javascript">
env={};
me=null;
messages_list=null;
env.messages_list=messages_list;
env.me=me;
$(function(){
	$.getJSON("listusers",{
		key:$.cookie("key"),
	}, function(d){
		console.log(d);
		a=new Mes(d);
		me=a.users;
		env.me=me;
	})
o=0;
fd="${que}";
$("#likes").click(function(){
	selected= $(this).attr("selectedf");
	if(selected != null && selected=="true") {
		$.getJSON("removelike",{
			key:$.cookie("key"),
			id_post:env.messages_list[$("#postModalLecture").attr("data-index")].id
		},function(d){
			console.log(d);
			if ("response" in d) {

			sd = $("#likep").val();
			df=parseInt(sd,10);
			
			$("#likes").css("background-color","black");
			$("#likep").val(df-1);
			}
		});
	}else{
		$.getJSON("addlike",{
			key:$.cookie("key"),
			id_post:env.messages_list[$("#postModalLecture").attr("data-index")].id
		},function(d){
			console.log(d);

			if ("response" in d) {
				sd = $("#likep").val();
				df=parseInt(sd,10);
			
				$("#likes").css("background-color","blue");
				$("#likep").val(df+1);
				
			}
		})
	}
});

































$("#commentaire").click(function(){
	$.getJSON("addcomment",{
		key:$.cookie("key"),
		text:"rrr",
		id_post:env.messages_list[$("#postModalLecture").attr("data-index")].id
	},function(d){
		console.log(d);
		if ("response" in d) {
			sd = $("#comsp").val();
			df=parseInt(sd,10);
			$("#commentaire").css("background-color","blue");
			$("#comsp").val(df+1);
		}
	})
});





























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
		env.messages_list=messages_list;
    	   $items=a.HTML()
		$(".grid").append($items).masonry( 'appended', $items ).masonry();
		setTimeout(function(){
			$('pre code').each(function(i, e) {hljs.highlightBlock(e);}); 
			
			$(".title_mess").click(function(){
				id=$(this).attr('data-index');
				console.log(id);
				console.log(messages_list)
				console.log(messages_list[id])
				$("#postModalLecture").attr("data-index",id);
				$("#postModalLecture").toggleClass("hidden");
				lg=messages_list[id].language;
				if(lg=="c") {
					lg="c_cpp";
				}
				setSessionMode2(lg);
				editor2.setValue(_.unescape(messages_list[id].text),-1);
				$("#modal_desc").html(messages_list[id].description);
				$("#modal_title").html(messages_list[id].title);
				$("#modal_user").html(messages_list[id].login);
				$("#likes").replace_motif("nb_likes",messages_list[id].nb_like);
				$("#commentaire").replace_motif("nb_coms",messages_list[id].nb_comments);
				$("#likes").attr('selectedf',"false");
				env.messages_list[id].getLikes(env,id,function(j){
					if ( env.me[0].id in j) {
						$("#likes").css("background-color","blue");
						$("#likes").attr('selectedf',"true");
					}
				});
				
				
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
