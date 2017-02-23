<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
#t:hover {
filter: grayscale(100%);
}
input:not(.sub) {
    background-color: transparent;
    border: 1px solid white;
    width: 300px;
    height: 40px;
    font-size: 22px;
    color:white;
    text-align: center;
    margin-bottom:5px;
 }
 ::-webkit-input-placeholder {
   color: white;
}

:-moz-placeholder { /* Firefox 18- */
   color: white;  
}

::-moz-placeholder {  /* Firefox 19+ */
   color: white;  
}

:-ms-input-placeholder {  
   color: white;  
}
</style>
<link href="https://fonts.googleapis.com/css?family=Caveat+Brush" rel="stylesheet">
<title>Twister</title>
</head>
<body style='background-color:darkred;'>
<p style='position:relative;text-align:center;top:33vh;margin:0;color:white;font-size:130px;font-family:"Caveat Brush", cursive;'>Twister
<span><a id='t' href='http://luluperet.github.io/doc' target='_blank' style='transition: all 2s;' ><img src='https://upload.wikimedia.org/wikipedia/fr/c/c8/Twitter_Bird.svg' width="120px" /></a></span></p>
<div style="position:relative;margin-top:300px;text-align:center;">
<form action="login_form" id="form_login" method="GET" >
<div>
<input type="text" name="login" id="login" placeholder="Login..."/>
</div>
<div>
<input type="password" id="password" name="password" placeholder="mot de passe"/>
</div>
<div>
<input type="submit" id="submit" class="sub" name="login" value="Login"/>
<button id="create_user">Créer un compte</button>

</div>

</form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#form_login").submit(function(e){
		e.preventDefault();
		Login();
	})
	function Login(){
		submit = $("#submit");
		if(submit.attr("name")=="login"){
			$.getJSON("login",{
				login:$("#login").val(),
				password:$("#password").val()
			}, function(d){
				console.log(d);
				if("response" in d){
				 window.location.href=window.location.href ;
					
				}else{
					alert("pb mdp");
				}
			});
		}else if(submit.attr("name")=="create"){
			$.getJSON("createuser",{
				login:$("#login").val(),
				password:$("#password").val(),
				email:$("#email").val()
			}, function(d){
				console.log(d);
		
			});
		}
	}
		
	$("#create_user").click(function(){
		$("#submit").before("<div><input type='password' name='mdp2' placeholder='confirmation du mot de passe'/></div>");

		$("#submit").before("<div><input type='text' name='email' placeholder='email...'/></div>");
		$(this).remove();
		$("#submit").attr("name","create").val("Créer son compte");
	});
});
</script>
</body>
</html>