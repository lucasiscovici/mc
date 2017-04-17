
$(function(){
	

$("#post").click(function(){
	if ($("#postModal").attr("type")=="0") {
		
	
	if($("#form_lg").val()=="rien"){
		return;
	}
	env.post.addpost({
		text: _.escape(editor.getValue()),
		key: _.escape($("#key").val()),
		lg:_.escape($("#form_lg").html()),
		title: _.escape($("#form_title").val()),
		description: _.escape($("#form_description").val())
		},
		function(f){
			env.func_tools.reload(f);
			}
		);
	}else{
		env.post.updatepost({
			text: _.escape(editor.getValue()),
			key: _.escape($("#key").val()),
			lg:_.escape($("#form_lg").html()),
			title: _.escape($("#form_title").val()),
			description: _.escape($("#form_description").val()),
			id:_.escape($("#postModal").attr("idp"))
			},
			function(f){
					console.log(f);
					if("response" in f){
						$("#postModal").toggleClass("hidden");
						env.func_tools.reload(f);
					}
				}
			);
	}
});
});