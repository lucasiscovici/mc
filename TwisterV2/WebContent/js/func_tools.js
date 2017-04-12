env.func_tools = {};
env.func_tools.inObjToArr =  function(obj,mo){
	g=[];
	for(i=0;i<obj.length;i++){
		g.push(obj[i][mo]);
	}
	return g;
}
$(function(){
	
	env.func_tools.inArray = function(t,a) {
	return $.inArray(t,a) != -1
	}
});

(function($){
	$.fn.replace_motif = function(key,d){
    	var e = $(this).html();
    	var df = replace_motif2(key,d,e);
    	$(this).html(df);
    	console.log(df);
	};
	function replace_motif2(key,d,ou){
		var find = "{{"+key+"}}";
    	var re = new RegExp(find, 'g');
    	return ou.replace(re,d);
    	
	}
})(jQuery);

env.func_tools._reload = function(){
	window.location.href=window.location.href;
	window.location.reload();
}
env.func_tools.reload = function(d){
	console.log(d);
	env.func_tools._reload();
}