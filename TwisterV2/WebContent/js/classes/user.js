env.user = {}
$(function() {

	env.user.listusers = function(tab, callback) {
		env.getJSONK("listusers", tab, function(d) {
			callback(d);
		})
	}
	env.user.createuser = function(tab, callback) {
		env.getJSON("createuser", tab, function(d) {
			callback(d);

		})
	}
});

(function() {

	if (typeof env.user.Me !== "undefined") {
		return;
	}

	env.user.Me = function(m) {
		obj = m;
		this.id = obj["id"];
		this.prenom = obj["prenom"];
		this.nom = obj["nom"];
		this.login = obj["login"];
	}

}());

(function() {
	if (typeof env.userMes !== "undefined") {
		return;
	}
	var m = [];
	env.user.Mes = function(m) {
		this.m = m["response"]["users"];
		this.users = [];
		if (typeof (this.m) == "string") {
			this.m = [];
		} else {
			console.log(typeof (this.m));
			if (typeof (this.m) == "object" && !$.isArray(this.m)) {
				this.m.pos = 0;
				this.users.push(new env.user.Me(this.m));
			} else {
				// if (typeof(this.m)=="object")
				for (var i = 0; i < this.m.length; i++) {
					this.m[i].pos = i;
					this.users.unshift(new env.user.Me(this.m[i]));
				}
			}
		}
	}
	env.user.Mes.prototype.get = function(d){
		return this.users[d];
	}
}(jQuery));