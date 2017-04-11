me=null;
messages_list=null;
env.messages_list=messages_list;
env.me=me;

$(function(){
	
	env.user.listusers({},function(d){
		console.log(d);
		a=new Mes(d);
		me=a.users;
		env.me=me;
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
    	messages_list=a.mess;
		env.messages_list=messages_list;
    	
		$items=a.HTML()
		$(".grid").append($items).masonry( 'appended', $items ).masonry();
		
		setTimeout(function(){
			env.highlight.apply();
			$(".title_mess").click(function(){
				id=$(this).attr('data-index');
				console.log(id);
				console.log(messages_list)
				console.log(messages_list[id])
				
				$("#postModalLecture").attr("data-index",id);
				$("#postModalLecture").toggleClass("hidden");
				
				lg=messages_list[id].language;
				if(lg=="c") {
					lg="c_cpp";
				}
				setSessionMode2(lg);
				editor2.setValue(_.unescape(messages_list[id].text),-1);
				
				$("#modal_desc").html(messages_list[id].description);
				$("#modal_title").html(messages_list[id].title);
				$("#modal_user").html(messages_list[id].login);
				$("#likes").replace_motif("nb_likes",messages_list[id].nb_like);
				$("#commentaire2").replace_motif("nb_coms",messages_list[id].nb_comments);
				$("#likes").attr('selectedf',"false");
				
				env.messages_list[id].getLikes(env,id,function(j){
			
					if ( env.func_tools.inArray(env.me[0].id, env.func_tools.inObjToArr(j,"id_user"))) {
						$("#likes").css("background-color","blue");
						$("#likes").attr('selectedf',"true");
					}
				});
				
				
				editor2.clearSelection();
			});
		},1000);
		
  }
	});
}
init();

})