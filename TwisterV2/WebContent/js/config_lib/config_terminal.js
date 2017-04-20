$(function(){
	
    text = "Connexion (c) ou Inscription (i)";
    etape = 1;
    mode="o";
    c=[];
  
    g = $('#i').terminal(function(command) {
    	console.log("lala "+command+" "+etape);
        if (command !== '') {
        	 this.set_mask(false);
        	
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
                    this.set_prompt("[[;GREEN;]"+text+": ]");
                }

                return;
            }
            if (mode=="c") {
                if (etape==2) {
                    this.set_prompt("[[;GREEN;]Password > ]");
                    c.push(command);
                    this.set_mask(true);
                }else if (etape==3) {
                   // CONNEXION AJAX 
                   c.push(command);
                   this.pause(false);
                   $this=this;
                   conexion(c[0],c[1],function(d){
                       	if ("code" in d) {
                       		$this.echo("[[;RED;] Erreur, identifiant non valide ...]")
                       	 $this.resume();
                       	etape=1;
                       	c=[];
                       	$this.set_prompt("[[;GREEN;]"+text+": ]");
                       	console.log(etape);
                       	}else{
                       		$this.echo("[[;BLUE;]Chargement en cours ... ]");
                       		window.location.href=window.location.href;
					 		window.location.reload();
                       	}
                       });


                }
            	console.log("ici");
                  etape+=1;
            }else if (mode=="i") {
            	
				if (etape==2) {
                    this.set_prompt("[[;GREEN;]Password > ]");
                    c.push(command);
                    this.set_mask(true);
                }else if (etape==3){
                	  this.set_prompt("[[;GREEN;]Email > ]");
                    c.push(command);
                }
                else if (etape==4) {
                   // CONNEXION AJAX 
                   c.push(command);
                   this.pause(false);
                   $this=this;
                   create(c[0],c[1],c[2],function(d){
                       	if("response" in d){
					$this.echo("[[;BLUE;]Compte crée... ]");
					etape+=1000;
					setTimeout(function(){
						conexion(c[0],c[1],function(d){
							if("response" in d){
							$this.echo("[[;BLUE;]Chargement en cours ... ]");
								window.location.href=window.location.href;
								 window.location.reload();
							}else{
								etape=1;
                       			c=[];
								alert(d.description);
								$this.resume();
								etape=1;
                    			c=[];
								$this.set_prompt("[[;GREEN;]"+text+": ]");
                    		   	console.log(etape);
							}
						});
					}, 2000);
					
				}else{
				
					alert(d.description);
					$this.resume();
					etape=1;
                    c=[];
					$this.set_prompt("[[;GREEN;]"+text+": ]");
                       	console.log(etape);
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
        greetings: 'ctrl+c pour annuler',
        name: 'js_demo',
        prompt: "[[;GREEN;]"+text+": ]"
    });
    function isCommandPressed(event) {
      return event.metaKey || event.ctrlKey;
    }
  $(window).bind('keypress', function(event) {
    console.log(event);
  if((event.which==3 || event.which==99) && isCommandPressed(event)) {
	    etape = 1;
	    mode="o";
	    c=[];
	    g.set_prompt("[[;GREEN;]"+text+": ]");
    g.reset();
    g.set_mask(false);
    
    return false;
  }
  

  
});
$(".terminal-wrapper").css("height","100vh");
  
  $(".clipboard").bind('keypress', function(event) {
	    console.log(event);
	    console.log(event.which);
	    console.log(isCommandPressed(event));
	  if((event.which==3 || event.which==99) && isCommandPressed(event)) {
		  text = "Connexion (c) ou Inscription (i)";
		    etape = 1;
		    mode="o";
		    c=[];
		    g.set_prompt("[[;GREEN;]"+text+": ]");
	    g.reset();
	    g.set_mask(false);

	    
	    return false;
	  }
	  

	  
	});
});