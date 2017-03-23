
(function() {
	function template(arr,name){
    html = $("[data-template='"+name+"']").html();
    for(key in arr){
    	var find = "{{"+key+"}}";
    	var re = new RegExp(find, 'g');
       html = html.replace(re,arr[key]);
    }
    return html;
	}
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
	  return template(arr,"message");
  }

  }());

(function() {
    if (typeof Messages !== "undefined") {
    return;
  	}
 var m = [];
	this.Messages = function(m) {
    this.m=m["response"]["messages"];
    this.mess=[];
    if (typeof(this.m)=="string"){
    	this.m = [];
    }else{
    	console.log(typeof(this.m));
    if (typeof(this.m)=="object" && !$.isArray(this.m)){
    	this.m.pos=0;
    	this.mess.push(new Message(this.m));
    }else{
   //if (typeof(this.m)=="object")
    for (var i = 0; i < this.m.length; i++) {
    	this.m[i].pos=i;
  	  this.mess.push(new Message(this.m[i]));
    }
    }
    } 
  }
	Messages.prototype.HTML = function() {
		return getHTML(this);
  	}
  function getHTML(that) {
      html = "";
      for (var i = 0; i < that.mess.length; i++) {
    	  html+=(that.mess[i].HTML());
      }
      return html;
  }

  }(jQuery));

