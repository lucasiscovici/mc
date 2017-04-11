env.like = {}
$(function(){
	env.like.removelike = function(tab,callback){
		env.getJSONK("removelike",tab,function(d){
			callback(d);
		})
	}
	env.like.addlike = function(tab,callback){
		env.getJSONK("addlike",tab,function(d){
			callback(d);
		})
}
});



(function() {

    if (typeof Like !== "undefined") {
    return;
  	}

	this.Like = function(m) {
		obj = m;
    this.id=obj["id"];
    this.id_user=obj["id_user"];
     }


  }());

(function() {
    if (typeof Likes !== "undefined") {
    return;
  	}
 var m = [];
	this.Likes = function(m) {
    this.m=m["response"]["likes"];
    this.likes=[];
    if (typeof(this.m)=="string"){
    	this.m = [];
    }else{
    	console.log(typeof(this.m));
    if (typeof(this.m)=="object" && !$.isArray(this.m)){
    	this.m.pos=0;
    	this.likes.push(new Like(this.m));
    }else{
   //if (typeof(this.m)=="object")
    for (var i = 0; i < this.m.length; i++) {
    	this.m[i].pos=i;
  	  this.likes.unshift(new Like(this.m[i]));
    }
    }
    } 
  }

  }(jQuery));