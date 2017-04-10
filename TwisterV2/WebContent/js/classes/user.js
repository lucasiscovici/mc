env.user = {}
$(function(){

	env.user.listusers = function(tab,callback){
		tab["key"]=env.getKey();
		$.getJSON("listusers",tab, function(d){	
			callback(d);
		})
	}
	env.user.createuser = function(tab,callback){
		$.getJSON("createuser",tab, function(d){
			callback(d);

		})
	}
});





(function() {

    if (typeof Me !== "undefined") {
    return;
  	}

	this.Me = function(m) {
		obj = m;
    this.id=obj["id"];
    this.prenom=obj["prenom"];
    this.nom = obj["nom"];
    this.login = obj["login"];
     }


  }());

(function() {
    if (typeof Mes !== "undefined") {
    return;
  	}
 var m = [];
	this.Mes = function(m) {
    this.m=m["response"]["users"];
    this.users=[];
    if (typeof(this.m)=="string"){
    	this.m = [];
    }else{
    	console.log(typeof(this.m));
    if (typeof(this.m)=="object" && !$.isArray(this.m)){
    	this.m.pos=0;
    	this.users.push(new Me(this.m));
    }else{
   //if (typeof(this.m)=="object")
    for (var i = 0; i < this.m.length; i++) {
    	this.m[i].pos=i;
  	  this.users.unshift(new Me(this.m[i]));
    }
    }
    } 
  }

  }(jQuery));