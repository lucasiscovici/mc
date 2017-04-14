env.post = {}
$(function(){
	env.post.listposts = function(tab,callback){
		env.getJSONK("listposts",tab,function(d){
			callback(d);
		})
	}
	env.post.removepost = function(tab,callback){
		env.getJSONK("removepost",tab,function(d){
			callback(d);
		})
	}
	env.post.addpost = function(tab,callback){
		env.tabKey(tab);
		$.post("addpost",tab,function(d){
			callback(d);
		})
	}
	
	env.post.updatepost = function(tab,callback){
		env.tabKey(tab);
		$.post("updatepost",tab,function(d){
			callback(d);
		})
	}
	
	env.post.reloadPostWId = function(pid,call){
		p = env.messages_list.get(pid);
		env.post.listposts({ id:pid },function(d){
			a=new Messages(d);
			console.log(a);
			if(a.get(pid)==null){
				console.log("mess supprimer");
				call(false,null,true);
			}else{
			if(p.date > a.get(pid).date){
				console.log("date vieille");
				call(true,a.get(pid),false);
			}else{
				console.log("date jeune");
				call(false,null,false);
			}
			}
		});
	}

	env.post.reloadPost = function(pos,call){
		p=env.messages_list.get(pos);
		env.post.reloadPostWId(p.id,function(reload,mess,suppr){
			call(reload,mess,suppr);
		});
	}
	
});


(function() {
    if (typeof Message !== "undefined") {
    return;
  	}

	this.Message = function(m) {
		obj = m;
    this.date=moment(parseInt(obj["date"])).locale("fr");
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
	Message.prototype.reload = function() {
		env.post.reloadPostWId(this.id,function(reload,mess,suppr){
			console.log(reload,mess,suppr);
			if(suppr || reload){
				delete env.messages_list.mess[this.id]
			}
			if(reload && mess != null){
				env.messages_list.mess[this.id] = mess;
			}
			if(suppr){
				$(".grid-item[data-index='"+(this.id)+"']").remove();
			}
			if(reload){
				v=$(".grid-item[data-index='"+(this.id)+"']");
				v.find(".mess_title").html(mess.title);
				v.find(".mess_desc").html(mess.description);
				v.find(".mess_lg").html(mess.language);
				v.find(".nb_likes_grid_item").html(mess.nb_like);
				v.find(".nb_comments_grid_item").html(mess.nb_comments);
			}
		});
	}
  function getHTML(that) {
	  html ="";
	  arr= {};
	  arr.text = that.text;
	    arr.name = that.login;
	    arr.desc = that.description;
	    arr.title = that.title;
	    arr.lg = that.language;
	    arr.index = that.pos;
	    arr.nb_likes = that.nb_like;
	    arr.nb_coms = that.nb_comments;
	    arr.cl = arr.desc == null ? "hidden" : "" ;
	    arr.date = that.date.format("dddd DD MMM YYYY \\à HH\\h\\\\mm");
	    arr.baseImg = env.var.bases.baseImg;
	  return template(arr,"message");
  }
  
  Message.prototype.getLikes = function(env,pos,callback){
 getLikes(this,env,pos,function(g){
	 callback(g);
 });
  }
  function getLikes(that,env,pos,callback){

	  env.like.listlikes({
		  id_post:that.id
	  },function(d){
		  console.log(d);
		  a=new Likes(d);
		  that.likes=a.likes;
		  env.messages_list.get(pos).likes=a.likes;
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
    	a = new Message(this.m);
    	a.pos = a.id;
    	this.mess[a.id]=a;
    }else{
   //if (typeof(this.m)=="object")
    for (var i = 0; i < this.m.length; i++) {
    	
    l=this.m.length-i-1;
  	  a=new Message(this.m[i]);
  	  a.pos = a.id;
  	this.mess[a.id]=a;
    }
    }
    } 
  }
	Messages.prototype.get = function(f) {
		return this.mess[f];
	}

	Messages.prototype.HTML = function() {
		return getHTML(this);
  	}
  function getHTML(that) {
      html = "";
      fs=[];
      for (i in that.mess) {
    	  sf=that.mess[i].HTML();
    	  fs.push(sf);
    	  
      }
      return fs.join("");
  }

  }(jQuery));