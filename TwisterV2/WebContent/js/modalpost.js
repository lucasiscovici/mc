
$(function(){
	

$("#post").click(function(){
	if($("#form_lg").val()=="rien"){
		return;
	}
	env.post.addpost({
		text: _.escape(editor.getValue()),
		key: $("#key").val(),lg:$("#form_lg").val(),
		title: $("#form_title").val(),
		description: $("#form_description").val()
		},
		function(f){
			env.func_tools.reload(f);
			}
		);
	
});
});