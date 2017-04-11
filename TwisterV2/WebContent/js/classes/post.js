env.post = {}
$(function(){
	env.post.listposts = function(tab,callback){
		env.getJSONK("listposts",tab,function(d){
			callback(d);
		})
	}
	env.post.addpost = function(tab,callback){
		env.tabKey(tab);
		$.post("addpost",tab,function(d){
			callback(d);
		})
	}
	env.post.reloadPost = function(pos,call){
		p=env.messages_list[pos];
		env.post.listposts({ id:p.id },function(d){
			a=new Messages(d);
			console.log(a);
			if(a.mess[0]==null){
				console.log("mess supprimer");
			}else{
			if(p.date > a.mess[0].date){
				console.log("date vieille");
				call(false,null);
			}else{
				console.log("date jeune");
				call(true,a.mess[0]);
			}
			}
		});
	}
	
});


(function() {
    if (typeof Message !== "undefined") {
    return;
  	}

	this.Message = function(m) {
		obj = m;
    this.date=obj["date"];
    this.pos=obj["pos"];
    this.id = obj["id"];
    this.login = obj["login"];
    this.id_user = obj["id_user"];
    this.text = obj["text"];
    this.title = obj["title"];
    this.description = obj["description"];
    this.language= obj["lg"];
    this.nb_like =obj["nb_likes"];
    this.nb_comments = obj["nb_coms"];
  }
	Message.prototype.HTML = function() {
		 return getHTML(this)
		
  	}
  function getHTML(that) {
	  html ="";
	  arr={};
	  arr.text = that.text;
	    arr.name = that.login;
	    arr.desc = that.description;
	    arr.title = that.title;
	    arr.lg = that.language;
	    arr.index=that.pos;
	    arr.nb_likes=that.nb_like;
	    arr.nb_coms=that.nb_comments;
	    arr.cl = arr.desc == null ? "hidden" : "" ;
	  return template(arr,"message");
  }
  
  Message.prototype.getLikes = function(env,pos,callback){
 getLikes(this,env,pos,function(g){
	 callback(g);
 });
  }
  function getLikes(that,env,pos,callback){
	  $.getJSON("listlikes",{
		  key:$.cookie("key"),
		  id_post:that.id
	  },function(d){
		  console.log(d);
		  a=new Likes(d);
		  that.likes=a.likes;
		  env.messages_list[pos].likes=a.likes;
		  callback(a.likes);
	  });

  }

  }());

(function() {
    if (typeof Messages !== "undefined") {
    return;
  	}
 var m = [];
	this.Messages = function(m) {
    this.m=m["response"]["messages"];
    this.mess={};
    if (typeof(this.m)=="string"){
    	this.m = [];
    }else{
    	console.log(typeof(this.m));
    if (typeof(this.m)=="object" && !$.isArray(this.m)){
    	this.m.pos=0;
    	this.mess[0]=new Message(this.m);
    }else{
   //if (typeof(this.m)=="object")
    for (var i = 0; i < this.m.length; i++) {
    	
    l=this.m.length-i-1;
    	this.m[i].pos=l;
  	  this.mess[l]=new Message(this.m[i]);
    }
    }
    } 
  }
	Messages.prototype.HTML = function() {
		return getHTML(this);
  	}
  function getHTML(that) {
      html = "";
      for (i in that.mess) {
    	  html+=(that.mess[i].HTML());
      }
      return html;
  }

  }(jQuery));