env.friend = {}
$(function(){
	env.friend.listfriends = function(tab,callback){
		env.getJSONK("listfriends",tab,function(d){
			callback(d);
		})
	}
	env.friend.removefriend = function(tab,callback){
		env.getJSONK("removefriend",tab,function(d){
			callback(d);
		})
	}
	env.friend.addfriend = function(tab,callback){
		env.getJSONK("addfriend",tab,function(d){
			callback(d);
		})
	}
	
	env.friend.reloadFriendWId = function(pid,call){
		p = env.friends_list.get(pid);
		env.friend.listfriends({ id:pid },function(d){
			a=new Friends(d);
			if(a.get(pid)==null){
				call(false,null,true);
			}
		});
	}

	env.friend.reloadFriend = function(pos,call){
		p=env.friends_list.get(pos);
		env.friend.reloadPostWId(p.id,function(reload,mess,suppr){
			call(reload,mess,suppr);
		});
	}
	
});

(function() {
    if (typeof env.friend.Friend !== "undefined") {
    	return;
  	}

    env.friend.Friend = function(m) {
		obj = m;
		this.date=moment(parseInt(obj["since"])).locale("fr");
		this.id_friend=obj["to"];
		this.id = obj["id"];
		this.id_user = obj["from"];
	}
	
    env.friend.Friend.prototype.reload = function() {
		env.friend.reloadFriendWId(this.id,function(reload,mess,suppr){
			console.log(reload,mess,suppr);
			if(suppr || reload){
				delete env.friends_list.mess[this.id]
			}
			if(reload && mess != null){
				env.friends_list.mess[this.id] = mess;
			}
			if(suppr){
				$(".grid-item[data-index='"+(this.id)+"']").remove();
			}
			if(reload){
				v=$(".grid-item[data-index='"+(this.id)+"']");
				v.find(".Friend").html(mess.title);
			}
		});
	}
	
}());

(function() {
	if (typeof env.friend.Friends !== "undefined") {
    	return;
  	}
	var m = [];
	env.friend.Friends = function(m) {
		this.m=m["response"]["friends"];
		this.mess={};
		if (typeof(this.m)=="string"){
			this.m = [];
		}else{
			if (typeof(this.m)=="object" && !$.isArray(this.m)){
				a = new env.friend.Friend(this.m);
				console.log(a);
				a.pos = a.id;
				this.mess[a.id]=a;
			}else{
   //if (typeof(this.m)=="object")
				for (var i = 0; i < this.m.length; i++) {
    	
					l=this.m.length-i-1;
					a=new env.friend.Friend(this.m[i]);
					a.pos = a.id;
					this.mess[a.id]=a;
				}
			}
		} 
	}
	
	env.friend.Friends.prototype.get = function(f) {
		return this.mess[f];
	}
	
}(jQuery));