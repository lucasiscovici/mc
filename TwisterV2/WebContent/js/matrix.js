env.messages_list=null;
env.me=null;

function suppressionPost(idp){
	env.post.removepost({id:idp},
			function(d){
				if("response" in d){
					env.func_tools.reload(d);
				}
	})
}
$(function(){
	
	env.user.listusers({},function(d){
		console.log(d);
		a=new Mes(d);
		env.me=a.users;
	});

o=0;	


function init(){
	dd ={};
	if(fd=="posts") {
		dd={}
	}else{
		dd= {
				type:"TOTAL"
			};
	}
	
	env.post.listposts(dd,function(d){
		console.log(d);
		if ("response" in d) {
			
    	a=new Messages(d);
		env.messages_list=a.mess;
    	
		$items=a.HTML()
		$(".grid").append($items).masonry( 'appended', $items ).masonry();
		
		setTimeout(function(){
			
			env.highlight.apply();
			
			$(".title_mess").click(function(){
				
				id=$(this).attr('data-index');
				
				
				console.log(id);
				console.log(env.messages_list)
				console.log(env.messages_list.get(id));
				mess=env.messages_list.get(id);
				mess.reload();
				$("#postModalLecture").attr("data-index",id);
				
				$("#postModalLecture").toggleClass("hidden");
				
				lg=messages_list[id].language;
				if(lg=="c") {
					lg="c_cpp";
				}
				setSessionMode2(lg);
				editor2.setValue(_.unescape(mess.text),-1);
				
				if(mess.description==null){
					$("#modal_desc").parent().hide();
				}else{
				$("#modal_desc").html(mess.description);
				}
				
				$("#modal_title").html(mess.title);
				$("#modal_user").html(mess.login);
				$("#likep").html(mess.nb_like);
				$("#comsp").html(mess.nb_comments);
				$("#likes").attr('selectedf',"false");
				$("#likes").css("background-color","black");
				
				mess.getLikes(env,id,function(j){
					
					if ( env.func_tools.inArray(env.me.get(0).id, env.func_tools.inObjToArr(j,"id_user"))) {
						$("#likes").css("background-color","blue");
						$("#likes").attr('selectedf',"true");
					}
					console.log(env.func_tools.inObjToArr(j,"id_user").length);
				});
				
				$("#modalspan").replace_motif("index",id);
				editor2.clearSelection();
			});
		},1000);
		
  }
	});
}
init();

})