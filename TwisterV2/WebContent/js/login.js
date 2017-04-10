
	function conexion(login,password,callback){
		env.session.login({
			login:login,
			password:password
		}, function(d){
			console.log(d);
			callback(d);
		});
	}
	function create(login,password,email,callback) {
		env.user.createuser({
				login:login,
				password:password,
				email:email
			}, function(d){
				console.log(d);
							callback(d);

			})
}
				
	