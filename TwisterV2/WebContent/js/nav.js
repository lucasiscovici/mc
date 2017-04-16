
$(function(){

$("#logout").click(function(){
	env.session.logout({},function(f){
		env.func_tools.reload(f);
	})

});
});