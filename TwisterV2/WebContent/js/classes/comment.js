env.comment = {}
$(function(){
	env.comment.listcomments = function(tab,callback){
		env.getJSONK("listcomments",tab,function(d){
			callback(d);
		})
	}
	env.comment.removecomment = function(tab,callback){
		env.getJSONK("removecomment",tab,function(d){
			callback(d);
		})
	}
	env.comment.addcomment = function(tab,callback){
		env.tabKey(tab);
		$.post("addcomment",tab,function(d){
			callback(d);
		})
	}
	
	env.comment.updatecomment = function(tab,callback){
		env.tabKey(tab);
		$.post("updatecomment",tab,function(d){
			console.log(d);
			callback(d);
		}, 'json' )
	}
	
	env.comment.reloadCommentWId = function(pid,call){
		p = env.comments_list.get(pid);
		env.comment.listcomments({ id:pid },function(d){
			a=new Comments(d);
			if(a.get(pid)==null){
				call(false,null,true);
			}
		});
	}

	env.comment.reloadComment = function(pos,call){
		p=env.comments_list.get(pos);
		env.comment.reloadPostWId(p.id,function(reload,mess,suppr){
			call(reload,mess,suppr);
		});
	}
	
});

(function() {
    if (typeof Comment !== "undefined") {
    	return;
  	}

	this.Comment = function(m) {
		obj = m;
		this.date=moment(parseInt(obj["date"])).locale("fr");
		this.id_post=obj["id_post"];
		this.id = obj["id"];
		this.id_user = obj["id_user"];
		this.text = obj["text"];
	}
	Comment.prototype.HTML = function() {
		 return getHTML(this)
  	}
	
	Comment.prototype.reload = function() {
		env.comment.reloadCommentWId(this.id,function(reload,mess,suppr){
			console.log(reload,mess,suppr);
			if(suppr || reload){
				delete env.comments_list.mess[this.id]
			}
			if(reload && mess != null){
				env.comments_list.mess[this.id] = mess;
			}
			if(suppr){
				$(".grid-item[data-index='"+(this.id)+"']").remove();
			}
			if(reload){
				v=$(".grid-item[data-index='"+(this.id)+"']");
				v.find(".textComment").html(mess.title);
			}
		});
	}
	
	function getHTML(that) {
		html ="";
		arr= {};
		arr.text = that.text;
	    arr.index = that.id;
	    arr.id_post = that.id_post;
	    arr.date = that.date.format("dddd DD MMM YYYY \\à HH\\h\\\\mm");
	    arr.baseImg = env.var.bases.baseImg;
	    return template(arr,"commentaire");
	}
}());

(function() {
	if (typeof Comments !== "undefined") {
    	return;
  	}
	var m = [];
	this.Comments = function(m) {
		this.m=m["response"]["comments"];
		this.mess={};
		if (typeof(this.m)=="string"){
			this.m = [];
		}else{
			console.log(typeof(this.m));
			if (typeof(this.m)=="object" && !$.isArray(this.m)){
				a = new Comment(this.m);
				a.pos = a.id;
				this.mess[a.id]=a;
			}else{
   //if (typeof(this.m)=="object")
				for (var i = 0; i < this.m.length; i++) {
    	
					l=this.m.length-i-1;
					a=new Comment(this.m[i]);
					a.pos = a.id;
					this.mess[a.id]=a;
				}
			}
		} 
	}
	
	Comments.prototype.get = function(f) {
		return this.mess[f];
	}

	Comments.prototype.HTML = function() {
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