env.session={};
$(function(){
	env.session.login = function(tab,callback){
		env.getJSON("login",tab, function(d){
			callback(d);
		});
	}
	env.session.logout = function(tab,callback){
		env.getJSONK("logout",
				tab,
				function(f){
					callback(f);
					}
				);
	}
	
});
