$(function(){

$("#likes").click(function(){
	selected = $(this).attr("selectedf");
	
	if(selected != null && selected=="true") {
		idPost = $("#postModalLecture").attr("data-index");
		
		env.like.removelike({id_post: env.messages_list[idPost].id},function(d){
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
	if($('#newCommentaire').val().length > 0){
	$.getJSON("addcomment",{
		key:$.cookie("key"),
		text:$('#newCommentaire').val(),
		id_post:env.messages_list[$("#postModalLecture").attr("data-index")].id
	},function(d){
		console.log(d);
		if ("response" in d) {
			sd = $("#comsp").val();
			df=parseInt(sd,10);
			$("#comsp").val(df+1);
			$('#newCommentaire').val("");
		}
	})
	}
});
});