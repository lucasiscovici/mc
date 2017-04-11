$(function(){

$("#likes").click(function(){

	selected = $(this).attr("selectedf");
	idPost = $("#postModalLecture").attr("data-index");
	env.post.reloadPost(idPost,function(f){
		console.log(f);
	})
	if(selected != null && selected=="true") {
		
		env.like.removelike({id_post: env.messages_list[idPost].id},function(d){
			console.log(d);
			if ("response" in d) {

			sd = $("#likep").html();
			df=parseInt(sd,10);
			
			$("#likes").css("background-color","black");
			$("#likep").html(df-1);
			$("#likes").attr("selectedf","false");
			}
		});
		
	}else{
		$.getJSON("addlike",{
			key:env.getKey(),
			id_post:env.messages_list[idPost].id
		},function(d){
			console.log(d);

			if ("response" in d) {
				sd = $("#likep").html();
				df=parseInt(sd,10);
				$("#likes").attr("selectedf","true");
				$("#likes").css("background-color","blue");
				$("#likep").html(df+1);
				
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