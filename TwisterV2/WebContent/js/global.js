env = {};
env.auth={};
env.var = {};
env.var.bases= bases;
env.getKey = function(){
	return $("#key").val();
}
env.tabKey = function(tab){
	tab["key"]= env.getKey();
}

env.getJSON = function(url,tab,callback){
	$.getJSON(url,tab,function(d){
		callback(d);
})
}
env.getJSONK = function(url,tab,callback){
	env.tabKey(tab);
	env.getJSON(url,tab,function(e){
		callback(e);
	});
}
