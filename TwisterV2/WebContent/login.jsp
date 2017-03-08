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
<body style='background-color:black; margin: 0; height: 100%;'>
<div style='height: 100%;' align="center">
<div id="i" style="padding: 0; width: 100%; height: 100%;">
<img width="300px" src="http://li328.lip6.fr:8280/TwisterV2/img/logo.png" />
</div>
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
                    this.set_prompt("[[;GREEN;]Login > ]");
                    mode="c";
                    etape+=1;  
                }else if (command=="i") {
                    this.set_prompt("[[;GREEN;]Login > ]");
                    mode="i";
                    etape+=1;
                }else{
                    this.set_prompt(text+": ");
                }

                return;
            }
            if (mode=="c") {
                if (etape==2) {
                    this.set_prompt("[[;GREEN;]Password > ]");
                    c.push(command);
                }else if (etape==3) {
                   // CONNEXION AJAX 
                   c.push(command);
                   conexion(c[0],c[1],function(d){
                       	if ("code" in d) {
                       		alert("error");
                       		etape=1
                       		c=[];
                       	}else{
                       	$("body").append("Connecté !!! Connexion en Cours...");
                       		
                       		window.location.href=window.location.href;
					 		window.location.reload();
                       	}
                       });


                }
                  etape+=1;
            }else if (mode=="i") {
				if (etape==2) {
                    this.set_prompt("[[;GREEN;]Password > ]");
                    c.push(command);
                }else if (etape==3){
                	  this.set_prompt("[[;GREEN;]Email > ]");
                    c.push(command);
                }
                else if (etape==4) {
                   // CONNEXION AJAX 
                   c.push(command);
                   create(c[0],c[1],c[2],function(d){
                       	if("response" in d){
					$("body").append("Compte créé !!! Connexion en Cours...");
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
  etape+=1;
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