$(function() {

	function minus() {
		sd = $("#likep").html();
		df = parseInt(sd, 10);

		$("#likes").css("background-color", "black");
		$("#likep").html(df - 1);
		$("#likes").attr("selectedf", "false");
	}
	function maxus() {
		sd = $("#likep").html();
		df = parseInt(sd, 10);
		$("#likes").attr("selectedf", "true");
		$("#likes").css("background-color", "blue");
		$("#likep").html(df + 1);
	}
	
	function isSelected(c) {
		minus();
		env.like.removelike({
			id_post : env.messages_list[idPost].id
		}, function(d) {
			console.log(d);
			if (!("response" in d)) {
				maxus();
				
			}
			c();
		});

	}
	function isNotSelected(c) {
		maxus();
		env.like.addlike({
			id_post : messages_list[idPost].id
		}, function(d) {
			if (!("response" in d)) {
				minus();
				
			}
			c();
		})
	}
	k=true;
	$("#likes").click(function() {
		idPost = $("#postModalLecture").attr("data-index");

		env.post.reloadPost(idPost, function(f) {
			console.log(f);
		})
		if(k==true){
		k=false;
		selected = $(this).attr("selectedf");

		

		if (selected != null && selected == "true") {

			isSelected(function(){
				k=true;
			});
			

		} else {

			isNotSelected(function(){
				k=true;
			});
			

		}
		}
	});

	$("#commentaire").click(
			function() {
				if ($('#newCommentaire').val().length > 0) {
					$.getJSON("addcomment", {
						key : $.cookie("key"),
						text : $('#newCommentaire').val(),
						id_post : env.messages_list[$("#postModalLecture")
								.attr("data-index")].id
					}, function(d) {
						console.log(d);
						if ("response" in d) {
							sd = $("#comsp").val();
							df = parseInt(sd, 10);
							$("#comsp").val(df + 1);
							$('#newCommentaire').val("");
						}
					})
				}
			});
});