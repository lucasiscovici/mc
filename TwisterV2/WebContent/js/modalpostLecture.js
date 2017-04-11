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
	
	function isSelected() {
		minus();
		env.like.removelike({
			id_post : env.messages_list[idPost].id
		}, function(d) {
			console.log(d);
			if (!("response" in d)) {
				maxus();
			}
		});

	}
	function isNotSelected() {
		maxus();
		env.like.addlike({
			id_post : messages_list[idPost].id
		}, function(d) {
			if (!("response" in d)) {
				minus();
			}
		})
	}

	$("#likes").click(function() {

		selected = $(this).attr("selectedf");
		idPost = $("#postModalLecture").attr("data-index");

		env.post.reloadPost(idPost, function(f) {
			console.log(f);
		})

		if (selected != null && selected == "true") {

			isSelected();

		} else {

			isNotSelected();

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