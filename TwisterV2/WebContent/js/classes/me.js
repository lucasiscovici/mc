env.me = {}
$(function() {

	env.me.listusers = function(tab, callback) {
		env.getJSONK("listusers", tab, function(d) {
			callback(d);
		})
	}
	env.me.createuser = function(tab, callback) {
		env.getJSON("createuser", tab, function(d) {
			callback(d);

		})
	}
});

(function() {

	if (typeof env.me.Me !== "undefined") {
		return;
	}

	env.me.Me = function(m) {
		obj = m;
		this.id = obj["id"];
		this.prenom = obj["prenom"];
		this.nom = obj["nom"];
		this.login = obj["login"];
	}
	
	env.me.Me.prototype.removeFriend=function(idFriend, callback) {
		env.friend.removefriend({id_friend:idFriend}, function(d) {
			callback(d);
		});
	}

}());

(function() {
	if (typeof env.me.Mes !== "undefined") {
		return;
	}
	var m = [];
	env.me.Mes = function(m) {
		this.m = m["response"]["users"];
		this.users = [];
		if (typeof (this.m) == "string") {
			this.m = [];
		} else {
			console.log(typeof (this.m));
			if (typeof (this.m) == "object" && !$.isArray(this.m)) {
				this.m.pos = 0;
				this.users.push(new env.me.Me(this.m));
			} else {
				// if (typeof(this.m)=="object")
				for (var i = 0; i < this.m.length; i++) {
					this.m[i].pos = i;
					this.users.unshift(new env.me.Me(this.m[i]));
				}
			}
		}
	}
	env.me.Mes.prototype.get = function(d){
		return this.users[d];
	}
}(jQuery));