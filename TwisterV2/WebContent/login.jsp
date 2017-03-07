<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
.terminal .terminal-output div span {
	
	float: left !important; 
}
</style>
<link href="https://fonts.googleapis.com/css?family=Caveat+Brush" rel="stylesheet">
<title>Twister</title>
</head>
<body style='background-color:darkred;'>
<div style='position:relative;text-align:center;top:50%;transform:translateY(50%);margin:0;color:white;font-size:130px;font-family:"Caveat Brush", cursive;'>Twister
<span><a id='t' href='http://luluperet.github.io/doc' target='_blank' style='transition: all 2s;' ><img src='https://upload.wikimedia.org/wikipedia/fr/c/c8/Twitter_Bird.svg' width="120px" /></a></span>
<div id="i"></div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/jquery.terminal/1.0.10/css/jquery.terminal.min.css" rel="stylesheet"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.terminal/1.0.10/js/jquery.terminal.min.js"></script>

<script type="text/javascript">
$(function(){
jQuery(function($, undefined) {
    text = "Connexion (c) ou Inscription (i)";
    etape = 1;
    mode="o";
    c=[];
    $('#i').terminal(function(command) {
        if (command !== '') {
            if (etape==1) {
                if (command=="c") {
                    this.set_prompt("Login: ");
                    //c.push(command);
                    mode="c";
                    etape+=1;  
                }else if (command=="i") {
                    this.set_prompt("Login: ");
                    mode="i";

                    etape+=1;
                }else{
                    this.set_prompt(text+": ");
                }

				return;
            }
            if (mode=="c") {
                if (etape==2) {
                    this.set_prompt("Password: ");
                    c.push(command);
                }else if (etape==3) {
                   // CONNEXION AJAX 
                    c.push(command);
                   $this = this;
                   conexion(c[0],c[1],function(d){
                       	if ("code" in d) {
                       		
                       		$this.set_prompt("Password: ");
                       		$this.echo("error");
                       		etape-=1;
                       	}else{
                       		$this.echo("Connexion en cours...");

                       		$this.set_prompt();
                       		window.location.href=window.location.href;
					 window.location.reload();
                       	}
                       });


                }
                  etape+=1;
            }else if (mode=="i") {
				if (etape==2) {
                    this.set_prompt("Password: ");
                    c.push(command);
                    etape+=1;
                }else if (etape==3){
                	  this.set_prompt("Email: ");
                    c.push(command);
                    etape+=1;
                }
                else if (etape==4) {
                	
                	 c.push(command);
                   // CONNEXION AJAX 
                   create(c[0],c[1],c[2],function(d){
                       	if("response" in d){
					$("body").append("cOmpte créé !!! Connexion en Cours...");
					setTimeout(function(){
						conexion(c[0],c[1],function(d){
							if("response" in d){
								window.location.href=window.location.href;
								 window.location.reload();
							}else{
								alert(d.description);
							}
						});
					}, 2000);
					
				}else{
					alert(d.description);
				}
                       });

                }
 
            }
            // try {
            //     // var result = window.eval(command);
            //     // if (result !== undefined) {
            //     //     this.echo(new String(result));
            //     // }
            // } catch(e) {
            //     this.error(new String(e));
            // }
        } else {
           this.echo('');
        }
    }, {
        greetings: 'Twister',
        name: 'js_demo',
        height: 200,
        prompt: text+": "
    });
});
	function conexion(login,password,callback){
		$.getJSON("login",{
			login:login,
			password:password
		}, function(d){
			console.log(d);
			callback(d);
		});
	}
	function create(login,password,email,callback) {
		// body...
	
	
	$.getJSON("createuser",{
				login:$("#login").val(),
				password:$("#password").val(),
				email:$("#email").val()
			}, function(d){
				console.log(d);
							callback(d);

			})
}
				
		
	
		

});
</script>
</body>
</html>