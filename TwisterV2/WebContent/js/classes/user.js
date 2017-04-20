env.user = {}
$(function(){
	env.user.listusers = function(tab,callback){
		env.getJSONK("listusers",tab,function(d){
			console.log(d);
			callback(d);
		})
	}
	env.user.removeuser = function(tab,callback){
		env.getJSONK("removeuser",tab,function(d){
			callback(d);
		})
	}
	env.user.adduser = function(tab,callback){
		env.tabKey(tab);
		$.post("adduser",tab,function(d){
			callback(d);
		})
	}
	
	env.user.updateuser = function(tab,callback){
		env.tabKey(tab);
		$.post("updateuser",tab,function(d){
			console.log(d);
			callback(d);
		}, 'json' )
	}
	
	env.user.reloadUserWId = function(pid,call){
		p = env.users_list.get(pid);
		env.user.listusers({ id:pid },function(d){
			a=new Users(d);
			if(a.get(pid)==null){
				call(false,null,true);
			}
		});
	}

	env.user.reloadUser = function(pos,call){
		p=env.users_list.get(pos);
		env.user.reloadPostWId(p.id,function(reload,mess,suppr){
			call(reload,mess,suppr);
		});
	}
	
});

(function() {
    if (typeof env.user.User !== "undefined") {
    	return;
  	}

    env.user.User = function(m) {
		obj = m;
		this.login = obj["login"];
		this.id = obj["id"];
	}
	
    env.user.User.prototype.reload = function() {
		env.user.reloadUserWId(this.id,function(reload,mess,suppr){
			console.log(reload,mess,suppr);
			if(suppr || reload){
				delete env.users_list.mess[this.id]
			}
			if(reload && mess != null){
				env.users_list.mess[this.id] = mess;
			}
			if(suppr){
				$(".grid-item[data-index='"+(this.id)+"']").remove();
			}
			if(reload){
				v=$(".grid-item[data-index='"+(this.id)+"']");
			}
		});
	}
	
}());

(function() {
	if (typeof env.user.Users !== "undefined") {
    	return;
  	}
	var m = [];
	env.user.Users = function(m) {
		this.m=m["response"]["users"];
		this.mess={};
		if (typeof(this.m)=="string"){
			this.m = [];
		}else{
			if (typeof(this.m)=="object" && !$.isArray(this.m)){
				a = new env.user.User(this.m);
				console.log(a);
				a.pos = a.id;
				this.mess[a.id]=a;
			}else{
   //if (typeof(this.m)=="object")
				for (var i = 0; i < this.m.length; i++) {
    	
					l=this.m.length-i-1;
					a=new env.user.User(this.m[i]);
					a.pos = a.id;
					this.mess[a.id]=a;
				}
			}
		} 
	}
	
	env.user.Users.prototype.get = function(f) {
		return this.mess[f];
	}

}(jQuery));