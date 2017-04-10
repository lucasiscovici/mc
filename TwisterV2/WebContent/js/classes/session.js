env.session={};
$(function(){
	env.session.login = function(tab,callback){
		$.getJSON("login",tab, function(d){
			callback(d);
		});
	}
	env.session.logout = function(tab,callback){
		tab["key"]=env.getKey();
		$.get("logout",
				tab,
				function(f){
					callback(f);
					}
				);
	}
	
});
