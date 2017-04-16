
$(function(){
	

$("#post").click(function(){
	if($("#form_lg").val()=="rien"){
		return;
	}
	env.post.addpost({
		text: _.escape(editor.getValue()),
		key: _.escape($("#key").val()),lg:_.escape($("#form_lg").val()),
		title: _.escape($("#form_title").val()),
		description: _.escape($("#form_description").val())
		},
		function(f){
			env.func_tools.reload(f);
			}
		);
	
});
});