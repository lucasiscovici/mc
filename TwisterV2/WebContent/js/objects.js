
(function() {
	function template(arr,name){
    html = $("[data-template='"+name+"']").html();
    for(key in arr){
       html = html.replace("{{"+key+"}}",arr[key]);
    }
    return html;
	}
    if (typeof Message !== "undefined") {
    return;
  	}

	this.Message = function(m) {
		obj = m;
    this.date=obj["date"];
    this.id = obj["id"];
    this.login = obj["login"];
    this.id_user = obj["id_user"];
    this.text = obj["text"];
    //this.language= obj["lg"];
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
    if (typeof(this.m)=="string"){
    	this.m = [];
    }
  }
	Messages.prototype.HTML = function() {
		return getHTML(this);
  	}
  function getHTML(that) {
      html = "";
      for (var i = 0; i < that.m.length; i++) {
    	  html+=(new Message(that.m[i])).HTML();
      }
      return html;
  }

  }(jQuery));

